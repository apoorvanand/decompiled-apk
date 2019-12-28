package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.cache.LocalCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@GwtIncompatible
public final class CacheBuilderSpec {
    private static final Splitter KEYS_SPLITTER = Splitter.on(',').trimResults();
    private static final Splitter KEY_VALUE_SPLITTER = Splitter.on('=').trimResults();
    private static final ImmutableMap<String, ValueParser> VALUE_PARSERS = ImmutableMap.builder().put("initialCapacity", new InitialCapacityParser()).put("maximumSize", new MaximumSizeParser()).put("maximumWeight", new MaximumWeightParser()).put("concurrencyLevel", new ConcurrencyLevelParser()).put("weakKeys", new KeyStrengthParser(LocalCache.Strength.WEAK)).put("softValues", new ValueStrengthParser(LocalCache.Strength.SOFT)).put("weakValues", new ValueStrengthParser(LocalCache.Strength.WEAK)).put("recordStats", new RecordStatsParser()).put("expireAfterAccess", new AccessDurationParser()).put("expireAfterWrite", new WriteDurationParser()).put("refreshAfterWrite", new RefreshDurationParser()).put("refreshInterval", new RefreshDurationParser()).build();
    @VisibleForTesting
    Integer a;
    @VisibleForTesting
    Long b;
    @VisibleForTesting
    Long c;
    @VisibleForTesting
    Integer d;
    @VisibleForTesting
    LocalCache.Strength e;
    @VisibleForTesting
    LocalCache.Strength f;
    @VisibleForTesting
    Boolean g;
    @VisibleForTesting
    long h;
    @VisibleForTesting
    TimeUnit i;
    @VisibleForTesting
    long j;
    @VisibleForTesting
    TimeUnit k;
    @VisibleForTesting
    long l;
    @VisibleForTesting
    TimeUnit m;
    private final String specification;

    static class AccessDurationParser extends DurationParser {
        AccessDurationParser() {
        }

        /* access modifiers changed from: protected */
        public void a(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.k == null, "expireAfterAccess already set");
            cacheBuilderSpec.j = j;
            cacheBuilderSpec.k = timeUnit;
        }
    }

    static class ConcurrencyLevelParser extends IntegerParser {
        ConcurrencyLevelParser() {
        }

        /* access modifiers changed from: protected */
        public void a(CacheBuilderSpec cacheBuilderSpec, int i) {
            Preconditions.checkArgument(cacheBuilderSpec.d == null, "concurrency level was already set to ", (Object) cacheBuilderSpec.d);
            cacheBuilderSpec.d = Integer.valueOf(i);
        }
    }

    static abstract class DurationParser implements ValueParser {
        DurationParser() {
        }

        /* access modifiers changed from: protected */
        public abstract void a(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit);

        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            TimeUnit timeUnit;
            Preconditions.checkArgument(str2 != null && !str2.isEmpty(), "value of key %s omitted", (Object) str);
            try {
                char charAt = str2.charAt(str2.length() - 1);
                if (charAt == 'd') {
                    timeUnit = TimeUnit.DAYS;
                } else if (charAt == 'h') {
                    timeUnit = TimeUnit.HOURS;
                } else if (charAt == 'm') {
                    timeUnit = TimeUnit.MINUTES;
                } else if (charAt == 's') {
                    timeUnit = TimeUnit.SECONDS;
                } else {
                    throw new IllegalArgumentException(CacheBuilderSpec.format("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", str, str2));
                }
                a(cacheBuilderSpec, Long.parseLong(str2.substring(0, str2.length() - 1)), timeUnit);
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2));
            }
        }
    }

    static class InitialCapacityParser extends IntegerParser {
        InitialCapacityParser() {
        }

        /* access modifiers changed from: protected */
        public void a(CacheBuilderSpec cacheBuilderSpec, int i) {
            Preconditions.checkArgument(cacheBuilderSpec.a == null, "initial capacity was already set to ", (Object) cacheBuilderSpec.a);
            cacheBuilderSpec.a = Integer.valueOf(i);
        }
    }

    static abstract class IntegerParser implements ValueParser {
        IntegerParser() {
        }

        /* access modifiers changed from: protected */
        public abstract void a(CacheBuilderSpec cacheBuilderSpec, int i);

        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument(str2 != null && !str2.isEmpty(), "value of key %s omitted", (Object) str);
            try {
                a(cacheBuilderSpec, Integer.parseInt(str2));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2), e);
            }
        }
    }

    static class KeyStrengthParser implements ValueParser {
        private final LocalCache.Strength strength;

        public KeyStrengthParser(LocalCache.Strength strength2) {
            this.strength = strength2;
        }

        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, @Nullable String str2) {
            boolean z = true;
            Preconditions.checkArgument(str2 == null, "key %s does not take values", (Object) str);
            if (cacheBuilderSpec.e != null) {
                z = false;
            }
            Preconditions.checkArgument(z, "%s was already set to %s", (Object) str, (Object) cacheBuilderSpec.e);
            cacheBuilderSpec.e = this.strength;
        }
    }

    static abstract class LongParser implements ValueParser {
        LongParser() {
        }

        /* access modifiers changed from: protected */
        public abstract void a(CacheBuilderSpec cacheBuilderSpec, long j);

        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument(str2 != null && !str2.isEmpty(), "value of key %s omitted", (Object) str);
            try {
                a(cacheBuilderSpec, Long.parseLong(str2));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2), e);
            }
        }
    }

    static class MaximumSizeParser extends LongParser {
        MaximumSizeParser() {
        }

        /* access modifiers changed from: protected */
        public void a(CacheBuilderSpec cacheBuilderSpec, long j) {
            boolean z = true;
            Preconditions.checkArgument(cacheBuilderSpec.b == null, "maximum size was already set to ", (Object) cacheBuilderSpec.b);
            if (cacheBuilderSpec.c != null) {
                z = false;
            }
            Preconditions.checkArgument(z, "maximum weight was already set to ", (Object) cacheBuilderSpec.c);
            cacheBuilderSpec.b = Long.valueOf(j);
        }
    }

    static class MaximumWeightParser extends LongParser {
        MaximumWeightParser() {
        }

        /* access modifiers changed from: protected */
        public void a(CacheBuilderSpec cacheBuilderSpec, long j) {
            boolean z = true;
            Preconditions.checkArgument(cacheBuilderSpec.c == null, "maximum weight was already set to ", (Object) cacheBuilderSpec.c);
            if (cacheBuilderSpec.b != null) {
                z = false;
            }
            Preconditions.checkArgument(z, "maximum size was already set to ", (Object) cacheBuilderSpec.b);
            cacheBuilderSpec.c = Long.valueOf(j);
        }
    }

    static class RecordStatsParser implements ValueParser {
        RecordStatsParser() {
        }

        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, @Nullable String str2) {
            boolean z = false;
            Preconditions.checkArgument(str2 == null, "recordStats does not take values");
            if (cacheBuilderSpec.g == null) {
                z = true;
            }
            Preconditions.checkArgument(z, "recordStats already set");
            cacheBuilderSpec.g = true;
        }
    }

    static class RefreshDurationParser extends DurationParser {
        RefreshDurationParser() {
        }

        /* access modifiers changed from: protected */
        public void a(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.m == null, "refreshAfterWrite already set");
            cacheBuilderSpec.l = j;
            cacheBuilderSpec.m = timeUnit;
        }
    }

    private interface ValueParser {
        void parse(CacheBuilderSpec cacheBuilderSpec, String str, @Nullable String str2);
    }

    static class ValueStrengthParser implements ValueParser {
        private final LocalCache.Strength strength;

        public ValueStrengthParser(LocalCache.Strength strength2) {
            this.strength = strength2;
        }

        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, @Nullable String str2) {
            boolean z = true;
            Preconditions.checkArgument(str2 == null, "key %s does not take values", (Object) str);
            if (cacheBuilderSpec.f != null) {
                z = false;
            }
            Preconditions.checkArgument(z, "%s was already set to %s", (Object) str, (Object) cacheBuilderSpec.f);
            cacheBuilderSpec.f = this.strength;
        }
    }

    static class WriteDurationParser extends DurationParser {
        WriteDurationParser() {
        }

        /* access modifiers changed from: protected */
        public void a(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.i == null, "expireAfterWrite already set");
            cacheBuilderSpec.h = j;
            cacheBuilderSpec.i = timeUnit;
        }
    }

    private CacheBuilderSpec(String str) {
        this.specification = str;
    }

    public static CacheBuilderSpec disableCaching() {
        return parse("maximumSize=0");
    }

    @Nullable
    private static Long durationInNanos(long j2, @Nullable TimeUnit timeUnit) {
        if (timeUnit == null) {
            return null;
        }
        return Long.valueOf(timeUnit.toNanos(j2));
    }

    /* access modifiers changed from: private */
    public static String format(String str, Object... objArr) {
        return String.format(Locale.ROOT, str, objArr);
    }

    public static CacheBuilderSpec parse(String str) {
        CacheBuilderSpec cacheBuilderSpec = new CacheBuilderSpec(str);
        if (!str.isEmpty()) {
            for (String next : KEYS_SPLITTER.split(str)) {
                ImmutableList<E> copyOf = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(next));
                Preconditions.checkArgument(!copyOf.isEmpty(), "blank key-value pair");
                boolean z = false;
                Preconditions.checkArgument(copyOf.size() <= 2, "key-value pair %s with more than one equals sign", (Object) next);
                String str2 = (String) copyOf.get(0);
                ValueParser valueParser = VALUE_PARSERS.get(str2);
                if (valueParser != null) {
                    z = true;
                }
                Preconditions.checkArgument(z, "unknown key %s", (Object) str2);
                valueParser.parse(cacheBuilderSpec, str2, copyOf.size() == 1 ? null : (String) copyOf.get(1));
            }
        }
        return cacheBuilderSpec;
    }

    /* access modifiers changed from: package-private */
    public CacheBuilder<Object, Object> a() {
        CacheBuilder<Object, Object> newBuilder = CacheBuilder.newBuilder();
        Integer num = this.a;
        if (num != null) {
            newBuilder.initialCapacity(num.intValue());
        }
        Long l2 = this.b;
        if (l2 != null) {
            newBuilder.maximumSize(l2.longValue());
        }
        Long l3 = this.c;
        if (l3 != null) {
            newBuilder.maximumWeight(l3.longValue());
        }
        Integer num2 = this.d;
        if (num2 != null) {
            newBuilder.concurrencyLevel(num2.intValue());
        }
        if (this.e != null) {
            if (AnonymousClass1.a[this.e.ordinal()] == 1) {
                newBuilder.weakKeys();
            } else {
                throw new AssertionError();
            }
        }
        if (this.f != null) {
            switch (this.f) {
                case WEAK:
                    newBuilder.weakValues();
                    break;
                case SOFT:
                    newBuilder.softValues();
                    break;
                default:
                    throw new AssertionError();
            }
        }
        Boolean bool = this.g;
        if (bool != null && bool.booleanValue()) {
            newBuilder.recordStats();
        }
        TimeUnit timeUnit = this.i;
        if (timeUnit != null) {
            newBuilder.expireAfterWrite(this.h, timeUnit);
        }
        TimeUnit timeUnit2 = this.k;
        if (timeUnit2 != null) {
            newBuilder.expireAfterAccess(this.j, timeUnit2);
        }
        TimeUnit timeUnit3 = this.m;
        if (timeUnit3 != null) {
            newBuilder.refreshAfterWrite(this.l, timeUnit3);
        }
        return newBuilder;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CacheBuilderSpec)) {
            return false;
        }
        CacheBuilderSpec cacheBuilderSpec = (CacheBuilderSpec) obj;
        return Objects.equal(this.a, cacheBuilderSpec.a) && Objects.equal(this.b, cacheBuilderSpec.b) && Objects.equal(this.c, cacheBuilderSpec.c) && Objects.equal(this.d, cacheBuilderSpec.d) && Objects.equal(this.e, cacheBuilderSpec.e) && Objects.equal(this.f, cacheBuilderSpec.f) && Objects.equal(this.g, cacheBuilderSpec.g) && Objects.equal(durationInNanos(this.h, this.i), durationInNanos(cacheBuilderSpec.h, cacheBuilderSpec.i)) && Objects.equal(durationInNanos(this.j, this.k), durationInNanos(cacheBuilderSpec.j, cacheBuilderSpec.k)) && Objects.equal(durationInNanos(this.l, this.m), durationInNanos(cacheBuilderSpec.l, cacheBuilderSpec.m));
    }

    public int hashCode() {
        return Objects.hashCode(this.a, this.b, this.c, this.d, this.e, this.f, this.g, durationInNanos(this.h, this.i), durationInNanos(this.j, this.k), durationInNanos(this.l, this.m));
    }

    public String toParsableString() {
        return this.specification;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).addValue((Object) toParsableString()).toString();
    }
}
