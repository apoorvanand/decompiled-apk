package com.facebook.appevents.codeless;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.Nullable;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.codeless.CodelessLoggingEventListener;
import com.facebook.appevents.codeless.RCTCodelessLoggingEventListener;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.appevents.codeless.internal.EventBinding;
import com.facebook.appevents.codeless.internal.ParameterComponent;
import com.facebook.appevents.codeless.internal.PathComponent;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.InternalSettings;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CodelessMatcher {
    private static final String CURRENT_CLASS_NAME = ".";
    private static final String PARENT_CLASS_NAME = "..";
    /* access modifiers changed from: private */
    public static final String TAG = CodelessMatcher.class.getCanonicalName();
    private Set<Activity> activitiesSet = new HashSet();
    private HashMap<String, String> delegateMap = new HashMap<>();
    private final Handler uiThreadHandler = new Handler(Looper.getMainLooper());
    private Set<ViewMatcher> viewMatchers = new HashSet();

    public static class MatchedView {
        private WeakReference<View> view;
        private String viewMapKey;

        public MatchedView(View view2, String str) {
            this.view = new WeakReference<>(view2);
            this.viewMapKey = str;
        }

        @Nullable
        public View getView() {
            WeakReference<View> weakReference = this.view;
            if (weakReference == null) {
                return null;
            }
            return (View) weakReference.get();
        }

        public String getViewMapKey() {
            return this.viewMapKey;
        }
    }

    protected static class ViewMatcher implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, Runnable {
        private final String activityName;
        private HashMap<String, String> delegateMap;
        @Nullable
        private List<EventBinding> eventBindings;
        private final Handler handler;
        private WeakReference<View> rootView;

        public ViewMatcher(View view, Handler handler2, HashMap<String, String> hashMap, String str) {
            this.rootView = new WeakReference<>(view);
            this.handler = handler2;
            this.delegateMap = hashMap;
            this.activityName = str;
            this.handler.postDelayed(this, 200);
        }

        private void attachListener(MatchedView matchedView, View view, EventBinding eventBinding) {
            if (eventBinding != null) {
                try {
                    View view2 = matchedView.getView();
                    if (view2 != null) {
                        View findRCTRootView = ViewHierarchy.findRCTRootView(view2);
                        if (findRCTRootView != null && ViewHierarchy.isRCTButton(view2, findRCTRootView)) {
                            attachRCTListener(matchedView, view, findRCTRootView, eventBinding);
                        } else if (!view2.getClass().getName().startsWith("com.facebook.react")) {
                            String viewMapKey = matchedView.getViewMapKey();
                            View.AccessibilityDelegate existingDelegate = ViewHierarchy.getExistingDelegate(view2);
                            boolean z = true;
                            boolean z2 = existingDelegate != null;
                            boolean z3 = z2 && (existingDelegate instanceof CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate);
                            if (!z3 || !((CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate) existingDelegate).getSupportCodelessLogging()) {
                                z = false;
                            }
                            if (this.delegateMap.containsKey(viewMapKey)) {
                                return;
                            }
                            if (!z2 || !z3 || !z) {
                                view2.setAccessibilityDelegate(CodelessLoggingEventListener.getAccessibilityDelegate(eventBinding, view, view2));
                                this.delegateMap.put(viewMapKey, eventBinding.getEventName());
                            }
                        }
                    }
                } catch (FacebookException e) {
                    Log.e(CodelessMatcher.TAG, "Failed to attach auto logging event listener.", e);
                }
            }
        }

        private void attachRCTListener(MatchedView matchedView, View view, View view2, EventBinding eventBinding) {
            View view3;
            if (eventBinding != null && (view3 = matchedView.getView()) != null && ViewHierarchy.isRCTButton(view3, view2)) {
                String viewMapKey = matchedView.getViewMapKey();
                View.OnTouchListener existingOnTouchListener = ViewHierarchy.getExistingOnTouchListener(view3);
                boolean z = true;
                boolean z2 = existingOnTouchListener != null;
                boolean z3 = z2 && (existingOnTouchListener instanceof RCTCodelessLoggingEventListener.AutoLoggingOnTouchListener);
                if (!z3 || !((RCTCodelessLoggingEventListener.AutoLoggingOnTouchListener) existingOnTouchListener).getSupportCodelessLogging()) {
                    z = false;
                }
                if (this.delegateMap.containsKey(viewMapKey)) {
                    return;
                }
                if (!z2 || !z3 || !z) {
                    view3.setOnTouchListener(RCTCodelessLoggingEventListener.getOnTouchListener(eventBinding, view, view3));
                    this.delegateMap.put(viewMapKey, eventBinding.getEventName());
                }
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:27:0x0099  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.util.List<com.facebook.appevents.codeless.CodelessMatcher.MatchedView> findViewByPath(com.facebook.appevents.codeless.internal.EventBinding r8, android.view.View r9, java.util.List<com.facebook.appevents.codeless.internal.PathComponent> r10, int r11, int r12, java.lang.String r13) {
            /*
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                r0.append(r13)
                java.lang.String r13 = "."
                r0.append(r13)
                java.lang.String r13 = java.lang.String.valueOf(r12)
                r0.append(r13)
                java.lang.String r13 = r0.toString()
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                if (r9 != 0) goto L_0x0020
                return r0
            L_0x0020:
                int r1 = r10.size()
                r2 = 0
                if (r11 < r1) goto L_0x0030
                com.facebook.appevents.codeless.CodelessMatcher$MatchedView r12 = new com.facebook.appevents.codeless.CodelessMatcher$MatchedView
                r12.<init>(r9, r13)
            L_0x002c:
                r0.add(r12)
                goto L_0x0095
            L_0x0030:
                java.lang.Object r1 = r10.get(r11)
                com.facebook.appevents.codeless.internal.PathComponent r1 = (com.facebook.appevents.codeless.internal.PathComponent) r1
                java.lang.String r3 = r1.className
                java.lang.String r4 = ".."
                boolean r3 = r3.equals(r4)
                if (r3 == 0) goto L_0x006d
                android.view.ViewParent r9 = r9.getParent()
                boolean r12 = r9 instanceof android.view.ViewGroup
                if (r12 == 0) goto L_0x006c
                android.view.ViewGroup r9 = (android.view.ViewGroup) r9
                java.util.List r9 = findVisibleChildren(r9)
                int r12 = r9.size()
                r7 = 0
            L_0x0053:
                if (r7 >= r12) goto L_0x006c
                java.lang.Object r1 = r9.get(r7)
                r2 = r1
                android.view.View r2 = (android.view.View) r2
                int r4 = r11 + 1
                r1 = r8
                r3 = r10
                r5 = r7
                r6 = r13
                java.util.List r1 = findViewByPath(r1, r2, r3, r4, r5, r6)
                r0.addAll(r1)
                int r7 = r7 + 1
                goto L_0x0053
            L_0x006c:
                return r0
            L_0x006d:
                java.lang.String r3 = r1.className
                java.lang.String r4 = "."
                boolean r3 = r3.equals(r4)
                if (r3 == 0) goto L_0x0080
                com.facebook.appevents.codeless.CodelessMatcher$MatchedView r8 = new com.facebook.appevents.codeless.CodelessMatcher$MatchedView
                r8.<init>(r9, r13)
                r0.add(r8)
                return r0
            L_0x0080:
                boolean r12 = isTheSameView(r9, r1, r12)
                if (r12 != 0) goto L_0x0087
                return r0
            L_0x0087:
                int r12 = r10.size()
                int r12 = r12 + -1
                if (r11 != r12) goto L_0x0095
                com.facebook.appevents.codeless.CodelessMatcher$MatchedView r12 = new com.facebook.appevents.codeless.CodelessMatcher$MatchedView
                r12.<init>(r9, r13)
                goto L_0x002c
            L_0x0095:
                boolean r12 = r9 instanceof android.view.ViewGroup
                if (r12 == 0) goto L_0x00bd
                android.view.ViewGroup r9 = (android.view.ViewGroup) r9
                java.util.List r9 = findVisibleChildren(r9)
                int r12 = r9.size()
                r7 = 0
            L_0x00a4:
                if (r7 >= r12) goto L_0x00bd
                java.lang.Object r1 = r9.get(r7)
                r2 = r1
                android.view.View r2 = (android.view.View) r2
                int r4 = r11 + 1
                r1 = r8
                r3 = r10
                r5 = r7
                r6 = r13
                java.util.List r1 = findViewByPath(r1, r2, r3, r4, r5, r6)
                r0.addAll(r1)
                int r7 = r7 + 1
                goto L_0x00a4
            L_0x00bd:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.CodelessMatcher.ViewMatcher.findViewByPath(com.facebook.appevents.codeless.internal.EventBinding, android.view.View, java.util.List, int, int, java.lang.String):java.util.List");
        }

        private static List<View> findVisibleChildren(ViewGroup viewGroup) {
            ArrayList arrayList = new ArrayList();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    arrayList.add(childAt);
                }
            }
            return arrayList;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
            if (r3.getClass().getSimpleName().equals(r5[r5.length - 1]) == false) goto L_0x0043;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static boolean isTheSameView(android.view.View r3, com.facebook.appevents.codeless.internal.PathComponent r4, int r5) {
            /*
                int r0 = r4.index
                r1 = 0
                r2 = -1
                if (r0 == r2) goto L_0x000b
                int r0 = r4.index
                if (r5 == r0) goto L_0x000b
                return r1
            L_0x000b:
                java.lang.Class r5 = r3.getClass()
                java.lang.String r5 = r5.getCanonicalName()
                java.lang.String r0 = r4.className
                boolean r5 = r5.equals(r0)
                r0 = 1
                if (r5 != 0) goto L_0x0044
                java.lang.String r5 = r4.className
                java.lang.String r2 = ".*android\\..*"
                boolean r5 = r5.matches(r2)
                if (r5 == 0) goto L_0x0043
                java.lang.String r5 = r4.className
                java.lang.String r2 = "\\."
                java.lang.String[] r5 = r5.split(r2)
                int r2 = r5.length
                if (r2 <= 0) goto L_0x0043
                int r2 = r5.length
                int r2 = r2 - r0
                r5 = r5[r2]
                java.lang.Class r2 = r3.getClass()
                java.lang.String r2 = r2.getSimpleName()
                boolean r5 = r2.equals(r5)
                if (r5 != 0) goto L_0x0044
            L_0x0043:
                return r1
            L_0x0044:
                int r5 = r4.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r2 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.ID
                int r2 = r2.getValue()
                r5 = r5 & r2
                if (r5 <= 0) goto L_0x0058
                int r5 = r4.id
                int r2 = r3.getId()
                if (r5 == r2) goto L_0x0058
                return r1
            L_0x0058:
                int r5 = r4.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r2 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.TEXT
                int r2 = r2.getValue()
                r5 = r5 & r2
                if (r5 <= 0) goto L_0x0070
                java.lang.String r5 = r4.text
                java.lang.String r2 = com.facebook.appevents.codeless.internal.ViewHierarchy.getTextOfView(r3)
                boolean r5 = r5.equals(r2)
                if (r5 != 0) goto L_0x0070
                return r1
            L_0x0070:
                int r5 = r4.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r2 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.DESCRIPTION
                int r2 = r2.getValue()
                r5 = r5 & r2
                if (r5 <= 0) goto L_0x0095
                java.lang.String r5 = r4.description
                java.lang.CharSequence r2 = r3.getContentDescription()
                if (r2 != 0) goto L_0x0086
                java.lang.String r2 = ""
                goto L_0x008e
            L_0x0086:
                java.lang.CharSequence r2 = r3.getContentDescription()
                java.lang.String r2 = java.lang.String.valueOf(r2)
            L_0x008e:
                boolean r5 = r5.equals(r2)
                if (r5 != 0) goto L_0x0095
                return r1
            L_0x0095:
                int r5 = r4.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r2 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.HINT
                int r2 = r2.getValue()
                r5 = r5 & r2
                if (r5 <= 0) goto L_0x00ad
                java.lang.String r5 = r4.hint
                java.lang.String r2 = com.facebook.appevents.codeless.internal.ViewHierarchy.getHintOfView(r3)
                boolean r5 = r5.equals(r2)
                if (r5 != 0) goto L_0x00ad
                return r1
            L_0x00ad:
                int r5 = r4.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r2 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.TAG
                int r2 = r2.getValue()
                r5 = r5 & r2
                if (r5 <= 0) goto L_0x00d2
                java.lang.String r4 = r4.tag
                java.lang.Object r5 = r3.getTag()
                if (r5 != 0) goto L_0x00c3
                java.lang.String r3 = ""
                goto L_0x00cb
            L_0x00c3:
                java.lang.Object r3 = r3.getTag()
                java.lang.String r3 = java.lang.String.valueOf(r3)
            L_0x00cb:
                boolean r3 = r4.equals(r3)
                if (r3 != 0) goto L_0x00d2
                return r1
            L_0x00d2:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.CodelessMatcher.ViewMatcher.isTheSameView(android.view.View, com.facebook.appevents.codeless.internal.PathComponent, int):boolean");
        }

        private void startMatch() {
            if (this.eventBindings != null && this.rootView.get() != null) {
                for (int i = 0; i < this.eventBindings.size(); i++) {
                    findView(this.eventBindings.get(i), (View) this.rootView.get());
                }
            }
        }

        public void findView(EventBinding eventBinding, View view) {
            if (eventBinding != null && view != null) {
                if (TextUtils.isEmpty(eventBinding.getActivityName()) || eventBinding.getActivityName().equals(this.activityName)) {
                    List<PathComponent> viewPath = eventBinding.getViewPath();
                    if (viewPath.size() <= 25) {
                        for (MatchedView attachListener : findViewByPath(eventBinding, view, viewPath, 0, -1, this.activityName)) {
                            attachListener(attachListener, view, eventBinding);
                        }
                    }
                }
            }
        }

        public void onGlobalLayout() {
            startMatch();
        }

        public void onScrollChanged() {
            startMatch();
        }

        public void run() {
            View view;
            FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
            if (appSettingsWithoutQuery != null && appSettingsWithoutQuery.getCodelessEventsEnabled()) {
                this.eventBindings = EventBinding.parseArray(appSettingsWithoutQuery.getEventBindings());
                if (this.eventBindings != null && (view = (View) this.rootView.get()) != null) {
                    ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.addOnGlobalLayoutListener(this);
                        viewTreeObserver.addOnScrollChangedListener(this);
                    }
                    startMatch();
                }
            }
        }
    }

    public static Bundle getParameters(EventBinding eventBinding, View view, View view2) {
        List<ParameterComponent> viewParameters;
        String str;
        int i;
        int i2;
        List<PathComponent> list;
        View view3;
        EventBinding eventBinding2;
        Bundle bundle = new Bundle();
        if (!(eventBinding == null || (viewParameters = eventBinding.getViewParameters()) == null)) {
            for (ParameterComponent next : viewParameters) {
                if (next.value == null || next.value.length() <= 0) {
                    if (next.path.size() > 0) {
                        if (next.pathType.equals(Constants.PATH_TYPE_RELATIVE)) {
                            list = next.path;
                            i2 = 0;
                            i = -1;
                            str = view2.getClass().getSimpleName();
                            eventBinding2 = eventBinding;
                            view3 = view2;
                        } else {
                            list = next.path;
                            i2 = 0;
                            i = -1;
                            str = view.getClass().getSimpleName();
                            eventBinding2 = eventBinding;
                            view3 = view;
                        }
                        Iterator<MatchedView> it = ViewMatcher.findViewByPath(eventBinding2, view3, list, i2, i, str).iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            MatchedView next2 = it.next();
                            if (next2.getView() != null) {
                                String textOfView = ViewHierarchy.getTextOfView(next2.getView());
                                if (textOfView.length() > 0) {
                                    bundle.putString(next.name, textOfView);
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    bundle.putString(next.name, next.value);
                }
            }
        }
        return bundle;
    }

    /* access modifiers changed from: private */
    public void matchViews() {
        for (Activity next : this.activitiesSet) {
            this.viewMatchers.add(new ViewMatcher(next.getWindow().getDecorView().getRootView(), this.uiThreadHandler, this.delegateMap, next.getClass().getSimpleName()));
        }
    }

    private void startTracking() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            matchViews();
        } else {
            this.uiThreadHandler.post(new Runnable() {
                public void run() {
                    CodelessMatcher.this.matchViews();
                }
            });
        }
    }

    public void add(Activity activity) {
        if (!InternalSettings.isUnityApp()) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                this.activitiesSet.add(activity);
                this.delegateMap.clear();
                startTracking();
                return;
            }
            throw new FacebookException("Can't add activity to CodelessMatcher on non-UI thread");
        }
    }

    public void remove(Activity activity) {
        if (!InternalSettings.isUnityApp()) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                this.activitiesSet.remove(activity);
                this.viewMatchers.clear();
                this.delegateMap.clear();
                return;
            }
            throw new FacebookException("Can't remove activity from CodelessMatcher on non-UI thread");
        }
    }
}
