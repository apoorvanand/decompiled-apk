package androidx.constraintlayout.solver.widgets;

import java.util.Arrays;

public class Helper extends ConstraintWidget {
    protected ConstraintWidget[] Z = new ConstraintWidget[4];
    protected int aa = 0;

    public void add(ConstraintWidget constraintWidget) {
        int i = this.aa + 1;
        ConstraintWidget[] constraintWidgetArr = this.Z;
        if (i > constraintWidgetArr.length) {
            this.Z = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr, constraintWidgetArr.length * 2);
        }
        ConstraintWidget[] constraintWidgetArr2 = this.Z;
        int i2 = this.aa;
        constraintWidgetArr2[i2] = constraintWidget;
        this.aa = i2 + 1;
    }

    public void removeAllIds() {
        this.aa = 0;
    }
}
