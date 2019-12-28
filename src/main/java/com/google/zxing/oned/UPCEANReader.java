package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class UPCEANReader extends OneDReader {
    private static final float MAX_AVG_VARIANCE = 0.48f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;
    static final int[] b = {1, 1, 1};
    static final int[] c = {1, 1, 1, 1, 1};
    static final int[] d = {1, 1, 1, 1, 1, 1};
    static final int[][] e = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
    static final int[][] f = new int[20][];
    private final StringBuilder decodeRowStringBuffer = new StringBuilder(20);
    private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();
    private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();

    static {
        System.arraycopy(e, 0, f, 0, 10);
        for (int i = 10; i < 20; i++) {
            int[] iArr = e[i - 10];
            int[] iArr2 = new int[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr2[i2] = iArr[(iArr.length - i2) - 1];
            }
            f[i] = iArr2;
        }
    }

    protected UPCEANReader() {
    }

    static int a(BitArray bitArray, int[] iArr, int i, int[][] iArr2) {
        a(bitArray, i, iArr);
        int length = iArr2.length;
        float f2 = MAX_AVG_VARIANCE;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            float a = a(iArr, iArr2[i3], (float) MAX_INDIVIDUAL_VARIANCE);
            if (a < f2) {
                i2 = i3;
                f2 = a;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    static boolean a(CharSequence charSequence) {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        for (int i2 = length - 2; i2 >= 0; i2 -= 2) {
            int charAt = charSequence.charAt(i2) - '0';
            if (charAt < 0 || charAt > 9) {
                throw FormatException.getFormatInstance();
            }
            i += charAt;
        }
        int i3 = i * 3;
        for (int i4 = length - 1; i4 >= 0; i4 -= 2) {
            int charAt2 = charSequence.charAt(i4) - '0';
            if (charAt2 < 0 || charAt2 > 9) {
                throw FormatException.getFormatInstance();
            }
            i3 += charAt2;
        }
        return i3 % 10 == 0;
    }

    static int[] a(BitArray bitArray) {
        int[] iArr = new int[b.length];
        int[] iArr2 = null;
        boolean z = false;
        int i = 0;
        while (!z) {
            Arrays.fill(iArr, 0, b.length, 0);
            iArr2 = findGuardPattern(bitArray, i, false, b, iArr);
            int i2 = iArr2[0];
            int i3 = iArr2[1];
            int i4 = i2 - (i3 - i2);
            if (i4 >= 0) {
                z = bitArray.isRange(i4, i2, false);
            }
            i = i3;
        }
        return iArr2;
    }

    static int[] a(BitArray bitArray, int i, boolean z, int[] iArr) {
        return findGuardPattern(bitArray, i, z, iArr, new int[iArr.length]);
    }

    private static int[] findGuardPattern(BitArray bitArray, int i, boolean z, int[] iArr, int[] iArr2) {
        int size = bitArray.getSize();
        int nextUnset = z ? bitArray.getNextUnset(i) : bitArray.getNextSet(i);
        int length = iArr.length;
        int i2 = nextUnset;
        int i3 = 0;
        while (nextUnset < size) {
            boolean z2 = true;
            if (bitArray.get(nextUnset) ^ z) {
                iArr2[i3] = iArr2[i3] + 1;
            } else {
                int i4 = length - 1;
                if (i3 != i4) {
                    i3++;
                } else if (a(iArr2, iArr, (float) MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                    return new int[]{i2, nextUnset};
                } else {
                    i2 += iArr2[0] + iArr2[1];
                    int i5 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i5);
                    iArr2[i5] = 0;
                    iArr2[i4] = 0;
                    i3--;
                }
                iArr2[i3] = 1;
                if (z) {
                    z2 = false;
                }
                z = z2;
            }
            nextUnset++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* access modifiers changed from: protected */
    public abstract int a(BitArray bitArray, int[] iArr, StringBuilder sb);

    /* access modifiers changed from: package-private */
    public abstract BarcodeFormat a();

    /* access modifiers changed from: package-private */
    public boolean a(String str) {
        return a((CharSequence) str);
    }

    /* access modifiers changed from: package-private */
    public int[] a(BitArray bitArray, int i) {
        return a(bitArray, i, false, b);
    }

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) {
        return decodeRow(i, bitArray, a(bitArray), map);
    }

    public Result decodeRow(int i, BitArray bitArray, int[] iArr, Map<DecodeHintType, ?> map) {
        int i2;
        String a;
        int[] iArr2 = null;
        ResultPointCallback resultPointCallback = map == null ? null : (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        boolean z = true;
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint(((float) (iArr[0] + iArr[1])) / 2.0f, (float) i));
        }
        StringBuilder sb = this.decodeRowStringBuffer;
        sb.setLength(0);
        int a2 = a(bitArray, iArr, sb);
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint((float) a2, (float) i));
        }
        int[] a3 = a(bitArray, a2);
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint(((float) (a3[0] + a3[1])) / 2.0f, (float) i));
        }
        int i3 = a3[1];
        int i4 = (i3 - a3[0]) + i3;
        if (i4 >= bitArray.getSize() || !bitArray.isRange(i3, i4, false)) {
            throw NotFoundException.getNotFoundInstance();
        }
        String sb2 = sb.toString();
        if (sb2.length() < 8) {
            throw FormatException.getFormatInstance();
        } else if (a(sb2)) {
            BarcodeFormat a4 = a();
            float f2 = (float) i;
            Result result = new Result(sb2, (byte[]) null, new ResultPoint[]{new ResultPoint(((float) (iArr[1] + iArr[0])) / 2.0f, f2), new ResultPoint(((float) (a3[1] + a3[0])) / 2.0f, f2)}, a4);
            try {
                Result a5 = this.extensionReader.a(i, bitArray, a3[1]);
                result.putMetadata(ResultMetadataType.UPC_EAN_EXTENSION, a5.getText());
                result.putAllMetadata(a5.getResultMetadata());
                result.addResultPoints(a5.getResultPoints());
                i2 = a5.getText().length();
            } catch (ReaderException unused) {
                i2 = 0;
            }
            if (map != null) {
                iArr2 = (int[]) map.get(DecodeHintType.ALLOWED_EAN_EXTENSIONS);
            }
            if (iArr2 != null) {
                int length = iArr2.length;
                int i5 = 0;
                while (true) {
                    if (i5 >= length) {
                        z = false;
                        break;
                    } else if (i2 == iArr2[i5]) {
                        break;
                    } else {
                        i5++;
                    }
                }
                if (!z) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
            if ((a4 == BarcodeFormat.EAN_13 || a4 == BarcodeFormat.UPC_A) && (a = this.eanManSupport.a(sb2)) != null) {
                result.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, a);
            }
            return result;
        } else {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
