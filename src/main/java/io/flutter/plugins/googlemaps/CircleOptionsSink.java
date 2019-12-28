package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.model.LatLng;

interface CircleOptionsSink {
    void setCenter(LatLng latLng);

    void setConsumeTapEvents(boolean z);

    void setFillColor(int i);

    void setRadius(double d);

    void setStrokeColor(int i);

    void setStrokeWidth(float f);

    void setVisible(boolean z);

    void setZIndex(float f);
}
