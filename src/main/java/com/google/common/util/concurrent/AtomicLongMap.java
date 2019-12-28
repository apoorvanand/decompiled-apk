package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@GwtCompatible
public final class AtomicLongMap<K> {
    private transient Map<K, Long> asMap;
    private final ConcurrentHashMap<K, AtomicLong> map;

    private AtomicLongMap(ConcurrentHashMap<K, AtomicLong> concurrentHashMap) {
        this.map = (ConcurrentHashMap) Preconditions.checkNotNull(concurrentHashMap);
    }

    public static <K> AtomicLongMap<K> create() {
        return new AtomicLongMap<>(new ConcurrentHashMap());
    }

    public static <K> AtomicLongMap<K> create(Map<? extends K, ? extends Long> map2) {
        AtomicLongMap<K> create = create();
        create.putAll(map2);
        return create;
    }

    private Map<K, Long> createAsMap() {
        return Collections.unmodifiableMap(Maps.transformValues(this.map, new Function<AtomicLong, Long>() {
            public Long apply(AtomicLong atomicLong) {
                return Long.valueOf(atomicLong.get());
            }
        }));
    }

    /* access modifiers changed from: package-private */
    public boolean a(K k, long j) {
        AtomicLong atomicLong = this.map.get(k);
        if (atomicLong == null) {
            return false;
        }
        long j2 = atomicLong.get();
        if (j2 != j) {
            return false;
        }
        if (j2 != 0 && !atomicLong.compareAndSet(j2, 0)) {
            return false;
        }
        this.map.remove(k, atomicLong);
        return true;
    }

    @CanIgnoreReturnValue
    public long addAndGet(K k, long j) {
        AtomicLong atomicLong;
        do {
            atomicLong = this.map.get(k);
            if (atomicLong == null && (atomicLong = this.map.putIfAbsent(k, new AtomicLong(j))) == null) {
                return j;
            }
            while (true) {
                long j2 = atomicLong.get();
                if (j2 != 0) {
                    long j3 = j2 + j;
                    if (atomicLong.compareAndSet(j2, j3)) {
                        return j3;
                    }
                }
            }
        } while (!this.map.replace(k, atomicLong, new AtomicLong(j)));
        return j;
    }

    public Map<K, Long> asMap() {
        Map<K, Long> map2 = this.asMap;
        if (map2 != null) {
            return map2;
        }
        Map<K, Long> createAsMap = createAsMap();
        this.asMap = createAsMap;
        return createAsMap;
    }

    public void clear() {
        this.map.clear();
    }

    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @CanIgnoreReturnValue
    public long decrementAndGet(K k) {
        return addAndGet(k, -1);
    }

    public long get(K k) {
        AtomicLong atomicLong = this.map.get(k);
        if (atomicLong == null) {
            return 0;
        }
        return atomicLong.get();
    }

    @CanIgnoreReturnValue
    public long getAndAdd(K k, long j) {
        AtomicLong atomicLong;
        do {
            atomicLong = this.map.get(k);
            if (atomicLong == null && (atomicLong = this.map.putIfAbsent(k, new AtomicLong(j))) == null) {
                return 0;
            }
            while (true) {
                long j2 = atomicLong.get();
                if (j2 != 0) {
                    if (atomicLong.compareAndSet(j2, j2 + j)) {
                        return j2;
                    }
                }
            }
        } while (!this.map.replace(k, atomicLong, new AtomicLong(j)));
        return 0;
    }

    @CanIgnoreReturnValue
    public long getAndDecrement(K k) {
        return getAndAdd(k, -1);
    }

    @CanIgnoreReturnValue
    public long getAndIncrement(K k) {
        return getAndAdd(k, 1);
    }

    @CanIgnoreReturnValue
    public long incrementAndGet(K k) {
        return addAndGet(k, 1);
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @CanIgnoreReturnValue
    public long put(K k, long j) {
        AtomicLong atomicLong;
        do {
            atomicLong = this.map.get(k);
            if (atomicLong == null && (atomicLong = this.map.putIfAbsent(k, new AtomicLong(j))) == null) {
                return 0;
            }
            while (true) {
                long j2 = atomicLong.get();
                if (j2 != 0) {
                    if (atomicLong.compareAndSet(j2, j)) {
                        return j2;
                    }
                }
            }
        } while (!this.map.replace(k, atomicLong, new AtomicLong(j)));
        return 0;
    }

    public void putAll(Map<? extends K, ? extends Long> map2) {
        for (Map.Entry next : map2.entrySet()) {
            put(next.getKey(), ((Long) next.getValue()).longValue());
        }
    }

    @CanIgnoreReturnValue
    public long remove(K k) {
        long j;
        AtomicLong atomicLong = this.map.get(k);
        if (atomicLong == null) {
            return 0;
        }
        do {
            j = atomicLong.get();
            if (j == 0 || atomicLong.compareAndSet(j, 0)) {
                this.map.remove(k, atomicLong);
            }
            j = atomicLong.get();
            break;
        } while (atomicLong.compareAndSet(j, 0));
        this.map.remove(k, atomicLong);
        return j;
    }

    public void removeAllZeros() {
        Iterator<Map.Entry<K, AtomicLong>> it = this.map.entrySet().iterator();
        while (it.hasNext()) {
            AtomicLong atomicLong = (AtomicLong) it.next().getValue();
            if (atomicLong != null && atomicLong.get() == 0) {
                it.remove();
            }
        }
    }

    @CanIgnoreReturnValue
    @Beta
    public boolean removeIfZero(K k) {
        return a(k, 0);
    }

    public int size() {
        return this.map.size();
    }

    public long sum() {
        long j = 0;
        for (AtomicLong atomicLong : this.map.values()) {
            j += atomicLong.get();
        }
        return j;
    }

    public String toString() {
        return this.map.toString();
    }
}