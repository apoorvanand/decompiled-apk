package com.google.android.gms.internal.vision;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class zzcv {
    private final ConcurrentHashMap<zzcw, List<Throwable>> zzlt = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzlu = new ReferenceQueue<>();

    zzcv() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        while (true) {
            Reference<? extends Throwable> poll = this.zzlu.poll();
            if (poll != null) {
                this.zzlt.remove(poll);
            } else {
                return this.zzlt.get(new zzcw(th, (ReferenceQueue<Throwable>) null));
            }
        }
    }
}
