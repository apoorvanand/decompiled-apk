package com.google.android.gms.maps;

import android.location.Location;
import androidx.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzba;

final class zzj extends zzba {
    private final /* synthetic */ GoogleMap.OnMyLocationClickListener zzr;

    zzj(GoogleMap googleMap, GoogleMap.OnMyLocationClickListener onMyLocationClickListener) {
        this.zzr = onMyLocationClickListener;
    }

    public final void onMyLocationClick(@NonNull Location location) {
        this.zzr.onMyLocationClick(location);
    }
}
