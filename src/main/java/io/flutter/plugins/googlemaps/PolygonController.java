package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import java.util.List;

class PolygonController implements PolygonOptionsSink {
    private boolean consumeTapEvents;
    private final String googleMapsPolygonId;
    private final Polygon polygon;

    PolygonController(Polygon polygon2, boolean z) {
        this.polygon = polygon2;
        this.consumeTapEvents = z;
        this.googleMapsPolygonId = polygon2.getId();
    }

    /* access modifiers changed from: package-private */
    public boolean consumeTapEvents() {
        return this.consumeTapEvents;
    }

    /* access modifiers changed from: package-private */
    public String getGoogleMapsPolygonId() {
        return this.googleMapsPolygonId;
    }

    /* access modifiers changed from: package-private */
    public void remove() {
        this.polygon.remove();
    }

    public void setConsumeTapEvents(boolean z) {
        this.consumeTapEvents = z;
        this.polygon.setClickable(z);
    }

    public void setFillColor(int i) {
        this.polygon.setFillColor(i);
    }

    public void setGeodesic(boolean z) {
        this.polygon.setGeodesic(z);
    }

    public void setPoints(List<LatLng> list) {
        this.polygon.setPoints(list);
    }

    public void setStrokeColor(int i) {
        this.polygon.setStrokeColor(i);
    }

    public void setStrokeWidth(float f) {
        this.polygon.setStrokeWidth(f);
    }

    public void setVisible(boolean z) {
        this.polygon.setVisible(z);
    }

    public void setZIndex(float f) {
        this.polygon.setZIndex(f);
    }
}
