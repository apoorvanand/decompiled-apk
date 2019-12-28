package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;
import java.util.HashSet;

public class ConstraintAnchor {
    private static final boolean ALLOW_BINARY = false;
    public static final int AUTO_CONSTRAINT_CREATOR = 2;
    public static final int SCOUT_CREATOR = 1;
    private static final int UNSET_GONE_MARGIN = -1;
    public static final int USER_CREATOR = 0;
    final ConstraintWidget a;
    final Type b;
    ConstraintAnchor c;
    int d = -1;
    SolverVariable e;
    private int mConnectionCreator = 0;
    private ConnectionType mConnectionType = ConnectionType.RELAXED;
    public int mMargin = 0;
    private ResolutionAnchor mResolutionAnchor = new ResolutionAnchor(this);
    private Strength mStrength = Strength.NONE;

    public enum ConnectionType {
        RELAXED,
        STRICT
    }

    public enum Strength {
        NONE,
        STRONG,
        WEAK
    }

    public enum Type {
        NONE,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        BASELINE,
        CENTER,
        CENTER_X,
        CENTER_Y
    }

    public ConstraintAnchor(ConstraintWidget constraintWidget, Type type) {
        this.a = constraintWidget;
        this.b = type;
    }

    private boolean isConnectionToMe(ConstraintWidget constraintWidget, HashSet<ConstraintWidget> hashSet) {
        if (hashSet.contains(constraintWidget)) {
            return false;
        }
        hashSet.add(constraintWidget);
        if (constraintWidget == getOwner()) {
            return true;
        }
        ArrayList<ConstraintAnchor> anchors = constraintWidget.getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = anchors.get(i);
            if (constraintAnchor.isSimilarDimensionConnection(this) && constraintAnchor.isConnected() && isConnectionToMe(constraintAnchor.getTarget().getOwner(), hashSet)) {
                return true;
            }
        }
        return false;
    }

    public boolean connect(ConstraintAnchor constraintAnchor, int i) {
        return connect(constraintAnchor, i, -1, Strength.STRONG, 0, false);
    }

    public boolean connect(ConstraintAnchor constraintAnchor, int i, int i2) {
        return connect(constraintAnchor, i, -1, Strength.STRONG, i2, false);
    }

    public boolean connect(ConstraintAnchor constraintAnchor, int i, int i2, Strength strength, int i3, boolean z) {
        if (constraintAnchor == null) {
            this.c = null;
            this.mMargin = 0;
            this.d = -1;
            this.mStrength = Strength.NONE;
            this.mConnectionCreator = 2;
            return true;
        } else if (!z && !isValidConnection(constraintAnchor)) {
            return false;
        } else {
            this.c = constraintAnchor;
            if (i > 0) {
                this.mMargin = i;
            } else {
                this.mMargin = 0;
            }
            this.d = i2;
            this.mStrength = strength;
            this.mConnectionCreator = i3;
            return true;
        }
    }

    public boolean connect(ConstraintAnchor constraintAnchor, int i, Strength strength, int i2) {
        return connect(constraintAnchor, i, -1, strength, i2, false);
    }

    public int getConnectionCreator() {
        return this.mConnectionCreator;
    }

    public ConnectionType getConnectionType() {
        return this.mConnectionType;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0011, code lost:
        r0 = r3.c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getMargin() {
        /*
            r3 = this;
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r3.a
            int r0 = r0.getVisibility()
            r1 = 8
            if (r0 != r1) goto L_0x000c
            r0 = 0
            return r0
        L_0x000c:
            int r0 = r3.d
            r2 = -1
            if (r0 <= r2) goto L_0x0020
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r3.c
            if (r0 == 0) goto L_0x0020
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r0.a
            int r0 = r0.getVisibility()
            if (r0 != r1) goto L_0x0020
            int r0 = r3.d
            return r0
        L_0x0020:
            int r0 = r3.mMargin
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintAnchor.getMargin():int");
    }

    public final ConstraintAnchor getOpposite() {
        switch (this.b) {
            case CENTER:
            case BASELINE:
            case CENTER_X:
            case CENTER_Y:
            case NONE:
                return null;
            case LEFT:
                return this.a.r;
            case RIGHT:
                return this.a.p;
            case TOP:
                return this.a.s;
            case BOTTOM:
                return this.a.q;
            default:
                throw new AssertionError(this.b.name());
        }
    }

    public ConstraintWidget getOwner() {
        return this.a;
    }

    public int getPriorityLevel() {
        switch (this.b) {
            case CENTER:
            case LEFT:
            case RIGHT:
            case TOP:
            case BOTTOM:
                return 2;
            case BASELINE:
                return 1;
            case CENTER_X:
            case CENTER_Y:
            case NONE:
                return 0;
            default:
                throw new AssertionError(this.b.name());
        }
    }

    public ResolutionAnchor getResolutionNode() {
        return this.mResolutionAnchor;
    }

    public int getSnapPriorityLevel() {
        switch (this.b) {
            case CENTER:
                return 3;
            case LEFT:
            case RIGHT:
                return 1;
            case TOP:
            case BOTTOM:
                return 0;
            case BASELINE:
                return 2;
            case CENTER_X:
                return 0;
            case CENTER_Y:
                return 1;
            case NONE:
                return 0;
            default:
                throw new AssertionError(this.b.name());
        }
    }

    public SolverVariable getSolverVariable() {
        return this.e;
    }

    public Strength getStrength() {
        return this.mStrength;
    }

    public ConstraintAnchor getTarget() {
        return this.c;
    }

    public Type getType() {
        return this.b;
    }

    public boolean isConnected() {
        return this.c != null;
    }

    public boolean isConnectionAllowed(ConstraintWidget constraintWidget) {
        if (isConnectionToMe(constraintWidget, new HashSet())) {
            return false;
        }
        ConstraintWidget parent = getOwner().getParent();
        return parent == constraintWidget || constraintWidget.getParent() == parent;
    }

    public boolean isConnectionAllowed(ConstraintWidget constraintWidget, ConstraintAnchor constraintAnchor) {
        return isConnectionAllowed(constraintWidget);
    }

    public boolean isSideAnchor() {
        switch (this.b) {
            case CENTER:
            case BASELINE:
            case CENTER_X:
            case CENTER_Y:
            case NONE:
                return false;
            case LEFT:
            case RIGHT:
            case TOP:
            case BOTTOM:
                return true;
            default:
                throw new AssertionError(this.b.name());
        }
    }

    public boolean isSimilarDimensionConnection(ConstraintAnchor constraintAnchor) {
        Type type = constraintAnchor.getType();
        if (type == this.b) {
            return true;
        }
        switch (this.b) {
            case CENTER:
                return type != Type.BASELINE;
            case LEFT:
            case RIGHT:
            case CENTER_X:
                return type == Type.LEFT || type == Type.RIGHT || type == Type.CENTER_X;
            case TOP:
            case BOTTOM:
            case BASELINE:
            case CENTER_Y:
                return type == Type.TOP || type == Type.BOTTOM || type == Type.CENTER_Y || type == Type.BASELINE;
            case NONE:
                return false;
            default:
                throw new AssertionError(this.b.name());
        }
    }

    public boolean isSnapCompatibleWith(ConstraintAnchor constraintAnchor) {
        if (this.b == Type.CENTER) {
            return false;
        }
        if (this.b == constraintAnchor.getType()) {
            return true;
        }
        switch (this.b) {
            case CENTER:
            case BASELINE:
            case NONE:
                return false;
            case LEFT:
                int i = AnonymousClass1.a[constraintAnchor.getType().ordinal()];
                return i == 3 || i == 7;
            case RIGHT:
                int i2 = AnonymousClass1.a[constraintAnchor.getType().ordinal()];
                return i2 == 2 || i2 == 7;
            case TOP:
                int i3 = AnonymousClass1.a[constraintAnchor.getType().ordinal()];
                return i3 == 5 || i3 == 8;
            case BOTTOM:
                int i4 = AnonymousClass1.a[constraintAnchor.getType().ordinal()];
                return i4 == 4 || i4 == 8;
            case CENTER_X:
                switch (constraintAnchor.getType()) {
                    case LEFT:
                    case RIGHT:
                        return true;
                    default:
                        return false;
                }
            case CENTER_Y:
                switch (constraintAnchor.getType()) {
                    case TOP:
                    case BOTTOM:
                        return true;
                    default:
                        return false;
                }
            default:
                throw new AssertionError(this.b.name());
        }
    }

    public boolean isValidConnection(ConstraintAnchor constraintAnchor) {
        if (constraintAnchor == null) {
            return false;
        }
        Type type = constraintAnchor.getType();
        Type type2 = this.b;
        if (type == type2) {
            return type2 != Type.BASELINE || (constraintAnchor.getOwner().hasBaseline() && getOwner().hasBaseline());
        }
        switch (this.b) {
            case CENTER:
                return (type == Type.BASELINE || type == Type.CENTER_X || type == Type.CENTER_Y) ? false : true;
            case LEFT:
            case RIGHT:
                boolean z = type == Type.LEFT || type == Type.RIGHT;
                return constraintAnchor.getOwner() instanceof Guideline ? z || type == Type.CENTER_X : z;
            case TOP:
            case BOTTOM:
                boolean z2 = type == Type.TOP || type == Type.BOTTOM;
                return constraintAnchor.getOwner() instanceof Guideline ? z2 || type == Type.CENTER_Y : z2;
            case BASELINE:
            case CENTER_X:
            case CENTER_Y:
            case NONE:
                return false;
            default:
                throw new AssertionError(this.b.name());
        }
    }

    public boolean isVerticalAnchor() {
        switch (this.b) {
            case CENTER:
            case LEFT:
            case RIGHT:
            case CENTER_X:
                return false;
            case TOP:
            case BOTTOM:
            case BASELINE:
            case CENTER_Y:
            case NONE:
                return true;
            default:
                throw new AssertionError(this.b.name());
        }
    }

    public void reset() {
        this.c = null;
        this.mMargin = 0;
        this.d = -1;
        this.mStrength = Strength.STRONG;
        this.mConnectionCreator = 0;
        this.mConnectionType = ConnectionType.RELAXED;
        this.mResolutionAnchor.reset();
    }

    public void resetSolverVariable(Cache cache) {
        SolverVariable solverVariable = this.e;
        if (solverVariable == null) {
            this.e = new SolverVariable(SolverVariable.Type.UNRESTRICTED, (String) null);
        } else {
            solverVariable.reset();
        }
    }

    public void setConnectionCreator(int i) {
        this.mConnectionCreator = i;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.mConnectionType = connectionType;
    }

    public void setGoneMargin(int i) {
        if (isConnected()) {
            this.d = i;
        }
    }

    public void setMargin(int i) {
        if (isConnected()) {
            this.mMargin = i;
        }
    }

    public void setStrength(Strength strength) {
        if (isConnected()) {
            this.mStrength = strength;
        }
    }

    public String toString() {
        return this.a.getDebugName() + ":" + this.b.toString();
    }
}
