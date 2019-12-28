package com.facebook.internal;

import android.app.Activity;
import android.util.Log;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookDialog;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import java.util.Iterator;
import java.util.List;

public abstract class FacebookDialogBase<CONTENT, RESULT> implements FacebookDialog<CONTENT, RESULT> {
    private static final String TAG = "FacebookDialog";
    protected static final Object a = new Object();
    private final Activity activity;
    private final FragmentWrapper fragmentWrapper;
    private List<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> modeHandlers;
    private int requestCode;

    protected abstract class ModeHandler {
        protected ModeHandler() {
        }

        public abstract boolean canShow(CONTENT content, boolean z);

        public abstract AppCall createAppCall(CONTENT content);

        public Object getMode() {
            return FacebookDialogBase.a;
        }
    }

    protected FacebookDialogBase(Activity activity2, int i) {
        Validate.notNull(activity2, "activity");
        this.activity = activity2;
        this.fragmentWrapper = null;
        this.requestCode = i;
    }

    protected FacebookDialogBase(FragmentWrapper fragmentWrapper2, int i) {
        Validate.notNull(fragmentWrapper2, "fragmentWrapper");
        this.fragmentWrapper = fragmentWrapper2;
        this.activity = null;
        this.requestCode = i;
        if (fragmentWrapper2.getActivity() == null) {
            throw new IllegalArgumentException("Cannot use a fragment that is not attached to an activity");
        }
    }

    private List<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> cachedModeHandlers() {
        if (this.modeHandlers == null) {
            this.modeHandlers = b();
        }
        return this.modeHandlers;
    }

    private AppCall createAppCallForMode(CONTENT content, Object obj) {
        boolean z = obj == a;
        AppCall appCall = null;
        Iterator<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> it = cachedModeHandlers().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ModeHandler next = it.next();
            if ((z || Utility.areObjectsEqual(next.getMode(), obj)) && next.canShow(content, true)) {
                try {
                    appCall = next.createAppCall(content);
                    break;
                } catch (FacebookException e) {
                    appCall = c();
                    DialogPresenter.setupAppCallForValidationError(appCall, e);
                }
            }
        }
        if (appCall != null) {
            return appCall;
        }
        AppCall c = c();
        DialogPresenter.setupAppCallForCannotShowError(c);
        return c;
    }

    /* access modifiers changed from: protected */
    public Activity a() {
        Activity activity2 = this.activity;
        if (activity2 != null) {
            return activity2;
        }
        FragmentWrapper fragmentWrapper2 = this.fragmentWrapper;
        if (fragmentWrapper2 != null) {
            return fragmentWrapper2.getActivity();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        if (!FacebookSdk.isFacebookRequestCode(i)) {
            this.requestCode = i;
            return;
        }
        throw new IllegalArgumentException("Request code " + i + " cannot be within the range reserved by the Facebook SDK.");
    }

    /* access modifiers changed from: protected */
    public abstract void a(CallbackManagerImpl callbackManagerImpl, FacebookCallback<RESULT> facebookCallback);

    /* access modifiers changed from: protected */
    public boolean a(CONTENT content, Object obj) {
        boolean z = obj == a;
        for (ModeHandler next : cachedModeHandlers()) {
            if ((z || Utility.areObjectsEqual(next.getMode(), obj)) && next.canShow(content, false)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract List<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> b();

    /* access modifiers changed from: protected */
    public void b(CONTENT content, Object obj) {
        AppCall createAppCallForMode = createAppCallForMode(content, obj);
        if (createAppCallForMode != null) {
            FragmentWrapper fragmentWrapper2 = this.fragmentWrapper;
            if (fragmentWrapper2 != null) {
                DialogPresenter.present(createAppCallForMode, fragmentWrapper2);
            } else {
                DialogPresenter.present(createAppCallForMode, this.activity);
            }
        } else {
            Log.e(TAG, "No code path should ever result in a null appCall");
            if (FacebookSdk.isDebugEnabled()) {
                throw new IllegalStateException("No code path should ever result in a null appCall");
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract AppCall c();

    public boolean canShow(CONTENT content) {
        return a(content, a);
    }

    public int getRequestCode() {
        return this.requestCode;
    }

    public final void registerCallback(CallbackManager callbackManager, FacebookCallback<RESULT> facebookCallback) {
        if (callbackManager instanceof CallbackManagerImpl) {
            a((CallbackManagerImpl) callbackManager, facebookCallback);
            return;
        }
        throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
    }

    public final void registerCallback(CallbackManager callbackManager, FacebookCallback<RESULT> facebookCallback, int i) {
        a(i);
        registerCallback(callbackManager, facebookCallback);
    }

    public void show(CONTENT content) {
        b(content, a);
    }
}
