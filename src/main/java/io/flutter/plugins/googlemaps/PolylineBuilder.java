package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.List;

class PolylineBuilder implements PolylineOptionsSink {
    private boolean consumeTapEvents;
    private final float density;
    private final PolylineOptions polylineOptions = new PolylineOptions();

    PolylineBuilder(float f) {
        this.density = f;
    }

    /* access modifiers changed from: package-private */
    public PolylineOptions build() {
        return this.polylineOptions;
    }

    /* access modifiers changed from: package-private */
    public boolean consumeTapEvents() {
        return this.consumeTapEvents;
    }

    public void setColor(int i) {
        this.polylineOptions.color(i);
    }

    public void setConsumeTapEvents(boolean z) {
        this.consumeTapEvents = z;
        this.polylineOptions.clickable(z);
    }

    public void setEndCap(Cap cap) {
        this.polylineOptions.endCap(cap);
    }

    public void setGeodesic(boolean z) {
        this.polylineOptions.geodesic(z);
    }

    public void setJointType(int i) {
        this.polylineOptions.jointType(i);
    }

    public void setPattern(List<PatternItem> list) {
        this.polylineOptions.pattern(list);
    }

    public void setPoints(List<LatLng> list) {
        this.polylineOptions.addAll(list);
    }

    public void setStartCap(Cap cap) {
        this.polylineOptions.startCap(cap);
    }

    public void setVisible(boolean z) {
        this.polylineOptions.visible(z);
    }

    public void setWidth(float f) {
        this.polylineOptions.width(f * this.density);
    }

    public void setZIndex(float f) {
        this.polylineOptions.zIndex(f);
    }
}
