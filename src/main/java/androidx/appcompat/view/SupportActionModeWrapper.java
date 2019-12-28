package androidx.appcompat.view;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.menu.MenuWrapperFactory;
import androidx.collection.SimpleArrayMap;
import androidx.core.internal.view.SupportMenu;
import androidx.core.internal.view.SupportMenuItem;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SupportActionModeWrapper extends ActionMode {
    final Context a;
    final ActionMode b;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class CallbackWrapper implements ActionMode.Callback {
        final ActionMode.Callback a;
        final Context b;
        final ArrayList<SupportActionModeWrapper> c = new ArrayList<>();
        final SimpleArrayMap<Menu, Menu> d = new SimpleArrayMap<>();

        public CallbackWrapper(Context context, ActionMode.Callback callback) {
            this.b = context;
            this.a = callback;
        }

        private Menu getMenuWrapper(Menu menu) {
            Menu menu2 = this.d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            Menu wrapSupportMenu = MenuWrapperFactory.wrapSupportMenu(this.b, (SupportMenu) menu);
            this.d.put(menu, wrapSupportMenu);
            return wrapSupportMenu;
        }

        public android.view.ActionMode getActionModeWrapper(ActionMode actionMode) {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                SupportActionModeWrapper supportActionModeWrapper = this.c.get(i);
                if (supportActionModeWrapper != null && supportActionModeWrapper.b == actionMode) {
                    return supportActionModeWrapper;
                }
            }
            SupportActionModeWrapper supportActionModeWrapper2 = new SupportActionModeWrapper(this.b, actionMode);
            this.c.add(supportActionModeWrapper2);
            return supportActionModeWrapper2;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.a.onActionItemClicked(getActionModeWrapper(actionMode), MenuWrapperFactory.wrapSupportMenuItem(this.b, (SupportMenuItem) menuItem));
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.a.onCreateActionMode(getActionModeWrapper(actionMode), getMenuWrapper(menu));
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.a.onDestroyActionMode(getActionModeWrapper(actionMode));
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.a.onPrepareActionMode(getActionModeWrapper(actionMode), getMenuWrapper(menu));
        }
    }

    public SupportActionModeWrapper(Context context, ActionMode actionMode) {
        this.a = context;
        this.b = actionMode;
    }

    public void finish() {
        this.b.finish();
    }

    public View getCustomView() {
        return this.b.getCustomView();
    }

    public Menu getMenu() {
        return MenuWrapperFactory.wrapSupportMenu(this.a, (SupportMenu) this.b.getMenu());
    }

    public MenuInflater getMenuInflater() {
        return this.b.getMenuInflater();
    }

    public CharSequence getSubtitle() {
        return this.b.getSubtitle();
    }

    public Object getTag() {
        return this.b.getTag();
    }

    public CharSequence getTitle() {
        return this.b.getTitle();
    }

    public boolean getTitleOptionalHint() {
        return this.b.getTitleOptionalHint();
    }

    public void invalidate() {
        this.b.invalidate();
    }

    public boolean isTitleOptional() {
        return this.b.isTitleOptional();
    }

    public void setCustomView(View view) {
        this.b.setCustomView(view);
    }

    public void setSubtitle(int i) {
        this.b.setSubtitle(i);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.b.setSubtitle(charSequence);
    }

    public void setTag(Object obj) {
        this.b.setTag(obj);
    }

    public void setTitle(int i) {
        this.b.setTitle(i);
    }

    public void setTitle(CharSequence charSequence) {
        this.b.setTitle(charSequence);
    }

    public void setTitleOptionalHint(boolean z) {
        this.b.setTitleOptionalHint(z);
    }
}
