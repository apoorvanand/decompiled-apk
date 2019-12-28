package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class TreeMultiset<E> extends AbstractSortedMultiset<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 1;
    /* access modifiers changed from: private */
    public final transient AvlNode<E> header;
    /* access modifiers changed from: private */
    public final transient GeneralRange<E> range;
    private final transient Reference<AvlNode<E>> rootReference;

    private enum Aggregate {
        SIZE {
            /* access modifiers changed from: package-private */
            public int a(AvlNode<?> avlNode) {
                return avlNode.elemCount;
            }

            /* access modifiers changed from: package-private */
            public long b(@Nullable AvlNode<?> avlNode) {
                if (avlNode == null) {
                    return 0;
                }
                return avlNode.totalCount;
            }
        },
        DISTINCT {
            /* access modifiers changed from: package-private */
            public int a(AvlNode<?> avlNode) {
                return 1;
            }

            /* access modifiers changed from: package-private */
            public long b(@Nullable AvlNode<?> avlNode) {
                if (avlNode == null) {
                    return 0;
                }
                return (long) avlNode.distinctElements;
            }
        };

        /* access modifiers changed from: package-private */
        public abstract int a(AvlNode<?> avlNode);

        /* access modifiers changed from: package-private */
        public abstract long b(@Nullable AvlNode<?> avlNode);
    }

    private static final class AvlNode<E> extends Multisets.AbstractEntry<E> {
        /* access modifiers changed from: private */
        public int distinctElements;
        /* access modifiers changed from: private */
        @Nullable
        public final E elem;
        /* access modifiers changed from: private */
        public int elemCount;
        private int height;
        /* access modifiers changed from: private */
        public AvlNode<E> left;
        /* access modifiers changed from: private */
        public AvlNode<E> pred;
        /* access modifiers changed from: private */
        public AvlNode<E> right;
        /* access modifiers changed from: private */
        public AvlNode<E> succ;
        /* access modifiers changed from: private */
        public long totalCount;

        AvlNode(@Nullable E e, int i) {
            Preconditions.checkArgument(i > 0);
            this.elem = e;
            this.elemCount = i;
            this.totalCount = (long) i;
            this.distinctElements = 1;
            this.height = 1;
            this.left = null;
            this.right = null;
        }

        private AvlNode<E> addLeftChild(E e, int i) {
            this.left = new AvlNode<>(e, i);
            TreeMultiset.successor(this.pred, this.left, this);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += (long) i;
            return this;
        }

        private AvlNode<E> addRightChild(E e, int i) {
            this.right = new AvlNode<>(e, i);
            TreeMultiset.successor(this, this.right, this.succ);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += (long) i;
            return this;
        }

        private int balanceFactor() {
            return height(this.left) - height(this.right);
        }

        /* access modifiers changed from: private */
        @Nullable
        public AvlNode<E> ceiling(Comparator<? super E> comparator, E e) {
            int compare = comparator.compare(e, this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                return avlNode == null ? this : (AvlNode) MoreObjects.firstNonNull(avlNode.ceiling(comparator, e), this);
            } else if (compare == 0) {
                return this;
            } else {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    return null;
                }
                return avlNode2.ceiling(comparator, e);
            }
        }

        private AvlNode<E> deleteMe() {
            int i = this.elemCount;
            this.elemCount = 0;
            TreeMultiset.successor(this.pred, this.succ);
            AvlNode<E> avlNode = this.left;
            if (avlNode == null) {
                return this.right;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return avlNode;
            }
            if (avlNode.height >= avlNode2.height) {
                AvlNode<E> avlNode3 = this.pred;
                avlNode3.left = avlNode.removeMax(avlNode3);
                avlNode3.right = this.right;
                avlNode3.distinctElements = this.distinctElements - 1;
                avlNode3.totalCount = this.totalCount - ((long) i);
                return avlNode3.rebalance();
            }
            AvlNode<E> avlNode4 = this.succ;
            avlNode4.right = avlNode2.removeMin(avlNode4);
            avlNode4.left = this.left;
            avlNode4.distinctElements = this.distinctElements - 1;
            avlNode4.totalCount = this.totalCount - ((long) i);
            return avlNode4.rebalance();
        }

        /* access modifiers changed from: private */
        @Nullable
        public AvlNode<E> floor(Comparator<? super E> comparator, E e) {
            int compare = comparator.compare(e, this.elem);
            if (compare > 0) {
                AvlNode<E> avlNode = this.right;
                return avlNode == null ? this : (AvlNode) MoreObjects.firstNonNull(avlNode.floor(comparator, e), this);
            } else if (compare == 0) {
                return this;
            } else {
                AvlNode<E> avlNode2 = this.left;
                if (avlNode2 == null) {
                    return null;
                }
                return avlNode2.floor(comparator, e);
            }
        }

        private static int height(@Nullable AvlNode<?> avlNode) {
            if (avlNode == null) {
                return 0;
            }
            return avlNode.height;
        }

        private AvlNode<E> rebalance() {
            int balanceFactor = balanceFactor();
            if (balanceFactor == -2) {
                if (this.right.balanceFactor() > 0) {
                    this.right = this.right.rotateRight();
                }
                return rotateLeft();
            } else if (balanceFactor != 2) {
                recomputeHeight();
                return this;
            } else {
                if (this.left.balanceFactor() < 0) {
                    this.left = this.left.rotateLeft();
                }
                return rotateRight();
            }
        }

        private void recompute() {
            recomputeMultiset();
            recomputeHeight();
        }

        private void recomputeHeight() {
            this.height = Math.max(height(this.left), height(this.right)) + 1;
        }

        private void recomputeMultiset() {
            this.distinctElements = TreeMultiset.a((AvlNode<?>) this.left) + 1 + TreeMultiset.a((AvlNode<?>) this.right);
            this.totalCount = ((long) this.elemCount) + totalCount(this.left) + totalCount(this.right);
        }

        private AvlNode<E> removeMax(AvlNode<E> avlNode) {
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return this.left;
            }
            this.right = avlNode2.removeMax(avlNode);
            this.distinctElements--;
            this.totalCount -= (long) avlNode.elemCount;
            return rebalance();
        }

        private AvlNode<E> removeMin(AvlNode<E> avlNode) {
            AvlNode<E> avlNode2 = this.left;
            if (avlNode2 == null) {
                return this.right;
            }
            this.left = avlNode2.removeMin(avlNode);
            this.distinctElements--;
            this.totalCount -= (long) avlNode.elemCount;
            return rebalance();
        }

        private AvlNode<E> rotateLeft() {
            Preconditions.checkState(this.right != null);
            AvlNode<E> avlNode = this.right;
            this.right = avlNode.left;
            avlNode.left = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recompute();
            avlNode.recomputeHeight();
            return avlNode;
        }

        private AvlNode<E> rotateRight() {
            Preconditions.checkState(this.left != null);
            AvlNode<E> avlNode = this.left;
            this.left = avlNode.right;
            avlNode.right = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recompute();
            avlNode.recomputeHeight();
            return avlNode;
        }

        private static long totalCount(@Nullable AvlNode<?> avlNode) {
            if (avlNode == null) {
                return 0;
            }
            return avlNode.totalCount;
        }

        /* access modifiers changed from: package-private */
        public AvlNode<E> a(Comparator<? super E> comparator, @Nullable E e, int i, int i2, int[] iArr) {
            int i3;
            int i4;
            int compare = comparator.compare(e, this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return (i != 0 || i2 <= 0) ? this : addLeftChild(e, i2);
                }
                this.left = avlNode.a(comparator, e, i, i2, iArr);
                if (iArr[0] == i) {
                    if (i2 != 0 || iArr[0] == 0) {
                        if (i2 > 0 && iArr[0] == 0) {
                            i4 = this.distinctElements + 1;
                        }
                        this.totalCount += (long) (i2 - iArr[0]);
                    } else {
                        i4 = this.distinctElements - 1;
                    }
                    this.distinctElements = i4;
                    this.totalCount += (long) (i2 - iArr[0]);
                }
                return rebalance();
            } else if (compare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    return (i != 0 || i2 <= 0) ? this : addRightChild(e, i2);
                }
                this.right = avlNode2.a(comparator, e, i, i2, iArr);
                if (iArr[0] == i) {
                    if (i2 != 0 || iArr[0] == 0) {
                        if (i2 > 0 && iArr[0] == 0) {
                            i3 = this.distinctElements + 1;
                        }
                        this.totalCount += (long) (i2 - iArr[0]);
                    } else {
                        i3 = this.distinctElements - 1;
                    }
                    this.distinctElements = i3;
                    this.totalCount += (long) (i2 - iArr[0]);
                }
                return rebalance();
            } else {
                int i5 = this.elemCount;
                iArr[0] = i5;
                if (i == i5) {
                    if (i2 == 0) {
                        return deleteMe();
                    }
                    this.totalCount += (long) (i2 - i5);
                    this.elemCount = i2;
                }
                return this;
            }
        }

        /* access modifiers changed from: package-private */
        public AvlNode<E> a(Comparator<? super E> comparator, @Nullable E e, int i, int[] iArr) {
            int compare = comparator.compare(e, this.elem);
            boolean z = true;
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return addLeftChild(e, i);
                }
                int i2 = avlNode.height;
                this.left = avlNode.a(comparator, e, i, iArr);
                if (iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += (long) i;
                return this.left.height == i2 ? this : rebalance();
            } else if (compare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    return addRightChild(e, i);
                }
                int i3 = avlNode2.height;
                this.right = avlNode2.a(comparator, e, i, iArr);
                if (iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += (long) i;
                return this.right.height == i3 ? this : rebalance();
            } else {
                int i4 = this.elemCount;
                iArr[0] = i4;
                long j = (long) i;
                if (((long) i4) + j > 2147483647L) {
                    z = false;
                }
                Preconditions.checkArgument(z);
                this.elemCount += i;
                this.totalCount += j;
                return this;
            }
        }

        /* access modifiers changed from: package-private */
        public AvlNode<E> b(Comparator<? super E> comparator, @Nullable E e, int i, int[] iArr) {
            long j;
            long j2;
            int compare = comparator.compare(e, this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.left = avlNode.b(comparator, e, i, iArr);
                if (iArr[0] > 0) {
                    if (i >= iArr[0]) {
                        this.distinctElements--;
                        j2 = this.totalCount;
                        i = iArr[0];
                    } else {
                        j2 = this.totalCount;
                    }
                    this.totalCount = j2 - ((long) i);
                }
                return iArr[0] == 0 ? this : rebalance();
            } else if (compare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.right = avlNode2.b(comparator, e, i, iArr);
                if (iArr[0] > 0) {
                    if (i >= iArr[0]) {
                        this.distinctElements--;
                        j = this.totalCount;
                        i = iArr[0];
                    } else {
                        j = this.totalCount;
                    }
                    this.totalCount = j - ((long) i);
                }
                return rebalance();
            } else {
                int i2 = this.elemCount;
                iArr[0] = i2;
                if (i >= i2) {
                    return deleteMe();
                }
                this.elemCount = i2 - i;
                this.totalCount -= (long) i;
                return this;
            }
        }

        /* access modifiers changed from: package-private */
        public AvlNode<E> c(Comparator<? super E> comparator, @Nullable E e, int i, int[] iArr) {
            long j;
            int i2;
            int i3;
            int i4;
            int compare = comparator.compare(e, this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return i > 0 ? addLeftChild(e, i) : this;
                }
                this.left = avlNode.c(comparator, e, i, iArr);
                if (i != 0 || iArr[0] == 0) {
                    if (i > 0 && iArr[0] == 0) {
                        i4 = this.distinctElements + 1;
                    }
                    j = this.totalCount;
                    i2 = iArr[0];
                } else {
                    i4 = this.distinctElements - 1;
                }
                this.distinctElements = i4;
                j = this.totalCount;
                i2 = iArr[0];
            } else if (compare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    return i > 0 ? addRightChild(e, i) : this;
                }
                this.right = avlNode2.c(comparator, e, i, iArr);
                if (i != 0 || iArr[0] == 0) {
                    if (i > 0 && iArr[0] == 0) {
                        i3 = this.distinctElements + 1;
                    }
                    j = this.totalCount;
                    i2 = iArr[0];
                } else {
                    i3 = this.distinctElements - 1;
                }
                this.distinctElements = i3;
                j = this.totalCount;
                i2 = iArr[0];
            } else {
                int i5 = this.elemCount;
                iArr[0] = i5;
                if (i == 0) {
                    return deleteMe();
                }
                this.totalCount += (long) (i - i5);
                this.elemCount = i;
                return this;
            }
            this.totalCount = j + ((long) (i - i2));
            return rebalance();
        }

        public int count(Comparator<? super E> comparator, E e) {
            int compare = comparator.compare(e, this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    return 0;
                }
                return avlNode.count(comparator, e);
            } else if (compare <= 0) {
                return this.elemCount;
            } else {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    return 0;
                }
                return avlNode2.count(comparator, e);
            }
        }

        public int getCount() {
            return this.elemCount;
        }

        public E getElement() {
            return this.elem;
        }

        public String toString() {
            return Multisets.immutableEntry(getElement(), getCount()).toString();
        }
    }

    private static final class Reference<T> {
        @Nullable
        private T value;

        private Reference() {
        }

        public void checkAndSet(@Nullable T t, T t2) {
            if (this.value == t) {
                this.value = t2;
                return;
            }
            throw new ConcurrentModificationException();
        }

        @Nullable
        public T get() {
            return this.value;
        }
    }

    TreeMultiset(Reference<AvlNode<E>> reference, GeneralRange<E> generalRange, AvlNode<E> avlNode) {
        super(generalRange.a());
        this.rootReference = reference;
        this.range = generalRange;
        this.header = avlNode;
    }

    TreeMultiset(Comparator<? super E> comparator) {
        super(comparator);
        this.range = GeneralRange.a(comparator);
        this.header = new AvlNode<>(null, 1);
        AvlNode<E> avlNode = this.header;
        successor(avlNode, avlNode);
        this.rootReference = new Reference<>();
    }

    static int a(@Nullable AvlNode<?> avlNode) {
        if (avlNode == null) {
            return 0;
        }
        return avlNode.distinctElements;
    }

    private long aggregateAboveRange(Aggregate aggregate, @Nullable AvlNode<E> avlNode) {
        long b;
        long aggregateAboveRange;
        if (avlNode == null) {
            return 0;
        }
        int compare = comparator().compare(this.range.f(), avlNode.elem);
        if (compare > 0) {
            return aggregateAboveRange(aggregate, avlNode.right);
        }
        if (compare == 0) {
            switch (this.range.g()) {
                case OPEN:
                    b = (long) aggregate.a(avlNode);
                    aggregateAboveRange = aggregate.b(avlNode.right);
                    break;
                case CLOSED:
                    return aggregate.b(avlNode.right);
                default:
                    throw new AssertionError();
            }
        } else {
            b = aggregate.b(avlNode.right) + ((long) aggregate.a(avlNode));
            aggregateAboveRange = aggregateAboveRange(aggregate, avlNode.left);
        }
        return b + aggregateAboveRange;
    }

    private long aggregateBelowRange(Aggregate aggregate, @Nullable AvlNode<E> avlNode) {
        long b;
        long aggregateBelowRange;
        if (avlNode == null) {
            return 0;
        }
        int compare = comparator().compare(this.range.d(), avlNode.elem);
        if (compare < 0) {
            return aggregateBelowRange(aggregate, avlNode.left);
        }
        if (compare == 0) {
            switch (this.range.e()) {
                case OPEN:
                    b = (long) aggregate.a(avlNode);
                    aggregateBelowRange = aggregate.b(avlNode.left);
                    break;
                case CLOSED:
                    return aggregate.b(avlNode.left);
                default:
                    throw new AssertionError();
            }
        } else {
            b = aggregate.b(avlNode.left) + ((long) aggregate.a(avlNode));
            aggregateBelowRange = aggregateBelowRange(aggregate, avlNode.right);
        }
        return b + aggregateBelowRange;
    }

    private long aggregateForEntries(Aggregate aggregate) {
        AvlNode avlNode = this.rootReference.get();
        long b = aggregate.b(avlNode);
        if (this.range.b()) {
            b -= aggregateBelowRange(aggregate, avlNode);
        }
        return this.range.c() ? b - aggregateAboveRange(aggregate, avlNode) : b;
    }

    public static <E extends Comparable> TreeMultiset<E> create() {
        return new TreeMultiset<>(Ordering.natural());
    }

    public static <E extends Comparable> TreeMultiset<E> create(Iterable<? extends E> iterable) {
        TreeMultiset<E> create = create();
        Iterables.addAll(create, iterable);
        return create;
    }

    public static <E> TreeMultiset<E> create(@Nullable Comparator<? super E> comparator) {
        return comparator == null ? new TreeMultiset<>(Ordering.natural()) : new TreeMultiset<>(comparator);
    }

    /* access modifiers changed from: private */
    @Nullable
    public AvlNode<E> firstNode() {
        AvlNode<E> avlNode;
        if (this.rootReference.get() == null) {
            return null;
        }
        if (this.range.b()) {
            E d = this.range.d();
            AvlNode<E> a = this.rootReference.get().ceiling(comparator(), d);
            if (a == null) {
                return null;
            }
            if (this.range.e() == BoundType.OPEN && comparator().compare(d, a.getElement()) == 0) {
                a = a.succ;
            }
            avlNode = a;
        } else {
            avlNode = this.header.succ;
        }
        if (avlNode == this.header || !this.range.c(avlNode.getElement())) {
            return null;
        }
        return avlNode;
    }

    /* access modifiers changed from: private */
    @Nullable
    public AvlNode<E> lastNode() {
        AvlNode<E> avlNode;
        if (this.rootReference.get() == null) {
            return null;
        }
        if (this.range.c()) {
            E f = this.range.f();
            AvlNode<E> b = this.rootReference.get().floor(comparator(), f);
            if (b == null) {
                return null;
            }
            if (this.range.g() == BoundType.OPEN && comparator().compare(f, b.getElement()) == 0) {
                b = b.pred;
            }
            avlNode = b;
        } else {
            avlNode = this.header.pred;
        }
        if (avlNode == this.header || !this.range.c(avlNode.getElement())) {
            return null;
        }
        return avlNode;
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        Serialization.a(AbstractSortedMultiset.class, "comparator").a(this, (Object) comparator);
        Serialization.a(TreeMultiset.class, "range").a(this, (Object) GeneralRange.a(comparator));
        Serialization.a(TreeMultiset.class, "rootReference").a(this, (Object) new Reference());
        AvlNode avlNode = new AvlNode(null, 1);
        Serialization.a(TreeMultiset.class, "header").a(this, (Object) avlNode);
        successor(avlNode, avlNode);
        Serialization.a(this, objectInputStream);
    }

    /* access modifiers changed from: private */
    public static <T> void successor(AvlNode<T> avlNode, AvlNode<T> avlNode2) {
        AvlNode unused = avlNode.succ = avlNode2;
        AvlNode unused2 = avlNode2.pred = avlNode;
    }

    /* access modifiers changed from: private */
    public static <T> void successor(AvlNode<T> avlNode, AvlNode<T> avlNode2, AvlNode<T> avlNode3) {
        successor(avlNode, avlNode2);
        successor(avlNode2, avlNode3);
    }

    /* access modifiers changed from: private */
    public Multiset.Entry<E> wrapEntry(final AvlNode<E> avlNode) {
        return new Multisets.AbstractEntry<E>() {
            public int getCount() {
                int count = avlNode.getCount();
                return count == 0 ? TreeMultiset.this.count(getElement()) : count;
            }

            public E getElement() {
                return avlNode.getElement();
            }
        };
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(elementSet().comparator());
        Serialization.a(this, objectOutputStream);
    }

    /* access modifiers changed from: package-private */
    public Iterator<Multiset.Entry<E>> a() {
        return new Iterator<Multiset.Entry<E>>() {
            AvlNode<E> a = TreeMultiset.this.firstNode();
            Multiset.Entry<E> b;

            public boolean hasNext() {
                if (this.a == null) {
                    return false;
                }
                if (!TreeMultiset.this.range.b(this.a.getElement())) {
                    return true;
                }
                this.a = null;
                return false;
            }

            public Multiset.Entry<E> next() {
                if (hasNext()) {
                    Multiset.Entry<E> a2 = TreeMultiset.this.wrapEntry(this.a);
                    this.b = a2;
                    this.a = this.a.succ == TreeMultiset.this.header ? null : this.a.succ;
                    return a2;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                CollectPreconditions.a(this.b != null);
                TreeMultiset.this.setCount(this.b.getElement(), 0);
                this.b = null;
            }
        };
    }

    @CanIgnoreReturnValue
    public int add(@Nullable E e, int i) {
        CollectPreconditions.a(i, "occurrences");
        if (i == 0) {
            return count(e);
        }
        Preconditions.checkArgument(this.range.c(e));
        AvlNode avlNode = this.rootReference.get();
        if (avlNode == null) {
            comparator().compare(e, e);
            AvlNode avlNode2 = new AvlNode(e, i);
            AvlNode<E> avlNode3 = this.header;
            successor(avlNode3, avlNode2, avlNode3);
            this.rootReference.checkAndSet(avlNode, avlNode2);
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.checkAndSet(avlNode, avlNode.a(comparator(), e, i, iArr));
        return iArr[0];
    }

    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }

    public /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
        return super.addAll(collection);
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.DISTINCT));
    }

    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public /* bridge */ /* synthetic */ Comparator comparator() {
        return super.comparator();
    }

    public /* bridge */ /* synthetic */ boolean contains(Object obj) {
        return super.contains(obj);
    }

    public int count(@Nullable Object obj) {
        try {
            AvlNode avlNode = this.rootReference.get();
            if (this.range.c(obj)) {
                if (avlNode != null) {
                    return avlNode.count(comparator(), obj);
                }
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    public /* bridge */ /* synthetic */ SortedMultiset descendingMultiset() {
        return super.descendingMultiset();
    }

    /* access modifiers changed from: package-private */
    public Iterator<Multiset.Entry<E>> e() {
        return new Iterator<Multiset.Entry<E>>() {
            AvlNode<E> a = TreeMultiset.this.lastNode();
            Multiset.Entry<E> b = null;

            public boolean hasNext() {
                if (this.a == null) {
                    return false;
                }
                if (!TreeMultiset.this.range.a(this.a.getElement())) {
                    return true;
                }
                this.a = null;
                return false;
            }

            public Multiset.Entry<E> next() {
                if (hasNext()) {
                    Multiset.Entry<E> a2 = TreeMultiset.this.wrapEntry(this.a);
                    this.b = a2;
                    this.a = this.a.pred == TreeMultiset.this.header ? null : this.a.pred;
                    return a2;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                CollectPreconditions.a(this.b != null);
                TreeMultiset.this.setCount(this.b.getElement(), 0);
                this.b = null;
            }
        };
    }

    public /* bridge */ /* synthetic */ NavigableSet elementSet() {
        return super.elementSet();
    }

    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ Multiset.Entry firstEntry() {
        return super.firstEntry();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public SortedMultiset<E> headMultiset(@Nullable E e, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.a(GeneralRange.b(comparator(), e, boundType)), this.header);
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    public /* bridge */ /* synthetic */ Multiset.Entry lastEntry() {
        return super.lastEntry();
    }

    public /* bridge */ /* synthetic */ Multiset.Entry pollFirstEntry() {
        return super.pollFirstEntry();
    }

    public /* bridge */ /* synthetic */ Multiset.Entry pollLastEntry() {
        return super.pollLastEntry();
    }

    @CanIgnoreReturnValue
    public int remove(@Nullable Object obj, int i) {
        CollectPreconditions.a(i, "occurrences");
        if (i == 0) {
            return count(obj);
        }
        AvlNode avlNode = this.rootReference.get();
        int[] iArr = new int[1];
        try {
            if (this.range.c(obj)) {
                if (avlNode != null) {
                    this.rootReference.checkAndSet(avlNode, avlNode.b(comparator(), obj, i, iArr));
                    return iArr[0];
                }
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    public /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @CanIgnoreReturnValue
    public int setCount(@Nullable E e, int i) {
        CollectPreconditions.a(i, "count");
        boolean z = true;
        if (!this.range.c(e)) {
            if (i != 0) {
                z = false;
            }
            Preconditions.checkArgument(z);
            return 0;
        }
        AvlNode avlNode = this.rootReference.get();
        if (avlNode == null) {
            if (i > 0) {
                add(e, i);
            }
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.checkAndSet(avlNode, avlNode.c(comparator(), e, i, iArr));
        return iArr[0];
    }

    @CanIgnoreReturnValue
    public boolean setCount(@Nullable E e, int i, int i2) {
        CollectPreconditions.a(i2, "newCount");
        CollectPreconditions.a(i, "oldCount");
        Preconditions.checkArgument(this.range.c(e));
        AvlNode avlNode = this.rootReference.get();
        if (avlNode != null) {
            int[] iArr = new int[1];
            this.rootReference.checkAndSet(avlNode, avlNode.a(comparator(), e, i, i2, iArr));
            return iArr[0] == i;
        } else if (i != 0) {
            return false;
        } else {
            if (i2 > 0) {
                add(e, i2);
            }
            return true;
        }
    }

    public int size() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.SIZE));
    }

    public /* bridge */ /* synthetic */ SortedMultiset subMultiset(Object obj, BoundType boundType, Object obj2, BoundType boundType2) {
        return super.subMultiset(obj, boundType, obj2, boundType2);
    }

    public SortedMultiset<E> tailMultiset(@Nullable E e, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.a(GeneralRange.a(comparator(), e, boundType)), this.header);
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
