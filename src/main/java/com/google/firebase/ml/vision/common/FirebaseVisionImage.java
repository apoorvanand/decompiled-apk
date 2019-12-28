package com.google.firebase.ml.vision.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzou;
import com.google.android.gms.internal.firebase_ml.zzow;
import com.google.android.gms.internal.firebase_ml.zzox;
import com.google.android.gms.vision.Frame;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FirebaseVisionImage {
    private static zzow zzaut = zzow.zzly();
    @Nullable
    private volatile Bitmap zzauu;
    @Nullable
    private volatile ByteBuffer zzauv;
    @Nullable
    private volatile FirebaseVisionImageMetadata zzauw;
    @Nullable
    private volatile Frame zzaux;
    @Nullable
    private volatile byte[] zzauy;
    private final long zzauz;

    private FirebaseVisionImage(@NonNull Bitmap bitmap) {
        this.zzauz = SystemClock.elapsedRealtime();
        this.zzauu = (Bitmap) Preconditions.checkNotNull(bitmap);
    }

    private FirebaseVisionImage(@NonNull ByteBuffer byteBuffer, @NonNull FirebaseVisionImageMetadata firebaseVisionImageMetadata) {
        this.zzauz = SystemClock.elapsedRealtime();
        this.zzauv = (ByteBuffer) Preconditions.checkNotNull(byteBuffer);
        this.zzauw = (FirebaseVisionImageMetadata) Preconditions.checkNotNull(firebaseVisionImageMetadata);
    }

    private FirebaseVisionImage(byte[] bArr) {
        this.zzauz = SystemClock.elapsedRealtime();
        this.zzauy = (byte[]) Preconditions.checkNotNull(bArr);
    }

    private FirebaseVisionImage(@NonNull byte[] bArr, @NonNull FirebaseVisionImageMetadata firebaseVisionImageMetadata) {
        this(ByteBuffer.wrap((byte[]) Preconditions.checkNotNull(bArr)), firebaseVisionImageMetadata);
    }

    public static FirebaseVisionImage fromBitmap(@NonNull Bitmap bitmap) {
        return new FirebaseVisionImage(bitmap);
    }

    public static FirebaseVisionImage fromByteArray(@NonNull byte[] bArr, @NonNull FirebaseVisionImageMetadata firebaseVisionImageMetadata) {
        return new FirebaseVisionImage(bArr, firebaseVisionImageMetadata);
    }

    public static FirebaseVisionImage fromByteBuffer(@NonNull ByteBuffer byteBuffer, @NonNull FirebaseVisionImageMetadata firebaseVisionImageMetadata) {
        return new FirebaseVisionImage(byteBuffer, firebaseVisionImageMetadata);
    }

    public static FirebaseVisionImage fromFilePath(@NonNull Context context, @NonNull Uri uri) {
        Preconditions.checkNotNull(context, "Please provide a valid Context");
        Preconditions.checkNotNull(uri, "Please provide a valid imageUri");
        zzox.zzlz();
        return new FirebaseVisionImage(zzox.zza(context.getContentResolver(), uri, 1024));
    }

    @RequiresApi(19)
    @TargetApi(19)
    public static FirebaseVisionImage fromMediaImage(@NonNull Image image, int i) {
        Preconditions.checkNotNull(image, "Please provide a valid image");
        Preconditions.checkArgument(image.getFormat() == 256 || image.getFormat() == 35, "Only JPEG and YUV_420_888 are supported now");
        Image.Plane[] planes = image.getPlanes();
        if (image.getFormat() != 256) {
            return new FirebaseVisionImage(zzow.zza(planes, image.getWidth(), image.getHeight()), new FirebaseVisionImageMetadata.Builder().setFormat(17).setWidth(image.getWidth()).setHeight(image.getHeight()).setRotation(i).build());
        }
        if (planes == null || planes.length != 1) {
            throw new IllegalArgumentException("Unexpected image format, JPEG should have exactly 1 image plane");
        }
        ByteBuffer buffer = planes[0].getBuffer();
        byte[] bArr = new byte[buffer.remaining()];
        buffer.get(bArr);
        return i == 0 ? new FirebaseVisionImage(bArr) : new FirebaseVisionImage(zza(BitmapFactory.decodeByteArray(bArr, 0, bArr.length), i));
    }

    private static Bitmap zza(Bitmap bitmap, int i) {
        int zzbd = zzou.zzbd(i);
        if (zzbd == 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) zzbd);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005b, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final byte[] zzad(boolean r3) {
        /*
            r2 = this;
            byte[] r0 = r2.zzauy
            if (r0 == 0) goto L_0x0007
            byte[] r3 = r2.zzauy
            return r3
        L_0x0007:
            monitor-enter(r2)
            byte[] r0 = r2.zzauy     // Catch:{ all -> 0x0068 }
            if (r0 == 0) goto L_0x0010
            byte[] r3 = r2.zzauy     // Catch:{ all -> 0x0068 }
            monitor-exit(r2)     // Catch:{ all -> 0x0068 }
            return r3
        L_0x0010:
            java.nio.ByteBuffer r0 = r2.zzauv     // Catch:{ all -> 0x0068 }
            if (r0 == 0) goto L_0x005c
            if (r3 == 0) goto L_0x001e
            com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata r3 = r2.zzauw     // Catch:{ all -> 0x0068 }
            int r3 = r3.getRotation()     // Catch:{ all -> 0x0068 }
            if (r3 != 0) goto L_0x005c
        L_0x001e:
            java.nio.ByteBuffer r3 = r2.zzauv     // Catch:{ all -> 0x0068 }
            byte[] r3 = com.google.android.gms.internal.firebase_ml.zzow.zza((java.nio.ByteBuffer) r3)     // Catch:{ all -> 0x0068 }
            com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata r0 = r2.zzauw     // Catch:{ all -> 0x0068 }
            int r0 = r0.getFormat()     // Catch:{ all -> 0x0068 }
            r1 = 17
            if (r0 == r1) goto L_0x0040
            r1 = 842094169(0x32315659, float:1.0322389E-8)
            if (r0 != r1) goto L_0x0038
            byte[] r3 = com.google.android.gms.internal.firebase_ml.zzow.zzg(r3)     // Catch:{ all -> 0x0068 }
            goto L_0x0040
        L_0x0038:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0068 }
            java.lang.String r0 = "Must be one of: IMAGE_FORMAT_NV21, IMAGE_FORMAT_YV12"
            r3.<init>(r0)     // Catch:{ all -> 0x0068 }
            throw r3     // Catch:{ all -> 0x0068 }
        L_0x0040:
            com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata r0 = r2.zzauw     // Catch:{ all -> 0x0068 }
            int r0 = r0.getWidth()     // Catch:{ all -> 0x0068 }
            com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata r1 = r2.zzauw     // Catch:{ all -> 0x0068 }
            int r1 = r1.getHeight()     // Catch:{ all -> 0x0068 }
            byte[] r3 = com.google.android.gms.internal.firebase_ml.zzow.zza((byte[]) r3, (int) r0, (int) r1)     // Catch:{ all -> 0x0068 }
            com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata r0 = r2.zzauw     // Catch:{ all -> 0x0068 }
            int r0 = r0.getRotation()     // Catch:{ all -> 0x0068 }
            if (r0 != 0) goto L_0x005a
            r2.zzauy = r3     // Catch:{ all -> 0x0068 }
        L_0x005a:
            monitor-exit(r2)     // Catch:{ all -> 0x0068 }
            return r3
        L_0x005c:
            android.graphics.Bitmap r3 = r2.zzlx()     // Catch:{ all -> 0x0068 }
            byte[] r3 = com.google.android.gms.internal.firebase_ml.zzow.zza((android.graphics.Bitmap) r3)     // Catch:{ all -> 0x0068 }
            r2.zzauy = r3     // Catch:{ all -> 0x0068 }
            monitor-exit(r2)     // Catch:{ all -> 0x0068 }
            return r3
        L_0x0068:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0068 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.ml.vision.common.FirebaseVisionImage.zzad(boolean):byte[]");
    }

    private final Bitmap zzlx() {
        if (this.zzauu != null) {
            return this.zzauu;
        }
        synchronized (this) {
            if (this.zzauu == null) {
                byte[] zzad = zzad(false);
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(zzad, 0, zzad.length);
                if (this.zzauw != null) {
                    decodeByteArray = zza(decodeByteArray, this.zzauw.getRotation());
                }
                this.zzauu = decodeByteArray;
            }
        }
        return this.zzauu;
    }

    @KeepForSdk
    public Bitmap getBitmapForDebugging() {
        return zzlx();
    }

    public final synchronized Frame zza(boolean z, boolean z2) {
        int i = 1;
        Preconditions.checkArgument(!z || !z2, "Can't restrict to bitmap-only and NV21 byte buffer-only");
        if (this.zzaux == null) {
            Frame.Builder builder = new Frame.Builder();
            if (this.zzauv == null || z) {
                builder.setBitmap(zzlx());
            } else {
                int i2 = FirebaseVisionImageMetadata.IMAGE_FORMAT_YV12;
                if (z2 && this.zzauw.getFormat() != 17) {
                    if (this.zzauw.getFormat() == 842094169) {
                        this.zzauv = ByteBuffer.wrap(zzow.zzg(zzow.zza(this.zzauv)));
                        this.zzauw = new FirebaseVisionImageMetadata.Builder().setFormat(17).setWidth(this.zzauw.getWidth()).setHeight(this.zzauw.getHeight()).setRotation(this.zzauw.getRotation()).build();
                    } else {
                        throw new IllegalStateException("Must be one of: IMAGE_FORMAT_NV21, IMAGE_FORMAT_YV12");
                    }
                }
                ByteBuffer byteBuffer = this.zzauv;
                int width = this.zzauw.getWidth();
                int height = this.zzauw.getHeight();
                int format = this.zzauw.getFormat();
                if (format == 17) {
                    i2 = 17;
                } else if (format != 842094169) {
                    i2 = 0;
                }
                builder.setImageData(byteBuffer, width, height, i2);
                int rotation = this.zzauw.getRotation();
                switch (rotation) {
                    case 0:
                        i = 0;
                        break;
                    case 1:
                        break;
                    case 2:
                        i = 2;
                        break;
                    case 3:
                        i = 3;
                        break;
                    default:
                        StringBuilder sb = new StringBuilder(29);
                        sb.append("Invalid rotation: ");
                        sb.append(rotation);
                        throw new IllegalArgumentException(sb.toString());
                }
                builder.setRotation(i);
            }
            builder.setTimestampMillis(this.zzauz);
            this.zzaux = builder.build();
        }
        return this.zzaux;
    }

    public final Pair<byte[], Float> zzb(int i, int i2) {
        int i3;
        int i4;
        byte[] bArr;
        if (this.zzauw != null) {
            boolean z = this.zzauw.getRotation() == 1 || this.zzauw.getRotation() == 3;
            i3 = z ? this.zzauw.getHeight() : this.zzauw.getWidth();
            i4 = z ? this.zzauw.getWidth() : this.zzauw.getHeight();
        } else {
            i3 = zzlx().getWidth();
            i4 = zzlx().getHeight();
        }
        float min = Math.min(((float) i) / ((float) i3), ((float) i2) / ((float) i4));
        float f = 1.0f;
        if (min < 1.0f) {
            Bitmap zzlx = zzlx();
            Matrix matrix = new Matrix();
            matrix.postScale(min, min);
            f = min;
            bArr = zzow.zza(Bitmap.createBitmap(zzlx, 0, 0, this.zzauu.getWidth(), this.zzauu.getHeight(), matrix, true));
        } else {
            bArr = zzad(true);
        }
        return Pair.create(bArr, Float.valueOf(f));
    }
}
