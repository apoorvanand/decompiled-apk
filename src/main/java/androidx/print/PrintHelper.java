package androidx.print;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.FileNotFoundException;

public final class PrintHelper {
    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_COLOR = 2;
    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_MONOCHROME = 1;
    private static final String LOG_TAG = "PrintHelper";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    static final boolean a = (Build.VERSION.SDK_INT < 20 || Build.VERSION.SDK_INT > 23);
    static final boolean b;
    final Context c;
    BitmapFactory.Options d = null;
    final Object e = new Object();
    int f = 2;
    int g = 2;
    int h = 1;

    public interface OnPrintFinishCallback {
        void onFinish();
    }

    @RequiresApi(19)
    private class PrintBitmapAdapter extends PrintDocumentAdapter {
        private PrintAttributes mAttributes;
        private final Bitmap mBitmap;
        private final OnPrintFinishCallback mCallback;
        private final int mFittingMode;
        private final String mJobName;

        PrintBitmapAdapter(String str, int i, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
            this.mJobName = str;
            this.mFittingMode = i;
            this.mBitmap = bitmap;
            this.mCallback = onPrintFinishCallback;
        }

        public void onFinish() {
            OnPrintFinishCallback onPrintFinishCallback = this.mCallback;
            if (onPrintFinishCallback != null) {
                onPrintFinishCallback.onFinish();
            }
        }

        public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            this.mAttributes = printAttributes2;
            layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.mJobName).setContentType(1).setPageCount(1).build(), !printAttributes2.equals(printAttributes));
        }

        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PrintHelper.this.a(this.mAttributes, this.mFittingMode, this.mBitmap, parcelFileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    @RequiresApi(19)
    private class PrintUriAdapter extends PrintDocumentAdapter {
        final String a;
        final Uri b;
        final OnPrintFinishCallback c;
        final int d;
        PrintAttributes e;
        AsyncTask<Uri, Boolean, Bitmap> f;
        Bitmap g = null;

        PrintUriAdapter(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback, int i) {
            this.a = str;
            this.b = uri;
            this.c = onPrintFinishCallback;
            this.d = i;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            synchronized (PrintHelper.this.e) {
                if (PrintHelper.this.d != null) {
                    if (Build.VERSION.SDK_INT < 24) {
                        PrintHelper.this.d.requestCancelDecode();
                    }
                    PrintHelper.this.d = null;
                }
            }
        }

        public void onFinish() {
            super.onFinish();
            a();
            AsyncTask<Uri, Boolean, Bitmap> asyncTask = this.f;
            if (asyncTask != null) {
                asyncTask.cancel(true);
            }
            OnPrintFinishCallback onPrintFinishCallback = this.c;
            if (onPrintFinishCallback != null) {
                onPrintFinishCallback.onFinish();
            }
            Bitmap bitmap = this.g;
            if (bitmap != null) {
                bitmap.recycle();
                this.g = null;
            }
        }

        public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            synchronized (this) {
                this.e = printAttributes2;
            }
            if (cancellationSignal.isCanceled()) {
                layoutResultCallback.onLayoutCancelled();
            } else if (this.g != null) {
                layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.a).setContentType(1).setPageCount(1).build(), !printAttributes2.equals(printAttributes));
            } else {
                final CancellationSignal cancellationSignal2 = cancellationSignal;
                final PrintAttributes printAttributes3 = printAttributes2;
                final PrintAttributes printAttributes4 = printAttributes;
                final PrintDocumentAdapter.LayoutResultCallback layoutResultCallback2 = layoutResultCallback;
                this.f = new AsyncTask<Uri, Boolean, Bitmap>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public Bitmap doInBackground(Uri... uriArr) {
                        try {
                            return PrintHelper.this.a(PrintUriAdapter.this.b);
                        } catch (FileNotFoundException unused) {
                            return null;
                        }
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(Bitmap bitmap) {
                        PrintAttributes.MediaSize mediaSize;
                        super.onPostExecute(bitmap);
                        if (bitmap != null && (!PrintHelper.a || PrintHelper.this.h == 0)) {
                            synchronized (this) {
                                mediaSize = PrintUriAdapter.this.e.getMediaSize();
                            }
                            if (!(mediaSize == null || mediaSize.isPortrait() == PrintHelper.a(bitmap))) {
                                Matrix matrix = new Matrix();
                                matrix.postRotate(90.0f);
                                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                            }
                        }
                        PrintUriAdapter printUriAdapter = PrintUriAdapter.this;
                        printUriAdapter.g = bitmap;
                        if (bitmap != null) {
                            layoutResultCallback2.onLayoutFinished(new PrintDocumentInfo.Builder(printUriAdapter.a).setContentType(1).setPageCount(1).build(), true ^ printAttributes3.equals(printAttributes4));
                        } else {
                            layoutResultCallback2.onLayoutFailed((CharSequence) null);
                        }
                        PrintUriAdapter.this.f = null;
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: b */
                    public void onCancelled(Bitmap bitmap) {
                        layoutResultCallback2.onLayoutCancelled();
                        PrintUriAdapter.this.f = null;
                    }

                    /* access modifiers changed from: protected */
                    public void onPreExecute() {
                        cancellationSignal2.setOnCancelListener(new CancellationSignal.OnCancelListener() {
                            public void onCancel() {
                                PrintUriAdapter.this.a();
                                AnonymousClass1.this.cancel(false);
                            }
                        });
                    }
                }.execute(new Uri[0]);
            }
        }

        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PrintHelper.this.a(this.e, this.d, this.g, parcelFileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    static {
        boolean z = false;
        if (Build.VERSION.SDK_INT != 23) {
            z = true;
        }
        b = z;
    }

    public PrintHelper(@NonNull Context context) {
        this.c = context;
    }

    static Bitmap a(Bitmap bitmap, int i) {
        if (i != 1) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        canvas.setBitmap((Bitmap) null);
        return createBitmap;
    }

    static Matrix a(int i, int i2, RectF rectF, int i3) {
        Matrix matrix = new Matrix();
        float f2 = (float) i;
        float width = rectF.width() / f2;
        float max = i3 == 2 ? Math.max(width, rectF.height() / ((float) i2)) : Math.min(width, rectF.height() / ((float) i2));
        matrix.postScale(max, max);
        matrix.postTranslate((rectF.width() - (f2 * max)) / 2.0f, (rectF.height() - (((float) i2) * max)) / 2.0f);
        return matrix;
    }

    static boolean a(Bitmap bitmap) {
        return bitmap.getWidth() <= bitmap.getHeight();
    }

    @RequiresApi(19)
    private static PrintAttributes.Builder copyAttributes(PrintAttributes printAttributes) {
        PrintAttributes.Builder minMargins = new PrintAttributes.Builder().setMediaSize(printAttributes.getMediaSize()).setResolution(printAttributes.getResolution()).setMinMargins(printAttributes.getMinMargins());
        if (printAttributes.getColorMode() != 0) {
            minMargins.setColorMode(printAttributes.getColorMode());
        }
        if (Build.VERSION.SDK_INT >= 23 && printAttributes.getDuplexMode() != 0) {
            minMargins.setDuplexMode(printAttributes.getDuplexMode());
        }
        return minMargins;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0028 A[SYNTHETIC, Splitter:B:18:0x0028] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap loadBitmap(android.net.Uri r3, android.graphics.BitmapFactory.Options r4) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0035
            android.content.Context r0 = r2.c
            if (r0 == 0) goto L_0x0035
            r1 = 0
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ all -> 0x0025 }
            java.io.InputStream r3 = r0.openInputStream(r3)     // Catch:{ all -> 0x0025 }
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeStream(r3, r1, r4)     // Catch:{ all -> 0x0022 }
            if (r3 == 0) goto L_0x0021
            r3.close()     // Catch:{ IOException -> 0x0019 }
            goto L_0x0021
        L_0x0019:
            r3 = move-exception
            java.lang.String r0 = "PrintHelper"
            java.lang.String r1 = "close fail "
            android.util.Log.w(r0, r1, r3)
        L_0x0021:
            return r4
        L_0x0022:
            r4 = move-exception
            r1 = r3
            goto L_0x0026
        L_0x0025:
            r4 = move-exception
        L_0x0026:
            if (r1 == 0) goto L_0x0034
            r1.close()     // Catch:{ IOException -> 0x002c }
            goto L_0x0034
        L_0x002c:
            r3 = move-exception
            java.lang.String r0 = "PrintHelper"
            java.lang.String r1 = "close fail "
            android.util.Log.w(r0, r1, r3)
        L_0x0034:
            throw r4
        L_0x0035:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "bad argument to loadBitmap"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.print.PrintHelper.loadBitmap(android.net.Uri, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    public static boolean systemSupportsPrint() {
        return Build.VERSION.SDK_INT >= 19;
    }

    /* access modifiers changed from: package-private */
    public Bitmap a(Uri uri) {
        BitmapFactory.Options options;
        if (uri == null || this.c == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inJustDecodeBounds = true;
        loadBitmap(uri, options2);
        int i = options2.outWidth;
        int i2 = options2.outHeight;
        if (i > 0 && i2 > 0) {
            int max = Math.max(i, i2);
            int i3 = 1;
            while (max > MAX_PRINT_SIZE) {
                max >>>= 1;
                i3 <<= 1;
            }
            if (i3 > 0 && Math.min(i, i2) / i3 > 0) {
                synchronized (this.e) {
                    this.d = new BitmapFactory.Options();
                    this.d.inMutable = true;
                    this.d.inSampleSize = i3;
                    options = this.d;
                }
                try {
                    Bitmap loadBitmap = loadBitmap(uri, options);
                    synchronized (this.e) {
                        this.d = null;
                    }
                    return loadBitmap;
                } catch (Throwable th) {
                    synchronized (this.e) {
                        this.d = null;
                        throw th;
                    }
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    @RequiresApi(19)
    public void a(PrintAttributes printAttributes, int i, Bitmap bitmap, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
        final PrintAttributes build = b ? printAttributes : copyAttributes(printAttributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
        final CancellationSignal cancellationSignal2 = cancellationSignal;
        final Bitmap bitmap2 = bitmap;
        final PrintAttributes printAttributes2 = printAttributes;
        final int i2 = i;
        final ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
        final PrintDocumentAdapter.WriteResultCallback writeResultCallback2 = writeResultCallback;
        new AsyncTask<Void, Void, Throwable>() {
            /* access modifiers changed from: protected */
            /* JADX WARNING: Code restructure failed: missing block: B:49:0x00de, code lost:
                r1.recycle();
             */
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x00a3 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x00c5 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x00da */
            /* JADX WARNING: Removed duplicated region for block: B:27:0x00a7 A[Catch:{ all -> 0x00cd, Throwable -> 0x00e2 }] */
            /* JADX WARNING: Removed duplicated region for block: B:39:0x00c9 A[Catch:{ all -> 0x00cd, Throwable -> 0x00e2 }] */
            /* JADX WARNING: Removed duplicated region for block: B:49:0x00de A[Catch:{ all -> 0x00cd, Throwable -> 0x00e2 }] */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Throwable doInBackground(java.lang.Void... r8) {
                /*
                    r7 = this;
                    android.os.CancellationSignal r8 = r4     // Catch:{ Throwable -> 0x00e2 }
                    boolean r8 = r8.isCanceled()     // Catch:{ Throwable -> 0x00e2 }
                    r0 = 0
                    if (r8 == 0) goto L_0x000a
                    return r0
                L_0x000a:
                    android.print.pdf.PrintedPdfDocument r8 = new android.print.pdf.PrintedPdfDocument     // Catch:{ Throwable -> 0x00e2 }
                    androidx.print.PrintHelper r1 = androidx.print.PrintHelper.this     // Catch:{ Throwable -> 0x00e2 }
                    android.content.Context r1 = r1.c     // Catch:{ Throwable -> 0x00e2 }
                    android.print.PrintAttributes r2 = r5     // Catch:{ Throwable -> 0x00e2 }
                    r8.<init>(r1, r2)     // Catch:{ Throwable -> 0x00e2 }
                    android.graphics.Bitmap r1 = r6     // Catch:{ Throwable -> 0x00e2 }
                    android.print.PrintAttributes r2 = r5     // Catch:{ Throwable -> 0x00e2 }
                    int r2 = r2.getColorMode()     // Catch:{ Throwable -> 0x00e2 }
                    android.graphics.Bitmap r1 = androidx.print.PrintHelper.a(r1, r2)     // Catch:{ Throwable -> 0x00e2 }
                    android.os.CancellationSignal r2 = r4     // Catch:{ Throwable -> 0x00e2 }
                    boolean r2 = r2.isCanceled()     // Catch:{ Throwable -> 0x00e2 }
                    if (r2 == 0) goto L_0x002a
                    return r0
                L_0x002a:
                    r2 = 1
                    android.graphics.pdf.PdfDocument$Page r3 = r8.startPage(r2)     // Catch:{ all -> 0x00cd }
                    boolean r4 = androidx.print.PrintHelper.b     // Catch:{ all -> 0x00cd }
                    if (r4 == 0) goto L_0x0041
                    android.graphics.RectF r2 = new android.graphics.RectF     // Catch:{ all -> 0x00cd }
                    android.graphics.pdf.PdfDocument$PageInfo r4 = r3.getInfo()     // Catch:{ all -> 0x00cd }
                    android.graphics.Rect r4 = r4.getContentRect()     // Catch:{ all -> 0x00cd }
                    r2.<init>(r4)     // Catch:{ all -> 0x00cd }
                    goto L_0x0064
                L_0x0041:
                    android.print.pdf.PrintedPdfDocument r4 = new android.print.pdf.PrintedPdfDocument     // Catch:{ all -> 0x00cd }
                    androidx.print.PrintHelper r5 = androidx.print.PrintHelper.this     // Catch:{ all -> 0x00cd }
                    android.content.Context r5 = r5.c     // Catch:{ all -> 0x00cd }
                    android.print.PrintAttributes r6 = r7     // Catch:{ all -> 0x00cd }
                    r4.<init>(r5, r6)     // Catch:{ all -> 0x00cd }
                    android.graphics.pdf.PdfDocument$Page r2 = r4.startPage(r2)     // Catch:{ all -> 0x00cd }
                    android.graphics.RectF r5 = new android.graphics.RectF     // Catch:{ all -> 0x00cd }
                    android.graphics.pdf.PdfDocument$PageInfo r6 = r2.getInfo()     // Catch:{ all -> 0x00cd }
                    android.graphics.Rect r6 = r6.getContentRect()     // Catch:{ all -> 0x00cd }
                    r5.<init>(r6)     // Catch:{ all -> 0x00cd }
                    r4.finishPage(r2)     // Catch:{ all -> 0x00cd }
                    r4.close()     // Catch:{ all -> 0x00cd }
                    r2 = r5
                L_0x0064:
                    int r4 = r1.getWidth()     // Catch:{ all -> 0x00cd }
                    int r5 = r1.getHeight()     // Catch:{ all -> 0x00cd }
                    int r6 = r8     // Catch:{ all -> 0x00cd }
                    android.graphics.Matrix r4 = androidx.print.PrintHelper.a(r4, r5, r2, r6)     // Catch:{ all -> 0x00cd }
                    boolean r5 = androidx.print.PrintHelper.b     // Catch:{ all -> 0x00cd }
                    if (r5 == 0) goto L_0x0077
                    goto L_0x0085
                L_0x0077:
                    float r5 = r2.left     // Catch:{ all -> 0x00cd }
                    float r6 = r2.top     // Catch:{ all -> 0x00cd }
                    r4.postTranslate(r5, r6)     // Catch:{ all -> 0x00cd }
                    android.graphics.Canvas r5 = r3.getCanvas()     // Catch:{ all -> 0x00cd }
                    r5.clipRect(r2)     // Catch:{ all -> 0x00cd }
                L_0x0085:
                    android.graphics.Canvas r2 = r3.getCanvas()     // Catch:{ all -> 0x00cd }
                    r2.drawBitmap(r1, r4, r0)     // Catch:{ all -> 0x00cd }
                    r8.finishPage(r3)     // Catch:{ all -> 0x00cd }
                    android.os.CancellationSignal r2 = r4     // Catch:{ all -> 0x00cd }
                    boolean r2 = r2.isCanceled()     // Catch:{ all -> 0x00cd }
                    if (r2 == 0) goto L_0x00ab
                    r8.close()     // Catch:{ Throwable -> 0x00e2 }
                    android.os.ParcelFileDescriptor r8 = r9     // Catch:{ Throwable -> 0x00e2 }
                    if (r8 == 0) goto L_0x00a3
                    android.os.ParcelFileDescriptor r8 = r9     // Catch:{ IOException -> 0x00a3 }
                    r8.close()     // Catch:{ IOException -> 0x00a3 }
                L_0x00a3:
                    android.graphics.Bitmap r8 = r6     // Catch:{ Throwable -> 0x00e2 }
                    if (r1 == r8) goto L_0x00aa
                    r1.recycle()     // Catch:{ Throwable -> 0x00e2 }
                L_0x00aa:
                    return r0
                L_0x00ab:
                    java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x00cd }
                    android.os.ParcelFileDescriptor r3 = r9     // Catch:{ all -> 0x00cd }
                    java.io.FileDescriptor r3 = r3.getFileDescriptor()     // Catch:{ all -> 0x00cd }
                    r2.<init>(r3)     // Catch:{ all -> 0x00cd }
                    r8.writeTo(r2)     // Catch:{ all -> 0x00cd }
                    r8.close()     // Catch:{ Throwable -> 0x00e2 }
                    android.os.ParcelFileDescriptor r8 = r9     // Catch:{ Throwable -> 0x00e2 }
                    if (r8 == 0) goto L_0x00c5
                    android.os.ParcelFileDescriptor r8 = r9     // Catch:{ IOException -> 0x00c5 }
                    r8.close()     // Catch:{ IOException -> 0x00c5 }
                L_0x00c5:
                    android.graphics.Bitmap r8 = r6     // Catch:{ Throwable -> 0x00e2 }
                    if (r1 == r8) goto L_0x00cc
                    r1.recycle()     // Catch:{ Throwable -> 0x00e2 }
                L_0x00cc:
                    return r0
                L_0x00cd:
                    r0 = move-exception
                    r8.close()     // Catch:{ Throwable -> 0x00e2 }
                    android.os.ParcelFileDescriptor r8 = r9     // Catch:{ Throwable -> 0x00e2 }
                    if (r8 == 0) goto L_0x00da
                    android.os.ParcelFileDescriptor r8 = r9     // Catch:{ IOException -> 0x00da }
                    r8.close()     // Catch:{ IOException -> 0x00da }
                L_0x00da:
                    android.graphics.Bitmap r8 = r6     // Catch:{ Throwable -> 0x00e2 }
                    if (r1 == r8) goto L_0x00e1
                    r1.recycle()     // Catch:{ Throwable -> 0x00e2 }
                L_0x00e1:
                    throw r0     // Catch:{ Throwable -> 0x00e2 }
                L_0x00e2:
                    r8 = move-exception
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.print.PrintHelper.AnonymousClass1.doInBackground(java.lang.Void[]):java.lang.Throwable");
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Throwable th) {
                if (cancellationSignal2.isCanceled()) {
                    writeResultCallback2.onWriteCancelled();
                } else if (th == null) {
                    writeResultCallback2.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                } else {
                    Log.e(PrintHelper.LOG_TAG, "Error writing printed content", th);
                    writeResultCallback2.onWriteFailed((CharSequence) null);
                }
            }
        }.execute(new Void[0]);
    }

    public int getColorMode() {
        return this.g;
    }

    public int getOrientation() {
        if (Build.VERSION.SDK_INT < 19 || this.h != 0) {
            return this.h;
        }
        return 1;
    }

    public int getScaleMode() {
        return this.f;
    }

    public void printBitmap(@NonNull String str, @NonNull Bitmap bitmap) {
        printBitmap(str, bitmap, (OnPrintFinishCallback) null);
    }

    public void printBitmap(@NonNull String str, @NonNull Bitmap bitmap, @Nullable OnPrintFinishCallback onPrintFinishCallback) {
        if (Build.VERSION.SDK_INT >= 19 && bitmap != null) {
            ((PrintManager) this.c.getSystemService("print")).print(str, new PrintBitmapAdapter(str, this.f, bitmap, onPrintFinishCallback), new PrintAttributes.Builder().setMediaSize(a(bitmap) ? PrintAttributes.MediaSize.UNKNOWN_PORTRAIT : PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE).setColorMode(this.g).build());
        }
    }

    public void printBitmap(@NonNull String str, @NonNull Uri uri) {
        printBitmap(str, uri, (OnPrintFinishCallback) null);
    }

    public void printBitmap(@NonNull String str, @NonNull Uri uri, @Nullable OnPrintFinishCallback onPrintFinishCallback) {
        PrintAttributes.MediaSize mediaSize;
        if (Build.VERSION.SDK_INT >= 19) {
            PrintUriAdapter printUriAdapter = new PrintUriAdapter(str, uri, onPrintFinishCallback, this.f);
            PrintManager printManager = (PrintManager) this.c.getSystemService("print");
            PrintAttributes.Builder builder = new PrintAttributes.Builder();
            builder.setColorMode(this.g);
            int i = this.h;
            if (i == 1 || i == 0) {
                mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
            } else {
                if (i == 2) {
                    mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
                }
                printManager.print(str, printUriAdapter, builder.build());
            }
            builder.setMediaSize(mediaSize);
            printManager.print(str, printUriAdapter, builder.build());
        }
    }

    public void setColorMode(int i) {
        this.g = i;
    }

    public void setOrientation(int i) {
        this.h = i;
    }

    public void setScaleMode(int i) {
        this.f = i;
    }
}