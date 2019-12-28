package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.R;
import android.util.AttributeSet;

public class Barrier extends ConstraintHelper {
    public static final int BOTTOM = 3;
    public static final int END = 6;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int START = 5;
    public static final int TOP = 2;
    private androidx.constraintlayout.solver.widgets.Barrier mBarrier;
    private int mIndicatedType = 0;
    private int mResolvedType = 0;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void a(AttributeSet attributeSet) {
        super.a(attributeSet);
        this.mBarrier = new androidx.constraintlayout.solver.widgets.Barrier();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.mBarrier.setAllowsGoneWidget(obtainStyledAttributes.getBoolean(index, true));
                }
            }
        }
        this.d = this.mBarrier;
        validateParams();
    }

    public int getType() {
        return this.mIndicatedType;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        if (r6 == 6) goto L_0x0012;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        if (r6 == 6) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        if (r6 == 6) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setType(int r6) {
        /*
            r5 = this;
            r5.mIndicatedType = r6
            r5.mResolvedType = r6
            int r6 = android.os.Build.VERSION.SDK_INT
            r0 = 6
            r1 = 5
            r2 = 0
            r3 = 1
            r4 = 17
            if (r6 >= r4) goto L_0x001a
            int r6 = r5.mIndicatedType
            if (r6 != r1) goto L_0x0015
        L_0x0012:
            r5.mResolvedType = r2
            goto L_0x003d
        L_0x0015:
            if (r6 != r0) goto L_0x003d
        L_0x0017:
            r5.mResolvedType = r3
            goto L_0x003d
        L_0x001a:
            android.content.res.Resources r6 = r5.getResources()
            android.content.res.Configuration r6 = r6.getConfiguration()
            int r6 = r6.getLayoutDirection()
            if (r3 != r6) goto L_0x002a
            r6 = 1
            goto L_0x002b
        L_0x002a:
            r6 = 0
        L_0x002b:
            if (r6 == 0) goto L_0x0035
            int r6 = r5.mIndicatedType
            if (r6 != r1) goto L_0x0032
            goto L_0x0017
        L_0x0032:
            if (r6 != r0) goto L_0x003d
            goto L_0x0012
        L_0x0035:
            int r6 = r5.mIndicatedType
            if (r6 != r1) goto L_0x003a
            goto L_0x0012
        L_0x003a:
            if (r6 != r0) goto L_0x003d
            goto L_0x0017
        L_0x003d:
            androidx.constraintlayout.solver.widgets.Barrier r6 = r5.mBarrier
            int r0 = r5.mResolvedType
            r6.setBarrierType(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.Barrier.setType(int):void");
    }
}
