package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;

class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    static void a(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        ChainHead[] chainHeadArr;
        int i3;
        if (i == 0) {
            int i4 = constraintWidgetContainer.ae;
            chainHeadArr = constraintWidgetContainer.ah;
            i2 = i4;
            i3 = 0;
        } else {
            i3 = 2;
            int i5 = constraintWidgetContainer.af;
            i2 = i5;
            chainHeadArr = constraintWidgetContainer.ag;
        }
        for (int i6 = 0; i6 < i2; i6++) {
            ChainHead chainHead = chainHeadArr[i6];
            chainHead.define();
            if (!constraintWidgetContainer.optimizeFor(4) || !Optimizer.a(constraintWidgetContainer, linearSystem, i, i3, chainHead)) {
                a(constraintWidgetContainer, linearSystem, i, i3, chainHead);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v0, resolved type: androidx.constraintlayout.solver.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v1, resolved type: androidx.constraintlayout.solver.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v2, resolved type: androidx.constraintlayout.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v3, resolved type: androidx.constraintlayout.solver.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v4, resolved type: androidx.constraintlayout.solver.widgets.ConstraintWidget} */
    /* JADX WARNING: type inference failed for: r4v11, types: [androidx.constraintlayout.solver.SolverVariable] */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r2.Q == 2) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0048, code lost:
        if (r2.R == 2) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004c, code lost:
        r5 = false;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x02fe  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x030b  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x031f  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x0326  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x0339  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0343  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x0346  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0351  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x0355  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x0358  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0385  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x0388  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x038e  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x03e7  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x03f0  */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x03fc  */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x03ff  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0445  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x049b  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x04a0  */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x04a6  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x04ab  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x04af  */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x04c0  */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x04c3  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0188  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0196  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r34, androidx.constraintlayout.solver.LinearSystem r35, int r36, int r37, androidx.constraintlayout.solver.widgets.ChainHead r38) {
        /*
            r0 = r34
            r9 = r35
            r1 = r38
            androidx.constraintlayout.solver.widgets.ConstraintWidget r10 = r1.a
            androidx.constraintlayout.solver.widgets.ConstraintWidget r11 = r1.c
            androidx.constraintlayout.solver.widgets.ConstraintWidget r12 = r1.b
            androidx.constraintlayout.solver.widgets.ConstraintWidget r13 = r1.d
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r1.e
            float r3 = r1.k
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r1.f
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r1.g
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r0.z
            r4 = r4[r36]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r7 = 1
            if (r4 != r5) goto L_0x0021
            r4 = 1
            goto L_0x0022
        L_0x0021:
            r4 = 0
        L_0x0022:
            r5 = 2
            if (r36 != 0) goto L_0x0038
            int r8 = r2.Q
            if (r8 != 0) goto L_0x002b
            r8 = 1
            goto L_0x002c
        L_0x002b:
            r8 = 0
        L_0x002c:
            int r14 = r2.Q
            if (r14 != r7) goto L_0x0032
            r14 = 1
            goto L_0x0033
        L_0x0032:
            r14 = 0
        L_0x0033:
            int r15 = r2.Q
            if (r15 != r5) goto L_0x004c
            goto L_0x004a
        L_0x0038:
            int r8 = r2.R
            if (r8 != 0) goto L_0x003e
            r8 = 1
            goto L_0x003f
        L_0x003e:
            r8 = 0
        L_0x003f:
            int r14 = r2.R
            if (r14 != r7) goto L_0x0045
            r14 = 1
            goto L_0x0046
        L_0x0045:
            r14 = 0
        L_0x0046:
            int r15 = r2.R
            if (r15 != r5) goto L_0x004c
        L_0x004a:
            r5 = 1
            goto L_0x004d
        L_0x004c:
            r5 = 0
        L_0x004d:
            r15 = r8
            r8 = r10
            r16 = r14
            r14 = r5
            r5 = 0
        L_0x0053:
            r20 = 0
            if (r5 != 0) goto L_0x012e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r8.x
            r6 = r6[r37]
            if (r4 != 0) goto L_0x0063
            if (r14 == 0) goto L_0x0060
            goto L_0x0063
        L_0x0060:
            r22 = 4
            goto L_0x0065
        L_0x0063:
            r22 = 1
        L_0x0065:
            int r23 = r6.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r6.c
            if (r7 == 0) goto L_0x0077
            if (r8 == r10) goto L_0x0077
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r6.c
            int r7 = r7.getMargin()
            int r23 = r23 + r7
        L_0x0077:
            r7 = r23
            if (r14 == 0) goto L_0x0085
            if (r8 == r10) goto L_0x0085
            if (r8 == r12) goto L_0x0085
            r23 = r3
            r22 = r5
            r3 = 6
            goto L_0x0095
        L_0x0085:
            if (r15 == 0) goto L_0x008f
            if (r4 == 0) goto L_0x008f
            r23 = r3
            r22 = r5
            r3 = 4
            goto L_0x0095
        L_0x008f:
            r23 = r3
            r3 = r22
            r22 = r5
        L_0x0095:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r6.c
            if (r5 == 0) goto L_0x00c2
            if (r8 != r12) goto L_0x00aa
            androidx.constraintlayout.solver.SolverVariable r5 = r6.e
            r24 = r15
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r15 = r6.c
            androidx.constraintlayout.solver.SolverVariable r15 = r15.e
            r25 = r2
            r2 = 5
            r9.addGreaterThan(r5, r15, r7, r2)
            goto L_0x00b8
        L_0x00aa:
            r25 = r2
            r24 = r15
            androidx.constraintlayout.solver.SolverVariable r2 = r6.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r6.c
            androidx.constraintlayout.solver.SolverVariable r5 = r5.e
            r15 = 6
            r9.addGreaterThan(r2, r5, r7, r15)
        L_0x00b8:
            androidx.constraintlayout.solver.SolverVariable r2 = r6.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r6.c
            androidx.constraintlayout.solver.SolverVariable r5 = r5.e
            r9.addEquality(r2, r5, r7, r3)
            goto L_0x00c6
        L_0x00c2:
            r25 = r2
            r24 = r15
        L_0x00c6:
            if (r4 == 0) goto L_0x00fd
            int r2 = r8.getVisibility()
            r3 = 8
            if (r2 == r3) goto L_0x00ec
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r8.z
            r2 = r2[r36]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r3) goto L_0x00ec
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r8.x
            int r3 = r37 + 1
            r2 = r2[r3]
            androidx.constraintlayout.solver.SolverVariable r2 = r2.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r8.x
            r3 = r3[r37]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.e
            r5 = 0
            r6 = 5
            r9.addGreaterThan(r2, r3, r5, r6)
            goto L_0x00ed
        L_0x00ec:
            r5 = 0
        L_0x00ed:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r8.x
            r2 = r2[r37]
            androidx.constraintlayout.solver.SolverVariable r2 = r2.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r0.x
            r3 = r3[r37]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.e
            r6 = 6
            r9.addGreaterThan(r2, r3, r5, r6)
        L_0x00fd:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r8.x
            int r3 = r37 + 1
            r2 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.c
            if (r2 == 0) goto L_0x011e
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r2.a
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r2.x
            r3 = r3[r37]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            if (r3 == 0) goto L_0x011e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r2.x
            r3 = r3[r37]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r3.a
            if (r3 == r8) goto L_0x011c
            goto L_0x011e
        L_0x011c:
            r20 = r2
        L_0x011e:
            if (r20 == 0) goto L_0x0125
            r8 = r20
            r5 = r22
            goto L_0x0126
        L_0x0125:
            r5 = 1
        L_0x0126:
            r3 = r23
            r15 = r24
            r2 = r25
            goto L_0x0053
        L_0x012e:
            r25 = r2
            r23 = r3
            r24 = r15
            if (r13 == 0) goto L_0x0158
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r11.x
            int r3 = r37 + 1
            r2 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.c
            if (r2 == 0) goto L_0x0158
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r13.x
            r2 = r2[r3]
            androidx.constraintlayout.solver.SolverVariable r5 = r2.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r11.x
            r3 = r6[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            androidx.constraintlayout.solver.SolverVariable r3 = r3.e
            int r2 = r2.getMargin()
            int r2 = -r2
            r7 = 5
            r9.addLowerThan(r5, r3, r2, r7)
            goto L_0x0159
        L_0x0158:
            r7 = 5
        L_0x0159:
            if (r4 == 0) goto L_0x0175
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r0.x
            int r2 = r37 + 1
            r0 = r0[r2]
            androidx.constraintlayout.solver.SolverVariable r0 = r0.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r11.x
            r3 = r3[r2]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r11.x
            r2 = r4[r2]
            int r2 = r2.getMargin()
            r4 = 6
            r9.addGreaterThan(r0, r3, r2, r4)
        L_0x0175:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r1.h
            if (r0 == 0) goto L_0x022d
            int r2 = r0.size()
            r3 = 1
            if (r2 <= r3) goto L_0x022d
            boolean r4 = r1.l
            if (r4 == 0) goto L_0x018c
            boolean r4 = r1.n
            if (r4 != 0) goto L_0x018c
            int r4 = r1.j
            float r4 = (float) r4
            goto L_0x018e
        L_0x018c:
            r4 = r23
        L_0x018e:
            r5 = 0
            r8 = r20
            r6 = 0
            r27 = 0
        L_0x0194:
            if (r6 >= r2) goto L_0x022d
            java.lang.Object r15 = r0.get(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r15 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r15
            float[] r3 = r15.U
            r3 = r3[r36]
            int r22 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r22 >= 0) goto L_0x01c2
            boolean r3 = r1.n
            if (r3 == 0) goto L_0x01be
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r15.x
            int r22 = r37 + 1
            r3 = r3[r22]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r15 = r15.x
            r15 = r15[r37]
            androidx.constraintlayout.solver.SolverVariable r15 = r15.e
            r5 = 4
            r7 = 0
            r9.addEquality(r3, r15, r7, r5)
            r5 = 0
            r7 = 6
            goto L_0x01db
        L_0x01be:
            r5 = 4
            r3 = 1065353216(0x3f800000, float:1.0)
            goto L_0x01c3
        L_0x01c2:
            r5 = 4
        L_0x01c3:
            r7 = 0
            int r19 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r19 != 0) goto L_0x01e0
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r15.x
            int r19 = r37 + 1
            r3 = r3[r19]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r15 = r15.x
            r15 = r15[r37]
            androidx.constraintlayout.solver.SolverVariable r15 = r15.e
            r5 = 0
            r7 = 6
            r9.addEquality(r3, r15, r5, r7)
        L_0x01db:
            r23 = r0
            r21 = r2
            goto L_0x0222
        L_0x01e0:
            r5 = 0
            r7 = 6
            if (r8 == 0) goto L_0x021b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r8.x
            r5 = r5[r37]
            androidx.constraintlayout.solver.SolverVariable r5 = r5.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r8.x
            int r21 = r37 + 1
            r8 = r8[r21]
            androidx.constraintlayout.solver.SolverVariable r8 = r8.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r15.x
            r7 = r7[r37]
            androidx.constraintlayout.solver.SolverVariable r7 = r7.e
            r23 = r0
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r15.x
            r0 = r0[r21]
            androidx.constraintlayout.solver.SolverVariable r0 = r0.e
            r21 = r2
            androidx.constraintlayout.solver.ArrayRow r2 = r35.createRow()
            r26 = r2
            r28 = r4
            r29 = r3
            r30 = r5
            r31 = r8
            r32 = r7
            r33 = r0
            r26.createRowEqualMatchDimensions(r27, r28, r29, r30, r31, r32, r33)
            r9.addConstraint(r2)
            goto L_0x021f
        L_0x021b:
            r23 = r0
            r21 = r2
        L_0x021f:
            r27 = r3
            r8 = r15
        L_0x0222:
            int r6 = r6 + 1
            r2 = r21
            r0 = r23
            r3 = 1
            r5 = 0
            r7 = 5
            goto L_0x0194
        L_0x022d:
            if (r12 == 0) goto L_0x0297
            if (r12 == r13) goto L_0x0233
            if (r14 == 0) goto L_0x0297
        L_0x0233:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r10.x
            r0 = r0[r37]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r11.x
            int r2 = r37 + 1
            r1 = r1[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r10.x
            r3 = r3[r37]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            if (r3 == 0) goto L_0x024e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r10.x
            r3 = r3[r37]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            androidx.constraintlayout.solver.SolverVariable r3 = r3.e
            goto L_0x0250
        L_0x024e:
            r3 = r20
        L_0x0250:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r11.x
            r4 = r4[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.c
            if (r4 == 0) goto L_0x0262
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r11.x
            r4 = r4[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.c
            androidx.constraintlayout.solver.SolverVariable r4 = r4.e
            r5 = r4
            goto L_0x0264
        L_0x0262:
            r5 = r20
        L_0x0264:
            if (r12 != r13) goto L_0x026e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.x
            r0 = r0[r37]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r12.x
            r1 = r1[r2]
        L_0x026e:
            if (r3 == 0) goto L_0x0487
            if (r5 == 0) goto L_0x0487
            if (r36 != 0) goto L_0x0279
            r2 = r25
            float r2 = r2.M
            goto L_0x027d
        L_0x0279:
            r2 = r25
            float r2 = r2.N
        L_0x027d:
            r4 = r2
            int r6 = r0.getMargin()
            int r7 = r1.getMargin()
            androidx.constraintlayout.solver.SolverVariable r2 = r0.e
            androidx.constraintlayout.solver.SolverVariable r8 = r1.e
            r10 = 5
            r0 = r35
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0487
        L_0x0297:
            if (r24 == 0) goto L_0x0373
            if (r12 == 0) goto L_0x0373
            int r0 = r1.j
            if (r0 <= 0) goto L_0x02a8
            int r0 = r1.i
            int r1 = r1.j
            if (r0 != r1) goto L_0x02a8
            r17 = 1
            goto L_0x02aa
        L_0x02a8:
            r17 = 0
        L_0x02aa:
            r0 = r12
            r14 = r0
        L_0x02ac:
            if (r14 == 0) goto L_0x0487
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r1 = r14.W
            r15 = r1[r36]
            if (r15 != 0) goto L_0x02bd
            if (r14 != r13) goto L_0x02b7
            goto L_0x02bd
        L_0x02b7:
            r19 = 6
            r22 = 4
            goto L_0x036f
        L_0x02bd:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r14.x
            r1 = r1[r37]
            androidx.constraintlayout.solver.SolverVariable r2 = r1.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r1.c
            if (r3 == 0) goto L_0x02cc
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r1.c
            androidx.constraintlayout.solver.SolverVariable r3 = r3.e
            goto L_0x02ce
        L_0x02cc:
            r3 = r20
        L_0x02ce:
            if (r0 == r14) goto L_0x02d9
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r0.x
            int r4 = r37 + 1
            r3 = r3[r4]
        L_0x02d6:
            androidx.constraintlayout.solver.SolverVariable r3 = r3.e
            goto L_0x02ee
        L_0x02d9:
            if (r14 != r12) goto L_0x02ee
            if (r0 != r14) goto L_0x02ee
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r10.x
            r3 = r3[r37]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            if (r3 == 0) goto L_0x02ec
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r10.x
            r3 = r3[r37]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.c
            goto L_0x02d6
        L_0x02ec:
            r3 = r20
        L_0x02ee:
            int r1 = r1.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r14.x
            int r5 = r37 + 1
            r4 = r4[r5]
            int r4 = r4.getMargin()
            if (r15 == 0) goto L_0x030b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r15.x
            r6 = r6[r37]
            androidx.constraintlayout.solver.SolverVariable r7 = r6.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r14.x
            r8 = r8[r5]
        L_0x0308:
            androidx.constraintlayout.solver.SolverVariable r8 = r8.e
            goto L_0x031d
        L_0x030b:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r11.x
            r6 = r6[r5]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r6.c
            if (r6 == 0) goto L_0x0316
            androidx.constraintlayout.solver.SolverVariable r7 = r6.e
            goto L_0x0318
        L_0x0316:
            r7 = r20
        L_0x0318:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r14.x
            r8 = r8[r5]
            goto L_0x0308
        L_0x031d:
            if (r6 == 0) goto L_0x0324
            int r6 = r6.getMargin()
            int r4 = r4 + r6
        L_0x0324:
            if (r0 == 0) goto L_0x032f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r0.x
            r0 = r0[r5]
            int r0 = r0.getMargin()
            int r1 = r1 + r0
        L_0x032f:
            if (r2 == 0) goto L_0x02b7
            if (r3 == 0) goto L_0x02b7
            if (r7 == 0) goto L_0x02b7
            if (r8 == 0) goto L_0x02b7
            if (r14 != r12) goto L_0x0343
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.x
            r0 = r0[r37]
            int r0 = r0.getMargin()
            r6 = r0
            goto L_0x0344
        L_0x0343:
            r6 = r1
        L_0x0344:
            if (r14 != r13) goto L_0x0351
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r13.x
            r0 = r0[r5]
            int r0 = r0.getMargin()
            r18 = r0
            goto L_0x0353
        L_0x0351:
            r18 = r4
        L_0x0353:
            if (r17 == 0) goto L_0x0358
            r21 = 6
            goto L_0x035a
        L_0x0358:
            r21 = 4
        L_0x035a:
            r4 = 1056964608(0x3f000000, float:0.5)
            r0 = r35
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = 4
            r5 = r7
            r19 = 6
            r22 = 4
            r6 = r8
            r7 = r18
            r8 = r21
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x036f:
            r0 = r14
            r14 = r15
            goto L_0x02ac
        L_0x0373:
            r19 = 6
            r22 = 4
            if (r16 == 0) goto L_0x0487
            if (r12 == 0) goto L_0x0487
            int r0 = r1.j
            if (r0 <= 0) goto L_0x0388
            int r0 = r1.i
            int r1 = r1.j
            if (r0 != r1) goto L_0x0388
            r17 = 1
            goto L_0x038a
        L_0x0388:
            r17 = 0
        L_0x038a:
            r0 = r12
            r14 = r0
        L_0x038c:
            if (r14 == 0) goto L_0x042c
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r1 = r14.W
            r1 = r1[r36]
            if (r14 == r12) goto L_0x0423
            if (r14 == r13) goto L_0x0423
            if (r1 == 0) goto L_0x0423
            if (r1 != r13) goto L_0x039d
            r15 = r20
            goto L_0x039e
        L_0x039d:
            r15 = r1
        L_0x039e:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r14.x
            r1 = r1[r37]
            androidx.constraintlayout.solver.SolverVariable r2 = r1.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r1.c
            if (r3 == 0) goto L_0x03ac
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r1.c
            androidx.constraintlayout.solver.SolverVariable r3 = r3.e
        L_0x03ac:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r0.x
            int r4 = r37 + 1
            r3 = r3[r4]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.e
            int r1 = r1.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r14.x
            r5 = r5[r4]
            int r5 = r5.getMargin()
            if (r15 == 0) goto L_0x03d2
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r15.x
            r6 = r6[r37]
            androidx.constraintlayout.solver.SolverVariable r7 = r6.e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r6.c
            if (r8 == 0) goto L_0x03cf
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r6.c
            goto L_0x03e3
        L_0x03cf:
            r8 = r20
            goto L_0x03e5
        L_0x03d2:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r14.x
            r6 = r6[r4]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r6.c
            if (r6 == 0) goto L_0x03dd
            androidx.constraintlayout.solver.SolverVariable r7 = r6.e
            goto L_0x03df
        L_0x03dd:
            r7 = r20
        L_0x03df:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r14.x
            r8 = r8[r4]
        L_0x03e3:
            androidx.constraintlayout.solver.SolverVariable r8 = r8.e
        L_0x03e5:
            if (r6 == 0) goto L_0x03ec
            int r6 = r6.getMargin()
            int r5 = r5 + r6
        L_0x03ec:
            r18 = r5
            if (r0 == 0) goto L_0x03f9
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r0.x
            r0 = r0[r4]
            int r0 = r0.getMargin()
            int r1 = r1 + r0
        L_0x03f9:
            r4 = r1
            if (r17 == 0) goto L_0x03ff
            r21 = 6
            goto L_0x0401
        L_0x03ff:
            r21 = 4
        L_0x0401:
            if (r2 == 0) goto L_0x041f
            if (r3 == 0) goto L_0x041f
            if (r7 == 0) goto L_0x041f
            if (r8 == 0) goto L_0x041f
            r5 = 1056964608(0x3f000000, float:0.5)
            r0 = r35
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r7
            r6 = r8
            r8 = 5
            r7 = r18
            r34 = r14
            r14 = 5
            r8 = r21
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0427
        L_0x041f:
            r34 = r14
            r14 = 5
            goto L_0x0427
        L_0x0423:
            r34 = r14
            r14 = 5
            r15 = r1
        L_0x0427:
            r0 = r34
            r14 = r15
            goto L_0x038c
        L_0x042c:
            r14 = 5
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.x
            r0 = r0[r37]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r10.x
            r1 = r1[r37]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r13.x
            int r3 = r37 + 1
            r10 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r11.x
            r2 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r15 = r2.c
            if (r1 == 0) goto L_0x0477
            if (r12 == r13) goto L_0x0453
            androidx.constraintlayout.solver.SolverVariable r2 = r0.e
            androidx.constraintlayout.solver.SolverVariable r1 = r1.e
            int r0 = r0.getMargin()
            r9.addEquality(r2, r1, r0, r14)
            goto L_0x0477
        L_0x0453:
            if (r15 == 0) goto L_0x0477
            androidx.constraintlayout.solver.SolverVariable r2 = r0.e
            androidx.constraintlayout.solver.SolverVariable r3 = r1.e
            int r4 = r0.getMargin()
            r5 = 1056964608(0x3f000000, float:0.5)
            androidx.constraintlayout.solver.SolverVariable r6 = r10.e
            androidx.constraintlayout.solver.SolverVariable r7 = r15.e
            int r8 = r10.getMargin()
            r17 = 5
            r0 = r35
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r17
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x0477:
            if (r15 == 0) goto L_0x0487
            if (r12 == r13) goto L_0x0487
            androidx.constraintlayout.solver.SolverVariable r0 = r10.e
            androidx.constraintlayout.solver.SolverVariable r1 = r15.e
            int r2 = r10.getMargin()
            int r2 = -r2
            r9.addEquality(r0, r1, r2, r14)
        L_0x0487:
            if (r24 != 0) goto L_0x048b
            if (r16 == 0) goto L_0x04f0
        L_0x048b:
            if (r12 == 0) goto L_0x04f0
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.x
            r0 = r0[r37]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r13.x
            int r2 = r37 + 1
            r1 = r1[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.c
            if (r3 == 0) goto L_0x04a0
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.c
            androidx.constraintlayout.solver.SolverVariable r3 = r3.e
            goto L_0x04a2
        L_0x04a0:
            r3 = r20
        L_0x04a2:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r1.c
            if (r4 == 0) goto L_0x04ab
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r1.c
            androidx.constraintlayout.solver.SolverVariable r4 = r4.e
            goto L_0x04ad
        L_0x04ab:
            r4 = r20
        L_0x04ad:
            if (r11 == r13) goto L_0x04c0
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r11.x
            r4 = r4[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r4.c
            if (r5 == 0) goto L_0x04bd
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.c
            androidx.constraintlayout.solver.SolverVariable r4 = r4.e
            r20 = r4
        L_0x04bd:
            r5 = r20
            goto L_0x04c1
        L_0x04c0:
            r5 = r4
        L_0x04c1:
            if (r12 != r13) goto L_0x04cb
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.x
            r0 = r0[r37]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r12.x
            r1 = r1[r2]
        L_0x04cb:
            if (r3 == 0) goto L_0x04f0
            if (r5 == 0) goto L_0x04f0
            r4 = 1056964608(0x3f000000, float:0.5)
            int r6 = r0.getMargin()
            if (r13 != 0) goto L_0x04d8
            goto L_0x04d9
        L_0x04d8:
            r11 = r13
        L_0x04d9:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r11.x
            r2 = r7[r2]
            int r7 = r2.getMargin()
            androidx.constraintlayout.solver.SolverVariable r2 = r0.e
            androidx.constraintlayout.solver.SolverVariable r8 = r1.e
            r10 = 5
            r0 = r35
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x04f0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Chain.a(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer, androidx.constraintlayout.solver.LinearSystem, int, int, androidx.constraintlayout.solver.widgets.ChainHead):void");
    }
}
