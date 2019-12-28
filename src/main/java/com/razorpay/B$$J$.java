package com.razorpay;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

final class B$$J$ implements Animation.AnimationListener {
    private ViewGroup G__G_;
    private float Q_$2$;
    private View R$$r_;
    private int a_$P$;
    private String b__J_;
    private Context d__1_;

    public B$$J$(Context context, ViewGroup viewGroup) {
        this(context, viewGroup, (String) null);
    }

    public B$$J$(Context context, ViewGroup viewGroup, String str) {
        int i;
        String str2;
        this.b__J_ = str;
        this.d__1_ = context;
        this.G__G_ = viewGroup;
        DisplayMetrics displayMetrics = this.d__1_.getResources().getDisplayMetrics();
        this.Q_$2$ = ((float) displayMetrics.widthPixels) / displayMetrics.density;
        this.a_$P$ = (int) TypedValue.applyDimension(1, 4.0f, this.d__1_.getResources().getDisplayMetrics());
        this.R$$r_ = new View(this.d__1_);
        this.R$$r_.setLayoutParams(new RelativeLayout.LayoutParams(0, this.a_$P$));
        if (TextUtils.isEmpty(this.b__J_)) {
            int identifier = Build.VERSION.SDK_INT >= 21 ? 16843829 : this.d__1_.getResources().getIdentifier("colorAccent", "attr", this.d__1_.getPackageName());
            TypedValue typedValue = new TypedValue();
            if (this.d__1_.getTheme().resolveAttribute(identifier, typedValue, true)) {
                i = typedValue.data;
                float[] fArr = new float[3];
                Color.colorToHSV(i, fArr);
                fArr[2] = fArr[2] * 0.8f;
                GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{i, Color.HSVToColor(fArr)});
                gradientDrawable.setCornerRadius(0.0f);
                this.R$$r_.setBackgroundDrawable(gradientDrawable);
                this.G__G_.addView(this.R$$r_);
            }
            str2 = "#4aa3df";
        } else {
            str2 = this.b__J_;
        }
        i = Color.parseColor(str2);
        float[] fArr2 = new float[3];
        Color.colorToHSV(i, fArr2);
        fArr2[2] = fArr2[2] * 0.8f;
        GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{i, Color.HSVToColor(fArr2)});
        gradientDrawable2.setCornerRadius(0.0f);
        this.R$$r_.setBackgroundDrawable(gradientDrawable2);
        this.G__G_.addView(this.R$$r_);
    }

    private void R$$r_() {
        y$_O_ y__o_ = new y$_O_(this.R$$r_, (int) TypedValue.applyDimension(1, (float) ((int) this.Q_$2$), this.d__1_.getResources().getDisplayMetrics()));
        y__o_.setDuration(200);
        this.R$$r_.startAnimation(y__o_);
        y__o_.setAnimationListener(new Animation.AnimationListener() {
            public final void onAnimationEnd(Animation animation) {
                B$$J$.this.R$$r_(0, 10);
            }

            public final void onAnimationRepeat(Animation animation) {
            }

            public final void onAnimationStart(Animation animation) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void R$$r_(int i, int i2) {
        y$_O_ y__o_ = new y$_O_(this.R$$r_, (int) TypedValue.applyDimension(1, (float) ((int) ((this.Q_$2$ * ((float) i)) / 100.0f)), this.d__1_.getResources().getDisplayMetrics()));
        y__o_.setDuration((long) i2);
        this.R$$r_.startAnimation(y__o_);
        y__o_.setAnimationListener(this);
    }

    /* access modifiers changed from: package-private */
    public final void G__G_(int i) {
        if (i == 100) {
            R$$r_();
        } else {
            R$$r_(i, 500);
        }
    }

    /* access modifiers changed from: package-private */
    public final void Q_$2$() {
        R$$r_();
    }

    public final void onAnimationEnd(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationStart(Animation animation) {
    }
}
