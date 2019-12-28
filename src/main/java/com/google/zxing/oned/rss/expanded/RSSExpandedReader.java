package com.google.zxing.oned.rss.expanded;

import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.oned.rss.AbstractRSSReader;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.google.zxing.oned.rss.RSSUtils;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class RSSExpandedReader extends AbstractRSSReader {
    private static final int[] EVEN_TOTAL_SUBSET = {4, 20, 52, 104, 204};
    private static final int[][] FINDER_PATTERNS = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] FINDER_PATTERN_SEQUENCES = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
    private static final int FINDER_PAT_A = 0;
    private static final int FINDER_PAT_B = 1;
    private static final int FINDER_PAT_C = 2;
    private static final int FINDER_PAT_D = 3;
    private static final int FINDER_PAT_E = 4;
    private static final int FINDER_PAT_F = 5;
    private static final int[] GSUM = {0, 348, 1388, 2948, 3988};
    private static final int MAX_PAIRS = 11;
    private static final int[] SYMBOL_WIDEST = {7, 5, 4, 3, 1};
    private static final int[][] WEIGHTS = {new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, 143, 7, 21, 63}, new int[]{189, 145, 13, 39, 117, 140, 209, 205}, new int[]{193, 157, 49, 147, 19, 57, 171, 91}, new int[]{62, 186, 136, 197, 169, 85, 44, 132}, new int[]{185, 133, 188, 142, 4, 12, 36, 108}, new int[]{113, 128, 173, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, 158, 52, 156}, new int[]{46, 138, 203, 187, 139, 206, 196, 166}, new int[]{76, 17, 51, 153, 37, 111, 122, 155}, new int[]{43, 129, 176, 106, 107, 110, 119, 146}, new int[]{16, 48, 144, 10, 30, 90, 59, 177}, new int[]{109, 116, 137, 200, 178, 112, 125, 164}, new int[]{70, 210, 208, 202, 184, 130, 179, 115}, new int[]{134, 191, 151, 31, 93, 68, 204, FacebookRequestErrorClassification.EC_INVALID_TOKEN}, new int[]{148, 22, 66, 198, 172, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, 192, 154, 40}, new int[]{120, 149, 25, 75, 14, 42, 126, 167}, new int[]{79, 26, 78, 23, 69, 207, 199, 175}, new int[]{103, 98, 83, 38, 114, 131, 182, 124}, new int[]{161, 61, 183, 127, 170, 88, 53, 159}, new int[]{55, 165, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, 194, 160, 58, 174, 100, 89}};
    private final List<ExpandedPair> pairs = new ArrayList(11);
    private final List<ExpandedRow> rows = new ArrayList();
    private final int[] startEnd = new int[2];
    private boolean startFromEven;

    static Result a(List<ExpandedPair> list) {
        String parseInformation = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.a(list)).parseInformation();
        ResultPoint[] resultPoints = list.get(0).c().getResultPoints();
        ResultPoint[] resultPoints2 = list.get(list.size() - 1).c().getResultPoints();
        return new Result(parseInformation, (byte[]) null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void adjustOddEvenCounts(int r11) {
        /*
            r10 = this;
            int[] r0 = r10.e()
            int r0 = com.google.zxing.common.detector.MathUtils.sum(r0)
            int[] r1 = r10.f()
            int r1 = com.google.zxing.common.detector.MathUtils.sum(r1)
            r2 = 4
            r3 = 13
            r4 = 0
            r5 = 1
            if (r0 <= r3) goto L_0x0019
            r6 = 1
            goto L_0x001f
        L_0x0019:
            if (r0 >= r2) goto L_0x001e
            r6 = 0
            r7 = 1
            goto L_0x0020
        L_0x001e:
            r6 = 0
        L_0x001f:
            r7 = 0
        L_0x0020:
            if (r1 <= r3) goto L_0x0025
            r2 = 0
            r3 = 1
            goto L_0x002b
        L_0x0025:
            if (r1 >= r2) goto L_0x0029
            r2 = 1
            goto L_0x002a
        L_0x0029:
            r2 = 0
        L_0x002a:
            r3 = 0
        L_0x002b:
            int r8 = r0 + r1
            int r8 = r8 - r11
            r11 = r0 & 1
            if (r11 != r5) goto L_0x0034
            r11 = 1
            goto L_0x0035
        L_0x0034:
            r11 = 0
        L_0x0035:
            r9 = r1 & 1
            if (r9 != 0) goto L_0x003a
            r4 = 1
        L_0x003a:
            if (r8 != r5) goto L_0x0050
            if (r11 == 0) goto L_0x0047
            if (r4 != 0) goto L_0x0042
        L_0x0040:
            r6 = 1
            goto L_0x007a
        L_0x0042:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x0047:
            if (r4 == 0) goto L_0x004b
            r3 = 1
            goto L_0x007a
        L_0x004b:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x0050:
            r9 = -1
            if (r8 != r9) goto L_0x0067
            if (r11 == 0) goto L_0x005e
            if (r4 != 0) goto L_0x0059
        L_0x0057:
            r7 = 1
            goto L_0x007a
        L_0x0059:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x005e:
            if (r4 == 0) goto L_0x0062
            r2 = 1
            goto L_0x007a
        L_0x0062:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x0067:
            if (r8 != 0) goto L_0x00c4
            if (r11 == 0) goto L_0x0078
            if (r4 == 0) goto L_0x0073
            if (r0 >= r1) goto L_0x0071
            r3 = 1
            goto L_0x0057
        L_0x0071:
            r2 = 1
            goto L_0x0040
        L_0x0073:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x0078:
            if (r4 != 0) goto L_0x00bf
        L_0x007a:
            if (r7 == 0) goto L_0x008f
            if (r6 != 0) goto L_0x008a
            int[] r11 = r10.e()
            float[] r0 = r10.c()
            a((int[]) r11, (float[]) r0)
            goto L_0x008f
        L_0x008a:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x008f:
            if (r6 == 0) goto L_0x009c
            int[] r11 = r10.e()
            float[] r0 = r10.c()
            b(r11, r0)
        L_0x009c:
            if (r2 == 0) goto L_0x00b1
            if (r3 != 0) goto L_0x00ac
            int[] r11 = r10.f()
            float[] r0 = r10.c()
            a((int[]) r11, (float[]) r0)
            goto L_0x00b1
        L_0x00ac:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x00b1:
            if (r3 == 0) goto L_0x00be
            int[] r11 = r10.f()
            float[] r0 = r10.d()
            b(r11, r0)
        L_0x00be:
            return
        L_0x00bf:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x00c4:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.adjustOddEvenCounts(int):void");
    }

    private boolean checkChecksum() {
        ExpandedPair expandedPair = this.pairs.get(0);
        DataCharacter a = expandedPair.a();
        DataCharacter b = expandedPair.b();
        if (b == null) {
            return false;
        }
        int checksumPortion = b.getChecksumPortion();
        int i = 2;
        for (int i2 = 1; i2 < this.pairs.size(); i2++) {
            ExpandedPair expandedPair2 = this.pairs.get(i2);
            checksumPortion += expandedPair2.a().getChecksumPortion();
            i++;
            DataCharacter b2 = expandedPair2.b();
            if (b2 != null) {
                checksumPortion += b2.getChecksumPortion();
                i++;
            }
        }
        return ((i + -4) * 211) + (checksumPortion % 211) == a.getValue();
    }

    private List<ExpandedPair> checkRows(List<ExpandedRow> list, int i) {
        while (i < this.rows.size()) {
            ExpandedRow expandedRow = this.rows.get(i);
            this.pairs.clear();
            for (ExpandedRow a : list) {
                this.pairs.addAll(a.a());
            }
            this.pairs.addAll(expandedRow.a());
            if (!isValidSequence(this.pairs)) {
                i++;
            } else if (checkChecksum()) {
                return this.pairs;
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(list);
                arrayList.add(expandedRow);
                try {
                    return checkRows(arrayList, i + 1);
                } catch (NotFoundException unused) {
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private List<ExpandedPair> checkRows(boolean z) {
        List<ExpandedPair> list = null;
        if (this.rows.size() > 25) {
            this.rows.clear();
            return null;
        }
        this.pairs.clear();
        if (z) {
            Collections.reverse(this.rows);
        }
        try {
            list = checkRows(new ArrayList(), 0);
        } catch (NotFoundException unused) {
        }
        if (z) {
            Collections.reverse(this.rows);
        }
        return list;
    }

    private void findNextPair(BitArray bitArray, List<ExpandedPair> list, int i) {
        int[] a = a();
        a[0] = 0;
        a[1] = 0;
        a[2] = 0;
        a[3] = 0;
        int size = bitArray.getSize();
        if (i < 0) {
            i = list.isEmpty() ? 0 : list.get(list.size() - 1).c().getStartEnd()[1];
        }
        boolean z = list.size() % 2 != 0;
        if (this.startFromEven) {
            z = !z;
        }
        boolean z2 = false;
        while (i < size) {
            z2 = !bitArray.get(i);
            if (!z2) {
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
                if (i3 == 3) {
                    if (z) {
                        reverseCounters(a);
                    }
                    if (a(a)) {
                        int[] iArr = this.startEnd;
                        iArr[0] = i2;
                        iArr[1] = i;
                        return;
                    }
                    if (z) {
                        reverseCounters(a);
                    }
                    i2 += a[0] + a[1];
                    a[0] = a[2];
                    a[1] = a[3];
                    a[2] = 0;
                    a[3] = 0;
                    i3--;
                } else {
                    i3++;
                }
                a[i3] = 1;
                z2 = !z2;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int getNextSecondBar(BitArray bitArray, int i) {
        return bitArray.get(i) ? bitArray.getNextSet(bitArray.getNextUnset(i)) : bitArray.getNextUnset(bitArray.getNextSet(i));
    }

    private static boolean isNotA1left(FinderPattern finderPattern, boolean z, boolean z2) {
        return finderPattern.getValue() != 0 || !z || !z2;
    }

    private static boolean isPartialRow(Iterable<ExpandedPair> iterable, Iterable<ExpandedRow> iterable2) {
        boolean z;
        boolean z2;
        Iterator<ExpandedRow> it = iterable2.iterator();
        do {
            z = false;
            if (it.hasNext()) {
                ExpandedRow next = it.next();
                Iterator<ExpandedPair> it2 = iterable.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z = true;
                        continue;
                        break;
                    }
                    ExpandedPair next2 = it2.next();
                    Iterator<ExpandedPair> it3 = next.a().iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (next2.equals(it3.next())) {
                                z2 = true;
                                continue;
                                break;
                            }
                        } else {
                            z2 = false;
                            continue;
                            break;
                        }
                    }
                    if (!z2) {
                        continue;
                        break;
                    }
                }
            } else {
                return false;
            }
        } while (!z);
        return true;
    }

    private static boolean isValidSequence(List<ExpandedPair> list) {
        boolean z;
        for (int[] iArr : FINDER_PATTERN_SEQUENCES) {
            if (list.size() <= iArr.length) {
                int i = 0;
                while (true) {
                    if (i >= list.size()) {
                        z = true;
                        break;
                    } else if (list.get(i).c().getValue() != iArr[i]) {
                        z = false;
                        break;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i, boolean z) {
        int i2;
        int i3;
        int i4;
        if (z) {
            int i5 = this.startEnd[0] - 1;
            while (i5 >= 0 && !bitArray.get(i5)) {
                i5--;
            }
            int i6 = i5 + 1;
            int[] iArr = this.startEnd;
            i3 = iArr[1];
            i4 = i6;
            i2 = iArr[0] - i6;
        } else {
            int[] iArr2 = this.startEnd;
            int i7 = iArr2[0];
            int nextUnset = bitArray.getNextUnset(iArr2[1] + 1);
            i2 = nextUnset - this.startEnd[1];
            i3 = nextUnset;
            i4 = i7;
        }
        int[] a = a();
        System.arraycopy(a, 0, a, 1, a.length - 1);
        a[0] = i2;
        try {
            return new FinderPattern(a(a, FINDER_PATTERNS), new int[]{i4, i3}, i4, i3, i);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    private static void removePartialRows(List<ExpandedPair> list, List<ExpandedRow> list2) {
        boolean z;
        Iterator<ExpandedRow> it = list2.iterator();
        while (it.hasNext()) {
            ExpandedRow next = it.next();
            if (next.a().size() != list.size()) {
                Iterator<ExpandedPair> it2 = next.a().iterator();
                while (true) {
                    z = false;
                    boolean z2 = true;
                    if (!it2.hasNext()) {
                        z = true;
                        break;
                    }
                    ExpandedPair next2 = it2.next();
                    Iterator<ExpandedPair> it3 = list.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (next2.equals(it3.next())) {
                                continue;
                                break;
                            }
                        } else {
                            z2 = false;
                            continue;
                            break;
                        }
                    }
                    if (!z2) {
                        break;
                    }
                }
                if (z) {
                    it.remove();
                }
            }
        }
    }

    private static void reverseCounters(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length / 2; i++) {
            int i2 = iArr[i];
            int i3 = (length - i) - 1;
            iArr[i] = iArr[i3];
            iArr[i3] = i2;
        }
    }

    private void storeRow(int i, boolean z) {
        boolean z2 = false;
        int i2 = 0;
        boolean z3 = false;
        while (true) {
            if (i2 >= this.rows.size()) {
                break;
            }
            ExpandedRow expandedRow = this.rows.get(i2);
            if (expandedRow.b() > i) {
                z2 = expandedRow.a(this.pairs);
                break;
            } else {
                z3 = expandedRow.a(this.pairs);
                i2++;
            }
        }
        if (!z2 && !z3 && !isPartialRow(this.pairs, this.rows)) {
            this.rows.add(i2, new ExpandedRow(this.pairs, i, z));
            removePartialRows(this.pairs, this.rows);
        }
    }

    /* access modifiers changed from: package-private */
    public DataCharacter a(BitArray bitArray, FinderPattern finderPattern, boolean z, boolean z2) {
        BitArray bitArray2 = bitArray;
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
            a(bitArray2, finderPattern.getStartEnd()[1], b);
            int i = 0;
            for (int length = b.length - 1; i < length; length--) {
                int i2 = b[i];
                b[i] = b[length];
                b[length] = i2;
                i++;
            }
        }
        float sum = ((float) MathUtils.sum(b)) / 17.0f;
        float f = ((float) (finderPattern.getStartEnd()[1] - finderPattern.getStartEnd()[0])) / 15.0f;
        if (Math.abs(sum - f) / f <= 0.3f) {
            int[] e = e();
            int[] f2 = f();
            float[] c = c();
            float[] d = d();
            for (int i3 = 0; i3 < b.length; i3++) {
                float f3 = (((float) b[i3]) * 1.0f) / sum;
                int i4 = (int) (0.5f + f3);
                if (i4 <= 0) {
                    if (f3 >= 0.3f) {
                        i4 = 1;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                } else if (i4 > 8) {
                    if (f3 <= 8.7f) {
                        i4 = 8;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
                int i5 = i3 / 2;
                if ((i3 & 1) == 0) {
                    e[i5] = i4;
                    c[i5] = f3 - ((float) i4);
                } else {
                    f2[i5] = i4;
                    d[i5] = f3 - ((float) i4);
                }
            }
            adjustOddEvenCounts(17);
            int value = (((finderPattern.getValue() * 4) + (z ? 0 : 2)) + (z2 ^ true ? 1 : 0)) - 1;
            int i6 = 0;
            int i7 = 0;
            for (int length2 = e.length - 1; length2 >= 0; length2--) {
                if (isNotA1left(finderPattern, z, z2)) {
                    i6 += e[length2] * WEIGHTS[value][length2 * 2];
                }
                i7 += e[length2];
            }
            int i8 = 0;
            for (int length3 = f2.length - 1; length3 >= 0; length3--) {
                if (isNotA1left(finderPattern, z, z2)) {
                    i8 += f2[length3] * WEIGHTS[value][(length3 * 2) + 1];
                }
            }
            int i9 = i6 + i8;
            if ((i7 & 1) != 0 || i7 > 13 || i7 < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            int i10 = (13 - i7) / 2;
            int i11 = SYMBOL_WIDEST[i10];
            return new DataCharacter((RSSUtils.getRSSvalue(e, i11, true) * EVEN_TOTAL_SUBSET[i10]) + RSSUtils.getRSSvalue(f2, 9 - i11, false) + GSUM[i10], i9);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* access modifiers changed from: package-private */
    public ExpandedPair a(BitArray bitArray, List<ExpandedPair> list, int i) {
        FinderPattern parseFoundFinderPattern;
        DataCharacter dataCharacter;
        boolean z = list.size() % 2 == 0;
        if (this.startFromEven) {
            z = !z;
        }
        int i2 = -1;
        boolean z2 = true;
        do {
            findNextPair(bitArray, list, i2);
            parseFoundFinderPattern = parseFoundFinderPattern(bitArray, i, z);
            if (parseFoundFinderPattern == null) {
                i2 = getNextSecondBar(bitArray, this.startEnd[0]);
                continue;
            } else {
                z2 = false;
                continue;
            }
        } while (z2);
        DataCharacter a = a(bitArray, parseFoundFinderPattern, z, true);
        if (list.isEmpty() || !list.get(list.size() - 1).mustBeLast()) {
            try {
                dataCharacter = a(bitArray, parseFoundFinderPattern, z, false);
            } catch (NotFoundException unused) {
                dataCharacter = null;
            }
            return new ExpandedPair(a, dataCharacter, parseFoundFinderPattern, true);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* access modifiers changed from: package-private */
    public List<ExpandedPair> a(int i, BitArray bitArray) {
        while (true) {
            try {
                this.pairs.add(a(bitArray, this.pairs, i));
            } catch (NotFoundException e) {
                if (this.pairs.isEmpty()) {
                    throw e;
                } else if (checkChecksum()) {
                    return this.pairs;
                } else {
                    boolean z = !this.rows.isEmpty();
                    storeRow(i, false);
                    if (z) {
                        List<ExpandedPair> checkRows = checkRows(false);
                        if (checkRows != null) {
                            return checkRows;
                        }
                        List<ExpandedPair> checkRows2 = checkRows(true);
                        if (checkRows2 != null) {
                            return checkRows2;
                        }
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
    }

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) {
        this.pairs.clear();
        this.startFromEven = false;
        try {
            return a(a(i, bitArray));
        } catch (NotFoundException unused) {
            this.pairs.clear();
            this.startFromEven = true;
            return a(a(i, bitArray));
        }
    }

    public void reset() {
        this.pairs.clear();
        this.rows.clear();
    }
}
