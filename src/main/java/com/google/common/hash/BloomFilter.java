package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.hash.BloomFilterStrategies;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.annotation.Nullable;

@Beta
public final class BloomFilter<T> implements Predicate<T>, Serializable {
    /* access modifiers changed from: private */
    public final BloomFilterStrategies.BitArray bits;
    /* access modifiers changed from: private */
    public final Funnel<? super T> funnel;
    /* access modifiers changed from: private */
    public final int numHashFunctions;
    /* access modifiers changed from: private */
    public final Strategy strategy;

    private static class SerialForm<T> implements Serializable {
        private static final long serialVersionUID = 1;
        final long[] a;
        final int b;
        final Funnel<? super T> c;
        final Strategy d;

        SerialForm(BloomFilter<T> bloomFilter) {
            this.a = bloomFilter.bits.a;
            this.b = bloomFilter.numHashFunctions;
            this.c = bloomFilter.funnel;
            this.d = bloomFilter.strategy;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return new BloomFilter(new BloomFilterStrategies.BitArray(this.a), this.b, this.c, this.d);
        }
    }

    interface Strategy extends Serializable {
        <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, BloomFilterStrategies.BitArray bitArray);

        int ordinal();

        <T> boolean put(T t, Funnel<? super T> funnel, int i, BloomFilterStrategies.BitArray bitArray);
    }

    private BloomFilter(BloomFilterStrategies.BitArray bitArray, int i, Funnel<? super T> funnel2, Strategy strategy2) {
        boolean z = true;
        Preconditions.checkArgument(i > 0, "numHashFunctions (%s) must be > 0", i);
        Preconditions.checkArgument(i > 255 ? false : z, "numHashFunctions (%s) must be <= 255", i);
        this.bits = (BloomFilterStrategies.BitArray) Preconditions.checkNotNull(bitArray);
        this.numHashFunctions = i;
        this.funnel = (Funnel) Preconditions.checkNotNull(funnel2);
        this.strategy = (Strategy) Preconditions.checkNotNull(strategy2);
    }

    @VisibleForTesting
    static int a(long j, long j2) {
        return Math.max(1, (int) Math.round((((double) j2) / ((double) j)) * Math.log(2.0d)));
    }

    @VisibleForTesting
    static long a(long j, double d) {
        if (d == 0.0d) {
            d = Double.MIN_VALUE;
        }
        return (long) ((((double) (-j)) * Math.log(d)) / (Math.log(2.0d) * Math.log(2.0d)));
    }

    @VisibleForTesting
    static <T> BloomFilter<T> a(Funnel<? super T> funnel2, long j, double d, Strategy strategy2) {
        Preconditions.checkNotNull(funnel2);
        boolean z = true;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        Preconditions.checkArgument(i >= 0, "Expected insertions (%s) must be >= 0", j);
        Preconditions.checkArgument(d > 0.0d, "False positive probability (%s) must be > 0.0", (Object) Double.valueOf(d));
        if (d >= 1.0d) {
            z = false;
        }
        Preconditions.checkArgument(z, "False positive probability (%s) must be < 1.0", (Object) Double.valueOf(d));
        Preconditions.checkNotNull(strategy2);
        if (i == 0) {
            j = 1;
        }
        long a = a(j, d);
        try {
            return new BloomFilter<>(new BloomFilterStrategies.BitArray(a), a(j, a), funnel2, strategy2);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not create BloomFilter of " + a + " bits", e);
        }
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, int i) {
        return create(funnel2, (long) i);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, int i, double d) {
        return create(funnel2, (long) i, d);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, long j) {
        return create(funnel2, j, 0.03d);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, long j, double d) {
        return a(funnel2, j, d, BloomFilterStrategies.MURMUR128_MITZ_64);
    }

    public static <T> BloomFilter<T> readFrom(InputStream inputStream, Funnel<T> funnel2) {
        byte b;
        int i;
        Preconditions.checkNotNull(inputStream, "InputStream");
        Preconditions.checkNotNull(funnel2, "Funnel");
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            b = dataInputStream.readByte();
            try {
                i = UnsignedBytes.toInt(dataInputStream.readByte());
            } catch (RuntimeException e) {
                e = e;
                i = -1;
                throw new IOException("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: " + b + " numHashFunctions: " + i + " dataLength: " + -1, e);
            }
            try {
                int readInt = dataInputStream.readInt();
                BloomFilterStrategies bloomFilterStrategies = BloomFilterStrategies.values()[b];
                long[] jArr = new long[readInt];
                for (int i2 = 0; i2 < jArr.length; i2++) {
                    jArr[i2] = dataInputStream.readLong();
                }
                return new BloomFilter<>(new BloomFilterStrategies.BitArray(jArr), i, funnel2, bloomFilterStrategies);
            } catch (RuntimeException e2) {
                e = e2;
                throw new IOException("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: " + b + " numHashFunctions: " + i + " dataLength: " + -1, e);
            }
        } catch (RuntimeException e3) {
            e = e3;
            b = -1;
            i = -1;
            throw new IOException("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: " + b + " numHashFunctions: " + i + " dataLength: " + -1, e);
        }
    }

    private Object writeReplace() {
        return new SerialForm(this);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public long a() {
        return this.bits.a();
    }

    @Deprecated
    public boolean apply(T t) {
        return mightContain(t);
    }

    public BloomFilter<T> copy() {
        return new BloomFilter<>(this.bits.c(), this.numHashFunctions, this.funnel, this.strategy);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BloomFilter)) {
            return false;
        }
        BloomFilter bloomFilter = (BloomFilter) obj;
        return this.numHashFunctions == bloomFilter.numHashFunctions && this.funnel.equals(bloomFilter.funnel) && this.bits.equals(bloomFilter.bits) && this.strategy.equals(bloomFilter.strategy);
    }

    public double expectedFpp() {
        return Math.pow(((double) this.bits.b()) / ((double) a()), (double) this.numHashFunctions);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.numHashFunctions), this.funnel, this.strategy, this.bits);
    }

    public boolean isCompatible(BloomFilter<T> bloomFilter) {
        Preconditions.checkNotNull(bloomFilter);
        return this != bloomFilter && this.numHashFunctions == bloomFilter.numHashFunctions && a() == bloomFilter.a() && this.strategy.equals(bloomFilter.strategy) && this.funnel.equals(bloomFilter.funnel);
    }

    public boolean mightContain(T t) {
        return this.strategy.mightContain(t, this.funnel, this.numHashFunctions, this.bits);
    }

    @CanIgnoreReturnValue
    public boolean put(T t) {
        return this.strategy.put(t, this.funnel, this.numHashFunctions, this.bits);
    }

    public void putAll(BloomFilter<T> bloomFilter) {
        Preconditions.checkNotNull(bloomFilter);
        Preconditions.checkArgument(this != bloomFilter, "Cannot combine a BloomFilter with itself.");
        Preconditions.checkArgument(this.numHashFunctions == bloomFilter.numHashFunctions, "BloomFilters must have the same number of hash functions (%s != %s)", this.numHashFunctions, bloomFilter.numHashFunctions);
        Preconditions.checkArgument(a() == bloomFilter.a(), "BloomFilters must have the same size underlying bit arrays (%s != %s)", a(), bloomFilter.a());
        Preconditions.checkArgument(this.strategy.equals(bloomFilter.strategy), "BloomFilters must have equal strategies (%s != %s)", (Object) this.strategy, (Object) bloomFilter.strategy);
        Preconditions.checkArgument(this.funnel.equals(bloomFilter.funnel), "BloomFilters must have equal funnels (%s != %s)", (Object) this.funnel, (Object) bloomFilter.funnel);
        this.bits.a(bloomFilter.bits);
    }

    public void writeTo(OutputStream outputStream) {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeByte(SignedBytes.checkedCast((long) this.strategy.ordinal()));
        dataOutputStream.writeByte(UnsignedBytes.checkedCast((long) this.numHashFunctions));
        dataOutputStream.writeInt(this.bits.a.length);
        for (long writeLong : this.bits.a) {
            dataOutputStream.writeLong(writeLong);
        }
    }
}
