package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.maps.model.Tile;

public final class zzah extends zza implements zzaf {
    zzah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    public final Tile getTile(int i, int i2, int i3) {
        Parcel a = a();
        a.writeInt(i);
        a.writeInt(i2);
        a.writeInt(i3);
        Parcel a2 = a(1, a);
        Tile tile = (Tile) zzc.zza(a2, Tile.CREATOR);
        a2.recycle();
        return tile;
    }
}
