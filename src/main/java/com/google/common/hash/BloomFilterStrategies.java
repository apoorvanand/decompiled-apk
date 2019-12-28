package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.BloomFilter;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.Nullable;
import kotlin.jvm.internal.LongCompanionObject;

enum BloomFilterStrategies implements BloomFilter.Strategy {
    MURMUR128_MITZ_32 {
        public <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, BitArray bitArray) {
            long a = bitArray.a();
            long asLong = Hashing.murmur3_128().hashObject(t, funnel).asLong();
            int i2 = (int) asLong;
            int i3 = (int) (asLong >>> 32);
            for (int i4 = 1; i4 <= i; i4++) {
                int i5 = (i4 * i3) + i2;
                if (i5 < 0) {
                    i5 = ~i5;
                }
                if (!bitArray.b(((long) i5) % a)) {
                    return false;
                }
            }
            return true;
        }

        public <T> boolean put(T t, Funnel<? super T> funnel, int i, BitArray bitArray) {
            long a = bitArray.a();
            long asLong = Hashing.murmur3_128().hashObject(t, funnel).asLong();
            int i2 = (int) asLong;
            int i3 = (int) (asLong >>> 32);
            boolean z = false;
            for (int i4 = 1; i4 <= i; i4++) {
                int i5 = (i4 * i3) + i2;
                if (i5 < 0) {
                    i5 = ~i5;
                }
                z |= bitArray.a(((long) i5) % a);
            }
            return z;
        }
    },
    MURMUR128_MITZ_64 {
        private long lowerEight(byte[] bArr) {
            return Longs.fromBytes(bArr[7], bArr[6], bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]);
        }

        private long upperEight(byte[] bArr) {
            return Longs.fromBytes(bArr[15], bArr[14], bArr[13], bArr[12], bArr[11], bArr[10], bArr[9], bArr[8]);
        }

        public <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, BitArray bitArray) {
            long a = bitArray.a();
            byte[] a2 = Hashing.murmur3_128().hashObject(t, funnel).a();
            long lowerEight = lowerEight(a2);
            long upperEight = upperEight(a2);
            long j = lowerEight;
            for (int i2 = 0; i2 < i; i2++) {
                if (!bitArray.b((LongCompanionObject.MAX_VALUE & j) % a)) {
                    return false;
                }
                j += upperEight;
            }
            return true;
        }

        public <T> boolean put(T t, Funnel<? super T> funnel, int i, BitArray bitArray) {
            long a = bitArray.a();
            byte[] a2 = Hashing.murmur3_128().hashObject(t, funnel).a();
            long lowerEight = lowerEight(a2);
            long upperEight = upperEight(a2);
            long j = lowerEight;
            boolean z = false;
            for (int i2 = 0; i2 < i; i2++) {
                z |= bitArray.a((LongCompanionObject.MAX_VALUE & j) % a);
                j += upperEight;
            }
            return z;
        }
    };

    static final class BitArray {
        final long[] a;
        long b;

        BitArray(long j) {
            this(new long[Ints.checkedCast(LongMath.divide(j, 64, RoundingMode.CEILING))]);
        }

        BitArray(long[] jArr) {
            Preconditions.checkArgument(jArr.length > 0, "data length is zero!");
            this.a = jArr;
            long j = 0;
            for (long bitCount : jArr) {
                j += (long) Long.bitCount(bitCount);
            }
            this.b = j;
        }

        /* access modifiers changed from: package-private */
        public long a() {
            return ((long) this.a.length) * 64;
        }

        /* access modifiers changed from: package-private */
        public void a(BitArray bitArray) {
            int i = 0;
            Preconditions.checkArgument(this.a.length == bitArray.a.length, "BitArrays must be of equal length (%s != %s)", this.a.length, bitArray.a.length);
            this.b = 0;
            while (true) {
                long[] jArr = this.a;
                if (i < jArr.length) {
                    jArr[i] = jArr[i] | bitArray.a[i];
                    this.b += (long) Long.bitCount(jArr[i]);
                    i++;
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(long j) {
            if (b(j)) {
                return false;
            }
            long[] jArr = this.a;
            int i = (int) (j >>> 6);
            jArr[i] = (1 << ((int) j)) | jArr[i];
            this.b++;
            return true;
        }

        /* access modifiers changed from: package-private */
        public long b() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public boolean b(long j) {
            return ((1 << ((int) j)) & this.a[(int) (j >>> 6)]) != 0;
        }

        /* access modifiers changed from: package-private */
        public BitArray c() {
            return new BitArray((long[]) this.a.clone());
        }

        public boolean equals(@Nullable Object obj) {
            if (obj instanceof BitArray) {
                return Arrays.equals(this.a, ((BitArray) obj).a);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.a);
        }
    }
}
