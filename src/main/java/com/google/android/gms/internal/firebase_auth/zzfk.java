package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "StringListCreator")
public final class zzfk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfk> CREATOR = new zzfn();
    @SafeParcelable.VersionField(id = 1)
    private final int versionCode;
    @SafeParcelable.Field(getter = "getValues", id = 2)
    private List<String> zzsm;

    public zzfk() {
        this((List<String>) null);
    }

    @SafeParcelable.Constructor
    zzfk(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) List<String> list) {
        List<String> emptyList;
        this.versionCode = i;
        if (list == null || list.isEmpty()) {
            emptyList = Collections.emptyList();
        } else {
            for (int i2 = 0; i2 < list.size(); i2++) {
                list.set(i2, Strings.emptyToNull(list.get(i2)));
            }
            emptyList = Collections.unmodifiableList(list);
        }
        this.zzsm = emptyList;
    }

    private zzfk(@Nullable List<String> list) {
        this.versionCode = 1;
        this.zzsm = new ArrayList();
        if (list != null && !list.isEmpty()) {
            this.zzsm.addAll(list);
        }
    }

    public static zzfk zza(zzfk zzfk) {
        return new zzfk(zzfk != null ? zzfk.zzsm : null);
    }

    public static zzfk zzfa() {
        return new zzfk((List<String>) null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeStringList(parcel, 2, this.zzsm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List<String> zzez() {
        return this.zzsm;
    }
}
