package androidx.constraintlayout.solver;

import androidx.exifinterface.media.ExifInterface;
import java.util.Arrays;

public class SolverVariable {
    private static final boolean INTERNAL_DEBUG = false;
    public static final int STRENGTH_BARRIER = 7;
    public static final int STRENGTH_EQUALITY = 5;
    public static final int STRENGTH_FIXED = 6;
    public static final int STRENGTH_HIGH = 3;
    public static final int STRENGTH_HIGHEST = 4;
    public static final int STRENGTH_LOW = 1;
    public static final int STRENGTH_MEDIUM = 2;
    public static final int STRENGTH_NONE = 0;
    private static int uniqueConstantId = 1;
    private static int uniqueErrorId = 1;
    private static int uniqueId = 1;
    private static int uniqueSlackId = 1;
    private static int uniqueUnrestrictedId = 1;
    int a = -1;
    float[] b = new float[7];
    Type c;
    public float computedValue;
    ArrayRow[] d = new ArrayRow[8];
    int e = 0;
    public int id = -1;
    private String mName;
    public int strength = 0;
    public int usageInRowCount = 0;

    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(Type type, String str) {
        this.c = type;
    }

    public SolverVariable(String str, Type type) {
        this.mName = str;
        this.c = type;
    }

    static void a() {
        uniqueErrorId++;
    }

    private static String getUniqueName(Type type, String str) {
        StringBuilder sb;
        int i;
        if (str != null) {
            sb = new StringBuilder();
            sb.append(str);
            i = uniqueErrorId;
        } else {
            switch (type) {
                case UNRESTRICTED:
                    sb = new StringBuilder();
                    sb.append("U");
                    i = uniqueUnrestrictedId + 1;
                    uniqueUnrestrictedId = i;
                    break;
                case CONSTANT:
                    sb = new StringBuilder();
                    sb.append("C");
                    i = uniqueConstantId + 1;
                    uniqueConstantId = i;
                    break;
                case SLACK:
                    sb = new StringBuilder();
                    sb.append(ExifInterface.LATITUDE_SOUTH);
                    i = uniqueSlackId + 1;
                    uniqueSlackId = i;
                    break;
                case ERROR:
                    sb = new StringBuilder();
                    sb.append("e");
                    i = uniqueErrorId + 1;
                    uniqueErrorId = i;
                    break;
                case UNKNOWN:
                    sb = new StringBuilder();
                    sb.append(ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
                    i = uniqueId + 1;
                    uniqueId = i;
                    break;
                default:
                    throw new AssertionError(type.name());
            }
        }
        sb.append(i);
        return sb.toString();
    }

    public final void addToRow(ArrayRow arrayRow) {
        int i = 0;
        while (true) {
            int i2 = this.e;
            if (i >= i2) {
                ArrayRow[] arrayRowArr = this.d;
                if (i2 >= arrayRowArr.length) {
                    this.d = (ArrayRow[]) Arrays.copyOf(arrayRowArr, arrayRowArr.length * 2);
                }
                ArrayRow[] arrayRowArr2 = this.d;
                int i3 = this.e;
                arrayRowArr2[i3] = arrayRow;
                this.e = i3 + 1;
                return;
            } else if (this.d[i] != arrayRow) {
                i++;
            } else {
                return;
            }
        }
    }

    public String getName() {
        return this.mName;
    }

    public final void removeFromRow(ArrayRow arrayRow) {
        int i = this.e;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.d[i2] == arrayRow) {
                for (int i3 = 0; i3 < (i - i2) - 1; i3++) {
                    ArrayRow[] arrayRowArr = this.d;
                    int i4 = i2 + i3;
                    arrayRowArr[i4] = arrayRowArr[i4 + 1];
                }
                this.e--;
                return;
            }
        }
    }

    public void reset() {
        this.mName = null;
        this.c = Type.UNKNOWN;
        this.strength = 0;
        this.id = -1;
        this.a = -1;
        this.computedValue = 0.0f;
        this.e = 0;
        this.usageInRowCount = 0;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setType(Type type, String str) {
        this.c = type;
    }

    public String toString() {
        return "" + this.mName;
    }

    public final void updateReferencesWithNewDefinition(ArrayRow arrayRow) {
        int i = this.e;
        for (int i2 = 0; i2 < i; i2++) {
            this.d[i2].variables.a(this.d[i2], arrayRow, false);
        }
        this.e = 0;
    }
}
