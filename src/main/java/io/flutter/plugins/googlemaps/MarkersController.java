package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MarkersController {
    private GoogleMap googleMap;
    private final Map<String, String> googleMapsMarkerIdToDartMarkerId = new HashMap();
    private final Map<String, MarkerController> markerIdToController = new HashMap();
    private final MethodChannel methodChannel;

    MarkersController(MethodChannel methodChannel2) {
        this.methodChannel = methodChannel2;
    }

    private void addMarker(Object obj) {
        if (obj != null) {
            MarkerBuilder markerBuilder = new MarkerBuilder();
            addMarker(Convert.interpretMarkerOptions(obj, markerBuilder), markerBuilder.build(), markerBuilder.consumeTapEvents());
        }
    }

    private void addMarker(String str, MarkerOptions markerOptions, boolean z) {
        Marker addMarker = this.googleMap.addMarker(markerOptions);
        this.markerIdToController.put(str, new MarkerController(addMarker, z));
        this.googleMapsMarkerIdToDartMarkerId.put(addMarker.getId(), str);
    }

    private void changeMarker(Object obj) {
        MarkerController markerController;
        if (obj != null && (markerController = this.markerIdToController.get(getMarkerId(obj))) != null) {
            Convert.interpretMarkerOptions(obj, markerController);
        }
    }

    private static String getMarkerId(Object obj) {
        return (String) ((Map) obj).get("markerId");
    }

    /* access modifiers changed from: package-private */
    public void addMarkers(List<Object> list) {
        if (list != null) {
            for (Object addMarker : list) {
                addMarker(addMarker);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void changeMarkers(List<Object> list) {
        if (list != null) {
            for (Object changeMarker : list) {
                changeMarker(changeMarker);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onInfoWindowTap(String str) {
        String str2 = this.googleMapsMarkerIdToDartMarkerId.get(str);
        if (str2 != null) {
            this.methodChannel.invokeMethod("infoWindow#onTap", Convert.markerIdToJson(str2));
        }
    }

    /* access modifiers changed from: package-private */
    public void onMarkerDragEnd(String str, LatLng latLng) {
        String str2 = this.googleMapsMarkerIdToDartMarkerId.get(str);
        if (str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("markerId", str2);
            hashMap.put("position", Convert.latLngToJson(latLng));
            this.methodChannel.invokeMethod("marker#onDragEnd", hashMap);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onMarkerTap(String str) {
        String str2 = this.googleMapsMarkerIdToDartMarkerId.get(str);
        if (str2 == null) {
            return false;
        }
        this.methodChannel.invokeMethod("marker#onTap", Convert.markerIdToJson(str2));
        MarkerController markerController = this.markerIdToController.get(str2);
        if (markerController != null) {
            return markerController.consumeTapEvents();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void removeMarkers(List<Object> list) {
        MarkerController remove;
        if (list != null) {
            for (Object next : list) {
                if (!(next == null || (remove = this.markerIdToController.remove((String) next)) == null)) {
                    remove.remove();
                    this.googleMapsMarkerIdToDartMarkerId.remove(remove.getGoogleMapsMarkerId());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setGoogleMap(GoogleMap googleMap2) {
        this.googleMap = googleMap2;
    }
}
