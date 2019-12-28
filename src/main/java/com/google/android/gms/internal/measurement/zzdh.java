package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzdf;
import com.google.android.gms.internal.measurement.zzdh;
import java.io.IOException;

public abstract class zzdh<MessageType extends zzdf<MessageType, BuilderType>, BuilderType extends zzdh<MessageType, BuilderType>> implements zzgh {
    /* access modifiers changed from: protected */
    public abstract BuilderType a(MessageType messagetype);

    public abstract BuilderType zza(zzeb zzeb, zzel zzel);

    public BuilderType zza(byte[] bArr, int i, int i2, zzel zzel) {
        try {
            zzeb a = zzeb.a(bArr, 0, i2, false);
            zza(a, zzel);
            a.zzat(0);
            return this;
        } catch (zzfi e) {
            throw e;
        } catch (IOException e2) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 60 + String.valueOf("byte array").length());
            sb.append("Reading ");
            sb.append(name);
            sb.append(" from a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e2);
        }
    }

    public final /* synthetic */ zzgh zza(zzgi zzgi) {
        if (zzuh().getClass().isInstance(zzgi)) {
            return a((zzdf) zzgi);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    public final BuilderType zzf(byte[] bArr, zzel zzel) {
        return zza(bArr, 0, bArr.length, zzel);
    }

    /* renamed from: zzru */
    public abstract BuilderType clone();
}
