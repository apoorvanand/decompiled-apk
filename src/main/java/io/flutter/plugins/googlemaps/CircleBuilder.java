package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

class CircleBuilder implements CircleOptionsSink {
    private final CircleOptions circleOptions = new CircleOptions();
    private boolean consumeTapEvents;

    CircleBuilder() {
    }

    /* access modifiers changed from: package-private */
    public CircleOptions build() {
        return this.circleOptions;
    }

    /* access modifiers changed from: package-private */
    public boolean consumeTapEvents() {
        return this.consumeTapEvents;
    }

    public void setCenter(LatLng latLng) {
        this.circleOptions.center(latLng);
    }

    public void setConsumeTapEvents(boolean z) {
        this.consumeTapEvents = z;
        this.circleOptions.clickable(z);
    }

    public void setFillColor(int i) {
        this.circleOptions.fillColor(i);
    }

    public void setRadius(double d) {
        this.circleOptions.radius(d);
    }

    public void setStrokeColor(int i) {
        this.circleOptions.strokeColor(i);
    }

    public void setStrokeWidth(float f) {
        this.circleOptions.strokeWidth(f);
    }

    public void setVisible(boolean z) {
        this.circleOptions.visible(z);
    }

    public void setZIndex(float f) {
        this.circleOptions.zIndex(f);
    }
}
