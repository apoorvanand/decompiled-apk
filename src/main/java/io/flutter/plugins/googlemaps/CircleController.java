package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;

class CircleController implements CircleOptionsSink {
    private final Circle circle;
    private boolean consumeTapEvents;
    private final String googleMapsCircleId;

    CircleController(Circle circle2, boolean z) {
        this.circle = circle2;
        this.consumeTapEvents = z;
        this.googleMapsCircleId = circle2.getId();
    }

    /* access modifiers changed from: package-private */
    public boolean consumeTapEvents() {
        return this.consumeTapEvents;
    }

    /* access modifiers changed from: package-private */
    public String getGoogleMapsCircleId() {
        return this.googleMapsCircleId;
    }

    /* access modifiers changed from: package-private */
    public void remove() {
        this.circle.remove();
    }

    public void setCenter(LatLng latLng) {
        this.circle.setCenter(latLng);
    }

    public void setConsumeTapEvents(boolean z) {
        this.consumeTapEvents = z;
        this.circle.setClickable(z);
    }

    public void setFillColor(int i) {
        this.circle.setFillColor(i);
    }

    public void setRadius(double d) {
        this.circle.setRadius(d);
    }

    public void setStrokeColor(int i) {
        this.circle.setStrokeColor(i);
    }

    public void setStrokeWidth(float f) {
        this.circle.setStrokeWidth(f);
    }

    public void setVisible(boolean z) {
        this.circle.setVisible(z);
    }

    public void setZIndex(float f) {
        this.circle.setZIndex(f);
    }
}
