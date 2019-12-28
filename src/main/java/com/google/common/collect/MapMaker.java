package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMakerInternalMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@GwtCompatible(emulated = true)
public final class MapMaker {
    private static final int DEFAULT_CONCURRENCY_LEVEL = 4;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    boolean a;
    int b = -1;
    int c = -1;
    MapMakerInternalMap.Strength d;
    MapMakerInternalMap.Strength e;
    Equivalence<Object> f;

    /* access modifiers changed from: package-private */
    public Equivalence<Object> a() {
        return (Equivalence) MoreObjects.firstNonNull(this.f, d().a());
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    @CanIgnoreReturnValue
    public MapMaker a(Equivalence<Object> equivalence) {
        Preconditions.checkState(this.f == null, "key equivalence was already set to %s", (Object) this.f);
        this.f = (Equivalence) Preconditions.checkNotNull(equivalence);
        this.a = true;
        return this;
    }

    /* access modifiers changed from: package-private */
    public MapMaker a(MapMakerInternalMap.Strength strength) {
        Preconditions.checkState(this.d == null, "Key strength was already set to %s", (Object) this.d);
        this.d = (MapMakerInternalMap.Strength) Preconditions.checkNotNull(strength);
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.a = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        int i = this.b;
        if (i == -1) {
            return 16;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public MapMaker b(MapMakerInternalMap.Strength strength) {
        Preconditions.checkState(this.e == null, "Value strength was already set to %s", (Object) this.e);
        this.e = (MapMakerInternalMap.Strength) Preconditions.checkNotNull(strength);
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.a = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        int i = this.c;
        if (i == -1) {
            return 4;
        }
        return i;
    }

    @CanIgnoreReturnValue
    public MapMaker concurrencyLevel(int i) {
        boolean z = true;
        Preconditions.checkState(this.c == -1, "concurrency level was already set to %s", this.c);
        if (i <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.c = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public MapMakerInternalMap.Strength d() {
        return (MapMakerInternalMap.Strength) MoreObjects.firstNonNull(this.d, MapMakerInternalMap.Strength.STRONG);
    }

    /* access modifiers changed from: package-private */
    public MapMakerInternalMap.Strength e() {
        return (MapMakerInternalMap.Strength) MoreObjects.firstNonNull(this.e, MapMakerInternalMap.Strength.STRONG);
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public <K, V> MapMakerInternalMap<K, V, ?, ?> f() {
        return MapMakerInternalMap.a(this);
    }

    @CanIgnoreReturnValue
    public MapMaker initialCapacity(int i) {
        boolean z = true;
        Preconditions.checkState(this.b == -1, "initial capacity was already set to %s", this.b);
        if (i < 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.b = i;
        return this;
    }

    public <K, V> ConcurrentMap<K, V> makeMap() {
        return !this.a ? new ConcurrentHashMap(b(), 0.75f, c()) : MapMakerInternalMap.a(this);
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Object) this);
        int i = this.b;
        if (i != -1) {
            stringHelper.add("initialCapacity", i);
        }
        int i2 = this.c;
        if (i2 != -1) {
            stringHelper.add("concurrencyLevel", i2);
        }
        MapMakerInternalMap.Strength strength = this.d;
        if (strength != null) {
            stringHelper.add("keyStrength", (Object) Ascii.toLowerCase(strength.toString()));
        }
        MapMakerInternalMap.Strength strength2 = this.e;
        if (strength2 != null) {
            stringHelper.add("valueStrength", (Object) Ascii.toLowerCase(strength2.toString()));
        }
        if (this.f != null) {
            stringHelper.addValue((Object) "keyEquivalence");
        }
        return stringHelper.toString();
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public MapMaker weakKeys() {
        return a(MapMakerInternalMap.Strength.WEAK);
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public MapMaker weakValues() {
        return b(MapMakerInternalMap.Strength.WEAK);
    }
}
