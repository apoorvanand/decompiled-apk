package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.model.LatLngBounds;

interface GoogleMapOptionsSink {
    void setCameraTargetBounds(LatLngBounds latLngBounds);

    void setCompassEnabled(boolean z);

    void setIndoorEnabled(boolean z);

    void setInitialCircles(Object obj);

    void setInitialMarkers(Object obj);

    void setInitialPolygons(Object obj);

    void setInitialPolylines(Object obj);

    void setMapToolbarEnabled(boolean z);

    void setMapType(int i);

    void setMinMaxZoomPreference(Float f, Float f2);

    void setMyLocationButtonEnabled(boolean z);

    void setMyLocationEnabled(boolean z);

    void setPadding(float f, float f2, float f3, float f4);

    void setRotateGesturesEnabled(boolean z);

    void setScrollGesturesEnabled(boolean z);

    void setTiltGesturesEnabled(boolean z);

    void setTrackCameraPosition(boolean z);

    void setTrafficEnabled(boolean z);

    void setZoomGesturesEnabled(boolean z);
}
