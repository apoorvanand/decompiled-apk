package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
abstract class AbstractMultiset<E> extends AbstractCollection<E> implements Multiset<E> {
    private transient Set<E> elementSet;
    private transient Set<Multiset.Entry<E>> entrySet;

    class ElementSet extends Multisets.ElementSet<E> {
        ElementSet() {
        }

        /* access modifiers changed from: package-private */
        public Multiset<E> a() {
            return AbstractMultiset.this;
        }
    }

    class EntrySet extends Multisets.EntrySet<E> {
        EntrySet() {
        }

        /* access modifiers changed from: package-private */
        public Multiset<E> a() {
            return AbstractMultiset.this;
        }

        public Iterator<Multiset.Entry<E>> iterator() {
            return AbstractMultiset.this.a();
        }

        public int size() {
            return AbstractMultiset.this.b();
        }
    }

    AbstractMultiset() {
    }

    /* access modifiers changed from: package-private */
    public abstract Iterator<Multiset.Entry<E>> a();

    @CanIgnoreReturnValue
    public int add(@Nullable E e, int i) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    public boolean add(@Nullable E e) {
        add(e, 1);
        return true;
    }

    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        return Multisets.a(this, collection);
    }

    /* access modifiers changed from: package-private */
    public abstract int b();

    /* access modifiers changed from: package-private */
    public Set<E> c() {
        return new ElementSet();
    }

    public void clear() {
        Iterators.b(a());
    }

    public boolean contains(@Nullable Object obj) {
        return count(obj) > 0;
    }

    public int count(@Nullable Object obj) {
        for (Multiset.Entry entry : entrySet()) {
            if (Objects.equal(entry.getElement(), obj)) {
                return entry.getCount();
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public Set<Multiset.Entry<E>> createEntrySet() {
        return new EntrySet();
    }

    public Set<E> elementSet() {
        Set<E> set = this.elementSet;
        if (set != null) {
            return set;
        }
        Set<E> c = c();
        this.elementSet = c;
        return c;
    }

    public Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        Set<Multiset.Entry<E>> createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    public boolean equals(@Nullable Object obj) {
        return Multisets.a((Multiset<?>) this, obj);
    }

    public int hashCode() {
        return entrySet().hashCode();
    }

    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    public Iterator<E> iterator() {
        return Multisets.a(this);
    }

    @CanIgnoreReturnValue
    public int remove(@Nullable Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    public boolean remove(@Nullable Object obj) {
        return remove(obj, 1) > 0;
    }

    @CanIgnoreReturnValue
    public boolean removeAll(Collection<?> collection) {
        return Multisets.b(this, collection);
    }

    @CanIgnoreReturnValue
    public boolean retainAll(Collection<?> collection) {
        return Multisets.c(this, collection);
    }

    @CanIgnoreReturnValue
    public int setCount(@Nullable E e, int i) {
        return Multisets.a(this, e, i);
    }

    @CanIgnoreReturnValue
    public boolean setCount(@Nullable E e, int i, int i2) {
        return Multisets.a(this, e, i, i2);
    }

    public int size() {
        return Multisets.b((Multiset<?>) this);
    }

    public String toString() {
        return entrySet().toString();
    }
}
