package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class Lists {

    /* renamed from: com.google.common.collect.Lists$1  reason: invalid class name */
    static class AnonymousClass1 extends RandomAccessListWrapper<E> {
        private static final long serialVersionUID = 0;

        public ListIterator<E> listIterator(int i) {
            return this.a.listIterator(i);
        }
    }

    /* renamed from: com.google.common.collect.Lists$2  reason: invalid class name */
    static class AnonymousClass2 extends AbstractListWrapper<E> {
        private static final long serialVersionUID = 0;

        public ListIterator<E> listIterator(int i) {
            return this.a.listIterator(i);
        }
    }

    private static class AbstractListWrapper<E> extends AbstractList<E> {
        final List<E> a;

        public void add(int i, E e) {
            this.a.add(i, e);
        }

        public boolean addAll(int i, Collection<? extends E> collection) {
            return this.a.addAll(i, collection);
        }

        public boolean contains(Object obj) {
            return this.a.contains(obj);
        }

        public E get(int i) {
            return this.a.get(i);
        }

        public E remove(int i) {
            return this.a.remove(i);
        }

        public E set(int i, E e) {
            return this.a.set(i, e);
        }

        public int size() {
            return this.a.size();
        }
    }

    private static final class CharSequenceAsList extends AbstractList<Character> {
        private final CharSequence sequence;

        CharSequenceAsList(CharSequence charSequence) {
            this.sequence = charSequence;
        }

        public Character get(int i) {
            Preconditions.checkElementIndex(i, size());
            return Character.valueOf(this.sequence.charAt(i));
        }

        public int size() {
            return this.sequence.length();
        }
    }

    private static class OnePlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
        private static final long serialVersionUID = 0;
        final E a;
        final E[] b;

        OnePlusArrayList(@Nullable E e, E[] eArr) {
            this.a = e;
            this.b = (Object[]) Preconditions.checkNotNull(eArr);
        }

        public E get(int i) {
            Preconditions.checkElementIndex(i, size());
            return i == 0 ? this.a : this.b[i - 1];
        }

        public int size() {
            return IntMath.saturatedAdd(this.b.length, 1);
        }
    }

    private static class Partition<T> extends AbstractList<List<T>> {
        final List<T> a;
        final int b;

        Partition(List<T> list, int i) {
            this.a = list;
            this.b = i;
        }

        public List<T> get(int i) {
            Preconditions.checkElementIndex(i, size());
            int i2 = this.b;
            int i3 = i * i2;
            return this.a.subList(i3, Math.min(i2 + i3, this.a.size()));
        }

        public boolean isEmpty() {
            return this.a.isEmpty();
        }

        public int size() {
            return IntMath.divide(this.a.size(), this.b, RoundingMode.CEILING);
        }
    }

    private static class RandomAccessListWrapper<E> extends AbstractListWrapper<E> implements RandomAccess {
    }

    private static class RandomAccessPartition<T> extends Partition<T> implements RandomAccess {
        RandomAccessPartition(List<T> list, int i) {
            super(list, i);
        }
    }

    private static class RandomAccessReverseList<T> extends ReverseList<T> implements RandomAccess {
        RandomAccessReverseList(List<T> list) {
            super(list);
        }
    }

    private static class ReverseList<T> extends AbstractList<T> {
        private final List<T> forwardList;

        ReverseList(List<T> list) {
            this.forwardList = (List) Preconditions.checkNotNull(list);
        }

        private int reverseIndex(int i) {
            int size = size();
            Preconditions.checkElementIndex(i, size);
            return (size - 1) - i;
        }

        /* access modifiers changed from: private */
        public int reversePosition(int i) {
            int size = size();
            Preconditions.checkPositionIndex(i, size);
            return size - i;
        }

        /* access modifiers changed from: package-private */
        public List<T> a() {
            return this.forwardList;
        }

        public void add(int i, @Nullable T t) {
            this.forwardList.add(reversePosition(i), t);
        }

        public void clear() {
            this.forwardList.clear();
        }

        public T get(int i) {
            return this.forwardList.get(reverseIndex(i));
        }

        public Iterator<T> iterator() {
            return listIterator();
        }

        public ListIterator<T> listIterator(int i) {
            final ListIterator<T> listIterator = this.forwardList.listIterator(reversePosition(i));
            return new ListIterator<T>() {
                boolean a;

                public void add(T t) {
                    listIterator.add(t);
                    listIterator.previous();
                    this.a = false;
                }

                public boolean hasNext() {
                    return listIterator.hasPrevious();
                }

                public boolean hasPrevious() {
                    return listIterator.hasNext();
                }

                public T next() {
                    if (hasNext()) {
                        this.a = true;
                        return listIterator.previous();
                    }
                    throw new NoSuchElementException();
                }

                public int nextIndex() {
                    return ReverseList.this.reversePosition(listIterator.nextIndex());
                }

                public T previous() {
                    if (hasPrevious()) {
                        this.a = true;
                        return listIterator.next();
                    }
                    throw new NoSuchElementException();
                }

                public int previousIndex() {
                    return nextIndex() - 1;
                }

                public void remove() {
                    CollectPreconditions.a(this.a);
                    listIterator.remove();
                    this.a = false;
                }

                public void set(T t) {
                    Preconditions.checkState(this.a);
                    listIterator.set(t);
                }
            };
        }

        public T remove(int i) {
            return this.forwardList.remove(reverseIndex(i));
        }

        /* access modifiers changed from: protected */
        public void removeRange(int i, int i2) {
            subList(i, i2).clear();
        }

        public T set(int i, @Nullable T t) {
            return this.forwardList.set(reverseIndex(i), t);
        }

        public int size() {
            return this.forwardList.size();
        }

        public List<T> subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, size());
            return Lists.reverse(this.forwardList.subList(reversePosition(i2), reversePosition(i)));
        }
    }

    private static final class StringAsImmutableList extends ImmutableList<Character> {
        private final String string;

        StringAsImmutableList(String str) {
            this.string = str;
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return false;
        }

        public Character get(int i) {
            Preconditions.checkElementIndex(i, size());
            return Character.valueOf(this.string.charAt(i));
        }

        public int indexOf(@Nullable Object obj) {
            if (obj instanceof Character) {
                return this.string.indexOf(((Character) obj).charValue());
            }
            return -1;
        }

        public int lastIndexOf(@Nullable Object obj) {
            if (obj instanceof Character) {
                return this.string.lastIndexOf(((Character) obj).charValue());
            }
            return -1;
        }

        public int size() {
            return this.string.length();
        }

        public ImmutableList<Character> subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, size());
            return Lists.charactersOf(this.string.substring(i, i2));
        }
    }

    private static class TransformingRandomAccessList<F, T> extends AbstractList<T> implements Serializable, RandomAccess {
        private static final long serialVersionUID = 0;
        final List<F> a;
        final Function<? super F, ? extends T> b;

        TransformingRandomAccessList(List<F> list, Function<? super F, ? extends T> function) {
            this.a = (List) Preconditions.checkNotNull(list);
            this.b = (Function) Preconditions.checkNotNull(function);
        }

        public void clear() {
            this.a.clear();
        }

        public T get(int i) {
            return this.b.apply(this.a.get(i));
        }

        public boolean isEmpty() {
            return this.a.isEmpty();
        }

        public Iterator<T> iterator() {
            return listIterator();
        }

        public ListIterator<T> listIterator(int i) {
            return new TransformedListIterator<F, T>(this.a.listIterator(i)) {
                /* access modifiers changed from: package-private */
                public T a(F f) {
                    return TransformingRandomAccessList.this.b.apply(f);
                }
            };
        }

        public T remove(int i) {
            return this.b.apply(this.a.remove(i));
        }

        public int size() {
            return this.a.size();
        }
    }

    private static class TransformingSequentialList<F, T> extends AbstractSequentialList<T> implements Serializable {
        private static final long serialVersionUID = 0;
        final List<F> a;
        final Function<? super F, ? extends T> b;

        TransformingSequentialList(List<F> list, Function<? super F, ? extends T> function) {
            this.a = (List) Preconditions.checkNotNull(list);
            this.b = (Function) Preconditions.checkNotNull(function);
        }

        public void clear() {
            this.a.clear();
        }

        public ListIterator<T> listIterator(int i) {
            return new TransformedListIterator<F, T>(this.a.listIterator(i)) {
                /* access modifiers changed from: package-private */
                public T a(F f) {
                    return TransformingSequentialList.this.b.apply(f);
                }
            };
        }

        public int size() {
            return this.a.size();
        }
    }

    private static class TwoPlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
        private static final long serialVersionUID = 0;
        final E a;
        final E b;
        final E[] c;

        TwoPlusArrayList(@Nullable E e, @Nullable E e2, E[] eArr) {
            this.a = e;
            this.b = e2;
            this.c = (Object[]) Preconditions.checkNotNull(eArr);
        }

        public E get(int i) {
            switch (i) {
                case 0:
                    return this.a;
                case 1:
                    return this.b;
                default:
                    Preconditions.checkElementIndex(i, size());
                    return this.c[i - 2];
            }
        }

        public int size() {
            return IntMath.saturatedAdd(this.c.length, 2);
        }
    }

    private Lists() {
    }

    @VisibleForTesting
    static int a(int i) {
        CollectPreconditions.a(i, "arraySize");
        return Ints.saturatedCast(((long) i) + 5 + ((long) (i / 10)));
    }

    static <T> List<T> a(Iterable<T> iterable) {
        return (List) iterable;
    }

    static boolean a(List<?> list, @Nullable Object obj) {
        if (obj == Preconditions.checkNotNull(list)) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list2 = (List) obj;
        int size = list.size();
        if (size != list2.size()) {
            return false;
        }
        if (!(list instanceof RandomAccess) || !(list2 instanceof RandomAccess)) {
            return Iterators.elementsEqual(list.iterator(), list2.iterator());
        }
        for (int i = 0; i < size; i++) {
            if (!Objects.equal(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static <E> List<E> asList(@Nullable E e, @Nullable E e2, E[] eArr) {
        return new TwoPlusArrayList(e, e2, eArr);
    }

    public static <E> List<E> asList(@Nullable E e, E[] eArr) {
        return new OnePlusArrayList(e, eArr);
    }

    static int b(List<?> list, @Nullable Object obj) {
        if (list instanceof RandomAccess) {
            return indexOfRandomAccess(list, obj);
        }
        ListIterator<?> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (Objects.equal(obj, listIterator.next())) {
                return listIterator.previousIndex();
            }
        }
        return -1;
    }

    static int c(List<?> list, @Nullable Object obj) {
        if (list instanceof RandomAccess) {
            return lastIndexOfRandomAccess(list, obj);
        }
        ListIterator<?> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            if (Objects.equal(obj, listIterator.previous())) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    public static <B> List<List<B>> cartesianProduct(List<? extends List<? extends B>> list) {
        return CartesianList.a(list);
    }

    public static <B> List<List<B>> cartesianProduct(List<? extends B>... listArr) {
        return cartesianProduct(Arrays.asList(listArr));
    }

    public static ImmutableList<Character> charactersOf(String str) {
        return new StringAsImmutableList((String) Preconditions.checkNotNull(str));
    }

    @Beta
    public static List<Character> charactersOf(CharSequence charSequence) {
        return new CharSequenceAsList((CharSequence) Preconditions.checkNotNull(charSequence));
    }

    private static int indexOfRandomAccess(List<?> list, @Nullable Object obj) {
        int size = list.size();
        int i = 0;
        if (obj == null) {
            while (i < size) {
                if (list.get(i) == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        while (i < size) {
            if (obj.equals(list.get(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static int lastIndexOfRandomAccess(List<?> list, @Nullable Object obj) {
        if (obj == null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                if (list.get(size) == null) {
                    return size;
                }
            }
            return -1;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            if (obj.equals(list.get(size2))) {
                return size2;
            }
        }
        return -1;
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    @GwtCompatible(serializable = true)
    @CanIgnoreReturnValue
    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> iterable) {
        Preconditions.checkNotNull(iterable);
        return iterable instanceof Collection ? new ArrayList<>(Collections2.a(iterable)) : newArrayList(iterable.iterator());
    }

    @GwtCompatible(serializable = true)
    @CanIgnoreReturnValue
    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> it) {
        ArrayList<E> newArrayList = newArrayList();
        Iterators.addAll(newArrayList, it);
        return newArrayList;
    }

    @GwtCompatible(serializable = true)
    @CanIgnoreReturnValue
    public static <E> ArrayList<E> newArrayList(E... eArr) {
        Preconditions.checkNotNull(eArr);
        ArrayList<E> arrayList = new ArrayList<>(a(eArr.length));
        Collections.addAll(arrayList, eArr);
        return arrayList;
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayListWithCapacity(int i) {
        CollectPreconditions.a(i, "initialArraySize");
        return new ArrayList<>(i);
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayListWithExpectedSize(int i) {
        return new ArrayList<>(a(i));
    }

    @GwtIncompatible
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<>();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Iterable<? extends E>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @com.google.common.annotations.GwtIncompatible
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <E> java.util.concurrent.CopyOnWriteArrayList<E> newCopyOnWriteArrayList(java.lang.Iterable<? extends E> r1) {
        /*
            boolean r0 = r1 instanceof java.util.Collection
            if (r0 == 0) goto L_0x0009
            java.util.Collection r1 = com.google.common.collect.Collections2.a(r1)
            goto L_0x000d
        L_0x0009:
            java.util.ArrayList r1 = newArrayList(r1)
        L_0x000d:
            java.util.concurrent.CopyOnWriteArrayList r0 = new java.util.concurrent.CopyOnWriteArrayList
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Lists.newCopyOnWriteArrayList(java.lang.Iterable):java.util.concurrent.CopyOnWriteArrayList");
    }

    @GwtCompatible(serializable = true)
    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList<>();
    }

    @GwtCompatible(serializable = true)
    public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> iterable) {
        LinkedList<E> newLinkedList = newLinkedList();
        Iterables.addAll(newLinkedList, iterable);
        return newLinkedList;
    }

    public static <T> List<List<T>> partition(List<T> list, int i) {
        Preconditions.checkNotNull(list);
        Preconditions.checkArgument(i > 0);
        return list instanceof RandomAccess ? new RandomAccessPartition(list, i) : new Partition(list, i);
    }

    public static <T> List<T> reverse(List<T> list) {
        return list instanceof ImmutableList ? ((ImmutableList) list).reverse() : list instanceof ReverseList ? ((ReverseList) list).a() : list instanceof RandomAccess ? new RandomAccessReverseList(list) : new ReverseList(list);
    }

    public static <F, T> List<T> transform(List<F> list, Function<? super F, ? extends T> function) {
        return list instanceof RandomAccess ? new TransformingRandomAccessList(list, function) : new TransformingSequentialList(list, function);
    }
}
