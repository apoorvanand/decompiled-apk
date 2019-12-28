package com.google.firebase.auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "UserProfileChangeRequestCreator")
@SafeParcelable.Reserved({1})
public class UserProfileChangeRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<UserProfileChangeRequest> CREATOR = new zzaf();
    @SafeParcelable.Field(getter = "getDisplayName", id = 2)
    private String zzjv;
    private Uri zzjz;
    @SafeParcelable.Field(getter = "shouldRemoveDisplayName", id = 4)
    private boolean zzka;
    @SafeParcelable.Field(getter = "shouldRemovePhotoUri", id = 5)
    private boolean zzkb;
    @SafeParcelable.Field(getter = "getPhotoUrl", id = 3)
    private String zzkc;

    public static class Builder {
        private String zzjv;
        private Uri zzjz;
        private boolean zzka;
        private boolean zzkb;

        public UserProfileChangeRequest build() {
            String str = this.zzjv;
            Uri uri = this.zzjz;
            return new UserProfileChangeRequest(str, uri == null ? null : uri.toString(), this.zzka, this.zzkb);
        }

        public Builder setDisplayName(@Nullable String str) {
            if (str == null) {
                this.zzka = true;
            } else {
                this.zzjv = str;
            }
            return this;
        }

        public Builder setPhotoUri(@Nullable Uri uri) {
            if (uri == null) {
                this.zzkb = true;
            } else {
                this.zzjz = uri;
            }
            return this;
        }
    }

    @SafeParcelable.Constructor
    UserProfileChangeRequest(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) boolean z2) {
        this.zzjv = str;
        this.zzkc = str2;
        this.zzka = z;
        this.zzkb = z2;
        this.zzjz = TextUtils.isEmpty(str2) ? null : Uri.parse(str2);
    }

    @Nullable
    public String getDisplayName() {
        return this.zzjv;
    }

    @Nullable
    public Uri getPhotoUri() {
        return this.zzjz;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzkc, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzka);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzkb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzam() {
        return this.zzkc;
    }

    public final boolean zzde() {
        return this.zzka;
    }

    public final boolean zzdf() {
        return this.zzkb;
    }
}
