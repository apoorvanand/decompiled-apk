package io.flutter.plugins.googlemaps;

import android.content.Context;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class GoogleMapFactory extends PlatformViewFactory {
    private final AtomicInteger mActivityState;
    private final PluginRegistry.Registrar mPluginRegistrar;

    GoogleMapFactory(AtomicInteger atomicInteger, PluginRegistry.Registrar registrar) {
        super(StandardMessageCodec.INSTANCE);
        this.mActivityState = atomicInteger;
        this.mPluginRegistrar = registrar;
    }

    public PlatformView create(Context context, int i, Object obj) {
        Map map = (Map) obj;
        GoogleMapBuilder googleMapBuilder = new GoogleMapBuilder();
        Convert.interpretGoogleMapOptions(map.get(Constant.METHOD_OPTIONS), googleMapBuilder);
        if (map.containsKey("initialCameraPosition")) {
            googleMapBuilder.setInitialCameraPosition(Convert.toCameraPosition(map.get("initialCameraPosition")));
        }
        if (map.containsKey("markersToAdd")) {
            googleMapBuilder.setInitialMarkers(map.get("markersToAdd"));
        }
        if (map.containsKey("polygonsToAdd")) {
            googleMapBuilder.setInitialPolygons(map.get("polygonsToAdd"));
        }
        if (map.containsKey("polylinesToAdd")) {
            googleMapBuilder.setInitialPolylines(map.get("polylinesToAdd"));
        }
        if (map.containsKey("circlesToAdd")) {
            googleMapBuilder.setInitialCircles(map.get("circlesToAdd"));
        }
        return googleMapBuilder.build(i, context, this.mActivityState, this.mPluginRegistrar);
    }
}
