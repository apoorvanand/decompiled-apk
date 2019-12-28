package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache;
import com.google.common.cache.LocalCache;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;

@GwtCompatible(emulated = true)
public final class CacheBuilder<K, V> {
    private static final int DEFAULT_CONCURRENCY_LEVEL = 4;
    private static final int DEFAULT_EXPIRATION_NANOS = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final int DEFAULT_REFRESH_NANOS = 0;
    static final Supplier<? extends AbstractCache.StatsCounter> a = Suppliers.ofInstance(new AbstractCache.StatsCounter() {
        public void recordEviction() {
        }

        public void recordHits(int i) {
        }

        public void recordLoadException(long j) {
        }

        public void recordLoadSuccess(long j) {
        }

        public void recordMisses(int i) {
        }

        public CacheStats snapshot() {
            return CacheBuilder.b;
        }
    });
    static final CacheStats b = new CacheStats(0, 0, 0, 0, 0, 0);
    static final Supplier<AbstractCache.StatsCounter> c = new Supplier<AbstractCache.StatsCounter>() {
        public AbstractCache.StatsCounter get() {
            return new AbstractCache.SimpleStatsCounter();
        }
    };
    static final Ticker d = new Ticker() {
        public long read() {
            return 0;
        }
    };
    private static final Logger logger = Logger.getLogger(CacheBuilder.class.getName());
    boolean e = true;
    int f = -1;
    int g = -1;
    long h = -1;
    long i = -1;
    Weigher<? super K, ? super V> j;
    LocalCache.Strength k;
    LocalCache.Strength l;
    long m = -1;
    long n = -1;
    long o = -1;
    Equivalence<Object> p;
    Equivalence<Object> q;
    RemovalListener<? super K, ? super V> r;
    Ticker s;
    Supplier<? extends AbstractCache.StatsCounter> t = a;

    enum NullListener implements RemovalListener<Object, Object> {
        INSTANCE;

        public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
        }
    }

    enum OneWeigher implements Weigher<Object, Object> {
        INSTANCE;

        public int weigh(Object obj, Object obj2) {
            return 1;
        }
    }

    CacheBuilder() {
    }

    private void checkNonLoadingCache() {
        Preconditions.checkState(this.o == -1, "refreshAfterWrite requires a LoadingCache");
    }

    private void checkWeightWithWeigher() {
        String str;
        boolean z = true;
        if (this.j == null) {
            if (this.i != -1) {
                z = false;
            }
            str = "maximumWeight requires weigher";
        } else if (this.e) {
            if (this.i == -1) {
                z = false;
            }
            str = "weigher requires maximumWeight";
        } else if (this.i == -1) {
            logger.log(Level.WARNING, "ignoring weigher specified without maximumWeight");
            return;
        } else {
            return;
        }
        Preconditions.checkState(z, str);
    }

    @GwtIncompatible
    public static CacheBuilder<Object, Object> from(CacheBuilderSpec cacheBuilderSpec) {
        return cacheBuilderSpec.a().a();
    }

    @GwtIncompatible
    public static CacheBuilder<Object, Object> from(String str) {
        return from(CacheBuilderSpec.parse(str));
    }

    public static CacheBuilder<Object, Object> newBuilder() {
        return new CacheBuilder<>();
    }

    /* access modifiers changed from: package-private */
    public Ticker a(boolean z) {
        Ticker ticker = this.s;
        return ticker != null ? ticker : z ? Ticker.systemTicker() : d;
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public CacheBuilder<K, V> a() {
        this.e = false;
        return this;
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public CacheBuilder<K, V> a(Equivalence<Object> equivalence) {
        Preconditions.checkState(this.p == null, "key equivalence was already set to %s", (Object) this.p);
        this.p = (Equivalence) Preconditions.checkNotNull(equivalence);
        return this;
    }

    /* access modifiers changed from: package-private */
    public CacheBuilder<K, V> a(LocalCache.Strength strength) {
        Preconditions.checkState(this.k == null, "Key strength was already set to %s", (Object) this.k);
        this.k = (LocalCache.Strength) Preconditions.checkNotNull(strength);
        return this;
    }

    /* access modifiers changed from: package-private */
    public Equivalence<Object> b() {
        return (Equivalence) MoreObjects.firstNonNull(this.p, h().a());
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public CacheBuilder<K, V> b(Equivalence<Object> equivalence) {
        Preconditions.checkState(this.q == null, "value equivalence was already set to %s", (Object) this.q);
        this.q = (Equivalence) Preconditions.checkNotNull(equivalence);
        return this;
    }

    /* access modifiers changed from: package-private */
    public CacheBuilder<K, V> b(LocalCache.Strength strength) {
        Preconditions.checkState(this.l == null, "Value strength was already set to %s", (Object) this.l);
        this.l = (LocalCache.Strength) Preconditions.checkNotNull(strength);
        return this;
    }

    public <K1 extends K, V1 extends V> Cache<K1, V1> build() {
        checkWeightWithWeigher();
        checkNonLoadingCache();
        return new LocalCache.LocalManualCache(this);
    }

    public <K1 extends K, V1 extends V> LoadingCache<K1, V1> build(CacheLoader<? super K1, V1> cacheLoader) {
        checkWeightWithWeigher();
        return new LocalCache.LocalLoadingCache(this, cacheLoader);
    }

    /* access modifiers changed from: package-private */
    public Equivalence<Object> c() {
        return (Equivalence) MoreObjects.firstNonNull(this.q, i().a());
    }

    public CacheBuilder<K, V> concurrencyLevel(int i2) {
        boolean z = true;
        Preconditions.checkState(this.g == -1, "concurrency level was already set to %s", this.g);
        if (i2 <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.g = i2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        int i2 = this.f;
        if (i2 == -1) {
            return 16;
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        int i2 = this.g;
        if (i2 == -1) {
            return 4;
        }
        return i2;
    }

    public CacheBuilder<K, V> expireAfterAccess(long j2, TimeUnit timeUnit) {
        boolean z = true;
        Preconditions.checkState(this.n == -1, "expireAfterAccess was already set to %s ns", this.n);
        if (j2 < 0) {
            z = false;
        }
        Preconditions.checkArgument(z, "duration cannot be negative: %s %s", j2, (Object) timeUnit);
        this.n = timeUnit.toNanos(j2);
        return this;
    }

    public CacheBuilder<K, V> expireAfterWrite(long j2, TimeUnit timeUnit) {
        boolean z = true;
        Preconditions.checkState(this.m == -1, "expireAfterWrite was already set to %s ns", this.m);
        if (j2 < 0) {
            z = false;
        }
        Preconditions.checkArgument(z, "duration cannot be negative: %s %s", j2, (Object) timeUnit);
        this.m = timeUnit.toNanos(j2);
        return this;
    }

    /* access modifiers changed from: package-private */
    public long f() {
        if (this.m == 0 || this.n == 0) {
            return 0;
        }
        return this.j == null ? this.h : this.i;
    }

    /* access modifiers changed from: package-private */
    public <K1 extends K, V1 extends V> Weigher<K1, V1> g() {
        return (Weigher) MoreObjects.firstNonNull(this.j, OneWeigher.INSTANCE);
    }

    /* access modifiers changed from: package-private */
    public LocalCache.Strength h() {
        return (LocalCache.Strength) MoreObjects.firstNonNull(this.k, LocalCache.Strength.STRONG);
    }

    /* access modifiers changed from: package-private */
    public LocalCache.Strength i() {
        return (LocalCache.Strength) MoreObjects.firstNonNull(this.l, LocalCache.Strength.STRONG);
    }

    public CacheBuilder<K, V> initialCapacity(int i2) {
        boolean z = true;
        Preconditions.checkState(this.f == -1, "initial capacity was already set to %s", this.f);
        if (i2 < 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.f = i2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public long j() {
        long j2 = this.m;
        if (j2 == -1) {
            return 0;
        }
        return j2;
    }

    /* access modifiers changed from: package-private */
    public long k() {
        long j2 = this.n;
        if (j2 == -1) {
            return 0;
        }
        return j2;
    }

    /* access modifiers changed from: package-private */
    public long l() {
        long j2 = this.o;
        if (j2 == -1) {
            return 0;
        }
        return j2;
    }

    /* access modifiers changed from: package-private */
    public <K1 extends K, V1 extends V> RemovalListener<K1, V1> m() {
        return (RemovalListener) MoreObjects.firstNonNull(this.r, NullListener.INSTANCE);
    }

    public CacheBuilder<K, V> maximumSize(long j2) {
        boolean z = true;
        Preconditions.checkState(this.h == -1, "maximum size was already set to %s", this.h);
        Preconditions.checkState(this.i == -1, "maximum weight was already set to %s", this.i);
        Preconditions.checkState(this.j == null, "maximum size can not be combined with weigher");
        if (j2 < 0) {
            z = false;
        }
        Preconditions.checkArgument(z, "maximum size must not be negative");
        this.h = j2;
        return this;
    }

    @GwtIncompatible
    public CacheBuilder<K, V> maximumWeight(long j2) {
        boolean z = true;
        Preconditions.checkState(this.i == -1, "maximum weight was already set to %s", this.i);
        Preconditions.checkState(this.h == -1, "maximum size was already set to %s", this.h);
        this.i = j2;
        if (j2 < 0) {
            z = false;
        }
        Preconditions.checkArgument(z, "maximum weight must not be negative");
        return this;
    }

    /* access modifiers changed from: package-private */
    public Supplier<? extends AbstractCache.StatsCounter> n() {
        return this.t;
    }

    public CacheBuilder<K, V> recordStats() {
        this.t = c;
        return this;
    }

    @GwtIncompatible
    public CacheBuilder<K, V> refreshAfterWrite(long j2, TimeUnit timeUnit) {
        Preconditions.checkNotNull(timeUnit);
        boolean z = true;
        Preconditions.checkState(this.o == -1, "refresh was already set to %s ns", this.o);
        if (j2 <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z, "duration must be positive: %s %s", j2, (Object) timeUnit);
        this.o = timeUnit.toNanos(j2);
        return this;
    }

    @CheckReturnValue
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> removalListener(RemovalListener<? super K1, ? super V1> removalListener) {
        Preconditions.checkState(this.r == null);
        this.r = (RemovalListener) Preconditions.checkNotNull(removalListener);
        return this;
    }

    @GwtIncompatible
    public CacheBuilder<K, V> softValues() {
        return b(LocalCache.Strength.SOFT);
    }

    public CacheBuilder<K, V> ticker(Ticker ticker) {
        Preconditions.checkState(this.s == null);
        this.s = (Ticker) Preconditions.checkNotNull(ticker);
        return this;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Object) this);
        int i2 = this.f;
        if (i2 != -1) {
            stringHelper.add("initialCapacity", i2);
        }
        int i3 = this.g;
        if (i3 != -1) {
            stringHelper.add("concurrencyLevel", i3);
        }
        long j2 = this.h;
        if (j2 != -1) {
            stringHelper.add("maximumSize", j2);
        }
        long j3 = this.i;
        if (j3 != -1) {
            stringHelper.add("maximumWeight", j3);
        }
        if (this.m != -1) {
            stringHelper.add("expireAfterWrite", (Object) this.m + "ns");
        }
        if (this.n != -1) {
            stringHelper.add("expireAfterAccess", (Object) this.n + "ns");
        }
        LocalCache.Strength strength = this.k;
        if (strength != null) {
            stringHelper.add("keyStrength", (Object) Ascii.toLowerCase(strength.toString()));
        }
        LocalCache.Strength strength2 = this.l;
        if (strength2 != null) {
            stringHelper.add("valueStrength", (Object) Ascii.toLowerCase(strength2.toString()));
        }
        if (this.p != null) {
            stringHelper.addValue((Object) "keyEquivalence");
        }
        if (this.q != null) {
            stringHelper.addValue((Object) "valueEquivalence");
        }
        if (this.r != null) {
            stringHelper.addValue((Object) "removalListener");
        }
        return stringHelper.toString();
    }

    @GwtIncompatible
    public CacheBuilder<K, V> weakKeys() {
        return a(LocalCache.Strength.WEAK);
    }

    @GwtIncompatible
    public CacheBuilder<K, V> weakValues() {
        return b(LocalCache.Strength.WEAK);
    }

    @GwtIncompatible
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> weigher(Weigher<? super K1, ? super V1> weigher) {
        boolean z = true;
        Preconditions.checkState(this.j == null);
        if (this.e) {
            if (this.h != -1) {
                z = false;
            }
            Preconditions.checkState(z, "weigher can not be combined with maximum size", this.h);
        }
        this.j = (Weigher) Preconditions.checkNotNull(weigher);
        return this;
    }
}
