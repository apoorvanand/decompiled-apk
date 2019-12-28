package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContentLoadingProgressBar extends ProgressBar {
    private static final int MIN_DELAY = 500;
    private static final int MIN_SHOW_TIME = 500;
    long a;
    boolean b;
    boolean c;
    boolean d;
    private final Runnable mDelayedHide;
    private final Runnable mDelayedShow;

    public ContentLoadingProgressBar(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public ContentLoadingProgressBar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.a = -1;
        this.b = false;
        this.c = false;
        this.d = false;
        this.mDelayedHide = new Runnable() {
            public void run() {
                ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
                contentLoadingProgressBar.b = false;
                contentLoadingProgressBar.a = -1;
                contentLoadingProgressBar.setVisibility(8);
            }
        };
        this.mDelayedShow = new Runnable() {
            public void run() {
                ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
                contentLoadingProgressBar.c = false;
                if (!contentLoadingProgressBar.d) {
                    ContentLoadingProgressBar.this.a = System.currentTimeMillis();
                    ContentLoadingProgressBar.this.setVisibility(0);
                }
            }
        };
    }

    private void removeCallbacks() {
        removeCallbacks(this.mDelayedHide);
        removeCallbacks(this.mDelayedShow);
    }

    public synchronized void hide() {
        this.d = true;
        removeCallbacks(this.mDelayedShow);
        this.c = false;
        long currentTimeMillis = System.currentTimeMillis() - this.a;
        if (currentTimeMillis < 500) {
            if (this.a != -1) {
                if (!this.b) {
                    postDelayed(this.mDelayedHide, 500 - currentTimeMillis);
                    this.b = true;
                }
            }
        }
        setVisibility(8);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallbacks();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks();
    }

    public synchronized void show() {
        this.a = -1;
        this.d = false;
        removeCallbacks(this.mDelayedHide);
        this.b = false;
        if (!this.c) {
            postDelayed(this.mDelayedShow, 500);
            this.c = true;
        }
    }
}
