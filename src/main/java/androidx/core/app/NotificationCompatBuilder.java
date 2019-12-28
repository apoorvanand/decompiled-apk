package androidx.core.app;

import android.app.Notification;
import android.app.RemoteInput;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
    private final List<Bundle> mActionExtrasList = new ArrayList();
    private RemoteViews mBigContentView;
    private final Notification.Builder mBuilder;
    private final NotificationCompat.Builder mBuilderCompat;
    private RemoteViews mContentView;
    private final Bundle mExtras = new Bundle();
    private int mGroupAlertBehavior;
    private RemoteViews mHeadsUpContentView;

    NotificationCompatBuilder(NotificationCompat.Builder builder) {
        String str;
        Bundle bundle;
        this.mBuilderCompat = builder;
        this.mBuilder = Build.VERSION.SDK_INT >= 26 ? new Notification.Builder(builder.mContext, builder.G) : new Notification.Builder(builder.mContext);
        Notification notification = builder.L;
        this.mBuilder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, builder.f).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(builder.b).setContentText(builder.c).setContentInfo(builder.h).setContentIntent(builder.d).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(builder.e, (notification.flags & 128) != 0).setLargeIcon(builder.g).setNumber(builder.i).setProgress(builder.p, builder.q, builder.r);
        if (Build.VERSION.SDK_INT < 21) {
            this.mBuilder.setSound(notification.sound, notification.audioStreamType);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.mBuilder.setSubText(builder.n).setUsesChronometer(builder.l).setPriority(builder.j);
            Iterator<NotificationCompat.Action> it = builder.mActions.iterator();
            while (it.hasNext()) {
                addAction(it.next());
            }
            if (builder.z != null) {
                this.mExtras.putAll(builder.z);
            }
            if (Build.VERSION.SDK_INT < 20) {
                if (builder.v) {
                    this.mExtras.putBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY, true);
                }
                if (builder.s != null) {
                    this.mExtras.putString(NotificationCompatExtras.EXTRA_GROUP_KEY, builder.s);
                    if (builder.t) {
                        bundle = this.mExtras;
                        str = NotificationCompatExtras.EXTRA_GROUP_SUMMARY;
                    } else {
                        bundle = this.mExtras;
                        str = NotificationManagerCompat.EXTRA_USE_SIDE_CHANNEL;
                    }
                    bundle.putBoolean(str, true);
                }
                if (builder.u != null) {
                    this.mExtras.putString(NotificationCompatExtras.EXTRA_SORT_KEY, builder.u);
                }
            }
            this.mContentView = builder.D;
            this.mBigContentView = builder.E;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.mBuilder.setShowWhen(builder.k);
            if (Build.VERSION.SDK_INT < 21 && builder.mPeople != null && !builder.mPeople.isEmpty()) {
                this.mExtras.putStringArray(NotificationCompat.EXTRA_PEOPLE, (String[]) builder.mPeople.toArray(new String[builder.mPeople.size()]));
            }
        }
        if (Build.VERSION.SDK_INT >= 20) {
            this.mBuilder.setLocalOnly(builder.v).setGroup(builder.s).setGroupSummary(builder.t).setSortKey(builder.u);
            this.mGroupAlertBehavior = builder.K;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.mBuilder.setCategory(builder.y).setColor(builder.A).setVisibility(builder.B).setPublicVersion(builder.C).setSound(notification.sound, notification.audioAttributes);
            Iterator<String> it2 = builder.mPeople.iterator();
            while (it2.hasNext()) {
                this.mBuilder.addPerson(it2.next());
            }
            this.mHeadsUpContentView = builder.F;
            if (builder.a.size() > 0) {
                Bundle bundle2 = builder.getExtras().getBundle("android.car.EXTENSIONS");
                bundle2 = bundle2 == null ? new Bundle() : bundle2;
                Bundle bundle3 = new Bundle();
                for (int i = 0; i < builder.a.size(); i++) {
                    bundle3.putBundle(Integer.toString(i), NotificationCompatJellybean.a(builder.a.get(i)));
                }
                bundle2.putBundle("invisible_actions", bundle3);
                builder.getExtras().putBundle("android.car.EXTENSIONS", bundle2);
                this.mExtras.putBundle("android.car.EXTENSIONS", bundle2);
            }
        }
        if (Build.VERSION.SDK_INT >= 24) {
            this.mBuilder.setExtras(builder.z).setRemoteInputHistory(builder.o);
            if (builder.D != null) {
                this.mBuilder.setCustomContentView(builder.D);
            }
            if (builder.E != null) {
                this.mBuilder.setCustomBigContentView(builder.E);
            }
            if (builder.F != null) {
                this.mBuilder.setCustomHeadsUpContentView(builder.F);
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.mBuilder.setBadgeIconType(builder.H).setShortcutId(builder.I).setTimeoutAfter(builder.J).setGroupAlertBehavior(builder.K);
            if (builder.x) {
                this.mBuilder.setColorized(builder.w);
            }
            if (!TextUtils.isEmpty(builder.G)) {
                this.mBuilder.setSound((Uri) null).setDefaults(0).setLights(0, 0, 0).setVibrate((long[]) null);
            }
        }
    }

    private void addAction(NotificationCompat.Action action) {
        if (Build.VERSION.SDK_INT >= 20) {
            Notification.Action.Builder builder = new Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
            if (action.getRemoteInputs() != null) {
                for (RemoteInput addRemoteInput : RemoteInput.a(action.getRemoteInputs())) {
                    builder.addRemoteInput(addRemoteInput);
                }
            }
            Bundle bundle = action.getExtras() != null ? new Bundle(action.getExtras()) : new Bundle();
            bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
            if (Build.VERSION.SDK_INT >= 24) {
                builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
            }
            bundle.putInt("android.support.action.semanticAction", action.getSemanticAction());
            if (Build.VERSION.SDK_INT >= 28) {
                builder.setSemanticAction(action.getSemanticAction());
            }
            bundle.putBoolean("android.support.action.showsUserInterface", action.getShowsUserInterface());
            builder.addExtras(bundle);
            this.mBuilder.addAction(builder.build());
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.mBuilder, action));
        }
    }

    private void removeSoundAndVibration(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        notification.defaults &= -2;
        notification.defaults &= -3;
    }

    /* access modifiers changed from: protected */
    public Notification a() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.mBuilder.build();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            Notification build = this.mBuilder.build();
            if (this.mGroupAlertBehavior != 0) {
                if (!(build.getGroup() == null || (build.flags & 512) == 0 || this.mGroupAlertBehavior != 2)) {
                    removeSoundAndVibration(build);
                }
                if (build.getGroup() != null && (build.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
                    removeSoundAndVibration(build);
                }
            }
            return build;
        } else if (Build.VERSION.SDK_INT >= 21) {
            this.mBuilder.setExtras(this.mExtras);
            Notification build2 = this.mBuilder.build();
            RemoteViews remoteViews = this.mContentView;
            if (remoteViews != null) {
                build2.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.mBigContentView;
            if (remoteViews2 != null) {
                build2.bigContentView = remoteViews2;
            }
            RemoteViews remoteViews3 = this.mHeadsUpContentView;
            if (remoteViews3 != null) {
                build2.headsUpContentView = remoteViews3;
            }
            if (this.mGroupAlertBehavior != 0) {
                if (!(build2.getGroup() == null || (build2.flags & 512) == 0 || this.mGroupAlertBehavior != 2)) {
                    removeSoundAndVibration(build2);
                }
                if (build2.getGroup() != null && (build2.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
                    removeSoundAndVibration(build2);
                }
            }
            return build2;
        } else if (Build.VERSION.SDK_INT >= 20) {
            this.mBuilder.setExtras(this.mExtras);
            Notification build3 = this.mBuilder.build();
            RemoteViews remoteViews4 = this.mContentView;
            if (remoteViews4 != null) {
                build3.contentView = remoteViews4;
            }
            RemoteViews remoteViews5 = this.mBigContentView;
            if (remoteViews5 != null) {
                build3.bigContentView = remoteViews5;
            }
            if (this.mGroupAlertBehavior != 0) {
                if (!(build3.getGroup() == null || (build3.flags & 512) == 0 || this.mGroupAlertBehavior != 2)) {
                    removeSoundAndVibration(build3);
                }
                if (build3.getGroup() != null && (build3.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
                    removeSoundAndVibration(build3);
                }
            }
            return build3;
        } else if (Build.VERSION.SDK_INT >= 19) {
            SparseArray<Bundle> buildActionExtrasMap = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if (buildActionExtrasMap != null) {
                this.mExtras.putSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS, buildActionExtrasMap);
            }
            this.mBuilder.setExtras(this.mExtras);
            Notification build4 = this.mBuilder.build();
            RemoteViews remoteViews6 = this.mContentView;
            if (remoteViews6 != null) {
                build4.contentView = remoteViews6;
            }
            RemoteViews remoteViews7 = this.mBigContentView;
            if (remoteViews7 != null) {
                build4.bigContentView = remoteViews7;
            }
            return build4;
        } else if (Build.VERSION.SDK_INT < 16) {
            return this.mBuilder.getNotification();
        } else {
            Notification build5 = this.mBuilder.build();
            Bundle extras = NotificationCompat.getExtras(build5);
            Bundle bundle = new Bundle(this.mExtras);
            for (String str : this.mExtras.keySet()) {
                if (extras.containsKey(str)) {
                    bundle.remove(str);
                }
            }
            extras.putAll(bundle);
            SparseArray<Bundle> buildActionExtrasMap2 = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if (buildActionExtrasMap2 != null) {
                NotificationCompat.getExtras(build5).putSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS, buildActionExtrasMap2);
            }
            RemoteViews remoteViews8 = this.mContentView;
            if (remoteViews8 != null) {
                build5.contentView = remoteViews8;
            }
            RemoteViews remoteViews9 = this.mBigContentView;
            if (remoteViews9 != null) {
                build5.bigContentView = remoteViews9;
            }
            return build5;
        }
    }

    public Notification build() {
        Bundle extras;
        RemoteViews makeHeadsUpContentView;
        RemoteViews makeBigContentView;
        NotificationCompat.Style style = this.mBuilderCompat.m;
        if (style != null) {
            style.apply(this);
        }
        RemoteViews makeContentView = style != null ? style.makeContentView(this) : null;
        Notification a = a();
        if (makeContentView == null) {
            if (this.mBuilderCompat.D != null) {
                makeContentView = this.mBuilderCompat.D;
            }
            if (!(Build.VERSION.SDK_INT < 16 || style == null || (makeBigContentView = style.makeBigContentView(this)) == null)) {
                a.bigContentView = makeBigContentView;
            }
            if (!(Build.VERSION.SDK_INT < 21 || style == null || (makeHeadsUpContentView = this.mBuilderCompat.m.makeHeadsUpContentView(this)) == null)) {
                a.headsUpContentView = makeHeadsUpContentView;
            }
            if (!(Build.VERSION.SDK_INT < 16 || style == null || (extras = NotificationCompat.getExtras(a)) == null)) {
                style.addCompatExtras(extras);
            }
            return a;
        }
        a.contentView = makeContentView;
        a.bigContentView = makeBigContentView;
        a.headsUpContentView = makeHeadsUpContentView;
        style.addCompatExtras(extras);
        return a;
    }

    public Notification.Builder getBuilder() {
        return this.mBuilder;
    }
}
