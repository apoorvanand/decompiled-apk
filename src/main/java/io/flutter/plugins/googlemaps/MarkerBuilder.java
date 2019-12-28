package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

class MarkerBuilder implements MarkerOptionsSink {
    private boolean consumeTapEvents;
    private final MarkerOptions markerOptions = new MarkerOptions();

    MarkerBuilder() {
    }

    /* access modifiers changed from: package-private */
    public MarkerOptions build() {
        return this.markerOptions;
    }

    /* access modifiers changed from: package-private */
    public boolean consumeTapEvents() {
        return this.consumeTapEvents;
    }

    public void setAlpha(float f) {
        this.markerOptions.alpha(f);
    }

    public void setAnchor(float f, float f2) {
        this.markerOptions.anchor(f, f2);
    }

    public void setConsumeTapEvents(boolean z) {
        this.consumeTapEvents = z;
    }

    public void setDraggable(boolean z) {
        this.markerOptions.draggable(z);
    }

    public void setFlat(boolean z) {
        this.markerOptions.flat(z);
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        this.markerOptions.icon(bitmapDescriptor);
    }

    public void setInfoWindowAnchor(float f, float f2) {
        this.markerOptions.infoWindowAnchor(f, f2);
    }

    public void setInfoWindowText(String str, String str2) {
        this.markerOptions.title(str);
        this.markerOptions.snippet(str2);
    }

    public void setPosition(LatLng latLng) {
        this.markerOptions.position(latLng);
    }

    public void setRotation(float f) {
        this.markerOptions.rotation(f);
    }

    public void setVisible(boolean z) {
        this.markerOptions.visible(z);
    }

    public void setZIndex(float f) {
        this.markerOptions.zIndex(f);
    }
}
