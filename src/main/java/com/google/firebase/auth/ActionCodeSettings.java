package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzfw;

@SafeParcelable.Class(creator = "ActionCodeSettingsCreator")
public class ActionCodeSettings extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ActionCodeSettings> CREATOR = new zzc();
    @SafeParcelable.Field(getter = "getUrl", id = 1)
    private final String url;
    @SafeParcelable.Field(getter = "getIOSBundle", id = 2)
    private final String zzhk;
    @SafeParcelable.Field(getter = "getIOSAppStoreId", id = 3)
    private final String zzhl;
    @SafeParcelable.Field(getter = "getAndroidPackageName", id = 4)
    private final String zzhm;
    @SafeParcelable.Field(getter = "getAndroidInstallApp", id = 5)
    private final boolean zzhn;
    @SafeParcelable.Field(getter = "getAndroidMinimumVersion", id = 6)
    private final String zzho;
    @SafeParcelable.Field(getter = "canHandleCodeInApp", id = 7)
    private final boolean zzhp;
    @SafeParcelable.Field(getter = "getLocaleHeader", id = 8)
    private String zzhq;
    @SafeParcelable.Field(getter = "getRequestType", id = 9)
    private int zzhr;
    @SafeParcelable.Field(getter = "getDynamicLinkDomain", id = 10)
    private String zzhs;

    public static class Builder {
        /* access modifiers changed from: private */
        public String url;
        /* access modifiers changed from: private */
        public String zzhk;
        /* access modifiers changed from: private */
        public String zzhm;
        /* access modifiers changed from: private */
        public boolean zzhn;
        /* access modifiers changed from: private */
        public String zzho;
        /* access modifiers changed from: private */
        public boolean zzhp;
        /* access modifiers changed from: private */
        public String zzhs;

        private Builder() {
            this.zzhp = false;
        }

        public ActionCodeSettings build() {
            if (this.url != null) {
                return new ActionCodeSettings(this);
            }
            throw new IllegalArgumentException("Cannot build ActionCodeSettings with null URL. Call #setUrl(String) before calling build()");
        }

        public Builder setAndroidPackageName(@NonNull String str, boolean z, @Nullable String str2) {
            this.zzhm = str;
            this.zzhn = z;
            this.zzho = str2;
            return this;
        }

        public Builder setDynamicLinkDomain(String str) {
            this.zzhs = str;
            return this;
        }

        public Builder setHandleCodeInApp(boolean z) {
            this.zzhp = z;
            return this;
        }

        public Builder setIOSBundleId(@NonNull String str) {
            this.zzhk = str;
            return this;
        }

        public Builder setUrl(@NonNull String str) {
            this.url = str;
            return this;
        }
    }

    private ActionCodeSettings(Builder builder) {
        this.url = builder.url;
        this.zzhk = builder.zzhk;
        this.zzhl = null;
        this.zzhm = builder.zzhm;
        this.zzhn = builder.zzhn;
        this.zzho = builder.zzho;
        this.zzhp = builder.zzhp;
        this.zzhs = builder.zzhs;
    }

    @SafeParcelable.Constructor
    ActionCodeSettings(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) String str3, @SafeParcelable.Param(id = 4) String str4, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 6) String str5, @SafeParcelable.Param(id = 7) boolean z2, @SafeParcelable.Param(id = 8) String str6, @SafeParcelable.Param(id = 9) int i, @SafeParcelable.Param(id = 10) String str7) {
        this.url = str;
        this.zzhk = str2;
        this.zzhl = str3;
        this.zzhm = str4;
        this.zzhn = z;
        this.zzho = str5;
        this.zzhp = z2;
        this.zzhq = str6;
        this.zzhr = i;
        this.zzhs = str7;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static ActionCodeSettings zzcj() {
        return new ActionCodeSettings(new Builder());
    }

    public boolean canHandleCodeInApp() {
        return this.zzhp;
    }

    public boolean getAndroidInstallApp() {
        return this.zzhn;
    }

    public String getAndroidMinimumVersion() {
        return this.zzho;
    }

    public String getAndroidPackageName() {
        return this.zzhm;
    }

    public String getIOSBundle() {
        return this.zzhk;
    }

    public final int getRequestType() {
        return this.zzhr;
    }

    public String getUrl() {
        return this.url;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getUrl(), false);
        SafeParcelWriter.writeString(parcel, 2, getIOSBundle(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzhl, false);
        SafeParcelWriter.writeString(parcel, 4, getAndroidPackageName(), false);
        SafeParcelWriter.writeBoolean(parcel, 5, getAndroidInstallApp());
        SafeParcelWriter.writeString(parcel, 6, getAndroidMinimumVersion(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, canHandleCodeInApp());
        SafeParcelWriter.writeString(parcel, 8, this.zzhq, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzhr);
        SafeParcelWriter.writeString(parcel, 10, this.zzhs, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final void zzb(@NonNull zzfw zzfw) {
        this.zzhr = zzfw.zzbq();
    }

    public final void zzbq(@NonNull String str) {
        this.zzhq = str;
    }

    public final String zzck() {
        return this.zzhl;
    }

    public final String zzcl() {
        return this.zzhq;
    }

    public final String zzcm() {
        return this.zzhs;
    }
}
