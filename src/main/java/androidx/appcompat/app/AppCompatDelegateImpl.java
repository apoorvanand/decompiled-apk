package androidx.appcompat.app;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.R;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.SupportActionModeWrapper;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.WindowCallbackWrapper;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.DecorContentParent;
import androidx.appcompat.widget.FitWindowsViewGroup;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.appcompat.widget.ViewUtils;
import androidx.core.app.NavUtils;
import androidx.core.view.KeyEventDispatcher;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.WindowInsetsCompat;
import java.lang.Thread;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

class AppCompatDelegateImpl extends AppCompatDelegate implements LayoutInflater.Factory2, MenuBuilder.Callback {
    private static final boolean DEBUG = false;
    private static final boolean IS_PRE_LOLLIPOP = (Build.VERSION.SDK_INT < 21);
    private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
    private static boolean sInstalledExceptionHandler = true;
    private static final int[] sWindowBackgroundStyleable = {16842836};
    final Context a;
    final Window b;
    final Window.Callback c;
    final Window.Callback d;
    final AppCompatCallback e;
    ActionBar f;
    MenuInflater g;
    ActionMode h;
    ActionBarContextView i;
    PopupWindow j;
    Runnable k;
    ViewPropertyAnimatorCompat l = null;
    boolean m;
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    private AppCompatViewInflater mAppCompatViewInflater;
    private boolean mApplyDayNightCalled;
    private AutoNightModeManager mAutoNightModeManager;
    private boolean mClosingActionMenu;
    private DecorContentParent mDecorContentParent;
    private boolean mEnableDefaultActionBarUp;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    private boolean mHandleNativeActionModes = true;
    private final Runnable mInvalidatePanelMenuRunnable = new Runnable() {
        public void run() {
            if ((AppCompatDelegateImpl.this.t & 1) != 0) {
                AppCompatDelegateImpl.this.d(0);
            }
            if ((AppCompatDelegateImpl.this.t & 4096) != 0) {
                AppCompatDelegateImpl.this.d(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.s = false;
            appCompatDelegateImpl.t = 0;
        }
    };
    private int mLocalNightMode = -100;
    private boolean mLongPressBackDown;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    private PanelFeatureState mPreparedPanel;
    private View mStatusGuard;
    private ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private CharSequence mTitle;
    private TextView mTitleView;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    boolean r;
    boolean s;
    int t;

    private class ActionBarDrawableToggleImpl implements ActionBarDrawerToggle.Delegate {
        ActionBarDrawableToggleImpl() {
        }

        public Context getActionBarThemedContext() {
            return AppCompatDelegateImpl.this.c();
        }

        public Drawable getThemeUpIndicator() {
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(getActionBarThemedContext(), (AttributeSet) null, new int[]{R.attr.homeAsUpIndicator});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return drawable;
        }

        public boolean isNavigationVisible() {
            ActionBar supportActionBar = AppCompatDelegateImpl.this.getSupportActionBar();
            return (supportActionBar == null || (supportActionBar.getDisplayOptions() & 4) == 0) ? false : true;
        }

        public void setActionBarDescription(int i) {
            ActionBar supportActionBar = AppCompatDelegateImpl.this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeActionContentDescription(i);
            }
        }

        public void setActionBarUpIndicator(Drawable drawable, int i) {
            ActionBar supportActionBar = AppCompatDelegateImpl.this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeAsUpIndicator(drawable);
                supportActionBar.setHomeActionContentDescription(i);
            }
        }
    }

    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        ActionMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            AppCompatDelegateImpl.this.a(menuBuilder);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback b = AppCompatDelegateImpl.this.b();
            if (b == null) {
                return true;
            }
            b.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
        private ActionMode.Callback mWrapped;

        public ActionModeCallbackWrapperV9(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            if (AppCompatDelegateImpl.this.j != null) {
                AppCompatDelegateImpl.this.b.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.k);
            }
            if (AppCompatDelegateImpl.this.i != null) {
                AppCompatDelegateImpl.this.f();
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                appCompatDelegateImpl.l = ViewCompat.animate(appCompatDelegateImpl.i).alpha(0.0f);
                AppCompatDelegateImpl.this.l.setListener(new ViewPropertyAnimatorListenerAdapter() {
                    public void onAnimationEnd(View view) {
                        AppCompatDelegateImpl.this.i.setVisibility(8);
                        if (AppCompatDelegateImpl.this.j != null) {
                            AppCompatDelegateImpl.this.j.dismiss();
                        } else if (AppCompatDelegateImpl.this.i.getParent() instanceof View) {
                            ViewCompat.requestApplyInsets((View) AppCompatDelegateImpl.this.i.getParent());
                        }
                        AppCompatDelegateImpl.this.i.removeAllViews();
                        AppCompatDelegateImpl.this.l.setListener((ViewPropertyAnimatorListener) null);
                        AppCompatDelegateImpl.this.l = null;
                    }
                });
            }
            if (AppCompatDelegateImpl.this.e != null) {
                AppCompatDelegateImpl.this.e.onSupportActionModeFinished(AppCompatDelegateImpl.this.h);
            }
            AppCompatDelegateImpl.this.h = null;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }
    }

    class AppCompatWindowCallback extends WindowCallbackWrapper {
        AppCompatWindowCallback(Window.Callback callback) {
            super(callback);
        }

        /* access modifiers changed from: package-private */
        public final android.view.ActionMode a(ActionMode.Callback callback) {
            SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImpl.this.a, callback);
            androidx.appcompat.view.ActionMode startSupportActionMode = AppCompatDelegateImpl.this.startSupportActionMode(callbackWrapper);
            if (startSupportActionMode != null) {
                return callbackWrapper.getActionModeWrapper(startSupportActionMode);
            }
            return null;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.a(keyEvent.getKeyCode(), keyEvent);
        }

        public void onContentChanged() {
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof MenuBuilder)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.b(i);
            return true;
        }

        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.a(i);
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
            if (i == 0 && menuBuilder == null) {
                return false;
            }
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(true);
            }
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(false);
            }
            return onPreparePanel;
        }

        @RequiresApi(24)
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            PanelFeatureState a2 = AppCompatDelegateImpl.this.a(0, true);
            if (!(a2 == null || a2.j == null)) {
                menu = a2.j;
            }
            super.onProvideKeyboardShortcuts(list, menu, i);
        }

        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled() ? a(callback) : super.onWindowStartingActionMode(callback);
        }

        @RequiresApi(23)
        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            return (!AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled() || i != 0) ? super.onWindowStartingActionMode(callback, i) : a(callback);
        }
    }

    @VisibleForTesting
    final class AutoNightModeManager {
        private BroadcastReceiver mAutoTimeChangeReceiver;
        private IntentFilter mAutoTimeChangeReceiverFilter;
        private boolean mIsNight;
        private TwilightManager mTwilightManager;

        AutoNightModeManager(TwilightManager twilightManager) {
            this.mTwilightManager = twilightManager;
            this.mIsNight = twilightManager.a();
        }

        /* access modifiers changed from: package-private */
        public int a() {
            this.mIsNight = this.mTwilightManager.a();
            return this.mIsNight ? 2 : 1;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            boolean a2 = this.mTwilightManager.a();
            if (a2 != this.mIsNight) {
                this.mIsNight = a2;
                AppCompatDelegateImpl.this.applyDayNight();
            }
        }

        /* access modifiers changed from: package-private */
        public void c() {
            d();
            if (this.mAutoTimeChangeReceiver == null) {
                this.mAutoTimeChangeReceiver = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        AutoNightModeManager.this.b();
                    }
                };
            }
            if (this.mAutoTimeChangeReceiverFilter == null) {
                this.mAutoTimeChangeReceiverFilter = new IntentFilter();
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_SET");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
            }
            AppCompatDelegateImpl.this.a.registerReceiver(this.mAutoTimeChangeReceiver, this.mAutoTimeChangeReceiverFilter);
        }

        /* access modifiers changed from: package-private */
        public void d() {
            if (this.mAutoTimeChangeReceiver != null) {
                AppCompatDelegateImpl.this.a.unregisterReceiver(this.mAutoTimeChangeReceiver);
                this.mAutoTimeChangeReceiver = null;
            }
        }
    }

    private class ListMenuDecorView extends ContentFrameLayout {
        public ListMenuDecorView(Context context) {
            super(context);
        }

        private boolean isOutOfBounds(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !isOutOfBounds((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.c(0);
            return true;
        }

        public void setBackgroundResource(int i) {
            setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), i));
        }
    }

    protected static final class PanelFeatureState {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        ViewGroup g;
        View h;
        View i;
        MenuBuilder j;
        ListMenuPresenter k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        boolean p = false;
        boolean q;
        public boolean qwertyMode;
        Bundle r;

        private static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.a(parcel, (ClassLoader) null);
                }

                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.a(parcel, classLoader);
                }

                public SavedState[] newArray(int i) {
                    return new SavedState[i];
                }
            };
            int a;
            boolean b;
            Bundle c;

            SavedState() {
            }

            static SavedState a(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.a = parcel.readInt();
                boolean z = true;
                if (parcel.readInt() != 1) {
                    z = false;
                }
                savedState.b = z;
                if (savedState.b) {
                    savedState.c = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.a);
                parcel.writeInt(this.b ? 1 : 0);
                if (this.b) {
                    parcel.writeBundle(this.c);
                }
            }
        }

        PanelFeatureState(int i2) {
            this.a = i2;
        }

        /* access modifiers changed from: package-private */
        public MenuView a(MenuPresenter.Callback callback) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                this.k = new ListMenuPresenter(this.l, R.layout.abc_list_menu_item_layout);
                this.k.setCallback(callback);
                this.j.addMenuPresenter(this.k);
            }
            return this.k.getMenuView(this.g);
        }

        /* access modifiers changed from: package-private */
        public void a(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            }
            newTheme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
            newTheme.applyStyle(typedValue.resourceId != 0 ? typedValue.resourceId : R.style.Theme_AppCompat_CompactMenu, true);
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 0);
            contextThemeWrapper.getTheme().setTo(newTheme);
            this.l = contextThemeWrapper;
            TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(R.styleable.AppCompatTheme);
            this.b = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_panelBackground, 0);
            this.f = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }

        /* access modifiers changed from: package-private */
        public void a(MenuBuilder menuBuilder) {
            ListMenuPresenter listMenuPresenter;
            MenuBuilder menuBuilder2 = this.j;
            if (menuBuilder != menuBuilder2) {
                if (menuBuilder2 != null) {
                    menuBuilder2.removeMenuPresenter(this.k);
                }
                this.j = menuBuilder;
                if (menuBuilder != null && (listMenuPresenter = this.k) != null) {
                    menuBuilder.addMenuPresenter(listMenuPresenter);
                }
            }
        }

        public void clearMenuPresenters() {
            MenuBuilder menuBuilder = this.j;
            if (menuBuilder != null) {
                menuBuilder.removeMenuPresenter(this.k);
            }
            this.k = null;
        }

        public boolean hasPanelItems() {
            if (this.h == null) {
                return false;
            }
            return this.i != null || this.k.getAdapter().getCount() > 0;
        }
    }

    private final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
        PanelMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            MenuBuilder rootMenu = menuBuilder.getRootMenu();
            boolean z2 = rootMenu != menuBuilder;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                menuBuilder = rootMenu;
            }
            PanelFeatureState a2 = appCompatDelegateImpl.a((Menu) menuBuilder);
            if (a2 == null) {
                return;
            }
            if (z2) {
                AppCompatDelegateImpl.this.a(a2.a, a2, rootMenu);
                AppCompatDelegateImpl.this.a(a2, true);
                return;
            }
            AppCompatDelegateImpl.this.a(a2, z);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback b;
            if (menuBuilder != null || !AppCompatDelegateImpl.this.m || (b = AppCompatDelegateImpl.this.b()) == null || AppCompatDelegateImpl.this.r) {
                return true;
            }
            b.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    static {
        if (IS_PRE_LOLLIPOP && !sInstalledExceptionHandler) {
            final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                private boolean shouldWrapException(Throwable th) {
                    String message;
                    if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                        return false;
                    }
                    return message.contains("drawable") || message.contains("Drawable");
                }

                public void uncaughtException(Thread thread, Throwable th) {
                    if (shouldWrapException(th)) {
                        Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                        notFoundException.initCause(th.getCause());
                        notFoundException.setStackTrace(th.getStackTrace());
                        defaultUncaughtExceptionHandler.uncaughtException(thread, notFoundException);
                        return;
                    }
                    defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                }
            });
        }
    }

    AppCompatDelegateImpl(Context context, Window window, AppCompatCallback appCompatCallback) {
        this.a = context;
        this.b = window;
        this.e = appCompatCallback;
        this.c = this.b.getCallback();
        Window.Callback callback = this.c;
        if (!(callback instanceof AppCompatWindowCallback)) {
            this.d = new AppCompatWindowCallback(callback);
            this.b.setCallback(this.d);
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, (AttributeSet) null, sWindowBackgroundStyleable);
            Drawable drawableIfKnown = obtainStyledAttributes.getDrawableIfKnown(0);
            if (drawableIfKnown != null) {
                this.b.setBackgroundDrawable(drawableIfKnown);
            }
            obtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    private void applyFixedSizeWindow() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.mSubDecor.findViewById(16908290);
        View decorView = this.b.getDecorView();
        contentFrameLayout.setDecorPadding(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(R.styleable.AppCompatTheme);
        obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    private ViewGroup createSubDecor() {
        ViewGroup viewGroup;
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(R.styleable.AppCompatTheme);
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowActionBar)) {
            if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowNoTitle, false)) {
                requestWindowFeature(1);
            } else if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionBar, false)) {
                requestWindowFeature(108);
            }
            if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
                requestWindowFeature(109);
            }
            if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
                requestWindowFeature(10);
            }
            this.p = obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_android_windowIsFloating, false);
            obtainStyledAttributes.recycle();
            this.b.getDecorView();
            LayoutInflater from = LayoutInflater.from(this.a);
            if (this.q) {
                viewGroup = (ViewGroup) from.inflate(this.o ? R.layout.abc_screen_simple_overlay_action_mode : R.layout.abc_screen_simple, (ViewGroup) null);
                if (Build.VERSION.SDK_INT >= 21) {
                    ViewCompat.setOnApplyWindowInsetsListener(viewGroup, new OnApplyWindowInsetsListener() {
                        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                            int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
                            int e = AppCompatDelegateImpl.this.e(systemWindowInsetTop);
                            if (systemWindowInsetTop != e) {
                                windowInsetsCompat = windowInsetsCompat.replaceSystemWindowInsets(windowInsetsCompat.getSystemWindowInsetLeft(), e, windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
                            }
                            return ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
                        }
                    });
                } else {
                    ((FitWindowsViewGroup) viewGroup).setOnFitSystemWindowsListener(new FitWindowsViewGroup.OnFitSystemWindowsListener() {
                        public void onFitSystemWindows(Rect rect) {
                            rect.top = AppCompatDelegateImpl.this.e(rect.top);
                        }
                    });
                }
            } else if (this.p) {
                viewGroup = (ViewGroup) from.inflate(R.layout.abc_dialog_title_material, (ViewGroup) null);
                this.n = false;
                this.m = false;
            } else if (this.m) {
                TypedValue typedValue = new TypedValue();
                this.a.getTheme().resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new ContextThemeWrapper(this.a, typedValue.resourceId) : this.a).inflate(R.layout.abc_screen_toolbar, (ViewGroup) null);
                this.mDecorContentParent = (DecorContentParent) viewGroup.findViewById(R.id.decor_content_parent);
                this.mDecorContentParent.setWindowCallback(b());
                if (this.n) {
                    this.mDecorContentParent.initFeature(109);
                }
                if (this.mFeatureProgress) {
                    this.mDecorContentParent.initFeature(2);
                }
                if (this.mFeatureIndeterminateProgress) {
                    this.mDecorContentParent.initFeature(5);
                }
            } else {
                viewGroup = null;
            }
            if (viewGroup != null) {
                if (this.mDecorContentParent == null) {
                    this.mTitleView = (TextView) viewGroup.findViewById(R.id.title);
                }
                ViewUtils.makeOptionalFitsSystemWindows(viewGroup);
                ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R.id.action_bar_activity_content);
                ViewGroup viewGroup2 = (ViewGroup) this.b.findViewById(16908290);
                if (viewGroup2 != null) {
                    while (viewGroup2.getChildCount() > 0) {
                        View childAt = viewGroup2.getChildAt(0);
                        viewGroup2.removeViewAt(0);
                        contentFrameLayout.addView(childAt);
                    }
                    viewGroup2.setId(-1);
                    contentFrameLayout.setId(16908290);
                    if (viewGroup2 instanceof FrameLayout) {
                        ((FrameLayout) viewGroup2).setForeground((Drawable) null);
                    }
                }
                this.b.setContentView(viewGroup);
                contentFrameLayout.setAttachListener(new ContentFrameLayout.OnAttachListener() {
                    public void onAttachedFromWindow() {
                    }

                    public void onDetachedFromWindow() {
                        AppCompatDelegateImpl.this.h();
                    }
                });
                return viewGroup;
            }
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.m + ", windowActionBarOverlay: " + this.n + ", android:windowIsFloating: " + this.p + ", windowActionModeOverlay: " + this.o + ", windowNoTitle: " + this.q + " }");
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    private void ensureAutoNightModeManager() {
        if (this.mAutoNightModeManager == null) {
            this.mAutoNightModeManager = new AutoNightModeManager(TwilightManager.a(this.a));
        }
    }

    private void ensureSubDecor() {
        if (!this.mSubDecorInstalled) {
            this.mSubDecor = createSubDecor();
            CharSequence d2 = d();
            if (!TextUtils.isEmpty(d2)) {
                DecorContentParent decorContentParent = this.mDecorContentParent;
                if (decorContentParent != null) {
                    decorContentParent.setWindowTitle(d2);
                } else if (a() != null) {
                    a().setWindowTitle(d2);
                } else {
                    TextView textView = this.mTitleView;
                    if (textView != null) {
                        textView.setText(d2);
                    }
                }
            }
            applyFixedSizeWindow();
            a(this.mSubDecor);
            this.mSubDecorInstalled = true;
            PanelFeatureState a2 = a(0, false);
            if (this.r) {
                return;
            }
            if (a2 == null || a2.j == null) {
                invalidatePanelMenu(108);
            }
        }
    }

    private int getNightMode() {
        int i2 = this.mLocalNightMode;
        return i2 != -100 ? i2 : getDefaultNightMode();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initWindowDecorActionBar() {
        /*
            r3 = this;
            r3.ensureSubDecor()
            boolean r0 = r3.m
            if (r0 == 0) goto L_0x0033
            androidx.appcompat.app.ActionBar r0 = r3.f
            if (r0 == 0) goto L_0x000c
            goto L_0x0033
        L_0x000c:
            android.view.Window$Callback r0 = r3.c
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L_0x001e
            androidx.appcompat.app.WindowDecorActionBar r1 = new androidx.appcompat.app.WindowDecorActionBar
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r2 = r3.n
            r1.<init>(r0, r2)
        L_0x001b:
            r3.f = r1
            goto L_0x002a
        L_0x001e:
            boolean r1 = r0 instanceof android.app.Dialog
            if (r1 == 0) goto L_0x002a
            androidx.appcompat.app.WindowDecorActionBar r1 = new androidx.appcompat.app.WindowDecorActionBar
            android.app.Dialog r0 = (android.app.Dialog) r0
            r1.<init>((android.app.Dialog) r0)
            goto L_0x001b
        L_0x002a:
            androidx.appcompat.app.ActionBar r0 = r3.f
            if (r0 == 0) goto L_0x0033
            boolean r1 = r3.mEnableDefaultActionBarUp
            r0.setDefaultDisplayHomeAsUpEnabled(r1)
        L_0x0033:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.initWindowDecorActionBar():void");
    }

    private boolean initializePanelContent(PanelFeatureState panelFeatureState) {
        if (panelFeatureState.i != null) {
            panelFeatureState.h = panelFeatureState.i;
            return true;
        } else if (panelFeatureState.j == null) {
            return false;
        } else {
            if (this.mPanelMenuPresenterCallback == null) {
                this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback();
            }
            panelFeatureState.h = (View) panelFeatureState.a((MenuPresenter.Callback) this.mPanelMenuPresenterCallback);
            return panelFeatureState.h != null;
        }
    }

    private boolean initializePanelDecor(PanelFeatureState panelFeatureState) {
        panelFeatureState.a(c());
        panelFeatureState.g = new ListMenuDecorView(panelFeatureState.l);
        panelFeatureState.c = 81;
        return true;
    }

    private boolean initializePanelMenu(PanelFeatureState panelFeatureState) {
        Context context = this.a;
        if ((panelFeatureState.a == 0 || panelFeatureState.a == 108) && this.mDecorContentParent != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
            Resources.Theme theme2 = null;
            if (typedValue.resourceId != 0) {
                theme2 = context.getResources().newTheme();
                theme2.setTo(theme);
                theme2.applyStyle(typedValue.resourceId, true);
                theme2.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (theme2 == null) {
                    theme2 = context.getResources().newTheme();
                    theme2.setTo(theme);
                }
                theme2.applyStyle(typedValue.resourceId, true);
            }
            if (theme2 != null) {
                ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 0);
                contextThemeWrapper.getTheme().setTo(theme2);
                context = contextThemeWrapper;
            }
        }
        MenuBuilder menuBuilder = new MenuBuilder(context);
        menuBuilder.setCallback(this);
        panelFeatureState.a(menuBuilder);
        return true;
    }

    private void invalidatePanelMenu(int i2) {
        this.t = (1 << i2) | this.t;
        if (!this.s) {
            ViewCompat.postOnAnimation(this.b.getDecorView(), this.mInvalidatePanelMenuRunnable);
            this.s = true;
        }
    }

    private boolean onKeyDownPanel(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState a2 = a(i2, true);
        if (!a2.o) {
            return preparePanel(a2, keyEvent);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean onKeyUpPanel(int r4, android.view.KeyEvent r5) {
        /*
            r3 = this;
            androidx.appcompat.view.ActionMode r0 = r3.h
            r1 = 0
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            r0 = 1
            androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r2 = r3.a((int) r4, (boolean) r0)
            if (r4 != 0) goto L_0x0043
            androidx.appcompat.widget.DecorContentParent r4 = r3.mDecorContentParent
            if (r4 == 0) goto L_0x0043
            boolean r4 = r4.canShowOverflowMenu()
            if (r4 == 0) goto L_0x0043
            android.content.Context r4 = r3.a
            android.view.ViewConfiguration r4 = android.view.ViewConfiguration.get(r4)
            boolean r4 = r4.hasPermanentMenuKey()
            if (r4 != 0) goto L_0x0043
            androidx.appcompat.widget.DecorContentParent r4 = r3.mDecorContentParent
            boolean r4 = r4.isOverflowMenuShowing()
            if (r4 != 0) goto L_0x003c
            boolean r4 = r3.r
            if (r4 != 0) goto L_0x0063
            boolean r4 = r3.preparePanel(r2, r5)
            if (r4 == 0) goto L_0x0063
            androidx.appcompat.widget.DecorContentParent r4 = r3.mDecorContentParent
            boolean r4 = r4.showOverflowMenu()
            goto L_0x006a
        L_0x003c:
            androidx.appcompat.widget.DecorContentParent r4 = r3.mDecorContentParent
            boolean r4 = r4.hideOverflowMenu()
            goto L_0x006a
        L_0x0043:
            boolean r4 = r2.o
            if (r4 != 0) goto L_0x0065
            boolean r4 = r2.n
            if (r4 == 0) goto L_0x004c
            goto L_0x0065
        L_0x004c:
            boolean r4 = r2.m
            if (r4 == 0) goto L_0x0063
            boolean r4 = r2.q
            if (r4 == 0) goto L_0x005b
            r2.m = r1
            boolean r4 = r3.preparePanel(r2, r5)
            goto L_0x005c
        L_0x005b:
            r4 = 1
        L_0x005c:
            if (r4 == 0) goto L_0x0063
            r3.openPanel(r2, r5)
            r4 = 1
            goto L_0x006a
        L_0x0063:
            r4 = 0
            goto L_0x006a
        L_0x0065:
            boolean r4 = r2.o
            r3.a((androidx.appcompat.app.AppCompatDelegateImpl.PanelFeatureState) r2, (boolean) r0)
        L_0x006a:
            if (r4 == 0) goto L_0x0083
            android.content.Context r5 = r3.a
            java.lang.String r0 = "audio"
            java.lang.Object r5 = r5.getSystemService(r0)
            android.media.AudioManager r5 = (android.media.AudioManager) r5
            if (r5 == 0) goto L_0x007c
            r5.playSoundEffect(r1)
            goto L_0x0083
        L_0x007c:
            java.lang.String r5 = "AppCompatDelegate"
            java.lang.String r0 = "Couldn't get audio manager"
            android.util.Log.w(r5, r0)
        L_0x0083:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.onKeyUpPanel(int, android.view.KeyEvent):boolean");
    }

    private void openPanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (!panelFeatureState.o && !this.r) {
            if (panelFeatureState.a == 0) {
                if ((this.a.getResources().getConfiguration().screenLayout & 15) == 4) {
                    return;
                }
            }
            Window.Callback b2 = b();
            if (b2 == null || b2.onMenuOpened(panelFeatureState.a, panelFeatureState.j)) {
                WindowManager windowManager = (WindowManager) this.a.getSystemService("window");
                if (windowManager != null && preparePanel(panelFeatureState, keyEvent)) {
                    if (panelFeatureState.g == null || panelFeatureState.p) {
                        if (panelFeatureState.g == null) {
                            if (!initializePanelDecor(panelFeatureState) || panelFeatureState.g == null) {
                                return;
                            }
                        } else if (panelFeatureState.p && panelFeatureState.g.getChildCount() > 0) {
                            panelFeatureState.g.removeAllViews();
                        }
                        if (initializePanelContent(panelFeatureState) && panelFeatureState.hasPanelItems()) {
                            ViewGroup.LayoutParams layoutParams2 = panelFeatureState.h.getLayoutParams();
                            if (layoutParams2 == null) {
                                layoutParams2 = new ViewGroup.LayoutParams(-2, -2);
                            }
                            panelFeatureState.g.setBackgroundResource(panelFeatureState.b);
                            ViewParent parent = panelFeatureState.h.getParent();
                            if (parent != null && (parent instanceof ViewGroup)) {
                                ((ViewGroup) parent).removeView(panelFeatureState.h);
                            }
                            panelFeatureState.g.addView(panelFeatureState.h, layoutParams2);
                            if (!panelFeatureState.h.hasFocus()) {
                                panelFeatureState.h.requestFocus();
                            }
                        } else {
                            return;
                        }
                    } else if (!(panelFeatureState.i == null || (layoutParams = panelFeatureState.i.getLayoutParams()) == null || layoutParams.width != -1)) {
                        i2 = -1;
                        panelFeatureState.n = false;
                        WindowManager.LayoutParams layoutParams3 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.d, panelFeatureState.e, 1002, 8519680, -3);
                        layoutParams3.gravity = panelFeatureState.c;
                        layoutParams3.windowAnimations = panelFeatureState.f;
                        windowManager.addView(panelFeatureState.g, layoutParams3);
                        panelFeatureState.o = true;
                        return;
                    }
                    i2 = -2;
                    panelFeatureState.n = false;
                    WindowManager.LayoutParams layoutParams32 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.d, panelFeatureState.e, 1002, 8519680, -3);
                    layoutParams32.gravity = panelFeatureState.c;
                    layoutParams32.windowAnimations = panelFeatureState.f;
                    windowManager.addView(panelFeatureState.g, layoutParams32);
                    panelFeatureState.o = true;
                    return;
                }
                return;
            }
            a(panelFeatureState, true);
        }
    }

    private boolean performPanelShortcut(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent, int i3) {
        boolean z = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.m || preparePanel(panelFeatureState, keyEvent)) && panelFeatureState.j != null) {
            z = panelFeatureState.j.performShortcut(i2, keyEvent, i3);
        }
        if (z && (i3 & 1) == 0 && this.mDecorContentParent == null) {
            a(panelFeatureState, true);
        }
        return z;
    }

    private boolean preparePanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        DecorContentParent decorContentParent;
        DecorContentParent decorContentParent2;
        DecorContentParent decorContentParent3;
        if (this.r) {
            return false;
        }
        if (panelFeatureState.m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.mPreparedPanel;
        if (!(panelFeatureState2 == null || panelFeatureState2 == panelFeatureState)) {
            a(panelFeatureState2, false);
        }
        Window.Callback b2 = b();
        if (b2 != null) {
            panelFeatureState.i = b2.onCreatePanelView(panelFeatureState.a);
        }
        boolean z = panelFeatureState.a == 0 || panelFeatureState.a == 108;
        if (z && (decorContentParent3 = this.mDecorContentParent) != null) {
            decorContentParent3.setMenuPrepared();
        }
        if (panelFeatureState.i == null && (!z || !(a() instanceof ToolbarActionBar))) {
            if (panelFeatureState.j == null || panelFeatureState.q) {
                if (panelFeatureState.j == null && (!initializePanelMenu(panelFeatureState) || panelFeatureState.j == null)) {
                    return false;
                }
                if (z && this.mDecorContentParent != null) {
                    if (this.mActionMenuPresenterCallback == null) {
                        this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
                    }
                    this.mDecorContentParent.setMenu(panelFeatureState.j, this.mActionMenuPresenterCallback);
                }
                panelFeatureState.j.stopDispatchingItemsChanged();
                if (!b2.onCreatePanelMenu(panelFeatureState.a, panelFeatureState.j)) {
                    panelFeatureState.a((MenuBuilder) null);
                    if (z && (decorContentParent2 = this.mDecorContentParent) != null) {
                        decorContentParent2.setMenu((Menu) null, this.mActionMenuPresenterCallback);
                    }
                    return false;
                }
                panelFeatureState.q = false;
            }
            panelFeatureState.j.stopDispatchingItemsChanged();
            if (panelFeatureState.r != null) {
                panelFeatureState.j.restoreActionViewStates(panelFeatureState.r);
                panelFeatureState.r = null;
            }
            if (!b2.onPreparePanel(0, panelFeatureState.i, panelFeatureState.j)) {
                if (z && (decorContentParent = this.mDecorContentParent) != null) {
                    decorContentParent.setMenu((Menu) null, this.mActionMenuPresenterCallback);
                }
                panelFeatureState.j.startDispatchingItemsChanged();
                return false;
            }
            panelFeatureState.qwertyMode = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.j.setQwertyMode(panelFeatureState.qwertyMode);
            panelFeatureState.j.startDispatchingItemsChanged();
        }
        panelFeatureState.m = true;
        panelFeatureState.n = false;
        this.mPreparedPanel = panelFeatureState;
        return true;
    }

    private void reopenMenu(MenuBuilder menuBuilder, boolean z) {
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent == null || !decorContentParent.canShowOverflowMenu() || (ViewConfiguration.get(this.a).hasPermanentMenuKey() && !this.mDecorContentParent.isOverflowMenuShowPending())) {
            PanelFeatureState a2 = a(0, true);
            a2.p = true;
            a(a2, false);
            openPanel(a2, (KeyEvent) null);
            return;
        }
        Window.Callback b2 = b();
        if (this.mDecorContentParent.isOverflowMenuShowing() && z) {
            this.mDecorContentParent.hideOverflowMenu();
            if (!this.r) {
                b2.onPanelClosed(108, a(0, true).j);
            }
        } else if (b2 != null && !this.r) {
            if (this.s && (this.t & 1) != 0) {
                this.b.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
                this.mInvalidatePanelMenuRunnable.run();
            }
            PanelFeatureState a3 = a(0, true);
            if (a3.j != null && !a3.q && b2.onPreparePanel(0, a3.i, a3.j)) {
                b2.onMenuOpened(108, a3.j);
                this.mDecorContentParent.showOverflowMenu();
            }
        }
    }

    private int sanitizeWindowFeatureId(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        } else if (i2 != 9) {
            return i2;
        } else {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        }
    }

    private boolean shouldInheritContext(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.b.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || ViewCompat.isAttachedToWindow((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private boolean shouldRecreateOnNightModeChange() {
        if (this.mApplyDayNightCalled) {
            Context context = this.a;
            if (context instanceof Activity) {
                try {
                    return (context.getPackageManager().getActivityInfo(new ComponentName(this.a, this.a.getClass()), 0).configChanges & 512) == 0;
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                    return true;
                }
            }
        }
        return false;
    }

    private void throwFeatureRequestIfSubDecorInstalled() {
        if (this.mSubDecorInstalled) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private boolean updateForNightMode(int i2) {
        Resources resources = this.a.getResources();
        Configuration configuration = resources.getConfiguration();
        int i3 = configuration.uiMode & 48;
        int i4 = i2 == 2 ? 32 : 16;
        if (i3 == i4) {
            return false;
        }
        if (shouldRecreateOnNightModeChange()) {
            ((Activity) this.a).recreate();
            return true;
        }
        Configuration configuration2 = new Configuration(configuration);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        configuration2.uiMode = i4 | (configuration2.uiMode & -49);
        resources.updateConfiguration(configuration2, displayMetrics);
        if (Build.VERSION.SDK_INT >= 26) {
            return true;
        }
        ResourcesFlusher.a(resources);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final ActionBar a() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public PanelFeatureState a(int i2, boolean z) {
        PanelFeatureState[] panelFeatureStateArr = this.mPanels;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i2) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[(i2 + 1)];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.mPanels = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i2);
        panelFeatureStateArr[i2] = panelFeatureState2;
        return panelFeatureState2;
    }

    /* access modifiers changed from: package-private */
    public PanelFeatureState a(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.mPanels;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
            if (panelFeatureState != null && panelFeatureState.j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.appcompat.view.ActionMode a(@androidx.annotation.NonNull androidx.appcompat.view.ActionMode.Callback r8) {
        /*
            r7 = this;
            r7.f()
            androidx.appcompat.view.ActionMode r0 = r7.h
            if (r0 == 0) goto L_0x000a
            r0.finish()
        L_0x000a:
            boolean r0 = r8 instanceof androidx.appcompat.app.AppCompatDelegateImpl.ActionModeCallbackWrapperV9
            if (r0 != 0) goto L_0x0014
            androidx.appcompat.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9 r0 = new androidx.appcompat.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9
            r0.<init>(r8)
            r8 = r0
        L_0x0014:
            androidx.appcompat.app.AppCompatCallback r0 = r7.e
            r1 = 0
            if (r0 == 0) goto L_0x0022
            boolean r2 = r7.r
            if (r2 != 0) goto L_0x0022
            androidx.appcompat.view.ActionMode r0 = r0.onWindowStartingSupportActionMode(r8)     // Catch:{ AbstractMethodError -> 0x0022 }
            goto L_0x0023
        L_0x0022:
            r0 = r1
        L_0x0023:
            if (r0 == 0) goto L_0x0029
            r7.h = r0
            goto L_0x0166
        L_0x0029:
            androidx.appcompat.widget.ActionBarContextView r0 = r7.i
            r2 = 0
            r3 = 1
            if (r0 != 0) goto L_0x00d6
            boolean r0 = r7.p
            if (r0 == 0) goto L_0x00b7
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            android.content.Context r4 = r7.a
            android.content.res.Resources$Theme r4 = r4.getTheme()
            int r5 = androidx.appcompat.R.attr.actionBarTheme
            r4.resolveAttribute(r5, r0, r3)
            int r5 = r0.resourceId
            if (r5 == 0) goto L_0x0068
            android.content.Context r5 = r7.a
            android.content.res.Resources r5 = r5.getResources()
            android.content.res.Resources$Theme r5 = r5.newTheme()
            r5.setTo(r4)
            int r4 = r0.resourceId
            r5.applyStyle(r4, r3)
            androidx.appcompat.view.ContextThemeWrapper r4 = new androidx.appcompat.view.ContextThemeWrapper
            android.content.Context r6 = r7.a
            r4.<init>((android.content.Context) r6, (int) r2)
            android.content.res.Resources$Theme r6 = r4.getTheme()
            r6.setTo(r5)
            goto L_0x006a
        L_0x0068:
            android.content.Context r4 = r7.a
        L_0x006a:
            androidx.appcompat.widget.ActionBarContextView r5 = new androidx.appcompat.widget.ActionBarContextView
            r5.<init>(r4)
            r7.i = r5
            android.widget.PopupWindow r5 = new android.widget.PopupWindow
            int r6 = androidx.appcompat.R.attr.actionModePopupWindowStyle
            r5.<init>(r4, r1, r6)
            r7.j = r5
            android.widget.PopupWindow r5 = r7.j
            r6 = 2
            androidx.core.widget.PopupWindowCompat.setWindowLayoutType(r5, r6)
            android.widget.PopupWindow r5 = r7.j
            androidx.appcompat.widget.ActionBarContextView r6 = r7.i
            r5.setContentView(r6)
            android.widget.PopupWindow r5 = r7.j
            r6 = -1
            r5.setWidth(r6)
            android.content.res.Resources$Theme r5 = r4.getTheme()
            int r6 = androidx.appcompat.R.attr.actionBarSize
            r5.resolveAttribute(r6, r0, r3)
            int r0 = r0.data
            android.content.res.Resources r4 = r4.getResources()
            android.util.DisplayMetrics r4 = r4.getDisplayMetrics()
            int r0 = android.util.TypedValue.complexToDimensionPixelSize(r0, r4)
            androidx.appcompat.widget.ActionBarContextView r4 = r7.i
            r4.setContentHeight(r0)
            android.widget.PopupWindow r0 = r7.j
            r4 = -2
            r0.setHeight(r4)
            androidx.appcompat.app.AppCompatDelegateImpl$6 r0 = new androidx.appcompat.app.AppCompatDelegateImpl$6
            r0.<init>()
            r7.k = r0
            goto L_0x00d6
        L_0x00b7:
            android.view.ViewGroup r0 = r7.mSubDecor
            int r4 = androidx.appcompat.R.id.action_mode_bar_stub
            android.view.View r0 = r0.findViewById(r4)
            androidx.appcompat.widget.ViewStubCompat r0 = (androidx.appcompat.widget.ViewStubCompat) r0
            if (r0 == 0) goto L_0x00d6
            android.content.Context r4 = r7.c()
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            r0.setLayoutInflater(r4)
            android.view.View r0 = r0.inflate()
            androidx.appcompat.widget.ActionBarContextView r0 = (androidx.appcompat.widget.ActionBarContextView) r0
            r7.i = r0
        L_0x00d6:
            androidx.appcompat.widget.ActionBarContextView r0 = r7.i
            if (r0 == 0) goto L_0x0166
            r7.f()
            androidx.appcompat.widget.ActionBarContextView r0 = r7.i
            r0.killMode()
            androidx.appcompat.view.StandaloneActionMode r0 = new androidx.appcompat.view.StandaloneActionMode
            androidx.appcompat.widget.ActionBarContextView r4 = r7.i
            android.content.Context r4 = r4.getContext()
            androidx.appcompat.widget.ActionBarContextView r5 = r7.i
            android.widget.PopupWindow r6 = r7.j
            if (r6 != 0) goto L_0x00f1
            goto L_0x00f2
        L_0x00f1:
            r3 = 0
        L_0x00f2:
            r0.<init>(r4, r5, r8, r3)
            android.view.Menu r3 = r0.getMenu()
            boolean r8 = r8.onCreateActionMode(r0, r3)
            if (r8 == 0) goto L_0x0164
            r0.invalidate()
            androidx.appcompat.widget.ActionBarContextView r8 = r7.i
            r8.initForMode(r0)
            r7.h = r0
            boolean r8 = r7.e()
            r0 = 1065353216(0x3f800000, float:1.0)
            if (r8 == 0) goto L_0x012e
            androidx.appcompat.widget.ActionBarContextView r8 = r7.i
            r1 = 0
            r8.setAlpha(r1)
            androidx.appcompat.widget.ActionBarContextView r8 = r7.i
            androidx.core.view.ViewPropertyAnimatorCompat r8 = androidx.core.view.ViewCompat.animate(r8)
            androidx.core.view.ViewPropertyAnimatorCompat r8 = r8.alpha(r0)
            r7.l = r8
            androidx.core.view.ViewPropertyAnimatorCompat r8 = r7.l
            androidx.appcompat.app.AppCompatDelegateImpl$7 r0 = new androidx.appcompat.app.AppCompatDelegateImpl$7
            r0.<init>()
            r8.setListener(r0)
            goto L_0x0154
        L_0x012e:
            androidx.appcompat.widget.ActionBarContextView r8 = r7.i
            r8.setAlpha(r0)
            androidx.appcompat.widget.ActionBarContextView r8 = r7.i
            r8.setVisibility(r2)
            androidx.appcompat.widget.ActionBarContextView r8 = r7.i
            r0 = 32
            r8.sendAccessibilityEvent(r0)
            androidx.appcompat.widget.ActionBarContextView r8 = r7.i
            android.view.ViewParent r8 = r8.getParent()
            boolean r8 = r8 instanceof android.view.View
            if (r8 == 0) goto L_0x0154
            androidx.appcompat.widget.ActionBarContextView r8 = r7.i
            android.view.ViewParent r8 = r8.getParent()
            android.view.View r8 = (android.view.View) r8
            androidx.core.view.ViewCompat.requestApplyInsets(r8)
        L_0x0154:
            android.widget.PopupWindow r8 = r7.j
            if (r8 == 0) goto L_0x0166
            android.view.Window r8 = r7.b
            android.view.View r8 = r8.getDecorView()
            java.lang.Runnable r0 = r7.k
            r8.post(r0)
            goto L_0x0166
        L_0x0164:
            r7.h = r1
        L_0x0166:
            androidx.appcompat.view.ActionMode r8 = r7.h
            if (r8 == 0) goto L_0x0171
            androidx.appcompat.app.AppCompatCallback r0 = r7.e
            if (r0 == 0) goto L_0x0171
            r0.onSupportActionModeStarted(r8)
        L_0x0171:
            androidx.appcompat.view.ActionMode r8 = r7.h
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.a(androidx.appcompat.view.ActionMode$Callback):androidx.appcompat.view.ActionMode");
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        if (i2 == 108) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.dispatchMenuVisibilityChanged(false);
            }
        } else if (i2 == 0) {
            PanelFeatureState a2 = a(i2, true);
            if (a2.o) {
                a(a2, false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i2 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.mPanels;
                if (i2 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i2];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.o) && !this.r) {
            this.c.onPanelClosed(i2, menu);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ViewGroup viewGroup) {
    }

    /* access modifiers changed from: package-private */
    public void a(PanelFeatureState panelFeatureState, boolean z) {
        DecorContentParent decorContentParent;
        if (!z || panelFeatureState.a != 0 || (decorContentParent = this.mDecorContentParent) == null || !decorContentParent.isOverflowMenuShowing()) {
            WindowManager windowManager = (WindowManager) this.a.getSystemService("window");
            if (!(windowManager == null || !panelFeatureState.o || panelFeatureState.g == null)) {
                windowManager.removeView(panelFeatureState.g);
                if (z) {
                    a(panelFeatureState.a, panelFeatureState, (Menu) null);
                }
            }
            panelFeatureState.m = false;
            panelFeatureState.n = false;
            panelFeatureState.o = false;
            panelFeatureState.h = null;
            panelFeatureState.p = true;
            if (this.mPreparedPanel == panelFeatureState) {
                this.mPreparedPanel = null;
                return;
            }
            return;
        }
        a(panelFeatureState.j);
    }

    /* access modifiers changed from: package-private */
    public void a(MenuBuilder menuBuilder) {
        if (!this.mClosingActionMenu) {
            this.mClosingActionMenu = true;
            this.mDecorContentParent.dismissPopups();
            Window.Callback b2 = b();
            if (b2 != null && !this.r) {
                b2.onPanelClosed(108, menuBuilder);
            }
            this.mClosingActionMenu = false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2, KeyEvent keyEvent) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null && supportActionBar.onKeyShortcut(i2, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.mPreparedPanel;
        if (panelFeatureState == null || !performPanelShortcut(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            if (this.mPreparedPanel == null) {
                PanelFeatureState a2 = a(0, true);
                preparePanel(a2, keyEvent);
                boolean performPanelShortcut = performPanelShortcut(a2, keyEvent.getKeyCode(), keyEvent, 1);
                a2.m = false;
                if (performPanelShortcut) {
                    return true;
                }
            }
            return false;
        }
        PanelFeatureState panelFeatureState2 = this.mPreparedPanel;
        if (panelFeatureState2 != null) {
            panelFeatureState2.n = true;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean a(KeyEvent keyEvent) {
        View decorView;
        Window.Callback callback = this.c;
        boolean z = true;
        if (((callback instanceof KeyEventDispatcher.Component) || (callback instanceof AppCompatDialog)) && (decorView = this.b.getDecorView()) != null && KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.c.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) {
            z = false;
        }
        return z ? c(keyCode, keyEvent) : b(keyCode, keyEvent);
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ensureSubDecor();
        ((ViewGroup) this.mSubDecor.findViewById(16908290)).addView(view, layoutParams);
        this.c.onContentChanged();
    }

    public boolean applyDayNight() {
        int nightMode = getNightMode();
        int f2 = f(nightMode);
        boolean updateForNightMode = f2 != -1 ? updateForNightMode(f2) : false;
        if (nightMode == 0) {
            ensureAutoNightModeManager();
            this.mAutoNightModeManager.c();
        }
        this.mApplyDayNightCalled = true;
        return updateForNightMode;
    }

    /* access modifiers changed from: package-private */
    public final Window.Callback b() {
        return this.b.getCallback();
    }

    /* access modifiers changed from: package-private */
    public void b(int i2) {
        ActionBar supportActionBar;
        if (i2 == 108 && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.dispatchMenuVisibilityChanged(true);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z = this.mLongPressBackDown;
            this.mLongPressBackDown = false;
            PanelFeatureState a2 = a(0, false);
            if (a2 != null && a2.o) {
                if (!z) {
                    a(a2, true);
                }
                return true;
            } else if (g()) {
                return true;
            }
        } else if (i2 == 82) {
            onKeyUpPanel(0, keyEvent);
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final Context c() {
        ActionBar supportActionBar = getSupportActionBar();
        Context themedContext = supportActionBar != null ? supportActionBar.getThemedContext() : null;
        return themedContext == null ? this.a : themedContext;
    }

    /* access modifiers changed from: package-private */
    public void c(int i2) {
        a(a(i2, true), true);
    }

    /* access modifiers changed from: package-private */
    public boolean c(int i2, KeyEvent keyEvent) {
        boolean z = true;
        if (i2 == 4) {
            if ((keyEvent.getFlags() & 128) == 0) {
                z = false;
            }
            this.mLongPressBackDown = z;
        } else if (i2 == 82) {
            onKeyDownPanel(0, keyEvent);
            return true;
        }
        return false;
    }

    public View createView(View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        boolean z;
        AppCompatViewInflater appCompatViewInflater;
        boolean z2 = false;
        if (this.mAppCompatViewInflater == null) {
            String string = this.a.obtainStyledAttributes(R.styleable.AppCompatTheme).getString(R.styleable.AppCompatTheme_viewInflaterClass);
            if (string == null || AppCompatViewInflater.class.getName().equals(string)) {
                appCompatViewInflater = new AppCompatViewInflater();
            } else {
                try {
                    this.mAppCompatViewInflater = (AppCompatViewInflater) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    appCompatViewInflater = new AppCompatViewInflater();
                }
            }
            this.mAppCompatViewInflater = appCompatViewInflater;
        }
        if (IS_PRE_LOLLIPOP) {
            if (!(attributeSet instanceof XmlPullParser)) {
                z2 = shouldInheritContext((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                z2 = true;
            }
            z = z2;
        } else {
            z = false;
        }
        return this.mAppCompatViewInflater.a(view, str, context, attributeSet, z, IS_PRE_LOLLIPOP, true, VectorEnabledTintResources.shouldBeUsed());
    }

    /* access modifiers changed from: package-private */
    public final CharSequence d() {
        Window.Callback callback = this.c;
        return callback instanceof Activity ? ((Activity) callback).getTitle() : this.mTitle;
    }

    /* access modifiers changed from: package-private */
    public void d(int i2) {
        PanelFeatureState a2;
        PanelFeatureState a3 = a(i2, true);
        if (a3.j != null) {
            Bundle bundle = new Bundle();
            a3.j.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                a3.r = bundle;
            }
            a3.j.stopDispatchingItemsChanged();
            a3.j.clear();
        }
        a3.q = true;
        a3.p = true;
        if ((i2 == 108 || i2 == 0) && this.mDecorContentParent != null && (a2 = a(0, false)) != null) {
            a2.m = false;
            preparePanel(a2, (KeyEvent) null);
        }
    }

    /* access modifiers changed from: package-private */
    public int e(int i2) {
        boolean z;
        boolean z2;
        boolean z3;
        ActionBarContextView actionBarContextView = this.i;
        int i3 = 0;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.i.getLayoutParams();
            z = true;
            if (this.i.isShown()) {
                if (this.mTempRect1 == null) {
                    this.mTempRect1 = new Rect();
                    this.mTempRect2 = new Rect();
                }
                Rect rect = this.mTempRect1;
                Rect rect2 = this.mTempRect2;
                rect.set(0, i2, 0, 0);
                ViewUtils.computeFitSystemWindows(this.mSubDecor, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i2 : 0)) {
                    marginLayoutParams.topMargin = i2;
                    View view = this.mStatusGuard;
                    if (view == null) {
                        this.mStatusGuard = new View(this.a);
                        this.mStatusGuard.setBackgroundColor(this.a.getResources().getColor(R.color.abc_input_method_navigation_guard));
                        this.mSubDecor.addView(this.mStatusGuard, -1, new ViewGroup.LayoutParams(-1, i2));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i2) {
                            layoutParams.height = i2;
                            this.mStatusGuard.setLayoutParams(layoutParams);
                        }
                    }
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (this.mStatusGuard == null) {
                    z = false;
                }
                if (!this.o && z) {
                    i2 = 0;
                }
            } else {
                if (marginLayoutParams.topMargin != 0) {
                    marginLayoutParams.topMargin = 0;
                    z3 = true;
                } else {
                    z3 = false;
                }
                z = false;
            }
            if (z2) {
                this.i.setLayoutParams(marginLayoutParams);
            }
        }
        View view2 = this.mStatusGuard;
        if (view2 != null) {
            if (!z) {
                i3 = 8;
            }
            view2.setVisibility(i3);
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.mSubDecor;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean e() {
        /*
            r1 = this;
            boolean r0 = r1.mSubDecorInstalled
            if (r0 == 0) goto L_0x0010
            android.view.ViewGroup r0 = r1.mSubDecor
            if (r0 == 0) goto L_0x0010
            boolean r0 = androidx.core.view.ViewCompat.isLaidOut(r0)
            if (r0 == 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.e():boolean");
    }

    /* access modifiers changed from: package-private */
    public int f(int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != 0) {
            return i2;
        }
        if (Build.VERSION.SDK_INT >= 23 && ((UiModeManager) this.a.getSystemService(UiModeManager.class)).getNightMode() == 0) {
            return -1;
        }
        ensureAutoNightModeManager();
        return this.mAutoNightModeManager.a();
    }

    /* access modifiers changed from: package-private */
    public void f() {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.l;
        if (viewPropertyAnimatorCompat != null) {
            viewPropertyAnimatorCompat.cancel();
        }
    }

    @Nullable
    public <T extends View> T findViewById(@IdRes int i2) {
        ensureSubDecor();
        return this.b.findViewById(i2);
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        androidx.appcompat.view.ActionMode actionMode = this.h;
        if (actionMode != null) {
            actionMode.finish();
            return true;
        }
        ActionBar supportActionBar = getSupportActionBar();
        return supportActionBar != null && supportActionBar.collapseActionView();
    }

    public final ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
        return new ActionBarDrawableToggleImpl();
    }

    public MenuInflater getMenuInflater() {
        if (this.g == null) {
            initWindowDecorActionBar();
            ActionBar actionBar = this.f;
            this.g = new SupportMenuInflater(actionBar != null ? actionBar.getThemedContext() : this.a);
        }
        return this.g;
    }

    public ActionBar getSupportActionBar() {
        initWindowDecorActionBar();
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public void h() {
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent != null) {
            decorContentParent.dismissPopups();
        }
        if (this.j != null) {
            this.b.getDecorView().removeCallbacks(this.k);
            if (this.j.isShowing()) {
                try {
                    this.j.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.j = null;
        }
        f();
        PanelFeatureState a2 = a(0, false);
        if (a2 != null && a2.j != null) {
            a2.j.close();
        }
    }

    public boolean hasWindowFeature(int i2) {
        boolean z;
        switch (sanitizeWindowFeatureId(i2)) {
            case 1:
                z = this.q;
                break;
            case 2:
                z = this.mFeatureProgress;
                break;
            case 5:
                z = this.mFeatureIndeterminateProgress;
                break;
            case 10:
                z = this.o;
                break;
            case 108:
                z = this.m;
                break;
            case 109:
                z = this.n;
                break;
            default:
                z = false;
                break;
        }
        return z || this.b.hasFeature(i2);
    }

    public void installViewFactory() {
        LayoutInflater from = LayoutInflater.from(this.a);
        if (from.getFactory() == null) {
            LayoutInflaterCompat.setFactory2(from, this);
        } else if (!(from.getFactory2() instanceof AppCompatDelegateImpl)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public void invalidateOptionsMenu() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null || !supportActionBar.invalidateOptionsMenu()) {
            invalidatePanelMenu(0);
        }
    }

    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }

    public void onConfigurationChanged(Configuration configuration) {
        ActionBar supportActionBar;
        if (this.m && this.mSubDecorInstalled && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.onConfigurationChanged(configuration);
        }
        AppCompatDrawableManager.get().onConfigurationChanged(this.a);
        applyDayNight();
    }

    public void onCreate(Bundle bundle) {
        Window.Callback callback = this.c;
        if (callback instanceof Activity) {
            String str = null;
            try {
                str = NavUtils.getParentActivityName((Activity) callback);
            } catch (IllegalArgumentException unused) {
            }
            if (str != null) {
                ActionBar a2 = a();
                if (a2 == null) {
                    this.mEnableDefaultActionBarUp = true;
                } else {
                    a2.setDefaultDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (bundle != null && this.mLocalNightMode == -100) {
            this.mLocalNightMode = bundle.getInt(KEY_LOCAL_NIGHT_MODE, -100);
        }
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return createView(view, str, context, attributeSet);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView((View) null, str, context, attributeSet);
    }

    public void onDestroy() {
        if (this.s) {
            this.b.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
        }
        this.r = true;
        ActionBar actionBar = this.f;
        if (actionBar != null) {
            actionBar.a();
        }
        AutoNightModeManager autoNightModeManager = this.mAutoNightModeManager;
        if (autoNightModeManager != null) {
            autoNightModeManager.d();
        }
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        PanelFeatureState a2;
        Window.Callback b2 = b();
        if (b2 == null || this.r || (a2 = a((Menu) menuBuilder.getRootMenu())) == null) {
            return false;
        }
        return b2.onMenuItemSelected(a2.a, menuItem);
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
        reopenMenu(menuBuilder, true);
    }

    public void onPostCreate(Bundle bundle) {
        ensureSubDecor();
    }

    public void onPostResume() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(true);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        int i2 = this.mLocalNightMode;
        if (i2 != -100) {
            bundle.putInt(KEY_LOCAL_NIGHT_MODE, i2);
        }
    }

    public void onStart() {
        applyDayNight();
    }

    public void onStop() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
        }
        AutoNightModeManager autoNightModeManager = this.mAutoNightModeManager;
        if (autoNightModeManager != null) {
            autoNightModeManager.d();
        }
    }

    public boolean requestWindowFeature(int i2) {
        int sanitizeWindowFeatureId = sanitizeWindowFeatureId(i2);
        if (this.q && sanitizeWindowFeatureId == 108) {
            return false;
        }
        if (this.m && sanitizeWindowFeatureId == 1) {
            this.m = false;
        }
        switch (sanitizeWindowFeatureId) {
            case 1:
                throwFeatureRequestIfSubDecorInstalled();
                this.q = true;
                return true;
            case 2:
                throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureProgress = true;
                return true;
            case 5:
                throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureIndeterminateProgress = true;
                return true;
            case 10:
                throwFeatureRequestIfSubDecorInstalled();
                this.o = true;
                return true;
            case 108:
                throwFeatureRequestIfSubDecorInstalled();
                this.m = true;
                return true;
            case 109:
                throwFeatureRequestIfSubDecorInstalled();
                this.n = true;
                return true;
            default:
                return this.b.requestFeature(sanitizeWindowFeatureId);
        }
    }

    public void setContentView(int i2) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.a).inflate(i2, viewGroup);
        this.c.onContentChanged();
    }

    public void setContentView(View view) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.c.onContentChanged();
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.c.onContentChanged();
    }

    public void setHandleNativeActionModesEnabled(boolean z) {
        this.mHandleNativeActionModes = z;
    }

    public void setLocalNightMode(int i2) {
        switch (i2) {
            case -1:
            case 0:
            case 1:
            case 2:
                if (this.mLocalNightMode != i2) {
                    this.mLocalNightMode = i2;
                    if (this.mApplyDayNightCalled) {
                        applyDayNight();
                        return;
                    }
                    return;
                }
                return;
            default:
                Log.i("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
                return;
        }
    }

    public void setSupportActionBar(Toolbar toolbar) {
        Window window;
        Window.Callback callback;
        if (this.c instanceof Activity) {
            ActionBar supportActionBar = getSupportActionBar();
            if (!(supportActionBar instanceof WindowDecorActionBar)) {
                this.g = null;
                if (supportActionBar != null) {
                    supportActionBar.a();
                }
                if (toolbar != null) {
                    ToolbarActionBar toolbarActionBar = new ToolbarActionBar(toolbar, ((Activity) this.c).getTitle(), this.d);
                    this.f = toolbarActionBar;
                    window = this.b;
                    callback = toolbarActionBar.getWrappedWindowCallback();
                } else {
                    this.f = null;
                    window = this.b;
                    callback = this.d;
                }
                window.setCallback(callback);
                invalidateOptionsMenu();
                return;
            }
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
    }

    public final void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent != null) {
            decorContentParent.setWindowTitle(charSequence);
        } else if (a() != null) {
            a().setWindowTitle(charSequence);
        } else {
            TextView textView = this.mTitleView;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
    }

    public androidx.appcompat.view.ActionMode startSupportActionMode(@NonNull ActionMode.Callback callback) {
        AppCompatCallback appCompatCallback;
        if (callback != null) {
            androidx.appcompat.view.ActionMode actionMode = this.h;
            if (actionMode != null) {
                actionMode.finish();
            }
            ActionModeCallbackWrapperV9 actionModeCallbackWrapperV9 = new ActionModeCallbackWrapperV9(callback);
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                this.h = supportActionBar.startActionMode(actionModeCallbackWrapperV9);
                androidx.appcompat.view.ActionMode actionMode2 = this.h;
                if (!(actionMode2 == null || (appCompatCallback = this.e) == null)) {
                    appCompatCallback.onSupportActionModeStarted(actionMode2);
                }
            }
            if (this.h == null) {
                this.h = a((ActionMode.Callback) actionModeCallbackWrapperV9);
            }
            return this.h;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }
}
