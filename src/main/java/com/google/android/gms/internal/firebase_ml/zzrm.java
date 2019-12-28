package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzrl;
import com.google.android.gms.internal.firebase_ml.zzrm;

public abstract class zzrm<MessageType extends zzrl<MessageType, BuilderType>, BuilderType extends zzrm<MessageType, BuilderType>> implements zzun {
    /* access modifiers changed from: protected */
    public abstract BuilderType a(MessageType messagetype);

    public final /* synthetic */ zzun zza(zzum zzum) {
        if (zzps().getClass().isInstance(zzum)) {
            return a((zzrl) zzum);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    /* renamed from: zzof */
    public abstract BuilderType clone();
}
