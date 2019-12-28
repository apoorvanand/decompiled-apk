package androidx.browser.customtabs;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.ICustomTabsService;
import androidx.collection.ArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class CustomTabsService extends Service {
    public static final String ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService";
    public static final String KEY_URL = "android.support.customtabs.otherurls.URL";
    public static final int RELATION_HANDLE_ALL_URLS = 2;
    public static final int RELATION_USE_AS_ORIGIN = 1;
    public static final int RESULT_FAILURE_DISALLOWED = -1;
    public static final int RESULT_FAILURE_MESSAGING_ERROR = -3;
    public static final int RESULT_FAILURE_REMOTE_ERROR = -2;
    public static final int RESULT_SUCCESS = 0;
    final Map<IBinder, IBinder.DeathRecipient> a = new ArrayMap();
    private ICustomTabsService.Stub mBinder = new ICustomTabsService.Stub() {
        public Bundle extraCommand(String str, Bundle bundle) {
            return CustomTabsService.this.a(str, bundle);
        }

        public boolean mayLaunchUrl(ICustomTabsCallback iCustomTabsCallback, Uri uri, Bundle bundle, List<Bundle> list) {
            return CustomTabsService.this.a(new CustomTabsSessionToken(iCustomTabsCallback), uri, bundle, list);
        }

        public boolean newSession(ICustomTabsCallback iCustomTabsCallback) {
            final CustomTabsSessionToken customTabsSessionToken = new CustomTabsSessionToken(iCustomTabsCallback);
            try {
                AnonymousClass1 r2 = new IBinder.DeathRecipient() {
                    public void binderDied() {
                        CustomTabsService.this.a(customTabsSessionToken);
                    }
                };
                synchronized (CustomTabsService.this.a) {
                    iCustomTabsCallback.asBinder().linkToDeath(r2, 0);
                    CustomTabsService.this.a.put(iCustomTabsCallback.asBinder(), r2);
                }
                return CustomTabsService.this.b(customTabsSessionToken);
            } catch (RemoteException unused) {
                return false;
            }
        }

        public int postMessage(ICustomTabsCallback iCustomTabsCallback, String str, Bundle bundle) {
            return CustomTabsService.this.a(new CustomTabsSessionToken(iCustomTabsCallback), str, bundle);
        }

        public boolean requestPostMessageChannel(ICustomTabsCallback iCustomTabsCallback, Uri uri) {
            return CustomTabsService.this.a(new CustomTabsSessionToken(iCustomTabsCallback), uri);
        }

        public boolean updateVisuals(ICustomTabsCallback iCustomTabsCallback, Bundle bundle) {
            return CustomTabsService.this.a(new CustomTabsSessionToken(iCustomTabsCallback), bundle);
        }

        public boolean validateRelationship(ICustomTabsCallback iCustomTabsCallback, int i, Uri uri, Bundle bundle) {
            return CustomTabsService.this.a(new CustomTabsSessionToken(iCustomTabsCallback), i, uri, bundle);
        }

        public boolean warmup(long j) {
            return CustomTabsService.this.a(j);
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface Relation {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
    }

    /* access modifiers changed from: protected */
    public abstract int a(CustomTabsSessionToken customTabsSessionToken, String str, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract Bundle a(String str, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract boolean a(long j);

    /* access modifiers changed from: protected */
    public boolean a(CustomTabsSessionToken customTabsSessionToken) {
        try {
            synchronized (this.a) {
                IBinder a2 = customTabsSessionToken.a();
                a2.unlinkToDeath(this.a.get(a2), 0);
                this.a.remove(a2);
            }
            return true;
        } catch (NoSuchElementException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(CustomTabsSessionToken customTabsSessionToken, int i, Uri uri, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract boolean a(CustomTabsSessionToken customTabsSessionToken, Uri uri);

    /* access modifiers changed from: protected */
    public abstract boolean a(CustomTabsSessionToken customTabsSessionToken, Uri uri, Bundle bundle, List<Bundle> list);

    /* access modifiers changed from: protected */
    public abstract boolean a(CustomTabsSessionToken customTabsSessionToken, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract boolean b(CustomTabsSessionToken customTabsSessionToken);

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
