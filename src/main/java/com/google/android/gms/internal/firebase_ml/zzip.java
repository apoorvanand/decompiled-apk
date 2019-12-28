package com.google.android.gms.internal.firebase_ml;

public abstract class zzip extends zzil {
    private static char[] zza(char[] cArr, int i, int i2) {
        char[] cArr2 = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i);
        }
        return cArr2;
    }

    /* access modifiers changed from: protected */
    public abstract int a(CharSequence charSequence, int i, int i2);

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: char} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String a(java.lang.String r12, int r13) {
        /*
            r11 = this;
            int r0 = r12.length()
            char[] r1 = com.google.android.gms.internal.firebase_ml.zzin.a()
            r2 = 0
            r4 = r1
            r1 = 0
            r3 = 0
        L_0x000c:
            if (r13 >= r0) goto L_0x00d4
            if (r13 >= r0) goto L_0x00cc
            int r5 = r13 + 1
            char r6 = r12.charAt(r13)
            r7 = 55296(0xd800, float:7.7486E-41)
            r8 = 1
            if (r6 < r7) goto L_0x008d
            r7 = 57343(0xdfff, float:8.0355E-41)
            if (r6 <= r7) goto L_0x0022
            goto L_0x008d
        L_0x0022:
            r7 = 56319(0xdbff, float:7.892E-41)
            if (r6 > r7) goto L_0x0063
            if (r5 != r0) goto L_0x002b
            int r6 = -r6
            goto L_0x008d
        L_0x002b:
            char r7 = r12.charAt(r5)
            boolean r9 = java.lang.Character.isLowSurrogate(r7)
            if (r9 == 0) goto L_0x003a
            int r6 = java.lang.Character.toCodePoint(r6, r7)
            goto L_0x008d
        L_0x003a:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r13 = 83
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r13)
            java.lang.String r13 = "Expected low surrogate but got char '"
            r0.append(r13)
            r0.append(r7)
            java.lang.String r13 = "' with value "
            r0.append(r13)
            r0.append(r7)
            java.lang.String r13 = " at index "
            r0.append(r13)
            r0.append(r5)
            java.lang.String r13 = r0.toString()
            r12.<init>(r13)
            throw r12
        L_0x0063:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            int r5 = r5 - r8
            r13 = 82
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r13)
            java.lang.String r13 = "Unexpected low surrogate character '"
            r0.append(r13)
            r0.append(r6)
            java.lang.String r13 = "' with value "
            r0.append(r13)
            r0.append(r6)
            java.lang.String r13 = " at index "
            r0.append(r13)
            r0.append(r5)
            java.lang.String r13 = r0.toString()
            r12.<init>(r13)
            throw r12
        L_0x008d:
            if (r6 < 0) goto L_0x00c4
            char[] r5 = r11.a(r6)
            boolean r6 = java.lang.Character.isSupplementaryCodePoint(r6)
            if (r6 == 0) goto L_0x009a
            r8 = 2
        L_0x009a:
            int r8 = r8 + r13
            if (r5 == 0) goto L_0x00be
            int r6 = r13 - r1
            int r7 = r3 + r6
            int r9 = r5.length
            int r9 = r9 + r7
            int r10 = r4.length
            if (r10 >= r9) goto L_0x00ae
            int r9 = r9 + r0
            int r9 = r9 - r13
            int r9 = r9 + 32
            char[] r4 = zza(r4, r3, r9)
        L_0x00ae:
            if (r6 <= 0) goto L_0x00b4
            r12.getChars(r1, r13, r4, r3)
            r3 = r7
        L_0x00b4:
            int r13 = r5.length
            if (r13 <= 0) goto L_0x00bd
            int r13 = r5.length
            java.lang.System.arraycopy(r5, r2, r4, r3, r13)
            int r13 = r5.length
            int r3 = r3 + r13
        L_0x00bd:
            r1 = r8
        L_0x00be:
            int r13 = r11.a(r12, r8, r0)
            goto L_0x000c
        L_0x00c4:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trailing high surrogate at end of input"
            r12.<init>(r13)
            throw r12
        L_0x00cc:
            java.lang.IndexOutOfBoundsException r12 = new java.lang.IndexOutOfBoundsException
            java.lang.String r13 = "Index exceeds specified range"
            r12.<init>(r13)
            throw r12
        L_0x00d4:
            int r13 = r0 - r1
            if (r13 <= 0) goto L_0x00e4
            int r13 = r13 + r3
            int r5 = r4.length
            if (r5 >= r13) goto L_0x00e0
            char[] r4 = zza(r4, r3, r13)
        L_0x00e0:
            r12.getChars(r1, r0, r4, r3)
            goto L_0x00e5
        L_0x00e4:
            r13 = r3
        L_0x00e5:
            java.lang.String r12 = new java.lang.String
            r12.<init>(r4, r2, r13)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzip.a(java.lang.String, int):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public abstract char[] a(int i);
}
