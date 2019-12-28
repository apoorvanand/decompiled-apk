package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
abstract class ImmutableMapEntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {

    @GwtIncompatible
    private static class EntrySetSerializedForm<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<K, V> a;

        EntrySetSerializedForm(ImmutableMap<K, V> immutableMap) {
            this.a = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.a.entrySet();
        }
    }

    static final class RegularEntrySet<K, V> extends ImmutableMapEntrySet<K, V> {
        private final transient Map.Entry<K, V>[] entries;
        @Weak
        private final transient ImmutableMap<K, V> map;

        RegularEntrySet(ImmutableMap<K, V> immutableMap, Map.Entry<K, V>[] entryArr) {
            this.map = immutableMap;
            this.entries = entryArr;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<K, V> b() {
            return this.map;
        }

        /* access modifiers changed from: package-private */
        public ImmutableList<Map.Entry<K, V>> d() {
            return new RegularImmutableAsList(this, (Object[]) this.entries);
        }

        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return Iterators.forArray(this.entries);
        }
    }

    ImmutableMapEntrySet() {
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return b().b();
    }

    /* access modifiers changed from: package-private */
    public abstract ImmutableMap<K, V> b();

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public boolean c() {
        return b().g();
    }

    public boolean contains(@Nullable Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = b().get(entry.getKey());
        return obj2 != null && obj2.equals(entry.getValue());
    }

    public int hashCode() {
        return b().hashCode();
    }

    public int size() {
        return b().size();
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public Object writeReplace() {
        return new EntrySetSerializedForm(b());
    }
}
