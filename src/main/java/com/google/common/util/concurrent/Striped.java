package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.MapMaker;
import com.google.common.math.IntMath;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@GwtIncompatible
@Beta
public abstract class Striped<L> {
    private static final int ALL_SET = -1;
    private static final int LARGE_LAZY_CUTOFF = 1024;
    private static final Supplier<ReadWriteLock> READ_WRITE_LOCK_SUPPLIER = new Supplier<ReadWriteLock>() {
        public ReadWriteLock get() {
            return new ReentrantReadWriteLock();
        }
    };

    private static class CompactStriped<L> extends PowerOfTwoStriped<L> {
        private final Object[] array;

        private CompactStriped(int i, Supplier<L> supplier) {
            super(i);
            int i2 = 0;
            Preconditions.checkArgument(i <= 1073741824, "Stripes must be <= 2^30)");
            this.array = new Object[(this.d + 1)];
            while (true) {
                Object[] objArr = this.array;
                if (i2 < objArr.length) {
                    objArr[i2] = supplier.get();
                    i2++;
                } else {
                    return;
                }
            }
        }

        public L getAt(int i) {
            return this.array[i];
        }

        public int size() {
            return this.array.length;
        }
    }

    @VisibleForTesting
    static class LargeLazyStriped<L> extends PowerOfTwoStriped<L> {
        final ConcurrentMap<Integer, L> a;
        final Supplier<L> b;
        final int c;

        LargeLazyStriped(int i, Supplier<L> supplier) {
            super(i);
            this.c = this.d == -1 ? Integer.MAX_VALUE : this.d + 1;
            this.b = supplier;
            this.a = new MapMaker().weakValues().makeMap();
        }

        public L getAt(int i) {
            if (this.c != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i, size());
            }
            L l = this.a.get(Integer.valueOf(i));
            if (l != null) {
                return l;
            }
            L l2 = this.b.get();
            return MoreObjects.firstNonNull(this.a.putIfAbsent(Integer.valueOf(i), l2), l2);
        }

        public int size() {
            return this.c;
        }
    }

    private static class PaddedLock extends ReentrantLock {
        PaddedLock() {
            super(false);
        }
    }

    private static class PaddedSemaphore extends Semaphore {
        PaddedSemaphore(int i) {
            super(i, false);
        }
    }

    private static abstract class PowerOfTwoStriped<L> extends Striped<L> {
        final int d;

        PowerOfTwoStriped(int i) {
            super();
            Preconditions.checkArgument(i > 0, "Stripes must be positive");
            this.d = i > 1073741824 ? -1 : Striped.ceilToPowerOfTwo(i) - 1;
        }

        /* access modifiers changed from: package-private */
        public final int a(Object obj) {
            return Striped.smear(obj.hashCode()) & this.d;
        }

        public final L get(Object obj) {
            return getAt(a(obj));
        }
    }

    @VisibleForTesting
    static class SmallLazyStriped<L> extends PowerOfTwoStriped<L> {
        final AtomicReferenceArray<ArrayReference<? extends L>> a;
        final Supplier<L> b;
        final int c;
        final ReferenceQueue<L> e = new ReferenceQueue<>();

        private static final class ArrayReference<L> extends WeakReference<L> {
            final int a;

            ArrayReference(L l, int i, ReferenceQueue<L> referenceQueue) {
                super(l, referenceQueue);
                this.a = i;
            }
        }

        SmallLazyStriped(int i, Supplier<L> supplier) {
            super(i);
            this.c = this.d == -1 ? Integer.MAX_VALUE : this.d + 1;
            this.a = new AtomicReferenceArray<>(this.c);
            this.b = supplier;
        }

        private void drainQueue() {
            while (true) {
                Reference<? extends L> poll = this.e.poll();
                if (poll != null) {
                    ArrayReference arrayReference = (ArrayReference) poll;
                    this.a.compareAndSet(arrayReference.a, arrayReference, (Object) null);
                } else {
                    return;
                }
            }
        }

        public L getAt(int i) {
            L l;
            if (this.c != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i, size());
            }
            ArrayReference arrayReference = this.a.get(i);
            L l2 = arrayReference == null ? null : arrayReference.get();
            if (l2 != null) {
                return l2;
            }
            L l3 = this.b.get();
            ArrayReference arrayReference2 = new ArrayReference(l3, i, this.e);
            while (!this.a.compareAndSet(i, arrayReference, arrayReference2)) {
                arrayReference = this.a.get(i);
                if (arrayReference == null) {
                    l = null;
                    continue;
                } else {
                    l = arrayReference.get();
                    continue;
                }
                if (l != null) {
                    return l;
                }
            }
            drainQueue();
            return l3;
        }

        public int size() {
            return this.c;
        }
    }

    private Striped() {
    }

    /* access modifiers changed from: private */
    public static int ceilToPowerOfTwo(int i) {
        return 1 << IntMath.log2(i, RoundingMode.CEILING);
    }

    private static <L> Striped<L> lazy(int i, Supplier<L> supplier) {
        return i < 1024 ? new SmallLazyStriped(i, supplier) : new LargeLazyStriped(i, supplier);
    }

    public static Striped<Lock> lazyWeakLock(int i) {
        return lazy(i, new Supplier<Lock>() {
            public Lock get() {
                return new ReentrantLock(false);
            }
        });
    }

    public static Striped<ReadWriteLock> lazyWeakReadWriteLock(int i) {
        return lazy(i, READ_WRITE_LOCK_SUPPLIER);
    }

    public static Striped<Semaphore> lazyWeakSemaphore(int i, final int i2) {
        return lazy(i, new Supplier<Semaphore>() {
            public Semaphore get() {
                return new Semaphore(i2, false);
            }
        });
    }

    public static Striped<Lock> lock(int i) {
        return new CompactStriped(i, new Supplier<Lock>() {
            public Lock get() {
                return new PaddedLock();
            }
        });
    }

    public static Striped<ReadWriteLock> readWriteLock(int i) {
        return new CompactStriped(i, READ_WRITE_LOCK_SUPPLIER);
    }

    public static Striped<Semaphore> semaphore(int i, final int i2) {
        return new CompactStriped(i, new Supplier<Semaphore>() {
            public Semaphore get() {
                return new PaddedSemaphore(i2);
            }
        });
    }

    /* access modifiers changed from: private */
    public static int smear(int i) {
        int i2 = i ^ ((i >>> 20) ^ (i >>> 12));
        return (i2 >>> 4) ^ ((i2 >>> 7) ^ i2);
    }

    /* access modifiers changed from: package-private */
    public abstract int a(Object obj);

    public Iterable<L> bulkGet(Iterable<?> iterable) {
        Object[] array = Iterables.toArray(iterable, Object.class);
        if (array.length == 0) {
            return ImmutableList.of();
        }
        int[] iArr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            iArr[i] = a(array[i]);
        }
        Arrays.sort(iArr);
        int i2 = iArr[0];
        array[0] = getAt(i2);
        for (int i3 = 1; i3 < array.length; i3++) {
            int i4 = iArr[i3];
            if (i4 == i2) {
                array[i3] = array[i3 - 1];
            } else {
                array[i3] = getAt(i4);
                i2 = i4;
            }
        }
        return Collections.unmodifiableList(Arrays.asList(array));
    }

    public abstract L get(Object obj);

    public abstract L getAt(int i);

    public abstract int size();
}
