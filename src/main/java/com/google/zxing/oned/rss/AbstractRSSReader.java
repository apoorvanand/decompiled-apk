package com.google.zxing.oned.rss;

import com.google.zxing.NotFoundException;
import com.google.zxing.oned.OneDReader;

public abstract class AbstractRSSReader extends OneDReader {
    private static final float MAX_AVG_VARIANCE = 0.2f;
    private static final float MAX_FINDER_PATTERN_RATIO = 0.89285713f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.45f;
    private static final float MIN_FINDER_PATTERN_RATIO = 0.7916667f;
    private final int[] dataCharacterCounters = new int[8];
    private final int[] decodeFinderCounters = new int[4];
    private final int[] evenCounts;
    private final float[] evenRoundingErrors = new float[4];
    private final int[] oddCounts;
    private final float[] oddRoundingErrors = new float[4];

    protected AbstractRSSReader() {
        int[] iArr = this.dataCharacterCounters;
        this.oddCounts = new int[(iArr.length / 2)];
        this.evenCounts = new int[(iArr.length / 2)];
    }

    protected static int a(int[] iArr, int[][] iArr2) {
        for (int i = 0; i < iArr2.length; i++) {
            if (a(iArr, iArr2[i], (float) MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                return i;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static void a(int[] iArr, float[] fArr) {
        float f = fArr[0];
        int i = 0;
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] > f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] + 1;
    }

    protected static boolean a(int[] iArr) {
        int i = iArr[0] + iArr[1];
        float f = ((float) i) / ((float) ((iArr[2] + i) + iArr[3]));
        if (f >= MIN_FINDER_PATTERN_RATIO && f <= MAX_FINDER_PATTERN_RATIO) {
            int i2 = Integer.MIN_VALUE;
            int i3 = Integer.MAX_VALUE;
            for (int i4 : iArr) {
                if (i4 > i2) {
                    i2 = i4;
                }
                if (i4 < i3) {
                    i3 = i4;
                }
            }
            if (i2 < i3 * 10) {
                return true;
            }
        }
        return false;
    }

    protected static void b(int[] iArr, float[] fArr) {
        float f = fArr[0];
        int i = 0;
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] < f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] - 1;
    }

    /* access modifiers changed from: protected */
    public final int[] a() {
        return this.decodeFinderCounters;
    }

    /* access modifiers changed from: protected */
    public final int[] b() {
        return this.dataCharacterCounters;
    }

    /* access modifiers changed from: protected */
    public final float[] c() {
        return this.oddRoundingErrors;
    }

    /* access modifiers changed from: protected */
    public final float[] d() {
        return this.evenRoundingErrors;
    }

    /* access modifiers changed from: protected */
    public final int[] e() {
        return this.oddCounts;
    }

    /* access modifiers changed from: protected */
    public final int[] f() {
        return this.evenCounts;
    }
}
