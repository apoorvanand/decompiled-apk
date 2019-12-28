package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
final class ImmutableMapValues<K, V> extends ImmutableCollection<V> {
    /* access modifiers changed from: private */
    @Weak
    public final ImmutableMap<K, V> map;

    @GwtIncompatible
    private static class SerializedForm<V> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<?, V> a;

        SerializedForm(ImmutableMap<?, V> immutableMap) {
            this.a = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.a.values();
        }
    }

    ImmutableMapValues(ImmutableMap<K, V> immutableMap) {
        this.map = immutableMap;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return true;
    }

    public ImmutableList<V> asList() {
        final ImmutableList<Map.Entry<K, V>> asList = this.map.entrySet().asList();
        return new ImmutableAsList<V>() {
            /* access modifiers changed from: package-private */
            public ImmutableCollection<V> b() {
                return ImmutableMapValues.this;
            }

            public V get(int i) {
                return ((Map.Entry) asList.get(i)).getValue();
            }
        };
    }

    public boolean contains(@Nullable Object obj) {
        return obj != null && Iterators.contains(iterator(), obj);
    }

    public UnmodifiableIterator<V> iterator() {
        return new UnmodifiableIterator<V>() {
            final UnmodifiableIterator<Map.Entry<K, V>> a = ImmutableMapValues.this.map.entrySet().iterator();

            public boolean hasNext() {
                return this.a.hasNext();
            }

            public V next() {
                return ((Map.Entry) this.a.next()).getValue();
            }
        };
    }

    public int size() {
        return this.map.size();
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(this.map);
    }
}
