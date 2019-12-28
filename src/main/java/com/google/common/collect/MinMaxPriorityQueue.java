package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

@GwtCompatible
@Beta
public final class MinMaxPriorityQueue<E> extends AbstractQueue<E> {
    private static final int DEFAULT_CAPACITY = 11;
    private static final int EVEN_POWERS_OF_TWO = 1431655765;
    private static final int ODD_POWERS_OF_TWO = -1431655766;
    @VisibleForTesting
    final int a;
    private final MinMaxPriorityQueue<E>.Heap maxHeap;
    private final MinMaxPriorityQueue<E>.Heap minHeap;
    /* access modifiers changed from: private */
    public int modCount;
    /* access modifiers changed from: private */
    public Object[] queue;
    /* access modifiers changed from: private */
    public int size;

    @Beta
    public static final class Builder<B> {
        private static final int UNSET_EXPECTED_SIZE = -1;
        private final Comparator<B> comparator;
        private int expectedSize;
        /* access modifiers changed from: private */
        public int maximumSize;

        private Builder(Comparator<B> comparator2) {
            this.expectedSize = -1;
            this.maximumSize = Integer.MAX_VALUE;
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
        }

        /* access modifiers changed from: private */
        public <T extends B> Ordering<T> ordering() {
            return Ordering.from(this.comparator);
        }

        public <T extends B> MinMaxPriorityQueue<T> create() {
            return create(Collections.emptySet());
        }

        public <T extends B> MinMaxPriorityQueue<T> create(Iterable<? extends T> iterable) {
            MinMaxPriorityQueue<T> minMaxPriorityQueue = new MinMaxPriorityQueue<>(this, MinMaxPriorityQueue.a(this.expectedSize, this.maximumSize, iterable));
            for (Object offer : iterable) {
                minMaxPriorityQueue.offer(offer);
            }
            return minMaxPriorityQueue;
        }

        @CanIgnoreReturnValue
        public Builder<B> expectedSize(int i) {
            Preconditions.checkArgument(i >= 0);
            this.expectedSize = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<B> maximumSize(int i) {
            Preconditions.checkArgument(i > 0);
            this.maximumSize = i;
            return this;
        }
    }

    private class Heap {
        final Ordering<E> a;
        @Weak
        MinMaxPriorityQueue<E>.Heap b;

        Heap(Ordering<E> ordering) {
            this.a = ordering;
        }

        private int getGrandparentIndex(int i) {
            return getParentIndex(getParentIndex(i));
        }

        private int getLeftChildIndex(int i) {
            return (i * 2) + 1;
        }

        private int getParentIndex(int i) {
            return (i - 1) / 2;
        }

        private int getRightChildIndex(int i) {
            return (i * 2) + 2;
        }

        private boolean verifyIndex(int i) {
            if (getLeftChildIndex(i) < MinMaxPriorityQueue.this.size && a(i, getLeftChildIndex(i)) > 0) {
                return false;
            }
            if (getRightChildIndex(i) < MinMaxPriorityQueue.this.size && a(i, getRightChildIndex(i)) > 0) {
                return false;
            }
            if (i <= 0 || a(i, getParentIndex(i)) <= 0) {
                return i <= 2 || a(getGrandparentIndex(i), i) <= 0;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public int a(int i) {
            return b(getLeftChildIndex(i), 2);
        }

        /* access modifiers changed from: package-private */
        public int a(int i, int i2) {
            return this.a.compare(MinMaxPriorityQueue.this.a(i), MinMaxPriorityQueue.this.a(i2));
        }

        /* access modifiers changed from: package-private */
        public int a(E e) {
            int rightChildIndex;
            int parentIndex = getParentIndex(MinMaxPriorityQueue.this.size);
            if (!(parentIndex == 0 || (rightChildIndex = getRightChildIndex(getParentIndex(parentIndex))) == parentIndex || getLeftChildIndex(rightChildIndex) < MinMaxPriorityQueue.this.size)) {
                Object a2 = MinMaxPriorityQueue.this.a(rightChildIndex);
                if (this.a.compare(a2, e) < 0) {
                    MinMaxPriorityQueue.this.queue[rightChildIndex] = e;
                    MinMaxPriorityQueue.this.queue[MinMaxPriorityQueue.this.size] = a2;
                    return rightChildIndex;
                }
            }
            return MinMaxPriorityQueue.this.size;
        }

        /* access modifiers changed from: package-private */
        public MoveDesc<E> a(int i, int i2, E e) {
            int d = d(i2, e);
            if (d == i2) {
                return null;
            }
            Object a2 = d < i ? MinMaxPriorityQueue.this.a(i) : MinMaxPriorityQueue.this.a(getParentIndex(i));
            if (this.b.b(d, e) < i) {
                return new MoveDesc<>(e, a2);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public void a(int i, E e) {
            Heap heap;
            int c2 = c(i, e);
            if (c2 == i) {
                c2 = i;
                heap = this;
            } else {
                heap = this.b;
            }
            heap.b(c2, e);
        }

        /* access modifiers changed from: package-private */
        public int b(int i) {
            int leftChildIndex = getLeftChildIndex(i);
            if (leftChildIndex < 0) {
                return -1;
            }
            return b(getLeftChildIndex(leftChildIndex), 4);
        }

        /* access modifiers changed from: package-private */
        public int b(int i, int i2) {
            if (i >= MinMaxPriorityQueue.this.size) {
                return -1;
            }
            Preconditions.checkState(i > 0);
            int min = Math.min(i, MinMaxPriorityQueue.this.size - i2) + i2;
            for (int i3 = i + 1; i3 < min; i3++) {
                if (a(i3, i) < 0) {
                    i = i3;
                }
            }
            return i;
        }

        /* access modifiers changed from: package-private */
        @CanIgnoreReturnValue
        public int b(int i, E e) {
            while (i > 2) {
                int grandparentIndex = getGrandparentIndex(i);
                Object a2 = MinMaxPriorityQueue.this.a(grandparentIndex);
                if (this.a.compare(a2, e) <= 0) {
                    break;
                }
                MinMaxPriorityQueue.this.queue[i] = a2;
                i = grandparentIndex;
            }
            MinMaxPriorityQueue.this.queue[i] = e;
            return i;
        }

        /* access modifiers changed from: package-private */
        public int c(int i) {
            while (true) {
                int b2 = b(i);
                if (b2 <= 0) {
                    return i;
                }
                MinMaxPriorityQueue.this.queue[i] = MinMaxPriorityQueue.this.a(b2);
                i = b2;
            }
        }

        /* access modifiers changed from: package-private */
        public int c(int i, E e) {
            int rightChildIndex;
            if (i == 0) {
                MinMaxPriorityQueue.this.queue[0] = e;
                return 0;
            }
            int parentIndex = getParentIndex(i);
            Object a2 = MinMaxPriorityQueue.this.a(parentIndex);
            if (!(parentIndex == 0 || (rightChildIndex = getRightChildIndex(getParentIndex(parentIndex))) == parentIndex || getLeftChildIndex(rightChildIndex) < MinMaxPriorityQueue.this.size)) {
                Object a3 = MinMaxPriorityQueue.this.a(rightChildIndex);
                if (this.a.compare(a3, a2) < 0) {
                    parentIndex = rightChildIndex;
                    a2 = a3;
                }
            }
            if (this.a.compare(a2, e) < 0) {
                MinMaxPriorityQueue.this.queue[i] = a2;
                MinMaxPriorityQueue.this.queue[parentIndex] = e;
                return parentIndex;
            }
            MinMaxPriorityQueue.this.queue[i] = e;
            return i;
        }

        /* access modifiers changed from: package-private */
        public int d(int i, E e) {
            int a2 = a(i);
            if (a2 <= 0 || this.a.compare(MinMaxPriorityQueue.this.a(a2), e) >= 0) {
                return c(i, e);
            }
            MinMaxPriorityQueue.this.queue[i] = MinMaxPriorityQueue.this.a(a2);
            MinMaxPriorityQueue.this.queue[a2] = e;
            return a2;
        }
    }

    static class MoveDesc<E> {
        final E a;
        final E b;

        MoveDesc(E e, E e2) {
            this.a = e;
            this.b = e2;
        }
    }

    private class QueueIterator implements Iterator<E> {
        private boolean canRemove;
        private int cursor;
        private int expectedModCount;
        private Queue<E> forgetMeNot;
        private E lastFromForgetMeNot;
        private List<E> skipMe;

        private QueueIterator() {
            this.cursor = -1;
            this.expectedModCount = MinMaxPriorityQueue.this.modCount;
        }

        private boolean containsExact(Iterable<E> iterable, E e) {
            for (E e2 : iterable) {
                if (e2 == e) {
                    return true;
                }
            }
            return false;
        }

        private int nextNotInSkipMe(int i) {
            if (this.skipMe != null) {
                while (i < MinMaxPriorityQueue.this.size() && containsExact(this.skipMe, MinMaxPriorityQueue.this.a(i))) {
                    i++;
                }
            }
            return i;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            if (MinMaxPriorityQueue.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(Object obj) {
            for (int i = 0; i < MinMaxPriorityQueue.this.size; i++) {
                if (MinMaxPriorityQueue.this.queue[i] == obj) {
                    MinMaxPriorityQueue.this.b(i);
                    return true;
                }
            }
            return false;
        }

        public boolean hasNext() {
            a();
            if (nextNotInSkipMe(this.cursor + 1) < MinMaxPriorityQueue.this.size()) {
                return true;
            }
            Queue<E> queue = this.forgetMeNot;
            return queue != null && !queue.isEmpty();
        }

        public E next() {
            a();
            int nextNotInSkipMe = nextNotInSkipMe(this.cursor + 1);
            if (nextNotInSkipMe < MinMaxPriorityQueue.this.size()) {
                this.cursor = nextNotInSkipMe;
                this.canRemove = true;
                return MinMaxPriorityQueue.this.a(this.cursor);
            }
            if (this.forgetMeNot != null) {
                this.cursor = MinMaxPriorityQueue.this.size();
                this.lastFromForgetMeNot = this.forgetMeNot.poll();
                E e = this.lastFromForgetMeNot;
                if (e != null) {
                    this.canRemove = true;
                    return e;
                }
            }
            throw new NoSuchElementException("iterator moved past last element in queue.");
        }

        public void remove() {
            CollectPreconditions.a(this.canRemove);
            a();
            this.canRemove = false;
            this.expectedModCount++;
            if (this.cursor < MinMaxPriorityQueue.this.size()) {
                MoveDesc b = MinMaxPriorityQueue.this.b(this.cursor);
                if (b != null) {
                    if (this.forgetMeNot == null) {
                        this.forgetMeNot = new ArrayDeque();
                        this.skipMe = new ArrayList(3);
                    }
                    this.forgetMeNot.add(b.a);
                    this.skipMe.add(b.b);
                }
                this.cursor--;
                return;
            }
            Preconditions.checkState(a(this.lastFromForgetMeNot));
            this.lastFromForgetMeNot = null;
        }
    }

    private MinMaxPriorityQueue(Builder<? super E> builder, int i) {
        Ordering a2 = builder.ordering();
        this.minHeap = new Heap(a2);
        this.maxHeap = new Heap(a2.reverse());
        MinMaxPriorityQueue<E>.Heap heap = this.minHeap;
        MinMaxPriorityQueue<E>.Heap heap2 = this.maxHeap;
        heap.b = heap2;
        heap2.b = heap;
        this.a = builder.maximumSize;
        this.queue = new Object[i];
    }

    @VisibleForTesting
    static int a(int i, int i2, Iterable<?> iterable) {
        if (i == -1) {
            i = 11;
        }
        if (iterable instanceof Collection) {
            i = Math.max(i, ((Collection) iterable).size());
        }
        return capAtMaximumSize(i, i2);
    }

    @VisibleForTesting
    static boolean c(int i) {
        int i2 = ~(~(i + 1));
        Preconditions.checkState(i2 > 0, "negative index");
        return (EVEN_POWERS_OF_TWO & i2) > (i2 & ODD_POWERS_OF_TWO);
    }

    private int calculateNewCapacity() {
        int length = this.queue.length;
        return capAtMaximumSize(length < 64 ? (length + 1) * 2 : IntMath.checkedMultiply(length / 2, 3), this.a);
    }

    private static int capAtMaximumSize(int i, int i2) {
        return Math.min(i - 1, i2) + 1;
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create() {
        return new Builder(Ordering.natural()).create();
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create(Iterable<? extends E> iterable) {
        return new Builder(Ordering.natural()).create(iterable);
    }

    public static Builder<Comparable> expectedSize(int i) {
        return new Builder(Ordering.natural()).expectedSize(i);
    }

    private MoveDesc<E> fillHole(int i, E e) {
        MinMaxPriorityQueue<E>.Heap heapForIndex = heapForIndex(i);
        int c = heapForIndex.c(i);
        int b = heapForIndex.b(c, e);
        if (b == c) {
            return heapForIndex.a(i, c, e);
        }
        if (b < i) {
            return new MoveDesc<>(e, a(i));
        }
        return null;
    }

    private int getMaxElementIndex() {
        switch (this.size) {
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                return this.maxHeap.a(1, 2) <= 0 ? 1 : 2;
        }
    }

    private void growIfNeeded() {
        if (this.size > this.queue.length) {
            Object[] objArr = new Object[calculateNewCapacity()];
            Object[] objArr2 = this.queue;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.queue = objArr;
        }
    }

    private MinMaxPriorityQueue<E>.Heap heapForIndex(int i) {
        return c(i) ? this.minHeap : this.maxHeap;
    }

    public static Builder<Comparable> maximumSize(int i) {
        return new Builder(Ordering.natural()).maximumSize(i);
    }

    public static <B> Builder<B> orderedBy(Comparator<B> comparator) {
        return new Builder<>(comparator);
    }

    private E removeAndGet(int i) {
        E a2 = a(i);
        b(i);
        return a2;
    }

    /* access modifiers changed from: package-private */
    public E a(int i) {
        return this.queue[i];
    }

    @CanIgnoreReturnValue
    public boolean add(E e) {
        offer(e);
        return true;
    }

    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        boolean z = false;
        for (Object offer : collection) {
            offer(offer);
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    @VisibleForTesting
    public MoveDesc<E> b(int i) {
        Preconditions.checkPositionIndex(i, this.size);
        this.modCount++;
        this.size--;
        int i2 = this.size;
        if (i2 == i) {
            this.queue[i2] = null;
            return null;
        }
        Object a2 = a(i2);
        int a3 = heapForIndex(this.size).a(a2);
        Object a4 = a(this.size);
        this.queue[this.size] = null;
        MoveDesc<E> fillHole = fillHole(i, a4);
        return a3 < i ? fillHole == null ? new MoveDesc<>(a2, a4) : new MoveDesc<>(a2, fillHole.b) : fillHole;
    }

    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.queue[i] = null;
        }
        this.size = 0;
    }

    public Comparator<? super E> comparator() {
        return this.minHeap.a;
    }

    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    @CanIgnoreReturnValue
    public boolean offer(E e) {
        Preconditions.checkNotNull(e);
        this.modCount++;
        int i = this.size;
        this.size = i + 1;
        growIfNeeded();
        heapForIndex(i).a(i, e);
        return this.size <= this.a || pollLast() != e;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return a(0);
    }

    public E peekFirst() {
        return peek();
    }

    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return a(getMaxElementIndex());
    }

    @CanIgnoreReturnValue
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(0);
    }

    @CanIgnoreReturnValue
    public E pollFirst() {
        return poll();
    }

    @CanIgnoreReturnValue
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(getMaxElementIndex());
    }

    @CanIgnoreReturnValue
    public E removeFirst() {
        return remove();
    }

    @CanIgnoreReturnValue
    public E removeLast() {
        if (!isEmpty()) {
            return removeAndGet(getMaxElementIndex());
        }
        throw new NoSuchElementException();
    }

    public int size() {
        return this.size;
    }

    public Object[] toArray() {
        int i = this.size;
        Object[] objArr = new Object[i];
        System.arraycopy(this.queue, 0, objArr, 0, i);
        return objArr;
    }
}
