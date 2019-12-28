package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    private static final int CUTOFF = 751619276;
    private static final double DESIRED_LOAD_FACTOR = 0.7d;
    @LazyInit
    private transient ImmutableList<E> asList;

    public static class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        public Builder() {
            this(4);
        }

        Builder(int i) {
            super(i);
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E e) {
            super.add(e);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            super.add(eArr);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            super.addAll(iterable);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            super.addAll(it);
            return this;
        }

        public ImmutableSet<E> build() {
            ImmutableSet<E> a = ImmutableSet.construct(this.b, this.a);
            this.b = a.size();
            return a;
        }
    }

    static abstract class Indexed<E> extends ImmutableSet<E> {
        Indexed() {
        }

        /* access modifiers changed from: package-private */
        public abstract E a(int i);

        /* access modifiers changed from: package-private */
        public ImmutableList<E> d() {
            return new ImmutableAsList<E>() {
                /* access modifiers changed from: package-private */
                /* renamed from: c */
                public Indexed<E> b() {
                    return Indexed.this;
                }

                public E get(int i) {
                    return Indexed.this.a(i);
                }
            };
        }

        public UnmodifiableIterator<E> iterator() {
            return asList().iterator();
        }
    }

    private static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] a;

        SerializedForm(Object[] objArr) {
            this.a = objArr;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return ImmutableSet.copyOf((E[]) this.a);
        }
    }

    ImmutableSet() {
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    @VisibleForTesting
    static int c(int i) {
        boolean z = true;
        if (i < CUTOFF) {
            int highestOneBit = Integer.highestOneBit(i - 1) << 1;
            while (((double) highestOneBit) * DESIRED_LOAD_FACTOR < ((double) i)) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        if (i >= 1073741824) {
            z = false;
        }
        Preconditions.checkArgument(z, "collection too large");
        return Ints.MAX_POWER_OF_TWO;
    }

    /* access modifiers changed from: private */
    public static <E> ImmutableSet<E> construct(int i, Object... objArr) {
        switch (i) {
            case 0:
                return of();
            case 1:
                return of(objArr[0]);
            default:
                int c = c(i);
                Object[] objArr2 = new Object[c];
                int i2 = c - 1;
                int i3 = 0;
                int i4 = 0;
                for (int i5 = 0; i5 < i; i5++) {
                    Object a = ObjectArrays.a(objArr[i5], i5);
                    int hashCode = a.hashCode();
                    int a2 = Hashing.a(hashCode);
                    while (true) {
                        int i6 = a2 & i2;
                        Object obj = objArr2[i6];
                        if (obj == null) {
                            objArr[i3] = a;
                            objArr2[i6] = a;
                            i4 += hashCode;
                            i3++;
                        } else if (!obj.equals(a)) {
                            a2++;
                        }
                    }
                }
                Arrays.fill(objArr, i3, i, (Object) null);
                if (i3 == 1) {
                    return new SingletonImmutableSet(objArr[0], i4);
                }
                if (c != c(i3)) {
                    return construct(i3, objArr);
                }
                if (i3 < objArr.length) {
                    objArr = ObjectArrays.a((T[]) objArr, i3);
                }
                return new RegularImmutableSet(objArr, i4, objArr2, i2);
        }
    }

    public static <E> ImmutableSet<E> copyOf(Iterable<? extends E> iterable) {
        return iterable instanceof Collection ? copyOf((Collection) iterable) : copyOf(iterable.iterator());
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> collection) {
        if ((collection instanceof ImmutableSet) && !(collection instanceof ImmutableSortedSet)) {
            ImmutableSet<E> immutableSet = (ImmutableSet) collection;
            if (!immutableSet.a()) {
                return immutableSet;
            }
        } else if (collection instanceof EnumSet) {
            return copyOfEnumSet((EnumSet) collection);
        }
        Object[] array = collection.toArray();
        return construct(array.length, array);
    }

    public static <E> ImmutableSet<E> copyOf(Iterator<? extends E> it) {
        if (!it.hasNext()) {
            return of();
        }
        Object next = it.next();
        return !it.hasNext() ? of(next) : new Builder().add((Object) next).addAll((Iterator) it).build();
    }

    public static <E> ImmutableSet<E> copyOf(E[] eArr) {
        switch (eArr.length) {
            case 0:
                return of();
            case 1:
                return of(eArr[0]);
            default:
                return construct(eArr.length, (Object[]) eArr.clone());
        }
    }

    private static ImmutableSet copyOfEnumSet(EnumSet enumSet) {
        return ImmutableEnumSet.a(EnumSet.copyOf(enumSet));
    }

    public static <E> ImmutableSet<E> of() {
        return RegularImmutableSet.a;
    }

    public static <E> ImmutableSet<E> of(E e) {
        return new SingletonImmutableSet(e);
    }

    public static <E> ImmutableSet<E> of(E e, E e2) {
        return construct(2, e, e2);
    }

    public static <E> ImmutableSet<E> of(E e, E e2, E e3) {
        return construct(3, e, e2, e3);
    }

    public static <E> ImmutableSet<E> of(E e, E e2, E e3, E e4) {
        return construct(4, e, e2, e3, e4);
    }

    public static <E> ImmutableSet<E> of(E e, E e2, E e3, E e4, E e5) {
        return construct(5, e, e2, e3, e4, e5);
    }

    @SafeVarargs
    public static <E> ImmutableSet<E> of(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        Object[] objArr = new Object[(eArr.length + 6)];
        objArr[0] = e;
        objArr[1] = e2;
        objArr[2] = e3;
        objArr[3] = e4;
        objArr[4] = e5;
        objArr[5] = e6;
        System.arraycopy(eArr, 0, objArr, 6, eArr.length);
        return construct(objArr.length, objArr);
    }

    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> d = d();
        this.asList = d;
        return d;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> d() {
        return new RegularImmutableAsList(this, toArray());
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableSet) || !c() || !((ImmutableSet) obj).c() || hashCode() == obj.hashCode()) {
            return Sets.a((Set<?>) this, obj);
        }
        return false;
    }

    public int hashCode() {
        return Sets.a(this);
    }

    public abstract UnmodifiableIterator<E> iterator();

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }
}
