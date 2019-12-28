package com.google.common.hash;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

final class FarmHashFingerprint64 extends AbstractNonStreamingHashFunction {
    private static final long K0 = -4348849565147123417L;
    private static final long K1 = -5435081209227447693L;
    private static final long K2 = -7286425919675154353L;

    FarmHashFingerprint64() {
    }

    @VisibleForTesting
    static long a(byte[] bArr, int i, int i2) {
        return i2 <= 32 ? i2 <= 16 ? hashLength0to16(bArr, i, i2) : hashLength17to32(bArr, i, i2) : i2 <= 64 ? hashLength33To64(bArr, i, i2) : hashLength65Plus(bArr, i, i2);
    }

    private static long hashLength0to16(byte[] bArr, int i, int i2) {
        if (i2 >= 8) {
            long j = ((long) (i2 * 2)) + K2;
            long a = LittleEndianByteArray.a(bArr, i) + K2;
            long a2 = LittleEndianByteArray.a(bArr, (i + i2) - 8);
            return hashLength16((Long.rotateRight(a2, 37) * j) + a, (Long.rotateRight(a, 25) + a2) * j, j);
        } else if (i2 >= 4) {
            return hashLength16(((long) i2) + ((((long) LittleEndianByteArray.b(bArr, i)) & 4294967295L) << 3), ((long) LittleEndianByteArray.b(bArr, (i + i2) - 4)) & 4294967295L, ((long) (i2 * 2)) + K2);
        } else if (i2 <= 0) {
            return K2;
        } else {
            return shiftMix((((long) ((bArr[i] & 255) + ((bArr[(i2 >> 1) + i] & 255) << 8))) * K2) ^ (((long) (i2 + ((bArr[i + (i2 - 1)] & 255) << 2))) * K0)) * K2;
        }
    }

    private static long hashLength16(long j, long j2, long j3) {
        long j4 = (j ^ j2) * j3;
        long j5 = ((j4 ^ (j4 >>> 47)) ^ j2) * j3;
        return (j5 ^ (j5 >>> 47)) * j3;
    }

    private static long hashLength17to32(byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr;
        long j = ((long) (i2 * 2)) + K2;
        long a = LittleEndianByteArray.a(bArr, i) * K1;
        long a2 = LittleEndianByteArray.a(bArr2, i + 8);
        int i3 = i + i2;
        long a3 = LittleEndianByteArray.a(bArr2, i3 - 8) * j;
        return hashLength16((LittleEndianByteArray.a(bArr2, i3 - 16) * K2) + Long.rotateRight(a + a2, 43) + Long.rotateRight(a3, 30), a + Long.rotateRight(a2 + K2, 18) + a3, j);
    }

    private static long hashLength33To64(byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr;
        long j = ((long) (i2 * 2)) + K2;
        long a = LittleEndianByteArray.a(bArr, i) * K2;
        long a2 = LittleEndianByteArray.a(bArr2, i + 8);
        int i3 = i + i2;
        long a3 = LittleEndianByteArray.a(bArr2, i3 - 8) * j;
        long rotateRight = Long.rotateRight(a + a2, 43) + Long.rotateRight(a3, 30) + (LittleEndianByteArray.a(bArr2, i3 - 16) * K2);
        long hashLength16 = hashLength16(rotateRight, a3 + Long.rotateRight(a2 + K2, 18) + a, j);
        long a4 = LittleEndianByteArray.a(bArr2, i + 16) * j;
        long a5 = LittleEndianByteArray.a(bArr2, i + 24);
        long a6 = (rotateRight + LittleEndianByteArray.a(bArr2, i3 - 32)) * j;
        return hashLength16(((hashLength16 + LittleEndianByteArray.a(bArr2, i3 - 24)) * j) + Long.rotateRight(a4 + a5, 43) + Long.rotateRight(a6, 30), a4 + Long.rotateRight(a5 + a, 18) + a6, j);
    }

    private static long hashLength65Plus(byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr;
        long shiftMix = shiftMix(-7956866745689871395L) * K2;
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        long a = 95310865018149119L + LittleEndianByteArray.a(bArr, i);
        int i3 = i2 - 1;
        int i4 = i + ((i3 / 64) * 64);
        int i5 = i3 & 63;
        int i6 = (i4 + i5) - 63;
        long j = 2480279821605975764L;
        int i7 = i;
        while (true) {
            long rotateRight = Long.rotateRight(a + j + jArr[0] + LittleEndianByteArray.a(bArr2, i7 + 8), 37) * K1;
            long rotateRight2 = Long.rotateRight(j + jArr[1] + LittleEndianByteArray.a(bArr2, i7 + 48), 42) * K1;
            long j2 = rotateRight ^ jArr2[1];
            long a2 = rotateRight2 + jArr[0] + LittleEndianByteArray.a(bArr2, i7 + 40);
            long rotateRight3 = Long.rotateRight(shiftMix + jArr2[0], 33) * K1;
            weakHashLength32WithSeeds(bArr, i7, jArr[1] * K1, j2 + jArr2[0], jArr);
            weakHashLength32WithSeeds(bArr, i7 + 32, rotateRight3 + jArr2[1], a2 + LittleEndianByteArray.a(bArr2, i7 + 16), jArr2);
            i7 += 64;
            if (i7 == i4) {
                long j3 = ((j2 & 255) << 1) + K1;
                jArr2[0] = jArr2[0] + ((long) i5);
                jArr[0] = jArr[0] + jArr2[0];
                jArr2[0] = jArr2[0] + jArr[0];
                long rotateRight4 = (Long.rotateRight(((rotateRight3 + a2) + jArr[0]) + LittleEndianByteArray.a(bArr2, i6 + 8), 37) * j3) ^ (jArr2[1] * 9);
                long rotateRight5 = (Long.rotateRight(a2 + jArr[1] + LittleEndianByteArray.a(bArr2, i6 + 48), 42) * j3) + (jArr[0] * 9) + LittleEndianByteArray.a(bArr2, i6 + 40);
                long rotateRight6 = Long.rotateRight(j2 + jArr2[0], 33) * j3;
                byte[] bArr3 = bArr;
                weakHashLength32WithSeeds(bArr3, i6, jArr[1] * j3, rotateRight4 + jArr2[0], jArr);
                weakHashLength32WithSeeds(bArr3, i6 + 32, rotateRight6 + jArr2[1], LittleEndianByteArray.a(bArr2, i6 + 16) + rotateRight5, jArr2);
                long j4 = j3;
                return hashLength16(hashLength16(jArr[0], jArr2[0], j4) + (shiftMix(rotateRight5) * K0) + rotateRight4, hashLength16(jArr[1], jArr2[1], j4) + rotateRight6, j4);
            }
            shiftMix = j2;
            j = a2;
            a = rotateRight3;
        }
    }

    private static long shiftMix(long j) {
        return j ^ (j >>> 47);
    }

    private static void weakHashLength32WithSeeds(byte[] bArr, int i, long j, long j2, long[] jArr) {
        long a = LittleEndianByteArray.a(bArr, i);
        long a2 = LittleEndianByteArray.a(bArr, i + 8);
        long a3 = LittleEndianByteArray.a(bArr, i + 16);
        long a4 = LittleEndianByteArray.a(bArr, i + 24);
        long j3 = j + a;
        long j4 = a2 + j3 + a3;
        jArr[0] = j4 + a4;
        jArr[1] = Long.rotateRight(j2 + j3 + a4, 21) + Long.rotateRight(j4, 44) + j3;
    }

    public int bits() {
        return 64;
    }

    public HashCode hashBytes(byte[] bArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        return HashCode.fromLong(a(bArr, i, i2));
    }

    public String toString() {
        return "Hashing.farmHashFingerprint64()";
    }
}
