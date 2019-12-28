package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ForwardingMapEntry;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Beta
public final class MutableTypeToInstanceMap<B> extends ForwardingMap<TypeToken<? extends B>, B> implements TypeToInstanceMap<B> {
    private final Map<TypeToken<? extends B>, B> backingMap = Maps.newHashMap();

    private static final class UnmodifiableEntry<K, V> extends ForwardingMapEntry<K, V> {
        private final Map.Entry<K, V> delegate;

        private UnmodifiableEntry(Map.Entry<K, V> entry) {
            this.delegate = (Map.Entry) Preconditions.checkNotNull(entry);
        }

        static <K, V> Set<Map.Entry<K, V>> a(final Set<Map.Entry<K, V>> set) {
            return new ForwardingSet<Map.Entry<K, V>>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Set<Map.Entry<K, V>> delegate() {
                    return set;
                }

                public Iterator<Map.Entry<K, V>> iterator() {
                    return UnmodifiableEntry.transformEntries(super.iterator());
                }

                public Object[] toArray() {
                    return g();
                }

                public <T> T[] toArray(T[] tArr) {
                    return a(tArr);
                }
            };
        }

        /* access modifiers changed from: private */
        public static <K, V> Iterator<Map.Entry<K, V>> transformEntries(Iterator<Map.Entry<K, V>> it) {
            return Iterators.transform(it, new Function<Map.Entry<K, V>, Map.Entry<K, V>>() {
                public Map.Entry<K, V> apply(Map.Entry<K, V> entry) {
                    return new UnmodifiableEntry(entry);
                }
            });
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Map.Entry<K, V> delegate() {
            return this.delegate;
        }

        public V setValue(V v) {
            throw new UnsupportedOperationException();
        }
    }

    @Nullable
    private <T extends B> T trustedGet(TypeToken<T> typeToken) {
        return this.backingMap.get(typeToken);
    }

    @Nullable
    private <T extends B> T trustedPut(TypeToken<T> typeToken, @Nullable T t) {
        return this.backingMap.put(typeToken, t);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Map<TypeToken<? extends B>, B> delegate() {
        return this.backingMap;
    }

    public Set<Map.Entry<TypeToken<? extends B>, B>> entrySet() {
        return UnmodifiableEntry.a(super.entrySet());
    }

    @Nullable
    public <T extends B> T getInstance(TypeToken<T> typeToken) {
        return trustedGet(typeToken.d());
    }

    @Nullable
    public <T extends B> T getInstance(Class<T> cls) {
        return trustedGet(TypeToken.of(cls));
    }

    @CanIgnoreReturnValue
    @Deprecated
    public B put(TypeToken<? extends B> typeToken, B b) {
        throw new UnsupportedOperationException("Please use putInstance() instead.");
    }

    @Deprecated
    public void putAll(Map<? extends TypeToken<? extends B>, ? extends B> map) {
        throw new UnsupportedOperationException("Please use putInstance() instead.");
    }

    @CanIgnoreReturnValue
    @Nullable
    public <T extends B> T putInstance(TypeToken<T> typeToken, @Nullable T t) {
        return trustedPut(typeToken.d(), t);
    }

    @CanIgnoreReturnValue
    @Nullable
    public <T extends B> T putInstance(Class<T> cls, @Nullable T t) {
        return trustedPut(TypeToken.of(cls), t);
    }
}
