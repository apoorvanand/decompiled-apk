package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "EmailAuthCredentialCreator")
public class EmailAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<EmailAuthCredential> CREATOR = new zzg();
    @SafeParcelable.Field(getter = "getEmail", id = 1)
    private String zzif;
    @SafeParcelable.Field(getter = "getPassword", id = 2)
    private String zzig;
    @SafeParcelable.Field(getter = "getSignInLink", id = 3)
    private final String zzih;
    @SafeParcelable.Field(getter = "getCachedState", id = 4)
    private String zzii;
    @SafeParcelable.Field(getter = "isForLinking", id = 5)
    private boolean zzij;

    EmailAuthCredential(String str, String str2) {
        this(str, str2, (String) null, (String) null, false);
    }

    @SafeParcelable.Constructor
    EmailAuthCredential(@SafeParcelable.Param(id = 1) @NonNull String str, @SafeParcelable.Param(id = 2) @NonNull String str2, @SafeParcelable.Param(id = 3) @NonNull String str3, @SafeParcelable.Param(id = 4) @NonNull String str4, @SafeParcelable.Param(id = 5) @NonNull boolean z) {
        this.zzif = Preconditions.checkNotEmpty(str);
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            this.zzig = str2;
            this.zzih = str3;
            this.zzii = str4;
            this.zzij = z;
            return;
        }
        throw new IllegalArgumentException("Cannot create an EmailAuthCredential without a password or emailLink.");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r2 = com.google.firebase.auth.zzb.zzbr(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isSignInWithEmailLink(@androidx.annotation.NonNull java.lang.String r2) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            com.google.firebase.auth.zzb r2 = com.google.firebase.auth.zzb.zzbr(r2)
            if (r2 == 0) goto L_0x0017
            int r2 = r2.getOperation()
            r0 = 4
            if (r2 != r0) goto L_0x0017
            r2 = 1
            return r2
        L_0x0017:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.EmailAuthCredential.isSignInWithEmailLink(java.lang.String):boolean");
    }

    @NonNull
    public final String getEmail() {
        return this.zzif;
    }

    @NonNull
    public final String getPassword() {
        return this.zzig;
    }

    @NonNull
    public String getProvider() {
        return "password";
    }

    public String getSignInMethod() {
        return !TextUtils.isEmpty(this.zzig) ? "password" : EmailAuthProvider.EMAIL_LINK_SIGN_IN_METHOD;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzif, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzig, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzih, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzii, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzij);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final EmailAuthCredential zza(@Nullable FirebaseUser firebaseUser) {
        this.zzii = firebaseUser.zzcz();
        this.zzij = true;
        return this;
    }

    @NonNull
    public final String zzco() {
        return this.zzih;
    }

    @Nullable
    public final String zzcp() {
        return this.zzii;
    }

    public final boolean zzcq() {
        return this.zzij;
    }

    public final boolean zzcr() {
        return !TextUtils.isEmpty(this.zzih);
    }
}
