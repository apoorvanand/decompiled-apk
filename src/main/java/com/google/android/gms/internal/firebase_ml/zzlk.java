package com.google.android.gms.internal.firebase_ml;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class zzlk {
    private final ConcurrentHashMap<zzll, List<Throwable>> zzadc = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzadd = new ReferenceQueue<>();

    zzlk() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        while (true) {
            Reference<? extends Throwable> poll = this.zzadd.poll();
            if (poll == null) {
                break;
            }
            this.zzadc.remove(poll);
        }
        List<Throwable> list = this.zzadc.get(new zzll(th, (ReferenceQueue<Throwable>) null));
        if (!z || list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> putIfAbsent = this.zzadc.putIfAbsent(new zzll(th, this.zzadd), vector);
        return putIfAbsent == null ? vector : putIfAbsent;
    }
}
