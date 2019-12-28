package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import kotlin.jvm.internal.LongCompanionObject;

final class zzfh<V> extends FutureTask<V> implements Comparable<zzfh> {
    final boolean a;
    private final String zzns;
    private final /* synthetic */ zzfc zznt;
    private final long zznw = zzfc.zznj.getAndIncrement();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfh(zzfc zzfc, Runnable runnable, boolean z, String str) {
        super(runnable, (Object) null);
        this.zznt = zzfc;
        Preconditions.checkNotNull(str);
        this.zzns = str;
        this.a = false;
        if (this.zznw == LongCompanionObject.MAX_VALUE) {
            zzfc.zzab().zzgk().zzao("Tasks index overflow");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfh(zzfc zzfc, Callable<V> callable, boolean z, String str) {
        super(callable);
        this.zznt = zzfc;
        Preconditions.checkNotNull(str);
        this.zzns = str;
        this.a = z;
        if (this.zznw == LongCompanionObject.MAX_VALUE) {
            zzfc.zzab().zzgk().zzao("Tasks index overflow");
        }
    }

    public final /* synthetic */ int compareTo(@NonNull Object obj) {
        zzfh zzfh = (zzfh) obj;
        boolean z = this.a;
        if (z != zzfh.a) {
            return z ? -1 : 1;
        }
        long j = this.zznw;
        long j2 = zzfh.zznw;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        this.zznt.zzab().zzgl().zza("Two tasks share the same index. index", Long.valueOf(this.zznw));
        return 0;
    }

    /* access modifiers changed from: protected */
    public final void setException(Throwable th) {
        this.zznt.zzab().zzgk().zza(this.zzns, th);
        if (th instanceof zzff) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }
}
