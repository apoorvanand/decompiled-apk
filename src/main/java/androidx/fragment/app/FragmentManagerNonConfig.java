package androidx.fragment.app;

import androidx.lifecycle.ViewModelStore;
import java.util.List;

public class FragmentManagerNonConfig {
    private final List<FragmentManagerNonConfig> mChildNonConfigs;
    private final List<Fragment> mFragments;
    private final List<ViewModelStore> mViewModelStores;

    FragmentManagerNonConfig(List<Fragment> list, List<FragmentManagerNonConfig> list2, List<ViewModelStore> list3) {
        this.mFragments = list;
        this.mChildNonConfigs = list2;
        this.mViewModelStores = list3;
    }

    /* access modifiers changed from: package-private */
    public List<Fragment> a() {
        return this.mFragments;
    }

    /* access modifiers changed from: package-private */
    public List<FragmentManagerNonConfig> b() {
        return this.mChildNonConfigs;
    }

    /* access modifiers changed from: package-private */
    public List<ViewModelStore> c() {
        return this.mViewModelStores;
    }
}
