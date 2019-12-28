package com.google.firebase.auth.api.internal;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzem;
import com.google.android.gms.internal.firebase_auth.zzew;
import com.google.android.gms.internal.firebase_auth.zzf;
import com.google.android.gms.internal.firebase_auth.zzfe;
import com.google.android.gms.internal.firebase_auth.zzfw;
import com.google.android.gms.internal.firebase_auth.zzk;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzaa;
import com.google.firebase.auth.internal.zzax;
import com.google.firebase.auth.internal.zzi;
import com.google.firebase.auth.internal.zzm;
import com.google.firebase.auth.internal.zzo;
import com.google.firebase.auth.internal.zzz;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public final class zzap extends zzag {
    private final Context zzml;
    private final zzee zzmm;
    private final Future<zzaj<zzee>> zzmn = zzdq();

    zzap(Context context, zzee zzee) {
        this.zzml = context;
        this.zzmm = zzee;
    }

    @NonNull
    @VisibleForTesting
    private final <ResultT> Task<ResultT> zza(Task<ResultT> task, zzan<zzdp, ResultT> zzan) {
        return task.continueWithTask(new zzao(this, zzan));
    }

    @NonNull
    @VisibleForTesting
    static zzm zza(FirebaseApp firebaseApp, zzem zzem) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(zzem);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new zzi(zzem, FirebaseAuthProvider.PROVIDER_ID));
        List<zzew> zzes = zzem.zzes();
        if (zzes != null && !zzes.isEmpty()) {
            for (int i = 0; i < zzes.size(); i++) {
                arrayList.add(new zzi(zzes.get(i)));
            }
        }
        zzm zzm = new zzm(firebaseApp, arrayList);
        zzm.zza(new zzo(zzem.getLastSignInTimestamp(), zzem.getCreationTimestamp()));
        zzm.zzs(zzem.isNewUser());
        zzm.zzb(zzem.zzdo());
        zzm.zzb(com.google.firebase.auth.internal.zzap.zzg(zzem.zzbc()));
        return zzm;
    }

    @NonNull
    public final Task<Void> setFirebaseUIVersion(String str) {
        zzcj zzcj = new zzcj(str);
        return zza(zzb(zzcj), zzcj);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, @Nullable ActionCodeSettings actionCodeSettings, String str) {
        zzcf zzcf = (zzcf) new zzcf(str, actionCodeSettings).zza(firebaseApp);
        return zza(zzb(zzcf), zzcf);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, AuthCredential authCredential, @Nullable String str, zza zza) {
        zzcn zzcn = (zzcn) new zzcn(authCredential, str).zza(firebaseApp).zzb(zza);
        return zza(zzb(zzcn), zzcn);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, EmailAuthCredential emailAuthCredential, zza zza) {
        zzct zzct = (zzct) new zzct(emailAuthCredential).zza(firebaseApp).zzb(zza);
        return zza(zzb(zzct), zzct);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, zzax zzax) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzax);
        List<String> zzcw = firebaseUser.zzcw();
        if (zzcw != null && zzcw.contains(authCredential.getProvider())) {
            return Tasks.forException(zzdr.zzb(new Status(FirebaseError.ERROR_PROVIDER_ALREADY_LINKED)));
        }
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            if (!emailAuthCredential.zzcr()) {
                zzbf zzbf = (zzbf) new zzbf(emailAuthCredential).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
                return zza(zzb(zzbf), zzbf);
            }
            zzbl zzbl = (zzbl) new zzbl(emailAuthCredential).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
            return zza(zzb(zzbl), zzbl);
        } else if (authCredential instanceof PhoneAuthCredential) {
            zzbj zzbj = (zzbj) new zzbj((PhoneAuthCredential) authCredential).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
            return zza(zzb(zzbj), zzbj);
        } else {
            Preconditions.checkNotNull(firebaseApp);
            Preconditions.checkNotNull(authCredential);
            Preconditions.checkNotNull(firebaseUser);
            Preconditions.checkNotNull(zzax);
            zzbh zzbh = (zzbh) new zzbh(authCredential).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
            return zza(zzb(zzbh), zzbh);
        }
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, @Nullable String str, zzax zzax) {
        zzbn zzbn = (zzbn) new zzbn(authCredential, str).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zzb(zzbn), zzbn);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, zzax zzax) {
        zzbr zzbr = (zzbr) new zzbr(emailAuthCredential).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zzb(zzbr), zzbr);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, zzax zzax) {
        zzdf zzdf = (zzdf) new zzdf(phoneAuthCredential).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zzb(zzdf), zzdf);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, @Nullable String str, zzax zzax) {
        zzbz zzbz = (zzbz) new zzbz(phoneAuthCredential, str).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zzb(zzbz), zzbz);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, UserProfileChangeRequest userProfileChangeRequest, zzax zzax) {
        zzdh zzdh = (zzdh) new zzdh(userProfileChangeRequest).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zzb(zzdh), zzdh);
    }

    @NonNull
    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, zzax zzax) {
        zzcd zzcd = (zzcd) new zzcd().zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zza(zzcd), zzcd);
    }

    public final Task<GetTokenResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzax zzax) {
        zzbd zzbd = (zzbd) new zzbd(str).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zza(zzbd), zzbd);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, @Nullable String str3, zzax zzax) {
        zzbv zzbv = (zzbv) new zzbv(str, str2, str3).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zzb(zzbv), zzbv);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, PhoneAuthCredential phoneAuthCredential, @Nullable String str, zza zza) {
        zzcv zzcv = (zzcv) new zzcv(phoneAuthCredential, str).zza(firebaseApp).zzb(zza);
        return zza(zzb(zzcv), zzcv);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, zza zza, @Nullable String str) {
        zzcl zzcl = (zzcl) new zzcl(str).zza(firebaseApp).zzb(zza);
        return zza(zzb(zzcl), zzcl);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, @Nullable String str2) {
        actionCodeSettings.zzb(zzfw.PASSWORD_RESET);
        zzch zzch = (zzch) new zzch(str, actionCodeSettings, str2, "sendPasswordResetEmail").zza(firebaseApp);
        return zza(zzb(zzch), zzch);
    }

    public final Task<SignInMethodQueryResult> zza(FirebaseApp firebaseApp, String str, @Nullable String str2) {
        zzbb zzbb = (zzbb) new zzbb(str, str2).zza(firebaseApp);
        return zza(zza(zzbb), zzbb);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, String str, @Nullable String str2, zza zza) {
        zzcp zzcp = (zzcp) new zzcp(str, str2).zza(firebaseApp).zzb(zza);
        return zza(zzb(zzcp), zzcp);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, String str, String str2, @Nullable String str3) {
        zzav zzav = (zzav) new zzav(str, str2, str3).zza(firebaseApp);
        return zza(zzb(zzav), zzav);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, String str, String str2, String str3, zza zza) {
        zzax zzax = (zzax) new zzax(str, str2, str3).zza(firebaseApp).zzb(zza);
        return zza(zzb(zzax), zzax);
    }

    @NonNull
    public final Task<Void> zza(FirebaseUser firebaseUser, zzaa zzaa) {
        zzaz zzaz = (zzaz) new zzaz().zzf(firebaseUser).zzb(zzaa).zza((zzz) zzaa);
        return zza(zzb(zzaz), zzaz);
    }

    public final void zza(FirebaseApp firebaseApp, zzfe zzfe, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, @Nullable Activity activity, Executor executor) {
        zzdl zzdl = (zzdl) new zzdl(zzfe).zza(firebaseApp).zza(onVerificationStateChangedCallbacks, activity, executor);
        zza(zzb(zzdl), zzdl);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, @Nullable String str, zzax zzax) {
        zzbp zzbp = (zzbp) new zzbp(authCredential, str).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zzb(zzbp), zzbp);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, zzax zzax) {
        zzbt zzbt = (zzbt) new zzbt(emailAuthCredential).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zzb(zzbt), zzbt);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, @Nullable String str, zzax zzax) {
        zzcb zzcb = (zzcb) new zzcb(phoneAuthCredential, str).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zzb(zzcb), zzcb);
    }

    public final Task<Void> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzax zzax) {
        zzdb zzdb = (zzdb) new zzdb(str).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zzb(zzdb), zzdb);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, zzax zzax) {
        zzbx zzbx = (zzbx) new zzbx(str, str2, str3).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zzb(zzbx), zzbx);
    }

    public final Task<Void> zzb(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, @Nullable String str2) {
        actionCodeSettings.zzb(zzfw.EMAIL_SIGNIN);
        zzch zzch = (zzch) new zzch(str, actionCodeSettings, str2, "sendSignInLinkToEmail").zza(firebaseApp);
        return zza(zzb(zzch), zzch);
    }

    public final Task<ActionCodeResult> zzb(FirebaseApp firebaseApp, String str, @Nullable String str2) {
        zzat zzat = (zzat) new zzat(str, str2).zza(firebaseApp);
        return zza(zzb(zzat), zzat);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, String str, String str2, @Nullable String str3, zza zza) {
        zzcr zzcr = (zzcr) new zzcr(str, str2, str3).zza(firebaseApp).zzb(zza);
        return zza(zzb(zzcr), zzcr);
    }

    public final Task<Void> zzc(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzax zzax) {
        zzdd zzdd = (zzdd) new zzdd(str).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zzb(zzdd), zzdd);
    }

    public final Task<Void> zzc(FirebaseApp firebaseApp, String str, @Nullable String str2) {
        zzar zzar = (zzar) new zzar(str, str2).zza(firebaseApp);
        return zza(zzb(zzar), zzar);
    }

    public final Task<AuthResult> zzd(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzax zzax) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzax);
        List<String> zzcw = firebaseUser.zzcw();
        if ((zzcw != null && !zzcw.contains(str)) || firebaseUser.isAnonymous()) {
            return Tasks.forException(zzdr.zzb(new Status(FirebaseError.ERROR_NO_SUCH_PROVIDER, str)));
        }
        char c = 65535;
        if (str.hashCode() == 1216985755 && str.equals("password")) {
            c = 0;
        }
        if (c != 0) {
            zzcz zzcz = (zzcz) new zzcz(str).zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
            return zza(zzb(zzcz), zzcz);
        }
        zzcx zzcx = (zzcx) new zzcx().zza(firebaseApp).zzf(firebaseUser).zzb(zzax).zza((zzz) zzax);
        return zza(zzb(zzcx), zzcx);
    }

    public final Task<String> zzd(FirebaseApp firebaseApp, String str, @Nullable String str2) {
        zzdj zzdj = (zzdj) new zzdj(str, str2).zza(firebaseApp);
        return zza(zzb(zzdj), zzdj);
    }

    /* access modifiers changed from: package-private */
    public final Future<zzaj<zzee>> zzdq() {
        Future<zzaj<zzee>> future = this.zzmn;
        if (future != null) {
            return future;
        }
        return zzf.zzb().zza(zzk.zzm).submit(new zzdn(this.zzmm, this.zzml));
    }
}
