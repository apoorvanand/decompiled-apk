package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMakerInternalMap.InternalEntry;
import com.google.common.collect.MapMakerInternalMap.Segment;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@GwtIncompatible
class MapMakerInternalMap<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> extends AbstractMap<K, V> implements Serializable, ConcurrentMap<K, V> {
    static final WeakValueReference<Object, Object, DummyInternalEntry> g = new WeakValueReference<Object, Object, DummyInternalEntry>() {
        public void clear() {
        }

        public WeakValueReference<Object, Object, DummyInternalEntry> copyFor(ReferenceQueue<Object> referenceQueue, DummyInternalEntry dummyInternalEntry) {
            return this;
        }

        public Object get() {
            return null;
        }

        public DummyInternalEntry getEntry() {
            return null;
        }
    };
    private static final long serialVersionUID = 5;
    final transient int a;
    final transient int b;
    final transient Segment<K, V, E, S>[] c;
    final int d;
    final Equivalence<Object> e;
    final transient InternalEntryHelper<K, V, E, S> f;
    transient Set<K> h;
    transient Collection<V> i;
    transient Set<Map.Entry<K, V>> j;

    static abstract class AbstractSerializationProxy<K, V> extends ForwardingConcurrentMap<K, V> implements Serializable {
        private static final long serialVersionUID = 3;
        final Strength a;
        final Strength b;
        final Equivalence<Object> c;
        final Equivalence<Object> d;
        final int e;
        transient ConcurrentMap<K, V> f;

        AbstractSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i, ConcurrentMap<K, V> concurrentMap) {
            this.a = strength;
            this.b = strength2;
            this.c = equivalence;
            this.d = equivalence2;
            this.e = i;
            this.f = concurrentMap;
        }

        /* access modifiers changed from: package-private */
        public MapMaker a(ObjectInputStream objectInputStream) {
            return new MapMaker().initialCapacity(objectInputStream.readInt()).a(this.a).b(this.b).a(this.c).concurrencyLevel(this.e);
        }

        /* access modifiers changed from: package-private */
        public void a(ObjectOutputStream objectOutputStream) {
            objectOutputStream.writeInt(this.f.size());
            for (Map.Entry entry : this.f.entrySet()) {
                objectOutputStream.writeObject(entry.getKey());
                objectOutputStream.writeObject(entry.getValue());
            }
            objectOutputStream.writeObject((Object) null);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public ConcurrentMap<K, V> delegate() {
            return this.f;
        }

        /* access modifiers changed from: package-private */
        public void b(ObjectInputStream objectInputStream) {
            while (true) {
                Object readObject = objectInputStream.readObject();
                if (readObject != null) {
                    this.f.put(readObject, objectInputStream.readObject());
                } else {
                    return;
                }
            }
        }
    }

    static abstract class AbstractStrongKeyEntry<K, V, E extends InternalEntry<K, V, E>> implements InternalEntry<K, V, E> {
        final K a;
        final int b;
        final E c;

        AbstractStrongKeyEntry(K k, int i, @Nullable E e) {
            this.a = k;
            this.b = i;
            this.c = e;
        }

        public int getHash() {
            return this.b;
        }

        public K getKey() {
            return this.a;
        }

        public E getNext() {
            return this.c;
        }
    }

    static abstract class AbstractWeakKeyEntry<K, V, E extends InternalEntry<K, V, E>> extends WeakReference<K> implements InternalEntry<K, V, E> {
        final int a;
        final E b;

        AbstractWeakKeyEntry(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable E e) {
            super(k, referenceQueue);
            this.a = i;
            this.b = e;
        }

        public int getHash() {
            return this.a;
        }

        public K getKey() {
            return get();
        }

        public E getNext() {
            return this.b;
        }
    }

    static final class CleanupMapTask implements Runnable {
        final WeakReference<MapMakerInternalMap<?, ?, ?, ?>> a;

        public CleanupMapTask(MapMakerInternalMap<?, ?, ?, ?> mapMakerInternalMap) {
            this.a = new WeakReference<>(mapMakerInternalMap);
        }

        public void run() {
            MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) this.a.get();
            if (mapMakerInternalMap != null) {
                for (Segment<K, V, E, S> i : mapMakerInternalMap.c) {
                    i.i();
                }
                return;
            }
            throw new CancellationException();
        }
    }

    static final class DummyInternalEntry implements InternalEntry<Object, Object, DummyInternalEntry> {
        private DummyInternalEntry() {
            throw new AssertionError();
        }

        public int getHash() {
            throw new AssertionError();
        }

        public Object getKey() {
            throw new AssertionError();
        }

        public DummyInternalEntry getNext() {
            throw new AssertionError();
        }

        public Object getValue() {
            throw new AssertionError();
        }
    }

    final class EntryIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<Map.Entry<K, V>> {
        EntryIterator() {
            super();
        }

        public Map.Entry<K, V> next() {
            return d();
        }
    }

    final class EntrySet extends SafeToArraySet<Map.Entry<K, V>> {
        EntrySet() {
            super();
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
            r0 = r3.a.get((r0 = r4.getKey()));
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
                com.google.common.collect.MapMakerInternalMap r2 = com.google.common.collect.MapMakerInternalMap.this
                java.lang.Object r0 = r2.get(r0)
                if (r0 == 0) goto L_0x0028
                com.google.common.collect.MapMakerInternalMap r2 = com.google.common.collect.MapMakerInternalMap.this
                com.google.common.base.Equivalence r2 = r2.b()
                java.lang.Object r4 = r4.getValue()
                boolean r4 = r2.equivalent(r4, r0)
                if (r4 == 0) goto L_0x0028
                r1 = 1
            L_0x0028:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.EntrySet.contains(java.lang.Object):boolean");
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
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
                com.google.common.collect.MapMakerInternalMap r2 = com.google.common.collect.MapMakerInternalMap.this
                java.lang.Object r4 = r4.getValue()
                boolean r4 = r2.remove(r0, r4)
                if (r4 == 0) goto L_0x001b
                r1 = 1
            L_0x001b:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.EntrySet.remove(java.lang.Object):boolean");
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }
    }

    abstract class HashIterator<T> implements Iterator<T> {
        int b;
        int c = -1;
        Segment<K, V, E, S> d;
        AtomicReferenceArray<E> e;
        E f;
        MapMakerInternalMap<K, V, E, S>.WriteThroughEntry g;
        MapMakerInternalMap<K, V, E, S>.WriteThroughEntry h;

        HashIterator() {
            this.b = MapMakerInternalMap.this.c.length - 1;
            a();
        }

        /* access modifiers changed from: package-private */
        public final void a() {
            this.g = null;
            if (!b() && !c()) {
                while (this.b >= 0) {
                    Segment<K, V, E, S>[] segmentArr = MapMakerInternalMap.this.c;
                    int i2 = this.b;
                    this.b = i2 - 1;
                    this.d = segmentArr[i2];
                    if (this.d.b != 0) {
                        this.e = this.d.e;
                        this.c = this.e.length() - 1;
                        if (c()) {
                            return;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(E e2) {
            boolean z;
            try {
                Object key = e2.getKey();
                Object b2 = MapMakerInternalMap.this.b(e2);
                if (b2 != null) {
                    this.g = new WriteThroughEntry(key, b2);
                    z = true;
                } else {
                    z = false;
                }
                return z;
            } finally {
                this.d.g();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            E e2 = this.f;
            if (e2 == null) {
                return false;
            }
            while (true) {
                this.f = e2.getNext();
                E e3 = this.f;
                if (e3 == null) {
                    return false;
                }
                if (a(e3)) {
                    return true;
                }
                e2 = this.f;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean c() {
            while (true) {
                int i2 = this.c;
                if (i2 < 0) {
                    return false;
                }
                AtomicReferenceArray<E> atomicReferenceArray = this.e;
                this.c = i2 - 1;
                E e2 = (InternalEntry) atomicReferenceArray.get(i2);
                this.f = e2;
                if (e2 != null && (a(this.f) || b())) {
                    return true;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public MapMakerInternalMap<K, V, E, S>.WriteThroughEntry d() {
            MapMakerInternalMap<K, V, E, S>.WriteThroughEntry writeThroughEntry = this.g;
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
            CollectPreconditions.a(this.h != null);
            MapMakerInternalMap.this.remove(this.h.getKey());
            this.h = null;
        }
    }

    interface InternalEntry<K, V, E extends InternalEntry<K, V, E>> {
        int getHash();

        K getKey();

        E getNext();

        V getValue();
    }

    interface InternalEntryHelper<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> {
        E copy(S s, E e, @Nullable E e2);

        Strength keyStrength();

        E newEntry(S s, K k, int i, @Nullable E e);

        S newSegment(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i, int i2);

        void setValue(S s, E e, V v);

        Strength valueStrength();
    }

    final class KeyIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<K> {
        KeyIterator() {
            super();
        }

        public K next() {
            return d().getKey();
        }
    }

    final class KeySet extends SafeToArraySet<K> {
        KeySet() {
            super();
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        public boolean contains(Object obj) {
            return MapMakerInternalMap.this.containsKey(obj);
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        public boolean remove(Object obj) {
            return MapMakerInternalMap.this.remove(obj) != null;
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }
    }

    private static abstract class SafeToArraySet<E> extends AbstractSet<E> {
        private SafeToArraySet() {
        }

        public Object[] toArray() {
            return MapMakerInternalMap.toArrayList(this).toArray();
        }

        public <E> E[] toArray(E[] eArr) {
            return MapMakerInternalMap.toArrayList(this).toArray(eArr);
        }
    }

    static abstract class Segment<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> extends ReentrantLock {
        @Weak
        final MapMakerInternalMap<K, V, E, S> a;
        volatile int b;
        int c;
        int d;
        volatile AtomicReferenceArray<E> e;
        final int f;
        final AtomicInteger g = new AtomicInteger();

        Segment(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i, int i2) {
            this.a = mapMakerInternalMap;
            this.f = i2;
            a(a(i));
        }

        static <K, V, E extends InternalEntry<K, V, E>> boolean a(E e2) {
            return e2.getValue() == null;
        }

        /* access modifiers changed from: package-private */
        public E a(E e2, E e3) {
            return this.a.f.copy(a(), e2, e3);
        }

        /* access modifiers changed from: package-private */
        public E a(Object obj, int i) {
            if (this.b == 0) {
                return null;
            }
            for (E b2 = b(i); b2 != null; b2 = b2.getNext()) {
                if (b2.getHash() == i) {
                    Object key = b2.getKey();
                    if (key == null) {
                        d();
                    } else if (this.a.e.equivalent(obj, key)) {
                        return b2;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public abstract S a();

        /* access modifiers changed from: package-private */
        public V a(K k, int i, V v) {
            lock();
            try {
                h();
                AtomicReferenceArray<E> atomicReferenceArray = this.e;
                int length = (atomicReferenceArray.length() - 1) & i;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i || key == null || !this.a.e.equivalent(k, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else {
                        V value = internalEntry2.getValue();
                        if (value == null) {
                            if (a(internalEntry2)) {
                                int i2 = this.b;
                                this.c++;
                                atomicReferenceArray.set(length, b(internalEntry, internalEntry2));
                                this.b--;
                            }
                            return null;
                        }
                        this.c++;
                        a(internalEntry2, v);
                        unlock();
                        return value;
                    }
                }
                unlock();
                return null;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public V a(K k, int i, V v, boolean z) {
            lock();
            try {
                h();
                int i2 = this.b + 1;
                if (i2 > this.d) {
                    e();
                    i2 = this.b + 1;
                }
                AtomicReferenceArray<E> atomicReferenceArray = this.e;
                int length = (atomicReferenceArray.length() - 1) & i;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i || key == null || !this.a.e.equivalent(k, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else {
                        V value = internalEntry2.getValue();
                        if (value == null) {
                            this.c++;
                            a(internalEntry2, v);
                            this.b = this.b;
                            return null;
                        } else if (z) {
                            unlock();
                            return value;
                        } else {
                            this.c++;
                            a(internalEntry2, v);
                            unlock();
                            return value;
                        }
                    }
                }
                this.c++;
                E newEntry = this.a.f.newEntry(a(), k, i, internalEntry);
                a(newEntry, v);
                atomicReferenceArray.set(length, newEntry);
                this.b = i2;
                unlock();
                return null;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public AtomicReferenceArray<E> a(int i) {
            return new AtomicReferenceArray<>(i);
        }

        /* access modifiers changed from: package-private */
        public void a(E e2, V v) {
            this.a.f.setValue(a(), e2, v);
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void a(ReferenceQueue<K> referenceQueue) {
            int i = 0;
            do {
                Reference<? extends K> poll = referenceQueue.poll();
                if (poll != null) {
                    this.a.a((InternalEntry) poll);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        public void a(AtomicReferenceArray<E> atomicReferenceArray) {
            this.d = (atomicReferenceArray.length() * 3) / 4;
            int i = this.d;
            if (i == this.f) {
                this.d = i + 1;
            }
            this.e = atomicReferenceArray;
        }

        /* access modifiers changed from: package-private */
        @CanIgnoreReturnValue
        public boolean a(E e2, int i) {
            lock();
            try {
                int i2 = this.b;
                AtomicReferenceArray<E> atomicReferenceArray = this.e;
                int length = i & (atomicReferenceArray.length() - 1);
                E e3 = (InternalEntry) atomicReferenceArray.get(length);
                for (E e4 = e3; e4 != null; e4 = e4.getNext()) {
                    if (e4 == e2) {
                        this.c++;
                        atomicReferenceArray.set(length, b(e3, e4));
                        this.b--;
                        return true;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        @CanIgnoreReturnValue
        public boolean a(K k, int i, WeakValueReference<K, V, E> weakValueReference) {
            lock();
            try {
                int i2 = this.b;
                AtomicReferenceArray<E> atomicReferenceArray = this.e;
                int length = (atomicReferenceArray.length() - 1) & i;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i || key == null || !this.a.e.equivalent(k, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else if (((WeakValueEntry) internalEntry2).getValueReference() == weakValueReference) {
                        this.c++;
                        atomicReferenceArray.set(length, b(internalEntry, internalEntry2));
                        this.b--;
                        return true;
                    } else {
                        unlock();
                        return false;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(K k, int i, V v, V v2) {
            lock();
            try {
                h();
                AtomicReferenceArray<E> atomicReferenceArray = this.e;
                int length = (atomicReferenceArray.length() - 1) & i;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i || key == null || !this.a.e.equivalent(k, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else {
                        Object value = internalEntry2.getValue();
                        if (value == null) {
                            if (a(internalEntry2)) {
                                int i2 = this.b;
                                this.c++;
                                atomicReferenceArray.set(length, b(internalEntry, internalEntry2));
                                this.b--;
                            }
                            return false;
                        } else if (this.a.b().equivalent(v, value)) {
                            this.c++;
                            a(internalEntry2, v2);
                            unlock();
                            return true;
                        } else {
                            unlock();
                            return false;
                        }
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public E b(int i) {
            AtomicReferenceArray<E> atomicReferenceArray = this.e;
            return (InternalEntry) atomicReferenceArray.get(i & (atomicReferenceArray.length() - 1));
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public E b(E e2, E e3) {
            int i = this.b;
            E next = e3.getNext();
            while (e2 != e3) {
                E a2 = a(e2, next);
                if (a2 != null) {
                    next = a2;
                } else {
                    i--;
                }
                e2 = e2.getNext();
            }
            this.b = i;
            return next;
        }

        /* access modifiers changed from: package-private */
        public E b(Object obj, int i) {
            return a(obj, i);
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public V b(E e2) {
            if (e2.getKey() == null) {
                d();
                return null;
            }
            V value = e2.getValue();
            if (value != null) {
                return value;
            }
            d();
            return null;
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void b() {
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void b(ReferenceQueue<V> referenceQueue) {
            int i = 0;
            do {
                Reference<? extends V> poll = referenceQueue.poll();
                if (poll != null) {
                    this.a.a((WeakValueReference) poll);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        public boolean b(Object obj, int i, Object obj2) {
            lock();
            try {
                h();
                int i2 = this.b;
                AtomicReferenceArray<E> atomicReferenceArray = this.e;
                int length = (atomicReferenceArray.length() - 1) & i;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (true) {
                    boolean z = false;
                    if (internalEntry2 != null) {
                        Object key = internalEntry2.getKey();
                        if (internalEntry2.getHash() != i || key == null || !this.a.e.equivalent(obj, key)) {
                            internalEntry2 = internalEntry2.getNext();
                        } else {
                            if (this.a.b().equivalent(obj2, internalEntry2.getValue())) {
                                z = true;
                            } else if (!a(internalEntry2)) {
                                unlock();
                                return false;
                            }
                            this.c++;
                            atomicReferenceArray.set(length, b(internalEntry, internalEntry2));
                            this.b--;
                            return z;
                        }
                    } else {
                        unlock();
                        return false;
                    }
                }
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public V c(Object obj, int i) {
            try {
                InternalEntry b2 = b(obj, i);
                if (b2 == null) {
                    return null;
                }
                V value = b2.getValue();
                if (value == null) {
                    d();
                }
                g();
                return value;
            } finally {
                g();
            }
        }

        /* access modifiers changed from: package-private */
        public void c() {
        }

        /* access modifiers changed from: package-private */
        public <T> void c(ReferenceQueue<T> referenceQueue) {
            do {
            } while (referenceQueue.poll() != null);
        }

        /* access modifiers changed from: package-private */
        public void d() {
            if (tryLock()) {
                try {
                    b();
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean d(Object obj, int i) {
            try {
                boolean z = false;
                if (this.b != 0) {
                    InternalEntry b2 = b(obj, i);
                    if (!(b2 == null || b2.getValue() == null)) {
                        z = true;
                    }
                    return z;
                }
                g();
                return false;
            } finally {
                g();
            }
        }

        /* access modifiers changed from: package-private */
        @CanIgnoreReturnValue
        public V e(Object obj, int i) {
            lock();
            try {
                h();
                int i2 = this.b;
                AtomicReferenceArray<E> atomicReferenceArray = this.e;
                int length = (atomicReferenceArray.length() - 1) & i;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i || key == null || !this.a.e.equivalent(obj, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else {
                        V value = internalEntry2.getValue();
                        if (value == null) {
                            if (!a(internalEntry2)) {
                                unlock();
                                return null;
                            }
                        }
                        this.c++;
                        atomicReferenceArray.set(length, b(internalEntry, internalEntry2));
                        this.b--;
                        return value;
                    }
                }
                unlock();
                return null;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void e() {
            AtomicReferenceArray<E> atomicReferenceArray = this.e;
            int length = atomicReferenceArray.length();
            if (length < 1073741824) {
                int i = this.b;
                AtomicReferenceArray<E> a2 = a(length << 1);
                this.d = (a2.length() * 3) / 4;
                int length2 = a2.length() - 1;
                for (int i2 = 0; i2 < length; i2++) {
                    InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(i2);
                    if (internalEntry != null) {
                        InternalEntry next = internalEntry.getNext();
                        int hash = internalEntry.getHash() & length2;
                        if (next == null) {
                            a2.set(hash, internalEntry);
                        } else {
                            InternalEntry internalEntry2 = internalEntry;
                            while (next != null) {
                                int hash2 = next.getHash() & length2;
                                if (hash2 != hash) {
                                    internalEntry2 = next;
                                    hash = hash2;
                                }
                                next = next.getNext();
                            }
                            a2.set(hash, internalEntry2);
                            while (internalEntry != internalEntry2) {
                                int hash3 = internalEntry.getHash() & length2;
                                InternalEntry a3 = a(internalEntry, (InternalEntry) a2.get(hash3));
                                if (a3 != null) {
                                    a2.set(hash3, a3);
                                } else {
                                    i--;
                                }
                                internalEntry = internalEntry.getNext();
                            }
                        }
                    }
                }
                this.e = a2;
                this.b = i;
            }
        }

        /* access modifiers changed from: package-private */
        public void f() {
            if (this.b != 0) {
                lock();
                try {
                    AtomicReferenceArray<E> atomicReferenceArray = this.e;
                    for (int i = 0; i < atomicReferenceArray.length(); i++) {
                        atomicReferenceArray.set(i, (Object) null);
                    }
                    c();
                    this.g.set(0);
                    this.c++;
                    this.b = 0;
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void g() {
            if ((this.g.incrementAndGet() & 63) == 0) {
                i();
            }
        }

        /* access modifiers changed from: package-private */
        public WeakValueReference<K, V, E> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("this")
        public void h() {
            j();
        }

        /* access modifiers changed from: package-private */
        public void i() {
            j();
        }

        /* access modifiers changed from: package-private */
        public void j() {
            if (tryLock()) {
                try {
                    b();
                    this.g.set(0);
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public WeakValueReference<K, V, E> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            throw new AssertionError();
        }
    }

    private static final class SerializationProxy<K, V> extends AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 3;

        SerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i, ConcurrentMap<K, V> concurrentMap) {
            super(strength, strength2, equivalence, equivalence2, i, concurrentMap);
        }

        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            this.f = a(objectInputStream).makeMap();
            b(objectInputStream);
        }

        private Object readResolve() {
            return this.f;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) {
            objectOutputStream.defaultWriteObject();
            a(objectOutputStream);
        }
    }

    enum Strength {
        STRONG {
            /* access modifiers changed from: package-private */
            public Equivalence<Object> a() {
                return Equivalence.equals();
            }
        },
        WEAK {
            /* access modifiers changed from: package-private */
            public Equivalence<Object> a() {
                return Equivalence.identity();
            }
        };

        /* access modifiers changed from: package-private */
        public abstract Equivalence<Object> a();
    }

    static final class StrongKeyStrongValueEntry<K, V> extends AbstractStrongKeyEntry<K, V, StrongKeyStrongValueEntry<K, V>> implements StrongValueEntry<K, V, StrongKeyStrongValueEntry<K, V>> {
        @Nullable
        private volatile V value = null;

        static final class Helper<K, V> implements InternalEntryHelper<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K, V> Helper<K, V> a() {
                return INSTANCE;
            }

            public StrongKeyStrongValueEntry<K, V> copy(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry, @Nullable StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry2) {
                return strongKeyStrongValueEntry.a(strongKeyStrongValueEntry2);
            }

            public Strength keyStrength() {
                return Strength.STRONG;
            }

            public StrongKeyStrongValueEntry<K, V> newEntry(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, K k, int i, @Nullable StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry) {
                return new StrongKeyStrongValueEntry<>(k, i, strongKeyStrongValueEntry);
            }

            public StrongKeyStrongValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
                return new StrongKeyStrongValueSegment<>(mapMakerInternalMap, i, i2);
            }

            public void setValue(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry, V v) {
                strongKeyStrongValueEntry.a(v);
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }
        }

        StrongKeyStrongValueEntry(K k, int i, @Nullable StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry) {
            super(k, i, strongKeyStrongValueEntry);
        }

        /* access modifiers changed from: package-private */
        public StrongKeyStrongValueEntry<K, V> a(StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry) {
            StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry2 = new StrongKeyStrongValueEntry<>(this.a, this.b, strongKeyStrongValueEntry);
            strongKeyStrongValueEntry2.value = this.value;
            return strongKeyStrongValueEntry2;
        }

        /* access modifiers changed from: package-private */
        public void a(V v) {
            this.value = v;
        }

        @Nullable
        public V getValue() {
            return this.value;
        }
    }

    static final class StrongKeyStrongValueSegment<K, V> extends Segment<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> {
        StrongKeyStrongValueSegment(MapMakerInternalMap<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        public StrongKeyStrongValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (StrongKeyStrongValueEntry) internalEntry;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: k */
        public StrongKeyStrongValueSegment<K, V> a() {
            return this;
        }
    }

    static final class StrongKeyWeakValueEntry<K, V> extends AbstractStrongKeyEntry<K, V, StrongKeyWeakValueEntry<K, V>> implements WeakValueEntry<K, V, StrongKeyWeakValueEntry<K, V>> {
        /* access modifiers changed from: private */
        public volatile WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> valueReference = MapMakerInternalMap.a();

        static final class Helper<K, V> implements InternalEntryHelper<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K, V> Helper<K, V> a() {
                return INSTANCE;
            }

            public StrongKeyWeakValueEntry<K, V> copy(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry, @Nullable StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry2) {
                if (Segment.a(strongKeyWeakValueEntry)) {
                    return null;
                }
                return strongKeyWeakValueEntry.a((ReferenceQueue<V>) strongKeyWeakValueSegment.queueForValues, strongKeyWeakValueEntry2);
            }

            public Strength keyStrength() {
                return Strength.STRONG;
            }

            public StrongKeyWeakValueEntry<K, V> newEntry(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, K k, int i, @Nullable StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry) {
                return new StrongKeyWeakValueEntry<>(k, i, strongKeyWeakValueEntry);
            }

            public StrongKeyWeakValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
                return new StrongKeyWeakValueSegment<>(mapMakerInternalMap, i, i2);
            }

            public void setValue(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry, V v) {
                strongKeyWeakValueEntry.a(v, (ReferenceQueue<V>) strongKeyWeakValueSegment.queueForValues);
            }

            public Strength valueStrength() {
                return Strength.WEAK;
            }
        }

        StrongKeyWeakValueEntry(K k, int i, @Nullable StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry) {
            super(k, i, strongKeyWeakValueEntry);
        }

        /* access modifiers changed from: package-private */
        public StrongKeyWeakValueEntry<K, V> a(ReferenceQueue<V> referenceQueue, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry) {
            StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry2 = new StrongKeyWeakValueEntry<>(this.a, this.b, strongKeyWeakValueEntry);
            strongKeyWeakValueEntry2.valueReference = this.valueReference.copyFor(referenceQueue, strongKeyWeakValueEntry2);
            return strongKeyWeakValueEntry2;
        }

        /* access modifiers changed from: package-private */
        public void a(V v, ReferenceQueue<V> referenceQueue) {
            WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> weakValueReference = this.valueReference;
            this.valueReference = new WeakValueReferenceImpl(referenceQueue, v, this);
            weakValueReference.clear();
        }

        public void clearValue() {
            this.valueReference.clear();
        }

        public V getValue() {
            return this.valueReference.get();
        }

        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> getValueReference() {
            return this.valueReference;
        }
    }

    static final class StrongKeyWeakValueSegment<K, V> extends Segment<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<V> queueForValues = new ReferenceQueue<>();

        StrongKeyWeakValueSegment(MapMakerInternalMap<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        /* access modifiers changed from: package-private */
        public void b() {
            b(this.queueForValues);
        }

        /* access modifiers changed from: package-private */
        public void c() {
            c(this.queueForValues);
        }

        public StrongKeyWeakValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (StrongKeyWeakValueEntry) internalEntry;
        }

        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            return castForTesting((InternalEntry) internalEntry).getValueReference();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: k */
        public StrongKeyWeakValueSegment<K, V> a() {
            return this;
        }

        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v) {
            return new WeakValueReferenceImpl(this.queueForValues, v, castForTesting((InternalEntry) internalEntry));
        }

        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            StrongKeyWeakValueEntry castForTesting = castForTesting((InternalEntry) internalEntry);
            WeakValueReference a = castForTesting.valueReference;
            WeakValueReference unused = castForTesting.valueReference = weakValueReference;
            a.clear();
        }
    }

    interface StrongValueEntry<K, V, E extends InternalEntry<K, V, E>> extends InternalEntry<K, V, E> {
    }

    final class ValueIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<V> {
        ValueIterator() {
            super();
        }

        public V next() {
            return d().getValue();
        }
    }

    final class Values extends AbstractCollection<V> {
        Values() {
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        public boolean contains(Object obj) {
            return MapMakerInternalMap.this.containsValue(obj);
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }

        public Object[] toArray() {
            return MapMakerInternalMap.toArrayList(this).toArray();
        }

        public <E> E[] toArray(E[] eArr) {
            return MapMakerInternalMap.toArrayList(this).toArray(eArr);
        }
    }

    static final class WeakKeyStrongValueEntry<K, V> extends AbstractWeakKeyEntry<K, V, WeakKeyStrongValueEntry<K, V>> implements StrongValueEntry<K, V, WeakKeyStrongValueEntry<K, V>> {
        @Nullable
        private volatile V value = null;

        static final class Helper<K, V> implements InternalEntryHelper<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K, V> Helper<K, V> a() {
                return INSTANCE;
            }

            public WeakKeyStrongValueEntry<K, V> copy(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry, @Nullable WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry2) {
                if (weakKeyStrongValueEntry.getKey() == null) {
                    return null;
                }
                return weakKeyStrongValueEntry.a(weakKeyStrongValueSegment.queueForKeys, weakKeyStrongValueEntry2);
            }

            public Strength keyStrength() {
                return Strength.WEAK;
            }

            public WeakKeyStrongValueEntry<K, V> newEntry(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, K k, int i, @Nullable WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry) {
                return new WeakKeyStrongValueEntry<>(weakKeyStrongValueSegment.queueForKeys, k, i, weakKeyStrongValueEntry);
            }

            public WeakKeyStrongValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
                return new WeakKeyStrongValueSegment<>(mapMakerInternalMap, i, i2);
            }

            public void setValue(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry, V v) {
                weakKeyStrongValueEntry.a(v);
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }
        }

        WeakKeyStrongValueEntry(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry) {
            super(referenceQueue, k, i, weakKeyStrongValueEntry);
        }

        /* access modifiers changed from: package-private */
        public WeakKeyStrongValueEntry<K, V> a(ReferenceQueue<K> referenceQueue, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry) {
            WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry2 = new WeakKeyStrongValueEntry<>(referenceQueue, getKey(), this.a, weakKeyStrongValueEntry);
            weakKeyStrongValueEntry2.a(this.value);
            return weakKeyStrongValueEntry2;
        }

        /* access modifiers changed from: package-private */
        public void a(V v) {
            this.value = v;
        }

        @Nullable
        public V getValue() {
            return this.value;
        }
    }

    static final class WeakKeyStrongValueSegment<K, V> extends Segment<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();

        WeakKeyStrongValueSegment(MapMakerInternalMap<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        /* access modifiers changed from: package-private */
        public void b() {
            a(this.queueForKeys);
        }

        /* access modifiers changed from: package-private */
        public void c() {
            c(this.queueForKeys);
        }

        public WeakKeyStrongValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (WeakKeyStrongValueEntry) internalEntry;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: k */
        public WeakKeyStrongValueSegment<K, V> a() {
            return this;
        }
    }

    static final class WeakKeyWeakValueEntry<K, V> extends AbstractWeakKeyEntry<K, V, WeakKeyWeakValueEntry<K, V>> implements WeakValueEntry<K, V, WeakKeyWeakValueEntry<K, V>> {
        /* access modifiers changed from: private */
        public volatile WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> valueReference = MapMakerInternalMap.a();

        static final class Helper<K, V> implements InternalEntryHelper<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K, V> Helper<K, V> a() {
                return INSTANCE;
            }

            public WeakKeyWeakValueEntry<K, V> copy(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry, @Nullable WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry2) {
                if (weakKeyWeakValueEntry.getKey() != null && !Segment.a(weakKeyWeakValueEntry)) {
                    return weakKeyWeakValueEntry.a(weakKeyWeakValueSegment.queueForKeys, weakKeyWeakValueSegment.queueForValues, weakKeyWeakValueEntry2);
                }
                return null;
            }

            public Strength keyStrength() {
                return Strength.WEAK;
            }

            public WeakKeyWeakValueEntry<K, V> newEntry(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, K k, int i, @Nullable WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry) {
                return new WeakKeyWeakValueEntry<>(weakKeyWeakValueSegment.queueForKeys, k, i, weakKeyWeakValueEntry);
            }

            public WeakKeyWeakValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
                return new WeakKeyWeakValueSegment<>(mapMakerInternalMap, i, i2);
            }

            public void setValue(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry, V v) {
                weakKeyWeakValueEntry.a(v, (ReferenceQueue<V>) weakKeyWeakValueSegment.queueForValues);
            }

            public Strength valueStrength() {
                return Strength.WEAK;
            }
        }

        WeakKeyWeakValueEntry(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry) {
            super(referenceQueue, k, i, weakKeyWeakValueEntry);
        }

        /* access modifiers changed from: package-private */
        public WeakKeyWeakValueEntry<K, V> a(ReferenceQueue<K> referenceQueue, ReferenceQueue<V> referenceQueue2, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry) {
            WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry2 = new WeakKeyWeakValueEntry<>(referenceQueue, getKey(), this.a, weakKeyWeakValueEntry);
            weakKeyWeakValueEntry2.valueReference = this.valueReference.copyFor(referenceQueue2, weakKeyWeakValueEntry2);
            return weakKeyWeakValueEntry2;
        }

        /* access modifiers changed from: package-private */
        public void a(V v, ReferenceQueue<V> referenceQueue) {
            WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> weakValueReference = this.valueReference;
            this.valueReference = new WeakValueReferenceImpl(referenceQueue, v, this);
            weakValueReference.clear();
        }

        public void clearValue() {
            this.valueReference.clear();
        }

        public V getValue() {
            return this.valueReference.get();
        }

        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> getValueReference() {
            return this.valueReference;
        }
    }

    static final class WeakKeyWeakValueSegment<K, V> extends Segment<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();
        /* access modifiers changed from: private */
        public final ReferenceQueue<V> queueForValues = new ReferenceQueue<>();

        WeakKeyWeakValueSegment(MapMakerInternalMap<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        /* access modifiers changed from: package-private */
        public void b() {
            a(this.queueForKeys);
            b(this.queueForValues);
        }

        /* access modifiers changed from: package-private */
        public void c() {
            c(this.queueForKeys);
        }

        public WeakKeyWeakValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (WeakKeyWeakValueEntry) internalEntry;
        }

        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            return castForTesting((InternalEntry) internalEntry).getValueReference();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: k */
        public WeakKeyWeakValueSegment<K, V> a() {
            return this;
        }

        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v) {
            return new WeakValueReferenceImpl(this.queueForValues, v, castForTesting((InternalEntry) internalEntry));
        }

        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            WeakKeyWeakValueEntry castForTesting = castForTesting((InternalEntry) internalEntry);
            WeakValueReference a = castForTesting.valueReference;
            WeakValueReference unused = castForTesting.valueReference = weakValueReference;
            a.clear();
        }
    }

    interface WeakValueEntry<K, V, E extends InternalEntry<K, V, E>> extends InternalEntry<K, V, E> {
        void clearValue();

        WeakValueReference<K, V, E> getValueReference();
    }

    interface WeakValueReference<K, V, E extends InternalEntry<K, V, E>> {
        void clear();

        WeakValueReference<K, V, E> copyFor(ReferenceQueue<V> referenceQueue, E e);

        @Nullable
        V get();

        E getEntry();
    }

    static final class WeakValueReferenceImpl<K, V, E extends InternalEntry<K, V, E>> extends WeakReference<V> implements WeakValueReference<K, V, E> {
        final E a;

        WeakValueReferenceImpl(ReferenceQueue<V> referenceQueue, V v, E e) {
            super(v, referenceQueue);
            this.a = e;
        }

        public WeakValueReference<K, V, E> copyFor(ReferenceQueue<V> referenceQueue, E e) {
            return new WeakValueReferenceImpl(referenceQueue, get(), e);
        }

        public E getEntry() {
            return this.a;
        }
    }

    final class WriteThroughEntry extends AbstractMapEntry<K, V> {
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
            V put = MapMakerInternalMap.this.put(this.a, v);
            this.b = v;
            return put;
        }
    }

    private MapMakerInternalMap(MapMaker mapMaker, InternalEntryHelper<K, V, E, S> internalEntryHelper) {
        this.d = Math.min(mapMaker.c(), 65536);
        this.e = mapMaker.a();
        this.f = internalEntryHelper;
        int min = Math.min(mapMaker.b(), Ints.MAX_POWER_OF_TWO);
        int i2 = 0;
        int i3 = 1;
        int i4 = 1;
        int i5 = 0;
        while (i4 < this.d) {
            i5++;
            i4 <<= 1;
        }
        this.b = 32 - i5;
        this.a = i4 - 1;
        this.c = c(i4);
        int i6 = min / i4;
        while (i3 < (i4 * i6 < min ? i6 + 1 : i6)) {
            i3 <<= 1;
        }
        while (true) {
            Segment<K, V, E, S>[] segmentArr = this.c;
            if (i2 < segmentArr.length) {
                segmentArr[i2] = a(i3, -1);
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

    static <K, V, E extends InternalEntry<K, V, E>> WeakValueReference<K, V, E> a() {
        return g;
    }

    static <K, V> MapMakerInternalMap<K, V, ? extends InternalEntry<K, V, ?>, ?> a(MapMaker mapMaker) {
        if (mapMaker.d() == Strength.STRONG && mapMaker.e() == Strength.STRONG) {
            return new MapMakerInternalMap<>(mapMaker, StrongKeyStrongValueEntry.Helper.a());
        }
        if (mapMaker.d() == Strength.STRONG && mapMaker.e() == Strength.WEAK) {
            return new MapMakerInternalMap<>(mapMaker, StrongKeyWeakValueEntry.Helper.a());
        }
        if (mapMaker.d() == Strength.WEAK && mapMaker.e() == Strength.STRONG) {
            return new MapMakerInternalMap<>(mapMaker, WeakKeyStrongValueEntry.Helper.a());
        }
        if (mapMaker.d() == Strength.WEAK && mapMaker.e() == Strength.WEAK) {
            return new MapMakerInternalMap<>(mapMaker, WeakKeyWeakValueEntry.Helper.a());
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: private */
    public static <E> ArrayList<E> toArrayList(Collection<E> collection) {
        ArrayList<E> arrayList = new ArrayList<>(collection.size());
        Iterators.addAll(arrayList, collection.iterator());
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public int a(Object obj) {
        return a(this.e.hash(obj));
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V, E, S> a(int i2, int i3) {
        return this.f.newSegment(this, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public void a(E e2) {
        int hash = e2.getHash();
        b(hash).a(e2, hash);
    }

    /* access modifiers changed from: package-private */
    public void a(WeakValueReference<K, V, E> weakValueReference) {
        E entry = weakValueReference.getEntry();
        int hash = entry.getHash();
        b(hash).a(entry.getKey(), hash, weakValueReference);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public Equivalence<Object> b() {
        return this.f.valueStrength().a();
    }

    /* access modifiers changed from: package-private */
    public E b(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        int a2 = a(obj);
        return b(a2).a(obj, a2);
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V, E, S> b(int i2) {
        return this.c[(i2 >>> this.b) & this.a];
    }

    /* access modifiers changed from: package-private */
    public V b(E e2) {
        V value;
        if (e2.getKey() == null || (value = e2.getValue()) == null) {
            return null;
        }
        return value;
    }

    /* access modifiers changed from: package-private */
    public final Segment<K, V, E, S>[] c(int i2) {
        return new Segment[i2];
    }

    public void clear() {
        for (Segment<K, V, E, S> f2 : this.c) {
            f2.f();
        }
    }

    public boolean containsKey(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        int a2 = a(obj);
        return b(a2).d(obj, a2);
    }

    public boolean containsValue(@Nullable Object obj) {
        Object obj2 = obj;
        if (obj2 == null) {
            return false;
        }
        Segment<K, V, E, S>[] segmentArr = this.c;
        long j2 = -1;
        int i2 = 0;
        while (i2 < 3) {
            long j3 = 0;
            for (Segment<K, V, E, S> segment : segmentArr) {
                int i3 = segment.b;
                AtomicReferenceArray<E> atomicReferenceArray = segment.e;
                for (int i4 = 0; i4 < atomicReferenceArray.length(); i4++) {
                    for (InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(i4); internalEntry != null; internalEntry = internalEntry.getNext()) {
                        V b2 = segment.b(internalEntry);
                        if (b2 != null && b().equivalent(obj2, b2)) {
                            return true;
                        }
                    }
                }
                j3 += (long) segment.c;
            }
            if (j3 == j2) {
                return false;
            }
            i2++;
            j2 = j3;
        }
        return false;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.j;
        if (set != null) {
            return set;
        }
        EntrySet entrySet = new EntrySet();
        this.j = entrySet;
        return entrySet;
    }

    public V get(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        int a2 = a(obj);
        return b(a2).c(obj, a2);
    }

    public boolean isEmpty() {
        Segment<K, V, E, S>[] segmentArr = this.c;
        long j2 = 0;
        for (int i2 = 0; i2 < segmentArr.length; i2++) {
            if (segmentArr[i2].b != 0) {
                return false;
            }
            j2 += (long) segmentArr[i2].c;
        }
        if (j2 == 0) {
            return true;
        }
        for (int i3 = 0; i3 < segmentArr.length; i3++) {
            if (segmentArr[i3].b != 0) {
                return false;
            }
            j2 -= (long) segmentArr[i3].c;
        }
        return j2 == 0;
    }

    public Set<K> keySet() {
        Set<K> set = this.h;
        if (set != null) {
            return set;
        }
        KeySet keySet = new KeySet();
        this.h = keySet;
        return keySet;
    }

    @CanIgnoreReturnValue
    public V put(K k, V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        int a2 = a((Object) k);
        return b(a2).a(k, a2, v, false);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    @CanIgnoreReturnValue
    public V putIfAbsent(K k, V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        int a2 = a((Object) k);
        return b(a2).a(k, a2, v, true);
    }

    @CanIgnoreReturnValue
    public V remove(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        int a2 = a(obj);
        return b(a2).e(obj, a2);
    }

    @CanIgnoreReturnValue
    public boolean remove(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int a2 = a(obj);
        return b(a2).b(obj, a2, obj2);
    }

    @CanIgnoreReturnValue
    public V replace(K k, V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        int a2 = a((Object) k);
        return b(a2).a(k, a2, v);
    }

    @CanIgnoreReturnValue
    public boolean replace(K k, @Nullable V v, V v2) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v2);
        if (v == null) {
            return false;
        }
        int a2 = a((Object) k);
        return b(a2).a(k, a2, v, v2);
    }

    public int size() {
        Segment<K, V, E, S>[] segmentArr = this.c;
        long j2 = 0;
        for (Segment<K, V, E, S> segment : segmentArr) {
            j2 += (long) segment.b;
        }
        return Ints.saturatedCast(j2);
    }

    public Collection<V> values() {
        Collection<V> collection = this.i;
        if (collection != null) {
            return collection;
        }
        Values values = new Values();
        this.i = values;
        return values;
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializationProxy(this.f.keyStrength(), this.f.valueStrength(), this.e, this.f.valueStrength().a(), this.d, this);
    }
}
