package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
abstract class AbstractMapBasedMultiset<E> extends AbstractMultiset<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = -2250766705698539974L;
    /* access modifiers changed from: private */
    public transient Map<E, Count> backingMap;
    private transient long size = ((long) super.size());

    private class MapBasedMultisetIterator implements Iterator<E> {
        final Iterator<Map.Entry<E, Count>> a;
        Map.Entry<E, Count> b;
        int c;
        boolean d;

        MapBasedMultisetIterator() {
            this.a = AbstractMapBasedMultiset.this.backingMap.entrySet().iterator();
        }

        public boolean hasNext() {
            return this.c > 0 || this.a.hasNext();
        }

        public E next() {
            if (this.c == 0) {
                this.b = this.a.next();
                this.c = this.b.getValue().get();
            }
            this.c--;
            this.d = true;
            return this.b.getKey();
        }

        public void remove() {
            CollectPreconditions.a(this.d);
            if (this.b.getValue().get() > 0) {
                if (this.b.getValue().addAndGet(-1) == 0) {
                    this.a.remove();
                }
                AbstractMapBasedMultiset.b(AbstractMapBasedMultiset.this);
                this.d = false;
                return;
            }
            throw new ConcurrentModificationException();
        }
    }

    protected AbstractMapBasedMultiset(Map<E, Count> map) {
        this.backingMap = (Map) Preconditions.checkNotNull(map);
    }

    static /* synthetic */ long a(AbstractMapBasedMultiset abstractMapBasedMultiset, long j) {
        long j2 = abstractMapBasedMultiset.size - j;
        abstractMapBasedMultiset.size = j2;
        return j2;
    }

    static /* synthetic */ long b(AbstractMapBasedMultiset abstractMapBasedMultiset) {
        long j = abstractMapBasedMultiset.size;
        abstractMapBasedMultiset.size = j - 1;
        return j;
    }

    private static int getAndSet(@Nullable Count count, int i) {
        if (count == null) {
            return 0;
        }
        return count.getAndSet(i);
    }

    @GwtIncompatible
    private void readObjectNoData() {
        throw new InvalidObjectException("Stream data required");
    }

    /* access modifiers changed from: package-private */
    public Iterator<Multiset.Entry<E>> a() {
        final Iterator<Map.Entry<E, Count>> it = this.backingMap.entrySet().iterator();
        return new Iterator<Multiset.Entry<E>>() {
            Map.Entry<E, Count> a;

            public boolean hasNext() {
                return it.hasNext();
            }

            public Multiset.Entry<E> next() {
                final Map.Entry<E, Count> entry = (Map.Entry) it.next();
                this.a = entry;
                return new Multisets.AbstractEntry<E>() {
                    public int getCount() {
                        Count count;
                        Count count2 = (Count) entry.getValue();
                        if ((count2 == null || count2.get() == 0) && (count = (Count) AbstractMapBasedMultiset.this.backingMap.get(getElement())) != null) {
                            return count.get();
                        }
                        if (count2 == null) {
                            return 0;
                        }
                        return count2.get();
                    }

                    public E getElement() {
                        return entry.getKey();
                    }
                };
            }

            public void remove() {
                CollectPreconditions.a(this.a != null);
                AbstractMapBasedMultiset.a(AbstractMapBasedMultiset.this, (long) this.a.getValue().getAndSet(0));
                it.remove();
                this.a = null;
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void a(Map<E, Count> map) {
        this.backingMap = map;
    }

    @CanIgnoreReturnValue
    public int add(@Nullable E e, int i) {
        int i2;
        if (i == 0) {
            return count(e);
        }
        boolean z = true;
        Preconditions.checkArgument(i > 0, "occurrences cannot be negative: %s", i);
        Count count = this.backingMap.get(e);
        if (count == null) {
            this.backingMap.put(e, new Count(i));
            i2 = 0;
        } else {
            i2 = count.get();
            long j = ((long) i2) + ((long) i);
            if (j > 2147483647L) {
                z = false;
            }
            Preconditions.checkArgument(z, "too many occurrences: %s", j);
            count.add(i);
        }
        this.size += (long) i;
        return i2;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.backingMap.size();
    }

    public void clear() {
        for (Count count : this.backingMap.values()) {
            count.set(0);
        }
        this.backingMap.clear();
        this.size = 0;
    }

    public int count(@Nullable Object obj) {
        Count count = (Count) Maps.a(this.backingMap, obj);
        if (count == null) {
            return 0;
        }
        return count.get();
    }

    public Set<Multiset.Entry<E>> entrySet() {
        return super.entrySet();
    }

    public Iterator<E> iterator() {
        return new MapBasedMultisetIterator();
    }

    @CanIgnoreReturnValue
    public int remove(@Nullable Object obj, int i) {
        if (i == 0) {
            return count(obj);
        }
        Preconditions.checkArgument(i > 0, "occurrences cannot be negative: %s", i);
        Count count = this.backingMap.get(obj);
        if (count == null) {
            return 0;
        }
        int i2 = count.get();
        if (i2 <= i) {
            this.backingMap.remove(obj);
            i = i2;
        }
        count.add(-i);
        this.size -= (long) i;
        return i2;
    }

    @CanIgnoreReturnValue
    public int setCount(@Nullable E e, int i) {
        int i2;
        CollectPreconditions.a(i, "count");
        if (i == 0) {
            i2 = getAndSet(this.backingMap.remove(e), i);
        } else {
            Count count = this.backingMap.get(e);
            int andSet = getAndSet(count, i);
            if (count == null) {
                this.backingMap.put(e, new Count(i));
            }
            i2 = andSet;
        }
        this.size += (long) (i - i2);
        return i2;
    }

    public int size() {
        return Ints.saturatedCast(this.size);
    }
}
