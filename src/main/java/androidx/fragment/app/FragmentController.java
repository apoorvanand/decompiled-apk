package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.app.LoaderManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class FragmentController {
    private final FragmentHostCallback<?> mHost;

    private FragmentController(FragmentHostCallback<?> fragmentHostCallback) {
        this.mHost = fragmentHostCallback;
    }

    public static FragmentController createController(FragmentHostCallback<?> fragmentHostCallback) {
        return new FragmentController(fragmentHostCallback);
    }

    public void attachHost(Fragment fragment) {
        FragmentManagerImpl fragmentManagerImpl = this.mHost.b;
        FragmentHostCallback<?> fragmentHostCallback = this.mHost;
        fragmentManagerImpl.attachController(fragmentHostCallback, fragmentHostCallback, fragment);
    }

    public void dispatchActivityCreated() {
        this.mHost.b.dispatchActivityCreated();
    }

    public void dispatchConfigurationChanged(Configuration configuration) {
        this.mHost.b.dispatchConfigurationChanged(configuration);
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem) {
        return this.mHost.b.dispatchContextItemSelected(menuItem);
    }

    public void dispatchCreate() {
        this.mHost.b.dispatchCreate();
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        return this.mHost.b.dispatchCreateOptionsMenu(menu, menuInflater);
    }

    public void dispatchDestroy() {
        this.mHost.b.dispatchDestroy();
    }

    public void dispatchDestroyView() {
        this.mHost.b.dispatchDestroyView();
    }

    public void dispatchLowMemory() {
        this.mHost.b.dispatchLowMemory();
    }

    public void dispatchMultiWindowModeChanged(boolean z) {
        this.mHost.b.dispatchMultiWindowModeChanged(z);
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        return this.mHost.b.dispatchOptionsItemSelected(menuItem);
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        this.mHost.b.dispatchOptionsMenuClosed(menu);
    }

    public void dispatchPause() {
        this.mHost.b.dispatchPause();
    }

    public void dispatchPictureInPictureModeChanged(boolean z) {
        this.mHost.b.dispatchPictureInPictureModeChanged(z);
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        return this.mHost.b.dispatchPrepareOptionsMenu(menu);
    }

    @Deprecated
    public void dispatchReallyStop() {
    }

    public void dispatchResume() {
        this.mHost.b.dispatchResume();
    }

    public void dispatchStart() {
        this.mHost.b.dispatchStart();
    }

    public void dispatchStop() {
        this.mHost.b.dispatchStop();
    }

    @Deprecated
    public void doLoaderDestroy() {
    }

    @Deprecated
    public void doLoaderRetain() {
    }

    @Deprecated
    public void doLoaderStart() {
    }

    @Deprecated
    public void doLoaderStop(boolean z) {
    }

    @Deprecated
    public void dumpLoaders(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public boolean execPendingActions() {
        return this.mHost.b.execPendingActions();
    }

    @Nullable
    public Fragment findFragmentByWho(String str) {
        return this.mHost.b.findFragmentByWho(str);
    }

    public List<Fragment> getActiveFragments(List<Fragment> list) {
        return this.mHost.b.a();
    }

    public int getActiveFragmentsCount() {
        return this.mHost.b.b();
    }

    public FragmentManager getSupportFragmentManager() {
        return this.mHost.d();
    }

    @Deprecated
    public LoaderManager getSupportLoaderManager() {
        throw new UnsupportedOperationException("Loaders are managed separately from FragmentController, use LoaderManager.getInstance() to obtain a LoaderManager.");
    }

    public void noteStateNotSaved() {
        this.mHost.b.noteStateNotSaved();
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mHost.b.onCreateView(view, str, context, attributeSet);
    }

    @Deprecated
    public void reportLoaderStart() {
    }

    public void restoreAllState(Parcelable parcelable, FragmentManagerNonConfig fragmentManagerNonConfig) {
        this.mHost.b.a(parcelable, fragmentManagerNonConfig);
    }

    @Deprecated
    public void restoreAllState(Parcelable parcelable, List<Fragment> list) {
        this.mHost.b.a(parcelable, new FragmentManagerNonConfig(list, (List<FragmentManagerNonConfig>) null, (List<ViewModelStore>) null));
    }

    @Deprecated
    public void restoreLoaderNonConfig(SimpleArrayMap<String, LoaderManager> simpleArrayMap) {
    }

    @Deprecated
    public SimpleArrayMap<String, LoaderManager> retainLoaderNonConfig() {
        return null;
    }

    public FragmentManagerNonConfig retainNestedNonConfig() {
        return this.mHost.b.g();
    }

    @Deprecated
    public List<Fragment> retainNonConfig() {
        FragmentManagerNonConfig g = this.mHost.b.g();
        if (g != null) {
            return g.a();
        }
        return null;
    }

    public Parcelable saveAllState() {
        return this.mHost.b.i();
    }
}
