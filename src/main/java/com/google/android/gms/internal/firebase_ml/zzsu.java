package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzsw;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzsu<FieldDescriptorType extends zzsw<FieldDescriptorType>> {
    private static final zzsu zzbji = new zzsu(true);
    private final zzvf<FieldDescriptorType, Object> zzbjf = zzvf.a(16);
    private boolean zzbjg;
    private boolean zzbjh = false;

    private zzsu() {
    }

    private zzsu(boolean z) {
        zzoh();
    }

    static int a(zzwj zzwj, int i, Object obj) {
        int zzcl = zzsj.zzcl(i);
        if (zzwj == zzwj.GROUP) {
            zzte.a((zzum) obj);
            zzcl <<= 1;
        }
        return zzcl + zzb(zzwj, obj);
    }

    static void a(zzsj zzsj, zzwj zzwj, int i, Object obj) {
        if (zzwj == zzwj.GROUP) {
            zzum zzum = (zzum) obj;
            zzte.a(zzum);
            zzsj.zzd(i, 3);
            zzum.zzb(zzsj);
            zzsj.zzd(i, 4);
            return;
        }
        zzsj.zzd(i, zzwj.zzse());
        switch (zzsv.b[zzwj.ordinal()]) {
            case 1:
                zzsj.zzc(((Double) obj).doubleValue());
                return;
            case 2:
                zzsj.zzr(((Float) obj).floatValue());
                return;
            case 3:
                zzsj.zzn(((Long) obj).longValue());
                return;
            case 4:
                zzsj.zzn(((Long) obj).longValue());
                return;
            case 5:
                zzsj.zzch(((Integer) obj).intValue());
                return;
            case 6:
                zzsj.zzp(((Long) obj).longValue());
                return;
            case 7:
                zzsj.zzck(((Integer) obj).intValue());
                return;
            case 8:
                zzsj.zzag(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzum) obj).zzb(zzsj);
                return;
            case 10:
                zzsj.zzb((zzum) obj);
                return;
            case 11:
                if (obj instanceof zzru) {
                    zzsj.zza((zzru) obj);
                    return;
                } else {
                    zzsj.zzcj((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzru) {
                    zzsj.zza((zzru) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzsj.zze(bArr, 0, bArr.length);
                return;
            case 13:
                zzsj.zzci(((Integer) obj).intValue());
                return;
            case 14:
                zzsj.zzck(((Integer) obj).intValue());
                return;
            case 15:
                zzsj.zzp(((Long) obj).longValue());
                return;
            case 16:
                zzsj.zzcj(((Integer) obj).intValue());
                return;
            case 17:
                zzsj.zzo(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zztf) {
                    zzsj.zzch(((zztf) obj).zzh());
                    return;
                } else {
                    zzsj.zzch(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.zzbjf.get(fielddescriptortype);
        return obj instanceof zztp ? zztp.zzqh() : obj;
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.zzpi()) {
            zza(fielddescriptortype.zzpg(), obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fielddescriptortype.zzpg(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zztp) {
            this.zzbjh = true;
        }
        this.zzbjf.put(fielddescriptortype, obj);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0026, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001b, code lost:
        if ((r3 instanceof com.google.android.gms.internal.firebase_ml.zztp) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if ((r3 instanceof com.google.android.gms.internal.firebase_ml.zztf) == false) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.firebase_ml.zzwj r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.firebase_ml.zzte.a(r3)
            int[] r0 = com.google.android.gms.internal.firebase_ml.zzsv.a
            com.google.android.gms.internal.firebase_ml.zzwo r2 = r2.zzsd()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L_0x0040;
                case 2: goto L_0x003d;
                case 3: goto L_0x003a;
                case 4: goto L_0x0037;
                case 5: goto L_0x0034;
                case 6: goto L_0x0031;
                case 7: goto L_0x0028;
                case 8: goto L_0x001e;
                case 9: goto L_0x0015;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x0043
        L_0x0015:
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase_ml.zzum
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase_ml.zztp
            if (r2 == 0) goto L_0x0043
            goto L_0x0026
        L_0x001e:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase_ml.zztf
            if (r2 == 0) goto L_0x0043
        L_0x0026:
            r1 = 1
            goto L_0x0043
        L_0x0028:
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase_ml.zzru
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L_0x0043
            goto L_0x0026
        L_0x0031:
            boolean r0 = r3 instanceof java.lang.String
            goto L_0x0042
        L_0x0034:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L_0x0042
        L_0x0037:
            boolean r0 = r3 instanceof java.lang.Double
            goto L_0x0042
        L_0x003a:
            boolean r0 = r3 instanceof java.lang.Float
            goto L_0x0042
        L_0x003d:
            boolean r0 = r3 instanceof java.lang.Long
            goto L_0x0042
        L_0x0040:
            boolean r0 = r3 instanceof java.lang.Integer
        L_0x0042:
            r1 = r0
        L_0x0043:
            if (r1 == 0) goto L_0x0046
            return
        L_0x0046:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzsu.zza(com.google.android.gms.internal.firebase_ml.zzwj, java.lang.Object):void");
    }

    private static int zzb(zzsw<?> zzsw, Object obj) {
        zzwj zzpg = zzsw.zzpg();
        int zzh = zzsw.zzh();
        if (!zzsw.zzpi()) {
            return a(zzpg, zzh, obj);
        }
        int i = 0;
        if (zzsw.zzpj()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzpg, zzb);
            }
            return zzsj.zzcl(zzh) + i + zzsj.zzcu(i);
        }
        for (Object a : (List) obj) {
            i += a(zzpg, zzh, a);
        }
        return i;
    }

    private static int zzb(zzwj zzwj, Object obj) {
        switch (zzsv.b[zzwj.ordinal()]) {
            case 1:
                return zzsj.zzd(((Double) obj).doubleValue());
            case 2:
                return zzsj.zzs(((Float) obj).floatValue());
            case 3:
                return zzsj.zzq(((Long) obj).longValue());
            case 4:
                return zzsj.zzr(((Long) obj).longValue());
            case 5:
                return zzsj.zzcm(((Integer) obj).intValue());
            case 6:
                return zzsj.zzt(((Long) obj).longValue());
            case 7:
                return zzsj.zzcp(((Integer) obj).intValue());
            case 8:
                return zzsj.zzah(((Boolean) obj).booleanValue());
            case 9:
                return zzsj.zzd((zzum) obj);
            case 10:
                return obj instanceof zztp ? zzsj.zza((zztt) (zztp) obj) : zzsj.zzc((zzum) obj);
            case 11:
                return obj instanceof zzru ? zzsj.zzb((zzru) obj) : zzsj.zzck((String) obj);
            case 12:
                return obj instanceof zzru ? zzsj.zzb((zzru) obj) : zzsj.zzi((byte[]) obj);
            case 13:
                return zzsj.zzcn(((Integer) obj).intValue());
            case 14:
                return zzsj.zzcq(((Integer) obj).intValue());
            case 15:
                return zzsj.zzu(((Long) obj).longValue());
            case 16:
                return zzsj.zzco(((Integer) obj).intValue());
            case 17:
                return zzsj.zzs(((Long) obj).longValue());
            case 18:
                return obj instanceof zztf ? zzsj.zzcr(((zztf) obj).zzh()) : zzsj.zzcr(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static boolean zzb(Map.Entry<FieldDescriptorType, Object> entry) {
        zzsw zzsw = (zzsw) entry.getKey();
        if (zzsw.zzph() == zzwo.MESSAGE) {
            if (zzsw.zzpi()) {
                for (zzum isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzum) {
                    if (!((zzum) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zztp) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    private final void zzc(Map.Entry<FieldDescriptorType, Object> entry) {
        zzsw zzsw = (zzsw) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zztp) {
            value = zztp.zzqh();
        }
        if (zzsw.zzpi()) {
            Object zza = zza(zzsw);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzr : (List) value) {
                ((List) zza).add(zzr(zzr));
            }
            this.zzbjf.put(zzsw, zza);
        } else if (zzsw.zzph() == zzwo.MESSAGE) {
            Object zza2 = zza(zzsw);
            if (zza2 == null) {
                this.zzbjf.put(zzsw, zzr(value));
            } else {
                this.zzbjf.put(zzsw, zza2 instanceof zzut ? zzsw.zza((zzut) zza2, (zzut) value) : zzsw.zza(((zzum) zza2).zzpq(), (zzum) value).zzpx());
            }
        } else {
            this.zzbjf.put(zzsw, zzr(value));
        }
    }

    private static int zzd(Map.Entry<FieldDescriptorType, Object> entry) {
        zzsw zzsw = (zzsw) entry.getKey();
        Object value = entry.getValue();
        return (zzsw.zzph() != zzwo.MESSAGE || zzsw.zzpi() || zzsw.zzpj()) ? zzb((zzsw<?>) zzsw, value) : value instanceof zztp ? zzsj.zzb(((zzsw) entry.getKey()).zzh(), (zztt) (zztp) value) : zzsj.zzb(((zzsw) entry.getKey()).zzh(), (zzum) value);
    }

    public static <T extends zzsw<T>> zzsu<T> zzpd() {
        return zzbji;
    }

    private static Object zzr(Object obj) {
        if (obj instanceof zzut) {
            return ((zzut) obj).zzqy();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    /* access modifiers changed from: package-private */
    public final boolean a() {
        return this.zzbjf.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<FieldDescriptorType, Object>> b() {
        return this.zzbjh ? new zzts(this.zzbjf.a().iterator()) : this.zzbjf.a().iterator();
    }

    public final /* synthetic */ Object clone() {
        zzsu zzsu = new zzsu();
        for (int i = 0; i < this.zzbjf.zzrl(); i++) {
            Map.Entry<FieldDescriptorType, Object> zzdg = this.zzbjf.zzdg(i);
            zzsu.zza((zzsw) zzdg.getKey(), zzdg.getValue());
        }
        for (Map.Entry next : this.zzbjf.zzrm()) {
            zzsu.zza((zzsw) next.getKey(), next.getValue());
        }
        zzsu.zzbjh = this.zzbjh;
        return zzsu;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzsu)) {
            return false;
        }
        return this.zzbjf.equals(((zzsu) obj).zzbjf);
    }

    public final int hashCode() {
        return this.zzbjf.hashCode();
    }

    public final boolean isImmutable() {
        return this.zzbjg;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzbjf.zzrl(); i++) {
            if (!zzb(this.zzbjf.zzdg(i))) {
                return false;
            }
        }
        for (Map.Entry<FieldDescriptorType, Object> zzb : this.zzbjf.zzrm()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzbjh ? new zzts(this.zzbjf.entrySet().iterator()) : this.zzbjf.entrySet().iterator();
    }

    public final void zza(zzsu<FieldDescriptorType> zzsu) {
        for (int i = 0; i < zzsu.zzbjf.zzrl(); i++) {
            zzc(zzsu.zzbjf.zzdg(i));
        }
        for (Map.Entry<FieldDescriptorType, Object> zzc : zzsu.zzbjf.zzrm()) {
            zzc(zzc);
        }
    }

    public final void zzoh() {
        if (!this.zzbjg) {
            this.zzbjf.zzoh();
            this.zzbjg = true;
        }
    }

    public final int zzpe() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzbjf.zzrl(); i2++) {
            Map.Entry<FieldDescriptorType, Object> zzdg = this.zzbjf.zzdg(i2);
            i += zzb((zzsw<?>) (zzsw) zzdg.getKey(), zzdg.getValue());
        }
        for (Map.Entry next : this.zzbjf.zzrm()) {
            i += zzb((zzsw<?>) (zzsw) next.getKey(), next.getValue());
        }
        return i;
    }

    public final int zzpf() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzbjf.zzrl(); i2++) {
            i += zzd(this.zzbjf.zzdg(i2));
        }
        for (Map.Entry<FieldDescriptorType, Object> zzd : this.zzbjf.zzrm()) {
            i += zzd(zzd);
        }
        return i;
    }
}
