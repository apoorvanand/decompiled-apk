package com.razorpay;

import android.app.Activity;

class I$_e_$5 implements Callback {
    private /* synthetic */ Object Q_$2$$7229b5a8;
    private /* synthetic */ String d__1_;

    public I$_e_$5(Object obj, String str) {
        this.Q_$2$$7229b5a8 = obj;
        this.d__1_ = str;
    }

    public final void run(t_$J_ t__j_) {
        String decryptFile;
        if (t__j_.a_$P$() != null && (decryptFile = BaseUtils.decryptFile(t__j_.a_$P$())) != null) {
            if (BaseUtils.storeFileInInternal((Activity) ((Class) K$$z$.G__G_(15392, 18, 18)).getField("d__1_").get(this.Q_$2$$7229b5a8), BaseUtils.getVersionedAssetName(this.d__1_, J$$A_.f$_G$().O_$B_()), t__j_.a_$P$())) {
                Object obj = this.Q_$2$$7229b5a8;
                try {
                    Object[] objArr = new Object[2];
                    objArr[1] = decryptFile;
                    objArr[0] = obj;
                    ((Class) K$$z$.G__G_(15392, 18, 18)).getDeclaredMethod("G__G_", new Class[]{(Class) K$$z$.G__G_(15392, 18, 18), String.class}).invoke((Object) null, objArr);
                    BaseUtils.updateLocalVersion((Activity) ((Class) K$$z$.G__G_(15392, 18, 18)).getField("d__1_").get(this.Q_$2$$7229b5a8), (String) ((Class) K$$z$.G__G_(15392, 18, 18)).getField("R$$r_").get((Object) null), this.d__1_);
                } catch (Throwable th) {
                    Throwable cause = th.getCause();
                    if (cause != null) {
                        throw cause;
                    }
                    throw th;
                }
            }
        }
    }
}
