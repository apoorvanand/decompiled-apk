package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMultiset<E> extends ImmutableCollection<E> implements Multiset<E> {
    @LazyInit
    private transient ImmutableList<E> asList;
    @LazyInit
    private transient ImmutableSet<Multiset.Entry<E>> entrySet;

    public static class Builder<E> extends ImmutableCollection.Builder<E> {
        final Multiset<E> a;

        public Builder() {
            this(LinkedHashMultiset.create());
        }

        Builder(Multiset<E> multiset) {
            this.a = multiset;
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E e) {
            this.a.add(Preconditions.checkNotNull(e));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            super.add(eArr);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            if (iterable instanceof Multiset) {
                for (Multiset.Entry next : Multisets.b(iterable).entrySet()) {
                    addCopies(next.getElement(), next.getCount());
                }
            } else {
                super.addAll(iterable);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            super.addAll(it);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addCopies(E e, int i) {
            this.a.add(Preconditions.checkNotNull(e), i);
            return this;
        }

        public ImmutableMultiset<E> build() {
            return ImmutableMultiset.copyOf(this.a);
        }

        @CanIgnoreReturnValue
        public Builder<E> setCount(E e, int i) {
            this.a.setCount(Preconditions.checkNotNull(e), i);
            return this;
        }
    }

    private final class EntrySet extends ImmutableSet.Indexed<Multiset.Entry<E>> {
        private static final long serialVersionUID = 0;

        private EntrySet() {
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return ImmutableMultiset.this.a();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Multiset.Entry<E> a(int i) {
            return ImmutableMultiset.this.a(i);
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry entry = (Multiset.Entry) obj;
            return entry.getCount() > 0 && ImmutableMultiset.this.count(entry.getElement()) == entry.getCount();
        }

        public int hashCode() {
            return ImmutableMultiset.this.hashCode();
        }

        public int size() {
            return ImmutableMultiset.this.elementSet().size();
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new EntrySetSerializedForm(ImmutableMultiset.this);
        }
    }

    static class EntrySetSerializedForm<E> implements Serializable {
        final ImmutableMultiset<E> a;

        EntrySetSerializedForm(ImmutableMultiset<E> immutableMultiset) {
            this.a = immutableMultiset;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.a.entrySet();
        }
    }

    private static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] a;
        final int[] b;

        SerializedForm(Multiset<?> multiset) {
            int size = multiset.entrySet().size();
            this.a = new Object[size];
            this.b = new int[size];
            int i = 0;
            for (Multiset.Entry next : multiset.entrySet()) {
                this.a[i] = next.getElement();
                this.b[i] = next.getCount();
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            LinkedHashMultiset create = LinkedHashMultiset.create(this.a.length);
            int i = 0;
            while (true) {
                Object[] objArr = this.a;
                if (i >= objArr.length) {
                    return ImmutableMultiset.copyOf(create);
                }
                create.add(objArr[i], this.b[i]);
                i++;
            }
        }
    }

    ImmutableMultiset() {
    }

    static <E> ImmutableMultiset<E> a(Collection<? extends Multiset.Entry<? extends E>> collection) {
        return collection.isEmpty() ? of() : new RegularImmutableMultiset(collection);
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    private static <E> ImmutableMultiset<E> copyFromElements(E... eArr) {
        LinkedHashMultiset create = LinkedHashMultiset.create();
        Collections.addAll(create, eArr);
        return a(create.entrySet());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Iterable<? extends E>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <E> com.google.common.collect.ImmutableMultiset<E> copyOf(java.lang.Iterable<? extends E> r2) {
        /*
            boolean r0 = r2 instanceof com.google.common.collect.ImmutableMultiset
            if (r0 == 0) goto L_0x000e
            r0 = r2
            com.google.common.collect.ImmutableMultiset r0 = (com.google.common.collect.ImmutableMultiset) r0
            boolean r1 = r0.a()
            if (r1 != 0) goto L_0x000e
            return r0
        L_0x000e:
            boolean r0 = r2 instanceof com.google.common.collect.Multiset
            if (r0 == 0) goto L_0x0017
            com.google.common.collect.Multiset r2 = com.google.common.collect.Multisets.b(r2)
            goto L_0x001b
        L_0x0017:
            com.google.common.collect.LinkedHashMultiset r2 = com.google.common.collect.LinkedHashMultiset.create(r2)
        L_0x001b:
            java.util.Set r2 = r2.entrySet()
            com.google.common.collect.ImmutableMultiset r2 = a(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ImmutableMultiset.copyOf(java.lang.Iterable):com.google.common.collect.ImmutableMultiset");
    }

    public static <E> ImmutableMultiset<E> copyOf(Iterator<? extends E> it) {
        LinkedHashMultiset create = LinkedHashMultiset.create();
        Iterators.addAll(create, it);
        return a(create.entrySet());
    }

    public static <E> ImmutableMultiset<E> copyOf(E[] eArr) {
        return copyFromElements(eArr);
    }

    private final ImmutableSet<Multiset.Entry<E>> createEntrySet() {
        return isEmpty() ? ImmutableSet.of() : new EntrySet();
    }

    public static <E> ImmutableMultiset<E> of() {
        return RegularImmutableMultiset.a;
    }

    public static <E> ImmutableMultiset<E> of(E e) {
        return copyFromElements(e);
    }

    public static <E> ImmutableMultiset<E> of(E e, E e2) {
        return copyFromElements(e, e2);
    }

    public static <E> ImmutableMultiset<E> of(E e, E e2, E e3) {
        return copyFromElements(e, e2, e3);
    }

    public static <E> ImmutableMultiset<E> of(E e, E e2, E e3, E e4) {
        return copyFromElements(e, e2, e3, e4);
    }

    public static <E> ImmutableMultiset<E> of(E e, E e2, E e3, E e4, E e5) {
        return copyFromElements(e, e2, e3, e4, e5);
    }

    public static <E> ImmutableMultiset<E> of(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        return new Builder().add((Object) e).add((Object) e2).add((Object) e3).add((Object) e4).add((Object) e5).add((Object) e6).add((Object[]) eArr).build();
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public int a(Object[] objArr, int i) {
        Iterator it = entrySet().iterator();
        while (it.hasNext()) {
            Multiset.Entry entry = (Multiset.Entry) it.next();
            Arrays.fill(objArr, i, entry.getCount() + i, entry.getElement());
            i += entry.getCount();
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public abstract Multiset.Entry<E> a(int i);

    @CanIgnoreReturnValue
    @Deprecated
    public final int add(E e, int i) {
        throw new UnsupportedOperationException();
    }

    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> b = b();
        this.asList = b;
        return b;
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> b() {
        return isEmpty() ? ImmutableList.of() : new RegularImmutableAsList(this, toArray());
    }

    public boolean contains(@Nullable Object obj) {
        return count(obj) > 0;
    }

    public ImmutableSet<Multiset.Entry<E>> entrySet() {
        ImmutableSet<Multiset.Entry<E>> immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<Multiset.Entry<E>> createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    public boolean equals(@Nullable Object obj) {
        return Multisets.a((Multiset<?>) this, obj);
    }

    public int hashCode() {
        return Sets.a(entrySet());
    }

    public UnmodifiableIterator<E> iterator() {
        final UnmodifiableIterator it = entrySet().iterator();
        return new UnmodifiableIterator<E>() {
            int a;
            E b;

            public boolean hasNext() {
                return this.a > 0 || it.hasNext();
            }

            public E next() {
                if (this.a <= 0) {
                    Multiset.Entry entry = (Multiset.Entry) it.next();
                    this.b = entry.getElement();
                    this.a = entry.getCount();
                }
                this.a--;
                return this.b;
            }
        };
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final int remove(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final int setCount(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final boolean setCount(E e, int i, int i2) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return entrySet().toString();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
