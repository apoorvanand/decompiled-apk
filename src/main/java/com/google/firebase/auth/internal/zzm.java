package com.google.firebase.auth.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzay;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.zzf;
import com.google.firebase.auth.zzv;
import com.google.firebase.auth.zzx;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SafeParcelable.Class(creator = "DefaultFirebaseUserCreator")
public class zzm extends FirebaseUser {
    public static final Parcelable.Creator<zzm> CREATOR = new zzl();
    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", id = 11)
    private zzf zzkw;
    @SafeParcelable.Field(getter = "isNewUser", id = 10)
    private boolean zzrg;
    @SafeParcelable.Field(getter = "getCachedTokenState", id = 1)
    private zzes zzth;
    @SafeParcelable.Field(getter = "getDefaultAuthUserInfo", id = 2)
    private zzi zzti;
    @SafeParcelable.Field(getter = "getFirebaseAppName", id = 3)
    private String zztj;
    @SafeParcelable.Field(getter = "getUserType", id = 4)
    private String zztk;
    @SafeParcelable.Field(getter = "getUserInfos", id = 5)
    private List<zzi> zztl;
    @SafeParcelable.Field(getter = "getProviders", id = 6)
    private List<String> zztm;
    @SafeParcelable.Field(getter = "getCurrentVersion", id = 7)
    private String zztn;
    @SafeParcelable.Field(getter = "isAnonymous", id = 8)
    private Boolean zzto;
    @SafeParcelable.Field(getter = "getMetadata", id = 9)
    private zzo zztp;
    @SafeParcelable.Field(getter = "getMultiFactorInfoList", id = 12)
    private zzao zztq;

    @SafeParcelable.Constructor
    zzm(@SafeParcelable.Param(id = 1) zzes zzes, @SafeParcelable.Param(id = 2) zzi zzi, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) List<zzi> list, @SafeParcelable.Param(id = 6) List<String> list2, @SafeParcelable.Param(id = 7) String str3, @SafeParcelable.Param(id = 8) Boolean bool, @SafeParcelable.Param(id = 9) zzo zzo, @SafeParcelable.Param(id = 10) boolean z, @SafeParcelable.Param(id = 11) zzf zzf, @SafeParcelable.Param(id = 12) zzao zzao) {
        this.zzth = zzes;
        this.zzti = zzi;
        this.zztj = str;
        this.zztk = str2;
        this.zztl = list;
        this.zztm = list2;
        this.zztn = str3;
        this.zzto = bool;
        this.zztp = zzo;
        this.zzrg = z;
        this.zzkw = zzf;
        this.zztq = zzao;
    }

    public zzm(FirebaseApp firebaseApp, List<? extends UserInfo> list) {
        Preconditions.checkNotNull(firebaseApp);
        this.zztj = firebaseApp.getName();
        this.zztk = "com.google.firebase.auth.internal.DefaultFirebaseUser";
        this.zztn = ExifInterface.GPS_MEASUREMENT_2D;
        zza(list);
    }

    public static FirebaseUser zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser) {
        zzo zzo;
        zzm zzm = new zzm(firebaseApp, firebaseUser.getProviderData());
        if (firebaseUser instanceof zzm) {
            zzm zzm2 = (zzm) firebaseUser;
            zzm.zztn = zzm2.zztn;
            zzm.zztk = zzm2.zztk;
            zzo = (zzo) zzm2.getMetadata();
        } else {
            zzo = null;
        }
        zzm.zztp = zzo;
        if (firebaseUser.zzcy() != null) {
            zzm.zza(firebaseUser.zzcy());
        }
        if (!firebaseUser.isAnonymous()) {
            zzm.zzcx();
        }
        return zzm;
    }

    @Nullable
    public String getDisplayName() {
        return this.zzti.getDisplayName();
    }

    @Nullable
    public String getEmail() {
        return this.zzti.getEmail();
    }

    public FirebaseUserMetadata getMetadata() {
        return this.zztp;
    }

    @Nullable
    public String getPhoneNumber() {
        return this.zzti.getPhoneNumber();
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.zzti.getPhotoUrl();
    }

    @NonNull
    public List<? extends UserInfo> getProviderData() {
        return this.zztl;
    }

    @NonNull
    public String getProviderId() {
        return this.zzti.getProviderId();
    }

    @NonNull
    public String getUid() {
        return this.zzti.getUid();
    }

    public boolean isAnonymous() {
        Boolean bool = this.zzto;
        if (bool == null || bool.booleanValue()) {
            String str = "";
            zzes zzes = this.zzth;
            if (zzes != null) {
                GetTokenResult zzdf = zzan.zzdf(zzes.getAccessToken());
                str = zzdf != null ? zzdf.getSignInProvider() : "";
            }
            boolean z = true;
            if (getProviderData().size() > 1 || (str != null && str.equals("custom"))) {
                z = false;
            }
            this.zzto = Boolean.valueOf(z);
        }
        return this.zzto.booleanValue();
    }

    public boolean isEmailVerified() {
        return this.zzti.isEmailVerified();
    }

    public final boolean isNewUser() {
        return this.zzrg;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, zzcy(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzti, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zztj, false);
        SafeParcelWriter.writeString(parcel, 4, this.zztk, false);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zztl, false);
        SafeParcelWriter.writeStringList(parcel, 6, zzcw(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zztn, false);
        SafeParcelWriter.writeBooleanObject(parcel, 8, Boolean.valueOf(isAnonymous()), false);
        SafeParcelWriter.writeParcelable(parcel, 9, getMetadata(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzrg);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzkw, i, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zztq, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final FirebaseUser zza(List<? extends UserInfo> list) {
        Preconditions.checkNotNull(list);
        this.zztl = new ArrayList(list.size());
        this.zztm = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            UserInfo userInfo = (UserInfo) list.get(i);
            if (userInfo.getProviderId().equals(FirebaseAuthProvider.PROVIDER_ID)) {
                this.zzti = (zzi) userInfo;
            } else {
                this.zztm.add(userInfo.getProviderId());
            }
            this.zztl.add((zzi) userInfo);
        }
        if (this.zzti == null) {
            this.zzti = this.zztl.get(0);
        }
        return this;
    }

    public final void zza(zzes zzes) {
        this.zzth = (zzes) Preconditions.checkNotNull(zzes);
    }

    public final void zza(zzo zzo) {
        this.zztp = zzo;
    }

    public final void zzb(zzf zzf) {
        this.zzkw = zzf;
    }

    public final void zzb(List<zzx> list) {
        this.zztq = zzao.zzf(list);
    }

    @Nullable
    public final String zzba() {
        Map map;
        zzes zzes = this.zzth;
        if (zzes == null || zzes.getAccessToken() == null || (map = (Map) zzan.zzdf(this.zzth.getAccessToken()).getClaims().get(FirebaseAuthProvider.PROVIDER_ID)) == null) {
            return null;
        }
        return (String) map.get("tenant");
    }

    @NonNull
    public final FirebaseApp zzcu() {
        return FirebaseApp.getInstance(this.zztj);
    }

    @Nullable
    public final List<String> zzcw() {
        return this.zztm;
    }

    public final /* synthetic */ FirebaseUser zzcx() {
        this.zzto = false;
        return this;
    }

    @NonNull
    public final zzes zzcy() {
        return this.zzth;
    }

    @NonNull
    public final String zzcz() {
        return this.zzth.zzew();
    }

    @NonNull
    public final String zzda() {
        return zzcy().getAccessToken();
    }

    public final zzm zzdb(String str) {
        this.zztn = str;
        return this;
    }

    public final /* synthetic */ zzv zzdb() {
        return new zzq(this);
    }

    @Nullable
    public final List<zzx> zzdc() {
        zzao zzao = this.zztq;
        return zzao != null ? zzao.zzdp() : zzay.zzce();
    }

    @Nullable
    public final zzf zzdo() {
        return this.zzkw;
    }

    public final List<zzi> zzff() {
        return this.zztl;
    }

    public final void zzs(boolean z) {
        this.zzrg = z;
    }
}
