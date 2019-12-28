package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

public class ConstraintTableLayout extends ConstraintWidgetContainer {
    public static final int ALIGN_CENTER = 0;
    private static final int ALIGN_FULL = 3;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    private ArrayList<Guideline> mHorizontalGuidelines = new ArrayList<>();
    private ArrayList<HorizontalSlice> mHorizontalSlices = new ArrayList<>();
    private int mNumCols = 0;
    private int mNumRows = 0;
    private int mPadding = 8;
    private boolean mVerticalGrowth = true;
    private ArrayList<Guideline> mVerticalGuidelines = new ArrayList<>();
    private ArrayList<VerticalSlice> mVerticalSlices = new ArrayList<>();
    private LinearSystem system = null;

    class HorizontalSlice {
        ConstraintWidget a;
        ConstraintWidget b;

        HorizontalSlice() {
        }
    }

    class VerticalSlice {
        ConstraintWidget a;
        ConstraintWidget b;
        int c = 1;

        VerticalSlice() {
        }
    }

    public ConstraintTableLayout() {
    }

    public ConstraintTableLayout(int i, int i2) {
        super(i, i2);
    }

    public ConstraintTableLayout(int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
    }

    private void setChildrenConnections() {
        ConstraintAnchor.Type type;
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor.Type type2;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor.Strength strength;
        ConstraintAnchor constraintAnchor3;
        int size = this.aj.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.aj.get(i2);
            int containerItemSkip = i + constraintWidget.getContainerItemSkip();
            int i3 = this.mNumCols;
            int i4 = containerItemSkip % i3;
            HorizontalSlice horizontalSlice = this.mHorizontalSlices.get(containerItemSkip / i3);
            VerticalSlice verticalSlice = this.mVerticalSlices.get(i4);
            ConstraintWidget constraintWidget2 = verticalSlice.a;
            ConstraintWidget constraintWidget3 = verticalSlice.b;
            ConstraintWidget constraintWidget4 = horizontalSlice.a;
            ConstraintWidget constraintWidget5 = horizontalSlice.b;
            constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT), this.mPadding);
            if (constraintWidget3 instanceof Guideline) {
                constraintAnchor = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT);
                type = ConstraintAnchor.Type.LEFT;
            } else {
                constraintAnchor = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT);
                type = ConstraintAnchor.Type.RIGHT;
            }
            constraintAnchor.connect(constraintWidget3.getAnchor(type), this.mPadding);
            switch (verticalSlice.c) {
                case 1:
                    constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT).setStrength(ConstraintAnchor.Strength.STRONG);
                    constraintAnchor3 = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT);
                    strength = ConstraintAnchor.Strength.WEAK;
                    break;
                case 2:
                    constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT).setStrength(ConstraintAnchor.Strength.WEAK);
                    constraintAnchor3 = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT);
                    strength = ConstraintAnchor.Strength.STRONG;
                    break;
                case 3:
                    constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                    break;
            }
            constraintAnchor3.setStrength(strength);
            constraintWidget.getAnchor(ConstraintAnchor.Type.TOP).connect(constraintWidget4.getAnchor(ConstraintAnchor.Type.TOP), this.mPadding);
            if (constraintWidget5 instanceof Guideline) {
                constraintAnchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM);
                type2 = ConstraintAnchor.Type.TOP;
            } else {
                constraintAnchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM);
                type2 = ConstraintAnchor.Type.BOTTOM;
            }
            constraintAnchor2.connect(constraintWidget5.getAnchor(type2), this.mPadding);
            i = containerItemSkip + 1;
        }
    }

    private void setHorizontalSlices() {
        this.mHorizontalSlices.clear();
        float f = 100.0f / ((float) this.mNumRows);
        ConstraintWidget constraintWidget = this;
        float f2 = f;
        for (int i = 0; i < this.mNumRows; i++) {
            HorizontalSlice horizontalSlice = new HorizontalSlice();
            horizontalSlice.a = constraintWidget;
            if (i < this.mNumRows - 1) {
                Guideline guideline = new Guideline();
                guideline.setOrientation(0);
                guideline.setParent(this);
                guideline.setGuidePercent((int) f2);
                f2 += f;
                horizontalSlice.b = guideline;
                this.mHorizontalGuidelines.add(guideline);
            } else {
                horizontalSlice.b = this;
            }
            constraintWidget = horizontalSlice.b;
            this.mHorizontalSlices.add(horizontalSlice);
        }
        updateDebugSolverNames();
    }

    private void setVerticalSlices() {
        this.mVerticalSlices.clear();
        float f = 100.0f / ((float) this.mNumCols);
        ConstraintWidget constraintWidget = this;
        float f2 = f;
        for (int i = 0; i < this.mNumCols; i++) {
            VerticalSlice verticalSlice = new VerticalSlice();
            verticalSlice.a = constraintWidget;
            if (i < this.mNumCols - 1) {
                Guideline guideline = new Guideline();
                guideline.setOrientation(1);
                guideline.setParent(this);
                guideline.setGuidePercent((int) f2);
                f2 += f;
                verticalSlice.b = guideline;
                this.mVerticalGuidelines.add(guideline);
            } else {
                verticalSlice.b = this;
            }
            constraintWidget = verticalSlice.b;
            this.mVerticalSlices.add(verticalSlice);
        }
        updateDebugSolverNames();
    }

    private void updateDebugSolverNames() {
        if (this.system != null) {
            int size = this.mVerticalGuidelines.size();
            for (int i = 0; i < size; i++) {
                LinearSystem linearSystem = this.system;
                this.mVerticalGuidelines.get(i).setDebugSolverName(linearSystem, getDebugName() + ".VG" + i);
            }
            int size2 = this.mHorizontalGuidelines.size();
            for (int i2 = 0; i2 < size2; i2++) {
                LinearSystem linearSystem2 = this.system;
                this.mHorizontalGuidelines.get(i2).setDebugSolverName(linearSystem2, getDebugName() + ".HG" + i2);
            }
        }
    }

    public void addToSolver(LinearSystem linearSystem) {
        super.addToSolver(linearSystem);
        int size = this.aj.size();
        if (size != 0) {
            setTableDimensions();
            if (linearSystem == this.Z) {
                int size2 = this.mVerticalGuidelines.size();
                int i = 0;
                while (true) {
                    boolean z = true;
                    if (i >= size2) {
                        break;
                    }
                    Guideline guideline = this.mVerticalGuidelines.get(i);
                    if (getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        z = false;
                    }
                    guideline.setPositionRelaxed(z);
                    guideline.addToSolver(linearSystem);
                    i++;
                }
                int size3 = this.mHorizontalGuidelines.size();
                for (int i2 = 0; i2 < size3; i2++) {
                    Guideline guideline2 = this.mHorizontalGuidelines.get(i2);
                    guideline2.setPositionRelaxed(getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                    guideline2.addToSolver(linearSystem);
                }
                for (int i3 = 0; i3 < size; i3++) {
                    ((ConstraintWidget) this.aj.get(i3)).addToSolver(linearSystem);
                }
            }
        }
    }

    public void computeGuidelinesPercentPositions() {
        int size = this.mVerticalGuidelines.size();
        for (int i = 0; i < size; i++) {
            this.mVerticalGuidelines.get(i).c();
        }
        int size2 = this.mHorizontalGuidelines.size();
        for (int i2 = 0; i2 < size2; i2++) {
            this.mHorizontalGuidelines.get(i2).c();
        }
    }

    public void cycleColumnAlignment(int i) {
        int i2;
        VerticalSlice verticalSlice = this.mVerticalSlices.get(i);
        switch (verticalSlice.c) {
            case 0:
                i2 = 2;
                break;
            case 1:
                i2 = 0;
                break;
            case 2:
                i2 = 1;
                break;
            default:
                setChildrenConnections();
        }
        verticalSlice.c = i2;
        setChildrenConnections();
    }

    public String getColumnAlignmentRepresentation(int i) {
        VerticalSlice verticalSlice = this.mVerticalSlices.get(i);
        return verticalSlice.c == 1 ? "L" : verticalSlice.c == 0 ? "C" : verticalSlice.c == 3 ? "F" : verticalSlice.c == 2 ? "R" : "!";
    }

    public String getColumnsAlignmentRepresentation() {
        StringBuilder sb;
        String str;
        int size = this.mVerticalSlices.size();
        String str2 = "";
        for (int i = 0; i < size; i++) {
            VerticalSlice verticalSlice = this.mVerticalSlices.get(i);
            if (verticalSlice.c == 1) {
                sb = new StringBuilder();
                sb.append(str2);
                str = "L";
            } else if (verticalSlice.c == 0) {
                sb = new StringBuilder();
                sb.append(str2);
                str = "C";
            } else if (verticalSlice.c == 3) {
                sb = new StringBuilder();
                sb.append(str2);
                str = "F";
            } else if (verticalSlice.c == 2) {
                sb = new StringBuilder();
                sb.append(str2);
                str = "R";
            }
            sb.append(str);
            str2 = sb.toString();
        }
        return str2;
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        return this.mHorizontalGuidelines;
    }

    public int getNumCols() {
        return this.mNumCols;
    }

    public int getNumRows() {
        return this.mNumRows;
    }

    public int getPadding() {
        return this.mPadding;
    }

    public String getType() {
        return "ConstraintTableLayout";
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        return this.mVerticalGuidelines;
    }

    public boolean handlesInternalConstraints() {
        return true;
    }

    public boolean isVerticalGrowth() {
        return this.mVerticalGrowth;
    }

    public void setColumnAlignment(int i, int i2) {
        if (i < this.mVerticalSlices.size()) {
            this.mVerticalSlices.get(i).c = i2;
            setChildrenConnections();
        }
    }

    public void setColumnAlignment(String str) {
        int i;
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == 'L') {
                i = 1;
            } else {
                if (charAt != 'C') {
                    if (charAt == 'F') {
                        i = 3;
                    } else if (charAt == 'R') {
                        i = 2;
                    }
                }
                setColumnAlignment(i2, 0);
            }
            setColumnAlignment(i2, i);
        }
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.system = linearSystem;
        super.setDebugSolverName(linearSystem, str);
        updateDebugSolverNames();
    }

    public void setNumCols(int i) {
        if (this.mVerticalGrowth && this.mNumCols != i) {
            this.mNumCols = i;
            setVerticalSlices();
            setTableDimensions();
        }
    }

    public void setNumRows(int i) {
        if (!this.mVerticalGrowth && this.mNumCols != i) {
            this.mNumRows = i;
            setHorizontalSlices();
            setTableDimensions();
        }
    }

    public void setPadding(int i) {
        if (i > 1) {
            this.mPadding = i;
        }
    }

    public void setTableDimensions() {
        int size = this.aj.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += ((ConstraintWidget) this.aj.get(i2)).getContainerItemSkip();
        }
        int i3 = size + i;
        if (this.mVerticalGrowth) {
            if (this.mNumCols == 0) {
                setNumCols(1);
            }
            int i4 = this.mNumCols;
            int i5 = i3 / i4;
            if (i4 * i5 < i3) {
                i5++;
            }
            if (this.mNumRows != i5 || this.mVerticalGuidelines.size() != this.mNumCols - 1) {
                this.mNumRows = i5;
                setHorizontalSlices();
            } else {
                return;
            }
        } else {
            if (this.mNumRows == 0) {
                setNumRows(1);
            }
            int i6 = this.mNumRows;
            int i7 = i3 / i6;
            if (i6 * i7 < i3) {
                i7++;
            }
            if (this.mNumCols != i7 || this.mHorizontalGuidelines.size() != this.mNumRows - 1) {
                this.mNumCols = i7;
                setVerticalSlices();
            } else {
                return;
            }
        }
        setChildrenConnections();
    }

    public void setVerticalGrowth(boolean z) {
        this.mVerticalGrowth = z;
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        super.updateFromSolver(linearSystem);
        if (linearSystem == this.Z) {
            int size = this.mVerticalGuidelines.size();
            for (int i = 0; i < size; i++) {
                this.mVerticalGuidelines.get(i).updateFromSolver(linearSystem);
            }
            int size2 = this.mHorizontalGuidelines.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.mHorizontalGuidelines.get(i2).updateFromSolver(linearSystem);
            }
        }
    }
}
