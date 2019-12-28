package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.RemoteViews;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.app.Person;
import androidx.core.text.BidiFormatter;
import androidx.core.view.ViewCompat;
import com.facebook.share.internal.ShareConstants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NotificationCompat {
    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    @ColorInt
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;
    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    public static class Action {
        public static final int SEMANTIC_ACTION_ARCHIVE = 5;
        public static final int SEMANTIC_ACTION_CALL = 10;
        public static final int SEMANTIC_ACTION_DELETE = 4;
        public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
        public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
        public static final int SEMANTIC_ACTION_MUTE = 6;
        public static final int SEMANTIC_ACTION_NONE = 0;
        public static final int SEMANTIC_ACTION_REPLY = 1;
        public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
        public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
        public static final int SEMANTIC_ACTION_UNMUTE = 7;
        final Bundle a;
        public PendingIntent actionIntent;
        boolean b;
        public int icon;
        private boolean mAllowGeneratedReplies;
        private final RemoteInput[] mDataOnlyRemoteInputs;
        private final RemoteInput[] mRemoteInputs;
        private final int mSemanticAction;
        public CharSequence title;

        public static final class Builder {
            private boolean mAllowGeneratedReplies;
            private final Bundle mExtras;
            private final int mIcon;
            private final PendingIntent mIntent;
            private ArrayList<RemoteInput> mRemoteInputs;
            private int mSemanticAction;
            private boolean mShowsUserInterface;
            private final CharSequence mTitle;

            public Builder(int i, CharSequence charSequence, PendingIntent pendingIntent) {
                this(i, charSequence, pendingIntent, new Bundle(), (RemoteInput[]) null, true, 0, true);
            }

            private Builder(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, boolean z, int i2, boolean z2) {
                this.mAllowGeneratedReplies = true;
                this.mShowsUserInterface = true;
                this.mIcon = i;
                this.mTitle = Builder.a(charSequence);
                this.mIntent = pendingIntent;
                this.mExtras = bundle;
                this.mRemoteInputs = remoteInputArr == null ? null : new ArrayList<>(Arrays.asList(remoteInputArr));
                this.mAllowGeneratedReplies = z;
                this.mSemanticAction = i2;
                this.mShowsUserInterface = z2;
            }

            public Builder(Action action) {
                this(action.icon, action.title, action.actionIntent, new Bundle(action.a), action.getRemoteInputs(), action.getAllowGeneratedReplies(), action.getSemanticAction(), action.b);
            }

            public Builder addExtras(Bundle bundle) {
                if (bundle != null) {
                    this.mExtras.putAll(bundle);
                }
                return this;
            }

            public Builder addRemoteInput(RemoteInput remoteInput) {
                if (this.mRemoteInputs == null) {
                    this.mRemoteInputs = new ArrayList<>();
                }
                this.mRemoteInputs.add(remoteInput);
                return this;
            }

            /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Object[]] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public androidx.core.app.NotificationCompat.Action build() {
                /*
                    r14 = this;
                    java.util.ArrayList r0 = new java.util.ArrayList
                    r0.<init>()
                    java.util.ArrayList r1 = new java.util.ArrayList
                    r1.<init>()
                    java.util.ArrayList<androidx.core.app.RemoteInput> r2 = r14.mRemoteInputs
                    if (r2 == 0) goto L_0x002c
                    java.util.Iterator r2 = r2.iterator()
                L_0x0012:
                    boolean r3 = r2.hasNext()
                    if (r3 == 0) goto L_0x002c
                    java.lang.Object r3 = r2.next()
                    androidx.core.app.RemoteInput r3 = (androidx.core.app.RemoteInput) r3
                    boolean r4 = r3.isDataOnly()
                    if (r4 == 0) goto L_0x0028
                    r0.add(r3)
                    goto L_0x0012
                L_0x0028:
                    r1.add(r3)
                    goto L_0x0012
                L_0x002c:
                    boolean r2 = r0.isEmpty()
                    r3 = 0
                    if (r2 == 0) goto L_0x0035
                    r10 = r3
                    goto L_0x0042
                L_0x0035:
                    int r2 = r0.size()
                    androidx.core.app.RemoteInput[] r2 = new androidx.core.app.RemoteInput[r2]
                    java.lang.Object[] r0 = r0.toArray(r2)
                    androidx.core.app.RemoteInput[] r0 = (androidx.core.app.RemoteInput[]) r0
                    r10 = r0
                L_0x0042:
                    boolean r0 = r1.isEmpty()
                    if (r0 == 0) goto L_0x0049
                    goto L_0x0056
                L_0x0049:
                    int r0 = r1.size()
                    androidx.core.app.RemoteInput[] r0 = new androidx.core.app.RemoteInput[r0]
                    java.lang.Object[] r0 = r1.toArray(r0)
                    r3 = r0
                    androidx.core.app.RemoteInput[] r3 = (androidx.core.app.RemoteInput[]) r3
                L_0x0056:
                    r9 = r3
                    androidx.core.app.NotificationCompat$Action r0 = new androidx.core.app.NotificationCompat$Action
                    int r5 = r14.mIcon
                    java.lang.CharSequence r6 = r14.mTitle
                    android.app.PendingIntent r7 = r14.mIntent
                    android.os.Bundle r8 = r14.mExtras
                    boolean r11 = r14.mAllowGeneratedReplies
                    int r12 = r14.mSemanticAction
                    boolean r13 = r14.mShowsUserInterface
                    r4 = r0
                    r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat.Action.Builder.build():androidx.core.app.NotificationCompat$Action");
            }

            public Builder extend(Extender extender) {
                extender.extend(this);
                return this;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            public Builder setAllowGeneratedReplies(boolean z) {
                this.mAllowGeneratedReplies = z;
                return this;
            }

            public Builder setSemanticAction(int i) {
                this.mSemanticAction = i;
                return this;
            }

            public Builder setShowsUserInterface(boolean z) {
                this.mShowsUserInterface = z;
                return this;
            }
        }

        public interface Extender {
            Builder extend(Builder builder);
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface SemanticAction {
        }

        public static final class WearableExtender implements Extender {
            private static final int DEFAULT_FLAGS = 1;
            private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            private static final int FLAG_AVAILABLE_OFFLINE = 1;
            private static final int FLAG_HINT_DISPLAY_INLINE = 4;
            private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
            private static final String KEY_CANCEL_LABEL = "cancelLabel";
            private static final String KEY_CONFIRM_LABEL = "confirmLabel";
            private static final String KEY_FLAGS = "flags";
            private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            private CharSequence mCancelLabel;
            private CharSequence mConfirmLabel;
            private int mFlags = 1;
            private CharSequence mInProgressLabel;

            public WearableExtender() {
            }

            public WearableExtender(Action action) {
                Bundle bundle = action.getExtras().getBundle(EXTRA_WEARABLE_EXTENSIONS);
                if (bundle != null) {
                    this.mFlags = bundle.getInt(KEY_FLAGS, 1);
                    this.mInProgressLabel = bundle.getCharSequence(KEY_IN_PROGRESS_LABEL);
                    this.mConfirmLabel = bundle.getCharSequence(KEY_CONFIRM_LABEL);
                    this.mCancelLabel = bundle.getCharSequence(KEY_CANCEL_LABEL);
                }
            }

            private void setFlag(int i, boolean z) {
                int i2;
                if (z) {
                    i2 = i | this.mFlags;
                } else {
                    i2 = (~i) & this.mFlags;
                }
                this.mFlags = i2;
            }

            public WearableExtender clone() {
                WearableExtender wearableExtender = new WearableExtender();
                wearableExtender.mFlags = this.mFlags;
                wearableExtender.mInProgressLabel = this.mInProgressLabel;
                wearableExtender.mConfirmLabel = this.mConfirmLabel;
                wearableExtender.mCancelLabel = this.mCancelLabel;
                return wearableExtender;
            }

            public Builder extend(Builder builder) {
                Bundle bundle = new Bundle();
                int i = this.mFlags;
                if (i != 1) {
                    bundle.putInt(KEY_FLAGS, i);
                }
                CharSequence charSequence = this.mInProgressLabel;
                if (charSequence != null) {
                    bundle.putCharSequence(KEY_IN_PROGRESS_LABEL, charSequence);
                }
                CharSequence charSequence2 = this.mConfirmLabel;
                if (charSequence2 != null) {
                    bundle.putCharSequence(KEY_CONFIRM_LABEL, charSequence2);
                }
                CharSequence charSequence3 = this.mCancelLabel;
                if (charSequence3 != null) {
                    bundle.putCharSequence(KEY_CANCEL_LABEL, charSequence3);
                }
                builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, bundle);
                return builder;
            }

            @Deprecated
            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }

            @Deprecated
            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }

            public boolean getHintDisplayActionInline() {
                return (this.mFlags & 4) != 0;
            }

            public boolean getHintLaunchesActivity() {
                return (this.mFlags & 2) != 0;
            }

            @Deprecated
            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }

            public boolean isAvailableOffline() {
                return (this.mFlags & 1) != 0;
            }

            public WearableExtender setAvailableOffline(boolean z) {
                setFlag(1, z);
                return this;
            }

            @Deprecated
            public WearableExtender setCancelLabel(CharSequence charSequence) {
                this.mCancelLabel = charSequence;
                return this;
            }

            @Deprecated
            public WearableExtender setConfirmLabel(CharSequence charSequence) {
                this.mConfirmLabel = charSequence;
                return this;
            }

            public WearableExtender setHintDisplayActionInline(boolean z) {
                setFlag(4, z);
                return this;
            }

            public WearableExtender setHintLaunchesActivity(boolean z) {
                setFlag(2, z);
                return this;
            }

            @Deprecated
            public WearableExtender setInProgressLabel(CharSequence charSequence) {
                this.mInProgressLabel = charSequence;
                return this;
            }
        }

        public Action(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i, charSequence, pendingIntent, new Bundle(), (RemoteInput[]) null, (RemoteInput[]) null, true, 0, true);
        }

        Action(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, RemoteInput[] remoteInputArr2, boolean z, int i2, boolean z2) {
            this.b = true;
            this.icon = i;
            this.title = Builder.a(charSequence);
            this.actionIntent = pendingIntent;
            this.a = bundle == null ? new Bundle() : bundle;
            this.mRemoteInputs = remoteInputArr;
            this.mDataOnlyRemoteInputs = remoteInputArr2;
            this.mAllowGeneratedReplies = z;
            this.mSemanticAction = i2;
            this.b = z2;
        }

        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        public RemoteInput[] getDataOnlyRemoteInputs() {
            return this.mDataOnlyRemoteInputs;
        }

        public Bundle getExtras() {
            return this.a;
        }

        public int getIcon() {
            return this.icon;
        }

        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public int getSemanticAction() {
            return this.mSemanticAction;
        }

        public boolean getShowsUserInterface() {
            return this.b;
        }

        public CharSequence getTitle() {
            return this.title;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeIconType {
    }

    public static class BigPictureStyle extends Style {
        private Bitmap mBigLargeIcon;
        private boolean mBigLargeIconSet;
        private Bitmap mPicture;

        public BigPictureStyle() {
        }

        public BigPictureStyle(Builder builder) {
            setBuilder(builder);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 16) {
                Notification.BigPictureStyle bigPicture = new Notification.BigPictureStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.b).bigPicture(this.mPicture);
                if (this.mBigLargeIconSet) {
                    bigPicture.bigLargeIcon(this.mBigLargeIcon);
                }
                if (this.d) {
                    bigPicture.setSummaryText(this.c);
                }
            }
        }

        public BigPictureStyle bigLargeIcon(Bitmap bitmap) {
            this.mBigLargeIcon = bitmap;
            this.mBigLargeIconSet = true;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap bitmap) {
            this.mPicture = bitmap;
            return this;
        }

        public BigPictureStyle setBigContentTitle(CharSequence charSequence) {
            this.b = Builder.a(charSequence);
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence charSequence) {
            this.c = Builder.a(charSequence);
            this.d = true;
            return this;
        }
    }

    public static class BigTextStyle extends Style {
        private CharSequence mBigText;

        public BigTextStyle() {
        }

        public BigTextStyle(Builder builder) {
            setBuilder(builder);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 16) {
                Notification.BigTextStyle bigText = new Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.b).bigText(this.mBigText);
                if (this.d) {
                    bigText.setSummaryText(this.c);
                }
            }
        }

        public BigTextStyle bigText(CharSequence charSequence) {
            this.mBigText = Builder.a(charSequence);
            return this;
        }

        public BigTextStyle setBigContentTitle(CharSequence charSequence) {
            this.b = Builder.a(charSequence);
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence charSequence) {
            this.c = Builder.a(charSequence);
            this.d = true;
            return this;
        }
    }

    public static class Builder {
        private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
        int A;
        int B;
        Notification C;
        RemoteViews D;
        RemoteViews E;
        RemoteViews F;
        String G;
        int H;
        String I;
        long J;
        int K;
        Notification L;
        ArrayList<Action> a;
        CharSequence b;
        CharSequence c;
        PendingIntent d;
        PendingIntent e;
        RemoteViews f;
        Bitmap g;
        CharSequence h;
        int i;
        int j;
        boolean k;
        boolean l;
        Style m;
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public ArrayList<Action> mActions;
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Context mContext;
        @Deprecated
        public ArrayList<String> mPeople;
        CharSequence n;
        CharSequence[] o;
        int p;
        int q;
        boolean r;
        String s;
        boolean t;
        String u;
        boolean v;
        boolean w;
        boolean x;
        String y;
        Bundle z;

        @Deprecated
        public Builder(Context context) {
            this(context, (String) null);
        }

        public Builder(@NonNull Context context, @NonNull String str) {
            this.mActions = new ArrayList<>();
            this.a = new ArrayList<>();
            this.k = true;
            this.v = false;
            this.A = 0;
            this.B = 0;
            this.H = 0;
            this.K = 0;
            this.L = new Notification();
            this.mContext = context;
            this.G = str;
            this.L.when = System.currentTimeMillis();
            this.L.audioStreamType = -1;
            this.j = 0;
            this.mPeople = new ArrayList<>();
        }

        protected static CharSequence a(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > MAX_CHARSEQUENCE_LENGTH) ? charSequence.subSequence(0, MAX_CHARSEQUENCE_LENGTH) : charSequence;
        }

        private Bitmap reduceLargeIconSize(Bitmap bitmap) {
            if (bitmap == null || Build.VERSION.SDK_INT >= 27) {
                return bitmap;
            }
            Resources resources = this.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_width);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_height);
            if (bitmap.getWidth() <= dimensionPixelSize && bitmap.getHeight() <= dimensionPixelSize2) {
                return bitmap;
            }
            double min = Math.min(((double) dimensionPixelSize) / ((double) Math.max(1, bitmap.getWidth())), ((double) dimensionPixelSize2) / ((double) Math.max(1, bitmap.getHeight())));
            return Bitmap.createScaledBitmap(bitmap, (int) Math.ceil(((double) bitmap.getWidth()) * min), (int) Math.ceil(((double) bitmap.getHeight()) * min), true);
        }

        private void setFlag(int i2, boolean z2) {
            Notification notification;
            int i3;
            if (z2) {
                notification = this.L;
                i3 = i2 | notification.flags;
            } else {
                notification = this.L;
                i3 = (~i2) & notification.flags;
            }
            notification.flags = i3;
        }

        public Builder addAction(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
            this.mActions.add(new Action(i2, charSequence, pendingIntent));
            return this;
        }

        public Builder addAction(Action action) {
            this.mActions.add(action);
            return this;
        }

        public Builder addExtras(Bundle bundle) {
            if (bundle != null) {
                Bundle bundle2 = this.z;
                if (bundle2 == null) {
                    this.z = new Bundle(bundle);
                } else {
                    bundle2.putAll(bundle);
                }
            }
            return this;
        }

        @RequiresApi(21)
        public Builder addInvisibleAction(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
            return addInvisibleAction(new Action(i2, charSequence, pendingIntent));
        }

        @RequiresApi(21)
        public Builder addInvisibleAction(Action action) {
            this.a.add(action);
            return this;
        }

        public Builder addPerson(String str) {
            this.mPeople.add(str);
            return this;
        }

        public Notification build() {
            return new NotificationCompatBuilder(this).build();
        }

        public Builder extend(Extender extender) {
            extender.extend(this);
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews getBigContentView() {
            return this.E;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public int getColor() {
            return this.A;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews getContentView() {
            return this.D;
        }

        public Bundle getExtras() {
            if (this.z == null) {
                this.z = new Bundle();
            }
            return this.z;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews getHeadsUpContentView() {
            return this.F;
        }

        @Deprecated
        public Notification getNotification() {
            return build();
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public int getPriority() {
            return this.j;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public long getWhenIfShowing() {
            if (this.k) {
                return this.L.when;
            }
            return 0;
        }

        public Builder setAutoCancel(boolean z2) {
            setFlag(16, z2);
            return this;
        }

        public Builder setBadgeIconType(int i2) {
            this.H = i2;
            return this;
        }

        public Builder setCategory(String str) {
            this.y = str;
            return this;
        }

        public Builder setChannelId(@NonNull String str) {
            this.G = str;
            return this;
        }

        public Builder setColor(@ColorInt int i2) {
            this.A = i2;
            return this;
        }

        public Builder setColorized(boolean z2) {
            this.w = z2;
            this.x = true;
            return this;
        }

        public Builder setContent(RemoteViews remoteViews) {
            this.L.contentView = remoteViews;
            return this;
        }

        public Builder setContentInfo(CharSequence charSequence) {
            this.h = a(charSequence);
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingIntent) {
            this.d = pendingIntent;
            return this;
        }

        public Builder setContentText(CharSequence charSequence) {
            this.c = a(charSequence);
            return this;
        }

        public Builder setContentTitle(CharSequence charSequence) {
            this.b = a(charSequence);
            return this;
        }

        public Builder setCustomBigContentView(RemoteViews remoteViews) {
            this.E = remoteViews;
            return this;
        }

        public Builder setCustomContentView(RemoteViews remoteViews) {
            this.D = remoteViews;
            return this;
        }

        public Builder setCustomHeadsUpContentView(RemoteViews remoteViews) {
            this.F = remoteViews;
            return this;
        }

        public Builder setDefaults(int i2) {
            Notification notification = this.L;
            notification.defaults = i2;
            if ((i2 & 4) != 0) {
                notification.flags |= 1;
            }
            return this;
        }

        public Builder setDeleteIntent(PendingIntent pendingIntent) {
            this.L.deleteIntent = pendingIntent;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.z = bundle;
            return this;
        }

        public Builder setFullScreenIntent(PendingIntent pendingIntent, boolean z2) {
            this.e = pendingIntent;
            setFlag(128, z2);
            return this;
        }

        public Builder setGroup(String str) {
            this.s = str;
            return this;
        }

        public Builder setGroupAlertBehavior(int i2) {
            this.K = i2;
            return this;
        }

        public Builder setGroupSummary(boolean z2) {
            this.t = z2;
            return this;
        }

        public Builder setLargeIcon(Bitmap bitmap) {
            this.g = reduceLargeIconSize(bitmap);
            return this;
        }

        public Builder setLights(@ColorInt int i2, int i3, int i4) {
            Notification notification = this.L;
            notification.ledARGB = i2;
            notification.ledOnMS = i3;
            notification.ledOffMS = i4;
            int i5 = (notification.ledOnMS == 0 || this.L.ledOffMS == 0) ? 0 : 1;
            Notification notification2 = this.L;
            notification2.flags = i5 | (notification2.flags & -2);
            return this;
        }

        public Builder setLocalOnly(boolean z2) {
            this.v = z2;
            return this;
        }

        public Builder setNumber(int i2) {
            this.i = i2;
            return this;
        }

        public Builder setOngoing(boolean z2) {
            setFlag(2, z2);
            return this;
        }

        public Builder setOnlyAlertOnce(boolean z2) {
            setFlag(8, z2);
            return this;
        }

        public Builder setPriority(int i2) {
            this.j = i2;
            return this;
        }

        public Builder setProgress(int i2, int i3, boolean z2) {
            this.p = i2;
            this.q = i3;
            this.r = z2;
            return this;
        }

        public Builder setPublicVersion(Notification notification) {
            this.C = notification;
            return this;
        }

        public Builder setRemoteInputHistory(CharSequence[] charSequenceArr) {
            this.o = charSequenceArr;
            return this;
        }

        public Builder setShortcutId(String str) {
            this.I = str;
            return this;
        }

        public Builder setShowWhen(boolean z2) {
            this.k = z2;
            return this;
        }

        public Builder setSmallIcon(int i2) {
            this.L.icon = i2;
            return this;
        }

        public Builder setSmallIcon(int i2, int i3) {
            Notification notification = this.L;
            notification.icon = i2;
            notification.iconLevel = i3;
            return this;
        }

        public Builder setSortKey(String str) {
            this.u = str;
            return this;
        }

        public Builder setSound(Uri uri) {
            Notification notification = this.L;
            notification.sound = uri;
            notification.audioStreamType = -1;
            if (Build.VERSION.SDK_INT >= 21) {
                this.L.audioAttributes = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
            }
            return this;
        }

        public Builder setSound(Uri uri, int i2) {
            Notification notification = this.L;
            notification.sound = uri;
            notification.audioStreamType = i2;
            if (Build.VERSION.SDK_INT >= 21) {
                this.L.audioAttributes = new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(i2).build();
            }
            return this;
        }

        public Builder setStyle(Style style) {
            if (this.m != style) {
                this.m = style;
                Style style2 = this.m;
                if (style2 != null) {
                    style2.setBuilder(this);
                }
            }
            return this;
        }

        public Builder setSubText(CharSequence charSequence) {
            this.n = a(charSequence);
            return this;
        }

        public Builder setTicker(CharSequence charSequence) {
            this.L.tickerText = a(charSequence);
            return this;
        }

        public Builder setTicker(CharSequence charSequence, RemoteViews remoteViews) {
            this.L.tickerText = a(charSequence);
            this.f = remoteViews;
            return this;
        }

        public Builder setTimeoutAfter(long j2) {
            this.J = j2;
            return this;
        }

        public Builder setUsesChronometer(boolean z2) {
            this.l = z2;
            return this;
        }

        public Builder setVibrate(long[] jArr) {
            this.L.vibrate = jArr;
            return this;
        }

        public Builder setVisibility(int i2) {
            this.B = i2;
            return this;
        }

        public Builder setWhen(long j2) {
            this.L.when = j2;
            return this;
        }
    }

    public static final class CarExtender implements Extender {
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String KEY_AUTHOR = "author";
        private static final String KEY_MESSAGES = "messages";
        private static final String KEY_ON_READ = "on_read";
        private static final String KEY_ON_REPLY = "on_reply";
        private static final String KEY_PARTICIPANTS = "participants";
        private static final String KEY_REMOTE_INPUT = "remote_input";
        private static final String KEY_TEXT = "text";
        private static final String KEY_TIMESTAMP = "timestamp";
        private int mColor = 0;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;

        public static class UnreadConversation {
            private final long mLatestTimestamp;
            private final String[] mMessages;
            private final String[] mParticipants;
            private final PendingIntent mReadPendingIntent;
            private final RemoteInput mRemoteInput;
            private final PendingIntent mReplyPendingIntent;

            public static class Builder {
                private long mLatestTimestamp;
                private final List<String> mMessages = new ArrayList();
                private final String mParticipant;
                private PendingIntent mReadPendingIntent;
                private RemoteInput mRemoteInput;
                private PendingIntent mReplyPendingIntent;

                public Builder(String str) {
                    this.mParticipant = str;
                }

                public Builder addMessage(String str) {
                    this.mMessages.add(str);
                    return this;
                }

                public UnreadConversation build() {
                    List<String> list = this.mMessages;
                    return new UnreadConversation((String[]) list.toArray(new String[list.size()]), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[]{this.mParticipant}, this.mLatestTimestamp);
                }

                public Builder setLatestTimestamp(long j) {
                    this.mLatestTimestamp = j;
                    return this;
                }

                public Builder setReadPendingIntent(PendingIntent pendingIntent) {
                    this.mReadPendingIntent = pendingIntent;
                    return this;
                }

                public Builder setReplyAction(PendingIntent pendingIntent, RemoteInput remoteInput) {
                    this.mRemoteInput = remoteInput;
                    this.mReplyPendingIntent = pendingIntent;
                    return this;
                }
            }

            UnreadConversation(String[] strArr, RemoteInput remoteInput, PendingIntent pendingIntent, PendingIntent pendingIntent2, String[] strArr2, long j) {
                this.mMessages = strArr;
                this.mRemoteInput = remoteInput;
                this.mReadPendingIntent = pendingIntent2;
                this.mReplyPendingIntent = pendingIntent;
                this.mParticipants = strArr2;
                this.mLatestTimestamp = j;
            }

            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }

            public String[] getMessages() {
                return this.mMessages;
            }

            public String getParticipant() {
                String[] strArr = this.mParticipants;
                if (strArr.length > 0) {
                    return strArr[0];
                }
                return null;
            }

            public String[] getParticipants() {
                return this.mParticipants;
            }

            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }

            public RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }

            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }
        }

        public CarExtender() {
        }

        public CarExtender(Notification notification) {
            if (Build.VERSION.SDK_INT >= 21) {
                Bundle bundle = NotificationCompat.getExtras(notification) == null ? null : NotificationCompat.getExtras(notification).getBundle("android.car.EXTENSIONS");
                if (bundle != null) {
                    this.mLargeIcon = (Bitmap) bundle.getParcelable(EXTRA_LARGE_ICON);
                    this.mColor = bundle.getInt(EXTRA_COLOR, 0);
                    this.mUnreadConversation = getUnreadConversationFromBundle(bundle.getBundle(EXTRA_CONVERSATION));
                }
            }
        }

        @RequiresApi(21)
        private static Bundle getBundleForUnreadConversation(@NonNull UnreadConversation unreadConversation) {
            Bundle bundle = new Bundle();
            String str = (unreadConversation.getParticipants() == null || unreadConversation.getParticipants().length <= 1) ? null : unreadConversation.getParticipants()[0];
            Parcelable[] parcelableArr = new Parcelable[unreadConversation.getMessages().length];
            for (int i = 0; i < parcelableArr.length; i++) {
                Bundle bundle2 = new Bundle();
                bundle2.putString(KEY_TEXT, unreadConversation.getMessages()[i]);
                bundle2.putString(KEY_AUTHOR, str);
                parcelableArr[i] = bundle2;
            }
            bundle.putParcelableArray(KEY_MESSAGES, parcelableArr);
            RemoteInput remoteInput = unreadConversation.getRemoteInput();
            if (remoteInput != null) {
                bundle.putParcelable(KEY_REMOTE_INPUT, new RemoteInput.Builder(remoteInput.getResultKey()).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras()).build());
            }
            bundle.putParcelable(KEY_ON_REPLY, unreadConversation.getReplyPendingIntent());
            bundle.putParcelable(KEY_ON_READ, unreadConversation.getReadPendingIntent());
            bundle.putStringArray(KEY_PARTICIPANTS, unreadConversation.getParticipants());
            bundle.putLong("timestamp", unreadConversation.getLatestTimestamp());
            return bundle;
        }

        @RequiresApi(21)
        private static UnreadConversation getUnreadConversationFromBundle(@Nullable Bundle bundle) {
            String[] strArr;
            Bundle bundle2 = bundle;
            RemoteInput remoteInput = null;
            if (bundle2 == null) {
                return null;
            }
            Parcelable[] parcelableArray = bundle2.getParcelableArray(KEY_MESSAGES);
            if (parcelableArray != null) {
                String[] strArr2 = new String[parcelableArray.length];
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= strArr2.length) {
                        z = true;
                        break;
                    } else if (!(parcelableArray[i] instanceof Bundle)) {
                        break;
                    } else {
                        strArr2[i] = ((Bundle) parcelableArray[i]).getString(KEY_TEXT);
                        if (strArr2[i] == null) {
                            break;
                        }
                        i++;
                    }
                }
                if (!z) {
                    return null;
                }
                strArr = strArr2;
            } else {
                strArr = null;
            }
            PendingIntent pendingIntent = (PendingIntent) bundle2.getParcelable(KEY_ON_READ);
            PendingIntent pendingIntent2 = (PendingIntent) bundle2.getParcelable(KEY_ON_REPLY);
            RemoteInput remoteInput2 = (RemoteInput) bundle2.getParcelable(KEY_REMOTE_INPUT);
            String[] stringArray = bundle2.getStringArray(KEY_PARTICIPANTS);
            if (stringArray == null || stringArray.length != 1) {
                return null;
            }
            if (remoteInput2 != null) {
                remoteInput = new RemoteInput(remoteInput2.getResultKey(), remoteInput2.getLabel(), remoteInput2.getChoices(), remoteInput2.getAllowFreeFormInput(), remoteInput2.getExtras(), (Set<String>) null);
            }
            return new UnreadConversation(strArr, remoteInput, pendingIntent2, pendingIntent, stringArray, bundle2.getLong("timestamp"));
        }

        public Builder extend(Builder builder) {
            if (Build.VERSION.SDK_INT < 21) {
                return builder;
            }
            Bundle bundle = new Bundle();
            Bitmap bitmap = this.mLargeIcon;
            if (bitmap != null) {
                bundle.putParcelable(EXTRA_LARGE_ICON, bitmap);
            }
            int i = this.mColor;
            if (i != 0) {
                bundle.putInt(EXTRA_COLOR, i);
            }
            UnreadConversation unreadConversation = this.mUnreadConversation;
            if (unreadConversation != null) {
                bundle.putBundle(EXTRA_CONVERSATION, getBundleForUnreadConversation(unreadConversation));
            }
            builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
            return builder;
        }

        @ColorInt
        public int getColor() {
            return this.mColor;
        }

        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }

        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }

        public CarExtender setColor(@ColorInt int i) {
            this.mColor = i;
            return this;
        }

        public CarExtender setLargeIcon(Bitmap bitmap) {
            this.mLargeIcon = bitmap;
            return this;
        }

        public CarExtender setUnreadConversation(UnreadConversation unreadConversation) {
            this.mUnreadConversation = unreadConversation;
            return this;
        }
    }

    public static class DecoratedCustomViewStyle extends Style {
        private static final int MAX_ACTION_BUTTONS = 3;

        private RemoteViews createRemoteViews(RemoteViews remoteViews, boolean z) {
            int min;
            boolean z2 = true;
            int i = 0;
            RemoteViews applyStandardTemplate = applyStandardTemplate(true, R.layout.notification_template_custom_big, false);
            applyStandardTemplate.removeAllViews(R.id.actions);
            if (!z || this.a.mActions == null || (min = Math.min(this.a.mActions.size(), 3)) <= 0) {
                z2 = false;
            } else {
                for (int i2 = 0; i2 < min; i2++) {
                    applyStandardTemplate.addView(R.id.actions, generateActionButton(this.a.mActions.get(i2)));
                }
            }
            if (!z2) {
                i = 8;
            }
            applyStandardTemplate.setViewVisibility(R.id.actions, i);
            applyStandardTemplate.setViewVisibility(R.id.action_divider, i);
            buildIntoRemoteViews(applyStandardTemplate, remoteViews);
            return applyStandardTemplate;
        }

        private RemoteViews generateActionButton(Action action) {
            boolean z = action.actionIntent == null;
            RemoteViews remoteViews = new RemoteViews(this.a.mContext.getPackageName(), z ? R.layout.notification_action_tombstone : R.layout.notification_action);
            remoteViews.setImageViewBitmap(R.id.action_image, createColoredBitmap(action.getIcon(), this.a.mContext.getResources().getColor(R.color.notification_action_color_filter)));
            remoteViews.setTextViewText(R.id.action_text, action.title);
            if (!z) {
                remoteViews.setOnClickPendingIntent(R.id.action_container, action.actionIntent);
            }
            if (Build.VERSION.SDK_INT >= 15) {
                remoteViews.setContentDescription(R.id.action_container, action.title);
            }
            return remoteViews;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(new Notification.DecoratedCustomViewStyle());
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews bigContentView = this.a.getBigContentView();
            if (bigContentView == null) {
                bigContentView = this.a.getContentView();
            }
            if (bigContentView == null) {
                return null;
            }
            return createRemoteViews(bigContentView, true);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT < 24 && this.a.getContentView() != null) {
                return createRemoteViews(this.a.getContentView(), false);
            }
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews headsUpContentView = this.a.getHeadsUpContentView();
            RemoteViews contentView = headsUpContentView != null ? headsUpContentView : this.a.getContentView();
            if (headsUpContentView == null) {
                return null;
            }
            return createRemoteViews(contentView, true);
        }
    }

    public interface Extender {
        Builder extend(Builder builder);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupAlertBehavior {
    }

    public static class InboxStyle extends Style {
        private ArrayList<CharSequence> mTexts = new ArrayList<>();

        public InboxStyle() {
        }

        public InboxStyle(Builder builder) {
            setBuilder(builder);
        }

        public InboxStyle addLine(CharSequence charSequence) {
            this.mTexts.add(Builder.a(charSequence));
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 16) {
                Notification.InboxStyle bigContentTitle = new Notification.InboxStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.b);
                if (this.d) {
                    bigContentTitle.setSummaryText(this.c);
                }
                Iterator<CharSequence> it = this.mTexts.iterator();
                while (it.hasNext()) {
                    bigContentTitle.addLine(it.next());
                }
            }
        }

        public InboxStyle setBigContentTitle(CharSequence charSequence) {
            this.b = Builder.a(charSequence);
            return this;
        }

        public InboxStyle setSummaryText(CharSequence charSequence) {
            this.c = Builder.a(charSequence);
            this.d = true;
            return this;
        }
    }

    public static class MessagingStyle extends Style {
        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        @Nullable
        private CharSequence mConversationTitle;
        @Nullable
        private Boolean mIsGroupConversation;
        private final List<Message> mMessages = new ArrayList();
        private Person mUser;

        public static final class Message {
            @Nullable
            private String mDataMimeType;
            @Nullable
            private Uri mDataUri;
            private Bundle mExtras;
            @Nullable
            private final Person mPerson;
            private final CharSequence mText;
            private final long mTimestamp;

            public Message(CharSequence charSequence, long j, @Nullable Person person) {
                this.mExtras = new Bundle();
                this.mText = charSequence;
                this.mTimestamp = j;
                this.mPerson = person;
            }

            @Deprecated
            public Message(CharSequence charSequence, long j, CharSequence charSequence2) {
                this(charSequence, j, new Person.Builder().setName(charSequence2).build());
            }

            @Nullable
            static Message a(Bundle bundle) {
                try {
                    if (bundle.containsKey("text")) {
                        if (bundle.containsKey("time")) {
                            Message message = new Message(bundle.getCharSequence("text"), bundle.getLong("time"), bundle.containsKey("person") ? Person.fromBundle(bundle.getBundle("person")) : (!bundle.containsKey("sender_person") || Build.VERSION.SDK_INT < 28) ? bundle.containsKey("sender") ? new Person.Builder().setName(bundle.getCharSequence("sender")).build() : null : Person.fromAndroidPerson((android.app.Person) bundle.getParcelable("sender_person")));
                            if (bundle.containsKey("type") && bundle.containsKey(ShareConstants.MEDIA_URI)) {
                                message.setData(bundle.getString("type"), (Uri) bundle.getParcelable(ShareConstants.MEDIA_URI));
                            }
                            if (bundle.containsKey("extras")) {
                                message.getExtras().putAll(bundle.getBundle("extras"));
                            }
                            return message;
                        }
                    }
                } catch (ClassCastException unused) {
                }
                return null;
            }

            @NonNull
            static List<Message> a(Parcelable[] parcelableArr) {
                Message a;
                ArrayList arrayList = new ArrayList(parcelableArr.length);
                for (int i = 0; i < parcelableArr.length; i++) {
                    if ((parcelableArr[i] instanceof Bundle) && (a = a(parcelableArr[i])) != null) {
                        arrayList.add(a);
                    }
                }
                return arrayList;
            }

            @NonNull
            static Bundle[] a(List<Message> list) {
                Bundle[] bundleArr = new Bundle[list.size()];
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    bundleArr[i] = list.get(i).toBundle();
                }
                return bundleArr;
            }

            private Bundle toBundle() {
                Bundle bundle = new Bundle();
                CharSequence charSequence = this.mText;
                if (charSequence != null) {
                    bundle.putCharSequence("text", charSequence);
                }
                bundle.putLong("time", this.mTimestamp);
                Person person = this.mPerson;
                if (person != null) {
                    bundle.putCharSequence("sender", person.getName());
                    if (Build.VERSION.SDK_INT >= 28) {
                        bundle.putParcelable("sender_person", this.mPerson.toAndroidPerson());
                    } else {
                        bundle.putBundle("person", this.mPerson.toBundle());
                    }
                }
                String str = this.mDataMimeType;
                if (str != null) {
                    bundle.putString("type", str);
                }
                Uri uri = this.mDataUri;
                if (uri != null) {
                    bundle.putParcelable(ShareConstants.MEDIA_URI, uri);
                }
                Bundle bundle2 = this.mExtras;
                if (bundle2 != null) {
                    bundle.putBundle("extras", bundle2);
                }
                return bundle;
            }

            @Nullable
            public String getDataMimeType() {
                return this.mDataMimeType;
            }

            @Nullable
            public Uri getDataUri() {
                return this.mDataUri;
            }

            @NonNull
            public Bundle getExtras() {
                return this.mExtras;
            }

            @Nullable
            public Person getPerson() {
                return this.mPerson;
            }

            @Deprecated
            @Nullable
            public CharSequence getSender() {
                Person person = this.mPerson;
                if (person == null) {
                    return null;
                }
                return person.getName();
            }

            @NonNull
            public CharSequence getText() {
                return this.mText;
            }

            public long getTimestamp() {
                return this.mTimestamp;
            }

            public Message setData(String str, Uri uri) {
                this.mDataMimeType = str;
                this.mDataUri = uri;
                return this;
            }
        }

        private MessagingStyle() {
        }

        public MessagingStyle(@NonNull Person person) {
            if (!TextUtils.isEmpty(person.getName())) {
                this.mUser = person;
                return;
            }
            throw new IllegalArgumentException("User's name must not be empty.");
        }

        @Deprecated
        public MessagingStyle(@NonNull CharSequence charSequence) {
            this.mUser = new Person.Builder().setName(charSequence).build();
        }

        @Nullable
        public static MessagingStyle extractMessagingStyleFromNotification(Notification notification) {
            Bundle extras = NotificationCompat.getExtras(notification);
            if (extras != null && !extras.containsKey(NotificationCompat.EXTRA_SELF_DISPLAY_NAME) && !extras.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                return null;
            }
            try {
                MessagingStyle messagingStyle = new MessagingStyle();
                messagingStyle.a(extras);
                return messagingStyle;
            } catch (ClassCastException unused) {
                return null;
            }
        }

        @Nullable
        private Message findLatestIncomingMessage() {
            for (int size = this.mMessages.size() - 1; size >= 0; size--) {
                Message message = this.mMessages.get(size);
                if (message.getPerson() != null && !TextUtils.isEmpty(message.getPerson().getName())) {
                    return message;
                }
            }
            if (this.mMessages.isEmpty()) {
                return null;
            }
            List<Message> list = this.mMessages;
            return list.get(list.size() - 1);
        }

        private boolean hasMessagesWithoutSender() {
            for (int size = this.mMessages.size() - 1; size >= 0; size--) {
                Message message = this.mMessages.get(size);
                if (message.getPerson() != null && message.getPerson().getName() == null) {
                    return true;
                }
            }
            return false;
        }

        @NonNull
        private TextAppearanceSpan makeFontColorSpan(int i) {
            return new TextAppearanceSpan((String) null, 0, 0, ColorStateList.valueOf(i), (ColorStateList) null);
        }

        private CharSequence makeMessageLine(Message message) {
            BidiFormatter instance = BidiFormatter.getInstance();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            boolean z = Build.VERSION.SDK_INT >= 21;
            int i = z ? ViewCompat.MEASURED_STATE_MASK : -1;
            String name = message.getPerson() == null ? "" : message.getPerson().getName();
            if (TextUtils.isEmpty(name)) {
                name = this.mUser.getName();
                if (z && this.a.getColor() != 0) {
                    i = this.a.getColor();
                }
            }
            CharSequence unicodeWrap = instance.unicodeWrap(name);
            spannableStringBuilder.append(unicodeWrap);
            spannableStringBuilder.setSpan(makeFontColorSpan(i), spannableStringBuilder.length() - unicodeWrap.length(), spannableStringBuilder.length(), 33);
            spannableStringBuilder.append("  ").append(instance.unicodeWrap(message.getText() == null ? "" : message.getText()));
            return spannableStringBuilder;
        }

        /* access modifiers changed from: protected */
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void a(Bundle bundle) {
            this.mMessages.clear();
            this.mUser = bundle.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER) ? Person.fromBundle(bundle.getBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) : new Person.Builder().setName(bundle.getString(NotificationCompat.EXTRA_SELF_DISPLAY_NAME)).build();
            this.mConversationTitle = bundle.getCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE);
            if (this.mConversationTitle == null) {
                this.mConversationTitle = bundle.getCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE);
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray(NotificationCompat.EXTRA_MESSAGES);
            if (parcelableArray != null) {
                this.mMessages.addAll(Message.a(parcelableArray));
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION)) {
                this.mIsGroupConversation = Boolean.valueOf(bundle.getBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION));
            }
        }

        public void addCompatExtras(Bundle bundle) {
            super.addCompatExtras(bundle);
            bundle.putCharSequence(NotificationCompat.EXTRA_SELF_DISPLAY_NAME, this.mUser.getName());
            bundle.putBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER, this.mUser.toBundle());
            bundle.putCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE, this.mConversationTitle);
            if (this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                bundle.putCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE, this.mConversationTitle);
            }
            if (!this.mMessages.isEmpty()) {
                bundle.putParcelableArray(NotificationCompat.EXTRA_MESSAGES, Message.a(this.mMessages));
            }
            Boolean bool = this.mIsGroupConversation;
            if (bool != null) {
                bundle.putBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION, bool.booleanValue());
            }
        }

        public MessagingStyle addMessage(Message message) {
            this.mMessages.add(message);
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        public MessagingStyle addMessage(CharSequence charSequence, long j, Person person) {
            addMessage(new Message(charSequence, j, person));
            return this;
        }

        @Deprecated
        public MessagingStyle addMessage(CharSequence charSequence, long j, CharSequence charSequence2) {
            this.mMessages.add(new Message(charSequence, j, new Person.Builder().setName(charSequence2).build()));
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:45:0x00ee  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x0108  */
        /* JADX WARNING: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
        @androidx.annotation.RestrictTo({androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP})
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void apply(androidx.core.app.NotificationBuilderWithBuilderAccessor r11) {
            /*
                r10 = this;
                boolean r0 = r10.isGroupConversation()
                r10.setGroupConversation(r0)
                int r0 = android.os.Build.VERSION.SDK_INT
                r1 = 0
                r2 = 24
                if (r0 < r2) goto L_0x00b4
                int r0 = android.os.Build.VERSION.SDK_INT
                r2 = 28
                if (r0 < r2) goto L_0x0020
                android.app.Notification$MessagingStyle r0 = new android.app.Notification$MessagingStyle
                androidx.core.app.Person r3 = r10.mUser
                android.app.Person r3 = r3.toAndroidPerson()
                r0.<init>(r3)
                goto L_0x002b
            L_0x0020:
                android.app.Notification$MessagingStyle r0 = new android.app.Notification$MessagingStyle
                androidx.core.app.Person r3 = r10.mUser
                java.lang.CharSequence r3 = r3.getName()
                r0.<init>(r3)
            L_0x002b:
                java.lang.Boolean r3 = r10.mIsGroupConversation
                boolean r3 = r3.booleanValue()
                if (r3 != 0) goto L_0x0037
                int r3 = android.os.Build.VERSION.SDK_INT
                if (r3 < r2) goto L_0x003c
            L_0x0037:
                java.lang.CharSequence r3 = r10.mConversationTitle
                r0.setConversationTitle(r3)
            L_0x003c:
                int r3 = android.os.Build.VERSION.SDK_INT
                if (r3 < r2) goto L_0x0049
                java.lang.Boolean r3 = r10.mIsGroupConversation
                boolean r3 = r3.booleanValue()
                r0.setGroupConversation(r3)
            L_0x0049:
                java.util.List<androidx.core.app.NotificationCompat$MessagingStyle$Message> r3 = r10.mMessages
                java.util.Iterator r3 = r3.iterator()
            L_0x004f:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L_0x00ab
                java.lang.Object r4 = r3.next()
                androidx.core.app.NotificationCompat$MessagingStyle$Message r4 = (androidx.core.app.NotificationCompat.MessagingStyle.Message) r4
                int r5 = android.os.Build.VERSION.SDK_INT
                if (r5 < r2) goto L_0x0079
                androidx.core.app.Person r5 = r4.getPerson()
                android.app.Notification$MessagingStyle$Message r6 = new android.app.Notification$MessagingStyle$Message
                java.lang.CharSequence r7 = r4.getText()
                long r8 = r4.getTimestamp()
                if (r5 != 0) goto L_0x0071
                r5 = r1
                goto L_0x0075
            L_0x0071:
                android.app.Person r5 = r5.toAndroidPerson()
            L_0x0075:
                r6.<init>(r7, r8, r5)
                goto L_0x0096
            L_0x0079:
                androidx.core.app.Person r5 = r4.getPerson()
                if (r5 == 0) goto L_0x0088
                androidx.core.app.Person r5 = r4.getPerson()
                java.lang.CharSequence r5 = r5.getName()
                goto L_0x0089
            L_0x0088:
                r5 = r1
            L_0x0089:
                android.app.Notification$MessagingStyle$Message r6 = new android.app.Notification$MessagingStyle$Message
                java.lang.CharSequence r7 = r4.getText()
                long r8 = r4.getTimestamp()
                r6.<init>(r7, r8, r5)
            L_0x0096:
                java.lang.String r5 = r4.getDataMimeType()
                if (r5 == 0) goto L_0x00a7
                java.lang.String r5 = r4.getDataMimeType()
                android.net.Uri r4 = r4.getDataUri()
                r6.setData(r5, r4)
            L_0x00a7:
                r0.addMessage(r6)
                goto L_0x004f
            L_0x00ab:
                android.app.Notification$Builder r11 = r11.getBuilder()
                r0.setBuilder(r11)
                goto L_0x015d
            L_0x00b4:
                androidx.core.app.NotificationCompat$MessagingStyle$Message r0 = r10.findLatestIncomingMessage()
                java.lang.CharSequence r2 = r10.mConversationTitle
                if (r2 == 0) goto L_0x00ce
                java.lang.Boolean r2 = r10.mIsGroupConversation
                boolean r2 = r2.booleanValue()
                if (r2 == 0) goto L_0x00ce
                android.app.Notification$Builder r2 = r11.getBuilder()
                java.lang.CharSequence r3 = r10.mConversationTitle
            L_0x00ca:
                r2.setContentTitle(r3)
                goto L_0x00ec
            L_0x00ce:
                if (r0 == 0) goto L_0x00ec
                android.app.Notification$Builder r2 = r11.getBuilder()
                java.lang.String r3 = ""
                r2.setContentTitle(r3)
                androidx.core.app.Person r2 = r0.getPerson()
                if (r2 == 0) goto L_0x00ec
                android.app.Notification$Builder r2 = r11.getBuilder()
                androidx.core.app.Person r3 = r0.getPerson()
                java.lang.CharSequence r3 = r3.getName()
                goto L_0x00ca
            L_0x00ec:
                if (r0 == 0) goto L_0x0102
                android.app.Notification$Builder r2 = r11.getBuilder()
                java.lang.CharSequence r3 = r10.mConversationTitle
                if (r3 == 0) goto L_0x00fb
                java.lang.CharSequence r0 = r10.makeMessageLine(r0)
                goto L_0x00ff
            L_0x00fb:
                java.lang.CharSequence r0 = r0.getText()
            L_0x00ff:
                r2.setContentText(r0)
            L_0x0102:
                int r0 = android.os.Build.VERSION.SDK_INT
                r2 = 16
                if (r0 < r2) goto L_0x015d
                android.text.SpannableStringBuilder r0 = new android.text.SpannableStringBuilder
                r0.<init>()
                java.lang.CharSequence r2 = r10.mConversationTitle
                r3 = 0
                r4 = 1
                if (r2 != 0) goto L_0x011c
                boolean r2 = r10.hasMessagesWithoutSender()
                if (r2 == 0) goto L_0x011a
                goto L_0x011c
            L_0x011a:
                r2 = 0
                goto L_0x011d
            L_0x011c:
                r2 = 1
            L_0x011d:
                java.util.List<androidx.core.app.NotificationCompat$MessagingStyle$Message> r5 = r10.mMessages
                int r5 = r5.size()
                int r5 = r5 - r4
            L_0x0124:
                if (r5 < 0) goto L_0x014d
                java.util.List<androidx.core.app.NotificationCompat$MessagingStyle$Message> r6 = r10.mMessages
                java.lang.Object r6 = r6.get(r5)
                androidx.core.app.NotificationCompat$MessagingStyle$Message r6 = (androidx.core.app.NotificationCompat.MessagingStyle.Message) r6
                if (r2 == 0) goto L_0x0135
                java.lang.CharSequence r6 = r10.makeMessageLine(r6)
                goto L_0x0139
            L_0x0135:
                java.lang.CharSequence r6 = r6.getText()
            L_0x0139:
                java.util.List<androidx.core.app.NotificationCompat$MessagingStyle$Message> r7 = r10.mMessages
                int r7 = r7.size()
                int r7 = r7 - r4
                if (r5 == r7) goto L_0x0147
                java.lang.String r7 = "\n"
                r0.insert(r3, r7)
            L_0x0147:
                r0.insert(r3, r6)
                int r5 = r5 + -1
                goto L_0x0124
            L_0x014d:
                android.app.Notification$BigTextStyle r2 = new android.app.Notification$BigTextStyle
                android.app.Notification$Builder r11 = r11.getBuilder()
                r2.<init>(r11)
                android.app.Notification$BigTextStyle r11 = r2.setBigContentTitle(r1)
                r11.bigText(r0)
            L_0x015d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat.MessagingStyle.apply(androidx.core.app.NotificationBuilderWithBuilderAccessor):void");
        }

        @Nullable
        public CharSequence getConversationTitle() {
            return this.mConversationTitle;
        }

        public List<Message> getMessages() {
            return this.mMessages;
        }

        public Person getUser() {
            return this.mUser;
        }

        @Deprecated
        public CharSequence getUserDisplayName() {
            return this.mUser.getName();
        }

        public boolean isGroupConversation() {
            if (this.a != null && this.a.mContext.getApplicationInfo().targetSdkVersion < 28 && this.mIsGroupConversation == null) {
                return this.mConversationTitle != null;
            }
            Boolean bool = this.mIsGroupConversation;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        }

        public MessagingStyle setConversationTitle(@Nullable CharSequence charSequence) {
            this.mConversationTitle = charSequence;
            return this;
        }

        public MessagingStyle setGroupConversation(boolean z) {
            this.mIsGroupConversation = Boolean.valueOf(z);
            return this;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NotificationVisibility {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamType {
    }

    public static abstract class Style {
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        protected Builder a;
        CharSequence b;
        CharSequence c;
        boolean d = false;

        private int calculateTopPadding() {
            Resources resources = this.a.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_top_pad);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
            float constrain = (constrain(resources.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
            return Math.round(((1.0f - constrain) * ((float) dimensionPixelSize)) + (constrain * ((float) dimensionPixelSize2)));
        }

        private static float constrain(float f, float f2, float f3) {
            return f < f2 ? f2 : f > f3 ? f3 : f;
        }

        private Bitmap createColoredBitmap(int i, int i2, int i3) {
            Drawable drawable = this.a.mContext.getResources().getDrawable(i);
            int intrinsicWidth = i3 == 0 ? drawable.getIntrinsicWidth() : i3;
            if (i3 == 0) {
                i3 = drawable.getIntrinsicHeight();
            }
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i3, Bitmap.Config.ARGB_8888);
            drawable.setBounds(0, 0, intrinsicWidth, i3);
            if (i2 != 0) {
                drawable.mutate().setColorFilter(new PorterDuffColorFilter(i2, PorterDuff.Mode.SRC_IN));
            }
            drawable.draw(new Canvas(createBitmap));
            return createBitmap;
        }

        private Bitmap createIconWithBackground(int i, int i2, int i3, int i4) {
            int i5 = R.drawable.notification_icon_background;
            if (i4 == 0) {
                i4 = 0;
            }
            Bitmap createColoredBitmap = createColoredBitmap(i5, i4, i2);
            Canvas canvas = new Canvas(createColoredBitmap);
            Drawable mutate = this.a.mContext.getResources().getDrawable(i).mutate();
            mutate.setFilterBitmap(true);
            int i6 = (i2 - i3) / 2;
            int i7 = i3 + i6;
            mutate.setBounds(i6, i6, i7, i7);
            mutate.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
            mutate.draw(canvas);
            return createColoredBitmap;
        }

        private void hideNormalContent(RemoteViews remoteViews) {
            remoteViews.setViewVisibility(R.id.title, 8);
            remoteViews.setViewVisibility(R.id.text2, 8);
            remoteViews.setViewVisibility(R.id.text, 8);
        }

        /* access modifiers changed from: protected */
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void a(Bundle bundle) {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void addCompatExtras(Bundle bundle) {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        }

        /* JADX WARNING: Removed duplicated region for block: B:64:0x0198  */
        /* JADX WARNING: Removed duplicated region for block: B:65:0x01a8  */
        /* JADX WARNING: Removed duplicated region for block: B:71:0x01b6  */
        /* JADX WARNING: Removed duplicated region for block: B:75:0x01d8  */
        /* JADX WARNING: Removed duplicated region for block: B:84:0x021d  */
        /* JADX WARNING: Removed duplicated region for block: B:85:0x021f  */
        /* JADX WARNING: Removed duplicated region for block: B:88:0x0229  */
        @androidx.annotation.RestrictTo({androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP})
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.widget.RemoteViews applyStandardTemplate(boolean r13, int r14, boolean r15) {
            /*
                r12 = this;
                androidx.core.app.NotificationCompat$Builder r0 = r12.a
                android.content.Context r0 = r0.mContext
                android.content.res.Resources r0 = r0.getResources()
                android.widget.RemoteViews r7 = new android.widget.RemoteViews
                androidx.core.app.NotificationCompat$Builder r1 = r12.a
                android.content.Context r1 = r1.mContext
                java.lang.String r1 = r1.getPackageName()
                r7.<init>(r1, r14)
                androidx.core.app.NotificationCompat$Builder r14 = r12.a
                int r14 = r14.getPriority()
                r1 = -1
                r8 = 1
                r9 = 0
                if (r14 >= r1) goto L_0x0022
                r14 = 1
                goto L_0x0023
            L_0x0022:
                r14 = 0
            L_0x0023:
                int r2 = android.os.Build.VERSION.SDK_INT
                r3 = 21
                r10 = 16
                if (r2 < r10) goto L_0x0053
                int r2 = android.os.Build.VERSION.SDK_INT
                if (r2 >= r3) goto L_0x0053
                if (r14 == 0) goto L_0x0041
                int r14 = androidx.core.R.id.notification_background
                java.lang.String r2 = "setBackgroundResource"
                int r4 = androidx.core.R.drawable.notification_bg_low
                r7.setInt(r14, r2, r4)
                int r14 = androidx.core.R.id.icon
                java.lang.String r2 = "setBackgroundResource"
                int r4 = androidx.core.R.drawable.notification_template_icon_low_bg
                goto L_0x0050
            L_0x0041:
                int r14 = androidx.core.R.id.notification_background
                java.lang.String r2 = "setBackgroundResource"
                int r4 = androidx.core.R.drawable.notification_bg
                r7.setInt(r14, r2, r4)
                int r14 = androidx.core.R.id.icon
                java.lang.String r2 = "setBackgroundResource"
                int r4 = androidx.core.R.drawable.notification_template_icon_bg
            L_0x0050:
                r7.setInt(r14, r2, r4)
            L_0x0053:
                androidx.core.app.NotificationCompat$Builder r14 = r12.a
                android.graphics.Bitmap r14 = r14.g
                r11 = 8
                if (r14 == 0) goto L_0x00bc
                int r14 = android.os.Build.VERSION.SDK_INT
                if (r14 < r10) goto L_0x006e
                int r14 = androidx.core.R.id.icon
                r7.setViewVisibility(r14, r9)
                int r14 = androidx.core.R.id.icon
                androidx.core.app.NotificationCompat$Builder r2 = r12.a
                android.graphics.Bitmap r2 = r2.g
                r7.setImageViewBitmap(r14, r2)
                goto L_0x0073
            L_0x006e:
                int r14 = androidx.core.R.id.icon
                r7.setViewVisibility(r14, r11)
            L_0x0073:
                if (r13 == 0) goto L_0x0107
                androidx.core.app.NotificationCompat$Builder r13 = r12.a
                android.app.Notification r13 = r13.L
                int r13 = r13.icon
                if (r13 == 0) goto L_0x0107
                int r13 = androidx.core.R.dimen.notification_right_icon_size
                int r13 = r0.getDimensionPixelSize(r13)
                int r14 = androidx.core.R.dimen.notification_small_icon_background_padding
                int r14 = r0.getDimensionPixelSize(r14)
                int r14 = r14 * 2
                int r14 = r13 - r14
                int r2 = android.os.Build.VERSION.SDK_INT
                if (r2 < r3) goto L_0x00a7
                androidx.core.app.NotificationCompat$Builder r1 = r12.a
                android.app.Notification r1 = r1.L
                int r1 = r1.icon
                androidx.core.app.NotificationCompat$Builder r2 = r12.a
                int r2 = r2.getColor()
                android.graphics.Bitmap r13 = r12.createIconWithBackground(r1, r13, r14, r2)
                int r14 = androidx.core.R.id.right_icon
                r7.setImageViewBitmap(r14, r13)
                goto L_0x00b6
            L_0x00a7:
                int r13 = androidx.core.R.id.right_icon
                androidx.core.app.NotificationCompat$Builder r14 = r12.a
                android.app.Notification r14 = r14.L
                int r14 = r14.icon
                android.graphics.Bitmap r14 = r12.createColoredBitmap(r14, r1)
                r7.setImageViewBitmap(r13, r14)
            L_0x00b6:
                int r13 = androidx.core.R.id.right_icon
                r7.setViewVisibility(r13, r9)
                goto L_0x0107
            L_0x00bc:
                if (r13 == 0) goto L_0x0107
                androidx.core.app.NotificationCompat$Builder r13 = r12.a
                android.app.Notification r13 = r13.L
                int r13 = r13.icon
                if (r13 == 0) goto L_0x0107
                int r13 = androidx.core.R.id.icon
                r7.setViewVisibility(r13, r9)
                int r13 = android.os.Build.VERSION.SDK_INT
                if (r13 < r3) goto L_0x00f8
                int r13 = androidx.core.R.dimen.notification_large_icon_width
                int r13 = r0.getDimensionPixelSize(r13)
                int r14 = androidx.core.R.dimen.notification_big_circle_margin
                int r14 = r0.getDimensionPixelSize(r14)
                int r13 = r13 - r14
                int r14 = androidx.core.R.dimen.notification_small_icon_size_as_large
                int r14 = r0.getDimensionPixelSize(r14)
                androidx.core.app.NotificationCompat$Builder r1 = r12.a
                android.app.Notification r1 = r1.L
                int r1 = r1.icon
                androidx.core.app.NotificationCompat$Builder r2 = r12.a
                int r2 = r2.getColor()
                android.graphics.Bitmap r13 = r12.createIconWithBackground(r1, r13, r14, r2)
                int r14 = androidx.core.R.id.icon
                r7.setImageViewBitmap(r14, r13)
                goto L_0x0107
            L_0x00f8:
                int r13 = androidx.core.R.id.icon
                androidx.core.app.NotificationCompat$Builder r14 = r12.a
                android.app.Notification r14 = r14.L
                int r14 = r14.icon
                android.graphics.Bitmap r14 = r12.createColoredBitmap(r14, r1)
                r7.setImageViewBitmap(r13, r14)
            L_0x0107:
                androidx.core.app.NotificationCompat$Builder r13 = r12.a
                java.lang.CharSequence r13 = r13.b
                if (r13 == 0) goto L_0x0116
                int r13 = androidx.core.R.id.title
                androidx.core.app.NotificationCompat$Builder r14 = r12.a
                java.lang.CharSequence r14 = r14.b
                r7.setTextViewText(r13, r14)
            L_0x0116:
                androidx.core.app.NotificationCompat$Builder r13 = r12.a
                java.lang.CharSequence r13 = r13.c
                if (r13 == 0) goto L_0x0127
                int r13 = androidx.core.R.id.text
                androidx.core.app.NotificationCompat$Builder r14 = r12.a
                java.lang.CharSequence r14 = r14.c
                r7.setTextViewText(r13, r14)
                r13 = 1
                goto L_0x0128
            L_0x0127:
                r13 = 0
            L_0x0128:
                int r14 = android.os.Build.VERSION.SDK_INT
                if (r14 >= r3) goto L_0x0134
                androidx.core.app.NotificationCompat$Builder r14 = r12.a
                android.graphics.Bitmap r14 = r14.g
                if (r14 == 0) goto L_0x0134
                r14 = 1
                goto L_0x0135
            L_0x0134:
                r14 = 0
            L_0x0135:
                androidx.core.app.NotificationCompat$Builder r1 = r12.a
                java.lang.CharSequence r1 = r1.h
                if (r1 == 0) goto L_0x014c
                int r13 = androidx.core.R.id.info
                androidx.core.app.NotificationCompat$Builder r14 = r12.a
                java.lang.CharSequence r14 = r14.h
            L_0x0141:
                r7.setTextViewText(r13, r14)
            L_0x0144:
                int r13 = androidx.core.R.id.info
                r7.setViewVisibility(r13, r9)
                r13 = 1
                r14 = 1
                goto L_0x017f
            L_0x014c:
                androidx.core.app.NotificationCompat$Builder r1 = r12.a
                int r1 = r1.i
                if (r1 <= 0) goto L_0x017a
                int r13 = androidx.core.R.integer.status_bar_notification_info_maxnum
                int r13 = r0.getInteger(r13)
                androidx.core.app.NotificationCompat$Builder r14 = r12.a
                int r14 = r14.i
                if (r14 <= r13) goto L_0x0167
                int r13 = androidx.core.R.id.info
                int r14 = androidx.core.R.string.status_bar_notification_info_overflow
                java.lang.String r14 = r0.getString(r14)
                goto L_0x0141
            L_0x0167:
                java.text.NumberFormat r13 = java.text.NumberFormat.getIntegerInstance()
                int r14 = androidx.core.R.id.info
                androidx.core.app.NotificationCompat$Builder r1 = r12.a
                int r1 = r1.i
                long r1 = (long) r1
                java.lang.String r13 = r13.format(r1)
                r7.setTextViewText(r14, r13)
                goto L_0x0144
            L_0x017a:
                int r1 = androidx.core.R.id.info
                r7.setViewVisibility(r1, r11)
            L_0x017f:
                androidx.core.app.NotificationCompat$Builder r1 = r12.a
                java.lang.CharSequence r1 = r1.n
                if (r1 == 0) goto L_0x01ad
                int r1 = android.os.Build.VERSION.SDK_INT
                if (r1 < r10) goto L_0x01ad
                int r1 = androidx.core.R.id.text
                androidx.core.app.NotificationCompat$Builder r2 = r12.a
                java.lang.CharSequence r2 = r2.n
                r7.setTextViewText(r1, r2)
                androidx.core.app.NotificationCompat$Builder r1 = r12.a
                java.lang.CharSequence r1 = r1.c
                if (r1 == 0) goto L_0x01a8
                int r1 = androidx.core.R.id.text2
                androidx.core.app.NotificationCompat$Builder r2 = r12.a
                java.lang.CharSequence r2 = r2.c
                r7.setTextViewText(r1, r2)
                int r1 = androidx.core.R.id.text2
                r7.setViewVisibility(r1, r9)
                r1 = 1
                goto L_0x01ae
            L_0x01a8:
                int r1 = androidx.core.R.id.text2
                r7.setViewVisibility(r1, r11)
            L_0x01ad:
                r1 = 0
            L_0x01ae:
                if (r1 == 0) goto L_0x01cc
                int r1 = android.os.Build.VERSION.SDK_INT
                if (r1 < r10) goto L_0x01cc
                if (r15 == 0) goto L_0x01c2
                int r15 = androidx.core.R.dimen.notification_subtext_size
                int r15 = r0.getDimensionPixelSize(r15)
                float r15 = (float) r15
                int r0 = androidx.core.R.id.text
                r7.setTextViewTextSize(r0, r9, r15)
            L_0x01c2:
                int r2 = androidx.core.R.id.line1
                r3 = 0
                r4 = 0
                r5 = 0
                r6 = 0
                r1 = r7
                r1.setViewPadding(r2, r3, r4, r5, r6)
            L_0x01cc:
                androidx.core.app.NotificationCompat$Builder r15 = r12.a
                long r0 = r15.getWhenIfShowing()
                r2 = 0
                int r15 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r15 == 0) goto L_0x0219
                androidx.core.app.NotificationCompat$Builder r14 = r12.a
                boolean r14 = r14.l
                if (r14 == 0) goto L_0x0206
                int r14 = android.os.Build.VERSION.SDK_INT
                if (r14 < r10) goto L_0x0206
                int r14 = androidx.core.R.id.chronometer
                r7.setViewVisibility(r14, r9)
                int r14 = androidx.core.R.id.chronometer
                java.lang.String r15 = "setBase"
                androidx.core.app.NotificationCompat$Builder r0 = r12.a
                long r0 = r0.getWhenIfShowing()
                long r2 = android.os.SystemClock.elapsedRealtime()
                long r4 = java.lang.System.currentTimeMillis()
                long r2 = r2 - r4
                long r0 = r0 + r2
                r7.setLong(r14, r15, r0)
                int r14 = androidx.core.R.id.chronometer
                java.lang.String r15 = "setStarted"
                r7.setBoolean(r14, r15, r8)
                goto L_0x0218
            L_0x0206:
                int r14 = androidx.core.R.id.time
                r7.setViewVisibility(r14, r9)
                int r14 = androidx.core.R.id.time
                java.lang.String r15 = "setTime"
                androidx.core.app.NotificationCompat$Builder r0 = r12.a
                long r0 = r0.getWhenIfShowing()
                r7.setLong(r14, r15, r0)
            L_0x0218:
                r14 = 1
            L_0x0219:
                int r15 = androidx.core.R.id.right_side
                if (r14 == 0) goto L_0x021f
                r14 = 0
                goto L_0x0221
            L_0x021f:
                r14 = 8
            L_0x0221:
                r7.setViewVisibility(r15, r14)
                int r14 = androidx.core.R.id.line3
                if (r13 == 0) goto L_0x0229
                goto L_0x022b
            L_0x0229:
                r9 = 8
            L_0x022b:
                r7.setViewVisibility(r14, r9)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat.Style.applyStandardTemplate(boolean, int, boolean):android.widget.RemoteViews");
        }

        public Notification build() {
            Builder builder = this.a;
            if (builder != null) {
                return builder.build();
            }
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void buildIntoRemoteViews(RemoteViews remoteViews, RemoteViews remoteViews2) {
            hideNormalContent(remoteViews);
            remoteViews.removeAllViews(R.id.notification_main_column);
            remoteViews.addView(R.id.notification_main_column, remoteViews2.clone());
            remoteViews.setViewVisibility(R.id.notification_main_column, 0);
            if (Build.VERSION.SDK_INT >= 21) {
                remoteViews.setViewPadding(R.id.notification_main_column_container, 0, calculateTopPadding(), 0, 0);
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Bitmap createColoredBitmap(int i, int i2) {
            return createColoredBitmap(i, i2, 0);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public void setBuilder(Builder builder) {
            if (this.a != builder) {
                this.a = builder;
                Builder builder2 = this.a;
                if (builder2 != null) {
                    builder2.setStyle(this);
                }
            }
        }
    }

    public static final class WearableExtender implements Extender {
        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_BRIDGE_TAG = "bridgeTag";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";
        public static final int SCREEN_TIMEOUT_LONG = -1;
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        public static final int SIZE_DEFAULT = 0;
        public static final int SIZE_FULL_SCREEN = 5;
        public static final int SIZE_LARGE = 4;
        public static final int SIZE_MEDIUM = 3;
        public static final int SIZE_SMALL = 2;
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList<Action> mActions = new ArrayList<>();
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex = -1;
        private int mContentIcon;
        private int mContentIconGravity = 8388613;
        private int mCustomContentHeight;
        private int mCustomSizePreset = 0;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags = 1;
        private int mGravity = 80;
        private int mHintScreenTimeout;
        private ArrayList<Notification> mPages = new ArrayList<>();

        public WearableExtender() {
        }

        public WearableExtender(Notification notification) {
            Bundle extras = NotificationCompat.getExtras(notification);
            Bundle bundle = extras != null ? extras.getBundle(EXTRA_WEARABLE_EXTENSIONS) : null;
            if (bundle != null) {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList(KEY_ACTIONS);
                if (Build.VERSION.SDK_INT >= 16 && parcelableArrayList != null) {
                    Action[] actionArr = new Action[parcelableArrayList.size()];
                    for (int i = 0; i < actionArr.length; i++) {
                        if (Build.VERSION.SDK_INT >= 20) {
                            actionArr[i] = NotificationCompat.a((Notification.Action) parcelableArrayList.get(i));
                        } else if (Build.VERSION.SDK_INT >= 16) {
                            actionArr[i] = NotificationCompatJellybean.a((Bundle) parcelableArrayList.get(i));
                        }
                    }
                    Collections.addAll(this.mActions, actionArr);
                }
                this.mFlags = bundle.getInt(KEY_FLAGS, 1);
                this.mDisplayIntent = (PendingIntent) bundle.getParcelable(KEY_DISPLAY_INTENT);
                Notification[] a = NotificationCompat.a(bundle, KEY_PAGES);
                if (a != null) {
                    Collections.addAll(this.mPages, a);
                }
                this.mBackground = (Bitmap) bundle.getParcelable(KEY_BACKGROUND);
                this.mContentIcon = bundle.getInt(KEY_CONTENT_ICON);
                this.mContentIconGravity = bundle.getInt(KEY_CONTENT_ICON_GRAVITY, 8388613);
                this.mContentActionIndex = bundle.getInt(KEY_CONTENT_ACTION_INDEX, -1);
                this.mCustomSizePreset = bundle.getInt(KEY_CUSTOM_SIZE_PRESET, 0);
                this.mCustomContentHeight = bundle.getInt(KEY_CUSTOM_CONTENT_HEIGHT);
                this.mGravity = bundle.getInt(KEY_GRAVITY, 80);
                this.mHintScreenTimeout = bundle.getInt(KEY_HINT_SCREEN_TIMEOUT);
                this.mDismissalId = bundle.getString(KEY_DISMISSAL_ID);
                this.mBridgeTag = bundle.getString(KEY_BRIDGE_TAG);
            }
        }

        @RequiresApi(20)
        private static Notification.Action getActionFromActionCompat(Action action) {
            Notification.Action.Builder builder = new Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
            Bundle bundle = action.getExtras() != null ? new Bundle(action.getExtras()) : new Bundle();
            bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
            if (Build.VERSION.SDK_INT >= 24) {
                builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
            }
            builder.addExtras(bundle);
            RemoteInput[] remoteInputs = action.getRemoteInputs();
            if (remoteInputs != null) {
                for (RemoteInput addRemoteInput : RemoteInput.a(remoteInputs)) {
                    builder.addRemoteInput(addRemoteInput);
                }
            }
            return builder.build();
        }

        private void setFlag(int i, boolean z) {
            int i2;
            if (z) {
                i2 = i | this.mFlags;
            } else {
                i2 = (~i) & this.mFlags;
            }
            this.mFlags = i2;
        }

        public WearableExtender addAction(Action action) {
            this.mActions.add(action);
            return this;
        }

        public WearableExtender addActions(List<Action> list) {
            this.mActions.addAll(list);
            return this;
        }

        public WearableExtender addPage(Notification notification) {
            this.mPages.add(notification);
            return this;
        }

        public WearableExtender addPages(List<Notification> list) {
            this.mPages.addAll(list);
            return this;
        }

        public WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }

        public WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }

        public WearableExtender clone() {
            WearableExtender wearableExtender = new WearableExtender();
            wearableExtender.mActions = new ArrayList<>(this.mActions);
            wearableExtender.mFlags = this.mFlags;
            wearableExtender.mDisplayIntent = this.mDisplayIntent;
            wearableExtender.mPages = new ArrayList<>(this.mPages);
            wearableExtender.mBackground = this.mBackground;
            wearableExtender.mContentIcon = this.mContentIcon;
            wearableExtender.mContentIconGravity = this.mContentIconGravity;
            wearableExtender.mContentActionIndex = this.mContentActionIndex;
            wearableExtender.mCustomSizePreset = this.mCustomSizePreset;
            wearableExtender.mCustomContentHeight = this.mCustomContentHeight;
            wearableExtender.mGravity = this.mGravity;
            wearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
            wearableExtender.mDismissalId = this.mDismissalId;
            wearableExtender.mBridgeTag = this.mBridgeTag;
            return wearableExtender;
        }

        public Builder extend(Builder builder) {
            Object a;
            Bundle bundle = new Bundle();
            if (!this.mActions.isEmpty()) {
                if (Build.VERSION.SDK_INT >= 16) {
                    ArrayList arrayList = new ArrayList(this.mActions.size());
                    Iterator<Action> it = this.mActions.iterator();
                    while (it.hasNext()) {
                        Action next = it.next();
                        if (Build.VERSION.SDK_INT >= 20) {
                            a = getActionFromActionCompat(next);
                        } else if (Build.VERSION.SDK_INT >= 16) {
                            a = NotificationCompatJellybean.a(next);
                        }
                        arrayList.add(a);
                    }
                    bundle.putParcelableArrayList(KEY_ACTIONS, arrayList);
                } else {
                    bundle.putParcelableArrayList(KEY_ACTIONS, (ArrayList) null);
                }
            }
            int i = this.mFlags;
            if (i != 1) {
                bundle.putInt(KEY_FLAGS, i);
            }
            PendingIntent pendingIntent = this.mDisplayIntent;
            if (pendingIntent != null) {
                bundle.putParcelable(KEY_DISPLAY_INTENT, pendingIntent);
            }
            if (!this.mPages.isEmpty()) {
                ArrayList<Notification> arrayList2 = this.mPages;
                bundle.putParcelableArray(KEY_PAGES, (Parcelable[]) arrayList2.toArray(new Notification[arrayList2.size()]));
            }
            Bitmap bitmap = this.mBackground;
            if (bitmap != null) {
                bundle.putParcelable(KEY_BACKGROUND, bitmap);
            }
            int i2 = this.mContentIcon;
            if (i2 != 0) {
                bundle.putInt(KEY_CONTENT_ICON, i2);
            }
            int i3 = this.mContentIconGravity;
            if (i3 != 8388613) {
                bundle.putInt(KEY_CONTENT_ICON_GRAVITY, i3);
            }
            int i4 = this.mContentActionIndex;
            if (i4 != -1) {
                bundle.putInt(KEY_CONTENT_ACTION_INDEX, i4);
            }
            int i5 = this.mCustomSizePreset;
            if (i5 != 0) {
                bundle.putInt(KEY_CUSTOM_SIZE_PRESET, i5);
            }
            int i6 = this.mCustomContentHeight;
            if (i6 != 0) {
                bundle.putInt(KEY_CUSTOM_CONTENT_HEIGHT, i6);
            }
            int i7 = this.mGravity;
            if (i7 != 80) {
                bundle.putInt(KEY_GRAVITY, i7);
            }
            int i8 = this.mHintScreenTimeout;
            if (i8 != 0) {
                bundle.putInt(KEY_HINT_SCREEN_TIMEOUT, i8);
            }
            String str = this.mDismissalId;
            if (str != null) {
                bundle.putString(KEY_DISMISSAL_ID, str);
            }
            String str2 = this.mBridgeTag;
            if (str2 != null) {
                bundle.putString(KEY_BRIDGE_TAG, str2);
            }
            builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, bundle);
            return builder;
        }

        public List<Action> getActions() {
            return this.mActions;
        }

        public Bitmap getBackground() {
            return this.mBackground;
        }

        public String getBridgeTag() {
            return this.mBridgeTag;
        }

        public int getContentAction() {
            return this.mContentActionIndex;
        }

        @Deprecated
        public int getContentIcon() {
            return this.mContentIcon;
        }

        @Deprecated
        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }

        public boolean getContentIntentAvailableOffline() {
            return (this.mFlags & 1) != 0;
        }

        @Deprecated
        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }

        @Deprecated
        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }

        public String getDismissalId() {
            return this.mDismissalId;
        }

        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }

        @Deprecated
        public int getGravity() {
            return this.mGravity;
        }

        public boolean getHintAmbientBigPicture() {
            return (this.mFlags & 32) != 0;
        }

        @Deprecated
        public boolean getHintAvoidBackgroundClipping() {
            return (this.mFlags & 16) != 0;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            return (this.mFlags & 64) != 0;
        }

        @Deprecated
        public boolean getHintHideIcon() {
            return (this.mFlags & 2) != 0;
        }

        @Deprecated
        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }

        @Deprecated
        public boolean getHintShowBackgroundOnly() {
            return (this.mFlags & 4) != 0;
        }

        public List<Notification> getPages() {
            return this.mPages;
        }

        public boolean getStartScrollBottom() {
            return (this.mFlags & 8) != 0;
        }

        public WearableExtender setBackground(Bitmap bitmap) {
            this.mBackground = bitmap;
            return this;
        }

        public WearableExtender setBridgeTag(String str) {
            this.mBridgeTag = str;
            return this;
        }

        public WearableExtender setContentAction(int i) {
            this.mContentActionIndex = i;
            return this;
        }

        @Deprecated
        public WearableExtender setContentIcon(int i) {
            this.mContentIcon = i;
            return this;
        }

        @Deprecated
        public WearableExtender setContentIconGravity(int i) {
            this.mContentIconGravity = i;
            return this;
        }

        public WearableExtender setContentIntentAvailableOffline(boolean z) {
            setFlag(1, z);
            return this;
        }

        @Deprecated
        public WearableExtender setCustomContentHeight(int i) {
            this.mCustomContentHeight = i;
            return this;
        }

        @Deprecated
        public WearableExtender setCustomSizePreset(int i) {
            this.mCustomSizePreset = i;
            return this;
        }

        public WearableExtender setDismissalId(String str) {
            this.mDismissalId = str;
            return this;
        }

        public WearableExtender setDisplayIntent(PendingIntent pendingIntent) {
            this.mDisplayIntent = pendingIntent;
            return this;
        }

        @Deprecated
        public WearableExtender setGravity(int i) {
            this.mGravity = i;
            return this;
        }

        public WearableExtender setHintAmbientBigPicture(boolean z) {
            setFlag(32, z);
            return this;
        }

        @Deprecated
        public WearableExtender setHintAvoidBackgroundClipping(boolean z) {
            setFlag(16, z);
            return this;
        }

        public WearableExtender setHintContentIntentLaunchesActivity(boolean z) {
            setFlag(64, z);
            return this;
        }

        @Deprecated
        public WearableExtender setHintHideIcon(boolean z) {
            setFlag(2, z);
            return this;
        }

        @Deprecated
        public WearableExtender setHintScreenTimeout(int i) {
            this.mHintScreenTimeout = i;
            return this;
        }

        @Deprecated
        public WearableExtender setHintShowBackgroundOnly(boolean z) {
            setFlag(4, z);
            return this;
        }

        public WearableExtender setStartScrollBottom(boolean z) {
            setFlag(8, z);
            return this;
        }
    }

    @RequiresApi(20)
    static Action a(Notification.Action action) {
        RemoteInput[] remoteInputArr;
        RemoteInput[] remoteInputs = action.getRemoteInputs();
        if (remoteInputs == null) {
            remoteInputArr = null;
        } else {
            RemoteInput[] remoteInputArr2 = new RemoteInput[remoteInputs.length];
            for (int i = 0; i < remoteInputs.length; i++) {
                RemoteInput remoteInput = remoteInputs[i];
                remoteInputArr2[i] = new RemoteInput(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras(), (Set<String>) null);
            }
            remoteInputArr = remoteInputArr2;
        }
        return new Action(action.icon, action.title, action.actionIntent, action.getExtras(), remoteInputArr, (RemoteInput[]) null, Build.VERSION.SDK_INT >= 24 ? action.getExtras().getBoolean("android.support.allowGeneratedReplies") || action.getAllowGeneratedReplies() : action.getExtras().getBoolean("android.support.allowGeneratedReplies"), Build.VERSION.SDK_INT >= 28 ? action.getSemanticAction() : action.getExtras().getInt("android.support.action.semanticAction", 0), action.getExtras().getBoolean("android.support.action.showsUserInterface", true));
    }

    static Notification[] a(Bundle bundle, String str) {
        Parcelable[] parcelableArray = bundle.getParcelableArray(str);
        if ((parcelableArray instanceof Notification[]) || parcelableArray == null) {
            return (Notification[]) parcelableArray;
        }
        Notification[] notificationArr = new Notification[parcelableArray.length];
        for (int i = 0; i < parcelableArray.length; i++) {
            notificationArr[i] = (Notification) parcelableArray[i];
        }
        bundle.putParcelableArray(str, notificationArr);
        return notificationArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.os.Bundle} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.core.app.NotificationCompat.Action getAction(android.app.Notification r3, int r4) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 20
            if (r0 < r1) goto L_0x000f
            android.app.Notification$Action[] r3 = r3.actions
            r3 = r3[r4]
            androidx.core.app.NotificationCompat$Action r3 = a(r3)
            return r3
        L_0x000f:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 19
            r2 = 0
            if (r0 < r1) goto L_0x0036
            android.app.Notification$Action[] r0 = r3.actions
            r0 = r0[r4]
            android.os.Bundle r3 = r3.extras
            java.lang.String r1 = "android.support.actionExtras"
            android.util.SparseArray r3 = r3.getSparseParcelableArray(r1)
            if (r3 == 0) goto L_0x002b
            java.lang.Object r3 = r3.get(r4)
            r2 = r3
            android.os.Bundle r2 = (android.os.Bundle) r2
        L_0x002b:
            int r3 = r0.icon
            java.lang.CharSequence r4 = r0.title
            android.app.PendingIntent r0 = r0.actionIntent
            androidx.core.app.NotificationCompat$Action r3 = androidx.core.app.NotificationCompatJellybean.readAction(r3, r4, r0, r2)
            return r3
        L_0x0036:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 16
            if (r0 < r1) goto L_0x0041
            androidx.core.app.NotificationCompat$Action r3 = androidx.core.app.NotificationCompatJellybean.getAction(r3, r4)
            return r3
        L_0x0041:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat.getAction(android.app.Notification, int):androidx.core.app.NotificationCompat$Action");
    }

    public static int getActionCount(Notification notification) {
        if (Build.VERSION.SDK_INT >= 19) {
            if (notification.actions != null) {
                return notification.actions.length;
            }
            return 0;
        } else if (Build.VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getActionCount(notification);
        } else {
            return 0;
        }
    }

    public static int getBadgeIconType(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getBadgeIconType();
        }
        return 0;
    }

    public static String getCategory(Notification notification) {
        if (Build.VERSION.SDK_INT >= 21) {
            return notification.category;
        }
        return null;
    }

    public static String getChannelId(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getChannelId();
        }
        return null;
    }

    @RequiresApi(19)
    public static CharSequence getContentTitle(Notification notification) {
        return notification.extras.getCharSequence(EXTRA_TITLE);
    }

    @Nullable
    public static Bundle getExtras(Notification notification) {
        if (Build.VERSION.SDK_INT >= 19) {
            return notification.extras;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(notification);
        }
        return null;
    }

    public static String getGroup(Notification notification) {
        Bundle extras;
        if (Build.VERSION.SDK_INT >= 20) {
            return notification.getGroup();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            extras = notification.extras;
        } else if (Build.VERSION.SDK_INT < 16) {
            return null;
        } else {
            extras = NotificationCompatJellybean.getExtras(notification);
        }
        return extras.getString(NotificationCompatExtras.EXTRA_GROUP_KEY);
    }

    public static int getGroupAlertBehavior(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getGroupAlertBehavior();
        }
        return 0;
    }

    @RequiresApi(21)
    public static List<Action> getInvisibleActions(Notification notification) {
        Bundle bundle;
        ArrayList arrayList = new ArrayList();
        Bundle bundle2 = notification.extras.getBundle("android.car.EXTENSIONS");
        if (!(bundle2 == null || (bundle = bundle2.getBundle("invisible_actions")) == null)) {
            for (int i = 0; i < bundle.size(); i++) {
                arrayList.add(NotificationCompatJellybean.a(bundle.getBundle(Integer.toString(i))));
            }
        }
        return arrayList;
    }

    public static boolean getLocalOnly(Notification notification) {
        Bundle extras;
        if (Build.VERSION.SDK_INT >= 20) {
            return (notification.flags & 256) != 0;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            extras = notification.extras;
        } else if (Build.VERSION.SDK_INT < 16) {
            return false;
        } else {
            extras = NotificationCompatJellybean.getExtras(notification);
        }
        return extras.getBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY);
    }

    public static String getShortcutId(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getShortcutId();
        }
        return null;
    }

    public static String getSortKey(Notification notification) {
        Bundle extras;
        if (Build.VERSION.SDK_INT >= 20) {
            return notification.getSortKey();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            extras = notification.extras;
        } else if (Build.VERSION.SDK_INT < 16) {
            return null;
        } else {
            extras = NotificationCompatJellybean.getExtras(notification);
        }
        return extras.getString(NotificationCompatExtras.EXTRA_SORT_KEY);
    }

    public static long getTimeoutAfter(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getTimeoutAfter();
        }
        return 0;
    }

    public static boolean isGroupSummary(Notification notification) {
        Bundle extras;
        if (Build.VERSION.SDK_INT >= 20) {
            return (notification.flags & 512) != 0;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            extras = notification.extras;
        } else if (Build.VERSION.SDK_INT < 16) {
            return false;
        } else {
            extras = NotificationCompatJellybean.getExtras(notification);
        }
        return extras.getBoolean(NotificationCompatExtras.EXTRA_GROUP_SUMMARY);
    }
}
