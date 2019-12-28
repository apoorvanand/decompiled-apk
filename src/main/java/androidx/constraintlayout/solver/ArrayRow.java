package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import com.facebook.appevents.UserDataStore;

public class ArrayRow implements LinearSystem.Row {
    private static final boolean DEBUG = false;
    private static final float epsilon = 0.001f;
    SolverVariable a = null;
    float b = 0.0f;
    boolean c = false;
    boolean d = false;
    public final ArrayLinkedVariables variables;

    public ArrayRow(Cache cache) {
        this.variables = new ArrayLinkedVariables(this, cache);
    }

    /* access modifiers changed from: package-private */
    public ArrayRow a(SolverVariable solverVariable, int i) {
        this.a = solverVariable;
        float f = (float) i;
        solverVariable.computedValue = f;
        this.b = f;
        this.d = true;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ArrayRow a(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2) {
        float f2;
        if (solverVariable2 == solverVariable3) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable2, -2.0f);
            return this;
        }
        if (f == 0.5f) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            if (i > 0 || i2 > 0) {
                f2 = (float) ((-i) + i2);
            }
            return this;
        } else if (f <= 0.0f) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            f2 = (float) i;
        } else if (f >= 1.0f) {
            this.variables.put(solverVariable3, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            f2 = (float) i2;
        } else {
            float f3 = 1.0f - f;
            this.variables.put(solverVariable, f3 * 1.0f);
            this.variables.put(solverVariable2, f3 * -1.0f);
            this.variables.put(solverVariable3, -1.0f * f);
            this.variables.put(solverVariable4, 1.0f * f);
            if (i > 0 || i2 > 0) {
                f2 = (((float) (-i)) * f3) + (((float) i2) * f);
            }
            return this;
        }
        this.b = f2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ArrayRow a(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, float f) {
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, 1.0f - f);
        this.variables.put(solverVariable3, f);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        SolverVariable solverVariable = this.a;
        return solverVariable != null && (solverVariable.c == SolverVariable.Type.UNRESTRICTED || this.b >= 0.0f);
    }

    /* access modifiers changed from: package-private */
    public boolean a(LinearSystem linearSystem) {
        boolean z;
        SolverVariable a2 = this.variables.a(linearSystem);
        if (a2 == null) {
            z = true;
        } else {
            c(a2);
            z = false;
        }
        if (this.variables.a == 0) {
            this.d = true;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean a(SolverVariable solverVariable) {
        return this.variables.a(solverVariable);
    }

    public ArrayRow addError(LinearSystem linearSystem, int i) {
        this.variables.put(linearSystem.createErrorVariable(i, "ep"), 1.0f);
        this.variables.put(linearSystem.createErrorVariable(i, UserDataStore.EMAIL), -1.0f);
        return this;
    }

    public void addError(SolverVariable solverVariable) {
        float f = 1.0f;
        if (solverVariable.strength != 1) {
            if (solverVariable.strength == 2) {
                f = 1000.0f;
            } else if (solverVariable.strength == 3) {
                f = 1000000.0f;
            } else if (solverVariable.strength == 4) {
                f = 1.0E9f;
            } else if (solverVariable.strength == 5) {
                f = 1.0E12f;
            }
        }
        this.variables.put(solverVariable, f);
    }

    /* access modifiers changed from: package-private */
    public ArrayRow b(SolverVariable solverVariable, int i) {
        this.variables.put(solverVariable, (float) i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public SolverVariable b(SolverVariable solverVariable) {
        return this.variables.a((boolean[]) null, solverVariable);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String b() {
        /*
            r10 = this;
            java.lang.String r0 = ""
            androidx.constraintlayout.solver.SolverVariable r1 = r10.a
            if (r1 != 0) goto L_0x0014
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "0"
            r1.append(r0)
            goto L_0x0021
        L_0x0014:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            androidx.constraintlayout.solver.SolverVariable r0 = r10.a
            r1.append(r0)
        L_0x0021:
            java.lang.String r0 = r1.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = " = "
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            float r1 = r10.b
            r2 = 0
            r3 = 1
            r4 = 0
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0052
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            float r0 = r10.b
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r1 = 1
            goto L_0x0053
        L_0x0052:
            r1 = 0
        L_0x0053:
            androidx.constraintlayout.solver.ArrayLinkedVariables r5 = r10.variables
            int r5 = r5.a
        L_0x0057:
            if (r2 >= r5) goto L_0x00d2
            androidx.constraintlayout.solver.ArrayLinkedVariables r6 = r10.variables
            androidx.constraintlayout.solver.SolverVariable r6 = r6.a((int) r2)
            if (r6 != 0) goto L_0x0062
            goto L_0x00cf
        L_0x0062:
            androidx.constraintlayout.solver.ArrayLinkedVariables r7 = r10.variables
            float r7 = r7.b(r2)
            int r8 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r8 != 0) goto L_0x006d
            goto L_0x00cf
        L_0x006d:
            java.lang.String r6 = r6.toString()
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r1 != 0) goto L_0x0084
            int r1 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r1 >= 0) goto L_0x00ab
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "- "
            goto L_0x00a2
        L_0x0084:
            if (r8 <= 0) goto L_0x0098
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = " + "
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            goto L_0x00ab
        L_0x0098:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = " - "
        L_0x00a2:
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            float r7 = r7 * r9
        L_0x00ab:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x00b7
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            goto L_0x00c4
        L_0x00b7:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r7)
            java.lang.String r0 = " "
        L_0x00c4:
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
            r1 = 1
        L_0x00cf:
            int r2 = r2 + 1
            goto L_0x0057
        L_0x00d2:
            if (r1 != 0) goto L_0x00e5
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "0.0"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
        L_0x00e5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.ArrayRow.b():java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return (this.a != null ? 4 : 0) + 4 + 4 + this.variables.b();
    }

    /* access modifiers changed from: package-private */
    public void c(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.a;
        if (solverVariable2 != null) {
            this.variables.put(solverVariable2, -1.0f);
            this.a = null;
        }
        float remove = this.variables.remove(solverVariable, true) * -1.0f;
        this.a = solverVariable;
        if (remove != 1.0f) {
            this.b /= remove;
            this.variables.a(remove);
        }
    }

    public void clear() {
        this.variables.clear();
        this.a = null;
        this.b = 0.0f;
    }

    public ArrayRow createRowDimensionRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, 1.0f);
        this.variables.put(solverVariable3, f);
        this.variables.put(solverVariable4, -f);
        return this;
    }

    public ArrayRow createRowEqualDimension(float f, float f2, float f3, SolverVariable solverVariable, int i, SolverVariable solverVariable2, int i2, SolverVariable solverVariable3, int i3, SolverVariable solverVariable4, int i4) {
        if (f2 == 0.0f || f == f3) {
            this.b = (float) (((-i) - i2) + i3 + i4);
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else {
            float f4 = (f / f2) / (f3 / f2);
            this.b = ((float) ((-i) - i2)) + (((float) i3) * f4) + (((float) i4) * f4);
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, f4);
            this.variables.put(solverVariable3, -f4);
        }
        return this;
    }

    public ArrayRow createRowEqualMatchDimensions(float f, float f2, float f3, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.b = 0.0f;
        if (f2 == 0.0f || f == f3) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else if (f == 0.0f) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
        } else if (f3 == 0.0f) {
            this.variables.put(solverVariable3, 1.0f);
            this.variables.put(solverVariable4, -1.0f);
        } else {
            float f4 = (f / f2) / (f3 / f2);
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, f4);
            this.variables.put(solverVariable3, -f4);
        }
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, int i) {
        ArrayLinkedVariables arrayLinkedVariables;
        float f;
        if (i < 0) {
            this.b = (float) (i * -1);
            arrayLinkedVariables = this.variables;
            f = 1.0f;
        } else {
            this.b = (float) i;
            arrayLinkedVariables = this.variables;
            f = -1.0f;
        }
        arrayLinkedVariables.put(solverVariable, f);
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, SolverVariable solverVariable2, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = (float) i;
        }
        if (!z) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
        } else {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
        }
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable solverVariable, int i, SolverVariable solverVariable2) {
        this.b = (float) i;
        this.variables.put(solverVariable, -1.0f);
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = (float) i;
        }
        if (!z) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.variables.put(solverVariable3, 1.0f);
        } else {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, -1.0f);
        }
        return this;
    }

    public ArrayRow createRowLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = (float) i;
        }
        if (!z) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, 1.0f);
        }
        return this;
    }

    public ArrayRow createRowWithAngle(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.variables.put(solverVariable3, 0.5f);
        this.variables.put(solverVariable4, 0.5f);
        this.variables.put(solverVariable, -0.5f);
        this.variables.put(solverVariable2, -0.5f);
        this.b = -f;
        return this;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        float f = this.b;
        if (f < 0.0f) {
            this.b = f * -1.0f;
            this.variables.a();
        }
    }

    public SolverVariable getKey() {
        return this.a;
    }

    public SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr) {
        return this.variables.a(zArr, (SolverVariable) null);
    }

    public void initFromRow(LinearSystem.Row row) {
        if (row instanceof ArrayRow) {
            ArrayRow arrayRow = (ArrayRow) row;
            this.a = null;
            this.variables.clear();
            for (int i = 0; i < arrayRow.variables.a; i++) {
                this.variables.a(arrayRow.variables.a(i), arrayRow.variables.b(i), true);
            }
        }
    }

    public boolean isEmpty() {
        return this.a == null && this.b == 0.0f && this.variables.a == 0;
    }

    public void reset() {
        this.a = null;
        this.variables.clear();
        this.b = 0.0f;
        this.d = false;
    }

    public String toString() {
        return b();
    }
}
