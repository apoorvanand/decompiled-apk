package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzfy;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

final class zzfm extends zzfl<zzfy.zze> {
    zzfm() {
    }

    /* access modifiers changed from: package-private */
    public final int a(Map.Entry<?, ?> entry) {
        return ((zzfy.zze) entry.getKey()).b;
    }

    /* access modifiers changed from: package-private */
    public final zzfp<zzfy.zze> a(Object obj) {
        return ((zzfy.zzd) obj).zzwp;
    }

    /* access modifiers changed from: package-private */
    public final Object a(zzfk zzfk, zzhf zzhf, int i) {
        return zzfk.zza(zzhf, i);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x016d, code lost:
        r1 = java.lang.Long.valueOf(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <UT, UB> UB a(com.google.android.gms.internal.vision.zzhv r5, java.lang.Object r6, com.google.android.gms.internal.vision.zzfk r7, com.google.android.gms.internal.vision.zzfp<com.google.android.gms.internal.vision.zzfy.zze> r8, UB r9, com.google.android.gms.internal.vision.zzio<UT, UB> r10) {
        /*
            r4 = this;
            com.google.android.gms.internal.vision.zzfy$zzf r6 = (com.google.android.gms.internal.vision.zzfy.zzf) r6
            com.google.android.gms.internal.vision.zzfy$zze r0 = r6.d
            int r0 = r0.b
            com.google.android.gms.internal.vision.zzfy$zze r1 = r6.d
            boolean r1 = r1.d
            if (r1 == 0) goto L_0x00d8
            com.google.android.gms.internal.vision.zzfy$zze r1 = r6.d
            boolean r1 = r1.e
            if (r1 == 0) goto L_0x00d8
            int[] r7 = com.google.android.gms.internal.vision.zzfn.a
            com.google.android.gms.internal.vision.zzfy$zze r1 = r6.d
            com.google.android.gms.internal.vision.zzjd r1 = r1.c
            int r1 = r1.ordinal()
            r7 = r7[r1]
            switch(r7) {
                case 1: goto L_0x00c9;
                case 2: goto L_0x00c0;
                case 3: goto L_0x00b7;
                case 4: goto L_0x00ae;
                case 5: goto L_0x00a5;
                case 6: goto L_0x009c;
                case 7: goto L_0x0093;
                case 8: goto L_0x008a;
                case 9: goto L_0x0081;
                case 10: goto L_0x0078;
                case 11: goto L_0x006f;
                case 12: goto L_0x0066;
                case 13: goto L_0x005c;
                case 14: goto L_0x004a;
                default: goto L_0x0021;
            }
        L_0x0021:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            com.google.android.gms.internal.vision.zzfy$zze r6 = r6.d
            com.google.android.gms.internal.vision.zzjd r6 = r6.c
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r7 = java.lang.String.valueOf(r6)
            int r7 = r7.length()
            int r7 = r7 + 23
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r7)
            java.lang.String r7 = "Type cannot be packed: "
            r8.append(r7)
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r5.<init>(r6)
            throw r5
        L_0x004a:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zzl(r7)
            com.google.android.gms.internal.vision.zzfy$zze r5 = r6.d
            com.google.android.gms.internal.vision.zzgc<?> r5 = r5.a
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzhy.a((int) r0, (java.util.List<java.lang.Integer>) r7, (com.google.android.gms.internal.vision.zzgc<?>) r5, r9, r10)
            goto L_0x00d1
        L_0x005c:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zzp(r7)
            goto L_0x00d1
        L_0x0066:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zzo(r7)
            goto L_0x00d1
        L_0x006f:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zzn(r7)
            goto L_0x00d1
        L_0x0078:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zzm(r7)
            goto L_0x00d1
        L_0x0081:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zzk(r7)
            goto L_0x00d1
        L_0x008a:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zzh(r7)
            goto L_0x00d1
        L_0x0093:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zzg(r7)
            goto L_0x00d1
        L_0x009c:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zzf(r7)
            goto L_0x00d1
        L_0x00a5:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zze(r7)
            goto L_0x00d1
        L_0x00ae:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zzc(r7)
            goto L_0x00d1
        L_0x00b7:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zzd(r7)
            goto L_0x00d1
        L_0x00c0:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zzb(r7)
            goto L_0x00d1
        L_0x00c9:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r5.zza(r7)
        L_0x00d1:
            com.google.android.gms.internal.vision.zzfy$zze r5 = r6.d
            r8.zza(r5, (java.lang.Object) r7)
            goto L_0x01b0
        L_0x00d8:
            r1 = 0
            com.google.android.gms.internal.vision.zzfy$zze r2 = r6.d
            com.google.android.gms.internal.vision.zzjd r2 = r2.c
            com.google.android.gms.internal.vision.zzjd r3 = com.google.android.gms.internal.vision.zzjd.ENUM
            if (r2 != r3) goto L_0x00f4
            int r5 = r5.zzcr()
            com.google.android.gms.internal.vision.zzfy$zze r7 = r6.d
            com.google.android.gms.internal.vision.zzgc<?> r7 = r7.a
            com.google.android.gms.internal.vision.zzgb r7 = r7.zzf(r5)
            if (r7 != 0) goto L_0x015f
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzhy.a((int) r0, (int) r5, r9, r10)
            return r5
        L_0x00f4:
            int[] r10 = com.google.android.gms.internal.vision.zzfn.a
            com.google.android.gms.internal.vision.zzfy$zze r0 = r6.d
            com.google.android.gms.internal.vision.zzjd r0 = r0.c
            int r0 = r0.ordinal()
            r10 = r10[r0]
            switch(r10) {
                case 1: goto L_0x017b;
                case 2: goto L_0x0172;
                case 3: goto L_0x0169;
                case 4: goto L_0x0164;
                case 5: goto L_0x015b;
                case 6: goto L_0x0156;
                case 7: goto L_0x0151;
                case 8: goto L_0x0148;
                case 9: goto L_0x0143;
                case 10: goto L_0x013e;
                case 11: goto L_0x0139;
                case 12: goto L_0x0134;
                case 13: goto L_0x012f;
                case 14: goto L_0x0127;
                case 15: goto L_0x0122;
                case 16: goto L_0x011d;
                case 17: goto L_0x0111;
                case 18: goto L_0x0105;
                default: goto L_0x0103;
            }
        L_0x0103:
            goto L_0x0183
        L_0x0105:
            com.google.android.gms.internal.vision.zzhf r10 = r6.c
            java.lang.Class r10 = r10.getClass()
            java.lang.Object r1 = r5.zza(r10, (com.google.android.gms.internal.vision.zzfk) r7)
            goto L_0x0183
        L_0x0111:
            com.google.android.gms.internal.vision.zzhf r10 = r6.c
            java.lang.Class r10 = r10.getClass()
            java.lang.Object r1 = r5.zzb(r10, r7)
            goto L_0x0183
        L_0x011d:
            java.lang.String r1 = r5.readString()
            goto L_0x0183
        L_0x0122:
            com.google.android.gms.internal.vision.zzeo r1 = r5.zzcw()
            goto L_0x0183
        L_0x0127:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Shouldn't reach here."
            r5.<init>(r6)
            throw r5
        L_0x012f:
            long r0 = r5.zzdc()
            goto L_0x016d
        L_0x0134:
            int r5 = r5.zzdb()
            goto L_0x015f
        L_0x0139:
            long r0 = r5.zzda()
            goto L_0x016d
        L_0x013e:
            int r5 = r5.zzcz()
            goto L_0x015f
        L_0x0143:
            int r5 = r5.zzcx()
            goto L_0x015f
        L_0x0148:
            boolean r5 = r5.zzcu()
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r5)
            goto L_0x0183
        L_0x0151:
            int r5 = r5.zzct()
            goto L_0x015f
        L_0x0156:
            long r0 = r5.zzcs()
            goto L_0x016d
        L_0x015b:
            int r5 = r5.zzcr()
        L_0x015f:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
            goto L_0x0183
        L_0x0164:
            long r0 = r5.zzcp()
            goto L_0x016d
        L_0x0169:
            long r0 = r5.zzcq()
        L_0x016d:
            java.lang.Long r1 = java.lang.Long.valueOf(r0)
            goto L_0x0183
        L_0x0172:
            float r5 = r5.readFloat()
            java.lang.Float r1 = java.lang.Float.valueOf(r5)
            goto L_0x0183
        L_0x017b:
            double r0 = r5.readDouble()
            java.lang.Double r1 = java.lang.Double.valueOf(r0)
        L_0x0183:
            com.google.android.gms.internal.vision.zzfy$zze r5 = r6.d
            boolean r5 = r5.d
            if (r5 == 0) goto L_0x018f
            com.google.android.gms.internal.vision.zzfy$zze r5 = r6.d
            r8.zzb(r5, (java.lang.Object) r1)
            goto L_0x01b0
        L_0x018f:
            int[] r5 = com.google.android.gms.internal.vision.zzfn.a
            com.google.android.gms.internal.vision.zzfy$zze r7 = r6.d
            com.google.android.gms.internal.vision.zzjd r7 = r7.c
            int r7 = r7.ordinal()
            r5 = r5[r7]
            switch(r5) {
                case 17: goto L_0x019f;
                case 18: goto L_0x019f;
                default: goto L_0x019e;
            }
        L_0x019e:
            goto L_0x01ab
        L_0x019f:
            com.google.android.gms.internal.vision.zzfy$zze r5 = r6.d
            java.lang.Object r5 = r8.zza(r5)
            if (r5 == 0) goto L_0x01ab
            java.lang.Object r1 = com.google.android.gms.internal.vision.zzga.a((java.lang.Object) r5, (java.lang.Object) r1)
        L_0x01ab:
            com.google.android.gms.internal.vision.zzfy$zze r5 = r6.d
            r8.zza(r5, (java.lang.Object) r1)
        L_0x01b0:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzfm.a(com.google.android.gms.internal.vision.zzhv, java.lang.Object, com.google.android.gms.internal.vision.zzfk, com.google.android.gms.internal.vision.zzfp, java.lang.Object, com.google.android.gms.internal.vision.zzio):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    public final void a(zzeo zzeo, Object obj, zzfk zzfk, zzfp<zzfy.zze> zzfp) {
        byte[] bArr;
        zzfy.zzf zzf = (zzfy.zzf) obj;
        zzhf zzff = zzf.c.zzfa().zzff();
        int size = zzeo.size();
        if (size == 0) {
            bArr = zzga.zzxn;
        } else {
            byte[] bArr2 = new byte[size];
            zzeo.a(bArr2, 0, 0, size);
            bArr = bArr2;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (wrap.hasArray()) {
            zzel zzel = new zzel(wrap, true);
            zzhs.zzgl().zzs(zzff).zza(zzff, zzel, zzfk);
            zzfp.zza(zzf.d, (Object) zzff);
            if (zzel.zzcn() != Integer.MAX_VALUE) {
                throw zzgf.e();
            }
            return;
        }
        throw new IllegalArgumentException("Direct buffers not yet supported");
    }

    /* access modifiers changed from: package-private */
    public final void a(zzhv zzhv, Object obj, zzfk zzfk, zzfp<zzfy.zze> zzfp) {
        zzfy.zzf zzf = (zzfy.zzf) obj;
        zzfp.zza(zzf.d, (Object) zzhv.zza(zzf.c.getClass(), zzfk));
    }

    /* access modifiers changed from: package-private */
    public final void a(zzjj zzjj, Map.Entry<?, ?> entry) {
        zzfy.zze zze = (zzfy.zze) entry.getKey();
        if (zze.d) {
            switch (zzfn.a[zze.c.ordinal()]) {
                case 1:
                    zzhy.zza(zze.b, (List<Double>) (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 2:
                    zzhy.zzb(zze.b, (List<Float>) (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 3:
                    zzhy.zzc(zze.b, (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 4:
                    zzhy.zzd(zze.b, (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 5:
                    zzhy.zzh(zze.b, (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 6:
                    zzhy.zzf(zze.b, (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 7:
                    zzhy.zzk(zze.b, (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 8:
                    zzhy.zzn(zze.b, (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 9:
                    zzhy.zzi(zze.b, (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 10:
                    zzhy.zzl(zze.b, (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 11:
                    zzhy.zzg(zze.b, (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 12:
                    zzhy.zzj(zze.b, (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 13:
                    zzhy.zze(zze.b, (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 14:
                    zzhy.zzh(zze.b, (List) entry.getValue(), zzjj, zze.e);
                    return;
                case 15:
                    zzhy.zzb(zze.b, (List) entry.getValue(), zzjj);
                    return;
                case 16:
                    zzhy.zza(zze.b, (List) entry.getValue(), zzjj);
                    return;
                case 17:
                    List list = (List) entry.getValue();
                    if (list != null && !list.isEmpty()) {
                        zzhy.zzb(zze.b, (List<?>) (List) entry.getValue(), zzjj, (zzhw) zzhs.zzgl().zzf(list.get(0).getClass()));
                        return;
                    }
                    return;
                case 18:
                    List list2 = (List) entry.getValue();
                    if (list2 != null && !list2.isEmpty()) {
                        zzhy.zza(zze.b, (List<?>) (List) entry.getValue(), zzjj, (zzhw) zzhs.zzgl().zzf(list2.get(0).getClass()));
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else {
            switch (zzfn.a[zze.c.ordinal()]) {
                case 1:
                    zzjj.zza(zze.b, ((Double) entry.getValue()).doubleValue());
                    return;
                case 2:
                    zzjj.zza(zze.b, ((Float) entry.getValue()).floatValue());
                    return;
                case 3:
                    zzjj.zzi(zze.b, ((Long) entry.getValue()).longValue());
                    return;
                case 4:
                    zzjj.zza(zze.b, ((Long) entry.getValue()).longValue());
                    return;
                case 5:
                    zzjj.zze(zze.b, ((Integer) entry.getValue()).intValue());
                    return;
                case 6:
                    zzjj.zzc(zze.b, ((Long) entry.getValue()).longValue());
                    return;
                case 7:
                    zzjj.zzh(zze.b, ((Integer) entry.getValue()).intValue());
                    return;
                case 8:
                    zzjj.zzb(zze.b, ((Boolean) entry.getValue()).booleanValue());
                    return;
                case 9:
                    zzjj.zzf(zze.b, ((Integer) entry.getValue()).intValue());
                    return;
                case 10:
                    zzjj.zzo(zze.b, ((Integer) entry.getValue()).intValue());
                    return;
                case 11:
                    zzjj.zzj(zze.b, ((Long) entry.getValue()).longValue());
                    return;
                case 12:
                    zzjj.zzg(zze.b, ((Integer) entry.getValue()).intValue());
                    return;
                case 13:
                    zzjj.zzb(zze.b, ((Long) entry.getValue()).longValue());
                    return;
                case 14:
                    zzjj.zze(zze.b, ((Integer) entry.getValue()).intValue());
                    return;
                case 15:
                    zzjj.zza(zze.b, (zzeo) entry.getValue());
                    return;
                case 16:
                    zzjj.zza(zze.b, (String) entry.getValue());
                    return;
                case 17:
                    zzjj.zzb(zze.b, (Object) entry.getValue(), (zzhw) zzhs.zzgl().zzf(entry.getValue().getClass()));
                    return;
                case 18:
                    zzjj.zza(zze.b, (Object) entry.getValue(), (zzhw) zzhs.zzgl().zzf(entry.getValue().getClass()));
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(Object obj, zzfp<zzfy.zze> zzfp) {
        ((zzfy.zzd) obj).zzwp = zzfp;
    }

    /* access modifiers changed from: package-private */
    public final boolean a(zzhf zzhf) {
        return zzhf instanceof zzfy.zzd;
    }

    /* access modifiers changed from: package-private */
    public final zzfp<zzfy.zze> b(Object obj) {
        zzfp<zzfy.zze> a = a(obj);
        if (!a.isImmutable()) {
            return a;
        }
        zzfp<zzfy.zze> zzfp = (zzfp) a.clone();
        a(obj, zzfp);
        return zzfp;
    }

    /* access modifiers changed from: package-private */
    public final void c(Object obj) {
        a(obj).zzci();
    }
}
