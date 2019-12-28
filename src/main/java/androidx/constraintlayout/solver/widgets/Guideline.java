package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

public class Guideline extends ConstraintWidget {
    public static final int HORIZONTAL = 0;
    public static final int RELATIVE_BEGIN = 1;
    public static final int RELATIVE_END = 2;
    public static final int RELATIVE_PERCENT = 0;
    public static final int RELATIVE_UNKNWON = -1;
    public static final int VERTICAL = 1;
    protected float Z = -1.0f;
    protected int aa = -1;
    protected int ab = -1;
    private ConstraintAnchor mAnchor = this.q;
    private Rectangle mHead;
    private int mHeadSize;
    private boolean mIsPositionRelaxed;
    private int mMinimumPosition;
    private int mOrientation;

    public Guideline() {
        this.mOrientation = 0;
        this.mIsPositionRelaxed = false;
        this.mMinimumPosition = 0;
        this.mHead = new Rectangle();
        this.mHeadSize = 8;
        this.y.clear();
        this.y.add(this.mAnchor);
        int length = this.x.length;
        for (int i = 0; i < length; i++) {
            this.x[i] = this.mAnchor;
        }
    }

    public void addToSolver(LinearSystem linearSystem) {
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) getParent();
        if (constraintWidgetContainer != null) {
            ConstraintAnchor anchor = constraintWidgetContainer.getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor anchor2 = constraintWidgetContainer.getAnchor(ConstraintAnchor.Type.RIGHT);
            boolean z = this.A != null && this.A.z[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (this.mOrientation == 0) {
                anchor = constraintWidgetContainer.getAnchor(ConstraintAnchor.Type.TOP);
                anchor2 = constraintWidgetContainer.getAnchor(ConstraintAnchor.Type.BOTTOM);
                z = this.A != null && this.A.z[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            }
            if (this.aa != -1) {
                SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mAnchor);
                linearSystem.addEquality(createObjectVariable, linearSystem.createObjectVariable(anchor), this.aa, 6);
                if (z) {
                    linearSystem.addGreaterThan(linearSystem.createObjectVariable(anchor2), createObjectVariable, 0, 5);
                }
            } else if (this.ab != -1) {
                SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mAnchor);
                SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(anchor2);
                linearSystem.addEquality(createObjectVariable2, createObjectVariable3, -this.ab, 6);
                if (z) {
                    linearSystem.addGreaterThan(createObjectVariable2, linearSystem.createObjectVariable(anchor), 0, 5);
                    linearSystem.addGreaterThan(createObjectVariable3, createObjectVariable2, 0, 5);
                }
            } else if (this.Z != -1.0f) {
                linearSystem.addConstraint(LinearSystem.createRowDimensionPercent(linearSystem, linearSystem.createObjectVariable(this.mAnchor), linearSystem.createObjectVariable(anchor), linearSystem.createObjectVariable(anchor2), this.Z, this.mIsPositionRelaxed));
            }
        }
    }

    public boolean allowedInBarrier() {
        return true;
    }

    public void analyze(int i) {
        ConstraintAnchor constraintAnchor;
        ResolutionAnchor resolutionAnchor;
        int i2;
        ConstraintAnchor constraintAnchor2;
        ResolutionAnchor resolutionAnchor2;
        ResolutionAnchor resolutionNode;
        int i3;
        ConstraintAnchor constraintAnchor3;
        ResolutionAnchor resolutionNode2;
        ConstraintWidget parent = getParent();
        if (parent != null) {
            if (getOrientation() == 1) {
                this.q.getResolutionNode().dependsOn(1, parent.q.getResolutionNode(), 0);
                this.s.getResolutionNode().dependsOn(1, parent.q.getResolutionNode(), 0);
                if (this.aa != -1) {
                    this.p.getResolutionNode().dependsOn(1, parent.p.getResolutionNode(), this.aa);
                    resolutionAnchor2 = this.r.getResolutionNode();
                    constraintAnchor3 = parent.p;
                } else if (this.ab != -1) {
                    this.p.getResolutionNode().dependsOn(1, parent.r.getResolutionNode(), -this.ab);
                    resolutionNode2 = this.r.getResolutionNode();
                    constraintAnchor2 = parent.r;
                    resolutionNode = constraintAnchor2.getResolutionNode();
                    i3 = -this.ab;
                    resolutionAnchor2.dependsOn(1, resolutionNode, i3);
                } else if (this.Z != -1.0f && parent.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    i2 = (int) (((float) parent.B) * this.Z);
                    this.p.getResolutionNode().dependsOn(1, parent.p.getResolutionNode(), i2);
                    resolutionAnchor = this.r.getResolutionNode();
                    constraintAnchor = parent.p;
                    resolutionAnchor.dependsOn(1, constraintAnchor.getResolutionNode(), i2);
                    return;
                } else {
                    return;
                }
            } else {
                this.p.getResolutionNode().dependsOn(1, parent.p.getResolutionNode(), 0);
                this.r.getResolutionNode().dependsOn(1, parent.p.getResolutionNode(), 0);
                if (this.aa != -1) {
                    this.q.getResolutionNode().dependsOn(1, parent.q.getResolutionNode(), this.aa);
                    resolutionAnchor2 = this.s.getResolutionNode();
                    constraintAnchor3 = parent.q;
                } else if (this.ab != -1) {
                    this.q.getResolutionNode().dependsOn(1, parent.s.getResolutionNode(), -this.ab);
                    resolutionNode2 = this.s.getResolutionNode();
                    constraintAnchor2 = parent.s;
                    resolutionNode = constraintAnchor2.getResolutionNode();
                    i3 = -this.ab;
                    resolutionAnchor2.dependsOn(1, resolutionNode, i3);
                } else if (this.Z != -1.0f && parent.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    i2 = (int) (((float) parent.C) * this.Z);
                    this.q.getResolutionNode().dependsOn(1, parent.q.getResolutionNode(), i2);
                    resolutionAnchor = this.s.getResolutionNode();
                    constraintAnchor = parent.q;
                    resolutionAnchor.dependsOn(1, constraintAnchor.getResolutionNode(), i2);
                    return;
                } else {
                    return;
                }
            }
            resolutionNode = constraintAnchor3.getResolutionNode();
            i3 = this.aa;
            resolutionAnchor2.dependsOn(1, resolutionNode, i3);
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        float x = ((float) getX()) / ((float) getParent().getWidth());
        if (this.mOrientation == 0) {
            x = ((float) getY()) / ((float) getParent().getHeight());
        }
        setGuidePercent(x);
    }

    public void cyclePosition() {
        if (this.aa != -1) {
            c();
        } else if (this.Z != -1.0f) {
            e();
        } else if (this.ab != -1) {
            d();
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        int x = getX();
        if (this.mOrientation == 0) {
            x = getY();
        }
        setGuideBegin(x);
    }

    /* access modifiers changed from: package-private */
    public void e() {
        int width = getParent().getWidth() - getX();
        if (this.mOrientation == 0) {
            width = getParent().getHeight() - getY();
        }
        setGuideEnd(width);
    }

    public ConstraintAnchor getAnchor() {
        return this.mAnchor;
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (type) {
            case LEFT:
            case RIGHT:
                if (this.mOrientation == 1) {
                    return this.mAnchor;
                }
                break;
            case TOP:
            case BOTTOM:
                if (this.mOrientation == 0) {
                    return this.mAnchor;
                }
                break;
            case BASELINE:
            case CENTER:
            case CENTER_X:
            case CENTER_Y:
            case NONE:
                return null;
        }
        throw new AssertionError(type.name());
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.y;
    }

    public Rectangle getHead() {
        Rectangle rectangle = this.mHead;
        int drawX = getDrawX() - this.mHeadSize;
        int drawY = getDrawY();
        int i = this.mHeadSize;
        rectangle.setBounds(drawX, drawY - (i * 2), i * 2, i * 2);
        if (getOrientation() == 0) {
            Rectangle rectangle2 = this.mHead;
            int drawX2 = getDrawX() - (this.mHeadSize * 2);
            int drawY2 = getDrawY();
            int i2 = this.mHeadSize;
            rectangle2.setBounds(drawX2, drawY2 - i2, i2 * 2, i2 * 2);
        }
        return this.mHead;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getRelativeBegin() {
        return this.aa;
    }

    public int getRelativeBehaviour() {
        if (this.Z != -1.0f) {
            return 0;
        }
        if (this.aa != -1) {
            return 1;
        }
        return this.ab != -1 ? 2 : -1;
    }

    public int getRelativeEnd() {
        return this.ab;
    }

    public float getRelativePercent() {
        return this.Z;
    }

    public String getType() {
        return "Guideline";
    }

    public void setDrawOrigin(int i, int i2) {
        int i3;
        float f;
        if (this.mOrientation == 1) {
            int i4 = i - this.H;
            if (this.aa != -1) {
                setGuideBegin(i4);
                return;
            } else if (this.ab != -1) {
                setGuideEnd(getParent().getWidth() - i4);
                return;
            } else if (this.Z != -1.0f) {
                f = (float) i4;
                i3 = getParent().getWidth();
            } else {
                return;
            }
        } else {
            int i5 = i2 - this.I;
            if (this.aa != -1) {
                setGuideBegin(i5);
                return;
            } else if (this.ab != -1) {
                setGuideEnd(getParent().getHeight() - i5);
                return;
            } else if (this.Z != -1.0f) {
                f = (float) i5;
                i3 = getParent().getHeight();
            } else {
                return;
            }
        }
        setGuidePercent(f / ((float) i3));
    }

    public void setGuideBegin(int i) {
        if (i > -1) {
            this.Z = -1.0f;
            this.aa = i;
            this.ab = -1;
        }
    }

    public void setGuideEnd(int i) {
        if (i > -1) {
            this.Z = -1.0f;
            this.aa = -1;
            this.ab = i;
        }
    }

    public void setGuidePercent(float f) {
        if (f > -1.0f) {
            this.Z = f;
            this.aa = -1;
            this.ab = -1;
        }
    }

    public void setGuidePercent(int i) {
        setGuidePercent(((float) i) / 100.0f);
    }

    public void setMinimumPosition(int i) {
        this.mMinimumPosition = i;
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            this.y.clear();
            this.mAnchor = this.mOrientation == 1 ? this.p : this.q;
            this.y.add(this.mAnchor);
            int length = this.x.length;
            for (int i2 = 0; i2 < length; i2++) {
                this.x[i2] = this.mAnchor;
            }
        }
    }

    public void setPositionRelaxed(boolean z) {
        if (this.mIsPositionRelaxed != z) {
            this.mIsPositionRelaxed = z;
        }
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        if (getParent() != null) {
            int objectVariableValue = linearSystem.getObjectVariableValue(this.mAnchor);
            if (this.mOrientation == 1) {
                setX(objectVariableValue);
                setY(0);
                setHeight(getParent().getHeight());
                setWidth(0);
                return;
            }
            setX(0);
            setY(objectVariableValue);
            setWidth(getParent().getWidth());
            setHeight(0);
        }
    }
}
