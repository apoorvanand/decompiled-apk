package com.google.android.gms.internal.base;

import android.graphics.drawable.Drawable;

final class zai extends Drawable.ConstantState {
    int a;
    int b;

    zai(zai zai) {
        if (zai != null) {
            this.a = zai.a;
            this.b = zai.b;
        }
    }

    public final int getChangingConfigurations() {
        return this.a;
    }

    public final Drawable newDrawable() {
        return new zae(this);
    }
}
