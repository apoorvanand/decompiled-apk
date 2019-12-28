package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.constraint.R;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout extends ViewGroup {
    private static final boolean DEBUG = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-1.1.2";
    SparseArray<View> a = new SparseArray<>();
    ConstraintWidgetContainer b = new ConstraintWidgetContainer();
    int c = -1;
    int d = -1;
    int e = 0;
    int f = 0;
    private ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList<>(4);
    private ConstraintSet mConstraintSet = null;
    private int mConstraintSetId = -1;
    private HashMap<String, Integer> mDesignIds = new HashMap<>();
    private boolean mDirtyHierarchy = true;
    private int mLastMeasureHeight = -1;
    private int mLastMeasureWidth = -1;
    private int mMaxHeight = Integer.MAX_VALUE;
    private int mMaxWidth = Integer.MAX_VALUE;
    private Metrics mMetrics;
    private int mMinHeight = 0;
    private int mMinWidth = 0;
    private int mOptimizationLevel = 3;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>(100);

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int BASELINE = 5;
        public static final int BOTTOM = 4;
        public static final int CHAIN_PACKED = 2;
        public static final int CHAIN_SPREAD = 0;
        public static final int CHAIN_SPREAD_INSIDE = 1;
        public static final int END = 7;
        public static final int HORIZONTAL = 0;
        public static final int LEFT = 1;
        public static final int MATCH_CONSTRAINT = 0;
        public static final int MATCH_CONSTRAINT_PERCENT = 2;
        public static final int MATCH_CONSTRAINT_SPREAD = 0;
        public static final int MATCH_CONSTRAINT_WRAP = 1;
        public static final int PARENT_ID = 0;
        public static final int RIGHT = 2;
        public static final int START = 6;
        public static final int TOP = 3;
        public static final int UNSET = -1;
        public static final int VERTICAL = 1;
        float a = 0.0f;
        int b = 1;
        public int baselineToBaseline = -1;
        public int bottomToBottom = -1;
        public int bottomToTop = -1;
        boolean c = true;
        public float circleAngle = 0.0f;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public boolean constrainedHeight = false;
        public boolean constrainedWidth = false;
        boolean d = true;
        public String dimensionRatio = null;
        boolean e = false;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int endToEnd = -1;
        public int endToStart = -1;
        boolean f = false;
        boolean g = false;
        public int goneBottomMargin = -1;
        public int goneEndMargin = -1;
        public int goneLeftMargin = -1;
        public int goneRightMargin = -1;
        public int goneStartMargin = -1;
        public int goneTopMargin = -1;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        boolean h = false;
        public boolean helped = false;
        public float horizontalBias = 0.5f;
        public int horizontalChainStyle = 0;
        public float horizontalWeight = -1.0f;
        int i = -1;
        int j = -1;
        int k = -1;
        int l = -1;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        int m = -1;
        public int matchConstraintDefaultHeight = 0;
        public int matchConstraintDefaultWidth = 0;
        public int matchConstraintMaxHeight = 0;
        public int matchConstraintMaxWidth = 0;
        public int matchConstraintMinHeight = 0;
        public int matchConstraintMinWidth = 0;
        public float matchConstraintPercentHeight = 1.0f;
        public float matchConstraintPercentWidth = 1.0f;
        int n = -1;
        float o = 0.5f;
        public int orientation = -1;
        int p;
        int q;
        float r;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        ConstraintWidget s = new ConstraintWidget();
        public int startToEnd = -1;
        public int startToStart = -1;
        public int topToBottom = -1;
        public int topToTop = -1;
        public float verticalBias = 0.5f;
        public int verticalChainStyle = 0;
        public float verticalWeight = -1.0f;

        private static class Table {
            public static final int ANDROID_ORIENTATION = 1;
            public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
            public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
            public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
            public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
            public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
            public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
            public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
            public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
            public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
            public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
            public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
            public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
            public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
            public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
            public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
            public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
            public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
            public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
            public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
            public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
            public static final int LAYOUT_GONE_MARGIN_END = 26;
            public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
            public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
            public static final int LAYOUT_GONE_MARGIN_START = 25;
            public static final int LAYOUT_GONE_MARGIN_TOP = 22;
            public static final int UNUSED = 0;
            public static final SparseIntArray map = new SparseIntArray();

            static {
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                map.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
            }

            private Table() {
            }
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x0216, code lost:
            android.util.Log.e(r4, r6);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(android.content.Context r10, android.util.AttributeSet r11) {
            /*
                r9 = this;
                r9.<init>(r10, r11)
                r0 = -1
                r9.guideBegin = r0
                r9.guideEnd = r0
                r1 = -1082130432(0xffffffffbf800000, float:-1.0)
                r9.guidePercent = r1
                r9.leftToLeft = r0
                r9.leftToRight = r0
                r9.rightToLeft = r0
                r9.rightToRight = r0
                r9.topToTop = r0
                r9.topToBottom = r0
                r9.bottomToTop = r0
                r9.bottomToBottom = r0
                r9.baselineToBaseline = r0
                r9.circleConstraint = r0
                r2 = 0
                r9.circleRadius = r2
                r3 = 0
                r9.circleAngle = r3
                r9.startToEnd = r0
                r9.startToStart = r0
                r9.endToStart = r0
                r9.endToEnd = r0
                r9.goneLeftMargin = r0
                r9.goneTopMargin = r0
                r9.goneRightMargin = r0
                r9.goneBottomMargin = r0
                r9.goneStartMargin = r0
                r9.goneEndMargin = r0
                r4 = 1056964608(0x3f000000, float:0.5)
                r9.horizontalBias = r4
                r9.verticalBias = r4
                r5 = 0
                r9.dimensionRatio = r5
                r9.a = r3
                r5 = 1
                r9.b = r5
                r9.horizontalWeight = r1
                r9.verticalWeight = r1
                r9.horizontalChainStyle = r2
                r9.verticalChainStyle = r2
                r9.matchConstraintDefaultWidth = r2
                r9.matchConstraintDefaultHeight = r2
                r9.matchConstraintMinWidth = r2
                r9.matchConstraintMinHeight = r2
                r9.matchConstraintMaxWidth = r2
                r9.matchConstraintMaxHeight = r2
                r1 = 1065353216(0x3f800000, float:1.0)
                r9.matchConstraintPercentWidth = r1
                r9.matchConstraintPercentHeight = r1
                r9.editorAbsoluteX = r0
                r9.editorAbsoluteY = r0
                r9.orientation = r0
                r9.constrainedWidth = r2
                r9.constrainedHeight = r2
                r9.c = r5
                r9.d = r5
                r9.e = r2
                r9.f = r2
                r9.g = r2
                r9.h = r2
                r9.i = r0
                r9.j = r0
                r9.k = r0
                r9.l = r0
                r9.m = r0
                r9.n = r0
                r9.o = r4
                androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = new androidx.constraintlayout.solver.widgets.ConstraintWidget
                r1.<init>()
                r9.s = r1
                r9.helped = r2
                int[] r1 = android.support.constraint.R.styleable.ConstraintLayout_Layout
                android.content.res.TypedArray r10 = r10.obtainStyledAttributes(r11, r1)
                int r11 = r10.getIndexCount()
                r1 = 0
            L_0x009a:
                if (r1 >= r11) goto L_0x03db
                int r4 = r10.getIndex(r1)
                android.util.SparseIntArray r6 = androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.Table.map
                int r6 = r6.get(r4)
                r7 = -2
                switch(r6) {
                    case 0: goto L_0x03d7;
                    case 1: goto L_0x03cf;
                    case 2: goto L_0x03bc;
                    case 3: goto L_0x03b3;
                    case 4: goto L_0x039c;
                    case 5: goto L_0x0393;
                    case 6: goto L_0x038a;
                    case 7: goto L_0x0381;
                    case 8: goto L_0x036e;
                    case 9: goto L_0x035b;
                    case 10: goto L_0x0347;
                    case 11: goto L_0x0333;
                    case 12: goto L_0x031f;
                    case 13: goto L_0x030b;
                    case 14: goto L_0x02f7;
                    case 15: goto L_0x02e3;
                    case 16: goto L_0x02cf;
                    case 17: goto L_0x02bb;
                    case 18: goto L_0x02a7;
                    case 19: goto L_0x0293;
                    case 20: goto L_0x027f;
                    case 21: goto L_0x0275;
                    case 22: goto L_0x026b;
                    case 23: goto L_0x0261;
                    case 24: goto L_0x0257;
                    case 25: goto L_0x024d;
                    case 26: goto L_0x0243;
                    case 27: goto L_0x0239;
                    case 28: goto L_0x022f;
                    case 29: goto L_0x0225;
                    case 30: goto L_0x021b;
                    case 31: goto L_0x0208;
                    case 32: goto L_0x01f9;
                    case 33: goto L_0x01e2;
                    case 34: goto L_0x01cb;
                    case 35: goto L_0x01bd;
                    case 36: goto L_0x01a6;
                    case 37: goto L_0x018f;
                    case 38: goto L_0x0181;
                    case 39: goto L_0x03d7;
                    case 40: goto L_0x03d7;
                    case 41: goto L_0x03d7;
                    case 42: goto L_0x03d7;
                    case 43: goto L_0x00aa;
                    case 44: goto L_0x00e4;
                    case 45: goto L_0x00da;
                    case 46: goto L_0x00d0;
                    case 47: goto L_0x00c8;
                    case 48: goto L_0x00c0;
                    case 49: goto L_0x00b6;
                    case 50: goto L_0x00ac;
                    default: goto L_0x00aa;
                }
            L_0x00aa:
                goto L_0x03d7
            L_0x00ac:
                int r6 = r9.editorAbsoluteY
                int r4 = r10.getDimensionPixelOffset(r4, r6)
                r9.editorAbsoluteY = r4
                goto L_0x03d7
            L_0x00b6:
                int r6 = r9.editorAbsoluteX
                int r4 = r10.getDimensionPixelOffset(r4, r6)
                r9.editorAbsoluteX = r4
                goto L_0x03d7
            L_0x00c0:
                int r4 = r10.getInt(r4, r2)
                r9.verticalChainStyle = r4
                goto L_0x03d7
            L_0x00c8:
                int r4 = r10.getInt(r4, r2)
                r9.horizontalChainStyle = r4
                goto L_0x03d7
            L_0x00d0:
                float r6 = r9.verticalWeight
                float r4 = r10.getFloat(r4, r6)
                r9.verticalWeight = r4
                goto L_0x03d7
            L_0x00da:
                float r6 = r9.horizontalWeight
                float r4 = r10.getFloat(r4, r6)
                r9.horizontalWeight = r4
                goto L_0x03d7
            L_0x00e4:
                java.lang.String r4 = r10.getString(r4)
                r9.dimensionRatio = r4
                r4 = 2143289344(0x7fc00000, float:NaN)
                r9.a = r4
                r9.b = r0
                java.lang.String r4 = r9.dimensionRatio
                if (r4 == 0) goto L_0x03d7
                int r4 = r4.length()
                java.lang.String r6 = r9.dimensionRatio
                r7 = 44
                int r6 = r6.indexOf(r7)
                if (r6 <= 0) goto L_0x0124
                int r7 = r4 + -1
                if (r6 >= r7) goto L_0x0124
                java.lang.String r7 = r9.dimensionRatio
                java.lang.String r7 = r7.substring(r2, r6)
                java.lang.String r8 = "W"
                boolean r8 = r7.equalsIgnoreCase(r8)
                if (r8 == 0) goto L_0x0117
                r9.b = r2
                goto L_0x0121
            L_0x0117:
                java.lang.String r8 = "H"
                boolean r7 = r7.equalsIgnoreCase(r8)
                if (r7 == 0) goto L_0x0121
                r9.b = r5
            L_0x0121:
                int r6 = r6 + 1
                goto L_0x0125
            L_0x0124:
                r6 = 0
            L_0x0125:
                java.lang.String r7 = r9.dimensionRatio
                r8 = 58
                int r7 = r7.indexOf(r8)
                if (r7 < 0) goto L_0x0170
                int r4 = r4 + -1
                if (r7 >= r4) goto L_0x0170
                java.lang.String r4 = r9.dimensionRatio
                java.lang.String r4 = r4.substring(r6, r7)
                java.lang.String r6 = r9.dimensionRatio
                int r7 = r7 + 1
                java.lang.String r6 = r6.substring(r7)
                int r7 = r4.length()
                if (r7 <= 0) goto L_0x03d7
                int r7 = r6.length()
                if (r7 <= 0) goto L_0x03d7
                float r4 = java.lang.Float.parseFloat(r4)     // Catch:{ NumberFormatException -> 0x03d7 }
                float r6 = java.lang.Float.parseFloat(r6)     // Catch:{ NumberFormatException -> 0x03d7 }
                int r7 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
                if (r7 <= 0) goto L_0x03d7
                int r7 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
                if (r7 <= 0) goto L_0x03d7
                int r7 = r9.b     // Catch:{ NumberFormatException -> 0x03d7 }
                if (r7 != r5) goto L_0x016a
                float r6 = r6 / r4
                float r4 = java.lang.Math.abs(r6)     // Catch:{ NumberFormatException -> 0x03d7 }
            L_0x0166:
                r9.a = r4     // Catch:{ NumberFormatException -> 0x03d7 }
                goto L_0x03d7
            L_0x016a:
                float r4 = r4 / r6
                float r4 = java.lang.Math.abs(r4)     // Catch:{ NumberFormatException -> 0x03d7 }
                goto L_0x0166
            L_0x0170:
                java.lang.String r4 = r9.dimensionRatio
                java.lang.String r4 = r4.substring(r6)
                int r6 = r4.length()
                if (r6 <= 0) goto L_0x03d7
                float r4 = java.lang.Float.parseFloat(r4)     // Catch:{ NumberFormatException -> 0x03d7 }
                goto L_0x0166
            L_0x0181:
                float r6 = r9.matchConstraintPercentHeight
                float r4 = r10.getFloat(r4, r6)
                float r4 = java.lang.Math.max(r3, r4)
                r9.matchConstraintPercentHeight = r4
                goto L_0x03d7
            L_0x018f:
                int r6 = r9.matchConstraintMaxHeight     // Catch:{ Exception -> 0x0199 }
                int r6 = r10.getDimensionPixelSize(r4, r6)     // Catch:{ Exception -> 0x0199 }
                r9.matchConstraintMaxHeight = r6     // Catch:{ Exception -> 0x0199 }
                goto L_0x03d7
            L_0x0199:
                int r6 = r9.matchConstraintMaxHeight
                int r4 = r10.getInt(r4, r6)
                if (r4 != r7) goto L_0x03d7
                r9.matchConstraintMaxHeight = r7
                goto L_0x03d7
            L_0x01a6:
                int r6 = r9.matchConstraintMinHeight     // Catch:{ Exception -> 0x01b0 }
                int r6 = r10.getDimensionPixelSize(r4, r6)     // Catch:{ Exception -> 0x01b0 }
                r9.matchConstraintMinHeight = r6     // Catch:{ Exception -> 0x01b0 }
                goto L_0x03d7
            L_0x01b0:
                int r6 = r9.matchConstraintMinHeight
                int r4 = r10.getInt(r4, r6)
                if (r4 != r7) goto L_0x03d7
                r9.matchConstraintMinHeight = r7
                goto L_0x03d7
            L_0x01bd:
                float r6 = r9.matchConstraintPercentWidth
                float r4 = r10.getFloat(r4, r6)
                float r4 = java.lang.Math.max(r3, r4)
                r9.matchConstraintPercentWidth = r4
                goto L_0x03d7
            L_0x01cb:
                int r6 = r9.matchConstraintMaxWidth     // Catch:{ Exception -> 0x01d5 }
                int r6 = r10.getDimensionPixelSize(r4, r6)     // Catch:{ Exception -> 0x01d5 }
                r9.matchConstraintMaxWidth = r6     // Catch:{ Exception -> 0x01d5 }
                goto L_0x03d7
            L_0x01d5:
                int r6 = r9.matchConstraintMaxWidth
                int r4 = r10.getInt(r4, r6)
                if (r4 != r7) goto L_0x03d7
                r9.matchConstraintMaxWidth = r7
                goto L_0x03d7
            L_0x01e2:
                int r6 = r9.matchConstraintMinWidth     // Catch:{ Exception -> 0x01ec }
                int r6 = r10.getDimensionPixelSize(r4, r6)     // Catch:{ Exception -> 0x01ec }
                r9.matchConstraintMinWidth = r6     // Catch:{ Exception -> 0x01ec }
                goto L_0x03d7
            L_0x01ec:
                int r6 = r9.matchConstraintMinWidth
                int r4 = r10.getInt(r4, r6)
                if (r4 != r7) goto L_0x03d7
                r9.matchConstraintMinWidth = r7
                goto L_0x03d7
            L_0x01f9:
                int r4 = r10.getInt(r4, r2)
                r9.matchConstraintDefaultHeight = r4
                int r4 = r9.matchConstraintDefaultHeight
                if (r4 != r5) goto L_0x03d7
                java.lang.String r4 = "ConstraintLayout"
                java.lang.String r6 = "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead."
                goto L_0x0216
            L_0x0208:
                int r4 = r10.getInt(r4, r2)
                r9.matchConstraintDefaultWidth = r4
                int r4 = r9.matchConstraintDefaultWidth
                if (r4 != r5) goto L_0x03d7
                java.lang.String r4 = "ConstraintLayout"
                java.lang.String r6 = "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead."
            L_0x0216:
                android.util.Log.e(r4, r6)
                goto L_0x03d7
            L_0x021b:
                float r6 = r9.verticalBias
                float r4 = r10.getFloat(r4, r6)
                r9.verticalBias = r4
                goto L_0x03d7
            L_0x0225:
                float r6 = r9.horizontalBias
                float r4 = r10.getFloat(r4, r6)
                r9.horizontalBias = r4
                goto L_0x03d7
            L_0x022f:
                boolean r6 = r9.constrainedHeight
                boolean r4 = r10.getBoolean(r4, r6)
                r9.constrainedHeight = r4
                goto L_0x03d7
            L_0x0239:
                boolean r6 = r9.constrainedWidth
                boolean r4 = r10.getBoolean(r4, r6)
                r9.constrainedWidth = r4
                goto L_0x03d7
            L_0x0243:
                int r6 = r9.goneEndMargin
                int r4 = r10.getDimensionPixelSize(r4, r6)
                r9.goneEndMargin = r4
                goto L_0x03d7
            L_0x024d:
                int r6 = r9.goneStartMargin
                int r4 = r10.getDimensionPixelSize(r4, r6)
                r9.goneStartMargin = r4
                goto L_0x03d7
            L_0x0257:
                int r6 = r9.goneBottomMargin
                int r4 = r10.getDimensionPixelSize(r4, r6)
                r9.goneBottomMargin = r4
                goto L_0x03d7
            L_0x0261:
                int r6 = r9.goneRightMargin
                int r4 = r10.getDimensionPixelSize(r4, r6)
                r9.goneRightMargin = r4
                goto L_0x03d7
            L_0x026b:
                int r6 = r9.goneTopMargin
                int r4 = r10.getDimensionPixelSize(r4, r6)
                r9.goneTopMargin = r4
                goto L_0x03d7
            L_0x0275:
                int r6 = r9.goneLeftMargin
                int r4 = r10.getDimensionPixelSize(r4, r6)
                r9.goneLeftMargin = r4
                goto L_0x03d7
            L_0x027f:
                int r6 = r9.endToEnd
                int r6 = r10.getResourceId(r4, r6)
                r9.endToEnd = r6
                int r6 = r9.endToEnd
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.endToEnd = r4
                goto L_0x03d7
            L_0x0293:
                int r6 = r9.endToStart
                int r6 = r10.getResourceId(r4, r6)
                r9.endToStart = r6
                int r6 = r9.endToStart
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.endToStart = r4
                goto L_0x03d7
            L_0x02a7:
                int r6 = r9.startToStart
                int r6 = r10.getResourceId(r4, r6)
                r9.startToStart = r6
                int r6 = r9.startToStart
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.startToStart = r4
                goto L_0x03d7
            L_0x02bb:
                int r6 = r9.startToEnd
                int r6 = r10.getResourceId(r4, r6)
                r9.startToEnd = r6
                int r6 = r9.startToEnd
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.startToEnd = r4
                goto L_0x03d7
            L_0x02cf:
                int r6 = r9.baselineToBaseline
                int r6 = r10.getResourceId(r4, r6)
                r9.baselineToBaseline = r6
                int r6 = r9.baselineToBaseline
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.baselineToBaseline = r4
                goto L_0x03d7
            L_0x02e3:
                int r6 = r9.bottomToBottom
                int r6 = r10.getResourceId(r4, r6)
                r9.bottomToBottom = r6
                int r6 = r9.bottomToBottom
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.bottomToBottom = r4
                goto L_0x03d7
            L_0x02f7:
                int r6 = r9.bottomToTop
                int r6 = r10.getResourceId(r4, r6)
                r9.bottomToTop = r6
                int r6 = r9.bottomToTop
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.bottomToTop = r4
                goto L_0x03d7
            L_0x030b:
                int r6 = r9.topToBottom
                int r6 = r10.getResourceId(r4, r6)
                r9.topToBottom = r6
                int r6 = r9.topToBottom
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.topToBottom = r4
                goto L_0x03d7
            L_0x031f:
                int r6 = r9.topToTop
                int r6 = r10.getResourceId(r4, r6)
                r9.topToTop = r6
                int r6 = r9.topToTop
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.topToTop = r4
                goto L_0x03d7
            L_0x0333:
                int r6 = r9.rightToRight
                int r6 = r10.getResourceId(r4, r6)
                r9.rightToRight = r6
                int r6 = r9.rightToRight
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.rightToRight = r4
                goto L_0x03d7
            L_0x0347:
                int r6 = r9.rightToLeft
                int r6 = r10.getResourceId(r4, r6)
                r9.rightToLeft = r6
                int r6 = r9.rightToLeft
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.rightToLeft = r4
                goto L_0x03d7
            L_0x035b:
                int r6 = r9.leftToRight
                int r6 = r10.getResourceId(r4, r6)
                r9.leftToRight = r6
                int r6 = r9.leftToRight
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.leftToRight = r4
                goto L_0x03d7
            L_0x036e:
                int r6 = r9.leftToLeft
                int r6 = r10.getResourceId(r4, r6)
                r9.leftToLeft = r6
                int r6 = r9.leftToLeft
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.leftToLeft = r4
                goto L_0x03d7
            L_0x0381:
                float r6 = r9.guidePercent
                float r4 = r10.getFloat(r4, r6)
                r9.guidePercent = r4
                goto L_0x03d7
            L_0x038a:
                int r6 = r9.guideEnd
                int r4 = r10.getDimensionPixelOffset(r4, r6)
                r9.guideEnd = r4
                goto L_0x03d7
            L_0x0393:
                int r6 = r9.guideBegin
                int r4 = r10.getDimensionPixelOffset(r4, r6)
                r9.guideBegin = r4
                goto L_0x03d7
            L_0x039c:
                float r6 = r9.circleAngle
                float r4 = r10.getFloat(r4, r6)
                r6 = 1135869952(0x43b40000, float:360.0)
                float r4 = r4 % r6
                r9.circleAngle = r4
                float r4 = r9.circleAngle
                int r7 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
                if (r7 >= 0) goto L_0x03d7
                float r4 = r6 - r4
                float r4 = r4 % r6
                r9.circleAngle = r4
                goto L_0x03d7
            L_0x03b3:
                int r6 = r9.circleRadius
                int r4 = r10.getDimensionPixelSize(r4, r6)
                r9.circleRadius = r4
                goto L_0x03d7
            L_0x03bc:
                int r6 = r9.circleConstraint
                int r6 = r10.getResourceId(r4, r6)
                r9.circleConstraint = r6
                int r6 = r9.circleConstraint
                if (r6 != r0) goto L_0x03d7
                int r4 = r10.getInt(r4, r0)
                r9.circleConstraint = r4
                goto L_0x03d7
            L_0x03cf:
                int r6 = r9.orientation
                int r4 = r10.getInt(r4, r6)
                r9.orientation = r4
            L_0x03d7:
                int r1 = r1 + 1
                goto L_0x009a
            L_0x03db:
                r10.recycle()
                r9.validate()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.guideBegin = layoutParams.guideBegin;
            this.guideEnd = layoutParams.guideEnd;
            this.guidePercent = layoutParams.guidePercent;
            this.leftToLeft = layoutParams.leftToLeft;
            this.leftToRight = layoutParams.leftToRight;
            this.rightToLeft = layoutParams.rightToLeft;
            this.rightToRight = layoutParams.rightToRight;
            this.topToTop = layoutParams.topToTop;
            this.topToBottom = layoutParams.topToBottom;
            this.bottomToTop = layoutParams.bottomToTop;
            this.bottomToBottom = layoutParams.bottomToBottom;
            this.baselineToBaseline = layoutParams.baselineToBaseline;
            this.circleConstraint = layoutParams.circleConstraint;
            this.circleRadius = layoutParams.circleRadius;
            this.circleAngle = layoutParams.circleAngle;
            this.startToEnd = layoutParams.startToEnd;
            this.startToStart = layoutParams.startToStart;
            this.endToStart = layoutParams.endToStart;
            this.endToEnd = layoutParams.endToEnd;
            this.goneLeftMargin = layoutParams.goneLeftMargin;
            this.goneTopMargin = layoutParams.goneTopMargin;
            this.goneRightMargin = layoutParams.goneRightMargin;
            this.goneBottomMargin = layoutParams.goneBottomMargin;
            this.goneStartMargin = layoutParams.goneStartMargin;
            this.goneEndMargin = layoutParams.goneEndMargin;
            this.horizontalBias = layoutParams.horizontalBias;
            this.verticalBias = layoutParams.verticalBias;
            this.dimensionRatio = layoutParams.dimensionRatio;
            this.a = layoutParams.a;
            this.b = layoutParams.b;
            this.horizontalWeight = layoutParams.horizontalWeight;
            this.verticalWeight = layoutParams.verticalWeight;
            this.horizontalChainStyle = layoutParams.horizontalChainStyle;
            this.verticalChainStyle = layoutParams.verticalChainStyle;
            this.constrainedWidth = layoutParams.constrainedWidth;
            this.constrainedHeight = layoutParams.constrainedHeight;
            this.matchConstraintDefaultWidth = layoutParams.matchConstraintDefaultWidth;
            this.matchConstraintDefaultHeight = layoutParams.matchConstraintDefaultHeight;
            this.matchConstraintMinWidth = layoutParams.matchConstraintMinWidth;
            this.matchConstraintMaxWidth = layoutParams.matchConstraintMaxWidth;
            this.matchConstraintMinHeight = layoutParams.matchConstraintMinHeight;
            this.matchConstraintMaxHeight = layoutParams.matchConstraintMaxHeight;
            this.matchConstraintPercentWidth = layoutParams.matchConstraintPercentWidth;
            this.matchConstraintPercentHeight = layoutParams.matchConstraintPercentHeight;
            this.editorAbsoluteX = layoutParams.editorAbsoluteX;
            this.editorAbsoluteY = layoutParams.editorAbsoluteY;
            this.orientation = layoutParams.orientation;
            this.c = layoutParams.c;
            this.d = layoutParams.d;
            this.e = layoutParams.e;
            this.f = layoutParams.f;
            this.i = layoutParams.i;
            this.j = layoutParams.j;
            this.k = layoutParams.k;
            this.l = layoutParams.l;
            this.m = layoutParams.m;
            this.n = layoutParams.n;
            this.o = layoutParams.o;
            this.s = layoutParams.s;
        }

        public void reset() {
            ConstraintWidget constraintWidget = this.s;
            if (constraintWidget != null) {
                constraintWidget.reset();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:71:0x00d6, code lost:
            if (r1 > 0) goto L_0x00d8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:77:0x00e5, code lost:
            if (r1 > 0) goto L_0x00d8;
         */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x004c  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0053  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x005a  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x0066  */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x007c  */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x0084  */
        /* JADX WARNING: Removed duplicated region for block: B:80:0x00ec  */
        /* JADX WARNING: Removed duplicated region for block: B:84:0x00f7  */
        @android.annotation.TargetApi(17)
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void resolveLayoutDirection(int r7) {
            /*
                r6 = this;
                int r0 = r6.leftMargin
                int r1 = r6.rightMargin
                super.resolveLayoutDirection(r7)
                r7 = -1
                r6.k = r7
                r6.l = r7
                r6.i = r7
                r6.j = r7
                r6.m = r7
                r6.n = r7
                int r2 = r6.goneLeftMargin
                r6.m = r2
                int r2 = r6.goneRightMargin
                r6.n = r2
                float r2 = r6.horizontalBias
                r6.o = r2
                int r2 = r6.guideBegin
                r6.p = r2
                int r2 = r6.guideEnd
                r6.q = r2
                float r2 = r6.guidePercent
                r6.r = r2
                int r2 = r6.getLayoutDirection()
                r3 = 0
                r4 = 1
                if (r4 != r2) goto L_0x0036
                r2 = 1
                goto L_0x0037
            L_0x0036:
                r2 = 0
            L_0x0037:
                if (r2 == 0) goto L_0x0098
                int r2 = r6.startToEnd
                if (r2 == r7) goto L_0x0041
                r6.k = r2
            L_0x003f:
                r3 = 1
                goto L_0x0048
            L_0x0041:
                int r2 = r6.startToStart
                if (r2 == r7) goto L_0x0048
                r6.l = r2
                goto L_0x003f
            L_0x0048:
                int r2 = r6.endToStart
                if (r2 == r7) goto L_0x004f
                r6.j = r2
                r3 = 1
            L_0x004f:
                int r2 = r6.endToEnd
                if (r2 == r7) goto L_0x0056
                r6.i = r2
                r3 = 1
            L_0x0056:
                int r2 = r6.goneStartMargin
                if (r2 == r7) goto L_0x005c
                r6.n = r2
            L_0x005c:
                int r2 = r6.goneEndMargin
                if (r2 == r7) goto L_0x0062
                r6.m = r2
            L_0x0062:
                r2 = 1065353216(0x3f800000, float:1.0)
                if (r3 == 0) goto L_0x006c
                float r3 = r6.horizontalBias
                float r3 = r2 - r3
                r6.o = r3
            L_0x006c:
                boolean r3 = r6.f
                if (r3 == 0) goto L_0x00bc
                int r3 = r6.orientation
                if (r3 != r4) goto L_0x00bc
                float r3 = r6.guidePercent
                r4 = -1082130432(0xffffffffbf800000, float:-1.0)
                int r5 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
                if (r5 == 0) goto L_0x0084
                float r2 = r2 - r3
                r6.r = r2
                r6.p = r7
                r6.q = r7
                goto L_0x00bc
            L_0x0084:
                int r2 = r6.guideBegin
                if (r2 == r7) goto L_0x008f
                r6.q = r2
                r6.p = r7
            L_0x008c:
                r6.r = r4
                goto L_0x00bc
            L_0x008f:
                int r2 = r6.guideEnd
                if (r2 == r7) goto L_0x00bc
                r6.p = r2
                r6.q = r7
                goto L_0x008c
            L_0x0098:
                int r2 = r6.startToEnd
                if (r2 == r7) goto L_0x009e
                r6.j = r2
            L_0x009e:
                int r2 = r6.startToStart
                if (r2 == r7) goto L_0x00a4
                r6.i = r2
            L_0x00a4:
                int r2 = r6.endToStart
                if (r2 == r7) goto L_0x00aa
                r6.k = r2
            L_0x00aa:
                int r2 = r6.endToEnd
                if (r2 == r7) goto L_0x00b0
                r6.l = r2
            L_0x00b0:
                int r2 = r6.goneStartMargin
                if (r2 == r7) goto L_0x00b6
                r6.m = r2
            L_0x00b6:
                int r2 = r6.goneEndMargin
                if (r2 == r7) goto L_0x00bc
                r6.n = r2
            L_0x00bc:
                int r2 = r6.endToStart
                if (r2 != r7) goto L_0x0104
                int r2 = r6.endToEnd
                if (r2 != r7) goto L_0x0104
                int r2 = r6.startToStart
                if (r2 != r7) goto L_0x0104
                int r2 = r6.startToEnd
                if (r2 != r7) goto L_0x0104
                int r2 = r6.rightToLeft
                if (r2 == r7) goto L_0x00db
                r6.k = r2
                int r2 = r6.rightMargin
                if (r2 > 0) goto L_0x00e8
                if (r1 <= 0) goto L_0x00e8
            L_0x00d8:
                r6.rightMargin = r1
                goto L_0x00e8
            L_0x00db:
                int r2 = r6.rightToRight
                if (r2 == r7) goto L_0x00e8
                r6.l = r2
                int r2 = r6.rightMargin
                if (r2 > 0) goto L_0x00e8
                if (r1 <= 0) goto L_0x00e8
                goto L_0x00d8
            L_0x00e8:
                int r1 = r6.leftToLeft
                if (r1 == r7) goto L_0x00f7
                r6.i = r1
                int r7 = r6.leftMargin
                if (r7 > 0) goto L_0x0104
                if (r0 <= 0) goto L_0x0104
            L_0x00f4:
                r6.leftMargin = r0
                goto L_0x0104
            L_0x00f7:
                int r1 = r6.leftToRight
                if (r1 == r7) goto L_0x0104
                r6.j = r1
                int r7 = r6.leftMargin
                if (r7 > 0) goto L_0x0104
                if (r0 <= 0) goto L_0x0104
                goto L_0x00f4
            L_0x0104:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.resolveLayoutDirection(int):void");
        }

        public void validate() {
            this.f = false;
            this.c = true;
            this.d = true;
            if (this.width == -2 && this.constrainedWidth) {
                this.c = false;
                this.matchConstraintDefaultWidth = 1;
            }
            if (this.height == -2 && this.constrainedHeight) {
                this.d = false;
                this.matchConstraintDefaultHeight = 1;
            }
            if (this.width == 0 || this.width == -1) {
                this.c = false;
                if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
                    this.width = -2;
                    this.constrainedWidth = true;
                }
            }
            if (this.height == 0 || this.height == -1) {
                this.d = false;
                if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
                    this.height = -2;
                    this.constrainedHeight = true;
                }
            }
            if (this.guidePercent != -1.0f || this.guideBegin != -1 || this.guideEnd != -1) {
                this.f = true;
                this.c = true;
                this.d = true;
                if (!(this.s instanceof Guideline)) {
                    this.s = new Guideline();
                }
                ((Guideline) this.s).setOrientation(this.orientation);
            }
        }
    }

    public ConstraintLayout(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private final ConstraintWidget getTargetWidget(int i) {
        if (i == 0) {
            return this.b;
        }
        View view = this.a.get(i);
        if (view == this) {
            return this.b;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).s;
    }

    private void init(AttributeSet attributeSet) {
        this.b.setCompanionWidget(this);
        this.a.put(getId(), this);
        this.mConstraintSet = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinWidth);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinHeight);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxWidth);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxHeight);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = obtainStyledAttributes.getInt(index, this.mOptimizationLevel);
                } else if (index == R.styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    try {
                        this.mConstraintSet = new ConstraintSet();
                        this.mConstraintSet.load(getContext(), resourceId);
                    } catch (Resources.NotFoundException unused) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = resourceId;
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.b.setOptimizationLevel(this.mOptimizationLevel);
    }

    private void internalMeasureChildren(int i, int i2) {
        boolean z;
        boolean z2;
        int baseline;
        int i3;
        int i4;
        int i5 = i;
        int i6 = i2;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                ConstraintWidget constraintWidget = layoutParams.s;
                if (!layoutParams.f && !layoutParams.g) {
                    constraintWidget.setVisibility(childAt.getVisibility());
                    int i8 = layoutParams.width;
                    int i9 = layoutParams.height;
                    if (layoutParams.c || layoutParams.d || (!layoutParams.c && layoutParams.matchConstraintDefaultWidth == 1) || layoutParams.width == -1 || (!layoutParams.d && (layoutParams.matchConstraintDefaultHeight == 1 || layoutParams.height == -1))) {
                        if (i8 == 0) {
                            i3 = getChildMeasureSpec(i5, paddingLeft, -2);
                            z2 = true;
                        } else if (i8 == -1) {
                            i3 = getChildMeasureSpec(i5, paddingLeft, -1);
                            z2 = false;
                        } else {
                            z2 = i8 == -2;
                            i3 = getChildMeasureSpec(i5, paddingLeft, i8);
                        }
                        if (i9 == 0) {
                            i4 = getChildMeasureSpec(i6, paddingTop, -2);
                            z = true;
                        } else if (i9 == -1) {
                            i4 = getChildMeasureSpec(i6, paddingTop, -1);
                            z = false;
                        } else {
                            z = i9 == -2;
                            i4 = getChildMeasureSpec(i6, paddingTop, i9);
                        }
                        childAt.measure(i3, i4);
                        Metrics metrics = this.mMetrics;
                        if (metrics != null) {
                            metrics.measures++;
                        }
                        constraintWidget.setWidthWrapContent(i8 == -2);
                        constraintWidget.setHeightWrapContent(i9 == -2);
                        i8 = childAt.getMeasuredWidth();
                        i9 = childAt.getMeasuredHeight();
                    } else {
                        z2 = false;
                        z = false;
                    }
                    constraintWidget.setWidth(i8);
                    constraintWidget.setHeight(i9);
                    if (z2) {
                        constraintWidget.setWrapWidth(i8);
                    }
                    if (z) {
                        constraintWidget.setWrapHeight(i9);
                    }
                    if (layoutParams.e && (baseline = childAt.getBaseline()) != -1) {
                        constraintWidget.setBaselineDistance(baseline);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:109:0x0206  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0240  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0265  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x026e  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0273  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0275  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x027b  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x027d  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0291  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0296  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x029b  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x02a3  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x02ac  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02b4  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x02c1  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void internalMeasureDimensions(int r24, int r25) {
        /*
            r23 = this;
            r0 = r23
            r1 = r24
            r2 = r25
            int r3 = r23.getPaddingTop()
            int r4 = r23.getPaddingBottom()
            int r3 = r3 + r4
            int r4 = r23.getPaddingLeft()
            int r5 = r23.getPaddingRight()
            int r4 = r4 + r5
            int r5 = r23.getChildCount()
            r7 = 0
        L_0x001d:
            r8 = 1
            r10 = 8
            r12 = -2
            if (r7 >= r5) goto L_0x00de
            android.view.View r14 = r0.getChildAt(r7)
            int r15 = r14.getVisibility()
            if (r15 != r10) goto L_0x0030
            goto L_0x00d6
        L_0x0030:
            android.view.ViewGroup$LayoutParams r10 = r14.getLayoutParams()
            androidx.constraintlayout.widget.ConstraintLayout$LayoutParams r10 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r10
            androidx.constraintlayout.solver.widgets.ConstraintWidget r15 = r10.s
            boolean r6 = r10.f
            if (r6 != 0) goto L_0x00d6
            boolean r6 = r10.g
            if (r6 == 0) goto L_0x0042
            goto L_0x00d6
        L_0x0042:
            int r6 = r14.getVisibility()
            r15.setVisibility(r6)
            int r6 = r10.width
            int r13 = r10.height
            if (r6 == 0) goto L_0x00c6
            if (r13 != 0) goto L_0x0053
            goto L_0x00c6
        L_0x0053:
            if (r6 != r12) goto L_0x0058
            r16 = 1
            goto L_0x005a
        L_0x0058:
            r16 = 0
        L_0x005a:
            int r11 = getChildMeasureSpec(r1, r4, r6)
            if (r13 != r12) goto L_0x0063
            r17 = 1
            goto L_0x0065
        L_0x0063:
            r17 = 0
        L_0x0065:
            int r12 = getChildMeasureSpec(r2, r3, r13)
            r14.measure(r11, r12)
            androidx.constraintlayout.solver.Metrics r11 = r0.mMetrics
            if (r11 == 0) goto L_0x0077
            r12 = r3
            long r2 = r11.measures
            long r2 = r2 + r8
            r11.measures = r2
            goto L_0x0078
        L_0x0077:
            r12 = r3
        L_0x0078:
            r2 = -2
            if (r6 != r2) goto L_0x007d
            r3 = 1
            goto L_0x007e
        L_0x007d:
            r3 = 0
        L_0x007e:
            r15.setWidthWrapContent(r3)
            if (r13 != r2) goto L_0x0085
            r2 = 1
            goto L_0x0086
        L_0x0085:
            r2 = 0
        L_0x0086:
            r15.setHeightWrapContent(r2)
            int r2 = r14.getMeasuredWidth()
            int r3 = r14.getMeasuredHeight()
            r15.setWidth(r2)
            r15.setHeight(r3)
            if (r16 == 0) goto L_0x009c
            r15.setWrapWidth(r2)
        L_0x009c:
            if (r17 == 0) goto L_0x00a1
            r15.setWrapHeight(r3)
        L_0x00a1:
            boolean r6 = r10.e
            if (r6 == 0) goto L_0x00af
            int r6 = r14.getBaseline()
            r8 = -1
            if (r6 == r8) goto L_0x00af
            r15.setBaselineDistance(r6)
        L_0x00af:
            boolean r6 = r10.c
            if (r6 == 0) goto L_0x00d7
            boolean r6 = r10.d
            if (r6 == 0) goto L_0x00d7
            androidx.constraintlayout.solver.widgets.ResolutionDimension r6 = r15.getResolutionWidth()
            r6.resolve(r2)
            androidx.constraintlayout.solver.widgets.ResolutionDimension r2 = r15.getResolutionHeight()
            r2.resolve(r3)
            goto L_0x00d7
        L_0x00c6:
            r12 = r3
            androidx.constraintlayout.solver.widgets.ResolutionDimension r2 = r15.getResolutionWidth()
            r2.invalidate()
            androidx.constraintlayout.solver.widgets.ResolutionDimension r2 = r15.getResolutionHeight()
            r2.invalidate()
            goto L_0x00d7
        L_0x00d6:
            r12 = r3
        L_0x00d7:
            int r7 = r7 + 1
            r3 = r12
            r2 = r25
            goto L_0x001d
        L_0x00de:
            r12 = r3
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r2 = r0.b
            r2.solveGraph()
            r2 = 0
        L_0x00e5:
            if (r2 >= r5) goto L_0x02e2
            android.view.View r3 = r0.getChildAt(r2)
            int r6 = r3.getVisibility()
            if (r6 != r10) goto L_0x00f3
            goto L_0x02ce
        L_0x00f3:
            android.view.ViewGroup$LayoutParams r6 = r3.getLayoutParams()
            androidx.constraintlayout.widget.ConstraintLayout$LayoutParams r6 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r6
            androidx.constraintlayout.solver.widgets.ConstraintWidget r7 = r6.s
            boolean r11 = r6.f
            if (r11 != 0) goto L_0x02ce
            boolean r11 = r6.g
            if (r11 == 0) goto L_0x0105
            goto L_0x02ce
        L_0x0105:
            int r11 = r3.getVisibility()
            r7.setVisibility(r11)
            int r11 = r6.width
            int r13 = r6.height
            if (r11 == 0) goto L_0x0116
            if (r13 == 0) goto L_0x0116
            goto L_0x02ce
        L_0x0116:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r14 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r14 = r7.getAnchor(r14)
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r14 = r14.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r15 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r15 = r7.getAnchor(r15)
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r15 = r15.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r10 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r7.getAnchor(r10)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r10.getTarget()
            if (r10 == 0) goto L_0x0144
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r10 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r7.getAnchor(r10)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r10.getTarget()
            if (r10 == 0) goto L_0x0144
            r10 = 1
            goto L_0x0145
        L_0x0144:
            r10 = 0
        L_0x0145:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r7.getAnchor(r8)
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r8 = r8.getResolutionNode()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r9 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r7.getAnchor(r9)
            androidx.constraintlayout.solver.widgets.ResolutionAnchor r9 = r9.getResolutionNode()
            r17 = r5
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r7.getAnchor(r5)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.getTarget()
            if (r5 == 0) goto L_0x0175
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r7.getAnchor(r5)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.getTarget()
            if (r5 == 0) goto L_0x0175
            r5 = 1
            goto L_0x0176
        L_0x0175:
            r5 = 0
        L_0x0176:
            if (r11 != 0) goto L_0x0188
            if (r13 != 0) goto L_0x0188
            if (r10 == 0) goto L_0x0188
            if (r5 == 0) goto L_0x0188
            r20 = r2
            r3 = -1
            r5 = r25
            r10 = -2
            r18 = 1
            goto L_0x02d8
        L_0x0188:
            r20 = r2
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r2 = r0.b
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = r2.getHorizontalDimensionBehaviour()
            r21 = r6
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 == r6) goto L_0x0198
            r6 = 1
            goto L_0x0199
        L_0x0198:
            r6 = 0
        L_0x0199:
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r2 = r0.b
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = r2.getVerticalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 == r0) goto L_0x01a5
            r0 = 1
            goto L_0x01a6
        L_0x01a5:
            r0 = 0
        L_0x01a6:
            if (r6 != 0) goto L_0x01af
            androidx.constraintlayout.solver.widgets.ResolutionDimension r2 = r7.getResolutionWidth()
            r2.invalidate()
        L_0x01af:
            if (r0 != 0) goto L_0x01b8
            androidx.constraintlayout.solver.widgets.ResolutionDimension r2 = r7.getResolutionHeight()
            r2.invalidate()
        L_0x01b8:
            if (r11 != 0) goto L_0x01f0
            if (r6 == 0) goto L_0x01e7
            boolean r2 = r7.isSpreadWidth()
            if (r2 == 0) goto L_0x01e7
            if (r10 == 0) goto L_0x01e7
            boolean r2 = r14.isResolved()
            if (r2 == 0) goto L_0x01e7
            boolean r2 = r15.isResolved()
            if (r2 == 0) goto L_0x01e7
            float r2 = r15.getResolvedValue()
            float r10 = r14.getResolvedValue()
            float r2 = r2 - r10
            int r11 = (int) r2
            androidx.constraintlayout.solver.widgets.ResolutionDimension r2 = r7.getResolutionWidth()
            r2.resolve(r11)
            int r2 = getChildMeasureSpec(r1, r4, r11)
            r14 = r2
            goto L_0x01f8
        L_0x01e7:
            r2 = -2
            int r6 = getChildMeasureSpec(r1, r4, r2)
            r14 = r6
            r2 = 1
            r6 = 0
            goto L_0x0204
        L_0x01f0:
            r2 = -2
            r10 = -1
            if (r11 != r10) goto L_0x01fa
            int r14 = getChildMeasureSpec(r1, r4, r10)
        L_0x01f8:
            r2 = 0
            goto L_0x0204
        L_0x01fa:
            if (r11 != r2) goto L_0x01fe
            r2 = 1
            goto L_0x01ff
        L_0x01fe:
            r2 = 0
        L_0x01ff:
            int r10 = getChildMeasureSpec(r1, r4, r11)
            r14 = r10
        L_0x0204:
            if (r13 != 0) goto L_0x0240
            if (r0 == 0) goto L_0x0236
            boolean r10 = r7.isSpreadHeight()
            if (r10 == 0) goto L_0x0236
            if (r5 == 0) goto L_0x0236
            boolean r5 = r8.isResolved()
            if (r5 == 0) goto L_0x0236
            boolean r5 = r9.isResolved()
            if (r5 == 0) goto L_0x0236
            float r5 = r9.getResolvedValue()
            float r8 = r8.getResolvedValue()
            float r5 = r5 - r8
            int r13 = (int) r5
            androidx.constraintlayout.solver.widgets.ResolutionDimension r5 = r7.getResolutionHeight()
            r5.resolve(r13)
            r5 = r25
            int r8 = getChildMeasureSpec(r5, r12, r13)
            r9 = r0
            r0 = r8
            goto L_0x024c
        L_0x0236:
            r5 = r25
            r8 = -2
            int r0 = getChildMeasureSpec(r5, r12, r8)
            r8 = 1
            r9 = 0
            goto L_0x025c
        L_0x0240:
            r5 = r25
            r8 = -2
            r9 = -1
            if (r13 != r9) goto L_0x024e
            int r10 = getChildMeasureSpec(r5, r12, r9)
            r9 = r0
            r0 = r10
        L_0x024c:
            r8 = 0
            goto L_0x025c
        L_0x024e:
            if (r13 != r8) goto L_0x0252
            r8 = 1
            goto L_0x0253
        L_0x0252:
            r8 = 0
        L_0x0253:
            int r9 = getChildMeasureSpec(r5, r12, r13)
            r22 = r9
            r9 = r0
            r0 = r22
        L_0x025c:
            r3.measure(r14, r0)
            r0 = r23
            androidx.constraintlayout.solver.Metrics r10 = r0.mMetrics
            if (r10 == 0) goto L_0x026e
            long r14 = r10.measures
            r18 = 1
            long r14 = r14 + r18
            r10.measures = r14
            goto L_0x0270
        L_0x026e:
            r18 = 1
        L_0x0270:
            r10 = -2
            if (r11 != r10) goto L_0x0275
            r11 = 1
            goto L_0x0276
        L_0x0275:
            r11 = 0
        L_0x0276:
            r7.setWidthWrapContent(r11)
            if (r13 != r10) goto L_0x027d
            r11 = 1
            goto L_0x027e
        L_0x027d:
            r11 = 0
        L_0x027e:
            r7.setHeightWrapContent(r11)
            int r11 = r3.getMeasuredWidth()
            int r13 = r3.getMeasuredHeight()
            r7.setWidth(r11)
            r7.setHeight(r13)
            if (r2 == 0) goto L_0x0294
            r7.setWrapWidth(r11)
        L_0x0294:
            if (r8 == 0) goto L_0x0299
            r7.setWrapHeight(r13)
        L_0x0299:
            if (r6 == 0) goto L_0x02a3
            androidx.constraintlayout.solver.widgets.ResolutionDimension r2 = r7.getResolutionWidth()
            r2.resolve(r11)
            goto L_0x02aa
        L_0x02a3:
            androidx.constraintlayout.solver.widgets.ResolutionDimension r2 = r7.getResolutionWidth()
            r2.remove()
        L_0x02aa:
            if (r9 == 0) goto L_0x02b4
            androidx.constraintlayout.solver.widgets.ResolutionDimension r2 = r7.getResolutionHeight()
            r2.resolve(r13)
            goto L_0x02bb
        L_0x02b4:
            androidx.constraintlayout.solver.widgets.ResolutionDimension r2 = r7.getResolutionHeight()
            r2.remove()
        L_0x02bb:
            r6 = r21
            boolean r2 = r6.e
            if (r2 == 0) goto L_0x02cc
            int r2 = r3.getBaseline()
            r3 = -1
            if (r2 == r3) goto L_0x02d8
            r7.setBaselineDistance(r2)
            goto L_0x02d8
        L_0x02cc:
            r3 = -1
            goto L_0x02d8
        L_0x02ce:
            r20 = r2
            r17 = r5
            r18 = r8
            r3 = -1
            r5 = r25
            r10 = -2
        L_0x02d8:
            int r2 = r20 + 1
            r5 = r17
            r8 = r18
            r10 = 8
            goto L_0x00e5
        L_0x02e2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.internalMeasureDimensions(int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:158:0x0264  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0271  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x028d  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x029a  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x02b6  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x0371  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x039a  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x03a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setChildrenConstraints() {
        /*
            r26 = this;
            r0 = r26
            boolean r1 = r26.isInEditMode()
            int r2 = r26.getChildCount()
            r3 = 0
            r4 = -1
            if (r1 == 0) goto L_0x0048
            r5 = 0
        L_0x000f:
            if (r5 >= r2) goto L_0x0048
            android.view.View r6 = r0.getChildAt(r5)
            android.content.res.Resources r7 = r26.getResources()     // Catch:{ NotFoundException -> 0x0045 }
            int r8 = r6.getId()     // Catch:{ NotFoundException -> 0x0045 }
            java.lang.String r7 = r7.getResourceName(r8)     // Catch:{ NotFoundException -> 0x0045 }
            int r8 = r6.getId()     // Catch:{ NotFoundException -> 0x0045 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ NotFoundException -> 0x0045 }
            r0.setDesignInformation(r3, r7, r8)     // Catch:{ NotFoundException -> 0x0045 }
            r8 = 47
            int r8 = r7.indexOf(r8)     // Catch:{ NotFoundException -> 0x0045 }
            if (r8 == r4) goto L_0x003a
            int r8 = r8 + 1
            java.lang.String r7 = r7.substring(r8)     // Catch:{ NotFoundException -> 0x0045 }
        L_0x003a:
            int r6 = r6.getId()     // Catch:{ NotFoundException -> 0x0045 }
            androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r0.getTargetWidget(r6)     // Catch:{ NotFoundException -> 0x0045 }
            r6.setDebugName(r7)     // Catch:{ NotFoundException -> 0x0045 }
        L_0x0045:
            int r5 = r5 + 1
            goto L_0x000f
        L_0x0048:
            r5 = 0
        L_0x0049:
            if (r5 >= r2) goto L_0x005c
            android.view.View r6 = r0.getChildAt(r5)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r0.getViewWidget(r6)
            if (r6 != 0) goto L_0x0056
            goto L_0x0059
        L_0x0056:
            r6.reset()
        L_0x0059:
            int r5 = r5 + 1
            goto L_0x0049
        L_0x005c:
            int r5 = r0.mConstraintSetId
            if (r5 == r4) goto L_0x007e
            r5 = 0
        L_0x0061:
            if (r5 >= r2) goto L_0x007e
            android.view.View r6 = r0.getChildAt(r5)
            int r7 = r6.getId()
            int r8 = r0.mConstraintSetId
            if (r7 != r8) goto L_0x007b
            boolean r7 = r6 instanceof androidx.constraintlayout.widget.Constraints
            if (r7 == 0) goto L_0x007b
            androidx.constraintlayout.widget.Constraints r6 = (androidx.constraintlayout.widget.Constraints) r6
            androidx.constraintlayout.widget.ConstraintSet r6 = r6.getConstraintSet()
            r0.mConstraintSet = r6
        L_0x007b:
            int r5 = r5 + 1
            goto L_0x0061
        L_0x007e:
            androidx.constraintlayout.widget.ConstraintSet r5 = r0.mConstraintSet
            if (r5 == 0) goto L_0x0085
            r5.a(r0)
        L_0x0085:
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r5 = r0.b
            r5.removeAllChildren()
            java.util.ArrayList<androidx.constraintlayout.widget.ConstraintHelper> r5 = r0.mConstraintHelpers
            int r5 = r5.size()
            if (r5 <= 0) goto L_0x00a3
            r6 = 0
        L_0x0093:
            if (r6 >= r5) goto L_0x00a3
            java.util.ArrayList<androidx.constraintlayout.widget.ConstraintHelper> r7 = r0.mConstraintHelpers
            java.lang.Object r7 = r7.get(r6)
            androidx.constraintlayout.widget.ConstraintHelper r7 = (androidx.constraintlayout.widget.ConstraintHelper) r7
            r7.updatePreLayout(r0)
            int r6 = r6 + 1
            goto L_0x0093
        L_0x00a3:
            r5 = 0
        L_0x00a4:
            if (r5 >= r2) goto L_0x00b6
            android.view.View r6 = r0.getChildAt(r5)
            boolean r7 = r6 instanceof androidx.constraintlayout.widget.Placeholder
            if (r7 == 0) goto L_0x00b3
            androidx.constraintlayout.widget.Placeholder r6 = (androidx.constraintlayout.widget.Placeholder) r6
            r6.updatePreLayout(r0)
        L_0x00b3:
            int r5 = r5 + 1
            goto L_0x00a4
        L_0x00b6:
            r5 = 0
        L_0x00b7:
            if (r5 >= r2) goto L_0x03dc
            android.view.View r6 = r0.getChildAt(r5)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r13 = r0.getViewWidget(r6)
            if (r13 != 0) goto L_0x00c5
            goto L_0x03d8
        L_0x00c5:
            android.view.ViewGroup$LayoutParams r7 = r6.getLayoutParams()
            r14 = r7
            androidx.constraintlayout.widget.ConstraintLayout$LayoutParams r14 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r14
            r14.validate()
            boolean r7 = r14.helped
            if (r7 == 0) goto L_0x00d6
            r14.helped = r3
            goto L_0x0108
        L_0x00d6:
            if (r1 == 0) goto L_0x0108
            android.content.res.Resources r7 = r26.getResources()     // Catch:{ NotFoundException -> 0x0107 }
            int r8 = r6.getId()     // Catch:{ NotFoundException -> 0x0107 }
            java.lang.String r7 = r7.getResourceName(r8)     // Catch:{ NotFoundException -> 0x0107 }
            int r8 = r6.getId()     // Catch:{ NotFoundException -> 0x0107 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ NotFoundException -> 0x0107 }
            r0.setDesignInformation(r3, r7, r8)     // Catch:{ NotFoundException -> 0x0107 }
            java.lang.String r8 = "id/"
            int r8 = r7.indexOf(r8)     // Catch:{ NotFoundException -> 0x0107 }
            int r8 = r8 + 3
            java.lang.String r7 = r7.substring(r8)     // Catch:{ NotFoundException -> 0x0107 }
            int r8 = r6.getId()     // Catch:{ NotFoundException -> 0x0107 }
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = r0.getTargetWidget(r8)     // Catch:{ NotFoundException -> 0x0107 }
            r8.setDebugName(r7)     // Catch:{ NotFoundException -> 0x0107 }
            goto L_0x0108
        L_0x0107:
        L_0x0108:
            int r7 = r6.getVisibility()
            r13.setVisibility(r7)
            boolean r7 = r14.h
            if (r7 == 0) goto L_0x0118
            r7 = 8
            r13.setVisibility(r7)
        L_0x0118:
            r13.setCompanionWidget(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r6 = r0.b
            r6.add(r13)
            boolean r6 = r14.d
            if (r6 == 0) goto L_0x0128
            boolean r6 = r14.c
            if (r6 != 0) goto L_0x012d
        L_0x0128:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r6 = r0.mVariableDimensionsWidgets
            r6.add(r13)
        L_0x012d:
            boolean r6 = r14.f
            r7 = 17
            if (r6 == 0) goto L_0x015e
            androidx.constraintlayout.solver.widgets.Guideline r13 = (androidx.constraintlayout.solver.widgets.Guideline) r13
            int r6 = r14.p
            int r8 = r14.q
            float r9 = r14.r
            int r10 = android.os.Build.VERSION.SDK_INT
            if (r10 >= r7) goto L_0x0145
            int r6 = r14.guideBegin
            int r8 = r14.guideEnd
            float r9 = r14.guidePercent
        L_0x0145:
            r7 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r7 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r7 == 0) goto L_0x0150
            r13.setGuidePercent((float) r9)
            goto L_0x03d8
        L_0x0150:
            if (r6 == r4) goto L_0x0157
            r13.setGuideBegin(r6)
            goto L_0x03d8
        L_0x0157:
            if (r8 == r4) goto L_0x03d8
            r13.setGuideEnd(r8)
            goto L_0x03d8
        L_0x015e:
            int r6 = r14.leftToLeft
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.leftToRight
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.rightToLeft
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.rightToRight
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.startToStart
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.startToEnd
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.endToStart
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.endToEnd
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.topToTop
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.topToBottom
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.bottomToTop
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.bottomToBottom
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.baselineToBaseline
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.editorAbsoluteX
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.editorAbsoluteY
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.circleConstraint
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.width
            if (r6 == r4) goto L_0x01a6
            int r6 = r14.height
            if (r6 != r4) goto L_0x03d8
        L_0x01a6:
            int r6 = r14.i
            int r8 = r14.j
            int r9 = r14.k
            int r10 = r14.l
            int r11 = r14.m
            int r12 = r14.n
            float r15 = r14.o
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 >= r7) goto L_0x01f1
            int r3 = r14.leftToLeft
            int r6 = r14.leftToRight
            int r9 = r14.rightToLeft
            int r10 = r14.rightToRight
            int r7 = r14.goneLeftMargin
            int r8 = r14.goneRightMargin
            float r15 = r14.horizontalBias
            if (r3 != r4) goto L_0x01d7
            if (r6 != r4) goto L_0x01d7
            int r11 = r14.startToStart
            if (r11 == r4) goto L_0x01d1
            int r3 = r14.startToStart
            goto L_0x01d7
        L_0x01d1:
            int r11 = r14.startToEnd
            if (r11 == r4) goto L_0x01d7
            int r6 = r14.startToEnd
        L_0x01d7:
            r25 = r6
            r6 = r3
            r3 = r25
            if (r9 != r4) goto L_0x01ed
            if (r10 != r4) goto L_0x01ed
            int r11 = r14.endToStart
            if (r11 == r4) goto L_0x01e7
            int r9 = r14.endToStart
            goto L_0x01ed
        L_0x01e7:
            int r11 = r14.endToEnd
            if (r11 == r4) goto L_0x01ed
            int r10 = r14.endToEnd
        L_0x01ed:
            r12 = r7
            r16 = r8
            goto L_0x01f5
        L_0x01f1:
            r3 = r8
            r16 = r12
            r12 = r11
        L_0x01f5:
            r11 = r10
            r10 = r15
            r15 = r9
            int r7 = r14.circleConstraint
            if (r7 == r4) goto L_0x020d
            int r3 = r14.circleConstraint
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r0.getTargetWidget(r3)
            if (r3 == 0) goto L_0x0329
            float r6 = r14.circleAngle
            int r7 = r14.circleRadius
            r13.connectCircularConstraint(r3, r6, r7)
            goto L_0x0329
        L_0x020d:
            if (r6 == r4) goto L_0x022a
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r6)
            if (r9 == 0) goto L_0x0225
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            int r6 = r14.leftMargin
            r7 = r13
            r17 = r10
            r10 = r3
            r3 = r11
            r11 = r6
            r7.immediateConnect(r8, r9, r10, r11, r12)
            goto L_0x0228
        L_0x0225:
            r17 = r10
            r3 = r11
        L_0x0228:
            r6 = r3
            goto L_0x023f
        L_0x022a:
            r17 = r10
            r6 = r11
            if (r3 == r4) goto L_0x023f
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r3)
            if (r9 == 0) goto L_0x023f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r10 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            int r11 = r14.leftMargin
            r7 = r13
            r7.immediateConnect(r8, r9, r10, r11, r12)
        L_0x023f:
            if (r15 == r4) goto L_0x024c
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r15)
            if (r9 == 0) goto L_0x0260
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r10 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            goto L_0x0258
        L_0x024c:
            if (r6 == r4) goto L_0x0260
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r6)
            if (r9 == 0) goto L_0x0260
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r10 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
        L_0x0258:
            int r11 = r14.rightMargin
            r7 = r13
            r12 = r16
            r7.immediateConnect(r8, r9, r10, r11, r12)
        L_0x0260:
            int r3 = r14.topToTop
            if (r3 == r4) goto L_0x0271
            int r3 = r14.topToTop
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r3)
            if (r9 == 0) goto L_0x0289
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r10 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            goto L_0x0281
        L_0x0271:
            int r3 = r14.topToBottom
            if (r3 == r4) goto L_0x0289
            int r3 = r14.topToBottom
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r3)
            if (r9 == 0) goto L_0x0289
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r10 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
        L_0x0281:
            int r11 = r14.topMargin
            int r12 = r14.goneTopMargin
            r7 = r13
            r7.immediateConnect(r8, r9, r10, r11, r12)
        L_0x0289:
            int r3 = r14.bottomToTop
            if (r3 == r4) goto L_0x029a
            int r3 = r14.bottomToTop
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r3)
            if (r9 == 0) goto L_0x02b2
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r10 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            goto L_0x02aa
        L_0x029a:
            int r3 = r14.bottomToBottom
            if (r3 == r4) goto L_0x02b2
            int r3 = r14.bottomToBottom
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r3)
            if (r9 == 0) goto L_0x02b2
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r10 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
        L_0x02aa:
            int r11 = r14.bottomMargin
            int r12 = r14.goneBottomMargin
            r7 = r13
            r7.immediateConnect(r8, r9, r10, r11, r12)
        L_0x02b2:
            int r3 = r14.baselineToBaseline
            if (r3 == r4) goto L_0x0308
            android.util.SparseArray<android.view.View> r3 = r0.a
            int r6 = r14.baselineToBaseline
            java.lang.Object r3 = r3.get(r6)
            android.view.View r3 = (android.view.View) r3
            int r6 = r14.baselineToBaseline
            androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r0.getTargetWidget(r6)
            if (r6 == 0) goto L_0x0308
            if (r3 == 0) goto L_0x0308
            android.view.ViewGroup$LayoutParams r7 = r3.getLayoutParams()
            boolean r7 = r7 instanceof androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
            if (r7 == 0) goto L_0x0308
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            androidx.constraintlayout.widget.ConstraintLayout$LayoutParams r3 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r3
            r7 = 1
            r14.e = r7
            r3.e = r7
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BASELINE
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r18 = r13.getAnchor(r3)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BASELINE
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r19 = r6.getAnchor(r3)
            r20 = 0
            r21 = -1
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Strength r22 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Strength.STRONG
            r23 = 0
            r24 = 1
            r18.connect(r19, r20, r21, r22, r23, r24)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r13.getAnchor(r3)
            r3.reset()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r13.getAnchor(r3)
            r3.reset()
        L_0x0308:
            r3 = 1056964608(0x3f000000, float:0.5)
            r6 = 0
            r15 = r17
            int r7 = (r15 > r6 ? 1 : (r15 == r6 ? 0 : -1))
            if (r7 < 0) goto L_0x0318
            int r7 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1))
            if (r7 == 0) goto L_0x0318
            r13.setHorizontalBiasPercent(r15)
        L_0x0318:
            float r7 = r14.verticalBias
            int r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r6 < 0) goto L_0x0329
            float r6 = r14.verticalBias
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x0329
            float r3 = r14.verticalBias
            r13.setVerticalBiasPercent(r3)
        L_0x0329:
            if (r1 == 0) goto L_0x033a
            int r3 = r14.editorAbsoluteX
            if (r3 != r4) goto L_0x0333
            int r3 = r14.editorAbsoluteY
            if (r3 == r4) goto L_0x033a
        L_0x0333:
            int r3 = r14.editorAbsoluteX
            int r6 = r14.editorAbsoluteY
            r13.setOrigin(r3, r6)
        L_0x033a:
            boolean r3 = r14.c
            if (r3 != 0) goto L_0x0363
            int r3 = r14.width
            if (r3 != r4) goto L_0x035c
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            r13.setHorizontalDimensionBehaviour(r3)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r13.getAnchor(r3)
            int r6 = r14.leftMargin
            r3.mMargin = r6
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r13.getAnchor(r3)
            int r6 = r14.rightMargin
            r3.mMargin = r6
            goto L_0x036d
        L_0x035c:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r13.setHorizontalDimensionBehaviour(r3)
            r3 = 0
            goto L_0x036a
        L_0x0363:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r13.setHorizontalDimensionBehaviour(r3)
            int r3 = r14.width
        L_0x036a:
            r13.setWidth(r3)
        L_0x036d:
            boolean r3 = r14.d
            if (r3 != 0) goto L_0x039a
            int r3 = r14.height
            if (r3 != r4) goto L_0x0390
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            r13.setVerticalDimensionBehaviour(r3)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r13.getAnchor(r3)
            int r6 = r14.topMargin
            r3.mMargin = r6
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r13.getAnchor(r3)
            int r6 = r14.bottomMargin
            r3.mMargin = r6
            r3 = 0
            goto L_0x03a5
        L_0x0390:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r13.setVerticalDimensionBehaviour(r3)
            r3 = 0
            r13.setHeight(r3)
            goto L_0x03a5
        L_0x039a:
            r3 = 0
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r13.setVerticalDimensionBehaviour(r6)
            int r6 = r14.height
            r13.setHeight(r6)
        L_0x03a5:
            java.lang.String r6 = r14.dimensionRatio
            if (r6 == 0) goto L_0x03ae
            java.lang.String r6 = r14.dimensionRatio
            r13.setDimensionRatio(r6)
        L_0x03ae:
            float r6 = r14.horizontalWeight
            r13.setHorizontalWeight(r6)
            float r6 = r14.verticalWeight
            r13.setVerticalWeight(r6)
            int r6 = r14.horizontalChainStyle
            r13.setHorizontalChainStyle(r6)
            int r6 = r14.verticalChainStyle
            r13.setVerticalChainStyle(r6)
            int r6 = r14.matchConstraintDefaultWidth
            int r7 = r14.matchConstraintMinWidth
            int r8 = r14.matchConstraintMaxWidth
            float r9 = r14.matchConstraintPercentWidth
            r13.setHorizontalMatchStyle(r6, r7, r8, r9)
            int r6 = r14.matchConstraintDefaultHeight
            int r7 = r14.matchConstraintMinHeight
            int r8 = r14.matchConstraintMaxHeight
            float r9 = r14.matchConstraintPercentHeight
            r13.setVerticalMatchStyle(r6, r7, r8, r9)
        L_0x03d8:
            int r5 = r5 + 1
            goto L_0x00b7
        L_0x03dc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.setChildrenConstraints():void");
    }

    private void setSelfDimensionBehaviour(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        getLayoutParams();
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            } else if (mode == 1073741824) {
                size = Math.min(this.mMaxWidth, size) - paddingLeft;
            }
            size = 0;
        } else {
            dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (mode2 != Integer.MIN_VALUE) {
            if (mode2 == 0) {
                dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            } else if (mode2 == 1073741824) {
                size2 = Math.min(this.mMaxHeight, size2) - paddingTop;
            }
            size2 = 0;
        } else {
            dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        this.b.setMinWidth(0);
        this.b.setMinHeight(0);
        this.b.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.b.setWidth(size);
        this.b.setVerticalDimensionBehaviour(dimensionBehaviour2);
        this.b.setHeight(size2);
        this.b.setMinWidth((this.mMinWidth - getPaddingLeft()) - getPaddingRight());
        this.b.setMinHeight((this.mMinHeight - getPaddingTop()) - getPaddingBottom());
    }

    private void updateHierarchy() {
        int childCount = getChildCount();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            } else if (getChildAt(i).isLayoutRequested()) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            this.mVariableDimensionsWidgets.clear();
            setChildrenConstraints();
        }
    }

    private void updatePostMeasures() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof Placeholder) {
                ((Placeholder) childAt).updatePostMeasure(this);
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                this.mConstraintHelpers.get(i2).updatePostMeasure(this);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        this.b.layout();
        Metrics metrics = this.mMetrics;
        if (metrics != null) {
            metrics.resolutions++;
        }
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (Build.VERSION.SDK_INT < 14) {
            onViewAdded(view);
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void dispatchDraw(Canvas canvas) {
        Object tag;
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = (float) getWidth();
            float height = (float) getHeight();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                if (!(childAt.getVisibility() == 8 || (tag = childAt.getTag()) == null || !(tag instanceof String))) {
                    String[] split = ((String) tag).split(",");
                    if (split.length == 4) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        int parseInt3 = Integer.parseInt(split[2]);
                        int i2 = (int) ((((float) parseInt) / 1080.0f) * width);
                        int i3 = (int) ((((float) parseInt2) / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(SupportMenu.CATEGORY_MASK);
                        float f2 = (float) i2;
                        float f3 = (float) (i2 + ((int) ((((float) parseInt3) / 1080.0f) * width)));
                        Canvas canvas2 = canvas;
                        float f4 = (float) i3;
                        float f5 = f2;
                        float f6 = f2;
                        float f7 = f4;
                        Paint paint2 = paint;
                        float f8 = f3;
                        Paint paint3 = paint2;
                        canvas2.drawLine(f5, f7, f8, f4, paint3);
                        float parseInt4 = (float) (i3 + ((int) ((((float) Integer.parseInt(split[3])) / 1920.0f) * height)));
                        float f9 = f3;
                        float f10 = parseInt4;
                        canvas2.drawLine(f9, f7, f8, f10, paint3);
                        float f11 = parseInt4;
                        float f12 = f6;
                        canvas2.drawLine(f9, f11, f12, f10, paint3);
                        float f13 = f6;
                        canvas2.drawLine(f13, f11, f12, f4, paint3);
                        Paint paint4 = paint2;
                        paint4.setColor(-16711936);
                        Paint paint5 = paint4;
                        float f14 = f3;
                        Paint paint6 = paint5;
                        canvas2.drawLine(f13, f4, f14, parseInt4, paint6);
                        canvas2.drawLine(f13, parseInt4, f14, f4, paint6);
                    }
                }
            }
        }
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.b.fillMetrics(metrics);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public Object getDesignInformation(int i, Object obj) {
        if (i != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        HashMap<String, Integer> hashMap = this.mDesignIds;
        if (hashMap == null || !hashMap.containsKey(str)) {
            return null;
        }
        return this.mDesignIds.get(str);
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getOptimizationLevel() {
        return this.b.getOptimizationLevel();
    }

    public View getViewById(int i) {
        return this.a.get(i);
    }

    public final ConstraintWidget getViewWidget(View view) {
        if (view == this) {
            return this.b;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).s;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View content;
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = layoutParams.s;
            if ((childAt.getVisibility() != 8 || layoutParams.f || layoutParams.g || isInEditMode) && !layoutParams.h) {
                int drawX = constraintWidget.getDrawX();
                int drawY = constraintWidget.getDrawY();
                int width = constraintWidget.getWidth() + drawX;
                int height = constraintWidget.getHeight() + drawY;
                childAt.layout(drawX, drawY, width, height);
                if ((childAt instanceof Placeholder) && (content = ((Placeholder) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(drawX, drawY, width, height);
                }
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                this.mConstraintHelpers.get(i6).updatePostLayout(this);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        boolean z;
        boolean z2;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = i;
        int i9 = i2;
        System.currentTimeMillis();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (this.mLastMeasureWidth != -1) {
            int i10 = this.mLastMeasureHeight;
        }
        if (mode == 1073741824 && mode2 == 1073741824 && size == this.mLastMeasureWidth) {
            int i11 = this.mLastMeasureHeight;
        }
        boolean z3 = mode == this.e && mode2 == this.f;
        if (z3 && size == this.c) {
            int i12 = this.d;
        }
        if (z3 && mode == Integer.MIN_VALUE && mode2 == 1073741824 && size >= this.mLastMeasureWidth) {
            int i13 = this.mLastMeasureHeight;
        }
        if (z3 && mode == 1073741824 && mode2 == Integer.MIN_VALUE && size == this.mLastMeasureWidth) {
            int i14 = this.mLastMeasureHeight;
        }
        this.e = mode;
        this.f = mode2;
        this.c = size;
        this.d = size2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.b.setX(paddingLeft);
        this.b.setY(paddingTop);
        this.b.setMaxWidth(this.mMaxWidth);
        this.b.setMaxHeight(this.mMaxHeight);
        if (Build.VERSION.SDK_INT >= 17) {
            this.b.setRtl(getLayoutDirection() == 1);
        }
        setSelfDimensionBehaviour(i, i2);
        int width = this.b.getWidth();
        int height = this.b.getHeight();
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            updateHierarchy();
        }
        boolean z4 = (this.mOptimizationLevel & 8) == 8;
        if (z4) {
            this.b.preOptimize();
            this.b.optimizeForDimensions(width, height);
            internalMeasureDimensions(i, i2);
        } else {
            internalMeasureChildren(i, i2);
        }
        updatePostMeasures();
        if (getChildCount() > 0) {
            a("First pass");
        }
        int size3 = this.mVariableDimensionsWidgets.size();
        int paddingBottom = paddingTop + getPaddingBottom();
        int paddingRight = paddingLeft + getPaddingRight();
        if (size3 > 0) {
            boolean z5 = this.b.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean z6 = this.b.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            int max = Math.max(this.b.getWidth(), this.mMinWidth);
            int max2 = Math.max(this.b.getHeight(), this.mMinHeight);
            int i15 = max;
            int i16 = 0;
            boolean z7 = false;
            int i17 = 0;
            while (i16 < size3) {
                ConstraintWidget constraintWidget = this.mVariableDimensionsWidgets.get(i16);
                View view = (View) constraintWidget.getCompanionWidget();
                if (view == null) {
                    i4 = width;
                    i6 = height;
                    i5 = size3;
                } else {
                    i5 = size3;
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    i6 = height;
                    if (layoutParams.g || layoutParams.f) {
                        i4 = width;
                    } else {
                        i4 = width;
                        if (view.getVisibility() != 8 && (!z4 || !constraintWidget.getResolutionWidth().isResolved() || !constraintWidget.getResolutionHeight().isResolved())) {
                            view.measure((layoutParams.width != -2 || !layoutParams.c) ? View.MeasureSpec.makeMeasureSpec(constraintWidget.getWidth(), Ints.MAX_POWER_OF_TWO) : getChildMeasureSpec(i8, paddingRight, layoutParams.width), (layoutParams.height != -2 || !layoutParams.d) ? View.MeasureSpec.makeMeasureSpec(constraintWidget.getHeight(), Ints.MAX_POWER_OF_TWO) : getChildMeasureSpec(i9, paddingBottom, layoutParams.height));
                            Metrics metrics = this.mMetrics;
                            if (metrics != null) {
                                metrics.additionalMeasures++;
                            }
                            int measuredWidth = view.getMeasuredWidth();
                            int measuredHeight = view.getMeasuredHeight();
                            if (measuredWidth != constraintWidget.getWidth()) {
                                constraintWidget.setWidth(measuredWidth);
                                if (z4) {
                                    constraintWidget.getResolutionWidth().resolve(measuredWidth);
                                }
                                if (z5 && constraintWidget.getRight() > i15) {
                                    i15 = Math.max(i15, constraintWidget.getRight() + constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                                }
                                z7 = true;
                            }
                            if (measuredHeight != constraintWidget.getHeight()) {
                                constraintWidget.setHeight(measuredHeight);
                                if (z4) {
                                    constraintWidget.getResolutionHeight().resolve(measuredHeight);
                                }
                                if (z6) {
                                    i7 = max2;
                                    if (constraintWidget.getBottom() > i7) {
                                        max2 = Math.max(i7, constraintWidget.getBottom() + constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                                        z7 = true;
                                    }
                                } else {
                                    i7 = max2;
                                }
                                max2 = i7;
                                z7 = true;
                            } else {
                                int i18 = max2;
                            }
                            if (layoutParams.e) {
                                int baseline = view.getBaseline();
                                if (!(baseline == -1 || baseline == constraintWidget.getBaselineDistance())) {
                                    constraintWidget.setBaselineDistance(baseline);
                                    z7 = true;
                                }
                            }
                            if (Build.VERSION.SDK_INT >= 11) {
                                i17 = combineMeasuredStates(i17, view.getMeasuredState());
                            } else {
                                int i19 = i17;
                            }
                            i16++;
                            height = i6;
                            size3 = i5;
                            width = i4;
                            i8 = i;
                        }
                    }
                }
                max2 = max2;
                i17 = i17;
                i16++;
                height = i6;
                size3 = i5;
                width = i4;
                i8 = i;
            }
            int i20 = width;
            int i21 = height;
            int i22 = size3;
            int i23 = max2;
            i3 = i17;
            if (z7) {
                this.b.setWidth(i20);
                this.b.setHeight(i21);
                if (z4) {
                    this.b.solveGraph();
                }
                a("2nd pass");
                if (this.b.getWidth() < i15) {
                    this.b.setWidth(i15);
                    z = true;
                } else {
                    z = false;
                }
                if (this.b.getHeight() < i23) {
                    this.b.setHeight(i23);
                    z2 = true;
                } else {
                    z2 = z;
                }
                if (z2) {
                    a("3rd pass");
                }
            }
            int i24 = i22;
            for (int i25 = 0; i25 < i24; i25++) {
                ConstraintWidget constraintWidget2 = this.mVariableDimensionsWidgets.get(i25);
                View view2 = (View) constraintWidget2.getCompanionWidget();
                if (view2 != null && (view2.getMeasuredWidth() != constraintWidget2.getWidth() || view2.getMeasuredHeight() != constraintWidget2.getHeight())) {
                    if (constraintWidget2.getVisibility() != 8) {
                        view2.measure(View.MeasureSpec.makeMeasureSpec(constraintWidget2.getWidth(), Ints.MAX_POWER_OF_TWO), View.MeasureSpec.makeMeasureSpec(constraintWidget2.getHeight(), Ints.MAX_POWER_OF_TWO));
                        Metrics metrics2 = this.mMetrics;
                        if (metrics2 != null) {
                            metrics2.additionalMeasures++;
                        }
                    }
                }
            }
        } else {
            i3 = 0;
        }
        int width2 = this.b.getWidth() + paddingRight;
        int height2 = this.b.getHeight() + paddingBottom;
        if (Build.VERSION.SDK_INT >= 11) {
            int resolveSizeAndState = resolveSizeAndState(width2, i, i3);
            int resolveSizeAndState2 = resolveSizeAndState(height2, i9, i3 << 16);
            int i26 = resolveSizeAndState & ViewCompat.MEASURED_SIZE_MASK;
            int i27 = resolveSizeAndState2 & ViewCompat.MEASURED_SIZE_MASK;
            int min = Math.min(this.mMaxWidth, i26);
            int min2 = Math.min(this.mMaxHeight, i27);
            if (this.b.isWidthMeasuredTooSmall()) {
                min |= 16777216;
            }
            if (this.b.isHeightMeasuredTooSmall()) {
                min2 |= 16777216;
            }
            setMeasuredDimension(min, min2);
            this.mLastMeasureWidth = min;
            this.mLastMeasureHeight = min2;
            return;
        }
        setMeasuredDimension(width2, height2);
        this.mLastMeasureWidth = width2;
        this.mLastMeasureHeight = height2;
    }

    public void onViewAdded(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        ConstraintWidget viewWidget = getViewWidget(view);
        if ((view instanceof Guideline) && !(viewWidget instanceof Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.s = new Guideline();
            layoutParams.f = true;
            ((Guideline) layoutParams.s).setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.validateParams();
            ((LayoutParams) view.getLayoutParams()).g = true;
            if (!this.mConstraintHelpers.contains(constraintHelper)) {
                this.mConstraintHelpers.add(constraintHelper);
            }
        }
        this.a.put(view.getId(), view);
        this.mDirtyHierarchy = true;
    }

    public void onViewRemoved(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.a.remove(view.getId());
        ConstraintWidget viewWidget = getViewWidget(view);
        this.b.remove(viewWidget);
        this.mConstraintHelpers.remove(view);
        this.mVariableDimensionsWidgets.remove(viewWidget);
        this.mDirtyHierarchy = true;
    }

    public void removeView(View view) {
        super.removeView(view);
        if (Build.VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    public void requestLayout() {
        super.requestLayout();
        this.mDirtyHierarchy = true;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.c = -1;
        this.d = -1;
        this.e = 0;
        this.f = 0;
    }

    public void setConstraintSet(ConstraintSet constraintSet) {
        this.mConstraintSet = constraintSet;
    }

    public void setDesignInformation(int i, Object obj, Object obj2) {
        if (i == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String str = (String) obj;
            int indexOf = str.indexOf("/");
            if (indexOf != -1) {
                str = str.substring(indexOf + 1);
            }
            this.mDesignIds.put(str, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    public void setId(int i) {
        this.a.remove(getId());
        super.setId(i);
        this.a.put(getId(), this);
    }

    public void setMaxHeight(int i) {
        if (i != this.mMaxHeight) {
            this.mMaxHeight = i;
            requestLayout();
        }
    }

    public void setMaxWidth(int i) {
        if (i != this.mMaxWidth) {
            this.mMaxWidth = i;
            requestLayout();
        }
    }

    public void setMinHeight(int i) {
        if (i != this.mMinHeight) {
            this.mMinHeight = i;
            requestLayout();
        }
    }

    public void setMinWidth(int i) {
        if (i != this.mMinWidth) {
            this.mMinWidth = i;
            requestLayout();
        }
    }

    public void setOptimizationLevel(int i) {
        this.b.setOptimizationLevel(i);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
