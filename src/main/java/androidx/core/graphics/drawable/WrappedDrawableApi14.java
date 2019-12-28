package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

class WrappedDrawableApi14 extends Drawable implements Drawable.Callback, TintAwareDrawable, WrappedDrawable {
    static final PorterDuff.Mode a = PorterDuff.Mode.SRC_IN;
    DrawableWrapperState b;
    Drawable c;
    private boolean mColorFilterSet;
    private int mCurrentColor;
    private PorterDuff.Mode mCurrentMode;
    private boolean mMutated;

    protected static abstract class DrawableWrapperState extends Drawable.ConstantState {
        int a;
        Drawable.ConstantState b;
        ColorStateList c = null;
        PorterDuff.Mode d = WrappedDrawableApi14.a;

        DrawableWrapperState(@Nullable DrawableWrapperState drawableWrapperState, @Nullable Resources resources) {
            if (drawableWrapperState != null) {
                this.a = drawableWrapperState.a;
                this.b = drawableWrapperState.b;
                this.c = drawableWrapperState.c;
                this.d = drawableWrapperState.d;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return this.b != null;
        }

        public int getChangingConfigurations() {
            int i = this.a;
            Drawable.ConstantState constantState = this.b;
            return i | (constantState != null ? constantState.getChangingConfigurations() : 0);
        }

        @NonNull
        public Drawable newDrawable() {
            return newDrawable((Resources) null);
        }

        @NonNull
        public abstract Drawable newDrawable(@Nullable Resources resources);
    }

    private static class DrawableWrapperStateBase extends DrawableWrapperState {
        DrawableWrapperStateBase(@Nullable DrawableWrapperState drawableWrapperState, @Nullable Resources resources) {
            super(drawableWrapperState, resources);
        }

        @NonNull
        public Drawable newDrawable(@Nullable Resources resources) {
            return new WrappedDrawableApi14(this, resources);
        }
    }

    WrappedDrawableApi14(@Nullable Drawable drawable) {
        this.b = a();
        setWrappedDrawable(drawable);
    }

    WrappedDrawableApi14(@NonNull DrawableWrapperState drawableWrapperState, @Nullable Resources resources) {
        this.b = drawableWrapperState;
        updateLocalState(resources);
    }

    private void updateLocalState(@Nullable Resources resources) {
        DrawableWrapperState drawableWrapperState = this.b;
        if (drawableWrapperState != null && drawableWrapperState.b != null) {
            setWrappedDrawable(this.b.b.newDrawable(resources));
        }
    }

    private boolean updateTint(int[] iArr) {
        if (!b()) {
            return false;
        }
        ColorStateList colorStateList = this.b.c;
        PorterDuff.Mode mode = this.b.d;
        if (colorStateList == null || mode == null) {
            this.mColorFilterSet = false;
            clearColorFilter();
        } else {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!(this.mColorFilterSet && colorForState == this.mCurrentColor && mode == this.mCurrentMode)) {
                setColorFilter(colorForState, mode);
                this.mCurrentColor = colorForState;
                this.mCurrentMode = mode;
                this.mColorFilterSet = true;
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public DrawableWrapperState a() {
        return new DrawableWrapperStateBase(this.b, (Resources) null);
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return true;
    }

    public void draw(@NonNull Canvas canvas) {
        this.c.draw(canvas);
    }

    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        DrawableWrapperState drawableWrapperState = this.b;
        return changingConfigurations | (drawableWrapperState != null ? drawableWrapperState.getChangingConfigurations() : 0) | this.c.getChangingConfigurations();
    }

    @Nullable
    public Drawable.ConstantState getConstantState() {
        DrawableWrapperState drawableWrapperState = this.b;
        if (drawableWrapperState == null || !drawableWrapperState.a()) {
            return null;
        }
        this.b.a = getChangingConfigurations();
        return this.b;
    }

    @NonNull
    public Drawable getCurrent() {
        return this.c.getCurrent();
    }

    public int getIntrinsicHeight() {
        return this.c.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.c.getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return this.c.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return this.c.getMinimumWidth();
    }

    public int getOpacity() {
        return this.c.getOpacity();
    }

    public boolean getPadding(@NonNull Rect rect) {
        return this.c.getPadding(rect);
    }

    @NonNull
    public int[] getState() {
        return this.c.getState();
    }

    public Region getTransparentRegion() {
        return this.c.getTransparentRegion();
    }

    public final Drawable getWrappedDrawable() {
        return this.c;
    }

    public void invalidateDrawable(@NonNull Drawable drawable) {
        invalidateSelf();
    }

    @RequiresApi(19)
    public boolean isAutoMirrored() {
        return this.c.isAutoMirrored();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r1.b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isStateful() {
        /*
            r1 = this;
            boolean r0 = r1.b()
            if (r0 == 0) goto L_0x000d
            androidx.core.graphics.drawable.WrappedDrawableApi14$DrawableWrapperState r0 = r1.b
            if (r0 == 0) goto L_0x000d
            android.content.res.ColorStateList r0 = r0.c
            goto L_0x000e
        L_0x000d:
            r0 = 0
        L_0x000e:
            if (r0 == 0) goto L_0x0016
            boolean r0 = r0.isStateful()
            if (r0 != 0) goto L_0x001e
        L_0x0016:
            android.graphics.drawable.Drawable r0 = r1.c
            boolean r0 = r0.isStateful()
            if (r0 == 0) goto L_0x0020
        L_0x001e:
            r0 = 1
            goto L_0x0021
        L_0x0020:
            r0 = 0
        L_0x0021:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.WrappedDrawableApi14.isStateful():boolean");
    }

    public void jumpToCurrentState() {
        this.c.jumpToCurrentState();
    }

    @NonNull
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.b = a();
            Drawable drawable = this.c;
            if (drawable != null) {
                drawable.mutate();
            }
            DrawableWrapperState drawableWrapperState = this.b;
            if (drawableWrapperState != null) {
                Drawable drawable2 = this.c;
                drawableWrapperState.b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.mMutated = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.c;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        return this.c.setLevel(i);
    }

    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void setAlpha(int i) {
        this.c.setAlpha(i);
    }

    @RequiresApi(19)
    public void setAutoMirrored(boolean z) {
        this.c.setAutoMirrored(z);
    }

    public void setChangingConfigurations(int i) {
        this.c.setChangingConfigurations(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.c.setColorFilter(colorFilter);
    }

    public void setDither(boolean z) {
        this.c.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.c.setFilterBitmap(z);
    }

    public boolean setState(@NonNull int[] iArr) {
        return updateTint(iArr) || this.c.setState(iArr);
    }

    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    public void setTintList(ColorStateList colorStateList) {
        this.b.c = colorStateList;
        updateTint(getState());
    }

    public void setTintMode(@NonNull PorterDuff.Mode mode) {
        this.b.d = mode;
        updateTint(getState());
    }

    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.c.setVisible(z, z2);
    }

    public final void setWrappedDrawable(Drawable drawable) {
        Drawable drawable2 = this.c;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback) null);
        }
        this.c = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            DrawableWrapperState drawableWrapperState = this.b;
            if (drawableWrapperState != null) {
                drawableWrapperState.b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
