package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

public class LinearSystem {
    private static final boolean DEBUG = false;
    public static final boolean FULL_DEBUG = false;
    private static int POOL_SIZE = 1000;
    public static Metrics sMetrics;
    private int TABLE_SIZE = 32;
    int a = 0;
    ArrayRow[] b;
    int c;
    int d;
    final Cache e;
    public boolean graphOptimizer;
    private boolean[] mAlreadyTestedCandidates;
    private Row mGoal;
    private int mMaxColumns;
    private int mMaxRows;
    private SolverVariable[] mPoolVariables;
    private int mPoolVariablesCount;
    private final Row mTempGoal;
    private HashMap<String, SolverVariable> mVariables = null;
    private ArrayRow[] tempClientsCopy;

    interface Row {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);

        void initFromRow(Row row);

        boolean isEmpty();
    }

    public LinearSystem() {
        int i = this.TABLE_SIZE;
        this.mMaxColumns = i;
        this.b = null;
        this.graphOptimizer = false;
        this.mAlreadyTestedCandidates = new boolean[i];
        this.c = 1;
        this.d = 0;
        this.mMaxRows = i;
        this.mPoolVariables = new SolverVariable[POOL_SIZE];
        this.mPoolVariablesCount = 0;
        this.tempClientsCopy = new ArrayRow[i];
        this.b = new ArrayRow[i];
        releaseRows();
        this.e = new Cache();
        this.mGoal = new GoalRow(this.e);
        this.mTempGoal = new ArrayRow(this.e);
    }

    private SolverVariable acquireSolverVariable(SolverVariable.Type type, String str) {
        SolverVariable acquire = this.e.b.acquire();
        if (acquire == null) {
            acquire = new SolverVariable(type, str);
        } else {
            acquire.reset();
        }
        acquire.setType(type, str);
        int i = this.mPoolVariablesCount;
        int i2 = POOL_SIZE;
        if (i >= i2) {
            POOL_SIZE = i2 * 2;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, POOL_SIZE);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i3 = this.mPoolVariablesCount;
        this.mPoolVariablesCount = i3 + 1;
        solverVariableArr[i3] = acquire;
        return acquire;
    }

    private void addError(ArrayRow arrayRow) {
        arrayRow.addError(this, 0);
    }

    private final void addRow(ArrayRow arrayRow) {
        if (this.b[this.d] != null) {
            this.e.a.release(this.b[this.d]);
        }
        this.b[this.d] = arrayRow;
        SolverVariable solverVariable = arrayRow.a;
        int i = this.d;
        solverVariable.a = i;
        this.d = i + 1;
        arrayRow.a.updateReferencesWithNewDefinition(arrayRow);
    }

    private void addSingleError(ArrayRow arrayRow, int i) {
        a(arrayRow, i, 0);
    }

    private void computeValues() {
        for (int i = 0; i < this.d; i++) {
            ArrayRow arrayRow = this.b[i];
            arrayRow.a.computedValue = arrayRow.b;
        }
    }

    public static ArrayRow createRowCentering(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        createRow.a(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (z) {
            LinearSystem linearSystem2 = linearSystem;
            createRow.addError(linearSystem, 4);
        }
        return createRow;
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, float f, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        if (z) {
            linearSystem.addError(createRow);
        }
        return createRow.a(solverVariable, solverVariable2, solverVariable3, f);
    }

    public static ArrayRow createRowEquals(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowEquals(solverVariable, solverVariable2, i);
        if (z) {
            linearSystem.addSingleError(createRow, 1);
        }
        return createRow;
    }

    public static ArrayRow createRowGreaterThan(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        SolverVariable createSlackVariable = linearSystem.createSlackVariable();
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (z) {
            linearSystem.addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f));
        }
        return createRow;
    }

    public static ArrayRow createRowLowerThan(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        SolverVariable createSlackVariable = linearSystem.createSlackVariable();
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (z) {
            linearSystem.addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f));
        }
        return createRow;
    }

    private SolverVariable createVariable(String str, SolverVariable.Type type) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.variables++;
        }
        if (this.c + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(type, (String) null);
        acquireSolverVariable.setName(str);
        this.a++;
        this.c++;
        acquireSolverVariable.id = this.a;
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        this.mVariables.put(str, acquireSolverVariable);
        this.e.c[this.a] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    private void displayRows() {
        displaySolverVariables();
        String str = "";
        for (int i = 0; i < this.d; i++) {
            str = (str + this.b[i]) + "\n";
        }
        System.out.println(str + this.mGoal + "\n");
    }

    private void displaySolverVariables() {
        System.out.println("Display Rows (" + this.d + "x" + this.c + ")\n");
    }

    private int enforceBFS(Row row) {
        float f;
        boolean z;
        int i = 0;
        while (true) {
            f = 0.0f;
            if (i >= this.d) {
                z = false;
                break;
            } else if (this.b[i].a.c != SolverVariable.Type.UNRESTRICTED && this.b[i].b < 0.0f) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            return 0;
        }
        boolean z2 = false;
        int i2 = 0;
        while (!z2) {
            Metrics metrics = sMetrics;
            if (metrics != null) {
                metrics.bfs++;
            }
            i2++;
            int i3 = 0;
            int i4 = -1;
            int i5 = -1;
            float f2 = Float.MAX_VALUE;
            int i6 = 0;
            while (i3 < this.d) {
                ArrayRow arrayRow = this.b[i3];
                if (arrayRow.a.c != SolverVariable.Type.UNRESTRICTED && !arrayRow.d && arrayRow.b < f) {
                    int i7 = 1;
                    while (i7 < this.c) {
                        SolverVariable solverVariable = this.e.c[i7];
                        float f3 = arrayRow.variables.get(solverVariable);
                        if (f3 > f) {
                            int i8 = i6;
                            float f4 = f2;
                            int i9 = i5;
                            int i10 = i4;
                            for (int i11 = 0; i11 < 7; i11++) {
                                float f5 = solverVariable.b[i11] / f3;
                                if ((f5 < f4 && i11 == i8) || i11 > i8) {
                                    i9 = i7;
                                    i10 = i3;
                                    f4 = f5;
                                    i8 = i11;
                                }
                            }
                            i4 = i10;
                            i5 = i9;
                            f2 = f4;
                            i6 = i8;
                        }
                        i7++;
                        f = 0.0f;
                    }
                }
                i3++;
                f = 0.0f;
            }
            if (i4 != -1) {
                ArrayRow arrayRow2 = this.b[i4];
                arrayRow2.a.a = -1;
                Metrics metrics2 = sMetrics;
                if (metrics2 != null) {
                    metrics2.pivots++;
                }
                arrayRow2.c(this.e.c[i5]);
                arrayRow2.a.a = i4;
                arrayRow2.a.updateReferencesWithNewDefinition(arrayRow2);
            } else {
                z2 = true;
            }
            if (i2 > this.c / 2) {
                z2 = true;
            }
            f = 0.0f;
        }
        return i2;
    }

    private String getDisplaySize(int i) {
        int i2 = i * 4;
        int i3 = i2 / 1024;
        int i4 = i3 / 1024;
        if (i4 > 0) {
            return "" + i4 + " Mb";
        } else if (i3 > 0) {
            return "" + i3 + " Kb";
        } else {
            return "" + i2 + " bytes";
        }
    }

    private String getDisplayStrength(int i) {
        return i == 1 ? "LOW" : i == 2 ? "MEDIUM" : i == 3 ? "HIGH" : i == 4 ? "HIGHEST" : i == 5 ? "EQUALITY" : i == 6 ? "FIXED" : "NONE";
    }

    public static Metrics getMetrics() {
        return sMetrics;
    }

    private void increaseTableSize() {
        this.TABLE_SIZE *= 2;
        this.b = (ArrayRow[]) Arrays.copyOf(this.b, this.TABLE_SIZE);
        Cache cache = this.e;
        cache.c = (SolverVariable[]) Arrays.copyOf(cache.c, this.TABLE_SIZE);
        int i = this.TABLE_SIZE;
        this.mAlreadyTestedCandidates = new boolean[i];
        this.mMaxColumns = i;
        this.mMaxRows = i;
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.tableSizeIncrease++;
            Metrics metrics2 = sMetrics;
            metrics2.maxTableSize = Math.max(metrics2.maxTableSize, (long) this.TABLE_SIZE);
            Metrics metrics3 = sMetrics;
            metrics3.lastTableSize = metrics3.maxTableSize;
        }
    }

    private final int optimize(Row row, boolean z) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.optimize++;
        }
        for (int i = 0; i < this.c; i++) {
            this.mAlreadyTestedCandidates[i] = false;
        }
        boolean z2 = false;
        int i2 = 0;
        while (!z2) {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.iterations++;
            }
            i2++;
            if (i2 >= this.c * 2) {
                return i2;
            }
            if (row.getKey() != null) {
                this.mAlreadyTestedCandidates[row.getKey().id] = true;
            }
            SolverVariable pivotCandidate = row.getPivotCandidate(this, this.mAlreadyTestedCandidates);
            if (pivotCandidate != null) {
                if (this.mAlreadyTestedCandidates[pivotCandidate.id]) {
                    return i2;
                }
                this.mAlreadyTestedCandidates[pivotCandidate.id] = true;
            }
            if (pivotCandidate != null) {
                int i3 = -1;
                float f = Float.MAX_VALUE;
                for (int i4 = 0; i4 < this.d; i4++) {
                    ArrayRow arrayRow = this.b[i4];
                    if (arrayRow.a.c != SolverVariable.Type.UNRESTRICTED && !arrayRow.d && arrayRow.a(pivotCandidate)) {
                        float f2 = arrayRow.variables.get(pivotCandidate);
                        if (f2 < 0.0f) {
                            float f3 = (-arrayRow.b) / f2;
                            if (f3 < f) {
                                i3 = i4;
                                f = f3;
                            }
                        }
                    }
                }
                if (i3 > -1) {
                    ArrayRow arrayRow2 = this.b[i3];
                    arrayRow2.a.a = -1;
                    Metrics metrics3 = sMetrics;
                    if (metrics3 != null) {
                        metrics3.pivots++;
                    }
                    arrayRow2.c(pivotCandidate);
                    arrayRow2.a.a = i3;
                    arrayRow2.a.updateReferencesWithNewDefinition(arrayRow2);
                }
            }
            z2 = true;
        }
        return i2;
    }

    private void releaseRows() {
        int i = 0;
        while (true) {
            ArrayRow[] arrayRowArr = this.b;
            if (i < arrayRowArr.length) {
                ArrayRow arrayRow = arrayRowArr[i];
                if (arrayRow != null) {
                    this.e.a.release(arrayRow);
                }
                this.b[i] = null;
                i++;
            } else {
                return;
            }
        }
    }

    private final void updateRowFromVariables(ArrayRow arrayRow) {
        if (this.d > 0) {
            arrayRow.variables.a(arrayRow, this.b);
            if (arrayRow.variables.a == 0) {
                arrayRow.d = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ArrayRow arrayRow, int i, int i2) {
        arrayRow.b(createErrorVariable(i2, (String) null), i);
    }

    /* access modifiers changed from: package-private */
    public void a(Row row) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimizeGoal++;
            Metrics metrics2 = sMetrics;
            metrics2.maxVariables = Math.max(metrics2.maxVariables, (long) this.c);
            Metrics metrics3 = sMetrics;
            metrics3.maxRows = Math.max(metrics3.maxRows, (long) this.d);
        }
        updateRowFromVariables((ArrayRow) row);
        enforceBFS(row);
        optimize(row, false);
        computeValues();
    }

    public void addCenterPoint(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f, int i) {
        ConstraintWidget constraintWidget3 = constraintWidget;
        ConstraintWidget constraintWidget4 = constraintWidget2;
        SolverVariable createObjectVariable = createObjectVariable(constraintWidget3.getAnchor(ConstraintAnchor.Type.LEFT));
        SolverVariable createObjectVariable2 = createObjectVariable(constraintWidget3.getAnchor(ConstraintAnchor.Type.TOP));
        SolverVariable createObjectVariable3 = createObjectVariable(constraintWidget3.getAnchor(ConstraintAnchor.Type.RIGHT));
        SolverVariable createObjectVariable4 = createObjectVariable(constraintWidget3.getAnchor(ConstraintAnchor.Type.BOTTOM));
        SolverVariable createObjectVariable5 = createObjectVariable(constraintWidget4.getAnchor(ConstraintAnchor.Type.LEFT));
        SolverVariable createObjectVariable6 = createObjectVariable(constraintWidget4.getAnchor(ConstraintAnchor.Type.TOP));
        SolverVariable createObjectVariable7 = createObjectVariable(constraintWidget4.getAnchor(ConstraintAnchor.Type.RIGHT));
        SolverVariable createObjectVariable8 = createObjectVariable(constraintWidget4.getAnchor(ConstraintAnchor.Type.BOTTOM));
        ArrayRow createRow = createRow();
        double d2 = (double) f;
        SolverVariable solverVariable = createObjectVariable3;
        double d3 = (double) i;
        createRow.createRowWithAngle(createObjectVariable2, createObjectVariable4, createObjectVariable6, createObjectVariable8, (float) (Math.sin(d2) * d3));
        addConstraint(createRow);
        ArrayRow createRow2 = createRow();
        createRow2.createRowWithAngle(createObjectVariable, solverVariable, createObjectVariable5, createObjectVariable7, (float) (Math.cos(d2) * d3));
        addConstraint(createRow2);
    }

    public void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, int i3) {
        int i4 = i3;
        ArrayRow createRow = createRow();
        createRow.a(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (i4 != 6) {
            createRow.addError(this, i4);
        }
        addConstraint(createRow);
    }

    public void addConstraint(ArrayRow arrayRow) {
        SolverVariable b2;
        if (arrayRow != null) {
            Metrics metrics = sMetrics;
            if (metrics != null) {
                metrics.constraints++;
                if (arrayRow.d) {
                    sMetrics.simpleconstraints++;
                }
            }
            if (this.d + 1 >= this.mMaxRows || this.c + 1 >= this.mMaxColumns) {
                increaseTableSize();
            }
            boolean z = false;
            if (!arrayRow.d) {
                updateRowFromVariables(arrayRow);
                if (!arrayRow.isEmpty()) {
                    arrayRow.d();
                    if (arrayRow.a(this)) {
                        SolverVariable createExtraVariable = createExtraVariable();
                        arrayRow.a = createExtraVariable;
                        addRow(arrayRow);
                        this.mTempGoal.initFromRow(arrayRow);
                        optimize(this.mTempGoal, true);
                        if (createExtraVariable.a == -1) {
                            if (arrayRow.a == createExtraVariable && (b2 = arrayRow.b(createExtraVariable)) != null) {
                                Metrics metrics2 = sMetrics;
                                if (metrics2 != null) {
                                    metrics2.pivots++;
                                }
                                arrayRow.c(b2);
                            }
                            if (!arrayRow.d) {
                                arrayRow.a.updateReferencesWithNewDefinition(arrayRow);
                            }
                            this.d--;
                        }
                        z = true;
                    }
                    if (!arrayRow.a()) {
                        return;
                    }
                } else {
                    return;
                }
            }
            if (!z) {
                addRow(arrayRow);
            }
        }
    }

    public ArrayRow addEquality(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        createRow.createRowEquals(solverVariable, solverVariable2, i);
        if (i2 != 6) {
            createRow.addError(this, i2);
        }
        addConstraint(createRow);
        return createRow;
    }

    public void addEquality(SolverVariable solverVariable, int i) {
        ArrayRow arrayRow;
        int i2 = solverVariable.a;
        if (solverVariable.a != -1) {
            ArrayRow arrayRow2 = this.b[i2];
            if (!arrayRow2.d) {
                if (arrayRow2.variables.a == 0) {
                    arrayRow2.d = true;
                } else {
                    arrayRow = createRow();
                    arrayRow.createRowEquals(solverVariable, i);
                }
            }
            arrayRow2.b = (float) i;
            return;
        }
        arrayRow = createRow();
        arrayRow.a(solverVariable, i);
        addConstraint(arrayRow);
    }

    public void addEquality(SolverVariable solverVariable, int i, int i2) {
        ArrayRow arrayRow;
        int i3 = solverVariable.a;
        if (solverVariable.a != -1) {
            ArrayRow arrayRow2 = this.b[i3];
            if (arrayRow2.d) {
                arrayRow2.b = (float) i;
                return;
            } else {
                arrayRow = createRow();
                arrayRow.createRowEquals(solverVariable, i);
            }
        } else {
            arrayRow = createRow();
            arrayRow.a(solverVariable, i);
        }
        arrayRow.addError(this, i2);
        addConstraint(arrayRow);
    }

    public void addGreaterBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, 0);
        if (z) {
            a(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f), 1);
        }
        addConstraint(createRow);
    }

    public void addGreaterThan(SolverVariable solverVariable, int i) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, i, createSlackVariable);
        addConstraint(createRow);
    }

    public void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 6) {
            a(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f), i2);
        }
        addConstraint(createRow);
    }

    public void addLowerBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, 0);
        if (z) {
            a(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f), 1);
        }
        addConstraint(createRow);
    }

    public void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 6) {
            a(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f), i2);
        }
        addConstraint(createRow);
    }

    public void addRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f, int i) {
        ArrayRow createRow = createRow();
        createRow.createRowDimensionRatio(solverVariable, solverVariable2, solverVariable3, solverVariable4, f);
        if (i != 6) {
            createRow.addError(this, i);
        }
        addConstraint(createRow);
    }

    public SolverVariable createErrorVariable(int i, String str) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.errors++;
        }
        if (this.c + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.ERROR, str);
        this.a++;
        this.c++;
        acquireSolverVariable.id = this.a;
        acquireSolverVariable.strength = i;
        this.e.c[this.a] = acquireSolverVariable;
        this.mGoal.addError(acquireSolverVariable);
        return acquireSolverVariable;
    }

    public SolverVariable createExtraVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.extravariables++;
        }
        if (this.c + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, (String) null);
        this.a++;
        this.c++;
        acquireSolverVariable.id = this.a;
        this.e.c[this.a] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    public SolverVariable createObjectVariable(Object obj) {
        SolverVariable solverVariable = null;
        if (obj == null) {
            return null;
        }
        if (this.c + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariable = constraintAnchor.getSolverVariable();
            if (solverVariable == null) {
                constraintAnchor.resetSolverVariable(this.e);
                solverVariable = constraintAnchor.getSolverVariable();
            }
            if (solverVariable.id == -1 || solverVariable.id > this.a || this.e.c[solverVariable.id] == null) {
                if (solverVariable.id != -1) {
                    solverVariable.reset();
                }
                this.a++;
                this.c++;
                solverVariable.id = this.a;
                solverVariable.c = SolverVariable.Type.UNRESTRICTED;
                this.e.c[this.a] = solverVariable;
            }
        }
        return solverVariable;
    }

    public ArrayRow createRow() {
        ArrayRow acquire = this.e.a.acquire();
        if (acquire == null) {
            acquire = new ArrayRow(this.e);
        } else {
            acquire.reset();
        }
        SolverVariable.a();
        return acquire;
    }

    public SolverVariable createSlackVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.slackvariables++;
        }
        if (this.c + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, (String) null);
        this.a++;
        this.c++;
        acquireSolverVariable.id = this.a;
        this.e.c[this.a] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    public void displayVariablesReadableRows() {
        displaySolverVariables();
        String str = "";
        for (int i = 0; i < this.d; i++) {
            if (this.b[i].a.c == SolverVariable.Type.UNRESTRICTED) {
                str = (str + this.b[i].b()) + "\n";
            }
        }
        System.out.println(str + this.mGoal + "\n");
    }

    public void fillMetrics(Metrics metrics) {
        sMetrics = metrics;
    }

    public Cache getCache() {
        return this.e;
    }

    public int getMemoryUsed() {
        int i = 0;
        for (int i2 = 0; i2 < this.d; i2++) {
            ArrayRow[] arrayRowArr = this.b;
            if (arrayRowArr[i2] != null) {
                i += arrayRowArr[i2].c();
            }
        }
        return i;
    }

    public int getNumEquations() {
        return this.d;
    }

    public int getNumVariables() {
        return this.a;
    }

    public int getObjectVariableValue(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).getSolverVariable();
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    public void minimize() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimize++;
        }
        if (this.graphOptimizer) {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.graphOptimizer++;
            }
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= this.d) {
                    z = true;
                    break;
                } else if (!this.b[i].d) {
                    break;
                } else {
                    i++;
                }
            }
            if (z) {
                Metrics metrics3 = sMetrics;
                if (metrics3 != null) {
                    metrics3.fullySolved++;
                }
                computeValues();
                return;
            }
        }
        a(this.mGoal);
    }

    public void reset() {
        for (SolverVariable solverVariable : this.e.c) {
            if (solverVariable != null) {
                solverVariable.reset();
            }
        }
        this.e.b.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.e.c, (Object) null);
        HashMap<String, SolverVariable> hashMap = this.mVariables;
        if (hashMap != null) {
            hashMap.clear();
        }
        this.a = 0;
        this.mGoal.clear();
        this.c = 1;
        for (int i = 0; i < this.d; i++) {
            this.b[i].c = false;
        }
        releaseRows();
        this.d = 0;
    }
}
