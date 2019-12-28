package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import java.util.ArrayList;

public class ConstraintWidget {
    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    ConstraintWidget A;
    int B;
    int C;
    protected float D;
    protected int E;
    protected int F;
    protected int G;
    protected int H;
    protected int I;
    int J;
    protected int K;
    protected int L;
    float M;
    float N;
    boolean O;
    boolean P;
    int Q;
    int R;
    boolean S;
    boolean T;
    float[] U;
    protected ConstraintWidget[] V;
    protected ConstraintWidget[] W;
    ConstraintWidget X;
    ConstraintWidget Y;
    ResolutionDimension a;
    ResolutionDimension b;
    int c;
    int d;
    int[] e;
    int f;
    int g;
    float h;
    int i;
    int j;
    float k;
    boolean l;
    boolean m;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    private int mDrawHeight;
    private int mDrawWidth;
    private int mDrawX;
    private int mDrawY;
    public int mHorizontalResolution;
    private int[] mMaxDimension;
    private String mType;
    public int mVerticalResolution;
    private int mVisibility;
    private int mWrapHeight;
    private int mWrapWidth;
    int n;
    float o;
    ConstraintAnchor p;
    ConstraintAnchor q;
    ConstraintAnchor r;
    ConstraintAnchor s;
    ConstraintAnchor t;
    ConstraintAnchor u;
    ConstraintAnchor v;
    ConstraintAnchor w;
    protected ConstraintAnchor[] x;
    protected ArrayList<ConstraintAnchor> y;
    protected DimensionBehaviour[] z;

    public enum ContentAlignment {
        BEGIN,
        MIDDLE,
        END,
        TOP,
        VERTICAL_MIDDLE,
        BOTTOM,
        LEFT,
        RIGHT
    }

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public ConstraintWidget() {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.c = 0;
        this.d = 0;
        this.e = new int[2];
        this.f = 0;
        this.g = 0;
        this.h = 1.0f;
        this.i = 0;
        this.j = 0;
        this.k = 1.0f;
        this.n = -1;
        this.o = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.p = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.q = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.r = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.s = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.t = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.u = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.v = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        this.w = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.x = new ConstraintAnchor[]{this.p, this.r, this.q, this.s, this.t, this.w};
        this.y = new ArrayList<>();
        this.z = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.A = null;
        this.B = 0;
        this.C = 0;
        this.D = 0.0f;
        this.E = -1;
        this.F = 0;
        this.G = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.H = 0;
        this.I = 0;
        this.J = 0;
        float f2 = DEFAULT_BIAS;
        this.M = f2;
        this.N = f2;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.Q = 0;
        this.R = 0;
        this.U = new float[]{-1.0f, -1.0f};
        this.V = new ConstraintWidget[]{null, null};
        this.W = new ConstraintWidget[]{null, null};
        this.X = null;
        this.Y = null;
        addAnchors();
    }

    public ConstraintWidget(int i2, int i3) {
        this(0, 0, i2, i3);
    }

    public ConstraintWidget(int i2, int i3, int i4, int i5) {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.c = 0;
        this.d = 0;
        this.e = new int[2];
        this.f = 0;
        this.g = 0;
        this.h = 1.0f;
        this.i = 0;
        this.j = 0;
        this.k = 1.0f;
        this.n = -1;
        this.o = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.p = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.q = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.r = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.s = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.t = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.u = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.v = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        this.w = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.x = new ConstraintAnchor[]{this.p, this.r, this.q, this.s, this.t, this.w};
        this.y = new ArrayList<>();
        this.z = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.A = null;
        this.B = 0;
        this.C = 0;
        this.D = 0.0f;
        this.E = -1;
        this.F = 0;
        this.G = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.H = 0;
        this.I = 0;
        this.J = 0;
        float f2 = DEFAULT_BIAS;
        this.M = f2;
        this.N = f2;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.Q = 0;
        this.R = 0;
        this.U = new float[]{-1.0f, -1.0f};
        this.V = new ConstraintWidget[]{null, null};
        this.W = new ConstraintWidget[]{null, null};
        this.X = null;
        this.Y = null;
        this.F = i2;
        this.G = i3;
        this.B = i4;
        this.C = i5;
        addAnchors();
        forceUpdateDrawPosition();
    }

    private void addAnchors() {
        this.y.add(this.p);
        this.y.add(this.q);
        this.y.add(this.r);
        this.y.add(this.s);
        this.y.add(this.u);
        this.y.add(this.v);
        this.y.add(this.w);
        this.y.add(this.t);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01ee, code lost:
        if (r29 != false) goto L_0x01f0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x029e  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02bc  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02c2  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02d4  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x02d8  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x02dd  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x02e4  */
    /* JADX WARNING: Removed duplicated region for block: B:174:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyConstraints(androidx.constraintlayout.solver.LinearSystem r28, boolean r29, androidx.constraintlayout.solver.SolverVariable r30, androidx.constraintlayout.solver.SolverVariable r31, androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour r32, boolean r33, androidx.constraintlayout.solver.widgets.ConstraintAnchor r34, androidx.constraintlayout.solver.widgets.ConstraintAnchor r35, int r36, int r37, int r38, int r39, float r40, boolean r41, boolean r42, int r43, int r44, int r45, float r46, boolean r47) {
        /*
            r27 = this;
            r0 = r27
            r10 = r28
            r11 = r30
            r12 = r31
            r1 = r38
            r2 = r39
            r13 = r34
            androidx.constraintlayout.solver.SolverVariable r14 = r10.createObjectVariable(r13)
            r15 = r35
            androidx.constraintlayout.solver.SolverVariable r9 = r10.createObjectVariable(r15)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r34.getTarget()
            androidx.constraintlayout.solver.SolverVariable r8 = r10.createObjectVariable(r3)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r35.getTarget()
            androidx.constraintlayout.solver.SolverVariable r7 = r10.createObjectVariable(r3)
            boolean r3 = r10.graphOptimizer
            r6 = 1
            r4 = 6
            r5 = 0
            if (r3 == 0) goto L_0x0066
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r3 = r34.getResolutionNode()
            int r3 = r3.i
            if (r3 != r6) goto L_0x0066
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r3 = r35.getResolutionNode()
            int r3 = r3.i
            if (r3 != r6) goto L_0x0066
            androidx.constraintlayout.solver.Metrics r1 = androidx.constraintlayout.solver.LinearSystem.getMetrics()
            if (r1 == 0) goto L_0x0050
            androidx.constraintlayout.solver.Metrics r1 = androidx.constraintlayout.solver.LinearSystem.getMetrics()
            long r2 = r1.resolvedWidgets
            r6 = 1
            long r2 = r2 + r6
            r1.resolvedWidgets = r2
        L_0x0050:
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r1 = r34.getResolutionNode()
            r1.a((androidx.constraintlayout.solver.LinearSystem) r10)
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r1 = r35.getResolutionNode()
            r1.a((androidx.constraintlayout.solver.LinearSystem) r10)
            if (r42 != 0) goto L_0x0065
            if (r29 == 0) goto L_0x0065
            r10.addGreaterThan(r12, r9, r5, r4)
        L_0x0065:
            return
        L_0x0066:
            androidx.constraintlayout.solver.Metrics r3 = androidx.constraintlayout.solver.LinearSystem.getMetrics()
            if (r3 == 0) goto L_0x007b
            androidx.constraintlayout.solver.Metrics r3 = androidx.constraintlayout.solver.LinearSystem.getMetrics()
            r18 = r7
            long r6 = r3.nonresolvedWidgets
            r16 = 1
            long r6 = r6 + r16
            r3.nonresolvedWidgets = r6
            goto L_0x007d
        L_0x007b:
            r18 = r7
        L_0x007d:
            boolean r16 = r34.isConnected()
            boolean r17 = r35.isConnected()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.w
            boolean r19 = r3.isConnected()
            if (r16 == 0) goto L_0x008f
            r3 = 1
            goto L_0x0090
        L_0x008f:
            r3 = 0
        L_0x0090:
            if (r17 == 0) goto L_0x0094
            int r3 = r3 + 1
        L_0x0094:
            if (r19 == 0) goto L_0x0098
            int r3 = r3 + 1
        L_0x0098:
            r7 = r3
            if (r41 == 0) goto L_0x009d
            r3 = 3
            goto L_0x009f
        L_0x009d:
            r3 = r43
        L_0x009f:
            int[] r20 = androidx.constraintlayout.solver.widgets.ConstraintWidget.AnonymousClass1.b
            int r21 = r32.ordinal()
            r20 = r20[r21]
            r5 = 4
            switch(r20) {
                case 1: goto L_0x00ab;
                case 2: goto L_0x00ab;
                case 3: goto L_0x00ab;
                case 4: goto L_0x00ae;
                default: goto L_0x00ab;
            }
        L_0x00ab:
            r20 = 0
            goto L_0x00b3
        L_0x00ae:
            if (r3 != r5) goto L_0x00b1
            goto L_0x00ab
        L_0x00b1:
            r20 = 1
        L_0x00b3:
            int r5 = r0.mVisibility
            r6 = 8
            if (r5 != r6) goto L_0x00bd
            r5 = 0
            r20 = 0
            goto L_0x00bf
        L_0x00bd:
            r5 = r37
        L_0x00bf:
            if (r47 == 0) goto L_0x00d8
            if (r16 != 0) goto L_0x00cd
            if (r17 != 0) goto L_0x00cd
            if (r19 != 0) goto L_0x00cd
            r6 = r36
            r10.addEquality(r14, r6)
            goto L_0x00d8
        L_0x00cd:
            if (r16 == 0) goto L_0x00d8
            if (r17 != 0) goto L_0x00d8
            int r6 = r34.getMargin()
            r10.addEquality(r14, r8, r6, r4)
        L_0x00d8:
            if (r20 != 0) goto L_0x0106
            if (r33 == 0) goto L_0x00f2
            r4 = 3
            r6 = 0
            r10.addEquality(r9, r14, r6, r4)
            if (r1 <= 0) goto L_0x00e8
            r5 = 6
            r10.addGreaterThan(r9, r14, r1, r5)
            goto L_0x00e9
        L_0x00e8:
            r5 = 6
        L_0x00e9:
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r2 >= r4) goto L_0x00f7
            r10.addLowerThan(r9, r14, r2, r5)
            goto L_0x00f7
        L_0x00f2:
            r2 = 6
            r6 = 0
            r10.addEquality(r9, r14, r5, r2)
        L_0x00f7:
            r13 = r44
            r0 = r45
            r32 = r3
            r15 = r7
            r22 = r8
            r25 = r18
            r21 = 4
            goto L_0x01d8
        L_0x0106:
            r6 = 0
            r2 = -2
            r4 = r44
            if (r4 != r2) goto L_0x0110
            r4 = r45
            r13 = r5
            goto L_0x0113
        L_0x0110:
            r13 = r4
            r4 = r45
        L_0x0113:
            if (r4 != r2) goto L_0x0116
            r4 = r5
        L_0x0116:
            if (r13 <= 0) goto L_0x0121
            r2 = 6
            r10.addGreaterThan(r9, r14, r13, r2)
            int r5 = java.lang.Math.max(r5, r13)
            goto L_0x0122
        L_0x0121:
            r2 = 6
        L_0x0122:
            if (r4 <= 0) goto L_0x0134
            if (r29 == 0) goto L_0x012b
            r6 = 1
            r10.addLowerThan(r9, r14, r4, r6)
            goto L_0x012f
        L_0x012b:
            r6 = 1
            r10.addLowerThan(r9, r14, r4, r2)
        L_0x012f:
            int r5 = java.lang.Math.min(r5, r4)
            goto L_0x0135
        L_0x0134:
            r6 = 1
        L_0x0135:
            if (r3 != r6) goto L_0x0146
            if (r29 == 0) goto L_0x013e
            r10.addEquality(r9, r14, r5, r2)
            goto L_0x01b1
        L_0x013e:
            if (r42 == 0) goto L_0x0141
            r6 = 4
        L_0x0141:
            r10.addEquality(r9, r14, r5, r6)
            goto L_0x01b1
        L_0x0146:
            r6 = 2
            if (r3 != r6) goto L_0x01b1
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = r34.getType()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r6 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            if (r2 == r6) goto L_0x016d
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = r34.getType()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r6 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            if (r2 != r6) goto L_0x015a
            goto L_0x016d
        L_0x015a:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r0.A
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r6 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.getAnchor(r6)
            androidx.constraintlayout.solver.SolverVariable r2 = r10.createObjectVariable(r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r0.A
            r37 = r2
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            goto L_0x017f
        L_0x016d:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r0.A
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r6 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.getAnchor(r6)
            androidx.constraintlayout.solver.SolverVariable r2 = r10.createObjectVariable(r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r0.A
            r37 = r2
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
        L_0x017f:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r6.getAnchor(r2)
            androidx.constraintlayout.solver.SolverVariable r2 = r10.createObjectVariable(r2)
            r20 = r37
            r6 = r2
            androidx.constraintlayout.solver.ArrayRow r2 = r28.createRow()
            r23 = 6
            r15 = r3
            r3 = r9
            r0 = r4
            r22 = r8
            r8 = 6
            r23 = 3
            r4 = r14
            r8 = r5
            r32 = r15
            r15 = 0
            r21 = 4
            r5 = r6
            r15 = 2
            r6 = r20
            r15 = r7
            r25 = r18
            r7 = r46
            androidx.constraintlayout.solver.ArrayRow r2 = r2.createRowDimensionRatio(r3, r4, r5, r6, r7)
            r10.addConstraint(r2)
            r5 = 0
            goto L_0x01be
        L_0x01b1:
            r32 = r3
            r0 = r4
            r15 = r7
            r22 = r8
            r25 = r18
            r21 = 4
            r8 = r5
            r5 = r20
        L_0x01be:
            if (r5 == 0) goto L_0x01d6
            r2 = 2
            if (r15 == r2) goto L_0x01d6
            if (r41 != 0) goto L_0x01d6
            int r2 = java.lang.Math.max(r13, r8)
            if (r0 <= 0) goto L_0x01cf
            int r2 = java.lang.Math.min(r0, r2)
        L_0x01cf:
            r3 = 6
            r10.addEquality(r9, r14, r2, r3)
            r20 = 0
            goto L_0x01d8
        L_0x01d6:
            r20 = r5
        L_0x01d8:
            if (r47 == 0) goto L_0x02eb
            if (r42 == 0) goto L_0x01de
            goto L_0x02eb
        L_0x01de:
            r2 = 5
            if (r16 != 0) goto L_0x01e9
            if (r17 != 0) goto L_0x01e9
            if (r19 != 0) goto L_0x01e9
            if (r29 == 0) goto L_0x02df
            r3 = 0
            goto L_0x01f0
        L_0x01e9:
            r3 = 0
            if (r16 == 0) goto L_0x01f5
            if (r17 != 0) goto L_0x01f5
            if (r29 == 0) goto L_0x02df
        L_0x01f0:
            r10.addGreaterThan(r12, r9, r3, r2)
            goto L_0x02df
        L_0x01f5:
            if (r16 != 0) goto L_0x020b
            if (r17 == 0) goto L_0x020b
            int r0 = r35.getMargin()
            int r0 = -r0
            r15 = r25
            r1 = 6
            r10.addEquality(r9, r15, r0, r1)
            if (r29 == 0) goto L_0x02df
            r10.addGreaterThan(r14, r11, r3, r2)
            goto L_0x02df
        L_0x020b:
            r15 = r25
            if (r16 == 0) goto L_0x02df
            if (r17 == 0) goto L_0x02df
            if (r20 == 0) goto L_0x0282
            if (r29 == 0) goto L_0x021c
            if (r1 != 0) goto L_0x021c
            r8 = 6
            r10.addGreaterThan(r9, r14, r3, r8)
            goto L_0x021d
        L_0x021c:
            r8 = 6
        L_0x021d:
            if (r32 != 0) goto L_0x024c
            if (r0 > 0) goto L_0x0227
            if (r13 <= 0) goto L_0x0224
            goto L_0x0227
        L_0x0224:
            r1 = 6
            r6 = 0
            goto L_0x0229
        L_0x0227:
            r1 = 4
            r6 = 1
        L_0x0229:
            int r3 = r34.getMargin()
            r7 = r22
            r10.addEquality(r14, r7, r3, r1)
            int r3 = r35.getMargin()
            int r3 = -r3
            r10.addEquality(r9, r15, r3, r1)
            if (r0 > 0) goto L_0x0242
            if (r13 <= 0) goto L_0x023f
            goto L_0x0242
        L_0x023f:
            r24 = 0
            goto L_0x0244
        L_0x0242:
            r24 = 1
        L_0x0244:
            r16 = r6
            r1 = r24
            r0 = 5
            r13 = r27
            goto L_0x029c
        L_0x024c:
            r6 = r32
            r7 = r22
            r1 = 1
            if (r6 != r1) goto L_0x0259
            r0 = 6
            r13 = r27
        L_0x0256:
            r16 = 1
            goto L_0x029c
        L_0x0259:
            r3 = 3
            if (r6 != r3) goto L_0x027d
            if (r41 != 0) goto L_0x0269
            r13 = r27
            int r3 = r13.n
            r4 = -1
            if (r3 == r4) goto L_0x026b
            if (r0 > 0) goto L_0x026b
            r0 = 6
            goto L_0x026c
        L_0x0269:
            r13 = r27
        L_0x026b:
            r0 = 4
        L_0x026c:
            int r3 = r34.getMargin()
            r10.addEquality(r14, r7, r3, r0)
            int r3 = r35.getMargin()
            int r3 = -r3
            r10.addEquality(r9, r15, r3, r0)
            r0 = 5
            goto L_0x0256
        L_0x027d:
            r13 = r27
            r0 = 5
            r1 = 0
            goto L_0x029a
        L_0x0282:
            r7 = r22
            r1 = 1
            r8 = 6
            r13 = r27
            if (r29 == 0) goto L_0x0299
            int r0 = r34.getMargin()
            r10.addGreaterThan(r14, r7, r0, r2)
            int r0 = r35.getMargin()
            int r0 = -r0
            r10.addLowerThan(r9, r15, r0, r2)
        L_0x0299:
            r0 = 5
        L_0x029a:
            r16 = 0
        L_0x029c:
            if (r1 == 0) goto L_0x02bc
            int r4 = r34.getMargin()
            int r17 = r35.getMargin()
            r1 = r28
            r2 = r14
            r3 = r7
            r5 = r40
            r6 = r15
            r18 = r7
            r7 = r9
            r13 = r18
            r12 = 6
            r8 = r17
            r26 = r9
            r9 = r0
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x02c0
        L_0x02bc:
            r13 = r7
            r26 = r9
            r12 = 6
        L_0x02c0:
            if (r16 == 0) goto L_0x02d4
            int r0 = r34.getMargin()
            r10.addGreaterThan(r14, r13, r0, r12)
            int r0 = r35.getMargin()
            int r0 = -r0
            r1 = r26
            r10.addLowerThan(r1, r15, r0, r12)
            goto L_0x02d6
        L_0x02d4:
            r1 = r26
        L_0x02d6:
            if (r29 == 0) goto L_0x02dd
            r0 = 0
            r10.addGreaterThan(r14, r11, r0, r12)
            goto L_0x02e2
        L_0x02dd:
            r0 = 0
            goto L_0x02e2
        L_0x02df:
            r1 = r9
            r0 = 0
            r12 = 6
        L_0x02e2:
            if (r29 == 0) goto L_0x02ea
            r2 = r31
            r3 = 6
            r10.addGreaterThan(r2, r1, r0, r3)
        L_0x02ea:
            return
        L_0x02eb:
            r1 = r9
            r2 = r12
            r0 = 0
            r3 = 6
            r4 = 2
            if (r15 >= r4) goto L_0x02fa
            if (r29 == 0) goto L_0x02fa
            r10.addGreaterThan(r14, r11, r0, r3)
            r10.addGreaterThan(r2, r1, r0, r3)
        L_0x02fa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.applyConstraints(androidx.constraintlayout.solver.LinearSystem, boolean, androidx.constraintlayout.solver.SolverVariable, androidx.constraintlayout.solver.SolverVariable, androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour, boolean, androidx.constraintlayout.solver.widgets.ConstraintAnchor, androidx.constraintlayout.solver.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, int, int, int, float, boolean):void");
    }

    /* access modifiers changed from: protected */
    public int a() {
        return this.F + this.H;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0224, code lost:
        if (r1 == -1) goto L_0x0228;
     */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x022b  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x023b  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x023e  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0250  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02b6  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02c7 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02c8  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x032f  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x0338  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x033e  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0346  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x037d  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x03a3  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x03ad  */
    /* JADX WARNING: Removed duplicated region for block: B:200:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addToSolver(androidx.constraintlayout.solver.LinearSystem r39) {
        /*
            r38 = this;
            r15 = r38
            r14 = r39
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.p
            androidx.constraintlayout.solver.SolverVariable r21 = r14.createObjectVariable(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.r
            androidx.constraintlayout.solver.SolverVariable r10 = r14.createObjectVariable(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.q
            androidx.constraintlayout.solver.SolverVariable r6 = r14.createObjectVariable(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.s
            androidx.constraintlayout.solver.SolverVariable r4 = r14.createObjectVariable(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.t
            androidx.constraintlayout.solver.SolverVariable r3 = r14.createObjectVariable(r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r15.A
            r1 = 8
            r2 = 1
            r13 = 0
            if (r0 == 0) goto L_0x011e
            if (r0 == 0) goto L_0x0036
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.z
            r0 = r0[r13]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r5) goto L_0x0036
            r0 = 1
            goto L_0x0037
        L_0x0036:
            r0 = 0
        L_0x0037:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r15.A
            if (r5 == 0) goto L_0x0045
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r5.z
            r5 = r5[r2]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r5 != r7) goto L_0x0045
            r5 = 1
            goto L_0x0046
        L_0x0045:
            r5 = 0
        L_0x0046:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r15.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            if (r7 == 0) goto L_0x006d
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r15.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.p
            if (r7 == r8) goto L_0x006d
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r15.r
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            if (r7 == 0) goto L_0x006d
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r15.r
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.r
            if (r7 != r8) goto L_0x006d
            androidx.constraintlayout.solver.widgets.ConstraintWidget r7 = r15.A
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r7 = (androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer) r7
            r7.a(r15, r13)
        L_0x006d:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r15.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            if (r7 == 0) goto L_0x007d
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r15.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.p
            if (r7 == r8) goto L_0x008d
        L_0x007d:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r15.r
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            if (r7 == 0) goto L_0x008f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r15.r
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r7.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.r
            if (r7 != r8) goto L_0x008f
        L_0x008d:
            r7 = 1
            goto L_0x0090
        L_0x008f:
            r7 = 0
        L_0x0090:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.c
            if (r8 == 0) goto L_0x00b7
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r15.q
            if (r8 == r9) goto L_0x00b7
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.s
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.c
            if (r8 == 0) goto L_0x00b7
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.s
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r15.s
            if (r8 != r9) goto L_0x00b7
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = r15.A
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r8 = (androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer) r8
            r8.a(r15, r2)
        L_0x00b7:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.c
            if (r8 == 0) goto L_0x00c7
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r15.q
            if (r8 == r9) goto L_0x00d7
        L_0x00c7:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.s
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.c
            if (r8 == 0) goto L_0x00d9
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.s
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r15.s
            if (r8 != r9) goto L_0x00d9
        L_0x00d7:
            r8 = 1
            goto L_0x00da
        L_0x00d9:
            r8 = 0
        L_0x00da:
            if (r0 == 0) goto L_0x00f7
            int r9 = r15.mVisibility
            if (r9 == r1) goto L_0x00f7
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r15.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            if (r9 != 0) goto L_0x00f7
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r15.r
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            if (r9 != 0) goto L_0x00f7
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r15.A
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.r
            androidx.constraintlayout.solver.SolverVariable r9 = r14.createObjectVariable(r9)
            r14.addGreaterThan(r9, r10, r13, r2)
        L_0x00f7:
            if (r5 == 0) goto L_0x0118
            int r9 = r15.mVisibility
            if (r9 == r1) goto L_0x0118
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r15.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            if (r9 != 0) goto L_0x0118
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r15.s
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.c
            if (r9 != 0) goto L_0x0118
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r15.t
            if (r9 != 0) goto L_0x0118
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r15.A
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.s
            androidx.constraintlayout.solver.SolverVariable r9 = r14.createObjectVariable(r9)
            r14.addGreaterThan(r9, r4, r13, r2)
        L_0x0118:
            r12 = r5
            r16 = r7
            r22 = r8
            goto L_0x0124
        L_0x011e:
            r0 = 0
            r12 = 0
            r16 = 0
            r22 = 0
        L_0x0124:
            int r5 = r15.B
            int r7 = r15.K
            if (r5 >= r7) goto L_0x012b
            r5 = r7
        L_0x012b:
            int r7 = r15.C
            int r8 = r15.L
            if (r7 >= r8) goto L_0x0132
            r7 = r8
        L_0x0132:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r8 = r15.z
            r8 = r8[r13]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 == r9) goto L_0x013c
            r8 = 1
            goto L_0x013d
        L_0x013c:
            r8 = 0
        L_0x013d:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r9 = r15.z
            r9 = r9[r2]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r9 == r11) goto L_0x0147
            r9 = 1
            goto L_0x0148
        L_0x0147:
            r9 = 0
        L_0x0148:
            int r11 = r15.E
            r15.n = r11
            float r11 = r15.D
            r15.o = r11
            int r2 = r15.c
            int r13 = r15.d
            r18 = 0
            r19 = 4
            int r11 = (r11 > r18 ? 1 : (r11 == r18 ? 0 : -1))
            if (r11 <= 0) goto L_0x0209
            int r11 = r15.mVisibility
            r1 = 8
            if (r11 == r1) goto L_0x0209
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.z
            r11 = 0
            r1 = r1[r11]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r23 = r3
            r3 = 3
            if (r1 != r11) goto L_0x0171
            if (r2 != 0) goto L_0x0171
            r2 = 3
        L_0x0171:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.z
            r11 = 1
            r1 = r1[r11]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r11) goto L_0x017d
            if (r13 != 0) goto L_0x017d
            r13 = 3
        L_0x017d:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.z
            r11 = 0
            r1 = r1[r11]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r11) goto L_0x0198
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.z
            r11 = 1
            r1 = r1[r11]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r11) goto L_0x0198
            if (r2 != r3) goto L_0x0198
            if (r13 != r3) goto L_0x0198
            r15.setupDimensionRatio(r0, r12, r8, r9)
            goto L_0x01fe
        L_0x0198:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.z
            r8 = 0
            r1 = r1[r8]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r9) goto L_0x01c4
            if (r2 != r3) goto L_0x01c4
            r15.n = r8
            float r1 = r15.o
            int r3 = r15.C
            float r3 = (float) r3
            float r1 = r1 * r3
            int r1 = (int) r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r15.z
            r8 = 1
            r3 = r3[r8]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 == r5) goto L_0x01bf
            r28 = r1
            r29 = r7
            r26 = r13
            r25 = 4
            goto L_0x0213
        L_0x01bf:
            r28 = r1
            r25 = r2
            goto L_0x0202
        L_0x01c4:
            r8 = 1
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.z
            r1 = r1[r8]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r9) goto L_0x01fe
            if (r13 != r3) goto L_0x01fe
            r15.n = r8
            int r1 = r15.E
            r3 = -1
            if (r1 != r3) goto L_0x01dd
            r1 = 1065353216(0x3f800000, float:1.0)
            float r3 = r15.o
            float r1 = r1 / r3
            r15.o = r1
        L_0x01dd:
            float r1 = r15.o
            int r3 = r15.B
            float r3 = (float) r3
            float r1 = r1 * r3
            int r1 = (int) r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r15.z
            r7 = 0
            r3 = r3[r7]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 == r7) goto L_0x01f7
            r29 = r1
            r25 = r2
            r28 = r5
            r26 = 4
            goto L_0x0213
        L_0x01f7:
            r29 = r1
            r25 = r2
            r28 = r5
            goto L_0x0204
        L_0x01fe:
            r25 = r2
            r28 = r5
        L_0x0202:
            r29 = r7
        L_0x0204:
            r26 = r13
            r27 = 1
            goto L_0x0215
        L_0x0209:
            r23 = r3
            r25 = r2
            r28 = r5
            r29 = r7
            r26 = r13
        L_0x0213:
            r27 = 0
        L_0x0215:
            int[] r1 = r15.e
            r2 = 0
            r1[r2] = r25
            r2 = 1
            r1[r2] = r26
            if (r27 == 0) goto L_0x022b
            int r1 = r15.n
            if (r1 == 0) goto L_0x0227
            r2 = -1
            if (r1 != r2) goto L_0x022c
            goto L_0x0228
        L_0x0227:
            r2 = -1
        L_0x0228:
            r24 = 1
            goto L_0x022e
        L_0x022b:
            r2 = -1
        L_0x022c:
            r24 = 0
        L_0x022e:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.z
            r3 = 0
            r1 = r1[r3]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r3) goto L_0x023e
            boolean r1 = r15 instanceof androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer
            if (r1 == 0) goto L_0x023e
            r30 = 1
            goto L_0x0240
        L_0x023e:
            r30 = 0
        L_0x0240:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r15.w
            boolean r1 = r1.isConnected()
            r3 = 1
            r31 = r1 ^ 1
            int r1 = r15.mHorizontalResolution
            r13 = 2
            r32 = 0
            if (r1 == r13) goto L_0x02b6
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r15.A
            if (r1 == 0) goto L_0x025d
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.r
            androidx.constraintlayout.solver.SolverVariable r1 = r14.createObjectVariable(r1)
            r20 = r1
            goto L_0x025f
        L_0x025d:
            r20 = r32
        L_0x025f:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r15.A
            if (r1 == 0) goto L_0x026c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.p
            androidx.constraintlayout.solver.SolverVariable r1 = r14.createObjectVariable(r1)
            r33 = r1
            goto L_0x026e
        L_0x026c:
            r33 = r32
        L_0x026e:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.z
            r17 = 0
            r5 = r1[r17]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r15.p
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.r
            int r9 = r15.F
            int r11 = r15.K
            int[] r1 = r15.mMaxDimension
            r1 = r1[r17]
            r34 = r12
            r12 = r1
            float r1 = r15.M
            r13 = r1
            int r1 = r15.f
            r17 = r1
            int r1 = r15.g
            r18 = r1
            float r1 = r15.h
            r19 = r1
            r35 = r0
            r0 = r38
            r1 = r39
            r2 = r35
            r36 = r23
            r3 = r33
            r23 = r4
            r4 = r20
            r37 = r6
            r6 = r30
            r30 = r10
            r10 = r28
            r14 = r24
            r15 = r16
            r16 = r25
            r20 = r31
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            goto L_0x02c0
        L_0x02b6:
            r37 = r6
            r30 = r10
            r34 = r12
            r36 = r23
            r23 = r4
        L_0x02c0:
            r15 = r38
            int r0 = r15.mVerticalResolution
            r1 = 2
            if (r0 != r1) goto L_0x02c8
            return
        L_0x02c8:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.z
            r14 = 1
            r0 = r0[r14]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r1) goto L_0x02d7
            boolean r0 = r15 instanceof androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer
            if (r0 == 0) goto L_0x02d7
            r6 = 1
            goto L_0x02d8
        L_0x02d7:
            r6 = 0
        L_0x02d8:
            if (r27 == 0) goto L_0x02e4
            int r0 = r15.n
            if (r0 == r14) goto L_0x02e1
            r1 = -1
            if (r0 != r1) goto L_0x02e4
        L_0x02e1:
            r16 = 1
            goto L_0x02e6
        L_0x02e4:
            r16 = 0
        L_0x02e6:
            int r0 = r15.J
            if (r0 <= 0) goto L_0x0325
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.t
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r0 = r0.getResolutionNode()
            int r0 = r0.i
            if (r0 != r14) goto L_0x0302
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.t
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r0 = r0.getResolutionNode()
            r10 = r39
            r0.a((androidx.constraintlayout.solver.LinearSystem) r10)
            r4 = r37
            goto L_0x0329
        L_0x0302:
            r10 = r39
            int r0 = r38.getBaselineDistance()
            r1 = 6
            r2 = r36
            r4 = r37
            r10.addEquality(r2, r4, r0, r1)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.t
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.c
            if (r0 == 0) goto L_0x0329
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.t
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.c
            androidx.constraintlayout.solver.SolverVariable r0 = r10.createObjectVariable(r0)
            r3 = 0
            r10.addEquality(r2, r0, r3, r1)
            r20 = 0
            goto L_0x032b
        L_0x0325:
            r4 = r37
            r10 = r39
        L_0x0329:
            r20 = r31
        L_0x032b:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r15.A
            if (r0 == 0) goto L_0x0338
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.s
            androidx.constraintlayout.solver.SolverVariable r0 = r10.createObjectVariable(r0)
            r24 = r0
            goto L_0x033a
        L_0x0338:
            r24 = r32
        L_0x033a:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r15.A
            if (r0 == 0) goto L_0x0346
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.q
            androidx.constraintlayout.solver.SolverVariable r0 = r10.createObjectVariable(r0)
            r3 = r0
            goto L_0x0348
        L_0x0346:
            r3 = r32
        L_0x0348:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.z
            r5 = r0[r14]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r15.q
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.s
            int r9 = r15.G
            int r11 = r15.L
            int[] r0 = r15.mMaxDimension
            r12 = r0[r14]
            float r13 = r15.N
            int r0 = r15.i
            r17 = r0
            int r0 = r15.j
            r18 = r0
            float r0 = r15.k
            r19 = r0
            r0 = r38
            r1 = r39
            r2 = r34
            r25 = r4
            r4 = r24
            r10 = r29
            r14 = r16
            r15 = r22
            r16 = r26
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            if (r27 == 0) goto L_0x03a3
            r6 = 6
            r7 = r38
            int r0 = r7.n
            r1 = 1
            if (r0 != r1) goto L_0x0392
            float r5 = r7.o
            r0 = r39
            r1 = r23
            r2 = r25
            r3 = r30
            r4 = r21
            goto L_0x039f
        L_0x0392:
            float r5 = r7.o
            r6 = 6
            r0 = r39
            r1 = r30
            r2 = r21
            r3 = r23
            r4 = r25
        L_0x039f:
            r0.addRatio(r1, r2, r3, r4, r5, r6)
            goto L_0x03a5
        L_0x03a3:
            r7 = r38
        L_0x03a5:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r7.w
            boolean r0 = r0.isConnected()
            if (r0 == 0) goto L_0x03cd
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r7.w
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.getTarget()
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r0.getOwner()
            float r1 = r7.mCircleConstraintAngle
            r2 = 1119092736(0x42b40000, float:90.0)
            float r1 = r1 + r2
            double r1 = (double) r1
            double r1 = java.lang.Math.toRadians(r1)
            float r1 = (float) r1
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r7.w
            int r2 = r2.getMargin()
            r3 = r39
            r3.addCenterPoint(r7, r0, r1, r2)
        L_0x03cd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.addToSolver(androidx.constraintlayout.solver.LinearSystem):void");
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void analyze(int i2) {
        Optimizer.a(i2, this);
    }

    /* access modifiers changed from: protected */
    public int b() {
        return this.G + this.I;
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2) {
        connect(type, constraintWidget, type2, 0, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2) {
        connect(type, constraintWidget, type2, i2, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2, ConstraintAnchor.Strength strength) {
        connect(type, constraintWidget, type2, i2, strength, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01fe, code lost:
        if (r3.isConnected() != false) goto L_0x0200;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0233, code lost:
        if (r3.isConnected() != false) goto L_0x0200;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type r16, androidx.constraintlayout.solver.widgets.ConstraintWidget r17, androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type r18, int r19, androidx.constraintlayout.solver.widgets.ConstraintAnchor.Strength r20, int r21) {
        /*
            r15 = this;
            r8 = r15
            r0 = r16
            r9 = r17
            r10 = r18
            r11 = r21
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER
            r12 = 0
            if (r0 != r1) goto L_0x00f2
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER
            if (r10 != r0) goto L_0x00a4
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r15.getAnchor(r1)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r15.getAnchor(r2)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r15.getAnchor(r2)
            r14 = 1
            if (r0 == 0) goto L_0x0033
            boolean r0 = r0.isConnected()
            if (r0 != 0) goto L_0x003b
        L_0x0033:
            if (r1 == 0) goto L_0x003d
            boolean r0 = r1.isConnected()
            if (r0 == 0) goto L_0x003d
        L_0x003b:
            r0 = 0
            goto L_0x0054
        L_0x003d:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            r5 = 0
            r1 = r15
            r3 = r17
            r6 = r20
            r7 = r21
            r1.connect(r2, r3, r4, r5, r6, r7)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            r1.connect(r2, r3, r4, r5, r6, r7)
            r0 = 1
        L_0x0054:
            if (r10 == 0) goto L_0x005c
            boolean r1 = r10.isConnected()
            if (r1 != 0) goto L_0x0064
        L_0x005c:
            if (r13 == 0) goto L_0x0066
            boolean r1 = r13.isConnected()
            if (r1 == 0) goto L_0x0066
        L_0x0064:
            r14 = 0
            goto L_0x007c
        L_0x0066:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            r5 = 0
            r1 = r15
            r3 = r17
            r6 = r20
            r7 = r21
            r1.connect(r2, r3, r4, r5, r6, r7)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            r1.connect(r2, r3, r4, r5, r6, r7)
        L_0x007c:
            if (r0 == 0) goto L_0x0089
            if (r14 == 0) goto L_0x0089
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER
            goto L_0x0093
        L_0x0089:
            if (r0 == 0) goto L_0x0099
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_X
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_X
        L_0x0093:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r9.getAnchor(r1)
            goto L_0x011a
        L_0x0099:
            if (r14 == 0) goto L_0x0247
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_Y
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_Y
            goto L_0x0093
        L_0x00a4:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            if (r10 == r0) goto L_0x00ca
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            if (r10 != r0) goto L_0x00ad
            goto L_0x00ca
        L_0x00ad:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            if (r10 == r0) goto L_0x00b5
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            if (r10 != r0) goto L_0x0247
        L_0x00b5:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            r5 = 0
            r1 = r15
            r3 = r17
            r4 = r18
            r6 = r20
            r7 = r21
            r1.connect(r2, r3, r4, r5, r6, r7)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            r1.connect(r2, r3, r4, r5, r6, r7)
            goto L_0x00e7
        L_0x00ca:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            r5 = 0
            r1 = r15
            r3 = r17
            r4 = r18
            r6 = r20
            r7 = r21
            r1.connect(r2, r3, r4, r5, r6, r7)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            r1 = r15
            r3 = r17
            r4 = r18
            r6 = r20
            r7 = r21
            r1.connect(r2, r3, r4, r5, r6, r7)     // Catch:{ Throwable -> 0x0248 }
        L_0x00e7:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER
        L_0x00e9:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r17.getAnchor(r18)
            goto L_0x011a
        L_0x00f2:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_X
            if (r0 != r1) goto L_0x011f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            if (r10 == r1) goto L_0x00fe
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            if (r10 != r1) goto L_0x011f
        L_0x00fe:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r17.getAnchor(r18)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r15.getAnchor(r2)
            r0.connect(r1, r12, r11)
            r2.connect(r1, r12, r11)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_X
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r0)
        L_0x011a:
            r0.connect(r1, r12, r11)
            goto L_0x0247
        L_0x011f:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_Y
            if (r0 != r1) goto L_0x014c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            if (r10 == r1) goto L_0x012b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            if (r10 != r1) goto L_0x014c
        L_0x012b:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r17.getAnchor(r18)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r15.getAnchor(r1)
            r1.connect(r0, r12, r11)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r15.getAnchor(r1)
            r1.connect(r0, r12, r11)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_Y
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r15.getAnchor(r1)
            r1.connect(r0, r12, r11)
            goto L_0x0247
        L_0x014c:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_X
            if (r0 != r1) goto L_0x0176
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_X
            if (r10 != r1) goto L_0x0176
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r9.getAnchor(r1)
            r0.connect(r1, r12, r11)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r9.getAnchor(r1)
            r0.connect(r1, r12, r11)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_X
            goto L_0x00e9
        L_0x0176:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_Y
            if (r0 != r1) goto L_0x01a0
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_Y
            if (r10 != r1) goto L_0x01a0
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r9.getAnchor(r1)
            r0.connect(r1, r12, r11)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r9.getAnchor(r1)
            r0.connect(r1, r12, r11)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_Y
            goto L_0x00e9
        L_0x01a0:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r15.getAnchor(r16)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r17.getAnchor(r18)
            boolean r3 = r1.isValidConnection(r2)
            if (r3 == 0) goto L_0x0247
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BASELINE
            if (r0 != r3) goto L_0x01cc
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r15.getAnchor(r3)
            if (r0 == 0) goto L_0x01c3
            r0.reset()
        L_0x01c3:
            if (r3 == 0) goto L_0x01c8
            r3.reset()
        L_0x01c8:
            r0 = r20
            goto L_0x0239
        L_0x01cc:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            if (r0 == r3) goto L_0x0207
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            if (r0 != r3) goto L_0x01d5
            goto L_0x0207
        L_0x01d5:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            if (r0 == r3) goto L_0x01dd
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            if (r0 != r3) goto L_0x0236
        L_0x01dd:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r15.getAnchor(r3)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r3.getTarget()
            if (r4 == r2) goto L_0x01ec
            r3.reset()
        L_0x01ec:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r16)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.getOpposite()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_X
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r15.getAnchor(r3)
            boolean r4 = r3.isConnected()
            if (r4 == 0) goto L_0x0236
        L_0x0200:
            r0.reset()
            r3.reset()
            goto L_0x0236
        L_0x0207:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BASELINE
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r15.getAnchor(r3)
            if (r3 == 0) goto L_0x0212
            r3.reset()
        L_0x0212:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r15.getAnchor(r3)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r3.getTarget()
            if (r4 == r2) goto L_0x0221
            r3.reset()
        L_0x0221:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.getAnchor(r16)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.getOpposite()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_Y
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r15.getAnchor(r3)
            boolean r4 = r3.isConnected()
            if (r4 == 0) goto L_0x0236
            goto L_0x0200
        L_0x0236:
            r12 = r19
            goto L_0x01c8
        L_0x0239:
            r1.connect(r2, r12, r0, r11)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r2.getOwner()
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r1.getOwner()
            r0.connectedTo(r1)
        L_0x0247:
            return
        L_0x0248:
            r0 = move-exception
            r1 = r0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.connect(androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type, androidx.constraintlayout.solver.widgets.ConstraintWidget, androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type, int, androidx.constraintlayout.solver.widgets.ConstraintAnchor$Strength, int):void");
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2) {
        connect(constraintAnchor, constraintAnchor2, i2, ConstraintAnchor.Strength.STRONG, 0);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2, int i3) {
        connect(constraintAnchor, constraintAnchor2, i2, ConstraintAnchor.Strength.STRONG, i3);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2, ConstraintAnchor.Strength strength, int i3) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i2, strength, i3);
        }
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f2, int i2) {
        immediateConnect(ConstraintAnchor.Type.CENTER, constraintWidget, ConstraintAnchor.Type.CENTER, i2, 0);
        this.mCircleConstraintAngle = f2;
    }

    public void connectedTo(ConstraintWidget constraintWidget) {
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.p);
        linearSystem.createObjectVariable(this.q);
        linearSystem.createObjectVariable(this.r);
        linearSystem.createObjectVariable(this.s);
        if (this.J > 0) {
            linearSystem.createObjectVariable(this.t);
        }
    }

    public void disconnectUnlockedWidget(ConstraintWidget constraintWidget) {
        ArrayList<ConstraintAnchor> anchors = getAnchors();
        int size = anchors.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintAnchor constraintAnchor = anchors.get(i2);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget && constraintAnchor.getConnectionCreator() == 2) {
                constraintAnchor.reset();
            }
        }
    }

    public void disconnectWidget(ConstraintWidget constraintWidget) {
        ArrayList<ConstraintAnchor> anchors = getAnchors();
        int size = anchors.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintAnchor constraintAnchor = anchors.get(i2);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget) {
                constraintAnchor.reset();
            }
        }
    }

    public void forceUpdateDrawPosition() {
        int i2 = this.F;
        int i3 = this.G;
        this.mDrawX = i2;
        this.mDrawY = i3;
        this.mDrawWidth = (this.B + i2) - i2;
        this.mDrawHeight = (this.C + i3) - i3;
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (type) {
            case LEFT:
                return this.p;
            case TOP:
                return this.q;
            case RIGHT:
                return this.r;
            case BOTTOM:
                return this.s;
            case BASELINE:
                return this.t;
            case CENTER:
                return this.w;
            case CENTER_X:
                return this.u;
            case CENTER_Y:
                return this.v;
            case NONE:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.y;
    }

    public int getBaselineDistance() {
        return this.J;
    }

    public int getBottom() {
        return getY() + this.C;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public float getDimensionRatio() {
        return this.D;
    }

    public int getDimensionRatioSide() {
        return this.E;
    }

    public int getDrawBottom() {
        return getDrawY() + this.mDrawHeight;
    }

    public int getDrawHeight() {
        return this.mDrawHeight;
    }

    public int getDrawRight() {
        return getDrawX() + this.mDrawWidth;
    }

    public int getDrawWidth() {
        return this.mDrawWidth;
    }

    public int getDrawX() {
        return this.mDrawX + this.H;
    }

    public int getDrawY() {
        return this.mDrawY + this.I;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.C;
    }

    public float getHorizontalBiasPercent() {
        return this.M;
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor target = anchor == null ? null : anchor.getTarget();
            ConstraintWidget owner = target == null ? null : target.getOwner();
            if (owner == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor target2 = owner == null ? null : owner.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
            if (target2 == null || target2.getOwner() == constraintWidget) {
                constraintWidget = owner;
            } else {
                constraintWidget2 = constraintWidget;
            }
        }
        return constraintWidget2;
    }

    public int getHorizontalChainStyle() {
        return this.Q;
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.z[0];
    }

    public int getInternalDrawBottom() {
        return this.mDrawY + this.mDrawHeight;
    }

    public int getInternalDrawRight() {
        return this.mDrawX + this.mDrawWidth;
    }

    public int getLeft() {
        return getX();
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public int getMinHeight() {
        return this.L;
    }

    public int getMinWidth() {
        return this.K;
    }

    public int getOptimizerWrapHeight() {
        int i2;
        int i3 = this.C;
        if (this.z[1] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i3;
        }
        if (this.d == 1) {
            i2 = Math.max(this.i, i3);
        } else {
            i2 = this.i;
            if (i2 > 0) {
                this.C = i2;
            } else {
                i2 = 0;
            }
        }
        int i4 = this.j;
        return (i4 <= 0 || i4 >= i2) ? i2 : i4;
    }

    public int getOptimizerWrapWidth() {
        int i2;
        int i3 = this.B;
        if (this.z[0] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i3;
        }
        if (this.c == 1) {
            i2 = Math.max(this.f, i3);
        } else {
            i2 = this.f;
            if (i2 > 0) {
                this.B = i2;
            } else {
                i2 = 0;
            }
        }
        int i4 = this.g;
        return (i4 <= 0 || i4 >= i2) ? i2 : i4;
    }

    public ConstraintWidget getParent() {
        return this.A;
    }

    public ResolutionDimension getResolutionHeight() {
        if (this.b == null) {
            this.b = new ResolutionDimension();
        }
        return this.b;
    }

    public ResolutionDimension getResolutionWidth() {
        if (this.a == null) {
            this.a = new ResolutionDimension();
        }
        return this.a;
    }

    public int getRight() {
        return getX() + this.B;
    }

    public WidgetContainer getRootWidgetContainer() {
        ConstraintWidget constraintWidget = this;
        while (constraintWidget.getParent() != null) {
            constraintWidget = constraintWidget.getParent();
        }
        if (constraintWidget instanceof WidgetContainer) {
            return (WidgetContainer) constraintWidget;
        }
        return null;
    }

    public int getTop() {
        return getY();
    }

    public String getType() {
        return this.mType;
    }

    public float getVerticalBiasPercent() {
        return this.N;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor target = anchor == null ? null : anchor.getTarget();
            ConstraintWidget owner = target == null ? null : target.getOwner();
            if (owner == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor target2 = owner == null ? null : owner.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
            if (target2 == null || target2.getOwner() == constraintWidget) {
                constraintWidget = owner;
            } else {
                constraintWidget2 = constraintWidget;
            }
        }
        return constraintWidget2;
    }

    public int getVerticalChainStyle() {
        return this.R;
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.z[1];
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.B;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getX() {
        return this.F;
    }

    public int getY() {
        return this.G;
    }

    public boolean hasAncestor(ConstraintWidget constraintWidget) {
        ConstraintWidget parent = getParent();
        if (parent == constraintWidget) {
            return true;
        }
        if (parent == constraintWidget.getParent()) {
            return false;
        }
        while (parent != null) {
            if (parent == constraintWidget || parent == constraintWidget.getParent()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public boolean hasBaseline() {
        return this.J > 0;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2, int i3) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i2, i3, ConstraintAnchor.Strength.STRONG, 0, true);
    }

    public boolean isFullyResolved() {
        return this.p.getResolutionNode().i == 1 && this.r.getResolutionNode().i == 1 && this.q.getResolutionNode().i == 1 && this.s.getResolutionNode().i == 1;
    }

    public boolean isHeightWrapContent() {
        return this.m;
    }

    public boolean isInHorizontalChain() {
        if (this.p.c == null || this.p.c.c != this.p) {
            return this.r.c != null && this.r.c.c == this.r;
        }
        return true;
    }

    public boolean isInVerticalChain() {
        if (this.q.c == null || this.q.c.c != this.q) {
            return this.s.c != null && this.s.c.c == this.s;
        }
        return true;
    }

    public boolean isInsideConstraintLayout() {
        ConstraintWidget parent = getParent();
        if (parent == null) {
            return false;
        }
        while (parent != null) {
            if (parent instanceof ConstraintWidgetContainer) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public boolean isRoot() {
        return this.A == null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.A;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isRootContainer() {
        /*
            r1 = this;
            boolean r0 = r1 instanceof androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer
            if (r0 == 0) goto L_0x000e
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r1.A
            if (r0 == 0) goto L_0x000c
            boolean r0 = r0 instanceof androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer
            if (r0 != 0) goto L_0x000e
        L_0x000c:
            r0 = 1
            goto L_0x000f
        L_0x000e:
            r0 = 0
        L_0x000f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.isRootContainer():boolean");
    }

    public boolean isSpreadHeight() {
        return this.d == 0 && this.D == 0.0f && this.i == 0 && this.j == 0 && this.z[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadWidth() {
        return this.c == 0 && this.D == 0.0f && this.f == 0 && this.g == 0 && this.z[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isWidthWrapContent() {
        return this.l;
    }

    public void reset() {
        this.p.reset();
        this.q.reset();
        this.r.reset();
        this.s.reset();
        this.t.reset();
        this.u.reset();
        this.v.reset();
        this.w.reset();
        this.A = null;
        this.mCircleConstraintAngle = 0.0f;
        this.B = 0;
        this.C = 0;
        this.D = 0.0f;
        this.E = -1;
        this.F = 0;
        this.G = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.H = 0;
        this.I = 0;
        this.J = 0;
        this.K = 0;
        this.L = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        float f2 = DEFAULT_BIAS;
        this.M = f2;
        this.N = f2;
        this.z[0] = DimensionBehaviour.FIXED;
        this.z[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.O = false;
        this.P = false;
        this.Q = 0;
        this.R = 0;
        this.S = false;
        this.T = false;
        float[] fArr = this.U;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.c = 0;
        this.d = 0;
        this.h = 1.0f;
        this.k = 1.0f;
        this.g = Integer.MAX_VALUE;
        this.j = Integer.MAX_VALUE;
        this.f = 0;
        this.i = 0;
        this.n = -1;
        this.o = 1.0f;
        ResolutionDimension resolutionDimension = this.a;
        if (resolutionDimension != null) {
            resolutionDimension.reset();
        }
        ResolutionDimension resolutionDimension2 = this.b;
        if (resolutionDimension2 != null) {
            resolutionDimension2.reset();
        }
    }

    public void resetAllConstraints() {
        DimensionBehaviour dimensionBehaviour;
        DimensionBehaviour dimensionBehaviour2;
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
        if (!(this instanceof ConstraintWidgetContainer)) {
            if (getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (getWidth() == getWrapWidth()) {
                    dimensionBehaviour2 = DimensionBehaviour.WRAP_CONTENT;
                } else if (getWidth() > getMinWidth()) {
                    dimensionBehaviour2 = DimensionBehaviour.FIXED;
                }
                setHorizontalDimensionBehaviour(dimensionBehaviour2);
            }
            if (getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (getHeight() == getWrapHeight()) {
                    dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
                } else if (getHeight() > getMinHeight()) {
                    dimensionBehaviour = DimensionBehaviour.FIXED;
                } else {
                    return;
                }
                setVerticalDimensionBehaviour(dimensionBehaviour);
            }
        }
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() == null || !(getParent() instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
            ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
            ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.CENTER);
            ConstraintAnchor anchor6 = getAnchor(ConstraintAnchor.Type.CENTER_X);
            ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
            if (constraintAnchor == anchor5) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor.reset();
                    anchor2.reset();
                }
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.M = 0.5f;
            } else {
                if (constraintAnchor == anchor6) {
                    if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                        anchor.reset();
                        anchor2.reset();
                    }
                    this.M = 0.5f;
                } else if (constraintAnchor == anchor7) {
                    if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                        anchor3.reset();
                        anchor4.reset();
                    }
                } else if (constraintAnchor == anchor || constraintAnchor == anchor2 ? !(!anchor.isConnected() || anchor.getTarget() != anchor2.getTarget()) : (constraintAnchor == anchor3 || constraintAnchor == anchor4) && anchor3.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                    anchor5.reset();
                }
                constraintAnchor.reset();
            }
            this.N = 0.5f;
            constraintAnchor.reset();
        }
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.y.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.y.get(i2).reset();
            }
        }
    }

    public void resetAnchors(int i2) {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.y.size();
            for (int i3 = 0; i3 < size; i3++) {
                ConstraintAnchor constraintAnchor = this.y.get(i3);
                if (i2 == constraintAnchor.getConnectionCreator()) {
                    if (constraintAnchor.isVerticalAnchor()) {
                        setVerticalBiasPercent(DEFAULT_BIAS);
                    } else {
                        setHorizontalBiasPercent(DEFAULT_BIAS);
                    }
                    constraintAnchor.reset();
                }
            }
        }
    }

    public void resetResolutionNodes() {
        for (int i2 = 0; i2 < 6; i2++) {
            this.x[i2].getResolutionNode().reset();
        }
    }

    public void resetSolverVariables(Cache cache) {
        this.p.resetSolverVariable(cache);
        this.q.resetSolverVariable(cache);
        this.r.resetSolverVariable(cache);
        this.s.resetSolverVariable(cache);
        this.t.resetSolverVariable(cache);
        this.w.resetSolverVariable(cache);
        this.u.resetSolverVariable(cache);
        this.v.resetSolverVariable(cache);
    }

    public void resolve() {
    }

    public void setBaselineDistance(int i2) {
        this.J = i2;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.mContainerItemSkip = i2;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.p);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.q);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.r);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.s);
        createObjectVariable.setName(str + ".left");
        createObjectVariable2.setName(str + ".top");
        createObjectVariable3.setName(str + ".right");
        createObjectVariable4.setName(str + ".bottom");
        if (this.J > 0) {
            SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(this.t);
            createObjectVariable5.setName(str + ".baseline");
        }
    }

    public void setDimension(int i2, int i3) {
        this.B = i2;
        int i4 = this.B;
        int i5 = this.K;
        if (i4 < i5) {
            this.B = i5;
        }
        this.C = i3;
        int i6 = this.C;
        int i7 = this.L;
        if (i6 < i7) {
            this.C = i7;
        }
    }

    public void setDimensionRatio(float f2, int i2) {
        this.D = f2;
        this.E = i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDimensionRatio(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L_0x008e
            int r1 = r9.length()
            if (r1 != 0) goto L_0x000b
            goto L_0x008e
        L_0x000b:
            r1 = -1
            int r2 = r9.length()
            r3 = 44
            int r3 = r9.indexOf(r3)
            r4 = 0
            r5 = 1
            if (r3 <= 0) goto L_0x0037
            int r6 = r2 + -1
            if (r3 >= r6) goto L_0x0037
            java.lang.String r6 = r9.substring(r4, r3)
            java.lang.String r7 = "W"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x002c
            r1 = 0
            goto L_0x0035
        L_0x002c:
            java.lang.String r4 = "H"
            boolean r4 = r6.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x0035
            r1 = 1
        L_0x0035:
            int r4 = r3 + 1
        L_0x0037:
            r3 = 58
            int r3 = r9.indexOf(r3)
            if (r3 < 0) goto L_0x0075
            int r2 = r2 - r5
            if (r3 >= r2) goto L_0x0075
            java.lang.String r2 = r9.substring(r4, r3)
            int r3 = r3 + r5
            java.lang.String r9 = r9.substring(r3)
            int r3 = r2.length()
            if (r3 <= 0) goto L_0x0084
            int r3 = r9.length()
            if (r3 <= 0) goto L_0x0084
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x0084 }
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            int r3 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0084
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0084
            if (r1 != r5) goto L_0x006f
            float r9 = r9 / r2
            float r9 = java.lang.Math.abs(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x006f:
            float r2 = r2 / r9
            float r9 = java.lang.Math.abs(r2)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x0075:
            java.lang.String r9 = r9.substring(r4)
            int r2 = r9.length()
            if (r2 <= 0) goto L_0x0084
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x0084:
            r9 = 0
        L_0x0085:
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x008d
            r8.D = r9
            r8.E = r1
        L_0x008d:
            return
        L_0x008e:
            r8.D = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.setDimensionRatio(java.lang.String):void");
    }

    public void setDrawHeight(int i2) {
        this.mDrawHeight = i2;
    }

    public void setDrawOrigin(int i2, int i3) {
        this.mDrawX = i2 - this.H;
        this.mDrawY = i3 - this.I;
        this.F = this.mDrawX;
        this.G = this.mDrawY;
    }

    public void setDrawWidth(int i2) {
        this.mDrawWidth = i2;
    }

    public void setDrawX(int i2) {
        this.mDrawX = i2 - this.H;
        this.F = this.mDrawX;
    }

    public void setDrawY(int i2) {
        this.mDrawY = i2 - this.I;
        this.G = this.mDrawY;
    }

    public void setFrame(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8 = i4 - i2;
        int i9 = i5 - i3;
        this.F = i2;
        this.G = i3;
        if (this.mVisibility == 8) {
            this.B = 0;
            this.C = 0;
            return;
        }
        if (this.z[0] != DimensionBehaviour.FIXED || i8 >= (i6 = this.B)) {
            i6 = i8;
        }
        if (this.z[1] != DimensionBehaviour.FIXED || i9 >= (i7 = this.C)) {
            i7 = i9;
        }
        this.B = i6;
        this.C = i7;
        int i10 = this.C;
        int i11 = this.L;
        if (i10 < i11) {
            this.C = i11;
        }
        int i12 = this.B;
        int i13 = this.K;
        if (i12 < i13) {
            this.B = i13;
        }
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int i2) {
        ConstraintAnchor constraintAnchor;
        switch (type) {
            case LEFT:
                constraintAnchor = this.p;
                break;
            case TOP:
                constraintAnchor = this.q;
                break;
            case RIGHT:
                constraintAnchor = this.r;
                break;
            case BOTTOM:
                constraintAnchor = this.s;
                break;
            default:
                return;
        }
        constraintAnchor.d = i2;
    }

    public void setHeight(int i2) {
        this.C = i2;
        int i3 = this.C;
        int i4 = this.L;
        if (i3 < i4) {
            this.C = i4;
        }
    }

    public void setHeightWrapContent(boolean z2) {
        this.m = z2;
    }

    public void setHorizontalBiasPercent(float f2) {
        this.M = f2;
    }

    public void setHorizontalChainStyle(int i2) {
        this.Q = i2;
    }

    public void setHorizontalDimension(int i2, int i3) {
        this.F = i2;
        this.B = i3 - i2;
        int i4 = this.B;
        int i5 = this.K;
        if (i4 < i5) {
            this.B = i5;
        }
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.z[0] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setHorizontalMatchStyle(int i2, int i3, int i4, float f2) {
        this.c = i2;
        this.f = i3;
        this.g = i4;
        this.h = f2;
        if (f2 < 1.0f && this.c == 0) {
            this.c = 2;
        }
    }

    public void setHorizontalWeight(float f2) {
        this.U[0] = f2;
    }

    public void setMaxHeight(int i2) {
        this.mMaxDimension[1] = i2;
    }

    public void setMaxWidth(int i2) {
        this.mMaxDimension[0] = i2;
    }

    public void setMinHeight(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.L = i2;
    }

    public void setMinWidth(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.K = i2;
    }

    public void setOffset(int i2, int i3) {
        this.H = i2;
        this.I = i3;
    }

    public void setOrigin(int i2, int i3) {
        this.F = i2;
        this.G = i3;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.A = constraintWidget;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVerticalBiasPercent(float f2) {
        this.N = f2;
    }

    public void setVerticalChainStyle(int i2) {
        this.R = i2;
    }

    public void setVerticalDimension(int i2, int i3) {
        this.G = i2;
        this.C = i3 - i2;
        int i4 = this.C;
        int i5 = this.L;
        if (i4 < i5) {
            this.C = i5;
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.z[1] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public void setVerticalMatchStyle(int i2, int i3, int i4, float f2) {
        this.d = i2;
        this.i = i3;
        this.j = i4;
        this.k = f2;
        if (f2 < 1.0f && this.d == 0) {
            this.d = 2;
        }
    }

    public void setVerticalWeight(float f2) {
        this.U[1] = f2;
    }

    public void setVisibility(int i2) {
        this.mVisibility = i2;
    }

    public void setWidth(int i2) {
        this.B = i2;
        int i3 = this.B;
        int i4 = this.K;
        if (i3 < i4) {
            this.B = i4;
        }
    }

    public void setWidthWrapContent(boolean z2) {
        this.l = z2;
    }

    public void setWrapHeight(int i2) {
        this.mWrapHeight = i2;
    }

    public void setWrapWidth(int i2) {
        this.mWrapWidth = i2;
    }

    public void setX(int i2) {
        this.F = i2;
    }

    public void setY(int i2) {
        this.G = i2;
    }

    public void setupDimensionRatio(boolean z2, boolean z3, boolean z4, boolean z5) {
        if (this.n == -1) {
            if (z4 && !z5) {
                this.n = 0;
            } else if (!z4 && z5) {
                this.n = 1;
                if (this.E == -1) {
                    this.o = 1.0f / this.o;
                }
            }
        }
        if (this.n == 0 && (!this.q.isConnected() || !this.s.isConnected())) {
            this.n = 1;
        } else if (this.n == 1 && (!this.p.isConnected() || !this.r.isConnected())) {
            this.n = 0;
        }
        if (this.n == -1 && (!this.q.isConnected() || !this.s.isConnected() || !this.p.isConnected() || !this.r.isConnected())) {
            if (this.q.isConnected() && this.s.isConnected()) {
                this.n = 0;
            } else if (this.p.isConnected() && this.r.isConnected()) {
                this.o = 1.0f / this.o;
                this.n = 1;
            }
        }
        if (this.n == -1) {
            if (z2 && !z3) {
                this.n = 0;
            } else if (!z2 && z3) {
                this.o = 1.0f / this.o;
                this.n = 1;
            }
        }
        if (this.n == -1) {
            if (this.f > 0 && this.i == 0) {
                this.n = 0;
            } else if (this.f == 0 && this.i > 0) {
                this.o = 1.0f / this.o;
                this.n = 1;
            }
        }
        if (this.n == -1 && z2 && z3) {
            this.o = 1.0f / this.o;
            this.n = 1;
        }
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        if (this.mType != null) {
            str = "type: " + this.mType + " ";
        } else {
            str = "";
        }
        sb.append(str);
        if (this.mDebugName != null) {
            str2 = "id: " + this.mDebugName + " ";
        } else {
            str2 = "";
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.F);
        sb.append(", ");
        sb.append(this.G);
        sb.append(") - (");
        sb.append(this.B);
        sb.append(" x ");
        sb.append(this.C);
        sb.append(") wrap: (");
        sb.append(this.mWrapWidth);
        sb.append(" x ");
        sb.append(this.mWrapHeight);
        sb.append(")");
        return sb.toString();
    }

    public void updateDrawPosition() {
        int i2 = this.F;
        int i3 = this.G;
        this.mDrawX = i2;
        this.mDrawY = i3;
        this.mDrawWidth = (this.B + i2) - i2;
        this.mDrawHeight = (this.C + i3) - i3;
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        int objectVariableValue = linearSystem.getObjectVariableValue(this.p);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.q);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.r);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.s);
        int i2 = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i2 < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue4 = 0;
            objectVariableValue = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }

    public void updateResolutionNodes() {
        for (int i2 = 0; i2 < 6; i2++) {
            this.x[i2].getResolutionNode().update();
        }
    }
}
