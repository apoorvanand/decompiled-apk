package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.AbstractSequentialIterator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.ExecutionError;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.j2objc.annotations.Weak;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import kotlin.jvm.internal.LongCompanionObject;

@GwtCompatible(emulated = true)
class LocalCache<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
    static final Logger a = Logger.getLogger(LocalCache.class.getName());
    static final ValueReference<Object, Object> u = new ValueReference<Object, Object>() {
        public ValueReference<Object, Object> copyFor(ReferenceQueue<Object> referenceQueue, @Nullable Object obj, ReferenceEntry<Object, Object> referenceEntry) {
            return this;
        }

        public Object get() {
            return null;
        }

        public ReferenceEntry<Object, Object> getEntry() {
            return null;
        }

        public int getWeight() {
            return 0;
        }

        public boolean isActive() {
            return false;
        }

        public boolean isLoading() {
            return false;
        }

        public void notifyNewValue(Object obj) {
        }

        public Object waitForValue() {
            return null;
        }
    };
    static final Queue<? extends Object> v = new AbstractQueue<Object>() {
        public Iterator<Object> iterator() {
            return ImmutableSet.of().iterator();
        }

        public boolean offer(Object obj) {
            return true;
        }

        public Object peek() {
            return null;
        }

        public Object poll() {
            return null;
        }

        public int size() {
            return 0;
        }
    };
    final int b;
    final int c;
    final Segment<K, V>[] d;
    final int e;
    final Equivalence<Object> f;
    final Equivalence<Object> g;
    final Strength h;
    final Strength i;
    final long j;
    final Weigher<K, V> k;
    final long l;
    final long m;
    final long n;
    final Queue<RemovalNotification<K, V>> o;
    final RemovalListener<K, V> p;
    final Ticker q;
    final EntryFactory r;
    final AbstractCache.StatsCounter s;
    @Nullable
    final CacheLoader<? super K, V> t;
    Set<K> w;
    Collection<V> x;
    Set<Map.Entry<K, V>> y;

    abstract class AbstractCacheSet<T> extends AbstractSet<T> {
        @Weak
        final ConcurrentMap<?, ?> a;

        AbstractCacheSet(ConcurrentMap<?, ?> concurrentMap) {
            this.a = concurrentMap;
        }

        public void clear() {
            this.a.clear();
        }

        public boolean isEmpty() {
            return this.a.isEmpty();
        }

        public int size() {
            return this.a.size();
        }

        public Object[] toArray() {
            return LocalCache.toArrayList(this).toArray();
        }

        public <E> E[] toArray(E[] eArr) {
            return LocalCache.toArrayList(this).toArray(eArr);
        }
    }

    static abstract class AbstractReferenceEntry<K, V> implements ReferenceEntry<K, V> {
        AbstractReferenceEntry() {
        }

        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        public int getHash() {
            throw new UnsupportedOperationException();
        }

        public K getKey() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getNext() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        public ValueReference<K, V> getValueReference() {
            throw new UnsupportedOperationException();
        }

        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        public void setAccessTime(long j) {
            throw new UnsupportedOperationException();
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setValueReference(ValueReference<K, V> valueReference) {
            throw new UnsupportedOperationException();
        }

        public void setWriteTime(long j) {
            throw new UnsupportedOperationException();
        }
    }

    static final class AccessQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> a = new AbstractReferenceEntry<K, V>() {
            ReferenceEntry<K, V> a = this;
            ReferenceEntry<K, V> b = this;

            public long getAccessTime() {
                return LongCompanionObject.MAX_VALUE;
            }

            public ReferenceEntry<K, V> getNextInAccessQueue() {
                return this.a;
            }

            public ReferenceEntry<K, V> getPreviousInAccessQueue() {
                return this.b;
            }

            public void setAccessTime(long j) {
            }

            public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
                this.a = referenceEntry;
            }

            public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
                this.b = referenceEntry;
            }
        };

        AccessQueue() {
        }

        public void clear() {
            ReferenceEntry<K, V> nextInAccessQueue = this.a.getNextInAccessQueue();
            while (true) {
                ReferenceEntry<K, V> referenceEntry = this.a;
                if (nextInAccessQueue != referenceEntry) {
                    ReferenceEntry<K, V> nextInAccessQueue2 = nextInAccessQueue.getNextInAccessQueue();
                    LocalCache.b(nextInAccessQueue);
                    nextInAccessQueue = nextInAccessQueue2;
                } else {
                    referenceEntry.setNextInAccessQueue(referenceEntry);
                    ReferenceEntry<K, V> referenceEntry2 = this.a;
                    referenceEntry2.setPreviousInAccessQueue(referenceEntry2);
                    return;
                }
            }
        }

        public boolean contains(Object obj) {
            return ((ReferenceEntry) obj).getNextInAccessQueue() != NullEntry.INSTANCE;
        }

        public boolean isEmpty() {
            return this.a.getNextInAccessQueue() == this.a;
        }

        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) {
                /* access modifiers changed from: protected */
                public ReferenceEntry<K, V> a(ReferenceEntry<K, V> referenceEntry) {
                    ReferenceEntry<K, V> nextInAccessQueue = referenceEntry.getNextInAccessQueue();
                    if (nextInAccessQueue == AccessQueue.this.a) {
                        return null;
                    }
                    return nextInAccessQueue;
                }
            };
        }

        public boolean offer(ReferenceEntry<K, V> referenceEntry) {
            LocalCache.a(referenceEntry.getPreviousInAccessQueue(), referenceEntry.getNextInAccessQueue());
            LocalCache.a(this.a.getPreviousInAccessQueue(), referenceEntry);
            LocalCache.a(referenceEntry, this.a);
            return true;
        }

        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> nextInAccessQueue = this.a.getNextInAccessQueue();
            if (nextInAccessQueue == this.a) {
                return null;
            }
            return nextInAccessQueue;
        }

        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> nextInAccessQueue = this.a.getNextInAccessQueue();
            if (nextInAccessQueue == this.a) {
                return null;
            }
            remove(nextInAccessQueue);
            return nextInAccessQueue;
        }

        public boolean remove(Object obj) {
            ReferenceEntry referenceEntry = (ReferenceEntry) obj;
            ReferenceEntry previousInAccessQueue = referenceEntry.getPreviousInAccessQueue();
            ReferenceEntry nextInAccessQueue = referenceEntry.getNextInAccessQueue();
            LocalCache.a(previousInAccessQueue, nextInAccessQueue);
            LocalCache.b(referenceEntry);
            return nextInAccessQueue != NullEntry.INSTANCE;
        }

        public int size() {
            int i = 0;
            for (ReferenceEntry<K, V> nextInAccessQueue = this.a.getNextInAccessQueue(); nextInAccessQueue != this.a; nextInAccessQueue = nextInAccessQueue.getNextInAccessQueue()) {
                i++;
            }
            return i;
        }
    }

    enum EntryFactory {
        STRONG {
            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
                return new StrongEntry(k, i, referenceEntry);
            }
        },
        STRONG_ACCESS {
            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> a = super.a(segment, referenceEntry, referenceEntry2);
                a(referenceEntry, a);
                return a;
            }

            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
                return new StrongAccessEntry(k, i, referenceEntry);
            }
        },
        STRONG_WRITE {
            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> a = super.a(segment, referenceEntry, referenceEntry2);
                b(referenceEntry, a);
                return a;
            }

            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
                return new StrongWriteEntry(k, i, referenceEntry);
            }
        },
        STRONG_ACCESS_WRITE {
            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> a = super.a(segment, referenceEntry, referenceEntry2);
                a(referenceEntry, a);
                b(referenceEntry, a);
                return a;
            }

            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
                return new StrongAccessWriteEntry(k, i, referenceEntry);
            }
        },
        WEAK {
            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
                return new WeakEntry(segment.h, k, i, referenceEntry);
            }
        },
        WEAK_ACCESS {
            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> a = super.a(segment, referenceEntry, referenceEntry2);
                a(referenceEntry, a);
                return a;
            }

            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
                return new WeakAccessEntry(segment.h, k, i, referenceEntry);
            }
        },
        WEAK_WRITE {
            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> a = super.a(segment, referenceEntry, referenceEntry2);
                b(referenceEntry, a);
                return a;
            }

            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
                return new WeakWriteEntry(segment.h, k, i, referenceEntry);
            }
        },
        WEAK_ACCESS_WRITE {
            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> a = super.a(segment, referenceEntry, referenceEntry2);
                a(referenceEntry, a);
                b(referenceEntry, a);
                return a;
            }

            /* access modifiers changed from: package-private */
            public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
                return new WeakAccessWriteEntry(segment.h, k, i, referenceEntry);
            }
        };
        
        static final EntryFactory[] a = null;

        static {
            EntryFactory entryFactory;
            EntryFactory entryFactory2;
            EntryFactory entryFactory3;
            EntryFactory entryFactory4;
            EntryFactory entryFactory5;
            EntryFactory entryFactory6;
            EntryFactory entryFactory7;
            EntryFactory entryFactory8;
            a = new EntryFactory[]{entryFactory, entryFactory2, entryFactory3, entryFactory4, entryFactory5, entryFactory6, entryFactory7, entryFactory8};
        }

        static EntryFactory a(Strength strength, boolean z, boolean z2) {
            char c = 0;
            boolean z3 = (strength == Strength.WEAK ? (char) 4 : 0) | z;
            if (z2) {
                c = 2;
            }
            return a[z3 | c];
        }

        /* access modifiers changed from: package-private */
        public <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            return a(segment, referenceEntry.getKey(), referenceEntry.getHash(), referenceEntry2);
        }

        /* access modifiers changed from: package-private */
        public abstract <K, V> ReferenceEntry<K, V> a(Segment<K, V> segment, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry);

        /* access modifiers changed from: package-private */
        public <K, V> void a(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            referenceEntry2.setAccessTime(referenceEntry.getAccessTime());
            LocalCache.a(referenceEntry.getPreviousInAccessQueue(), referenceEntry2);
            LocalCache.a(referenceEntry2, referenceEntry.getNextInAccessQueue());
            LocalCache.b(referenceEntry);
        }

        /* access modifiers changed from: package-private */
        public <K, V> void b(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            referenceEntry2.setWriteTime(referenceEntry.getWriteTime());
            LocalCache.b(referenceEntry.getPreviousInWriteQueue(), referenceEntry2);
            LocalCache.b(referenceEntry2, referenceEntry.getNextInWriteQueue());
            LocalCache.c(referenceEntry);
        }
    }

    final class EntryIterator extends LocalCache<K, V>.HashIterator<Map.Entry<K, V>> {
        EntryIterator() {
            super();
        }

        public Map.Entry<K, V> next() {
            return d();
        }
    }

    final class EntrySet extends LocalCache<K, V>.AbstractCacheSet<Map.Entry<K, V>> {
        EntrySet(ConcurrentMap<?, ?> concurrentMap) {
            super(concurrentMap);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
            r0 = r3.c.get((r0 = r4.getKey()));
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean contains(java.lang.Object r4) {
            /*
                r3 = this;
                boolean r0 = r4 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                java.lang.Object r0 = r4.getKey()
                if (r0 != 0) goto L_0x000f
                return r1
            L_0x000f:
                com.google.common.cache.LocalCache r2 = com.google.common.cache.LocalCache.this
                java.lang.Object r0 = r2.get(r0)
                if (r0 == 0) goto L_0x0026
                com.google.common.cache.LocalCache r2 = com.google.common.cache.LocalCache.this
                com.google.common.base.Equivalence<java.lang.Object> r2 = r2.g
                java.lang.Object r4 = r4.getValue()
                boolean r4 = r2.equivalent(r4, r0)
                if (r4 == 0) goto L_0x0026
                r1 = 1
            L_0x0026:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.EntrySet.contains(java.lang.Object):boolean");
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
            r4 = (java.util.Map.Entry) r4;
            r0 = r4.getKey();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean remove(java.lang.Object r4) {
            /*
                r3 = this;
                boolean r0 = r4 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                java.lang.Object r0 = r4.getKey()
                if (r0 == 0) goto L_0x001b
                com.google.common.cache.LocalCache r2 = com.google.common.cache.LocalCache.this
                java.lang.Object r4 = r4.getValue()
                boolean r4 = r2.remove(r0, r4)
                if (r4 == 0) goto L_0x001b
                r1 = 1
            L_0x001b:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.EntrySet.remove(java.lang.Object):boolean");
        }
    }

    abstract class HashIterator<T> implements Iterator<T> {
        int b;
        int c = -1;
        Segment<K, V> d;
        AtomicReferenceArray<ReferenceEntry<K, V>> e;
        ReferenceEntry<K, V> f;
        LocalCache<K, V>.WriteThroughEntry g;
        LocalCache<K, V>.WriteThroughEntry h;

        HashIterator() {
            this.b = LocalCache.this.d.length - 1;
            a();
        }

        /* access modifiers changed from: package-private */
        public final void a() {
            this.g = null;
            if (!b() && !c()) {
                while (this.b >= 0) {
                    Segment<K, V>[] segmentArr = LocalCache.this.d;
                    int i2 = this.b;
                    this.b = i2 - 1;
                    this.d = segmentArr[i2];
                    if (this.d.b != 0) {
                        this.e = this.d.f;
                        this.c = this.e.length() - 1;
                        if (c()) {
                            return;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(ReferenceEntry<K, V> referenceEntry) {
            boolean z;
            try {
                long read = LocalCache.this.q.read();
                K key = referenceEntry.getKey();
                V a = LocalCache.this.a(referenceEntry, read);
                if (a != null) {
                    this.g = new WriteThroughEntry(key, a);
                    z = true;
                } else {
                    z = false;
                }
                return z;
            } finally {
                this.d.l();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            ReferenceEntry<K, V> referenceEntry = this.f;
            if (referenceEntry == null) {
                return false;
            }
            while (true) {
                this.f = referenceEntry.getNext();
                ReferenceEntry<K, V> referenceEntry2 = this.f;
                if (referenceEntry2 == null) {
                    return false;
                }
                if (a(referenceEntry2)) {
                    return true;
                }
                referenceEntry = this.f;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean c() {
            while (true) {
                int i2 = this.c;
                if (i2 < 0) {
                    return false;
                }
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.e;
                this.c = i2 - 1;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(i2);
                this.f = referenceEntry;
                if (referenceEntry != null && (a(this.f) || b())) {
                    return true;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public LocalCache<K, V>.WriteThroughEntry d() {
            LocalCache<K, V>.WriteThroughEntry writeThroughEntry = this.g;
            if (writeThroughEntry != null) {
                this.h = writeThroughEntry;
                a();
                return this.h;
            }
            throw new NoSuchElementException();
        }

        public boolean hasNext() {
            return this.g != null;
        }

        public abstract T next();

        public void remove() {
            Preconditions.checkState(this.h != null);
            LocalCache.this.remove(this.h.getKey());
            this.h = null;
        }
    }

    final class KeyIterator extends LocalCache<K, V>.HashIterator<K> {
        KeyIterator() {
            super();
        }

        public K next() {
            return d().getKey();
        }
    }

    final class KeySet extends LocalCache<K, V>.AbstractCacheSet<K> {
        KeySet(ConcurrentMap<?, ?> concurrentMap) {
            super(concurrentMap);
        }

        public boolean contains(Object obj) {
            return this.a.containsKey(obj);
        }

        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        public boolean remove(Object obj) {
            return this.a.remove(obj) != null;
        }
    }

    static final class LoadingSerializationProxy<K, V> extends ManualSerializationProxy<K, V> implements LoadingCache<K, V>, Serializable {
        private static final long serialVersionUID = 1;
        transient LoadingCache<K, V> a;

        LoadingSerializationProxy(LocalCache<K, V> localCache) {
            super(localCache);
        }

        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            this.a = b().build(this.m);
        }

        private Object readResolve() {
            return this.a;
        }

        public final V apply(K k) {
            return this.a.apply(k);
        }

        public V get(K k) {
            return this.a.get(k);
        }

        public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) {
            return this.a.getAll(iterable);
        }

        public V getUnchecked(K k) {
            return this.a.getUnchecked(k);
        }

        public void refresh(K k) {
            this.a.refresh(k);
        }
    }

    static class LoadingValueReference<K, V> implements ValueReference<K, V> {
        volatile ValueReference<K, V> a;
        final SettableFuture<V> b;
        final Stopwatch c;

        public LoadingValueReference() {
            this(LocalCache.o());
        }

        public LoadingValueReference(ValueReference<K, V> valueReference) {
            this.b = SettableFuture.create();
            this.c = Stopwatch.createUnstarted();
            this.a = valueReference;
        }

        private ListenableFuture<V> fullyFailedFuture(Throwable th) {
            return Futures.immediateFailedFuture(th);
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, @Nullable V v, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public long elapsedNanos() {
            return this.c.elapsed(TimeUnit.NANOSECONDS);
        }

        public V get() {
            return this.a.get();
        }

        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public ValueReference<K, V> getOldValue() {
            return this.a;
        }

        public int getWeight() {
            return this.a.getWeight();
        }

        public boolean isActive() {
            return this.a.isActive();
        }

        public boolean isLoading() {
            return true;
        }

        public ListenableFuture<V> loadFuture(K k, CacheLoader<? super K, V> cacheLoader) {
            try {
                this.c.start();
                V v = this.a.get();
                if (v == null) {
                    V load = cacheLoader.load(k);
                    return set(load) ? this.b : Futures.immediateFuture(load);
                }
                ListenableFuture<V> reload = cacheLoader.reload(k, v);
                return reload == null ? Futures.immediateFuture(null) : Futures.transform(reload, new Function<V, V>() {
                    public V apply(V v) {
                        LoadingValueReference.this.set(v);
                        return v;
                    }
                });
            } catch (Throwable th) {
                ListenableFuture<V> fullyFailedFuture = setException(th) ? this.b : fullyFailedFuture(th);
                if (th instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                return fullyFailedFuture;
            }
        }

        public void notifyNewValue(@Nullable V v) {
            if (v != null) {
                set(v);
            } else {
                this.a = LocalCache.o();
            }
        }

        public boolean set(@Nullable V v) {
            return this.b.set(v);
        }

        public boolean setException(Throwable th) {
            return this.b.setException(th);
        }

        public V waitForValue() {
            return Uninterruptibles.getUninterruptibly(this.b);
        }
    }

    static class LocalLoadingCache<K, V> extends LocalManualCache<K, V> implements LoadingCache<K, V> {
        private static final long serialVersionUID = 1;

        LocalLoadingCache(CacheBuilder<? super K, ? super V> cacheBuilder, CacheLoader<? super K, V> cacheLoader) {
            super();
        }

        public final V apply(K k) {
            return getUnchecked(k);
        }

        public V get(K k) {
            return this.a.b(k);
        }

        public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) {
            return this.a.b(iterable);
        }

        public V getUnchecked(K k) {
            try {
                return get(k);
            } catch (ExecutionException e) {
                throw new UncheckedExecutionException(e.getCause());
            }
        }

        public void refresh(K k) {
            this.a.c(k);
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new LoadingSerializationProxy(this.a);
        }
    }

    static class LocalManualCache<K, V> implements Cache<K, V>, Serializable {
        private static final long serialVersionUID = 1;
        final LocalCache<K, V> a;

        LocalManualCache(CacheBuilder<? super K, ? super V> cacheBuilder) {
            this(new LocalCache(cacheBuilder, (CacheLoader) null));
        }

        private LocalManualCache(LocalCache<K, V> localCache) {
            this.a = localCache;
        }

        public ConcurrentMap<K, V> asMap() {
            return this.a;
        }

        public void cleanUp() {
            this.a.cleanUp();
        }

        public V get(K k, final Callable<? extends V> callable) {
            Preconditions.checkNotNull(callable);
            return this.a.a(k, new CacheLoader<Object, V>() {
                public V load(Object obj) {
                    return callable.call();
                }
            });
        }

        public ImmutableMap<K, V> getAllPresent(Iterable<?> iterable) {
            return this.a.a(iterable);
        }

        @Nullable
        public V getIfPresent(Object obj) {
            return this.a.getIfPresent(obj);
        }

        public void invalidate(Object obj) {
            Preconditions.checkNotNull(obj);
            this.a.remove(obj);
        }

        public void invalidateAll() {
            this.a.clear();
        }

        public void invalidateAll(Iterable<?> iterable) {
            this.a.c(iterable);
        }

        public void put(K k, V v) {
            this.a.put(k, v);
        }

        public void putAll(Map<? extends K, ? extends V> map) {
            this.a.putAll(map);
        }

        public long size() {
            return this.a.s();
        }

        public CacheStats stats() {
            AbstractCache.SimpleStatsCounter simpleStatsCounter = new AbstractCache.SimpleStatsCounter();
            simpleStatsCounter.incrementBy(this.a.s);
            for (Segment<K, V> segment : this.a.d) {
                simpleStatsCounter.incrementBy(segment.n);
            }
            return simpleStatsCounter.snapshot();
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new ManualSerializationProxy(this.a);
        }
    }

    static class ManualSerializationProxy<K, V> extends ForwardingCache<K, V> implements Serializable {
        private static final long serialVersionUID = 1;
        final Strength b;
        final Strength c;
        final Equivalence<Object> d;
        final Equivalence<Object> e;
        final long f;
        final long g;
        final long h;
        final Weigher<K, V> i;
        final int j;
        final RemovalListener<? super K, ? super V> k;
        final Ticker l;
        final CacheLoader<? super K, V> m;
        transient Cache<K, V> n;

        private ManualSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, long j2, long j3, long j4, Weigher<K, V> weigher, int i2, RemovalListener<? super K, ? super V> removalListener, Ticker ticker, CacheLoader<? super K, V> cacheLoader) {
            this.b = strength;
            this.c = strength2;
            this.d = equivalence;
            this.e = equivalence2;
            this.f = j2;
            this.g = j3;
            this.h = j4;
            this.i = weigher;
            this.j = i2;
            this.k = removalListener;
            this.l = (ticker == Ticker.systemTicker() || ticker == CacheBuilder.d) ? null : ticker;
            this.m = cacheLoader;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        ManualSerializationProxy(com.google.common.cache.LocalCache<K, V> r17) {
            /*
                r16 = this;
                r0 = r17
                com.google.common.cache.LocalCache$Strength r1 = r0.h
                com.google.common.cache.LocalCache$Strength r2 = r0.i
                com.google.common.base.Equivalence<java.lang.Object> r3 = r0.f
                com.google.common.base.Equivalence<java.lang.Object> r4 = r0.g
                long r5 = r0.m
                long r7 = r0.l
                long r9 = r0.j
                com.google.common.cache.Weigher<K, V> r11 = r0.k
                int r12 = r0.e
                com.google.common.cache.RemovalListener<K, V> r13 = r0.p
                com.google.common.base.Ticker r14 = r0.q
                com.google.common.cache.CacheLoader<? super K, V> r15 = r0.t
                r0 = r16
                r0.<init>(r1, r2, r3, r4, r5, r7, r9, r11, r12, r13, r14, r15)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.ManualSerializationProxy.<init>(com.google.common.cache.LocalCache):void");
        }

        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            this.n = b().build();
        }

        private Object readResolve() {
            return this.n;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Cache<K, V> delegate() {
            return this.n;
        }

        /* access modifiers changed from: package-private */
        public CacheBuilder<K, V> b() {
            CacheBuilder<K1, V1> removalListener = CacheBuilder.newBuilder().a(this.b).b(this.c).a((Equivalence<Object>) this.d).b((Equivalence<Object>) this.e).concurrencyLevel(this.j).removalListener(this.k);
            removalListener.e = false;
            long j2 = this.f;
            if (j2 > 0) {
                removalListener.expireAfterWrite(j2, TimeUnit.NANOSECONDS);
            }
            long j3 = this.g;
            if (j3 > 0) {
                removalListener.expireAfterAccess(j3, TimeUnit.NANOSECONDS);
            }
            if (this.i != CacheBuilder.OneWeigher.INSTANCE) {
                removalListener.weigher(this.i);
                long j4 = this.h;
                if (j4 != -1) {
                    removalListener.maximumWeight(j4);
                }
            } else {
                long j5 = this.h;
                if (j5 != -1) {
                    removalListener.maximumSize(j5);
                }
            }
            Ticker ticker = this.l;
            if (ticker != null) {
                removalListener.ticker(ticker);
            }
            return removalListener;
        }
    }

    private enum NullEntry implements ReferenceEntry<Object, Object> {
        INSTANCE;

        public long getAccessTime() {
            return 0;
        }

        public int getHash() {
            return 0;
        }

        public Object getKey() {
            return null;
        }

        public ReferenceEntry<Object, Object> getNext() {
            return null;
        }

        public ReferenceEntry<Object, Object> getNextInAccessQueue() {
            return this;
        }

        public ReferenceEntry<Object, Object> getNextInWriteQueue() {
            return this;
        }

        public ReferenceEntry<Object, Object> getPreviousInAccessQueue() {
            return this;
        }

        public ReferenceEntry<Object, Object> getPreviousInWriteQueue() {
            return this;
        }

        public ValueReference<Object, Object> getValueReference() {
            return null;
        }

        public long getWriteTime() {
            return 0;
        }

        public void setAccessTime(long j) {
        }

        public void setNextInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        public void setNextInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        public void setPreviousInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        public void setPreviousInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        public void setValueReference(ValueReference<Object, Object> valueReference) {
        }

        public void setWriteTime(long j) {
        }
    }

    interface ReferenceEntry<K, V> {
        long getAccessTime();

        int getHash();

        @Nullable
        K getKey();

        @Nullable
        ReferenceEntry<K, V> getNext();

        ReferenceEntry<K, V> getNextInAccessQueue();

        ReferenceEntry<K, V> getNextInWriteQueue();

        ReferenceEntry<K, V> getPreviousInAccessQueue();

        ReferenceEntry<K, V> getPreviousInWriteQueue();

        ValueReference<K, V> getValueReference();

        long getWriteTime();

        void setAccessTime(long j);

        void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry);

        void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry);

        void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry);

        void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry);

        void setValueReference(ValueReference<K, V> valueReference);

        void setWriteTime(long j);
    }

    static class Segment<K, V> extends ReentrantLock {
        @Weak
        final LocalCache<K, V> a;
        volatile int b;
        @GuardedBy("this")
        long c;
        int d;
        int e;
        volatile AtomicReferenceArray<ReferenceEntry<K, V>> f;
        final long g;
        final ReferenceQueue<K> h;
        final ReferenceQueue<V> i;
        final Queue<ReferenceEntry<K, V>> j;
        final AtomicInteger k = new AtomicInteger();
        @GuardedBy("this")
        final Queue<ReferenceEntry<K, V>> l;
        @GuardedBy("this")
        final Queue<ReferenceEntry<K, V>> m;
        final AbstractCache.StatsCounter n;

        Segment(LocalCache<K, V> localCache, int i2, long j2, AbstractCache.StatsCounter statsCounter) {
            this.a = localCache;
            this.g = j2;
            this.n = (AbstractCache.StatsCounter) Preconditions.checkNotNull(statsCounter);
            a(a(i2));
            ReferenceQueue<V> referenceQueue = null;
            this.h = localCache.m() ? new ReferenceQueue<>() : null;
            this.i = localCache.n() ? new ReferenceQueue<>() : referenceQueue;
            this.j = localCache.f() ? new ConcurrentLinkedQueue<>() : LocalCache.q();
            this.l = localCache.g() ? new WriteQueue<>() : LocalCache.q();
            this.m = localCache.f() ? new AccessQueue<>() : LocalCache.q();
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public LoadingValueReference<K, V> a(K k2, int i2, boolean z) {
            lock();
            try {
                long read = this.a.q.read();
                c(read);
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.f;
                int length = (atomicReferenceArray.length() - 1) & i2;
                ReferenceEntry referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (referenceEntry2 != null) {
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() != i2 || key == null || !this.a.f.equivalent(k2, key)) {
                        referenceEntry2 = referenceEntry2.getNext();
                    } else {
                        ValueReference valueReference = referenceEntry2.getValueReference();
                        if (!valueReference.isLoading()) {
                            if (!z || read - referenceEntry2.getWriteTime() >= this.a.n) {
                                this.d++;
                                LoadingValueReference<K, V> loadingValueReference = new LoadingValueReference<>(valueReference);
                                referenceEntry2.setValueReference(loadingValueReference);
                                unlock();
                                m();
                                return loadingValueReference;
                            }
                        }
                        return null;
                    }
                }
                this.d++;
                LoadingValueReference<K, V> loadingValueReference2 = new LoadingValueReference<>();
                ReferenceEntry a2 = a(k2, i2, referenceEntry);
                a2.setValueReference(loadingValueReference2);
                atomicReferenceArray.set(length, a2);
                unlock();
                m();
                return loadingValueReference2;
            } finally {
                unlock();
                m();
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public ReferenceEntry<K, V> a(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            if (referenceEntry.getKey() == null) {
                return null;
            }
            ValueReference<K, V> valueReference = referenceEntry.getValueReference();
            V v = valueReference.get();
            if (v == null && valueReference.isActive()) {
                return null;
            }
            ReferenceEntry<K, V> a2 = this.a.r.a(this, referenceEntry, referenceEntry2);
            a2.setValueReference(valueReference.copyFor(this.i, v, a2));
            return a2;
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        @Nullable
        public ReferenceEntry<K, V> a(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2, @Nullable K k2, int i2, V v, ValueReference<K, V> valueReference, RemovalCause removalCause) {
            a(k2, i2, v, valueReference.getWeight(), removalCause);
            this.l.remove(referenceEntry2);
            this.m.remove(referenceEntry2);
            if (!valueReference.isLoading()) {
                return b(referenceEntry, referenceEntry2);
            }
            valueReference.notifyNewValue(null);
            return referenceEntry;
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public ReferenceEntry<K, V> a(Object obj, int i2) {
            for (ReferenceEntry<K, V> b2 = b(i2); b2 != null; b2 = b2.getNext()) {
                if (b2.getHash() == i2) {
                    K key = b2.getKey();
                    if (key == null) {
                        a();
                    } else if (this.a.f.equivalent(obj, key)) {
                        return b2;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public ReferenceEntry<K, V> a(Object obj, int i2, long j2) {
            ReferenceEntry<K, V> a2 = a(obj, i2);
            if (a2 == null) {
                return null;
            }
            if (!this.a.b(a2, j2)) {
                return a2;
            }
            a(j2);
            return null;
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public ReferenceEntry<K, V> a(K k2, int i2, @Nullable ReferenceEntry<K, V> referenceEntry) {
            return this.a.r.a(this, Preconditions.checkNotNull(k2), i2, referenceEntry);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001f, code lost:
            r4 = a(r5, r6, r10, true);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V a(com.google.common.cache.LocalCache.ReferenceEntry<K, V> r4, K r5, int r6, V r7, long r8, com.google.common.cache.CacheLoader<? super K, V> r10) {
            /*
                r3 = this;
                com.google.common.cache.LocalCache<K, V> r0 = r3.a
                boolean r0 = r0.e()
                if (r0 == 0) goto L_0x0027
                long r0 = r4.getWriteTime()
                long r8 = r8 - r0
                com.google.common.cache.LocalCache<K, V> r0 = r3.a
                long r0 = r0.n
                int r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
                if (r2 <= 0) goto L_0x0027
                com.google.common.cache.LocalCache$ValueReference r4 = r4.getValueReference()
                boolean r4 = r4.isLoading()
                if (r4 != 0) goto L_0x0027
                r4 = 1
                java.lang.Object r4 = r3.a(r5, (int) r6, r10, (boolean) r4)
                if (r4 == 0) goto L_0x0027
                return r4
            L_0x0027:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.Segment.a(com.google.common.cache.LocalCache$ReferenceEntry, java.lang.Object, int, java.lang.Object, long, com.google.common.cache.CacheLoader):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        public V a(ReferenceEntry<K, V> referenceEntry, K k2, ValueReference<K, V> valueReference) {
            if (valueReference.isLoading()) {
                Preconditions.checkState(!Thread.holdsLock(referenceEntry), "Recursive load of: %s", (Object) k2);
                try {
                    V waitForValue = valueReference.waitForValue();
                    if (waitForValue != null) {
                        a(referenceEntry, this.a.q.read());
                        return waitForValue;
                    }
                    throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + k2 + ".");
                } finally {
                    this.n.recordMisses(1);
                }
            } else {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public V a(K k2, int i2, CacheLoader<? super K, V> cacheLoader) {
            ReferenceEntry a2;
            Preconditions.checkNotNull(k2);
            Preconditions.checkNotNull(cacheLoader);
            try {
                if (!(this.b == 0 || (a2 = a((Object) k2, i2)) == null)) {
                    long read = this.a.q.read();
                    Object c2 = c(a2, read);
                    if (c2 != null) {
                        a(a2, read);
                        this.n.recordHits(1);
                        V a3 = a(a2, k2, i2, c2, read, cacheLoader);
                        l();
                        return a3;
                    }
                    ValueReference valueReference = a2.getValueReference();
                    if (valueReference.isLoading()) {
                        V a4 = a(a2, k2, valueReference);
                        l();
                        return a4;
                    }
                }
                V b2 = b(k2, i2, cacheLoader);
                l();
                return b2;
            } catch (ExecutionException e2) {
                Throwable cause = e2.getCause();
                if (cause instanceof Error) {
                    throw new ExecutionError((Error) cause);
                } else if (cause instanceof RuntimeException) {
                    throw new UncheckedExecutionException(cause);
                } else {
                    throw e2;
                }
            } catch (Throwable th) {
                l();
                throw th;
            }
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public V a(K k2, int i2, CacheLoader<? super K, V> cacheLoader, boolean z) {
            LoadingValueReference a2 = a(k2, i2, z);
            if (a2 == null) {
                return null;
            }
            ListenableFuture<V> b2 = b(k2, i2, a2, cacheLoader);
            if (b2.isDone()) {
                try {
                    return Uninterruptibles.getUninterruptibly(b2);
                } catch (Throwable unused) {
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public V a(K k2, int i2, LoadingValueReference<K, V> loadingValueReference, CacheLoader<? super K, V> cacheLoader) {
            return a(k2, i2, loadingValueReference, loadingValueReference.loadFuture(k2, cacheLoader));
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0043  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V a(K r4, int r5, com.google.common.cache.LocalCache.LoadingValueReference<K, V> r6, com.google.common.util.concurrent.ListenableFuture<V> r7) {
            /*
                r3 = this;
                java.lang.Object r7 = com.google.common.util.concurrent.Uninterruptibles.getUninterruptibly(r7)     // Catch:{ all -> 0x003f }
                if (r7 == 0) goto L_0x0023
                com.google.common.cache.AbstractCache$StatsCounter r0 = r3.n     // Catch:{ all -> 0x0021 }
                long r1 = r6.elapsedNanos()     // Catch:{ all -> 0x0021 }
                r0.recordLoadSuccess(r1)     // Catch:{ all -> 0x0021 }
                r3.a(r4, (int) r5, r6, r7)     // Catch:{ all -> 0x0021 }
                if (r7 != 0) goto L_0x0020
                com.google.common.cache.AbstractCache$StatsCounter r0 = r3.n
                long r1 = r6.elapsedNanos()
                r0.recordLoadException(r1)
                r3.a(r4, (int) r5, r6)
            L_0x0020:
                return r7
            L_0x0021:
                r0 = move-exception
                goto L_0x0041
            L_0x0023:
                com.google.common.cache.CacheLoader$InvalidCacheLoadException r0 = new com.google.common.cache.CacheLoader$InvalidCacheLoadException     // Catch:{ all -> 0x0021 }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0021 }
                r1.<init>()     // Catch:{ all -> 0x0021 }
                java.lang.String r2 = "CacheLoader returned null for key "
                r1.append(r2)     // Catch:{ all -> 0x0021 }
                r1.append(r4)     // Catch:{ all -> 0x0021 }
                java.lang.String r2 = "."
                r1.append(r2)     // Catch:{ all -> 0x0021 }
                java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0021 }
                r0.<init>(r1)     // Catch:{ all -> 0x0021 }
                throw r0     // Catch:{ all -> 0x0021 }
            L_0x003f:
                r0 = move-exception
                r7 = 0
            L_0x0041:
                if (r7 != 0) goto L_0x004f
                com.google.common.cache.AbstractCache$StatsCounter r7 = r3.n
                long r1 = r6.elapsedNanos()
                r7.recordLoadException(r1)
                r3.a(r4, (int) r5, r6)
            L_0x004f:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.Segment.a(java.lang.Object, int, com.google.common.cache.LocalCache$LoadingValueReference, com.google.common.util.concurrent.ListenableFuture):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x003f, code lost:
            r15 = r12.getValueReference();
            r16 = r15.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0047, code lost:
            if (r16 != null) goto L_0x0075;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x004d, code lost:
            if (r15.isActive() == false) goto L_0x006e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x004f, code lost:
            r1 = r9.b;
            r9.d++;
            r10.set(r11, a(r2, r12, r4, r19, r16, r15, com.google.common.cache.RemovalCause.COLLECTED));
            r9.b--;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
            r9.d++;
            a(r18, r19, r16, r15.getWeight(), com.google.common.cache.RemovalCause.REPLACED);
            a(r12, r18, r20, r7);
            a(r12);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x009a, code lost:
            unlock();
            m();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a0, code lost:
            return r16;
         */
        @javax.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V a(K r18, int r19, V r20) {
            /*
                r17 = this;
                r9 = r17
                r0 = r19
                r17.lock()
                com.google.common.cache.LocalCache<K, V> r1 = r9.a     // Catch:{ all -> 0x00a9 }
                com.google.common.base.Ticker r1 = r1.q     // Catch:{ all -> 0x00a9 }
                long r7 = r1.read()     // Catch:{ all -> 0x00a9 }
                r9.c(r7)     // Catch:{ all -> 0x00a9 }
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.cache.LocalCache$ReferenceEntry<K, V>> r10 = r9.f     // Catch:{ all -> 0x00a9 }
                int r1 = r10.length()     // Catch:{ all -> 0x00a9 }
                int r1 = r1 + -1
                r11 = r0 & r1
                java.lang.Object r1 = r10.get(r11)     // Catch:{ all -> 0x00a9 }
                r2 = r1
                com.google.common.cache.LocalCache$ReferenceEntry r2 = (com.google.common.cache.LocalCache.ReferenceEntry) r2     // Catch:{ all -> 0x00a9 }
                r12 = r2
            L_0x0024:
                r13 = 0
                if (r12 == 0) goto L_0x006e
                java.lang.Object r4 = r12.getKey()     // Catch:{ all -> 0x00a9 }
                int r1 = r12.getHash()     // Catch:{ all -> 0x00a9 }
                if (r1 != r0) goto L_0x00a1
                if (r4 == 0) goto L_0x00a1
                com.google.common.cache.LocalCache<K, V> r1 = r9.a     // Catch:{ all -> 0x00a9 }
                com.google.common.base.Equivalence<java.lang.Object> r1 = r1.f     // Catch:{ all -> 0x00a9 }
                r14 = r18
                boolean r1 = r1.equivalent(r14, r4)     // Catch:{ all -> 0x00a9 }
                if (r1 == 0) goto L_0x00a3
                com.google.common.cache.LocalCache$ValueReference r15 = r12.getValueReference()     // Catch:{ all -> 0x00a9 }
                java.lang.Object r16 = r15.get()     // Catch:{ all -> 0x00a9 }
                if (r16 != 0) goto L_0x0075
                boolean r1 = r15.isActive()     // Catch:{ all -> 0x00a9 }
                if (r1 == 0) goto L_0x006e
                int r1 = r9.b     // Catch:{ all -> 0x00a9 }
                int r1 = r9.d     // Catch:{ all -> 0x00a9 }
                int r1 = r1 + 1
                r9.d = r1     // Catch:{ all -> 0x00a9 }
                com.google.common.cache.RemovalCause r8 = com.google.common.cache.RemovalCause.COLLECTED     // Catch:{ all -> 0x00a9 }
                r1 = r17
                r3 = r12
                r5 = r19
                r6 = r16
                r7 = r15
                com.google.common.cache.LocalCache$ReferenceEntry r0 = r1.a(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x00a9 }
                int r1 = r9.b     // Catch:{ all -> 0x00a9 }
                int r1 = r1 + -1
                r10.set(r11, r0)     // Catch:{ all -> 0x00a9 }
                r9.b = r1     // Catch:{ all -> 0x00a9 }
            L_0x006e:
                r17.unlock()
                r17.m()
                return r13
            L_0x0075:
                int r1 = r9.d     // Catch:{ all -> 0x00a9 }
                int r1 = r1 + 1
                r9.d = r1     // Catch:{ all -> 0x00a9 }
                int r5 = r15.getWeight()     // Catch:{ all -> 0x00a9 }
                com.google.common.cache.RemovalCause r6 = com.google.common.cache.RemovalCause.REPLACED     // Catch:{ all -> 0x00a9 }
                r1 = r17
                r2 = r18
                r3 = r19
                r4 = r16
                r1.a(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00a9 }
                r1 = r17
                r2 = r12
                r3 = r18
                r4 = r20
                r5 = r7
                r1.a(r2, r3, r4, (long) r5)     // Catch:{ all -> 0x00a9 }
                r9.a(r12)     // Catch:{ all -> 0x00a9 }
                r17.unlock()
                r17.m()
                return r16
            L_0x00a1:
                r14 = r18
            L_0x00a3:
                com.google.common.cache.LocalCache$ReferenceEntry r12 = r12.getNext()     // Catch:{ all -> 0x00a9 }
                goto L_0x0024
            L_0x00a9:
                r0 = move-exception
                r17.unlock()
                r17.m()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.Segment.a(java.lang.Object, int, java.lang.Object):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0049, code lost:
            r1 = r12.getValueReference();
            r10 = r1.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0051, code lost:
            if (r10 != null) goto L_0x0092;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0053, code lost:
            r7.d++;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x005d, code lost:
            if (r1.isActive() == false) goto L_0x0079;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x005f, code lost:
            a(r15, r16, r10, r1.getWeight(), com.google.common.cache.RemovalCause.COLLECTED);
            a(r12, r15, r17, r8);
            r0 = r7.b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0079, code lost:
            a(r12, r15, r17, r8);
            r0 = r7.b + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0086, code lost:
            r7.b = r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0092, code lost:
            if (r18 == false) goto L_0x009e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            b(r12, r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0097, code lost:
            unlock();
            m();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x009d, code lost:
            return r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
            r7.d++;
            a(r15, r16, r10, r1.getWeight(), com.google.common.cache.RemovalCause.REPLACED);
            a(r12, r15, r17, r8);
            a(r12);
         */
        @javax.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V a(K r15, int r16, V r17, boolean r18) {
            /*
                r14 = this;
                r7 = r14
                r0 = r15
                r3 = r16
                r14.lock()
                com.google.common.cache.LocalCache<K, V> r1 = r7.a     // Catch:{ all -> 0x00e2 }
                com.google.common.base.Ticker r1 = r1.q     // Catch:{ all -> 0x00e2 }
                long r8 = r1.read()     // Catch:{ all -> 0x00e2 }
                r14.c(r8)     // Catch:{ all -> 0x00e2 }
                int r1 = r7.b     // Catch:{ all -> 0x00e2 }
                int r1 = r1 + 1
                int r2 = r7.e     // Catch:{ all -> 0x00e2 }
                if (r1 <= r2) goto L_0x001f
                r14.j()     // Catch:{ all -> 0x00e2 }
                int r1 = r7.b     // Catch:{ all -> 0x00e2 }
            L_0x001f:
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.cache.LocalCache$ReferenceEntry<K, V>> r10 = r7.f     // Catch:{ all -> 0x00e2 }
                int r1 = r10.length()     // Catch:{ all -> 0x00e2 }
                int r1 = r1 + -1
                r11 = r3 & r1
                java.lang.Object r1 = r10.get(r11)     // Catch:{ all -> 0x00e2 }
                com.google.common.cache.LocalCache$ReferenceEntry r1 = (com.google.common.cache.LocalCache.ReferenceEntry) r1     // Catch:{ all -> 0x00e2 }
                r12 = r1
            L_0x0030:
                r13 = 0
                if (r12 == 0) goto L_0x00c5
                java.lang.Object r2 = r12.getKey()     // Catch:{ all -> 0x00e2 }
                int r4 = r12.getHash()     // Catch:{ all -> 0x00e2 }
                if (r4 != r3) goto L_0x00bf
                if (r2 == 0) goto L_0x00bf
                com.google.common.cache.LocalCache<K, V> r4 = r7.a     // Catch:{ all -> 0x00e2 }
                com.google.common.base.Equivalence<java.lang.Object> r4 = r4.f     // Catch:{ all -> 0x00e2 }
                boolean r2 = r4.equivalent(r15, r2)     // Catch:{ all -> 0x00e2 }
                if (r2 == 0) goto L_0x00bf
                com.google.common.cache.LocalCache$ValueReference r1 = r12.getValueReference()     // Catch:{ all -> 0x00e2 }
                java.lang.Object r10 = r1.get()     // Catch:{ all -> 0x00e2 }
                if (r10 != 0) goto L_0x0092
                int r2 = r7.d     // Catch:{ all -> 0x00e2 }
                int r2 = r2 + 1
                r7.d = r2     // Catch:{ all -> 0x00e2 }
                boolean r2 = r1.isActive()     // Catch:{ all -> 0x00e2 }
                if (r2 == 0) goto L_0x0079
                int r5 = r1.getWeight()     // Catch:{ all -> 0x00e2 }
                com.google.common.cache.RemovalCause r6 = com.google.common.cache.RemovalCause.COLLECTED     // Catch:{ all -> 0x00e2 }
                r1 = r14
                r2 = r15
                r3 = r16
                r4 = r10
                r1.a(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00e2 }
                r1 = r14
                r2 = r12
                r3 = r15
                r4 = r17
                r5 = r8
                r1.a(r2, r3, r4, (long) r5)     // Catch:{ all -> 0x00e2 }
                int r0 = r7.b     // Catch:{ all -> 0x00e2 }
                goto L_0x0086
            L_0x0079:
                r1 = r14
                r2 = r12
                r3 = r15
                r4 = r17
                r5 = r8
                r1.a(r2, r3, r4, (long) r5)     // Catch:{ all -> 0x00e2 }
                int r0 = r7.b     // Catch:{ all -> 0x00e2 }
                int r0 = r0 + 1
            L_0x0086:
                r7.b = r0     // Catch:{ all -> 0x00e2 }
            L_0x0088:
                r14.a(r12)     // Catch:{ all -> 0x00e2 }
                r14.unlock()
                r14.m()
                return r13
            L_0x0092:
                if (r18 == 0) goto L_0x009e
                r14.b(r12, (long) r8)     // Catch:{ all -> 0x00e2 }
            L_0x0097:
                r14.unlock()
                r14.m()
                return r10
            L_0x009e:
                int r2 = r7.d     // Catch:{ all -> 0x00e2 }
                int r2 = r2 + 1
                r7.d = r2     // Catch:{ all -> 0x00e2 }
                int r5 = r1.getWeight()     // Catch:{ all -> 0x00e2 }
                com.google.common.cache.RemovalCause r6 = com.google.common.cache.RemovalCause.REPLACED     // Catch:{ all -> 0x00e2 }
                r1 = r14
                r2 = r15
                r3 = r16
                r4 = r10
                r1.a(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00e2 }
                r1 = r14
                r2 = r12
                r3 = r15
                r4 = r17
                r5 = r8
                r1.a(r2, r3, r4, (long) r5)     // Catch:{ all -> 0x00e2 }
                r14.a(r12)     // Catch:{ all -> 0x00e2 }
                goto L_0x0097
            L_0x00bf:
                com.google.common.cache.LocalCache$ReferenceEntry r12 = r12.getNext()     // Catch:{ all -> 0x00e2 }
                goto L_0x0030
            L_0x00c5:
                int r2 = r7.d     // Catch:{ all -> 0x00e2 }
                int r2 = r2 + 1
                r7.d = r2     // Catch:{ all -> 0x00e2 }
                com.google.common.cache.LocalCache$ReferenceEntry r12 = r14.a(r15, (int) r3, r1)     // Catch:{ all -> 0x00e2 }
                r1 = r14
                r2 = r12
                r3 = r15
                r4 = r17
                r5 = r8
                r1.a(r2, r3, r4, (long) r5)     // Catch:{ all -> 0x00e2 }
                r10.set(r11, r12)     // Catch:{ all -> 0x00e2 }
                int r0 = r7.b     // Catch:{ all -> 0x00e2 }
                int r0 = r0 + 1
                r7.b = r0     // Catch:{ all -> 0x00e2 }
                goto L_0x0088
            L_0x00e2:
                r0 = move-exception
                r14.unlock()
                r14.m()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.Segment.a(java.lang.Object, int, java.lang.Object, boolean):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        public AtomicReferenceArray<ReferenceEntry<K, V>> a(int i2) {
            return new AtomicReferenceArray<>(i2);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            if (tryLock()) {
                try {
                    b();
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a(long j2) {
            if (tryLock()) {
                try {
                    b(j2);
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void a(ReferenceEntry<K, V> referenceEntry) {
            if (this.a.a()) {
                h();
                if (((long) referenceEntry.getValueReference().getWeight()) <= this.g || a(referenceEntry, referenceEntry.getHash(), RemovalCause.SIZE)) {
                    while (this.c > this.g) {
                        ReferenceEntry i2 = i();
                        if (!a(i2, i2.getHash(), RemovalCause.SIZE)) {
                            throw new AssertionError();
                        }
                    }
                    return;
                }
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void a(ReferenceEntry<K, V> referenceEntry, int i2, long j2) {
            h();
            this.c += (long) i2;
            if (this.a.i()) {
                referenceEntry.setAccessTime(j2);
            }
            if (this.a.h()) {
                referenceEntry.setWriteTime(j2);
            }
            this.m.add(referenceEntry);
            this.l.add(referenceEntry);
        }

        /* access modifiers changed from: package-private */
        public void a(ReferenceEntry<K, V> referenceEntry, long j2) {
            if (this.a.i()) {
                referenceEntry.setAccessTime(j2);
            }
            this.j.add(referenceEntry);
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void a(ReferenceEntry<K, V> referenceEntry, K k2, V v, long j2) {
            ValueReference<K, V> valueReference = referenceEntry.getValueReference();
            int weigh = this.a.k.weigh(k2, v);
            Preconditions.checkState(weigh >= 0, "Weights must be non-negative");
            referenceEntry.setValueReference(this.a.i.a(this, referenceEntry, v, weigh));
            a(referenceEntry, weigh, j2);
            valueReference.notifyNewValue(v);
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void a(@Nullable K k2, int i2, @Nullable V v, int i3, RemovalCause removalCause) {
            this.c -= (long) i3;
            if (removalCause.a()) {
                this.n.recordEviction();
            }
            if (this.a.o != LocalCache.v) {
                this.a.o.offer(RemovalNotification.create(k2, v, removalCause));
            }
        }

        /* access modifiers changed from: package-private */
        public void a(AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray) {
            this.e = (atomicReferenceArray.length() * 3) / 4;
            if (!this.a.b()) {
                int i2 = this.e;
                if (((long) i2) == this.g) {
                    this.e = i2 + 1;
                }
            }
            this.f = atomicReferenceArray;
        }

        /* access modifiers changed from: package-private */
        public boolean a(ReferenceEntry<K, V> referenceEntry, int i2) {
            lock();
            try {
                int i3 = this.b;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.f;
                int length = (atomicReferenceArray.length() - 1) & i2;
                ReferenceEntry<K, V> referenceEntry2 = atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> referenceEntry3 = referenceEntry2; referenceEntry3 != null; referenceEntry3 = referenceEntry3.getNext()) {
                    if (referenceEntry3 == referenceEntry) {
                        this.d++;
                        atomicReferenceArray.set(length, a(referenceEntry2, referenceEntry3, referenceEntry3.getKey(), i2, referenceEntry3.getValueReference().get(), referenceEntry3.getValueReference(), RemovalCause.COLLECTED));
                        this.b--;
                        return true;
                    }
                }
                unlock();
                m();
                return false;
            } finally {
                unlock();
                m();
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        @VisibleForTesting
        public boolean a(ReferenceEntry<K, V> referenceEntry, int i2, RemovalCause removalCause) {
            int i3 = this.b;
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.f;
            int length = (atomicReferenceArray.length() - 1) & i2;
            ReferenceEntry<K, V> referenceEntry2 = atomicReferenceArray.get(length);
            for (ReferenceEntry<K, V> referenceEntry3 = referenceEntry2; referenceEntry3 != null; referenceEntry3 = referenceEntry3.getNext()) {
                if (referenceEntry3 == referenceEntry) {
                    this.d++;
                    atomicReferenceArray.set(length, a(referenceEntry2, referenceEntry3, referenceEntry3.getKey(), i2, referenceEntry3.getValueReference().get(), referenceEntry3.getValueReference(), removalCause));
                    this.b--;
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean a(K k2, int i2, LoadingValueReference<K, V> loadingValueReference) {
            lock();
            try {
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.f;
                int length = (atomicReferenceArray.length() - 1) & i2;
                ReferenceEntry referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    if (referenceEntry2 == null) {
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() != i2 || key == null || !this.a.f.equivalent(k2, key)) {
                        referenceEntry2 = referenceEntry2.getNext();
                    } else if (referenceEntry2.getValueReference() == loadingValueReference) {
                        if (loadingValueReference.isActive()) {
                            referenceEntry2.setValueReference(loadingValueReference.getOldValue());
                        } else {
                            atomicReferenceArray.set(length, b(referenceEntry, referenceEntry2));
                        }
                        return true;
                    }
                }
                unlock();
                m();
                return false;
            } finally {
                unlock();
                m();
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x004a, code lost:
            r1 = r14.getValueReference();
            r4 = r1.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0054, code lost:
            if (r18 == r1) goto L_0x0072;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0056, code lost:
            if (r4 != null) goto L_0x005d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x005a, code lost:
            if (r1 == com.google.common.cache.LocalCache.u) goto L_0x005d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x005d, code lost:
            a(r16, r17, r19, 0, com.google.common.cache.RemovalCause.REPLACED);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x006a, code lost:
            unlock();
            m();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0071, code lost:
            return false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
            r7.d++;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x007b, code lost:
            if (r18.isActive() == false) goto L_0x0093;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x007d, code lost:
            if (r4 != null) goto L_0x0082;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x007f, code lost:
            r1 = com.google.common.cache.RemovalCause.COLLECTED;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0082, code lost:
            r1 = com.google.common.cache.RemovalCause.REPLACED;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0084, code lost:
            a(r16, r17, r4, r18.getWeight(), r1);
            r11 = r11 - 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0093, code lost:
            a(r14, r16, r19, r8);
            r7.b = r11;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(K r16, int r17, com.google.common.cache.LocalCache.LoadingValueReference<K, V> r18, V r19) {
            /*
                r15 = this;
                r7 = r15
                r0 = r16
                r3 = r17
                r15.lock()
                com.google.common.cache.LocalCache<K, V> r1 = r7.a     // Catch:{ all -> 0x00c9 }
                com.google.common.base.Ticker r1 = r1.q     // Catch:{ all -> 0x00c9 }
                long r8 = r1.read()     // Catch:{ all -> 0x00c9 }
                r15.c(r8)     // Catch:{ all -> 0x00c9 }
                int r1 = r7.b     // Catch:{ all -> 0x00c9 }
                r10 = 1
                int r1 = r1 + r10
                int r2 = r7.e     // Catch:{ all -> 0x00c9 }
                if (r1 <= r2) goto L_0x0021
                r15.j()     // Catch:{ all -> 0x00c9 }
                int r1 = r7.b     // Catch:{ all -> 0x00c9 }
                int r1 = r1 + r10
            L_0x0021:
                r11 = r1
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.cache.LocalCache$ReferenceEntry<K, V>> r12 = r7.f     // Catch:{ all -> 0x00c9 }
                int r1 = r12.length()     // Catch:{ all -> 0x00c9 }
                int r1 = r1 - r10
                r13 = r3 & r1
                java.lang.Object r1 = r12.get(r13)     // Catch:{ all -> 0x00c9 }
                com.google.common.cache.LocalCache$ReferenceEntry r1 = (com.google.common.cache.LocalCache.ReferenceEntry) r1     // Catch:{ all -> 0x00c9 }
                r14 = r1
            L_0x0032:
                if (r14 == 0) goto L_0x00b0
                java.lang.Object r2 = r14.getKey()     // Catch:{ all -> 0x00c9 }
                int r4 = r14.getHash()     // Catch:{ all -> 0x00c9 }
                if (r4 != r3) goto L_0x00a9
                if (r2 == 0) goto L_0x00a9
                com.google.common.cache.LocalCache<K, V> r4 = r7.a     // Catch:{ all -> 0x00c9 }
                com.google.common.base.Equivalence<java.lang.Object> r4 = r4.f     // Catch:{ all -> 0x00c9 }
                boolean r2 = r4.equivalent(r0, r2)     // Catch:{ all -> 0x00c9 }
                if (r2 == 0) goto L_0x00a9
                com.google.common.cache.LocalCache$ValueReference r1 = r14.getValueReference()     // Catch:{ all -> 0x00c9 }
                java.lang.Object r4 = r1.get()     // Catch:{ all -> 0x00c9 }
                r2 = r18
                if (r2 == r1) goto L_0x0072
                if (r4 != 0) goto L_0x005d
                com.google.common.cache.LocalCache$ValueReference<java.lang.Object, java.lang.Object> r5 = com.google.common.cache.LocalCache.u     // Catch:{ all -> 0x00c9 }
                if (r1 == r5) goto L_0x005d
                goto L_0x0072
            L_0x005d:
                r5 = 0
                com.google.common.cache.RemovalCause r6 = com.google.common.cache.RemovalCause.REPLACED     // Catch:{ all -> 0x00c9 }
                r1 = r15
                r2 = r16
                r3 = r17
                r4 = r19
                r1.a(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00c9 }
                r0 = 0
                r15.unlock()
                r15.m()
                return r0
            L_0x0072:
                int r1 = r7.d     // Catch:{ all -> 0x00c9 }
                int r1 = r1 + r10
                r7.d = r1     // Catch:{ all -> 0x00c9 }
                boolean r1 = r18.isActive()     // Catch:{ all -> 0x00c9 }
                if (r1 == 0) goto L_0x0093
                if (r4 != 0) goto L_0x0082
                com.google.common.cache.RemovalCause r1 = com.google.common.cache.RemovalCause.COLLECTED     // Catch:{ all -> 0x00c9 }
                goto L_0x0084
            L_0x0082:
                com.google.common.cache.RemovalCause r1 = com.google.common.cache.RemovalCause.REPLACED     // Catch:{ all -> 0x00c9 }
            L_0x0084:
                r6 = r1
                int r5 = r18.getWeight()     // Catch:{ all -> 0x00c9 }
                r1 = r15
                r2 = r16
                r3 = r17
                r1.a(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00c9 }
                int r11 = r11 + -1
            L_0x0093:
                r1 = r15
                r2 = r14
                r3 = r16
                r4 = r19
                r5 = r8
                r1.a(r2, r3, r4, (long) r5)     // Catch:{ all -> 0x00c9 }
                r7.b = r11     // Catch:{ all -> 0x00c9 }
            L_0x009f:
                r15.a(r14)     // Catch:{ all -> 0x00c9 }
                r15.unlock()
                r15.m()
                return r10
            L_0x00a9:
                r2 = r18
                com.google.common.cache.LocalCache$ReferenceEntry r14 = r14.getNext()     // Catch:{ all -> 0x00c9 }
                goto L_0x0032
            L_0x00b0:
                int r2 = r7.d     // Catch:{ all -> 0x00c9 }
                int r2 = r2 + r10
                r7.d = r2     // Catch:{ all -> 0x00c9 }
                com.google.common.cache.LocalCache$ReferenceEntry r14 = r15.a(r0, (int) r3, r1)     // Catch:{ all -> 0x00c9 }
                r1 = r15
                r2 = r14
                r3 = r16
                r4 = r19
                r5 = r8
                r1.a(r2, r3, r4, (long) r5)     // Catch:{ all -> 0x00c9 }
                r12.set(r13, r14)     // Catch:{ all -> 0x00c9 }
                r7.b = r11     // Catch:{ all -> 0x00c9 }
                goto L_0x009f
            L_0x00c9:
                r0 = move-exception
                r15.unlock()
                r15.m()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.Segment.a(java.lang.Object, int, com.google.common.cache.LocalCache$LoadingValueReference, java.lang.Object):boolean");
        }

        /* access modifiers changed from: package-private */
        public boolean a(K k2, int i2, ValueReference<K, V> valueReference) {
            lock();
            try {
                int i3 = this.b;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.f;
                int length = (atomicReferenceArray.length() - 1) & i2;
                ReferenceEntry referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (referenceEntry2 != null) {
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() != i2 || key == null || !this.a.f.equivalent(k2, key)) {
                        referenceEntry2 = referenceEntry2.getNext();
                    } else if (referenceEntry2.getValueReference() == valueReference) {
                        this.d++;
                        atomicReferenceArray.set(length, a(referenceEntry, referenceEntry2, key, i2, valueReference.get(), valueReference, RemovalCause.COLLECTED));
                        this.b--;
                        return true;
                    } else {
                        unlock();
                        if (!isHeldByCurrentThread()) {
                            m();
                        }
                        return false;
                    }
                }
                unlock();
                if (!isHeldByCurrentThread()) {
                    m();
                }
                return false;
            } finally {
                unlock();
                if (!isHeldByCurrentThread()) {
                    m();
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x003f, code lost:
            r16 = r13.getValueReference();
            r6 = r16.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0047, code lost:
            if (r6 != null) goto L_0x0072;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x004d, code lost:
            if (r16.isActive() == false) goto L_0x006b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x004f, code lost:
            r1 = r9.b;
            r9.d++;
            r10.set(r12, a(r2, r13, r4, r19, r6, r16, com.google.common.cache.RemovalCause.COLLECTED));
            r9.b--;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x007c, code lost:
            if (r9.a.g.equivalent(r20, r6) == false) goto L_0x00a9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x007e, code lost:
            r9.d++;
            a(r18, r19, r6, r16.getWeight(), com.google.common.cache.RemovalCause.REPLACED);
            a(r13, r18, r21, r7);
            a(r13);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a2, code lost:
            unlock();
            m();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x00a8, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
            b(r13, r7);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(K r18, int r19, V r20, V r21) {
            /*
                r17 = this;
                r9 = r17
                r0 = r19
                r17.lock()
                com.google.common.cache.LocalCache<K, V> r1 = r9.a     // Catch:{ all -> 0x00b7 }
                com.google.common.base.Ticker r1 = r1.q     // Catch:{ all -> 0x00b7 }
                long r7 = r1.read()     // Catch:{ all -> 0x00b7 }
                r9.c(r7)     // Catch:{ all -> 0x00b7 }
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.cache.LocalCache$ReferenceEntry<K, V>> r10 = r9.f     // Catch:{ all -> 0x00b7 }
                int r1 = r10.length()     // Catch:{ all -> 0x00b7 }
                r11 = 1
                int r1 = r1 - r11
                r12 = r0 & r1
                java.lang.Object r1 = r10.get(r12)     // Catch:{ all -> 0x00b7 }
                r2 = r1
                com.google.common.cache.LocalCache$ReferenceEntry r2 = (com.google.common.cache.LocalCache.ReferenceEntry) r2     // Catch:{ all -> 0x00b7 }
                r13 = r2
            L_0x0024:
                r14 = 0
                if (r13 == 0) goto L_0x006b
                java.lang.Object r4 = r13.getKey()     // Catch:{ all -> 0x00b7 }
                int r1 = r13.getHash()     // Catch:{ all -> 0x00b7 }
                if (r1 != r0) goto L_0x00ad
                if (r4 == 0) goto L_0x00ad
                com.google.common.cache.LocalCache<K, V> r1 = r9.a     // Catch:{ all -> 0x00b7 }
                com.google.common.base.Equivalence<java.lang.Object> r1 = r1.f     // Catch:{ all -> 0x00b7 }
                r15 = r18
                boolean r1 = r1.equivalent(r15, r4)     // Catch:{ all -> 0x00b7 }
                if (r1 == 0) goto L_0x00af
                com.google.common.cache.LocalCache$ValueReference r16 = r13.getValueReference()     // Catch:{ all -> 0x00b7 }
                java.lang.Object r6 = r16.get()     // Catch:{ all -> 0x00b7 }
                if (r6 != 0) goto L_0x0072
                boolean r1 = r16.isActive()     // Catch:{ all -> 0x00b7 }
                if (r1 == 0) goto L_0x006b
                int r1 = r9.b     // Catch:{ all -> 0x00b7 }
                int r1 = r9.d     // Catch:{ all -> 0x00b7 }
                int r1 = r1 + r11
                r9.d = r1     // Catch:{ all -> 0x00b7 }
                com.google.common.cache.RemovalCause r8 = com.google.common.cache.RemovalCause.COLLECTED     // Catch:{ all -> 0x00b7 }
                r1 = r17
                r3 = r13
                r5 = r19
                r7 = r16
                com.google.common.cache.LocalCache$ReferenceEntry r0 = r1.a(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x00b7 }
                int r1 = r9.b     // Catch:{ all -> 0x00b7 }
                int r1 = r1 - r11
                r10.set(r12, r0)     // Catch:{ all -> 0x00b7 }
                r9.b = r1     // Catch:{ all -> 0x00b7 }
            L_0x006b:
                r17.unlock()
                r17.m()
                return r14
            L_0x0072:
                com.google.common.cache.LocalCache<K, V> r1 = r9.a     // Catch:{ all -> 0x00b7 }
                com.google.common.base.Equivalence<java.lang.Object> r1 = r1.g     // Catch:{ all -> 0x00b7 }
                r3 = r20
                boolean r1 = r1.equivalent(r3, r6)     // Catch:{ all -> 0x00b7 }
                if (r1 == 0) goto L_0x00a9
                int r1 = r9.d     // Catch:{ all -> 0x00b7 }
                int r1 = r1 + r11
                r9.d = r1     // Catch:{ all -> 0x00b7 }
                int r5 = r16.getWeight()     // Catch:{ all -> 0x00b7 }
                com.google.common.cache.RemovalCause r10 = com.google.common.cache.RemovalCause.REPLACED     // Catch:{ all -> 0x00b7 }
                r1 = r17
                r2 = r18
                r3 = r19
                r4 = r6
                r6 = r10
                r1.a(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00b7 }
                r1 = r17
                r2 = r13
                r3 = r18
                r4 = r21
                r5 = r7
                r1.a(r2, r3, r4, (long) r5)     // Catch:{ all -> 0x00b7 }
                r9.a(r13)     // Catch:{ all -> 0x00b7 }
                r17.unlock()
                r17.m()
                return r11
            L_0x00a9:
                r9.b(r13, (long) r7)     // Catch:{ all -> 0x00b7 }
                goto L_0x006b
            L_0x00ad:
                r15 = r18
            L_0x00af:
                r3 = r20
                com.google.common.cache.LocalCache$ReferenceEntry r13 = r13.getNext()     // Catch:{ all -> 0x00b7 }
                goto L_0x0024
            L_0x00b7:
                r0 = move-exception
                r17.unlock()
                r17.m()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.Segment.a(java.lang.Object, int, java.lang.Object, java.lang.Object):boolean");
        }

        /* access modifiers changed from: package-private */
        public ReferenceEntry<K, V> b(int i2) {
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.f;
            return atomicReferenceArray.get(i2 & (atomicReferenceArray.length() - 1));
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        @Nullable
        public ReferenceEntry<K, V> b(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            int i2 = this.b;
            ReferenceEntry<K, V> next = referenceEntry2.getNext();
            while (referenceEntry != referenceEntry2) {
                ReferenceEntry<K, V> a2 = a(referenceEntry, next);
                if (a2 != null) {
                    next = a2;
                } else {
                    b(referenceEntry);
                    i2--;
                }
                referenceEntry = referenceEntry.getNext();
            }
            this.b = i2;
            return next;
        }

        /* access modifiers changed from: package-private */
        public ListenableFuture<V> b(K k2, int i2, LoadingValueReference<K, V> loadingValueReference, CacheLoader<? super K, V> cacheLoader) {
            ListenableFuture<V> loadFuture = loadingValueReference.loadFuture(k2, cacheLoader);
            final K k3 = k2;
            final int i3 = i2;
            final LoadingValueReference<K, V> loadingValueReference2 = loadingValueReference;
            final ListenableFuture<V> listenableFuture = loadFuture;
            loadFuture.addListener(new Runnable() {
                public void run() {
                    try {
                        Segment.this.a(k3, i3, loadingValueReference2, listenableFuture);
                    } catch (Throwable th) {
                        LocalCache.a.log(Level.WARNING, "Exception thrown during refresh", th);
                        loadingValueReference2.setException(th);
                    }
                }
            }, MoreExecutors.directExecutor());
            return loadFuture;
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public V b(Object obj, int i2) {
            try {
                if (this.b != 0) {
                    long read = this.a.q.read();
                    ReferenceEntry a2 = a(obj, i2, read);
                    if (a2 == null) {
                        return null;
                    }
                    Object obj2 = a2.getValueReference().get();
                    if (obj2 != null) {
                        a(a2, read);
                        V a3 = a(a2, a2.getKey(), i2, obj2, read, this.a.t);
                        l();
                        return a3;
                    }
                    a();
                }
                l();
                return null;
            } finally {
                l();
            }
        }

        /* access modifiers changed from: package-private */
        public V b(K k2, int i2, CacheLoader<? super K, V> cacheLoader) {
            LoadingValueReference loadingValueReference;
            ValueReference valueReference;
            boolean z;
            V a2;
            int weight;
            RemovalCause removalCause;
            K k3 = k2;
            int i3 = i2;
            lock();
            try {
                long read = this.a.q.read();
                c(read);
                int i4 = this.b - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.f;
                int length = i3 & (atomicReferenceArray.length() - 1);
                ReferenceEntry referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    loadingValueReference = null;
                    if (referenceEntry2 == null) {
                        valueReference = null;
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() != i3 || key == null || !this.a.f.equivalent(k3, key)) {
                        referenceEntry2 = referenceEntry2.getNext();
                    } else {
                        ValueReference valueReference2 = referenceEntry2.getValueReference();
                        if (valueReference2.isLoading()) {
                            z = false;
                            valueReference = valueReference2;
                        } else {
                            V v = valueReference2.get();
                            if (v == null) {
                                weight = valueReference2.getWeight();
                                removalCause = RemovalCause.COLLECTED;
                            } else if (this.a.b(referenceEntry2, read)) {
                                weight = valueReference2.getWeight();
                                removalCause = RemovalCause.EXPIRED;
                            } else {
                                b(referenceEntry2, read);
                                this.n.recordHits(1);
                                unlock();
                                m();
                                return v;
                            }
                            a(key, i2, v, weight, removalCause);
                            this.l.remove(referenceEntry2);
                            this.m.remove(referenceEntry2);
                            this.b = i4;
                            valueReference = valueReference2;
                        }
                    }
                }
                z = true;
                if (z) {
                    loadingValueReference = new LoadingValueReference();
                    if (referenceEntry2 == null) {
                        referenceEntry2 = a(k3, i3, referenceEntry);
                        referenceEntry2.setValueReference(loadingValueReference);
                        atomicReferenceArray.set(length, referenceEntry2);
                    } else {
                        referenceEntry2.setValueReference(loadingValueReference);
                    }
                }
                if (!z) {
                    return a(referenceEntry2, k3, valueReference);
                }
                try {
                    synchronized (referenceEntry2) {
                        a2 = a(k3, i3, loadingValueReference, cacheLoader);
                    }
                    this.n.recordMisses(1);
                    return a2;
                } catch (Throwable th) {
                    this.n.recordMisses(1);
                    throw th;
                }
            } finally {
                unlock();
                m();
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void b() {
            if (this.a.m()) {
                c();
            }
            if (this.a.n()) {
                d();
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void b(long j2) {
            ReferenceEntry peek;
            ReferenceEntry peek2;
            h();
            do {
                peek = this.l.peek();
                if (peek == null || !this.a.b(peek, j2)) {
                    do {
                        peek2 = this.m.peek();
                        if (peek2 == null || !this.a.b(peek2, j2)) {
                            return;
                        }
                    } while (a(peek2, peek2.getHash(), RemovalCause.EXPIRED));
                    throw new AssertionError();
                }
            } while (a(peek, peek.getHash(), RemovalCause.EXPIRED));
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void b(ReferenceEntry<K, V> referenceEntry) {
            a(referenceEntry.getKey(), referenceEntry.getHash(), referenceEntry.getValueReference().get(), referenceEntry.getValueReference().getWeight(), RemovalCause.COLLECTED);
            this.l.remove(referenceEntry);
            this.m.remove(referenceEntry);
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void b(ReferenceEntry<K, V> referenceEntry, long j2) {
            if (this.a.i()) {
                referenceEntry.setAccessTime(j2);
            }
            this.m.add(referenceEntry);
        }

        /* access modifiers changed from: package-private */
        public boolean b(Object obj, int i2, Object obj2) {
            RemovalCause removalCause;
            lock();
            try {
                c(this.a.q.read());
                int i3 = this.b;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.f;
                boolean z = true;
                int length = (atomicReferenceArray.length() - 1) & i2;
                ReferenceEntry referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    if (referenceEntry2 == null) {
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() != i2 || key == null || !this.a.f.equivalent(obj, key)) {
                        referenceEntry2 = referenceEntry2.getNext();
                    } else {
                        ValueReference valueReference = referenceEntry2.getValueReference();
                        Object obj3 = valueReference.get();
                        if (this.a.g.equivalent(obj2, obj3)) {
                            removalCause = RemovalCause.EXPLICIT;
                        } else if (obj3 == null && valueReference.isActive()) {
                            removalCause = RemovalCause.COLLECTED;
                        }
                        this.d++;
                        atomicReferenceArray.set(length, a(referenceEntry, referenceEntry2, key, i2, obj3, valueReference, removalCause));
                        this.b--;
                        if (removalCause != RemovalCause.EXPLICIT) {
                            z = false;
                        }
                        return z;
                    }
                }
                unlock();
                m();
                return false;
            } finally {
                unlock();
                m();
            }
        }

        /* access modifiers changed from: package-private */
        public V c(ReferenceEntry<K, V> referenceEntry, long j2) {
            if (referenceEntry.getKey() == null) {
                a();
                return null;
            }
            V v = referenceEntry.getValueReference().get();
            if (v == null) {
                a();
                return null;
            } else if (!this.a.b(referenceEntry, j2)) {
                return v;
            } else {
                a(j2);
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void c() {
            int i2 = 0;
            do {
                Reference<? extends K> poll = this.h.poll();
                if (poll != null) {
                    this.a.a((ReferenceEntry) poll);
                    i2++;
                } else {
                    return;
                }
            } while (i2 != 16);
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void c(long j2) {
            d(j2);
        }

        /* access modifiers changed from: package-private */
        public boolean c(Object obj, int i2) {
            try {
                boolean z = false;
                if (this.b != 0) {
                    ReferenceEntry a2 = a(obj, i2, this.a.q.read());
                    if (a2 == null) {
                        return false;
                    }
                    if (a2.getValueReference().get() != null) {
                        z = true;
                    }
                    l();
                    return z;
                }
                l();
                return false;
            } finally {
                l();
            }
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public V d(Object obj, int i2) {
            RemovalCause removalCause;
            lock();
            try {
                c(this.a.q.read());
                int i3 = this.b;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.f;
                int length = (atomicReferenceArray.length() - 1) & i2;
                ReferenceEntry referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    if (referenceEntry2 == null) {
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() != i2 || key == null || !this.a.f.equivalent(obj, key)) {
                        referenceEntry2 = referenceEntry2.getNext();
                    } else {
                        ValueReference valueReference = referenceEntry2.getValueReference();
                        V v = valueReference.get();
                        if (v != null) {
                            removalCause = RemovalCause.EXPLICIT;
                        } else if (valueReference.isActive()) {
                            removalCause = RemovalCause.COLLECTED;
                        }
                        RemovalCause removalCause2 = removalCause;
                        this.d++;
                        atomicReferenceArray.set(length, a(referenceEntry, referenceEntry2, key, i2, v, valueReference, removalCause2));
                        this.b--;
                        return v;
                    }
                }
                unlock();
                m();
                return null;
            } finally {
                unlock();
                m();
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void d() {
            int i2 = 0;
            do {
                Reference<? extends V> poll = this.i.poll();
                if (poll != null) {
                    this.a.a((ValueReference) poll);
                    i2++;
                } else {
                    return;
                }
            } while (i2 != 16);
        }

        /* access modifiers changed from: package-private */
        public void d(long j2) {
            if (tryLock()) {
                try {
                    b();
                    b(j2);
                    this.k.set(0);
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void e() {
            if (this.a.m()) {
                f();
            }
            if (this.a.n()) {
                g();
            }
        }

        /* access modifiers changed from: package-private */
        public void f() {
            do {
            } while (this.h.poll() != null);
        }

        /* access modifiers changed from: package-private */
        public void g() {
            do {
            } while (this.i.poll() != null);
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void h() {
            while (true) {
                ReferenceEntry poll = this.j.poll();
                if (poll == null) {
                    return;
                }
                if (this.m.contains(poll)) {
                    this.m.add(poll);
                }
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public ReferenceEntry<K, V> i() {
            for (ReferenceEntry<K, V> referenceEntry : this.m) {
                if (referenceEntry.getValueReference().getWeight() > 0) {
                    return referenceEntry;
                }
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void j() {
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.f;
            int length = atomicReferenceArray.length();
            if (length < 1073741824) {
                int i2 = this.b;
                AtomicReferenceArray<ReferenceEntry<K, V>> a2 = a(length << 1);
                this.e = (a2.length() * 3) / 4;
                int length2 = a2.length() - 1;
                for (int i3 = 0; i3 < length; i3++) {
                    ReferenceEntry referenceEntry = atomicReferenceArray.get(i3);
                    if (referenceEntry != null) {
                        ReferenceEntry next = referenceEntry.getNext();
                        int hash = referenceEntry.getHash() & length2;
                        if (next == null) {
                            a2.set(hash, referenceEntry);
                        } else {
                            ReferenceEntry referenceEntry2 = referenceEntry;
                            while (next != null) {
                                int hash2 = next.getHash() & length2;
                                if (hash2 != hash) {
                                    referenceEntry2 = next;
                                    hash = hash2;
                                }
                                next = next.getNext();
                            }
                            a2.set(hash, referenceEntry2);
                            while (referenceEntry != referenceEntry2) {
                                int hash3 = referenceEntry.getHash() & length2;
                                ReferenceEntry a3 = a(referenceEntry, (ReferenceEntry) a2.get(hash3));
                                if (a3 != null) {
                                    a2.set(hash3, a3);
                                } else {
                                    b(referenceEntry);
                                    i2--;
                                }
                                referenceEntry = referenceEntry.getNext();
                            }
                        }
                    }
                }
                this.f = a2;
                this.b = i2;
            }
        }

        /* access modifiers changed from: package-private */
        public void k() {
            RemovalCause removalCause;
            if (this.b != 0) {
                lock();
                try {
                    c(this.a.q.read());
                    AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.f;
                    for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
                        for (ReferenceEntry referenceEntry = atomicReferenceArray.get(i2); referenceEntry != null; referenceEntry = referenceEntry.getNext()) {
                            if (referenceEntry.getValueReference().isActive()) {
                                Object key = referenceEntry.getKey();
                                Object obj = referenceEntry.getValueReference().get();
                                if (key != null) {
                                    if (obj != null) {
                                        removalCause = RemovalCause.EXPLICIT;
                                        a(key, referenceEntry.getHash(), obj, referenceEntry.getValueReference().getWeight(), removalCause);
                                    }
                                }
                                removalCause = RemovalCause.COLLECTED;
                                a(key, referenceEntry.getHash(), obj, referenceEntry.getValueReference().getWeight(), removalCause);
                            }
                        }
                    }
                    for (int i3 = 0; i3 < atomicReferenceArray.length(); i3++) {
                        atomicReferenceArray.set(i3, (Object) null);
                    }
                    e();
                    this.l.clear();
                    this.m.clear();
                    this.k.set(0);
                    this.d++;
                    this.b = 0;
                } finally {
                    unlock();
                    m();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void l() {
            if ((this.k.incrementAndGet() & 63) == 0) {
                n();
            }
        }

        /* access modifiers changed from: package-private */
        public void m() {
            o();
        }

        /* access modifiers changed from: package-private */
        public void n() {
            d(this.a.q.read());
            o();
        }

        /* access modifiers changed from: package-private */
        public void o() {
            if (!isHeldByCurrentThread()) {
                this.a.r();
            }
        }
    }

    static class SoftValueReference<K, V> extends SoftReference<V> implements ValueReference<K, V> {
        final ReferenceEntry<K, V> a;

        SoftValueReference(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            super(v, referenceQueue);
            this.a = referenceEntry;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return new SoftValueReference(referenceQueue, v, referenceEntry);
        }

        public ReferenceEntry<K, V> getEntry() {
            return this.a;
        }

        public int getWeight() {
            return 1;
        }

        public boolean isActive() {
            return true;
        }

        public boolean isLoading() {
            return false;
        }

        public void notifyNewValue(V v) {
        }

        public V waitForValue() {
            return get();
        }
    }

    enum Strength {
        STRONG {
            /* access modifiers changed from: package-private */
            public Equivalence<Object> a() {
                return Equivalence.equals();
            }

            /* access modifiers changed from: package-private */
            public <K, V> ValueReference<K, V> a(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v, int i) {
                return i == 1 ? new StrongValueReference(v) : new WeightedStrongValueReference(v, i);
            }
        },
        SOFT {
            /* access modifiers changed from: package-private */
            public Equivalence<Object> a() {
                return Equivalence.identity();
            }

            /* access modifiers changed from: package-private */
            public <K, V> ValueReference<K, V> a(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v, int i) {
                return i == 1 ? new SoftValueReference(segment.i, v, referenceEntry) : new WeightedSoftValueReference(segment.i, v, referenceEntry, i);
            }
        },
        WEAK {
            /* access modifiers changed from: package-private */
            public Equivalence<Object> a() {
                return Equivalence.identity();
            }

            /* access modifiers changed from: package-private */
            public <K, V> ValueReference<K, V> a(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v, int i) {
                return i == 1 ? new WeakValueReference(segment.i, v, referenceEntry) : new WeightedWeakValueReference(segment.i, v, referenceEntry, i);
            }
        };

        /* access modifiers changed from: package-private */
        public abstract Equivalence<Object> a();

        /* access modifiers changed from: package-private */
        public abstract <K, V> ValueReference<K, V> a(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v, int i);
    }

    static final class StrongAccessEntry<K, V> extends StrongEntry<K, V> {
        volatile long a = LongCompanionObject.MAX_VALUE;
        ReferenceEntry<K, V> b = LocalCache.p();
        ReferenceEntry<K, V> c = LocalCache.p();

        StrongAccessEntry(K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
            super(k, i, referenceEntry);
        }

        public long getAccessTime() {
            return this.a;
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.b;
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.c;
        }

        public void setAccessTime(long j) {
            this.a = j;
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.b = referenceEntry;
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.c = referenceEntry;
        }
    }

    static final class StrongAccessWriteEntry<K, V> extends StrongEntry<K, V> {
        volatile long a = LongCompanionObject.MAX_VALUE;
        ReferenceEntry<K, V> b = LocalCache.p();
        ReferenceEntry<K, V> c = LocalCache.p();
        volatile long d = LongCompanionObject.MAX_VALUE;
        ReferenceEntry<K, V> e = LocalCache.p();
        ReferenceEntry<K, V> f = LocalCache.p();

        StrongAccessWriteEntry(K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
            super(k, i, referenceEntry);
        }

        public long getAccessTime() {
            return this.a;
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.b;
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.e;
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.c;
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.f;
        }

        public long getWriteTime() {
            return this.d;
        }

        public void setAccessTime(long j) {
            this.a = j;
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.b = referenceEntry;
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.e = referenceEntry;
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.c = referenceEntry;
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f = referenceEntry;
        }

        public void setWriteTime(long j) {
            this.d = j;
        }
    }

    static class StrongEntry<K, V> extends AbstractReferenceEntry<K, V> {
        final K g;
        final int h;
        final ReferenceEntry<K, V> i;
        volatile ValueReference<K, V> j = LocalCache.o();

        StrongEntry(K k, int i2, @Nullable ReferenceEntry<K, V> referenceEntry) {
            this.g = k;
            this.h = i2;
            this.i = referenceEntry;
        }

        public int getHash() {
            return this.h;
        }

        public K getKey() {
            return this.g;
        }

        public ReferenceEntry<K, V> getNext() {
            return this.i;
        }

        public ValueReference<K, V> getValueReference() {
            return this.j;
        }

        public void setValueReference(ValueReference<K, V> valueReference) {
            this.j = valueReference;
        }
    }

    static class StrongValueReference<K, V> implements ValueReference<K, V> {
        final V a;

        StrongValueReference(V v) {
            this.a = v;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public V get() {
            return this.a;
        }

        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public int getWeight() {
            return 1;
        }

        public boolean isActive() {
            return true;
        }

        public boolean isLoading() {
            return false;
        }

        public void notifyNewValue(V v) {
        }

        public V waitForValue() {
            return get();
        }
    }

    static final class StrongWriteEntry<K, V> extends StrongEntry<K, V> {
        volatile long a = LongCompanionObject.MAX_VALUE;
        ReferenceEntry<K, V> b = LocalCache.p();
        ReferenceEntry<K, V> c = LocalCache.p();

        StrongWriteEntry(K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
            super(k, i, referenceEntry);
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.b;
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.c;
        }

        public long getWriteTime() {
            return this.a;
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.b = referenceEntry;
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.c = referenceEntry;
        }

        public void setWriteTime(long j) {
            this.a = j;
        }
    }

    final class ValueIterator extends LocalCache<K, V>.HashIterator<V> {
        ValueIterator() {
            super();
        }

        public V next() {
            return d().getValue();
        }
    }

    interface ValueReference<K, V> {
        ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, @Nullable V v, ReferenceEntry<K, V> referenceEntry);

        @Nullable
        V get();

        @Nullable
        ReferenceEntry<K, V> getEntry();

        int getWeight();

        boolean isActive();

        boolean isLoading();

        void notifyNewValue(@Nullable V v);

        V waitForValue();
    }

    final class Values extends AbstractCollection<V> {
        private final ConcurrentMap<?, ?> map;

        Values(ConcurrentMap<?, ?> concurrentMap) {
            this.map = concurrentMap;
        }

        public void clear() {
            this.map.clear();
        }

        public boolean contains(Object obj) {
            return this.map.containsValue(obj);
        }

        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        public int size() {
            return this.map.size();
        }

        public Object[] toArray() {
            return LocalCache.toArrayList(this).toArray();
        }

        public <E> E[] toArray(E[] eArr) {
            return LocalCache.toArrayList(this).toArray(eArr);
        }
    }

    static final class WeakAccessEntry<K, V> extends WeakEntry<K, V> {
        volatile long a = LongCompanionObject.MAX_VALUE;
        ReferenceEntry<K, V> b = LocalCache.p();
        ReferenceEntry<K, V> c = LocalCache.p();

        WeakAccessEntry(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k, i, referenceEntry);
        }

        public long getAccessTime() {
            return this.a;
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.b;
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.c;
        }

        public void setAccessTime(long j) {
            this.a = j;
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.b = referenceEntry;
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.c = referenceEntry;
        }
    }

    static final class WeakAccessWriteEntry<K, V> extends WeakEntry<K, V> {
        volatile long a = LongCompanionObject.MAX_VALUE;
        ReferenceEntry<K, V> b = LocalCache.p();
        ReferenceEntry<K, V> c = LocalCache.p();
        volatile long d = LongCompanionObject.MAX_VALUE;
        ReferenceEntry<K, V> e = LocalCache.p();
        ReferenceEntry<K, V> f = LocalCache.p();

        WeakAccessWriteEntry(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k, i, referenceEntry);
        }

        public long getAccessTime() {
            return this.a;
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.b;
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.e;
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.c;
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.f;
        }

        public long getWriteTime() {
            return this.d;
        }

        public void setAccessTime(long j) {
            this.a = j;
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.b = referenceEntry;
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.e = referenceEntry;
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.c = referenceEntry;
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.f = referenceEntry;
        }

        public void setWriteTime(long j) {
            this.d = j;
        }
    }

    static class WeakEntry<K, V> extends WeakReference<K> implements ReferenceEntry<K, V> {
        final int g;
        final ReferenceEntry<K, V> h;
        volatile ValueReference<K, V> i = LocalCache.o();

        WeakEntry(ReferenceQueue<K> referenceQueue, K k, int i2, @Nullable ReferenceEntry<K, V> referenceEntry) {
            super(k, referenceQueue);
            this.g = i2;
            this.h = referenceEntry;
        }

        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        public int getHash() {
            return this.g;
        }

        public K getKey() {
            return get();
        }

        public ReferenceEntry<K, V> getNext() {
            return this.h;
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        public ValueReference<K, V> getValueReference() {
            return this.i;
        }

        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        public void setAccessTime(long j) {
            throw new UnsupportedOperationException();
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setValueReference(ValueReference<K, V> valueReference) {
            this.i = valueReference;
        }

        public void setWriteTime(long j) {
            throw new UnsupportedOperationException();
        }
    }

    static class WeakValueReference<K, V> extends WeakReference<V> implements ValueReference<K, V> {
        final ReferenceEntry<K, V> a;

        WeakValueReference(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            super(v, referenceQueue);
            this.a = referenceEntry;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return new WeakValueReference(referenceQueue, v, referenceEntry);
        }

        public ReferenceEntry<K, V> getEntry() {
            return this.a;
        }

        public int getWeight() {
            return 1;
        }

        public boolean isActive() {
            return true;
        }

        public boolean isLoading() {
            return false;
        }

        public void notifyNewValue(V v) {
        }

        public V waitForValue() {
            return get();
        }
    }

    static final class WeakWriteEntry<K, V> extends WeakEntry<K, V> {
        volatile long a = LongCompanionObject.MAX_VALUE;
        ReferenceEntry<K, V> b = LocalCache.p();
        ReferenceEntry<K, V> c = LocalCache.p();

        WeakWriteEntry(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k, i, referenceEntry);
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.b;
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.c;
        }

        public long getWriteTime() {
            return this.a;
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.b = referenceEntry;
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.c = referenceEntry;
        }

        public void setWriteTime(long j) {
            this.a = j;
        }
    }

    static final class WeightedSoftValueReference<K, V> extends SoftValueReference<K, V> {
        final int b;

        WeightedSoftValueReference(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry, int i) {
            super(referenceQueue, v, referenceEntry);
            this.b = i;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return new WeightedSoftValueReference(referenceQueue, v, referenceEntry, this.b);
        }

        public int getWeight() {
            return this.b;
        }
    }

    static final class WeightedStrongValueReference<K, V> extends StrongValueReference<K, V> {
        final int b;

        WeightedStrongValueReference(V v, int i) {
            super(v);
            this.b = i;
        }

        public int getWeight() {
            return this.b;
        }
    }

    static final class WeightedWeakValueReference<K, V> extends WeakValueReference<K, V> {
        final int b;

        WeightedWeakValueReference(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry, int i) {
            super(referenceQueue, v, referenceEntry);
            this.b = i;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return new WeightedWeakValueReference(referenceQueue, v, referenceEntry, this.b);
        }

        public int getWeight() {
            return this.b;
        }
    }

    static final class WriteQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> a = new AbstractReferenceEntry<K, V>() {
            ReferenceEntry<K, V> a = this;
            ReferenceEntry<K, V> b = this;

            public ReferenceEntry<K, V> getNextInWriteQueue() {
                return this.a;
            }

            public ReferenceEntry<K, V> getPreviousInWriteQueue() {
                return this.b;
            }

            public long getWriteTime() {
                return LongCompanionObject.MAX_VALUE;
            }

            public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
                this.a = referenceEntry;
            }

            public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
                this.b = referenceEntry;
            }

            public void setWriteTime(long j) {
            }
        };

        WriteQueue() {
        }

        public void clear() {
            ReferenceEntry<K, V> nextInWriteQueue = this.a.getNextInWriteQueue();
            while (true) {
                ReferenceEntry<K, V> referenceEntry = this.a;
                if (nextInWriteQueue != referenceEntry) {
                    ReferenceEntry<K, V> nextInWriteQueue2 = nextInWriteQueue.getNextInWriteQueue();
                    LocalCache.c(nextInWriteQueue);
                    nextInWriteQueue = nextInWriteQueue2;
                } else {
                    referenceEntry.setNextInWriteQueue(referenceEntry);
                    ReferenceEntry<K, V> referenceEntry2 = this.a;
                    referenceEntry2.setPreviousInWriteQueue(referenceEntry2);
                    return;
                }
            }
        }

        public boolean contains(Object obj) {
            return ((ReferenceEntry) obj).getNextInWriteQueue() != NullEntry.INSTANCE;
        }

        public boolean isEmpty() {
            return this.a.getNextInWriteQueue() == this.a;
        }

        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) {
                /* access modifiers changed from: protected */
                public ReferenceEntry<K, V> a(ReferenceEntry<K, V> referenceEntry) {
                    ReferenceEntry<K, V> nextInWriteQueue = referenceEntry.getNextInWriteQueue();
                    if (nextInWriteQueue == WriteQueue.this.a) {
                        return null;
                    }
                    return nextInWriteQueue;
                }
            };
        }

        public boolean offer(ReferenceEntry<K, V> referenceEntry) {
            LocalCache.b(referenceEntry.getPreviousInWriteQueue(), referenceEntry.getNextInWriteQueue());
            LocalCache.b(this.a.getPreviousInWriteQueue(), referenceEntry);
            LocalCache.b(referenceEntry, this.a);
            return true;
        }

        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> nextInWriteQueue = this.a.getNextInWriteQueue();
            if (nextInWriteQueue == this.a) {
                return null;
            }
            return nextInWriteQueue;
        }

        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> nextInWriteQueue = this.a.getNextInWriteQueue();
            if (nextInWriteQueue == this.a) {
                return null;
            }
            remove(nextInWriteQueue);
            return nextInWriteQueue;
        }

        public boolean remove(Object obj) {
            ReferenceEntry referenceEntry = (ReferenceEntry) obj;
            ReferenceEntry previousInWriteQueue = referenceEntry.getPreviousInWriteQueue();
            ReferenceEntry nextInWriteQueue = referenceEntry.getNextInWriteQueue();
            LocalCache.b(previousInWriteQueue, nextInWriteQueue);
            LocalCache.c(referenceEntry);
            return nextInWriteQueue != NullEntry.INSTANCE;
        }

        public int size() {
            int i = 0;
            for (ReferenceEntry<K, V> nextInWriteQueue = this.a.getNextInWriteQueue(); nextInWriteQueue != this.a; nextInWriteQueue = nextInWriteQueue.getNextInWriteQueue()) {
                i++;
            }
            return i;
        }
    }

    final class WriteThroughEntry implements Map.Entry<K, V> {
        final K a;
        V b;

        WriteThroughEntry(K k, V v) {
            this.a = k;
            this.b = v;
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return this.a.equals(entry.getKey()) && this.b.equals(entry.getValue());
        }

        public K getKey() {
            return this.a;
        }

        public V getValue() {
            return this.b;
        }

        public int hashCode() {
            return this.a.hashCode() ^ this.b.hashCode();
        }

        public V setValue(V v) {
            V put = LocalCache.this.put(this.a, v);
            this.b = v;
            return put;
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    LocalCache(CacheBuilder<? super K, ? super V> cacheBuilder, @Nullable CacheLoader<? super K, V> cacheLoader) {
        this.e = Math.min(cacheBuilder.e(), 65536);
        this.h = cacheBuilder.h();
        this.i = cacheBuilder.i();
        this.f = cacheBuilder.b();
        this.g = cacheBuilder.c();
        this.j = cacheBuilder.f();
        this.k = cacheBuilder.g();
        this.l = cacheBuilder.k();
        this.m = cacheBuilder.j();
        this.n = cacheBuilder.l();
        this.p = cacheBuilder.m();
        this.o = this.p == CacheBuilder.NullListener.INSTANCE ? q() : new ConcurrentLinkedQueue<>();
        this.q = cacheBuilder.a(j());
        this.r = EntryFactory.a(this.h, l(), k());
        this.s = (AbstractCache.StatsCounter) cacheBuilder.n().get();
        this.t = cacheLoader;
        int min = Math.min(cacheBuilder.d(), Ints.MAX_POWER_OF_TWO);
        if (a() && !b()) {
            min = Math.min(min, (int) this.j);
        }
        int i2 = 0;
        int i3 = 1;
        int i4 = 1;
        int i5 = 0;
        while (i4 < this.e && (!a() || ((long) (i4 * 20)) <= this.j)) {
            i5++;
            i4 <<= 1;
        }
        this.c = 32 - i5;
        this.b = i4 - 1;
        this.d = c(i4);
        int i6 = min / i4;
        while (i3 < (i6 * i4 < min ? i6 + 1 : i6)) {
            i3 <<= 1;
        }
        if (a()) {
            long j2 = this.j;
            long j3 = (long) i4;
            long j4 = (j2 / j3) + 1;
            long j5 = j2 % j3;
            while (i2 < this.d.length) {
                if (((long) i2) == j5) {
                    j4--;
                }
                this.d[i2] = a(i3, j4, (AbstractCache.StatsCounter) cacheBuilder.n().get());
                i2++;
            }
            return;
        }
        while (true) {
            Segment<K, V>[] segmentArr = this.d;
            if (i2 < segmentArr.length) {
                segmentArr[i2] = a(i3, -1, (AbstractCache.StatsCounter) cacheBuilder.n().get());
                i2++;
            } else {
                return;
            }
        }
    }

    static int a(int i2) {
        int i3 = i2 + ((i2 << 15) ^ -12931);
        int i4 = i3 ^ (i3 >>> 10);
        int i5 = i4 + (i4 << 3);
        int i6 = i5 ^ (i5 >>> 6);
        int i7 = i6 + (i6 << 2) + (i6 << 14);
        return i7 ^ (i7 >>> 16);
    }

    static <K, V> void a(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
        referenceEntry.setNextInAccessQueue(referenceEntry2);
        referenceEntry2.setPreviousInAccessQueue(referenceEntry);
    }

    static <K, V> void b(ReferenceEntry<K, V> referenceEntry) {
        ReferenceEntry p2 = p();
        referenceEntry.setNextInAccessQueue(p2);
        referenceEntry.setPreviousInAccessQueue(p2);
    }

    static <K, V> void b(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
        referenceEntry.setNextInWriteQueue(referenceEntry2);
        referenceEntry2.setPreviousInWriteQueue(referenceEntry);
    }

    static <K, V> void c(ReferenceEntry<K, V> referenceEntry) {
        ReferenceEntry p2 = p();
        referenceEntry.setNextInWriteQueue(p2);
        referenceEntry.setPreviousInWriteQueue(p2);
    }

    static <K, V> ValueReference<K, V> o() {
        return u;
    }

    static <K, V> ReferenceEntry<K, V> p() {
        return NullEntry.INSTANCE;
    }

    static <E> Queue<E> q() {
        return v;
    }

    /* access modifiers changed from: private */
    public static <E> ArrayList<E> toArrayList(Collection<E> collection) {
        ArrayList<E> arrayList = new ArrayList<>(collection.size());
        Iterators.addAll(arrayList, collection.iterator());
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public int a(@Nullable Object obj) {
        return a(this.f.hash(obj));
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V> a(int i2, long j2, AbstractCache.StatsCounter statsCounter) {
        return new Segment(this, i2, j2, statsCounter);
    }

    /* access modifiers changed from: package-private */
    public ImmutableMap<K, V> a(Iterable<?> iterable) {
        LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
        int i2 = 0;
        int i3 = 0;
        for (Object next : iterable) {
            Object obj = get(next);
            if (obj == null) {
                i3++;
            } else {
                newLinkedHashMap.put(next, obj);
                i2++;
            }
        }
        this.s.recordHits(i2);
        this.s.recordMisses(i3);
        return ImmutableMap.copyOf(newLinkedHashMap);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public V a(ReferenceEntry<K, V> referenceEntry, long j2) {
        V v2;
        if (referenceEntry.getKey() == null || (v2 = referenceEntry.getValueReference().get()) == null || b(referenceEntry, j2)) {
            return null;
        }
        return v2;
    }

    /* access modifiers changed from: package-private */
    public V a(K k2, CacheLoader<? super K, V> cacheLoader) {
        int a2 = a(Preconditions.checkNotNull(k2));
        return b(a2).a(k2, a2, cacheLoader);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Map<K, V> a(Set<? extends K> set, CacheLoader<? super K, V> cacheLoader) {
        Preconditions.checkNotNull(cacheLoader);
        Preconditions.checkNotNull(set);
        Stopwatch createStarted = Stopwatch.createStarted();
        boolean z = false;
        try {
            Map<? super K, V> loadAll = cacheLoader.loadAll(set);
            if (loadAll != null) {
                createStarted.stop();
                for (Map.Entry next : loadAll.entrySet()) {
                    Object key = next.getKey();
                    Object value = next.getValue();
                    if (key == null || value == null) {
                        z = true;
                    } else {
                        put(key, value);
                    }
                }
                if (!z) {
                    this.s.recordLoadSuccess(createStarted.elapsed(TimeUnit.NANOSECONDS));
                    return loadAll;
                }
                this.s.recordLoadException(createStarted.elapsed(TimeUnit.NANOSECONDS));
                throw new CacheLoader.InvalidCacheLoadException(cacheLoader + " returned null keys or values from loadAll");
            }
            this.s.recordLoadException(createStarted.elapsed(TimeUnit.NANOSECONDS));
            throw new CacheLoader.InvalidCacheLoadException(cacheLoader + " returned null map from loadAll");
        } catch (CacheLoader.UnsupportedLoadingOperationException e2) {
            throw e2;
        } catch (InterruptedException e3) {
            Thread.currentThread().interrupt();
            throw new ExecutionException(e3);
        } catch (RuntimeException e4) {
            throw new UncheckedExecutionException(e4);
        } catch (Exception e5) {
            throw new ExecutionException(e5);
        } catch (Error e6) {
            throw new ExecutionError(e6);
        } catch (Throwable th) {
            th = th;
            z = true;
        }
        if (!z) {
            this.s.recordLoadException(createStarted.elapsed(TimeUnit.NANOSECONDS));
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    public void a(ReferenceEntry<K, V> referenceEntry) {
        int hash = referenceEntry.getHash();
        b(hash).a(referenceEntry, hash);
    }

    /* access modifiers changed from: package-private */
    public void a(ValueReference<K, V> valueReference) {
        ReferenceEntry<K, V> entry = valueReference.getEntry();
        int hash = entry.getHash();
        b(hash).a(entry.getKey(), hash, valueReference);
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.j >= 0;
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V> b(int i2) {
        return this.d[(i2 >>> this.c) & this.b];
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:21|22|(2:25|23)|38) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r8 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0073, code lost:
        if (r8.hasNext() != false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0075, code lost:
        r1 = r8.next();
        r3 = r3 - 1;
        r0.put(r1, a(r1, r7.t));
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x006b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.common.collect.ImmutableMap<K, V> b(java.lang.Iterable<? extends K> r8) {
        /*
            r7 = this;
            java.util.LinkedHashMap r0 = com.google.common.collect.Maps.newLinkedHashMap()
            java.util.LinkedHashSet r1 = com.google.common.collect.Sets.newLinkedHashSet()
            java.util.Iterator r8 = r8.iterator()
            r2 = 0
            r3 = 0
        L_0x000e:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L_0x0030
            java.lang.Object r4 = r8.next()
            java.lang.Object r5 = r7.get(r4)
            boolean r6 = r0.containsKey(r4)
            if (r6 != 0) goto L_0x000e
            r0.put(r4, r5)
            if (r5 != 0) goto L_0x002d
            int r3 = r3 + 1
            r1.add(r4)
            goto L_0x000e
        L_0x002d:
            int r2 = r2 + 1
            goto L_0x000e
        L_0x0030:
            boolean r8 = r1.isEmpty()     // Catch:{ all -> 0x0094 }
            if (r8 != 0) goto L_0x0085
            com.google.common.cache.CacheLoader<? super K, V> r8 = r7.t     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
            java.util.Map r8 = r7.a(r1, r8)     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
            java.util.Iterator r4 = r1.iterator()     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
        L_0x0040:
            boolean r5 = r4.hasNext()     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
            if (r5 == 0) goto L_0x0085
            java.lang.Object r5 = r4.next()     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
            java.lang.Object r6 = r8.get(r5)     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
            if (r6 == 0) goto L_0x0054
            r0.put(r5, r6)     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
            goto L_0x0040
        L_0x0054:
            com.google.common.cache.CacheLoader$InvalidCacheLoadException r8 = new com.google.common.cache.CacheLoader$InvalidCacheLoadException     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
            r4.<init>()     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
            java.lang.String r6 = "loadAll failed to return a value for "
            r4.append(r6)     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
            r4.append(r5)     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
            java.lang.String r4 = r4.toString()     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
            r8.<init>(r4)     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
            throw r8     // Catch:{ UnsupportedLoadingOperationException -> 0x006b }
        L_0x006b:
            java.util.Iterator r8 = r1.iterator()     // Catch:{ all -> 0x0094 }
        L_0x006f:
            boolean r1 = r8.hasNext()     // Catch:{ all -> 0x0094 }
            if (r1 == 0) goto L_0x0085
            java.lang.Object r1 = r8.next()     // Catch:{ all -> 0x0094 }
            int r3 = r3 + -1
            com.google.common.cache.CacheLoader<? super K, V> r4 = r7.t     // Catch:{ all -> 0x0094 }
            java.lang.Object r4 = r7.a(r1, r4)     // Catch:{ all -> 0x0094 }
            r0.put(r1, r4)     // Catch:{ all -> 0x0094 }
            goto L_0x006f
        L_0x0085:
            com.google.common.collect.ImmutableMap r8 = com.google.common.collect.ImmutableMap.copyOf(r0)     // Catch:{ all -> 0x0094 }
            com.google.common.cache.AbstractCache$StatsCounter r0 = r7.s
            r0.recordHits(r2)
            com.google.common.cache.AbstractCache$StatsCounter r0 = r7.s
            r0.recordMisses(r3)
            return r8
        L_0x0094:
            r8 = move-exception
            com.google.common.cache.AbstractCache$StatsCounter r0 = r7.s
            r0.recordHits(r2)
            com.google.common.cache.AbstractCache$StatsCounter r0 = r7.s
            r0.recordMisses(r3)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.b(java.lang.Iterable):com.google.common.collect.ImmutableMap");
    }

    /* access modifiers changed from: package-private */
    public V b(K k2) {
        return a(k2, this.t);
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.k != CacheBuilder.OneWeigher.INSTANCE;
    }

    /* access modifiers changed from: package-private */
    public boolean b(ReferenceEntry<K, V> referenceEntry, long j2) {
        Preconditions.checkNotNull(referenceEntry);
        if (!d() || j2 - referenceEntry.getAccessTime() < this.l) {
            return c() && j2 - referenceEntry.getWriteTime() >= this.m;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void c(Iterable<?> iterable) {
        for (Object remove : iterable) {
            remove(remove);
        }
    }

    /* access modifiers changed from: package-private */
    public void c(K k2) {
        int a2 = a(Preconditions.checkNotNull(k2));
        b(a2).a(k2, a2, this.t, false);
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.m > 0;
    }

    /* access modifiers changed from: package-private */
    public final Segment<K, V>[] c(int i2) {
        return new Segment[i2];
    }

    public void cleanUp() {
        for (Segment<K, V> n2 : this.d) {
            n2.n();
        }
    }

    public void clear() {
        for (Segment<K, V> k2 : this.d) {
            k2.k();
        }
    }

    public boolean containsKey(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        int a2 = a(obj);
        return b(a2).c(obj, a2);
    }

    public boolean containsValue(@Nullable Object obj) {
        long j2;
        Object obj2 = obj;
        if (obj2 == null) {
            return false;
        }
        long read = this.q.read();
        Segment<K, V>[] segmentArr = this.d;
        long j3 = -1;
        int i2 = 0;
        while (i2 < 3) {
            int length = segmentArr.length;
            long j4 = 0;
            int i3 = 0;
            while (i3 < length) {
                Segment<K, V> segment = segmentArr[i3];
                int i4 = segment.b;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = segment.f;
                for (int i5 = 0; i5 < atomicReferenceArray.length(); i5++) {
                    ReferenceEntry referenceEntry = atomicReferenceArray.get(i5);
                    while (referenceEntry != null) {
                        Segment<K, V>[] segmentArr2 = segmentArr;
                        V c2 = segment.c((ReferenceEntry<K, V>) referenceEntry, read);
                        if (c2 != null) {
                            j2 = read;
                            if (this.g.equivalent(obj2, c2)) {
                                return true;
                            }
                        } else {
                            j2 = read;
                        }
                        referenceEntry = referenceEntry.getNext();
                        segmentArr = segmentArr2;
                        read = j2;
                    }
                    long j5 = read;
                    Segment<K, V>[] segmentArr3 = segmentArr;
                }
                Segment<K, V>[] segmentArr4 = segmentArr;
                j4 += (long) segment.d;
                i3++;
                read = read;
            }
            long j6 = read;
            Segment<K, V>[] segmentArr5 = segmentArr;
            if (j4 == j3) {
                return false;
            }
            i2++;
            j3 = j4;
            segmentArr = segmentArr5;
            read = j6;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.l > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.n > 0;
    }

    @GwtIncompatible
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.y;
        if (set != null) {
            return set;
        }
        EntrySet entrySet = new EntrySet(this);
        this.y = entrySet;
        return entrySet;
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return d() || a();
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return c();
    }

    @Nullable
    public V get(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        int a2 = a(obj);
        return b(a2).b(obj, a2);
    }

    @Nullable
    public V getIfPresent(Object obj) {
        int a2 = a(Preconditions.checkNotNull(obj));
        V b2 = b(a2).b(obj, a2);
        if (b2 == null) {
            this.s.recordMisses(1);
        } else {
            this.s.recordHits(1);
        }
        return b2;
    }

    @Nullable
    public V getOrDefault(@Nullable Object obj, @Nullable V v2) {
        V v3 = get(obj);
        return v3 != null ? v3 : v2;
    }

    /* access modifiers changed from: package-private */
    public boolean h() {
        return c() || e();
    }

    /* access modifiers changed from: package-private */
    public boolean i() {
        return d();
    }

    public boolean isEmpty() {
        Segment<K, V>[] segmentArr = this.d;
        long j2 = 0;
        for (int i2 = 0; i2 < segmentArr.length; i2++) {
            if (segmentArr[i2].b != 0) {
                return false;
            }
            j2 += (long) segmentArr[i2].d;
        }
        if (j2 == 0) {
            return true;
        }
        for (int i3 = 0; i3 < segmentArr.length; i3++) {
            if (segmentArr[i3].b != 0) {
                return false;
            }
            j2 -= (long) segmentArr[i3].d;
        }
        return j2 == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean j() {
        return h() || i();
    }

    /* access modifiers changed from: package-private */
    public boolean k() {
        return g() || h();
    }

    public Set<K> keySet() {
        Set<K> set = this.w;
        if (set != null) {
            return set;
        }
        KeySet keySet = new KeySet(this);
        this.w = keySet;
        return keySet;
    }

    /* access modifiers changed from: package-private */
    public boolean l() {
        return f() || i();
    }

    /* access modifiers changed from: package-private */
    public boolean m() {
        return this.h != Strength.STRONG;
    }

    /* access modifiers changed from: package-private */
    public boolean n() {
        return this.i != Strength.STRONG;
    }

    public V put(K k2, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        int a2 = a((Object) k2);
        return b(a2).a(k2, a2, v2, false);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    public V putIfAbsent(K k2, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        int a2 = a((Object) k2);
        return b(a2).a(k2, a2, v2, true);
    }

    /* access modifiers changed from: package-private */
    public void r() {
        while (true) {
            RemovalNotification poll = this.o.poll();
            if (poll != null) {
                try {
                    this.p.onRemoval(poll);
                } catch (Throwable th) {
                    a.log(Level.WARNING, "Exception thrown by removal listener", th);
                }
            } else {
                return;
            }
        }
    }

    public V remove(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        int a2 = a(obj);
        return b(a2).d(obj, a2);
    }

    public boolean remove(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int a2 = a(obj);
        return b(a2).b(obj, a2, obj2);
    }

    public V replace(K k2, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        int a2 = a((Object) k2);
        return b(a2).a(k2, a2, v2);
    }

    public boolean replace(K k2, @Nullable V v2, V v3) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v3);
        if (v2 == null) {
            return false;
        }
        int a2 = a((Object) k2);
        return b(a2).a(k2, a2, v2, v3);
    }

    /* access modifiers changed from: package-private */
    public long s() {
        Segment<K, V>[] segmentArr = this.d;
        long j2 = 0;
        for (Segment<K, V> segment : segmentArr) {
            j2 += (long) Math.max(0, segment.b);
        }
        return j2;
    }

    public int size() {
        return Ints.saturatedCast(s());
    }

    public Collection<V> values() {
        Collection<V> collection = this.x;
        if (collection != null) {
            return collection;
        }
        Values values = new Values(this);
        this.x = values;
        return values;
    }
}
