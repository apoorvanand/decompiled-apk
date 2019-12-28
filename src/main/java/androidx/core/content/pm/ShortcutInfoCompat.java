package androidx.core.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.drawable.IconCompat;
import java.util.Arrays;

public class ShortcutInfoCompat {
    Context a;
    String b;
    Intent[] c;
    ComponentName d;
    CharSequence e;
    CharSequence f;
    CharSequence g;
    IconCompat h;
    boolean i;

    public static class Builder {
        private final ShortcutInfoCompat mInfo = new ShortcutInfoCompat();

        public Builder(@NonNull Context context, @NonNull String str) {
            ShortcutInfoCompat shortcutInfoCompat = this.mInfo;
            shortcutInfoCompat.a = context;
            shortcutInfoCompat.b = str;
        }

        @NonNull
        public ShortcutInfoCompat build() {
            if (TextUtils.isEmpty(this.mInfo.e)) {
                throw new IllegalArgumentException("Shortcut must have a non-empty label");
            } else if (this.mInfo.c != null && this.mInfo.c.length != 0) {
                return this.mInfo;
            } else {
                throw new IllegalArgumentException("Shortcut must have an intent");
            }
        }

        @NonNull
        public Builder setActivity(@NonNull ComponentName componentName) {
            this.mInfo.d = componentName;
            return this;
        }

        public Builder setAlwaysBadged() {
            this.mInfo.i = true;
            return this;
        }

        @NonNull
        public Builder setDisabledMessage(@NonNull CharSequence charSequence) {
            this.mInfo.g = charSequence;
            return this;
        }

        @NonNull
        public Builder setIcon(IconCompat iconCompat) {
            this.mInfo.h = iconCompat;
            return this;
        }

        @NonNull
        public Builder setIntent(@NonNull Intent intent) {
            return setIntents(new Intent[]{intent});
        }

        @NonNull
        public Builder setIntents(@NonNull Intent[] intentArr) {
            this.mInfo.c = intentArr;
            return this;
        }

        @NonNull
        public Builder setLongLabel(@NonNull CharSequence charSequence) {
            this.mInfo.f = charSequence;
            return this;
        }

        @NonNull
        public Builder setShortLabel(@NonNull CharSequence charSequence) {
            this.mInfo.e = charSequence;
            return this;
        }
    }

    ShortcutInfoCompat() {
    }

    /* access modifiers changed from: package-private */
    public Intent a(Intent intent) {
        Intent[] intentArr = this.c;
        intent.putExtra("android.intent.extra.shortcut.INTENT", intentArr[intentArr.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.e.toString());
        if (this.h != null) {
            Drawable drawable = null;
            if (this.i) {
                PackageManager packageManager = this.a.getPackageManager();
                ComponentName componentName = this.d;
                if (componentName != null) {
                    try {
                        drawable = packageManager.getActivityIcon(componentName);
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                if (drawable == null) {
                    drawable = this.a.getApplicationInfo().loadIcon(packageManager);
                }
            }
            this.h.addToShortcutIntent(intent, drawable, this.a);
        }
        return intent;
    }

    @Nullable
    public ComponentName getActivity() {
        return this.d;
    }

    @Nullable
    public CharSequence getDisabledMessage() {
        return this.g;
    }

    @NonNull
    public String getId() {
        return this.b;
    }

    @NonNull
    public Intent getIntent() {
        Intent[] intentArr = this.c;
        return intentArr[intentArr.length - 1];
    }

    @NonNull
    public Intent[] getIntents() {
        Intent[] intentArr = this.c;
        return (Intent[]) Arrays.copyOf(intentArr, intentArr.length);
    }

    @Nullable
    public CharSequence getLongLabel() {
        return this.f;
    }

    @NonNull
    public CharSequence getShortLabel() {
        return this.e;
    }

    @RequiresApi(25)
    public ShortcutInfo toShortcutInfo() {
        ShortcutInfo.Builder intents = new ShortcutInfo.Builder(this.a, this.b).setShortLabel(this.e).setIntents(this.c);
        IconCompat iconCompat = this.h;
        if (iconCompat != null) {
            intents.setIcon(iconCompat.toIcon());
        }
        if (!TextUtils.isEmpty(this.f)) {
            intents.setLongLabel(this.f);
        }
        if (!TextUtils.isEmpty(this.g)) {
            intents.setDisabledMessage(this.g);
        }
        ComponentName componentName = this.d;
        if (componentName != null) {
            intents.setActivity(componentName);
        }
        return intents.build();
    }
}
