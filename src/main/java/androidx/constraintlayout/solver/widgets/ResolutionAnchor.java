package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;

public class ResolutionAnchor extends ResolutionNode {
    public static final int BARRIER_CONNECTION = 5;
    public static final int CENTER_CONNECTION = 2;
    public static final int CHAIN_CONNECTION = 4;
    public static final int DIRECT_CONNECTION = 1;
    public static final int MATCH_CONNECTION = 3;
    public static final int UNCONNECTED = 0;
    ConstraintAnchor a;
    float b;
    ResolutionAnchor c;
    float d;
    private ResolutionDimension dimension = null;
    private int dimensionMultiplier = 1;
    ResolutionAnchor e;
    float f;
    int g = 0;
    private ResolutionAnchor opposite;
    private ResolutionDimension oppositeDimension = null;
    private int oppositeDimensionMultiplier = 1;
    private float oppositeOffset;

    public ResolutionAnchor(ConstraintAnchor constraintAnchor) {
        this.a = constraintAnchor;
    }

    /* access modifiers changed from: package-private */
    public String a(int i) {
        return i == 1 ? "DIRECT" : i == 2 ? "CENTER" : i == 3 ? "MATCH" : i == 4 ? "CHAIN" : i == 5 ? "BARRIER" : "UNCONNECTED";
    }

    /* access modifiers changed from: package-private */
    public void a(LinearSystem linearSystem) {
        SolverVariable solverVariable = this.a.getSolverVariable();
        ResolutionAnchor resolutionAnchor = this.e;
        if (resolutionAnchor == null) {
            linearSystem.addEquality(solverVariable, (int) this.f);
        } else {
            linearSystem.addEquality(solverVariable, linearSystem.createObjectVariable(resolutionAnchor.a), (int) this.f, 6);
        }
    }

    public void dependsOn(int i, ResolutionAnchor resolutionAnchor, int i2) {
        this.g = i;
        this.c = resolutionAnchor;
        this.d = (float) i2;
        this.c.addDependent(this);
    }

    public void dependsOn(ResolutionAnchor resolutionAnchor, int i) {
        this.c = resolutionAnchor;
        this.d = (float) i;
        this.c.addDependent(this);
    }

    public void dependsOn(ResolutionAnchor resolutionAnchor, int i, ResolutionDimension resolutionDimension) {
        this.c = resolutionAnchor;
        this.c.addDependent(this);
        this.dimension = resolutionDimension;
        this.dimensionMultiplier = i;
        this.dimension.addDependent(this);
    }

    public float getResolvedValue() {
        return this.f;
    }

    public void remove(ResolutionDimension resolutionDimension) {
        ResolutionDimension resolutionDimension2 = this.dimension;
        if (resolutionDimension2 == resolutionDimension) {
            this.dimension = null;
            this.d = (float) this.dimensionMultiplier;
        } else if (resolutionDimension2 == this.oppositeDimension) {
            this.oppositeDimension = null;
            this.oppositeOffset = (float) this.oppositeDimensionMultiplier;
        }
        resolve();
    }

    public void reset() {
        super.reset();
        this.c = null;
        this.d = 0.0f;
        this.dimension = null;
        this.dimensionMultiplier = 1;
        this.oppositeDimension = null;
        this.oppositeDimensionMultiplier = 1;
        this.e = null;
        this.f = 0.0f;
        this.b = 0.0f;
        this.opposite = null;
        this.oppositeOffset = 0.0f;
        this.g = 0;
    }

    public void resolve() {
        ResolutionAnchor resolutionAnchor;
        ResolutionAnchor resolutionAnchor2;
        ResolutionAnchor resolutionAnchor3;
        ResolutionAnchor resolutionAnchor4;
        ResolutionAnchor resolutionAnchor5;
        ResolutionAnchor resolutionAnchor6;
        ResolutionAnchor resolutionAnchor7;
        float f2;
        float f3;
        float f4;
        ResolutionAnchor resolutionAnchor8;
        float f5;
        boolean z = true;
        if (this.i != 1 && this.g != 4) {
            ResolutionDimension resolutionDimension = this.dimension;
            if (resolutionDimension != null) {
                if (resolutionDimension.i == 1) {
                    this.d = ((float) this.dimensionMultiplier) * this.dimension.a;
                } else {
                    return;
                }
            }
            ResolutionDimension resolutionDimension2 = this.oppositeDimension;
            if (resolutionDimension2 != null) {
                if (resolutionDimension2.i == 1) {
                    this.oppositeOffset = ((float) this.oppositeDimensionMultiplier) * this.oppositeDimension.a;
                } else {
                    return;
                }
            }
            if (this.g == 1 && ((resolutionAnchor8 = this.c) == null || resolutionAnchor8.i == 1)) {
                ResolutionAnchor resolutionAnchor9 = this.c;
                if (resolutionAnchor9 == null) {
                    this.e = this;
                    f5 = this.d;
                } else {
                    this.e = resolutionAnchor9.e;
                    f5 = resolutionAnchor9.f + this.d;
                }
                this.f = f5;
                didResolve();
                return;
            }
            if (this.g == 2 && (resolutionAnchor4 = this.c) != null && resolutionAnchor4.i == 1 && (resolutionAnchor5 = this.opposite) != null && (resolutionAnchor6 = resolutionAnchor5.c) != null && resolutionAnchor6.i == 1) {
                if (LinearSystem.getMetrics() != null) {
                    LinearSystem.getMetrics().centerConnectionResolved++;
                }
                this.e = this.c.e;
                ResolutionAnchor resolutionAnchor10 = this.opposite;
                resolutionAnchor10.e = resolutionAnchor10.c.e;
                int i = 0;
                if (!(this.a.b == ConstraintAnchor.Type.RIGHT || this.a.b == ConstraintAnchor.Type.BOTTOM)) {
                    z = false;
                }
                if (z) {
                    f2 = this.c.f;
                    resolutionAnchor7 = this.opposite.c;
                } else {
                    f2 = this.opposite.c.f;
                    resolutionAnchor7 = this.c;
                }
                float f6 = f2 - resolutionAnchor7.f;
                if (this.a.b == ConstraintAnchor.Type.LEFT || this.a.b == ConstraintAnchor.Type.RIGHT) {
                    f4 = f6 - ((float) this.a.a.getWidth());
                    f3 = this.a.a.M;
                } else {
                    f4 = f6 - ((float) this.a.a.getHeight());
                    f3 = this.a.a.N;
                }
                int margin = this.a.getMargin();
                int margin2 = this.opposite.a.getMargin();
                if (this.a.getTarget() == this.opposite.a.getTarget()) {
                    f3 = 0.5f;
                    margin2 = 0;
                } else {
                    i = margin;
                }
                float f7 = (float) i;
                float f8 = (float) margin2;
                float f9 = (f4 - f7) - f8;
                if (z) {
                    ResolutionAnchor resolutionAnchor11 = this.opposite;
                    resolutionAnchor11.f = resolutionAnchor11.c.f + f8 + (f9 * f3);
                    this.f = (this.c.f - f7) - (f9 * (1.0f - f3));
                } else {
                    this.f = this.c.f + f7 + (f9 * f3);
                    ResolutionAnchor resolutionAnchor12 = this.opposite;
                    resolutionAnchor12.f = (resolutionAnchor12.c.f - f8) - (f9 * (1.0f - f3));
                }
            } else if (this.g == 3 && (resolutionAnchor = this.c) != null && resolutionAnchor.i == 1 && (resolutionAnchor2 = this.opposite) != null && (resolutionAnchor3 = resolutionAnchor2.c) != null && resolutionAnchor3.i == 1) {
                if (LinearSystem.getMetrics() != null) {
                    LinearSystem.getMetrics().matchConnectionResolved++;
                }
                ResolutionAnchor resolutionAnchor13 = this.c;
                this.e = resolutionAnchor13.e;
                ResolutionAnchor resolutionAnchor14 = this.opposite;
                ResolutionAnchor resolutionAnchor15 = resolutionAnchor14.c;
                resolutionAnchor14.e = resolutionAnchor15.e;
                this.f = resolutionAnchor13.f + this.d;
                resolutionAnchor14.f = resolutionAnchor15.f + resolutionAnchor14.d;
            } else if (this.g == 5) {
                this.a.a.resolve();
                return;
            } else {
                return;
            }
            didResolve();
            this.opposite.didResolve();
        }
    }

    public void resolve(ResolutionAnchor resolutionAnchor, float f2) {
        if (this.i == 0 || !(this.e == resolutionAnchor || this.f == f2)) {
            this.e = resolutionAnchor;
            this.f = f2;
            if (this.i == 1) {
                invalidate();
            }
            didResolve();
        }
    }

    public void setOpposite(ResolutionAnchor resolutionAnchor, float f2) {
        this.opposite = resolutionAnchor;
        this.oppositeOffset = f2;
    }

    public void setOpposite(ResolutionAnchor resolutionAnchor, int i, ResolutionDimension resolutionDimension) {
        this.opposite = resolutionAnchor;
        this.oppositeDimension = resolutionDimension;
        this.oppositeDimensionMultiplier = i;
    }

    public void setType(int i) {
        this.g = i;
    }

    public String toString() {
        StringBuilder sb;
        String str;
        if (this.i != 1) {
            sb = new StringBuilder();
            sb.append("{ ");
            sb.append(this.a);
            str = " UNRESOLVED} type: ";
        } else if (this.e == this) {
            sb = new StringBuilder();
            sb.append("[");
            sb.append(this.a);
            sb.append(", RESOLVED: ");
            sb.append(this.f);
            str = "]  type: ";
        } else {
            sb = new StringBuilder();
            sb.append("[");
            sb.append(this.a);
            sb.append(", RESOLVED: ");
            sb.append(this.e);
            sb.append(":");
            sb.append(this.f);
            str = "] type: ";
        }
        sb.append(str);
        sb.append(a(this.g));
        return sb.toString();
    }

    public void update() {
        ConstraintAnchor target = this.a.getTarget();
        if (target != null) {
            if (target.getTarget() == this.a) {
                this.g = 4;
                target.getResolutionNode().g = 4;
            }
            int margin = this.a.getMargin();
            if (this.a.b == ConstraintAnchor.Type.RIGHT || this.a.b == ConstraintAnchor.Type.BOTTOM) {
                margin = -margin;
            }
            dependsOn(target.getResolutionNode(), margin);
        }
    }
}
