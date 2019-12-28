package com.google.android.gms.internal.firebase_ml;

public final class zzfo {
    private zzia zzsv;
    private final zzfv zzsy;
    private String zztb;
    private zzfi zzty;
    private zzfk zzuk;
    private zzfl zzul = new zzfl();
    private zzfl zzum = new zzfl();
    private int zzun = 10;
    private int zzuo = 16384;
    private boolean zzup = true;
    private boolean zzuq = true;
    private zzfh zzur;
    private zzfg zzus;
    private int zzut = 20000;
    private int zzuu = 20000;
    private zzfu zzuv;
    private boolean zzuw = true;
    private boolean zzux = true;
    @Deprecated
    private boolean zzuy = false;
    private zzic zzuz = zzic.zzaah;

    zzfo(zzfv zzfv, String str) {
        this.zzsy = zzfv;
        zzad((String) null);
    }

    public final String getRequestMethod() {
        return this.zztb;
    }

    public final zzfo zza(zzfg zzfg) {
        this.zzus = (zzfg) zzky.checkNotNull(zzfg);
        return this;
    }

    public final zzfo zza(zzfh zzfh) {
        this.zzur = zzfh;
        return this;
    }

    public final zzfo zza(zzfi zzfi) {
        this.zzty = zzfi;
        return this;
    }

    public final zzfo zza(zzfk zzfk) {
        this.zzuk = zzfk;
        return this;
    }

    public final zzfo zza(zzfu zzfu) {
        this.zzuv = zzfu;
        return this;
    }

    public final zzfo zza(zzia zzia) {
        this.zzsv = zzia;
        return this;
    }

    public final zzfo zzad(String str) {
        zzky.checkArgument(str == null || zzfn.a(str));
        this.zztb = str;
        return this;
    }

    public final zzfv zzej() {
        return this.zzsy;
    }

    public final zzfg zzek() {
        return this.zzus;
    }

    public final zzfh zzel() {
        return this.zzur;
    }

    public final int zzem() {
        return this.zzuo;
    }

    public final boolean zzen() {
        return this.zzup;
    }

    public final zzfl zzeo() {
        return this.zzul;
    }

    public final zzfl zzep() {
        return this.zzum;
    }

    public final zzfu zzeq() {
        return this.zzuv;
    }

    public final zzia zzer() {
        return this.zzsv;
    }

    public final boolean zzes() {
        return this.zzux;
    }

    /* JADX WARNING: type inference failed for: r13v2, types: [com.google.android.gms.internal.firebase_ml.zzie, com.google.android.gms.internal.firebase_ml.zzfj] */
    /* JADX WARNING: type inference failed for: r13v5, types: [com.google.android.gms.internal.firebase_ml.zzhy] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0297 A[LOOP:0: B:5:0x001a->B:114:0x0297, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0279 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01f8  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01fa  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0211 A[Catch:{ all -> 0x029a }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x026f A[Catch:{ all -> 0x029a }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0273  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.firebase_ml.zzfr zzet() {
        /*
            r17 = this;
            r1 = r17
            int r0 = r1.zzun
            if (r0 < 0) goto L_0x0008
            r0 = 1
            goto L_0x0009
        L_0x0008:
            r0 = 0
        L_0x0009:
            com.google.android.gms.internal.firebase_ml.zzky.checkArgument(r0)
            int r0 = r1.zzun
            java.lang.String r4 = r1.zztb
            com.google.android.gms.internal.firebase_ml.zzky.checkNotNull(r4)
            com.google.android.gms.internal.firebase_ml.zzfg r4 = r1.zzus
            com.google.android.gms.internal.firebase_ml.zzky.checkNotNull(r4)
            r5 = r0
            r0 = 0
        L_0x001a:
            if (r0 == 0) goto L_0x001f
            r0.ignore()
        L_0x001f:
            com.google.android.gms.internal.firebase_ml.zzfk r0 = r1.zzuk
            if (r0 == 0) goto L_0x0026
            r0.zzb(r1)
        L_0x0026:
            com.google.android.gms.internal.firebase_ml.zzfg r0 = r1.zzus
            java.lang.String r0 = r0.zzeg()
            com.google.android.gms.internal.firebase_ml.zzfv r6 = r1.zzsy
            java.lang.String r7 = r1.zztb
            com.google.android.gms.internal.firebase_ml.zzfw r6 = r6.a(r7, r0)
            java.util.logging.Logger r7 = com.google.android.gms.internal.firebase_ml.zzfv.a
            boolean r8 = r1.zzup
            if (r8 == 0) goto L_0x0044
            java.util.logging.Level r8 = java.util.logging.Level.CONFIG
            boolean r8 = r7.isLoggable(r8)
            if (r8 == 0) goto L_0x0044
            r8 = 1
            goto L_0x0045
        L_0x0044:
            r8 = 0
        L_0x0045:
            if (r8 == 0) goto L_0x0088
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "-------------- REQUEST  --------------"
            r9.append(r10)
            java.lang.String r10 = com.google.android.gms.internal.firebase_ml.zzif.zzaai
            r9.append(r10)
            java.lang.String r10 = r1.zztb
            r9.append(r10)
            r10 = 32
            r9.append(r10)
            r9.append(r0)
            java.lang.String r10 = com.google.android.gms.internal.firebase_ml.zzif.zzaai
            r9.append(r10)
            boolean r10 = r1.zzuq
            if (r10 == 0) goto L_0x0089
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "curl -v --compressed"
            r10.<init>(r11)
            java.lang.String r11 = r1.zztb
            java.lang.String r12 = "GET"
            boolean r11 = r11.equals(r12)
            if (r11 != 0) goto L_0x008a
            java.lang.String r11 = " -X "
            r10.append(r11)
            java.lang.String r11 = r1.zztb
            r10.append(r11)
            goto L_0x008a
        L_0x0088:
            r9 = 0
        L_0x0089:
            r10 = 0
        L_0x008a:
            com.google.android.gms.internal.firebase_ml.zzfl r11 = r1.zzul
            java.lang.String r11 = r11.zzeh()
            if (r11 != 0) goto L_0x0097
            com.google.android.gms.internal.firebase_ml.zzfl r12 = r1.zzul
            java.lang.String r13 = "Google-HTTP-Java-Client/1.26.0-SNAPSHOT (gzip)"
            goto L_0x00b4
        L_0x0097:
            com.google.android.gms.internal.firebase_ml.zzfl r12 = r1.zzul
            java.lang.String r13 = java.lang.String.valueOf(r11)
            int r13 = r13.length()
            int r13 = r13 + 47
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>(r13)
            r14.append(r11)
            java.lang.String r13 = " Google-HTTP-Java-Client/1.26.0-SNAPSHOT (gzip)"
            r14.append(r13)
            java.lang.String r13 = r14.toString()
        L_0x00b4:
            r12.zzab(r13)
            com.google.android.gms.internal.firebase_ml.zzfl r12 = r1.zzul
            com.google.android.gms.internal.firebase_ml.zzfl.a(r12, r9, r10, r7, r6)
            com.google.android.gms.internal.firebase_ml.zzfl r12 = r1.zzul
            r12.zzab(r11)
            com.google.android.gms.internal.firebase_ml.zzfh r11 = r1.zzur
            if (r11 == 0) goto L_0x00c8
            r11.zzef()
        L_0x00c8:
            if (r11 == 0) goto L_0x01bc
            com.google.android.gms.internal.firebase_ml.zzfh r12 = r1.zzur
            java.lang.String r12 = r12.getType()
            if (r8 == 0) goto L_0x00de
            com.google.android.gms.internal.firebase_ml.zzhy r13 = new com.google.android.gms.internal.firebase_ml.zzhy
            java.util.logging.Logger r14 = com.google.android.gms.internal.firebase_ml.zzfv.a
            java.util.logging.Level r15 = java.util.logging.Level.CONFIG
            int r2 = r1.zzuo
            r13.<init>(r11, r14, r15, r2)
            r11 = r13
        L_0x00de:
            com.google.android.gms.internal.firebase_ml.zzfi r2 = r1.zzty
            if (r2 != 0) goto L_0x00ea
            com.google.android.gms.internal.firebase_ml.zzfh r2 = r1.zzur
            long r13 = r2.getLength()
            r2 = 0
            goto L_0x00fb
        L_0x00ea:
            java.lang.String r2 = r2.getName()
            com.google.android.gms.internal.firebase_ml.zzfj r13 = new com.google.android.gms.internal.firebase_ml.zzfj
            com.google.android.gms.internal.firebase_ml.zzfi r14 = r1.zzty
            r13.<init>(r11, r14)
            long r14 = com.google.android.gms.internal.firebase_ml.zzht.zzb(r13)
            r11 = r13
            r13 = r14
        L_0x00fb:
            if (r8 == 0) goto L_0x01a9
            if (r12 == 0) goto L_0x0142
            java.lang.String r15 = "Content-Type: "
            java.lang.String r3 = java.lang.String.valueOf(r12)
            int r16 = r3.length()
            if (r16 == 0) goto L_0x0110
            java.lang.String r3 = r15.concat(r3)
            goto L_0x0115
        L_0x0110:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r15)
        L_0x0115:
            r9.append(r3)
            java.lang.String r15 = com.google.android.gms.internal.firebase_ml.zzif.zzaai
            r9.append(r15)
            if (r10 == 0) goto L_0x0142
            java.lang.String r15 = java.lang.String.valueOf(r3)
            int r15 = r15.length()
            int r15 = r15 + 6
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r15)
            java.lang.String r15 = " -H '"
            r4.append(r15)
            r4.append(r3)
            java.lang.String r3 = "'"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r10.append(r3)
        L_0x0142:
            if (r2 == 0) goto L_0x0188
            java.lang.String r3 = "Content-Encoding: "
            java.lang.String r4 = java.lang.String.valueOf(r2)
            int r15 = r4.length()
            if (r15 == 0) goto L_0x0155
            java.lang.String r3 = r3.concat(r4)
            goto L_0x015b
        L_0x0155:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r3)
            r3 = r4
        L_0x015b:
            r9.append(r3)
            java.lang.String r4 = com.google.android.gms.internal.firebase_ml.zzif.zzaai
            r9.append(r4)
            if (r10 == 0) goto L_0x0188
            java.lang.String r4 = java.lang.String.valueOf(r3)
            int r4 = r4.length()
            int r4 = r4 + 6
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>(r4)
            java.lang.String r4 = " -H '"
            r15.append(r4)
            r15.append(r3)
            java.lang.String r3 = "'"
            r15.append(r3)
            java.lang.String r3 = r15.toString()
            r10.append(r3)
        L_0x0188:
            r3 = 0
            int r15 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r15 < 0) goto L_0x01a9
            r3 = 36
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Content-Length: "
            r4.append(r3)
            r4.append(r13)
            java.lang.String r3 = r4.toString()
            r9.append(r3)
            java.lang.String r3 = com.google.android.gms.internal.firebase_ml.zzif.zzaai
            r9.append(r3)
        L_0x01a9:
            if (r10 == 0) goto L_0x01b0
            java.lang.String r3 = " -d '@-'"
            r10.append(r3)
        L_0x01b0:
            r6.setContentType(r12)
            r6.setContentEncoding(r2)
            r6.setContentLength(r13)
            r6.zza(r11)
        L_0x01bc:
            if (r8 == 0) goto L_0x01f6
            java.util.logging.Level r2 = java.util.logging.Level.CONFIG
            java.lang.String r3 = "com.google.api.client.http.HttpRequest"
            java.lang.String r4 = "execute"
            java.lang.String r8 = r9.toString()
            r7.logp(r2, r3, r4, r8)
            if (r10 == 0) goto L_0x01f6
            java.lang.String r2 = " -- '"
            r10.append(r2)
            java.lang.String r2 = "'"
            java.lang.String r3 = "'\"'\"'"
            java.lang.String r0 = r0.replaceAll(r2, r3)
            r10.append(r0)
            java.lang.String r0 = "'"
            r10.append(r0)
            if (r11 == 0) goto L_0x01e9
            java.lang.String r0 = " << $$$"
            r10.append(r0)
        L_0x01e9:
            java.util.logging.Level r0 = java.util.logging.Level.CONFIG
            java.lang.String r2 = "com.google.api.client.http.HttpRequest"
            java.lang.String r3 = "execute"
            java.lang.String r4 = r10.toString()
            r7.logp(r0, r2, r3, r4)
        L_0x01f6:
            if (r5 <= 0) goto L_0x01fa
            r0 = 1
            goto L_0x01fb
        L_0x01fa:
            r0 = 0
        L_0x01fb:
            int r2 = r1.zzut
            int r3 = r1.zzuu
            r6.zza(r2, r3)
            com.google.android.gms.internal.firebase_ml.zzfx r2 = r6.zzey()     // Catch:{ IOException -> 0x02aa }
            com.google.android.gms.internal.firebase_ml.zzfr r3 = new com.google.android.gms.internal.firebase_ml.zzfr     // Catch:{ all -> 0x029f }
            r3.<init>(r1, r2)     // Catch:{ all -> 0x029f }
            boolean r2 = r3.zzeu()     // Catch:{ all -> 0x029a }
            if (r2 != 0) goto L_0x0273
            int r2 = r3.getStatusCode()     // Catch:{ all -> 0x029a }
            com.google.android.gms.internal.firebase_ml.zzfl r4 = r3.zzeo()     // Catch:{ all -> 0x029a }
            java.lang.String r4 = r4.getLocation()     // Catch:{ all -> 0x029a }
            boolean r6 = r1.zzuw     // Catch:{ all -> 0x029a }
            if (r6 == 0) goto L_0x026a
            r6 = 307(0x133, float:4.3E-43)
            if (r2 == r6) goto L_0x022a
            switch(r2) {
                case 301: goto L_0x022a;
                case 302: goto L_0x022a;
                case 303: goto L_0x022a;
                default: goto L_0x0228;
            }     // Catch:{ all -> 0x029a }
        L_0x0228:
            r6 = 0
            goto L_0x022b
        L_0x022a:
            r6 = 1
        L_0x022b:
            if (r6 == 0) goto L_0x026a
            if (r4 == 0) goto L_0x026a
            com.google.android.gms.internal.firebase_ml.zzfg r6 = new com.google.android.gms.internal.firebase_ml.zzfg     // Catch:{ all -> 0x029a }
            com.google.android.gms.internal.firebase_ml.zzfg r7 = r1.zzus     // Catch:{ all -> 0x029a }
            java.net.URL r4 = r7.zzr(r4)     // Catch:{ all -> 0x029a }
            r6.<init>((java.net.URL) r4)     // Catch:{ all -> 0x029a }
            r1.zza((com.google.android.gms.internal.firebase_ml.zzfg) r6)     // Catch:{ all -> 0x029a }
            r4 = 303(0x12f, float:4.25E-43)
            if (r2 != r4) goto L_0x0249
            java.lang.String r2 = "GET"
            r1.zzad(r2)     // Catch:{ all -> 0x029a }
            r2 = 0
            r1.zzur = r2     // Catch:{ all -> 0x029a }
        L_0x0249:
            com.google.android.gms.internal.firebase_ml.zzfl r2 = r1.zzul     // Catch:{ all -> 0x029a }
            r4 = 0
            r2.zzv(r4)     // Catch:{ all -> 0x029a }
            com.google.android.gms.internal.firebase_ml.zzfl r2 = r1.zzul     // Catch:{ all -> 0x029a }
            r2.zzx(r4)     // Catch:{ all -> 0x029a }
            com.google.android.gms.internal.firebase_ml.zzfl r2 = r1.zzul     // Catch:{ all -> 0x029a }
            r2.zzy(r4)     // Catch:{ all -> 0x029a }
            com.google.android.gms.internal.firebase_ml.zzfl r2 = r1.zzul     // Catch:{ all -> 0x029a }
            r2.zzw(r4)     // Catch:{ all -> 0x029a }
            com.google.android.gms.internal.firebase_ml.zzfl r2 = r1.zzul     // Catch:{ all -> 0x029a }
            r2.zzz(r4)     // Catch:{ all -> 0x029a }
            com.google.android.gms.internal.firebase_ml.zzfl r2 = r1.zzul     // Catch:{ all -> 0x029a }
            r2.zzaa(r4)     // Catch:{ all -> 0x029a }
            r2 = 1
            goto L_0x026c
        L_0x026a:
            r4 = 0
            r2 = 0
        L_0x026c:
            r0 = r0 & r2
            if (r0 == 0) goto L_0x0275
            r3.ignore()     // Catch:{ all -> 0x029a }
            goto L_0x0275
        L_0x0273:
            r4 = 0
            r0 = 0
        L_0x0275:
            int r5 = r5 + -1
            if (r0 != 0) goto L_0x0297
            com.google.android.gms.internal.firebase_ml.zzfu r0 = r1.zzuv
            if (r0 == 0) goto L_0x0280
            r0.zzb(r3)
        L_0x0280:
            boolean r0 = r1.zzux
            if (r0 == 0) goto L_0x0296
            boolean r0 = r3.zzeu()
            if (r0 == 0) goto L_0x028b
            goto L_0x0296
        L_0x028b:
            com.google.android.gms.internal.firebase_ml.zzfs r0 = new com.google.android.gms.internal.firebase_ml.zzfs     // Catch:{ all -> 0x0291 }
            r0.<init>((com.google.android.gms.internal.firebase_ml.zzfr) r3)     // Catch:{ all -> 0x0291 }
            throw r0     // Catch:{ all -> 0x0291 }
        L_0x0291:
            r0 = move-exception
            r3.disconnect()
            throw r0
        L_0x0296:
            return r3
        L_0x0297:
            r0 = r3
            goto L_0x001a
        L_0x029a:
            r0 = move-exception
            r3.disconnect()
            throw r0
        L_0x029f:
            r0 = move-exception
            java.io.InputStream r2 = r2.getContent()     // Catch:{ IOException -> 0x02aa }
            if (r2 == 0) goto L_0x02a9
            r2.close()     // Catch:{ IOException -> 0x02aa }
        L_0x02a9:
            throw r0     // Catch:{ IOException -> 0x02aa }
        L_0x02aa:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzfo.zzet():com.google.android.gms.internal.firebase_ml.zzfr");
    }

    public final zzfo zzx(int i) {
        zzky.checkArgument(true);
        this.zzut = 5000;
        return this;
    }

    public final zzfo zzy(int i) {
        zzky.checkArgument(true);
        this.zzuu = 10000;
        return this;
    }
}
