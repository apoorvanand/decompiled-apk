package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import java.util.List;

class PolylineController implements PolylineOptionsSink {
    private boolean consumeTapEvents;
    private final float density;
    private final String googleMapsPolylineId;
    private final Polyline polyline;

    PolylineController(Polyline polyline2, boolean z, float f) {
        this.polyline = polyline2;
        this.consumeTapEvents = z;
        this.density = f;
        this.googleMapsPolylineId = polyline2.getId();
    }

    /* access modifiers changed from: package-private */
    public boolean consumeTapEvents() {
        return this.consumeTapEvents;
    }

    /* access modifiers changed from: package-private */
    public String getGoogleMapsPolylineId() {
        return this.googleMapsPolylineId;
    }

    /* access modifiers changed from: package-private */
    public void remove() {
        this.polyline.remove();
    }

    public void setColor(int i) {
        this.polyline.setColor(i);
    }

    public void setConsumeTapEvents(boolean z) {
        this.consumeTapEvents = z;
        this.polyline.setClickable(z);
    }

    public void setEndCap(Cap cap) {
        this.polyline.setEndCap(cap);
    }

    public void setGeodesic(boolean z) {
        this.polyline.setGeodesic(z);
    }

    public void setJointType(int i) {
        this.polyline.setJointType(i);
    }

    public void setPattern(List<PatternItem> list) {
        this.polyline.setPattern(list);
    }

    public void setPoints(List<LatLng> list) {
        this.polyline.setPoints(list);
    }

    public void setStartCap(Cap cap) {
        this.polyline.setStartCap(cap);
    }

    public void setVisible(boolean z) {
        this.polyline.setVisible(z);
    }

    public void setWidth(float f) {
        this.polyline.setWidth(f * this.density);
    }

    public void setZIndex(float f) {
        this.polyline.setZIndex(f);
    }
}
