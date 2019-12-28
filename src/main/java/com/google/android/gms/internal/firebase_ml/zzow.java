package com.google.android.gms.internal.firebase_ml;

import android.annotation.TargetApi;
import android.media.Image;
import androidx.annotation.NonNull;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public final class zzow {
    private static final zzow zzavi = new zzow();

    private zzow() {
    }

    @TargetApi(19)
    public static ByteBuffer zza(Image.Plane[] planeArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr = new byte[(((i3 / 4) * 2) + i3)];
        ByteBuffer buffer = planeArr[1].getBuffer();
        ByteBuffer buffer2 = planeArr[2].getBuffer();
        int position = buffer2.position();
        int limit = buffer.limit();
        buffer2.position(position + 1);
        buffer.limit(limit - 1);
        int i4 = (i3 * 2) / 4;
        boolean z = buffer2.remaining() == i4 + -2 && buffer2.compareTo(buffer) == 0;
        buffer2.position(position);
        buffer.limit(limit);
        if (z) {
            planeArr[0].getBuffer().get(bArr, 0, i3);
            ByteBuffer buffer3 = planeArr[1].getBuffer();
            planeArr[2].getBuffer().get(bArr, i3, 1);
            buffer3.get(bArr, i3 + 1, i4 - 1);
        } else {
            int i5 = i;
            int i6 = i2;
            byte[] bArr2 = bArr;
            zza(planeArr[0], i5, i6, bArr2, 0, 1);
            zza(planeArr[1], i5, i6, bArr2, i3 + 1, 2);
            zza(planeArr[2], i, i2, bArr, i3, 2);
        }
        return ByteBuffer.wrap(bArr);
    }

    @TargetApi(19)
    private static void zza(Image.Plane plane, int i, int i2, byte[] bArr, int i3, int i4) {
        ByteBuffer buffer = plane.getBuffer();
        int position = buffer.position();
        int remaining = ((buffer.remaining() + plane.getRowStride()) - 1) / plane.getRowStride();
        int i5 = i / (i2 / remaining);
        int i6 = i3;
        int i7 = 0;
        int i8 = 0;
        while (i7 < remaining) {
            int i9 = i8;
            int i10 = i6;
            for (int i11 = 0; i11 < i5; i11++) {
                bArr[i10] = buffer.get(i9);
                i10 += i4;
                i9 += plane.getPixelStride();
            }
            i8 += plane.getRowStride();
            i7++;
            i6 = i10;
        }
        buffer.position(position);
    }

    private static /* synthetic */ void zza(Throwable th, ByteArrayOutputStream byteArrayOutputStream) {
        if (th != null) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                zzli.zza(th, th2);
            }
        } else {
            byteArrayOutputStream.close();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        r4 = r2;
        r2 = r5;
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0016, code lost:
        r2 = null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] zza(@androidx.annotation.NonNull android.graphics.Bitmap r5) {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0022 }
            r1.<init>()     // Catch:{ IOException -> 0x0022 }
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Throwable -> 0x0018, all -> 0x0015 }
            r3 = 100
            r5.compress(r2, r3, r1)     // Catch:{ Throwable -> 0x0018, all -> 0x0015 }
            byte[] r5 = r1.toByteArray()     // Catch:{ Throwable -> 0x0018, all -> 0x0015 }
            zza(r0, r1)     // Catch:{ IOException -> 0x0023 }
            goto L_0x002a
        L_0x0015:
            r5 = move-exception
            r2 = r0
            goto L_0x001e
        L_0x0018:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x001a }
        L_0x001a:
            r2 = move-exception
            r4 = r2
            r2 = r5
            r5 = r4
        L_0x001e:
            zza(r2, r1)     // Catch:{ IOException -> 0x0022 }
            throw r5     // Catch:{ IOException -> 0x0022 }
        L_0x0022:
            r5 = r0
        L_0x0023:
            java.lang.String r0 = "ImageConvertUtils"
            java.lang.String r1 = "Error closing ByteArrayOutputStream"
            android.util.Log.w(r0, r1)
        L_0x002a:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzow.zza(android.graphics.Bitmap):byte[]");
    }

    public static byte[] zza(@NonNull ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        byte[] bArr = new byte[byteBuffer.limit()];
        byteBuffer.get(bArr, 0, bArr.length);
        return bArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        r7 = r10;
        r10 = r9;
        r9 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0025, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        r10 = null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] zza(@androidx.annotation.NonNull byte[] r8, int r9, int r10) {
        /*
            android.graphics.YuvImage r6 = new android.graphics.YuvImage
            r2 = 17
            r5 = 0
            r0 = r6
            r1 = r8
            r3 = r9
            r4 = r10
            r0.<init>(r1, r2, r3, r4, r5)
            r8 = 0
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0032 }
            r0.<init>()     // Catch:{ IOException -> 0x0032 }
            android.graphics.Rect r1 = new android.graphics.Rect     // Catch:{ Throwable -> 0x0028, all -> 0x0025 }
            r2 = 0
            r1.<init>(r2, r2, r9, r10)     // Catch:{ Throwable -> 0x0028, all -> 0x0025 }
            r9 = 100
            r6.compressToJpeg(r1, r9, r0)     // Catch:{ Throwable -> 0x0028, all -> 0x0025 }
            byte[] r9 = r0.toByteArray()     // Catch:{ Throwable -> 0x0028, all -> 0x0025 }
            zza(r8, r0)     // Catch:{ IOException -> 0x0033 }
            goto L_0x003a
        L_0x0025:
            r9 = move-exception
            r10 = r8
            goto L_0x002e
        L_0x0028:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x002a }
        L_0x002a:
            r10 = move-exception
            r7 = r10
            r10 = r9
            r9 = r7
        L_0x002e:
            zza(r10, r0)     // Catch:{ IOException -> 0x0032 }
            throw r9     // Catch:{ IOException -> 0x0032 }
        L_0x0032:
            r9 = r8
        L_0x0033:
            java.lang.String r8 = "ImageConvertUtils"
            java.lang.String r10 = "Error closing ByteArrayOutputStream"
            android.util.Log.w(r8, r10)
        L_0x003a:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzow.zza(byte[], int, int):byte[]");
    }

    public static byte[] zzg(byte[] bArr) {
        int length = bArr.length;
        int i = length / 6;
        byte[] bArr2 = new byte[length];
        int i2 = i << 2;
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        for (int i3 = 0; i3 < (i << 1); i3++) {
            bArr2[i2 + i3] = bArr[((i3 % 2) * i) + i2 + (i3 / 2)];
        }
        return bArr2;
    }

    public static zzow zzly() {
        return zzavi;
    }
}
