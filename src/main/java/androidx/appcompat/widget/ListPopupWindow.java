package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.view.ViewCompat;
import androidx.core.widget.PopupWindowCompat;
import java.lang.reflect.Method;

public class ListPopupWindow implements ShowableListMenu {
    private static final boolean DEBUG = false;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private static Method sClipToWindowEnabledMethod;
    private static Method sGetMaxAvailableHeightMethod;
    private static Method sSetEpicenterBoundsMethod;
    DropDownListView c;
    int d;
    final ResizePopupRunnable e;
    final Handler f;
    PopupWindow g;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch;
    private final ListSelectorHider mHideSelector;
    private boolean mIsAnimatedFromAnchor;
    private AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    private boolean mModal;
    private DataSetObserver mObserver;
    private boolean mOverlapAnchor;
    private boolean mOverlapAnchorSet;
    private int mPromptPosition;
    private View mPromptView;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;

    private class ListSelectorHider implements Runnable {
        ListSelectorHider() {
        }

        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }

    private class PopupDataSetObserver extends DataSetObserver {
        PopupDataSetObserver() {
        }

        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    private class PopupScrollListener implements AbsListView.OnScrollListener {
        PopupScrollListener() {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.this.g.getContentView() != null) {
                ListPopupWindow.this.f.removeCallbacks(ListPopupWindow.this.e);
                ListPopupWindow.this.e.run();
            }
        }
    }

    private class PopupTouchInterceptor implements View.OnTouchListener {
        PopupTouchInterceptor() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && ListPopupWindow.this.g != null && ListPopupWindow.this.g.isShowing() && x >= 0 && x < ListPopupWindow.this.g.getWidth() && y >= 0 && y < ListPopupWindow.this.g.getHeight()) {
                ListPopupWindow.this.f.postDelayed(ListPopupWindow.this.e, 250);
                return false;
            } else if (action != 1) {
                return false;
            } else {
                ListPopupWindow.this.f.removeCallbacks(ListPopupWindow.this.e);
                return false;
            }
        }
    }

    private class ResizePopupRunnable implements Runnable {
        ResizePopupRunnable() {
        }

        public void run() {
            if (ListPopupWindow.this.c != null && ViewCompat.isAttachedToWindow(ListPopupWindow.this.c) && ListPopupWindow.this.c.getCount() > ListPopupWindow.this.c.getChildCount() && ListPopupWindow.this.c.getChildCount() <= ListPopupWindow.this.d) {
                ListPopupWindow.this.g.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }

    static {
        Class<PopupWindow> cls = PopupWindow.class;
        try {
            sClipToWindowEnabledMethod = cls.getDeclaredMethod("setClipToScreenEnabled", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException unused) {
            Log.i(TAG, "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        Class<PopupWindow> cls2 = PopupWindow.class;
        try {
            sGetMaxAvailableHeightMethod = cls2.getDeclaredMethod("getMaxAvailableHeight", new Class[]{View.class, Integer.TYPE, Boolean.TYPE});
        } catch (NoSuchMethodException unused2) {
            Log.i(TAG, "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        try {
            sSetEpicenterBoundsMethod = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", new Class[]{Rect.class});
        } catch (NoSuchMethodException unused3) {
            Log.i(TAG, "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    public ListPopupWindow(@NonNull Context context) {
        this(context, (AttributeSet) null, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownWindowLayoutType = 1002;
        this.mIsAnimatedFromAnchor = true;
        this.mDropDownGravity = 0;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.d = Integer.MAX_VALUE;
        this.mPromptPosition = 0;
        this.e = new ResizePopupRunnable();
        this.mTouchInterceptor = new PopupTouchInterceptor();
        this.mScrollListener = new PopupScrollListener();
        this.mHideSelector = new ListSelectorHider();
        this.mTempRect = new Rect();
        this.mContext = context;
        this.f = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ListPopupWindow, i, i2);
        this.mDropDownHorizontalOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.mDropDownVerticalOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.mDropDownVerticalOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        obtainStyledAttributes.recycle();
        this.g = new AppCompatPopupWindow(context, attributeSet, i, i2);
        this.g.setInputMethodMode(1);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v24, resolved type: androidx.appcompat.widget.DropDownListView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v25, resolved type: androidx.appcompat.widget.DropDownListView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: android.widget.LinearLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v30, resolved type: androidx.appcompat.widget.DropDownListView} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int buildDropDown() {
        /*
            r12 = this;
            androidx.appcompat.widget.DropDownListView r0 = r12.c
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = -1
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L_0x00c0
            android.content.Context r0 = r12.mContext
            androidx.appcompat.widget.ListPopupWindow$2 r5 = new androidx.appcompat.widget.ListPopupWindow$2
            r5.<init>()
            r12.mShowDropDownRunnable = r5
            boolean r5 = r12.mModal
            r5 = r5 ^ r3
            androidx.appcompat.widget.DropDownListView r5 = r12.a(r0, r5)
            r12.c = r5
            android.graphics.drawable.Drawable r5 = r12.mDropDownListHighlight
            if (r5 == 0) goto L_0x0024
            androidx.appcompat.widget.DropDownListView r6 = r12.c
            r6.setSelector(r5)
        L_0x0024:
            androidx.appcompat.widget.DropDownListView r5 = r12.c
            android.widget.ListAdapter r6 = r12.mAdapter
            r5.setAdapter(r6)
            androidx.appcompat.widget.DropDownListView r5 = r12.c
            android.widget.AdapterView$OnItemClickListener r6 = r12.mItemClickListener
            r5.setOnItemClickListener(r6)
            androidx.appcompat.widget.DropDownListView r5 = r12.c
            r5.setFocusable(r3)
            androidx.appcompat.widget.DropDownListView r5 = r12.c
            r5.setFocusableInTouchMode(r3)
            androidx.appcompat.widget.DropDownListView r5 = r12.c
            androidx.appcompat.widget.ListPopupWindow$3 r6 = new androidx.appcompat.widget.ListPopupWindow$3
            r6.<init>()
            r5.setOnItemSelectedListener(r6)
            androidx.appcompat.widget.DropDownListView r5 = r12.c
            androidx.appcompat.widget.ListPopupWindow$PopupScrollListener r6 = r12.mScrollListener
            r5.setOnScrollListener(r6)
            android.widget.AdapterView$OnItemSelectedListener r5 = r12.mItemSelectedListener
            if (r5 == 0) goto L_0x0056
            androidx.appcompat.widget.DropDownListView r6 = r12.c
            r6.setOnItemSelectedListener(r5)
        L_0x0056:
            androidx.appcompat.widget.DropDownListView r5 = r12.c
            android.view.View r6 = r12.mPromptView
            if (r6 == 0) goto L_0x00b9
            android.widget.LinearLayout r7 = new android.widget.LinearLayout
            r7.<init>(r0)
            r7.setOrientation(r3)
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
            r8 = 1065353216(0x3f800000, float:1.0)
            r0.<init>(r2, r4, r8)
            int r8 = r12.mPromptPosition
            switch(r8) {
                case 0: goto L_0x0090;
                case 1: goto L_0x0089;
                default: goto L_0x0070;
            }
        L_0x0070:
            java.lang.String r0 = "ListPopupWindow"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = "Invalid hint position "
            r5.append(r8)
            int r8 = r12.mPromptPosition
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            android.util.Log.e(r0, r5)
            goto L_0x0096
        L_0x0089:
            r7.addView(r5, r0)
            r7.addView(r6)
            goto L_0x0096
        L_0x0090:
            r7.addView(r6)
            r7.addView(r5, r0)
        L_0x0096:
            int r0 = r12.mDropDownWidth
            if (r0 < 0) goto L_0x009d
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x009f
        L_0x009d:
            r0 = 0
            r5 = 0
        L_0x009f:
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r5)
            r6.measure(r0, r4)
            android.view.ViewGroup$LayoutParams r0 = r6.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r0 = (android.widget.LinearLayout.LayoutParams) r0
            int r5 = r6.getMeasuredHeight()
            int r6 = r0.topMargin
            int r5 = r5 + r6
            int r0 = r0.bottomMargin
            int r5 = r5 + r0
            r0 = r5
            r5 = r7
            goto L_0x00ba
        L_0x00b9:
            r0 = 0
        L_0x00ba:
            android.widget.PopupWindow r6 = r12.g
            r6.setContentView(r5)
            goto L_0x00de
        L_0x00c0:
            android.widget.PopupWindow r0 = r12.g
            android.view.View r0 = r0.getContentView()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            android.view.View r0 = r12.mPromptView
            if (r0 == 0) goto L_0x00dd
            android.view.ViewGroup$LayoutParams r5 = r0.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r5 = (android.widget.LinearLayout.LayoutParams) r5
            int r0 = r0.getMeasuredHeight()
            int r6 = r5.topMargin
            int r0 = r0 + r6
            int r5 = r5.bottomMargin
            int r0 = r0 + r5
            goto L_0x00de
        L_0x00dd:
            r0 = 0
        L_0x00de:
            android.widget.PopupWindow r5 = r12.g
            android.graphics.drawable.Drawable r5 = r5.getBackground()
            if (r5 == 0) goto L_0x0100
            android.graphics.Rect r6 = r12.mTempRect
            r5.getPadding(r6)
            android.graphics.Rect r5 = r12.mTempRect
            int r5 = r5.top
            android.graphics.Rect r6 = r12.mTempRect
            int r6 = r6.bottom
            int r5 = r5 + r6
            boolean r6 = r12.mDropDownVerticalOffsetSet
            if (r6 != 0) goto L_0x0106
            android.graphics.Rect r6 = r12.mTempRect
            int r6 = r6.top
            int r6 = -r6
            r12.mDropDownVerticalOffset = r6
            goto L_0x0106
        L_0x0100:
            android.graphics.Rect r5 = r12.mTempRect
            r5.setEmpty()
            r5 = 0
        L_0x0106:
            android.widget.PopupWindow r6 = r12.g
            int r6 = r6.getInputMethodMode()
            r7 = 2
            if (r6 != r7) goto L_0x0110
            goto L_0x0111
        L_0x0110:
            r3 = 0
        L_0x0111:
            android.view.View r4 = r12.getAnchorView()
            int r6 = r12.mDropDownVerticalOffset
            int r3 = r12.getMaxAvailableHeight(r4, r6, r3)
            boolean r4 = r12.mDropDownAlwaysVisible
            if (r4 != 0) goto L_0x0185
            int r4 = r12.mDropDownHeight
            if (r4 != r2) goto L_0x0124
            goto L_0x0185
        L_0x0124:
            int r2 = r12.mDropDownWidth
            r4 = 1073741824(0x40000000, float:2.0)
            switch(r2) {
                case -2: goto L_0x014c;
                case -1: goto L_0x0131;
                default: goto L_0x012b;
            }
        L_0x012b:
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r4)
        L_0x012f:
            r7 = r1
            goto L_0x0167
        L_0x0131:
            android.content.Context r1 = r12.mContext
            android.content.res.Resources r1 = r1.getResources()
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()
            int r1 = r1.widthPixels
            android.graphics.Rect r2 = r12.mTempRect
            int r2 = r2.left
            android.graphics.Rect r6 = r12.mTempRect
            int r6 = r6.right
            int r2 = r2 + r6
            int r1 = r1 - r2
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r4)
            goto L_0x012f
        L_0x014c:
            android.content.Context r2 = r12.mContext
            android.content.res.Resources r2 = r2.getResources()
            android.util.DisplayMetrics r2 = r2.getDisplayMetrics()
            int r2 = r2.widthPixels
            android.graphics.Rect r4 = r12.mTempRect
            int r4 = r4.left
            android.graphics.Rect r6 = r12.mTempRect
            int r6 = r6.right
            int r4 = r4 + r6
            int r2 = r2 - r4
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r1)
            goto L_0x012f
        L_0x0167:
            androidx.appcompat.widget.DropDownListView r6 = r12.c
            r8 = 0
            r9 = -1
            int r10 = r3 - r0
            r11 = -1
            int r1 = r6.measureHeightOfChildrenCompat(r7, r8, r9, r10, r11)
            if (r1 <= 0) goto L_0x0183
            androidx.appcompat.widget.DropDownListView r2 = r12.c
            int r2 = r2.getPaddingTop()
            androidx.appcompat.widget.DropDownListView r3 = r12.c
            int r3 = r3.getPaddingBottom()
            int r2 = r2 + r3
            int r5 = r5 + r2
            int r0 = r0 + r5
        L_0x0183:
            int r1 = r1 + r0
            return r1
        L_0x0185:
            int r3 = r3 + r5
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ListPopupWindow.buildDropDown():int");
    }

    private int getMaxAvailableHeight(View view, int i, boolean z) {
        Method method = sGetMaxAvailableHeightMethod;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.g, new Object[]{view, Integer.valueOf(i), Boolean.valueOf(z)})).intValue();
            } catch (Exception unused) {
                Log.i(TAG, "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.g.getMaxAvailableHeight(view, i);
    }

    private static boolean isConfirmKey(int i) {
        return i == 66 || i == 23;
    }

    private void removePromptView() {
        View view = this.mPromptView;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mPromptView);
            }
        }
    }

    private void setPopupClipToScreenEnabled(boolean z) {
        Method method = sClipToWindowEnabledMethod;
        if (method != null) {
            try {
                method.invoke(this.g, new Object[]{Boolean.valueOf(z)});
            } catch (Exception unused) {
                Log.i(TAG, "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public DropDownListView a(Context context, boolean z) {
        return new DropDownListView(context, z);
    }

    public void clearListSelection() {
        DropDownListView dropDownListView = this.c;
        if (dropDownListView != null) {
            dropDownListView.setListSelectionHidden(true);
            dropDownListView.requestLayout();
        }
    }

    public View.OnTouchListener createDragToOpenListener(View view) {
        return new ForwardingListener(view) {
            public ListPopupWindow getPopup() {
                return ListPopupWindow.this;
            }
        };
    }

    public void dismiss() {
        this.g.dismiss();
        removePromptView();
        this.g.setContentView((View) null);
        this.c = null;
        this.f.removeCallbacks(this.e);
    }

    @Nullable
    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    @StyleRes
    public int getAnimationStyle() {
        return this.g.getAnimationStyle();
    }

    @Nullable
    public Drawable getBackground() {
        return this.g.getBackground();
    }

    public int getHeight() {
        return this.mDropDownHeight;
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    public int getInputMethodMode() {
        return this.g.getInputMethodMode();
    }

    @Nullable
    public ListView getListView() {
        return this.c;
    }

    public int getPromptPosition() {
        return this.mPromptPosition;
    }

    @Nullable
    public Object getSelectedItem() {
        if (!isShowing()) {
            return null;
        }
        return this.c.getSelectedItem();
    }

    public long getSelectedItemId() {
        if (!isShowing()) {
            return Long.MIN_VALUE;
        }
        return this.c.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        if (!isShowing()) {
            return -1;
        }
        return this.c.getSelectedItemPosition();
    }

    @Nullable
    public View getSelectedView() {
        if (!isShowing()) {
            return null;
        }
        return this.c.getSelectedView();
    }

    public int getSoftInputMode() {
        return this.g.getSoftInputMode();
    }

    public int getVerticalOffset() {
        if (!this.mDropDownVerticalOffsetSet) {
            return 0;
        }
        return this.mDropDownVerticalOffset;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isDropDownAlwaysVisible() {
        return this.mDropDownAlwaysVisible;
    }

    public boolean isInputMethodNotNeeded() {
        return this.g.getInputMethodMode() == 2;
    }

    public boolean isModal() {
        return this.mModal;
    }

    public boolean isShowing() {
        return this.g.isShowing();
    }

    public boolean onKeyDown(int i, @NonNull KeyEvent keyEvent) {
        int i2;
        if (isShowing() && i != 62 && (this.c.getSelectedItemPosition() >= 0 || !isConfirmKey(i))) {
            int selectedItemPosition = this.c.getSelectedItemPosition();
            boolean z = !this.g.isAboveAnchor();
            ListAdapter listAdapter = this.mAdapter;
            int i3 = Integer.MAX_VALUE;
            if (listAdapter != null) {
                boolean areAllItemsEnabled = listAdapter.areAllItemsEnabled();
                int lookForSelectablePosition = areAllItemsEnabled ? 0 : this.c.lookForSelectablePosition(0, true);
                i2 = areAllItemsEnabled ? listAdapter.getCount() - 1 : this.c.lookForSelectablePosition(listAdapter.getCount() - 1, false);
                i3 = lookForSelectablePosition;
            } else {
                i2 = Integer.MIN_VALUE;
            }
            if ((!z || i != 19 || selectedItemPosition > i3) && (z || i != 20 || selectedItemPosition < i2)) {
                this.c.setListSelectionHidden(false);
                if (this.c.onKeyDown(i, keyEvent)) {
                    this.g.setInputMethodMode(2);
                    this.c.requestFocusFromTouch();
                    show();
                    if (!(i == 23 || i == 66)) {
                        switch (i) {
                            case 19:
                            case 20:
                                break;
                        }
                    }
                    return true;
                } else if (!z || i != 20) {
                    return !z && i == 19 && selectedItemPosition == i3;
                } else {
                    if (selectedItemPosition == i2) {
                        return true;
                    }
                }
            } else {
                clearListSelection();
                this.g.setInputMethodMode(1);
                show();
                return true;
            }
        }
    }

    public boolean onKeyPreIme(int i, @NonNull KeyEvent keyEvent) {
        if (i != 4 || !isShowing()) {
            return false;
        }
        View view = this.mDropDownAnchorView;
        if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
            KeyEvent.DispatcherState keyDispatcherState = view.getKeyDispatcherState();
            if (keyDispatcherState != null) {
                keyDispatcherState.startTracking(keyEvent, this);
            }
            return true;
        } else if (keyEvent.getAction() != 1) {
            return false;
        } else {
            KeyEvent.DispatcherState keyDispatcherState2 = view.getKeyDispatcherState();
            if (keyDispatcherState2 != null) {
                keyDispatcherState2.handleUpEvent(keyEvent);
            }
            if (!keyEvent.isTracking() || keyEvent.isCanceled()) {
                return false;
            }
            dismiss();
            return true;
        }
    }

    public boolean onKeyUp(int i, @NonNull KeyEvent keyEvent) {
        if (!isShowing() || this.c.getSelectedItemPosition() < 0) {
            return false;
        }
        boolean onKeyUp = this.c.onKeyUp(i, keyEvent);
        if (onKeyUp && isConfirmKey(i)) {
            dismiss();
        }
        return onKeyUp;
    }

    public boolean performItemClick(int i) {
        if (!isShowing()) {
            return false;
        }
        if (this.mItemClickListener == null) {
            return true;
        }
        DropDownListView dropDownListView = this.c;
        int i2 = i;
        this.mItemClickListener.onItemClick(dropDownListView, dropDownListView.getChildAt(i - dropDownListView.getFirstVisiblePosition()), i2, dropDownListView.getAdapter().getItemId(i));
        return true;
    }

    public void postShow() {
        this.f.post(this.mShowDropDownRunnable);
    }

    public void setAdapter(@Nullable ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.mObserver;
        if (dataSetObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        } else {
            ListAdapter listAdapter2 = this.mAdapter;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.mAdapter = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.mObserver);
        }
        DropDownListView dropDownListView = this.c;
        if (dropDownListView != null) {
            dropDownListView.setAdapter(this.mAdapter);
        }
    }

    public void setAnchorView(@Nullable View view) {
        this.mDropDownAnchorView = view;
    }

    public void setAnimationStyle(@StyleRes int i) {
        this.g.setAnimationStyle(i);
    }

    public void setBackgroundDrawable(@Nullable Drawable drawable) {
        this.g.setBackgroundDrawable(drawable);
    }

    public void setContentWidth(int i) {
        Drawable background = this.g.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            this.mDropDownWidth = this.mTempRect.left + this.mTempRect.right + i;
            return;
        }
        setWidth(i);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setDropDownAlwaysVisible(boolean z) {
        this.mDropDownAlwaysVisible = z;
    }

    public void setDropDownGravity(int i) {
        this.mDropDownGravity = i;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setEpicenterBounds(Rect rect) {
        this.mEpicenterBounds = rect;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setForceIgnoreOutsideTouch(boolean z) {
        this.mForceIgnoreOutsideTouch = z;
    }

    public void setHeight(int i) {
        if (i >= 0 || -2 == i || -1 == i) {
            this.mDropDownHeight = i;
            return;
        }
        throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
    }

    public void setHorizontalOffset(int i) {
        this.mDropDownHorizontalOffset = i;
    }

    public void setInputMethodMode(int i) {
        this.g.setInputMethodMode(i);
    }

    public void setListSelector(Drawable drawable) {
        this.mDropDownListHighlight = drawable;
    }

    public void setModal(boolean z) {
        this.mModal = z;
        this.g.setFocusable(z);
    }

    public void setOnDismissListener(@Nullable PopupWindow.OnDismissListener onDismissListener) {
        this.g.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(@Nullable AdapterView.OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setOnItemSelectedListener(@Nullable AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.mItemSelectedListener = onItemSelectedListener;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setOverlapAnchor(boolean z) {
        this.mOverlapAnchorSet = true;
        this.mOverlapAnchor = z;
    }

    public void setPromptPosition(int i) {
        this.mPromptPosition = i;
    }

    public void setPromptView(@Nullable View view) {
        boolean isShowing = isShowing();
        if (isShowing) {
            removePromptView();
        }
        this.mPromptView = view;
        if (isShowing) {
            show();
        }
    }

    public void setSelection(int i) {
        DropDownListView dropDownListView = this.c;
        if (isShowing() && dropDownListView != null) {
            dropDownListView.setListSelectionHidden(false);
            dropDownListView.setSelection(i);
            if (dropDownListView.getChoiceMode() != 0) {
                dropDownListView.setItemChecked(i, true);
            }
        }
    }

    public void setSoftInputMode(int i) {
        this.g.setSoftInputMode(i);
    }

    public void setVerticalOffset(int i) {
        this.mDropDownVerticalOffset = i;
        this.mDropDownVerticalOffsetSet = true;
    }

    public void setWidth(int i) {
        this.mDropDownWidth = i;
    }

    public void setWindowLayoutType(int i) {
        this.mDropDownWindowLayoutType = i;
    }

    public void show() {
        int buildDropDown = buildDropDown();
        boolean isInputMethodNotNeeded = isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(this.g, this.mDropDownWindowLayoutType);
        boolean z = true;
        if (!this.g.isShowing()) {
            int i = this.mDropDownWidth;
            if (i == -1) {
                i = -1;
            } else if (i == -2) {
                i = getAnchorView().getWidth();
            }
            int i2 = this.mDropDownHeight;
            if (i2 == -1) {
                buildDropDown = -1;
            } else if (i2 != -2) {
                buildDropDown = i2;
            }
            this.g.setWidth(i);
            this.g.setHeight(buildDropDown);
            setPopupClipToScreenEnabled(true);
            this.g.setOutsideTouchable(!this.mForceIgnoreOutsideTouch && !this.mDropDownAlwaysVisible);
            this.g.setTouchInterceptor(this.mTouchInterceptor);
            if (this.mOverlapAnchorSet) {
                PopupWindowCompat.setOverlapAnchor(this.g, this.mOverlapAnchor);
            }
            Method method = sSetEpicenterBoundsMethod;
            if (method != null) {
                try {
                    method.invoke(this.g, new Object[]{this.mEpicenterBounds});
                } catch (Exception e2) {
                    Log.e(TAG, "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
            PopupWindowCompat.showAsDropDown(this.g, getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
            this.c.setSelection(-1);
            if (!this.mModal || this.c.isInTouchMode()) {
                clearListSelection();
            }
            if (!this.mModal) {
                this.f.post(this.mHideSelector);
            }
        } else if (ViewCompat.isAttachedToWindow(getAnchorView())) {
            int i3 = this.mDropDownWidth;
            if (i3 == -1) {
                i3 = -1;
            } else if (i3 == -2) {
                i3 = getAnchorView().getWidth();
            }
            int i4 = this.mDropDownHeight;
            if (i4 == -1) {
                if (!isInputMethodNotNeeded) {
                    buildDropDown = -1;
                }
                if (isInputMethodNotNeeded) {
                    this.g.setWidth(this.mDropDownWidth == -1 ? -1 : 0);
                    this.g.setHeight(0);
                } else {
                    this.g.setWidth(this.mDropDownWidth == -1 ? -1 : 0);
                    this.g.setHeight(-1);
                }
            } else if (i4 != -2) {
                buildDropDown = i4;
            }
            PopupWindow popupWindow = this.g;
            if (this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) {
                z = false;
            }
            popupWindow.setOutsideTouchable(z);
            this.g.update(getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, i3 < 0 ? -1 : i3, buildDropDown < 0 ? -1 : buildDropDown);
        }
    }
}
