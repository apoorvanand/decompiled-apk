package androidx.core.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.versionedparcelable.CustomVersionedParcelable;
import com.facebook.appevents.codeless.internal.Constants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

public class IconCompat extends CustomVersionedParcelable {
    private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25f;
    private static final int AMBIENT_SHADOW_ALPHA = 30;
    private static final float BLUR_FACTOR = 0.010416667f;
    private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667f;
    private static final String EXTRA_INT1 = "int1";
    private static final String EXTRA_INT2 = "int2";
    private static final String EXTRA_OBJ = "obj";
    private static final String EXTRA_TINT_LIST = "tint_list";
    private static final String EXTRA_TINT_MODE = "tint_mode";
    private static final String EXTRA_TYPE = "type";
    private static final float ICON_DIAMETER_FACTOR = 0.9166667f;
    private static final int KEY_SHADOW_ALPHA = 61;
    private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833334f;
    private static final String TAG = "IconCompat";
    public static final int TYPE_UNKNOWN = -1;
    static final PorterDuff.Mode b = PorterDuff.Mode.SRC_IN;
    Object a;
    PorterDuff.Mode c = b;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public byte[] mData;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mInt1;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mInt2;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Parcelable mParcelable;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public ColorStateList mTintList = null;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public String mTintModeStr;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mType;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface IconType {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public IconCompat() {
    }

    private IconCompat(int i) {
        this.mType = i;
    }

    @VisibleForTesting
    static Bitmap a(Bitmap bitmap, boolean z) {
        int min = (int) (((float) Math.min(bitmap.getWidth(), bitmap.getHeight())) * DEFAULT_VIEW_PORT_SCALE);
        Bitmap createBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(3);
        float f = (float) min;
        float f2 = 0.5f * f;
        float f3 = ICON_DIAMETER_FACTOR * f2;
        if (z) {
            float f4 = BLUR_FACTOR * f;
            paint.setColor(0);
            paint.setShadowLayer(f4, 0.0f, f * KEY_SHADOW_OFFSET_FACTOR, 1023410176);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.setShadowLayer(f4, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) ((-(bitmap.getWidth() - min)) / 2), (float) ((-(bitmap.getHeight() - min)) / 2));
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f2, f2, f3, paint);
        canvas.setBitmap((Bitmap) null);
        return createBitmap;
    }

    @Nullable
    public static IconCompat createFromBundle(@NonNull Bundle bundle) {
        Object obj;
        int i = bundle.getInt("type");
        IconCompat iconCompat = new IconCompat(i);
        iconCompat.mInt1 = bundle.getInt(EXTRA_INT1);
        iconCompat.mInt2 = bundle.getInt(EXTRA_INT2);
        if (bundle.containsKey(EXTRA_TINT_LIST)) {
            iconCompat.mTintList = (ColorStateList) bundle.getParcelable(EXTRA_TINT_LIST);
        }
        if (bundle.containsKey(EXTRA_TINT_MODE)) {
            iconCompat.c = PorterDuff.Mode.valueOf(bundle.getString(EXTRA_TINT_MODE));
        }
        if (i != -1) {
            switch (i) {
                case 1:
                case 5:
                    break;
                case 2:
                case 4:
                    obj = bundle.getString(EXTRA_OBJ);
                    break;
                case 3:
                    obj = bundle.getByteArray(EXTRA_OBJ);
                    break;
                default:
                    Log.w(TAG, "Unknown type " + i);
                    return null;
            }
        }
        obj = bundle.getParcelable(EXTRA_OBJ);
        iconCompat.a = obj;
        return iconCompat;
    }

    @RequiresApi(23)
    @Nullable
    public static IconCompat createFromIcon(@NonNull Context context, @NonNull Icon icon) {
        Preconditions.checkNotNull(icon);
        int type = getType(icon);
        if (type == 2) {
            String resPackage = getResPackage(icon);
            try {
                return createWithResource(getResources(context, resPackage), resPackage, getResId(icon));
            } catch (Resources.NotFoundException unused) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        } else if (type == 4) {
            return createWithContentUri(getUri(icon));
        } else {
            IconCompat iconCompat = new IconCompat(-1);
            iconCompat.a = icon;
            return iconCompat;
        }
    }

    @RequiresApi(23)
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static IconCompat createFromIcon(@NonNull Icon icon) {
        Preconditions.checkNotNull(icon);
        int type = getType(icon);
        if (type == 2) {
            return createWithResource((Resources) null, getResPackage(icon), getResId(icon));
        }
        if (type == 4) {
            return createWithContentUri(getUri(icon));
        }
        IconCompat iconCompat = new IconCompat(-1);
        iconCompat.a = icon;
        return iconCompat;
    }

    public static IconCompat createWithAdaptiveBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            IconCompat iconCompat = new IconCompat(5);
            iconCompat.a = bitmap;
            return iconCompat;
        }
        throw new IllegalArgumentException("Bitmap must not be null.");
    }

    public static IconCompat createWithBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            IconCompat iconCompat = new IconCompat(1);
            iconCompat.a = bitmap;
            return iconCompat;
        }
        throw new IllegalArgumentException("Bitmap must not be null.");
    }

    public static IconCompat createWithContentUri(Uri uri) {
        if (uri != null) {
            return createWithContentUri(uri.toString());
        }
        throw new IllegalArgumentException("Uri must not be null.");
    }

    public static IconCompat createWithContentUri(String str) {
        if (str != null) {
            IconCompat iconCompat = new IconCompat(4);
            iconCompat.a = str;
            return iconCompat;
        }
        throw new IllegalArgumentException("Uri must not be null.");
    }

    public static IconCompat createWithData(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            IconCompat iconCompat = new IconCompat(3);
            iconCompat.a = bArr;
            iconCompat.mInt1 = i;
            iconCompat.mInt2 = i2;
            return iconCompat;
        }
        throw new IllegalArgumentException("Data must not be null.");
    }

    public static IconCompat createWithResource(Context context, @DrawableRes int i) {
        if (context != null) {
            return createWithResource(context.getResources(), context.getPackageName(), i);
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static IconCompat createWithResource(Resources resources, String str, @DrawableRes int i) {
        if (str == null) {
            throw new IllegalArgumentException("Package must not be null.");
        } else if (i != 0) {
            IconCompat iconCompat = new IconCompat(2);
            iconCompat.mInt1 = i;
            if (resources != null) {
                try {
                    iconCompat.a = resources.getResourceName(i);
                } catch (Resources.NotFoundException unused) {
                    throw new IllegalArgumentException("Icon resource cannot be found");
                }
            } else {
                iconCompat.a = str;
            }
            return iconCompat;
        } else {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
    }

    @RequiresApi(23)
    @IdRes
    @DrawableRes
    private static int getResId(@NonNull Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e(TAG, "Unable to get icon resource", e);
            return 0;
        }
    }

    @RequiresApi(23)
    @Nullable
    private static String getResPackage(@NonNull Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e(TAG, "Unable to get icon package", e);
            return null;
        }
    }

    private static Resources getResources(Context context, String str) {
        if (Constants.PLATFORM.equals(str)) {
            return Resources.getSystem();
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 8192);
            if (applicationInfo != null) {
                return packageManager.getResourcesForApplication(applicationInfo);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, String.format("Unable to find pkg=%s for icon", new Object[]{str}), e);
            return null;
        }
    }

    @RequiresApi(23)
    private static int getType(@NonNull Icon icon) {
        String str;
        StringBuilder sb;
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getType();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getType", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            e = e;
            str = TAG;
            sb = new StringBuilder();
            sb.append("Unable to get icon type ");
            sb.append(icon);
            Log.e(str, sb.toString(), e);
            return -1;
        } catch (InvocationTargetException e2) {
            e = e2;
            str = TAG;
            sb = new StringBuilder();
            sb.append("Unable to get icon type ");
            sb.append(icon);
            Log.e(str, sb.toString(), e);
            return -1;
        } catch (NoSuchMethodException e3) {
            e = e3;
            str = TAG;
            sb = new StringBuilder();
            sb.append("Unable to get icon type ");
            sb.append(icon);
            Log.e(str, sb.toString(), e);
            return -1;
        }
    }

    @RequiresApi(23)
    @Nullable
    private static Uri getUri(@NonNull Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getUri();
        }
        try {
            return (Uri) icon.getClass().getMethod("getUri", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e(TAG, "Unable to get icon uri", e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable loadDrawableInner(android.content.Context r7) {
        /*
            r6 = this;
            int r0 = r6.mType
            r1 = 0
            r2 = 0
            switch(r0) {
                case 1: goto L_0x00d4;
                case 2: goto L_0x009a;
                case 3: goto L_0x0084;
                case 4: goto L_0x001b;
                case 5: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x00e2
        L_0x0009:
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r7 = r7.getResources()
            java.lang.Object r2 = r6.a
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            android.graphics.Bitmap r1 = a(r2, r1)
            r0.<init>(r7, r1)
            return r0
        L_0x001b:
            java.lang.Object r0 = r6.a
            java.lang.String r0 = (java.lang.String) r0
            android.net.Uri r0 = android.net.Uri.parse(r0)
            java.lang.String r1 = r0.getScheme()
            java.lang.String r3 = "content"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x0053
            java.lang.String r3 = "file"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0038
            goto L_0x0053
        L_0x0038:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0048 }
            java.io.File r3 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0048 }
            java.lang.Object r4 = r6.a     // Catch:{ FileNotFoundException -> 0x0048 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ FileNotFoundException -> 0x0048 }
            r3.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0048 }
            r1.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0048 }
            r0 = r1
            goto L_0x0074
        L_0x0048:
            r1 = move-exception
            java.lang.String r3 = "IconCompat"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Unable to load image from path: "
            goto L_0x0066
        L_0x0053:
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch:{ Exception -> 0x005c }
            java.io.InputStream r0 = r1.openInputStream(r0)     // Catch:{ Exception -> 0x005c }
            goto L_0x0074
        L_0x005c:
            r1 = move-exception
            java.lang.String r3 = "IconCompat"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Unable to load image from URI: "
        L_0x0066:
            r4.append(r5)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            android.util.Log.w(r3, r0, r1)
            r0 = r2
        L_0x0074:
            if (r0 == 0) goto L_0x00e2
            android.graphics.drawable.BitmapDrawable r1 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r7 = r7.getResources()
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r0)
            r1.<init>(r7, r0)
            return r1
        L_0x0084:
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r7 = r7.getResources()
            java.lang.Object r1 = r6.a
            byte[] r1 = (byte[]) r1
            int r2 = r6.mInt1
            int r3 = r6.mInt2
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeByteArray(r1, r2, r3)
            r0.<init>(r7, r1)
            return r0
        L_0x009a:
            java.lang.String r0 = r6.getResPackage()
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L_0x00a8
            java.lang.String r0 = r7.getPackageName()
        L_0x00a8:
            android.content.res.Resources r0 = getResources(r7, r0)
            int r3 = r6.mInt1     // Catch:{ RuntimeException -> 0x00b7 }
            android.content.res.Resources$Theme r7 = r7.getTheme()     // Catch:{ RuntimeException -> 0x00b7 }
            android.graphics.drawable.Drawable r7 = androidx.core.content.res.ResourcesCompat.getDrawable(r0, r3, r7)     // Catch:{ RuntimeException -> 0x00b7 }
            return r7
        L_0x00b7:
            r7 = move-exception
            java.lang.String r0 = "IconCompat"
            java.lang.String r3 = "Unable to load resource 0x%08x from pkg=%s"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]
            int r5 = r6.mInt1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r4[r1] = r5
            r1 = 1
            java.lang.Object r5 = r6.a
            r4[r1] = r5
            java.lang.String r1 = java.lang.String.format(r3, r4)
            android.util.Log.e(r0, r1, r7)
            goto L_0x00e2
        L_0x00d4:
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r7 = r7.getResources()
            java.lang.Object r1 = r6.a
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            r0.<init>(r7, r1)
            return r0
        L_0x00e2:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompat.loadDrawableInner(android.content.Context):android.graphics.drawable.Drawable");
    }

    private static String typeToString(int i) {
        switch (i) {
            case 1:
                return "BITMAP";
            case 2:
                return "RESOURCE";
            case 3:
                return "DATA";
            case 4:
                return "URI";
            case 5:
                return "BITMAP_MASKABLE";
            default:
                return "UNKNOWN";
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void addToShortcutIntent(@NonNull Intent intent, @Nullable Drawable drawable, @NonNull Context context) {
        Bitmap bitmap;
        checkResource(context);
        int i = this.mType;
        if (i != 5) {
            switch (i) {
                case 1:
                    bitmap = (Bitmap) this.a;
                    if (drawable != null) {
                        bitmap = bitmap.copy(bitmap.getConfig(), true);
                        break;
                    }
                    break;
                case 2:
                    try {
                        Context createPackageContext = context.createPackageContext(getResPackage(), 0);
                        if (drawable == null) {
                            intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(createPackageContext, this.mInt1));
                            return;
                        }
                        Drawable drawable2 = ContextCompat.getDrawable(createPackageContext, this.mInt1);
                        if (drawable2.getIntrinsicWidth() > 0) {
                            if (drawable2.getIntrinsicHeight() > 0) {
                                bitmap = Bitmap.createBitmap(drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                                drawable2.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                                drawable2.draw(new Canvas(bitmap));
                                break;
                            }
                        }
                        int launcherLargeIconSize = ((ActivityManager) createPackageContext.getSystemService("activity")).getLauncherLargeIconSize();
                        bitmap = Bitmap.createBitmap(launcherLargeIconSize, launcherLargeIconSize, Bitmap.Config.ARGB_8888);
                        drawable2.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                        drawable2.draw(new Canvas(bitmap));
                    } catch (PackageManager.NameNotFoundException e) {
                        throw new IllegalArgumentException("Can't find package " + this.a, e);
                    }
                default:
                    throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
            }
        } else {
            bitmap = a((Bitmap) this.a, true);
        }
        if (drawable != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            drawable.setBounds(width / 2, height / 2, width, height);
            drawable.draw(new Canvas(bitmap));
        }
        intent.putExtra("android.intent.extra.shortcut.ICON", bitmap);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void checkResource(Context context) {
        if (this.mType == 2) {
            String str = (String) this.a;
            if (str.contains(":")) {
                String str2 = str.split(":", -1)[1];
                String str3 = str2.split("/", -1)[0];
                String str4 = str2.split("/", -1)[1];
                String str5 = str.split(":", -1)[0];
                int identifier = getResources(context, str5).getIdentifier(str4, str3, str5);
                if (this.mInt1 != identifier) {
                    Log.i(TAG, "Id has changed for " + str5 + "/" + str4);
                    this.mInt1 = identifier;
                }
            }
        }
    }

    @IdRes
    public int getResId() {
        if (this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return getResId((Icon) this.a);
        }
        if (this.mType == 2) {
            return this.mInt1;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    @NonNull
    public String getResPackage() {
        if (this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return getResPackage((Icon) this.a);
        }
        if (this.mType == 2) {
            return ((String) this.a).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public int getType() {
        return (this.mType != -1 || Build.VERSION.SDK_INT < 23) ? this.mType : getType((Icon) this.a);
    }

    @NonNull
    public Uri getUri() {
        return (this.mType != -1 || Build.VERSION.SDK_INT < 23) ? Uri.parse((String) this.a) : getUri((Icon) this.a);
    }

    public Drawable loadDrawable(Context context) {
        checkResource(context);
        if (Build.VERSION.SDK_INT >= 23) {
            return toIcon().loadDrawable(context);
        }
        Drawable loadDrawableInner = loadDrawableInner(context);
        if (!(loadDrawableInner == null || (this.mTintList == null && this.c == b))) {
            loadDrawableInner.mutate();
            DrawableCompat.setTintList(loadDrawableInner, this.mTintList);
            DrawableCompat.setTintMode(loadDrawableInner, this.c);
        }
        return loadDrawableInner;
    }

    public void onPostParceling() {
        Object obj;
        this.c = PorterDuff.Mode.valueOf(this.mTintModeStr);
        int i = this.mType;
        if (i != -1) {
            switch (i) {
                case 1:
                case 5:
                    obj = this.mParcelable;
                    if (obj == null) {
                        byte[] bArr = this.mData;
                        this.a = bArr;
                        this.mType = 3;
                        this.mInt1 = 0;
                        this.mInt2 = bArr.length;
                        return;
                    }
                    break;
                case 2:
                case 4:
                    obj = new String(this.mData, Charset.forName("UTF-16"));
                    break;
                case 3:
                    obj = this.mData;
                    break;
                default:
                    return;
            }
        } else {
            obj = this.mParcelable;
            if (obj == null) {
                throw new IllegalArgumentException("Invalid icon");
            }
        }
        this.a = obj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0021, code lost:
        r4 = r4.getBytes(java.nio.charset.Charset.forName("UTF-16"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002b, code lost:
        r3.mData = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPreParceling(boolean r4) {
        /*
            r3 = this;
            android.graphics.PorterDuff$Mode r0 = r3.c
            java.lang.String r0 = r0.name()
            r3.mTintModeStr = r0
            int r0 = r3.mType
            r1 = -1
            if (r0 == r1) goto L_0x0045
            switch(r0) {
                case 1: goto L_0x002e;
                case 2: goto L_0x001d;
                case 3: goto L_0x0018;
                case 4: goto L_0x0011;
                case 5: goto L_0x002e;
                default: goto L_0x0010;
            }
        L_0x0010:
            goto L_0x004d
        L_0x0011:
            java.lang.Object r4 = r3.a
            java.lang.String r4 = r4.toString()
            goto L_0x0021
        L_0x0018:
            java.lang.Object r4 = r3.a
            byte[] r4 = (byte[]) r4
            goto L_0x002b
        L_0x001d:
            java.lang.Object r4 = r3.a
            java.lang.String r4 = (java.lang.String) r4
        L_0x0021:
            java.lang.String r0 = "UTF-16"
            java.nio.charset.Charset r0 = java.nio.charset.Charset.forName(r0)
            byte[] r4 = r4.getBytes(r0)
        L_0x002b:
            r3.mData = r4
            goto L_0x004d
        L_0x002e:
            if (r4 == 0) goto L_0x0047
            java.lang.Object r4 = r3.a
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.PNG
            r2 = 90
            r4.compress(r1, r2, r0)
            byte[] r4 = r0.toByteArray()
            goto L_0x002b
        L_0x0045:
            if (r4 != 0) goto L_0x004e
        L_0x0047:
            java.lang.Object r4 = r3.a
            android.os.Parcelable r4 = (android.os.Parcelable) r4
            r3.mParcelable = r4
        L_0x004d:
            return
        L_0x004e:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Can't serialize Icon created with IconCompat#createFromIcon"
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompat.onPreParceling(boolean):void");
    }

    public IconCompat setTint(@ColorInt int i) {
        return setTintList(ColorStateList.valueOf(i));
    }

    public IconCompat setTintList(ColorStateList colorStateList) {
        this.mTintList = colorStateList;
        return this;
    }

    public IconCompat setTintMode(PorterDuff.Mode mode) {
        this.c = mode;
        return this;
    }

    public Bundle toBundle() {
        Parcelable parcelable;
        String str;
        Bundle bundle = new Bundle();
        int i = this.mType;
        if (i != -1) {
            switch (i) {
                case 1:
                case 5:
                    str = EXTRA_OBJ;
                    parcelable = (Bitmap) this.a;
                    break;
                case 2:
                case 4:
                    bundle.putString(EXTRA_OBJ, (String) this.a);
                    break;
                case 3:
                    bundle.putByteArray(EXTRA_OBJ, (byte[]) this.a);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid icon");
            }
        } else {
            str = EXTRA_OBJ;
            parcelable = (Parcelable) this.a;
        }
        bundle.putParcelable(str, parcelable);
        bundle.putInt("type", this.mType);
        bundle.putInt(EXTRA_INT1, this.mInt1);
        bundle.putInt(EXTRA_INT2, this.mInt2);
        ColorStateList colorStateList = this.mTintList;
        if (colorStateList != null) {
            bundle.putParcelable(EXTRA_TINT_LIST, colorStateList);
        }
        PorterDuff.Mode mode = this.c;
        if (mode != b) {
            bundle.putString(EXTRA_TINT_MODE, mode.name());
        }
        return bundle;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    @androidx.annotation.RequiresApi(23)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.drawable.Icon toIcon() {
        /*
            r3 = this;
            int r0 = r3.mType
            r1 = -1
            if (r0 == r1) goto L_0x0063
            switch(r0) {
                case 1: goto L_0x004a;
                case 2: goto L_0x003f;
                case 3: goto L_0x0032;
                case 4: goto L_0x0029;
                case 5: goto L_0x0010;
                default: goto L_0x0008;
            }
        L_0x0008:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Unknown type"
            r0.<init>(r1)
            throw r0
        L_0x0010:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 26
            if (r0 < r1) goto L_0x001f
            java.lang.Object r0 = r3.a
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            android.graphics.drawable.Icon r0 = android.graphics.drawable.Icon.createWithAdaptiveBitmap(r0)
            goto L_0x0052
        L_0x001f:
            java.lang.Object r0 = r3.a
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            r1 = 0
            android.graphics.Bitmap r0 = a(r0, r1)
            goto L_0x004e
        L_0x0029:
            java.lang.Object r0 = r3.a
            java.lang.String r0 = (java.lang.String) r0
            android.graphics.drawable.Icon r0 = android.graphics.drawable.Icon.createWithContentUri(r0)
            goto L_0x0052
        L_0x0032:
            java.lang.Object r0 = r3.a
            byte[] r0 = (byte[]) r0
            int r1 = r3.mInt1
            int r2 = r3.mInt2
            android.graphics.drawable.Icon r0 = android.graphics.drawable.Icon.createWithData(r0, r1, r2)
            goto L_0x0052
        L_0x003f:
            java.lang.String r0 = r3.getResPackage()
            int r1 = r3.mInt1
            android.graphics.drawable.Icon r0 = android.graphics.drawable.Icon.createWithResource(r0, r1)
            goto L_0x0052
        L_0x004a:
            java.lang.Object r0 = r3.a
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
        L_0x004e:
            android.graphics.drawable.Icon r0 = android.graphics.drawable.Icon.createWithBitmap(r0)
        L_0x0052:
            android.content.res.ColorStateList r1 = r3.mTintList
            if (r1 == 0) goto L_0x0059
            r0.setTintList(r1)
        L_0x0059:
            android.graphics.PorterDuff$Mode r1 = r3.c
            android.graphics.PorterDuff$Mode r2 = b
            if (r1 == r2) goto L_0x0062
            r0.setTintMode(r1)
        L_0x0062:
            return r0
        L_0x0063:
            java.lang.Object r0 = r3.a
            android.graphics.drawable.Icon r0 = (android.graphics.drawable.Icon) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompat.toIcon():android.graphics.drawable.Icon");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r5 = this;
            int r0 = r5.mType
            r1 = -1
            if (r0 != r1) goto L_0x000c
            java.lang.Object r0 = r5.a
            java.lang.String r0 = java.lang.String.valueOf(r0)
            return r0
        L_0x000c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Icon(typ="
            r0.<init>(r1)
            int r1 = r5.mType
            java.lang.String r1 = typeToString(r1)
            r0.append(r1)
            int r1 = r5.mType
            switch(r1) {
                case 1: goto L_0x006c;
                case 2: goto L_0x0043;
                case 3: goto L_0x002d;
                case 4: goto L_0x0022;
                case 5: goto L_0x006c;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x008c
        L_0x0022:
            java.lang.String r1 = " uri="
            r0.append(r1)
            java.lang.Object r1 = r5.a
            r0.append(r1)
            goto L_0x008c
        L_0x002d:
            java.lang.String r1 = " len="
            r0.append(r1)
            int r1 = r5.mInt1
            r0.append(r1)
            int r1 = r5.mInt2
            if (r1 == 0) goto L_0x008c
            java.lang.String r1 = " off="
            r0.append(r1)
            int r1 = r5.mInt2
            goto L_0x0089
        L_0x0043:
            java.lang.String r1 = " pkg="
            r0.append(r1)
            java.lang.String r1 = r5.getResPackage()
            r0.append(r1)
            java.lang.String r1 = " id="
            r0.append(r1)
            java.lang.String r1 = "0x%08x"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            int r4 = r5.getResId()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2[r3] = r4
            java.lang.String r1 = java.lang.String.format(r1, r2)
            r0.append(r1)
            goto L_0x008c
        L_0x006c:
            java.lang.String r1 = " size="
            r0.append(r1)
            java.lang.Object r1 = r5.a
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getWidth()
            r0.append(r1)
            java.lang.String r1 = "x"
            r0.append(r1)
            java.lang.Object r1 = r5.a
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getHeight()
        L_0x0089:
            r0.append(r1)
        L_0x008c:
            android.content.res.ColorStateList r1 = r5.mTintList
            if (r1 == 0) goto L_0x009a
            java.lang.String r1 = " tint="
            r0.append(r1)
            android.content.res.ColorStateList r1 = r5.mTintList
            r0.append(r1)
        L_0x009a:
            android.graphics.PorterDuff$Mode r1 = r5.c
            android.graphics.PorterDuff$Mode r2 = b
            if (r1 == r2) goto L_0x00aa
            java.lang.String r1 = " mode="
            r0.append(r1)
            android.graphics.PorterDuff$Mode r1 = r5.c
            r0.append(r1)
        L_0x00aa:
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompat.toString():java.lang.String");
    }
}
