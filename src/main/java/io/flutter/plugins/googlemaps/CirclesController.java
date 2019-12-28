package io.flutter.plugins.googlemaps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CirclesController {
    private final Map<String, CircleController> circleIdToController = new HashMap();
    private GoogleMap googleMap;
    private final Map<String, String> googleMapsCircleIdToDartCircleId = new HashMap();
    private final MethodChannel methodChannel;

    CirclesController(MethodChannel methodChannel2) {
        this.methodChannel = methodChannel2;
    }

    private void addCircle(Object obj) {
        if (obj != null) {
            CircleBuilder circleBuilder = new CircleBuilder();
            addCircle(Convert.interpretCircleOptions(obj, circleBuilder), circleBuilder.build(), circleBuilder.consumeTapEvents());
        }
    }

    private void addCircle(String str, CircleOptions circleOptions, boolean z) {
        Circle addCircle = this.googleMap.addCircle(circleOptions);
        this.circleIdToController.put(str, new CircleController(addCircle, z));
        this.googleMapsCircleIdToDartCircleId.put(addCircle.getId(), str);
    }

    private void changeCircle(Object obj) {
        CircleController circleController;
        if (obj != null && (circleController = this.circleIdToController.get(getCircleId(obj))) != null) {
            Convert.interpretCircleOptions(obj, circleController);
        }
    }

    private static String getCircleId(Object obj) {
        return (String) ((Map) obj).get("circleId");
    }

    /* access modifiers changed from: package-private */
    public void addCircles(List<Object> list) {
        if (list != null) {
            for (Object addCircle : list) {
                addCircle(addCircle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void changeCircles(List<Object> list) {
        if (list != null) {
            for (Object changeCircle : list) {
                changeCircle(changeCircle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onCircleTap(String str) {
        String str2 = this.googleMapsCircleIdToDartCircleId.get(str);
        if (str2 == null) {
            return false;
        }
        this.methodChannel.invokeMethod("circle#onTap", Convert.circleIdToJson(str2));
        CircleController circleController = this.circleIdToController.get(str2);
        if (circleController != null) {
            return circleController.consumeTapEvents();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void removeCircles(List<Object> list) {
        CircleController remove;
        if (list != null) {
            for (Object next : list) {
                if (!(next == null || (remove = this.circleIdToController.remove((String) next)) == null)) {
                    remove.remove();
                    this.googleMapsCircleIdToDartCircleId.remove(remove.getGoogleMapsCircleId());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setGoogleMap(GoogleMap googleMap2) {
        this.googleMap = googleMap2;
    }
}
