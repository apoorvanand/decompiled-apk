package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;

interface PolylineOptionsSink {
    void setColor(int i);

    void setConsumeTapEvents(boolean z);

    void setEndCap(Cap cap);

    void setGeodesic(boolean z);

    void setJointType(int i);

    void setPattern(List<PatternItem> list);

    void setPoints(List<LatLng> list);

    void setStartCap(Cap cap);

    void setVisible(boolean z);

    void setWidth(float f);

    void setZIndex(float f);
}
