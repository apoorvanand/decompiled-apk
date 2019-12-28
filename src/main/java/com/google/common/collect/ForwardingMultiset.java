package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class ForwardingMultiset<E> extends ForwardingCollection<E> implements Multiset<E> {

    @Beta
    protected class StandardElementSet extends Multisets.ElementSet<E> {
        public StandardElementSet() {
        }

        /* access modifiers changed from: package-private */
        public Multiset<E> a() {
            return ForwardingMultiset.this;
        }
    }

    protected ForwardingMultiset() {
    }

    /* access modifiers changed from: protected */
    public boolean a(@Nullable Object obj) {
        return count(obj) > 0;
    }

    @CanIgnoreReturnValue
    public int add(E e, int i) {
        return delegate().add(e, i);
    }

    /* access modifiers changed from: protected */
    public boolean b(Object obj) {
        return remove(obj, 1) > 0;
    }

    /* access modifiers changed from: protected */
    @Beta
    public boolean b(Collection<? extends E> collection) {
        return Multisets.a(this, collection);
    }

    /* access modifiers changed from: protected */
    public boolean c(Collection<?> collection) {
        return Multisets.b(this, collection);
    }

    public int count(Object obj) {
        return delegate().count(obj);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract Multiset<E> delegate();

    /* access modifiers changed from: protected */
    public boolean d(Collection<?> collection) {
        return Multisets.c(this, collection);
    }

    public Set<E> elementSet() {
        return delegate().elementSet();
    }

    public Set<Multiset.Entry<E>> entrySet() {
        return delegate().entrySet();
    }

    public boolean equals(@Nullable Object obj) {
        return obj == this || delegate().equals(obj);
    }

    /* access modifiers changed from: protected */
    public String f() {
        return entrySet().toString();
    }

    public int hashCode() {
        return delegate().hashCode();
    }

    @CanIgnoreReturnValue
    public int remove(Object obj, int i) {
        return delegate().remove(obj, i);
    }

    @CanIgnoreReturnValue
    public int setCount(E e, int i) {
        return delegate().setCount(e, i);
    }

    @CanIgnoreReturnValue
    public boolean setCount(E e, int i, int i2) {
        return delegate().setCount(e, i, i2);
    }
}
