package com.razorpay;

import android.content.Context;
import java.lang.Thread;

final class C__D$ implements Thread.UncaughtExceptionHandler {
    Context R$$r_;
    Thread.UncaughtExceptionHandler a_$P$;

    C__D$(Context context, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.a_$P$ = uncaughtExceptionHandler;
        this.R$$r_ = context;
    }

    public final void uncaughtException(Thread thread, final Throwable th) {
        new Thread() {
            public final void run() {
                AnalyticsUtil.reportUncaughtException(th);
                AnalyticsUtil.saveEventsToPreferences(C__D$.this.R$$r_);
            }
        }.start();
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.a_$P$;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
