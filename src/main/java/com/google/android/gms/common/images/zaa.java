package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zak;

public abstract class zaa {
    final zab a;
    protected int b = 0;
    private int zamw = 0;
    private boolean zamy = false;
    private boolean zamz = true;
    private boolean zana = false;
    private boolean zanb = true;

    public zaa(Uri uri, int i) {
        this.a = new zab(uri);
        this.b = i;
    }

    /* access modifiers changed from: package-private */
    public final void a(Context context, Bitmap bitmap, boolean z) {
        Asserts.checkNotNull(bitmap);
        a(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    /* access modifiers changed from: package-private */
    public final void a(Context context, zak zak) {
        if (this.zanb) {
            a((Drawable) null, false, true, false);
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(Context context, zak zak, boolean z) {
        int i = this.b;
        a(i != 0 ? context.getResources().getDrawable(i) : null, z, false, false);
    }

    /* access modifiers changed from: protected */
    public abstract void a(Drawable drawable, boolean z, boolean z2, boolean z3);

    /* access modifiers changed from: protected */
    public final boolean a(boolean z, boolean z2) {
        return this.zamz && !z2 && !z;
    }
}
