package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListFragment extends Fragment {
    ListAdapter a;
    ListView b;
    View c;
    TextView d;
    View e;
    View f;
    CharSequence g;
    boolean h;
    private final Handler mHandler = new Handler();
    private final AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ListFragment.this.onListItemClick((ListView) adapterView, view, i, j);
        }
    };
    private final Runnable mRequestFocus = new Runnable() {
        public void run() {
            ListFragment.this.b.focusableViewAvailable(ListFragment.this.b);
        }
    };

    private void ensureList() {
        if (this.b == null) {
            View view = getView();
            if (view != null) {
                if (view instanceof ListView) {
                    this.b = (ListView) view;
                } else {
                    this.d = (TextView) view.findViewById(16711681);
                    TextView textView = this.d;
                    if (textView == null) {
                        this.c = view.findViewById(16908292);
                    } else {
                        textView.setVisibility(8);
                    }
                    this.e = view.findViewById(16711682);
                    this.f = view.findViewById(16711683);
                    View findViewById = view.findViewById(16908298);
                    if (findViewById instanceof ListView) {
                        this.b = (ListView) findViewById;
                        View view2 = this.c;
                        if (view2 != null) {
                            this.b.setEmptyView(view2);
                        } else {
                            CharSequence charSequence = this.g;
                            if (charSequence != null) {
                                this.d.setText(charSequence);
                                this.b.setEmptyView(this.d);
                            }
                        }
                    } else if (findViewById == null) {
                        throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
                    } else {
                        throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
                    }
                }
                this.h = true;
                this.b.setOnItemClickListener(this.mOnClickListener);
                ListAdapter listAdapter = this.a;
                if (listAdapter != null) {
                    this.a = null;
                    setListAdapter(listAdapter);
                } else if (this.e != null) {
                    setListShown(false, false);
                }
                this.mHandler.post(this.mRequestFocus);
                return;
            }
            throw new IllegalStateException("Content view not yet created");
        }
    }

    private void setListShown(boolean z, boolean z2) {
        ensureList();
        View view = this.e;
        if (view == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        } else if (this.h != z) {
            this.h = z;
            if (z) {
                if (z2) {
                    view.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
                    this.f.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
                } else {
                    view.clearAnimation();
                    this.f.clearAnimation();
                }
                this.e.setVisibility(8);
                this.f.setVisibility(0);
                return;
            }
            if (z2) {
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
                this.f.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
            } else {
                view.clearAnimation();
                this.f.clearAnimation();
            }
            this.e.setVisibility(0);
            this.f.setVisibility(8);
        }
    }

    public ListAdapter getListAdapter() {
        return this.a;
    }

    public ListView getListView() {
        ensureList();
        return this.b;
    }

    public long getSelectedItemId() {
        ensureList();
        return this.b.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        ensureList();
        return this.b.getSelectedItemPosition();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context context = getContext();
        FrameLayout frameLayout = new FrameLayout(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setId(16711682);
        linearLayout.setOrientation(1);
        linearLayout.setVisibility(8);
        linearLayout.setGravity(17);
        linearLayout.addView(new ProgressBar(context, (AttributeSet) null, 16842874), new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout frameLayout2 = new FrameLayout(context);
        frameLayout2.setId(16711683);
        TextView textView = new TextView(context);
        textView.setId(16711681);
        textView.setGravity(17);
        frameLayout2.addView(textView, new FrameLayout.LayoutParams(-1, -1));
        ListView listView = new ListView(context);
        listView.setId(16908298);
        listView.setDrawSelectorOnTop(false);
        frameLayout2.addView(listView, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(frameLayout2, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return frameLayout;
    }

    public void onDestroyView() {
        this.mHandler.removeCallbacks(this.mRequestFocus);
        this.b = null;
        this.h = false;
        this.f = null;
        this.e = null;
        this.c = null;
        this.d = null;
        super.onDestroyView();
    }

    public void onListItemClick(ListView listView, View view, int i, long j) {
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ensureList();
    }

    public void setEmptyText(CharSequence charSequence) {
        ensureList();
        TextView textView = this.d;
        if (textView != null) {
            textView.setText(charSequence);
            if (this.g == null) {
                this.b.setEmptyView(this.d);
            }
            this.g = charSequence;
            return;
        }
        throw new IllegalStateException("Can't be used with a custom content view");
    }

    public void setListAdapter(ListAdapter listAdapter) {
        boolean z = false;
        boolean z2 = this.a != null;
        this.a = listAdapter;
        ListView listView = this.b;
        if (listView != null) {
            listView.setAdapter(listAdapter);
            if (!this.h && !z2) {
                if (getView().getWindowToken() != null) {
                    z = true;
                }
                setListShown(true, z);
            }
        }
    }

    public void setListShown(boolean z) {
        setListShown(z, true);
    }

    public void setListShownNoAnimation(boolean z) {
        setListShown(z, false);
    }

    public void setSelection(int i) {
        ensureList();
        this.b.setSelection(i);
    }
}
