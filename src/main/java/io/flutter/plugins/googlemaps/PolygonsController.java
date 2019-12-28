package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PolygonsController {
    private GoogleMap googleMap;
    private final Map<String, String> googleMapsPolygonIdToDartPolygonId = new HashMap();
    private final MethodChannel methodChannel;
    private final Map<String, PolygonController> polygonIdToController = new HashMap();

    PolygonsController(MethodChannel methodChannel2) {
        this.methodChannel = methodChannel2;
    }

    private void addPolygon(Object obj) {
        if (obj != null) {
            PolygonBuilder polygonBuilder = new PolygonBuilder();
            addPolygon(Convert.interpretPolygonOptions(obj, polygonBuilder), polygonBuilder.build(), polygonBuilder.consumeTapEvents());
        }
    }

    private void addPolygon(String str, PolygonOptions polygonOptions, boolean z) {
        Polygon addPolygon = this.googleMap.addPolygon(polygonOptions);
        this.polygonIdToController.put(str, new PolygonController(addPolygon, z));
        this.googleMapsPolygonIdToDartPolygonId.put(addPolygon.getId(), str);
    }

    private void changePolygon(Object obj) {
        PolygonController polygonController;
        if (obj != null && (polygonController = this.polygonIdToController.get(getPolygonId(obj))) != null) {
            Convert.interpretPolygonOptions(obj, polygonController);
        }
    }

    private static String getPolygonId(Object obj) {
        return (String) ((Map) obj).get("polygonId");
    }

    /* access modifiers changed from: package-private */
    public void addPolygons(List<Object> list) {
        if (list != null) {
            for (Object addPolygon : list) {
                addPolygon(addPolygon);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void changePolygons(List<Object> list) {
        if (list != null) {
            for (Object changePolygon : list) {
                changePolygon(changePolygon);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onPolygonTap(String str) {
        String str2 = this.googleMapsPolygonIdToDartPolygonId.get(str);
        if (str2 == null) {
            return false;
        }
        this.methodChannel.invokeMethod("polygon#onTap", Convert.polygonIdToJson(str2));
        PolygonController polygonController = this.polygonIdToController.get(str2);
        if (polygonController != null) {
            return polygonController.consumeTapEvents();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void removePolygons(List<Object> list) {
        PolygonController remove;
        if (list != null) {
            for (Object next : list) {
                if (!(next == null || (remove = this.polygonIdToController.remove((String) next)) == null)) {
                    remove.remove();
                    this.googleMapsPolygonIdToDartPolygonId.remove(remove.getGoogleMapsPolygonId());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setGoogleMap(GoogleMap googleMap2) {
        this.googleMap = googleMap2;
    }
}
