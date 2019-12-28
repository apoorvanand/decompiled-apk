package com.google.android.gms.internal.vision;

public final class zzdn extends zzjn<zzdn> {
    public Integer zzow;
    public Integer zzox;
    public Integer zzoy;
    public Boolean zzoz = null;
    public Boolean zzpa = null;
    public Float zzpb = null;

    public zzdn() {
        this.b = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0077, code lost:
        throw new java.lang.IllegalArgumentException(r5.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a2, code lost:
        throw new java.lang.IllegalArgumentException(r5.toString());
     */
    /* renamed from: zzd */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.vision.zzdn zza(com.google.android.gms.internal.vision.zzjk r7) {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r7.zzdq()
            if (r0 == 0) goto L_0x00d8
            r1 = 8
            r2 = 3
            if (r0 == r1) goto L_0x00a3
            r1 = 16
            r3 = 40
            if (r0 == r1) goto L_0x0078
            r1 = 24
            if (r0 == r1) goto L_0x004b
            r1 = 32
            if (r0 == r1) goto L_0x0040
            if (r0 == r3) goto L_0x0035
            r1 = 53
            if (r0 == r1) goto L_0x0026
            boolean r0 = super.a(r7, r0)
            if (r0 != 0) goto L_0x0000
            return r6
        L_0x0026:
            int r0 = r7.zzdv()
            float r0 = java.lang.Float.intBitsToFloat(r0)
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            r6.zzpb = r0
            goto L_0x0000
        L_0x0035:
            boolean r0 = r7.zzcu()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.zzpa = r0
            goto L_0x0000
        L_0x0040:
            boolean r0 = r7.zzcu()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.zzoz = r0
            goto L_0x0000
        L_0x004b:
            int r1 = r7.getPosition()
            int r2 = r7.zzdt()     // Catch:{ IllegalArgumentException -> 0x00d0 }
            if (r2 < 0) goto L_0x005f
            r3 = 2
            if (r2 > r3) goto L_0x005f
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r6.zzoy = r2     // Catch:{ IllegalArgumentException -> 0x00d0 }
            goto L_0x0000
        L_0x005f:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r4 = 46
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            java.lang.String r2 = " is not a valid enum Classification"
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            java.lang.String r2 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            throw r3     // Catch:{ IllegalArgumentException -> 0x00d0 }
        L_0x0078:
            int r1 = r7.getPosition()
            int r4 = r7.zzdt()     // Catch:{ IllegalArgumentException -> 0x00d0 }
            if (r4 < 0) goto L_0x008c
            if (r4 > r2) goto L_0x008c
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r6.zzox = r2     // Catch:{ IllegalArgumentException -> 0x00d0 }
            goto L_0x0000
        L_0x008c:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x00d0 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r5.<init>(r3)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r5.append(r4)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            java.lang.String r3 = " is not a valid enum Landmark"
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            java.lang.String r3 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r2.<init>(r3)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            throw r2     // Catch:{ IllegalArgumentException -> 0x00d0 }
        L_0x00a3:
            int r1 = r7.getPosition()
            int r3 = r7.zzdt()     // Catch:{ IllegalArgumentException -> 0x00d0 }
            if (r3 < 0) goto L_0x00b7
            if (r3 > r2) goto L_0x00b7
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r6.zzow = r2     // Catch:{ IllegalArgumentException -> 0x00d0 }
            goto L_0x0000
        L_0x00b7:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r4 = 36
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            java.lang.String r3 = " is not a valid enum Mode"
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            java.lang.String r3 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x00d0 }
            r2.<init>(r3)     // Catch:{ IllegalArgumentException -> 0x00d0 }
            throw r2     // Catch:{ IllegalArgumentException -> 0x00d0 }
        L_0x00d0:
            r7.zzbt(r1)
            r6.a(r7, r0)
            goto L_0x0000
        L_0x00d8:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzdn.zza(com.google.android.gms.internal.vision.zzjk):com.google.android.gms.internal.vision.zzdn");
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        Integer num = this.zzow;
        if (num != null) {
            a += zzjl.zzi(1, num.intValue());
        }
        Integer num2 = this.zzox;
        if (num2 != null) {
            a += zzjl.zzi(2, num2.intValue());
        }
        Integer num3 = this.zzoy;
        if (num3 != null) {
            a += zzjl.zzi(3, num3.intValue());
        }
        Boolean bool = this.zzoz;
        if (bool != null) {
            bool.booleanValue();
            a += zzjl.zzav(4) + 1;
        }
        Boolean bool2 = this.zzpa;
        if (bool2 != null) {
            bool2.booleanValue();
            a += zzjl.zzav(5) + 1;
        }
        Float f = this.zzpb;
        if (f == null) {
            return a;
        }
        f.floatValue();
        return a + zzjl.zzav(6) + 4;
    }

    public final void zza(zzjl zzjl) {
        Integer num = this.zzow;
        if (num != null) {
            zzjl.zze(1, num.intValue());
        }
        Integer num2 = this.zzox;
        if (num2 != null) {
            zzjl.zze(2, num2.intValue());
        }
        Integer num3 = this.zzoy;
        if (num3 != null) {
            zzjl.zze(3, num3.intValue());
        }
        Boolean bool = this.zzoz;
        if (bool != null) {
            zzjl.zzb(4, bool.booleanValue());
        }
        Boolean bool2 = this.zzpa;
        if (bool2 != null) {
            zzjl.zzb(5, bool2.booleanValue());
        }
        Float f = this.zzpb;
        if (f != null) {
            zzjl.zza(6, f.floatValue());
        }
        super.zza(zzjl);
    }
}
