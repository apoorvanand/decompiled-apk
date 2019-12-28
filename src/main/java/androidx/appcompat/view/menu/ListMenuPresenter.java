package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ListMenuPresenter implements AdapterView.OnItemClickListener, MenuPresenter {
    private static final String TAG = "ListMenuPresenter";
    public static final String VIEWS_TAG = "android:menu:list";
    Context a;
    LayoutInflater b;
    MenuBuilder c;
    ExpandedMenuView d;
    int e;
    int f;
    int g;
    MenuAdapter h;
    private MenuPresenter.Callback mCallback;
    private int mId;

    private class MenuAdapter extends BaseAdapter {
        private int mExpandedIndex = -1;

        public MenuAdapter() {
            a();
        }

        /* access modifiers changed from: package-private */
        public void a() {
            MenuItemImpl expandedItem = ListMenuPresenter.this.c.getExpandedItem();
            if (expandedItem != null) {
                ArrayList<MenuItemImpl> nonActionItems = ListMenuPresenter.this.c.getNonActionItems();
                int size = nonActionItems.size();
                for (int i = 0; i < size; i++) {
                    if (nonActionItems.get(i) == expandedItem) {
                        this.mExpandedIndex = i;
                        return;
                    }
                }
            }
            this.mExpandedIndex = -1;
        }

        public int getCount() {
            int size = ListMenuPresenter.this.c.getNonActionItems().size() - ListMenuPresenter.this.e;
            return this.mExpandedIndex < 0 ? size : size - 1;
        }

        public MenuItemImpl getItem(int i) {
            ArrayList<MenuItemImpl> nonActionItems = ListMenuPresenter.this.c.getNonActionItems();
            int i2 = i + ListMenuPresenter.this.e;
            int i3 = this.mExpandedIndex;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return nonActionItems.get(i2);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ListMenuPresenter.this.b.inflate(ListMenuPresenter.this.g, viewGroup, false);
            }
            ((MenuView.ItemView) view).initialize(getItem(i), 0);
            return view;
        }

        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public ListMenuPresenter(int i, int i2) {
        this.g = i;
        this.f = i2;
    }

    public ListMenuPresenter(Context context, int i) {
        this(i, 0);
        this.a = context;
        this.b = LayoutInflater.from(this.a);
    }

    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean flagActionItems() {
        return false;
    }

    public ListAdapter getAdapter() {
        if (this.h == null) {
            this.h = new MenuAdapter();
        }
        return this.h;
    }

    public int getId() {
        return this.mId;
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        if (this.d == null) {
            this.d = (ExpandedMenuView) this.b.inflate(R.layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.h == null) {
                this.h = new MenuAdapter();
            }
            this.d.setAdapter(this.h);
            this.d.setOnItemClickListener(this);
        }
        return this.d;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        if (r2.b == null) goto L_0x000b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initForMenu(android.content.Context r3, androidx.appcompat.view.menu.MenuBuilder r4) {
        /*
            r2 = this;
            int r0 = r2.f
            if (r0 == 0) goto L_0x0014
            android.view.ContextThemeWrapper r1 = new android.view.ContextThemeWrapper
            r1.<init>(r3, r0)
            r2.a = r1
        L_0x000b:
            android.content.Context r3 = r2.a
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r3)
            r2.b = r3
            goto L_0x001f
        L_0x0014:
            android.content.Context r0 = r2.a
            if (r0 == 0) goto L_0x001f
            r2.a = r3
            android.view.LayoutInflater r3 = r2.b
            if (r3 != 0) goto L_0x001f
            goto L_0x000b
        L_0x001f:
            r2.c = r4
            androidx.appcompat.view.menu.ListMenuPresenter$MenuAdapter r3 = r2.h
            if (r3 == 0) goto L_0x0028
            r3.notifyDataSetChanged()
        L_0x0028:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.ListMenuPresenter.initForMenu(android.content.Context, androidx.appcompat.view.menu.MenuBuilder):void");
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        MenuPresenter.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.c.performItemAction(this.h.getItem(i), this, 0);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        restoreHierarchyState((Bundle) parcelable);
    }

    public Parcelable onSaveInstanceState() {
        if (this.d == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        saveHierarchyState(bundle);
        return bundle;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        new MenuDialogHelper(subMenuBuilder).show((IBinder) null);
        MenuPresenter.Callback callback = this.mCallback;
        if (callback == null) {
            return true;
        }
        callback.onOpenSubMenu(subMenuBuilder);
        return true;
    }

    public void restoreHierarchyState(Bundle bundle) {
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(VIEWS_TAG);
        if (sparseParcelableArray != null) {
            this.d.restoreHierarchyState(sparseParcelableArray);
        }
    }

    public void saveHierarchyState(Bundle bundle) {
        SparseArray sparseArray = new SparseArray();
        ExpandedMenuView expandedMenuView = this.d;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray(VIEWS_TAG, sparseArray);
    }

    public void setCallback(MenuPresenter.Callback callback) {
        this.mCallback = callback;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public void setItemIndexOffset(int i) {
        this.e = i;
        if (this.d != null) {
            updateMenuView(false);
        }
    }

    public void updateMenuView(boolean z) {
        MenuAdapter menuAdapter = this.h;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }
}
