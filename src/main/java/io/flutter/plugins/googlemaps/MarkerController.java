package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

class MarkerController implements MarkerOptionsSink {
    private boolean consumeTapEvents;
    private final String googleMapsMarkerId;
    private final Marker marker;

    MarkerController(Marker marker2, boolean z) {
        this.marker = marker2;
        this.consumeTapEvents = z;
        this.googleMapsMarkerId = marker2.getId();
    }

    /* access modifiers changed from: package-private */
    public boolean consumeTapEvents() {
        return this.consumeTapEvents;
    }

    /* access modifiers changed from: package-private */
    public String getGoogleMapsMarkerId() {
        return this.googleMapsMarkerId;
    }

    /* access modifiers changed from: package-private */
    public void remove() {
        this.marker.remove();
    }

    public void setAlpha(float f) {
        this.marker.setAlpha(f);
    }

    public void setAnchor(float f, float f2) {
        this.marker.setAnchor(f, f2);
    }

    public void setConsumeTapEvents(boolean z) {
        this.consumeTapEvents = z;
    }

    public void setDraggable(boolean z) {
        this.marker.setDraggable(z);
    }

    public void setFlat(boolean z) {
        this.marker.setFlat(z);
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        this.marker.setIcon(bitmapDescriptor);
    }

    public void setInfoWindowAnchor(float f, float f2) {
        this.marker.setInfoWindowAnchor(f, f2);
    }

    public void setInfoWindowText(String str, String str2) {
        this.marker.setTitle(str);
        this.marker.setSnippet(str2);
    }

    public void setPosition(LatLng latLng) {
        this.marker.setPosition(latLng);
    }

    public void setRotation(float f) {
        this.marker.setRotation(f);
    }

    public void setVisible(boolean z) {
        this.marker.setVisible(z);
    }

    public void setZIndex(float f) {
        this.marker.setZIndex(f);
    }
}
