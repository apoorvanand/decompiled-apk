package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzjc;
import java.io.InputStream;

public abstract class zzgc<MessageType extends zzjc> implements zzjm<MessageType> {
    private static final zzhf zzvq = zzhf.zzhq();

    private final MessageType zza(InputStream inputStream, zzhf zzhf) {
        zzgr zzgr;
        if (inputStream == null) {
            byte[] bArr = zzht.EMPTY_BYTE_ARRAY;
            zzgr = zzgr.a(bArr, 0, bArr.length, false);
        } else {
            zzgr = new zzgw(inputStream);
        }
        MessageType messagetype = (zzjc) zza(zzgr, zzhf);
        try {
            zzgr.zzs(0);
            return messagetype;
        } catch (zzic e) {
            throw e.zzh(messagetype);
        }
    }

    public final /* synthetic */ Object zzb(InputStream inputStream, zzhf zzhf) {
        zzjc zza = zza(inputStream, zzhf);
        if (zza == null || zza.isInitialized()) {
            return zza;
        }
        throw new zzic((!(zza instanceof zzfx) ? zza instanceof zzfz ? new zzkl((zzfz) zza) : new zzkl(zza) : new zzkl((zzfx) zza)).getMessage()).zzh(zza);
    }
}
