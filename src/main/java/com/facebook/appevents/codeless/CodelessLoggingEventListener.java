package com.facebook.appevents.codeless;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.appevents.codeless.internal.EventBinding;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.appevents.internal.AppEventUtility;
import java.lang.ref.WeakReference;

public class CodelessLoggingEventListener {
    /* access modifiers changed from: private */
    public static final String TAG = CodelessLoggingEventListener.class.getCanonicalName();

    public static class AutoLoggingAccessibilityDelegate extends View.AccessibilityDelegate {
        protected boolean a = false;
        private int accessibilityEventType;
        private View.AccessibilityDelegate existingDelegate;
        private WeakReference<View> hostView;
        private EventBinding mapping;
        private WeakReference<View> rootView;
        private boolean supportCodelessLogging = false;

        public AutoLoggingAccessibilityDelegate() {
        }

        public AutoLoggingAccessibilityDelegate(EventBinding eventBinding, View view, View view2) {
            int i;
            if (eventBinding != null && view != null && view2 != null) {
                this.existingDelegate = ViewHierarchy.getExistingDelegate(view2);
                this.mapping = eventBinding;
                this.hostView = new WeakReference<>(view2);
                this.rootView = new WeakReference<>(view);
                EventBinding.ActionType type = eventBinding.getType();
                switch (eventBinding.getType()) {
                    case CLICK:
                        this.accessibilityEventType = 1;
                        break;
                    case SELECTED:
                        i = 4;
                        break;
                    case TEXT_CHANGED:
                        i = 16;
                        break;
                    default:
                        throw new FacebookException("Unsupported action type: " + type.toString());
                }
                this.accessibilityEventType = i;
                this.supportCodelessLogging = true;
            }
        }

        private void logEvent() {
            final String eventName = this.mapping.getEventName();
            final Bundle parameters = CodelessMatcher.getParameters(this.mapping, (View) this.rootView.get(), (View) this.hostView.get());
            if (parameters.containsKey(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM)) {
                parameters.putDouble(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM, AppEventUtility.normalizePrice(parameters.getString(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM)));
            }
            parameters.putString(Constants.IS_CODELESS_EVENT_KEY, AppEventsConstants.EVENT_PARAM_VALUE_YES);
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    AppEventsLogger.newLogger(FacebookSdk.getApplicationContext()).logEvent(eventName, parameters);
                }
            });
        }

        public boolean getSupportButtonIndexing() {
            return this.a;
        }

        public boolean getSupportCodelessLogging() {
            return this.supportCodelessLogging;
        }

        public void sendAccessibilityEvent(View view, int i) {
            if (i == -1) {
                Log.e(CodelessLoggingEventListener.TAG, "Unsupported action type");
            }
            if (i == this.accessibilityEventType) {
                View.AccessibilityDelegate accessibilityDelegate = this.existingDelegate;
                if (accessibilityDelegate != null && !(accessibilityDelegate instanceof AutoLoggingAccessibilityDelegate)) {
                    accessibilityDelegate.sendAccessibilityEvent(view, i);
                }
                logEvent();
            }
        }
    }

    public static AutoLoggingAccessibilityDelegate getAccessibilityDelegate(EventBinding eventBinding, View view, View view2) {
        return new AutoLoggingAccessibilityDelegate(eventBinding, view, view2);
    }
}
