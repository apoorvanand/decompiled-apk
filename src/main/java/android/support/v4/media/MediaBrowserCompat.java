package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompatApi21;
import android.support.v4.media.MediaBrowserCompatApi23;
import android.support.v4.media.MediaBrowserCompatApi26;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import androidx.core.app.BundleCompat;
import androidx.media.MediaBrowserCompatUtils;
import androidx.media.MediaBrowserProtocol;
import androidx.media.MediaBrowserServiceCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class MediaBrowserCompat {
    public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
    public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
    public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
    public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    static final boolean a = Log.isLoggable("MediaBrowserCompat", 3);
    private final MediaBrowserImpl mImpl;

    private static class CallbackHandler extends Handler {
        private final WeakReference<MediaBrowserServiceCallbackImpl> mCallbackImplRef;
        private WeakReference<Messenger> mCallbacksMessengerRef;

        CallbackHandler(MediaBrowserServiceCallbackImpl mediaBrowserServiceCallbackImpl) {
            this.mCallbackImplRef = new WeakReference<>(mediaBrowserServiceCallbackImpl);
        }

        /* access modifiers changed from: package-private */
        public void a(Messenger messenger) {
            this.mCallbacksMessengerRef = new WeakReference<>(messenger);
        }

        public void handleMessage(Message message) {
            WeakReference<Messenger> weakReference = this.mCallbacksMessengerRef;
            if (weakReference != null && weakReference.get() != null && this.mCallbackImplRef.get() != null) {
                Bundle data = message.getData();
                MediaSessionCompat.ensureClassLoader(data);
                MediaBrowserServiceCallbackImpl mediaBrowserServiceCallbackImpl = (MediaBrowserServiceCallbackImpl) this.mCallbackImplRef.get();
                Messenger messenger = (Messenger) this.mCallbacksMessengerRef.get();
                try {
                    switch (message.what) {
                        case 1:
                            Bundle bundle = data.getBundle(MediaBrowserProtocol.DATA_ROOT_HINTS);
                            MediaSessionCompat.ensureClassLoader(bundle);
                            mediaBrowserServiceCallbackImpl.onServiceConnected(messenger, data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), (MediaSessionCompat.Token) data.getParcelable(MediaBrowserProtocol.DATA_MEDIA_SESSION_TOKEN), bundle);
                            return;
                        case 2:
                            mediaBrowserServiceCallbackImpl.onConnectionFailed(messenger);
                            return;
                        case 3:
                            Bundle bundle2 = data.getBundle(MediaBrowserProtocol.DATA_OPTIONS);
                            MediaSessionCompat.ensureClassLoader(bundle2);
                            Bundle bundle3 = data.getBundle(MediaBrowserProtocol.DATA_NOTIFY_CHILDREN_CHANGED_OPTIONS);
                            MediaSessionCompat.ensureClassLoader(bundle3);
                            mediaBrowserServiceCallbackImpl.onLoadChildren(messenger, data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), data.getParcelableArrayList(MediaBrowserProtocol.DATA_MEDIA_ITEM_LIST), bundle2, bundle3);
                            return;
                        default:
                            Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: " + 1 + "\n  Service version: " + message.arg1);
                            return;
                    }
                } catch (BadParcelableException unused) {
                    Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                    if (message.what == 1) {
                        mediaBrowserServiceCallbackImpl.onConnectionFailed(messenger);
                    }
                }
            }
        }
    }

    public static class ConnectionCallback {
        final Object a;
        ConnectionCallbackInternal b;

        interface ConnectionCallbackInternal {
            void onConnected();

            void onConnectionFailed();

            void onConnectionSuspended();
        }

        private class StubApi21 implements MediaBrowserCompatApi21.ConnectionCallback {
            StubApi21() {
            }

            public void onConnected() {
                if (ConnectionCallback.this.b != null) {
                    ConnectionCallback.this.b.onConnected();
                }
                ConnectionCallback.this.onConnected();
            }

            public void onConnectionFailed() {
                if (ConnectionCallback.this.b != null) {
                    ConnectionCallback.this.b.onConnectionFailed();
                }
                ConnectionCallback.this.onConnectionFailed();
            }

            public void onConnectionSuspended() {
                if (ConnectionCallback.this.b != null) {
                    ConnectionCallback.this.b.onConnectionSuspended();
                }
                ConnectionCallback.this.onConnectionSuspended();
            }
        }

        public ConnectionCallback() {
            this.a = Build.VERSION.SDK_INT >= 21 ? MediaBrowserCompatApi21.createConnectionCallback(new StubApi21()) : null;
        }

        /* access modifiers changed from: package-private */
        public void a(ConnectionCallbackInternal connectionCallbackInternal) {
            this.b = connectionCallbackInternal;
        }

        public void onConnected() {
        }

        public void onConnectionFailed() {
        }

        public void onConnectionSuspended() {
        }
    }

    public static abstract class CustomActionCallback {
        public void onError(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onProgressUpdate(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onResult(String str, Bundle bundle, Bundle bundle2) {
        }
    }

    private static class CustomActionResultReceiver extends ResultReceiver {
        private final String mAction;
        private final CustomActionCallback mCallback;
        private final Bundle mExtras;

        CustomActionResultReceiver(String str, Bundle bundle, CustomActionCallback customActionCallback, Handler handler) {
            super(handler);
            this.mAction = str;
            this.mExtras = bundle;
            this.mCallback = customActionCallback;
        }

        /* access modifiers changed from: protected */
        public void a(int i, Bundle bundle) {
            if (this.mCallback != null) {
                MediaSessionCompat.ensureClassLoader(bundle);
                switch (i) {
                    case -1:
                        this.mCallback.onError(this.mAction, this.mExtras, bundle);
                        return;
                    case 0:
                        this.mCallback.onResult(this.mAction, this.mExtras, bundle);
                        return;
                    case 1:
                        this.mCallback.onProgressUpdate(this.mAction, this.mExtras, bundle);
                        return;
                    default:
                        Log.w("MediaBrowserCompat", "Unknown result code: " + i + " (extras=" + this.mExtras + ", resultData=" + bundle + ")");
                        return;
                }
            }
        }
    }

    public static abstract class ItemCallback {
        final Object a;

        private class StubApi23 implements MediaBrowserCompatApi23.ItemCallback {
            StubApi23() {
            }

            public void onError(@NonNull String str) {
                ItemCallback.this.onError(str);
            }

            public void onItemLoaded(Parcel parcel) {
                ItemCallback itemCallback;
                MediaItem mediaItem;
                if (parcel == null) {
                    itemCallback = ItemCallback.this;
                    mediaItem = null;
                } else {
                    parcel.setDataPosition(0);
                    mediaItem = MediaItem.CREATOR.createFromParcel(parcel);
                    parcel.recycle();
                    itemCallback = ItemCallback.this;
                }
                itemCallback.onItemLoaded(mediaItem);
            }
        }

        public ItemCallback() {
            this.a = Build.VERSION.SDK_INT >= 23 ? MediaBrowserCompatApi23.createItemCallback(new StubApi23()) : null;
        }

        public void onError(@NonNull String str) {
        }

        public void onItemLoaded(MediaItem mediaItem) {
        }
    }

    private static class ItemReceiver extends ResultReceiver {
        private final ItemCallback mCallback;
        private final String mMediaId;

        ItemReceiver(String str, ItemCallback itemCallback, Handler handler) {
            super(handler);
            this.mMediaId = str;
            this.mCallback = itemCallback;
        }

        /* access modifiers changed from: protected */
        public void a(int i, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            if (i != 0 || bundle == null || !bundle.containsKey(MediaBrowserServiceCompat.KEY_MEDIA_ITEM)) {
                this.mCallback.onError(this.mMediaId);
                return;
            }
            Parcelable parcelable = bundle.getParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM);
            if (parcelable == null || (parcelable instanceof MediaItem)) {
                this.mCallback.onItemLoaded((MediaItem) parcelable);
            } else {
                this.mCallback.onError(this.mMediaId);
            }
        }
    }

    interface MediaBrowserImpl {
        void connect();

        void disconnect();

        @Nullable
        Bundle getExtras();

        void getItem(@NonNull String str, @NonNull ItemCallback itemCallback);

        @Nullable
        Bundle getNotifyChildrenChangedOptions();

        @NonNull
        String getRoot();

        ComponentName getServiceComponent();

        @NonNull
        MediaSessionCompat.Token getSessionToken();

        boolean isConnected();

        void search(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback);

        void sendCustomAction(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback);

        void subscribe(@NonNull String str, @Nullable Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback);

        void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback);
    }

    @RequiresApi(21)
    static class MediaBrowserImplApi21 implements ConnectionCallback.ConnectionCallbackInternal, MediaBrowserImpl, MediaBrowserServiceCallbackImpl {
        final Context a;
        protected final Object b;
        protected final Bundle c;
        protected final CallbackHandler d = new CallbackHandler(this);
        protected int e;
        protected ServiceBinderWrapper f;
        protected Messenger g;
        private MediaSessionCompat.Token mMediaSessionToken;
        private Bundle mNotifyChildrenChangedOptions;
        private final ArrayMap<String, Subscription> mSubscriptions = new ArrayMap<>();

        MediaBrowserImplApi21(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            this.a = context;
            this.c = bundle != null ? new Bundle(bundle) : new Bundle();
            this.c.putInt(MediaBrowserProtocol.EXTRA_CLIENT_VERSION, 1);
            connectionCallback.a(this);
            this.b = MediaBrowserCompatApi21.createBrowser(context, componentName, connectionCallback.a, this.c);
        }

        public void connect() {
            MediaBrowserCompatApi21.connect(this.b);
        }

        public void disconnect() {
            Messenger messenger;
            ServiceBinderWrapper serviceBinderWrapper = this.f;
            if (!(serviceBinderWrapper == null || (messenger = this.g) == null)) {
                try {
                    serviceBinderWrapper.b(messenger);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                }
            }
            MediaBrowserCompatApi21.disconnect(this.b);
        }

        @Nullable
        public Bundle getExtras() {
            return MediaBrowserCompatApi21.getExtras(this.b);
        }

        public void getItem(@NonNull final String str, @NonNull final ItemCallback itemCallback) {
            CallbackHandler callbackHandler;
            Runnable r1;
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("mediaId is empty");
            } else if (itemCallback != null) {
                if (!MediaBrowserCompatApi21.isConnected(this.b)) {
                    Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                    callbackHandler = this.d;
                    r1 = new Runnable() {
                        public void run() {
                            itemCallback.onError(str);
                        }
                    };
                } else if (this.f == null) {
                    callbackHandler = this.d;
                    r1 = new Runnable() {
                        public void run() {
                            itemCallback.onError(str);
                        }
                    };
                } else {
                    try {
                        this.f.a(str, (ResultReceiver) new ItemReceiver(str, itemCallback, this.d), this.g);
                        return;
                    } catch (RemoteException unused) {
                        Log.i("MediaBrowserCompat", "Remote error getting media item: " + str);
                        this.d.post(new Runnable() {
                            public void run() {
                                itemCallback.onError(str);
                            }
                        });
                        return;
                    }
                }
                callbackHandler.post(r1);
            } else {
                throw new IllegalArgumentException("cb is null");
            }
        }

        public Bundle getNotifyChildrenChangedOptions() {
            return this.mNotifyChildrenChangedOptions;
        }

        @NonNull
        public String getRoot() {
            return MediaBrowserCompatApi21.getRoot(this.b);
        }

        public ComponentName getServiceComponent() {
            return MediaBrowserCompatApi21.getServiceComponent(this.b);
        }

        @NonNull
        public MediaSessionCompat.Token getSessionToken() {
            if (this.mMediaSessionToken == null) {
                this.mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.b));
            }
            return this.mMediaSessionToken;
        }

        public boolean isConnected() {
            return MediaBrowserCompatApi21.isConnected(this.b);
        }

        public void onConnected() {
            Bundle extras = MediaBrowserCompatApi21.getExtras(this.b);
            if (extras != null) {
                this.e = extras.getInt(MediaBrowserProtocol.EXTRA_SERVICE_VERSION, 0);
                IBinder binder = BundleCompat.getBinder(extras, MediaBrowserProtocol.EXTRA_MESSENGER_BINDER);
                if (binder != null) {
                    this.f = new ServiceBinderWrapper(binder, this.c);
                    this.g = new Messenger(this.d);
                    this.d.a(this.g);
                    try {
                        this.f.b(this.a, this.g);
                    } catch (RemoteException unused) {
                        Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                    }
                }
                IMediaSession asInterface = IMediaSession.Stub.asInterface(BundleCompat.getBinder(extras, MediaBrowserProtocol.EXTRA_SESSION_BINDER));
                if (asInterface != null) {
                    this.mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.b), asInterface);
                }
            }
        }

        public void onConnectionFailed() {
        }

        public void onConnectionFailed(Messenger messenger) {
        }

        public void onConnectionSuspended() {
            this.f = null;
            this.g = null;
            this.mMediaSessionToken = null;
            this.d.a((Messenger) null);
        }

        public void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (this.g == messenger) {
                Subscription subscription = this.mSubscriptions.get(str);
                if (subscription != null) {
                    SubscriptionCallback callback = subscription.getCallback(bundle);
                    if (callback != null) {
                        if (bundle == null) {
                            if (list == null) {
                                callback.onError(str);
                                return;
                            } else {
                                this.mNotifyChildrenChangedOptions = bundle2;
                                callback.onChildrenLoaded(str, list);
                            }
                        } else if (list == null) {
                            callback.onError(str, bundle);
                            return;
                        } else {
                            this.mNotifyChildrenChangedOptions = bundle2;
                            callback.onChildrenLoaded(str, list, bundle);
                        }
                        this.mNotifyChildrenChangedOptions = null;
                    }
                } else if (MediaBrowserCompat.a) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + str);
                }
            }
        }

        public void onServiceConnected(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
        }

        public void search(@NonNull final String str, final Bundle bundle, @NonNull final SearchCallback searchCallback) {
            if (!isConnected()) {
                throw new IllegalStateException("search() called while not connected");
            } else if (this.f == null) {
                Log.i("MediaBrowserCompat", "The connected service doesn't support search.");
                this.d.post(new Runnable() {
                    public void run() {
                        searchCallback.onError(str, bundle);
                    }
                });
            } else {
                try {
                    this.f.a(str, bundle, (ResultReceiver) new SearchResultReceiver(str, bundle, searchCallback, this.d), this.g);
                } catch (RemoteException e2) {
                    Log.i("MediaBrowserCompat", "Remote error searching items with query: " + str, e2);
                    this.d.post(new Runnable() {
                        public void run() {
                            searchCallback.onError(str, bundle);
                        }
                    });
                }
            }
        }

        public void sendCustomAction(@NonNull final String str, final Bundle bundle, @Nullable final CustomActionCallback customActionCallback) {
            if (isConnected()) {
                if (this.f == null) {
                    Log.i("MediaBrowserCompat", "The connected service doesn't support sendCustomAction.");
                    if (customActionCallback != null) {
                        this.d.post(new Runnable() {
                            public void run() {
                                customActionCallback.onError(str, bundle, (Bundle) null);
                            }
                        });
                    }
                }
                try {
                    this.f.b(str, bundle, new CustomActionResultReceiver(str, bundle, customActionCallback, this.d), this.g);
                } catch (RemoteException e2) {
                    Log.i("MediaBrowserCompat", "Remote error sending a custom action: action=" + str + ", extras=" + bundle, e2);
                    if (customActionCallback != null) {
                        this.d.post(new Runnable() {
                            public void run() {
                                customActionCallback.onError(str, bundle, (Bundle) null);
                            }
                        });
                    }
                }
            } else {
                throw new IllegalStateException("Cannot send a custom action (" + str + ") with " + "extras " + bundle + " because the browser is not connected to the " + "service.");
            }
        }

        public void subscribe(@NonNull String str, Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            Subscription subscription = this.mSubscriptions.get(str);
            if (subscription == null) {
                subscription = new Subscription();
                this.mSubscriptions.put(str, subscription);
            }
            subscriptionCallback.a(subscription);
            Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
            subscription.putCallback(bundle2, subscriptionCallback);
            ServiceBinderWrapper serviceBinderWrapper = this.f;
            if (serviceBinderWrapper == null) {
                MediaBrowserCompatApi21.subscribe(this.b, str, subscriptionCallback.a);
                return;
            }
            try {
                serviceBinderWrapper.a(str, subscriptionCallback.b, bundle2, this.g);
            } catch (RemoteException unused) {
                Log.i("MediaBrowserCompat", "Remote error subscribing media item: " + str);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
            if (r1.size() == 0) goto L_0x0011;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void unsubscribe(@androidx.annotation.NonNull java.lang.String r8, android.support.v4.media.MediaBrowserCompat.SubscriptionCallback r9) {
            /*
                r7 = this;
                androidx.collection.ArrayMap<java.lang.String, android.support.v4.media.MediaBrowserCompat$Subscription> r0 = r7.mSubscriptions
                java.lang.Object r0 = r0.get(r8)
                android.support.v4.media.MediaBrowserCompat$Subscription r0 = (android.support.v4.media.MediaBrowserCompat.Subscription) r0
                if (r0 != 0) goto L_0x000b
                return
            L_0x000b:
                android.support.v4.media.MediaBrowserCompat$ServiceBinderWrapper r1 = r7.f
                if (r1 != 0) goto L_0x003d
                if (r9 != 0) goto L_0x0017
            L_0x0011:
                java.lang.Object r1 = r7.b
                android.support.v4.media.MediaBrowserCompatApi21.unsubscribe(r1, r8)
                goto L_0x0084
            L_0x0017:
                java.util.List r1 = r0.getCallbacks()
                java.util.List r2 = r0.getOptionsList()
                int r3 = r1.size()
                int r3 = r3 + -1
            L_0x0025:
                if (r3 < 0) goto L_0x0036
                java.lang.Object r4 = r1.get(r3)
                if (r4 != r9) goto L_0x0033
                r1.remove(r3)
                r2.remove(r3)
            L_0x0033:
                int r3 = r3 + -1
                goto L_0x0025
            L_0x0036:
                int r1 = r1.size()
                if (r1 != 0) goto L_0x0084
                goto L_0x0011
            L_0x003d:
                if (r9 != 0) goto L_0x0046
                r2 = 0
                android.os.Messenger r3 = r7.g     // Catch:{ RemoteException -> 0x006e }
                r1.a((java.lang.String) r8, (android.os.IBinder) r2, (android.os.Messenger) r3)     // Catch:{ RemoteException -> 0x006e }
                goto L_0x0084
            L_0x0046:
                java.util.List r1 = r0.getCallbacks()     // Catch:{ RemoteException -> 0x006e }
                java.util.List r2 = r0.getOptionsList()     // Catch:{ RemoteException -> 0x006e }
                int r3 = r1.size()     // Catch:{ RemoteException -> 0x006e }
                int r3 = r3 + -1
            L_0x0054:
                if (r3 < 0) goto L_0x0084
                java.lang.Object r4 = r1.get(r3)     // Catch:{ RemoteException -> 0x006e }
                if (r4 != r9) goto L_0x006b
                android.support.v4.media.MediaBrowserCompat$ServiceBinderWrapper r4 = r7.f     // Catch:{ RemoteException -> 0x006e }
                android.os.IBinder r5 = r9.b     // Catch:{ RemoteException -> 0x006e }
                android.os.Messenger r6 = r7.g     // Catch:{ RemoteException -> 0x006e }
                r4.a((java.lang.String) r8, (android.os.IBinder) r5, (android.os.Messenger) r6)     // Catch:{ RemoteException -> 0x006e }
                r1.remove(r3)     // Catch:{ RemoteException -> 0x006e }
                r2.remove(r3)     // Catch:{ RemoteException -> 0x006e }
            L_0x006b:
                int r3 = r3 + -1
                goto L_0x0054
            L_0x006e:
                java.lang.String r1 = "MediaBrowserCompat"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "removeSubscription failed with RemoteException parentId="
                r2.append(r3)
                r2.append(r8)
                java.lang.String r2 = r2.toString()
                android.util.Log.d(r1, r2)
            L_0x0084:
                boolean r0 = r0.isEmpty()
                if (r0 != 0) goto L_0x008c
                if (r9 != 0) goto L_0x0091
            L_0x008c:
                androidx.collection.ArrayMap<java.lang.String, android.support.v4.media.MediaBrowserCompat$Subscription> r9 = r7.mSubscriptions
                r9.remove(r8)
            L_0x0091:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.unsubscribe(java.lang.String, android.support.v4.media.MediaBrowserCompat$SubscriptionCallback):void");
        }
    }

    @RequiresApi(23)
    static class MediaBrowserImplApi23 extends MediaBrowserImplApi21 {
        MediaBrowserImplApi23(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }

        public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
            if (this.f == null) {
                MediaBrowserCompatApi23.getItem(this.b, str, itemCallback.a);
            } else {
                super.getItem(str, itemCallback);
            }
        }
    }

    @RequiresApi(26)
    static class MediaBrowserImplApi26 extends MediaBrowserImplApi23 {
        MediaBrowserImplApi26(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }

        public void subscribe(@NonNull String str, @Nullable Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            if (this.f != null && this.e >= 2) {
                super.subscribe(str, bundle, subscriptionCallback);
            } else if (bundle == null) {
                MediaBrowserCompatApi21.subscribe(this.b, str, subscriptionCallback.a);
            } else {
                MediaBrowserCompatApi26.subscribe(this.b, str, bundle, subscriptionCallback.a);
            }
        }

        public void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            if (this.f != null && this.e >= 2) {
                super.unsubscribe(str, subscriptionCallback);
            } else if (subscriptionCallback == null) {
                MediaBrowserCompatApi21.unsubscribe(this.b, str);
            } else {
                MediaBrowserCompatApi26.unsubscribe(this.b, str, subscriptionCallback.a);
            }
        }
    }

    static class MediaBrowserImplBase implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl {
        final Context a;
        final ComponentName b;
        final ConnectionCallback c;
        final Bundle d;
        final CallbackHandler e = new CallbackHandler(this);
        int f = 1;
        MediaServiceConnection g;
        ServiceBinderWrapper h;
        Messenger i;
        private Bundle mExtras;
        private MediaSessionCompat.Token mMediaSessionToken;
        private Bundle mNotifyChildrenChangedOptions;
        private String mRootId;
        private final ArrayMap<String, Subscription> mSubscriptions = new ArrayMap<>();

        private class MediaServiceConnection implements ServiceConnection {
            MediaServiceConnection() {
            }

            private void postOrRun(Runnable runnable) {
                if (Thread.currentThread() == MediaBrowserImplBase.this.e.getLooper().getThread()) {
                    runnable.run();
                } else {
                    MediaBrowserImplBase.this.e.post(runnable);
                }
            }

            /* access modifiers changed from: package-private */
            public boolean a(String str) {
                if (MediaBrowserImplBase.this.g == this && MediaBrowserImplBase.this.f != 0 && MediaBrowserImplBase.this.f != 1) {
                    return true;
                }
                if (MediaBrowserImplBase.this.f == 0 || MediaBrowserImplBase.this.f == 1) {
                    return false;
                }
                Log.i("MediaBrowserCompat", str + " for " + MediaBrowserImplBase.this.b + " with mServiceConnection=" + MediaBrowserImplBase.this.g + " this=" + this);
                return false;
            }

            public void onServiceConnected(final ComponentName componentName, final IBinder iBinder) {
                postOrRun(new Runnable() {
                    public void run() {
                        if (MediaBrowserCompat.a) {
                            Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + componentName + " binder=" + iBinder);
                            MediaBrowserImplBase.this.b();
                        }
                        if (MediaServiceConnection.this.a("onServiceConnected")) {
                            MediaBrowserImplBase.this.h = new ServiceBinderWrapper(iBinder, MediaBrowserImplBase.this.d);
                            MediaBrowserImplBase.this.i = new Messenger(MediaBrowserImplBase.this.e);
                            MediaBrowserImplBase.this.e.a(MediaBrowserImplBase.this.i);
                            MediaBrowserImplBase.this.f = 2;
                            try {
                                if (MediaBrowserCompat.a) {
                                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                    MediaBrowserImplBase.this.b();
                                }
                                MediaBrowserImplBase.this.h.a(MediaBrowserImplBase.this.a, MediaBrowserImplBase.this.i);
                            } catch (RemoteException unused) {
                                Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserImplBase.this.b);
                                if (MediaBrowserCompat.a) {
                                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                    MediaBrowserImplBase.this.b();
                                }
                            }
                        }
                    }
                });
            }

            public void onServiceDisconnected(final ComponentName componentName) {
                postOrRun(new Runnable() {
                    public void run() {
                        if (MediaBrowserCompat.a) {
                            Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + componentName + " this=" + this + " mServiceConnection=" + MediaBrowserImplBase.this.g);
                            MediaBrowserImplBase.this.b();
                        }
                        if (MediaServiceConnection.this.a("onServiceDisconnected")) {
                            MediaBrowserImplBase.this.h = null;
                            MediaBrowserImplBase.this.i = null;
                            MediaBrowserImplBase.this.e.a((Messenger) null);
                            MediaBrowserImplBase.this.f = 4;
                            MediaBrowserImplBase.this.c.onConnectionSuspended();
                        }
                    }
                });
            }
        }

        public MediaBrowserImplBase(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            if (context == null) {
                throw new IllegalArgumentException("context must not be null");
            } else if (componentName == null) {
                throw new IllegalArgumentException("service component must not be null");
            } else if (connectionCallback != null) {
                this.a = context;
                this.b = componentName;
                this.c = connectionCallback;
                this.d = bundle == null ? null : new Bundle(bundle);
            } else {
                throw new IllegalArgumentException("connection callback must not be null");
            }
        }

        private static String getStateLabel(int i2) {
            switch (i2) {
                case 0:
                    return "CONNECT_STATE_DISCONNECTING";
                case 1:
                    return "CONNECT_STATE_DISCONNECTED";
                case 2:
                    return "CONNECT_STATE_CONNECTING";
                case 3:
                    return "CONNECT_STATE_CONNECTED";
                case 4:
                    return "CONNECT_STATE_SUSPENDED";
                default:
                    return "UNKNOWN/" + i2;
            }
        }

        private boolean isCurrent(Messenger messenger, String str) {
            int i2;
            if (this.i == messenger && (i2 = this.f) != 0 && i2 != 1) {
                return true;
            }
            int i3 = this.f;
            if (i3 == 0 || i3 == 1) {
                return false;
            }
            Log.i("MediaBrowserCompat", str + " for " + this.b + " with mCallbacksMessenger=" + this.i + " this=" + this);
            return false;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            MediaServiceConnection mediaServiceConnection = this.g;
            if (mediaServiceConnection != null) {
                this.a.unbindService(mediaServiceConnection);
            }
            this.f = 1;
            this.g = null;
            this.h = null;
            this.i = null;
            this.e.a((Messenger) null);
            this.mRootId = null;
            this.mMediaSessionToken = null;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
            Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.b);
            Log.d("MediaBrowserCompat", "  mCallback=" + this.c);
            Log.d("MediaBrowserCompat", "  mRootHints=" + this.d);
            Log.d("MediaBrowserCompat", "  mState=" + getStateLabel(this.f));
            Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.g);
            Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.h);
            Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.i);
            Log.d("MediaBrowserCompat", "  mRootId=" + this.mRootId);
            Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.mMediaSessionToken);
        }

        public void connect() {
            int i2 = this.f;
            if (i2 == 0 || i2 == 1) {
                this.f = 2;
                this.e.post(new Runnable() {
                    public void run() {
                        if (MediaBrowserImplBase.this.f != 0) {
                            MediaBrowserImplBase.this.f = 2;
                            if (MediaBrowserCompat.a && MediaBrowserImplBase.this.g != null) {
                                throw new RuntimeException("mServiceConnection should be null. Instead it is " + MediaBrowserImplBase.this.g);
                            } else if (MediaBrowserImplBase.this.h != null) {
                                throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + MediaBrowserImplBase.this.h);
                            } else if (MediaBrowserImplBase.this.i == null) {
                                Intent intent = new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE);
                                intent.setComponent(MediaBrowserImplBase.this.b);
                                MediaBrowserImplBase mediaBrowserImplBase = MediaBrowserImplBase.this;
                                mediaBrowserImplBase.g = new MediaServiceConnection();
                                boolean z = false;
                                try {
                                    z = MediaBrowserImplBase.this.a.bindService(intent, MediaBrowserImplBase.this.g, 1);
                                } catch (Exception unused) {
                                    Log.e("MediaBrowserCompat", "Failed binding to service " + MediaBrowserImplBase.this.b);
                                }
                                if (!z) {
                                    MediaBrowserImplBase.this.a();
                                    MediaBrowserImplBase.this.c.onConnectionFailed();
                                }
                                if (MediaBrowserCompat.a) {
                                    Log.d("MediaBrowserCompat", "connect...");
                                    MediaBrowserImplBase.this.b();
                                }
                            } else {
                                throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + MediaBrowserImplBase.this.i);
                            }
                        }
                    }
                });
                return;
            }
            throw new IllegalStateException("connect() called while neigther disconnecting nor disconnected (state=" + getStateLabel(this.f) + ")");
        }

        public void disconnect() {
            this.f = 0;
            this.e.post(new Runnable() {
                public void run() {
                    if (MediaBrowserImplBase.this.i != null) {
                        try {
                            MediaBrowserImplBase.this.h.a(MediaBrowserImplBase.this.i);
                        } catch (RemoteException unused) {
                            Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserImplBase.this.b);
                        }
                    }
                    int i = MediaBrowserImplBase.this.f;
                    MediaBrowserImplBase.this.a();
                    if (i != 0) {
                        MediaBrowserImplBase.this.f = i;
                    }
                    if (MediaBrowserCompat.a) {
                        Log.d("MediaBrowserCompat", "disconnect...");
                        MediaBrowserImplBase.this.b();
                    }
                }
            });
        }

        @Nullable
        public Bundle getExtras() {
            if (isConnected()) {
                return this.mExtras;
            }
            throw new IllegalStateException("getExtras() called while not connected (state=" + getStateLabel(this.f) + ")");
        }

        public void getItem(@NonNull final String str, @NonNull final ItemCallback itemCallback) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("mediaId is empty");
            } else if (itemCallback == null) {
                throw new IllegalArgumentException("cb is null");
            } else if (!isConnected()) {
                Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                this.e.post(new Runnable() {
                    public void run() {
                        itemCallback.onError(str);
                    }
                });
            } else {
                try {
                    this.h.a(str, (ResultReceiver) new ItemReceiver(str, itemCallback, this.e), this.i);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error getting media item: " + str);
                    this.e.post(new Runnable() {
                        public void run() {
                            itemCallback.onError(str);
                        }
                    });
                }
            }
        }

        public Bundle getNotifyChildrenChangedOptions() {
            return this.mNotifyChildrenChangedOptions;
        }

        @NonNull
        public String getRoot() {
            if (isConnected()) {
                return this.mRootId;
            }
            throw new IllegalStateException("getRoot() called while not connected(state=" + getStateLabel(this.f) + ")");
        }

        @NonNull
        public ComponentName getServiceComponent() {
            if (isConnected()) {
                return this.b;
            }
            throw new IllegalStateException("getServiceComponent() called while not connected (state=" + this.f + ")");
        }

        @NonNull
        public MediaSessionCompat.Token getSessionToken() {
            if (isConnected()) {
                return this.mMediaSessionToken;
            }
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.f + ")");
        }

        public boolean isConnected() {
            return this.f == 3;
        }

        public void onConnectionFailed(Messenger messenger) {
            Log.e("MediaBrowserCompat", "onConnectFailed for " + this.b);
            if (isCurrent(messenger, "onConnectFailed")) {
                if (this.f != 2) {
                    Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.f) + "... ignoring");
                    return;
                }
                a();
                this.c.onConnectionFailed();
            }
        }

        public void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (isCurrent(messenger, "onLoadChildren")) {
                if (MediaBrowserCompat.a) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for " + this.b + " id=" + str);
                }
                Subscription subscription = this.mSubscriptions.get(str);
                if (subscription != null) {
                    SubscriptionCallback callback = subscription.getCallback(bundle);
                    if (callback != null) {
                        if (bundle == null) {
                            if (list == null) {
                                callback.onError(str);
                                return;
                            } else {
                                this.mNotifyChildrenChangedOptions = bundle2;
                                callback.onChildrenLoaded(str, list);
                            }
                        } else if (list == null) {
                            callback.onError(str, bundle);
                            return;
                        } else {
                            this.mNotifyChildrenChangedOptions = bundle2;
                            callback.onChildrenLoaded(str, list, bundle);
                        }
                        this.mNotifyChildrenChangedOptions = null;
                    }
                } else if (MediaBrowserCompat.a) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + str);
                }
            }
        }

        public void onServiceConnected(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
            if (isCurrent(messenger, "onConnect")) {
                if (this.f != 2) {
                    Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.f) + "... ignoring");
                    return;
                }
                this.mRootId = str;
                this.mMediaSessionToken = token;
                this.mExtras = bundle;
                this.f = 3;
                if (MediaBrowserCompat.a) {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    b();
                }
                this.c.onConnected();
                try {
                    for (Map.Entry next : this.mSubscriptions.entrySet()) {
                        String str2 = (String) next.getKey();
                        Subscription subscription = (Subscription) next.getValue();
                        List<SubscriptionCallback> callbacks = subscription.getCallbacks();
                        List<Bundle> optionsList = subscription.getOptionsList();
                        for (int i2 = 0; i2 < callbacks.size(); i2++) {
                            this.h.a(str2, callbacks.get(i2).b, optionsList.get(i2), this.i);
                        }
                    }
                } catch (RemoteException unused) {
                    Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
                }
            }
        }

        public void search(@NonNull final String str, final Bundle bundle, @NonNull final SearchCallback searchCallback) {
            if (isConnected()) {
                try {
                    this.h.a(str, bundle, (ResultReceiver) new SearchResultReceiver(str, bundle, searchCallback, this.e), this.i);
                } catch (RemoteException e2) {
                    Log.i("MediaBrowserCompat", "Remote error searching items with query: " + str, e2);
                    this.e.post(new Runnable() {
                        public void run() {
                            searchCallback.onError(str, bundle);
                        }
                    });
                }
            } else {
                throw new IllegalStateException("search() called while not connected (state=" + getStateLabel(this.f) + ")");
            }
        }

        public void sendCustomAction(@NonNull final String str, final Bundle bundle, @Nullable final CustomActionCallback customActionCallback) {
            if (isConnected()) {
                try {
                    this.h.b(str, bundle, new CustomActionResultReceiver(str, bundle, customActionCallback, this.e), this.i);
                } catch (RemoteException e2) {
                    Log.i("MediaBrowserCompat", "Remote error sending a custom action: action=" + str + ", extras=" + bundle, e2);
                    if (customActionCallback != null) {
                        this.e.post(new Runnable() {
                            public void run() {
                                customActionCallback.onError(str, bundle, (Bundle) null);
                            }
                        });
                    }
                }
            } else {
                throw new IllegalStateException("Cannot send a custom action (" + str + ") with " + "extras " + bundle + " because the browser is not connected to the " + "service.");
            }
        }

        public void subscribe(@NonNull String str, Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            Subscription subscription = this.mSubscriptions.get(str);
            if (subscription == null) {
                subscription = new Subscription();
                this.mSubscriptions.put(str, subscription);
            }
            Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
            subscription.putCallback(bundle2, subscriptionCallback);
            if (isConnected()) {
                try {
                    this.h.a(str, subscriptionCallback.b, bundle2, this.i);
                } catch (RemoteException unused) {
                    Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + str);
                }
            }
        }

        public void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            Subscription subscription = this.mSubscriptions.get(str);
            if (subscription != null) {
                if (subscriptionCallback == null) {
                    try {
                        if (isConnected()) {
                            this.h.a(str, (IBinder) null, this.i);
                        }
                    } catch (RemoteException unused) {
                        Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + str);
                    }
                } else {
                    List<SubscriptionCallback> callbacks = subscription.getCallbacks();
                    List<Bundle> optionsList = subscription.getOptionsList();
                    for (int size = callbacks.size() - 1; size >= 0; size--) {
                        if (callbacks.get(size) == subscriptionCallback) {
                            if (isConnected()) {
                                this.h.a(str, subscriptionCallback.b, this.i);
                            }
                            callbacks.remove(size);
                            optionsList.remove(size);
                        }
                    }
                }
                if (subscription.isEmpty() || subscriptionCallback == null) {
                    this.mSubscriptions.remove(str);
                }
            }
        }
    }

    interface MediaBrowserServiceCallbackImpl {
        void onConnectionFailed(Messenger messenger);

        void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2);

        void onServiceConnected(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle);
    }

    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new Parcelable.Creator<MediaItem>() {
            public MediaItem createFromParcel(Parcel parcel) {
                return new MediaItem(parcel);
            }

            public MediaItem[] newArray(int i) {
                return new MediaItem[i];
            }
        };
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescriptionCompat mDescription;
        private final int mFlags;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        public @interface Flags {
        }

        MediaItem(Parcel parcel) {
            this.mFlags = parcel.readInt();
            this.mDescription = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }

        public MediaItem(@NonNull MediaDescriptionCompat mediaDescriptionCompat, int i) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            } else if (!TextUtils.isEmpty(mediaDescriptionCompat.getMediaId())) {
                this.mFlags = i;
                this.mDescription = mediaDescriptionCompat;
            } else {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
        }

        public static MediaItem fromMediaItem(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new MediaItem(MediaDescriptionCompat.fromMediaDescription(MediaBrowserCompatApi21.MediaItem.getDescription(obj)), MediaBrowserCompatApi21.MediaItem.getFlags(obj));
        }

        public static List<MediaItem> fromMediaItemList(List<?> list) {
            if (list == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (Object fromMediaItem : list) {
                arrayList.add(fromMediaItem(fromMediaItem));
            }
            return arrayList;
        }

        public int describeContents() {
            return 0;
        }

        @NonNull
        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }

        public int getFlags() {
            return this.mFlags;
        }

        @Nullable
        public String getMediaId() {
            return this.mDescription.getMediaId();
        }

        public boolean isBrowsable() {
            return (this.mFlags & 1) != 0;
        }

        public boolean isPlayable() {
            return (this.mFlags & 2) != 0;
        }

        public String toString() {
            return "MediaItem{" + "mFlags=" + this.mFlags + ", mDescription=" + this.mDescription + '}';
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mFlags);
            this.mDescription.writeToParcel(parcel, i);
        }
    }

    public static abstract class SearchCallback {
        public void onError(@NonNull String str, Bundle bundle) {
        }

        public void onSearchResult(@NonNull String str, Bundle bundle, @NonNull List<MediaItem> list) {
        }
    }

    private static class SearchResultReceiver extends ResultReceiver {
        private final SearchCallback mCallback;
        private final Bundle mExtras;
        private final String mQuery;

        SearchResultReceiver(String str, Bundle bundle, SearchCallback searchCallback, Handler handler) {
            super(handler);
            this.mQuery = str;
            this.mExtras = bundle;
            this.mCallback = searchCallback;
        }

        /* access modifiers changed from: protected */
        public void a(int i, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            if (i != 0 || bundle == null || !bundle.containsKey(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS)) {
                this.mCallback.onError(this.mQuery, this.mExtras);
                return;
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS);
            ArrayList arrayList = null;
            if (parcelableArray != null) {
                arrayList = new ArrayList();
                for (Parcelable parcelable : parcelableArray) {
                    arrayList.add((MediaItem) parcelable);
                }
            }
            this.mCallback.onSearchResult(this.mQuery, this.mExtras, arrayList);
        }
    }

    private static class ServiceBinderWrapper {
        private Messenger mMessenger;
        private Bundle mRootHints;

        public ServiceBinderWrapper(IBinder iBinder, Bundle bundle) {
            this.mMessenger = new Messenger(iBinder);
            this.mRootHints = bundle;
        }

        private void sendRequest(int i, Bundle bundle, Messenger messenger) {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = 1;
            obtain.setData(bundle);
            obtain.replyTo = messenger;
            this.mMessenger.send(obtain);
        }

        /* access modifiers changed from: package-private */
        public void a(Context context, Messenger messenger) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_PACKAGE_NAME, context.getPackageName());
            bundle.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, this.mRootHints);
            sendRequest(1, bundle, messenger);
        }

        /* access modifiers changed from: package-private */
        public void a(Messenger messenger) {
            sendRequest(2, (Bundle) null, messenger);
        }

        /* access modifiers changed from: package-private */
        public void a(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_SEARCH_QUERY, str);
            bundle2.putBundle(MediaBrowserProtocol.DATA_SEARCH_EXTRAS, bundle);
            bundle2.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, resultReceiver);
            sendRequest(8, bundle2, messenger);
        }

        /* access modifiers changed from: package-private */
        public void a(String str, IBinder iBinder, Bundle bundle, Messenger messenger) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            BundleCompat.putBinder(bundle2, MediaBrowserProtocol.DATA_CALLBACK_TOKEN, iBinder);
            bundle2.putBundle(MediaBrowserProtocol.DATA_OPTIONS, bundle);
            sendRequest(3, bundle2, messenger);
        }

        /* access modifiers changed from: package-private */
        public void a(String str, IBinder iBinder, Messenger messenger) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            BundleCompat.putBinder(bundle, MediaBrowserProtocol.DATA_CALLBACK_TOKEN, iBinder);
            sendRequest(4, bundle, messenger);
        }

        /* access modifiers changed from: package-private */
        public void a(String str, ResultReceiver resultReceiver, Messenger messenger) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            bundle.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, resultReceiver);
            sendRequest(5, bundle, messenger);
        }

        /* access modifiers changed from: package-private */
        public void b(Context context, Messenger messenger) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_PACKAGE_NAME, context.getPackageName());
            bundle.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, this.mRootHints);
            sendRequest(6, bundle, messenger);
        }

        /* access modifiers changed from: package-private */
        public void b(Messenger messenger) {
            sendRequest(7, (Bundle) null, messenger);
        }

        /* access modifiers changed from: package-private */
        public void b(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_CUSTOM_ACTION, str);
            bundle2.putBundle(MediaBrowserProtocol.DATA_CUSTOM_ACTION_EXTRAS, bundle);
            bundle2.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, resultReceiver);
            sendRequest(9, bundle2, messenger);
        }
    }

    private static class Subscription {
        private final List<SubscriptionCallback> mCallbacks = new ArrayList();
        private final List<Bundle> mOptionsList = new ArrayList();

        public SubscriptionCallback getCallback(Bundle bundle) {
            for (int i = 0; i < this.mOptionsList.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i), bundle)) {
                    return this.mCallbacks.get(i);
                }
            }
            return null;
        }

        public List<SubscriptionCallback> getCallbacks() {
            return this.mCallbacks;
        }

        public List<Bundle> getOptionsList() {
            return this.mOptionsList;
        }

        public boolean isEmpty() {
            return this.mCallbacks.isEmpty();
        }

        public void putCallback(Bundle bundle, SubscriptionCallback subscriptionCallback) {
            for (int i = 0; i < this.mOptionsList.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i), bundle)) {
                    this.mCallbacks.set(i, subscriptionCallback);
                    return;
                }
            }
            this.mCallbacks.add(subscriptionCallback);
            this.mOptionsList.add(bundle);
        }
    }

    public static abstract class SubscriptionCallback {
        final Object a;
        final IBinder b = new Binder();
        WeakReference<Subscription> c;

        private class StubApi21 implements MediaBrowserCompatApi21.SubscriptionCallback {
            StubApi21() {
            }

            /* access modifiers changed from: package-private */
            public List<MediaItem> a(List<MediaItem> list, Bundle bundle) {
                if (list == null) {
                    return null;
                }
                int i = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
                int i2 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
                if (i == -1 && i2 == -1) {
                    return list;
                }
                int i3 = i2 * i;
                int i4 = i3 + i2;
                if (i < 0 || i2 < 1 || i3 >= list.size()) {
                    return Collections.emptyList();
                }
                if (i4 > list.size()) {
                    i4 = list.size();
                }
                return list.subList(i3, i4);
            }

            public void onChildrenLoaded(@NonNull String str, List<?> list) {
                Subscription subscription = SubscriptionCallback.this.c == null ? null : (Subscription) SubscriptionCallback.this.c.get();
                if (subscription == null) {
                    SubscriptionCallback.this.onChildrenLoaded(str, MediaItem.fromMediaItemList(list));
                    return;
                }
                List<MediaItem> fromMediaItemList = MediaItem.fromMediaItemList(list);
                List<SubscriptionCallback> callbacks = subscription.getCallbacks();
                List<Bundle> optionsList = subscription.getOptionsList();
                for (int i = 0; i < callbacks.size(); i++) {
                    Bundle bundle = optionsList.get(i);
                    if (bundle == null) {
                        SubscriptionCallback.this.onChildrenLoaded(str, fromMediaItemList);
                    } else {
                        SubscriptionCallback.this.onChildrenLoaded(str, a(fromMediaItemList, bundle), bundle);
                    }
                }
            }

            public void onError(@NonNull String str) {
                SubscriptionCallback.this.onError(str);
            }
        }

        private class StubApi26 extends StubApi21 implements MediaBrowserCompatApi26.SubscriptionCallback {
            StubApi26() {
                super();
            }

            public void onChildrenLoaded(@NonNull String str, List<?> list, @NonNull Bundle bundle) {
                SubscriptionCallback.this.onChildrenLoaded(str, MediaItem.fromMediaItemList(list), bundle);
            }

            public void onError(@NonNull String str, @NonNull Bundle bundle) {
                SubscriptionCallback.this.onError(str, bundle);
            }
        }

        public SubscriptionCallback() {
            this.a = Build.VERSION.SDK_INT >= 26 ? MediaBrowserCompatApi26.a(new StubApi26()) : Build.VERSION.SDK_INT >= 21 ? MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21()) : null;
        }

        /* access modifiers changed from: package-private */
        public void a(Subscription subscription) {
            this.c = new WeakReference<>(subscription);
        }

        public void onChildrenLoaded(@NonNull String str, @NonNull List<MediaItem> list) {
        }

        public void onChildrenLoaded(@NonNull String str, @NonNull List<MediaItem> list, @NonNull Bundle bundle) {
        }

        public void onError(@NonNull String str) {
        }

        public void onError(@NonNull String str, @NonNull Bundle bundle) {
        }
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
        this.mImpl = Build.VERSION.SDK_INT >= 26 ? new MediaBrowserImplApi26(context, componentName, connectionCallback, bundle) : Build.VERSION.SDK_INT >= 23 ? new MediaBrowserImplApi23(context, componentName, connectionCallback, bundle) : Build.VERSION.SDK_INT >= 21 ? new MediaBrowserImplApi21(context, componentName, connectionCallback, bundle) : new MediaBrowserImplBase(context, componentName, connectionCallback, bundle);
    }

    public void connect() {
        this.mImpl.connect();
    }

    public void disconnect() {
        this.mImpl.disconnect();
    }

    @Nullable
    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }

    public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
        this.mImpl.getItem(str, itemCallback);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Bundle getNotifyChildrenChangedOptions() {
        return this.mImpl.getNotifyChildrenChangedOptions();
    }

    @NonNull
    public String getRoot() {
        return this.mImpl.getRoot();
    }

    @NonNull
    public ComponentName getServiceComponent() {
        return this.mImpl.getServiceComponent();
    }

    @NonNull
    public MediaSessionCompat.Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }

    public boolean isConnected() {
        return this.mImpl.isConnected();
    }

    public void search(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query cannot be empty");
        } else if (searchCallback != null) {
            this.mImpl.search(str, bundle, searchCallback);
        } else {
            throw new IllegalArgumentException("callback cannot be null");
        }
    }

    public void sendCustomAction(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback) {
        if (!TextUtils.isEmpty(str)) {
            this.mImpl.sendCustomAction(str, bundle, customActionCallback);
            return;
        }
        throw new IllegalArgumentException("action cannot be empty");
    }

    public void subscribe(@NonNull String str, @NonNull Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        } else if (subscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        } else if (bundle != null) {
            this.mImpl.subscribe(str, bundle, subscriptionCallback);
        } else {
            throw new IllegalArgumentException("options are null");
        }
    }

    public void subscribe(@NonNull String str, @NonNull SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        } else if (subscriptionCallback != null) {
            this.mImpl.subscribe(str, (Bundle) null, subscriptionCallback);
        } else {
            throw new IllegalArgumentException("callback is null");
        }
    }

    public void unsubscribe(@NonNull String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mImpl.unsubscribe(str, (SubscriptionCallback) null);
            return;
        }
        throw new IllegalArgumentException("parentId is empty");
    }

    public void unsubscribe(@NonNull String str, @NonNull SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        } else if (subscriptionCallback != null) {
            this.mImpl.unsubscribe(str, subscriptionCallback);
        } else {
            throw new IllegalArgumentException("callback is null");
        }
    }
}
