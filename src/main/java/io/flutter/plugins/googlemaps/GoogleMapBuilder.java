package io.flutter.plugins.googlemaps;

import android.content.Context;
import android.graphics.Rect;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;
import io.flutter.plugin.common.PluginRegistry;
import java.util.concurrent.atomic.AtomicInteger;

class GoogleMapBuilder implements GoogleMapOptionsSink {
    private boolean indoorEnabled = true;
    private Object initialCircles;
    private Object initialMarkers;
    private Object initialPolygons;
    private Object initialPolylines;
    private boolean myLocationButtonEnabled = false;
    private boolean myLocationEnabled = false;
    private final GoogleMapOptions options = new GoogleMapOptions();
    private Rect padding = new Rect(0, 0, 0, 0);
    private boolean trackCameraPosition = false;
    private boolean trafficEnabled = false;

    GoogleMapBuilder() {
    }

    /* access modifiers changed from: package-private */
    public GoogleMapController build(int i, Context context, AtomicInteger atomicInteger, PluginRegistry.Registrar registrar) {
        GoogleMapController googleMapController = new GoogleMapController(i, context, atomicInteger, registrar, this.options);
        googleMapController.init();
        googleMapController.setMyLocationEnabled(this.myLocationEnabled);
        googleMapController.setMyLocationButtonEnabled(this.myLocationButtonEnabled);
        googleMapController.setIndoorEnabled(this.indoorEnabled);
        googleMapController.setTrafficEnabled(this.trafficEnabled);
        googleMapController.setTrackCameraPosition(this.trackCameraPosition);
        googleMapController.setInitialMarkers(this.initialMarkers);
        googleMapController.setInitialPolygons(this.initialPolygons);
        googleMapController.setInitialPolylines(this.initialPolylines);
        googleMapController.setInitialCircles(this.initialCircles);
        googleMapController.setPadding((float) this.padding.top, (float) this.padding.left, (float) this.padding.bottom, (float) this.padding.right);
        return googleMapController;
    }

    public void setCameraTargetBounds(LatLngBounds latLngBounds) {
        this.options.latLngBoundsForCameraTarget(latLngBounds);
    }

    public void setCompassEnabled(boolean z) {
        this.options.compassEnabled(z);
    }

    public void setIndoorEnabled(boolean z) {
        this.indoorEnabled = z;
    }

    /* access modifiers changed from: package-private */
    public void setInitialCameraPosition(CameraPosition cameraPosition) {
        this.options.camera(cameraPosition);
    }

    public void setInitialCircles(Object obj) {
        this.initialCircles = obj;
    }

    public void setInitialMarkers(Object obj) {
        this.initialMarkers = obj;
    }

    public void setInitialPolygons(Object obj) {
        this.initialPolygons = obj;
    }

    public void setInitialPolylines(Object obj) {
        this.initialPolylines = obj;
    }

    public void setMapToolbarEnabled(boolean z) {
        this.options.mapToolbarEnabled(z);
    }

    public void setMapType(int i) {
        this.options.mapType(i);
    }

    public void setMinMaxZoomPreference(Float f, Float f2) {
        if (f != null) {
            this.options.minZoomPreference(f.floatValue());
        }
        if (f2 != null) {
            this.options.maxZoomPreference(f2.floatValue());
        }
    }

    public void setMyLocationButtonEnabled(boolean z) {
        this.myLocationButtonEnabled = z;
    }

    public void setMyLocationEnabled(boolean z) {
        this.myLocationEnabled = z;
    }

    public void setPadding(float f, float f2, float f3, float f4) {
        this.padding = new Rect((int) f2, (int) f, (int) f4, (int) f3);
    }

    public void setRotateGesturesEnabled(boolean z) {
        this.options.rotateGesturesEnabled(z);
    }

    public void setScrollGesturesEnabled(boolean z) {
        this.options.scrollGesturesEnabled(z);
    }

    public void setTiltGesturesEnabled(boolean z) {
        this.options.tiltGesturesEnabled(z);
    }

    public void setTrackCameraPosition(boolean z) {
        this.trackCameraPosition = z;
    }

    public void setTrafficEnabled(boolean z) {
        this.trafficEnabled = z;
    }

    public void setZoomGesturesEnabled(boolean z) {
        this.options.zoomGesturesEnabled(z);
    }
}
