package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import javax.annotation.Nullable;

@GwtIncompatible
class ImmutableMapEntry<K, V> extends ImmutableEntry<K, V> {

    static final class NonTerminalImmutableBiMapEntry<K, V> extends NonTerminalImmutableMapEntry<K, V> {
        private final transient ImmutableMapEntry<K, V> nextInValueBucket;

        NonTerminalImmutableBiMapEntry(K k, V v, ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2) {
            super(k, v, immutableMapEntry);
            this.nextInValueBucket = immutableMapEntry2;
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public ImmutableMapEntry<K, V> b() {
            return this.nextInValueBucket;
        }
    }

    static class NonTerminalImmutableMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        private final transient ImmutableMapEntry<K, V> nextInKeyBucket;

        NonTerminalImmutableMapEntry(K k, V v, ImmutableMapEntry<K, V> immutableMapEntry) {
            super(k, v);
            this.nextInKeyBucket = immutableMapEntry;
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public final ImmutableMapEntry<K, V> a() {
            return this.nextInKeyBucket;
        }

        /* access modifiers changed from: package-private */
        public final boolean c() {
            return false;
        }
    }

    ImmutableMapEntry(K k, V v) {
        super(k, v);
        CollectPreconditions.a((Object) k, (Object) v);
    }

    static <K, V> ImmutableMapEntry<K, V>[] a(int i) {
        return new ImmutableMapEntry[i];
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public ImmutableMapEntry<K, V> a() {
        return null;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public ImmutableMapEntry<K, V> b() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return true;
    }
}
