package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.List;

class PolygonBuilder implements PolygonOptionsSink {
    private boolean consumeTapEvents;
    private final PolygonOptions polygonOptions = new PolygonOptions();

    PolygonBuilder() {
    }

    /* access modifiers changed from: package-private */
    public PolygonOptions build() {
        return this.polygonOptions;
    }

    /* access modifiers changed from: package-private */
    public boolean consumeTapEvents() {
        return this.consumeTapEvents;
    }

    public void setConsumeTapEvents(boolean z) {
        this.consumeTapEvents = z;
        this.polygonOptions.clickable(z);
    }

    public void setFillColor(int i) {
        this.polygonOptions.fillColor(i);
    }

    public void setGeodesic(boolean z) {
        this.polygonOptions.geodesic(z);
    }

    public void setPoints(List<LatLng> list) {
        this.polygonOptions.addAll(list);
    }

    public void setStrokeColor(int i) {
        this.polygonOptions.strokeColor(i);
    }

    public void setStrokeWidth(float f) {
        this.polygonOptions.strokeWidth(f);
    }

    public void setVisible(boolean z) {
        this.polygonOptions.visible(z);
    }

    public void setZIndex(float f) {
        this.polygonOptions.zIndex(f);
    }
}
