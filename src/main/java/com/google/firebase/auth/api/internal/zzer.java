package com.google.firebase.auth.api.internal;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

final class zzer implements zzev {
    private final /* synthetic */ PhoneAuthCredential zzqo;

    zzer(zzep zzep, PhoneAuthCredential phoneAuthCredential) {
        this.zzqo = phoneAuthCredential;
    }

    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationCompleted(this.zzqo);
    }
}
