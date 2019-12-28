package androidx.swiperefreshlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.content.ContextCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ListViewCompat;
import com.google.common.primitives.Ints;

public class SwipeRefreshLayout extends ViewGroup implements NestedScrollingChild, NestedScrollingParent {
    private static final int ALPHA_ANIMATION_DURATION = 300;
    private static final int ANIMATE_TO_START_DURATION = 200;
    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    private static final int CIRCLE_BG_LIGHT = -328966;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    public static final int DEFAULT = 1;
    private static final int DEFAULT_CIRCLE_TARGET = 64;
    public static final int DEFAULT_SLINGSHOT_DISTANCE = -1;
    private static final float DRAG_RATE = 0.5f;
    private static final int INVALID_POINTER = -1;
    public static final int LARGE = 0;
    private static final int[] LAYOUT_ATTRS = {16842766};
    private static final String LOG_TAG = "SwipeRefreshLayout";
    private static final int MAX_ALPHA = 255;
    private static final float MAX_PROGRESS_ANGLE = 0.8f;
    private static final int SCALE_DOWN_DURATION = 150;
    private static final int STARTING_PROGRESS_ALPHA = 76;
    OnRefreshListener a;
    boolean b;
    int c;
    boolean d;
    CircleImageView e;
    protected int f;
    float g;
    protected int h;
    int i;
    int j;
    CircularProgressDrawable k;
    boolean l;
    boolean m;
    private int mActivePointerId;
    private Animation mAlphaMaxAnimation;
    private Animation mAlphaStartAnimation;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private OnChildScrollUpCallback mChildScrollUpCallback;
    private int mCircleDiameter;
    private int mCircleViewIndex;
    private final DecelerateInterpolator mDecelerateInterpolator;
    private float mInitialDownY;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    private int mMediumAnimationDuration;
    private boolean mNestedScrollInProgress;
    private final NestedScrollingChildHelper mNestedScrollingChildHelper;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private final int[] mParentOffsetInWindow;
    private final int[] mParentScrollConsumed;
    private Animation.AnimationListener mRefreshListener;
    private boolean mReturningToStart;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private Animation mScaleDownToStartAnimation;
    private View mTarget;
    private float mTotalDragDistance;
    private float mTotalUnconsumed;
    private int mTouchSlop;

    public interface OnChildScrollUpCallback {
        boolean canChildScrollUp(@NonNull SwipeRefreshLayout swipeRefreshLayout, @Nullable View view);
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public SwipeRefreshLayout(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public SwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = false;
        this.mTotalDragDistance = -1.0f;
        this.mParentScrollConsumed = new int[2];
        this.mParentOffsetInWindow = new int[2];
        this.mActivePointerId = -1;
        this.mCircleViewIndex = -1;
        this.mRefreshListener = new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                if (SwipeRefreshLayout.this.b) {
                    SwipeRefreshLayout.this.k.setAlpha(255);
                    SwipeRefreshLayout.this.k.start();
                    if (SwipeRefreshLayout.this.l && SwipeRefreshLayout.this.a != null) {
                        SwipeRefreshLayout.this.a.onRefresh();
                    }
                    SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
                    swipeRefreshLayout.c = swipeRefreshLayout.e.getTop();
                    return;
                }
                SwipeRefreshLayout.this.a();
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        };
        this.mAnimateToCorrectPosition = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                SwipeRefreshLayout.this.setTargetOffsetTopAndBottom((SwipeRefreshLayout.this.f + ((int) (((float) ((!SwipeRefreshLayout.this.m ? SwipeRefreshLayout.this.i - Math.abs(SwipeRefreshLayout.this.h) : SwipeRefreshLayout.this.i) - SwipeRefreshLayout.this.f)) * f))) - SwipeRefreshLayout.this.e.getTop());
                SwipeRefreshLayout.this.k.setArrowScale(1.0f - f);
            }
        };
        this.mAnimateToStartPosition = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                SwipeRefreshLayout.this.a(f);
            }
        };
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMediumAnimationDuration = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.mDecelerateInterpolator = new DecelerateInterpolator(DECELERATE_INTERPOLATION_FACTOR);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mCircleDiameter = (int) (displayMetrics.density * 40.0f);
        createProgressView();
        setChildrenDrawingOrderEnabled(true);
        this.i = (int) (displayMetrics.density * 64.0f);
        this.mTotalDragDistance = (float) this.i;
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        this.mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        int i2 = -this.mCircleDiameter;
        this.c = i2;
        this.h = i2;
        a(1.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, LAYOUT_ATTRS);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
    }

    private void animateOffsetToCorrectPosition(int i2, Animation.AnimationListener animationListener) {
        this.f = i2;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(200);
        this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.e.setAnimationListener(animationListener);
        }
        this.e.clearAnimation();
        this.e.startAnimation(this.mAnimateToCorrectPosition);
    }

    private void animateOffsetToStartPosition(int i2, Animation.AnimationListener animationListener) {
        if (this.d) {
            startScaleDownReturnToStartAnimation(i2, animationListener);
            return;
        }
        this.f = i2;
        this.mAnimateToStartPosition.reset();
        this.mAnimateToStartPosition.setDuration(200);
        this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.e.setAnimationListener(animationListener);
        }
        this.e.clearAnimation();
        this.e.startAnimation(this.mAnimateToStartPosition);
    }

    private void createProgressView() {
        this.e = new CircleImageView(getContext(), CIRCLE_BG_LIGHT);
        this.k = new CircularProgressDrawable(getContext());
        this.k.setStyle(1);
        this.e.setImageDrawable(this.k);
        this.e.setVisibility(8);
        addView(this.e);
    }

    private void ensureTarget() {
        if (this.mTarget == null) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt = getChildAt(i2);
                if (!childAt.equals(this.e)) {
                    this.mTarget = childAt;
                    return;
                }
            }
        }
    }

    private void finishSpinner(float f2) {
        if (f2 > this.mTotalDragDistance) {
            setRefreshing(true, true);
            return;
        }
        this.b = false;
        this.k.setStartEndTrim(0.0f, 0.0f);
        AnonymousClass5 r0 = null;
        if (!this.d) {
            r0 = new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    if (!SwipeRefreshLayout.this.d) {
                        SwipeRefreshLayout.this.a((Animation.AnimationListener) null);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            };
        }
        animateOffsetToStartPosition(this.c, r0);
        this.k.setArrowEnabled(false);
    }

    private boolean isAnimationRunning(Animation animation) {
        return animation != null && animation.hasStarted() && !animation.hasEnded();
    }

    private void moveSpinner(float f2) {
        this.k.setArrowEnabled(true);
        float min = Math.min(1.0f, Math.abs(f2 / this.mTotalDragDistance));
        float max = (((float) Math.max(((double) min) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float abs = Math.abs(f2) - this.mTotalDragDistance;
        int i2 = this.j;
        if (i2 <= 0) {
            i2 = this.m ? this.i - this.h : this.i;
        }
        float f3 = (float) i2;
        double max2 = (double) (Math.max(0.0f, Math.min(abs, f3 * DECELERATE_INTERPOLATION_FACTOR) / f3) / 4.0f);
        float pow = ((float) (max2 - Math.pow(max2, 2.0d))) * DECELERATE_INTERPOLATION_FACTOR;
        int i3 = this.h + ((int) ((f3 * min) + (f3 * pow * DECELERATE_INTERPOLATION_FACTOR)));
        if (this.e.getVisibility() != 0) {
            this.e.setVisibility(0);
        }
        if (!this.d) {
            this.e.setScaleX(1.0f);
            this.e.setScaleY(1.0f);
        }
        if (this.d) {
            setAnimationProgress(Math.min(1.0f, f2 / this.mTotalDragDistance));
        }
        if (f2 < this.mTotalDragDistance) {
            if (this.k.getAlpha() > 76 && !isAnimationRunning(this.mAlphaStartAnimation)) {
                startProgressAlphaStartAnimation();
            }
        } else if (this.k.getAlpha() < 255 && !isAnimationRunning(this.mAlphaMaxAnimation)) {
            startProgressAlphaMaxAnimation();
        }
        this.k.setStartEndTrim(0.0f, Math.min(MAX_PROGRESS_ANGLE, max * MAX_PROGRESS_ANGLE));
        this.k.setArrowScale(Math.min(1.0f, max));
        this.k.setProgressRotation((((max * 0.4f) - 16.0f) + (pow * DECELERATE_INTERPOLATION_FACTOR)) * DRAG_RATE);
        setTargetOffsetTopAndBottom(i3 - this.c);
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            this.mActivePointerId = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }

    private void setColorViewAlpha(int i2) {
        this.e.getBackground().setAlpha(i2);
        this.k.setAlpha(i2);
    }

    private void setRefreshing(boolean z, boolean z2) {
        if (this.b != z) {
            this.l = z2;
            ensureTarget();
            this.b = z;
            if (this.b) {
                animateOffsetToCorrectPosition(this.c, this.mRefreshListener);
            } else {
                a(this.mRefreshListener);
            }
        }
    }

    private Animation startAlphaAnimation(final int i2, final int i3) {
        AnonymousClass4 r0 = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                CircularProgressDrawable circularProgressDrawable = SwipeRefreshLayout.this.k;
                int i = i2;
                circularProgressDrawable.setAlpha((int) (((float) i) + (((float) (i3 - i)) * f)));
            }
        };
        r0.setDuration(300);
        this.e.setAnimationListener((Animation.AnimationListener) null);
        this.e.clearAnimation();
        this.e.startAnimation(r0);
        return r0;
    }

    private void startDragging(float f2) {
        float f3 = this.mInitialDownY;
        int i2 = this.mTouchSlop;
        if (f2 - f3 > ((float) i2) && !this.mIsBeingDragged) {
            this.mInitialMotionY = f3 + ((float) i2);
            this.mIsBeingDragged = true;
            this.k.setAlpha(76);
        }
    }

    private void startProgressAlphaMaxAnimation() {
        this.mAlphaMaxAnimation = startAlphaAnimation(this.k.getAlpha(), 255);
    }

    private void startProgressAlphaStartAnimation() {
        this.mAlphaStartAnimation = startAlphaAnimation(this.k.getAlpha(), 76);
    }

    private void startScaleDownReturnToStartAnimation(int i2, Animation.AnimationListener animationListener) {
        this.f = i2;
        this.g = this.e.getScaleX();
        this.mScaleDownToStartAnimation = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                SwipeRefreshLayout.this.setAnimationProgress(SwipeRefreshLayout.this.g + ((-SwipeRefreshLayout.this.g) * f));
                SwipeRefreshLayout.this.a(f);
            }
        };
        this.mScaleDownToStartAnimation.setDuration(150);
        if (animationListener != null) {
            this.e.setAnimationListener(animationListener);
        }
        this.e.clearAnimation();
        this.e.startAnimation(this.mScaleDownToStartAnimation);
    }

    private void startScaleUpAnimation(Animation.AnimationListener animationListener) {
        this.e.setVisibility(0);
        this.k.setAlpha(255);
        this.mScaleAnimation = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                SwipeRefreshLayout.this.setAnimationProgress(f);
            }
        };
        this.mScaleAnimation.setDuration((long) this.mMediumAnimationDuration);
        if (animationListener != null) {
            this.e.setAnimationListener(animationListener);
        }
        this.e.clearAnimation();
        this.e.startAnimation(this.mScaleAnimation);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.e.clearAnimation();
        this.k.stop();
        this.e.setVisibility(8);
        setColorViewAlpha(255);
        if (this.d) {
            setAnimationProgress(0.0f);
        } else {
            setTargetOffsetTopAndBottom(this.h - this.c);
        }
        this.c = this.e.getTop();
    }

    /* access modifiers changed from: package-private */
    public void a(float f2) {
        int i2 = this.f;
        setTargetOffsetTopAndBottom((i2 + ((int) (((float) (this.h - i2)) * f2))) - this.e.getTop());
    }

    /* access modifiers changed from: package-private */
    public void a(Animation.AnimationListener animationListener) {
        this.mScaleDownAnimation = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                SwipeRefreshLayout.this.setAnimationProgress(1.0f - f);
            }
        };
        this.mScaleDownAnimation.setDuration(150);
        this.e.setAnimationListener(animationListener);
        this.e.clearAnimation();
        this.e.startAnimation(this.mScaleDownAnimation);
    }

    public boolean canChildScrollUp() {
        OnChildScrollUpCallback onChildScrollUpCallback = this.mChildScrollUpCallback;
        if (onChildScrollUpCallback != null) {
            return onChildScrollUpCallback.canChildScrollUp(this, this.mTarget);
        }
        View view = this.mTarget;
        return view instanceof ListView ? ListViewCompat.canScrollList((ListView) view, -1) : view.canScrollVertically(-1);
    }

    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return this.mNestedScrollingChildHelper.dispatchNestedFling(f2, f3, z);
    }

    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreFling(f2, f3);
    }

    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(i2, i3, iArr, iArr2);
    }

    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return this.mNestedScrollingChildHelper.dispatchNestedScroll(i2, i3, i4, i5, iArr);
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i2, int i3) {
        int i4 = this.mCircleViewIndex;
        return i4 < 0 ? i3 : i3 == i2 + -1 ? i4 : i3 >= i4 ? i3 + 1 : i3;
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    public int getProgressCircleDiameter() {
        return this.mCircleDiameter;
    }

    public int getProgressViewEndOffset() {
        return this.i;
    }

    public int getProgressViewStartOffset() {
        return this.h;
    }

    public boolean hasNestedScrollingParent() {
        return this.mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    public boolean isNestedScrollingEnabled() {
        return this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    public boolean isRefreshing() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ensureTarget();
        int actionMasked = motionEvent.getActionMasked();
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || canChildScrollUp() || this.b || this.mNestedScrollInProgress) {
            return false;
        }
        if (actionMasked != 6) {
            switch (actionMasked) {
                case 0:
                    setTargetOffsetTopAndBottom(this.h - this.e.getTop());
                    this.mActivePointerId = motionEvent.getPointerId(0);
                    this.mIsBeingDragged = false;
                    int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (findPointerIndex >= 0) {
                        this.mInitialDownY = motionEvent.getY(findPointerIndex);
                        break;
                    } else {
                        return false;
                    }
                case 1:
                case 3:
                    this.mIsBeingDragged = false;
                    this.mActivePointerId = -1;
                    break;
                case 2:
                    int i2 = this.mActivePointerId;
                    if (i2 != -1) {
                        int findPointerIndex2 = motionEvent.findPointerIndex(i2);
                        if (findPointerIndex2 >= 0) {
                            startDragging(motionEvent.getY(findPointerIndex2));
                            break;
                        } else {
                            return false;
                        }
                    } else {
                        Log.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
                        return false;
                    }
            }
        } else {
            onSecondaryPointerUp(motionEvent);
        }
        return this.mIsBeingDragged;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() != 0) {
            if (this.mTarget == null) {
                ensureTarget();
            }
            View view = this.mTarget;
            if (view != null) {
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
                int measuredWidth2 = this.e.getMeasuredWidth();
                int measuredHeight2 = this.e.getMeasuredHeight();
                int i6 = measuredWidth / 2;
                int i7 = measuredWidth2 / 2;
                int i8 = this.c;
                this.e.layout(i6 - i7, i8, i6 + i7, measuredHeight2 + i8);
            }
        }
    }

    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.mTarget == null) {
            ensureTarget();
        }
        View view = this.mTarget;
        if (view != null) {
            view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), Ints.MAX_POWER_OF_TWO), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), Ints.MAX_POWER_OF_TWO));
            this.e.measure(View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, Ints.MAX_POWER_OF_TWO), View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, Ints.MAX_POWER_OF_TWO));
            this.mCircleViewIndex = -1;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                if (getChildAt(i4) == this.e) {
                    this.mCircleViewIndex = i4;
                    return;
                }
            }
        }
    }

    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return dispatchNestedFling(f2, f3, z);
    }

    public boolean onNestedPreFling(View view, float f2, float f3) {
        return dispatchNestedPreFling(f2, f3);
    }

    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        if (i3 > 0) {
            float f2 = this.mTotalUnconsumed;
            if (f2 > 0.0f) {
                float f3 = (float) i3;
                if (f3 > f2) {
                    iArr[1] = i3 - ((int) f2);
                    this.mTotalUnconsumed = 0.0f;
                } else {
                    this.mTotalUnconsumed = f2 - f3;
                    iArr[1] = i3;
                }
                moveSpinner(this.mTotalUnconsumed);
            }
        }
        if (this.m && i3 > 0 && this.mTotalUnconsumed == 0.0f && Math.abs(i3 - iArr[1]) > 0) {
            this.e.setVisibility(8);
        }
        int[] iArr2 = this.mParentScrollConsumed;
        if (dispatchNestedPreScroll(i2 - iArr[0], i3 - iArr[1], iArr2, (int[]) null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        dispatchNestedScroll(i2, i3, i4, i5, this.mParentOffsetInWindow);
        int i6 = i5 + this.mParentOffsetInWindow[1];
        if (i6 < 0 && !canChildScrollUp()) {
            this.mTotalUnconsumed += (float) Math.abs(i6);
            moveSpinner(this.mTotalUnconsumed);
        }
    }

    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(view, view2, i2);
        startNestedScroll(i2 & 2);
        this.mTotalUnconsumed = 0.0f;
        this.mNestedScrollInProgress = true;
    }

    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return isEnabled() && !this.mReturningToStart && !this.b && (i2 & 2) != 0;
    }

    public void onStopNestedScroll(View view) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(view);
        this.mNestedScrollInProgress = false;
        float f2 = this.mTotalUnconsumed;
        if (f2 > 0.0f) {
            finishSpinner(f2);
            this.mTotalUnconsumed = 0.0f;
        }
        stopNestedScroll();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || canChildScrollUp() || this.b || this.mNestedScrollInProgress) {
            return false;
        }
        switch (actionMasked) {
            case 0:
                this.mActivePointerId = motionEvent.getPointerId(0);
                this.mIsBeingDragged = false;
                return true;
            case 1:
                int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                if (findPointerIndex < 0) {
                    Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.mIsBeingDragged) {
                    float y = (motionEvent.getY(findPointerIndex) - this.mInitialMotionY) * DRAG_RATE;
                    this.mIsBeingDragged = false;
                    finishSpinner(y);
                }
                this.mActivePointerId = -1;
                return false;
            case 2:
                int findPointerIndex2 = motionEvent.findPointerIndex(this.mActivePointerId);
                if (findPointerIndex2 < 0) {
                    Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                float y2 = motionEvent.getY(findPointerIndex2);
                startDragging(y2);
                if (!this.mIsBeingDragged) {
                    return true;
                }
                float f2 = (y2 - this.mInitialMotionY) * DRAG_RATE;
                if (f2 <= 0.0f) {
                    return false;
                }
                moveSpinner(f2);
                return true;
            case 3:
                return false;
            case 5:
                int actionIndex = motionEvent.getActionIndex();
                if (actionIndex < 0) {
                    Log.e(LOG_TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                    return false;
                }
                this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                return true;
            case 6:
                onSecondaryPointerUp(motionEvent);
                return true;
            default:
                return true;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (Build.VERSION.SDK_INT >= 21 || !(this.mTarget instanceof AbsListView)) {
            View view = this.mTarget;
            if (view == null || ViewCompat.isNestedScrollingEnabled(view)) {
                super.requestDisallowInterceptTouchEvent(z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setAnimationProgress(float f2) {
        this.e.setScaleX(f2);
        this.e.setScaleY(f2);
    }

    @Deprecated
    public void setColorScheme(@ColorRes int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(@ColorInt int... iArr) {
        ensureTarget();
        this.k.setColorSchemeColors(iArr);
    }

    public void setColorSchemeResources(@ColorRes int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr2[i2] = ContextCompat.getColor(context, iArr[i2]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i2) {
        this.mTotalDragDistance = (float) i2;
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!z) {
            a();
        }
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.mNestedScrollingChildHelper.setNestedScrollingEnabled(z);
    }

    public void setOnChildScrollUpCallback(@Nullable OnChildScrollUpCallback onChildScrollUpCallback) {
        this.mChildScrollUpCallback = onChildScrollUpCallback;
    }

    public void setOnRefreshListener(@Nullable OnRefreshListener onRefreshListener) {
        this.a = onRefreshListener;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i2) {
        setProgressBackgroundColorSchemeResource(i2);
    }

    public void setProgressBackgroundColorSchemeColor(@ColorInt int i2) {
        this.e.setBackgroundColor(i2);
    }

    public void setProgressBackgroundColorSchemeResource(@ColorRes int i2) {
        setProgressBackgroundColorSchemeColor(ContextCompat.getColor(getContext(), i2));
    }

    public void setProgressViewEndTarget(boolean z, int i2) {
        this.i = i2;
        this.d = z;
        this.e.invalidate();
    }

    public void setProgressViewOffset(boolean z, int i2, int i3) {
        this.d = z;
        this.h = i2;
        this.i = i3;
        this.m = true;
        a();
        this.b = false;
    }

    public void setRefreshing(boolean z) {
        if (!z || this.b == z) {
            setRefreshing(z, false);
            return;
        }
        this.b = z;
        setTargetOffsetTopAndBottom((!this.m ? this.i + this.h : this.i) - this.c);
        this.l = false;
        startScaleUpAnimation(this.mRefreshListener);
    }

    public void setSize(int i2) {
        if (i2 == 0 || i2 == 1) {
            this.mCircleDiameter = (int) (getResources().getDisplayMetrics().density * (i2 == 0 ? 56.0f : 40.0f));
            this.e.setImageDrawable((Drawable) null);
            this.k.setStyle(i2);
            this.e.setImageDrawable(this.k);
        }
    }

    public void setSlingshotDistance(@Px int i2) {
        this.j = i2;
    }

    /* access modifiers changed from: package-private */
    public void setTargetOffsetTopAndBottom(int i2) {
        this.e.bringToFront();
        ViewCompat.offsetTopAndBottom(this.e, i2);
        this.c = this.e.getTop();
    }

    public boolean startNestedScroll(int i2) {
        return this.mNestedScrollingChildHelper.startNestedScroll(i2);
    }

    public void stopNestedScroll() {
        this.mNestedScrollingChildHelper.stopNestedScroll();
    }
}
