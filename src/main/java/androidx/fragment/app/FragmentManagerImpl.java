package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;
import androidx.core.util.DebugUtils;
import androidx.core.util.LogWriter;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelStore;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class FragmentManagerImpl extends FragmentManager implements LayoutInflater.Factory2 {
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static final Interpolator F = new DecelerateInterpolator(2.5f);
    static final Interpolator G = new DecelerateInterpolator(1.5f);
    static final Interpolator H = new AccelerateInterpolator(2.5f);
    static final Interpolator I = new AccelerateInterpolator(1.5f);
    static boolean a = false;
    static Field q;
    Bundle A = null;
    SparseArray<Parcelable> B = null;
    ArrayList<StartEnterTransitionListener> C;
    FragmentManagerNonConfig D;
    Runnable E = new Runnable() {
        public void run() {
            FragmentManagerImpl.this.execPendingActions();
        }
    };
    ArrayList<OpGenerator> b;
    boolean c;
    int d = 0;
    final ArrayList<Fragment> e = new ArrayList<>();
    SparseArray<Fragment> f;
    ArrayList<BackStackRecord> g;
    ArrayList<Fragment> h;
    ArrayList<BackStackRecord> i;
    ArrayList<Integer> j;
    ArrayList<FragmentManager.OnBackStackChangedListener> k;
    int l = 0;
    FragmentHostCallback m;
    private final CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> mLifecycleCallbacks = new CopyOnWriteArrayList<>();
    FragmentContainer n;
    Fragment o;
    @Nullable
    Fragment p;
    boolean r;
    boolean s;
    boolean t;
    boolean u;
    String v;
    boolean w;
    ArrayList<BackStackRecord> x;
    ArrayList<Boolean> y;
    ArrayList<Fragment> z;

    private static class AnimateOnHWLayerIfNeededListener extends AnimationListenerWrapper {
        View a;

        AnimateOnHWLayerIfNeededListener(View view, Animation.AnimationListener animationListener) {
            super(animationListener);
            this.a = view;
        }

        @CallSuper
        public void onAnimationEnd(Animation animation) {
            if (ViewCompat.isAttachedToWindow(this.a) || Build.VERSION.SDK_INT >= 24) {
                this.a.post(new Runnable() {
                    public void run() {
                        AnimateOnHWLayerIfNeededListener.this.a.setLayerType(0, (Paint) null);
                    }
                });
            } else {
                this.a.setLayerType(0, (Paint) null);
            }
            super.onAnimationEnd(animation);
        }
    }

    private static class AnimationListenerWrapper implements Animation.AnimationListener {
        private final Animation.AnimationListener mWrapped;

        AnimationListenerWrapper(Animation.AnimationListener animationListener) {
            this.mWrapped = animationListener;
        }

        @CallSuper
        public void onAnimationEnd(Animation animation) {
            Animation.AnimationListener animationListener = this.mWrapped;
            if (animationListener != null) {
                animationListener.onAnimationEnd(animation);
            }
        }

        @CallSuper
        public void onAnimationRepeat(Animation animation) {
            Animation.AnimationListener animationListener = this.mWrapped;
            if (animationListener != null) {
                animationListener.onAnimationRepeat(animation);
            }
        }

        @CallSuper
        public void onAnimationStart(Animation animation) {
            Animation.AnimationListener animationListener = this.mWrapped;
            if (animationListener != null) {
                animationListener.onAnimationStart(animation);
            }
        }
    }

    private static class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        AnimationOrAnimator(Animator animator2) {
            this.animation = null;
            this.animator = animator2;
            if (animator2 == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }

        AnimationOrAnimator(Animation animation2) {
            this.animation = animation2;
            this.animator = null;
            if (animation2 == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }
    }

    private static class AnimatorOnHWLayerIfNeededListener extends AnimatorListenerAdapter {
        View a;

        AnimatorOnHWLayerIfNeededListener(View view) {
            this.a = view;
        }

        public void onAnimationEnd(Animator animator) {
            this.a.setLayerType(0, (Paint) null);
            animator.removeListener(this);
        }

        public void onAnimationStart(Animator animator) {
            this.a.setLayerType(2, (Paint) null);
        }
    }

    private static class EndViewTransitionAnimator extends AnimationSet implements Runnable {
        private boolean mAnimating = true;
        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        EndViewTransitionAnimator(@NonNull Animation animation, @NonNull ViewGroup viewGroup, @NonNull View view) {
            super(false);
            this.mParent = viewGroup;
            this.mChild = view;
            addAnimation(animation);
            this.mParent.post(this);
        }

        public boolean getTransformation(long j, Transformation transformation) {
            this.mAnimating = true;
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j, transformation)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        public boolean getTransformation(long j, Transformation transformation, float f) {
            this.mAnimating = true;
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        public void run() {
            if (this.mEnded || !this.mAnimating) {
                this.mParent.endViewTransition(this.mChild);
                this.mTransitionEnded = true;
                return;
            }
            this.mAnimating = false;
            this.mParent.post(this);
        }
    }

    private static final class FragmentLifecycleCallbacksHolder {
        final FragmentManager.FragmentLifecycleCallbacks a;
        final boolean b;

        FragmentLifecycleCallbacksHolder(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
            this.a = fragmentLifecycleCallbacks;
            this.b = z;
        }
    }

    static class FragmentTag {
        public static final int[] Fragment = {16842755, 16842960, 16842961};
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;

        private FragmentTag() {
        }
    }

    interface OpGenerator {
        boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2);
    }

    private class PopBackStackState implements OpGenerator {
        final String a;
        final int b;
        final int c;

        PopBackStackState(String str, int i, int i2) {
            this.a = str;
            this.b = i;
            this.c = i2;
        }

        public boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
            FragmentManager peekChildFragmentManager;
            if (FragmentManagerImpl.this.p != null && this.b < 0 && this.a == null && (peekChildFragmentManager = FragmentManagerImpl.this.p.peekChildFragmentManager()) != null && peekChildFragmentManager.popBackStackImmediate()) {
                return false;
            }
            return FragmentManagerImpl.this.a(arrayList, arrayList2, this.a, this.b, this.c);
        }
    }

    static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
        final boolean a;
        final BackStackRecord b;
        private int mNumPostponed;

        StartEnterTransitionListener(BackStackRecord backStackRecord, boolean z) {
            this.a = z;
            this.b = backStackRecord;
        }

        public void cancelTransaction() {
            this.b.a.a(this.b, this.a, false, false);
        }

        public void completeTransaction() {
            boolean z = this.mNumPostponed > 0;
            FragmentManagerImpl fragmentManagerImpl = this.b.a;
            int size = fragmentManagerImpl.e.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = fragmentManagerImpl.e.get(i);
                fragment.setOnStartEnterTransitionListener((Fragment.OnStartEnterTransitionListener) null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            this.b.a.a(this.b, this.a, !z, true);
        }

        public boolean isReady() {
            return this.mNumPostponed == 0;
        }

        public void onStartEnterTransition() {
            this.mNumPostponed--;
            if (this.mNumPostponed == 0) {
                this.b.a.d();
            }
        }

        public void startListening() {
            this.mNumPostponed++;
        }
    }

    FragmentManagerImpl() {
    }

    static AnimationOrAnimator a(Context context, float f2, float f3) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f3);
        alphaAnimation.setInterpolator(G);
        alphaAnimation.setDuration(220);
        return new AnimationOrAnimator((Animation) alphaAnimation);
    }

    static AnimationOrAnimator a(Context context, float f2, float f3, float f4, float f5) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(F);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f4, f5);
        alphaAnimation.setInterpolator(G);
        alphaAnimation.setDuration(220);
        animationSet.addAnimation(alphaAnimation);
        return new AnimationOrAnimator((Animation) animationSet);
    }

    static boolean a(Animator animator) {
        if (animator == null) {
            return false;
        }
        if (animator instanceof ValueAnimator) {
            PropertyValuesHolder[] values = ((ValueAnimator) animator).getValues();
            for (PropertyValuesHolder propertyName : values) {
                if ("alpha".equals(propertyName.getPropertyName())) {
                    return true;
                }
            }
        } else if (animator instanceof AnimatorSet) {
            ArrayList<Animator> childAnimations = ((AnimatorSet) animator).getChildAnimations();
            for (int i2 = 0; i2 < childAnimations.size(); i2++) {
                if (a(childAnimations.get(i2))) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean a(View view, AnimationOrAnimator animationOrAnimator) {
        return view != null && animationOrAnimator != null && Build.VERSION.SDK_INT >= 19 && view.getLayerType() == 0 && ViewCompat.hasOverlappingRendering(view) && a(animationOrAnimator);
    }

    static boolean a(AnimationOrAnimator animationOrAnimator) {
        if (animationOrAnimator.animation instanceof AlphaAnimation) {
            return true;
        }
        if (!(animationOrAnimator.animation instanceof AnimationSet)) {
            return a(animationOrAnimator.animator);
        }
        List<Animation> animations = ((AnimationSet) animationOrAnimator.animation).getAnimations();
        for (int i2 = 0; i2 < animations.size(); i2++) {
            if (animations.get(i2) instanceof AlphaAnimation) {
                return true;
            }
        }
        return false;
    }

    private void addAddedFragments(ArraySet<Fragment> arraySet) {
        int i2 = this.l;
        if (i2 >= 1) {
            int min = Math.min(i2, 3);
            int size = this.e.size();
            for (int i3 = 0; i3 < size; i3++) {
                Fragment fragment = this.e.get(i3);
                if (fragment.mState < min) {
                    a(fragment, min, fragment.getNextAnim(), fragment.getNextTransition(), false);
                    if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                        arraySet.add(fragment);
                    }
                }
            }
        }
    }

    private void animateRemoveFragment(@NonNull final Fragment fragment, @NonNull AnimationOrAnimator animationOrAnimator, int i2) {
        final View view = fragment.mView;
        final ViewGroup viewGroup = fragment.mContainer;
        viewGroup.startViewTransition(view);
        fragment.setStateAfterAnimating(i2);
        if (animationOrAnimator.animation != null) {
            EndViewTransitionAnimator endViewTransitionAnimator = new EndViewTransitionAnimator(animationOrAnimator.animation, viewGroup, view);
            fragment.setAnimatingAway(fragment.mView);
            endViewTransitionAnimator.setAnimationListener(new AnimationListenerWrapper(getAnimationListener(endViewTransitionAnimator)) {
                public void onAnimationEnd(Animation animation) {
                    super.onAnimationEnd(animation);
                    viewGroup.post(new Runnable() {
                        public void run() {
                            if (fragment.getAnimatingAway() != null) {
                                fragment.setAnimatingAway((View) null);
                                FragmentManagerImpl.this.a(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                            }
                        }
                    });
                }
            });
            setHWLayerAnimListenerIfAlpha(view, animationOrAnimator);
            fragment.mView.startAnimation(endViewTransitionAnimator);
            return;
        }
        Animator animator = animationOrAnimator.animator;
        fragment.setAnimator(animationOrAnimator.animator);
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                viewGroup.endViewTransition(view);
                Animator animator2 = fragment.getAnimator();
                fragment.setAnimator((Animator) null);
                if (animator2 != null && viewGroup.indexOfChild(view) < 0) {
                    FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this;
                    Fragment fragment = fragment;
                    fragmentManagerImpl.a(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                }
            }
        });
        animator.setTarget(fragment.mView);
        setHWLayerAnimListenerIfAlpha(fragment.mView, animationOrAnimator);
        animator.start();
    }

    private void burpActive() {
        SparseArray<Fragment> sparseArray = this.f;
        if (sparseArray != null) {
            for (int size = sparseArray.size() - 1; size >= 0; size--) {
                if (this.f.valueAt(size) == null) {
                    SparseArray<Fragment> sparseArray2 = this.f;
                    sparseArray2.delete(sparseArray2.keyAt(size));
                }
            }
        }
    }

    private void checkStateLoss() {
        if (isStateSaved()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.v != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.v);
        }
    }

    private void cleanupExec() {
        this.c = false;
        this.y.clear();
        this.x.clear();
    }

    /* JADX INFO: finally extract failed */
    private void dispatchStateChange(int i2) {
        try {
            this.c = true;
            a(i2, false);
            this.c = false;
            execPendingActions();
        } catch (Throwable th) {
            this.c = false;
            throw th;
        }
    }

    private void endAnimatingAwayFragments() {
        SparseArray<Fragment> sparseArray = this.f;
        int size = sparseArray == null ? 0 : sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment valueAt = this.f.valueAt(i2);
            if (valueAt != null) {
                if (valueAt.getAnimatingAway() != null) {
                    int stateAfterAnimating = valueAt.getStateAfterAnimating();
                    View animatingAway = valueAt.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    valueAt.setAnimatingAway((View) null);
                    a(valueAt, stateAfterAnimating, 0, 0, false);
                } else if (valueAt.getAnimator() != null) {
                    valueAt.getAnimator().end();
                }
            }
        }
    }

    private void ensureExecReady(boolean z2) {
        if (this.c) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (this.m == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        } else if (Looper.myLooper() == this.m.c().getLooper()) {
            if (!z2) {
                checkStateLoss();
            }
            if (this.x == null) {
                this.x = new ArrayList<>();
                this.y = new ArrayList<>();
            }
            this.c = true;
            try {
                executePostponedTransaction((ArrayList<BackStackRecord>) null, (ArrayList<Boolean>) null);
            } finally {
                this.c = false;
            }
        } else {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
    }

    private static void executeOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            BackStackRecord backStackRecord = arrayList.get(i2);
            boolean z2 = true;
            if (arrayList2.get(i2).booleanValue()) {
                backStackRecord.a(-1);
                if (i2 != i3 - 1) {
                    z2 = false;
                }
                backStackRecord.b(z2);
            } else {
                backStackRecord.a(1);
                backStackRecord.a();
            }
            i2++;
        }
    }

    private void executeOpsTogether(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        int i4;
        ArrayList<BackStackRecord> arrayList3 = arrayList;
        ArrayList<Boolean> arrayList4 = arrayList2;
        int i5 = i2;
        int i6 = i3;
        boolean z2 = arrayList3.get(i5).t;
        ArrayList<Fragment> arrayList5 = this.z;
        if (arrayList5 == null) {
            this.z = new ArrayList<>();
        } else {
            arrayList5.clear();
        }
        this.z.addAll(this.e);
        Fragment primaryNavigationFragment = getPrimaryNavigationFragment();
        boolean z3 = false;
        for (int i7 = i5; i7 < i6; i7++) {
            BackStackRecord backStackRecord = arrayList3.get(i7);
            primaryNavigationFragment = !arrayList4.get(i7).booleanValue() ? backStackRecord.a(this.z, primaryNavigationFragment) : backStackRecord.b(this.z, primaryNavigationFragment);
            z3 = z3 || backStackRecord.i;
        }
        this.z.clear();
        if (!z2) {
            FragmentTransition.a(this, arrayList, arrayList2, i2, i3, false);
        }
        executeOps(arrayList, arrayList2, i2, i3);
        if (z2) {
            ArraySet arraySet = new ArraySet();
            addAddedFragments(arraySet);
            int postponePostponableTransactions = postponePostponableTransactions(arrayList, arrayList2, i2, i3, arraySet);
            makeRemovedFragmentsInvisible(arraySet);
            i4 = postponePostponableTransactions;
        } else {
            i4 = i6;
        }
        if (i4 != i5 && z2) {
            FragmentTransition.a(this, arrayList, arrayList2, i2, i4, true);
            a(this.l, true);
        }
        while (i5 < i6) {
            BackStackRecord backStackRecord2 = arrayList3.get(i5);
            if (arrayList4.get(i5).booleanValue() && backStackRecord2.m >= 0) {
                freeBackStackIndex(backStackRecord2.m);
                backStackRecord2.m = -1;
            }
            backStackRecord2.runOnCommitRunnables();
            i5++;
        }
        if (z3) {
            f();
        }
    }

    private void executePostponedTransaction(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        int indexOf;
        int indexOf2;
        ArrayList<StartEnterTransitionListener> arrayList3 = this.C;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            StartEnterTransitionListener startEnterTransitionListener = this.C.get(i2);
            if (arrayList == null || startEnterTransitionListener.a || (indexOf2 = arrayList.indexOf(startEnterTransitionListener.b)) == -1 || !arrayList2.get(indexOf2).booleanValue()) {
                if (startEnterTransitionListener.isReady() || (arrayList != null && startEnterTransitionListener.b.a(arrayList, 0, arrayList.size()))) {
                    this.C.remove(i2);
                    i2--;
                    size--;
                    if (arrayList == null || startEnterTransitionListener.a || (indexOf = arrayList.indexOf(startEnterTransitionListener.b)) == -1 || !arrayList2.get(indexOf).booleanValue()) {
                        startEnterTransitionListener.completeTransaction();
                    }
                }
                i2++;
            }
            startEnterTransitionListener.cancelTransaction();
            i2++;
        }
    }

    private Fragment findFragmentUnder(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        View view = fragment.mView;
        if (!(viewGroup == null || view == null)) {
            for (int indexOf = this.e.indexOf(fragment) - 1; indexOf >= 0; indexOf--) {
                Fragment fragment2 = this.e.get(indexOf);
                if (fragment2.mContainer == viewGroup && fragment2.mView != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    private void forcePostponedTransactions() {
        if (this.C != null) {
            while (!this.C.isEmpty()) {
                this.C.remove(0).completeTransaction();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean generateOpsForPendingActions(java.util.ArrayList<androidx.fragment.app.BackStackRecord> r5, java.util.ArrayList<java.lang.Boolean> r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.ArrayList<androidx.fragment.app.FragmentManagerImpl$OpGenerator> r0 = r4.b     // Catch:{ all -> 0x003c }
            r1 = 0
            if (r0 == 0) goto L_0x003a
            java.util.ArrayList<androidx.fragment.app.FragmentManagerImpl$OpGenerator> r0 = r4.b     // Catch:{ all -> 0x003c }
            int r0 = r0.size()     // Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x000f
            goto L_0x003a
        L_0x000f:
            java.util.ArrayList<androidx.fragment.app.FragmentManagerImpl$OpGenerator> r0 = r4.b     // Catch:{ all -> 0x003c }
            int r0 = r0.size()     // Catch:{ all -> 0x003c }
            r2 = 0
        L_0x0016:
            if (r1 >= r0) goto L_0x0028
            java.util.ArrayList<androidx.fragment.app.FragmentManagerImpl$OpGenerator> r3 = r4.b     // Catch:{ all -> 0x003c }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ all -> 0x003c }
            androidx.fragment.app.FragmentManagerImpl$OpGenerator r3 = (androidx.fragment.app.FragmentManagerImpl.OpGenerator) r3     // Catch:{ all -> 0x003c }
            boolean r3 = r3.generateOps(r5, r6)     // Catch:{ all -> 0x003c }
            r2 = r2 | r3
            int r1 = r1 + 1
            goto L_0x0016
        L_0x0028:
            java.util.ArrayList<androidx.fragment.app.FragmentManagerImpl$OpGenerator> r5 = r4.b     // Catch:{ all -> 0x003c }
            r5.clear()     // Catch:{ all -> 0x003c }
            androidx.fragment.app.FragmentHostCallback r5 = r4.m     // Catch:{ all -> 0x003c }
            android.os.Handler r5 = r5.c()     // Catch:{ all -> 0x003c }
            java.lang.Runnable r6 = r4.E     // Catch:{ all -> 0x003c }
            r5.removeCallbacks(r6)     // Catch:{ all -> 0x003c }
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            return r2
        L_0x003a:
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            return r1
        L_0x003c:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManagerImpl.generateOpsForPendingActions(java.util.ArrayList, java.util.ArrayList):boolean");
    }

    private static Animation.AnimationListener getAnimationListener(Animation animation) {
        String str;
        String str2;
        try {
            if (q == null) {
                q = Animation.class.getDeclaredField("mListener");
                q.setAccessible(true);
            }
            return (Animation.AnimationListener) q.get(animation);
        } catch (NoSuchFieldException e2) {
            e = e2;
            str2 = "FragmentManager";
            str = "No field with the name mListener is found in Animation class";
            Log.e(str2, str, e);
            return null;
        } catch (IllegalAccessException e3) {
            e = e3;
            str2 = "FragmentManager";
            str = "Cannot access Animation's mListener field";
            Log.e(str2, str, e);
            return null;
        }
    }

    private void makeRemovedFragmentsInvisible(ArraySet<Fragment> arraySet) {
        int size = arraySet.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment valueAt = arraySet.valueAt(i2);
            if (!valueAt.mAdded) {
                View view = valueAt.getView();
                valueAt.mPostponedAlpha = view.getAlpha();
                view.setAlpha(0.0f);
            }
        }
    }

    private boolean popBackStackImmediate(String str, int i2, int i3) {
        FragmentManager peekChildFragmentManager;
        execPendingActions();
        ensureExecReady(true);
        Fragment fragment = this.p;
        if (fragment != null && i2 < 0 && str == null && (peekChildFragmentManager = fragment.peekChildFragmentManager()) != null && peekChildFragmentManager.popBackStackImmediate()) {
            return true;
        }
        boolean a2 = a(this.x, this.y, str, i2, i3);
        if (a2) {
            this.c = true;
            try {
                removeRedundantOperationsAndExecute(this.x, this.y);
            } finally {
                cleanupExec();
            }
        }
        e();
        burpActive();
        return a2;
    }

    private int postponePostponableTransactions(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, ArraySet<Fragment> arraySet) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            BackStackRecord backStackRecord = arrayList.get(i5);
            boolean booleanValue = arrayList2.get(i5).booleanValue();
            if (backStackRecord.b() && !backStackRecord.a(arrayList, i5 + 1, i3)) {
                if (this.C == null) {
                    this.C = new ArrayList<>();
                }
                StartEnterTransitionListener startEnterTransitionListener = new StartEnterTransitionListener(backStackRecord, booleanValue);
                this.C.add(startEnterTransitionListener);
                backStackRecord.a((Fragment.OnStartEnterTransitionListener) startEnterTransitionListener);
                if (booleanValue) {
                    backStackRecord.a();
                } else {
                    backStackRecord.b(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, backStackRecord);
                }
                addAddedFragments(arraySet);
            }
        }
        return i4;
    }

    private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList != null && !arrayList.isEmpty()) {
            if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            executePostponedTransaction(arrayList, arrayList2);
            int size = arrayList.size();
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                if (!arrayList.get(i2).t) {
                    if (i3 != i2) {
                        executeOpsTogether(arrayList, arrayList2, i3, i2);
                    }
                    i3 = i2 + 1;
                    if (arrayList2.get(i2).booleanValue()) {
                        while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).t) {
                            i3++;
                        }
                    }
                    executeOpsTogether(arrayList, arrayList2, i2, i3);
                    i2 = i3 - 1;
                }
                i2++;
            }
            if (i3 != size) {
                executeOpsTogether(arrayList, arrayList2, i3, size);
            }
        }
    }

    public static int reverseTransit(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 == 4099) {
            return FragmentTransaction.TRANSIT_FRAGMENT_FADE;
        }
        if (i2 != 8194) {
            return 0;
        }
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    private static void setHWLayerAnimListenerIfAlpha(View view, AnimationOrAnimator animationOrAnimator) {
        if (view != null && animationOrAnimator != null && a(view, animationOrAnimator)) {
            if (animationOrAnimator.animator != null) {
                animationOrAnimator.animator.addListener(new AnimatorOnHWLayerIfNeededListener(view));
                return;
            }
            Animation.AnimationListener animationListener = getAnimationListener(animationOrAnimator.animation);
            view.setLayerType(2, (Paint) null);
            animationOrAnimator.animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(view, animationListener));
        }
    }

    private static void setRetaining(FragmentManagerNonConfig fragmentManagerNonConfig) {
        if (fragmentManagerNonConfig != null) {
            List<Fragment> a2 = fragmentManagerNonConfig.a();
            if (a2 != null) {
                for (Fragment fragment : a2) {
                    fragment.mRetaining = true;
                }
            }
            List<FragmentManagerNonConfig> b2 = fragmentManagerNonConfig.b();
            if (b2 != null) {
                for (FragmentManagerNonConfig retaining : b2) {
                    setRetaining(retaining);
                }
            }
        }
    }

    private void throwException(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
        FragmentHostCallback fragmentHostCallback = this.m;
        if (fragmentHostCallback != null) {
            try {
                fragmentHostCallback.onDump("  ", (FileDescriptor) null, printWriter, new String[0]);
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
            }
        } else {
            dump("  ", (FileDescriptor) null, printWriter, new String[0]);
        }
        throw runtimeException;
    }

    public static int transitToStyleIndex(int i2, boolean z2) {
        if (i2 == 4097) {
            return z2 ? 1 : 2;
        }
        if (i2 == 4099) {
            return z2 ? 5 : 6;
        }
        if (i2 != 8194) {
            return -1;
        }
        return z2 ? 3 : 4;
    }

    /* access modifiers changed from: package-private */
    public AnimationOrAnimator a(Fragment fragment, int i2, boolean z2, int i3) {
        int transitToStyleIndex;
        int nextAnim = fragment.getNextAnim();
        Animation onCreateAnimation = fragment.onCreateAnimation(i2, z2, nextAnim);
        if (onCreateAnimation != null) {
            return new AnimationOrAnimator(onCreateAnimation);
        }
        Animator onCreateAnimator = fragment.onCreateAnimator(i2, z2, nextAnim);
        if (onCreateAnimator != null) {
            return new AnimationOrAnimator(onCreateAnimator);
        }
        if (nextAnim != 0) {
            boolean equals = "anim".equals(this.m.b().getResources().getResourceTypeName(nextAnim));
            boolean z3 = false;
            if (equals) {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(this.m.b(), nextAnim);
                    if (loadAnimation != null) {
                        return new AnimationOrAnimator(loadAnimation);
                    }
                    z3 = true;
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                }
            }
            if (!z3) {
                try {
                    Animator loadAnimator = AnimatorInflater.loadAnimator(this.m.b(), nextAnim);
                    if (loadAnimator != null) {
                        return new AnimationOrAnimator(loadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (!equals) {
                        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.m.b(), nextAnim);
                        if (loadAnimation2 != null) {
                            return new AnimationOrAnimator(loadAnimation2);
                        }
                    } else {
                        throw e3;
                    }
                }
            }
        }
        if (i2 == 0 || (transitToStyleIndex = transitToStyleIndex(i2, z2)) < 0) {
            return null;
        }
        switch (transitToStyleIndex) {
            case 1:
                return a(this.m.b(), 1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return a(this.m.b(), 1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return a(this.m.b(), 0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return a(this.m.b(), 1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return a(this.m.b(), 0.0f, 1.0f);
            case 6:
                return a(this.m.b(), 1.0f, 0.0f);
            default:
                if (i3 == 0 && this.m.onHasWindowAnimations()) {
                    i3 = this.m.onGetWindowAnimations();
                }
                if (i3 == 0) {
                }
                return null;
        }
    }

    /* access modifiers changed from: package-private */
    public List<Fragment> a() {
        SparseArray<Fragment> sparseArray = this.f;
        if (sparseArray == null) {
            return null;
        }
        int size = sparseArray.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(this.f.valueAt(i2));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, boolean z2) {
        FragmentHostCallback fragmentHostCallback;
        if (this.m == null && i2 != 0) {
            throw new IllegalStateException("No activity");
        } else if (z2 || i2 != this.l) {
            this.l = i2;
            if (this.f != null) {
                int size = this.e.size();
                for (int i3 = 0; i3 < size; i3++) {
                    d(this.e.get(i3));
                }
                int size2 = this.f.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    Fragment valueAt = this.f.valueAt(i4);
                    if (valueAt != null && ((valueAt.mRemoving || valueAt.mDetached) && !valueAt.mIsNewlyAdded)) {
                        d(valueAt);
                    }
                }
                c();
                if (this.r && (fragmentHostCallback = this.m) != null && this.l == 4) {
                    fragmentHostCallback.onSupportInvalidateOptionsMenu();
                    this.r = false;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Parcelable parcelable, FragmentManagerNonConfig fragmentManagerNonConfig) {
        List<ViewModelStore> list;
        List<FragmentManagerNonConfig> list2;
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.a != null) {
                if (fragmentManagerNonConfig != null) {
                    List<Fragment> a2 = fragmentManagerNonConfig.a();
                    list2 = fragmentManagerNonConfig.b();
                    list = fragmentManagerNonConfig.c();
                    int size = a2 != null ? a2.size() : 0;
                    for (int i2 = 0; i2 < size; i2++) {
                        Fragment fragment = a2.get(i2);
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + fragment);
                        }
                        int i3 = 0;
                        while (i3 < fragmentManagerState.a.length && fragmentManagerState.a[i3].b != fragment.mIndex) {
                            i3++;
                        }
                        if (i3 == fragmentManagerState.a.length) {
                            throwException(new IllegalStateException("Could not find active fragment with index " + fragment.mIndex));
                        }
                        FragmentState fragmentState = fragmentManagerState.a[i3];
                        fragmentState.l = fragment;
                        fragment.mSavedViewState = null;
                        fragment.mBackStackNesting = 0;
                        fragment.mInLayout = false;
                        fragment.mAdded = false;
                        fragment.mTarget = null;
                        if (fragmentState.k != null) {
                            fragmentState.k.setClassLoader(this.m.b().getClassLoader());
                            fragment.mSavedViewState = fragmentState.k.getSparseParcelableArray("android:view_state");
                            fragment.mSavedFragmentState = fragmentState.k;
                        }
                    }
                } else {
                    list2 = null;
                    list = null;
                }
                this.f = new SparseArray<>(fragmentManagerState.a.length);
                int i4 = 0;
                while (i4 < fragmentManagerState.a.length) {
                    FragmentState fragmentState2 = fragmentManagerState.a[i4];
                    if (fragmentState2 != null) {
                        Fragment instantiate = fragmentState2.instantiate(this.m, this.n, this.o, (list2 == null || i4 >= list2.size()) ? null : list2.get(i4), (list == null || i4 >= list.size()) ? null : list.get(i4));
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: active #" + i4 + ": " + instantiate);
                        }
                        this.f.put(instantiate.mIndex, instantiate);
                        fragmentState2.l = null;
                    }
                    i4++;
                }
                if (fragmentManagerNonConfig != null) {
                    List<Fragment> a3 = fragmentManagerNonConfig.a();
                    int size2 = a3 != null ? a3.size() : 0;
                    for (int i5 = 0; i5 < size2; i5++) {
                        Fragment fragment2 = a3.get(i5);
                        if (fragment2.mTargetIndex >= 0) {
                            fragment2.mTarget = this.f.get(fragment2.mTargetIndex);
                            if (fragment2.mTarget == null) {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + fragment2 + " target no longer exists: " + fragment2.mTargetIndex);
                            }
                        }
                    }
                }
                this.e.clear();
                if (fragmentManagerState.b != null) {
                    int i6 = 0;
                    while (i6 < fragmentManagerState.b.length) {
                        Fragment fragment3 = this.f.get(fragmentManagerState.b[i6]);
                        if (fragment3 == null) {
                            throwException(new IllegalStateException("No instantiated fragment for index #" + fragmentManagerState.b[i6]));
                        }
                        fragment3.mAdded = true;
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: added #" + i6 + ": " + fragment3);
                        }
                        if (!this.e.contains(fragment3)) {
                            synchronized (this.e) {
                                this.e.add(fragment3);
                            }
                            i6++;
                        } else {
                            throw new IllegalStateException("Already added!");
                        }
                    }
                }
                if (fragmentManagerState.c != null) {
                    this.g = new ArrayList<>(fragmentManagerState.c.length);
                    for (int i7 = 0; i7 < fragmentManagerState.c.length; i7++) {
                        BackStackRecord instantiate2 = fragmentManagerState.c[i7].instantiate(this);
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: back stack #" + i7 + " (index " + instantiate2.m + "): " + instantiate2);
                            PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
                            instantiate2.dump("  ", printWriter, false);
                            printWriter.close();
                        }
                        this.g.add(instantiate2);
                        if (instantiate2.m >= 0) {
                            setBackStackIndex(instantiate2.m, instantiate2);
                        }
                    }
                } else {
                    this.g = null;
                }
                if (fragmentManagerState.d >= 0) {
                    this.p = this.f.get(fragmentManagerState.d);
                }
                this.d = fragmentManagerState.e;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(BackStackRecord backStackRecord) {
        if (this.g == null) {
            this.g = new ArrayList<>();
        }
        this.g.add(backStackRecord);
    }

    /* access modifiers changed from: package-private */
    public void a(BackStackRecord backStackRecord, boolean z2, boolean z3, boolean z4) {
        if (z2) {
            backStackRecord.b(z4);
        } else {
            backStackRecord.a();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(backStackRecord);
        arrayList2.add(Boolean.valueOf(z2));
        if (z3) {
            FragmentTransition.a(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z4) {
            a(this.l, true);
        }
        SparseArray<Fragment> sparseArray = this.f;
        if (sparseArray != null) {
            int size = sparseArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                Fragment valueAt = this.f.valueAt(i2);
                if (valueAt != null && valueAt.mView != null && valueAt.mIsNewlyAdded && backStackRecord.b(valueAt.mContainerId)) {
                    if (valueAt.mPostponedAlpha > 0.0f) {
                        valueAt.mView.setAlpha(valueAt.mPostponedAlpha);
                    }
                    if (z4) {
                        valueAt.mPostponedAlpha = 0.0f;
                    } else {
                        valueAt.mPostponedAlpha = -1.0f;
                        valueAt.mIsNewlyAdded = false;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Fragment fragment) {
        a(fragment, this.l, 0, 0, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: int} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        r1 = r15.getResources().getResourceName(r7.mContainerId);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x020b, code lost:
        r1 = "unknown";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x023d, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0318, code lost:
        if (r11 >= 3) goto L_0x033a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x031c, code lost:
        if (a == false) goto L_0x0334;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x031e, code lost:
        android.util.Log.v("FragmentManager", "movefrom STARTED: " + r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0334, code lost:
        r15.performStop();
        d(r15, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x033a, code lost:
        if (r11 >= 2) goto L_0x03c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x033e, code lost:
        if (a == false) goto L_0x0356;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0340, code lost:
        android.util.Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0358, code lost:
        if (r7.mView == null) goto L_0x0369;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0360, code lost:
        if (r6.m.onShouldSaveFragmentState(r15) == false) goto L_0x0369;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0364, code lost:
        if (r7.mSavedViewState != null) goto L_0x0369;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0366, code lost:
        g(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0369, code lost:
        r15.performDestroyView();
        e(r15, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0371, code lost:
        if (r7.mView == null) goto L_0x03b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0375, code lost:
        if (r7.mContainer == null) goto L_0x03b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0377, code lost:
        r7.mContainer.endViewTransition(r7.mView);
        r7.mView.clearAnimation();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0386, code lost:
        if (r6.l <= 0) goto L_0x03a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x038a, code lost:
        if (r6.u != false) goto L_0x03a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0392, code lost:
        if (r7.mView.getVisibility() != 0) goto L_0x03a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0398, code lost:
        if (r7.mPostponedAlpha < 0.0f) goto L_0x03a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x039a, code lost:
        r0 = a(r15, r17, false, r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x03a3, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x03a4, code lost:
        r7.mPostponedAlpha = 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x03a6, code lost:
        if (r0 == null) goto L_0x03ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x03a8, code lost:
        animateRemoveFragment(r15, r0, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x03ab, code lost:
        r7.mContainer.removeView(r7.mView);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x03b2, code lost:
        r7.mContainer = null;
        r7.mView = null;
        r7.mViewLifecycleOwner = null;
        r7.mViewLifecycleOwnerLiveData.setValue(null);
        r7.mInnerView = null;
        r7.mInLayout = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x03c1, code lost:
        if (r11 >= 1) goto L_0x0437;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x03c5, code lost:
        if (r6.u == false) goto L_0x03e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x03cb, code lost:
        if (r15.getAnimatingAway() == null) goto L_0x03d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x03cd, code lost:
        r0 = r15.getAnimatingAway();
        r15.setAnimatingAway((android.view.View) null);
        r0.clearAnimation();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x03dc, code lost:
        if (r15.getAnimator() == null) goto L_0x03e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x03de, code lost:
        r0 = r15.getAnimator();
        r15.setAnimator((android.animation.Animator) null);
        r0.cancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x03ec, code lost:
        if (r15.getAnimatingAway() != null) goto L_0x0433;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x03f2, code lost:
        if (r15.getAnimator() == null) goto L_0x03f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x03f7, code lost:
        if (a == false) goto L_0x040f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x03f9, code lost:
        android.util.Log.v("FragmentManager", "movefrom CREATED: " + r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0411, code lost:
        if (r7.mRetaining != false) goto L_0x041a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x0413, code lost:
        r15.performDestroy();
        f(r15, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x041a, code lost:
        r7.mState = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x041c, code lost:
        r15.performDetach();
        g(r15, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x0422, code lost:
        if (r19 != false) goto L_0x0437;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0426, code lost:
        if (r7.mRetaining != false) goto L_0x042c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x0428, code lost:
        f(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x042c, code lost:
        r7.mHost = null;
        r7.mParentFragment = null;
        r7.mFragmentManager = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0433, code lost:
        r15.setStateAfterAnimating(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01a6, code lost:
        b(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01a9, code lost:
        if (r11 <= 1) goto L_0x02a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01ad, code lost:
        if (a == false) goto L_0x01c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01af, code lost:
        android.util.Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01c7, code lost:
        if (r7.mFromLayout != false) goto L_0x028b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01cb, code lost:
        if (r7.mContainerId == 0) goto L_0x023d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01d0, code lost:
        if (r7.mContainerId != -1) goto L_0x01f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01d2, code lost:
        throwException(new java.lang.IllegalArgumentException("Cannot create fragment " + r15 + " for a container view with no id"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01f0, code lost:
        r0 = (android.view.ViewGroup) r6.n.onFindViewById(r7.mContainerId);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01fa, code lost:
        if (r0 != null) goto L_0x023e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01fe, code lost:
        if (r7.mRestored != false) goto L_0x023e;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x043c  */
    /* JADX WARNING: Removed duplicated region for block: B:211:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(androidx.fragment.app.Fragment r15, int r16, int r17, int r18, boolean r19) {
        /*
            r14 = this;
            r6 = r14
            r7 = r15
            boolean r0 = r7.mAdded
            r8 = 1
            if (r0 == 0) goto L_0x000f
            boolean r0 = r7.mDetached
            if (r0 == 0) goto L_0x000c
            goto L_0x000f
        L_0x000c:
            r0 = r16
            goto L_0x0014
        L_0x000f:
            r0 = r16
            if (r0 <= r8) goto L_0x0014
            r0 = 1
        L_0x0014:
            boolean r1 = r7.mRemoving
            if (r1 == 0) goto L_0x002a
            int r1 = r7.mState
            if (r0 <= r1) goto L_0x002a
            int r0 = r7.mState
            if (r0 != 0) goto L_0x0028
            boolean r0 = r15.isInBackStack()
            if (r0 == 0) goto L_0x0028
            r0 = 1
            goto L_0x002a
        L_0x0028:
            int r0 = r7.mState
        L_0x002a:
            boolean r1 = r7.mDeferStart
            r9 = 3
            r10 = 2
            if (r1 == 0) goto L_0x0038
            int r1 = r7.mState
            if (r1 >= r9) goto L_0x0038
            if (r0 <= r10) goto L_0x0038
            r11 = 2
            goto L_0x0039
        L_0x0038:
            r11 = r0
        L_0x0039:
            int r0 = r7.mState
            r12 = 0
            r13 = 0
            if (r0 > r11) goto L_0x02ea
            boolean r0 = r7.mFromLayout
            if (r0 == 0) goto L_0x0048
            boolean r0 = r7.mInLayout
            if (r0 != 0) goto L_0x0048
            return
        L_0x0048:
            android.view.View r0 = r15.getAnimatingAway()
            if (r0 != 0) goto L_0x0054
            android.animation.Animator r0 = r15.getAnimator()
            if (r0 == 0) goto L_0x0066
        L_0x0054:
            r15.setAnimatingAway(r12)
            r15.setAnimator(r12)
            int r2 = r15.getStateAfterAnimating()
            r3 = 0
            r4 = 0
            r5 = 1
            r0 = r14
            r1 = r15
            r0.a((androidx.fragment.app.Fragment) r1, (int) r2, (int) r3, (int) r4, (boolean) r5)
        L_0x0066:
            int r0 = r7.mState
            switch(r0) {
                case 0: goto L_0x006d;
                case 1: goto L_0x01a6;
                case 2: goto L_0x02a0;
                case 3: goto L_0x02c2;
                default: goto L_0x006b;
            }
        L_0x006b:
            goto L_0x0437
        L_0x006d:
            if (r11 <= 0) goto L_0x01a6
            boolean r0 = a
            if (r0 == 0) goto L_0x0089
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto CREATED: "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x0089:
            android.os.Bundle r0 = r7.mSavedFragmentState
            if (r0 == 0) goto L_0x00e0
            android.os.Bundle r0 = r7.mSavedFragmentState
            androidx.fragment.app.FragmentHostCallback r1 = r6.m
            android.content.Context r1 = r1.b()
            java.lang.ClassLoader r1 = r1.getClassLoader()
            r0.setClassLoader(r1)
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:view_state"
            android.util.SparseArray r0 = r0.getSparseParcelableArray(r1)
            r7.mSavedViewState = r0
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:target_state"
            androidx.fragment.app.Fragment r0 = r14.getFragment(r0, r1)
            r7.mTarget = r0
            androidx.fragment.app.Fragment r0 = r7.mTarget
            if (r0 == 0) goto L_0x00be
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:target_req_state"
            int r0 = r0.getInt(r1, r13)
            r7.mTargetRequestCode = r0
        L_0x00be:
            java.lang.Boolean r0 = r7.mSavedUserVisibleHint
            if (r0 == 0) goto L_0x00cd
            java.lang.Boolean r0 = r7.mSavedUserVisibleHint
            boolean r0 = r0.booleanValue()
            r7.mUserVisibleHint = r0
            r7.mSavedUserVisibleHint = r12
            goto L_0x00d7
        L_0x00cd:
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:user_visible_hint"
            boolean r0 = r0.getBoolean(r1, r8)
            r7.mUserVisibleHint = r0
        L_0x00d7:
            boolean r0 = r7.mUserVisibleHint
            if (r0 != 0) goto L_0x00e0
            r7.mDeferStart = r8
            if (r11 <= r10) goto L_0x00e0
            r11 = 2
        L_0x00e0:
            androidx.fragment.app.FragmentHostCallback r0 = r6.m
            r7.mHost = r0
            androidx.fragment.app.Fragment r1 = r6.o
            r7.mParentFragment = r1
            if (r1 == 0) goto L_0x00ed
            androidx.fragment.app.FragmentManagerImpl r0 = r1.mChildFragmentManager
            goto L_0x00f1
        L_0x00ed:
            androidx.fragment.app.FragmentManagerImpl r0 = r0.d()
        L_0x00f1:
            r7.mFragmentManager = r0
            androidx.fragment.app.Fragment r0 = r7.mTarget
            if (r0 == 0) goto L_0x013c
            android.util.SparseArray<androidx.fragment.app.Fragment> r0 = r6.f
            androidx.fragment.app.Fragment r1 = r7.mTarget
            int r1 = r1.mIndex
            java.lang.Object r0 = r0.get(r1)
            androidx.fragment.app.Fragment r1 = r7.mTarget
            if (r0 != r1) goto L_0x0116
            androidx.fragment.app.Fragment r0 = r7.mTarget
            int r0 = r0.mState
            if (r0 >= r8) goto L_0x013c
            androidx.fragment.app.Fragment r1 = r7.mTarget
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = 1
            r0 = r14
            r0.a((androidx.fragment.app.Fragment) r1, (int) r2, (int) r3, (int) r4, (boolean) r5)
            goto L_0x013c
        L_0x0116:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Fragment "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r2 = " declared target fragment "
            r1.append(r2)
            androidx.fragment.app.Fragment r2 = r7.mTarget
            r1.append(r2)
            java.lang.String r2 = " that does not belong to this FragmentManager!"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x013c:
            androidx.fragment.app.FragmentHostCallback r0 = r6.m
            android.content.Context r0 = r0.b()
            r14.a((androidx.fragment.app.Fragment) r15, (android.content.Context) r0, (boolean) r13)
            r7.mCalled = r13
            androidx.fragment.app.FragmentHostCallback r0 = r6.m
            android.content.Context r0 = r0.b()
            r15.onAttach((android.content.Context) r0)
            boolean r0 = r7.mCalled
            if (r0 == 0) goto L_0x018a
            androidx.fragment.app.Fragment r0 = r7.mParentFragment
            if (r0 != 0) goto L_0x015e
            androidx.fragment.app.FragmentHostCallback r0 = r6.m
            r0.onAttachFragment(r15)
            goto L_0x0163
        L_0x015e:
            androidx.fragment.app.Fragment r0 = r7.mParentFragment
            r0.onAttachFragment(r15)
        L_0x0163:
            androidx.fragment.app.FragmentHostCallback r0 = r6.m
            android.content.Context r0 = r0.b()
            r14.b((androidx.fragment.app.Fragment) r15, (android.content.Context) r0, (boolean) r13)
            boolean r0 = r7.mIsCreated
            if (r0 != 0) goto L_0x0180
            android.os.Bundle r0 = r7.mSavedFragmentState
            r14.a((androidx.fragment.app.Fragment) r15, (android.os.Bundle) r0, (boolean) r13)
            android.os.Bundle r0 = r7.mSavedFragmentState
            r15.performCreate(r0)
            android.os.Bundle r0 = r7.mSavedFragmentState
            r14.b((androidx.fragment.app.Fragment) r15, (android.os.Bundle) r0, (boolean) r13)
            goto L_0x0187
        L_0x0180:
            android.os.Bundle r0 = r7.mSavedFragmentState
            r15.restoreChildFragmentState(r0)
            r7.mState = r8
        L_0x0187:
            r7.mRetaining = r13
            goto L_0x01a6
        L_0x018a:
            androidx.fragment.app.SuperNotCalledException r0 = new androidx.fragment.app.SuperNotCalledException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Fragment "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r2 = " did not call through to super.onAttach()"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x01a6:
            r14.b(r15)
            if (r11 <= r8) goto L_0x02a0
            boolean r0 = a
            if (r0 == 0) goto L_0x01c5
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto ACTIVITY_CREATED: "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x01c5:
            boolean r0 = r7.mFromLayout
            if (r0 != 0) goto L_0x028b
            int r0 = r7.mContainerId
            if (r0 == 0) goto L_0x023d
            int r0 = r7.mContainerId
            r1 = -1
            if (r0 != r1) goto L_0x01f0
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot create fragment "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r2 = " for a container view with no id"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            r14.throwException(r0)
        L_0x01f0:
            androidx.fragment.app.FragmentContainer r0 = r6.n
            int r1 = r7.mContainerId
            android.view.View r0 = r0.onFindViewById(r1)
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            if (r0 != 0) goto L_0x023e
            boolean r1 = r7.mRestored
            if (r1 != 0) goto L_0x023e
            android.content.res.Resources r1 = r15.getResources()     // Catch:{ NotFoundException -> 0x020b }
            int r2 = r7.mContainerId     // Catch:{ NotFoundException -> 0x020b }
            java.lang.String r1 = r1.getResourceName(r2)     // Catch:{ NotFoundException -> 0x020b }
            goto L_0x020d
        L_0x020b:
            java.lang.String r1 = "unknown"
        L_0x020d:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "No view found for id 0x"
            r3.append(r4)
            int r4 = r7.mContainerId
            java.lang.String r4 = java.lang.Integer.toHexString(r4)
            r3.append(r4)
            java.lang.String r4 = " ("
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = ") for fragment "
            r3.append(r1)
            r3.append(r15)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            r14.throwException(r2)
            goto L_0x023e
        L_0x023d:
            r0 = r12
        L_0x023e:
            r7.mContainer = r0
            android.os.Bundle r1 = r7.mSavedFragmentState
            android.view.LayoutInflater r1 = r15.performGetLayoutInflater(r1)
            android.os.Bundle r2 = r7.mSavedFragmentState
            r15.performCreateView(r1, r0, r2)
            android.view.View r1 = r7.mView
            if (r1 == 0) goto L_0x0289
            android.view.View r1 = r7.mView
            r7.mInnerView = r1
            android.view.View r1 = r7.mView
            r1.setSaveFromParentEnabled(r13)
            if (r0 == 0) goto L_0x025f
            android.view.View r1 = r7.mView
            r0.addView(r1)
        L_0x025f:
            boolean r0 = r7.mHidden
            if (r0 == 0) goto L_0x026a
            android.view.View r0 = r7.mView
            r1 = 8
            r0.setVisibility(r1)
        L_0x026a:
            android.view.View r0 = r7.mView
            android.os.Bundle r1 = r7.mSavedFragmentState
            r15.onViewCreated(r0, r1)
            android.view.View r0 = r7.mView
            android.os.Bundle r1 = r7.mSavedFragmentState
            r14.a((androidx.fragment.app.Fragment) r15, (android.view.View) r0, (android.os.Bundle) r1, (boolean) r13)
            android.view.View r0 = r7.mView
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L_0x0285
            android.view.ViewGroup r0 = r7.mContainer
            if (r0 == 0) goto L_0x0285
            goto L_0x0286
        L_0x0285:
            r8 = 0
        L_0x0286:
            r7.mIsNewlyAdded = r8
            goto L_0x028b
        L_0x0289:
            r7.mInnerView = r12
        L_0x028b:
            android.os.Bundle r0 = r7.mSavedFragmentState
            r15.performActivityCreated(r0)
            android.os.Bundle r0 = r7.mSavedFragmentState
            r14.c(r15, r0, r13)
            android.view.View r0 = r7.mView
            if (r0 == 0) goto L_0x029e
            android.os.Bundle r0 = r7.mSavedFragmentState
            r15.restoreViewState(r0)
        L_0x029e:
            r7.mSavedFragmentState = r12
        L_0x02a0:
            if (r11 <= r10) goto L_0x02c2
            boolean r0 = a
            if (r0 == 0) goto L_0x02bc
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto STARTED: "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x02bc:
            r15.performStart()
            r14.a((androidx.fragment.app.Fragment) r15, (boolean) r13)
        L_0x02c2:
            if (r11 <= r9) goto L_0x0437
            boolean r0 = a
            if (r0 == 0) goto L_0x02de
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto RESUMED: "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x02de:
            r15.performResume()
            r14.b(r15, r13)
            r7.mSavedFragmentState = r12
            r7.mSavedViewState = r12
            goto L_0x0437
        L_0x02ea:
            int r0 = r7.mState
            if (r0 <= r11) goto L_0x0437
            int r0 = r7.mState
            switch(r0) {
                case 1: goto L_0x03c1;
                case 2: goto L_0x033a;
                case 3: goto L_0x0318;
                case 4: goto L_0x02f5;
                default: goto L_0x02f3;
            }
        L_0x02f3:
            goto L_0x0437
        L_0x02f5:
            r0 = 4
            if (r11 >= r0) goto L_0x0318
            boolean r0 = a
            if (r0 == 0) goto L_0x0312
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "movefrom RESUMED: "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x0312:
            r15.performPause()
            r14.c(r15, r13)
        L_0x0318:
            if (r11 >= r9) goto L_0x033a
            boolean r0 = a
            if (r0 == 0) goto L_0x0334
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "movefrom STARTED: "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x0334:
            r15.performStop()
            r14.d(r15, r13)
        L_0x033a:
            if (r11 >= r10) goto L_0x03c1
            boolean r0 = a
            if (r0 == 0) goto L_0x0356
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "movefrom ACTIVITY_CREATED: "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x0356:
            android.view.View r0 = r7.mView
            if (r0 == 0) goto L_0x0369
            androidx.fragment.app.FragmentHostCallback r0 = r6.m
            boolean r0 = r0.onShouldSaveFragmentState(r15)
            if (r0 == 0) goto L_0x0369
            android.util.SparseArray<android.os.Parcelable> r0 = r7.mSavedViewState
            if (r0 != 0) goto L_0x0369
            r14.g(r15)
        L_0x0369:
            r15.performDestroyView()
            r14.e(r15, r13)
            android.view.View r0 = r7.mView
            if (r0 == 0) goto L_0x03b2
            android.view.ViewGroup r0 = r7.mContainer
            if (r0 == 0) goto L_0x03b2
            android.view.ViewGroup r0 = r7.mContainer
            android.view.View r1 = r7.mView
            r0.endViewTransition(r1)
            android.view.View r0 = r7.mView
            r0.clearAnimation()
            int r0 = r6.l
            r1 = 0
            if (r0 <= 0) goto L_0x03a3
            boolean r0 = r6.u
            if (r0 != 0) goto L_0x03a3
            android.view.View r0 = r7.mView
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L_0x03a3
            float r0 = r7.mPostponedAlpha
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 < 0) goto L_0x03a3
            r0 = r17
            r2 = r18
            androidx.fragment.app.FragmentManagerImpl$AnimationOrAnimator r0 = r14.a((androidx.fragment.app.Fragment) r15, (int) r0, (boolean) r13, (int) r2)
            goto L_0x03a4
        L_0x03a3:
            r0 = r12
        L_0x03a4:
            r7.mPostponedAlpha = r1
            if (r0 == 0) goto L_0x03ab
            r14.animateRemoveFragment(r15, r0, r11)
        L_0x03ab:
            android.view.ViewGroup r0 = r7.mContainer
            android.view.View r1 = r7.mView
            r0.removeView(r1)
        L_0x03b2:
            r7.mContainer = r12
            r7.mView = r12
            r7.mViewLifecycleOwner = r12
            androidx.lifecycle.MutableLiveData<androidx.lifecycle.LifecycleOwner> r0 = r7.mViewLifecycleOwnerLiveData
            r0.setValue(r12)
            r7.mInnerView = r12
            r7.mInLayout = r13
        L_0x03c1:
            if (r11 >= r8) goto L_0x0437
            boolean r0 = r6.u
            if (r0 == 0) goto L_0x03e8
            android.view.View r0 = r15.getAnimatingAway()
            if (r0 == 0) goto L_0x03d8
            android.view.View r0 = r15.getAnimatingAway()
            r15.setAnimatingAway(r12)
            r0.clearAnimation()
            goto L_0x03e8
        L_0x03d8:
            android.animation.Animator r0 = r15.getAnimator()
            if (r0 == 0) goto L_0x03e8
            android.animation.Animator r0 = r15.getAnimator()
            r15.setAnimator(r12)
            r0.cancel()
        L_0x03e8:
            android.view.View r0 = r15.getAnimatingAway()
            if (r0 != 0) goto L_0x0433
            android.animation.Animator r0 = r15.getAnimator()
            if (r0 == 0) goto L_0x03f5
            goto L_0x0433
        L_0x03f5:
            boolean r0 = a
            if (r0 == 0) goto L_0x040f
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "movefrom CREATED: "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x040f:
            boolean r0 = r7.mRetaining
            if (r0 != 0) goto L_0x041a
            r15.performDestroy()
            r14.f(r15, r13)
            goto L_0x041c
        L_0x041a:
            r7.mState = r13
        L_0x041c:
            r15.performDetach()
            r14.g(r15, r13)
            if (r19 != 0) goto L_0x0437
            boolean r0 = r7.mRetaining
            if (r0 != 0) goto L_0x042c
            r14.f(r15)
            goto L_0x0437
        L_0x042c:
            r7.mHost = r12
            r7.mParentFragment = r12
            r7.mFragmentManager = r12
            goto L_0x0437
        L_0x0433:
            r15.setStateAfterAnimating(r11)
            goto L_0x0438
        L_0x0437:
            r8 = r11
        L_0x0438:
            int r0 = r7.mState
            if (r0 == r8) goto L_0x046b
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveToState: Fragment state for "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r2 = " not updated inline; "
            r1.append(r2)
            java.lang.String r2 = "expected state "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r2 = " found "
            r1.append(r2)
            int r2 = r7.mState
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.w(r0, r1)
            r7.mState = r8
        L_0x046b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManagerImpl.a(androidx.fragment.app.Fragment, int, int, int, boolean):void");
    }

    /* access modifiers changed from: package-private */
    public void a(@NonNull Fragment fragment, @NonNull Context context, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).a(fragment, context, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentPreAttached(this, fragment, context);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).a(fragment, bundle, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentPreCreated(this, fragment, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(@NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).a(fragment, view, bundle, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentViewCreated(this, fragment, view, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(@NonNull Fragment fragment, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).a(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentStarted(this, fragment);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2) {
        return this.l >= i2;
    }

    /* access modifiers changed from: package-private */
    public boolean a(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, String str, int i2, int i3) {
        int i4;
        ArrayList<BackStackRecord> arrayList3 = this.g;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.g.remove(size));
            arrayList2.add(true);
        } else {
            if (str != null || i2 >= 0) {
                i4 = this.g.size() - 1;
                while (i4 >= 0) {
                    BackStackRecord backStackRecord = this.g.get(i4);
                    if ((str != null && str.equals(backStackRecord.getName())) || (i2 >= 0 && i2 == backStackRecord.m)) {
                        break;
                    }
                    i4--;
                }
                if (i4 < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    while (true) {
                        i4--;
                        if (i4 < 0) {
                            break;
                        }
                        BackStackRecord backStackRecord2 = this.g.get(i4);
                        if ((str == null || !str.equals(backStackRecord2.getName())) && (i2 < 0 || i2 != backStackRecord2.m)) {
                            break;
                        }
                    }
                }
            } else {
                i4 = -1;
            }
            if (i4 == this.g.size() - 1) {
                return false;
            }
            for (int size2 = this.g.size() - 1; size2 > i4; size2--) {
                arrayList.add(this.g.remove(size2));
                arrayList2.add(true);
            }
        }
        return true;
    }

    public void addFragment(Fragment fragment, boolean z2) {
        if (a) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        e(fragment);
        if (fragment.mDetached) {
            return;
        }
        if (!this.e.contains(fragment)) {
            synchronized (this.e) {
                this.e.add(fragment);
            }
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.r = true;
            }
            if (z2) {
                a(fragment);
                return;
            }
            return;
        }
        throw new IllegalStateException("Fragment already added: " + fragment);
    }

    public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onBackStackChangedListener) {
        if (this.k == null) {
            this.k = new ArrayList<>();
        }
        this.k.add(onBackStackChangedListener);
    }

    public int allocBackStackIndex(BackStackRecord backStackRecord) {
        synchronized (this) {
            if (this.j != null) {
                if (this.j.size() > 0) {
                    int intValue = this.j.remove(this.j.size() - 1).intValue();
                    if (a) {
                        Log.v("FragmentManager", "Adding back stack index " + intValue + " with " + backStackRecord);
                    }
                    this.i.set(intValue, backStackRecord);
                    return intValue;
                }
            }
            if (this.i == null) {
                this.i = new ArrayList<>();
            }
            int size = this.i.size();
            if (a) {
                Log.v("FragmentManager", "Setting back stack index " + size + " to " + backStackRecord);
            }
            this.i.add(backStackRecord);
            return size;
        }
    }

    public void attachController(FragmentHostCallback fragmentHostCallback, FragmentContainer fragmentContainer, Fragment fragment) {
        if (this.m == null) {
            this.m = fragmentHostCallback;
            this.n = fragmentContainer;
            this.o = fragment;
            return;
        }
        throw new IllegalStateException("Already attached");
    }

    public void attachFragment(Fragment fragment) {
        if (a) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            if (!this.e.contains(fragment)) {
                if (a) {
                    Log.v("FragmentManager", "add from attach: " + fragment);
                }
                synchronized (this.e) {
                    this.e.add(fragment);
                }
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.r = true;
                    return;
                }
                return;
            }
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
    }

    /* access modifiers changed from: package-private */
    public int b() {
        SparseArray<Fragment> sparseArray = this.f;
        if (sparseArray == null) {
            return 0;
        }
        return sparseArray.size();
    }

    /* access modifiers changed from: package-private */
    public void b(Fragment fragment) {
        if (fragment.mFromLayout && !fragment.mPerformedCreateView) {
            fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), (ViewGroup) null, fragment.mSavedFragmentState);
            if (fragment.mView != null) {
                fragment.mInnerView = fragment.mView;
                fragment.mView.setSaveFromParentEnabled(false);
                if (fragment.mHidden) {
                    fragment.mView.setVisibility(8);
                }
                fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                a(fragment, fragment.mView, fragment.mSavedFragmentState, false);
                return;
            }
            fragment.mInnerView = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void b(@NonNull Fragment fragment, @NonNull Context context, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).b(fragment, context, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentAttached(this, fragment, context);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).b(fragment, bundle, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentCreated(this, fragment, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(@NonNull Fragment fragment, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).b(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentResumed(this, fragment);
            }
        }
    }

    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (this.f != null) {
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                Fragment valueAt = this.f.valueAt(i2);
                if (valueAt != null) {
                    performPendingDeferredStart(valueAt);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c(final Fragment fragment) {
        if (fragment.mView != null) {
            AnimationOrAnimator a2 = a(fragment, fragment.getNextTransition(), !fragment.mHidden, fragment.getNextTransitionStyle());
            if (a2 == null || a2.animator == null) {
                if (a2 != null) {
                    setHWLayerAnimListenerIfAlpha(fragment.mView, a2);
                    fragment.mView.startAnimation(a2.animation);
                    a2.animation.start();
                }
                fragment.mView.setVisibility((!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            } else {
                a2.animator.setTarget(fragment.mView);
                if (!fragment.mHidden) {
                    fragment.mView.setVisibility(0);
                } else if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                } else {
                    final ViewGroup viewGroup = fragment.mContainer;
                    final View view = fragment.mView;
                    viewGroup.startViewTransition(view);
                    a2.animator.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            viewGroup.endViewTransition(view);
                            animator.removeListener(this);
                            if (fragment.mView != null) {
                                fragment.mView.setVisibility(8);
                            }
                        }
                    });
                }
                setHWLayerAnimListenerIfAlpha(fragment.mView, a2);
                a2.animator.start();
            }
        }
        if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
            this.r = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    /* access modifiers changed from: package-private */
    public void c(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).c(fragment, bundle, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentActivityCreated(this, fragment, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c(@NonNull Fragment fragment, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).c(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentPaused(this, fragment);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        synchronized (this) {
            boolean z2 = false;
            boolean z3 = this.C != null && !this.C.isEmpty();
            if (this.b != null && this.b.size() == 1) {
                z2 = true;
            }
            if (z3 || z2) {
                this.m.c().removeCallbacks(this.E);
                this.m.c().post(this.E);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        r0 = r0.mView;
        r1 = r11.mContainer;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d(androidx.fragment.app.Fragment r11) {
        /*
            r10 = this;
            if (r11 != 0) goto L_0x0003
            return
        L_0x0003:
            int r0 = r10.l
            boolean r1 = r11.mRemoving
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x001a
            boolean r1 = r11.isInBackStack()
            if (r1 == 0) goto L_0x0016
            int r0 = java.lang.Math.min(r0, r2)
            goto L_0x001a
        L_0x0016:
            int r0 = java.lang.Math.min(r0, r3)
        L_0x001a:
            r6 = r0
            int r7 = r11.getNextTransition()
            int r8 = r11.getNextTransitionStyle()
            r9 = 0
            r4 = r10
            r5 = r11
            r4.a((androidx.fragment.app.Fragment) r5, (int) r6, (int) r7, (int) r8, (boolean) r9)
            android.view.View r0 = r11.mView
            if (r0 == 0) goto L_0x0090
            androidx.fragment.app.Fragment r0 = r10.findFragmentUnder(r11)
            if (r0 == 0) goto L_0x004b
            android.view.View r0 = r0.mView
            android.view.ViewGroup r1 = r11.mContainer
            int r0 = r1.indexOfChild(r0)
            android.view.View r4 = r11.mView
            int r4 = r1.indexOfChild(r4)
            if (r4 >= r0) goto L_0x004b
            r1.removeViewAt(r4)
            android.view.View r4 = r11.mView
            r1.addView(r4, r0)
        L_0x004b:
            boolean r0 = r11.mIsNewlyAdded
            if (r0 == 0) goto L_0x0090
            android.view.ViewGroup r0 = r11.mContainer
            if (r0 == 0) goto L_0x0090
            float r0 = r11.mPostponedAlpha
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x0061
            android.view.View r0 = r11.mView
            float r4 = r11.mPostponedAlpha
            r0.setAlpha(r4)
        L_0x0061:
            r11.mPostponedAlpha = r1
            r11.mIsNewlyAdded = r3
            int r0 = r11.getNextTransition()
            int r1 = r11.getNextTransitionStyle()
            androidx.fragment.app.FragmentManagerImpl$AnimationOrAnimator r0 = r10.a((androidx.fragment.app.Fragment) r11, (int) r0, (boolean) r2, (int) r1)
            if (r0 == 0) goto L_0x0090
            android.view.View r1 = r11.mView
            setHWLayerAnimListenerIfAlpha(r1, r0)
            android.view.animation.Animation r1 = r0.animation
            if (r1 == 0) goto L_0x0084
            android.view.View r1 = r11.mView
            android.view.animation.Animation r0 = r0.animation
            r1.startAnimation(r0)
            goto L_0x0090
        L_0x0084:
            android.animation.Animator r1 = r0.animator
            android.view.View r2 = r11.mView
            r1.setTarget(r2)
            android.animation.Animator r0 = r0.animator
            r0.start()
        L_0x0090:
            boolean r0 = r11.mHiddenChanged
            if (r0 == 0) goto L_0x0097
            r10.c(r11)
        L_0x0097:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManagerImpl.d(androidx.fragment.app.Fragment):void");
    }

    /* access modifiers changed from: package-private */
    public void d(@NonNull Fragment fragment, @NonNull Bundle bundle, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).d(fragment, bundle, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentSaveInstanceState(this, fragment, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void d(@NonNull Fragment fragment, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).d(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentStopped(this, fragment);
            }
        }
    }

    public void detachFragment(Fragment fragment) {
        if (a) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (a) {
                    Log.v("FragmentManager", "remove from detach: " + fragment);
                }
                synchronized (this.e) {
                    this.e.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.r = true;
                }
                fragment.mAdded = false;
            }
        }
    }

    public void dispatchActivityCreated() {
        this.s = false;
        this.t = false;
        dispatchStateChange(2);
    }

    public void dispatchConfigurationChanged(Configuration configuration) {
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            Fragment fragment = this.e.get(i2);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem) {
        if (this.l < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            Fragment fragment = this.e.get(i2);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchCreate() {
        this.s = false;
        this.t = false;
        dispatchStateChange(1);
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.l < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z2 = false;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            Fragment fragment = this.e.get(i2);
            if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z2 = true;
            }
        }
        if (this.h != null) {
            for (int i3 = 0; i3 < this.h.size(); i3++) {
                Fragment fragment2 = this.h.get(i3);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.h = arrayList;
        return z2;
    }

    public void dispatchDestroy() {
        this.u = true;
        execPendingActions();
        dispatchStateChange(0);
        this.m = null;
        this.n = null;
        this.o = null;
    }

    public void dispatchDestroyView() {
        dispatchStateChange(1);
    }

    public void dispatchLowMemory() {
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            Fragment fragment = this.e.get(i2);
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public void dispatchMultiWindowModeChanged(boolean z2) {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            Fragment fragment = this.e.get(size);
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z2);
            }
        }
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        if (this.l < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            Fragment fragment = this.e.get(i2);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        if (this.l >= 1) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                Fragment fragment = this.e.get(i2);
                if (fragment != null) {
                    fragment.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public void dispatchPause() {
        dispatchStateChange(3);
    }

    public void dispatchPictureInPictureModeChanged(boolean z2) {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            Fragment fragment = this.e.get(size);
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z2);
            }
        }
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        if (this.l < 1) {
            return false;
        }
        boolean z2 = false;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            Fragment fragment = this.e.get(i2);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z2 = true;
            }
        }
        return z2;
    }

    public void dispatchResume() {
        this.s = false;
        this.t = false;
        dispatchStateChange(4);
    }

    public void dispatchStart() {
        this.s = false;
        this.t = false;
        dispatchStateChange(3);
    }

    public void dispatchStop() {
        this.t = true;
        dispatchStateChange(2);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        int size5;
        String str2 = str + "    ";
        SparseArray<Fragment> sparseArray = this.f;
        if (sparseArray != null && (size5 = sparseArray.size()) > 0) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (int i2 = 0; i2 < size5; i2++) {
                Fragment valueAt = this.f.valueAt(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(valueAt);
                if (valueAt != null) {
                    valueAt.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size6 = this.e.size();
        if (size6 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i3 = 0; i3 < size6; i3++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(this.e.get(i3).toString());
            }
        }
        ArrayList<Fragment> arrayList = this.h;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i4 = 0; i4 < size4; i4++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(this.h.get(i4).toString());
            }
        }
        ArrayList<BackStackRecord> arrayList2 = this.g;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i5 = 0; i5 < size3; i5++) {
                BackStackRecord backStackRecord = this.g.get(i5);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i5);
                printWriter.print(": ");
                printWriter.println(backStackRecord.toString());
                backStackRecord.dump(str2, fileDescriptor, printWriter, strArr);
            }
        }
        synchronized (this) {
            if (this.i != null && (size2 = this.i.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i6 = 0; i6 < size2; i6++) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i6);
                    printWriter.print(": ");
                    printWriter.println(this.i.get(i6));
                }
            }
            if (this.j != null && this.j.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.j.toArray()));
            }
        }
        ArrayList<OpGenerator> arrayList3 = this.b;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i7 = 0; i7 < size; i7++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i7);
                printWriter.print(": ");
                printWriter.println(this.b.get(i7));
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.m);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.n);
        if (this.o != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.o);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.l);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.s);
        printWriter.print(" mStopped=");
        printWriter.print(this.t);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.u);
        if (this.r) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.r);
        }
        if (this.v != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.v);
        }
    }

    /* access modifiers changed from: package-private */
    public void e() {
        if (this.w) {
            this.w = false;
            c();
        }
    }

    /* access modifiers changed from: package-private */
    public void e(Fragment fragment) {
        if (fragment.mIndex < 0) {
            int i2 = this.d;
            this.d = i2 + 1;
            fragment.setIndex(i2, this.o);
            if (this.f == null) {
                this.f = new SparseArray<>();
            }
            this.f.put(fragment.mIndex, fragment);
            if (a) {
                Log.v("FragmentManager", "Allocated fragment index " + fragment);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void e(@NonNull Fragment fragment, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).e(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentViewDestroyed(this, fragment);
            }
        }
    }

    public void enqueueAction(OpGenerator opGenerator, boolean z2) {
        if (!z2) {
            checkStateLoss();
        }
        synchronized (this) {
            if (!this.u) {
                if (this.m != null) {
                    if (this.b == null) {
                        this.b = new ArrayList<>();
                    }
                    this.b.add(opGenerator);
                    d();
                    return;
                }
            }
            if (!z2) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean execPendingActions() {
        ensureExecReady(true);
        boolean z2 = false;
        while (generateOpsForPendingActions(this.x, this.y)) {
            this.c = true;
            try {
                removeRedundantOperationsAndExecute(this.x, this.y);
                cleanupExec();
                z2 = true;
            } catch (Throwable th) {
                cleanupExec();
                throw th;
            }
        }
        e();
        burpActive();
        return z2;
    }

    public void execSingleAction(OpGenerator opGenerator, boolean z2) {
        if (!z2 || (this.m != null && !this.u)) {
            ensureExecReady(z2);
            if (opGenerator.generateOps(this.x, this.y)) {
                this.c = true;
                try {
                    removeRedundantOperationsAndExecute(this.x, this.y);
                } finally {
                    cleanupExec();
                }
            }
            e();
            burpActive();
        }
    }

    public boolean executePendingTransactions() {
        boolean execPendingActions = execPendingActions();
        forcePostponedTransactions();
        return execPendingActions;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        if (this.k != null) {
            for (int i2 = 0; i2 < this.k.size(); i2++) {
                this.k.get(i2).onBackStackChanged();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void f(Fragment fragment) {
        if (fragment.mIndex >= 0) {
            if (a) {
                Log.v("FragmentManager", "Freeing fragment index " + fragment);
            }
            this.f.put(fragment.mIndex, (Object) null);
            fragment.initState();
        }
    }

    /* access modifiers changed from: package-private */
    public void f(@NonNull Fragment fragment, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).f(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentDestroyed(this, fragment);
            }
        }
    }

    @Nullable
    public Fragment findFragmentById(int i2) {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            Fragment fragment = this.e.get(size);
            if (fragment != null && fragment.mFragmentId == i2) {
                return fragment;
            }
        }
        SparseArray<Fragment> sparseArray = this.f;
        if (sparseArray == null) {
            return null;
        }
        for (int size2 = sparseArray.size() - 1; size2 >= 0; size2--) {
            Fragment valueAt = this.f.valueAt(size2);
            if (valueAt != null && valueAt.mFragmentId == i2) {
                return valueAt;
            }
        }
        return null;
    }

    @Nullable
    public Fragment findFragmentByTag(@Nullable String str) {
        if (str != null) {
            for (int size = this.e.size() - 1; size >= 0; size--) {
                Fragment fragment = this.e.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        SparseArray<Fragment> sparseArray = this.f;
        if (sparseArray == null || str == null) {
            return null;
        }
        for (int size2 = sparseArray.size() - 1; size2 >= 0; size2--) {
            Fragment valueAt = this.f.valueAt(size2);
            if (valueAt != null && str.equals(valueAt.mTag)) {
                return valueAt;
            }
        }
        return null;
    }

    public Fragment findFragmentByWho(String str) {
        Fragment findFragmentByWho;
        SparseArray<Fragment> sparseArray = this.f;
        if (sparseArray == null || str == null) {
            return null;
        }
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            Fragment valueAt = this.f.valueAt(size);
            if (valueAt != null && (findFragmentByWho = valueAt.findFragmentByWho(str)) != null) {
                return findFragmentByWho;
            }
        }
        return null;
    }

    public void freeBackStackIndex(int i2) {
        synchronized (this) {
            this.i.set(i2, (Object) null);
            if (this.j == null) {
                this.j = new ArrayList<>();
            }
            if (a) {
                Log.v("FragmentManager", "Freeing back stack index " + i2);
            }
            this.j.add(Integer.valueOf(i2));
        }
    }

    /* access modifiers changed from: package-private */
    public FragmentManagerNonConfig g() {
        setRetaining(this.D);
        return this.D;
    }

    /* access modifiers changed from: package-private */
    public void g(Fragment fragment) {
        if (fragment.mInnerView != null) {
            SparseArray<Parcelable> sparseArray = this.B;
            if (sparseArray == null) {
                this.B = new SparseArray<>();
            } else {
                sparseArray.clear();
            }
            fragment.mInnerView.saveHierarchyState(this.B);
            if (this.B.size() > 0) {
                fragment.mSavedViewState = this.B;
                this.B = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void g(@NonNull Fragment fragment, boolean z2) {
        Fragment fragment2 = this.o;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).g(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z2 || next.b) {
                next.a.onFragmentDetached(this, fragment);
            }
        }
    }

    public FragmentManager.BackStackEntry getBackStackEntryAt(int i2) {
        return this.g.get(i2);
    }

    public int getBackStackEntryCount() {
        ArrayList<BackStackRecord> arrayList = this.g;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Nullable
    public Fragment getFragment(Bundle bundle, String str) {
        int i2 = bundle.getInt(str, -1);
        if (i2 == -1) {
            return null;
        }
        Fragment fragment = this.f.get(i2);
        if (fragment == null) {
            throwException(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i2));
        }
        return fragment;
    }

    public List<Fragment> getFragments() {
        List<Fragment> list;
        if (this.e.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.e) {
            list = (List) this.e.clone();
        }
        return list;
    }

    @Nullable
    public Fragment getPrimaryNavigationFragment() {
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public Bundle h(Fragment fragment) {
        Bundle bundle;
        if (this.A == null) {
            this.A = new Bundle();
        }
        fragment.performSaveInstanceState(this.A);
        d(fragment, this.A, false);
        if (!this.A.isEmpty()) {
            bundle = this.A;
            this.A = null;
        } else {
            bundle = null;
        }
        if (fragment.mView != null) {
            g(fragment);
        }
        if (fragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        if (!fragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle;
    }

    /* access modifiers changed from: package-private */
    public void h() {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        FragmentManagerNonConfig fragmentManagerNonConfig;
        if (this.f != null) {
            arrayList3 = null;
            arrayList2 = null;
            arrayList = null;
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                Fragment valueAt = this.f.valueAt(i2);
                if (valueAt != null) {
                    if (valueAt.mRetainInstance) {
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        arrayList3.add(valueAt);
                        valueAt.mTargetIndex = valueAt.mTarget != null ? valueAt.mTarget.mIndex : -1;
                        if (a) {
                            Log.v("FragmentManager", "retainNonConfig: keeping retained " + valueAt);
                        }
                    }
                    if (valueAt.mChildFragmentManager != null) {
                        valueAt.mChildFragmentManager.h();
                        fragmentManagerNonConfig = valueAt.mChildFragmentManager.D;
                    } else {
                        fragmentManagerNonConfig = valueAt.mChildNonConfig;
                    }
                    if (arrayList2 == null && fragmentManagerNonConfig != null) {
                        arrayList2 = new ArrayList(this.f.size());
                        for (int i3 = 0; i3 < i2; i3++) {
                            arrayList2.add((Object) null);
                        }
                    }
                    if (arrayList2 != null) {
                        arrayList2.add(fragmentManagerNonConfig);
                    }
                    if (arrayList == null && valueAt.mViewModelStore != null) {
                        arrayList = new ArrayList(this.f.size());
                        for (int i4 = 0; i4 < i2; i4++) {
                            arrayList.add((Object) null);
                        }
                    }
                    if (arrayList != null) {
                        arrayList.add(valueAt.mViewModelStore);
                    }
                }
            }
        } else {
            arrayList3 = null;
            arrayList2 = null;
            arrayList = null;
        }
        if (arrayList3 == null && arrayList2 == null && arrayList == null) {
            this.D = null;
        } else {
            this.D = new FragmentManagerNonConfig(arrayList3, arrayList2, arrayList);
        }
    }

    public void hideFragment(Fragment fragment) {
        if (a) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
        }
    }

    /* access modifiers changed from: package-private */
    public Parcelable i() {
        int[] iArr;
        int size;
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        execPendingActions();
        this.s = true;
        BackStackState[] backStackStateArr = null;
        this.D = null;
        SparseArray<Fragment> sparseArray = this.f;
        if (sparseArray == null || sparseArray.size() <= 0) {
            return null;
        }
        int size2 = this.f.size();
        FragmentState[] fragmentStateArr = new FragmentState[size2];
        boolean z2 = false;
        for (int i2 = 0; i2 < size2; i2++) {
            Fragment valueAt = this.f.valueAt(i2);
            if (valueAt != null) {
                if (valueAt.mIndex < 0) {
                    throwException(new IllegalStateException("Failure saving state: active " + valueAt + " has cleared index: " + valueAt.mIndex));
                }
                FragmentState fragmentState = new FragmentState(valueAt);
                fragmentStateArr[i2] = fragmentState;
                if (valueAt.mState <= 0 || fragmentState.k != null) {
                    fragmentState.k = valueAt.mSavedFragmentState;
                } else {
                    fragmentState.k = h(valueAt);
                    if (valueAt.mTarget != null) {
                        if (valueAt.mTarget.mIndex < 0) {
                            throwException(new IllegalStateException("Failure saving state: " + valueAt + " has target not in fragment manager: " + valueAt.mTarget));
                        }
                        if (fragmentState.k == null) {
                            fragmentState.k = new Bundle();
                        }
                        putFragment(fragmentState.k, "android:target_state", valueAt.mTarget);
                        if (valueAt.mTargetRequestCode != 0) {
                            fragmentState.k.putInt("android:target_req_state", valueAt.mTargetRequestCode);
                        }
                    }
                }
                if (a) {
                    Log.v("FragmentManager", "Saved state of " + valueAt + ": " + fragmentState.k);
                }
                z2 = true;
            }
        }
        if (!z2) {
            if (a) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        int size3 = this.e.size();
        if (size3 > 0) {
            iArr = new int[size3];
            for (int i3 = 0; i3 < size3; i3++) {
                iArr[i3] = this.e.get(i3).mIndex;
                if (iArr[i3] < 0) {
                    throwException(new IllegalStateException("Failure saving state: active " + this.e.get(i3) + " has cleared index: " + iArr[i3]));
                }
                if (a) {
                    Log.v("FragmentManager", "saveAllState: adding fragment #" + i3 + ": " + this.e.get(i3));
                }
            }
        } else {
            iArr = null;
        }
        ArrayList<BackStackRecord> arrayList = this.g;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i4 = 0; i4 < size; i4++) {
                backStackStateArr[i4] = new BackStackState(this.g.get(i4));
                if (a) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i4 + ": " + this.g.get(i4));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.a = fragmentStateArr;
        fragmentManagerState.b = iArr;
        fragmentManagerState.c = backStackStateArr;
        Fragment fragment = this.p;
        if (fragment != null) {
            fragmentManagerState.d = fragment.mIndex;
        }
        fragmentManagerState.e = this.d;
        h();
        return fragmentManagerState;
    }

    public boolean isDestroyed() {
        return this.u;
    }

    public boolean isStateSaved() {
        return this.s || this.t;
    }

    /* access modifiers changed from: package-private */
    public LayoutInflater.Factory2 j() {
        return this;
    }

    public void noteStateNotSaved() {
        this.D = null;
        this.s = false;
        this.t = false;
        int size = this.e.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.e.get(i2);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        Fragment fragment;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        String str2 = str;
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet2.getAttributeValue((String) null, "class");
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet2, FragmentTag.Fragment);
        int i2 = 0;
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(0);
        }
        String str3 = attributeValue;
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!Fragment.isSupportFragmentClass(this.m.b(), str3)) {
            return null;
        }
        if (view != null) {
            i2 = view.getId();
        }
        if (i2 == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str3);
        }
        Fragment findFragmentById = resourceId != -1 ? findFragmentById(resourceId) : null;
        if (findFragmentById == null && string != null) {
            findFragmentById = findFragmentByTag(string);
        }
        if (findFragmentById == null && i2 != -1) {
            findFragmentById = findFragmentById(i2);
        }
        if (a) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + str3 + " existing=" + findFragmentById);
        }
        if (findFragmentById == null) {
            Fragment instantiate = this.n.instantiate(context2, str3, (Bundle) null);
            instantiate.mFromLayout = true;
            instantiate.mFragmentId = resourceId != 0 ? resourceId : i2;
            instantiate.mContainerId = i2;
            instantiate.mTag = string;
            instantiate.mInLayout = true;
            instantiate.mFragmentManager = this;
            FragmentHostCallback fragmentHostCallback = this.m;
            instantiate.mHost = fragmentHostCallback;
            instantiate.onInflate(fragmentHostCallback.b(), attributeSet2, instantiate.mSavedFragmentState);
            addFragment(instantiate, true);
            fragment = instantiate;
        } else if (!findFragmentById.mInLayout) {
            findFragmentById.mInLayout = true;
            findFragmentById.mHost = this.m;
            if (!findFragmentById.mRetaining) {
                findFragmentById.onInflate(this.m.b(), attributeSet2, findFragmentById.mSavedFragmentState);
            }
            fragment = findFragmentById;
        } else {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(i2) + " with another fragment for " + str3);
        }
        if (this.l >= 1 || !fragment.mFromLayout) {
            a(fragment);
        } else {
            a(fragment, 1, 0, 0, false);
        }
        if (fragment.mView != null) {
            if (resourceId != 0) {
                fragment.mView.setId(resourceId);
            }
            if (fragment.mView.getTag() == null) {
                fragment.mView.setTag(string);
            }
            return fragment.mView;
        }
        throw new IllegalStateException("Fragment " + str3 + " did not create a view.");
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView((View) null, str, context, attributeSet);
    }

    public void performPendingDeferredStart(Fragment fragment) {
        if (!fragment.mDeferStart) {
            return;
        }
        if (this.c) {
            this.w = true;
            return;
        }
        fragment.mDeferStart = false;
        a(fragment, this.l, 0, 0, false);
    }

    public void popBackStack() {
        enqueueAction(new PopBackStackState((String) null, -1, 0), false);
    }

    public void popBackStack(int i2, int i3) {
        if (i2 >= 0) {
            enqueueAction(new PopBackStackState((String) null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    public void popBackStack(@Nullable String str, int i2) {
        enqueueAction(new PopBackStackState(str, -1, i2), false);
    }

    public boolean popBackStackImmediate() {
        checkStateLoss();
        return popBackStackImmediate((String) null, -1, 0);
    }

    public boolean popBackStackImmediate(int i2, int i3) {
        checkStateLoss();
        execPendingActions();
        if (i2 >= 0) {
            return popBackStackImmediate((String) null, i2, i3);
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    public boolean popBackStackImmediate(@Nullable String str, int i2) {
        checkStateLoss();
        return popBackStackImmediate(str, -1, i2);
    }

    public void putFragment(Bundle bundle, String str, Fragment fragment) {
        if (fragment.mIndex < 0) {
            throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putInt(str, fragment.mIndex);
    }

    public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z2) {
        this.mLifecycleCallbacks.add(new FragmentLifecycleCallbacksHolder(fragmentLifecycleCallbacks, z2));
    }

    public void removeFragment(Fragment fragment) {
        if (a) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z2 = !fragment.isInBackStack();
        if (!fragment.mDetached || z2) {
            synchronized (this.e) {
                this.e.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.r = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onBackStackChangedListener) {
        ArrayList<FragmentManager.OnBackStackChangedListener> arrayList = this.k;
        if (arrayList != null) {
            arrayList.remove(onBackStackChangedListener);
        }
    }

    @Nullable
    public Fragment.SavedState saveFragmentInstanceState(Fragment fragment) {
        Bundle h2;
        if (fragment.mIndex < 0) {
            throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        if (fragment.mState <= 0 || (h2 = h(fragment)) == null) {
            return null;
        }
        return new Fragment.SavedState(h2);
    }

    public void setBackStackIndex(int i2, BackStackRecord backStackRecord) {
        synchronized (this) {
            if (this.i == null) {
                this.i = new ArrayList<>();
            }
            int size = this.i.size();
            if (i2 < size) {
                if (a) {
                    Log.v("FragmentManager", "Setting back stack index " + i2 + " to " + backStackRecord);
                }
                this.i.set(i2, backStackRecord);
            } else {
                while (size < i2) {
                    this.i.add((Object) null);
                    if (this.j == null) {
                        this.j = new ArrayList<>();
                    }
                    if (a) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.j.add(Integer.valueOf(size));
                    size++;
                }
                if (a) {
                    Log.v("FragmentManager", "Adding back stack index " + i2 + " with " + backStackRecord);
                }
                this.i.add(backStackRecord);
            }
        }
    }

    public void setPrimaryNavigationFragment(Fragment fragment) {
        if (fragment == null || (this.f.get(fragment.mIndex) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this))) {
            this.p = fragment;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void showFragment(Fragment fragment) {
        if (a) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Object obj = this.o;
        if (obj == null) {
            obj = this.m;
        }
        DebugUtils.buildShortClassTag(obj, sb);
        sb.append("}}");
        return sb.toString();
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        synchronized (this.mLifecycleCallbacks) {
            int i2 = 0;
            int size = this.mLifecycleCallbacks.size();
            while (true) {
                if (i2 >= size) {
                    break;
                } else if (this.mLifecycleCallbacks.get(i2).a == fragmentLifecycleCallbacks) {
                    this.mLifecycleCallbacks.remove(i2);
                    break;
                } else {
                    i2++;
                }
            }
        }
    }
}
