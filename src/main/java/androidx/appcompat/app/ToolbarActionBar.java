package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SpinnerAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.WindowCallbackWrapper;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;

class ToolbarActionBar extends ActionBar {
    DecorToolbar a;
    boolean b;
    Window.Callback c;
    private boolean mLastMenuVisibility;
    private boolean mMenuCallbackSet;
    private final Toolbar.OnMenuItemClickListener mMenuClicker = new Toolbar.OnMenuItemClickListener() {
        public boolean onMenuItemClick(MenuItem menuItem) {
            return ToolbarActionBar.this.c.onMenuItemSelected(0, menuItem);
        }
    };
    private final Runnable mMenuInvalidator = new Runnable() {
        public void run() {
            ToolbarActionBar.this.b();
        }
    };
    private ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners = new ArrayList<>();

    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        private boolean mClosingActionMenu;

        ActionMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            if (!this.mClosingActionMenu) {
                this.mClosingActionMenu = true;
                ToolbarActionBar.this.a.dismissPopupMenus();
                if (ToolbarActionBar.this.c != null) {
                    ToolbarActionBar.this.c.onPanelClosed(108, menuBuilder);
                }
                this.mClosingActionMenu = false;
            }
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (ToolbarActionBar.this.c == null) {
                return false;
            }
            ToolbarActionBar.this.c.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    private final class MenuBuilderCallback implements MenuBuilder.Callback {
        MenuBuilderCallback() {
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            return false;
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (ToolbarActionBar.this.c == null) {
                return;
            }
            if (ToolbarActionBar.this.a.isOverflowMenuShowing()) {
                ToolbarActionBar.this.c.onPanelClosed(108, menuBuilder);
            } else if (ToolbarActionBar.this.c.onPreparePanel(0, (View) null, menuBuilder)) {
                ToolbarActionBar.this.c.onMenuOpened(108, menuBuilder);
            }
        }
    }

    private class ToolbarCallbackWrapper extends WindowCallbackWrapper {
        public ToolbarCallbackWrapper(Window.Callback callback) {
            super(callback);
        }

        public View onCreatePanelView(int i) {
            return i == 0 ? new View(ToolbarActionBar.this.a.getContext()) : super.onCreatePanelView(i);
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (onPreparePanel && !ToolbarActionBar.this.b) {
                ToolbarActionBar.this.a.setMenuPrepared();
                ToolbarActionBar.this.b = true;
            }
            return onPreparePanel;
        }
    }

    ToolbarActionBar(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        this.a = new ToolbarWidgetWrapper(toolbar, false);
        this.c = new ToolbarCallbackWrapper(callback);
        this.a.setWindowCallback(this.c);
        toolbar.setOnMenuItemClickListener(this.mMenuClicker);
        this.a.setWindowTitle(charSequence);
    }

    private Menu getMenu() {
        if (!this.mMenuCallbackSet) {
            this.a.setMenuCallbacks(new ActionMenuPresenterCallback(), new MenuBuilderCallback());
            this.mMenuCallbackSet = true;
        }
        return this.a.getMenu();
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.a.getViewGroup().removeCallbacks(this.mMenuInvalidator);
    }

    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.add(onMenuVisibilityListener);
    }

    public void addTab(ActionBar.Tab tab) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void addTab(ActionBar.Tab tab, int i) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void addTab(ActionBar.Tab tab, int i, boolean z) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void addTab(ActionBar.Tab tab, boolean z) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    /* access modifiers changed from: package-private */
    public void b() {
        Menu menu = getMenu();
        MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
        if (menuBuilder != null) {
            menuBuilder.stopDispatchingItemsChanged();
        }
        try {
            menu.clear();
            if (!this.c.onCreatePanelMenu(0, menu) || !this.c.onPreparePanel(0, (View) null, menu)) {
                menu.clear();
            }
        } finally {
            if (menuBuilder != null) {
                menuBuilder.startDispatchingItemsChanged();
            }
        }
    }

    public boolean closeOptionsMenu() {
        return this.a.hideOverflowMenu();
    }

    public boolean collapseActionView() {
        if (!this.a.hasExpandedActionView()) {
            return false;
        }
        this.a.collapseActionView();
        return true;
    }

    public void dispatchMenuVisibilityChanged(boolean z) {
        if (z != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = z;
            int size = this.mMenuVisibilityListeners.size();
            for (int i = 0; i < size; i++) {
                this.mMenuVisibilityListeners.get(i).onMenuVisibilityChanged(z);
            }
        }
    }

    public View getCustomView() {
        return this.a.getCustomView();
    }

    public int getDisplayOptions() {
        return this.a.getDisplayOptions();
    }

    public float getElevation() {
        return ViewCompat.getElevation(this.a.getViewGroup());
    }

    public int getHeight() {
        return this.a.getHeight();
    }

    public int getNavigationItemCount() {
        return 0;
    }

    public int getNavigationMode() {
        return 0;
    }

    public int getSelectedNavigationIndex() {
        return -1;
    }

    public ActionBar.Tab getSelectedTab() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public CharSequence getSubtitle() {
        return this.a.getSubtitle();
    }

    public ActionBar.Tab getTabAt(int i) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public int getTabCount() {
        return 0;
    }

    public Context getThemedContext() {
        return this.a.getContext();
    }

    public CharSequence getTitle() {
        return this.a.getTitle();
    }

    public Window.Callback getWrappedWindowCallback() {
        return this.c;
    }

    public void hide() {
        this.a.setVisibility(8);
    }

    public boolean invalidateOptionsMenu() {
        this.a.getViewGroup().removeCallbacks(this.mMenuInvalidator);
        ViewCompat.postOnAnimation(this.a.getViewGroup(), this.mMenuInvalidator);
        return true;
    }

    public boolean isShowing() {
        return this.a.getVisibility() == 0;
    }

    public boolean isTitleTruncated() {
        return super.isTitleTruncated();
    }

    public ActionBar.Tab newTab() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        Menu menu = getMenu();
        if (menu == null) {
            return false;
        }
        boolean z = true;
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
            z = false;
        }
        menu.setQwertyMode(z);
        return menu.performShortcut(i, keyEvent, 0);
    }

    public boolean onMenuKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            openOptionsMenu();
        }
        return true;
    }

    public boolean openOptionsMenu() {
        return this.a.showOverflowMenu();
    }

    public void removeAllTabs() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.remove(onMenuVisibilityListener);
    }

    public void removeTab(ActionBar.Tab tab) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void removeTabAt(int i) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public boolean requestFocus() {
        ViewGroup viewGroup = this.a.getViewGroup();
        if (viewGroup == null || viewGroup.hasFocus()) {
            return false;
        }
        viewGroup.requestFocus();
        return true;
    }

    public void selectTab(ActionBar.Tab tab) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void setBackgroundDrawable(@Nullable Drawable drawable) {
        this.a.setBackgroundDrawable(drawable);
    }

    public void setCustomView(int i) {
        setCustomView(LayoutInflater.from(this.a.getContext()).inflate(i, this.a.getViewGroup(), false));
    }

    public void setCustomView(View view) {
        setCustomView(view, new ActionBar.LayoutParams(-2, -2));
    }

    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        if (view != null) {
            view.setLayoutParams(layoutParams);
        }
        this.a.setCustomView(view);
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
    }

    public void setDisplayHomeAsUpEnabled(boolean z) {
        setDisplayOptions(z ? 4 : 0, 4);
    }

    @SuppressLint({"WrongConstant"})
    public void setDisplayOptions(int i) {
        setDisplayOptions(i, -1);
    }

    public void setDisplayOptions(int i, int i2) {
        this.a.setDisplayOptions((i & i2) | ((~i2) & this.a.getDisplayOptions()));
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

    public void setElevation(float f) {
        ViewCompat.setElevation(this.a.getViewGroup(), f);
    }

    public void setHomeActionContentDescription(int i) {
        this.a.setNavigationContentDescription(i);
    }

    public void setHomeActionContentDescription(CharSequence charSequence) {
        this.a.setNavigationContentDescription(charSequence);
    }

    public void setHomeAsUpIndicator(int i) {
        this.a.setNavigationIcon(i);
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        this.a.setNavigationIcon(drawable);
    }

    public void setHomeButtonEnabled(boolean z) {
    }

    public void setIcon(int i) {
        this.a.setIcon(i);
    }

    public void setIcon(Drawable drawable) {
        this.a.setIcon(drawable);
    }

    public void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter, ActionBar.OnNavigationListener onNavigationListener) {
        this.a.setDropdownParams(spinnerAdapter, new NavItemSelectedListener(onNavigationListener));
    }

    public void setLogo(int i) {
        this.a.setLogo(i);
    }

    public void setLogo(Drawable drawable) {
        this.a.setLogo(drawable);
    }

    public void setNavigationMode(int i) {
        if (i != 2) {
            this.a.setNavigationMode(i);
            return;
        }
        throw new IllegalArgumentException("Tabs not supported in this configuration");
    }

    public void setSelectedNavigationItem(int i) {
        if (this.a.getNavigationMode() == 1) {
            this.a.setDropdownSelectedPosition(i);
            return;
        }
        throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
    }

    public void setShowHideAnimationEnabled(boolean z) {
    }

    public void setSplitBackgroundDrawable(Drawable drawable) {
    }

    public void setStackedBackgroundDrawable(Drawable drawable) {
    }

    public void setSubtitle(int i) {
        DecorToolbar decorToolbar = this.a;
        decorToolbar.setSubtitle(i != 0 ? decorToolbar.getContext().getText(i) : null);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.a.setSubtitle(charSequence);
    }

    public void setTitle(int i) {
        DecorToolbar decorToolbar = this.a;
        decorToolbar.setTitle(i != 0 ? decorToolbar.getContext().getText(i) : null);
    }

    public void setTitle(CharSequence charSequence) {
        this.a.setTitle(charSequence);
    }

    public void setWindowTitle(CharSequence charSequence) {
        this.a.setWindowTitle(charSequence);
    }

    public void show() {
        this.a.setVisibility(0);
    }
}
