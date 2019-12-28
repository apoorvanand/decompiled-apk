package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzeq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzeo<FieldDescriptorType extends zzeq<FieldDescriptorType>> {
    private static final zzeo zzafa = new zzeo(true);
    final zzhc<FieldDescriptorType, Object> a = zzhc.a(16);
    private boolean zzaey;
    private boolean zzaez = false;

    private zzeo() {
    }

    private zzeo(boolean z) {
        zzry();
    }

    static int a(zzig zzig, int i, Object obj) {
        int zzbi = zzee.zzbi(i);
        if (zzig == zzig.GROUP) {
            zzez.a((zzgi) obj);
            zzbi <<= 1;
        }
        return zzbi + zzb(zzig, obj);
    }

    static void a(zzee zzee, zzig zzig, int i, Object obj) {
        if (zzig == zzig.GROUP) {
            zzgi zzgi = (zzgi) obj;
            zzez.a(zzgi);
            zzee.zzb(i, 3);
            zzgi.zzb(zzee);
            zzee.zzb(i, 4);
            return;
        }
        zzee.zzb(i, zzig.zzxa());
        switch (zzer.b[zzig.ordinal()]) {
            case 1:
                zzee.zzd(((Double) obj).doubleValue());
                return;
            case 2:
                zzee.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzee.zzbn(((Long) obj).longValue());
                return;
            case 4:
                zzee.zzbn(((Long) obj).longValue());
                return;
            case 5:
                zzee.zzbe(((Integer) obj).intValue());
                return;
            case 6:
                zzee.zzbp(((Long) obj).longValue());
                return;
            case 7:
                zzee.zzbh(((Integer) obj).intValue());
                return;
            case 8:
                zzee.zzq(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzgi) obj).zzb(zzee);
                return;
            case 10:
                zzee.zzb((zzgi) obj);
                return;
            case 11:
                if (obj instanceof zzdp) {
                    zzee.zza((zzdp) obj);
                    return;
                } else {
                    zzee.zzdr((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzdp) {
                    zzee.zza((zzdp) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzee.zze(bArr, 0, bArr.length);
                return;
            case 13:
                zzee.zzbf(((Integer) obj).intValue());
                return;
            case 14:
                zzee.zzbh(((Integer) obj).intValue());
                return;
            case 15:
                zzee.zzbp(((Long) obj).longValue());
                return;
            case 16:
                zzee.zzbg(((Integer) obj).intValue());
                return;
            case 17:
                zzee.zzbo(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzfc) {
                    zzee.zzbe(((zzfc) obj).zzlg());
                    return;
                } else {
                    zzee.zzbe(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.a.get(fielddescriptortype);
        return obj instanceof zzfj ? zzfj.zzvc() : obj;
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.zzty()) {
            zza(fielddescriptortype.zztw(), obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fielddescriptortype.zztw(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzfj) {
            this.zzaez = true;
        }
        this.a.put(fielddescriptortype, obj);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0026, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001b, code lost:
        if ((r3 instanceof com.google.android.gms.internal.measurement.zzfj) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if ((r3 instanceof com.google.android.gms.internal.measurement.zzfc) == false) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.measurement.zzig r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.measurement.zzez.a(r3)
            int[] r0 = com.google.android.gms.internal.measurement.zzer.a
            com.google.android.gms.internal.measurement.zzij r2 = r2.zzwz()
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
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzgi
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzfj
            if (r2 == 0) goto L_0x0043
            goto L_0x0026
        L_0x001e:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzfc
            if (r2 == 0) goto L_0x0043
        L_0x0026:
            r1 = 1
            goto L_0x0043
        L_0x0028:
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzdp
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzeo.zza(com.google.android.gms.internal.measurement.zzig, java.lang.Object):void");
    }

    public static int zzb(zzeq<?> zzeq, Object obj) {
        zzig zztw = zzeq.zztw();
        int zzlg = zzeq.zzlg();
        if (!zzeq.zzty()) {
            return a(zztw, zzlg, obj);
        }
        int i = 0;
        if (zzeq.zztz()) {
            for (Object zzb : (List) obj) {
                i += zzb(zztw, zzb);
            }
            return zzee.zzbi(zzlg) + i + zzee.zzbq(i);
        }
        for (Object a2 : (List) obj) {
            i += a(zztw, zzlg, a2);
        }
        return i;
    }

    private static int zzb(zzig zzig, Object obj) {
        switch (zzer.b[zzig.ordinal()]) {
            case 1:
                return zzee.zze(((Double) obj).doubleValue());
            case 2:
                return zzee.zzb(((Float) obj).floatValue());
            case 3:
                return zzee.zzbq(((Long) obj).longValue());
            case 4:
                return zzee.zzbr(((Long) obj).longValue());
            case 5:
                return zzee.zzbj(((Integer) obj).intValue());
            case 6:
                return zzee.zzbt(((Long) obj).longValue());
            case 7:
                return zzee.zzbm(((Integer) obj).intValue());
            case 8:
                return zzee.zzr(((Boolean) obj).booleanValue());
            case 9:
                return zzee.zzd((zzgi) obj);
            case 10:
                return obj instanceof zzfj ? zzee.zza((zzfn) (zzfj) obj) : zzee.zzc((zzgi) obj);
            case 11:
                return obj instanceof zzdp ? zzee.zzb((zzdp) obj) : zzee.zzds((String) obj);
            case 12:
                return obj instanceof zzdp ? zzee.zzb((zzdp) obj) : zzee.zzg((byte[]) obj);
            case 13:
                return zzee.zzbk(((Integer) obj).intValue());
            case 14:
                return zzee.zzbn(((Integer) obj).intValue());
            case 15:
                return zzee.zzbu(((Long) obj).longValue());
            case 16:
                return zzee.zzbl(((Integer) obj).intValue());
            case 17:
                return zzee.zzbs(((Long) obj).longValue());
            case 18:
                return obj instanceof zzfc ? zzee.zzbo(((zzfc) obj).zzlg()) : zzee.zzbo(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static boolean zzb(Map.Entry<FieldDescriptorType, Object> entry) {
        zzeq zzeq = (zzeq) entry.getKey();
        if (zzeq.zztx() == zzij.MESSAGE) {
            if (zzeq.zzty()) {
                for (zzgi isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzgi) {
                    if (!((zzgi) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzfj) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    private final void zzc(Map.Entry<FieldDescriptorType, Object> entry) {
        zzeq zzeq = (zzeq) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzfj) {
            value = zzfj.zzvc();
        }
        if (zzeq.zzty()) {
            Object zza = zza(zzeq);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzk : (List) value) {
                ((List) zza).add(zzk(zzk));
            }
            this.a.put(zzeq, zza);
        } else if (zzeq.zztx() == zzij.MESSAGE) {
            Object zza2 = zza(zzeq);
            if (zza2 == null) {
                this.a.put(zzeq, zzk(value));
            } else {
                this.a.put(zzeq, zza2 instanceof zzgn ? zzeq.zza((zzgn) zza2, (zzgn) value) : zzeq.zza(((zzgi) zza2).zzuo(), (zzgi) value).zzug());
            }
        } else {
            this.a.put(zzeq, zzk(value));
        }
    }

    private static int zzd(Map.Entry<FieldDescriptorType, Object> entry) {
        zzeq zzeq = (zzeq) entry.getKey();
        Object value = entry.getValue();
        return (zzeq.zztx() != zzij.MESSAGE || zzeq.zzty() || zzeq.zztz()) ? zzb((zzeq<?>) zzeq, value) : value instanceof zzfj ? zzee.zzb(((zzeq) entry.getKey()).zzlg(), (zzfn) (zzfj) value) : zzee.zzd(((zzeq) entry.getKey()).zzlg(), (zzgi) value);
    }

    private static Object zzk(Object obj) {
        if (obj instanceof zzgn) {
            return ((zzgn) obj).zzvu();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static <T extends zzeq<T>> zzeo<T> zztr() {
        return zzafa;
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<FieldDescriptorType, Object>> a() {
        return this.zzaez ? new zzfo(this.a.a().iterator()) : this.a.a().iterator();
    }

    public final /* synthetic */ Object clone() {
        zzeo zzeo = new zzeo();
        for (int i = 0; i < this.a.zzwh(); i++) {
            Map.Entry<FieldDescriptorType, Object> zzcf = this.a.zzcf(i);
            zzeo.zza((zzeq) zzcf.getKey(), zzcf.getValue());
        }
        for (Map.Entry next : this.a.zzwi()) {
            zzeo.zza((zzeq) next.getKey(), next.getValue());
        }
        zzeo.zzaez = this.zzaez;
        return zzeo;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzeo)) {
            return false;
        }
        return this.a.equals(((zzeo) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final boolean isImmutable() {
        return this.zzaey;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.a.zzwh(); i++) {
            if (!zzb(this.a.zzcf(i))) {
                return false;
            }
        }
        for (Map.Entry<FieldDescriptorType, Object> zzb : this.a.zzwi()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzaez ? new zzfo(this.a.entrySet().iterator()) : this.a.entrySet().iterator();
    }

    public final void zza(zzeo<FieldDescriptorType> zzeo) {
        for (int i = 0; i < zzeo.a.zzwh(); i++) {
            zzc(zzeo.a.zzcf(i));
        }
        for (Map.Entry<FieldDescriptorType, Object> zzc : zzeo.a.zzwi()) {
            zzc(zzc);
        }
    }

    public final void zzry() {
        if (!this.zzaey) {
            this.a.zzry();
            this.zzaey = true;
        }
    }

    public final int zzts() {
        int i = 0;
        for (int i2 = 0; i2 < this.a.zzwh(); i2++) {
            i += zzd(this.a.zzcf(i2));
        }
        for (Map.Entry<FieldDescriptorType, Object> zzd : this.a.zzwi()) {
            i += zzd(zzd);
        }
        return i;
    }
}
