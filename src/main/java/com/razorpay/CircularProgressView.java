package com.razorpay;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

class CircularProgressView extends View {
    private float B$$W$;
    private float D$_X_;
    private int E$_6$;
    /* access modifiers changed from: private */
    public float E$_j$;
    private Paint G__G_;
    private AnimatorSet J$_0_;
    private float K$$z$;
    private ValueAnimator L__R$;
    private int O_$B_;
    private boolean Q_$2$;
    private boolean R$$r_;
    private int Y$_o$;
    private int a_$P$ = 0;
    private int b__J_;
    private int c__C_;
    private RectF d__1_;
    /* access modifiers changed from: private */
    public float f$_G$;
    private ValueAnimator g__v_;
    private int l_$w$;
    /* access modifiers changed from: private */
    public float l__d$;
    /* access modifiers changed from: private */
    public float r$_Y_;

    public CircularProgressView(Context context) {
        super(context);
        Q_$2$(context);
    }

    public CircularProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Q_$2$(context);
    }

    public CircularProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Q_$2$(context);
    }

    private void G__G_() {
        this.G__G_.setColor(this.E$_6$);
        this.G__G_.setStyle(Paint.Style.STROKE);
        this.G__G_.setStrokeWidth((float) this.b__J_);
        this.G__G_.setStrokeCap(Paint.Cap.BUTT);
    }

    private void G__G_(Context context) {
        getResources();
        this.D$_X_ = 0.0f;
        this.B$$W$ = 100.0f;
        this.b__J_ = (int) TypedValue.applyDimension(1, 3.0f, context.getResources().getDisplayMetrics());
        this.Q_$2$ = true;
        this.R$$r_ = true;
        this.K$$z$ = -90.0f;
        this.f$_G$ = this.K$$z$;
        this.E$_6$ = Color.parseColor("#4aa3df");
        this.c__C_ = 4000;
        this.l_$w$ = 5000;
        this.O_$B_ = 500;
        this.Y$_o$ = 3;
    }

    private void Q_$2$() {
        ValueAnimator valueAnimator = this.L__R$;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.L__R$ = null;
        }
        ValueAnimator valueAnimator2 = this.g__v_;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
            this.g__v_ = null;
        }
        AnimatorSet animatorSet = this.J$_0_;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.J$_0_ = null;
        }
    }

    private void Q_$2$(Context context) {
        G__G_(context);
        this.G__G_ = new Paint(1);
        G__G_();
        this.d__1_ = new RectF();
    }

    private void R$$r_() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        RectF rectF = this.d__1_;
        int i = this.b__J_;
        int i2 = this.a_$P$;
        rectF.set((float) (paddingLeft + i), (float) (paddingTop + i), (float) ((i2 - paddingLeft) - i), (float) ((i2 - paddingTop) - i));
    }

    private AnimatorSet d__1_(float f) {
        int i = this.Y$_o$;
        final float f2 = ((((float) (i - 1)) * 360.0f) / ((float) i)) + 15.0f;
        final float f3 = ((f2 - 15.0f) * f) - 0.049804688f;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{15.0f, f2});
        ofFloat.setDuration((long) ((this.c__C_ / this.Y$_o$) / 2));
        ofFloat.setInterpolator(new DecelerateInterpolator(1.0f));
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = CircularProgressView.this.r$_Y_ = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CircularProgressView.this.invalidate();
            }
        });
        int i2 = this.Y$_o$;
        float f4 = (0.5f + f) * 720.0f;
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{(f * 720.0f) / ((float) i2), f4 / ((float) i2)});
        ofFloat2.setDuration((long) ((this.c__C_ / this.Y$_o$) / 2));
        ofFloat2.setInterpolator(new LinearInterpolator());
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = CircularProgressView.this.E$_j$ = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            }
        });
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(new float[]{f3, (f3 + f2) - 15.0f});
        ofFloat3.setDuration((long) ((this.c__C_ / this.Y$_o$) / 2));
        ofFloat3.setInterpolator(new DecelerateInterpolator(1.0f));
        ofFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = CircularProgressView.this.f$_G$ = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CircularProgressView circularProgressView = CircularProgressView.this;
                float unused2 = circularProgressView.r$_Y_ = (f2 - circularProgressView.f$_G$) + f3;
                CircularProgressView.this.invalidate();
            }
        });
        int i3 = this.Y$_o$;
        ValueAnimator ofFloat4 = ValueAnimator.ofFloat(new float[]{f4 / ((float) i3), ((f + 1.0f) * 720.0f) / ((float) i3)});
        ofFloat4.setDuration((long) ((this.c__C_ / this.Y$_o$) / 2));
        ofFloat4.setInterpolator(new LinearInterpolator());
        ofFloat4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = CircularProgressView.this.E$_j$ = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.play(ofFloat3).with(ofFloat4).after(ofFloat2);
        return animatorSet;
    }

    public final void a_$P$() {
        ValueAnimator valueAnimator = this.L__R$;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.L__R$.cancel();
        }
        ValueAnimator valueAnimator2 = this.g__v_;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            this.g__v_.cancel();
        }
        AnimatorSet animatorSet = this.J$_0_;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.J$_0_.cancel();
        }
        int i = 0;
        if (!this.Q_$2$) {
            this.f$_G$ = this.K$$z$;
            float f = this.f$_G$;
            this.L__R$ = ValueAnimator.ofFloat(new float[]{f, f + 360.0f});
            this.L__R$.setDuration((long) this.l_$w$);
            this.L__R$.setInterpolator(new DecelerateInterpolator(2.0f));
            this.L__R$.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float unused = CircularProgressView.this.f$_G$ = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    CircularProgressView.this.invalidate();
                }
            });
            this.L__R$.start();
            this.l__d$ = 0.0f;
            this.g__v_ = ValueAnimator.ofFloat(new float[]{this.l__d$, this.D$_X_});
            this.g__v_.setDuration((long) this.O_$B_);
            this.g__v_.setInterpolator(new LinearInterpolator());
            this.g__v_.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float unused = CircularProgressView.this.l__d$ = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    CircularProgressView.this.invalidate();
                }
            });
            this.g__v_.start();
            return;
        }
        this.r$_Y_ = 15.0f;
        this.J$_0_ = new AnimatorSet();
        AnimatorSet animatorSet2 = null;
        while (i < this.Y$_o$) {
            AnimatorSet d__1_2 = d__1_((float) i);
            AnimatorSet.Builder play = this.J$_0_.play(d__1_2);
            if (animatorSet2 != null) {
                play.after(animatorSet2);
            }
            i++;
            animatorSet2 = d__1_2;
        }
        this.J$_0_.addListener(new AnimatorListenerAdapter() {
            private boolean G__G_ = false;

            public final void onAnimationCancel(Animator animator) {
                this.G__G_ = true;
            }

            public final void onAnimationEnd(Animator animator) {
                if (!this.G__G_) {
                    CircularProgressView.this.a_$P$();
                }
            }
        });
        this.J$_0_.start();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.R$$r_) {
            a_$P$();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Q_$2$();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = ((isInEditMode() ? this.D$_X_ : this.l__d$) / this.B$$W$) * 360.0f;
        if (!this.Q_$2$) {
            canvas.drawArc(this.d__1_, this.f$_G$, f, false, this.G__G_);
        } else {
            canvas.drawArc(this.d__1_, this.f$_G$ + this.E$_j$, this.r$_Y_, false, this.G__G_);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int measuredWidth = getMeasuredWidth() - paddingLeft;
        int measuredHeight = getMeasuredHeight() - paddingTop;
        if (measuredWidth >= measuredHeight) {
            measuredWidth = measuredHeight;
        }
        this.a_$P$ = measuredWidth;
        int i3 = this.a_$P$;
        setMeasuredDimension(paddingLeft + i3, i3 + paddingTop);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i >= i2) {
            i = i2;
        }
        this.a_$P$ = i;
        R$$r_();
    }

    public void setColor(int i) {
        this.E$_6$ = i;
        G__G_();
        invalidate();
    }

    public void setIndeterminate(boolean z) {
        boolean z2 = this.Q_$2$ == z;
        this.Q_$2$ = z;
        if (z2) {
            a_$P$();
        }
    }

    public void setMaxProgress(float f) {
        this.B$$W$ = f;
        invalidate();
    }

    public void setProgress(float f) {
        this.D$_X_ = f;
        if (!this.Q_$2$) {
            ValueAnimator valueAnimator = this.g__v_;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.g__v_.cancel();
            }
            this.g__v_ = ValueAnimator.ofFloat(new float[]{this.l__d$, f});
            this.g__v_.setDuration((long) this.O_$B_);
            this.g__v_.setInterpolator(new LinearInterpolator());
            this.g__v_.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float unused = CircularProgressView.this.l__d$ = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    CircularProgressView.this.invalidate();
                }
            });
            this.g__v_.start();
        }
        invalidate();
    }

    public void setThickness(int i) {
        this.b__J_ = i;
        G__G_();
        R$$r_();
        invalidate();
    }

    public void setVisibility(int i) {
        int visibility = getVisibility();
        super.setVisibility(i);
        if (i == visibility) {
            return;
        }
        if (i == 0) {
            a_$P$();
        } else if (i == 8 || i == 4) {
            Q_$2$();
        }
    }
}
