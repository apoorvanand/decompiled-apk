package com.razorpay;

public final class J$$A_ extends O_$B_ {
    private static O_$B_ a_$P$;

    private J$$A_() {
    }

    public static void d__1_(O_$B_ o_$b_) {
        a_$P$ = o_$b_;
    }

    public static O_$B_ f$_G$() {
        if (a_$P$ == null) {
            a_$P$ = new J$$A_();
        }
        return a_$P$;
    }
}
