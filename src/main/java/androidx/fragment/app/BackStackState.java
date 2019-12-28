package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.BackStackRecord;
import java.util.ArrayList;

final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new Parcelable.Creator<BackStackState>() {
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        public BackStackState[] newArray(int i) {
            return new BackStackState[i];
        }
    };
    final int[] a;
    final int b;
    final int c;
    final String d;
    final int e;
    final int f;
    final CharSequence g;
    final int h;
    final CharSequence i;
    final ArrayList<String> j;
    final ArrayList<String> k;
    final boolean l;

    public BackStackState(Parcel parcel) {
        this.a = parcel.createIntArray();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.h = parcel.readInt();
        this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.j = parcel.createStringArrayList();
        this.k = parcel.createStringArrayList();
        this.l = parcel.readInt() != 0;
    }

    public BackStackState(BackStackRecord backStackRecord) {
        int size = backStackRecord.b.size();
        this.a = new int[(size * 6)];
        if (backStackRecord.i) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                BackStackRecord.Op op = backStackRecord.b.get(i2);
                int i4 = i3 + 1;
                this.a[i3] = op.a;
                int i5 = i4 + 1;
                this.a[i4] = op.b != null ? op.b.mIndex : -1;
                int i6 = i5 + 1;
                this.a[i5] = op.c;
                int i7 = i6 + 1;
                this.a[i6] = op.d;
                int i8 = i7 + 1;
                this.a[i7] = op.e;
                this.a[i8] = op.f;
                i2++;
                i3 = i8 + 1;
            }
            this.b = backStackRecord.g;
            this.c = backStackRecord.h;
            this.d = backStackRecord.k;
            this.e = backStackRecord.m;
            this.f = backStackRecord.n;
            this.g = backStackRecord.o;
            this.h = backStackRecord.p;
            this.i = backStackRecord.q;
            this.j = backStackRecord.r;
            this.k = backStackRecord.s;
            this.l = backStackRecord.t;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public int describeContents() {
        return 0;
    }

    public BackStackRecord instantiate(FragmentManagerImpl fragmentManagerImpl) {
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManagerImpl);
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.a.length) {
            BackStackRecord.Op op = new BackStackRecord.Op();
            int i4 = i2 + 1;
            op.a = this.a[i2];
            if (FragmentManagerImpl.a) {
                Log.v("FragmentManager", "Instantiate " + backStackRecord + " op #" + i3 + " base fragment #" + this.a[i4]);
            }
            int i5 = i4 + 1;
            int i6 = this.a[i4];
            op.b = i6 >= 0 ? fragmentManagerImpl.f.get(i6) : null;
            int[] iArr = this.a;
            int i7 = i5 + 1;
            op.c = iArr[i5];
            int i8 = i7 + 1;
            op.d = iArr[i7];
            int i9 = i8 + 1;
            op.e = iArr[i8];
            op.f = iArr[i9];
            backStackRecord.c = op.c;
            backStackRecord.d = op.d;
            backStackRecord.e = op.e;
            backStackRecord.f = op.f;
            backStackRecord.a(op);
            i3++;
            i2 = i9 + 1;
        }
        backStackRecord.g = this.b;
        backStackRecord.h = this.c;
        backStackRecord.k = this.d;
        backStackRecord.m = this.e;
        backStackRecord.i = true;
        backStackRecord.n = this.f;
        backStackRecord.o = this.g;
        backStackRecord.p = this.h;
        backStackRecord.q = this.i;
        backStackRecord.r = this.j;
        backStackRecord.s = this.k;
        backStackRecord.t = this.l;
        backStackRecord.a(1);
        return backStackRecord;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeIntArray(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        TextUtils.writeToParcel(this.g, parcel, 0);
        parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.i, parcel, 0);
        parcel.writeStringList(this.j);
        parcel.writeStringList(this.k);
        parcel.writeInt(this.l ? 1 : 0);
    }
}
