package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhk;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzhi<FieldDescriptorType extends zzhk<FieldDescriptorType>> {
    private static final zzhi zzxk = new zzhi(true);
    final zzjt<FieldDescriptorType, Object> a = zzjt.a(16);
    private boolean zzxi;
    private boolean zzxj = false;

    private zzhi() {
    }

    private zzhi(boolean z) {
        zzfy();
    }

    static int a(zzlb zzlb, int i, Object obj) {
        int zzak = zzha.zzak(i);
        if (zzlb == zzlb.GROUP) {
            zzht.a((zzjc) obj);
            zzak <<= 1;
        }
        return zzak + zzb(zzlb, obj);
    }

    static void a(zzha zzha, zzlb zzlb, int i, Object obj) {
        if (zzlb == zzlb.GROUP) {
            zzjc zzjc = (zzjc) obj;
            zzht.a(zzjc);
            zzha.zze(i, 3);
            zzjc.zzb(zzha);
            zzha.zze(i, 4);
            return;
        }
        zzha.zze(i, zzlb.zzky());
        switch (zzhl.b[zzlb.ordinal()]) {
            case 1:
                zzha.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzha.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzha.zzb(((Long) obj).longValue());
                return;
            case 4:
                zzha.zzb(((Long) obj).longValue());
                return;
            case 5:
                zzha.zzag(((Integer) obj).intValue());
                return;
            case 6:
                zzha.zzd(((Long) obj).longValue());
                return;
            case 7:
                zzha.zzaj(((Integer) obj).intValue());
                return;
            case 8:
                zzha.zzt(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzjc) obj).zzb(zzha);
                return;
            case 10:
                zzha.zzc((zzjc) obj);
                return;
            case 11:
                if (obj instanceof zzgf) {
                    zzha.zza((zzgf) obj);
                    return;
                } else {
                    zzha.zzdi((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzgf) {
                    zzha.zza((zzgf) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzha.zzd(bArr, 0, bArr.length);
                return;
            case 13:
                zzha.zzah(((Integer) obj).intValue());
                return;
            case 14:
                zzha.zzaj(((Integer) obj).intValue());
                return;
            case 15:
                zzha.zzd(((Long) obj).longValue());
                return;
            case 16:
                zzha.zzai(((Integer) obj).intValue());
                return;
            case 17:
                zzha.zzc(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzhw) {
                    zzha.zzag(((zzhw) obj).zzbq());
                    return;
                } else {
                    zzha.zzag(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.a.get(fielddescriptortype);
        return obj instanceof zzid ? zzid.zzja() : obj;
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.zzhz()) {
            zza(fielddescriptortype.zzhx(), obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fielddescriptortype.zzhx(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzid) {
            this.zzxj = true;
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
        if ((r3 instanceof com.google.android.gms.internal.firebase_auth.zzid) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if ((r3 instanceof com.google.android.gms.internal.firebase_auth.zzhw) == false) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.firebase_auth.zzlb r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.firebase_auth.zzht.a(r3)
            int[] r0 = com.google.android.gms.internal.firebase_auth.zzhl.a
            com.google.android.gms.internal.firebase_auth.zzle r2 = r2.zzkx()
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
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase_auth.zzjc
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase_auth.zzid
            if (r2 == 0) goto L_0x0043
            goto L_0x0026
        L_0x001e:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase_auth.zzhw
            if (r2 == 0) goto L_0x0043
        L_0x0026:
            r1 = 1
            goto L_0x0043
        L_0x0028:
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase_auth.zzgf
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzhi.zza(com.google.android.gms.internal.firebase_auth.zzlb, java.lang.Object):void");
    }

    public static int zzb(zzhk<?> zzhk, Object obj) {
        zzlb zzhx = zzhk.zzhx();
        int zzbq = zzhk.zzbq();
        if (!zzhk.zzhz()) {
            return a(zzhx, zzbq, obj);
        }
        int i = 0;
        if (zzhk.zzia()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzhx, zzb);
            }
            return zzha.zzak(zzbq) + i + zzha.zzas(i);
        }
        for (Object a2 : (List) obj) {
            i += a(zzhx, zzbq, a2);
        }
        return i;
    }

    private static int zzb(zzlb zzlb, Object obj) {
        switch (zzhl.b[zzlb.ordinal()]) {
            case 1:
                return zzha.zzb(((Double) obj).doubleValue());
            case 2:
                return zzha.zzb(((Float) obj).floatValue());
            case 3:
                return zzha.zze(((Long) obj).longValue());
            case 4:
                return zzha.zzf(((Long) obj).longValue());
            case 5:
                return zzha.zzal(((Integer) obj).intValue());
            case 6:
                return zzha.zzh(((Long) obj).longValue());
            case 7:
                return zzha.zzao(((Integer) obj).intValue());
            case 8:
                return zzha.zzu(((Boolean) obj).booleanValue());
            case 9:
                return zzha.zze((zzjc) obj);
            case 10:
                return obj instanceof zzid ? zzha.zza((zzih) (zzid) obj) : zzha.zzd((zzjc) obj);
            case 11:
                return obj instanceof zzgf ? zzha.zzb((zzgf) obj) : zzha.zzdj((String) obj);
            case 12:
                return obj instanceof zzgf ? zzha.zzb((zzgf) obj) : zzha.zzd((byte[]) obj);
            case 13:
                return zzha.zzam(((Integer) obj).intValue());
            case 14:
                return zzha.zzap(((Integer) obj).intValue());
            case 15:
                return zzha.zzi(((Long) obj).longValue());
            case 16:
                return zzha.zzan(((Integer) obj).intValue());
            case 17:
                return zzha.zzg(((Long) obj).longValue());
            case 18:
                return obj instanceof zzhw ? zzha.zzaq(((zzhw) obj).zzbq()) : zzha.zzaq(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static boolean zzb(Map.Entry<FieldDescriptorType, Object> entry) {
        zzhk zzhk = (zzhk) entry.getKey();
        if (zzhk.zzhy() == zzle.MESSAGE) {
            if (zzhk.zzhz()) {
                for (zzjc isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzjc) {
                    if (!((zzjc) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzid) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    private final void zzc(Map.Entry<FieldDescriptorType, Object> entry) {
        zzhk zzhk = (zzhk) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzid) {
            value = zzid.zzja();
        }
        if (zzhk.zzhz()) {
            Object zza = zza(zzhk);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzg : (List) value) {
                ((List) zza).add(zzg(zzg));
            }
            this.a.put(zzhk, zza);
        } else if (zzhk.zzhy() == zzle.MESSAGE) {
            Object zza2 = zza(zzhk);
            if (zza2 == null) {
                this.a.put(zzhk, zzg(value));
            } else {
                this.a.put(zzhk, zza2 instanceof zzji ? zzhk.zza((zzji) zza2, (zzji) value) : zzhk.zza(((zzjc) zza2).zzin(), (zzjc) value).zzih());
            }
        } else {
            this.a.put(zzhk, zzg(value));
        }
    }

    private static int zzd(Map.Entry<FieldDescriptorType, Object> entry) {
        zzhk zzhk = (zzhk) entry.getKey();
        Object value = entry.getValue();
        return (zzhk.zzhy() != zzle.MESSAGE || zzhk.zzhz() || zzhk.zzia()) ? zzb((zzhk<?>) zzhk, value) : value instanceof zzid ? zzha.zzb(((zzhk) entry.getKey()).zzbq(), (zzih) (zzid) value) : zzha.zzb(((zzhk) entry.getKey()).zzbq(), (zzjc) value);
    }

    private static Object zzg(Object obj) {
        if (obj instanceof zzji) {
            return ((zzji) obj).zzfv();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static <T extends zzhk<T>> zzhi<T> zzhs() {
        return zzxk;
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<FieldDescriptorType, Object>> a() {
        return this.zzxj ? new zzii(this.a.a().iterator()) : this.a.a().iterator();
    }

    public final /* synthetic */ Object clone() {
        zzhi zzhi = new zzhi();
        for (int i = 0; i < this.a.zzjy(); i++) {
            Map.Entry<FieldDescriptorType, Object> zzbf = this.a.zzbf(i);
            zzhi.zza((zzhk) zzbf.getKey(), zzbf.getValue());
        }
        for (Map.Entry next : this.a.zzjz()) {
            zzhi.zza((zzhk) next.getKey(), next.getValue());
        }
        zzhi.zzxj = this.zzxj;
        return zzhi;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzhi)) {
            return false;
        }
        return this.a.equals(((zzhi) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final boolean isImmutable() {
        return this.zzxi;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.a.zzjy(); i++) {
            if (!zzb(this.a.zzbf(i))) {
                return false;
            }
        }
        for (Map.Entry<FieldDescriptorType, Object> zzb : this.a.zzjz()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzxj ? new zzii(this.a.entrySet().iterator()) : this.a.entrySet().iterator();
    }

    public final void zza(zzhi<FieldDescriptorType> zzhi) {
        for (int i = 0; i < zzhi.a.zzjy(); i++) {
            zzc(zzhi.a.zzbf(i));
        }
        for (Map.Entry<FieldDescriptorType, Object> zzc : zzhi.a.zzjz()) {
            zzc(zzc);
        }
    }

    public final void zzfy() {
        if (!this.zzxi) {
            this.a.zzfy();
            this.zzxi = true;
        }
    }

    public final int zzht() {
        int i = 0;
        for (int i2 = 0; i2 < this.a.zzjy(); i2++) {
            i += zzd(this.a.zzbf(i2));
        }
        for (Map.Entry<FieldDescriptorType, Object> zzd : this.a.zzjz()) {
            i += zzd(zzd);
        }
        return i;
    }
}
