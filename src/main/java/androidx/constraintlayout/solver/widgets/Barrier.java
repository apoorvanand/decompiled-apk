package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

public class Barrier extends Helper {
    public static final int BOTTOM = 3;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    private boolean mAllowsGoneWidget = true;
    private int mBarrierType = 0;
    private ArrayList<ResolutionAnchor> mNodes = new ArrayList<>(4);

    public void addToSolver(LinearSystem linearSystem) {
        boolean z;
        SolverVariable solverVariable;
        ConstraintAnchor constraintAnchor;
        int i;
        int i2;
        this.x[0] = this.p;
        this.x[2] = this.q;
        this.x[1] = this.r;
        this.x[3] = this.s;
        for (int i3 = 0; i3 < this.x.length; i3++) {
            this.x[i3].e = linearSystem.createObjectVariable(this.x[i3]);
        }
        int i4 = this.mBarrierType;
        if (i4 >= 0 && i4 < 4) {
            ConstraintAnchor constraintAnchor2 = this.x[this.mBarrierType];
            int i5 = 0;
            while (true) {
                if (i5 >= this.aa) {
                    z = false;
                    break;
                }
                ConstraintWidget constraintWidget = this.Z[i5];
                if ((this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) && ((((i = this.mBarrierType) == 0 || i == 1) && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) || (((i2 = this.mBarrierType) == 2 || i2 == 3) && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT))) {
                    z = true;
                } else {
                    i5++;
                }
            }
            z = true;
            int i6 = this.mBarrierType;
            if (i6 == 0 || i6 == 1 ? getParent().getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT : getParent().getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                z = false;
            }
            for (int i7 = 0; i7 < this.aa; i7++) {
                ConstraintWidget constraintWidget2 = this.Z[i7];
                if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                    SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintWidget2.x[this.mBarrierType]);
                    ConstraintAnchor[] constraintAnchorArr = constraintWidget2.x;
                    int i8 = this.mBarrierType;
                    constraintAnchorArr[i8].e = createObjectVariable;
                    if (i8 == 0 || i8 == 2) {
                        linearSystem.addLowerBarrier(constraintAnchor2.e, createObjectVariable, z);
                    } else {
                        linearSystem.addGreaterBarrier(constraintAnchor2.e, createObjectVariable, z);
                    }
                }
            }
            int i9 = this.mBarrierType;
            if (i9 == 0) {
                linearSystem.addEquality(this.r.e, this.p.e, 0, 6);
                if (!z) {
                    solverVariable = this.p.e;
                    constraintAnchor = this.A.r;
                } else {
                    return;
                }
            } else if (i9 == 1) {
                linearSystem.addEquality(this.p.e, this.r.e, 0, 6);
                if (!z) {
                    solverVariable = this.p.e;
                    constraintAnchor = this.A.p;
                } else {
                    return;
                }
            } else if (i9 == 2) {
                linearSystem.addEquality(this.s.e, this.q.e, 0, 6);
                if (!z) {
                    solverVariable = this.q.e;
                    constraintAnchor = this.A.s;
                } else {
                    return;
                }
            } else if (i9 == 3) {
                linearSystem.addEquality(this.q.e, this.s.e, 0, 6);
                if (!z) {
                    solverVariable = this.q.e;
                    constraintAnchor = this.A.q;
                } else {
                    return;
                }
            } else {
                return;
            }
            linearSystem.addEquality(solverVariable, constraintAnchor.e, 0, 5);
        }
    }

    public boolean allowedInBarrier() {
        return true;
    }

    public void analyze(int i) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ResolutionAnchor resolutionAnchor;
        ConstraintAnchor constraintAnchor3;
        if (this.A != null && ((ConstraintWidgetContainer) this.A).optimizeFor(2)) {
            switch (this.mBarrierType) {
                case 0:
                    constraintAnchor = this.p;
                    break;
                case 1:
                    constraintAnchor = this.r;
                    break;
                case 2:
                    constraintAnchor = this.q;
                    break;
                case 3:
                    constraintAnchor = this.s;
                    break;
                default:
                    return;
            }
            ResolutionAnchor resolutionNode = constraintAnchor.getResolutionNode();
            resolutionNode.setType(5);
            int i2 = this.mBarrierType;
            if (i2 == 0 || i2 == 1) {
                this.q.getResolutionNode().resolve((ResolutionAnchor) null, 0.0f);
                constraintAnchor2 = this.s;
            } else {
                this.p.getResolutionNode().resolve((ResolutionAnchor) null, 0.0f);
                constraintAnchor2 = this.r;
            }
            constraintAnchor2.getResolutionNode().resolve((ResolutionAnchor) null, 0.0f);
            this.mNodes.clear();
            for (int i3 = 0; i3 < this.aa; i3++) {
                ConstraintWidget constraintWidget = this.Z[i3];
                if (this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) {
                    switch (this.mBarrierType) {
                        case 0:
                            constraintAnchor3 = constraintWidget.p;
                            break;
                        case 1:
                            constraintAnchor3 = constraintWidget.r;
                            break;
                        case 2:
                            constraintAnchor3 = constraintWidget.q;
                            break;
                        case 3:
                            constraintAnchor3 = constraintWidget.s;
                            break;
                        default:
                            resolutionAnchor = null;
                            break;
                    }
                    resolutionAnchor = constraintAnchor3.getResolutionNode();
                    if (resolutionAnchor != null) {
                        this.mNodes.add(resolutionAnchor);
                        resolutionAnchor.addDependent(resolutionNode);
                    }
                }
            }
        }
    }

    public void resetResolutionNodes() {
        super.resetResolutionNodes();
        this.mNodes.clear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r4 >= r2) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
        r5 = r8.mNodes.get(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        if (r5.i == 1) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        r6 = r8.mBarrierType;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
        if (r6 == 0) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003b, code lost:
        if (r6 != 2) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0042, code lost:
        if (r5.f <= r1) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        if (r5.f >= r1) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
        r1 = r5.f;
        r3 = r5.e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004f, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0056, code lost:
        if (androidx.constraintlayout.solver.LinearSystem.getMetrics() == null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0058, code lost:
        androidx.constraintlayout.solver.LinearSystem.getMetrics().barrierConnectionResolved++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0063, code lost:
        r0.e = r3;
        r0.f = r1;
        r0.didResolve();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006c, code lost:
        switch(r8.mBarrierType) {
            case 0: goto L_0x0079;
            case 1: goto L_0x0076;
            case 2: goto L_0x0073;
            case 3: goto L_0x0070;
            default: goto L_0x006f;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0070, code lost:
        r0 = r8.q;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0073, code lost:
        r0 = r8.s;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0076, code lost:
        r0 = r8.p;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0079, code lost:
        r0 = r8.r;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x007b, code lost:
        r0.getResolutionNode().resolve(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0082, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        r0 = r0.getResolutionNode();
        r1 = 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r0 = r0.getResolutionNode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        r2 = r8.mNodes.size();
        r3 = null;
        r4 = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void resolve() {
        /*
            r8 = this;
            int r0 = r8.mBarrierType
            r1 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r2 = 0
            switch(r0) {
                case 0: goto L_0x0018;
                case 1: goto L_0x0010;
                case 2: goto L_0x000d;
                case 3: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            return
        L_0x000a:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r8.s
            goto L_0x0012
        L_0x000d:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r8.q
            goto L_0x001a
        L_0x0010:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r8.r
        L_0x0012:
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r0 = r0.getResolutionNode()
            r1 = 0
            goto L_0x001e
        L_0x0018:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r8.p
        L_0x001a:
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r0 = r0.getResolutionNode()
        L_0x001e:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ResolutionAnchor> r2 = r8.mNodes
            int r2 = r2.size()
            r3 = 0
            r4 = 0
        L_0x0026:
            if (r4 >= r2) goto L_0x0052
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ResolutionAnchor> r5 = r8.mNodes
            java.lang.Object r5 = r5.get(r4)
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r5 = (androidx.constraintlayout.solver.widgets.ResolutionAnchor) r5
            int r6 = r5.i
            r7 = 1
            if (r6 == r7) goto L_0x0036
            return
        L_0x0036:
            int r6 = r8.mBarrierType
            if (r6 == 0) goto L_0x0045
            r7 = 2
            if (r6 != r7) goto L_0x003e
            goto L_0x0045
        L_0x003e:
            float r6 = r5.f
            int r6 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r6 <= 0) goto L_0x004f
            goto L_0x004b
        L_0x0045:
            float r6 = r5.f
            int r6 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r6 >= 0) goto L_0x004f
        L_0x004b:
            float r1 = r5.f
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r3 = r5.e
        L_0x004f:
            int r4 = r4 + 1
            goto L_0x0026
        L_0x0052:
            androidx.constraintlayout.solver.Metrics r2 = androidx.constraintlayout.solver.LinearSystem.getMetrics()
            if (r2 == 0) goto L_0x0063
            androidx.constraintlayout.solver.Metrics r2 = androidx.constraintlayout.solver.LinearSystem.getMetrics()
            long r4 = r2.barrierConnectionResolved
            r6 = 1
            long r4 = r4 + r6
            r2.barrierConnectionResolved = r4
        L_0x0063:
            r0.e = r3
            r0.f = r1
            r0.didResolve()
            int r0 = r8.mBarrierType
            switch(r0) {
                case 0: goto L_0x0079;
                case 1: goto L_0x0076;
                case 2: goto L_0x0073;
                case 3: goto L_0x0070;
                default: goto L_0x006f;
            }
        L_0x006f:
            return
        L_0x0070:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r8.q
            goto L_0x007b
        L_0x0073:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r8.s
            goto L_0x007b
        L_0x0076:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r8.p
            goto L_0x007b
        L_0x0079:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r8.r
        L_0x007b:
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r0 = r0.getResolutionNode()
            r0.resolve(r3, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Barrier.resolve():void");
    }

    public void setAllowsGoneWidget(boolean z) {
        this.mAllowsGoneWidget = z;
    }

    public void setBarrierType(int i) {
        this.mBarrierType = i;
    }
}
