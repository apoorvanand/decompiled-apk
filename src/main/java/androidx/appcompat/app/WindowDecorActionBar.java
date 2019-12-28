package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.SpinnerAdapter;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.ViewPropertyAnimatorCompatSet;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.ViewPropertyAnimatorUpdateListener;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WindowDecorActionBar extends ActionBar implements ActionBarOverlayLayout.ActionBarVisibilityCallback {
    private static final long FADE_IN_DURATION_MS = 200;
    private static final long FADE_OUT_DURATION_MS = 100;
    private static final int INVALID_POSITION = -1;
    private static final String TAG = "WindowDecorActionBar";
    static final /* synthetic */ boolean s = (!WindowDecorActionBar.class.desiredAssertionStatus());
    private static final Interpolator sHideInterpolator = new AccelerateInterpolator();
    private static final Interpolator sShowInterpolator = new DecelerateInterpolator();
    Context a;
    ActionBarOverlayLayout b;
    ActionBarContainer c;
    DecorToolbar d;
    ActionBarContextView e;
    View f;
    ScrollingTabContainerView g;
    ActionModeImpl h;
    ActionMode i;
    ActionMode.Callback j;
    boolean k = true;
    boolean l;
    boolean m;
    private Activity mActivity;
    private int mCurWindowVisibility = 0;
    private Dialog mDialog;
    private boolean mDisplayHomeAsUpSet;
    private boolean mHasEmbeddedTabs;
    private boolean mLastMenuVisibility;
    private ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners = new ArrayList<>();
    private boolean mNowShowing = true;
    private int mSavedTabPosition = -1;
    private TabImpl mSelectedTab;
    private boolean mShowHideAnimationEnabled;
    private boolean mShowingForMode;
    private ArrayList<TabImpl> mTabs = new ArrayList<>();
    private Context mThemedContext;
    ViewPropertyAnimatorCompatSet n;
    boolean o;
    final ViewPropertyAnimatorListener p = new ViewPropertyAnimatorListenerAdapter() {
        public void onAnimationEnd(View view) {
            if (WindowDecorActionBar.this.k && WindowDecorActionBar.this.f != null) {
                WindowDecorActionBar.this.f.setTranslationY(0.0f);
                WindowDecorActionBar.this.c.setTranslationY(0.0f);
            }
            WindowDecorActionBar.this.c.setVisibility(8);
            WindowDecorActionBar.this.c.setTransitioning(false);
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            windowDecorActionBar.n = null;
            windowDecorActionBar.b();
            if (WindowDecorActionBar.this.b != null) {
                ViewCompat.requestApplyInsets(WindowDecorActionBar.this.b);
            }
        }
    };
    final ViewPropertyAnimatorListener q = new ViewPropertyAnimatorListenerAdapter() {
        public void onAnimationEnd(View view) {
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            windowDecorActionBar.n = null;
            windowDecorActionBar.c.requestLayout();
        }
    };
    final ViewPropertyAnimatorUpdateListener r = new ViewPropertyAnimatorUpdateListener() {
        public void onAnimationUpdate(View view) {
            ((View) WindowDecorActionBar.this.c.getParent()).invalidate();
        }
    };

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
        private final Context mActionModeContext;
        private ActionMode.Callback mCallback;
        private WeakReference<View> mCustomView;
        private final MenuBuilder mMenu;

        public ActionModeImpl(Context context, ActionMode.Callback callback) {
            this.mActionModeContext = context;
            this.mCallback = callback;
            this.mMenu = new MenuBuilder(context).setDefaultShowAsAction(1);
            this.mMenu.setCallback(this);
        }

        public boolean dispatchOnCreate() {
            this.mMenu.stopDispatchingItemsChanged();
            try {
                return this.mCallback.onCreateActionMode(this, this.mMenu);
            } finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }

        public void finish() {
            if (WindowDecorActionBar.this.h == this) {
                if (!WindowDecorActionBar.a(WindowDecorActionBar.this.l, WindowDecorActionBar.this.m, false)) {
                    WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
                    windowDecorActionBar.i = this;
                    windowDecorActionBar.j = this.mCallback;
                } else {
                    this.mCallback.onDestroyActionMode(this);
                }
                this.mCallback = null;
                WindowDecorActionBar.this.animateToMode(false);
                WindowDecorActionBar.this.e.closeMode();
                WindowDecorActionBar.this.d.getViewGroup().sendAccessibilityEvent(32);
                WindowDecorActionBar.this.b.setHideOnContentScrollEnabled(WindowDecorActionBar.this.o);
                WindowDecorActionBar.this.h = null;
            }
        }

        public View getCustomView() {
            WeakReference<View> weakReference = this.mCustomView;
            if (weakReference != null) {
                return (View) weakReference.get();
            }
            return null;
        }

        public Menu getMenu() {
            return this.mMenu;
        }

        public MenuInflater getMenuInflater() {
            return new SupportMenuInflater(this.mActionModeContext);
        }

        public CharSequence getSubtitle() {
            return WindowDecorActionBar.this.e.getSubtitle();
        }

        public CharSequence getTitle() {
            return WindowDecorActionBar.this.e.getTitle();
        }

        public void invalidate() {
            if (WindowDecorActionBar.this.h == this) {
                this.mMenu.stopDispatchingItemsChanged();
                try {
                    this.mCallback.onPrepareActionMode(this, this.mMenu);
                } finally {
                    this.mMenu.startDispatchingItemsChanged();
                }
            }
        }

        public boolean isTitleOptional() {
            return WindowDecorActionBar.this.e.isTitleOptional();
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }

        public void onCloseSubMenu(SubMenuBuilder subMenuBuilder) {
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            ActionMode.Callback callback = this.mCallback;
            if (callback != null) {
                return callback.onActionItemClicked(this, menuItem);
            }
            return false;
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (this.mCallback != null) {
                invalidate();
                WindowDecorActionBar.this.e.showOverflowMenu();
            }
        }

        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
            if (this.mCallback == null) {
                return false;
            }
            if (!subMenuBuilder.hasVisibleItems()) {
                return true;
            }
            new MenuPopupHelper(WindowDecorActionBar.this.getThemedContext(), subMenuBuilder).show();
            return true;
        }

        public void setCustomView(View view) {
            WindowDecorActionBar.this.e.setCustomView(view);
            this.mCustomView = new WeakReference<>(view);
        }

        public void setSubtitle(int i) {
            setSubtitle((CharSequence) WindowDecorActionBar.this.a.getResources().getString(i));
        }

        public void setSubtitle(CharSequence charSequence) {
            WindowDecorActionBar.this.e.setSubtitle(charSequence);
        }

        public void setTitle(int i) {
            setTitle((CharSequence) WindowDecorActionBar.this.a.getResources().getString(i));
        }

        public void setTitle(CharSequence charSequence) {
            WindowDecorActionBar.this.e.setTitle(charSequence);
        }

        public void setTitleOptionalHint(boolean z) {
            super.setTitleOptionalHint(z);
            WindowDecorActionBar.this.e.setTitleOptional(z);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public class TabImpl extends ActionBar.Tab {
        private ActionBar.TabListener mCallback;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private int mPosition = -1;
        private Object mTag;
        private CharSequence mText;

        public TabImpl() {
        }

        public ActionBar.TabListener getCallback() {
            return this.mCallback;
        }

        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }

        public View getCustomView() {
            return this.mCustomView;
        }

        public Drawable getIcon() {
            return this.mIcon;
        }

        public int getPosition() {
            return this.mPosition;
        }

        public Object getTag() {
            return this.mTag;
        }

        public CharSequence getText() {
            return this.mText;
        }

        public void select() {
            WindowDecorActionBar.this.selectTab(this);
        }

        public ActionBar.Tab setContentDescription(int i) {
            return setContentDescription(WindowDecorActionBar.this.a.getResources().getText(i));
        }

        public ActionBar.Tab setContentDescription(CharSequence charSequence) {
            this.mContentDesc = charSequence;
            if (this.mPosition >= 0) {
                WindowDecorActionBar.this.g.updateTab(this.mPosition);
            }
            return this;
        }

        public ActionBar.Tab setCustomView(int i) {
            return setCustomView(LayoutInflater.from(WindowDecorActionBar.this.getThemedContext()).inflate(i, (ViewGroup) null));
        }

        public ActionBar.Tab setCustomView(View view) {
            this.mCustomView = view;
            if (this.mPosition >= 0) {
                WindowDecorActionBar.this.g.updateTab(this.mPosition);
            }
            return this;
        }

        public ActionBar.Tab setIcon(int i) {
            return setIcon(AppCompatResources.getDrawable(WindowDecorActionBar.this.a, i));
        }

        public ActionBar.Tab setIcon(Drawable drawable) {
            this.mIcon = drawable;
            if (this.mPosition >= 0) {
                WindowDecorActionBar.this.g.updateTab(this.mPosition);
            }
            return this;
        }

        public void setPosition(int i) {
            this.mPosition = i;
        }

        public ActionBar.Tab setTabListener(ActionBar.TabListener tabListener) {
            this.mCallback = tabListener;
            return this;
        }

        public ActionBar.Tab setTag(Object obj) {
            this.mTag = obj;
            return this;
        }

        public ActionBar.Tab setText(int i) {
            return setText(WindowDecorActionBar.this.a.getResources().getText(i));
        }

        public ActionBar.Tab setText(CharSequence charSequence) {
            this.mText = charSequence;
            if (this.mPosition >= 0) {
                WindowDecorActionBar.this.g.updateTab(this.mPosition);
            }
            return this;
        }
    }

    public WindowDecorActionBar(Activity activity, boolean z) {
        this.mActivity = activity;
        View decorView = activity.getWindow().getDecorView();
        init(decorView);
        if (!z) {
            this.f = decorView.findViewById(16908290);
        }
    }

    public WindowDecorActionBar(Dialog dialog) {
        this.mDialog = dialog;
        init(dialog.getWindow().getDecorView());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WindowDecorActionBar(View view) {
        if (s || view.isInEditMode()) {
            init(view);
            return;
        }
        throw new AssertionError();
    }

    static boolean a(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return !z && !z2;
    }

    private void cleanupTabs() {
        if (this.mSelectedTab != null) {
            selectTab((ActionBar.Tab) null);
        }
        this.mTabs.clear();
        ScrollingTabContainerView scrollingTabContainerView = this.g;
        if (scrollingTabContainerView != null) {
            scrollingTabContainerView.removeAllTabs();
        }
        this.mSavedTabPosition = -1;
    }

    private void configureTab(ActionBar.Tab tab, int i2) {
        TabImpl tabImpl = (TabImpl) tab;
        if (tabImpl.getCallback() != null) {
            tabImpl.setPosition(i2);
            this.mTabs.add(i2, tabImpl);
            int size = this.mTabs.size();
            while (true) {
                i2++;
                if (i2 < size) {
                    this.mTabs.get(i2).setPosition(i2);
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        }
    }

    private void ensureTabsExist() {
        if (this.g == null) {
            ScrollingTabContainerView scrollingTabContainerView = new ScrollingTabContainerView(this.a);
            if (this.mHasEmbeddedTabs) {
                scrollingTabContainerView.setVisibility(0);
                this.d.setEmbeddedTabView(scrollingTabContainerView);
            } else {
                if (getNavigationMode() == 2) {
                    scrollingTabContainerView.setVisibility(0);
                    ActionBarOverlayLayout actionBarOverlayLayout = this.b;
                    if (actionBarOverlayLayout != null) {
                        ViewCompat.requestApplyInsets(actionBarOverlayLayout);
                    }
                } else {
                    scrollingTabContainerView.setVisibility(8);
                }
                this.c.setTabContainer(scrollingTabContainerView);
            }
            this.g = scrollingTabContainerView;
        }
    }

    private DecorToolbar getDecorToolbar(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != null ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sb.toString());
    }

    private void hideForActionMode() {
        if (this.mShowingForMode) {
            this.mShowingForMode = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.b;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            updateVisibility(false);
        }
    }

    private void init(View view) {
        this.b = (ActionBarOverlayLayout) view.findViewById(R.id.decor_content_parent);
        ActionBarOverlayLayout actionBarOverlayLayout = this.b;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.d = getDecorToolbar(view.findViewById(R.id.action_bar));
        this.e = (ActionBarContextView) view.findViewById(R.id.action_context_bar);
        this.c = (ActionBarContainer) view.findViewById(R.id.action_bar_container);
        DecorToolbar decorToolbar = this.d;
        if (decorToolbar == null || this.e == null || this.c == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.a = decorToolbar.getContext();
        boolean z = (this.d.getDisplayOptions() & 4) != 0;
        if (z) {
            this.mDisplayHomeAsUpSet = true;
        }
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(this.a);
        setHomeButtonEnabled(actionBarPolicy.enableHomeButtonByDefault() || z);
        setHasEmbeddedTabs(actionBarPolicy.hasEmbeddedTabs());
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes((AttributeSet) null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(R.styleable.ActionBar_hideOnContentScroll, false)) {
            setHideOnContentScrollEnabled(true);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            setElevation((float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    private void setHasEmbeddedTabs(boolean z) {
        this.mHasEmbeddedTabs = z;
        if (!this.mHasEmbeddedTabs) {
            this.d.setEmbeddedTabView((ScrollingTabContainerView) null);
            this.c.setTabContainer(this.g);
        } else {
            this.c.setTabContainer((ScrollingTabContainerView) null);
            this.d.setEmbeddedTabView(this.g);
        }
        boolean z2 = true;
        boolean z3 = getNavigationMode() == 2;
        ScrollingTabContainerView scrollingTabContainerView = this.g;
        if (scrollingTabContainerView != null) {
            if (z3) {
                scrollingTabContainerView.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.b;
                if (actionBarOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(actionBarOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
        }
        this.d.setCollapsible(!this.mHasEmbeddedTabs && z3);
        ActionBarOverlayLayout actionBarOverlayLayout2 = this.b;
        if (this.mHasEmbeddedTabs || !z3) {
            z2 = false;
        }
        actionBarOverlayLayout2.setHasNonEmbeddedTabs(z2);
    }

    private boolean shouldAnimateContextView() {
        return ViewCompat.isLaidOut(this.c);
    }

    private void showForActionMode() {
        if (!this.mShowingForMode) {
            this.mShowingForMode = true;
            ActionBarOverlayLayout actionBarOverlayLayout = this.b;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(true);
            }
            updateVisibility(false);
        }
    }

    private void updateVisibility(boolean z) {
        if (a(this.l, this.m, this.mShowingForMode)) {
            if (!this.mNowShowing) {
                this.mNowShowing = true;
                doShow(z);
            }
        } else if (this.mNowShowing) {
            this.mNowShowing = false;
            doHide(z);
        }
    }

    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.add(onMenuVisibilityListener);
    }

    public void addTab(ActionBar.Tab tab) {
        addTab(tab, this.mTabs.isEmpty());
    }

    public void addTab(ActionBar.Tab tab, int i2) {
        addTab(tab, i2, this.mTabs.isEmpty());
    }

    public void addTab(ActionBar.Tab tab, int i2, boolean z) {
        ensureTabsExist();
        this.g.addTab(tab, i2, z);
        configureTab(tab, i2);
        if (z) {
            selectTab(tab);
        }
    }

    public void addTab(ActionBar.Tab tab, boolean z) {
        ensureTabsExist();
        this.g.addTab(tab, z);
        configureTab(tab, this.mTabs.size());
        if (z) {
            selectTab(tab);
        }
    }

    public void animateToMode(boolean z) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat;
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2;
        if (z) {
            showForActionMode();
        } else {
            hideForActionMode();
        }
        if (shouldAnimateContextView()) {
            if (z) {
                viewPropertyAnimatorCompat = this.d.setupAnimatorToVisibility(4, FADE_OUT_DURATION_MS);
                viewPropertyAnimatorCompat2 = this.e.setupAnimatorToVisibility(0, FADE_IN_DURATION_MS);
            } else {
                viewPropertyAnimatorCompat2 = this.d.setupAnimatorToVisibility(0, FADE_IN_DURATION_MS);
                viewPropertyAnimatorCompat = this.e.setupAnimatorToVisibility(8, FADE_OUT_DURATION_MS);
            }
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
            viewPropertyAnimatorCompatSet.playSequentially(viewPropertyAnimatorCompat, viewPropertyAnimatorCompat2);
            viewPropertyAnimatorCompatSet.start();
        } else if (z) {
            this.d.setVisibility(4);
            this.e.setVisibility(0);
        } else {
            this.d.setVisibility(0);
            this.e.setVisibility(8);
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        ActionMode.Callback callback = this.j;
        if (callback != null) {
            callback.onDestroyActionMode(this.i);
            this.i = null;
            this.j = null;
        }
    }

    public boolean collapseActionView() {
        DecorToolbar decorToolbar = this.d;
        if (decorToolbar == null || !decorToolbar.hasExpandedActionView()) {
            return false;
        }
        this.d.collapseActionView();
        return true;
    }

    public void dispatchMenuVisibilityChanged(boolean z) {
        if (z != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = z;
            int size = this.mMenuVisibilityListeners.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mMenuVisibilityListeners.get(i2).onMenuVisibilityChanged(z);
            }
        }
    }

    public void doHide(boolean z) {
        View view;
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.n;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.cancel();
        }
        if (this.mCurWindowVisibility != 0 || (!this.mShowHideAnimationEnabled && !z)) {
            this.p.onAnimationEnd((View) null);
            return;
        }
        this.c.setAlpha(1.0f);
        this.c.setTransitioning(true);
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet2 = new ViewPropertyAnimatorCompatSet();
        float f2 = (float) (-this.c.getHeight());
        if (z) {
            int[] iArr = {0, 0};
            this.c.getLocationInWindow(iArr);
            f2 -= (float) iArr[1];
        }
        ViewPropertyAnimatorCompat translationY = ViewCompat.animate(this.c).translationY(f2);
        translationY.setUpdateListener(this.r);
        viewPropertyAnimatorCompatSet2.play(translationY);
        if (this.k && (view = this.f) != null) {
            viewPropertyAnimatorCompatSet2.play(ViewCompat.animate(view).translationY(f2));
        }
        viewPropertyAnimatorCompatSet2.setInterpolator(sHideInterpolator);
        viewPropertyAnimatorCompatSet2.setDuration(250);
        viewPropertyAnimatorCompatSet2.setListener(this.p);
        this.n = viewPropertyAnimatorCompatSet2;
        viewPropertyAnimatorCompatSet2.start();
    }

    public void doShow(boolean z) {
        View view;
        View view2;
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.n;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.cancel();
        }
        this.c.setVisibility(0);
        if (this.mCurWindowVisibility != 0 || (!this.mShowHideAnimationEnabled && !z)) {
            this.c.setAlpha(1.0f);
            this.c.setTranslationY(0.0f);
            if (this.k && (view = this.f) != null) {
                view.setTranslationY(0.0f);
            }
            this.q.onAnimationEnd((View) null);
        } else {
            this.c.setTranslationY(0.0f);
            float f2 = (float) (-this.c.getHeight());
            if (z) {
                int[] iArr = {0, 0};
                this.c.getLocationInWindow(iArr);
                f2 -= (float) iArr[1];
            }
            this.c.setTranslationY(f2);
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet2 = new ViewPropertyAnimatorCompatSet();
            ViewPropertyAnimatorCompat translationY = ViewCompat.animate(this.c).translationY(0.0f);
            translationY.setUpdateListener(this.r);
            viewPropertyAnimatorCompatSet2.play(translationY);
            if (this.k && (view2 = this.f) != null) {
                view2.setTranslationY(f2);
                viewPropertyAnimatorCompatSet2.play(ViewCompat.animate(this.f).translationY(0.0f));
            }
            viewPropertyAnimatorCompatSet2.setInterpolator(sShowInterpolator);
            viewPropertyAnimatorCompatSet2.setDuration(250);
            viewPropertyAnimatorCompatSet2.setListener(this.q);
            this.n = viewPropertyAnimatorCompatSet2;
            viewPropertyAnimatorCompatSet2.start();
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.b;
        if (actionBarOverlayLayout != null) {
            ViewCompat.requestApplyInsets(actionBarOverlayLayout);
        }
    }

    public void enableContentAnimations(boolean z) {
        this.k = z;
    }

    public View getCustomView() {
        return this.d.getCustomView();
    }

    public int getDisplayOptions() {
        return this.d.getDisplayOptions();
    }

    public float getElevation() {
        return ViewCompat.getElevation(this.c);
    }

    public int getHeight() {
        return this.c.getHeight();
    }

    public int getHideOffset() {
        return this.b.getActionBarHideOffset();
    }

    public int getNavigationItemCount() {
        switch (this.d.getNavigationMode()) {
            case 1:
                return this.d.getDropdownItemCount();
            case 2:
                return this.mTabs.size();
            default:
                return 0;
        }
    }

    public int getNavigationMode() {
        return this.d.getNavigationMode();
    }

    public int getSelectedNavigationIndex() {
        switch (this.d.getNavigationMode()) {
            case 1:
                return this.d.getDropdownSelectedPosition();
            case 2:
                TabImpl tabImpl = this.mSelectedTab;
                if (tabImpl != null) {
                    return tabImpl.getPosition();
                }
                return -1;
            default:
                return -1;
        }
    }

    public ActionBar.Tab getSelectedTab() {
        return this.mSelectedTab;
    }

    public CharSequence getSubtitle() {
        return this.d.getSubtitle();
    }

    public ActionBar.Tab getTabAt(int i2) {
        return this.mTabs.get(i2);
    }

    public int getTabCount() {
        return this.mTabs.size();
    }

    public Context getThemedContext() {
        if (this.mThemedContext == null) {
            TypedValue typedValue = new TypedValue();
            this.a.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.mThemedContext = new ContextThemeWrapper(this.a, i2);
            } else {
                this.mThemedContext = this.a;
            }
        }
        return this.mThemedContext;
    }

    public CharSequence getTitle() {
        return this.d.getTitle();
    }

    public boolean hasIcon() {
        return this.d.hasIcon();
    }

    public boolean hasLogo() {
        return this.d.hasLogo();
    }

    public void hide() {
        if (!this.l) {
            this.l = true;
            updateVisibility(false);
        }
    }

    public void hideForSystem() {
        if (!this.m) {
            this.m = true;
            updateVisibility(true);
        }
    }

    public boolean isHideOnContentScrollEnabled() {
        return this.b.isHideOnContentScrollEnabled();
    }

    public boolean isShowing() {
        int height = getHeight();
        return this.mNowShowing && (height == 0 || getHideOffset() < height);
    }

    public boolean isTitleTruncated() {
        DecorToolbar decorToolbar = this.d;
        return decorToolbar != null && decorToolbar.isTitleTruncated();
    }

    public ActionBar.Tab newTab() {
        return new TabImpl();
    }

    public void onConfigurationChanged(Configuration configuration) {
        setHasEmbeddedTabs(ActionBarPolicy.get(this.a).hasEmbeddedTabs());
    }

    public void onContentScrollStarted() {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.n;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.cancel();
            this.n = null;
        }
    }

    public void onContentScrollStopped() {
    }

    public boolean onKeyShortcut(int i2, KeyEvent keyEvent) {
        Menu menu;
        ActionModeImpl actionModeImpl = this.h;
        if (actionModeImpl == null || (menu = actionModeImpl.getMenu()) == null) {
            return false;
        }
        boolean z = true;
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
            z = false;
        }
        menu.setQwertyMode(z);
        return menu.performShortcut(i2, keyEvent, 0);
    }

    public void onWindowVisibilityChanged(int i2) {
        this.mCurWindowVisibility = i2;
    }

    public void removeAllTabs() {
        cleanupTabs();
    }

    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.remove(onMenuVisibilityListener);
    }

    public void removeTab(ActionBar.Tab tab) {
        removeTabAt(tab.getPosition());
    }

    public void removeTabAt(int i2) {
        if (this.g != null) {
            TabImpl tabImpl = this.mSelectedTab;
            int position = tabImpl != null ? tabImpl.getPosition() : this.mSavedTabPosition;
            this.g.removeTabAt(i2);
            TabImpl remove = this.mTabs.remove(i2);
            if (remove != null) {
                remove.setPosition(-1);
            }
            int size = this.mTabs.size();
            for (int i3 = i2; i3 < size; i3++) {
                this.mTabs.get(i3).setPosition(i3);
            }
            if (position == i2) {
                selectTab(this.mTabs.isEmpty() ? null : this.mTabs.get(Math.max(0, i2 - 1)));
            }
        }
    }

    public boolean requestFocus() {
        ViewGroup viewGroup = this.d.getViewGroup();
        if (viewGroup == null || viewGroup.hasFocus()) {
            return false;
        }
        viewGroup.requestFocus();
        return true;
    }

    public void selectTab(ActionBar.Tab tab) {
        int i2 = -1;
        if (getNavigationMode() != 2) {
            if (tab != null) {
                i2 = tab.getPosition();
            }
            this.mSavedTabPosition = i2;
            return;
        }
        FragmentTransaction disallowAddToBackStack = (!(this.mActivity instanceof FragmentActivity) || this.d.getViewGroup().isInEditMode()) ? null : ((FragmentActivity) this.mActivity).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
        TabImpl tabImpl = this.mSelectedTab;
        if (tabImpl != tab) {
            ScrollingTabContainerView scrollingTabContainerView = this.g;
            if (tab != null) {
                i2 = tab.getPosition();
            }
            scrollingTabContainerView.setTabSelected(i2);
            TabImpl tabImpl2 = this.mSelectedTab;
            if (tabImpl2 != null) {
                tabImpl2.getCallback().onTabUnselected(this.mSelectedTab, disallowAddToBackStack);
            }
            this.mSelectedTab = (TabImpl) tab;
            TabImpl tabImpl3 = this.mSelectedTab;
            if (tabImpl3 != null) {
                tabImpl3.getCallback().onTabSelected(this.mSelectedTab, disallowAddToBackStack);
            }
        } else if (tabImpl != null) {
            tabImpl.getCallback().onTabReselected(this.mSelectedTab, disallowAddToBackStack);
            this.g.animateToTab(tab.getPosition());
        }
        if (disallowAddToBackStack != null && !disallowAddToBackStack.isEmpty()) {
            disallowAddToBackStack.commit();
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.c.setPrimaryBackground(drawable);
    }

    public void setCustomView(int i2) {
        setCustomView(LayoutInflater.from(getThemedContext()).inflate(i2, this.d.getViewGroup(), false));
    }

    public void setCustomView(View view) {
        this.d.setCustomView(view);
    }

    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        view.setLayoutParams(layoutParams);
        this.d.setCustomView(view);
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
        if (!this.mDisplayHomeAsUpSet) {
            setDisplayHomeAsUpEnabled(z);
        }
    }

    public void setDisplayHomeAsUpEnabled(boolean z) {
        setDisplayOptions(z ? 4 : 0, 4);
    }

    public void setDisplayOptions(int i2) {
        if ((i2 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.d.setDisplayOptions(i2);
    }

    public void setDisplayOptions(int i2, int i3) {
        int displayOptions = this.d.getDisplayOptions();
        if ((i3 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.d.setDisplayOptions((i2 & i3) | ((~i3) & displayOptions));
    }

    public void setDisplayShowCustomEnabled(boolean z) {
        setDisplayOptions(z ? 16 : 0, 16);
    }

    public void setDisplayShowHomeEnabled(boolean z) {
        setDisplayOptions(z ? 2 : 0, 2);
    }

    public void setDisplayShowTitleEnabled(boolean z) {
        setDisplayOptions(z ? 8 : 0, 8);
    }

    public void setDisplayUseLogoEnabled(boolean z) {
        setDisplayOptions(z ? 1 : 0, 1);
    }

    public void setElevation(float f2) {
        ViewCompat.setElevation(this.c, f2);
    }

    public void setHideOffset(int i2) {
        if (i2 == 0 || this.b.isInOverlayMode()) {
            this.b.setActionBarHideOffset(i2);
            return;
        }
        throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (!z || this.b.isInOverlayMode()) {
            this.o = z;
            this.b.setHideOnContentScrollEnabled(z);
            return;
        }
        throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
    }

    public void setHomeActionContentDescription(int i2) {
        this.d.setNavigationContentDescription(i2);
    }

    public void setHomeActionContentDescription(CharSequence charSequence) {
        this.d.setNavigationContentDescription(charSequence);
    }

    public void setHomeAsUpIndicator(int i2) {
        this.d.setNavigationIcon(i2);
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        this.d.setNavigationIcon(drawable);
    }

    public void setHomeButtonEnabled(boolean z) {
        this.d.setHomeButtonEnabled(z);
    }

    public void setIcon(int i2) {
        this.d.setIcon(i2);
    }

    public void setIcon(Drawable drawable) {
        this.d.setIcon(drawable);
    }

    public void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter, ActionBar.OnNavigationListener onNavigationListener) {
        this.d.setDropdownParams(spinnerAdapter, new NavItemSelectedListener(onNavigationListener));
    }

    public void setLogo(int i2) {
        this.d.setLogo(i2);
    }

    public void setLogo(Drawable drawable) {
        this.d.setLogo(drawable);
    }

    public void setNavigationMode(int i2) {
        ActionBarOverlayLayout actionBarOverlayLayout;
        int navigationMode = this.d.getNavigationMode();
        if (navigationMode == 2) {
            this.mSavedTabPosition = getSelectedNavigationIndex();
            selectTab((ActionBar.Tab) null);
            this.g.setVisibility(8);
        }
        if (!(navigationMode == i2 || this.mHasEmbeddedTabs || (actionBarOverlayLayout = this.b) == null)) {
            ViewCompat.requestApplyInsets(actionBarOverlayLayout);
        }
        this.d.setNavigationMode(i2);
        boolean z = false;
        if (i2 == 2) {
            ensureTabsExist();
            this.g.setVisibility(0);
            int i3 = this.mSavedTabPosition;
            if (i3 != -1) {
                setSelectedNavigationItem(i3);
                this.mSavedTabPosition = -1;
            }
        }
        this.d.setCollapsible(i2 == 2 && !this.mHasEmbeddedTabs);
        ActionBarOverlayLayout actionBarOverlayLayout2 = this.b;
        if (i2 == 2 && !this.mHasEmbeddedTabs) {
            z = true;
        }
        actionBarOverlayLayout2.setHasNonEmbeddedTabs(z);
    }

    public void setSelectedNavigationItem(int i2) {
        switch (this.d.getNavigationMode()) {
            case 1:
                this.d.setDropdownSelectedPosition(i2);
                return;
            case 2:
                selectTab(this.mTabs.get(i2));
                return;
            default:
                throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
        }
    }

    public void setShowHideAnimationEnabled(boolean z) {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet;
        this.mShowHideAnimationEnabled = z;
        if (!z && (viewPropertyAnimatorCompatSet = this.n) != null) {
            viewPropertyAnimatorCompatSet.cancel();
        }
    }

    public void setSplitBackgroundDrawable(Drawable drawable) {
    }

    public void setStackedBackgroundDrawable(Drawable drawable) {
        this.c.setStackedBackground(drawable);
    }

    public void setSubtitle(int i2) {
        setSubtitle((CharSequence) this.a.getString(i2));
    }

    public void setSubtitle(CharSequence charSequence) {
        this.d.setSubtitle(charSequence);
    }

    public void setTitle(int i2) {
        setTitle((CharSequence) this.a.getString(i2));
    }

    public void setTitle(CharSequence charSequence) {
        this.d.setTitle(charSequence);
    }

    public void setWindowTitle(CharSequence charSequence) {
        this.d.setWindowTitle(charSequence);
    }

    public void show() {
        if (this.l) {
            this.l = false;
            updateVisibility(false);
        }
    }

    public void showForSystem() {
        if (this.m) {
            this.m = false;
            updateVisibility(true);
        }
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        ActionModeImpl actionModeImpl = this.h;
        if (actionModeImpl != null) {
            actionModeImpl.finish();
        }
        this.b.setHideOnContentScrollEnabled(false);
        this.e.killMode();
        ActionModeImpl actionModeImpl2 = new ActionModeImpl(this.e.getContext(), callback);
        if (!actionModeImpl2.dispatchOnCreate()) {
            return null;
        }
        this.h = actionModeImpl2;
        actionModeImpl2.invalidate();
        this.e.initForMode(actionModeImpl2);
        animateToMode(true);
        this.e.sendAccessibilityEvent(32);
        return actionModeImpl2;
    }
}
