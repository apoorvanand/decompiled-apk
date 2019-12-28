package androidx.appcompat.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

class ActionBarBackgroundDrawable extends Drawable {
    final ActionBarContainer a;

    public ActionBarBackgroundDrawable(ActionBarContainer actionBarContainer) {
        this.a = actionBarContainer;
    }

    public void draw(Canvas canvas) {
        Drawable drawable;
        if (!this.a.d) {
            if (this.a.a != null) {
                this.a.a.draw(canvas);
            }
            if (this.a.b != null && this.a.e) {
                drawable = this.a.b;
            } else {
                return;
            }
        } else if (this.a.c != null) {
            drawable = this.a.c;
        } else {
            return;
        }
        drawable.draw(canvas);
    }

    public int getOpacity() {
        return 0;
    }

    @RequiresApi(21)
    public void getOutline(@NonNull Outline outline) {
        Drawable drawable;
        if (this.a.d) {
            if (this.a.c != null) {
                drawable = this.a.c;
            } else {
                return;
            }
        } else if (this.a.a != null) {
            drawable = this.a.a;
        } else {
            return;
        }
        drawable.getOutline(outline);
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}
