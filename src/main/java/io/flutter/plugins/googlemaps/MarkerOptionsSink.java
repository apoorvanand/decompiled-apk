package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;

interface MarkerOptionsSink {
    void setAlpha(float f);

    void setAnchor(float f, float f2);

    void setConsumeTapEvents(boolean z);

    void setDraggable(boolean z);

    void setFlat(boolean z);

    void setIcon(BitmapDescriptor bitmapDescriptor);

    void setInfoWindowAnchor(float f, float f2);

    void setInfoWindowText(String str, String str2);

    void setPosition(LatLng latLng);

    void setRotation(float f);

    void setVisible(boolean z);

    void setZIndex(float f);
}
