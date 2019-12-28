package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class ForwardingSet<E> extends ForwardingCollection<E> implements Set<E> {
    protected ForwardingSet() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Set<E> delegate();

    /* access modifiers changed from: protected */
    public boolean c(Collection<?> collection) {
        return Sets.a((Set<?>) this, (Collection<?>) (Collection) Preconditions.checkNotNull(collection));
    }

    /* access modifiers changed from: protected */
    public int e() {
        return Sets.a(this);
    }

    /* access modifiers changed from: protected */
    public boolean e(@Nullable Object obj) {
        return Sets.a((Set<?>) this, obj);
    }

    public boolean equals(@Nullable Object obj) {
        return obj == this || delegate().equals(obj);
    }

    public int hashCode() {
        return delegate().hashCode();
    }
}
