package com.google.firebase.auth;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.android.gms.internal.firebase_auth.zzfe;
import com.google.android.gms.internal.firebase_auth.zzfw;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.api.internal.zzap;
import com.google.firebase.auth.api.internal.zzdr;
import com.google.firebase.auth.api.internal.zzec;
import com.google.firebase.auth.api.internal.zzed;
import com.google.firebase.auth.api.internal.zzem;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.auth.internal.zzaa;
import com.google.firebase.auth.internal.zzak;
import com.google.firebase.auth.internal.zzan;
import com.google.firebase.auth.internal.zzaq;
import com.google.firebase.auth.internal.zzas;
import com.google.firebase.auth.internal.zzat;
import com.google.firebase.auth.internal.zzau;
import com.google.firebase.auth.internal.zzax;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzj;
import com.google.firebase.auth.internal.zzm;
import com.google.firebase.auth.internal.zzz;
import com.google.firebase.internal.InternalTokenResult;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class FirebaseAuth implements InternalAuthProvider {
    private String zzhx;
    private String zzhy;
    private FirebaseApp zzik;
    /* access modifiers changed from: private */
    public final List<IdTokenListener> zzil;
    /* access modifiers changed from: private */
    public final List<com.google.firebase.auth.internal.IdTokenListener> zzim;
    /* access modifiers changed from: private */
    public List<AuthStateListener> zzin;
    private zzap zzio;
    /* access modifiers changed from: private */
    public FirebaseUser zzip;
    /* access modifiers changed from: private */
    public zzj zziq;
    private final Object zzir;
    private final Object zzis;
    private final zzat zzit;
    private final zzak zziu;
    private zzas zziv;
    private zzau zziw;

    public interface AuthStateListener {
        void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth);
    }

    public interface IdTokenListener {
        void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth);
    }

    @VisibleForTesting
    class zza extends zzb implements com.google.firebase.auth.internal.zza, zzz {
        zza() {
            super();
        }

        public final void zza(Status status) {
            if (status.getStatusCode() == 17011 || status.getStatusCode() == 17021 || status.getStatusCode() == 17005) {
                FirebaseAuth.this.signOut();
            }
        }
    }

    @VisibleForTesting
    class zzb implements com.google.firebase.auth.internal.zza {
        zzb() {
        }

        public final void zza(@NonNull zzes zzes, @NonNull FirebaseUser firebaseUser) {
            Preconditions.checkNotNull(zzes);
            Preconditions.checkNotNull(firebaseUser);
            firebaseUser.zza(zzes);
            FirebaseAuth.this.zza(firebaseUser, zzes, true);
        }
    }

    class zzc extends zzb implements com.google.firebase.auth.internal.zza, zzz {
        zzc(FirebaseAuth firebaseAuth) {
            super();
        }

        public final void zza(Status status) {
        }
    }

    public FirebaseAuth(FirebaseApp firebaseApp) {
        this(firebaseApp, zzec.zza(firebaseApp.getApplicationContext(), new zzed(firebaseApp.getOptions().getApiKey()).zzef()), new zzat(firebaseApp.getApplicationContext(), firebaseApp.getPersistenceKey()), zzak.zzfn());
    }

    @VisibleForTesting
    private FirebaseAuth(FirebaseApp firebaseApp, zzap zzap, zzat zzat, zzak zzak) {
        zzes zzh;
        this.zzir = new Object();
        this.zzis = new Object();
        this.zzik = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        this.zzio = (zzap) Preconditions.checkNotNull(zzap);
        this.zzit = (zzat) Preconditions.checkNotNull(zzat);
        this.zziq = new zzj();
        this.zziu = (zzak) Preconditions.checkNotNull(zzak);
        this.zzil = new CopyOnWriteArrayList();
        this.zzim = new CopyOnWriteArrayList();
        this.zzin = new CopyOnWriteArrayList();
        this.zziw = zzau.zzfs();
        this.zzip = this.zzit.zzfr();
        FirebaseUser firebaseUser = this.zzip;
        if (!(firebaseUser == null || (zzh = this.zzit.zzh(firebaseUser)) == null)) {
            zza(this.zzip, zzh, false);
        }
        this.zziu.zzf(this);
    }

    @Keep
    public static FirebaseAuth getInstance() {
        return (FirebaseAuth) FirebaseApp.getInstance().get(FirebaseAuth.class);
    }

    @Keep
    public static FirebaseAuth getInstance(@NonNull FirebaseApp firebaseApp) {
        return (FirebaseAuth) firebaseApp.get(FirebaseAuth.class);
    }

    @NonNull
    private final Task<Void> zza(@NonNull FirebaseUser firebaseUser, zzax zzax) {
        Preconditions.checkNotNull(firebaseUser);
        return this.zzio.zza(this.zzik, firebaseUser, zzax);
    }

    @VisibleForTesting
    private final synchronized void zza(zzas zzas) {
        this.zziv = zzas;
    }

    private final void zzb(@Nullable FirebaseUser firebaseUser) {
        String str;
        String str2;
        if (firebaseUser != null) {
            str2 = "FirebaseAuth";
            String uid = firebaseUser.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(uid).length() + 45);
            sb.append("Notifying id token listeners about user ( ");
            sb.append(uid);
            sb.append(" ).");
            str = sb.toString();
        } else {
            str2 = "FirebaseAuth";
            str = "Notifying id token listeners about a sign-out event.";
        }
        Log.d(str2, str);
        this.zziw.execute(new zzl(this, new InternalTokenResult(firebaseUser != null ? firebaseUser.zzda() : null)));
    }

    private final boolean zzbs(String str) {
        zzb zzbr = zzb.zzbr(str);
        return zzbr != null && !TextUtils.equals(this.zzhy, zzbr.zzba());
    }

    private final void zzc(@Nullable FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(uid).length() + 47);
            sb.append("Notifying auth state listeners about user ( ");
            sb.append(uid);
            sb.append(" ).");
            Log.d("FirebaseAuth", sb.toString());
        } else {
            Log.d("FirebaseAuth", "Notifying auth state listeners about a sign-out event.");
        }
        this.zziw.execute(new zzk(this));
    }

    @VisibleForTesting
    private final synchronized zzas zzct() {
        if (this.zziv == null) {
            zza(new zzas(this.zzik));
        }
        return this.zziv;
    }

    public void addAuthStateListener(@NonNull AuthStateListener authStateListener) {
        this.zzin.add(authStateListener);
        this.zziw.execute(new zzi(this, authStateListener));
    }

    public void addIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        this.zzil.add(idTokenListener);
        this.zziw.execute(new zzj(this, idTokenListener));
    }

    @KeepForSdk
    public void addIdTokenListener(@NonNull com.google.firebase.auth.internal.IdTokenListener idTokenListener) {
        Preconditions.checkNotNull(idTokenListener);
        this.zzim.add(idTokenListener);
        zzct().zzj(this.zzim.size());
    }

    @NonNull
    public Task<Void> applyActionCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zzio.zzc(this.zzik, str, this.zzhy);
    }

    @NonNull
    public Task<ActionCodeResult> checkActionCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zzio.zzb(this.zzik, str, this.zzhy);
    }

    @NonNull
    public Task<Void> confirmPasswordReset(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return this.zzio.zza(this.zzik, str, str2, this.zzhy);
    }

    @NonNull
    public Task<AuthResult> createUserWithEmailAndPassword(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return this.zzio.zza(this.zzik, str, str2, this.zzhy, (com.google.firebase.auth.internal.zza) new zzb());
    }

    @NonNull
    public Task<SignInMethodQueryResult> fetchSignInMethodsForEmail(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zzio.zza(this.zzik, str, this.zzhy);
    }

    @NonNull
    public Task<GetTokenResult> getAccessToken(boolean z) {
        return zza(this.zzip, z);
    }

    public FirebaseApp getApp() {
        return this.zzik;
    }

    @Nullable
    public FirebaseUser getCurrentUser() {
        return this.zzip;
    }

    public FirebaseAuthSettings getFirebaseAuthSettings() {
        return this.zziq;
    }

    @Nullable
    public String getLanguageCode() {
        String str;
        synchronized (this.zzir) {
            str = this.zzhx;
        }
        return str;
    }

    @Nullable
    public Task<AuthResult> getPendingAuthResult() {
        return this.zziu.zzfo();
    }

    @Nullable
    public String getUid() {
        FirebaseUser firebaseUser = this.zzip;
        if (firebaseUser == null) {
            return null;
        }
        return firebaseUser.getUid();
    }

    public boolean isSignInWithEmailLink(@NonNull String str) {
        return EmailAuthCredential.isSignInWithEmailLink(str);
    }

    public void removeAuthStateListener(@NonNull AuthStateListener authStateListener) {
        this.zzin.remove(authStateListener);
    }

    public void removeIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        this.zzil.remove(idTokenListener);
    }

    @KeepForSdk
    public void removeIdTokenListener(@NonNull com.google.firebase.auth.internal.IdTokenListener idTokenListener) {
        Preconditions.checkNotNull(idTokenListener);
        this.zzim.remove(idTokenListener);
        zzct().zzj(this.zzim.size());
    }

    @NonNull
    public Task<Void> sendPasswordResetEmail(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return sendPasswordResetEmail(str, (ActionCodeSettings) null);
    }

    @NonNull
    public Task<Void> sendPasswordResetEmail(@NonNull String str, @Nullable ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        if (actionCodeSettings == null) {
            actionCodeSettings = ActionCodeSettings.zzcj();
        }
        String str2 = this.zzhx;
        if (str2 != null) {
            actionCodeSettings.zzbq(str2);
        }
        actionCodeSettings.zzb(zzfw.PASSWORD_RESET);
        return this.zzio.zza(this.zzik, str, actionCodeSettings, this.zzhy);
    }

    public Task<Void> sendSignInLinkToEmail(@NonNull String str, @NonNull ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(actionCodeSettings);
        if (actionCodeSettings.canHandleCodeInApp()) {
            String str2 = this.zzhx;
            if (str2 != null) {
                actionCodeSettings.zzbq(str2);
            }
            return this.zzio.zzb(this.zzik, str, actionCodeSettings, this.zzhy);
        }
        throw new IllegalArgumentException("You must set canHandleCodeInApp in your ActionCodeSettings to true for Email-Link Sign-in.");
    }

    public Task<Void> setFirebaseUIVersion(@Nullable String str) {
        return this.zzio.setFirebaseUIVersion(str);
    }

    public void setLanguageCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        synchronized (this.zzir) {
            this.zzhx = str;
        }
    }

    @NonNull
    public Task<AuthResult> signInAnonymously() {
        FirebaseUser firebaseUser = this.zzip;
        if (firebaseUser == null || !firebaseUser.isAnonymous()) {
            return this.zzio.zza(this.zzik, (com.google.firebase.auth.internal.zza) new zzb(), this.zzhy);
        }
        zzm zzm = (zzm) this.zzip;
        zzm.zzs(false);
        return Tasks.forResult(new zzg(zzm));
    }

    @NonNull
    public Task<AuthResult> signInWithCredential(@NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            return !emailAuthCredential.zzcr() ? this.zzio.zzb(this.zzik, emailAuthCredential.getEmail(), emailAuthCredential.getPassword(), this.zzhy, (com.google.firebase.auth.internal.zza) new zzb()) : zzbs(emailAuthCredential.zzco()) ? Tasks.forException(zzdr.zzb(new Status(17072))) : this.zzio.zza(this.zzik, emailAuthCredential, (com.google.firebase.auth.internal.zza) new zzb());
        } else if (!(authCredential instanceof PhoneAuthCredential)) {
            return this.zzio.zza(this.zzik, authCredential, this.zzhy, (com.google.firebase.auth.internal.zza) new zzb());
        } else {
            return this.zzio.zza(this.zzik, (PhoneAuthCredential) authCredential, this.zzhy, (com.google.firebase.auth.internal.zza) new zzb());
        }
    }

    @NonNull
    public Task<AuthResult> signInWithCustomToken(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zzio.zza(this.zzik, str, this.zzhy, (com.google.firebase.auth.internal.zza) new zzb());
    }

    @NonNull
    public Task<AuthResult> signInWithEmailAndPassword(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return this.zzio.zzb(this.zzik, str, str2, this.zzhy, (com.google.firebase.auth.internal.zza) new zzb());
    }

    @NonNull
    public Task<AuthResult> signInWithEmailLink(@NonNull String str, @NonNull String str2) {
        return signInWithCredential(EmailAuthProvider.getCredentialWithLink(str, str2));
    }

    public void signOut() {
        zzcs();
        zzas zzas = this.zziv;
        if (zzas != null) {
            zzas.cancel();
        }
    }

    public Task<AuthResult> startActivityForSignInWithProvider(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(activity);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zziu.zza(activity, taskCompletionSource, this)) {
            return Tasks.forException(zzdr.zzb(new Status(17057)));
        }
        zzaq.zza(activity.getApplicationContext(), this);
        federatedAuthProvider.zza(activity);
        return taskCompletionSource.getTask();
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [com.google.firebase.auth.FirebaseAuth$zzc, com.google.firebase.auth.internal.zzax] */
    public Task<Void> updateCurrentUser(@NonNull FirebaseUser firebaseUser) {
        String str;
        if (firebaseUser == null) {
            throw new IllegalArgumentException("Cannot update current user with null user!");
        } else if ((firebaseUser.zzba() != null && !firebaseUser.zzba().equals(this.zzhy)) || ((str = this.zzhy) != null && !str.equals(firebaseUser.zzba()))) {
            return Tasks.forException(zzdr.zzb(new Status(17072)));
        } else {
            String apiKey = firebaseUser.zzcu().getOptions().getApiKey();
            String apiKey2 = this.zzik.getOptions().getApiKey();
            if (!firebaseUser.zzcy().isValid() || !apiKey2.equals(apiKey)) {
                return zza(firebaseUser, (zzax) new zzc(this));
            }
            zza(zzm.zza(this.zzik, firebaseUser), firebaseUser.zzcy(), true);
            return Tasks.forResult(null);
        }
    }

    public void useAppLanguage() {
        synchronized (this.zzir) {
            this.zzhx = zzem.zzem();
        }
    }

    @NonNull
    public Task<String> verifyPasswordResetCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zzio.zzd(this.zzik, str, this.zzhy);
    }

    public final Task<AuthResult> zza(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider, @NonNull FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(firebaseUser);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zziu.zza(activity, taskCompletionSource, this, firebaseUser)) {
            return Tasks.forException(zzdr.zzb(new Status(17057)));
        }
        zzaq.zza(activity.getApplicationContext(), this, firebaseUser);
        federatedAuthProvider.zzb(activity);
        return taskCompletionSource.getTask();
    }

    @NonNull
    public final Task<Void> zza(@Nullable ActionCodeSettings actionCodeSettings, @NonNull String str) {
        Preconditions.checkNotEmpty(str);
        if (this.zzhx != null) {
            if (actionCodeSettings == null) {
                actionCodeSettings = ActionCodeSettings.zzcj();
            }
            actionCodeSettings.zzbq(this.zzhx);
        }
        return this.zzio.zza(this.zzik, actionCodeSettings, str);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    /* JADX WARNING: type inference failed for: r2v2, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    /* JADX WARNING: type inference failed for: r7v0, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(authCredential);
        if (EmailAuthCredential.class.isAssignableFrom(authCredential.getClass())) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            if (!"password".equals(emailAuthCredential.getSignInMethod())) {
                return zzbs(emailAuthCredential.zzco()) ? Tasks.forException(zzdr.zzb(new Status(17072))) : this.zzio.zza(this.zzik, firebaseUser, emailAuthCredential, (zzax) new zza());
            }
            return this.zzio.zza(this.zzik, firebaseUser, emailAuthCredential.getEmail(), emailAuthCredential.getPassword(), firebaseUser.zzba(), new zza());
        } else if (authCredential instanceof PhoneAuthCredential) {
            return this.zzio.zza(this.zzik, firebaseUser, (PhoneAuthCredential) authCredential, this.zzhy, (zzax) new zza());
        } else {
            return this.zzio.zza(this.zzik, firebaseUser, authCredential, firebaseUser.zzba(), (zzax) new zza());
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull PhoneAuthCredential phoneAuthCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(phoneAuthCredential);
        return this.zzio.zza(this.zzik, firebaseUser, phoneAuthCredential, (zzax) new zza());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull UserProfileChangeRequest userProfileChangeRequest) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(userProfileChangeRequest);
        return this.zzio.zza(this.zzik, firebaseUser, userProfileChangeRequest, (zzax) new zza());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    @NonNull
    public final Task<AuthResult> zza(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(firebaseUser);
        return this.zzio.zzd(this.zzik, firebaseUser, str, new zza());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.zzn, com.google.firebase.auth.internal.zzax] */
    @NonNull
    public final Task<GetTokenResult> zza(@Nullable FirebaseUser firebaseUser, boolean z) {
        if (firebaseUser == null) {
            return Tasks.forException(zzdr.zzb(new Status(FirebaseError.ERROR_NO_SIGNED_IN_USER)));
        }
        zzes zzcy = firebaseUser.zzcy();
        return (!zzcy.isValid() || z) ? this.zzio.zza(this.zzik, firebaseUser, zzcy.zzs(), (zzax) new zzn(this)) : Tasks.forResult(zzan.zzdf(zzcy.getAccessToken()));
    }

    public final void zza(@NonNull FirebaseUser firebaseUser, @NonNull zzes zzes, boolean z) {
        boolean z2;
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzes);
        FirebaseUser firebaseUser2 = this.zzip;
        boolean z3 = true;
        if (firebaseUser2 == null) {
            z2 = true;
        } else {
            boolean z4 = !firebaseUser2.zzcy().getAccessToken().equals(zzes.getAccessToken());
            boolean equals = this.zzip.getUid().equals(firebaseUser.getUid());
            z2 = !equals || z4;
            if (equals) {
                z3 = false;
            }
        }
        Preconditions.checkNotNull(firebaseUser);
        FirebaseUser firebaseUser3 = this.zzip;
        if (firebaseUser3 == null) {
            this.zzip = firebaseUser;
        } else {
            firebaseUser3.zza(firebaseUser.getProviderData());
            if (!firebaseUser.isAnonymous()) {
                this.zzip.zzcx();
            }
            this.zzip.zzb(firebaseUser.zzdb().zzdc());
        }
        if (z) {
            this.zzit.zzg(this.zzip);
        }
        if (z2) {
            FirebaseUser firebaseUser4 = this.zzip;
            if (firebaseUser4 != null) {
                firebaseUser4.zza(zzes);
            }
            zzb(this.zzip);
        }
        if (z3) {
            zzc(this.zzip);
        }
        if (z) {
            this.zzit.zza(firebaseUser, zzes);
        }
        zzct().zzc(this.zzip.zzcy());
    }

    public final void zza(@NonNull String str, long j, TimeUnit timeUnit, @NonNull PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, @Nullable Activity activity, @NonNull Executor executor, boolean z) {
        zzm zzm;
        long j2 = j;
        long convert = TimeUnit.SECONDS.convert(j, timeUnit);
        if (convert < 0 || convert > 120) {
            throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
        }
        zzfe zzfe = new zzfe(str, convert, z, this.zzhx, this.zzhy);
        if (this.zziq.zzfe()) {
            String str2 = str;
            if (str.equals(this.zziq.getPhoneNumber())) {
                zzm = new zzm(this, onVerificationStateChangedCallbacks);
                this.zzio.zza(this.zzik, zzfe, zzm, activity, executor);
            }
        }
        zzm = onVerificationStateChangedCallbacks;
        this.zzio.zza(this.zzik, zzfe, zzm, activity, executor);
    }

    public final Task<AuthResult> zzb(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider, @NonNull FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(firebaseUser);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zziu.zza(activity, taskCompletionSource, this, firebaseUser)) {
            return Tasks.forException(zzdr.zzb(new Status(17057)));
        }
        zzaq.zza(activity.getApplicationContext(), this, firebaseUser);
        federatedAuthProvider.zzc(activity);
        return taskCompletionSource.getTask();
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    /* JADX WARNING: type inference failed for: r2v2, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    /* JADX WARNING: type inference failed for: r7v0, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    public final Task<AuthResult> zzb(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(authCredential);
        if (EmailAuthCredential.class.isAssignableFrom(authCredential.getClass())) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            if (!"password".equals(emailAuthCredential.getSignInMethod())) {
                return zzbs(emailAuthCredential.zzco()) ? Tasks.forException(zzdr.zzb(new Status(17072))) : this.zzio.zzb(this.zzik, firebaseUser, emailAuthCredential, (zzax) new zza());
            }
            return this.zzio.zzb(this.zzik, firebaseUser, emailAuthCredential.getEmail(), emailAuthCredential.getPassword(), firebaseUser.zzba(), new zza());
        } else if (authCredential instanceof PhoneAuthCredential) {
            return this.zzio.zzb(this.zzik, firebaseUser, (PhoneAuthCredential) authCredential, this.zzhy, (zzax) new zza());
        } else {
            return this.zzio.zzb(this.zzik, firebaseUser, authCredential, firebaseUser.zzba(), (zzax) new zza());
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    @NonNull
    public final Task<Void> zzb(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zzio.zzb(this.zzik, firebaseUser, str, (zzax) new zza());
    }

    @Nullable
    public final String zzba() {
        String str;
        synchronized (this.zzis) {
            str = this.zzhy;
        }
        return str;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    @NonNull
    public final Task<AuthResult> zzc(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        return this.zzio.zza(this.zzik, firebaseUser, authCredential, (zzax) new zza());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    @NonNull
    public final Task<Void> zzc(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zzio.zzc(this.zzik, firebaseUser, str, new zza());
    }

    public final void zzcs() {
        FirebaseUser firebaseUser = this.zzip;
        if (firebaseUser != null) {
            zzat zzat = this.zzit;
            Preconditions.checkNotNull(firebaseUser);
            zzat.clear(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}));
            this.zzip = null;
        }
        this.zzit.clear("com.google.firebase.auth.FIREBASE_USER");
        zzb((FirebaseUser) null);
        zzc((FirebaseUser) null);
    }

    public final FirebaseApp zzcu() {
        return this.zzik;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.firebase.auth.FirebaseAuth$zza, com.google.firebase.auth.internal.zzax] */
    public final Task<Void> zzd(@NonNull FirebaseUser firebaseUser) {
        return zza(firebaseUser, (zzax) new zza());
    }

    @NonNull
    public final Task<Void> zze(@NonNull FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        return this.zzio.zza(firebaseUser, (zzaa) new zzo(this, firebaseUser));
    }

    public final void zzf(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        synchronized (this.zzis) {
            this.zzhy = str;
        }
    }
}
