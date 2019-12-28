package com.google.android.gms.internal.firebase_ml;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import kotlin.text.Typography;

public final class zzpl implements Closeable {
    private static final char[] zzaxr = ")]}'\n".toCharArray();
    private final Reader in;
    private int limit = 0;
    private int pos = 0;
    private boolean zzaxs = false;
    private final char[] zzaxt = new char[1024];
    private int zzaxu = 0;
    private int zzaxv = 0;
    private int zzaxw = 0;
    private long zzaxx;
    private int zzaxy;
    private String zzaxz;
    private int[] zzaya = new int[32];
    private int zzayb = 0;
    private String[] zzayc;
    private int[] zzayd;

    static {
        zzpk.zzaxq = new zzpm();
    }

    public zzpl(Reader reader) {
        int[] iArr = this.zzaya;
        int i = this.zzayb;
        this.zzayb = i + 1;
        iArr[i] = 6;
        this.zzayc = new String[32];
        this.zzayd = new int[32];
        if (reader != null) {
            this.in = reader;
            return;
        }
        throw new NullPointerException("in == null");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0051, code lost:
        if (r1 != '/') goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0053, code lost:
        r9.pos = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        if (r4 != r2) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0058, code lost:
        r9.pos--;
        r2 = zzbk(2);
        r9.pos++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0066, code lost:
        if (r2 != false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0068, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0069, code lost:
        zzmh();
        r2 = r9.pos;
        r4 = r0[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0072, code lost:
        if (r4 == '*') goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0074, code lost:
        if (r4 == '/') goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0076, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0077, code lost:
        r9.pos = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007c, code lost:
        r9.pos = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0082, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0088, code lost:
        if ((r9.pos + 2) <= r9.limit) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008e, code lost:
        if (zzbk(2) == false) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0091, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0093, code lost:
        r2 = r9.zzaxt;
        r4 = r9.pos;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0099, code lost:
        if (r2[r4] != 10) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009b, code lost:
        r9.zzaxu++;
        r9.zzaxv = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a5, code lost:
        if (r6 >= 2) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b2, code lost:
        if (r9.zzaxt[r9.pos + r6] != "*/".charAt(r6)) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b4, code lost:
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b7, code lost:
        r9.pos++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00bd, code lost:
        if (r3 == false) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ca, code lost:
        throw zzcd("Unterminated comment");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00cd, code lost:
        if (r1 != '#') goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00cf, code lost:
        r9.pos = r4;
        zzmh();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d9, code lost:
        r9.pos = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00db, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzae(boolean r10) {
        /*
            r9 = this;
            char[] r0 = r9.zzaxt
        L_0x0002:
            int r1 = r9.pos
        L_0x0004:
            int r2 = r9.limit
        L_0x0006:
            r3 = 1
            if (r1 != r2) goto L_0x0032
            r9.pos = r1
            boolean r1 = r9.zzbk(r3)
            if (r1 == 0) goto L_0x0016
            int r1 = r9.pos
            int r2 = r9.limit
            goto L_0x0032
        L_0x0016:
            if (r10 != 0) goto L_0x001a
            r10 = -1
            return r10
        L_0x001a:
            java.io.EOFException r10 = new java.io.EOFException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "End of input"
            r0.<init>(r1)
            java.lang.String r1 = r9.zzmj()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r10.<init>(r0)
            throw r10
        L_0x0032:
            int r4 = r1 + 1
            char r1 = r0[r1]
            r5 = 10
            if (r1 != r5) goto L_0x0043
            int r1 = r9.zzaxu
            int r1 = r1 + r3
            r9.zzaxu = r1
            r9.zzaxv = r4
            goto L_0x00dc
        L_0x0043:
            r6 = 32
            if (r1 == r6) goto L_0x00dc
            r6 = 13
            if (r1 == r6) goto L_0x00dc
            r6 = 9
            if (r1 == r6) goto L_0x00dc
            r6 = 47
            if (r1 != r6) goto L_0x00cb
            r9.pos = r4
            r7 = 2
            if (r4 != r2) goto L_0x0069
            int r2 = r9.pos
            int r2 = r2 - r3
            r9.pos = r2
            boolean r2 = r9.zzbk(r7)
            int r4 = r9.pos
            int r4 = r4 + r3
            r9.pos = r4
            if (r2 != 0) goto L_0x0069
            return r1
        L_0x0069:
            r9.zzmh()
            int r2 = r9.pos
            char r4 = r0[r2]
            r8 = 42
            if (r4 == r8) goto L_0x007c
            if (r4 == r6) goto L_0x0077
            return r1
        L_0x0077:
            int r2 = r2 + 1
            r9.pos = r2
            goto L_0x00d4
        L_0x007c:
            int r2 = r2 + 1
            r9.pos = r2
            java.lang.String r1 = "*/"
        L_0x0082:
            int r2 = r9.pos
            int r2 = r2 + r7
            int r4 = r9.limit
            r6 = 0
            if (r2 <= r4) goto L_0x0093
            boolean r2 = r9.zzbk(r7)
            if (r2 == 0) goto L_0x0091
            goto L_0x0093
        L_0x0091:
            r3 = 0
            goto L_0x00bd
        L_0x0093:
            char[] r2 = r9.zzaxt
            int r4 = r9.pos
            char r2 = r2[r4]
            if (r2 != r5) goto L_0x00a5
            int r2 = r9.zzaxu
            int r2 = r2 + r3
            r9.zzaxu = r2
            int r4 = r4 + 1
            r9.zzaxv = r4
            goto L_0x00b7
        L_0x00a5:
            if (r6 >= r7) goto L_0x00bd
            char[] r2 = r9.zzaxt
            int r4 = r9.pos
            int r4 = r4 + r6
            char r2 = r2[r4]
            char r4 = r1.charAt(r6)
            if (r2 != r4) goto L_0x00b7
            int r6 = r6 + 1
            goto L_0x00a5
        L_0x00b7:
            int r2 = r9.pos
            int r2 = r2 + r3
            r9.pos = r2
            goto L_0x0082
        L_0x00bd:
            if (r3 == 0) goto L_0x00c4
            int r1 = r9.pos
            int r1 = r1 + r7
            goto L_0x0004
        L_0x00c4:
            java.lang.String r10 = "Unterminated comment"
            java.io.IOException r10 = r9.zzcd(r10)
            throw r10
        L_0x00cb:
            r2 = 35
            if (r1 != r2) goto L_0x00d9
            r9.pos = r4
            r9.zzmh()
        L_0x00d4:
            r9.zzmi()
            goto L_0x0002
        L_0x00d9:
            r9.pos = r4
            return r1
        L_0x00dc:
            r1 = r4
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzpl.zzae(boolean):int");
    }

    private final void zzbj(int i) {
        int i2 = this.zzayb;
        int[] iArr = this.zzaya;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[(i2 << 1)];
            int[] iArr3 = new int[(i2 << 1)];
            String[] strArr = new String[(i2 << 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.zzayd, 0, iArr3, 0, this.zzayb);
            System.arraycopy(this.zzayc, 0, strArr, 0, this.zzayb);
            this.zzaya = iArr2;
            this.zzayd = iArr3;
            this.zzayc = strArr;
        }
        int[] iArr4 = this.zzaya;
        int i3 = this.zzayb;
        this.zzayb = i3 + 1;
        iArr4[i3] = i;
    }

    private final boolean zzbk(int i) {
        int i2;
        char[] cArr = this.zzaxt;
        int i3 = this.zzaxv;
        int i4 = this.pos;
        this.zzaxv = i3 - i4;
        int i5 = this.limit;
        if (i5 != i4) {
            this.limit = i5 - i4;
            System.arraycopy(cArr, i4, cArr, 0, this.limit);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            Reader reader = this.in;
            int i6 = this.limit;
            int read = reader.read(cArr, i6, cArr.length - i6);
            if (read == -1) {
                return false;
            }
            this.limit += read;
            if (this.zzaxu == 0 && (i2 = this.zzaxv) == 0 && this.limit > 0 && cArr[0] == 65279) {
                this.pos++;
                this.zzaxv = i2 + 1;
                i++;
            }
        } while (this.limit < i);
        return true;
    }

    private final IOException zzcd(String str) {
        throw new zzpp(str + zzmj());
    }

    private final boolean zze(char c) {
        switch (c) {
            case 9:
            case 10:
            case 12:
            case 13:
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case '{':
            case '}':
                return false;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                zzmh();
                return false;
            default:
                return true;
        }
    }

    private final String zzf(char c) {
        char[] cArr = this.zzaxt;
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = this.pos;
            int i2 = this.limit;
            int i3 = i;
            while (true) {
                if (i3 < i2) {
                    int i4 = i3 + 1;
                    char c2 = cArr[i3];
                    if (c2 == c) {
                        this.pos = i4;
                        sb.append(cArr, i, (i4 - i) - 1);
                        return sb.toString();
                    } else if (c2 == '\\') {
                        this.pos = i4;
                        sb.append(cArr, i, (i4 - i) - 1);
                        sb.append(zzmk());
                        break;
                    } else {
                        if (c2 == 10) {
                            this.zzaxu++;
                            this.zzaxv = i4;
                        }
                        i3 = i4;
                    }
                } else {
                    sb.append(cArr, i, i3 - i);
                    this.pos = i3;
                    if (!zzbk(1)) {
                        throw zzcd("Unterminated string");
                    }
                }
            }
        }
    }

    private final void zzg(char c) {
        char[] cArr = this.zzaxt;
        while (true) {
            int i = this.pos;
            int i2 = this.limit;
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    char c2 = cArr[i];
                    if (c2 == c) {
                        this.pos = i3;
                        return;
                    } else if (c2 == '\\') {
                        this.pos = i3;
                        zzmk();
                        break;
                    } else {
                        if (c2 == 10) {
                            this.zzaxu++;
                            this.zzaxv = i3;
                        }
                        i = i3;
                    }
                } else {
                    this.pos = i;
                    if (!zzbk(1)) {
                        throw zzcd("Unterminated string");
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:107:0x019d, code lost:
        r1 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0208, code lost:
        if (zze(r5) == false) goto L_0x019d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x020b, code lost:
        if (r7 != 2) goto L_0x022a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x020d, code lost:
        if (r8 == false) goto L_0x0229;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0213, code lost:
        if (r12 != Long.MIN_VALUE) goto L_0x0217;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0215, code lost:
        if (r9 == false) goto L_0x0229;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x0217, code lost:
        if (r9 == false) goto L_0x021a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x021a, code lost:
        r12 = -r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x021b, code lost:
        r0.zzaxx = r12;
        r0.pos += r3;
        r0.zzaxw = 15;
        r16 = 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0229, code lost:
        r1 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x022a, code lost:
        if (r7 == r1) goto L_0x0236;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x022d, code lost:
        if (r7 == 4) goto L_0x0236;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0230, code lost:
        if (r7 != 7) goto L_0x0233;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0236, code lost:
        r0.zzaxy = r3;
        r0.zzaxw = 16;
        r16 = 16;
     */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x02ab  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x017a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x017b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzmf() {
        /*
            r19 = this;
            r0 = r19
            int[] r1 = r0.zzaya
            int r2 = r0.zzayb
            int r3 = r2 + -1
            r3 = r1[r3]
            r4 = 8
            r7 = 93
            r8 = 59
            r9 = 44
            r10 = 3
            r11 = 6
            r12 = 7
            r13 = 4
            r14 = 5
            r15 = 2
            r5 = 0
            r6 = 1
            if (r3 != r6) goto L_0x0021
            int r2 = r2 - r6
            r1[r2] = r15
            goto L_0x00d2
        L_0x0021:
            if (r3 != r15) goto L_0x003c
            int r1 = r0.zzae(r6)
            if (r1 == r9) goto L_0x00d2
            if (r1 == r8) goto L_0x0037
            if (r1 != r7) goto L_0x0030
            r0.zzaxw = r13
            return r13
        L_0x0030:
            java.lang.String r1 = "Unterminated array"
            java.io.IOException r1 = r0.zzcd(r1)
            throw r1
        L_0x0037:
            r19.zzmh()
            goto L_0x00d2
        L_0x003c:
            if (r3 == r10) goto L_0x02b7
            if (r3 != r14) goto L_0x0042
            goto L_0x02b7
        L_0x0042:
            if (r3 != r13) goto L_0x0077
            int r2 = r2 - r6
            r1[r2] = r14
            int r1 = r0.zzae(r6)
            r2 = 58
            if (r1 == r2) goto L_0x00d2
            r2 = 61
            if (r1 != r2) goto L_0x0070
            r19.zzmh()
            int r1 = r0.pos
            int r2 = r0.limit
            if (r1 < r2) goto L_0x0062
            boolean r1 = r0.zzbk(r6)
            if (r1 == 0) goto L_0x00d2
        L_0x0062:
            char[] r1 = r0.zzaxt
            int r2 = r0.pos
            char r1 = r1[r2]
            r13 = 62
            if (r1 != r13) goto L_0x00d2
            int r2 = r2 + r6
            r0.pos = r2
            goto L_0x00d2
        L_0x0070:
            java.lang.String r1 = "Expected ':'"
            java.io.IOException r1 = r0.zzcd(r1)
            throw r1
        L_0x0077:
            if (r3 != r11) goto L_0x00b9
            boolean r1 = r0.zzaxs
            if (r1 == 0) goto L_0x00b1
            r0.zzae(r6)
            int r1 = r0.pos
            int r1 = r1 - r6
            r0.pos = r1
            int r1 = r0.pos
            char[] r2 = zzaxr
            int r13 = r2.length
            int r1 = r1 + r13
            int r13 = r0.limit
            if (r1 <= r13) goto L_0x0096
            int r1 = r2.length
            boolean r1 = r0.zzbk(r1)
            if (r1 == 0) goto L_0x00b1
        L_0x0096:
            r1 = 0
        L_0x0097:
            char[] r2 = zzaxr
            int r13 = r2.length
            if (r1 >= r13) goto L_0x00ab
            char[] r13 = r0.zzaxt
            int r11 = r0.pos
            int r11 = r11 + r1
            char r11 = r13[r11]
            char r2 = r2[r1]
            if (r11 != r2) goto L_0x00b1
            int r1 = r1 + 1
            r11 = 6
            goto L_0x0097
        L_0x00ab:
            int r1 = r0.pos
            int r2 = r2.length
            int r1 = r1 + r2
            r0.pos = r1
        L_0x00b1:
            int[] r1 = r0.zzaya
            int r2 = r0.zzayb
            int r2 = r2 - r6
            r1[r2] = r12
            goto L_0x00d2
        L_0x00b9:
            if (r3 != r12) goto L_0x00d0
            int r1 = r0.zzae(r5)
            r2 = -1
            if (r1 != r2) goto L_0x00c7
            r1 = 17
        L_0x00c4:
            r0.zzaxw = r1
            return r1
        L_0x00c7:
            r19.zzmh()
            int r1 = r0.pos
            int r1 = r1 - r6
            r0.pos = r1
            goto L_0x00d2
        L_0x00d0:
            if (r3 == r4) goto L_0x02af
        L_0x00d2:
            int r1 = r0.zzae(r6)
            r2 = 34
            if (r1 == r2) goto L_0x02ab
            r2 = 39
            if (r1 == r2) goto L_0x02a5
            if (r1 == r9) goto L_0x028d
            if (r1 == r8) goto L_0x028d
            r2 = 91
            if (r1 == r2) goto L_0x028a
            if (r1 == r7) goto L_0x0285
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L_0x0282
            int r1 = r0.pos
            int r1 = r1 - r6
            r0.pos = r1
            char[] r1 = r0.zzaxt
            int r2 = r0.pos
            char r1 = r1[r2]
            r2 = 116(0x74, float:1.63E-43)
            if (r1 == r2) goto L_0x011f
            r2 = 84
            if (r1 != r2) goto L_0x0100
            goto L_0x011f
        L_0x0100:
            r2 = 102(0x66, float:1.43E-43)
            if (r1 == r2) goto L_0x0118
            r2 = 70
            if (r1 != r2) goto L_0x0109
            goto L_0x0118
        L_0x0109:
            r2 = 110(0x6e, float:1.54E-43)
            if (r1 == r2) goto L_0x0111
            r2 = 78
            if (r1 != r2) goto L_0x016f
        L_0x0111:
            java.lang.String r1 = "null"
            java.lang.String r2 = "NULL"
            r3 = r2
            r2 = 7
            goto L_0x0125
        L_0x0118:
            java.lang.String r1 = "false"
            java.lang.String r2 = "FALSE"
            r3 = r2
            r2 = 6
            goto L_0x0125
        L_0x011f:
            java.lang.String r1 = "true"
            java.lang.String r2 = "TRUE"
            r3 = r2
            r2 = 5
        L_0x0125:
            int r4 = r1.length()
            r7 = 1
        L_0x012a:
            if (r7 >= r4) goto L_0x0153
            int r8 = r0.pos
            int r8 = r8 + r7
            int r9 = r0.limit
            if (r8 < r9) goto L_0x013c
            int r8 = r7 + 1
            boolean r8 = r0.zzbk(r8)
            if (r8 != 0) goto L_0x013c
            goto L_0x016f
        L_0x013c:
            char[] r8 = r0.zzaxt
            int r9 = r0.pos
            int r9 = r9 + r7
            char r8 = r8[r9]
            char r9 = r1.charAt(r7)
            if (r8 == r9) goto L_0x0150
            char r9 = r3.charAt(r7)
            if (r8 == r9) goto L_0x0150
            goto L_0x016f
        L_0x0150:
            int r7 = r7 + 1
            goto L_0x012a
        L_0x0153:
            int r1 = r0.pos
            int r1 = r1 + r4
            int r3 = r0.limit
            if (r1 < r3) goto L_0x0162
            int r1 = r4 + 1
            boolean r1 = r0.zzbk(r1)
            if (r1 == 0) goto L_0x0171
        L_0x0162:
            char[] r1 = r0.zzaxt
            int r3 = r0.pos
            int r3 = r3 + r4
            char r1 = r1[r3]
            boolean r1 = r0.zze(r1)
            if (r1 == 0) goto L_0x0171
        L_0x016f:
            r2 = 0
            goto L_0x0178
        L_0x0171:
            int r1 = r0.pos
            int r1 = r1 + r4
            r0.pos = r1
            r0.zzaxw = r2
        L_0x0178:
            if (r2 == 0) goto L_0x017b
            return r2
        L_0x017b:
            char[] r1 = r0.zzaxt
            int r2 = r0.pos
            int r3 = r0.limit
            r7 = 0
            r4 = r3
            r12 = r7
            r3 = 0
            r7 = 0
            r8 = 1
            r9 = 0
        L_0x0189:
            int r5 = r2 + r3
            if (r5 != r4) goto L_0x01a0
            int r2 = r1.length
            if (r3 == r2) goto L_0x0233
            int r2 = r3 + 1
            boolean r2 = r0.zzbk(r2)
            if (r2 == 0) goto L_0x019d
            int r2 = r0.pos
            int r4 = r0.limit
            goto L_0x01a0
        L_0x019d:
            r1 = 2
            goto L_0x020b
        L_0x01a0:
            int r5 = r2 + r3
            char r5 = r1[r5]
            r11 = 43
            if (r5 == r11) goto L_0x025a
            r11 = 69
            if (r5 == r11) goto L_0x0250
            r11 = 101(0x65, float:1.42E-43)
            if (r5 == r11) goto L_0x0250
            switch(r5) {
                case 45: goto L_0x0245;
                case 46: goto L_0x023f;
                default: goto L_0x01b3;
            }
        L_0x01b3:
            r11 = 48
            if (r5 < r11) goto L_0x0204
            r11 = 57
            if (r5 <= r11) goto L_0x01bc
            goto L_0x0204
        L_0x01bc:
            if (r7 == r6) goto L_0x01fb
            if (r7 != 0) goto L_0x01c1
            goto L_0x01fb
        L_0x01c1:
            if (r7 != r15) goto L_0x01ea
            r17 = 0
            int r11 = (r12 > r17 ? 1 : (r12 == r17 ? 0 : -1))
            if (r11 == 0) goto L_0x0233
            r17 = 10
            long r17 = r17 * r12
            int r5 = r5 + -48
            long r14 = (long) r5
            long r17 = r17 - r14
            r14 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r5 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r5 > 0) goto L_0x01e4
            if (r5 != 0) goto L_0x01e2
            int r5 = (r17 > r12 ? 1 : (r17 == r12 ? 0 : -1))
            if (r5 >= 0) goto L_0x01e2
            goto L_0x01e4
        L_0x01e2:
            r5 = 0
            goto L_0x01e5
        L_0x01e4:
            r5 = 1
        L_0x01e5:
            r5 = r5 & r8
            r8 = r5
            r12 = r17
            goto L_0x01ed
        L_0x01ea:
            if (r7 != r10) goto L_0x01f0
            r7 = 4
        L_0x01ed:
            r14 = 6
            goto L_0x025f
        L_0x01f0:
            r5 = 5
            if (r7 == r5) goto L_0x01f7
            r14 = 6
            if (r7 != r14) goto L_0x025f
            goto L_0x01f8
        L_0x01f7:
            r14 = 6
        L_0x01f8:
            r7 = 7
            goto L_0x025f
        L_0x01fb:
            r14 = 6
            int r5 = r5 + -48
            int r5 = -r5
            long r11 = (long) r5
            r12 = r11
            r7 = 2
            goto L_0x025f
        L_0x0204:
            boolean r1 = r0.zze(r5)
            if (r1 == 0) goto L_0x019d
            goto L_0x0233
        L_0x020b:
            if (r7 != r1) goto L_0x022a
            if (r8 == 0) goto L_0x0229
            r1 = -9223372036854775808
            int r4 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r4 != 0) goto L_0x0217
            if (r9 == 0) goto L_0x0229
        L_0x0217:
            if (r9 == 0) goto L_0x021a
            goto L_0x021b
        L_0x021a:
            long r12 = -r12
        L_0x021b:
            r0.zzaxx = r12
            int r1 = r0.pos
            int r1 = r1 + r3
            r0.pos = r1
            r5 = 15
            r0.zzaxw = r5
            r16 = 15
            goto L_0x0265
        L_0x0229:
            r1 = 2
        L_0x022a:
            if (r7 == r1) goto L_0x0236
            r1 = 4
            if (r7 == r1) goto L_0x0236
            r1 = 7
            if (r7 != r1) goto L_0x0233
            goto L_0x0236
        L_0x0233:
            r16 = 0
            goto L_0x0265
        L_0x0236:
            r0.zzaxy = r3
            r5 = 16
            r0.zzaxw = r5
            r16 = 16
            goto L_0x0265
        L_0x023f:
            r5 = 2
            r14 = 6
            if (r7 != r5) goto L_0x0233
            r7 = 3
            goto L_0x025f
        L_0x0245:
            r5 = 2
            r14 = 6
            if (r7 != 0) goto L_0x024c
            r7 = 1
            r9 = 1
            goto L_0x025f
        L_0x024c:
            r15 = 5
            if (r7 != r15) goto L_0x0233
            goto L_0x025e
        L_0x0250:
            r5 = 2
            r14 = 6
            r15 = 5
            if (r7 == r5) goto L_0x0258
            r5 = 4
            if (r7 != r5) goto L_0x0233
        L_0x0258:
            r7 = 5
            goto L_0x025f
        L_0x025a:
            r14 = 6
            r15 = 5
            if (r7 != r15) goto L_0x0233
        L_0x025e:
            r7 = 6
        L_0x025f:
            int r3 = r3 + 1
            r14 = 5
            r15 = 2
            goto L_0x0189
        L_0x0265:
            if (r16 == 0) goto L_0x0268
            return r16
        L_0x0268:
            char[] r1 = r0.zzaxt
            int r2 = r0.pos
            char r1 = r1[r2]
            boolean r1 = r0.zze(r1)
            if (r1 == 0) goto L_0x027b
            r19.zzmh()
            r1 = 10
            goto L_0x00c4
        L_0x027b:
            java.lang.String r1 = "Expected value"
            java.io.IOException r1 = r0.zzcd(r1)
            throw r1
        L_0x0282:
            r0.zzaxw = r6
            return r6
        L_0x0285:
            if (r3 != r6) goto L_0x028d
            r1 = 4
            goto L_0x00c4
        L_0x028a:
            r0.zzaxw = r10
            return r10
        L_0x028d:
            if (r3 == r6) goto L_0x029a
            r1 = 2
            if (r3 != r1) goto L_0x0293
            goto L_0x029a
        L_0x0293:
            java.lang.String r1 = "Unexpected value"
            java.io.IOException r1 = r0.zzcd(r1)
            throw r1
        L_0x029a:
            r19.zzmh()
            int r1 = r0.pos
            int r1 = r1 - r6
            r0.pos = r1
            r1 = 7
            goto L_0x00c4
        L_0x02a5:
            r19.zzmh()
            r0.zzaxw = r4
            return r4
        L_0x02ab:
            r1 = 9
            goto L_0x00c4
        L_0x02af:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "JsonReader is closed"
            r1.<init>(r2)
            throw r1
        L_0x02b7:
            int[] r1 = r0.zzaya
            int r2 = r0.zzayb
            int r2 = r2 - r6
            r4 = 4
            r1[r2] = r4
            r1 = 5
            if (r3 != r1) goto L_0x02db
            int r1 = r0.zzae(r6)
            if (r1 == r9) goto L_0x02db
            if (r1 == r8) goto L_0x02d8
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 != r2) goto L_0x02d1
        L_0x02ce:
            r1 = 2
            goto L_0x00c4
        L_0x02d1:
            java.lang.String r1 = "Unterminated object"
            java.io.IOException r1 = r0.zzcd(r1)
            throw r1
        L_0x02d8:
            r19.zzmh()
        L_0x02db:
            int r1 = r0.zzae(r6)
            r2 = 34
            if (r1 == r2) goto L_0x0317
            r2 = 39
            if (r1 == r2) goto L_0x0310
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 == r2) goto L_0x0305
            r19.zzmh()
            int r2 = r0.pos
            int r2 = r2 - r6
            r0.pos = r2
            char r1 = (char) r1
            boolean r1 = r0.zze(r1)
            if (r1 == 0) goto L_0x02fe
            r1 = 14
            goto L_0x00c4
        L_0x02fe:
            java.lang.String r1 = "Expected name"
            java.io.IOException r1 = r0.zzcd(r1)
            throw r1
        L_0x0305:
            r1 = 5
            if (r3 == r1) goto L_0x0309
            goto L_0x02ce
        L_0x0309:
            java.lang.String r1 = "Expected name"
            java.io.IOException r1 = r0.zzcd(r1)
            throw r1
        L_0x0310:
            r19.zzmh()
            r1 = 12
            goto L_0x00c4
        L_0x0317:
            r1 = 13
            goto L_0x00c4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzpl.zzmf():int");
    }

    private final String zzmg() {
        int i;
        String str;
        int i2 = 0;
        StringBuilder sb = null;
        while (true) {
            i = 0;
            while (true) {
                int i3 = this.pos;
                if (i3 + i < this.limit) {
                    switch (this.zzaxt[i3 + i]) {
                        case 9:
                        case 10:
                        case 12:
                        case 13:
                        case ' ':
                        case ',':
                        case ':':
                        case '[':
                        case ']':
                        case '{':
                        case '}':
                            break;
                        case '#':
                        case '/':
                        case ';':
                        case '=':
                        case '\\':
                            zzmh();
                            break;
                        default:
                            i++;
                            break;
                    }
                } else if (i >= this.zzaxt.length) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    sb.append(this.zzaxt, this.pos, i);
                    this.pos += i;
                    if (!zzbk(1)) {
                    }
                } else if (zzbk(i + 1)) {
                }
            }
        }
        i2 = i;
        if (sb == null) {
            str = new String(this.zzaxt, this.pos, i2);
        } else {
            sb.append(this.zzaxt, this.pos, i2);
            str = sb.toString();
        }
        this.pos += i2;
        return str;
    }

    private final void zzmh() {
        if (!this.zzaxs) {
            throw zzcd("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private final void zzmi() {
        char c;
        do {
            if (this.pos < this.limit || zzbk(1)) {
                char[] cArr = this.zzaxt;
                int i = this.pos;
                this.pos = i + 1;
                c = cArr[i];
                if (c == 10) {
                    this.zzaxu++;
                    this.zzaxv = this.pos;
                    return;
                }
            } else {
                return;
            }
        } while (c != 13);
    }

    private final String zzmj() {
        StringBuilder sb = new StringBuilder(" at line ");
        sb.append(this.zzaxu + 1);
        sb.append(" column ");
        sb.append((this.pos - this.zzaxv) + 1);
        sb.append(" path ");
        StringBuilder sb2 = new StringBuilder("$");
        int i = this.zzayb;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.zzaya[i2]) {
                case 1:
                case 2:
                    sb2.append('[');
                    sb2.append(this.zzayd[i2]);
                    sb2.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sb2.append('.');
                    String[] strArr = this.zzayc;
                    if (strArr[i2] == null) {
                        break;
                    } else {
                        sb2.append(strArr[i2]);
                        break;
                    }
            }
        }
        sb.append(sb2.toString());
        return sb.toString();
    }

    private final char zzmk() {
        int i;
        int i2;
        if (this.pos != this.limit || zzbk(1)) {
            char[] cArr = this.zzaxt;
            int i3 = this.pos;
            this.pos = i3 + 1;
            char c = cArr[i3];
            if (c == 10) {
                this.zzaxu++;
                this.zzaxv = this.pos;
            } else if (!(c == '\"' || c == '\'' || c == '/' || c == '\\')) {
                if (c == 'b') {
                    return 8;
                }
                if (c == 'f') {
                    return 12;
                }
                if (c == 'n') {
                    return 10;
                }
                if (c == 'r') {
                    return 13;
                }
                switch (c) {
                    case 't':
                        return 9;
                    case 'u':
                        if (this.pos + 4 <= this.limit || zzbk(4)) {
                            char c2 = 0;
                            int i4 = this.pos;
                            int i5 = i4 + 4;
                            while (i4 < i5) {
                                char c3 = this.zzaxt[i4];
                                char c4 = (char) (c2 << 4);
                                if (c3 < '0' || c3 > '9') {
                                    if (c3 >= 'a' && c3 <= 'f') {
                                        i = c3 - 'a';
                                    } else if (c3 < 'A' || c3 > 'F') {
                                        throw new NumberFormatException("\\u" + new String(this.zzaxt, this.pos, 4));
                                    } else {
                                        i = c3 - 'A';
                                    }
                                    i2 = i + 10;
                                } else {
                                    i2 = c3 - '0';
                                }
                                c2 = (char) (c4 + i2);
                                i4++;
                            }
                            this.pos += 4;
                            return c2;
                        }
                        throw zzcd("Unterminated escape sequence");
                    default:
                        throw zzcd("Invalid escape sequence");
                }
            }
            return c;
        }
        throw zzcd("Unterminated escape sequence");
    }

    public final void beginArray() {
        int i = this.zzaxw;
        if (i == 0) {
            i = zzmf();
        }
        if (i == 3) {
            zzbj(1);
            this.zzayd[this.zzayb - 1] = 0;
            this.zzaxw = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + zzme() + zzmj());
    }

    public final void beginObject() {
        int i = this.zzaxw;
        if (i == 0) {
            i = zzmf();
        }
        if (i == 1) {
            zzbj(3);
            this.zzaxw = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + zzme() + zzmj());
    }

    public final void close() {
        this.zzaxw = 0;
        this.zzaya[0] = 8;
        this.zzayb = 1;
        this.in.close();
    }

    public final void endArray() {
        int i = this.zzaxw;
        if (i == 0) {
            i = zzmf();
        }
        if (i == 4) {
            this.zzayb--;
            int[] iArr = this.zzayd;
            int i2 = this.zzayb - 1;
            iArr[i2] = iArr[i2] + 1;
            this.zzaxw = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + zzme() + zzmj());
    }

    public final void endObject() {
        int i = this.zzaxw;
        if (i == 0) {
            i = zzmf();
        }
        if (i == 2) {
            this.zzayb--;
            String[] strArr = this.zzayc;
            int i2 = this.zzayb;
            strArr[i2] = null;
            int[] iArr = this.zzayd;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.zzaxw = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + zzme() + zzmj());
    }

    public final boolean nextBoolean() {
        int i = this.zzaxw;
        if (i == 0) {
            i = zzmf();
        }
        if (i == 5) {
            this.zzaxw = 0;
            int[] iArr = this.zzayd;
            int i2 = this.zzayb - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.zzaxw = 0;
            int[] iArr2 = this.zzayd;
            int i3 = this.zzayb - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + zzme() + zzmj());
        }
    }

    public final String nextName() {
        String str;
        char c;
        int i = this.zzaxw;
        if (i == 0) {
            i = zzmf();
        }
        if (i == 14) {
            str = zzmg();
        } else {
            if (i == 12) {
                c = '\'';
            } else if (i == 13) {
                c = Typography.quote;
            } else {
                throw new IllegalStateException("Expected a name but was " + zzme() + zzmj());
            }
            str = zzf(c);
        }
        this.zzaxw = 0;
        this.zzayc[this.zzayb - 1] = str;
        return str;
    }

    public final void nextNull() {
        int i = this.zzaxw;
        if (i == 0) {
            i = zzmf();
        }
        if (i == 7) {
            this.zzaxw = 0;
            int[] iArr = this.zzayd;
            int i2 = this.zzayb - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + zzme() + zzmj());
    }

    public final String nextString() {
        char c;
        int i = this.zzaxw;
        if (i == 0) {
            i = zzmf();
        }
        String str = null;
        if (i == 10) {
            str = zzmg();
        } else {
            if (i == 8) {
                c = '\'';
            } else if (i == 9) {
                c = Typography.quote;
            } else if (i == 11) {
                this.zzaxz = null;
            } else if (i == 15) {
                str = Long.toString(this.zzaxx);
            } else if (i == 16) {
                str = new String(this.zzaxt, this.pos, this.zzaxy);
                this.pos += this.zzaxy;
            } else {
                throw new IllegalStateException("Expected a string but was " + zzme() + zzmj());
            }
            str = zzf(c);
        }
        this.zzaxw = 0;
        int[] iArr = this.zzayd;
        int i2 = this.zzayb - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public final void setLenient(boolean z) {
        this.zzaxs = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0071, code lost:
        r7.pos += r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void skipValue() {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            int r2 = r7.zzaxw
            if (r2 != 0) goto L_0x000a
            int r2 = r7.zzmf()
        L_0x000a:
            r3 = 3
            r4 = 1
            if (r2 != r3) goto L_0x0015
            r7.zzbj(r4)
        L_0x0011:
            int r1 = r1 + 1
            goto L_0x0080
        L_0x0015:
            if (r2 != r4) goto L_0x001b
            r7.zzbj(r3)
            goto L_0x0011
        L_0x001b:
            r3 = 4
            if (r2 != r3) goto L_0x0026
        L_0x001e:
            int r2 = r7.zzayb
            int r2 = r2 - r4
            r7.zzayb = r2
            int r1 = r1 + -1
            goto L_0x0080
        L_0x0026:
            r3 = 2
            if (r2 != r3) goto L_0x002a
            goto L_0x001e
        L_0x002a:
            r3 = 14
            if (r2 == r3) goto L_0x005a
            r3 = 10
            if (r2 != r3) goto L_0x0033
            goto L_0x005a
        L_0x0033:
            r3 = 8
            if (r2 == r3) goto L_0x0054
            r3 = 12
            if (r2 != r3) goto L_0x003c
            goto L_0x0054
        L_0x003c:
            r3 = 9
            if (r2 == r3) goto L_0x0051
            r3 = 13
            if (r2 != r3) goto L_0x0045
            goto L_0x0051
        L_0x0045:
            r3 = 16
            if (r2 != r3) goto L_0x0080
            int r2 = r7.pos
            int r3 = r7.zzaxy
            int r2 = r2 + r3
            r7.pos = r2
            goto L_0x0080
        L_0x0051:
            r2 = 34
            goto L_0x0056
        L_0x0054:
            r2 = 39
        L_0x0056:
            r7.zzg(r2)
            goto L_0x0080
        L_0x005a:
            r2 = 0
        L_0x005b:
            int r3 = r7.pos
            int r5 = r3 + r2
            int r6 = r7.limit
            if (r5 >= r6) goto L_0x0077
            char[] r5 = r7.zzaxt
            int r3 = r3 + r2
            char r3 = r5[r3]
            switch(r3) {
                case 9: goto L_0x0071;
                case 10: goto L_0x0071;
                case 12: goto L_0x0071;
                case 13: goto L_0x0071;
                case 32: goto L_0x0071;
                case 35: goto L_0x006e;
                case 44: goto L_0x0071;
                case 47: goto L_0x006e;
                case 58: goto L_0x0071;
                case 59: goto L_0x006e;
                case 61: goto L_0x006e;
                case 91: goto L_0x0071;
                case 92: goto L_0x006e;
                case 93: goto L_0x0071;
                case 123: goto L_0x0071;
                case 125: goto L_0x0071;
                default: goto L_0x006b;
            }
        L_0x006b:
            int r2 = r2 + 1
            goto L_0x005b
        L_0x006e:
            r7.zzmh()
        L_0x0071:
            int r3 = r7.pos
            int r3 = r3 + r2
            r7.pos = r3
            goto L_0x0080
        L_0x0077:
            int r3 = r3 + r2
            r7.pos = r3
            boolean r2 = r7.zzbk(r4)
            if (r2 != 0) goto L_0x005a
        L_0x0080:
            r7.zzaxw = r0
            if (r1 != 0) goto L_0x0002
            int[] r0 = r7.zzayd
            int r1 = r7.zzayb
            int r2 = r1 + -1
            r3 = r0[r2]
            int r3 = r3 + r4
            r0[r2] = r3
            java.lang.String[] r0 = r7.zzayc
            int r1 = r1 - r4
            java.lang.String r2 = "null"
            r0[r1] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzpl.skipValue():void");
    }

    public final String toString() {
        return getClass().getSimpleName() + zzmj();
    }

    public final zzpn zzme() {
        int i = this.zzaxw;
        if (i == 0) {
            i = zzmf();
        }
        switch (i) {
            case 1:
                return zzpn.BEGIN_OBJECT;
            case 2:
                return zzpn.END_OBJECT;
            case 3:
                return zzpn.BEGIN_ARRAY;
            case 4:
                return zzpn.END_ARRAY;
            case 5:
            case 6:
                return zzpn.BOOLEAN;
            case 7:
                return zzpn.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return zzpn.STRING;
            case 12:
            case 13:
            case 14:
                return zzpn.NAME;
            case 15:
            case 16:
                return zzpn.NUMBER;
            case 17:
                return zzpn.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }
}
