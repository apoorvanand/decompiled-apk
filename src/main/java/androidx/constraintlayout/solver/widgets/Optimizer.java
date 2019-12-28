package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

public class Optimizer {
    public static final int OPTIMIZATION_BARRIER = 2;
    public static final int OPTIMIZATION_CHAIN = 4;
    public static final int OPTIMIZATION_DIMENSIONS = 8;
    public static final int OPTIMIZATION_DIRECT = 1;
    public static final int OPTIMIZATION_NONE = 0;
    public static final int OPTIMIZATION_RATIO = 16;
    public static final int OPTIMIZATION_STANDARD = 3;
    static boolean[] a = new boolean[3];

    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01e0, code lost:
        if (r6 != false) goto L_0x01c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0070, code lost:
        if (r6 != false) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0080, code lost:
        if (r6 != false) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008f, code lost:
        if (r6 != false) goto L_0x012e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e9, code lost:
        if (r6 != false) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x010f, code lost:
        if (r6 != false) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01c4, code lost:
        if (r6 != false) goto L_0x01c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01cf, code lost:
        r4.dependsOn(r2, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(int r16, androidx.constraintlayout.solver.widgets.ConstraintWidget r17) {
        /*
            r0 = r17
            r17.updateResolutionNodes()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.p
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r1 = r1.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r0.q
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r2 = r2.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.r
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r3 = r3.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r0.s
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r4 = r4.getResolutionNode()
            r5 = 8
            r6 = r16 & 8
            r7 = 0
            r8 = 1
            if (r6 != r5) goto L_0x0027
            r6 = 1
            goto L_0x0028
        L_0x0027:
            r6 = 0
        L_0x0028:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r9 = r0.z
            r9 = r9[r7]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r9 != r10) goto L_0x0038
            boolean r9 = optimizableMatchConstraint(r0, r7)
            if (r9 == 0) goto L_0x0038
            r9 = 1
            goto L_0x0039
        L_0x0038:
            r9 = 0
        L_0x0039:
            int r10 = r1.g
            r11 = 3
            r12 = 4
            r13 = 0
            r14 = -1
            r15 = 2
            if (r10 == r12) goto L_0x0181
            int r10 = r3.g
            if (r10 == r12) goto L_0x0181
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r0.z
            r10 = r10[r7]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r10 == r7) goto L_0x00d7
            if (r9 == 0) goto L_0x0058
            int r7 = r17.getVisibility()
            if (r7 != r5) goto L_0x0058
            goto L_0x00d7
        L_0x0058:
            if (r9 == 0) goto L_0x0181
            int r7 = r17.getWidth()
            r1.setType(r8)
            r3.setType(r8)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r0.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            if (r9 != 0) goto L_0x0074
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r0.r
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            if (r9 != 0) goto L_0x0074
            if (r6 == 0) goto L_0x00f8
            goto L_0x00eb
        L_0x0074:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r0.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            if (r9 == 0) goto L_0x0083
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r0.r
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            if (r9 != 0) goto L_0x0083
            if (r6 == 0) goto L_0x00f8
            goto L_0x00eb
        L_0x0083:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r0.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            if (r9 != 0) goto L_0x0093
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r0.r
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            if (r9 == 0) goto L_0x0093
            if (r6 == 0) goto L_0x013a
            goto L_0x012e
        L_0x0093:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r0.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            if (r9 == 0) goto L_0x0181
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r0.r
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            if (r9 == 0) goto L_0x0181
            if (r6 == 0) goto L_0x00af
            androidx.constraintlayout.solver.widgets.ResolutionDimension r9 = r17.getResolutionWidth()
            r9.addDependent(r1)
            androidx.constraintlayout.solver.widgets.ResolutionDimension r9 = r17.getResolutionWidth()
            r9.addDependent(r3)
        L_0x00af:
            float r9 = r0.D
            int r9 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r9 != 0) goto L_0x00c3
            r1.setType(r11)
            r3.setType(r11)
            r1.setOpposite(r3, r13)
            r3.setOpposite(r1, r13)
            goto L_0x0181
        L_0x00c3:
            r1.setType(r15)
            r3.setType(r15)
            int r9 = -r7
            float r9 = (float) r9
            r1.setOpposite(r3, r9)
            float r9 = (float) r7
            r3.setOpposite(r1, r9)
            r0.setWidth(r7)
            goto L_0x0181
        L_0x00d7:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r0.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            if (r7 != 0) goto L_0x00fd
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r0.r
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            if (r7 != 0) goto L_0x00fd
            r1.setType(r8)
            r3.setType(r8)
            if (r6 == 0) goto L_0x00f4
        L_0x00eb:
            androidx.constraintlayout.solver.widgets.ResolutionDimension r7 = r17.getResolutionWidth()
            r3.dependsOn((androidx.constraintlayout.solver.widgets.ResolutionAnchor) r1, (int) r8, (androidx.constraintlayout.solver.widgets.ResolutionDimension) r7)
            goto L_0x0181
        L_0x00f4:
            int r7 = r17.getWidth()
        L_0x00f8:
            r3.dependsOn(r1, r7)
            goto L_0x0181
        L_0x00fd:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r0.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            if (r7 == 0) goto L_0x0112
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r0.r
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            if (r7 != 0) goto L_0x0112
            r1.setType(r8)
            r3.setType(r8)
            if (r6 == 0) goto L_0x00f4
            goto L_0x00eb
        L_0x0112:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r0.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            if (r7 != 0) goto L_0x013f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r0.r
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            if (r7 == 0) goto L_0x013f
            r1.setType(r8)
            r3.setType(r8)
            int r7 = r17.getWidth()
            int r7 = -r7
            r1.dependsOn(r3, r7)
            if (r6 == 0) goto L_0x0136
        L_0x012e:
            androidx.constraintlayout.solver.widgets.ResolutionDimension r7 = r17.getResolutionWidth()
            r1.dependsOn((androidx.constraintlayout.solver.widgets.ResolutionAnchor) r3, (int) r14, (androidx.constraintlayout.solver.widgets.ResolutionDimension) r7)
            goto L_0x0181
        L_0x0136:
            int r7 = r17.getWidth()
        L_0x013a:
            int r7 = -r7
            r1.dependsOn(r3, r7)
            goto L_0x0181
        L_0x013f:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r0.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            if (r7 == 0) goto L_0x0181
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r0.r
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            if (r7 == 0) goto L_0x0181
            r1.setType(r15)
            r3.setType(r15)
            if (r6 == 0) goto L_0x0170
            androidx.constraintlayout.solver.widgets.ResolutionDimension r7 = r17.getResolutionWidth()
            r7.addDependent(r1)
            androidx.constraintlayout.solver.widgets.ResolutionDimension r7 = r17.getResolutionWidth()
            r7.addDependent(r3)
            androidx.constraintlayout.solver.widgets.ResolutionDimension r7 = r17.getResolutionWidth()
            r1.setOpposite(r3, r14, r7)
            androidx.constraintlayout.solver.widgets.ResolutionDimension r7 = r17.getResolutionWidth()
            r3.setOpposite(r1, r8, r7)
            goto L_0x0181
        L_0x0170:
            int r7 = r17.getWidth()
            int r7 = -r7
            float r7 = (float) r7
            r1.setOpposite(r3, r7)
            int r7 = r17.getWidth()
            float r7 = (float) r7
            r3.setOpposite(r1, r7)
        L_0x0181:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r0.z
            r1 = r1[r8]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r3) goto L_0x0191
            boolean r1 = optimizableMatchConstraint(r0, r8)
            if (r1 == 0) goto L_0x0191
            r1 = 1
            goto L_0x0192
        L_0x0191:
            r1 = 0
        L_0x0192:
            int r3 = r2.g
            if (r3 == r12) goto L_0x032b
            int r3 = r4.g
            if (r3 == r12) goto L_0x032b
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r0.z
            r3 = r3[r8]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r3 == r7) goto L_0x0247
            if (r1 == 0) goto L_0x01ac
            int r3 = r17.getVisibility()
            if (r3 != r5) goto L_0x01ac
            goto L_0x0247
        L_0x01ac:
            if (r1 == 0) goto L_0x032b
            int r1 = r17.getHeight()
            r2.setType(r8)
            r4.setType(r8)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            if (r3 != 0) goto L_0x01d4
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.s
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            if (r3 != 0) goto L_0x01d4
            if (r6 == 0) goto L_0x01cf
        L_0x01c6:
            androidx.constraintlayout.solver.widgets.ResolutionDimension r0 = r17.getResolutionHeight()
            r4.dependsOn((androidx.constraintlayout.solver.widgets.ResolutionAnchor) r2, (int) r8, (androidx.constraintlayout.solver.widgets.ResolutionDimension) r0)
            goto L_0x032b
        L_0x01cf:
            r4.dependsOn(r2, r1)
            goto L_0x032b
        L_0x01d4:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            if (r3 == 0) goto L_0x01e3
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.s
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            if (r3 != 0) goto L_0x01e3
            if (r6 == 0) goto L_0x01cf
            goto L_0x01c6
        L_0x01e3:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            if (r3 != 0) goto L_0x0200
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.s
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            if (r3 == 0) goto L_0x0200
            if (r6 == 0) goto L_0x01fa
            androidx.constraintlayout.solver.widgets.ResolutionDimension r0 = r17.getResolutionHeight()
            r2.dependsOn((androidx.constraintlayout.solver.widgets.ResolutionAnchor) r4, (int) r14, (androidx.constraintlayout.solver.widgets.ResolutionDimension) r0)
            goto L_0x032b
        L_0x01fa:
            int r0 = -r1
            r2.dependsOn(r4, r0)
            goto L_0x032b
        L_0x0200:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            if (r3 == 0) goto L_0x032b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.s
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            if (r3 == 0) goto L_0x032b
            if (r6 == 0) goto L_0x021c
            androidx.constraintlayout.solver.widgets.ResolutionDimension r3 = r17.getResolutionHeight()
            r3.addDependent(r2)
            androidx.constraintlayout.solver.widgets.ResolutionDimension r3 = r17.getResolutionWidth()
            r3.addDependent(r4)
        L_0x021c:
            float r3 = r0.D
            int r3 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r3 != 0) goto L_0x0230
            r2.setType(r11)
            r4.setType(r11)
            r2.setOpposite(r4, r13)
            r4.setOpposite(r2, r13)
            goto L_0x032b
        L_0x0230:
            r2.setType(r15)
            r4.setType(r15)
            int r3 = -r1
            float r3 = (float) r3
            r2.setOpposite(r4, r3)
            float r3 = (float) r1
            r4.setOpposite(r2, r3)
            r0.setHeight(r1)
            int r1 = r0.J
            if (r1 <= 0) goto L_0x032b
            goto L_0x02ae
        L_0x0247:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.c
            if (r1 != 0) goto L_0x0287
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.s
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.c
            if (r1 != 0) goto L_0x0287
            r2.setType(r8)
            r4.setType(r8)
            if (r6 == 0) goto L_0x0263
            androidx.constraintlayout.solver.widgets.ResolutionDimension r1 = r17.getResolutionHeight()
            r4.dependsOn((androidx.constraintlayout.solver.widgets.ResolutionAnchor) r2, (int) r8, (androidx.constraintlayout.solver.widgets.ResolutionDimension) r1)
            goto L_0x026a
        L_0x0263:
            int r1 = r17.getHeight()
            r4.dependsOn(r2, r1)
        L_0x026a:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.t
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.c
            if (r1 == 0) goto L_0x032b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.t
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r1 = r1.getResolutionNode()
            r1.setType(r8)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.t
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r1 = r1.getResolutionNode()
            int r0 = r0.J
            int r0 = -r0
            r2.dependsOn((int) r8, (androidx.constraintlayout.solver.widgets.ResolutionAnchor) r1, (int) r0)
            goto L_0x032b
        L_0x0287:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.c
            if (r1 == 0) goto L_0x02bb
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.s
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.c
            if (r1 != 0) goto L_0x02bb
            r2.setType(r8)
            r4.setType(r8)
            if (r6 == 0) goto L_0x02a3
            androidx.constraintlayout.solver.widgets.ResolutionDimension r1 = r17.getResolutionHeight()
            r4.dependsOn((androidx.constraintlayout.solver.widgets.ResolutionAnchor) r2, (int) r8, (androidx.constraintlayout.solver.widgets.ResolutionDimension) r1)
            goto L_0x02aa
        L_0x02a3:
            int r1 = r17.getHeight()
            r4.dependsOn(r2, r1)
        L_0x02aa:
            int r1 = r0.J
            if (r1 <= 0) goto L_0x032b
        L_0x02ae:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.t
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r1 = r1.getResolutionNode()
            int r0 = r0.J
            r1.dependsOn((int) r8, (androidx.constraintlayout.solver.widgets.ResolutionAnchor) r2, (int) r0)
            goto L_0x032b
        L_0x02bb:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.c
            if (r1 != 0) goto L_0x02e4
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.s
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.c
            if (r1 == 0) goto L_0x02e4
            r2.setType(r8)
            r4.setType(r8)
            if (r6 == 0) goto L_0x02d7
            androidx.constraintlayout.solver.widgets.ResolutionDimension r1 = r17.getResolutionHeight()
            r2.dependsOn((androidx.constraintlayout.solver.widgets.ResolutionAnchor) r4, (int) r14, (androidx.constraintlayout.solver.widgets.ResolutionDimension) r1)
            goto L_0x02df
        L_0x02d7:
            int r1 = r17.getHeight()
            int r1 = -r1
            r2.dependsOn(r4, r1)
        L_0x02df:
            int r1 = r0.J
            if (r1 <= 0) goto L_0x032b
            goto L_0x02ae
        L_0x02e4:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.c
            if (r1 == 0) goto L_0x032b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.s
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.c
            if (r1 == 0) goto L_0x032b
            r2.setType(r15)
            r4.setType(r15)
            if (r6 == 0) goto L_0x0315
            androidx.constraintlayout.solver.widgets.ResolutionDimension r1 = r17.getResolutionHeight()
            r2.setOpposite(r4, r14, r1)
            androidx.constraintlayout.solver.widgets.ResolutionDimension r1 = r17.getResolutionHeight()
            r4.setOpposite(r2, r8, r1)
            androidx.constraintlayout.solver.widgets.ResolutionDimension r1 = r17.getResolutionHeight()
            r1.addDependent(r2)
            androidx.constraintlayout.solver.widgets.ResolutionDimension r1 = r17.getResolutionWidth()
            r1.addDependent(r4)
            goto L_0x0326
        L_0x0315:
            int r1 = r17.getHeight()
            int r1 = -r1
            float r1 = (float) r1
            r2.setOpposite(r4, r1)
            int r1 = r17.getHeight()
            float r1 = (float) r1
            r4.setOpposite(r2, r1)
        L_0x0326:
            int r1 = r0.J
            if (r1 <= 0) goto L_0x032b
            goto L_0x02ae
        L_0x032b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Optimizer.a(int, androidx.constraintlayout.solver.widgets.ConstraintWidget):void");
    }

    static void a(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ConstraintWidget constraintWidget) {
        if (constraintWidgetContainer.z[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && constraintWidget.z[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i = constraintWidget.p.mMargin;
            int width = constraintWidgetContainer.getWidth() - constraintWidget.r.mMargin;
            constraintWidget.p.e = linearSystem.createObjectVariable(constraintWidget.p);
            constraintWidget.r.e = linearSystem.createObjectVariable(constraintWidget.r);
            linearSystem.addEquality(constraintWidget.p.e, i);
            linearSystem.addEquality(constraintWidget.r.e, width);
            constraintWidget.mHorizontalResolution = 2;
            constraintWidget.setHorizontalDimension(i, width);
        }
        if (constraintWidgetContainer.z[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && constraintWidget.z[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i2 = constraintWidget.q.mMargin;
            int height = constraintWidgetContainer.getHeight() - constraintWidget.s.mMargin;
            constraintWidget.q.e = linearSystem.createObjectVariable(constraintWidget.q);
            constraintWidget.s.e = linearSystem.createObjectVariable(constraintWidget.s);
            linearSystem.addEquality(constraintWidget.q.e, i2);
            linearSystem.addEquality(constraintWidget.s.e, height);
            if (constraintWidget.J > 0 || constraintWidget.getVisibility() == 8) {
                constraintWidget.t.e = linearSystem.createObjectVariable(constraintWidget.t);
                linearSystem.addEquality(constraintWidget.t.e, constraintWidget.J + i2);
            }
            constraintWidget.mVerticalResolution = 2;
            constraintWidget.setVerticalDimension(i2, height);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0030, code lost:
        if (r6.Q == 2) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0046, code lost:
        if (r6.R == 2) goto L_0x0032;
     */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean a(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r20, androidx.constraintlayout.solver.LinearSystem r21, int r22, int r23, androidx.constraintlayout.solver.widgets.ChainHead r24) {
        /*
            r0 = r21
            r1 = r24
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r1.a
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r1.c
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r1.b
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r1.d
            androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r1.e
            float r7 = r1.k
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = r1.f
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r1.g
            r8 = r20
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r8.z
            r1 = r1[r22]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r1 = 2
            r9 = 1
            if (r22 != 0) goto L_0x0036
            int r10 = r6.Q
            if (r10 != 0) goto L_0x0026
            r10 = 1
            goto L_0x0027
        L_0x0026:
            r10 = 0
        L_0x0027:
            int r11 = r6.Q
            if (r11 != r9) goto L_0x002d
            r11 = 1
            goto L_0x002e
        L_0x002d:
            r11 = 0
        L_0x002e:
            int r6 = r6.Q
            if (r6 != r1) goto L_0x0034
        L_0x0032:
            r1 = 1
            goto L_0x0049
        L_0x0034:
            r1 = 0
            goto L_0x0049
        L_0x0036:
            int r10 = r6.R
            if (r10 != 0) goto L_0x003c
            r10 = 1
            goto L_0x003d
        L_0x003c:
            r10 = 0
        L_0x003d:
            int r11 = r6.R
            if (r11 != r9) goto L_0x0043
            r11 = 1
            goto L_0x0044
        L_0x0043:
            r11 = 0
        L_0x0044:
            int r6 = r6.R
            if (r6 != r1) goto L_0x0034
            goto L_0x0032
        L_0x0049:
            r13 = r2
            r6 = 0
            r12 = 0
            r14 = 0
            r15 = 0
            r16 = 0
        L_0x0050:
            if (r12 != 0) goto L_0x00ef
            int r9 = r13.getVisibility()
            r8 = 8
            if (r9 == r8) goto L_0x008d
            int r14 = r14 + 1
            if (r22 != 0) goto L_0x0063
            int r9 = r13.getWidth()
            goto L_0x0067
        L_0x0063:
            int r9 = r13.getHeight()
        L_0x0067:
            float r9 = (float) r9
            float r15 = r15 + r9
            if (r13 == r4) goto L_0x0075
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r9 = r13.x
            r9 = r9[r23]
            int r9 = r9.getMargin()
            float r9 = (float) r9
            float r15 = r15 + r9
        L_0x0075:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r9 = r13.x
            r9 = r9[r23]
            int r9 = r9.getMargin()
            float r9 = (float) r9
            float r16 = r16 + r9
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r9 = r13.x
            int r17 = r23 + 1
            r9 = r9[r17]
            int r9 = r9.getMargin()
            float r9 = (float) r9
            float r16 = r16 + r9
        L_0x008d:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r9 = r13.x
            r9 = r9[r23]
            int r9 = r13.getVisibility()
            if (r9 == r8) goto L_0x00c2
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r8 = r13.z
            r8 = r8[r22]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 != r9) goto L_0x00c2
            int r6 = r6 + 1
            if (r22 != 0) goto L_0x00b3
            int r8 = r13.c
            if (r8 == 0) goto L_0x00a9
            r8 = 0
            return r8
        L_0x00a9:
            r8 = 0
            int r9 = r13.f
            if (r9 != 0) goto L_0x00b2
            int r9 = r13.g
            if (r9 == 0) goto L_0x00c2
        L_0x00b2:
            return r8
        L_0x00b3:
            r8 = 0
            int r9 = r13.d
            if (r9 == 0) goto L_0x00b9
            return r8
        L_0x00b9:
            int r9 = r13.i
            if (r9 != 0) goto L_0x00c1
            int r9 = r13.j
            if (r9 == 0) goto L_0x00c2
        L_0x00c1:
            return r8
        L_0x00c2:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r13.x
            int r9 = r23 + 1
            r8 = r8[r9]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.c
            if (r8 == 0) goto L_0x00e4
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = r8.a
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r9 = r8.x
            r9 = r9[r23]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            if (r9 == 0) goto L_0x00e4
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r9 = r8.x
            r9 = r9[r23]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r9.a
            if (r9 == r13) goto L_0x00e1
            goto L_0x00e4
        L_0x00e1:
            r17 = r8
            goto L_0x00e6
        L_0x00e4:
            r17 = 0
        L_0x00e6:
            if (r17 == 0) goto L_0x00eb
            r13 = r17
            goto L_0x00ec
        L_0x00eb:
            r12 = 1
        L_0x00ec:
            r9 = 1
            goto L_0x0050
        L_0x00ef:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r2.x
            r8 = r8[r23]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r8 = r8.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r3.x
            int r9 = r23 + 1
            r3 = r3[r9]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r3 = r3.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r12 = r8.c
            if (r12 == 0) goto L_0x0347
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r12 = r3.c
            if (r12 != 0) goto L_0x010b
            goto L_0x0347
        L_0x010b:
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r12 = r8.c
            int r12 = r12.i
            r17 = r2
            r2 = 1
            if (r12 == r2) goto L_0x011c
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r12 = r3.c
            int r12 = r12.i
            if (r12 == r2) goto L_0x011c
            r2 = 0
            return r2
        L_0x011c:
            r2 = 0
            if (r6 <= 0) goto L_0x0122
            if (r6 == r14) goto L_0x0122
            return r2
        L_0x0122:
            if (r1 != 0) goto L_0x012b
            if (r10 != 0) goto L_0x012b
            if (r11 == 0) goto L_0x0129
            goto L_0x012b
        L_0x0129:
            r2 = 0
            goto L_0x0144
        L_0x012b:
            if (r4 == 0) goto L_0x0137
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r4.x
            r2 = r2[r23]
            int r2 = r2.getMargin()
            float r2 = (float) r2
            goto L_0x0138
        L_0x0137:
            r2 = 0
        L_0x0138:
            if (r5 == 0) goto L_0x0144
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r12 = r5.x
            r12 = r12[r9]
            int r12 = r12.getMargin()
            float r12 = (float) r12
            float r2 = r2 + r12
        L_0x0144:
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r12 = r8.c
            float r12 = r12.f
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r3 = r3.c
            float r3 = r3.f
            int r18 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r18 >= 0) goto L_0x0152
            float r3 = r3 - r12
            goto L_0x0154
        L_0x0152:
            float r3 = r12 - r3
        L_0x0154:
            float r3 = r3 - r15
            r18 = 1
            if (r6 <= 0) goto L_0x0215
            if (r6 != r14) goto L_0x0215
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.getParent()
            if (r1 == 0) goto L_0x016f
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.getParent()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r1.z
            r1 = r1[r22]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r11) goto L_0x016f
            r1 = 0
            return r1
        L_0x016f:
            float r3 = r3 + r15
            float r3 = r3 - r16
            if (r10 == 0) goto L_0x0178
            float r16 = r16 - r2
            float r3 = r3 - r16
        L_0x0178:
            if (r10 == 0) goto L_0x0194
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r4.x
            r1 = r1[r9]
            int r1 = r1.getMargin()
            float r1 = (float) r1
            float r12 = r12 + r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r1 = r4.W
            r1 = r1[r22]
            if (r1 == 0) goto L_0x0194
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r1.x
            r1 = r1[r23]
            int r1 = r1.getMargin()
            float r1 = (float) r1
            float r12 = r12 + r1
        L_0x0194:
            if (r4 == 0) goto L_0x0213
            androidx.constraintlayout.solver.Metrics r1 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            if (r1 == 0) goto L_0x01b2
            androidx.constraintlayout.solver.Metrics r1 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r10 = r1.nonresolvedWidgets
            long r10 = r10 - r18
            r1.nonresolvedWidgets = r10
            androidx.constraintlayout.solver.Metrics r1 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r10 = r1.resolvedWidgets
            long r10 = r10 + r18
            r1.resolvedWidgets = r10
            androidx.constraintlayout.solver.Metrics r1 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r10 = r1.chainConnectionResolved
            long r10 = r10 + r18
            r1.chainConnectionResolved = r10
        L_0x01b2:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r1 = r4.W
            r1 = r1[r22]
            if (r1 != 0) goto L_0x01bd
            if (r4 != r5) goto L_0x01bb
            goto L_0x01bd
        L_0x01bb:
            r10 = 0
            goto L_0x0211
        L_0x01bd:
            float r2 = (float) r6
            float r2 = r3 / r2
            r10 = 0
            int r11 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r11 <= 0) goto L_0x01cc
            float[] r2 = r4.U
            r2 = r2[r22]
            float r2 = r2 * r3
            float r2 = r2 / r7
        L_0x01cc:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r11 = r4.x
            r11 = r11[r23]
            int r11 = r11.getMargin()
            float r11 = (float) r11
            float r12 = r12 + r11
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r11 = r4.x
            r11 = r11[r23]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r11 = r11.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r13 = r8.e
            r11.resolve(r13, r12)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r11 = r4.x
            r11 = r11[r9]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r11 = r11.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r13 = r8.e
            float r12 = r12 + r2
            r11.resolve(r13, r12)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r4.x
            r2 = r2[r23]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r2 = r2.getResolutionNode()
            r2.a((androidx.constraintlayout.solver.LinearSystem) r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r4.x
            r2 = r2[r9]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r2 = r2.getResolutionNode()
            r2.a((androidx.constraintlayout.solver.LinearSystem) r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r4.x
            r2 = r2[r9]
            int r2 = r2.getMargin()
            float r2 = (float) r2
            float r12 = r12 + r2
        L_0x0211:
            r4 = r1
            goto L_0x0194
        L_0x0213:
            r1 = 1
            return r1
        L_0x0215:
            int r6 = (r3 > r15 ? 1 : (r3 == r15 ? 0 : -1))
            if (r6 >= 0) goto L_0x021b
            r6 = 0
            return r6
        L_0x021b:
            if (r1 == 0) goto L_0x029e
            float r3 = r3 - r2
            float r1 = r17.getHorizontalBiasPercent()
            float r3 = r3 * r1
            float r12 = r12 + r3
        L_0x0225:
            if (r4 == 0) goto L_0x02a3
            androidx.constraintlayout.solver.Metrics r1 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            if (r1 == 0) goto L_0x0243
            androidx.constraintlayout.solver.Metrics r1 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r2 = r1.nonresolvedWidgets
            long r2 = r2 - r18
            r1.nonresolvedWidgets = r2
            androidx.constraintlayout.solver.Metrics r1 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r2 = r1.resolvedWidgets
            long r2 = r2 + r18
            r1.resolvedWidgets = r2
            androidx.constraintlayout.solver.Metrics r1 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r2 = r1.chainConnectionResolved
            long r2 = r2 + r18
            r1.chainConnectionResolved = r2
        L_0x0243:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r1 = r4.W
            r1 = r1[r22]
            if (r1 != 0) goto L_0x024b
            if (r4 != r5) goto L_0x029c
        L_0x024b:
            if (r22 != 0) goto L_0x0252
            int r2 = r4.getWidth()
            goto L_0x0256
        L_0x0252:
            int r2 = r4.getHeight()
        L_0x0256:
            float r2 = (float) r2
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r4.x
            r3 = r3[r23]
            int r3 = r3.getMargin()
            float r3 = (float) r3
            float r12 = r12 + r3
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r4.x
            r3 = r3[r23]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r3 = r3.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r6 = r8.e
            r3.resolve(r6, r12)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r4.x
            r3 = r3[r9]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r3 = r3.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r6 = r8.e
            float r12 = r12 + r2
            r3.resolve(r6, r12)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r4.x
            r2 = r2[r23]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r2 = r2.getResolutionNode()
            r2.a((androidx.constraintlayout.solver.LinearSystem) r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r4.x
            r2 = r2[r9]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r2 = r2.getResolutionNode()
            r2.a((androidx.constraintlayout.solver.LinearSystem) r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r4.x
            r2 = r2[r9]
            int r2 = r2.getMargin()
            float r2 = (float) r2
            float r12 = r12 + r2
        L_0x029c:
            r4 = r1
            goto L_0x0225
        L_0x029e:
            if (r10 != 0) goto L_0x02a6
            if (r11 == 0) goto L_0x02a3
            goto L_0x02a6
        L_0x02a3:
            r0 = 1
            goto L_0x0346
        L_0x02a6:
            if (r10 == 0) goto L_0x02aa
        L_0x02a8:
            float r3 = r3 - r2
            goto L_0x02ad
        L_0x02aa:
            if (r11 == 0) goto L_0x02ad
            goto L_0x02a8
        L_0x02ad:
            int r1 = r14 + 1
            float r1 = (float) r1
            float r1 = r3 / r1
            if (r11 == 0) goto L_0x02bf
            r2 = 1
            if (r14 <= r2) goto L_0x02bb
            int r1 = r14 + -1
            float r1 = (float) r1
            goto L_0x02bd
        L_0x02bb:
            r1 = 1073741824(0x40000000, float:2.0)
        L_0x02bd:
            float r1 = r3 / r1
        L_0x02bf:
            float r2 = r12 + r1
            if (r11 == 0) goto L_0x02d0
            r3 = 1
            if (r14 <= r3) goto L_0x02d0
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r4.x
            r2 = r2[r23]
            int r2 = r2.getMargin()
            float r2 = (float) r2
            float r2 = r2 + r12
        L_0x02d0:
            if (r10 == 0) goto L_0x02de
            if (r4 == 0) goto L_0x02de
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r4.x
            r3 = r3[r23]
            int r3 = r3.getMargin()
            float r3 = (float) r3
            float r2 = r2 + r3
        L_0x02de:
            if (r4 == 0) goto L_0x02a3
            androidx.constraintlayout.solver.Metrics r3 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            if (r3 == 0) goto L_0x02fc
            androidx.constraintlayout.solver.Metrics r3 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r6 = r3.nonresolvedWidgets
            long r6 = r6 - r18
            r3.nonresolvedWidgets = r6
            androidx.constraintlayout.solver.Metrics r3 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r6 = r3.resolvedWidgets
            long r6 = r6 + r18
            r3.resolvedWidgets = r6
            androidx.constraintlayout.solver.Metrics r3 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r6 = r3.chainConnectionResolved
            long r6 = r6 + r18
            r3.chainConnectionResolved = r6
        L_0x02fc:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r3 = r4.W
            r3 = r3[r22]
            if (r3 != 0) goto L_0x0304
            if (r4 != r5) goto L_0x0344
        L_0x0304:
            if (r22 != 0) goto L_0x030b
            int r6 = r4.getWidth()
            goto L_0x030f
        L_0x030b:
            int r6 = r4.getHeight()
        L_0x030f:
            float r6 = (float) r6
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r4.x
            r7 = r7[r23]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r7 = r7.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r10 = r8.e
            r7.resolve(r10, r2)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r4.x
            r7 = r7[r9]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r7 = r7.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r10 = r8.e
            float r11 = r2 + r6
            r7.resolve(r10, r11)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r4.x
            r7 = r7[r23]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r7 = r7.getResolutionNode()
            r7.a((androidx.constraintlayout.solver.LinearSystem) r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r4.x
            r4 = r4[r9]
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r4 = r4.getResolutionNode()
            r4.a((androidx.constraintlayout.solver.LinearSystem) r0)
            float r6 = r6 + r1
            float r2 = r2 + r6
        L_0x0344:
            r4 = r3
            goto L_0x02de
        L_0x0346:
            return r0
        L_0x0347:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Optimizer.a(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer, androidx.constraintlayout.solver.LinearSystem, int, int, androidx.constraintlayout.solver.widgets.ChainHead):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x003d A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean optimizableMatchConstraint(androidx.constraintlayout.solver.widgets.ConstraintWidget r4, int r5) {
        /*
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r4.z
            r0 = r0[r5]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r2 = 0
            if (r0 == r1) goto L_0x000a
            return r2
        L_0x000a:
            float r0 = r4.D
            r1 = 0
            r3 = 1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x001f
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r4.z
            if (r5 != 0) goto L_0x0017
            goto L_0x0018
        L_0x0017:
            r3 = 0
        L_0x0018:
            r4 = r4[r3]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r5) goto L_0x001e
        L_0x001e:
            return r2
        L_0x001f:
            if (r5 != 0) goto L_0x002f
            int r5 = r4.c
            if (r5 == 0) goto L_0x0026
            return r2
        L_0x0026:
            int r5 = r4.f
            if (r5 != 0) goto L_0x002e
            int r4 = r4.g
            if (r4 == 0) goto L_0x003d
        L_0x002e:
            return r2
        L_0x002f:
            int r5 = r4.d
            if (r5 == 0) goto L_0x0034
            return r2
        L_0x0034:
            int r5 = r4.i
            if (r5 != 0) goto L_0x003e
            int r4 = r4.j
            if (r4 == 0) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            return r3
        L_0x003e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Optimizer.optimizableMatchConstraint(androidx.constraintlayout.solver.widgets.ConstraintWidget, int):boolean");
    }
}
