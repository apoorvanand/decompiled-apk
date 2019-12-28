package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.lifecycle.ViewModelStore;

final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new Parcelable.Creator<FragmentState>() {
        public FragmentState createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        public FragmentState[] newArray(int i) {
            return new FragmentState[i];
        }
    };
    final String a;
    final int b;
    final boolean c;
    final int d;
    final int e;
    final String f;
    final boolean g;
    final boolean h;
    final Bundle i;
    final boolean j;
    Bundle k;
    Fragment l;

    FragmentState(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readInt();
        boolean z = true;
        this.c = parcel.readInt() != 0;
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readString();
        this.g = parcel.readInt() != 0;
        this.h = parcel.readInt() != 0;
        this.i = parcel.readBundle();
        this.j = parcel.readInt() == 0 ? false : z;
        this.k = parcel.readBundle();
    }

    FragmentState(Fragment fragment) {
        this.a = fragment.getClass().getName();
        this.b = fragment.mIndex;
        this.c = fragment.mFromLayout;
        this.d = fragment.mFragmentId;
        this.e = fragment.mContainerId;
        this.f = fragment.mTag;
        this.g = fragment.mRetainInstance;
        this.h = fragment.mDetached;
        this.i = fragment.mArguments;
        this.j = fragment.mHidden;
    }

    public int describeContents() {
        return 0;
    }

    public Fragment instantiate(FragmentHostCallback fragmentHostCallback, FragmentContainer fragmentContainer, Fragment fragment, FragmentManagerNonConfig fragmentManagerNonConfig, ViewModelStore viewModelStore) {
        if (this.l == null) {
            Context b2 = fragmentHostCallback.b();
            Bundle bundle = this.i;
            if (bundle != null) {
                bundle.setClassLoader(b2.getClassLoader());
            }
            this.l = fragmentContainer != null ? fragmentContainer.instantiate(b2, this.a, this.i) : Fragment.instantiate(b2, this.a, this.i);
            Bundle bundle2 = this.k;
            if (bundle2 != null) {
                bundle2.setClassLoader(b2.getClassLoader());
                this.l.mSavedFragmentState = this.k;
            }
            this.l.setIndex(this.b, fragment);
            Fragment fragment2 = this.l;
            fragment2.mFromLayout = this.c;
            fragment2.mRestored = true;
            fragment2.mFragmentId = this.d;
            fragment2.mContainerId = this.e;
            fragment2.mTag = this.f;
            fragment2.mRetainInstance = this.g;
            fragment2.mDetached = this.h;
            fragment2.mHidden = this.j;
            fragment2.mFragmentManager = fragmentHostCallback.b;
            if (FragmentManagerImpl.a) {
                Log.v("FragmentManager", "Instantiated fragment " + this.l);
            }
        }
        Fragment fragment3 = this.l;
        fragment3.mChildNonConfig = fragmentManagerNonConfig;
        fragment3.mViewModelStore = viewModelStore;
        return fragment3;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c ? 1 : 0);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        parcel.writeInt(this.g ? 1 : 0);
        parcel.writeInt(this.h ? 1 : 0);
        parcel.writeBundle(this.i);
        parcel.writeInt(this.j ? 1 : 0);
        parcel.writeBundle(this.k);
    }
}
