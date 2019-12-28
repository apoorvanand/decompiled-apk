package com.facebook.appevents.codeless.internal;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild;
import com.facebook.internal.Utility;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewHierarchy {
    private static final int ADAPTER_VIEW_ITEM_BITMASK = 9;
    private static final int BUTTON_BITMASK = 2;
    private static final int CHECKBOX_BITMASK = 15;
    private static final String CHILDREN_VIEW_KEY = "childviews";
    private static final String CLASS_NAME_KEY = "classname";
    private static final String CLASS_RCTROOTVIEW = "com.facebook.react.ReactRootView";
    private static final String CLASS_RCTTEXTVIEW = "com.facebook.react.views.view.ReactTextView";
    private static final String CLASS_RCTVIEWGROUP = "com.facebook.react.views.view.ReactViewGroup";
    private static final String CLASS_TOUCHTARGETHELPER = "com.facebook.react.uimanager.TouchTargetHelper";
    private static final String CLASS_TYPE_BITMASK_KEY = "classtypebitmask";
    private static final int CLICKABLE_VIEW_BITMASK = 5;
    private static final String DESC_KEY = "description";
    private static final String DIMENSION_HEIGHT_KEY = "height";
    private static final String DIMENSION_KEY = "dimension";
    private static final String DIMENSION_LEFT_KEY = "left";
    private static final String DIMENSION_SCROLL_X_KEY = "scrollx";
    private static final String DIMENSION_SCROLL_Y_KEY = "scrolly";
    private static final String DIMENSION_TOP_KEY = "top";
    private static final String DIMENSION_VISIBILITY_KEY = "visibility";
    private static final String DIMENSION_WIDTH_KEY = "width";
    private static final String GET_ACCESSIBILITY_METHOD = "getAccessibilityDelegate";
    private static final String HINT_KEY = "hint";
    private static final String ICON_BITMAP = "icon_image";
    private static final int ICON_MAX_EDGE_LENGTH = 44;
    private static final String ID_KEY = "id";
    private static final int IMAGEVIEW_BITMASK = 1;
    private static final int INPUT_BITMASK = 11;
    private static final int LABEL_BITMASK = 10;
    private static final String METHOD_FIND_TOUCHTARGET_VIEW = "findTouchTargetView";
    private static final int PICKER_BITMASK = 12;
    private static final int RADIO_GROUP_BITMASK = 14;
    private static final int RATINGBAR_BITMASK = 16;
    private static WeakReference<View> RCTRootViewReference = new WeakReference<>((Object) null);
    private static final int REACT_NATIVE_BUTTON_BITMASK = 6;
    private static final int SWITCH_BITMASK = 13;
    private static final String TAG = ViewHierarchy.class.getCanonicalName();
    private static final String TAG_KEY = "tag";
    private static final int TEXTVIEW_BITMASK = 0;
    private static final String TEXT_IS_BOLD = "is_bold";
    private static final String TEXT_IS_ITALIC = "is_italic";
    private static final String TEXT_KEY = "text";
    private static final String TEXT_SIZE = "font_size";
    private static final String TEXT_STYLE = "text_style";
    @Nullable
    private static Method methodFindTouchTargetView = null;

    @Nullable
    public static View findRCTRootView(View view) {
        while (view != null) {
            if (isRCTRootView(view)) {
                return view;
            }
            ViewParent parent = view.getParent();
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
        return null;
    }

    public static List<View> getChildrenOfView(View view) {
        ArrayList arrayList = new ArrayList();
        if (view != null && (view instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                arrayList.add(viewGroup.getChildAt(i));
            }
        }
        return arrayList;
    }

    private static int getClassTypeBitmask(View view) {
        int i = view instanceof ImageView ? 2 : 0;
        if (isClickableView(view)) {
            i |= 32;
        }
        if (isAdapterViewItem(view)) {
            i |= 512;
        }
        if (!(view instanceof TextView)) {
            return ((view instanceof Spinner) || (view instanceof DatePicker)) ? i | 4096 : view instanceof RatingBar ? i | 65536 : view instanceof RadioGroup ? i | 16384 : (!(view instanceof ViewGroup) || !isRCTButton(view, (View) RCTRootViewReference.get())) ? i : i | 64;
        }
        int i2 = i | 1024 | 1;
        if (view instanceof Button) {
            i2 |= 4;
            if (view instanceof Switch) {
                i2 |= 8192;
            } else if (view instanceof CheckBox) {
                i2 |= 32768;
            }
        }
        return view instanceof EditText ? i2 | 2048 : i2;
    }

    public static JSONObject getDictionaryOfView(View view) {
        if (view.getClass().getName().equals(CLASS_RCTROOTVIEW)) {
            RCTRootViewReference = new WeakReference<>(view);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject = setBasicInfoOfView(view, jSONObject);
            JSONArray jSONArray = new JSONArray();
            List<View> childrenOfView = getChildrenOfView(view);
            for (int i = 0; i < childrenOfView.size(); i++) {
                jSONArray.put(getDictionaryOfView(childrenOfView.get(i)));
            }
            jSONObject.put(CHILDREN_VIEW_KEY, jSONArray);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for view.", e);
        }
        return jSONObject;
    }

    private static JSONObject getDimensionOfView(View view) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(DIMENSION_TOP_KEY, view.getTop());
            jSONObject.put(DIMENSION_LEFT_KEY, view.getLeft());
            jSONObject.put(DIMENSION_WIDTH_KEY, view.getWidth());
            jSONObject.put(DIMENSION_HEIGHT_KEY, view.getHeight());
            jSONObject.put(DIMENSION_SCROLL_X_KEY, view.getScrollX());
            jSONObject.put(DIMENSION_SCROLL_Y_KEY, view.getScrollY());
            jSONObject.put(DIMENSION_VISIBILITY_KEY, view.getVisibility());
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for dimension.", e);
        }
        return jSONObject;
    }

    @Nullable
    public static View.AccessibilityDelegate getExistingDelegate(View view) {
        try {
            return (View.AccessibilityDelegate) view.getClass().getMethod(GET_ACCESSIBILITY_METHOD, new Class[0]).invoke(view, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | NullPointerException | SecurityException | InvocationTargetException unused) {
            return null;
        }
    }

    @Nullable
    public static View.OnTouchListener getExistingOnTouchListener(View view) {
        try {
            Field declaredField = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (declaredField != null) {
                declaredField.setAccessible(true);
            }
            Object obj = declaredField.get(view);
            if (obj == null) {
                return null;
            }
            Field declaredField2 = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnTouchListener");
            if (declaredField2 == null) {
                return null;
            }
            declaredField2.setAccessible(true);
            return (View.OnTouchListener) declaredField2.get(obj);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            Utility.logd(TAG, (Exception) e);
            return null;
        }
    }

    public static String getHintOfView(View view) {
        CharSequence hint = view instanceof TextView ? ((TextView) view).getHint() : view instanceof EditText ? ((EditText) view).getHint() : null;
        return hint == null ? "" : hint.toString();
    }

    @Nullable
    public static ViewGroup getParentOfView(View view) {
        ViewParent parent;
        if (view == null || (parent = view.getParent()) == null || !(parent instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) parent;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c7 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ca  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getTextOfView(android.view.View r7) {
        /*
            boolean r0 = r7 instanceof android.widget.TextView
            r1 = 0
            if (r0 == 0) goto L_0x0020
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.CharSequence r1 = r0.getText()
            boolean r0 = r7 instanceof android.widget.Switch
            if (r0 == 0) goto L_0x00c5
            android.widget.Switch r7 = (android.widget.Switch) r7
            boolean r7 = r7.isChecked()
            if (r7 == 0) goto L_0x001b
            java.lang.String r7 = "1"
            goto L_0x001d
        L_0x001b:
            java.lang.String r7 = "0"
        L_0x001d:
            r1 = r7
            goto L_0x00c5
        L_0x0020:
            boolean r0 = r7 instanceof android.widget.Spinner
            if (r0 == 0) goto L_0x0032
            android.widget.Spinner r7 = (android.widget.Spinner) r7
            java.lang.Object r7 = r7.getSelectedItem()
            if (r7 == 0) goto L_0x00c5
            java.lang.String r1 = r7.toString()
            goto L_0x00c5
        L_0x0032:
            boolean r0 = r7 instanceof android.widget.DatePicker
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 == 0) goto L_0x0063
            android.widget.DatePicker r7 = (android.widget.DatePicker) r7
            int r0 = r7.getYear()
            int r1 = r7.getMonth()
            int r7 = r7.getDayOfMonth()
            java.lang.String r5 = "%04d-%02d-%02d"
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6[r4] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            r6[r3] = r0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r6[r2] = r7
            java.lang.String r1 = java.lang.String.format(r5, r6)
            goto L_0x00c5
        L_0x0063:
            boolean r0 = r7 instanceof android.widget.TimePicker
            if (r0 == 0) goto L_0x008e
            android.widget.TimePicker r7 = (android.widget.TimePicker) r7
            java.lang.Integer r0 = r7.getCurrentHour()
            int r0 = r0.intValue()
            java.lang.Integer r7 = r7.getCurrentMinute()
            int r7 = r7.intValue()
            java.lang.String r1 = "%02d:%02d"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2[r4] = r0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r2[r3] = r7
            java.lang.String r1 = java.lang.String.format(r1, r2)
            goto L_0x00c5
        L_0x008e:
            boolean r0 = r7 instanceof android.widget.RadioGroup
            if (r0 == 0) goto L_0x00b7
            android.widget.RadioGroup r7 = (android.widget.RadioGroup) r7
            int r0 = r7.getCheckedRadioButtonId()
            int r2 = r7.getChildCount()
        L_0x009c:
            if (r4 >= r2) goto L_0x00c5
            android.view.View r3 = r7.getChildAt(r4)
            int r5 = r3.getId()
            if (r5 != r0) goto L_0x00b4
            boolean r5 = r3 instanceof android.widget.RadioButton
            if (r5 == 0) goto L_0x00b4
            android.widget.RadioButton r3 = (android.widget.RadioButton) r3
            java.lang.CharSequence r7 = r3.getText()
            goto L_0x001d
        L_0x00b4:
            int r4 = r4 + 1
            goto L_0x009c
        L_0x00b7:
            boolean r0 = r7 instanceof android.widget.RatingBar
            if (r0 == 0) goto L_0x00c5
            android.widget.RatingBar r7 = (android.widget.RatingBar) r7
            float r7 = r7.getRating()
            java.lang.String r1 = java.lang.String.valueOf(r7)
        L_0x00c5:
            if (r1 != 0) goto L_0x00ca
            java.lang.String r7 = ""
            goto L_0x00ce
        L_0x00ca:
            java.lang.String r7 = r1.toString()
        L_0x00ce:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.ViewHierarchy.getTextOfView(android.view.View):java.lang.String");
    }

    @Nullable
    public static View getTouchReactView(float[] fArr, @Nullable View view) {
        View view2;
        initTouchTargetHelperMethods();
        Method method = methodFindTouchTargetView;
        if (!(method == null || view == null)) {
            try {
                View view3 = (View) method.invoke((Object) null, new Object[]{fArr, view});
                if (view3 == null || view3.getId() <= 0 || (view2 = (View) view3.getParent()) == null) {
                    return null;
                }
                return view2;
            } catch (IllegalAccessException | InvocationTargetException e) {
                Utility.logd(TAG, (Exception) e);
            }
        }
        return null;
    }

    private static float[] getViewLocationOnScreen(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new float[]{(float) iArr[0], (float) iArr[1]};
    }

    private static void initTouchTargetHelperMethods() {
        if (methodFindTouchTargetView == null) {
            try {
                methodFindTouchTargetView = Class.forName(CLASS_TOUCHTARGETHELPER).getDeclaredMethod(METHOD_FIND_TOUCHTARGET_VIEW, new Class[]{float[].class, ViewGroup.class});
                methodFindTouchTargetView.setAccessible(true);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                Utility.logd(TAG, (Exception) e);
            }
        }
    }

    private static boolean isAdapterViewItem(View view) {
        ViewParent parent = view.getParent();
        if (parent != null) {
            return (parent instanceof AdapterView) || (parent instanceof NestedScrollingChild);
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: android.view.View$OnClickListener} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isClickableView(android.view.View r5) {
        /*
            r0 = 0
            java.lang.String r1 = "android.view.View"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Exception -> 0x0034 }
            java.lang.String r2 = "mListenerInfo"
            java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ Exception -> 0x0034 }
            r2 = 1
            if (r1 == 0) goto L_0x0013
            r1.setAccessible(r2)     // Catch:{ Exception -> 0x0034 }
        L_0x0013:
            java.lang.Object r5 = r1.get(r5)     // Catch:{ Exception -> 0x0034 }
            if (r5 != 0) goto L_0x001a
            return r0
        L_0x001a:
            r1 = 0
            java.lang.String r3 = "android.view.View$ListenerInfo"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ Exception -> 0x0034 }
            java.lang.String r4 = "mOnClickListener"
            java.lang.reflect.Field r3 = r3.getDeclaredField(r4)     // Catch:{ Exception -> 0x0034 }
            if (r3 == 0) goto L_0x0030
            java.lang.Object r5 = r3.get(r5)     // Catch:{ Exception -> 0x0034 }
            r1 = r5
            android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1     // Catch:{ Exception -> 0x0034 }
        L_0x0030:
            if (r1 == 0) goto L_0x0033
            r0 = 1
        L_0x0033:
            return r0
        L_0x0034:
            r5 = move-exception
            java.lang.String r1 = TAG
            java.lang.String r2 = "Failed to check if the view is clickable."
            android.util.Log.e(r1, r2, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.ViewHierarchy.isClickableView(android.view.View):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0011, code lost:
        r3 = getTouchReactView(getViewLocationOnScreen(r2), r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isRCTButton(android.view.View r2, @androidx.annotation.Nullable android.view.View r3) {
        /*
            java.lang.Class r0 = r2.getClass()
            java.lang.String r0 = r0.getName()
            java.lang.String r1 = "com.facebook.react.views.view.ReactViewGroup"
            boolean r0 = r0.equals(r1)
            r1 = 0
            if (r0 == 0) goto L_0x0026
            float[] r0 = getViewLocationOnScreen(r2)
            android.view.View r3 = getTouchReactView(r0, r3)
            if (r3 == 0) goto L_0x0026
            int r3 = r3.getId()
            int r2 = r2.getId()
            if (r3 != r2) goto L_0x0026
            r1 = 1
        L_0x0026:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.ViewHierarchy.isRCTButton(android.view.View, android.view.View):boolean");
    }

    public static boolean isRCTRootView(View view) {
        return view.getClass().getName().equals(CLASS_RCTROOTVIEW);
    }

    public static boolean isRCTTextView(View view) {
        return view.getClass().getName().equals(CLASS_RCTTEXTVIEW);
    }

    public static boolean isRCTViewGroup(View view) {
        return view.getClass().getName().equals(CLASS_RCTVIEWGROUP);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        r1 = (android.widget.TextView) r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject setAppearanceOfView(android.view.View r6, org.json.JSONObject r7, float r8) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0079 }
            r0.<init>()     // Catch:{ JSONException -> 0x0079 }
            boolean r1 = r6 instanceof android.widget.TextView     // Catch:{ JSONException -> 0x0079 }
            if (r1 == 0) goto L_0x0033
            r1 = r6
            android.widget.TextView r1 = (android.widget.TextView) r1     // Catch:{ JSONException -> 0x0079 }
            android.graphics.Typeface r2 = r1.getTypeface()     // Catch:{ JSONException -> 0x0079 }
            if (r2 == 0) goto L_0x0033
            java.lang.String r3 = "font_size"
            float r1 = r1.getTextSize()     // Catch:{ JSONException -> 0x0079 }
            double r4 = (double) r1     // Catch:{ JSONException -> 0x0079 }
            r0.put(r3, r4)     // Catch:{ JSONException -> 0x0079 }
            java.lang.String r1 = "is_bold"
            boolean r3 = r2.isBold()     // Catch:{ JSONException -> 0x0079 }
            r0.put(r1, r3)     // Catch:{ JSONException -> 0x0079 }
            java.lang.String r1 = "is_italic"
            boolean r2 = r2.isItalic()     // Catch:{ JSONException -> 0x0079 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0079 }
            java.lang.String r1 = "text_style"
            r7.put(r1, r0)     // Catch:{ JSONException -> 0x0079 }
        L_0x0033:
            boolean r0 = r6 instanceof android.widget.ImageView     // Catch:{ JSONException -> 0x0079 }
            if (r0 == 0) goto L_0x007f
            r0 = r6
            android.widget.ImageView r0 = (android.widget.ImageView) r0     // Catch:{ JSONException -> 0x0079 }
            android.graphics.drawable.Drawable r0 = r0.getDrawable()     // Catch:{ JSONException -> 0x0079 }
            boolean r1 = r0 instanceof android.graphics.drawable.BitmapDrawable     // Catch:{ JSONException -> 0x0079 }
            if (r1 == 0) goto L_0x007f
            int r1 = r6.getHeight()     // Catch:{ JSONException -> 0x0079 }
            float r1 = (float) r1     // Catch:{ JSONException -> 0x0079 }
            float r1 = r1 / r8
            r2 = 1110441984(0x42300000, float:44.0)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x007f
            int r6 = r6.getWidth()     // Catch:{ JSONException -> 0x0079 }
            float r6 = (float) r6     // Catch:{ JSONException -> 0x0079 }
            float r6 = r6 / r8
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 > 0) goto L_0x007f
            android.graphics.drawable.BitmapDrawable r0 = (android.graphics.drawable.BitmapDrawable) r0     // Catch:{ JSONException -> 0x0079 }
            android.graphics.Bitmap r6 = r0.getBitmap()     // Catch:{ JSONException -> 0x0079 }
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream     // Catch:{ JSONException -> 0x0079 }
            r8.<init>()     // Catch:{ JSONException -> 0x0079 }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ JSONException -> 0x0079 }
            r1 = 100
            r6.compress(r0, r1, r8)     // Catch:{ JSONException -> 0x0079 }
            byte[] r6 = r8.toByteArray()     // Catch:{ JSONException -> 0x0079 }
            r8 = 0
            java.lang.String r6 = android.util.Base64.encodeToString(r6, r8)     // Catch:{ JSONException -> 0x0079 }
            java.lang.String r8 = "icon_image"
            r7.put(r8, r6)     // Catch:{ JSONException -> 0x0079 }
            goto L_0x007f
        L_0x0079:
            r6 = move-exception
            java.lang.String r8 = TAG
            com.facebook.internal.Utility.logd((java.lang.String) r8, (java.lang.Exception) r6)
        L_0x007f:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.ViewHierarchy.setAppearanceOfView(android.view.View, org.json.JSONObject, float):org.json.JSONObject");
    }

    public static JSONObject setBasicInfoOfView(View view, JSONObject jSONObject) {
        try {
            String textOfView = getTextOfView(view);
            String hintOfView = getHintOfView(view);
            Object tag = view.getTag();
            CharSequence contentDescription = view.getContentDescription();
            jSONObject.put(CLASS_NAME_KEY, view.getClass().getCanonicalName());
            jSONObject.put(CLASS_TYPE_BITMASK_KEY, getClassTypeBitmask(view));
            jSONObject.put("id", view.getId());
            if (!SensitiveUserDataUtils.isSensitiveUserData(view)) {
                jSONObject.put(TEXT_KEY, textOfView);
            } else {
                jSONObject.put(TEXT_KEY, "");
            }
            jSONObject.put(HINT_KEY, hintOfView);
            if (tag != null) {
                jSONObject.put(TAG_KEY, tag.toString());
            }
            if (contentDescription != null) {
                jSONObject.put("description", contentDescription.toString());
            }
            jSONObject.put(DIMENSION_KEY, getDimensionOfView(view));
        } catch (JSONException e) {
            Utility.logd(TAG, (Exception) e);
        }
        return jSONObject;
    }
}
