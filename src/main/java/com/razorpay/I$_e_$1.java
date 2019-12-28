package com.razorpay;

import android.app.Activity;

class I$_e_$1 implements Callback {
    private /* synthetic */ Object R$$r_$7229b5a8;

    public I$_e_$1(Object obj) {
        this.R$$r_$7229b5a8 = obj;
    }

    public final void run(t_$J_ t__j_) {
        if (t__j_.a_$P$() != null) {
            try {
                String versionFromJsonString = BaseUtils.getVersionFromJsonString(t__j_.a_$P$(), (String) ((Class) K$$z$.G__G_(15392, 18, 18)).getField("R$$r_").get((Object) null));
                if (!BaseUtils.getLocalVersion((Activity) ((Class) K$$z$.G__G_(15392, 18, 18)).getDeclaredField("d__1_").get(this.R$$r_$7229b5a8), (String) ((Class) K$$z$.G__G_(15392, 18, 18)).getField("R$$r_").get((Object) null)).equals(versionFromJsonString)) {
                    Object obj = this.R$$r_$7229b5a8;
                    Object[] objArr = new Object[2];
                    objArr[1] = versionFromJsonString;
                    objArr[0] = obj;
                    ((Class) K$$z$.G__G_(15392, 18, 18)).getDeclaredMethod("R$$r_", new Class[]{(Class) K$$z$.G__G_(15392, 18, 18), String.class}).invoke((Object) null, objArr);
                }
            } catch (Exception e) {
                AnalyticsUtil.reportError(e, "error", "Could not extract version from server json");
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
