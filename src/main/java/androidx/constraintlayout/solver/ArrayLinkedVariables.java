package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.SolverVariable;
import java.io.PrintStream;
import java.util.Arrays;

public class ArrayLinkedVariables {
    private static final boolean DEBUG = false;
    private static final boolean FULL_NEW_CHECK = false;
    private static final int NONE = -1;
    private int ROW_SIZE = 8;
    int a = 0;
    private SolverVariable candidate = null;
    private int[] mArrayIndices;
    private int[] mArrayNextIndices;
    private float[] mArrayValues;
    private final Cache mCache;
    private boolean mDidFillOnce;
    private int mHead;
    private int mLast;
    private final ArrayRow mRow;

    ArrayLinkedVariables(ArrayRow arrayRow, Cache cache) {
        int i = this.ROW_SIZE;
        this.mArrayIndices = new int[i];
        this.mArrayNextIndices = new int[i];
        this.mArrayValues = new float[i];
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.mRow = arrayRow;
        this.mCache = cache;
    }

    private boolean isNew(SolverVariable solverVariable, LinearSystem linearSystem) {
        return solverVariable.usageInRowCount <= 1;
    }

    /* access modifiers changed from: package-private */
    public final SolverVariable a(int i) {
        int i2 = this.mHead;
        int i3 = 0;
        while (i2 != -1 && i3 < this.a) {
            if (i3 == i) {
                return this.mCache.c[this.mArrayIndices[i2]];
            }
            i2 = this.mArrayNextIndices[i2];
            i3++;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0086 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.constraintlayout.solver.SolverVariable a(androidx.constraintlayout.solver.LinearSystem r15) {
        /*
            r14 = this;
            int r0 = r14.mHead
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = r1
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x000a:
            r9 = -1
            if (r0 == r9) goto L_0x008e
            int r9 = r14.a
            if (r2 >= r9) goto L_0x008e
            float[] r9 = r14.mArrayValues
            r9 = r9[r0]
            r10 = 981668463(0x3a83126f, float:0.001)
            androidx.constraintlayout.solver.Cache r11 = r14.mCache
            androidx.constraintlayout.solver.SolverVariable[] r11 = r11.c
            int[] r12 = r14.mArrayIndices
            r12 = r12[r0]
            r11 = r11[r12]
            int r12 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r12 >= 0) goto L_0x0032
            r10 = -1165815185(0xffffffffba83126f, float:-0.001)
            int r10 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r10 <= 0) goto L_0x0040
            float[] r9 = r14.mArrayValues
            r9[r0] = r3
            goto L_0x003a
        L_0x0032:
            int r10 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x0040
            float[] r9 = r14.mArrayValues
            r9[r0] = r3
        L_0x003a:
            androidx.constraintlayout.solver.ArrayRow r9 = r14.mRow
            r11.removeFromRow(r9)
            r9 = 0
        L_0x0040:
            r10 = 1
            int r12 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r12 == 0) goto L_0x0086
            androidx.constraintlayout.solver.SolverVariable$Type r12 = r11.c
            androidx.constraintlayout.solver.SolverVariable$Type r13 = androidx.constraintlayout.solver.SolverVariable.Type.UNRESTRICTED
            if (r12 != r13) goto L_0x0066
            if (r4 != 0) goto L_0x0055
        L_0x004d:
            boolean r4 = r14.isNew(r11, r15)
            r6 = r4
            r5 = r9
            r4 = r11
            goto L_0x0086
        L_0x0055:
            int r12 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r12 <= 0) goto L_0x005a
            goto L_0x004d
        L_0x005a:
            if (r6 != 0) goto L_0x0086
            boolean r12 = r14.isNew(r11, r15)
            if (r12 == 0) goto L_0x0086
            r5 = r9
            r4 = r11
            r6 = 1
            goto L_0x0086
        L_0x0066:
            if (r4 != 0) goto L_0x0086
            int r12 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r12 >= 0) goto L_0x0086
            if (r1 != 0) goto L_0x0076
        L_0x006e:
            boolean r1 = r14.isNew(r11, r15)
            r8 = r1
            r7 = r9
            r1 = r11
            goto L_0x0086
        L_0x0076:
            int r12 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r12 <= 0) goto L_0x007b
            goto L_0x006e
        L_0x007b:
            if (r8 != 0) goto L_0x0086
            boolean r12 = r14.isNew(r11, r15)
            if (r12 == 0) goto L_0x0086
            r7 = r9
            r1 = r11
            r8 = 1
        L_0x0086:
            int[] r9 = r14.mArrayNextIndices
            r0 = r9[r0]
            int r2 = r2 + 1
            goto L_0x000a
        L_0x008e:
            if (r4 == 0) goto L_0x0091
            return r4
        L_0x0091:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.ArrayLinkedVariables.a(androidx.constraintlayout.solver.LinearSystem):androidx.constraintlayout.solver.SolverVariable");
    }

    /* access modifiers changed from: package-private */
    public SolverVariable a(boolean[] zArr, SolverVariable solverVariable) {
        int i = this.mHead;
        int i2 = 0;
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        while (i != -1 && i2 < this.a) {
            if (this.mArrayValues[i] < 0.0f) {
                SolverVariable solverVariable3 = this.mCache.c[this.mArrayIndices[i]];
                if ((zArr == null || !zArr[solverVariable3.id]) && solverVariable3 != solverVariable && (solverVariable3.c == SolverVariable.Type.SLACK || solverVariable3.c == SolverVariable.Type.ERROR)) {
                    float f2 = this.mArrayValues[i];
                    if (f2 < f) {
                        solverVariable2 = solverVariable3;
                        f = f2;
                    }
                }
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return solverVariable2;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.a) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] * -1.0f;
            i = this.mArrayNextIndices[i];
            i2++;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(float f) {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.a) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] / f;
            i = this.mArrayNextIndices[i];
            i2++;
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(ArrayRow arrayRow, ArrayRow arrayRow2, boolean z) {
        int i = this.mHead;
        while (true) {
            int i2 = 0;
            while (i != -1 && i2 < this.a) {
                if (this.mArrayIndices[i] == arrayRow2.a.id) {
                    float f = this.mArrayValues[i];
                    remove(arrayRow2.a, z);
                    ArrayLinkedVariables arrayLinkedVariables = arrayRow2.variables;
                    int i3 = arrayLinkedVariables.mHead;
                    int i4 = 0;
                    while (i3 != -1 && i4 < arrayLinkedVariables.a) {
                        a(this.mCache.c[arrayLinkedVariables.mArrayIndices[i3]], arrayLinkedVariables.mArrayValues[i3] * f, z);
                        i3 = arrayLinkedVariables.mArrayNextIndices[i3];
                        i4++;
                    }
                    arrayRow.b += arrayRow2.b * f;
                    if (z) {
                        arrayRow2.a.removeFromRow(arrayRow);
                    }
                    i = this.mHead;
                } else {
                    i = this.mArrayNextIndices[i];
                    i2++;
                }
            }
            return;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ArrayRow arrayRow, ArrayRow[] arrayRowArr) {
        int i = this.mHead;
        while (true) {
            int i2 = 0;
            while (i != -1 && i2 < this.a) {
                SolverVariable solverVariable = this.mCache.c[this.mArrayIndices[i]];
                if (solverVariable.a != -1) {
                    float f = this.mArrayValues[i];
                    remove(solverVariable, true);
                    ArrayRow arrayRow2 = arrayRowArr[solverVariable.a];
                    if (!arrayRow2.d) {
                        ArrayLinkedVariables arrayLinkedVariables = arrayRow2.variables;
                        int i3 = arrayLinkedVariables.mHead;
                        int i4 = 0;
                        while (i3 != -1 && i4 < arrayLinkedVariables.a) {
                            a(this.mCache.c[arrayLinkedVariables.mArrayIndices[i3]], arrayLinkedVariables.mArrayValues[i3] * f, true);
                            i3 = arrayLinkedVariables.mArrayNextIndices[i3];
                            i4++;
                        }
                    }
                    arrayRow.b += arrayRow2.b * f;
                    arrayRow2.a.removeFromRow(arrayRow);
                    i = this.mHead;
                } else {
                    i = this.mArrayNextIndices[i];
                    i2++;
                }
            }
            return;
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(SolverVariable solverVariable, float f, boolean z) {
        if (f != 0.0f) {
            int i = this.mHead;
            if (i == -1) {
                this.mHead = 0;
                float[] fArr = this.mArrayValues;
                int i2 = this.mHead;
                fArr[i2] = f;
                this.mArrayIndices[i2] = solverVariable.id;
                this.mArrayNextIndices[this.mHead] = -1;
                solverVariable.usageInRowCount++;
                solverVariable.addToRow(this.mRow);
                this.a++;
                if (!this.mDidFillOnce) {
                    this.mLast++;
                    int i3 = this.mLast;
                    int[] iArr = this.mArrayIndices;
                    if (i3 >= iArr.length) {
                        this.mDidFillOnce = true;
                        this.mLast = iArr.length - 1;
                        return;
                    }
                    return;
                }
                return;
            }
            int i4 = 0;
            int i5 = -1;
            while (i != -1 && i4 < this.a) {
                if (this.mArrayIndices[i] == solverVariable.id) {
                    float[] fArr2 = this.mArrayValues;
                    fArr2[i] = fArr2[i] + f;
                    if (fArr2[i] == 0.0f) {
                        if (i == this.mHead) {
                            this.mHead = this.mArrayNextIndices[i];
                        } else {
                            int[] iArr2 = this.mArrayNextIndices;
                            iArr2[i5] = iArr2[i];
                        }
                        if (z) {
                            solverVariable.removeFromRow(this.mRow);
                        }
                        if (this.mDidFillOnce) {
                            this.mLast = i;
                        }
                        solverVariable.usageInRowCount--;
                        this.a--;
                        return;
                    }
                    return;
                }
                if (this.mArrayIndices[i] < solverVariable.id) {
                    i5 = i;
                }
                i = this.mArrayNextIndices[i];
                i4++;
            }
            int i6 = this.mLast;
            int i7 = i6 + 1;
            if (this.mDidFillOnce) {
                int[] iArr3 = this.mArrayIndices;
                if (iArr3[i6] != -1) {
                    i6 = iArr3.length;
                }
            } else {
                i6 = i7;
            }
            int[] iArr4 = this.mArrayIndices;
            if (i6 >= iArr4.length && this.a < iArr4.length) {
                int i8 = 0;
                while (true) {
                    int[] iArr5 = this.mArrayIndices;
                    if (i8 >= iArr5.length) {
                        break;
                    } else if (iArr5[i8] == -1) {
                        i6 = i8;
                        break;
                    } else {
                        i8++;
                    }
                }
            }
            int[] iArr6 = this.mArrayIndices;
            if (i6 >= iArr6.length) {
                i6 = iArr6.length;
                this.ROW_SIZE *= 2;
                this.mDidFillOnce = false;
                this.mLast = i6 - 1;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
                this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
            }
            this.mArrayIndices[i6] = solverVariable.id;
            this.mArrayValues[i6] = f;
            if (i5 != -1) {
                int[] iArr7 = this.mArrayNextIndices;
                iArr7[i6] = iArr7[i5];
                iArr7[i5] = i6;
            } else {
                this.mArrayNextIndices[i6] = this.mHead;
                this.mHead = i6;
            }
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.mRow);
            this.a++;
            if (!this.mDidFillOnce) {
                this.mLast++;
            }
            int i9 = this.mLast;
            int[] iArr8 = this.mArrayIndices;
            if (i9 >= iArr8.length) {
                this.mDidFillOnce = true;
                this.mLast = iArr8.length - 1;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a(SolverVariable solverVariable) {
        int i = this.mHead;
        if (i == -1) {
            return false;
        }
        int i2 = 0;
        while (i != -1 && i2 < this.a) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return true;
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final float b(int i) {
        int i2 = this.mHead;
        int i3 = 0;
        while (i2 != -1 && i3 < this.a) {
            if (i3 == i) {
                return this.mArrayValues[i2];
            }
            i2 = this.mArrayNextIndices[i2];
            i3++;
        }
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return (this.mArrayIndices.length * 4 * 3) + 0 + 36;
    }

    public final void clear() {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.a) {
            SolverVariable solverVariable = this.mCache.c[this.mArrayIndices[i]];
            if (solverVariable != null) {
                solverVariable.removeFromRow(this.mRow);
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.a = 0;
    }

    public void display() {
        int i = this.a;
        System.out.print("{ ");
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable a2 = a(i2);
            if (a2 != null) {
                PrintStream printStream = System.out;
                printStream.print(a2 + " = " + b(i2) + " ");
            }
        }
        System.out.println(" }");
    }

    public final float get(SolverVariable solverVariable) {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.a) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return this.mArrayValues[i];
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return 0.0f;
    }

    public final void put(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            remove(solverVariable, true);
            return;
        }
        int i = this.mHead;
        if (i == -1) {
            this.mHead = 0;
            float[] fArr = this.mArrayValues;
            int i2 = this.mHead;
            fArr[i2] = f;
            this.mArrayIndices[i2] = solverVariable.id;
            this.mArrayNextIndices[this.mHead] = -1;
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.mRow);
            this.a++;
            if (!this.mDidFillOnce) {
                this.mLast++;
                int i3 = this.mLast;
                int[] iArr = this.mArrayIndices;
                if (i3 >= iArr.length) {
                    this.mDidFillOnce = true;
                    this.mLast = iArr.length - 1;
                    return;
                }
                return;
            }
            return;
        }
        int i4 = 0;
        int i5 = -1;
        while (i != -1 && i4 < this.a) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                this.mArrayValues[i] = f;
                return;
            }
            if (this.mArrayIndices[i] < solverVariable.id) {
                i5 = i;
            }
            i = this.mArrayNextIndices[i];
            i4++;
        }
        int i6 = this.mLast;
        int i7 = i6 + 1;
        if (this.mDidFillOnce) {
            int[] iArr2 = this.mArrayIndices;
            if (iArr2[i6] != -1) {
                i6 = iArr2.length;
            }
        } else {
            i6 = i7;
        }
        int[] iArr3 = this.mArrayIndices;
        if (i6 >= iArr3.length && this.a < iArr3.length) {
            int i8 = 0;
            while (true) {
                int[] iArr4 = this.mArrayIndices;
                if (i8 >= iArr4.length) {
                    break;
                } else if (iArr4[i8] == -1) {
                    i6 = i8;
                    break;
                } else {
                    i8++;
                }
            }
        }
        int[] iArr5 = this.mArrayIndices;
        if (i6 >= iArr5.length) {
            i6 = iArr5.length;
            this.ROW_SIZE *= 2;
            this.mDidFillOnce = false;
            this.mLast = i6 - 1;
            this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
            this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
            this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
        }
        this.mArrayIndices[i6] = solverVariable.id;
        this.mArrayValues[i6] = f;
        if (i5 != -1) {
            int[] iArr6 = this.mArrayNextIndices;
            iArr6[i6] = iArr6[i5];
            iArr6[i5] = i6;
        } else {
            this.mArrayNextIndices[i6] = this.mHead;
            this.mHead = i6;
        }
        solverVariable.usageInRowCount++;
        solverVariable.addToRow(this.mRow);
        this.a++;
        if (!this.mDidFillOnce) {
            this.mLast++;
        }
        if (this.a >= this.mArrayIndices.length) {
            this.mDidFillOnce = true;
        }
        int i9 = this.mLast;
        int[] iArr7 = this.mArrayIndices;
        if (i9 >= iArr7.length) {
            this.mDidFillOnce = true;
            this.mLast = iArr7.length - 1;
        }
    }

    public final float remove(SolverVariable solverVariable, boolean z) {
        if (this.candidate == solverVariable) {
            this.candidate = null;
        }
        int i = this.mHead;
        if (i == -1) {
            return 0.0f;
        }
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.a) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                if (i == this.mHead) {
                    this.mHead = this.mArrayNextIndices[i];
                } else {
                    int[] iArr = this.mArrayNextIndices;
                    iArr[i3] = iArr[i];
                }
                if (z) {
                    solverVariable.removeFromRow(this.mRow);
                }
                solverVariable.usageInRowCount--;
                this.a--;
                this.mArrayIndices[i] = -1;
                if (this.mDidFillOnce) {
                    this.mLast = i;
                }
                return this.mArrayValues[i];
            }
            i2++;
            i3 = i;
            i = this.mArrayNextIndices[i];
        }
        return 0.0f;
    }

    public String toString() {
        String str = "";
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.a) {
            str = ((str + " -> ") + this.mArrayValues[i] + " : ") + this.mCache.c[this.mArrayIndices[i]];
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return str;
    }
}
