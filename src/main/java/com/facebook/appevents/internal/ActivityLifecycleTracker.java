package com.facebook.appevents.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.codeless.CodelessMatcher;
import com.facebook.appevents.codeless.ViewIndexer;
import com.facebook.appevents.codeless.ViewIndexingTrigger;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.appevents.internal.SourceApplicationInfo;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;

public class ActivityLifecycleTracker {
    private static final String INCORRECT_IMPL_WARNING = "Unexpected activity pause without a matching activity resume. Logging data may be incorrect. Make sure you call activateApp from your Application's onCreate method";
    private static final long INTERRUPTION_THRESHOLD_MILLISECONDS = 1000;
    /* access modifiers changed from: private */
    public static final String TAG = ActivityLifecycleTracker.class.getCanonicalName();
    /* access modifiers changed from: private */
    public static String appId;
    private static final CodelessMatcher codelessMatcher = new CodelessMatcher();
    /* access modifiers changed from: private */
    public static long currentActivityAppearTime;
    /* access modifiers changed from: private */
    public static volatile ScheduledFuture currentFuture;
    /* access modifiers changed from: private */
    public static final Object currentFutureLock = new Object();
    /* access modifiers changed from: private */
    public static volatile SessionInfo currentSession;
    /* access modifiers changed from: private */
    @Nullable
    public static String deviceSessionID = null;
    /* access modifiers changed from: private */
    public static AtomicInteger foregroundActivityCount = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public static Boolean isAppIndexingEnabled = false;
    /* access modifiers changed from: private */
    public static volatile Boolean isCheckingSession = false;
    private static SensorManager sensorManager;
    /* access modifiers changed from: private */
    public static final ScheduledExecutorService singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
    private static AtomicBoolean tracking = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static ViewIndexer viewIndexer;
    private static final ViewIndexingTrigger viewIndexingTrigger = new ViewIndexingTrigger();

    private static void cancelCurrentTask() {
        synchronized (currentFutureLock) {
            if (currentFuture != null) {
                currentFuture.cancel(false);
            }
            currentFuture = null;
        }
    }

    public static void checkCodelessSession(final String str) {
        if (!isCheckingSession.booleanValue()) {
            isCheckingSession = true;
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    boolean z = true;
                    GraphRequest newPostRequest = GraphRequest.newPostRequest((AccessToken) null, String.format(Locale.US, "%s/app_indexing_session", new Object[]{str}), (JSONObject) null, (GraphRequest.Callback) null);
                    Bundle parameters = newPostRequest.getParameters();
                    if (parameters == null) {
                        parameters = new Bundle();
                    }
                    AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(Build.MODEL != null ? Build.MODEL : "");
                    jSONArray.put((attributionIdentifiers == null || attributionIdentifiers.getAndroidAdvertiserId() == null) ? "" : attributionIdentifiers.getAndroidAdvertiserId());
                    jSONArray.put(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    jSONArray.put(AppEventUtility.isEmulator() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    Locale currentLocale = Utility.getCurrentLocale();
                    jSONArray.put(currentLocale.getLanguage() + "_" + currentLocale.getCountry());
                    String jSONArray2 = jSONArray.toString();
                    parameters.putString(Constants.DEVICE_SESSION_ID, ActivityLifecycleTracker.getCurrentDeviceSessionID());
                    parameters.putString(Constants.EXTINFO, jSONArray2);
                    newPostRequest.setParameters(parameters);
                    if (newPostRequest != null) {
                        JSONObject jSONObject = newPostRequest.executeAndWait().getJSONObject();
                        if (jSONObject == null || !jSONObject.optBoolean(Constants.APP_INDEXING_ENABLED, false)) {
                            z = false;
                        }
                        Boolean unused = ActivityLifecycleTracker.isAppIndexingEnabled = Boolean.valueOf(z);
                        if (!ActivityLifecycleTracker.isAppIndexingEnabled.booleanValue()) {
                            String unused2 = ActivityLifecycleTracker.deviceSessionID = null;
                        } else {
                            ActivityLifecycleTracker.viewIndexer.schedule();
                        }
                    }
                    Boolean unused3 = ActivityLifecycleTracker.isCheckingSession = false;
                }
            });
        }
    }

    public static String getCurrentDeviceSessionID() {
        if (deviceSessionID == null) {
            deviceSessionID = UUID.randomUUID().toString();
        }
        return deviceSessionID;
    }

    public static UUID getCurrentSessionGuid() {
        if (currentSession != null) {
            return currentSession.getSessionId();
        }
        return null;
    }

    public static boolean getIsAppIndexingEnabled() {
        return isAppIndexingEnabled.booleanValue();
    }

    /* access modifiers changed from: private */
    public static int getSessionTimeoutInSeconds() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        return appSettingsWithoutQuery == null ? Constants.getDefaultAppEventsSessionTimeoutInSeconds() : appSettingsWithoutQuery.getSessionTimeoutInSeconds();
    }

    public static boolean isTracking() {
        return tracking.get();
    }

    public static void onActivityCreated(Activity activity) {
        System.currentTimeMillis();
        activity.getApplicationContext();
        Utility.getActivityName(activity);
        SourceApplicationInfo.Factory.create(activity);
        singleThreadExecutor.execute(new Runnable() {
            public void run() {
                if (ActivityLifecycleTracker.currentSession == null) {
                    SessionInfo unused = ActivityLifecycleTracker.currentSession = SessionInfo.getStoredSessionInfo();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void onActivityPaused(Activity activity) {
        if (foregroundActivityCount.decrementAndGet() < 0) {
            foregroundActivityCount.set(0);
            Log.w(TAG, INCORRECT_IMPL_WARNING);
        }
        cancelCurrentTask();
        final long currentTimeMillis = System.currentTimeMillis();
        final String activityName = Utility.getActivityName(activity);
        codelessMatcher.remove(activity);
        singleThreadExecutor.execute(new Runnable() {
            public void run() {
                if (ActivityLifecycleTracker.currentSession == null) {
                    SessionInfo unused = ActivityLifecycleTracker.currentSession = new SessionInfo(Long.valueOf(currentTimeMillis), (Long) null);
                }
                ActivityLifecycleTracker.currentSession.setSessionLastEventTime(Long.valueOf(currentTimeMillis));
                if (ActivityLifecycleTracker.foregroundActivityCount.get() <= 0) {
                    AnonymousClass1 r0 = new Runnable() {
                        public void run() {
                            if (ActivityLifecycleTracker.foregroundActivityCount.get() <= 0) {
                                SessionLogger.logDeactivateApp(activityName, ActivityLifecycleTracker.currentSession, ActivityLifecycleTracker.appId);
                                SessionInfo.clearSavedSessionFromDisk();
                                SessionInfo unused = ActivityLifecycleTracker.currentSession = null;
                            }
                            synchronized (ActivityLifecycleTracker.currentFutureLock) {
                                ScheduledFuture unused2 = ActivityLifecycleTracker.currentFuture = null;
                            }
                        }
                    };
                    synchronized (ActivityLifecycleTracker.currentFutureLock) {
                        ScheduledFuture unused2 = ActivityLifecycleTracker.currentFuture = ActivityLifecycleTracker.singleThreadExecutor.schedule(r0, (long) ActivityLifecycleTracker.getSessionTimeoutInSeconds(), TimeUnit.SECONDS);
                    }
                }
                long h = ActivityLifecycleTracker.currentActivityAppearTime;
                long j = 0;
                if (h > 0) {
                    j = (currentTimeMillis - h) / ActivityLifecycleTracker.INTERRUPTION_THRESHOLD_MILLISECONDS;
                }
                AutomaticAnalyticsLogger.logActivityTimeSpentEvent(activityName, j);
                ActivityLifecycleTracker.currentSession.writeSessionToDisk();
            }
        });
        ViewIndexer viewIndexer2 = viewIndexer;
        if (viewIndexer2 != null) {
            viewIndexer2.unschedule();
        }
        SensorManager sensorManager2 = sensorManager;
        if (sensorManager2 != null) {
            sensorManager2.unregisterListener(viewIndexingTrigger);
        }
    }

    public static void onActivityResumed(Activity activity) {
        foregroundActivityCount.incrementAndGet();
        cancelCurrentTask();
        final long currentTimeMillis = System.currentTimeMillis();
        currentActivityAppearTime = currentTimeMillis;
        final String activityName = Utility.getActivityName(activity);
        codelessMatcher.add(activity);
        singleThreadExecutor.execute(new Runnable() {
            public void run() {
                if (ActivityLifecycleTracker.currentSession == null) {
                    SessionInfo unused = ActivityLifecycleTracker.currentSession = new SessionInfo(Long.valueOf(currentTimeMillis), (Long) null);
                    SessionLogger.logActivateApp(activityName, (SourceApplicationInfo) null, ActivityLifecycleTracker.appId);
                } else if (ActivityLifecycleTracker.currentSession.getSessionLastEventTime() != null) {
                    long longValue = currentTimeMillis - ActivityLifecycleTracker.currentSession.getSessionLastEventTime().longValue();
                    if (longValue > ((long) (ActivityLifecycleTracker.getSessionTimeoutInSeconds() * 1000))) {
                        SessionLogger.logDeactivateApp(activityName, ActivityLifecycleTracker.currentSession, ActivityLifecycleTracker.appId);
                        SessionLogger.logActivateApp(activityName, (SourceApplicationInfo) null, ActivityLifecycleTracker.appId);
                        SessionInfo unused2 = ActivityLifecycleTracker.currentSession = new SessionInfo(Long.valueOf(currentTimeMillis), (Long) null);
                    } else if (longValue > ActivityLifecycleTracker.INTERRUPTION_THRESHOLD_MILLISECONDS) {
                        ActivityLifecycleTracker.currentSession.incrementInterruptionCount();
                    }
                }
                ActivityLifecycleTracker.currentSession.setSessionLastEventTime(Long.valueOf(currentTimeMillis));
                ActivityLifecycleTracker.currentSession.writeSessionToDisk();
            }
        });
        Context applicationContext = activity.getApplicationContext();
        final String applicationId = FacebookSdk.getApplicationId();
        final FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(applicationId);
        if (appSettingsWithoutQuery != null && appSettingsWithoutQuery.getCodelessSetupEnabled()) {
            sensorManager = (SensorManager) applicationContext.getSystemService("sensor");
            SensorManager sensorManager2 = sensorManager;
            if (sensorManager2 != null) {
                Sensor defaultSensor = sensorManager2.getDefaultSensor(1);
                viewIndexer = new ViewIndexer(activity);
                viewIndexingTrigger.setOnShakeListener(new ViewIndexingTrigger.OnShakeListener() {
                    public void onShake() {
                        FetchedAppSettings fetchedAppSettings = appSettingsWithoutQuery;
                        if (fetchedAppSettings != null && fetchedAppSettings.getCodelessEventsEnabled()) {
                            ActivityLifecycleTracker.checkCodelessSession(applicationId);
                        }
                    }
                });
                sensorManager.registerListener(viewIndexingTrigger, defaultSensor, 2);
                if (appSettingsWithoutQuery != null && appSettingsWithoutQuery.getCodelessEventsEnabled()) {
                    viewIndexer.schedule();
                }
            }
        }
    }

    public static void startTracking(Application application, String str) {
        if (tracking.compareAndSet(false, true)) {
            appId = str;
            application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityCreated");
                    AppEventUtility.assertIsMainThread();
                    ActivityLifecycleTracker.onActivityCreated(activity);
                }

                public void onActivityDestroyed(Activity activity) {
                    Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityDestroyed");
                }

                public void onActivityPaused(Activity activity) {
                    Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityPaused");
                    AppEventUtility.assertIsMainThread();
                    ActivityLifecycleTracker.onActivityPaused(activity);
                }

                public void onActivityResumed(Activity activity) {
                    Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityResumed");
                    AppEventUtility.assertIsMainThread();
                    ActivityLifecycleTracker.onActivityResumed(activity);
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivitySaveInstanceState");
                }

                public void onActivityStarted(Activity activity) {
                    Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityStarted");
                }

                public void onActivityStopped(Activity activity) {
                    Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityStopped");
                    AppEventsLogger.onContextStop();
                }
            });
        }
    }

    public static void updateAppIndexing(Boolean bool) {
        isAppIndexingEnabled = bool;
    }
}
