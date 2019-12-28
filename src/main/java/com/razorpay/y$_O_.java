package com.razorpay;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

final class y$_O_ extends Animation {
    private int G__G_;
    private View a_$P$;
    private int d__1_;

    y$_O_(View view, int i) {
        this.a_$P$ = view;
        this.d__1_ = i;
        this.G__G_ = view.getWidth();
    }

    /* access modifiers changed from: protected */
    public final void applyTransformation(float f, Transformation transformation) {
        int i = this.G__G_;
        this.a_$P$.getLayoutParams().width = i + ((int) (((float) (this.d__1_ - i)) * f));
        this.a_$P$.requestLayout();
    }

    public final void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
    }

    public final boolean willChangeBounds() {
        return true;
    }
}
