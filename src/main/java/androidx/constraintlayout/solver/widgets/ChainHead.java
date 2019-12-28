package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

public class ChainHead {
    protected ConstraintWidget a;
    protected ConstraintWidget b;
    protected ConstraintWidget c;
    protected ConstraintWidget d;
    protected ConstraintWidget e;
    protected ConstraintWidget f;
    protected ConstraintWidget g;
    protected ArrayList<ConstraintWidget> h;
    protected int i;
    protected int j;
    protected float k = 0.0f;
    protected boolean l;
    protected boolean m;
    private boolean mDefined;
    private boolean mIsRtl = false;
    private int mOrientation;
    protected boolean n;

    public ChainHead(ConstraintWidget constraintWidget, int i2, boolean z) {
        this.a = constraintWidget;
        this.mOrientation = i2;
        this.mIsRtl = z;
    }

    private void defineChainProperties() {
        int i2 = this.mOrientation * 2;
        boolean z = false;
        ConstraintWidget constraintWidget = this.a;
        boolean z2 = false;
        while (!z2) {
            this.i++;
            ConstraintWidget constraintWidget2 = null;
            constraintWidget.W[this.mOrientation] = null;
            constraintWidget.V[this.mOrientation] = null;
            if (constraintWidget.getVisibility() != 8) {
                if (this.b == null) {
                    this.b = constraintWidget;
                }
                ConstraintWidget constraintWidget3 = this.d;
                if (constraintWidget3 != null) {
                    constraintWidget3.W[this.mOrientation] = constraintWidget;
                }
                this.d = constraintWidget;
                if (constraintWidget.z[this.mOrientation] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (constraintWidget.e[this.mOrientation] == 0 || constraintWidget.e[this.mOrientation] == 3 || constraintWidget.e[this.mOrientation] == 2)) {
                    this.j++;
                    float f2 = constraintWidget.U[this.mOrientation];
                    if (f2 > 0.0f) {
                        this.k += constraintWidget.U[this.mOrientation];
                    }
                    if (isMatchConstraintEqualityCandidate(constraintWidget, this.mOrientation)) {
                        if (f2 < 0.0f) {
                            this.l = true;
                        } else {
                            this.m = true;
                        }
                        if (this.h == null) {
                            this.h = new ArrayList<>();
                        }
                        this.h.add(constraintWidget);
                    }
                    if (this.f == null) {
                        this.f = constraintWidget;
                    }
                    ConstraintWidget constraintWidget4 = this.g;
                    if (constraintWidget4 != null) {
                        constraintWidget4.V[this.mOrientation] = constraintWidget;
                    }
                    this.g = constraintWidget;
                }
            }
            ConstraintAnchor constraintAnchor = constraintWidget.x[i2 + 1].c;
            if (constraintAnchor != null) {
                ConstraintWidget constraintWidget5 = constraintAnchor.a;
                if (constraintWidget5.x[i2].c != null && constraintWidget5.x[i2].c.a == constraintWidget) {
                    constraintWidget2 = constraintWidget5;
                }
            }
            if (constraintWidget2 != null) {
                constraintWidget = constraintWidget2;
            } else {
                z2 = true;
            }
        }
        this.c = constraintWidget;
        this.e = (this.mOrientation != 0 || !this.mIsRtl) ? this.a : this.c;
        if (this.m && this.l) {
            z = true;
        }
        this.n = z;
    }

    private static boolean isMatchConstraintEqualityCandidate(ConstraintWidget constraintWidget, int i2) {
        return constraintWidget.getVisibility() != 8 && constraintWidget.z[i2] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (constraintWidget.e[i2] == 0 || constraintWidget.e[i2] == 3);
    }

    public void define() {
        if (!this.mDefined) {
            defineChainProperties();
        }
        this.mDefined = true;
    }

    public ConstraintWidget getFirst() {
        return this.a;
    }

    public ConstraintWidget getFirstMatchConstraintWidget() {
        return this.f;
    }

    public ConstraintWidget getFirstVisibleWidget() {
        return this.b;
    }

    public ConstraintWidget getHead() {
        return this.e;
    }

    public ConstraintWidget getLast() {
        return this.c;
    }

    public ConstraintWidget getLastMatchConstraintWidget() {
        return this.g;
    }

    public ConstraintWidget getLastVisibleWidget() {
        return this.d;
    }

    public float getTotalWeight() {
        return this.k;
    }
}
