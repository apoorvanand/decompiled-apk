package androidx.vectordrawable.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import androidx.core.content.res.ComplexColorCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawableCompat extends VectorDrawableCommon {
    private static final boolean DBG_VECTOR_DRAWABLE = false;
    private static final int LINECAP_BUTT = 0;
    private static final int LINECAP_ROUND = 1;
    private static final int LINECAP_SQUARE = 2;
    private static final int LINEJOIN_BEVEL = 2;
    private static final int LINEJOIN_MITER = 0;
    private static final int LINEJOIN_ROUND = 1;
    private static final int MAX_CACHED_BITMAP_SIZE = 2048;
    private static final String SHAPE_CLIP_PATH = "clip-path";
    private static final String SHAPE_GROUP = "group";
    private static final String SHAPE_PATH = "path";
    private static final String SHAPE_VECTOR = "vector";
    static final PorterDuff.Mode a = PorterDuff.Mode.SRC_IN;
    private boolean mAllowCaching;
    private Drawable.ConstantState mCachedConstantStateDelegate;
    private ColorFilter mColorFilter;
    private boolean mMutated;
    private PorterDuffColorFilter mTintFilter;
    private final Rect mTmpBounds;
    private final float[] mTmpFloats;
    private final Matrix mTmpMatrix;
    private VectorDrawableCompatState mVectorState;

    private static class VClipPath extends VPath {
        public VClipPath() {
        }

        public VClipPath(VClipPath vClipPath) {
            super(vClipPath);
        }

        private void updateStateFromTypedArray(TypedArray typedArray) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.n = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.m = PathParser.createNodesFromPathData(string2);
            }
        }

        public void inflate(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
                TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.d);
                updateStateFromTypedArray(obtainAttributes);
                obtainAttributes.recycle();
            }
        }

        public boolean isClipPath() {
            return true;
        }
    }

    private static class VFullPath extends VPath {
        private static final int FILL_TYPE_WINDING = 0;
        ComplexColorCompat a;
        float b = 0.0f;
        ComplexColorCompat c;
        float d = 1.0f;
        int e = 0;
        float f = 1.0f;
        float g = 0.0f;
        float h = 1.0f;
        float i = 0.0f;
        Paint.Cap j = Paint.Cap.BUTT;
        Paint.Join k = Paint.Join.MITER;
        float l = 4.0f;
        private int[] mThemeAttrs;

        public VFullPath() {
        }

        public VFullPath(VFullPath vFullPath) {
            super(vFullPath);
            this.mThemeAttrs = vFullPath.mThemeAttrs;
            this.a = vFullPath.a;
            this.b = vFullPath.b;
            this.d = vFullPath.d;
            this.c = vFullPath.c;
            this.e = vFullPath.e;
            this.f = vFullPath.f;
            this.g = vFullPath.g;
            this.h = vFullPath.h;
            this.i = vFullPath.i;
            this.j = vFullPath.j;
            this.k = vFullPath.k;
            this.l = vFullPath.l;
        }

        private Paint.Cap getStrokeLineCap(int i2, Paint.Cap cap) {
            switch (i2) {
                case 0:
                    return Paint.Cap.BUTT;
                case 1:
                    return Paint.Cap.ROUND;
                case 2:
                    return Paint.Cap.SQUARE;
                default:
                    return cap;
            }
        }

        private Paint.Join getStrokeLineJoin(int i2, Paint.Join join) {
            switch (i2) {
                case 0:
                    return Paint.Join.MITER;
                case 1:
                    return Paint.Join.ROUND;
                case 2:
                    return Paint.Join.BEVEL;
                default:
                    return join;
            }
        }

        private void updateStateFromTypedArray(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.mThemeAttrs = null;
            if (TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.n = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.m = PathParser.createNodesFromPathData(string2);
                }
                Resources.Theme theme2 = theme;
                this.c = TypedArrayUtils.getNamedComplexColor(typedArray, xmlPullParser, theme2, "fillColor", 1, 0);
                this.f = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "fillAlpha", 12, this.f);
                this.j = getStrokeLineCap(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.j);
                this.k = getStrokeLineJoin(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.k);
                this.l = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.l);
                this.a = TypedArrayUtils.getNamedComplexColor(typedArray, xmlPullParser, theme2, "strokeColor", 3, 0);
                this.d = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeAlpha", 11, this.d);
                this.b = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeWidth", 4, this.b);
                this.h = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathEnd", 6, this.h);
                this.i = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathOffset", 7, this.i);
                this.g = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathStart", 5, this.g);
                this.e = TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "fillType", 13, this.e);
            }
        }

        public void applyTheme(Resources.Theme theme) {
            if (this.mThemeAttrs == null) {
            }
        }

        public boolean canApplyTheme() {
            return this.mThemeAttrs != null;
        }

        /* access modifiers changed from: package-private */
        public float getFillAlpha() {
            return this.f;
        }

        /* access modifiers changed from: package-private */
        @ColorInt
        public int getFillColor() {
            return this.c.getColor();
        }

        /* access modifiers changed from: package-private */
        public float getStrokeAlpha() {
            return this.d;
        }

        /* access modifiers changed from: package-private */
        @ColorInt
        public int getStrokeColor() {
            return this.a.getColor();
        }

        /* access modifiers changed from: package-private */
        public float getStrokeWidth() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathEnd() {
            return this.h;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathOffset() {
            return this.i;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathStart() {
            return this.g;
        }

        public void inflate(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.c);
            updateStateFromTypedArray(obtainAttributes, xmlPullParser, theme);
            obtainAttributes.recycle();
        }

        public boolean isStateful() {
            return this.c.isStateful() || this.a.isStateful();
        }

        public boolean onStateChanged(int[] iArr) {
            return this.a.onStateChanged(iArr) | this.c.onStateChanged(iArr);
        }

        /* access modifiers changed from: package-private */
        public void setFillAlpha(float f2) {
            this.f = f2;
        }

        /* access modifiers changed from: package-private */
        public void setFillColor(int i2) {
            this.c.setColor(i2);
        }

        /* access modifiers changed from: package-private */
        public void setStrokeAlpha(float f2) {
            this.d = f2;
        }

        /* access modifiers changed from: package-private */
        public void setStrokeColor(int i2) {
            this.a.setColor(i2);
        }

        /* access modifiers changed from: package-private */
        public void setStrokeWidth(float f2) {
            this.b = f2;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathEnd(float f2) {
            this.h = f2;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathOffset(float f2) {
            this.i = f2;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathStart(float f2) {
            this.g = f2;
        }
    }

    private static class VGroup extends VObject {
        final Matrix a = new Matrix();
        final ArrayList<VObject> b = new ArrayList<>();
        float c = 0.0f;
        final Matrix d = new Matrix();
        int e;
        private String mGroupName = null;
        private float mPivotX = 0.0f;
        private float mPivotY = 0.0f;
        private float mScaleX = 1.0f;
        private float mScaleY = 1.0f;
        private int[] mThemeAttrs;
        private float mTranslateX = 0.0f;
        private float mTranslateY = 0.0f;

        public VGroup() {
            super();
        }

        public VGroup(VGroup vGroup, ArrayMap<String, Object> arrayMap) {
            super();
            VPath vPath;
            this.c = vGroup.c;
            this.mPivotX = vGroup.mPivotX;
            this.mPivotY = vGroup.mPivotY;
            this.mScaleX = vGroup.mScaleX;
            this.mScaleY = vGroup.mScaleY;
            this.mTranslateX = vGroup.mTranslateX;
            this.mTranslateY = vGroup.mTranslateY;
            this.mThemeAttrs = vGroup.mThemeAttrs;
            this.mGroupName = vGroup.mGroupName;
            this.e = vGroup.e;
            String str = this.mGroupName;
            if (str != null) {
                arrayMap.put(str, this);
            }
            this.d.set(vGroup.d);
            ArrayList<VObject> arrayList = vGroup.b;
            for (int i = 0; i < arrayList.size(); i++) {
                VObject vObject = arrayList.get(i);
                if (vObject instanceof VGroup) {
                    this.b.add(new VGroup((VGroup) vObject, arrayMap));
                } else {
                    if (vObject instanceof VFullPath) {
                        vPath = new VFullPath((VFullPath) vObject);
                    } else if (vObject instanceof VClipPath) {
                        vPath = new VClipPath((VClipPath) vObject);
                    } else {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    this.b.add(vPath);
                    if (vPath.n != null) {
                        arrayMap.put(vPath.n, vPath);
                    }
                }
            }
        }

        private void updateLocalMatrix() {
            this.d.reset();
            this.d.postTranslate(-this.mPivotX, -this.mPivotY);
            this.d.postScale(this.mScaleX, this.mScaleY);
            this.d.postRotate(this.c, 0.0f, 0.0f);
            this.d.postTranslate(this.mTranslateX + this.mPivotX, this.mTranslateY + this.mPivotY);
        }

        private void updateStateFromTypedArray(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.mThemeAttrs = null;
            this.c = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "rotation", 5, this.c);
            this.mPivotX = typedArray.getFloat(1, this.mPivotX);
            this.mPivotY = typedArray.getFloat(2, this.mPivotY);
            this.mScaleX = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "scaleX", 3, this.mScaleX);
            this.mScaleY = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "scaleY", 4, this.mScaleY);
            this.mTranslateX = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "translateX", 6, this.mTranslateX);
            this.mTranslateY = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "translateY", 7, this.mTranslateY);
            String string = typedArray.getString(0);
            if (string != null) {
                this.mGroupName = string;
            }
            updateLocalMatrix();
        }

        public String getGroupName() {
            return this.mGroupName;
        }

        public Matrix getLocalMatrix() {
            return this.d;
        }

        public float getPivotX() {
            return this.mPivotX;
        }

        public float getPivotY() {
            return this.mPivotY;
        }

        public float getRotation() {
            return this.c;
        }

        public float getScaleX() {
            return this.mScaleX;
        }

        public float getScaleY() {
            return this.mScaleY;
        }

        public float getTranslateX() {
            return this.mTranslateX;
        }

        public float getTranslateY() {
            return this.mTranslateY;
        }

        public void inflate(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.b);
            updateStateFromTypedArray(obtainAttributes, xmlPullParser);
            obtainAttributes.recycle();
        }

        public boolean isStateful() {
            for (int i = 0; i < this.b.size(); i++) {
                if (this.b.get(i).isStateful()) {
                    return true;
                }
            }
            return false;
        }

        public boolean onStateChanged(int[] iArr) {
            boolean z = false;
            for (int i = 0; i < this.b.size(); i++) {
                z |= this.b.get(i).onStateChanged(iArr);
            }
            return z;
        }

        public void setPivotX(float f) {
            if (f != this.mPivotX) {
                this.mPivotX = f;
                updateLocalMatrix();
            }
        }

        public void setPivotY(float f) {
            if (f != this.mPivotY) {
                this.mPivotY = f;
                updateLocalMatrix();
            }
        }

        public void setRotation(float f) {
            if (f != this.c) {
                this.c = f;
                updateLocalMatrix();
            }
        }

        public void setScaleX(float f) {
            if (f != this.mScaleX) {
                this.mScaleX = f;
                updateLocalMatrix();
            }
        }

        public void setScaleY(float f) {
            if (f != this.mScaleY) {
                this.mScaleY = f;
                updateLocalMatrix();
            }
        }

        public void setTranslateX(float f) {
            if (f != this.mTranslateX) {
                this.mTranslateX = f;
                updateLocalMatrix();
            }
        }

        public void setTranslateY(float f) {
            if (f != this.mTranslateY) {
                this.mTranslateY = f;
                updateLocalMatrix();
            }
        }
    }

    private static abstract class VObject {
        private VObject() {
        }

        public boolean isStateful() {
            return false;
        }

        public boolean onStateChanged(int[] iArr) {
            return false;
        }
    }

    private static abstract class VPath extends VObject {
        protected PathParser.PathDataNode[] m = null;
        String n;
        int o;

        public VPath() {
            super();
        }

        public VPath(VPath vPath) {
            super();
            this.n = vPath.n;
            this.o = vPath.o;
            this.m = PathParser.deepCopyNodes(vPath.m);
        }

        public void applyTheme(Resources.Theme theme) {
        }

        public boolean canApplyTheme() {
            return false;
        }

        public PathParser.PathDataNode[] getPathData() {
            return this.m;
        }

        public String getPathName() {
            return this.n;
        }

        public boolean isClipPath() {
            return false;
        }

        public String nodesToString(PathParser.PathDataNode[] pathDataNodeArr) {
            String str = " ";
            int i = 0;
            while (i < pathDataNodeArr.length) {
                float[] fArr = pathDataNodeArr[i].mParams;
                String str2 = str + pathDataNodeArr[i].mType + ":";
                for (int i2 = 0; i2 < fArr.length; i2++) {
                    str2 = str2 + fArr[i2] + ",";
                }
                i++;
                str = str2;
            }
            return str;
        }

        public void printVPath(int i) {
            String str = "";
            for (int i2 = 0; i2 < i; i2++) {
                str = str + "    ";
            }
            Log.v("VectorDrawableCompat", str + "current path is :" + this.n + " pathData is " + nodesToString(this.m));
        }

        public void setPathData(PathParser.PathDataNode[] pathDataNodeArr) {
            if (!PathParser.canMorph(this.m, pathDataNodeArr)) {
                this.m = PathParser.deepCopyNodes(pathDataNodeArr);
            } else {
                PathParser.updateNodes(this.m, pathDataNodeArr);
            }
        }

        public void toPath(Path path) {
            path.reset();
            PathParser.PathDataNode[] pathDataNodeArr = this.m;
            if (pathDataNodeArr != null) {
                PathParser.PathDataNode.nodesToPath(pathDataNodeArr, path);
            }
        }
    }

    private static class VPathRenderer {
        private static final Matrix IDENTITY_MATRIX = new Matrix();
        Paint a;
        Paint b;
        final VGroup c;
        float d;
        float e;
        float f;
        float g;
        int h;
        String i;
        Boolean j;
        final ArrayMap<String, Object> k;
        private int mChangingConfigurations;
        private final Matrix mFinalPathMatrix;
        private final Path mPath;
        private PathMeasure mPathMeasure;
        private final Path mRenderPath;

        public VPathRenderer() {
            this.mFinalPathMatrix = new Matrix();
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 255;
            this.i = null;
            this.j = null;
            this.k = new ArrayMap<>();
            this.c = new VGroup();
            this.mPath = new Path();
            this.mRenderPath = new Path();
        }

        public VPathRenderer(VPathRenderer vPathRenderer) {
            this.mFinalPathMatrix = new Matrix();
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 255;
            this.i = null;
            this.j = null;
            this.k = new ArrayMap<>();
            this.c = new VGroup(vPathRenderer.c, this.k);
            this.mPath = new Path(vPathRenderer.mPath);
            this.mRenderPath = new Path(vPathRenderer.mRenderPath);
            this.d = vPathRenderer.d;
            this.e = vPathRenderer.e;
            this.f = vPathRenderer.f;
            this.g = vPathRenderer.g;
            this.mChangingConfigurations = vPathRenderer.mChangingConfigurations;
            this.h = vPathRenderer.h;
            this.i = vPathRenderer.i;
            String str = vPathRenderer.i;
            if (str != null) {
                this.k.put(str, this);
            }
            this.j = vPathRenderer.j;
        }

        private static float cross(float f2, float f3, float f4, float f5) {
            return (f2 * f5) - (f3 * f4);
        }

        private void drawGroupTree(VGroup vGroup, Matrix matrix, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            vGroup.a.set(matrix);
            vGroup.a.preConcat(vGroup.d);
            canvas.save();
            for (int i4 = 0; i4 < vGroup.b.size(); i4++) {
                VObject vObject = vGroup.b.get(i4);
                if (vObject instanceof VGroup) {
                    drawGroupTree((VGroup) vObject, vGroup.a, canvas, i2, i3, colorFilter);
                } else if (vObject instanceof VPath) {
                    drawPath(vGroup, (VPath) vObject, canvas, i2, i3, colorFilter);
                }
            }
            canvas.restore();
        }

        private void drawPath(VGroup vGroup, VPath vPath, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            float f2 = ((float) i2) / this.f;
            float f3 = ((float) i3) / this.g;
            float min = Math.min(f2, f3);
            Matrix matrix = vGroup.a;
            this.mFinalPathMatrix.set(matrix);
            this.mFinalPathMatrix.postScale(f2, f3);
            float matrixScale = getMatrixScale(matrix);
            if (matrixScale != 0.0f) {
                vPath.toPath(this.mPath);
                Path path = this.mPath;
                this.mRenderPath.reset();
                if (vPath.isClipPath()) {
                    this.mRenderPath.addPath(path, this.mFinalPathMatrix);
                    canvas.clipPath(this.mRenderPath);
                    return;
                }
                VFullPath vFullPath = (VFullPath) vPath;
                if (!(vFullPath.g == 0.0f && vFullPath.h == 1.0f)) {
                    float f4 = (vFullPath.g + vFullPath.i) % 1.0f;
                    float f5 = (vFullPath.h + vFullPath.i) % 1.0f;
                    if (this.mPathMeasure == null) {
                        this.mPathMeasure = new PathMeasure();
                    }
                    this.mPathMeasure.setPath(this.mPath, false);
                    float length = this.mPathMeasure.getLength();
                    float f6 = f4 * length;
                    float f7 = f5 * length;
                    path.reset();
                    if (f6 > f7) {
                        this.mPathMeasure.getSegment(f6, length, path, true);
                        this.mPathMeasure.getSegment(0.0f, f7, path, true);
                    } else {
                        this.mPathMeasure.getSegment(f6, f7, path, true);
                    }
                    path.rLineTo(0.0f, 0.0f);
                }
                this.mRenderPath.addPath(path, this.mFinalPathMatrix);
                if (vFullPath.c.willDraw()) {
                    ComplexColorCompat complexColorCompat = vFullPath.c;
                    if (this.b == null) {
                        this.b = new Paint(1);
                        this.b.setStyle(Paint.Style.FILL);
                    }
                    Paint paint = this.b;
                    if (complexColorCompat.isGradient()) {
                        Shader shader = complexColorCompat.getShader();
                        shader.setLocalMatrix(this.mFinalPathMatrix);
                        paint.setShader(shader);
                        paint.setAlpha(Math.round(vFullPath.f * 255.0f));
                    } else {
                        paint.setShader((Shader) null);
                        paint.setAlpha(255);
                        paint.setColor(VectorDrawableCompat.a(complexColorCompat.getColor(), vFullPath.f));
                    }
                    paint.setColorFilter(colorFilter);
                    this.mRenderPath.setFillType(vFullPath.e == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                    canvas.drawPath(this.mRenderPath, paint);
                }
                if (vFullPath.a.willDraw()) {
                    ComplexColorCompat complexColorCompat2 = vFullPath.a;
                    if (this.a == null) {
                        this.a = new Paint(1);
                        this.a.setStyle(Paint.Style.STROKE);
                    }
                    Paint paint2 = this.a;
                    if (vFullPath.k != null) {
                        paint2.setStrokeJoin(vFullPath.k);
                    }
                    if (vFullPath.j != null) {
                        paint2.setStrokeCap(vFullPath.j);
                    }
                    paint2.setStrokeMiter(vFullPath.l);
                    if (complexColorCompat2.isGradient()) {
                        Shader shader2 = complexColorCompat2.getShader();
                        shader2.setLocalMatrix(this.mFinalPathMatrix);
                        paint2.setShader(shader2);
                        paint2.setAlpha(Math.round(vFullPath.d * 255.0f));
                    } else {
                        paint2.setShader((Shader) null);
                        paint2.setAlpha(255);
                        paint2.setColor(VectorDrawableCompat.a(complexColorCompat2.getColor(), vFullPath.d));
                    }
                    paint2.setColorFilter(colorFilter);
                    paint2.setStrokeWidth(vFullPath.b * min * matrixScale);
                    canvas.drawPath(this.mRenderPath, paint2);
                }
            }
        }

        private float getMatrixScale(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float cross = cross(fArr[0], fArr[1], fArr[2], fArr[3]);
            float max = Math.max((float) Math.hypot((double) fArr[0], (double) fArr[1]), (float) Math.hypot((double) fArr[2], (double) fArr[3]));
            if (max > 0.0f) {
                return Math.abs(cross) / max;
            }
            return 0.0f;
        }

        public void draw(Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            drawGroupTree(this.c, IDENTITY_MATRIX, canvas, i2, i3, colorFilter);
        }

        public float getAlpha() {
            return ((float) getRootAlpha()) / 255.0f;
        }

        public int getRootAlpha() {
            return this.h;
        }

        public boolean isStateful() {
            if (this.j == null) {
                this.j = Boolean.valueOf(this.c.isStateful());
            }
            return this.j.booleanValue();
        }

        public boolean onStateChanged(int[] iArr) {
            return this.c.onStateChanged(iArr);
        }

        public void setAlpha(float f2) {
            setRootAlpha((int) (f2 * 255.0f));
        }

        public void setRootAlpha(int i2) {
            this.h = i2;
        }
    }

    private static class VectorDrawableCompatState extends Drawable.ConstantState {
        int a;
        VPathRenderer b;
        ColorStateList c;
        PorterDuff.Mode d;
        boolean e;
        Bitmap f;
        ColorStateList g;
        PorterDuff.Mode h;
        int i;
        boolean j;
        boolean k;
        Paint l;

        public VectorDrawableCompatState() {
            this.c = null;
            this.d = VectorDrawableCompat.a;
            this.b = new VPathRenderer();
        }

        public VectorDrawableCompatState(VectorDrawableCompatState vectorDrawableCompatState) {
            this.c = null;
            this.d = VectorDrawableCompat.a;
            if (vectorDrawableCompatState != null) {
                this.a = vectorDrawableCompatState.a;
                this.b = new VPathRenderer(vectorDrawableCompatState.b);
                if (vectorDrawableCompatState.b.b != null) {
                    this.b.b = new Paint(vectorDrawableCompatState.b.b);
                }
                if (vectorDrawableCompatState.b.a != null) {
                    this.b.a = new Paint(vectorDrawableCompatState.b.a);
                }
                this.c = vectorDrawableCompatState.c;
                this.d = vectorDrawableCompatState.d;
                this.e = vectorDrawableCompatState.e;
            }
        }

        public boolean canReuseBitmap(int i2, int i3) {
            return i2 == this.f.getWidth() && i3 == this.f.getHeight();
        }

        public boolean canReuseCache() {
            return !this.k && this.g == this.c && this.h == this.d && this.j == this.e && this.i == this.b.getRootAlpha();
        }

        public void createCachedBitmapIfNeeded(int i2, int i3) {
            if (this.f == null || !canReuseBitmap(i2, i3)) {
                this.f = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
                this.k = true;
            }
        }

        public void drawCachedBitmapWithRootAlpha(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f, (Rect) null, rect, getPaint(colorFilter));
        }

        public int getChangingConfigurations() {
            return this.a;
        }

        public Paint getPaint(ColorFilter colorFilter) {
            if (!hasTranslucentRoot() && colorFilter == null) {
                return null;
            }
            if (this.l == null) {
                this.l = new Paint();
                this.l.setFilterBitmap(true);
            }
            this.l.setAlpha(this.b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public boolean hasTranslucentRoot() {
            return this.b.getRootAlpha() < 255;
        }

        public boolean isStateful() {
            return this.b.isStateful();
        }

        @NonNull
        public Drawable newDrawable() {
            return new VectorDrawableCompat(this);
        }

        @NonNull
        public Drawable newDrawable(Resources resources) {
            return new VectorDrawableCompat(this);
        }

        public boolean onStateChanged(int[] iArr) {
            boolean onStateChanged = this.b.onStateChanged(iArr);
            this.k |= onStateChanged;
            return onStateChanged;
        }

        public void updateCacheStates() {
            this.g = this.c;
            this.h = this.d;
            this.i = this.b.getRootAlpha();
            this.j = this.e;
            this.k = false;
        }

        public void updateCachedBitmap(int i2, int i3) {
            this.f.eraseColor(0);
            this.b.draw(new Canvas(this.f), i2, i3, (ColorFilter) null);
        }
    }

    @RequiresApi(24)
    private static class VectorDrawableDelegateState extends Drawable.ConstantState {
        private final Drawable.ConstantState mDelegateState;

        public VectorDrawableDelegateState(Drawable.ConstantState constantState) {
            this.mDelegateState = constantState;
        }

        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }

        public Drawable newDrawable() {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.d = (VectorDrawable) this.mDelegateState.newDrawable();
            return vectorDrawableCompat;
        }

        public Drawable newDrawable(Resources resources) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.d = (VectorDrawable) this.mDelegateState.newDrawable(resources);
            return vectorDrawableCompat;
        }

        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.d = (VectorDrawable) this.mDelegateState.newDrawable(resources, theme);
            return vectorDrawableCompat;
        }
    }

    VectorDrawableCompat() {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = new VectorDrawableCompatState();
    }

    VectorDrawableCompat(@NonNull VectorDrawableCompatState vectorDrawableCompatState) {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = vectorDrawableCompatState;
        this.mTintFilter = a(this.mTintFilter, vectorDrawableCompatState.c, vectorDrawableCompatState.d);
    }

    static int a(int i, float f) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (((int) (((float) Color.alpha(i)) * f)) << 24);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034 A[Catch:{ IOException | XmlPullParserException -> 0x0041 }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0039 A[Catch:{ IOException | XmlPullParserException -> 0x0041 }] */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.vectordrawable.graphics.drawable.VectorDrawableCompat create(@androidx.annotation.NonNull android.content.res.Resources r4, @androidx.annotation.DrawableRes int r5, @androidx.annotation.Nullable android.content.res.Resources.Theme r6) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 24
            if (r0 < r1) goto L_0x001f
            androidx.vectordrawable.graphics.drawable.VectorDrawableCompat r0 = new androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
            r0.<init>()
            android.graphics.drawable.Drawable r4 = androidx.core.content.res.ResourcesCompat.getDrawable(r4, r5, r6)
            r0.d = r4
            androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$VectorDrawableDelegateState r4 = new androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$VectorDrawableDelegateState
            android.graphics.drawable.Drawable r5 = r0.d
            android.graphics.drawable.Drawable$ConstantState r5 = r5.getConstantState()
            r4.<init>(r5)
            r0.mCachedConstantStateDelegate = r4
            return r0
        L_0x001f:
            android.content.res.XmlResourceParser r5 = r4.getXml(r5)     // Catch:{ XmlPullParserException -> 0x0043, IOException -> 0x0041 }
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r5)     // Catch:{ XmlPullParserException -> 0x0043, IOException -> 0x0041 }
        L_0x0027:
            int r1 = r5.next()     // Catch:{ XmlPullParserException -> 0x0043, IOException -> 0x0041 }
            r2 = 2
            if (r1 == r2) goto L_0x0032
            r3 = 1
            if (r1 == r3) goto L_0x0032
            goto L_0x0027
        L_0x0032:
            if (r1 != r2) goto L_0x0039
            androidx.vectordrawable.graphics.drawable.VectorDrawableCompat r4 = createFromXmlInner(r4, r5, r0, r6)     // Catch:{ XmlPullParserException -> 0x0043, IOException -> 0x0041 }
            return r4
        L_0x0039:
            org.xmlpull.v1.XmlPullParserException r4 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ XmlPullParserException -> 0x0043, IOException -> 0x0041 }
            java.lang.String r5 = "No start tag found"
            r4.<init>(r5)     // Catch:{ XmlPullParserException -> 0x0043, IOException -> 0x0041 }
            throw r4     // Catch:{ XmlPullParserException -> 0x0043, IOException -> 0x0041 }
        L_0x0041:
            r4 = move-exception
            goto L_0x0044
        L_0x0043:
            r4 = move-exception
        L_0x0044:
            java.lang.String r5 = "VectorDrawableCompat"
            java.lang.String r6 = "parser error"
            android.util.Log.e(r5, r6, r4)
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.create(android.content.res.Resources, int, android.content.res.Resources$Theme):androidx.vectordrawable.graphics.drawable.VectorDrawableCompat");
    }

    public static VectorDrawableCompat createFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
        vectorDrawableCompat.inflate(resources, xmlPullParser, attributeSet, theme);
        return vectorDrawableCompat;
    }

    private void inflateInternal(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int i;
        int i2;
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        VPathRenderer vPathRenderer = vectorDrawableCompatState.b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(vPathRenderer.c);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                VGroup vGroup = (VGroup) arrayDeque.peek();
                if (SHAPE_PATH.equals(name)) {
                    VFullPath vFullPath = new VFullPath();
                    vFullPath.inflate(resources, attributeSet, theme, xmlPullParser);
                    vGroup.b.add(vFullPath);
                    if (vFullPath.getPathName() != null) {
                        vPathRenderer.k.put(vFullPath.getPathName(), vFullPath);
                    }
                    z = false;
                    i = vectorDrawableCompatState.a;
                    i2 = vFullPath.o;
                } else if (SHAPE_CLIP_PATH.equals(name)) {
                    VClipPath vClipPath = new VClipPath();
                    vClipPath.inflate(resources, attributeSet, theme, xmlPullParser);
                    vGroup.b.add(vClipPath);
                    if (vClipPath.getPathName() != null) {
                        vPathRenderer.k.put(vClipPath.getPathName(), vClipPath);
                    }
                    i = vectorDrawableCompatState.a;
                    i2 = vClipPath.o;
                } else if (SHAPE_GROUP.equals(name)) {
                    VGroup vGroup2 = new VGroup();
                    vGroup2.inflate(resources, attributeSet, theme, xmlPullParser);
                    vGroup.b.add(vGroup2);
                    arrayDeque.push(vGroup2);
                    if (vGroup2.getGroupName() != null) {
                        vPathRenderer.k.put(vGroup2.getGroupName(), vGroup2);
                    }
                    i = vectorDrawableCompatState.a;
                    i2 = vGroup2.e;
                }
                vectorDrawableCompatState.a = i2 | i;
            } else if (eventType == 3 && SHAPE_GROUP.equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z) {
            throw new XmlPullParserException("no path defined");
        }
    }

    private boolean needMirroring() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && DrawableCompat.getLayoutDirection(this) == 1;
    }

    private static PorterDuff.Mode parseTintModeCompat(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    private void printGroupTree(VGroup vGroup, int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            str = str + "    ";
        }
        Log.v("VectorDrawableCompat", str + "current group is :" + vGroup.getGroupName() + " rotation is " + vGroup.c);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("matrix is :");
        sb.append(vGroup.getLocalMatrix().toString());
        Log.v("VectorDrawableCompat", sb.toString());
        for (int i3 = 0; i3 < vGroup.b.size(); i3++) {
            VObject vObject = vGroup.b.get(i3);
            if (vObject instanceof VGroup) {
                printGroupTree((VGroup) vObject, i + 1);
            } else {
                ((VPath) vObject).printVPath(i + 1);
            }
        }
    }

    private void updateStateFromTypedArray(TypedArray typedArray, XmlPullParser xmlPullParser) {
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        VPathRenderer vPathRenderer = vectorDrawableCompatState.b;
        vectorDrawableCompatState.d = parseTintModeCompat(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateList = typedArray.getColorStateList(1);
        if (colorStateList != null) {
            vectorDrawableCompatState.c = colorStateList;
        }
        vectorDrawableCompatState.e = TypedArrayUtils.getNamedBoolean(typedArray, xmlPullParser, "autoMirrored", 5, vectorDrawableCompatState.e);
        vPathRenderer.f = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "viewportWidth", 7, vPathRenderer.f);
        vPathRenderer.g = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "viewportHeight", 8, vPathRenderer.g);
        if (vPathRenderer.f <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        } else if (vPathRenderer.g > 0.0f) {
            vPathRenderer.d = typedArray.getDimension(3, vPathRenderer.d);
            vPathRenderer.e = typedArray.getDimension(2, vPathRenderer.e);
            if (vPathRenderer.d <= 0.0f) {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
            } else if (vPathRenderer.e > 0.0f) {
                vPathRenderer.setAlpha(TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "alpha", 4, vPathRenderer.getAlpha()));
                String string = typedArray.getString(0);
                if (string != null) {
                    vPathRenderer.i = string;
                    vPathRenderer.k.put(string, vPathRenderer);
                }
            } else {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
            }
        } else {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
    }

    /* access modifiers changed from: package-private */
    public PorterDuffColorFilter a(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    /* access modifiers changed from: package-private */
    public Object a(String str) {
        return this.mVectorState.b.k.get(str);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.mAllowCaching = z;
    }

    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    public boolean canApplyTheme() {
        if (this.d == null) {
            return false;
        }
        DrawableCompat.canApplyTheme(this.d);
        return false;
    }

    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public void draw(Canvas canvas) {
        if (this.d != null) {
            this.d.draw(canvas);
            return;
        }
        copyBounds(this.mTmpBounds);
        if (this.mTmpBounds.width() > 0 && this.mTmpBounds.height() > 0) {
            ColorFilter colorFilter = this.mColorFilter;
            if (colorFilter == null) {
                colorFilter = this.mTintFilter;
            }
            canvas.getMatrix(this.mTmpMatrix);
            this.mTmpMatrix.getValues(this.mTmpFloats);
            float abs = Math.abs(this.mTmpFloats[0]);
            float abs2 = Math.abs(this.mTmpFloats[4]);
            float abs3 = Math.abs(this.mTmpFloats[1]);
            float abs4 = Math.abs(this.mTmpFloats[3]);
            if (!(abs3 == 0.0f && abs4 == 0.0f)) {
                abs = 1.0f;
                abs2 = 1.0f;
            }
            int min = Math.min(2048, (int) (((float) this.mTmpBounds.width()) * abs));
            int min2 = Math.min(2048, (int) (((float) this.mTmpBounds.height()) * abs2));
            if (min > 0 && min2 > 0) {
                int save = canvas.save();
                canvas.translate((float) this.mTmpBounds.left, (float) this.mTmpBounds.top);
                if (needMirroring()) {
                    canvas.translate((float) this.mTmpBounds.width(), 0.0f);
                    canvas.scale(-1.0f, 1.0f);
                }
                this.mTmpBounds.offsetTo(0, 0);
                this.mVectorState.createCachedBitmapIfNeeded(min, min2);
                if (!this.mAllowCaching) {
                    this.mVectorState.updateCachedBitmap(min, min2);
                } else if (!this.mVectorState.canReuseCache()) {
                    this.mVectorState.updateCachedBitmap(min, min2);
                    this.mVectorState.updateCacheStates();
                }
                this.mVectorState.drawCachedBitmapWithRootAlpha(canvas, colorFilter, this.mTmpBounds);
                canvas.restoreToCount(save);
            }
        }
    }

    public int getAlpha() {
        return this.d != null ? DrawableCompat.getAlpha(this.d) : this.mVectorState.b.getRootAlpha();
    }

    public int getChangingConfigurations() {
        return this.d != null ? this.d.getChangingConfigurations() : super.getChangingConfigurations() | this.mVectorState.getChangingConfigurations();
    }

    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public Drawable.ConstantState getConstantState() {
        if (this.d != null && Build.VERSION.SDK_INT >= 24) {
            return new VectorDrawableDelegateState(this.d.getConstantState());
        }
        this.mVectorState.a = getChangingConfigurations();
        return this.mVectorState;
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public int getIntrinsicHeight() {
        return this.d != null ? this.d.getIntrinsicHeight() : (int) this.mVectorState.b.e;
    }

    public int getIntrinsicWidth() {
        return this.d != null ? this.d.getIntrinsicWidth() : (int) this.mVectorState.b.d;
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public int getOpacity() {
        if (this.d != null) {
            return this.d.getOpacity();
        }
        return -3;
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getPixelSize() {
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        if (vectorDrawableCompatState == null || vectorDrawableCompatState.b == null || this.mVectorState.b.d == 0.0f || this.mVectorState.b.e == 0.0f || this.mVectorState.b.g == 0.0f || this.mVectorState.b.f == 0.0f) {
            return 1.0f;
        }
        float f = this.mVectorState.b.d;
        float f2 = this.mVectorState.b.e;
        return Math.min(this.mVectorState.b.f / f, this.mVectorState.b.g / f2);
    }

    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        if (this.d != null) {
            this.d.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, (Resources.Theme) null);
        }
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        if (this.d != null) {
            DrawableCompat.inflate(this.d, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        vectorDrawableCompatState.b = new VPathRenderer();
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.a);
        updateStateFromTypedArray(obtainAttributes, xmlPullParser);
        obtainAttributes.recycle();
        vectorDrawableCompatState.a = getChangingConfigurations();
        vectorDrawableCompatState.k = true;
        inflateInternal(resources, xmlPullParser, attributeSet, theme);
        this.mTintFilter = a(this.mTintFilter, vectorDrawableCompatState.c, vectorDrawableCompatState.d);
    }

    public void invalidateSelf() {
        if (this.d != null) {
            this.d.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    public boolean isAutoMirrored() {
        return this.d != null ? DrawableCompat.isAutoMirrored(this.d) : this.mVectorState.e;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0011, code lost:
        r0 = r1.mVectorState;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isStateful() {
        /*
            r1 = this;
            android.graphics.drawable.Drawable r0 = r1.d
            if (r0 == 0) goto L_0x000b
            android.graphics.drawable.Drawable r0 = r1.d
            boolean r0 = r0.isStateful()
            return r0
        L_0x000b:
            boolean r0 = super.isStateful()
            if (r0 != 0) goto L_0x002e
            androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$VectorDrawableCompatState r0 = r1.mVectorState
            if (r0 == 0) goto L_0x002c
            boolean r0 = r0.isStateful()
            if (r0 != 0) goto L_0x002e
            androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$VectorDrawableCompatState r0 = r1.mVectorState
            android.content.res.ColorStateList r0 = r0.c
            if (r0 == 0) goto L_0x002c
            androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$VectorDrawableCompatState r0 = r1.mVectorState
            android.content.res.ColorStateList r0 = r0.c
            boolean r0 = r0.isStateful()
            if (r0 == 0) goto L_0x002c
            goto L_0x002e
        L_0x002c:
            r0 = 0
            goto L_0x002f
        L_0x002e:
            r0 = 1
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.isStateful():boolean");
    }

    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public Drawable mutate() {
        if (this.d != null) {
            this.d.mutate();
            return this;
        }
        if (!this.mMutated && super.mutate() == this) {
            this.mVectorState = new VectorDrawableCompatState(this.mVectorState);
            this.mMutated = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        if (this.d != null) {
            this.d.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        if (this.d != null) {
            return this.d.setState(iArr);
        }
        boolean z = false;
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        if (!(vectorDrawableCompatState.c == null || vectorDrawableCompatState.d == null)) {
            this.mTintFilter = a(this.mTintFilter, vectorDrawableCompatState.c, vectorDrawableCompatState.d);
            invalidateSelf();
            z = true;
        }
        if (!vectorDrawableCompatState.isStateful() || !vectorDrawableCompatState.onStateChanged(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    public void scheduleSelf(Runnable runnable, long j) {
        if (this.d != null) {
            this.d.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    public void setAlpha(int i) {
        if (this.d != null) {
            this.d.setAlpha(i);
        } else if (this.mVectorState.b.getRootAlpha() != i) {
            this.mVectorState.b.setRootAlpha(i);
            invalidateSelf();
        }
    }

    public void setAutoMirrored(boolean z) {
        if (this.d != null) {
            DrawableCompat.setAutoMirrored(this.d, z);
        } else {
            this.mVectorState.e = z;
        }
    }

    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.d != null) {
            this.d.setColorFilter(colorFilter);
            return;
        }
        this.mColorFilter = colorFilter;
        invalidateSelf();
    }

    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    public /* bridge */ /* synthetic */ void setHotspot(float f, float f2) {
        super.setHotspot(f, f2);
    }

    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    public void setTint(int i) {
        if (this.d != null) {
            DrawableCompat.setTint(this.d, i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        if (this.d != null) {
            DrawableCompat.setTintList(this.d, colorStateList);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        if (vectorDrawableCompatState.c != colorStateList) {
            vectorDrawableCompatState.c = colorStateList;
            this.mTintFilter = a(this.mTintFilter, colorStateList, vectorDrawableCompatState.d);
            invalidateSelf();
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        if (this.d != null) {
            DrawableCompat.setTintMode(this.d, mode);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        if (vectorDrawableCompatState.d != mode) {
            vectorDrawableCompatState.d = mode;
            this.mTintFilter = a(this.mTintFilter, vectorDrawableCompatState.c, mode);
            invalidateSelf();
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        return this.d != null ? this.d.setVisible(z, z2) : super.setVisible(z, z2);
    }

    public void unscheduleSelf(Runnable runnable) {
        if (this.d != null) {
            this.d.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }
}
