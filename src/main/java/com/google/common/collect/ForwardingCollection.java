package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class ForwardingCollection<E> extends ForwardingObject implements Collection<E> {
    protected ForwardingCollection() {
    }

    /* access modifiers changed from: protected */
    public boolean a(@Nullable Object obj) {
        return Iterators.contains(iterator(), obj);
    }

    /* access modifiers changed from: protected */
    public boolean a(Collection<?> collection) {
        return Collections2.a((Collection<?>) this, collection);
    }

    /* access modifiers changed from: protected */
    public <T> T[] a(T[] tArr) {
        return ObjectArrays.a((Collection<?>) this, tArr);
    }

    @CanIgnoreReturnValue
    public boolean add(E e) {
        return delegate().add(e);
    }

    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        return delegate().addAll(collection);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract Collection<E> delegate();

    /* access modifiers changed from: protected */
    public boolean b(@Nullable Object obj) {
        Iterator it = iterator();
        while (it.hasNext()) {
            if (Objects.equal(it.next(), obj)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean b(Collection<? extends E> collection) {
        return Iterators.addAll(this, collection.iterator());
    }

    /* access modifiers changed from: protected */
    public boolean c(Collection<?> collection) {
        return Iterators.removeAll(iterator(), collection);
    }

    public void clear() {
        delegate().clear();
    }

    public boolean contains(Object obj) {
        return delegate().contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return delegate().containsAll(collection);
    }

    /* access modifiers changed from: protected */
    public boolean d(Collection<?> collection) {
        return Iterators.retainAll(iterator(), collection);
    }

    /* access modifiers changed from: protected */
    public String f() {
        return Collections2.a((Collection<?>) this);
    }

    /* access modifiers changed from: protected */
    public Object[] g() {
        return toArray(new Object[size()]);
    }

    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    public Iterator<E> iterator() {
        return delegate().iterator();
    }

    @CanIgnoreReturnValue
    public boolean remove(Object obj) {
        return delegate().remove(obj);
    }

    @CanIgnoreReturnValue
    public boolean removeAll(Collection<?> collection) {
        return delegate().removeAll(collection);
    }

    @CanIgnoreReturnValue
    public boolean retainAll(Collection<?> collection) {
        return delegate().retainAll(collection);
    }

    public int size() {
        return delegate().size();
    }

    public Object[] toArray() {
        return delegate().toArray();
    }

    @CanIgnoreReturnValue
    public <T> T[] toArray(T[] tArr) {
        return delegate().toArray(tArr);
    }
}
