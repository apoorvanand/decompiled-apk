package com.google.android.gms.internal.firebase_ml;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzll extends WeakReference<Throwable> {
    private final int zzade;

    public zzll(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th != null) {
            this.zzade = System.identityHashCode(th);
            return;
        }
        throw new NullPointerException("The referent cannot be null");
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (this == obj) {
                return true;
            }
            zzll zzll = (zzll) obj;
            return this.zzade == zzll.zzade && get() == zzll.get();
        }
    }

    public final int hashCode() {
        return this.zzade;
    }
}
