package androidx.media.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.widget.RemoteViews;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.app.BundleCompat;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;
import androidx.media.R;

public class NotificationCompat {

    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        private void setBackgroundColor(RemoteViews remoteViews) {
            remoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", this.a.getColor() != 0 ? this.a.getColor() : this.a.mContext.getResources().getColor(R.color.notification_material_background_media_default_color));
        }

        /* access modifiers changed from: package-private */
        public int a() {
            return this.a.getContentView() != null ? R.layout.notification_template_media_custom : super.a();
        }

        /* access modifiers changed from: package-private */
        public int a(int i) {
            return i <= 3 ? R.layout.notification_template_big_media_narrow_custom : R.layout.notification_template_big_media_custom;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(a((Notification.MediaStyle) new Notification.DecoratedMediaCustomViewStyle()));
            } else {
                super.apply(notificationBuilderWithBuilderAccessor);
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews bigContentView = this.a.getBigContentView() != null ? this.a.getBigContentView() : this.a.getContentView();
            if (bigContentView == null) {
                return null;
            }
            RemoteViews c = c();
            buildIntoRemoteViews(c, bigContentView);
            if (Build.VERSION.SDK_INT >= 21) {
                setBackgroundColor(c);
            }
            return c;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            boolean z = true;
            boolean z2 = this.a.getContentView() != null;
            if (Build.VERSION.SDK_INT >= 21) {
                if (!z2 && this.a.getBigContentView() == null) {
                    z = false;
                }
                if (z) {
                    RemoteViews b = b();
                    if (z2) {
                        buildIntoRemoteViews(b, this.a.getContentView());
                    }
                    setBackgroundColor(b);
                    return b;
                }
            } else {
                RemoteViews b2 = b();
                if (z2) {
                    buildIntoRemoteViews(b2, this.a.getContentView());
                    return b2;
                }
            }
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews headsUpContentView = this.a.getHeadsUpContentView() != null ? this.a.getHeadsUpContentView() : this.a.getContentView();
            if (headsUpContentView == null) {
                return null;
            }
            RemoteViews c = c();
            buildIntoRemoteViews(c, headsUpContentView);
            if (Build.VERSION.SDK_INT >= 21) {
                setBackgroundColor(c);
            }
            return c;
        }
    }

    public static class MediaStyle extends NotificationCompat.Style {
        private static final int MAX_MEDIA_BUTTONS = 5;
        private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        int[] e = null;
        MediaSessionCompat.Token f;
        boolean g;
        PendingIntent h;

        public MediaStyle() {
        }

        public MediaStyle(NotificationCompat.Builder builder) {
            setBuilder(builder);
        }

        private RemoteViews generateMediaActionButton(NotificationCompat.Action action) {
            boolean z = action.getActionIntent() == null;
            RemoteViews remoteViews = new RemoteViews(this.a.mContext.getPackageName(), R.layout.notification_media_action);
            remoteViews.setImageViewResource(R.id.action0, action.getIcon());
            if (!z) {
                remoteViews.setOnClickPendingIntent(R.id.action0, action.getActionIntent());
            }
            if (Build.VERSION.SDK_INT >= 15) {
                remoteViews.setContentDescription(R.id.action0, action.getTitle());
            }
            return remoteViews;
        }

        public static MediaSessionCompat.Token getMediaSession(Notification notification) {
            Bundle extras = androidx.core.app.NotificationCompat.getExtras(notification);
            if (extras == null) {
                return null;
            }
            if (Build.VERSION.SDK_INT >= 21) {
                Parcelable parcelable = extras.getParcelable(androidx.core.app.NotificationCompat.EXTRA_MEDIA_SESSION);
                if (parcelable != null) {
                    return MediaSessionCompat.Token.fromToken(parcelable);
                }
                return null;
            }
            IBinder binder = BundleCompat.getBinder(extras, androidx.core.app.NotificationCompat.EXTRA_MEDIA_SESSION);
            if (binder == null) {
                return null;
            }
            Parcel obtain = Parcel.obtain();
            obtain.writeStrongBinder(binder);
            obtain.setDataPosition(0);
            MediaSessionCompat.Token createFromParcel = MediaSessionCompat.Token.CREATOR.createFromParcel(obtain);
            obtain.recycle();
            return createFromParcel;
        }

        /* access modifiers changed from: package-private */
        public int a() {
            return R.layout.notification_template_media;
        }

        /* access modifiers changed from: package-private */
        public int a(int i) {
            return i <= 3 ? R.layout.notification_template_big_media_narrow : R.layout.notification_template_big_media;
        }

        /* access modifiers changed from: package-private */
        @RequiresApi(21)
        public Notification.MediaStyle a(Notification.MediaStyle mediaStyle) {
            int[] iArr = this.e;
            if (iArr != null) {
                mediaStyle.setShowActionsInCompactView(iArr);
            }
            MediaSessionCompat.Token token = this.f;
            if (token != null) {
                mediaStyle.setMediaSession((MediaSession.Token) token.getToken());
            }
            return mediaStyle;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 21) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(a(new Notification.MediaStyle()));
            } else if (this.g) {
                notificationBuilderWithBuilderAccessor.getBuilder().setOngoing(true);
            }
        }

        /* access modifiers changed from: package-private */
        public RemoteViews b() {
            RemoteViews applyStandardTemplate = applyStandardTemplate(false, a(), true);
            int size = this.a.mActions.size();
            int[] iArr = this.e;
            int min = iArr == null ? 0 : Math.min(iArr.length, 3);
            applyStandardTemplate.removeAllViews(R.id.media_actions);
            if (min > 0) {
                int i = 0;
                while (i < min) {
                    if (i < size) {
                        applyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton(this.a.mActions.get(this.e[i])));
                        i++;
                    } else {
                        throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[]{Integer.valueOf(i), Integer.valueOf(size - 1)}));
                    }
                }
            }
            if (this.g) {
                applyStandardTemplate.setViewVisibility(R.id.end_padder, 8);
                applyStandardTemplate.setViewVisibility(R.id.cancel_action, 0);
                applyStandardTemplate.setOnClickPendingIntent(R.id.cancel_action, this.h);
                applyStandardTemplate.setInt(R.id.cancel_action, "setAlpha", this.a.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
            } else {
                applyStandardTemplate.setViewVisibility(R.id.end_padder, 0);
                applyStandardTemplate.setViewVisibility(R.id.cancel_action, 8);
            }
            return applyStandardTemplate;
        }

        /* access modifiers changed from: package-private */
        public RemoteViews c() {
            int min = Math.min(this.a.mActions.size(), 5);
            RemoteViews applyStandardTemplate = applyStandardTemplate(false, a(min), false);
            applyStandardTemplate.removeAllViews(R.id.media_actions);
            if (min > 0) {
                for (int i = 0; i < min; i++) {
                    applyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton(this.a.mActions.get(i)));
                }
            }
            if (this.g) {
                applyStandardTemplate.setViewVisibility(R.id.cancel_action, 0);
                applyStandardTemplate.setInt(R.id.cancel_action, "setAlpha", this.a.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
                applyStandardTemplate.setOnClickPendingIntent(R.id.cancel_action, this.h);
            } else {
                applyStandardTemplate.setViewVisibility(R.id.cancel_action, 8);
            }
            return applyStandardTemplate;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 21) {
                return null;
            }
            return c();
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 21) {
                return null;
            }
            return b();
        }

        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            this.h = pendingIntent;
            return this;
        }

        public MediaStyle setMediaSession(MediaSessionCompat.Token token) {
            this.f = token;
            return this;
        }

        public MediaStyle setShowActionsInCompactView(int... iArr) {
            this.e = iArr;
            return this;
        }

        public MediaStyle setShowCancelButton(boolean z) {
            if (Build.VERSION.SDK_INT < 21) {
                this.g = z;
            }
            return this;
        }
    }

    private NotificationCompat() {
    }
}
