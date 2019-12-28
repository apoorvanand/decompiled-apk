package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.common.internal.GmsLogger;

public final class zzox {
    private static final GmsLogger zzaoe = new GmsLogger("MLKitImageUtils", "");
    private static zzox zzavj = new zzox();

    private zzox() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x005d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zza(android.content.ContentResolver r7, android.net.Uri r8) {
        /*
            java.lang.String r0 = "content"
            java.lang.String r1 = r8.getScheme()
            boolean r0 = r0.equals(r1)
            r1 = 0
            if (r0 != 0) goto L_0x001a
            java.lang.String r0 = "file"
            java.lang.String r2 = r8.getScheme()
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x001a
            return r1
        L_0x001a:
            r0 = 0
            java.io.InputStream r7 = r7.openInputStream(r8)     // Catch:{ IOException -> 0x0030, all -> 0x002d }
            androidx.exifinterface.media.ExifInterface r2 = new androidx.exifinterface.media.ExifInterface     // Catch:{ IOException -> 0x002b }
            r2.<init>((java.io.InputStream) r7)     // Catch:{ IOException -> 0x002b }
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r7)
            r0 = r2
            goto L_0x005b
        L_0x0029:
            r8 = move-exception
            goto L_0x0066
        L_0x002b:
            r2 = move-exception
            goto L_0x0032
        L_0x002d:
            r8 = move-exception
            r7 = r0
            goto L_0x0066
        L_0x0030:
            r2 = move-exception
            r7 = r0
        L_0x0032:
            com.google.android.gms.common.internal.GmsLogger r3 = zzaoe     // Catch:{ all -> 0x0029 }
            java.lang.String r4 = "MLKitImageUtils"
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x0029 }
            java.lang.String r5 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x0029 }
            int r5 = r5.length()     // Catch:{ all -> 0x0029 }
            int r5 = r5 + 48
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0029 }
            r6.<init>(r5)     // Catch:{ all -> 0x0029 }
            java.lang.String r5 = "failed to open file to read rotation meta data: "
            r6.append(r5)     // Catch:{ all -> 0x0029 }
            r6.append(r8)     // Catch:{ all -> 0x0029 }
            java.lang.String r8 = r6.toString()     // Catch:{ all -> 0x0029 }
            r3.e(r4, r8, r2)     // Catch:{ all -> 0x0029 }
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r7)
        L_0x005b:
            if (r0 != 0) goto L_0x005e
            return r1
        L_0x005e:
            java.lang.String r7 = "Orientation"
            r8 = 1
            int r7 = r0.getAttributeInt(r7, r8)
            return r7
        L_0x0066:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzox.zza(android.content.ContentResolver, android.net.Uri):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: android.graphics.Matrix} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: android.graphics.Matrix} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: android.graphics.Matrix} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: android.graphics.Matrix} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: android.graphics.Matrix} */
    /* JADX WARNING: type inference failed for: r0v0, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v1 */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0050, code lost:
        r3.postScale(-1.0f, 1.0f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0065, code lost:
        r8 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0070, code lost:
        if (r8 == null) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0072, code lost:
        r10 = android.graphics.Bitmap.createBitmap(r1, 0, 0, r6, r7, r8, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007a, code lost:
        if (r1 == r10) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007c, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0080, code lost:
        r10 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0081, code lost:
        com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r0);
        com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0087, code lost:
        return r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0047, code lost:
        r8 = r12;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ad A[Catch:{ all -> 0x00bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b2 A[Catch:{ all -> 0x00bb }] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap zza(android.content.ContentResolver r10, android.net.Uri r11, int r12) {
        /*
            r12 = 0
            java.io.InputStream r0 = r10.openInputStream(r11)     // Catch:{ FileNotFoundException -> 0x0097, all -> 0x0093 }
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options     // Catch:{ FileNotFoundException -> 0x008f, all -> 0x008c }
            r1.<init>()     // Catch:{ FileNotFoundException -> 0x008f, all -> 0x008c }
            r2 = 1
            r1.inJustDecodeBounds = r2     // Catch:{ FileNotFoundException -> 0x008f, all -> 0x008c }
            android.graphics.BitmapFactory.decodeStream(r0, r12, r1)     // Catch:{ FileNotFoundException -> 0x008f, all -> 0x008c }
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options     // Catch:{ FileNotFoundException -> 0x008f, all -> 0x008c }
            r1.<init>()     // Catch:{ FileNotFoundException -> 0x008f, all -> 0x008c }
            int r2 = r1.outWidth     // Catch:{ FileNotFoundException -> 0x008f, all -> 0x008c }
            int r2 = r2 / 1024
            int r3 = r1.outHeight     // Catch:{ FileNotFoundException -> 0x008f, all -> 0x008c }
            int r3 = r3 / 1024
            int r2 = java.lang.Math.max(r2, r3)     // Catch:{ FileNotFoundException -> 0x008f, all -> 0x008c }
            r1.inSampleSize = r2     // Catch:{ FileNotFoundException -> 0x008f, all -> 0x008c }
            java.io.InputStream r2 = r10.openInputStream(r11)     // Catch:{ FileNotFoundException -> 0x008f, all -> 0x008c }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r2, r12, r1)     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            int r10 = zza(r10, r11)     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            android.graphics.Matrix r3 = new android.graphics.Matrix     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            r3.<init>()     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            int r6 = r1.getWidth()     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            int r7 = r1.getHeight()     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            r4 = -1028390912(0xffffffffc2b40000, float:-90.0)
            r5 = 1119092736(0x42b40000, float:90.0)
            r8 = -1082130432(0xffffffffbf800000, float:-1.0)
            r9 = 1065353216(0x3f800000, float:1.0)
            switch(r10) {
                case 0: goto L_0x0047;
                case 1: goto L_0x0047;
                case 2: goto L_0x0067;
                case 3: goto L_0x0060;
                case 4: goto L_0x005c;
                case 5: goto L_0x0058;
                case 6: goto L_0x0054;
                case 7: goto L_0x004d;
                case 8: goto L_0x0049;
                default: goto L_0x0047;
            }     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
        L_0x0047:
            r8 = r12
            goto L_0x0070
        L_0x0049:
            r3.postRotate(r4)     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            goto L_0x0065
        L_0x004d:
            r3.postRotate(r4)     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
        L_0x0050:
            r3.postScale(r8, r9)     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            goto L_0x0065
        L_0x0054:
            r3.postRotate(r5)     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            goto L_0x0065
        L_0x0058:
            r3.postRotate(r5)     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            goto L_0x0050
        L_0x005c:
            r3.postScale(r9, r8)     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            goto L_0x0065
        L_0x0060:
            r10 = 1127481344(0x43340000, float:180.0)
            r3.postRotate(r10)     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
        L_0x0065:
            r8 = r3
            goto L_0x0070
        L_0x0067:
            android.graphics.Matrix r12 = new android.graphics.Matrix     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            r12.<init>()     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            r12.postScale(r8, r9)     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            goto L_0x0047
        L_0x0070:
            if (r8 == 0) goto L_0x0080
            r4 = 0
            r5 = 0
            r9 = 1
            r3 = r1
            android.graphics.Bitmap r10 = android.graphics.Bitmap.createBitmap(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            if (r1 == r10) goto L_0x0080
            r1.recycle()     // Catch:{ FileNotFoundException -> 0x008a, all -> 0x0088 }
            goto L_0x0081
        L_0x0080:
            r10 = r1
        L_0x0081:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r0)
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r2)
            return r10
        L_0x0088:
            r10 = move-exception
            goto L_0x00bd
        L_0x008a:
            r10 = move-exception
            goto L_0x0091
        L_0x008c:
            r10 = move-exception
            r2 = r12
            goto L_0x00bd
        L_0x008f:
            r10 = move-exception
            r2 = r12
        L_0x0091:
            r12 = r0
            goto L_0x0099
        L_0x0093:
            r10 = move-exception
            r0 = r12
            r2 = r0
            goto L_0x00bd
        L_0x0097:
            r10 = move-exception
            r2 = r12
        L_0x0099:
            com.google.android.gms.common.internal.GmsLogger r0 = zzaoe     // Catch:{ all -> 0x00bb }
            java.lang.String r1 = "MLKitImageUtils"
            java.lang.String r3 = "Could not open file: "
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00bb }
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x00bb }
            int r4 = r11.length()     // Catch:{ all -> 0x00bb }
            if (r4 == 0) goto L_0x00b2
            java.lang.String r11 = r3.concat(r11)     // Catch:{ all -> 0x00bb }
            goto L_0x00b7
        L_0x00b2:
            java.lang.String r11 = new java.lang.String     // Catch:{ all -> 0x00bb }
            r11.<init>(r3)     // Catch:{ all -> 0x00bb }
        L_0x00b7:
            r0.e(r1, r11, r10)     // Catch:{ all -> 0x00bb }
            throw r10     // Catch:{ all -> 0x00bb }
        L_0x00bb:
            r10 = move-exception
            r0 = r12
        L_0x00bd:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r0)
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzox.zza(android.content.ContentResolver, android.net.Uri, int):android.graphics.Bitmap");
    }

    public static zzox zzlz() {
        return zzavj;
    }
}
