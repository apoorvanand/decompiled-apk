package com.razorpay;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

final class r$_Y_ implements ViewTreeObserver.OnGlobalLayoutListener {
    private View G__G_;
    private int Q_$2$;
    private FrameLayout.LayoutParams a_$P$;
    private int d__1_;

    r$_Y_() {
    }

    private r$_Y_(Activity activity) {
        this.G__G_ = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
        this.G__G_.getViewTreeObserver().addOnGlobalLayoutListener(this);
        this.a_$P$ = (FrameLayout.LayoutParams) this.G__G_.getLayoutParams();
        this.Q_$2$ = this.a_$P$.height;
    }

    static void a_$P$(Activity activity) {
        new r$_Y_(activity);
    }

    public final void onGlobalLayout() {
        Rect rect = new Rect();
        this.G__G_.getWindowVisibleDisplayFrame(rect);
        int i = rect.bottom - rect.top;
        if (i != this.d__1_) {
            int height = this.G__G_.getRootView().getHeight();
            if (height - i > height / 4) {
                this.a_$P$.height = i;
            } else {
                this.a_$P$.height = this.Q_$2$;
            }
            this.G__G_.requestLayout();
            this.d__1_ = i;
        }
    }
}
