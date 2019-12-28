package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzam;

final class zzk extends zzam {
    private final /* synthetic */ GoogleMap.OnMapLoadedCallback zzs;

    zzk(GoogleMap googleMap, GoogleMap.OnMapLoadedCallback onMapLoadedCallback) {
        this.zzs = onMapLoadedCallback;
    }

    public final void onMapLoaded() {
        this.zzs.onMapLoaded();
    }
}
