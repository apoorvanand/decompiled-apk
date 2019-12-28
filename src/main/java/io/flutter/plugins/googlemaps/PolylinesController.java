package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PolylinesController {
    private final float density;
    private GoogleMap googleMap;
    private final Map<String, String> googleMapsPolylineIdToDartPolylineId = new HashMap();
    private final MethodChannel methodChannel;
    private final Map<String, PolylineController> polylineIdToController = new HashMap();

    PolylinesController(MethodChannel methodChannel2, float f) {
        this.methodChannel = methodChannel2;
        this.density = f;
    }

    private void addPolyline(Object obj) {
        if (obj != null) {
            PolylineBuilder polylineBuilder = new PolylineBuilder(this.density);
            addPolyline(Convert.interpretPolylineOptions(obj, polylineBuilder), polylineBuilder.build(), polylineBuilder.consumeTapEvents());
        }
    }

    private void addPolyline(String str, PolylineOptions polylineOptions, boolean z) {
        Polyline addPolyline = this.googleMap.addPolyline(polylineOptions);
        this.polylineIdToController.put(str, new PolylineController(addPolyline, z, this.density));
        this.googleMapsPolylineIdToDartPolylineId.put(addPolyline.getId(), str);
    }

    private void changePolyline(Object obj) {
        PolylineController polylineController;
        if (obj != null && (polylineController = this.polylineIdToController.get(getPolylineId(obj))) != null) {
            Convert.interpretPolylineOptions(obj, polylineController);
        }
    }

    private static String getPolylineId(Object obj) {
        return (String) ((Map) obj).get("polylineId");
    }

    /* access modifiers changed from: package-private */
    public void addPolylines(List<Object> list) {
        if (list != null) {
            for (Object addPolyline : list) {
                addPolyline(addPolyline);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void changePolylines(List<Object> list) {
        if (list != null) {
            for (Object changePolyline : list) {
                changePolyline(changePolyline);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onPolylineTap(String str) {
        String str2 = this.googleMapsPolylineIdToDartPolylineId.get(str);
        if (str2 == null) {
            return false;
        }
        this.methodChannel.invokeMethod("polyline#onTap", Convert.polylineIdToJson(str2));
        PolylineController polylineController = this.polylineIdToController.get(str2);
        if (polylineController != null) {
            return polylineController.consumeTapEvents();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void removePolylines(List<Object> list) {
        PolylineController remove;
        if (list != null) {
            for (Object next : list) {
                if (!(next == null || (remove = this.polylineIdToController.remove((String) next)) == null)) {
                    remove.remove();
                    this.googleMapsPolylineIdToDartPolylineId.remove(remove.getGoogleMapsPolylineId());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setGoogleMap(GoogleMap googleMap2) {
        this.googleMap = googleMap2;
    }
}
