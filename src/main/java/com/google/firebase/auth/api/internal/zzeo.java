package com.google.firebase.auth.api.internal;

import com.google.firebase.auth.PhoneAuthProvider;

final class zzeo implements zzev {
    private final /* synthetic */ String zzqm;

    zzeo(zzep zzep, String str) {
        this.zzqm = str;
    }

    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onCodeSent(this.zzqm, PhoneAuthProvider.ForceResendingToken.zzdd());
    }
}
