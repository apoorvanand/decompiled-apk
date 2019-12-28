package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ConstraintWidgetContainer extends WidgetContainer {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final int MAX_ITERATIONS = 8;
    private static final boolean USE_SNAPSHOT = true;
    protected LinearSystem Z = new LinearSystem();
    int aa;
    int ab;
    int ac;
    int ad;
    int ae = 0;
    int af = 0;
    ChainHead[] ag = new ChainHead[4];
    ChainHead[] ah = new ChainHead[4];
    int ai = 0;
    private boolean mHeightMeasuredTooSmall = false;
    private boolean mIsRtl = false;
    private int mOptimizationLevel = 3;
    private Snapshot mSnapshot;
    private boolean mWidthMeasuredTooSmall = false;

    public ConstraintWidgetContainer() {
    }

    public ConstraintWidgetContainer(int i, int i2) {
        super(i, i2);
    }

    public ConstraintWidgetContainer(int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        int i = this.ae + 1;
        ChainHead[] chainHeadArr = this.ah;
        if (i >= chainHeadArr.length) {
            this.ah = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.ah[this.ae] = new ChainHead(constraintWidget, 0, isRtl());
        this.ae++;
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        int i = this.af + 1;
        ChainHead[] chainHeadArr = this.ag;
        if (i >= chainHeadArr.length) {
            this.ag = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.ag[this.af] = new ChainHead(constraintWidget, 1, isRtl());
        this.af++;
    }

    private void resetChains() {
        this.ae = 0;
        this.af = 0;
    }

    /* access modifiers changed from: package-private */
    public void a(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            addHorizontalChain(constraintWidget);
        } else if (i == 1) {
            addVerticalChain(constraintWidget);
        }
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem) {
        addToSolver(linearSystem);
        int size = this.aj.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.aj.get(i);
            if (constraintWidget instanceof ConstraintWidgetContainer) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.z[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget.z[1];
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                constraintWidget.addToSolver(linearSystem);
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            } else {
                Optimizer.a(this, linearSystem, constraintWidget);
                constraintWidget.addToSolver(linearSystem);
            }
        }
        if (this.ae > 0) {
            Chain.a(this, linearSystem, 0);
        }
        if (this.af > 0) {
            Chain.a(this, linearSystem, 1);
        }
        return true;
    }

    public void analyze(int i) {
        super.analyze(i);
        int size = this.aj.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ConstraintWidget) this.aj.get(i2)).analyze(i);
        }
    }

    public void fillMetrics(Metrics metrics) {
        this.Z.fillMetrics(metrics);
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.aj.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.aj.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 0) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public int getOptimizationLevel() {
        return this.mOptimizationLevel;
    }

    public LinearSystem getSystem() {
        return this.Z;
    }

    public String getType() {
        return "ConstraintLayout";
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.aj.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.aj.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public boolean isRtl() {
        return this.mIsRtl;
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    /* JADX WARNING: type inference failed for: r12v12, types: [boolean] */
    /* JADX WARNING: type inference failed for: r12v16 */
    /* JADX WARNING: type inference failed for: r12v17 */
    public void layout() {
        boolean z;
        boolean z2;
        ? r12;
        int i = this.F;
        int i2 = this.G;
        char c = 0;
        int max = Math.max(0, getWidth());
        int max2 = Math.max(0, getHeight());
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        if (this.A != null) {
            if (this.mSnapshot == null) {
                this.mSnapshot = new Snapshot(this);
            }
            this.mSnapshot.updateFrom(this);
            setX(this.aa);
            setY(this.ab);
            resetAnchors();
            resetSolverVariables(this.Z.getCache());
        } else {
            this.F = 0;
            this.G = 0;
        }
        if (this.mOptimizationLevel != 0) {
            if (!optimizeFor(8)) {
                optimizeReset();
            }
            optimize();
            this.Z.graphOptimizer = true;
        } else {
            this.Z.graphOptimizer = false;
        }
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.z[1];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.z[0];
        resetChains();
        int size = this.aj.size();
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.aj.get(i3);
            if (constraintWidget instanceof WidgetContainer) {
                ((WidgetContainer) constraintWidget).layout();
            }
        }
        int i4 = 0;
        boolean z3 = true;
        boolean z4 = false;
        while (z3) {
            int i5 = i4 + 1;
            try {
                this.Z.reset();
                createObjectVariables(this.Z);
                for (int i6 = 0; i6 < size; i6++) {
                    ((ConstraintWidget) this.aj.get(i6)).createObjectVariables(this.Z);
                }
                z3 = addChildrenToSolver(this.Z);
                if (z3) {
                    this.Z.minimize();
                }
            } catch (Exception e) {
                e.printStackTrace();
                PrintStream printStream = System.out;
                printStream.println("EXCEPTION : " + e);
            }
            if (!z3) {
                updateFromSolver(this.Z);
                int i7 = 0;
                while (true) {
                    if (i7 >= size) {
                        break;
                    }
                    ConstraintWidget constraintWidget2 = (ConstraintWidget) this.aj.get(i7);
                    if (constraintWidget2.z[c] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget2.getWidth() >= constraintWidget2.getWrapWidth()) {
                        if (constraintWidget2.z[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget2.getHeight() < constraintWidget2.getWrapHeight()) {
                            Optimizer.a[2] = true;
                            break;
                        } else {
                            i7++;
                            c = 0;
                        }
                    } else {
                        Optimizer.a[2] = true;
                        break;
                    }
                }
            } else {
                updateChildrenFromSolver(this.Z, Optimizer.a);
            }
            if (i5 >= 8 || !Optimizer.a[2]) {
                z = z4;
                z2 = false;
            } else {
                int i8 = 0;
                int i9 = 0;
                for (int i10 = 0; i10 < size; i10++) {
                    ConstraintWidget constraintWidget3 = (ConstraintWidget) this.aj.get(i10);
                    i8 = Math.max(i8, constraintWidget3.F + constraintWidget3.getWidth());
                    i9 = Math.max(i9, constraintWidget3.G + constraintWidget3.getHeight());
                }
                int max3 = Math.max(this.K, i8);
                int max4 = Math.max(this.L, i9);
                if (dimensionBehaviour2 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || getWidth() >= max3) {
                    z2 = false;
                } else {
                    setWidth(max3);
                    this.z[0] = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    z2 = true;
                    z4 = true;
                }
                if (dimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || getHeight() >= max4) {
                    z = z4;
                } else {
                    setHeight(max4);
                    this.z[1] = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    z2 = true;
                    z = true;
                }
            }
            int max5 = Math.max(this.K, getWidth());
            if (max5 > getWidth()) {
                setWidth(max5);
                this.z[0] = ConstraintWidget.DimensionBehaviour.FIXED;
                z2 = true;
                z = true;
            }
            int max6 = Math.max(this.L, getHeight());
            if (max6 > getHeight()) {
                setHeight(max6);
                r12 = 1;
                this.z[1] = ConstraintWidget.DimensionBehaviour.FIXED;
                z2 = true;
                z = true;
            } else {
                r12 = 1;
            }
            if (!z) {
                if (this.z[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && max > 0 && getWidth() > max) {
                    this.mWidthMeasuredTooSmall = r12;
                    this.z[0] = ConstraintWidget.DimensionBehaviour.FIXED;
                    setWidth(max);
                    z2 = true;
                    z = true;
                }
                if (this.z[r12] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && max2 > 0 && getHeight() > max2) {
                    this.mHeightMeasuredTooSmall = r12;
                    this.z[r12] = ConstraintWidget.DimensionBehaviour.FIXED;
                    setHeight(max2);
                    z3 = true;
                    z4 = true;
                    i4 = i5;
                    c = 0;
                }
            }
            z3 = z2;
            z4 = z;
            i4 = i5;
            c = 0;
        }
        if (this.A != null) {
            int max7 = Math.max(this.K, getWidth());
            int max8 = Math.max(this.L, getHeight());
            this.mSnapshot.applyTo(this);
            setWidth(max7 + this.aa + this.ac);
            setHeight(max8 + this.ab + this.ad);
        } else {
            this.F = i;
            this.G = i2;
        }
        if (z4) {
            this.z[0] = dimensionBehaviour2;
            this.z[1] = dimensionBehaviour;
        }
        resetSolverVariables(this.Z.getCache());
        if (this == getRootConstraintContainer()) {
            updateDrawPosition();
        }
    }

    public void optimize() {
        if (!optimizeFor(8)) {
            analyze(this.mOptimizationLevel);
        }
        solveGraph();
    }

    public boolean optimizeFor(int i) {
        return (this.mOptimizationLevel & i) == i;
    }

    public void optimizeForDimensions(int i, int i2) {
        if (!(this.z[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || this.a == null)) {
            this.a.resolve(i);
        }
        if (this.z[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && this.b != null) {
            this.b.resolve(i2);
        }
    }

    public void optimizeReset() {
        int size = this.aj.size();
        resetResolutionNodes();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) this.aj.get(i)).resetResolutionNodes();
        }
    }

    public void preOptimize() {
        optimizeReset();
        analyze(this.mOptimizationLevel);
    }

    public void reset() {
        this.Z.reset();
        this.aa = 0;
        this.ac = 0;
        this.ab = 0;
        this.ad = 0;
        super.reset();
    }

    public void resetGraph() {
        ResolutionAnchor resolutionNode = getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
        ResolutionAnchor resolutionNode2 = getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
        resolutionNode.invalidateAnchors();
        resolutionNode2.invalidateAnchors();
        resolutionNode.resolve((ResolutionAnchor) null, 0.0f);
        resolutionNode2.resolve((ResolutionAnchor) null, 0.0f);
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.aa = i;
        this.ab = i2;
        this.ac = i3;
        this.ad = i4;
    }

    public void setRtl(boolean z) {
        this.mIsRtl = z;
    }

    public void solveGraph() {
        ResolutionAnchor resolutionNode = getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
        ResolutionAnchor resolutionNode2 = getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
        resolutionNode.resolve((ResolutionAnchor) null, 0.0f);
        resolutionNode2.resolve((ResolutionAnchor) null, 0.0f);
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, boolean[] zArr) {
        zArr[2] = false;
        updateFromSolver(linearSystem);
        int size = this.aj.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.aj.get(i);
            constraintWidget.updateFromSolver(linearSystem);
            if (constraintWidget.z[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getWidth() < constraintWidget.getWrapWidth()) {
                zArr[2] = true;
            }
            if (constraintWidget.z[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight()) {
                zArr[2] = true;
            }
        }
    }
}
