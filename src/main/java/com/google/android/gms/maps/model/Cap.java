package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

@SafeParcelable.Class(creator = "CapCreator")
@SafeParcelable.Reserved({1})
public class Cap extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Cap> CREATOR = new zzb();
    private static final String TAG = "Cap";
    @SafeParcelable.Field(getter = "getWrappedBitmapDescriptorImplBinder", id = 3, type = "android.os.IBinder")
    @Nullable
    private final BitmapDescriptor bitmapDescriptor;
    @SafeParcelable.Field(getter = "getType", id = 2)
    private final int type;
    @SafeParcelable.Field(getter = "getBitmapRefWidth", id = 4)
    @Nullable
    private final Float zzcn;

    protected Cap(int i) {
        this(i, (BitmapDescriptor) null, (Float) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @SafeParcelable.Constructor
    Cap(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) @Nullable IBinder iBinder, @SafeParcelable.Param(id = 4) @Nullable Float f) {
        this(i, iBinder == null ? null : new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder)), f);
    }

    private Cap(int i, @Nullable BitmapDescriptor bitmapDescriptor2, @Nullable Float f) {
        Preconditions.checkArgument(i != 3 || (bitmapDescriptor2 != null && (f != null && (f.floatValue() > 0.0f ? 1 : (f.floatValue() == 0.0f ? 0 : -1)) > 0)), String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", new Object[]{Integer.valueOf(i), bitmapDescriptor2, f}));
        this.type = i;
        this.bitmapDescriptor = bitmapDescriptor2;
        this.zzcn = f;
    }

    protected Cap(@NonNull BitmapDescriptor bitmapDescriptor2, float f) {
        this(3, bitmapDescriptor2, Float.valueOf(f));
    }

    /* access modifiers changed from: package-private */
    public final Cap a() {
        int i = this.type;
        switch (i) {
            case 0:
                return new ButtCap();
            case 1:
                return new SquareCap();
            case 2:
                return new RoundCap();
            case 3:
                return new CustomCap(this.bitmapDescriptor, this.zzcn.floatValue());
            default:
                String str = TAG;
                StringBuilder sb = new StringBuilder(29);
                sb.append("Unknown Cap type: ");
                sb.append(i);
                Log.w(str, sb.toString());
                return this;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cap)) {
            return false;
        }
        Cap cap = (Cap) obj;
        return this.type == cap.type && Objects.equal(this.bitmapDescriptor, cap.bitmapDescriptor) && Objects.equal(this.zzcn, cap.zzcn);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type), this.bitmapDescriptor, this.zzcn);
    }

    public String toString() {
        int i = this.type;
        StringBuilder sb = new StringBuilder(23);
        sb.append("[Cap: type=");
        sb.append(i);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        BitmapDescriptor bitmapDescriptor2 = this.bitmapDescriptor;
        SafeParcelWriter.writeIBinder(parcel, 3, bitmapDescriptor2 == null ? null : bitmapDescriptor2.zzb().asBinder(), false);
        SafeParcelWriter.writeFloatObject(parcel, 4, this.zzcn, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
