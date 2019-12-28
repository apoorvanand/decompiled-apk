package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.util.List;

@RequiresApi(21)
class MediaBrowserCompatApi21 {

    interface ConnectionCallback {
        void onConnected();

        void onConnectionFailed();

        void onConnectionSuspended();
    }

    static class ConnectionCallbackProxy<T extends ConnectionCallback> extends MediaBrowser.ConnectionCallback {
        protected final T a;

        public ConnectionCallbackProxy(T t) {
            this.a = t;
        }

        public void onConnected() {
            this.a.onConnected();
        }

        public void onConnectionFailed() {
            this.a.onConnectionFailed();
        }

        public void onConnectionSuspended() {
            this.a.onConnectionSuspended();
        }
    }

    static class MediaItem {
        private MediaItem() {
        }

        public static Object getDescription(Object obj) {
            return ((MediaBrowser.MediaItem) obj).getDescription();
        }

        public static int getFlags(Object obj) {
            return ((MediaBrowser.MediaItem) obj).getFlags();
        }
    }

    interface SubscriptionCallback {
        void onChildrenLoaded(@NonNull String str, List<?> list);

        void onError(@NonNull String str);
    }

    static class SubscriptionCallbackProxy<T extends SubscriptionCallback> extends MediaBrowser.SubscriptionCallback {
        protected final T a;

        public SubscriptionCallbackProxy(T t) {
            this.a = t;
        }

        public void onChildrenLoaded(@NonNull String str, List<MediaBrowser.MediaItem> list) {
            this.a.onChildrenLoaded(str, list);
        }

        public void onError(@NonNull String str) {
            this.a.onError(str);
        }
    }

    private MediaBrowserCompatApi21() {
    }

    public static void connect(Object obj) {
        ((MediaBrowser) obj).connect();
    }

    public static Object createBrowser(Context context, ComponentName componentName, Object obj, Bundle bundle) {
        return new MediaBrowser(context, componentName, (MediaBrowser.ConnectionCallback) obj, bundle);
    }

    public static Object createConnectionCallback(ConnectionCallback connectionCallback) {
        return new ConnectionCallbackProxy(connectionCallback);
    }

    public static Object createSubscriptionCallback(SubscriptionCallback subscriptionCallback) {
        return new SubscriptionCallbackProxy(subscriptionCallback);
    }

    public static void disconnect(Object obj) {
        ((MediaBrowser) obj).disconnect();
    }

    public static Bundle getExtras(Object obj) {
        return ((MediaBrowser) obj).getExtras();
    }

    public static String getRoot(Object obj) {
        return ((MediaBrowser) obj).getRoot();
    }

    public static ComponentName getServiceComponent(Object obj) {
        return ((MediaBrowser) obj).getServiceComponent();
    }

    public static Object getSessionToken(Object obj) {
        return ((MediaBrowser) obj).getSessionToken();
    }

    public static boolean isConnected(Object obj) {
        return ((MediaBrowser) obj).isConnected();
    }

    public static void subscribe(Object obj, String str, Object obj2) {
        ((MediaBrowser) obj).subscribe(str, (MediaBrowser.SubscriptionCallback) obj2);
    }

    public static void unsubscribe(Object obj, String str) {
        ((MediaBrowser) obj).unsubscribe(str);
    }
}
