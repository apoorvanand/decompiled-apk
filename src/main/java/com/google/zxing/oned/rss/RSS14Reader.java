package com.google.zxing.oned.rss;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.detector.MathUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class RSS14Reader extends AbstractRSSReader {
    private static final int[][] FINDER_PATTERNS = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};
    private static final int[] INSIDE_GSUM = {0, 336, 1036, 1516};
    private static final int[] INSIDE_ODD_TOTAL_SUBSET = {4, 20, 48, 81};
    private static final int[] INSIDE_ODD_WIDEST = {2, 4, 6, 8};
    private static final int[] OUTSIDE_EVEN_TOTAL_SUBSET = {1, 10, 34, 70, 126};
    private static final int[] OUTSIDE_GSUM = {0, 161, 961, 2015, 2715};
    private static final int[] OUTSIDE_ODD_WIDEST = {8, 6, 4, 3, 1};
    private final List<Pair> possibleLeftPairs = new ArrayList();
    private final List<Pair> possibleRightPairs = new ArrayList();

    private static void addOrTally(Collection<Pair> collection, Pair pair) {
        if (pair != null) {
            boolean z = false;
            Iterator<Pair> it = collection.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Pair next = it.next();
                if (next.getValue() == pair.getValue()) {
                    next.c();
                    z = true;
                    break;
                }
            }
            if (!z) {
                collection.add(pair);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0044, code lost:
        if (r1 < 4) goto L_0x0046;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:85:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void adjustOddEvenCounts(boolean r10, int r11) {
        /*
            r9 = this;
            int[] r0 = r9.e()
            int r0 = com.google.zxing.common.detector.MathUtils.sum(r0)
            int[] r1 = r9.f()
            int r1 = com.google.zxing.common.detector.MathUtils.sum(r1)
            r2 = 4
            r3 = 0
            r4 = 1
            if (r10 == 0) goto L_0x002f
            r5 = 12
            if (r0 <= r5) goto L_0x001c
            r6 = 0
            r7 = 1
            goto L_0x0022
        L_0x001c:
            if (r0 >= r2) goto L_0x0020
            r6 = 1
            goto L_0x0021
        L_0x0020:
            r6 = 0
        L_0x0021:
            r7 = 0
        L_0x0022:
            if (r1 <= r5) goto L_0x0027
            r5 = r6
            r6 = r7
            goto L_0x0041
        L_0x0027:
            if (r1 >= r2) goto L_0x002c
            r5 = r6
            r6 = r7
            goto L_0x0046
        L_0x002c:
            r5 = r6
            r6 = r7
            goto L_0x0048
        L_0x002f:
            r5 = 11
            if (r0 <= r5) goto L_0x0036
            r5 = 0
            r6 = 1
            goto L_0x003d
        L_0x0036:
            r5 = 5
            if (r0 >= r5) goto L_0x003b
            r5 = 1
            goto L_0x003c
        L_0x003b:
            r5 = 0
        L_0x003c:
            r6 = 0
        L_0x003d:
            r7 = 10
            if (r1 <= r7) goto L_0x0044
        L_0x0041:
            r2 = 0
            r7 = 1
            goto L_0x004a
        L_0x0044:
            if (r1 >= r2) goto L_0x0048
        L_0x0046:
            r2 = 1
            goto L_0x0049
        L_0x0048:
            r2 = 0
        L_0x0049:
            r7 = 0
        L_0x004a:
            int r8 = r0 + r1
            int r8 = r8 - r11
            r11 = r0 & 1
            if (r11 != r10) goto L_0x0053
            r10 = 1
            goto L_0x0054
        L_0x0053:
            r10 = 0
        L_0x0054:
            r11 = r1 & 1
            if (r11 != r4) goto L_0x0059
            r3 = 1
        L_0x0059:
            if (r8 != r4) goto L_0x006f
            if (r10 == 0) goto L_0x0066
            if (r3 != 0) goto L_0x0061
        L_0x005f:
            r6 = 1
            goto L_0x0099
        L_0x0061:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0066:
            if (r3 == 0) goto L_0x006a
        L_0x0068:
            r7 = 1
            goto L_0x0099
        L_0x006a:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x006f:
            r11 = -1
            if (r8 != r11) goto L_0x0086
            if (r10 == 0) goto L_0x007d
            if (r3 != 0) goto L_0x0078
            r5 = 1
            goto L_0x0099
        L_0x0078:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x007d:
            if (r3 == 0) goto L_0x0081
            r2 = 1
            goto L_0x0099
        L_0x0081:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0086:
            if (r8 != 0) goto L_0x00e3
            if (r10 == 0) goto L_0x0097
            if (r3 == 0) goto L_0x0092
            if (r0 >= r1) goto L_0x0090
            r5 = 1
            goto L_0x0068
        L_0x0090:
            r2 = 1
            goto L_0x005f
        L_0x0092:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0097:
            if (r3 != 0) goto L_0x00de
        L_0x0099:
            if (r5 == 0) goto L_0x00ae
            if (r6 != 0) goto L_0x00a9
            int[] r10 = r9.e()
            float[] r11 = r9.c()
            a((int[]) r10, (float[]) r11)
            goto L_0x00ae
        L_0x00a9:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x00ae:
            if (r6 == 0) goto L_0x00bb
            int[] r10 = r9.e()
            float[] r11 = r9.c()
            b(r10, r11)
        L_0x00bb:
            if (r2 == 0) goto L_0x00d0
            if (r7 != 0) goto L_0x00cb
            int[] r10 = r9.f()
            float[] r11 = r9.c()
            a((int[]) r10, (float[]) r11)
            goto L_0x00d0
        L_0x00cb:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x00d0:
            if (r7 == 0) goto L_0x00dd
            int[] r10 = r9.f()
            float[] r11 = r9.d()
            b(r10, r11)
        L_0x00dd:
            return
        L_0x00de:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x00e3:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.RSS14Reader.adjustOddEvenCounts(boolean, int):void");
    }

    private static boolean checkChecksum(Pair pair, Pair pair2) {
        int checksumPortion = (pair.getChecksumPortion() + (pair2.getChecksumPortion() * 16)) % 79;
        int value = (pair.a().getValue() * 9) + pair2.a().getValue();
        if (value > 72) {
            value--;
        }
        if (value > 8) {
            value--;
        }
        return checksumPortion == value;
    }

    private static Result constructResult(Pair pair, Pair pair2) {
        String valueOf = String.valueOf((((long) pair.getValue()) * 4537077) + ((long) pair2.getValue()));
        StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - valueOf.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(valueOf);
        int i = 0;
        for (int i2 = 0; i2 < 13; i2++) {
            int charAt = sb.charAt(i2) - '0';
            if ((i2 & 1) == 0) {
                charAt *= 3;
            }
            i += charAt;
        }
        int i3 = 10 - (i % 10);
        if (i3 == 10) {
            i3 = 0;
        }
        sb.append(i3);
        ResultPoint[] resultPoints = pair.a().getResultPoints();
        ResultPoint[] resultPoints2 = pair2.a().getResultPoints();
        return new Result(String.valueOf(sb.toString()), (byte[]) null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_14);
    }

    private DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z) {
        BitArray bitArray2 = bitArray;
        boolean z2 = z;
        int[] b = b();
        b[0] = 0;
        b[1] = 0;
        b[2] = 0;
        b[3] = 0;
        b[4] = 0;
        b[5] = 0;
        b[6] = 0;
        b[7] = 0;
        if (z2) {
            b(bitArray2, finderPattern.getStartEnd()[0], b);
        } else {
            a(bitArray2, finderPattern.getStartEnd()[1] + 1, b);
            int i = 0;
            for (int length = b.length - 1; i < length; length--) {
                int i2 = b[i];
                b[i] = b[length];
                b[length] = i2;
                i++;
            }
        }
        int i3 = z2 ? 16 : 15;
        float sum = ((float) MathUtils.sum(b)) / ((float) i3);
        int[] e = e();
        int[] f = f();
        float[] c = c();
        float[] d = d();
        for (int i4 = 0; i4 < b.length; i4++) {
            float f2 = ((float) b[i4]) / sum;
            int i5 = (int) (0.5f + f2);
            if (i5 <= 0) {
                i5 = 1;
            } else if (i5 > 8) {
                i5 = 8;
            }
            int i6 = i4 / 2;
            if ((i4 & 1) == 0) {
                e[i6] = i5;
                c[i6] = f2 - ((float) i5);
            } else {
                f[i6] = i5;
                d[i6] = f2 - ((float) i5);
            }
        }
        adjustOddEvenCounts(z2, i3);
        int i7 = 0;
        int i8 = 0;
        for (int length2 = e.length - 1; length2 >= 0; length2--) {
            i7 = (i7 * 9) + e[length2];
            i8 += e[length2];
        }
        int i9 = 0;
        int i10 = 0;
        for (int length3 = f.length - 1; length3 >= 0; length3--) {
            i9 = (i9 * 9) + f[length3];
            i10 += f[length3];
        }
        int i11 = i7 + (i9 * 3);
        if (z2) {
            if ((i8 & 1) != 0 || i8 > 12 || i8 < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            int i12 = (12 - i8) / 2;
            int i13 = OUTSIDE_ODD_WIDEST[i12];
            return new DataCharacter((RSSUtils.getRSSvalue(e, i13, false) * OUTSIDE_EVEN_TOTAL_SUBSET[i12]) + RSSUtils.getRSSvalue(f, 9 - i13, true) + OUTSIDE_GSUM[i12], i11);
        } else if ((i10 & 1) != 0 || i10 > 10 || i10 < 4) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            int i14 = (10 - i10) / 2;
            int i15 = INSIDE_ODD_WIDEST[i14];
            return new DataCharacter((RSSUtils.getRSSvalue(f, 9 - i15, false) * INSIDE_ODD_TOTAL_SUBSET[i14]) + RSSUtils.getRSSvalue(e, i15, true) + INSIDE_GSUM[i14], i11);
        }
    }

    private Pair decodePair(BitArray bitArray, boolean z, int i, Map<DecodeHintType, ?> map) {
        try {
            int[] findFinderPattern = findFinderPattern(bitArray, 0, z);
            FinderPattern parseFoundFinderPattern = parseFoundFinderPattern(bitArray, i, z, findFinderPattern);
            ResultPointCallback resultPointCallback = map == null ? null : (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            if (resultPointCallback != null) {
                float f = ((float) (findFinderPattern[0] + findFinderPattern[1])) / 2.0f;
                if (z) {
                    f = ((float) (bitArray.getSize() - 1)) - f;
                }
                resultPointCallback.foundPossibleResultPoint(new ResultPoint(f, (float) i));
            }
            DataCharacter decodeDataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, true);
            DataCharacter decodeDataCharacter2 = decodeDataCharacter(bitArray, parseFoundFinderPattern, false);
            return new Pair((decodeDataCharacter.getValue() * 1597) + decodeDataCharacter2.getValue(), decodeDataCharacter.getChecksumPortion() + (decodeDataCharacter2.getChecksumPortion() * 4), parseFoundFinderPattern);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    private int[] findFinderPattern(BitArray bitArray, int i, boolean z) {
        int[] a = a();
        a[0] = 0;
        a[1] = 0;
        a[2] = 0;
        a[3] = 0;
        int size = bitArray.getSize();
        boolean z2 = false;
        while (i < size) {
            z2 = !bitArray.get(i);
            if (z == z2) {
                break;
            }
            i++;
        }
        int i2 = i;
        int i3 = 0;
        while (i < size) {
            if (bitArray.get(i) ^ z2) {
                a[i3] = a[i3] + 1;
            } else {
                if (i3 != 3) {
                    i3++;
                } else if (a(a)) {
                    return new int[]{i2, i};
                } else {
                    i2 += a[0] + a[1];
                    a[0] = a[2];
                    a[1] = a[3];
                    a[2] = 0;
                    a[3] = 0;
                    i3--;
                }
                a[i3] = 1;
                z2 = !z2;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i, boolean z, int[] iArr) {
        int i2;
        int i3;
        boolean z2 = bitArray.get(iArr[0]);
        int i4 = iArr[0] - 1;
        while (i4 >= 0 && (bitArray.get(i4) ^ z2)) {
            i4--;
        }
        int i5 = i4 + 1;
        int[] a = a();
        System.arraycopy(a, 0, a, 1, a.length - 1);
        a[0] = iArr[0] - i5;
        int a2 = a(a, FINDER_PATTERNS);
        int i6 = iArr[1];
        if (z) {
            i2 = (bitArray.getSize() - 1) - i6;
            i3 = (bitArray.getSize() - 1) - i5;
        } else {
            i2 = i6;
            i3 = i5;
        }
        return new FinderPattern(a2, new int[]{i5, iArr[1]}, i3, i2, i);
    }

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) {
        addOrTally(this.possibleLeftPairs, decodePair(bitArray, false, i, map));
        bitArray.reverse();
        addOrTally(this.possibleRightPairs, decodePair(bitArray, true, i, map));
        bitArray.reverse();
        for (Pair next : this.possibleLeftPairs) {
            if (next.b() > 1) {
                for (Pair next2 : this.possibleRightPairs) {
                    if (next2.b() > 1 && checkChecksum(next, next2)) {
                        return constructResult(next, next2);
                    }
                }
                continue;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public void reset() {
        this.possibleLeftPairs.clear();
        this.possibleRightPairs.clear();
    }
}
