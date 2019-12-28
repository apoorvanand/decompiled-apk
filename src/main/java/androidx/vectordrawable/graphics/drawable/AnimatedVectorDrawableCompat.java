package androidx.vectordrawable.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.collection.ArrayMap;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

public class AnimatedVectorDrawableCompat extends VectorDrawableCommon implements Animatable2Compat {
    private static final String ANIMATED_VECTOR = "animated-vector";
    private static final boolean DBG_ANIMATION_VECTOR_DRAWABLE = false;
    private static final String LOGTAG = "AnimatedVDCompat";
    private static final String TARGET = "target";
    AnimatedVectorDrawableDelegateState a;
    ArrayList<Animatable2Compat.AnimationCallback> b;
    final Drawable.Callback c;
    private AnimatedVectorDrawableCompatState mAnimatedVectorState;
    private Animator.AnimatorListener mAnimatorListener;
    private ArgbEvaluator mArgbEvaluator;
    private Context mContext;

    private static class AnimatedVectorDrawableCompatState extends Drawable.ConstantState {
        int a;
        VectorDrawableCompat b;
        AnimatorSet c;
        ArrayList<Animator> d;
        ArrayMap<Animator, String> e;

        public AnimatedVectorDrawableCompatState(Context context, AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState, Drawable.Callback callback, Resources resources) {
            if (animatedVectorDrawableCompatState != null) {
                this.a = animatedVectorDrawableCompatState.a;
                VectorDrawableCompat vectorDrawableCompat = animatedVectorDrawableCompatState.b;
                if (vectorDrawableCompat != null) {
                    Drawable.ConstantState constantState = vectorDrawableCompat.getConstantState();
                    this.b = (VectorDrawableCompat) (resources != null ? constantState.newDrawable(resources) : constantState.newDrawable());
                    this.b = (VectorDrawableCompat) this.b.mutate();
                    this.b.setCallback(callback);
                    this.b.setBounds(animatedVectorDrawableCompatState.b.getBounds());
                    this.b.a(false);
                }
                ArrayList<Animator> arrayList = animatedVectorDrawableCompatState.d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.d = new ArrayList<>(size);
                    this.e = new ArrayMap<>(size);
                    for (int i = 0; i < size; i++) {
                        Animator animator = animatedVectorDrawableCompatState.d.get(i);
                        Animator clone = animator.clone();
                        String str = animatedVectorDrawableCompatState.e.get(animator);
                        clone.setTarget(this.b.a(str));
                        this.d.add(clone);
                        this.e.put(clone, str);
                    }
                    setupAnimatorSet();
                }
            }
        }

        public int getChangingConfigurations() {
            return this.a;
        }

        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public Drawable newDrawable(Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public void setupAnimatorSet() {
            if (this.c == null) {
                this.c = new AnimatorSet();
            }
            this.c.playTogether(this.d);
        }
    }

    @RequiresApi(24)
    private static class AnimatedVectorDrawableDelegateState extends Drawable.ConstantState {
        private final Drawable.ConstantState mDelegateState;

        public AnimatedVectorDrawableDelegateState(Drawable.ConstantState constantState) {
            this.mDelegateState = constantState;
        }

        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }

        public Drawable newDrawable() {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
            animatedVectorDrawableCompat.d = this.mDelegateState.newDrawable();
            animatedVectorDrawableCompat.d.setCallback(animatedVectorDrawableCompat.c);
            return animatedVectorDrawableCompat;
        }

        public Drawable newDrawable(Resources resources) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
            animatedVectorDrawableCompat.d = this.mDelegateState.newDrawable(resources);
            animatedVectorDrawableCompat.d.setCallback(animatedVectorDrawableCompat.c);
            return animatedVectorDrawableCompat;
        }

        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
            animatedVectorDrawableCompat.d = this.mDelegateState.newDrawable(resources, theme);
            animatedVectorDrawableCompat.d.setCallback(animatedVectorDrawableCompat.c);
            return animatedVectorDrawableCompat;
        }
    }

    AnimatedVectorDrawableCompat() {
        this((Context) null, (AnimatedVectorDrawableCompatState) null, (Resources) null);
    }

    private AnimatedVectorDrawableCompat(@Nullable Context context) {
        this(context, (AnimatedVectorDrawableCompatState) null, (Resources) null);
    }

    private AnimatedVectorDrawableCompat(@Nullable Context context, @Nullable AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState, @Nullable Resources resources) {
        this.mArgbEvaluator = null;
        this.mAnimatorListener = null;
        this.b = null;
        this.c = new Drawable.Callback() {
            public void invalidateDrawable(Drawable drawable) {
                AnimatedVectorDrawableCompat.this.invalidateSelf();
            }

            public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
                AnimatedVectorDrawableCompat.this.scheduleSelf(runnable, j);
            }

            public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
                AnimatedVectorDrawableCompat.this.unscheduleSelf(runnable);
            }
        };
        this.mContext = context;
        if (animatedVectorDrawableCompatState != null) {
            this.mAnimatedVectorState = animatedVectorDrawableCompatState;
        } else {
            this.mAnimatedVectorState = new AnimatedVectorDrawableCompatState(context, animatedVectorDrawableCompatState, this.c, resources);
        }
    }

    public static void clearAnimationCallbacks(Drawable drawable) {
        if (drawable != null && (drawable instanceof Animatable)) {
            if (Build.VERSION.SDK_INT >= 24) {
                ((AnimatedVectorDrawable) drawable).clearAnimationCallbacks();
            } else {
                ((AnimatedVectorDrawableCompat) drawable).clearAnimationCallbacks();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0047 A[Catch:{ IOException | XmlPullParserException -> 0x005c }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0054 A[Catch:{ IOException | XmlPullParserException -> 0x005c }] */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat create(@androidx.annotation.NonNull android.content.Context r4, @androidx.annotation.DrawableRes int r5) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 24
            if (r0 < r1) goto L_0x002e
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat r0 = new androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
            r0.<init>(r4)
            android.content.res.Resources r1 = r4.getResources()
            android.content.res.Resources$Theme r4 = r4.getTheme()
            android.graphics.drawable.Drawable r4 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r5, r4)
            r0.d = r4
            android.graphics.drawable.Drawable r4 = r0.d
            android.graphics.drawable.Drawable$Callback r5 = r0.c
            r4.setCallback(r5)
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat$AnimatedVectorDrawableDelegateState r4 = new androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat$AnimatedVectorDrawableDelegateState
            android.graphics.drawable.Drawable r5 = r0.d
            android.graphics.drawable.Drawable$ConstantState r5 = r5.getConstantState()
            r4.<init>(r5)
            r0.a = r4
            return r0
        L_0x002e:
            android.content.res.Resources r0 = r4.getResources()
            android.content.res.XmlResourceParser r5 = r0.getXml(r5)     // Catch:{ XmlPullParserException -> 0x005e, IOException -> 0x005c }
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r5)     // Catch:{ XmlPullParserException -> 0x005e, IOException -> 0x005c }
        L_0x003a:
            int r1 = r5.next()     // Catch:{ XmlPullParserException -> 0x005e, IOException -> 0x005c }
            r2 = 2
            if (r1 == r2) goto L_0x0045
            r3 = 1
            if (r1 == r3) goto L_0x0045
            goto L_0x003a
        L_0x0045:
            if (r1 != r2) goto L_0x0054
            android.content.res.Resources r1 = r4.getResources()     // Catch:{ XmlPullParserException -> 0x005e, IOException -> 0x005c }
            android.content.res.Resources$Theme r2 = r4.getTheme()     // Catch:{ XmlPullParserException -> 0x005e, IOException -> 0x005c }
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat r4 = createFromXmlInner(r4, r1, r5, r0, r2)     // Catch:{ XmlPullParserException -> 0x005e, IOException -> 0x005c }
            return r4
        L_0x0054:
            org.xmlpull.v1.XmlPullParserException r4 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ XmlPullParserException -> 0x005e, IOException -> 0x005c }
            java.lang.String r5 = "No start tag found"
            r4.<init>(r5)     // Catch:{ XmlPullParserException -> 0x005e, IOException -> 0x005c }
            throw r4     // Catch:{ XmlPullParserException -> 0x005e, IOException -> 0x005c }
        L_0x005c:
            r4 = move-exception
            goto L_0x005f
        L_0x005e:
            r4 = move-exception
        L_0x005f:
            java.lang.String r5 = "AnimatedVDCompat"
            java.lang.String r0 = "parser error"
            android.util.Log.e(r5, r0, r4)
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat.create(android.content.Context, int):androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat");
    }

    public static AnimatedVectorDrawableCompat createFromXmlInner(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(context);
        animatedVectorDrawableCompat.inflate(resources, xmlPullParser, attributeSet, theme);
        return animatedVectorDrawableCompat;
    }

    public static void registerAnimationCallback(Drawable drawable, Animatable2Compat.AnimationCallback animationCallback) {
        if (drawable != null && animationCallback != null && (drawable instanceof Animatable)) {
            if (Build.VERSION.SDK_INT >= 24) {
                registerPlatformCallback((AnimatedVectorDrawable) drawable, animationCallback);
            } else {
                ((AnimatedVectorDrawableCompat) drawable).registerAnimationCallback(animationCallback);
            }
        }
    }

    @RequiresApi(23)
    private static void registerPlatformCallback(@NonNull AnimatedVectorDrawable animatedVectorDrawable, @NonNull Animatable2Compat.AnimationCallback animationCallback) {
        animatedVectorDrawable.registerAnimationCallback(animationCallback.a());
    }

    private void removeAnimatorSetListener() {
        if (this.mAnimatorListener != null) {
            this.mAnimatedVectorState.c.removeListener(this.mAnimatorListener);
            this.mAnimatorListener = null;
        }
    }

    private void setupAnimatorsForTarget(String str, Animator animator) {
        animator.setTarget(this.mAnimatedVectorState.b.a(str));
        if (Build.VERSION.SDK_INT < 21) {
            setupColorAnimator(animator);
        }
        if (this.mAnimatedVectorState.d == null) {
            this.mAnimatedVectorState.d = new ArrayList<>();
            this.mAnimatedVectorState.e = new ArrayMap<>();
        }
        this.mAnimatedVectorState.d.add(animator);
        this.mAnimatedVectorState.e.put(animator, str);
    }

    private void setupColorAnimator(Animator animator) {
        ArrayList<Animator> childAnimations;
        if ((animator instanceof AnimatorSet) && (childAnimations = ((AnimatorSet) animator).getChildAnimations()) != null) {
            for (int i = 0; i < childAnimations.size(); i++) {
                setupColorAnimator(childAnimations.get(i));
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.mArgbEvaluator == null) {
                    this.mArgbEvaluator = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.mArgbEvaluator);
            }
        }
    }

    public static boolean unregisterAnimationCallback(Drawable drawable, Animatable2Compat.AnimationCallback animationCallback) {
        if (drawable == null || animationCallback == null || !(drawable instanceof Animatable)) {
            return false;
        }
        return Build.VERSION.SDK_INT >= 24 ? unregisterPlatformCallback((AnimatedVectorDrawable) drawable, animationCallback) : ((AnimatedVectorDrawableCompat) drawable).unregisterAnimationCallback(animationCallback);
    }

    @RequiresApi(23)
    private static boolean unregisterPlatformCallback(AnimatedVectorDrawable animatedVectorDrawable, Animatable2Compat.AnimationCallback animationCallback) {
        return animatedVectorDrawable.unregisterAnimationCallback(animationCallback.a());
    }

    public void applyTheme(Resources.Theme theme) {
        if (this.d != null) {
            DrawableCompat.applyTheme(this.d, theme);
        }
    }

    public boolean canApplyTheme() {
        if (this.d != null) {
            return DrawableCompat.canApplyTheme(this.d);
        }
        return false;
    }

    public void clearAnimationCallbacks() {
        if (this.d != null) {
            ((AnimatedVectorDrawable) this.d).clearAnimationCallbacks();
            return;
        }
        removeAnimatorSetListener();
        ArrayList<Animatable2Compat.AnimationCallback> arrayList = this.b;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public void draw(Canvas canvas) {
        if (this.d != null) {
            this.d.draw(canvas);
            return;
        }
        this.mAnimatedVectorState.b.draw(canvas);
        if (this.mAnimatedVectorState.c.isStarted()) {
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.d != null ? DrawableCompat.getAlpha(this.d) : this.mAnimatedVectorState.b.getAlpha();
    }

    public int getChangingConfigurations() {
        return this.d != null ? this.d.getChangingConfigurations() : super.getChangingConfigurations() | this.mAnimatedVectorState.a;
    }

    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public Drawable.ConstantState getConstantState() {
        if (this.d == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new AnimatedVectorDrawableDelegateState(this.d.getConstantState());
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public int getIntrinsicHeight() {
        return this.d != null ? this.d.getIntrinsicHeight() : this.mAnimatedVectorState.b.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.d != null ? this.d.getIntrinsicWidth() : this.mAnimatedVectorState.b.getIntrinsicWidth();
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public int getOpacity() {
        return this.d != null ? this.d.getOpacity() : this.mAnimatedVectorState.b.getOpacity();
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        inflate(resources, xmlPullParser, attributeSet, (Resources.Theme) null);
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray obtainAttributes;
        if (this.d != null) {
            DrawableCompat.inflate(this.d, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (ANIMATED_VECTOR.equals(name)) {
                    obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.e);
                    int resourceId = obtainAttributes.getResourceId(0, 0);
                    if (resourceId != 0) {
                        VectorDrawableCompat create = VectorDrawableCompat.create(resources, resourceId, theme);
                        create.a(false);
                        create.setCallback(this.c);
                        if (this.mAnimatedVectorState.b != null) {
                            this.mAnimatedVectorState.b.setCallback((Drawable.Callback) null);
                        }
                        this.mAnimatedVectorState.b = create;
                    }
                } else if (TARGET.equals(name)) {
                    obtainAttributes = resources.obtainAttributes(attributeSet, AndroidResources.f);
                    String string = obtainAttributes.getString(0);
                    int resourceId2 = obtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.mContext;
                        if (context != null) {
                            setupAnimatorsForTarget(string, AnimatorInflaterCompat.loadAnimator(context, resourceId2));
                        } else {
                            obtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                    }
                } else {
                    continue;
                }
                obtainAttributes.recycle();
            }
            eventType = xmlPullParser.next();
        }
        this.mAnimatedVectorState.setupAnimatorSet();
    }

    public boolean isAutoMirrored() {
        return this.d != null ? DrawableCompat.isAutoMirrored(this.d) : this.mAnimatedVectorState.b.isAutoMirrored();
    }

    public boolean isRunning() {
        return this.d != null ? ((AnimatedVectorDrawable) this.d).isRunning() : this.mAnimatedVectorState.c.isRunning();
    }

    public boolean isStateful() {
        return this.d != null ? this.d.isStateful() : this.mAnimatedVectorState.b.isStateful();
    }

    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public Drawable mutate() {
        if (this.d != null) {
            this.d.mutate();
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        if (this.d != null) {
            this.d.setBounds(rect);
        } else {
            this.mAnimatedVectorState.b.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        return this.d != null ? this.d.setLevel(i) : this.mAnimatedVectorState.b.setLevel(i);
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        return this.d != null ? this.d.setState(iArr) : this.mAnimatedVectorState.b.setState(iArr);
    }

    public void registerAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        if (this.d != null) {
            registerPlatformCallback((AnimatedVectorDrawable) this.d, animationCallback);
        } else if (animationCallback != null) {
            if (this.b == null) {
                this.b = new ArrayList<>();
            }
            if (!this.b.contains(animationCallback)) {
                this.b.add(animationCallback);
                if (this.mAnimatorListener == null) {
                    this.mAnimatorListener = new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            ArrayList arrayList = new ArrayList(AnimatedVectorDrawableCompat.this.b);
                            int size = arrayList.size();
                            for (int i = 0; i < size; i++) {
                                ((Animatable2Compat.AnimationCallback) arrayList.get(i)).onAnimationEnd(AnimatedVectorDrawableCompat.this);
                            }
                        }

                        public void onAnimationStart(Animator animator) {
                            ArrayList arrayList = new ArrayList(AnimatedVectorDrawableCompat.this.b);
                            int size = arrayList.size();
                            for (int i = 0; i < size; i++) {
                                ((Animatable2Compat.AnimationCallback) arrayList.get(i)).onAnimationStart(AnimatedVectorDrawableCompat.this);
                            }
                        }
                    };
                }
                this.mAnimatedVectorState.c.addListener(this.mAnimatorListener);
            }
        }
    }

    public void setAlpha(int i) {
        if (this.d != null) {
            this.d.setAlpha(i);
        } else {
            this.mAnimatedVectorState.b.setAlpha(i);
        }
    }

    public void setAutoMirrored(boolean z) {
        if (this.d != null) {
            DrawableCompat.setAutoMirrored(this.d, z);
        } else {
            this.mAnimatedVectorState.b.setAutoMirrored(z);
        }
    }

    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.d != null) {
            this.d.setColorFilter(colorFilter);
        } else {
            this.mAnimatedVectorState.b.setColorFilter(colorFilter);
        }
    }

    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    public /* bridge */ /* synthetic */ void setHotspot(float f, float f2) {
        super.setHotspot(f, f2);
    }

    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    public void setTint(int i) {
        if (this.d != null) {
            DrawableCompat.setTint(this.d, i);
        } else {
            this.mAnimatedVectorState.b.setTint(i);
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        if (this.d != null) {
            DrawableCompat.setTintList(this.d, colorStateList);
        } else {
            this.mAnimatedVectorState.b.setTintList(colorStateList);
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        if (this.d != null) {
            DrawableCompat.setTintMode(this.d, mode);
        } else {
            this.mAnimatedVectorState.b.setTintMode(mode);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        if (this.d != null) {
            return this.d.setVisible(z, z2);
        }
        this.mAnimatedVectorState.b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    public void start() {
        if (this.d != null) {
            ((AnimatedVectorDrawable) this.d).start();
        } else if (!this.mAnimatedVectorState.c.isStarted()) {
            this.mAnimatedVectorState.c.start();
            invalidateSelf();
        }
    }

    public void stop() {
        if (this.d != null) {
            ((AnimatedVectorDrawable) this.d).stop();
        } else {
            this.mAnimatedVectorState.c.end();
        }
    }

    public boolean unregisterAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        if (this.d != null) {
            unregisterPlatformCallback((AnimatedVectorDrawable) this.d, animationCallback);
        }
        ArrayList<Animatable2Compat.AnimationCallback> arrayList = this.b;
        if (arrayList == null || animationCallback == null) {
            return false;
        }
        boolean remove = arrayList.remove(animationCallback);
        if (this.b.size() == 0) {
            removeAnimatorSetListener();
        }
        return remove;
    }
}
