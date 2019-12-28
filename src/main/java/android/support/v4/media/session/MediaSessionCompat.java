package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompatApi21;
import android.support.v4.media.session.MediaSessionCompatApi23;
import android.support.v4.media.session.MediaSessionCompatApi24;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.app.BundleCompat;
import androidx.media.MediaSessionManager;
import androidx.media.VolumeProviderCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaSessionCompat {
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_CAPTIONING_ENABLED = "android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_RATING = "android.support.v4.media.session.action.ARGUMENT_RATING";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_REPEAT_MODE = "android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_SHUFFLE_MODE = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
    public static final String ACTION_FLAG_AS_INAPPROPRIATE = "android.support.v4.media.session.action.FLAG_AS_INAPPROPRIATE";
    public static final String ACTION_FOLLOW = "android.support.v4.media.session.action.FOLLOW";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_CAPTIONING_ENABLED = "android.support.v4.media.session.action.SET_CAPTIONING_ENABLED";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_RATING = "android.support.v4.media.session.action.SET_RATING";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_REPEAT_MODE = "android.support.v4.media.session.action.SET_REPEAT_MODE";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_SHUFFLE_MODE = "android.support.v4.media.session.action.SET_SHUFFLE_MODE";
    public static final String ACTION_SKIP_AD = "android.support.v4.media.session.action.SKIP_AD";
    public static final String ACTION_UNFOLLOW = "android.support.v4.media.session.action.UNFOLLOW";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE_VALUE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE_VALUE";
    private static final String DATA_CALLING_PACKAGE = "data_calling_pkg";
    private static final String DATA_CALLING_PID = "data_calling_pid";
    private static final String DATA_CALLING_UID = "data_calling_uid";
    private static final String DATA_EXTRAS = "data_extras";
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_QUEUE_COMMANDS = 4;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String KEY_EXTRA_BINDER = "android.support.v4.media.session.EXTRA_BINDER";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String KEY_SESSION_TOKEN2_BUNDLE = "android.support.v4.media.session.SESSION_TOKEN2_BUNDLE";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String KEY_TOKEN = "android.support.v4.media.session.TOKEN";
    private static final int MAX_BITMAP_SIZE_IN_DP = 320;
    public static final int MEDIA_ATTRIBUTE_ALBUM = 1;
    public static final int MEDIA_ATTRIBUTE_ARTIST = 0;
    public static final int MEDIA_ATTRIBUTE_PLAYLIST = 2;
    static int a;
    private final ArrayList<OnActiveChangeListener> mActiveListeners;
    private final MediaControllerCompat mController;
    private final MediaSessionImpl mImpl;

    public static abstract class Callback {
        final Object b;
        WeakReference<MediaSessionImpl> c;
        private CallbackHandler mCallbackHandler = null;
        private boolean mMediaPlayPauseKeyPending;

        private class CallbackHandler extends Handler {
            private static final int MSG_MEDIA_PLAY_PAUSE_KEY_DOUBLE_TAP_TIMEOUT = 1;

            CallbackHandler(Looper looper) {
                super(looper);
            }

            public void handleMessage(Message message) {
                if (message.what == 1) {
                    Callback.this.a((MediaSessionManager.RemoteUserInfo) message.obj);
                }
            }
        }

        @RequiresApi(21)
        private class StubApi21 implements MediaSessionCompatApi21.Callback {
            StubApi21() {
            }

            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.support.v4.media.session.MediaSessionCompat$QueueItem} */
            /* JADX WARNING: type inference failed for: r1v0 */
            /* JADX WARNING: type inference failed for: r1v4, types: [android.os.IBinder] */
            /* JADX WARNING: type inference failed for: r1v6 */
            /* JADX WARNING: type inference failed for: r1v7 */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onCommand(java.lang.String r4, android.os.Bundle r5, android.os.ResultReceiver r6) {
                /*
                    r3 = this;
                    java.lang.String r0 = "android.support.v4.media.session.command.GET_EXTRA_BINDER"
                    boolean r0 = r4.equals(r0)     // Catch:{ BadParcelableException -> 0x00c7 }
                    r1 = 0
                    if (r0 == 0) goto L_0x003d
                    android.support.v4.media.session.MediaSessionCompat$Callback r4 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00c7 }
                    java.lang.ref.WeakReference<android.support.v4.media.session.MediaSessionCompat$MediaSessionImpl> r4 = r4.c     // Catch:{ BadParcelableException -> 0x00c7 }
                    java.lang.Object r4 = r4.get()     // Catch:{ BadParcelableException -> 0x00c7 }
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi21 r4 = (android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi21) r4     // Catch:{ BadParcelableException -> 0x00c7 }
                    if (r4 == 0) goto L_0x00ce
                    android.os.Bundle r5 = new android.os.Bundle     // Catch:{ BadParcelableException -> 0x00c7 }
                    r5.<init>()     // Catch:{ BadParcelableException -> 0x00c7 }
                    android.support.v4.media.session.MediaSessionCompat$Token r4 = r4.getSessionToken()     // Catch:{ BadParcelableException -> 0x00c7 }
                    android.support.v4.media.session.IMediaSession r0 = r4.getExtraBinder()     // Catch:{ BadParcelableException -> 0x00c7 }
                    java.lang.String r2 = "android.support.v4.media.session.EXTRA_BINDER"
                    if (r0 != 0) goto L_0x0027
                    goto L_0x002b
                L_0x0027:
                    android.os.IBinder r1 = r0.asBinder()     // Catch:{ BadParcelableException -> 0x00c7 }
                L_0x002b:
                    androidx.core.app.BundleCompat.putBinder(r5, r2, r1)     // Catch:{ BadParcelableException -> 0x00c7 }
                    android.os.Bundle r4 = r4.getSessionToken2Bundle()     // Catch:{ BadParcelableException -> 0x00c7 }
                    java.lang.String r0 = "android.support.v4.media.session.SESSION_TOKEN2_BUNDLE"
                    r5.putBundle(r0, r4)     // Catch:{ BadParcelableException -> 0x00c7 }
                    r4 = 0
                    r6.send(r4, r5)     // Catch:{ BadParcelableException -> 0x00c7 }
                    goto L_0x00ce
                L_0x003d:
                    java.lang.String r0 = "android.support.v4.media.session.command.ADD_QUEUE_ITEM"
                    boolean r0 = r4.equals(r0)     // Catch:{ BadParcelableException -> 0x00c7 }
                    if (r0 == 0) goto L_0x0054
                    android.support.v4.media.session.MediaSessionCompat$Callback r4 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00c7 }
                    java.lang.String r6 = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"
                    android.os.Parcelable r5 = r5.getParcelable(r6)     // Catch:{ BadParcelableException -> 0x00c7 }
                    android.support.v4.media.MediaDescriptionCompat r5 = (android.support.v4.media.MediaDescriptionCompat) r5     // Catch:{ BadParcelableException -> 0x00c7 }
                    r4.onAddQueueItem(r5)     // Catch:{ BadParcelableException -> 0x00c7 }
                    goto L_0x00ce
                L_0x0054:
                    java.lang.String r0 = "android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT"
                    boolean r0 = r4.equals(r0)     // Catch:{ BadParcelableException -> 0x00c7 }
                    if (r0 == 0) goto L_0x0070
                    android.support.v4.media.session.MediaSessionCompat$Callback r4 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00c7 }
                    java.lang.String r6 = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"
                    android.os.Parcelable r6 = r5.getParcelable(r6)     // Catch:{ BadParcelableException -> 0x00c7 }
                    android.support.v4.media.MediaDescriptionCompat r6 = (android.support.v4.media.MediaDescriptionCompat) r6     // Catch:{ BadParcelableException -> 0x00c7 }
                    java.lang.String r0 = "android.support.v4.media.session.command.ARGUMENT_INDEX"
                    int r5 = r5.getInt(r0)     // Catch:{ BadParcelableException -> 0x00c7 }
                    r4.onAddQueueItem(r6, r5)     // Catch:{ BadParcelableException -> 0x00c7 }
                    goto L_0x00ce
                L_0x0070:
                    java.lang.String r0 = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM"
                    boolean r0 = r4.equals(r0)     // Catch:{ BadParcelableException -> 0x00c7 }
                    if (r0 == 0) goto L_0x0086
                    android.support.v4.media.session.MediaSessionCompat$Callback r4 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00c7 }
                    java.lang.String r6 = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"
                    android.os.Parcelable r5 = r5.getParcelable(r6)     // Catch:{ BadParcelableException -> 0x00c7 }
                    android.support.v4.media.MediaDescriptionCompat r5 = (android.support.v4.media.MediaDescriptionCompat) r5     // Catch:{ BadParcelableException -> 0x00c7 }
                L_0x0082:
                    r4.onRemoveQueueItem(r5)     // Catch:{ BadParcelableException -> 0x00c7 }
                    goto L_0x00ce
                L_0x0086:
                    java.lang.String r0 = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT"
                    boolean r0 = r4.equals(r0)     // Catch:{ BadParcelableException -> 0x00c7 }
                    if (r0 == 0) goto L_0x00c1
                    android.support.v4.media.session.MediaSessionCompat$Callback r4 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00c7 }
                    java.lang.ref.WeakReference<android.support.v4.media.session.MediaSessionCompat$MediaSessionImpl> r4 = r4.c     // Catch:{ BadParcelableException -> 0x00c7 }
                    java.lang.Object r4 = r4.get()     // Catch:{ BadParcelableException -> 0x00c7 }
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi21 r4 = (android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi21) r4     // Catch:{ BadParcelableException -> 0x00c7 }
                    if (r4 == 0) goto L_0x00ce
                    java.util.List<android.support.v4.media.session.MediaSessionCompat$QueueItem> r6 = r4.f     // Catch:{ BadParcelableException -> 0x00c7 }
                    if (r6 == 0) goto L_0x00ce
                    java.lang.String r6 = "android.support.v4.media.session.command.ARGUMENT_INDEX"
                    r0 = -1
                    int r5 = r5.getInt(r6, r0)     // Catch:{ BadParcelableException -> 0x00c7 }
                    if (r5 < 0) goto L_0x00b8
                    java.util.List<android.support.v4.media.session.MediaSessionCompat$QueueItem> r6 = r4.f     // Catch:{ BadParcelableException -> 0x00c7 }
                    int r6 = r6.size()     // Catch:{ BadParcelableException -> 0x00c7 }
                    if (r5 >= r6) goto L_0x00b8
                    java.util.List<android.support.v4.media.session.MediaSessionCompat$QueueItem> r4 = r4.f     // Catch:{ BadParcelableException -> 0x00c7 }
                    java.lang.Object r4 = r4.get(r5)     // Catch:{ BadParcelableException -> 0x00c7 }
                    r1 = r4
                    android.support.v4.media.session.MediaSessionCompat$QueueItem r1 = (android.support.v4.media.session.MediaSessionCompat.QueueItem) r1     // Catch:{ BadParcelableException -> 0x00c7 }
                L_0x00b8:
                    if (r1 == 0) goto L_0x00ce
                    android.support.v4.media.session.MediaSessionCompat$Callback r4 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00c7 }
                    android.support.v4.media.MediaDescriptionCompat r5 = r1.getDescription()     // Catch:{ BadParcelableException -> 0x00c7 }
                    goto L_0x0082
                L_0x00c1:
                    android.support.v4.media.session.MediaSessionCompat$Callback r0 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00c7 }
                    r0.onCommand(r4, r5, r6)     // Catch:{ BadParcelableException -> 0x00c7 }
                    goto L_0x00ce
                L_0x00c7:
                    java.lang.String r4 = "MediaSessionCompat"
                    java.lang.String r5 = "Could not unparcel the extra data."
                    android.util.Log.e(r4, r5)
                L_0x00ce:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.MediaSessionCompat.Callback.StubApi21.onCommand(java.lang.String, android.os.Bundle, android.os.ResultReceiver):void");
            }

            public void onCustomAction(String str, Bundle bundle) {
                Bundle bundle2 = bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                MediaSessionCompat.ensureClassLoader(bundle2);
                if (str.equals(MediaSessionCompat.ACTION_PLAY_FROM_URI)) {
                    Callback.this.onPlayFromUri((Uri) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI), bundle2);
                } else if (str.equals(MediaSessionCompat.ACTION_PREPARE)) {
                    Callback.this.onPrepare();
                } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_MEDIA_ID)) {
                    Callback.this.onPrepareFromMediaId(bundle.getString(MediaSessionCompat.ACTION_ARGUMENT_MEDIA_ID), bundle2);
                } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_SEARCH)) {
                    Callback.this.onPrepareFromSearch(bundle.getString(MediaSessionCompat.ACTION_ARGUMENT_QUERY), bundle2);
                } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_URI)) {
                    Callback.this.onPrepareFromUri((Uri) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI), bundle2);
                } else if (str.equals(MediaSessionCompat.ACTION_SET_CAPTIONING_ENABLED)) {
                    Callback.this.onSetCaptioningEnabled(bundle.getBoolean(MediaSessionCompat.ACTION_ARGUMENT_CAPTIONING_ENABLED));
                } else if (str.equals(MediaSessionCompat.ACTION_SET_REPEAT_MODE)) {
                    Callback.this.onSetRepeatMode(bundle.getInt(MediaSessionCompat.ACTION_ARGUMENT_REPEAT_MODE));
                } else if (str.equals(MediaSessionCompat.ACTION_SET_SHUFFLE_MODE)) {
                    Callback.this.onSetShuffleMode(bundle.getInt(MediaSessionCompat.ACTION_ARGUMENT_SHUFFLE_MODE));
                } else if (str.equals(MediaSessionCompat.ACTION_SET_RATING)) {
                    Callback.this.onSetRating((RatingCompat) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_RATING), bundle2);
                } else {
                    Callback.this.onCustomAction(str, bundle);
                }
            }

            public void onFastForward() {
                Callback.this.onFastForward();
            }

            public boolean onMediaButtonEvent(Intent intent) {
                return Callback.this.onMediaButtonEvent(intent);
            }

            public void onPause() {
                Callback.this.onPause();
            }

            public void onPlay() {
                Callback.this.onPlay();
            }

            public void onPlayFromMediaId(String str, Bundle bundle) {
                Callback.this.onPlayFromMediaId(str, bundle);
            }

            public void onPlayFromSearch(String str, Bundle bundle) {
                Callback.this.onPlayFromSearch(str, bundle);
            }

            public void onRewind() {
                Callback.this.onRewind();
            }

            public void onSeekTo(long j) {
                Callback.this.onSeekTo(j);
            }

            public void onSetRating(Object obj) {
                Callback.this.onSetRating(RatingCompat.fromRating(obj));
            }

            public void onSetRating(Object obj, Bundle bundle) {
            }

            public void onSkipToNext() {
                Callback.this.onSkipToNext();
            }

            public void onSkipToPrevious() {
                Callback.this.onSkipToPrevious();
            }

            public void onSkipToQueueItem(long j) {
                Callback.this.onSkipToQueueItem(j);
            }

            public void onStop() {
                Callback.this.onStop();
            }
        }

        @RequiresApi(23)
        private class StubApi23 extends StubApi21 implements MediaSessionCompatApi23.Callback {
            StubApi23() {
                super();
            }

            public void onPlayFromUri(Uri uri, Bundle bundle) {
                Callback.this.onPlayFromUri(uri, bundle);
            }
        }

        @RequiresApi(24)
        private class StubApi24 extends StubApi23 implements MediaSessionCompatApi24.Callback {
            StubApi24() {
                super();
            }

            public void onPrepare() {
                Callback.this.onPrepare();
            }

            public void onPrepareFromMediaId(String str, Bundle bundle) {
                Callback.this.onPrepareFromMediaId(str, bundle);
            }

            public void onPrepareFromSearch(String str, Bundle bundle) {
                Callback.this.onPrepareFromSearch(str, bundle);
            }

            public void onPrepareFromUri(Uri uri, Bundle bundle) {
                Callback.this.onPrepareFromUri(uri, bundle);
            }
        }

        public Callback() {
            Object obj = null;
            if (Build.VERSION.SDK_INT >= 24) {
                obj = MediaSessionCompatApi24.createCallback(new StubApi24());
            } else if (Build.VERSION.SDK_INT >= 23) {
                obj = MediaSessionCompatApi23.createCallback(new StubApi23());
            } else if (Build.VERSION.SDK_INT >= 21) {
                obj = MediaSessionCompatApi21.createCallback(new StubApi21());
            }
            this.b = obj;
        }

        /* access modifiers changed from: package-private */
        public void a(MediaSessionImpl mediaSessionImpl, Handler handler) {
            this.c = new WeakReference<>(mediaSessionImpl);
            CallbackHandler callbackHandler = this.mCallbackHandler;
            if (callbackHandler != null) {
                callbackHandler.removeCallbacksAndMessages((Object) null);
            }
            this.mCallbackHandler = new CallbackHandler(handler.getLooper());
        }

        /* access modifiers changed from: package-private */
        public void a(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            if (this.mMediaPlayPauseKeyPending) {
                boolean z = false;
                this.mMediaPlayPauseKeyPending = false;
                this.mCallbackHandler.removeMessages(1);
                MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) this.c.get();
                if (mediaSessionImpl != null) {
                    PlaybackStateCompat playbackState = mediaSessionImpl.getPlaybackState();
                    long actions = playbackState == null ? 0 : playbackState.getActions();
                    boolean z2 = playbackState != null && playbackState.getState() == 3;
                    boolean z3 = (516 & actions) != 0;
                    if ((actions & 514) != 0) {
                        z = true;
                    }
                    mediaSessionImpl.setCurrentControllerInfo(remoteUserInfo);
                    if (z2 && z) {
                        onPause();
                    } else if (!z2 && z3) {
                        onPlay();
                    }
                    mediaSessionImpl.setCurrentControllerInfo((MediaSessionManager.RemoteUserInfo) null);
                }
            }
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        }

        public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        }

        public void onCustomAction(String str, Bundle bundle) {
        }

        public void onFastForward() {
        }

        public boolean onMediaButtonEvent(Intent intent) {
            MediaSessionImpl mediaSessionImpl;
            KeyEvent keyEvent;
            if (Build.VERSION.SDK_INT >= 27 || (mediaSessionImpl = (MediaSessionImpl) this.c.get()) == null || this.mCallbackHandler == null || (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) == null || keyEvent.getAction() != 0) {
                return false;
            }
            MediaSessionManager.RemoteUserInfo currentControllerInfo = mediaSessionImpl.getCurrentControllerInfo();
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 79 || keyCode == 85) {
                if (keyEvent.getRepeatCount() > 0) {
                    a(currentControllerInfo);
                } else if (this.mMediaPlayPauseKeyPending) {
                    this.mCallbackHandler.removeMessages(1);
                    this.mMediaPlayPauseKeyPending = false;
                    PlaybackStateCompat playbackState = mediaSessionImpl.getPlaybackState();
                    if (((playbackState == null ? 0 : playbackState.getActions()) & 32) != 0) {
                        onSkipToNext();
                    }
                } else {
                    this.mMediaPlayPauseKeyPending = true;
                    CallbackHandler callbackHandler = this.mCallbackHandler;
                    callbackHandler.sendMessageDelayed(callbackHandler.obtainMessage(1, currentControllerInfo), (long) ViewConfiguration.getDoubleTapTimeout());
                }
                return true;
            }
            a(currentControllerInfo);
            return false;
        }

        public void onPause() {
        }

        public void onPlay() {
        }

        public void onPlayFromMediaId(String str, Bundle bundle) {
        }

        public void onPlayFromSearch(String str, Bundle bundle) {
        }

        public void onPlayFromUri(Uri uri, Bundle bundle) {
        }

        public void onPrepare() {
        }

        public void onPrepareFromMediaId(String str, Bundle bundle) {
        }

        public void onPrepareFromSearch(String str, Bundle bundle) {
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle) {
        }

        public void onRemoveQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        @Deprecated
        public void onRemoveQueueItemAt(int i) {
        }

        public void onRewind() {
        }

        public void onSeekTo(long j) {
        }

        public void onSetCaptioningEnabled(boolean z) {
        }

        public void onSetRating(RatingCompat ratingCompat) {
        }

        public void onSetRating(RatingCompat ratingCompat, Bundle bundle) {
        }

        public void onSetRepeatMode(int i) {
        }

        public void onSetShuffleMode(int i) {
        }

        public void onSkipToNext() {
        }

        public void onSkipToPrevious() {
        }

        public void onSkipToQueueItem(long j) {
        }

        public void onStop() {
        }
    }

    interface MediaSessionImpl {
        String getCallingPackage();

        MediaSessionManager.RemoteUserInfo getCurrentControllerInfo();

        Object getMediaSession();

        PlaybackStateCompat getPlaybackState();

        Object getRemoteControlClient();

        Token getSessionToken();

        boolean isActive();

        void release();

        void sendSessionEvent(String str, Bundle bundle);

        void setActive(boolean z);

        void setCallback(Callback callback, Handler handler);

        void setCaptioningEnabled(boolean z);

        void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo);

        void setExtras(Bundle bundle);

        void setFlags(int i);

        void setMediaButtonReceiver(PendingIntent pendingIntent);

        void setMetadata(MediaMetadataCompat mediaMetadataCompat);

        void setPlaybackState(PlaybackStateCompat playbackStateCompat);

        void setPlaybackToLocal(int i);

        void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat);

        void setQueue(List<QueueItem> list);

        void setQueueTitle(CharSequence charSequence);

        void setRatingType(int i);

        void setRepeatMode(int i);

        void setSessionActivity(PendingIntent pendingIntent);

        void setShuffleMode(int i);
    }

    @RequiresApi(18)
    static class MediaSessionImplApi18 extends MediaSessionImplBase {
        private static boolean sIsMbrPendingIntentSupported = true;

        MediaSessionImplApi18(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            super(context, str, componentName, pendingIntent);
        }

        /* access modifiers changed from: package-private */
        public int a(long j) {
            int a = super.a(j);
            return (j & 256) != 0 ? a | 256 : a;
        }

        /* access modifiers changed from: package-private */
        public void a(PendingIntent pendingIntent, ComponentName componentName) {
            if (sIsMbrPendingIntentSupported) {
                try {
                    this.c.registerMediaButtonEventReceiver(pendingIntent);
                } catch (NullPointerException unused) {
                    Log.w("MediaSessionCompat", "Unable to register media button event receiver with PendingIntent, falling back to ComponentName.");
                    sIsMbrPendingIntentSupported = false;
                }
            }
            if (!sIsMbrPendingIntentSupported) {
                super.a(pendingIntent, componentName);
            }
        }

        /* access modifiers changed from: package-private */
        public void a(PlaybackStateCompat playbackStateCompat) {
            long position = playbackStateCompat.getPosition();
            float playbackSpeed = playbackStateCompat.getPlaybackSpeed();
            long lastPositionUpdateTime = playbackStateCompat.getLastPositionUpdateTime();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (playbackStateCompat.getState() == 3) {
                long j = 0;
                if (position > 0) {
                    if (lastPositionUpdateTime > 0) {
                        j = elapsedRealtime - lastPositionUpdateTime;
                        if (playbackSpeed > 0.0f && playbackSpeed != 1.0f) {
                            j = (long) (((float) j) * playbackSpeed);
                        }
                    }
                    position += j;
                }
            }
            this.d.setPlaybackState(a(playbackStateCompat.getState()), position, playbackSpeed);
        }

        /* access modifiers changed from: package-private */
        public void b(PendingIntent pendingIntent, ComponentName componentName) {
            if (sIsMbrPendingIntentSupported) {
                this.c.unregisterMediaButtonEventReceiver(pendingIntent);
            } else {
                super.b(pendingIntent, componentName);
            }
        }

        public void setCallback(Callback callback, Handler handler) {
            super.setCallback(callback, handler);
            if (callback == null) {
                this.d.setPlaybackPositionUpdateListener((RemoteControlClient.OnPlaybackPositionUpdateListener) null);
                return;
            }
            this.d.setPlaybackPositionUpdateListener(new RemoteControlClient.OnPlaybackPositionUpdateListener() {
                public void onPlaybackPositionUpdate(long j) {
                    MediaSessionImplApi18.this.a(18, -1, -1, Long.valueOf(j), (Bundle) null);
                }
            });
        }
    }

    @RequiresApi(19)
    static class MediaSessionImplApi19 extends MediaSessionImplApi18 {
        MediaSessionImplApi19(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            super(context, str, componentName, pendingIntent);
        }

        /* access modifiers changed from: package-private */
        public int a(long j) {
            int a = super.a(j);
            return (j & 128) != 0 ? a | 512 : a;
        }

        /* access modifiers changed from: package-private */
        public RemoteControlClient.MetadataEditor a(Bundle bundle) {
            RemoteControlClient.MetadataEditor a = super.a(bundle);
            if (((this.l == null ? 0 : this.l.getActions()) & 128) != 0) {
                a.addEditableKey(268435457);
            }
            if (bundle == null) {
                return a;
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_YEAR)) {
                a.putLong(8, bundle.getLong(MediaMetadataCompat.METADATA_KEY_YEAR));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_RATING)) {
                a.putObject(101, bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_RATING));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_USER_RATING)) {
                a.putObject(268435457, bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_USER_RATING));
            }
            return a;
        }

        public void setCallback(Callback callback, Handler handler) {
            super.setCallback(callback, handler);
            if (callback == null) {
                this.d.setMetadataUpdateListener((RemoteControlClient.OnMetadataUpdateListener) null);
                return;
            }
            this.d.setMetadataUpdateListener(new RemoteControlClient.OnMetadataUpdateListener() {
                public void onMetadataUpdate(int i, Object obj) {
                    if (i == 268435457 && (obj instanceof Rating)) {
                        MediaSessionImplApi19.this.a(19, -1, -1, RatingCompat.fromRating(obj), (Bundle) null);
                    }
                }
            });
        }
    }

    @RequiresApi(21)
    static class MediaSessionImplApi21 implements MediaSessionImpl {
        final Object a;
        final Token b;
        boolean c = false;
        final RemoteCallbackList<IMediaControllerCallback> d = new RemoteCallbackList<>();
        PlaybackStateCompat e;
        List<QueueItem> f;
        MediaMetadataCompat g;
        int h;
        boolean i;
        int j;
        int k;

        class ExtraSession extends IMediaSession.Stub {
            ExtraSession() {
            }

            public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            public void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i) {
                throw new AssertionError();
            }

            public void adjustVolume(int i, int i2, String str) {
                throw new AssertionError();
            }

            public void fastForward() {
                throw new AssertionError();
            }

            public Bundle getExtras() {
                throw new AssertionError();
            }

            public long getFlags() {
                throw new AssertionError();
            }

            public PendingIntent getLaunchPendingIntent() {
                throw new AssertionError();
            }

            public MediaMetadataCompat getMetadata() {
                throw new AssertionError();
            }

            public String getPackageName() {
                throw new AssertionError();
            }

            public PlaybackStateCompat getPlaybackState() {
                return MediaSessionCompat.a(MediaSessionImplApi21.this.e, MediaSessionImplApi21.this.g);
            }

            public List<QueueItem> getQueue() {
                return null;
            }

            public CharSequence getQueueTitle() {
                throw new AssertionError();
            }

            public int getRatingType() {
                return MediaSessionImplApi21.this.h;
            }

            public int getRepeatMode() {
                return MediaSessionImplApi21.this.j;
            }

            public int getShuffleMode() {
                return MediaSessionImplApi21.this.k;
            }

            public String getTag() {
                throw new AssertionError();
            }

            public ParcelableVolumeInfo getVolumeAttributes() {
                throw new AssertionError();
            }

            public boolean isCaptioningEnabled() {
                return MediaSessionImplApi21.this.i;
            }

            public boolean isShuffleModeEnabledRemoved() {
                return false;
            }

            public boolean isTransportControlEnabled() {
                throw new AssertionError();
            }

            public void next() {
                throw new AssertionError();
            }

            public void pause() {
                throw new AssertionError();
            }

            public void play() {
                throw new AssertionError();
            }

            public void playFromMediaId(String str, Bundle bundle) {
                throw new AssertionError();
            }

            public void playFromSearch(String str, Bundle bundle) {
                throw new AssertionError();
            }

            public void playFromUri(Uri uri, Bundle bundle) {
                throw new AssertionError();
            }

            public void prepare() {
                throw new AssertionError();
            }

            public void prepareFromMediaId(String str, Bundle bundle) {
                throw new AssertionError();
            }

            public void prepareFromSearch(String str, Bundle bundle) {
                throw new AssertionError();
            }

            public void prepareFromUri(Uri uri, Bundle bundle) {
                throw new AssertionError();
            }

            public void previous() {
                throw new AssertionError();
            }

            public void rate(RatingCompat ratingCompat) {
                throw new AssertionError();
            }

            public void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) {
                throw new AssertionError();
            }

            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                if (!MediaSessionImplApi21.this.c) {
                    String callingPackage = MediaSessionImplApi21.this.getCallingPackage();
                    if (callingPackage == null) {
                        callingPackage = MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER;
                    }
                    MediaSessionImplApi21.this.d.register(iMediaControllerCallback, new MediaSessionManager.RemoteUserInfo(callingPackage, getCallingPid(), getCallingUid()));
                }
            }

            public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            public void removeQueueItemAt(int i) {
                throw new AssertionError();
            }

            public void rewind() {
                throw new AssertionError();
            }

            public void seekTo(long j) {
                throw new AssertionError();
            }

            public void sendCommand(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                throw new AssertionError();
            }

            public void sendCustomAction(String str, Bundle bundle) {
                throw new AssertionError();
            }

            public boolean sendMediaButton(KeyEvent keyEvent) {
                throw new AssertionError();
            }

            public void setCaptioningEnabled(boolean z) {
                throw new AssertionError();
            }

            public void setRepeatMode(int i) {
                throw new AssertionError();
            }

            public void setShuffleMode(int i) {
                throw new AssertionError();
            }

            public void setShuffleModeEnabledRemoved(boolean z) {
            }

            public void setVolumeTo(int i, int i2, String str) {
                throw new AssertionError();
            }

            public void skipToQueueItem(long j) {
                throw new AssertionError();
            }

            public void stop() {
                throw new AssertionError();
            }

            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                MediaSessionImplApi21.this.d.unregister(iMediaControllerCallback);
            }
        }

        MediaSessionImplApi21(Context context, String str, Bundle bundle) {
            this.a = MediaSessionCompatApi21.createSession(context, str);
            this.b = new Token(MediaSessionCompatApi21.getSessionToken(this.a), new ExtraSession(), bundle);
        }

        MediaSessionImplApi21(Object obj) {
            this.a = MediaSessionCompatApi21.verifySession(obj);
            this.b = new Token(MediaSessionCompatApi21.getSessionToken(this.a), new ExtraSession());
        }

        public String getCallingPackage() {
            if (Build.VERSION.SDK_INT < 24) {
                return null;
            }
            return MediaSessionCompatApi24.getCallingPackage(this.a);
        }

        public MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
            return null;
        }

        public Object getMediaSession() {
            return this.a;
        }

        public PlaybackStateCompat getPlaybackState() {
            return this.e;
        }

        public Object getRemoteControlClient() {
            return null;
        }

        public Token getSessionToken() {
            return this.b;
        }

        public boolean isActive() {
            return MediaSessionCompatApi21.isActive(this.a);
        }

        public void release() {
            this.c = true;
            MediaSessionCompatApi21.release(this.a);
        }

        public void sendSessionEvent(String str, Bundle bundle) {
            if (Build.VERSION.SDK_INT < 23) {
                for (int beginBroadcast = this.d.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                    try {
                        this.d.getBroadcastItem(beginBroadcast).onEvent(str, bundle);
                    } catch (RemoteException unused) {
                    }
                }
                this.d.finishBroadcast();
            }
            MediaSessionCompatApi21.sendSessionEvent(this.a, str, bundle);
        }

        public void setActive(boolean z) {
            MediaSessionCompatApi21.setActive(this.a, z);
        }

        public void setCallback(Callback callback, Handler handler) {
            MediaSessionCompatApi21.setCallback(this.a, callback == null ? null : callback.b, handler);
            if (callback != null) {
                callback.a(this, handler);
            }
        }

        public void setCaptioningEnabled(boolean z) {
            if (this.i != z) {
                this.i = z;
                for (int beginBroadcast = this.d.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                    try {
                        this.d.getBroadcastItem(beginBroadcast).onCaptioningEnabledChanged(z);
                    } catch (RemoteException unused) {
                    }
                }
                this.d.finishBroadcast();
            }
        }

        public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        }

        public void setExtras(Bundle bundle) {
            MediaSessionCompatApi21.setExtras(this.a, bundle);
        }

        public void setFlags(int i2) {
            MediaSessionCompatApi21.setFlags(this.a, i2);
        }

        public void setMediaButtonReceiver(PendingIntent pendingIntent) {
            MediaSessionCompatApi21.setMediaButtonReceiver(this.a, pendingIntent);
        }

        public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
            this.g = mediaMetadataCompat;
            MediaSessionCompatApi21.setMetadata(this.a, mediaMetadataCompat == null ? null : mediaMetadataCompat.getMediaMetadata());
        }

        public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
            this.e = playbackStateCompat;
            for (int beginBroadcast = this.d.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.d.getBroadcastItem(beginBroadcast).onPlaybackStateChanged(playbackStateCompat);
                } catch (RemoteException unused) {
                }
            }
            this.d.finishBroadcast();
            MediaSessionCompatApi21.setPlaybackState(this.a, playbackStateCompat == null ? null : playbackStateCompat.getPlaybackState());
        }

        public void setPlaybackToLocal(int i2) {
            MediaSessionCompatApi21.setPlaybackToLocal(this.a, i2);
        }

        public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
            MediaSessionCompatApi21.setPlaybackToRemote(this.a, volumeProviderCompat.getVolumeProvider());
        }

        public void setQueue(List<QueueItem> list) {
            ArrayList arrayList;
            this.f = list;
            if (list != null) {
                arrayList = new ArrayList();
                for (QueueItem queueItem : list) {
                    arrayList.add(queueItem.getQueueItem());
                }
            } else {
                arrayList = null;
            }
            MediaSessionCompatApi21.setQueue(this.a, arrayList);
        }

        public void setQueueTitle(CharSequence charSequence) {
            MediaSessionCompatApi21.setQueueTitle(this.a, charSequence);
        }

        public void setRatingType(int i2) {
            if (Build.VERSION.SDK_INT < 22) {
                this.h = i2;
            } else {
                MediaSessionCompatApi22.setRatingType(this.a, i2);
            }
        }

        public void setRepeatMode(int i2) {
            if (this.j != i2) {
                this.j = i2;
                for (int beginBroadcast = this.d.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                    try {
                        this.d.getBroadcastItem(beginBroadcast).onRepeatModeChanged(i2);
                    } catch (RemoteException unused) {
                    }
                }
                this.d.finishBroadcast();
            }
        }

        public void setSessionActivity(PendingIntent pendingIntent) {
            MediaSessionCompatApi21.setSessionActivity(this.a, pendingIntent);
        }

        public void setShuffleMode(int i2) {
            if (this.k != i2) {
                this.k = i2;
                for (int beginBroadcast = this.d.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                    try {
                        this.d.getBroadcastItem(beginBroadcast).onShuffleModeChanged(i2);
                    } catch (RemoteException unused) {
                    }
                }
                this.d.finishBroadcast();
            }
        }
    }

    @RequiresApi(28)
    static class MediaSessionImplApi28 extends MediaSessionImplApi21 {
        MediaSessionImplApi28(Context context, String str, Bundle bundle) {
            super(context, str, bundle);
        }

        @NonNull
        public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
            return new MediaSessionManager.RemoteUserInfo(((MediaSession) this.a).getCurrentControllerInfo());
        }

        public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        }
    }

    static class MediaSessionImplBase implements MediaSessionImpl {
        final String a;
        final String b;
        final AudioManager c;
        final RemoteControlClient d;
        final Object e = new Object();
        final RemoteCallbackList<IMediaControllerCallback> f = new RemoteCallbackList<>();
        boolean g = false;
        boolean h = false;
        volatile Callback i;
        int j;
        MediaMetadataCompat k;
        PlaybackStateCompat l;
        PendingIntent m;
        private final Context mContext;
        private MessageHandler mHandler;
        private boolean mIsMbrRegistered = false;
        private boolean mIsRccRegistered = false;
        private final ComponentName mMediaButtonReceiverComponentName;
        private final PendingIntent mMediaButtonReceiverIntent;
        private MediaSessionManager.RemoteUserInfo mRemoteUserInfo;
        private final MediaSessionStub mStub;
        private final Token mToken;
        private VolumeProviderCompat.Callback mVolumeCallback = new VolumeProviderCompat.Callback() {
            public void onVolumeChanged(VolumeProviderCompat volumeProviderCompat) {
                if (MediaSessionImplBase.this.w == volumeProviderCompat) {
                    MediaSessionImplBase.this.a(new ParcelableVolumeInfo(MediaSessionImplBase.this.u, MediaSessionImplBase.this.v, volumeProviderCompat.getVolumeControl(), volumeProviderCompat.getMaxVolume(), volumeProviderCompat.getCurrentVolume()));
                }
            }
        };
        List<QueueItem> n;
        CharSequence o;
        int p;
        boolean q;
        int r;
        int s;
        Bundle t;
        int u;
        int v;
        VolumeProviderCompat w;

        private static final class Command {
            public final String command;
            public final Bundle extras;
            public final ResultReceiver stub;

            public Command(String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.command = str;
                this.extras = bundle;
                this.stub = resultReceiver;
            }
        }

        class MediaSessionStub extends IMediaSession.Stub {
            MediaSessionStub() {
            }

            /* access modifiers changed from: package-private */
            public void a(int i) {
                MediaSessionImplBase.this.a(i, 0, 0, (Object) null, (Bundle) null);
            }

            /* access modifiers changed from: package-private */
            public void a(int i, int i2) {
                MediaSessionImplBase.this.a(i, i2, 0, (Object) null, (Bundle) null);
            }

            /* access modifiers changed from: package-private */
            public void a(int i, Object obj) {
                MediaSessionImplBase.this.a(i, 0, 0, obj, (Bundle) null);
            }

            /* access modifiers changed from: package-private */
            public void a(int i, Object obj, int i2) {
                MediaSessionImplBase.this.a(i, i2, 0, obj, (Bundle) null);
            }

            /* access modifiers changed from: package-private */
            public void a(int i, Object obj, Bundle bundle) {
                MediaSessionImplBase.this.a(i, 0, 0, obj, bundle);
            }

            public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                a(25, (Object) mediaDescriptionCompat);
            }

            public void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i) {
                a(26, (Object) mediaDescriptionCompat, i);
            }

            public void adjustVolume(int i, int i2, String str) {
                MediaSessionImplBase.this.a(i, i2);
            }

            public void fastForward() {
                a(16);
            }

            public Bundle getExtras() {
                Bundle bundle;
                synchronized (MediaSessionImplBase.this.e) {
                    bundle = MediaSessionImplBase.this.t;
                }
                return bundle;
            }

            public long getFlags() {
                long j;
                synchronized (MediaSessionImplBase.this.e) {
                    j = (long) MediaSessionImplBase.this.j;
                }
                return j;
            }

            public PendingIntent getLaunchPendingIntent() {
                PendingIntent pendingIntent;
                synchronized (MediaSessionImplBase.this.e) {
                    pendingIntent = MediaSessionImplBase.this.m;
                }
                return pendingIntent;
            }

            public MediaMetadataCompat getMetadata() {
                return MediaSessionImplBase.this.k;
            }

            public String getPackageName() {
                return MediaSessionImplBase.this.a;
            }

            public PlaybackStateCompat getPlaybackState() {
                PlaybackStateCompat playbackStateCompat;
                MediaMetadataCompat mediaMetadataCompat;
                synchronized (MediaSessionImplBase.this.e) {
                    playbackStateCompat = MediaSessionImplBase.this.l;
                    mediaMetadataCompat = MediaSessionImplBase.this.k;
                }
                return MediaSessionCompat.a(playbackStateCompat, mediaMetadataCompat);
            }

            public List<QueueItem> getQueue() {
                List<QueueItem> list;
                synchronized (MediaSessionImplBase.this.e) {
                    list = MediaSessionImplBase.this.n;
                }
                return list;
            }

            public CharSequence getQueueTitle() {
                return MediaSessionImplBase.this.o;
            }

            public int getRatingType() {
                return MediaSessionImplBase.this.p;
            }

            public int getRepeatMode() {
                return MediaSessionImplBase.this.r;
            }

            public int getShuffleMode() {
                return MediaSessionImplBase.this.s;
            }

            public String getTag() {
                return MediaSessionImplBase.this.b;
            }

            public ParcelableVolumeInfo getVolumeAttributes() {
                int i;
                int i2;
                int i3;
                int i4;
                int i5;
                synchronized (MediaSessionImplBase.this.e) {
                    i = MediaSessionImplBase.this.u;
                    i2 = MediaSessionImplBase.this.v;
                    VolumeProviderCompat volumeProviderCompat = MediaSessionImplBase.this.w;
                    if (i == 2) {
                        int volumeControl = volumeProviderCompat.getVolumeControl();
                        int maxVolume = volumeProviderCompat.getMaxVolume();
                        i3 = volumeProviderCompat.getCurrentVolume();
                        i4 = maxVolume;
                        i5 = volumeControl;
                    } else {
                        i4 = MediaSessionImplBase.this.c.getStreamMaxVolume(i2);
                        i3 = MediaSessionImplBase.this.c.getStreamVolume(i2);
                        i5 = 2;
                    }
                }
                return new ParcelableVolumeInfo(i, i2, i5, i4, i3);
            }

            public boolean isCaptioningEnabled() {
                return MediaSessionImplBase.this.q;
            }

            public boolean isShuffleModeEnabledRemoved() {
                return false;
            }

            public boolean isTransportControlEnabled() {
                return (MediaSessionImplBase.this.j & 2) != 0;
            }

            public void next() {
                a(14);
            }

            public void pause() {
                a(12);
            }

            public void play() {
                a(7);
            }

            public void playFromMediaId(String str, Bundle bundle) {
                a(8, (Object) str, bundle);
            }

            public void playFromSearch(String str, Bundle bundle) {
                a(9, (Object) str, bundle);
            }

            public void playFromUri(Uri uri, Bundle bundle) {
                a(10, (Object) uri, bundle);
            }

            public void prepare() {
                a(3);
            }

            public void prepareFromMediaId(String str, Bundle bundle) {
                a(4, (Object) str, bundle);
            }

            public void prepareFromSearch(String str, Bundle bundle) {
                a(5, (Object) str, bundle);
            }

            public void prepareFromUri(Uri uri, Bundle bundle) {
                a(6, (Object) uri, bundle);
            }

            public void previous() {
                a(15);
            }

            public void rate(RatingCompat ratingCompat) {
                a(19, (Object) ratingCompat);
            }

            public void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) {
                a(31, (Object) ratingCompat, bundle);
            }

            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                if (MediaSessionImplBase.this.g) {
                    try {
                        iMediaControllerCallback.onSessionDestroyed();
                    } catch (Exception unused) {
                    }
                } else {
                    MediaSessionImplBase.this.f.register(iMediaControllerCallback, new MediaSessionManager.RemoteUserInfo(MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER, getCallingPid(), getCallingUid()));
                }
            }

            public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                a(27, (Object) mediaDescriptionCompat);
            }

            public void removeQueueItemAt(int i) {
                a(28, i);
            }

            public void rewind() {
                a(17);
            }

            public void seekTo(long j) {
                a(18, (Object) Long.valueOf(j));
            }

            public void sendCommand(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                a(1, (Object) new Command(str, bundle, resultReceiverWrapper.a));
            }

            public void sendCustomAction(String str, Bundle bundle) {
                a(20, (Object) str, bundle);
            }

            public boolean sendMediaButton(KeyEvent keyEvent) {
                boolean z = true;
                if ((MediaSessionImplBase.this.j & 1) == 0) {
                    z = false;
                }
                if (z) {
                    a(21, (Object) keyEvent);
                }
                return z;
            }

            public void setCaptioningEnabled(boolean z) {
                a(29, (Object) Boolean.valueOf(z));
            }

            public void setRepeatMode(int i) {
                a(23, i);
            }

            public void setShuffleMode(int i) {
                a(30, i);
            }

            public void setShuffleModeEnabledRemoved(boolean z) {
            }

            public void setVolumeTo(int i, int i2, String str) {
                MediaSessionImplBase.this.b(i, i2);
            }

            public void skipToQueueItem(long j) {
                a(11, (Object) Long.valueOf(j));
            }

            public void stop() {
                a(13);
            }

            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                MediaSessionImplBase.this.f.unregister(iMediaControllerCallback);
            }
        }

        class MessageHandler extends Handler {
            private static final int KEYCODE_MEDIA_PAUSE = 127;
            private static final int KEYCODE_MEDIA_PLAY = 126;
            private static final int MSG_ADD_QUEUE_ITEM = 25;
            private static final int MSG_ADD_QUEUE_ITEM_AT = 26;
            private static final int MSG_ADJUST_VOLUME = 2;
            private static final int MSG_COMMAND = 1;
            private static final int MSG_CUSTOM_ACTION = 20;
            private static final int MSG_FAST_FORWARD = 16;
            private static final int MSG_MEDIA_BUTTON = 21;
            private static final int MSG_NEXT = 14;
            private static final int MSG_PAUSE = 12;
            private static final int MSG_PLAY = 7;
            private static final int MSG_PLAY_MEDIA_ID = 8;
            private static final int MSG_PLAY_SEARCH = 9;
            private static final int MSG_PLAY_URI = 10;
            private static final int MSG_PREPARE = 3;
            private static final int MSG_PREPARE_MEDIA_ID = 4;
            private static final int MSG_PREPARE_SEARCH = 5;
            private static final int MSG_PREPARE_URI = 6;
            private static final int MSG_PREVIOUS = 15;
            private static final int MSG_RATE = 19;
            private static final int MSG_RATE_EXTRA = 31;
            private static final int MSG_REMOVE_QUEUE_ITEM = 27;
            private static final int MSG_REMOVE_QUEUE_ITEM_AT = 28;
            private static final int MSG_REWIND = 17;
            private static final int MSG_SEEK_TO = 18;
            private static final int MSG_SET_CAPTIONING_ENABLED = 29;
            private static final int MSG_SET_REPEAT_MODE = 23;
            private static final int MSG_SET_SHUFFLE_MODE = 30;
            private static final int MSG_SET_VOLUME = 22;
            private static final int MSG_SKIP_TO_ITEM = 11;
            private static final int MSG_STOP = 13;

            public MessageHandler(Looper looper) {
                super(looper);
            }

            private void onMediaButtonEvent(KeyEvent keyEvent, Callback callback) {
                if (keyEvent != null && keyEvent.getAction() == 0) {
                    long actions = MediaSessionImplBase.this.l == null ? 0 : MediaSessionImplBase.this.l.getActions();
                    int keyCode = keyEvent.getKeyCode();
                    if (keyCode != 79) {
                        switch (keyCode) {
                            case 85:
                                break;
                            case 86:
                                if ((actions & 1) != 0) {
                                    callback.onStop();
                                    return;
                                }
                                return;
                            case 87:
                                if ((actions & 32) != 0) {
                                    callback.onSkipToNext();
                                    return;
                                }
                                return;
                            case 88:
                                if ((actions & 16) != 0) {
                                    callback.onSkipToPrevious();
                                    return;
                                }
                                return;
                            case 89:
                                if ((actions & 8) != 0) {
                                    callback.onRewind();
                                    return;
                                }
                                return;
                            case 90:
                                if ((actions & 64) != 0) {
                                    callback.onFastForward();
                                    return;
                                }
                                return;
                            default:
                                switch (keyCode) {
                                    case KEYCODE_MEDIA_PLAY /*126*/:
                                        if ((actions & 4) != 0) {
                                            callback.onPlay();
                                            return;
                                        }
                                        return;
                                    case KEYCODE_MEDIA_PAUSE /*127*/:
                                        if ((actions & 2) != 0) {
                                            callback.onPause();
                                            return;
                                        }
                                        return;
                                    default:
                                        return;
                                }
                        }
                    }
                    Log.w("MediaSessionCompat", "KEYCODE_MEDIA_PLAY_PAUSE and KEYCODE_HEADSETHOOK are handled already");
                }
            }

            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void handleMessage(android.os.Message r8) {
                /*
                    r7 = this;
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase r0 = android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.this
                    android.support.v4.media.session.MediaSessionCompat$Callback r0 = r0.i
                    if (r0 != 0) goto L_0x0007
                    return
                L_0x0007:
                    android.os.Bundle r1 = r8.getData()
                    android.support.v4.media.session.MediaSessionCompat.ensureClassLoader(r1)
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase r2 = android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.this
                    androidx.media.MediaSessionManager$RemoteUserInfo r3 = new androidx.media.MediaSessionManager$RemoteUserInfo
                    java.lang.String r4 = "data_calling_pkg"
                    java.lang.String r4 = r1.getString(r4)
                    java.lang.String r5 = "data_calling_pid"
                    int r5 = r1.getInt(r5)
                    java.lang.String r6 = "data_calling_uid"
                    int r6 = r1.getInt(r6)
                    r3.<init>(r4, r5, r6)
                    r2.setCurrentControllerInfo(r3)
                    java.lang.String r2 = "data_extras"
                    android.os.Bundle r1 = r1.getBundle(r2)
                    android.support.v4.media.session.MediaSessionCompat.ensureClassLoader(r1)
                    r2 = 0
                    int r3 = r8.what     // Catch:{ all -> 0x0165 }
                    r4 = 0
                    switch(r3) {
                        case 1: goto L_0x0152;
                        case 2: goto L_0x014a;
                        case 3: goto L_0x0146;
                        case 4: goto L_0x013e;
                        case 5: goto L_0x0136;
                        case 6: goto L_0x012e;
                        case 7: goto L_0x012a;
                        case 8: goto L_0x0122;
                        case 9: goto L_0x011a;
                        case 10: goto L_0x0112;
                        case 11: goto L_0x0106;
                        case 12: goto L_0x0102;
                        case 13: goto L_0x00fe;
                        case 14: goto L_0x00fa;
                        case 15: goto L_0x00f5;
                        case 16: goto L_0x00f0;
                        case 17: goto L_0x00eb;
                        case 18: goto L_0x00de;
                        case 19: goto L_0x00d5;
                        case 20: goto L_0x00cc;
                        case 21: goto L_0x00b1;
                        case 22: goto L_0x00a8;
                        case 23: goto L_0x00a1;
                        case 24: goto L_0x003a;
                        case 25: goto L_0x0098;
                        case 26: goto L_0x008d;
                        case 27: goto L_0x0088;
                        case 28: goto L_0x0059;
                        case 29: goto L_0x004c;
                        case 30: goto L_0x0045;
                        case 31: goto L_0x003c;
                        default: goto L_0x003a;
                    }     // Catch:{ all -> 0x0165 }
                L_0x003a:
                    goto L_0x015f
                L_0x003c:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    android.support.v4.media.RatingCompat r8 = (android.support.v4.media.RatingCompat) r8     // Catch:{ all -> 0x0165 }
                    r0.onSetRating(r8, r1)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x0045:
                    int r8 = r8.arg1     // Catch:{ all -> 0x0165 }
                    r0.onSetShuffleMode(r8)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x004c:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0165 }
                    boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0165 }
                    r0.onSetCaptioningEnabled(r8)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x0059:
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase r1 = android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.this     // Catch:{ all -> 0x0165 }
                    java.util.List<android.support.v4.media.session.MediaSessionCompat$QueueItem> r1 = r1.n     // Catch:{ all -> 0x0165 }
                    if (r1 == 0) goto L_0x015f
                    int r1 = r8.arg1     // Catch:{ all -> 0x0165 }
                    if (r1 < 0) goto L_0x007c
                    int r1 = r8.arg1     // Catch:{ all -> 0x0165 }
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase r3 = android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.this     // Catch:{ all -> 0x0165 }
                    java.util.List<android.support.v4.media.session.MediaSessionCompat$QueueItem> r3 = r3.n     // Catch:{ all -> 0x0165 }
                    int r3 = r3.size()     // Catch:{ all -> 0x0165 }
                    if (r1 >= r3) goto L_0x007c
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase r1 = android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.this     // Catch:{ all -> 0x0165 }
                    java.util.List<android.support.v4.media.session.MediaSessionCompat$QueueItem> r1 = r1.n     // Catch:{ all -> 0x0165 }
                    int r8 = r8.arg1     // Catch:{ all -> 0x0165 }
                    java.lang.Object r8 = r1.get(r8)     // Catch:{ all -> 0x0165 }
                    android.support.v4.media.session.MediaSessionCompat$QueueItem r8 = (android.support.v4.media.session.MediaSessionCompat.QueueItem) r8     // Catch:{ all -> 0x0165 }
                    goto L_0x007d
                L_0x007c:
                    r8 = r2
                L_0x007d:
                    if (r8 == 0) goto L_0x015f
                    android.support.v4.media.MediaDescriptionCompat r8 = r8.getDescription()     // Catch:{ all -> 0x0165 }
                L_0x0083:
                    r0.onRemoveQueueItem(r8)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x0088:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    android.support.v4.media.MediaDescriptionCompat r8 = (android.support.v4.media.MediaDescriptionCompat) r8     // Catch:{ all -> 0x0165 }
                    goto L_0x0083
                L_0x008d:
                    java.lang.Object r1 = r8.obj     // Catch:{ all -> 0x0165 }
                    android.support.v4.media.MediaDescriptionCompat r1 = (android.support.v4.media.MediaDescriptionCompat) r1     // Catch:{ all -> 0x0165 }
                    int r8 = r8.arg1     // Catch:{ all -> 0x0165 }
                    r0.onAddQueueItem(r1, r8)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x0098:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    android.support.v4.media.MediaDescriptionCompat r8 = (android.support.v4.media.MediaDescriptionCompat) r8     // Catch:{ all -> 0x0165 }
                    r0.onAddQueueItem(r8)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x00a1:
                    int r8 = r8.arg1     // Catch:{ all -> 0x0165 }
                    r0.onSetRepeatMode(r8)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x00a8:
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase r0 = android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.this     // Catch:{ all -> 0x0165 }
                    int r8 = r8.arg1     // Catch:{ all -> 0x0165 }
                    r0.b((int) r8, (int) r4)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x00b1:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    android.view.KeyEvent r8 = (android.view.KeyEvent) r8     // Catch:{ all -> 0x0165 }
                    android.content.Intent r1 = new android.content.Intent     // Catch:{ all -> 0x0165 }
                    java.lang.String r3 = "android.intent.action.MEDIA_BUTTON"
                    r1.<init>(r3)     // Catch:{ all -> 0x0165 }
                    java.lang.String r3 = "android.intent.extra.KEY_EVENT"
                    r1.putExtra(r3, r8)     // Catch:{ all -> 0x0165 }
                    boolean r1 = r0.onMediaButtonEvent(r1)     // Catch:{ all -> 0x0165 }
                    if (r1 != 0) goto L_0x015f
                    r7.onMediaButtonEvent(r8, r0)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x00cc:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x0165 }
                    r0.onCustomAction(r8, r1)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x00d5:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    android.support.v4.media.RatingCompat r8 = (android.support.v4.media.RatingCompat) r8     // Catch:{ all -> 0x0165 }
                    r0.onSetRating(r8)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x00de:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    java.lang.Long r8 = (java.lang.Long) r8     // Catch:{ all -> 0x0165 }
                    long r3 = r8.longValue()     // Catch:{ all -> 0x0165 }
                    r0.onSeekTo(r3)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x00eb:
                    r0.onRewind()     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x00f0:
                    r0.onFastForward()     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x00f5:
                    r0.onSkipToPrevious()     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x00fa:
                    r0.onSkipToNext()     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x00fe:
                    r0.onStop()     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x0102:
                    r0.onPause()     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x0106:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    java.lang.Long r8 = (java.lang.Long) r8     // Catch:{ all -> 0x0165 }
                    long r3 = r8.longValue()     // Catch:{ all -> 0x0165 }
                    r0.onSkipToQueueItem(r3)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x0112:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    android.net.Uri r8 = (android.net.Uri) r8     // Catch:{ all -> 0x0165 }
                    r0.onPlayFromUri(r8, r1)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x011a:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x0165 }
                    r0.onPlayFromSearch(r8, r1)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x0122:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x0165 }
                    r0.onPlayFromMediaId(r8, r1)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x012a:
                    r0.onPlay()     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x012e:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    android.net.Uri r8 = (android.net.Uri) r8     // Catch:{ all -> 0x0165 }
                    r0.onPrepareFromUri(r8, r1)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x0136:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x0165 }
                    r0.onPrepareFromSearch(r8, r1)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x013e:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x0165 }
                    r0.onPrepareFromMediaId(r8, r1)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x0146:
                    r0.onPrepare()     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x014a:
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase r0 = android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.this     // Catch:{ all -> 0x0165 }
                    int r8 = r8.arg1     // Catch:{ all -> 0x0165 }
                    r0.a((int) r8, (int) r4)     // Catch:{ all -> 0x0165 }
                    goto L_0x015f
                L_0x0152:
                    java.lang.Object r8 = r8.obj     // Catch:{ all -> 0x0165 }
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase$Command r8 = (android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.Command) r8     // Catch:{ all -> 0x0165 }
                    java.lang.String r1 = r8.command     // Catch:{ all -> 0x0165 }
                    android.os.Bundle r3 = r8.extras     // Catch:{ all -> 0x0165 }
                    android.os.ResultReceiver r8 = r8.stub     // Catch:{ all -> 0x0165 }
                    r0.onCommand(r1, r3, r8)     // Catch:{ all -> 0x0165 }
                L_0x015f:
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase r8 = android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.this
                    r8.setCurrentControllerInfo(r2)
                    return
                L_0x0165:
                    r8 = move-exception
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase r0 = android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.this
                    r0.setCurrentControllerInfo(r2)
                    throw r8
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.MessageHandler.handleMessage(android.os.Message):void");
            }
        }

        public MediaSessionImplBase(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            if (componentName != null) {
                this.mContext = context;
                this.a = context.getPackageName();
                this.c = (AudioManager) context.getSystemService("audio");
                this.b = str;
                this.mMediaButtonReceiverComponentName = componentName;
                this.mMediaButtonReceiverIntent = pendingIntent;
                this.mStub = new MediaSessionStub();
                this.mToken = new Token(this.mStub);
                this.p = 0;
                this.u = 1;
                this.v = 3;
                this.d = new RemoteControlClient(pendingIntent);
                return;
            }
            throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
        }

        private void sendCaptioningEnabled(boolean z) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.f.getBroadcastItem(beginBroadcast).onCaptioningEnabledChanged(z);
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
        }

        private void sendEvent(String str, Bundle bundle) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.f.getBroadcastItem(beginBroadcast).onEvent(str, bundle);
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
        }

        private void sendExtras(Bundle bundle) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.f.getBroadcastItem(beginBroadcast).onExtrasChanged(bundle);
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
        }

        private void sendMetadata(MediaMetadataCompat mediaMetadataCompat) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.f.getBroadcastItem(beginBroadcast).onMetadataChanged(mediaMetadataCompat);
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
        }

        private void sendQueue(List<QueueItem> list) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.f.getBroadcastItem(beginBroadcast).onQueueChanged(list);
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
        }

        private void sendQueueTitle(CharSequence charSequence) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.f.getBroadcastItem(beginBroadcast).onQueueTitleChanged(charSequence);
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
        }

        private void sendRepeatMode(int i2) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.f.getBroadcastItem(beginBroadcast).onRepeatModeChanged(i2);
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
        }

        private void sendSessionDestroyed() {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.f.getBroadcastItem(beginBroadcast).onSessionDestroyed();
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
            this.f.kill();
        }

        private void sendShuffleMode(int i2) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.f.getBroadcastItem(beginBroadcast).onShuffleModeChanged(i2);
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
        }

        private void sendState(PlaybackStateCompat playbackStateCompat) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.f.getBroadcastItem(beginBroadcast).onPlaybackStateChanged(playbackStateCompat);
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
        }

        /* access modifiers changed from: package-private */
        public int a(int i2) {
            switch (i2) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
                case 6:
                case 8:
                    return 8;
                case 7:
                    return 9;
                case 9:
                    return 7;
                case 10:
                case 11:
                    return 6;
                default:
                    return -1;
            }
        }

        /* access modifiers changed from: package-private */
        public int a(long j2) {
            int i2 = (1 & j2) != 0 ? 32 : 0;
            if ((2 & j2) != 0) {
                i2 |= 16;
            }
            if ((4 & j2) != 0) {
                i2 |= 4;
            }
            if ((8 & j2) != 0) {
                i2 |= 2;
            }
            if ((16 & j2) != 0) {
                i2 |= 1;
            }
            if ((32 & j2) != 0) {
                i2 |= 128;
            }
            if ((64 & j2) != 0) {
                i2 |= 64;
            }
            return (j2 & 512) != 0 ? i2 | 8 : i2;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
            if (r2 != null) goto L_0x001f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001d, code lost:
            if (r2 != null) goto L_0x001f;
         */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0046  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0057  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x006a  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x007c  */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x008e  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x00a1  */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x00b3  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x00c5  */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x00d8  */
        /* JADX WARNING: Removed duplicated region for block: B:42:0x00eb  */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x00fd  */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x010f  */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x0120  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.media.RemoteControlClient.MetadataEditor a(android.os.Bundle r7) {
            /*
                r6 = this;
                android.media.RemoteControlClient r0 = r6.d
                r1 = 1
                android.media.RemoteControlClient$MetadataEditor r0 = r0.editMetadata(r1)
                if (r7 != 0) goto L_0x000a
                return r0
            L_0x000a:
                java.lang.String r2 = "android.media.metadata.ART"
                boolean r2 = r7.containsKey(r2)
                r3 = 100
                r4 = 0
                if (r2 == 0) goto L_0x002b
                java.lang.String r2 = "android.media.metadata.ART"
                android.os.Parcelable r2 = r7.getParcelable(r2)
                android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
                if (r2 == 0) goto L_0x0027
            L_0x001f:
                android.graphics.Bitmap$Config r5 = r2.getConfig()
                android.graphics.Bitmap r2 = r2.copy(r5, r4)
            L_0x0027:
                r0.putBitmap(r3, r2)
                goto L_0x003e
            L_0x002b:
                java.lang.String r2 = "android.media.metadata.ALBUM_ART"
                boolean r2 = r7.containsKey(r2)
                if (r2 == 0) goto L_0x003e
                java.lang.String r2 = "android.media.metadata.ALBUM_ART"
                android.os.Parcelable r2 = r7.getParcelable(r2)
                android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
                if (r2 == 0) goto L_0x0027
                goto L_0x001f
            L_0x003e:
                java.lang.String r2 = "android.media.metadata.ALBUM"
                boolean r2 = r7.containsKey(r2)
                if (r2 == 0) goto L_0x004f
                java.lang.String r2 = "android.media.metadata.ALBUM"
                java.lang.String r2 = r7.getString(r2)
                r0.putString(r1, r2)
            L_0x004f:
                java.lang.String r1 = "android.media.metadata.ALBUM_ARTIST"
                boolean r1 = r7.containsKey(r1)
                if (r1 == 0) goto L_0x0062
                r1 = 13
                java.lang.String r2 = "android.media.metadata.ALBUM_ARTIST"
                java.lang.String r2 = r7.getString(r2)
                r0.putString(r1, r2)
            L_0x0062:
                java.lang.String r1 = "android.media.metadata.ARTIST"
                boolean r1 = r7.containsKey(r1)
                if (r1 == 0) goto L_0x0074
                r1 = 2
                java.lang.String r2 = "android.media.metadata.ARTIST"
                java.lang.String r2 = r7.getString(r2)
                r0.putString(r1, r2)
            L_0x0074:
                java.lang.String r1 = "android.media.metadata.AUTHOR"
                boolean r1 = r7.containsKey(r1)
                if (r1 == 0) goto L_0x0086
                r1 = 3
                java.lang.String r2 = "android.media.metadata.AUTHOR"
                java.lang.String r2 = r7.getString(r2)
                r0.putString(r1, r2)
            L_0x0086:
                java.lang.String r1 = "android.media.metadata.COMPILATION"
                boolean r1 = r7.containsKey(r1)
                if (r1 == 0) goto L_0x0099
                r1 = 15
                java.lang.String r2 = "android.media.metadata.COMPILATION"
                java.lang.String r2 = r7.getString(r2)
                r0.putString(r1, r2)
            L_0x0099:
                java.lang.String r1 = "android.media.metadata.COMPOSER"
                boolean r1 = r7.containsKey(r1)
                if (r1 == 0) goto L_0x00ab
                r1 = 4
                java.lang.String r2 = "android.media.metadata.COMPOSER"
                java.lang.String r2 = r7.getString(r2)
                r0.putString(r1, r2)
            L_0x00ab:
                java.lang.String r1 = "android.media.metadata.DATE"
                boolean r1 = r7.containsKey(r1)
                if (r1 == 0) goto L_0x00bd
                r1 = 5
                java.lang.String r2 = "android.media.metadata.DATE"
                java.lang.String r2 = r7.getString(r2)
                r0.putString(r1, r2)
            L_0x00bd:
                java.lang.String r1 = "android.media.metadata.DISC_NUMBER"
                boolean r1 = r7.containsKey(r1)
                if (r1 == 0) goto L_0x00d0
                r1 = 14
                java.lang.String r2 = "android.media.metadata.DISC_NUMBER"
                long r2 = r7.getLong(r2)
                r0.putLong(r1, r2)
            L_0x00d0:
                java.lang.String r1 = "android.media.metadata.DURATION"
                boolean r1 = r7.containsKey(r1)
                if (r1 == 0) goto L_0x00e3
                r1 = 9
                java.lang.String r2 = "android.media.metadata.DURATION"
                long r2 = r7.getLong(r2)
                r0.putLong(r1, r2)
            L_0x00e3:
                java.lang.String r1 = "android.media.metadata.GENRE"
                boolean r1 = r7.containsKey(r1)
                if (r1 == 0) goto L_0x00f5
                r1 = 6
                java.lang.String r2 = "android.media.metadata.GENRE"
                java.lang.String r2 = r7.getString(r2)
                r0.putString(r1, r2)
            L_0x00f5:
                java.lang.String r1 = "android.media.metadata.TITLE"
                boolean r1 = r7.containsKey(r1)
                if (r1 == 0) goto L_0x0107
                r1 = 7
                java.lang.String r2 = "android.media.metadata.TITLE"
                java.lang.String r2 = r7.getString(r2)
                r0.putString(r1, r2)
            L_0x0107:
                java.lang.String r1 = "android.media.metadata.TRACK_NUMBER"
                boolean r1 = r7.containsKey(r1)
                if (r1 == 0) goto L_0x0118
                java.lang.String r1 = "android.media.metadata.TRACK_NUMBER"
                long r1 = r7.getLong(r1)
                r0.putLong(r4, r1)
            L_0x0118:
                java.lang.String r1 = "android.media.metadata.WRITER"
                boolean r1 = r7.containsKey(r1)
                if (r1 == 0) goto L_0x012b
                r1 = 11
                java.lang.String r2 = "android.media.metadata.WRITER"
                java.lang.String r7 = r7.getString(r2)
                r0.putString(r1, r7)
            L_0x012b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.a(android.os.Bundle):android.media.RemoteControlClient$MetadataEditor");
        }

        /* access modifiers changed from: package-private */
        public void a(int i2, int i3) {
            if (this.u == 2) {
                VolumeProviderCompat volumeProviderCompat = this.w;
                if (volumeProviderCompat != null) {
                    volumeProviderCompat.onAdjustVolume(i2);
                    return;
                }
                return;
            }
            this.c.adjustStreamVolume(this.v, i2, i3);
        }

        /* access modifiers changed from: package-private */
        public void a(int i2, int i3, int i4, Object obj, Bundle bundle) {
            synchronized (this.e) {
                if (this.mHandler != null) {
                    Message obtainMessage = this.mHandler.obtainMessage(i2, i3, i4, obj);
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(MediaSessionCompat.DATA_CALLING_PACKAGE, MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER);
                    bundle2.putInt("data_calling_pid", Binder.getCallingPid());
                    bundle2.putInt("data_calling_uid", Binder.getCallingUid());
                    if (bundle != null) {
                        bundle2.putBundle(MediaSessionCompat.DATA_EXTRAS, bundle);
                    }
                    obtainMessage.setData(bundle2);
                    obtainMessage.sendToTarget();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a(PendingIntent pendingIntent, ComponentName componentName) {
            this.c.registerMediaButtonEventReceiver(componentName);
        }

        /* access modifiers changed from: package-private */
        public void a(ParcelableVolumeInfo parcelableVolumeInfo) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.f.getBroadcastItem(beginBroadcast).onVolumeInfoChanged(parcelableVolumeInfo);
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
        }

        /* access modifiers changed from: package-private */
        public void a(PlaybackStateCompat playbackStateCompat) {
            this.d.setPlaybackState(a(playbackStateCompat.getState()));
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
            if ((r4.j & 2) == 0) goto L_0x005b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0059, code lost:
            if (r4.mIsRccRegistered != false) goto L_0x005b;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a() {
            /*
                r4 = this;
                boolean r0 = r4.h
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L_0x004a
                boolean r0 = r4.mIsMbrRegistered
                if (r0 != 0) goto L_0x0019
                int r0 = r4.j
                r0 = r0 & r1
                if (r0 == 0) goto L_0x0019
                android.app.PendingIntent r0 = r4.mMediaButtonReceiverIntent
                android.content.ComponentName r3 = r4.mMediaButtonReceiverComponentName
                r4.a((android.app.PendingIntent) r0, (android.content.ComponentName) r3)
                r4.mIsMbrRegistered = r1
                goto L_0x002b
            L_0x0019:
                boolean r0 = r4.mIsMbrRegistered
                if (r0 == 0) goto L_0x002b
                int r0 = r4.j
                r0 = r0 & r1
                if (r0 != 0) goto L_0x002b
                android.app.PendingIntent r0 = r4.mMediaButtonReceiverIntent
                android.content.ComponentName r3 = r4.mMediaButtonReceiverComponentName
                r4.b((android.app.PendingIntent) r0, (android.content.ComponentName) r3)
                r4.mIsMbrRegistered = r2
            L_0x002b:
                boolean r0 = r4.mIsRccRegistered
                if (r0 != 0) goto L_0x003f
                int r0 = r4.j
                r0 = r0 & 2
                if (r0 == 0) goto L_0x003f
                android.media.AudioManager r0 = r4.c
                android.media.RemoteControlClient r2 = r4.d
                r0.registerRemoteControlClient(r2)
                r4.mIsRccRegistered = r1
                goto L_0x006a
            L_0x003f:
                boolean r0 = r4.mIsRccRegistered
                if (r0 == 0) goto L_0x0069
                int r0 = r4.j
                r0 = r0 & 2
                if (r0 != 0) goto L_0x0069
                goto L_0x005b
            L_0x004a:
                boolean r0 = r4.mIsMbrRegistered
                if (r0 == 0) goto L_0x0057
                android.app.PendingIntent r0 = r4.mMediaButtonReceiverIntent
                android.content.ComponentName r1 = r4.mMediaButtonReceiverComponentName
                r4.b((android.app.PendingIntent) r0, (android.content.ComponentName) r1)
                r4.mIsMbrRegistered = r2
            L_0x0057:
                boolean r0 = r4.mIsRccRegistered
                if (r0 == 0) goto L_0x0069
            L_0x005b:
                android.media.RemoteControlClient r0 = r4.d
                r0.setPlaybackState(r2)
                android.media.AudioManager r0 = r4.c
                android.media.RemoteControlClient r1 = r4.d
                r0.unregisterRemoteControlClient(r1)
                r4.mIsRccRegistered = r2
            L_0x0069:
                r1 = 0
            L_0x006a:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.a():boolean");
        }

        /* access modifiers changed from: package-private */
        public void b(int i2, int i3) {
            if (this.u == 2) {
                VolumeProviderCompat volumeProviderCompat = this.w;
                if (volumeProviderCompat != null) {
                    volumeProviderCompat.onSetVolumeTo(i2);
                    return;
                }
                return;
            }
            this.c.setStreamVolume(this.v, i2, i3);
        }

        /* access modifiers changed from: package-private */
        public void b(PendingIntent pendingIntent, ComponentName componentName) {
            this.c.unregisterMediaButtonEventReceiver(componentName);
        }

        public String getCallingPackage() {
            return null;
        }

        public MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
            MediaSessionManager.RemoteUserInfo remoteUserInfo;
            synchronized (this.e) {
                remoteUserInfo = this.mRemoteUserInfo;
            }
            return remoteUserInfo;
        }

        public Object getMediaSession() {
            return null;
        }

        public PlaybackStateCompat getPlaybackState() {
            PlaybackStateCompat playbackStateCompat;
            synchronized (this.e) {
                playbackStateCompat = this.l;
            }
            return playbackStateCompat;
        }

        public Object getRemoteControlClient() {
            return null;
        }

        public Token getSessionToken() {
            return this.mToken;
        }

        public boolean isActive() {
            return this.h;
        }

        public void release() {
            this.h = false;
            this.g = true;
            a();
            sendSessionDestroyed();
        }

        public void sendSessionEvent(String str, Bundle bundle) {
            sendEvent(str, bundle);
        }

        public void setActive(boolean z) {
            if (z != this.h) {
                this.h = z;
                if (a()) {
                    setMetadata(this.k);
                    setPlaybackState(this.l);
                }
            }
        }

        public void setCallback(Callback callback, Handler handler) {
            this.i = callback;
            if (callback != null) {
                if (handler == null) {
                    handler = new Handler();
                }
                synchronized (this.e) {
                    if (this.mHandler != null) {
                        this.mHandler.removeCallbacksAndMessages((Object) null);
                    }
                    this.mHandler = new MessageHandler(handler.getLooper());
                    this.i.a(this, handler);
                }
            }
        }

        public void setCaptioningEnabled(boolean z) {
            if (this.q != z) {
                this.q = z;
                sendCaptioningEnabled(z);
            }
        }

        public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            synchronized (this.e) {
                this.mRemoteUserInfo = remoteUserInfo;
            }
        }

        public void setExtras(Bundle bundle) {
            this.t = bundle;
            sendExtras(bundle);
        }

        public void setFlags(int i2) {
            synchronized (this.e) {
                this.j = i2;
            }
            a();
        }

        public void setMediaButtonReceiver(PendingIntent pendingIntent) {
        }

        public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat != null) {
                mediaMetadataCompat = new MediaMetadataCompat.Builder(mediaMetadataCompat, MediaSessionCompat.a).build();
            }
            synchronized (this.e) {
                this.k = mediaMetadataCompat;
            }
            sendMetadata(mediaMetadataCompat);
            if (this.h) {
                a(mediaMetadataCompat == null ? null : mediaMetadataCompat.getBundle()).apply();
            }
        }

        public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
            synchronized (this.e) {
                this.l = playbackStateCompat;
            }
            sendState(playbackStateCompat);
            if (this.h) {
                if (playbackStateCompat == null) {
                    this.d.setPlaybackState(0);
                    this.d.setTransportControlFlags(0);
                    return;
                }
                a(playbackStateCompat);
                this.d.setTransportControlFlags(a(playbackStateCompat.getActions()));
            }
        }

        public void setPlaybackToLocal(int i2) {
            VolumeProviderCompat volumeProviderCompat = this.w;
            if (volumeProviderCompat != null) {
                volumeProviderCompat.setCallback((VolumeProviderCompat.Callback) null);
            }
            this.v = i2;
            this.u = 1;
            int i3 = this.u;
            int i4 = this.v;
            a(new ParcelableVolumeInfo(i3, i4, 2, this.c.getStreamMaxVolume(i4), this.c.getStreamVolume(this.v)));
        }

        public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
            if (volumeProviderCompat != null) {
                VolumeProviderCompat volumeProviderCompat2 = this.w;
                if (volumeProviderCompat2 != null) {
                    volumeProviderCompat2.setCallback((VolumeProviderCompat.Callback) null);
                }
                this.u = 2;
                this.w = volumeProviderCompat;
                a(new ParcelableVolumeInfo(this.u, this.v, this.w.getVolumeControl(), this.w.getMaxVolume(), this.w.getCurrentVolume()));
                volumeProviderCompat.setCallback(this.mVolumeCallback);
                return;
            }
            throw new IllegalArgumentException("volumeProvider may not be null");
        }

        public void setQueue(List<QueueItem> list) {
            this.n = list;
            sendQueue(list);
        }

        public void setQueueTitle(CharSequence charSequence) {
            this.o = charSequence;
            sendQueueTitle(charSequence);
        }

        public void setRatingType(int i2) {
            this.p = i2;
        }

        public void setRepeatMode(int i2) {
            if (this.r != i2) {
                this.r = i2;
                sendRepeatMode(i2);
            }
        }

        public void setSessionActivity(PendingIntent pendingIntent) {
            synchronized (this.e) {
                this.m = pendingIntent;
            }
        }

        public void setShuffleMode(int i2) {
            if (this.s != i2) {
                this.s = i2;
                sendShuffleMode(i2);
            }
        }
    }

    public interface OnActiveChangeListener {
        void onActiveChanged();
    }

    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = new Parcelable.Creator<QueueItem>() {
            public QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            public QueueItem[] newArray(int i) {
                return new QueueItem[i];
            }
        };
        public static final int UNKNOWN_ID = -1;
        private final MediaDescriptionCompat mDescription;
        private final long mId;
        private Object mItem;

        QueueItem(Parcel parcel) {
            this.mDescription = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.mId = parcel.readLong();
        }

        public QueueItem(MediaDescriptionCompat mediaDescriptionCompat, long j) {
            this((Object) null, mediaDescriptionCompat, j);
        }

        private QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            } else if (j != -1) {
                this.mDescription = mediaDescriptionCompat;
                this.mId = j;
                this.mItem = obj;
            } else {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
        }

        public static QueueItem fromQueueItem(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new QueueItem(obj, MediaDescriptionCompat.fromMediaDescription(MediaSessionCompatApi21.QueueItem.getDescription(obj)), MediaSessionCompatApi21.QueueItem.getQueueId(obj));
        }

        public static List<QueueItem> fromQueueItemList(List<?> list) {
            if (list == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Object fromQueueItem : list) {
                arrayList.add(fromQueueItem(fromQueueItem));
            }
            return arrayList;
        }

        public int describeContents() {
            return 0;
        }

        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }

        public long getQueueId() {
            return this.mId;
        }

        public Object getQueueItem() {
            if (this.mItem != null || Build.VERSION.SDK_INT < 21) {
                return this.mItem;
            }
            this.mItem = MediaSessionCompatApi21.QueueItem.createItem(this.mDescription.getMediaDescription(), this.mId);
            return this.mItem;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.mDescription + ", Id=" + this.mId + " }";
        }

        public void writeToParcel(Parcel parcel, int i) {
            this.mDescription.writeToParcel(parcel, i);
            parcel.writeLong(this.mId);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new Parcelable.Creator<ResultReceiverWrapper>() {
            public ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            public ResultReceiverWrapper[] newArray(int i) {
                return new ResultReceiverWrapper[i];
            }
        };
        ResultReceiver a;

        ResultReceiverWrapper(Parcel parcel) {
            this.a = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        public ResultReceiverWrapper(ResultReceiver resultReceiver) {
            this.a = resultReceiver;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            this.a.writeToParcel(parcel, i);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SessionFlags {
    }

    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = new Parcelable.Creator<Token>() {
            public Token createFromParcel(Parcel parcel) {
                return new Token(Build.VERSION.SDK_INT >= 21 ? parcel.readParcelable((ClassLoader) null) : parcel.readStrongBinder());
            }

            public Token[] newArray(int i) {
                return new Token[i];
            }
        };
        private IMediaSession mExtraBinder;
        private final Object mInner;
        private Bundle mSessionToken2Bundle;

        Token(Object obj) {
            this(obj, (IMediaSession) null, (Bundle) null);
        }

        Token(Object obj, IMediaSession iMediaSession) {
            this(obj, iMediaSession, (Bundle) null);
        }

        Token(Object obj, IMediaSession iMediaSession, Bundle bundle) {
            this.mInner = obj;
            this.mExtraBinder = iMediaSession;
            this.mSessionToken2Bundle = bundle;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static Token fromBundle(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            IMediaSession asInterface = IMediaSession.Stub.asInterface(BundleCompat.getBinder(bundle, MediaSessionCompat.KEY_EXTRA_BINDER));
            Bundle bundle2 = bundle.getBundle(MediaSessionCompat.KEY_SESSION_TOKEN2_BUNDLE);
            Token token = (Token) bundle.getParcelable(MediaSessionCompat.KEY_TOKEN);
            if (token == null) {
                return null;
            }
            return new Token(token.mInner, asInterface, bundle2);
        }

        public static Token fromToken(Object obj) {
            return fromToken(obj, (IMediaSession) null);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static Token fromToken(Object obj, IMediaSession iMediaSession) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new Token(MediaSessionCompatApi21.verifyToken(obj), iMediaSession);
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            Object obj2 = this.mInner;
            if (obj2 == null) {
                return token.mInner == null;
            }
            Object obj3 = token.mInner;
            if (obj3 == null) {
                return false;
            }
            return obj2.equals(obj3);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public IMediaSession getExtraBinder() {
            return this.mExtraBinder;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Bundle getSessionToken2Bundle() {
            return this.mSessionToken2Bundle;
        }

        public Object getToken() {
            return this.mInner;
        }

        public int hashCode() {
            Object obj = this.mInner;
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void setExtraBinder(IMediaSession iMediaSession) {
            this.mExtraBinder = iMediaSession;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void setSessionToken2Bundle(Bundle bundle) {
            this.mSessionToken2Bundle = bundle;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(MediaSessionCompat.KEY_TOKEN, this);
            IMediaSession iMediaSession = this.mExtraBinder;
            if (iMediaSession != null) {
                BundleCompat.putBinder(bundle, MediaSessionCompat.KEY_EXTRA_BINDER, iMediaSession.asBinder());
            }
            Bundle bundle2 = this.mSessionToken2Bundle;
            if (bundle2 != null) {
                bundle.putBundle(MediaSessionCompat.KEY_SESSION_TOKEN2_BUNDLE, bundle2);
            }
            return bundle;
        }

        public void writeToParcel(Parcel parcel, int i) {
            if (Build.VERSION.SDK_INT >= 21) {
                parcel.writeParcelable((Parcelable) this.mInner, i);
            } else {
                parcel.writeStrongBinder((IBinder) this.mInner);
            }
        }
    }

    private MediaSessionCompat(Context context, MediaSessionImpl mediaSessionImpl) {
        this.mActiveListeners = new ArrayList<>();
        this.mImpl = mediaSessionImpl;
        if (Build.VERSION.SDK_INT >= 21 && !MediaSessionCompatApi21.hasCallback(mediaSessionImpl.getMediaSession())) {
            setCallback(new Callback() {
            });
        }
        this.mController = new MediaControllerCompat(context, this);
    }

    public MediaSessionCompat(Context context, String str) {
        this(context, str, (ComponentName) null, (PendingIntent) null);
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
        this(context, str, componentName, pendingIntent, (Bundle) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private MediaSessionCompat(android.content.Context r3, java.lang.String r4, android.content.ComponentName r5, android.app.PendingIntent r6, android.os.Bundle r7) {
        /*
            r2 = this;
            r2.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r2.mActiveListeners = r0
            if (r3 == 0) goto L_0x00ab
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto L_0x00a3
            if (r5 != 0) goto L_0x0021
            android.content.ComponentName r5 = androidx.media.session.MediaButtonReceiver.getMediaButtonReceiverComponent(r3)
            if (r5 != 0) goto L_0x0021
            java.lang.String r0 = "MediaSessionCompat"
            java.lang.String r1 = "Couldn't find a unique registered media button receiver in the given context."
            android.util.Log.w(r0, r1)
        L_0x0021:
            if (r5 == 0) goto L_0x0034
            if (r6 != 0) goto L_0x0034
            android.content.Intent r6 = new android.content.Intent
            java.lang.String r0 = "android.intent.action.MEDIA_BUTTON"
            r6.<init>(r0)
            r6.setComponent(r5)
            r0 = 0
            android.app.PendingIntent r6 = android.app.PendingIntent.getBroadcast(r3, r0, r6, r0)
        L_0x0034:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L_0x004f
            android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi28 r5 = new android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi28
            r5.<init>(r3, r4, r7)
            r2.mImpl = r5
            android.support.v4.media.session.MediaSessionCompat$1 r4 = new android.support.v4.media.session.MediaSessionCompat$1
            r4.<init>()
        L_0x0046:
            r2.setCallback(r4)
            android.support.v4.media.session.MediaSessionCompat$MediaSessionImpl r4 = r2.mImpl
            r4.setMediaButtonReceiver(r6)
            goto L_0x0082
        L_0x004f:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r0 < r1) goto L_0x0062
            android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi21 r5 = new android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi21
            r5.<init>(r3, r4, r7)
            r2.mImpl = r5
            android.support.v4.media.session.MediaSessionCompat$2 r4 = new android.support.v4.media.session.MediaSessionCompat$2
            r4.<init>()
            goto L_0x0046
        L_0x0062:
            int r7 = android.os.Build.VERSION.SDK_INT
            r0 = 19
            if (r7 < r0) goto L_0x0070
            android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi19 r7 = new android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi19
            r7.<init>(r3, r4, r5, r6)
        L_0x006d:
            r2.mImpl = r7
            goto L_0x0082
        L_0x0070:
            int r7 = android.os.Build.VERSION.SDK_INT
            r0 = 18
            if (r7 < r0) goto L_0x007c
            android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi18 r7 = new android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi18
            r7.<init>(r3, r4, r5, r6)
            goto L_0x006d
        L_0x007c:
            android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase r7 = new android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase
            r7.<init>(r3, r4, r5, r6)
            goto L_0x006d
        L_0x0082:
            android.support.v4.media.session.MediaControllerCompat r4 = new android.support.v4.media.session.MediaControllerCompat
            r4.<init>((android.content.Context) r3, (android.support.v4.media.session.MediaSessionCompat) r2)
            r2.mController = r4
            int r4 = a
            if (r4 != 0) goto L_0x00a2
            r4 = 1
            r5 = 1134559232(0x43a00000, float:320.0)
            android.content.res.Resources r3 = r3.getResources()
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()
            float r3 = android.util.TypedValue.applyDimension(r4, r5, r3)
            r4 = 1056964608(0x3f000000, float:0.5)
            float r3 = r3 + r4
            int r3 = (int) r3
            a = r3
        L_0x00a2:
            return
        L_0x00a3:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "tag must not be null or empty"
            r3.<init>(r4)
            throw r3
        L_0x00ab:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "context must not be null"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.MediaSessionCompat.<init>(android.content.Context, java.lang.String, android.content.ComponentName, android.app.PendingIntent, android.os.Bundle):void");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MediaSessionCompat(Context context, String str, Bundle bundle) {
        this(context, str, (ComponentName) null, (PendingIntent) null, bundle);
    }

    static PlaybackStateCompat a(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat) {
        if (playbackStateCompat == null) {
            return playbackStateCompat;
        }
        long j = -1;
        if (playbackStateCompat.getPosition() == -1) {
            return playbackStateCompat;
        }
        if (playbackStateCompat.getState() != 3 && playbackStateCompat.getState() != 4 && playbackStateCompat.getState() != 5) {
            return playbackStateCompat;
        }
        long lastPositionUpdateTime = playbackStateCompat.getLastPositionUpdateTime();
        if (lastPositionUpdateTime <= 0) {
            return playbackStateCompat;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long playbackSpeed = ((long) (playbackStateCompat.getPlaybackSpeed() * ((float) (elapsedRealtime - lastPositionUpdateTime)))) + playbackStateCompat.getPosition();
        if (mediaMetadataCompat != null && mediaMetadataCompat.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
            j = mediaMetadataCompat.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
        }
        return new PlaybackStateCompat.Builder(playbackStateCompat).setState(playbackStateCompat.getState(), (j < 0 || playbackSpeed <= j) ? playbackSpeed < 0 ? 0 : playbackSpeed : j, playbackStateCompat.getPlaybackSpeed(), elapsedRealtime).build();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void ensureClassLoader(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
        }
    }

    public static MediaSessionCompat fromMediaSession(Context context, Object obj) {
        if (context == null || obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        return new MediaSessionCompat(context, (MediaSessionImpl) new MediaSessionImplApi21(obj));
    }

    public void addOnActiveChangeListener(OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener != null) {
            this.mActiveListeners.add(onActiveChangeListener);
            return;
        }
        throw new IllegalArgumentException("Listener may not be null");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String getCallingPackage() {
        return this.mImpl.getCallingPackage();
    }

    public MediaControllerCompat getController() {
        return this.mController;
    }

    @NonNull
    public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
        return this.mImpl.getCurrentControllerInfo();
    }

    public Object getMediaSession() {
        return this.mImpl.getMediaSession();
    }

    public Object getRemoteControlClient() {
        return this.mImpl.getRemoteControlClient();
    }

    public Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }

    public boolean isActive() {
        return this.mImpl.isActive();
    }

    public void release() {
        this.mImpl.release();
    }

    public void removeOnActiveChangeListener(OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener != null) {
            this.mActiveListeners.remove(onActiveChangeListener);
            return;
        }
        throw new IllegalArgumentException("Listener may not be null");
    }

    public void sendSessionEvent(String str, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.mImpl.sendSessionEvent(str, bundle);
            return;
        }
        throw new IllegalArgumentException("event cannot be null or empty");
    }

    public void setActive(boolean z) {
        this.mImpl.setActive(z);
        Iterator<OnActiveChangeListener> it = this.mActiveListeners.iterator();
        while (it.hasNext()) {
            it.next().onActiveChanged();
        }
    }

    public void setCallback(Callback callback) {
        setCallback(callback, (Handler) null);
    }

    public void setCallback(Callback callback, Handler handler) {
        if (callback == null) {
            this.mImpl.setCallback((Callback) null, (Handler) null);
            return;
        }
        MediaSessionImpl mediaSessionImpl = this.mImpl;
        if (handler == null) {
            handler = new Handler();
        }
        mediaSessionImpl.setCallback(callback, handler);
    }

    public void setCaptioningEnabled(boolean z) {
        this.mImpl.setCaptioningEnabled(z);
    }

    public void setExtras(Bundle bundle) {
        this.mImpl.setExtras(bundle);
    }

    public void setFlags(int i) {
        this.mImpl.setFlags(i);
    }

    public void setMediaButtonReceiver(PendingIntent pendingIntent) {
        this.mImpl.setMediaButtonReceiver(pendingIntent);
    }

    public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
        this.mImpl.setMetadata(mediaMetadataCompat);
    }

    public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
        this.mImpl.setPlaybackState(playbackStateCompat);
    }

    public void setPlaybackToLocal(int i) {
        this.mImpl.setPlaybackToLocal(i);
    }

    public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
        if (volumeProviderCompat != null) {
            this.mImpl.setPlaybackToRemote(volumeProviderCompat);
            return;
        }
        throw new IllegalArgumentException("volumeProvider may not be null!");
    }

    public void setQueue(List<QueueItem> list) {
        this.mImpl.setQueue(list);
    }

    public void setQueueTitle(CharSequence charSequence) {
        this.mImpl.setQueueTitle(charSequence);
    }

    public void setRatingType(int i) {
        this.mImpl.setRatingType(i);
    }

    public void setRepeatMode(int i) {
        this.mImpl.setRepeatMode(i);
    }

    public void setSessionActivity(PendingIntent pendingIntent) {
        this.mImpl.setSessionActivity(pendingIntent);
    }

    public void setShuffleMode(int i) {
        this.mImpl.setShuffleMode(i);
    }
}
