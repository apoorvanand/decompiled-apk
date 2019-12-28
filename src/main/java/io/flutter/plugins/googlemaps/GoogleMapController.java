package io.flutter.plugins.googlemaps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.platform.PlatformView;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class GoogleMapController implements Application.ActivityLifecycleCallbacks, GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnCircleClickListener, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnPolygonClickListener, GoogleMap.OnPolylineClickListener, OnMapReadyCallback, MethodChannel.MethodCallHandler, PlatformView, GoogleMapOptionsSink {
    private static final String TAG = "GoogleMapController";
    private final AtomicInteger activityState;
    private final CirclesController circlesController;
    private final Context context;
    private final float density;
    private boolean disposed = false;
    private GoogleMap googleMap;
    private final int id;
    private boolean indoorEnabled = true;
    private List<Object> initialCircles;
    private List<Object> initialMarkers;
    private List<Object> initialPolygons;
    private List<Object> initialPolylines;
    private MethodChannel.Result mapReadyResult;
    private final MapView mapView;
    private final MarkersController markersController;
    private final MethodChannel methodChannel;
    private boolean myLocationButtonEnabled = false;
    private boolean myLocationEnabled = false;
    private final PolygonsController polygonsController;
    private final PolylinesController polylinesController;
    private final PluginRegistry.Registrar registrar;
    private final int registrarActivityHashCode;
    private boolean trackCameraPosition = false;
    private boolean trafficEnabled = false;

    GoogleMapController(int i, Context context2, AtomicInteger atomicInteger, PluginRegistry.Registrar registrar2, GoogleMapOptions googleMapOptions) {
        this.id = i;
        this.context = context2;
        this.activityState = atomicInteger;
        this.registrar = registrar2;
        this.mapView = new MapView(context2, googleMapOptions);
        this.density = context2.getResources().getDisplayMetrics().density;
        BinaryMessenger messenger = registrar2.messenger();
        this.methodChannel = new MethodChannel(messenger, "plugins.flutter.io/google_maps_" + i);
        this.methodChannel.setMethodCallHandler(this);
        this.registrarActivityHashCode = registrar2.activity().hashCode();
        this.markersController = new MarkersController(this.methodChannel);
        this.polygonsController = new PolygonsController(this.methodChannel);
        this.polylinesController = new PolylinesController(this.methodChannel, this.density);
        this.circlesController = new CirclesController(this.methodChannel);
    }

    private void animateCamera(CameraUpdate cameraUpdate) {
        this.googleMap.animateCamera(cameraUpdate);
    }

    private int checkSelfPermission(String str) {
        if (str != null) {
            return this.context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    private CameraPosition getCameraPosition() {
        if (this.trackCameraPosition) {
            return this.googleMap.getCameraPosition();
        }
        return null;
    }

    private boolean hasLocationPermission() {
        return checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    private void moveCamera(CameraUpdate cameraUpdate) {
        this.googleMap.moveCamera(cameraUpdate);
    }

    private void updateInitialCircles() {
        this.circlesController.addCircles(this.initialCircles);
    }

    private void updateInitialMarkers() {
        this.markersController.addMarkers(this.initialMarkers);
    }

    private void updateInitialPolygons() {
        this.polygonsController.addPolygons(this.initialPolygons);
    }

    private void updateInitialPolylines() {
        this.polylinesController.addPolylines(this.initialPolylines);
    }

    @SuppressLint({"MissingPermission"})
    private void updateMyLocationSettings() {
        if (hasLocationPermission()) {
            this.googleMap.setMyLocationEnabled(this.myLocationEnabled);
            this.googleMap.getUiSettings().setMyLocationButtonEnabled(this.myLocationButtonEnabled);
            return;
        }
        Log.e(TAG, "Cannot enable MyLocation layer as location permissions are not granted");
    }

    public void dispose() {
        if (!this.disposed) {
            this.disposed = true;
            this.methodChannel.setMethodCallHandler((MethodChannel.MethodCallHandler) null);
            this.mapView.onDestroy();
            this.registrar.activity().getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    public View getView() {
        return this.mapView;
    }

    /* access modifiers changed from: package-private */
    public void init() {
        switch (this.activityState.get()) {
            case 1:
                this.mapView.onCreate((Bundle) null);
                break;
            case 2:
                this.mapView.onCreate((Bundle) null);
                this.mapView.onStart();
                break;
            case 3:
                this.mapView.onCreate((Bundle) null);
                this.mapView.onStart();
                this.mapView.onResume();
                break;
            case 4:
                this.mapView.onCreate((Bundle) null);
                this.mapView.onStart();
                this.mapView.onResume();
                this.mapView.onPause();
                break;
            case 5:
                this.mapView.onCreate((Bundle) null);
                this.mapView.onStart();
                this.mapView.onResume();
                this.mapView.onPause();
                this.mapView.onStop();
                break;
            case 6:
                break;
            default:
                throw new IllegalArgumentException("Cannot interpret " + this.activityState.get() + " as an activity state");
        }
        this.registrar.activity().getApplication().registerActivityLifecycleCallbacks(this);
        this.mapView.getMapAsync(this);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (!this.disposed && activity.hashCode() == this.registrarActivityHashCode) {
            this.mapView.onCreate(bundle);
        }
    }

    public void onActivityDestroyed(Activity activity) {
        if (!this.disposed && activity.hashCode() == this.registrarActivityHashCode) {
            this.mapView.onDestroy();
        }
    }

    public void onActivityPaused(Activity activity) {
        if (!this.disposed && activity.hashCode() == this.registrarActivityHashCode) {
            this.mapView.onPause();
        }
    }

    public void onActivityResumed(Activity activity) {
        if (!this.disposed && activity.hashCode() == this.registrarActivityHashCode) {
            this.mapView.onResume();
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (!this.disposed && activity.hashCode() == this.registrarActivityHashCode) {
            this.mapView.onSaveInstanceState(bundle);
        }
    }

    public void onActivityStarted(Activity activity) {
        if (!this.disposed && activity.hashCode() == this.registrarActivityHashCode) {
            this.mapView.onStart();
        }
    }

    public void onActivityStopped(Activity activity) {
        if (!this.disposed && activity.hashCode() == this.registrarActivityHashCode) {
            this.mapView.onStop();
        }
    }

    public void onCameraIdle() {
        this.methodChannel.invokeMethod("camera#onIdle", Collections.singletonMap("map", Integer.valueOf(this.id)));
    }

    public void onCameraMove() {
        if (this.trackCameraPosition) {
            HashMap hashMap = new HashMap(2);
            hashMap.put("position", Convert.cameraPositionToJson(this.googleMap.getCameraPosition()));
            this.methodChannel.invokeMethod("camera#onMove", hashMap);
        }
    }

    public void onCameraMoveStarted(int i) {
        HashMap hashMap = new HashMap(2);
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        hashMap.put("isGesture", Boolean.valueOf(z));
        this.methodChannel.invokeMethod("camera#onMoveStarted", hashMap);
    }

    public void onCircleClick(Circle circle) {
        this.circlesController.onCircleTap(circle.getId());
    }

    public void onInfoWindowClick(Marker marker) {
        this.markersController.onInfoWindowTap(marker.getId());
    }

    public void onInputConnectionLocked() {
    }

    public void onInputConnectionUnlocked() {
    }

    public void onMapClick(LatLng latLng) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("position", Convert.latLngToJson(latLng));
        this.methodChannel.invokeMethod("map#onTap", hashMap);
    }

    public void onMapLongClick(LatLng latLng) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("position", Convert.latLngToJson(latLng));
        this.methodChannel.invokeMethod("map#onLongPress", hashMap);
    }

    public void onMapReady(GoogleMap googleMap2) {
        this.googleMap = googleMap2;
        this.googleMap.setIndoorEnabled(this.indoorEnabled);
        this.googleMap.setTrafficEnabled(this.trafficEnabled);
        googleMap2.setOnInfoWindowClickListener(this);
        MethodChannel.Result result = this.mapReadyResult;
        if (result != null) {
            result.success((Object) null);
            this.mapReadyResult = null;
        }
        googleMap2.setOnCameraMoveStartedListener(this);
        googleMap2.setOnCameraMoveListener(this);
        googleMap2.setOnCameraIdleListener(this);
        googleMap2.setOnMarkerClickListener(this);
        googleMap2.setOnMarkerDragListener(this);
        googleMap2.setOnPolygonClickListener(this);
        googleMap2.setOnPolylineClickListener(this);
        googleMap2.setOnCircleClickListener(this);
        googleMap2.setOnMapClickListener(this);
        googleMap2.setOnMapLongClickListener(this);
        updateMyLocationSettings();
        this.markersController.setGoogleMap(googleMap2);
        this.polygonsController.setGoogleMap(googleMap2);
        this.polylinesController.setGoogleMap(googleMap2);
        this.circlesController.setGoogleMap(googleMap2);
        updateInitialMarkers();
        updateInitialPolygons();
        updateInitialPolylines();
        updateInitialCircles();
    }

    public boolean onMarkerClick(Marker marker) {
        return this.markersController.onMarkerTap(marker.getId());
    }

    public void onMarkerDrag(Marker marker) {
    }

    public void onMarkerDragEnd(Marker marker) {
        this.markersController.onMarkerDragEnd(marker.getId(), marker.getPosition());
    }

    public void onMarkerDragStart(Marker marker) {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x02c2, code lost:
        r5.error(r4, r0, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x02d7, code lost:
        r5.success(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01a4, code lost:
        r4 = java.lang.Boolean.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x026a, code lost:
        r5.success((java.lang.Object) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMethodCall(io.flutter.plugin.common.MethodCall r4, io.flutter.plugin.common.MethodChannel.Result r5) {
        /*
            r3 = this;
            java.lang.String r0 = r4.method
            int r1 = r0.hashCode()
            r2 = 2
            switch(r1) {
                case -2068530537: goto L_0x00ec;
                case -1753225255: goto L_0x00e1;
                case -1675017333: goto L_0x00d6;
                case -1389285936: goto L_0x00cc;
                case -1366519597: goto L_0x00c2;
                case -1264573565: goto L_0x00b8;
                case -1102318061: goto L_0x00ad;
                case -596474455: goto L_0x00a2;
                case -577075523: goto L_0x0097;
                case 262112323: goto L_0x008c;
                case 282895827: goto L_0x0080;
                case 295004975: goto L_0x0075;
                case 390939153: goto L_0x0069;
                case 622947733: goto L_0x005e;
                case 643972249: goto L_0x0052;
                case 712945078: goto L_0x0046;
                case 1098288608: goto L_0x003a;
                case 1322988819: goto L_0x002f;
                case 1546082965: goto L_0x0023;
                case 1873569705: goto L_0x0017;
                case 2003557999: goto L_0x000c;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x00f6
        L_0x000c:
            java.lang.String r1 = "camera#move"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 5
            goto L_0x00f7
        L_0x0017:
            java.lang.String r1 = "circles#update"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 10
            goto L_0x00f7
        L_0x0023:
            java.lang.String r1 = "map#isTrafficEnabled"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 19
            goto L_0x00f7
        L_0x002f:
            java.lang.String r1 = "markers#update"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 7
            goto L_0x00f7
        L_0x003a:
            java.lang.String r1 = "map#isCompassEnabled"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 11
            goto L_0x00f7
        L_0x0046:
            java.lang.String r1 = "map#setStyle"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 20
            goto L_0x00f7
        L_0x0052:
            java.lang.String r1 = "polylines#update"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 9
            goto L_0x00f7
        L_0x005e:
            java.lang.String r1 = "map#getLatLng"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 4
            goto L_0x00f7
        L_0x0069:
            java.lang.String r1 = "map#isMapToolbarEnabled"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 12
            goto L_0x00f7
        L_0x0075:
            java.lang.String r1 = "map#waitForMap"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 0
            goto L_0x00f7
        L_0x0080:
            java.lang.String r1 = "map#isZoomGesturesEnabled"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 14
            goto L_0x00f7
        L_0x008c:
            java.lang.String r1 = "map#getMinMaxZoomLevels"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 13
            goto L_0x00f7
        L_0x0097:
            java.lang.String r1 = "map#isMyLocationButtonEnabled"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 18
            goto L_0x00f7
        L_0x00a2:
            java.lang.String r1 = "map#isTiltGesturesEnabled"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 16
            goto L_0x00f7
        L_0x00ad:
            java.lang.String r1 = "polygons#update"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 8
            goto L_0x00f7
        L_0x00b8:
            java.lang.String r1 = "camera#animate"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 6
            goto L_0x00f7
        L_0x00c2:
            java.lang.String r1 = "map#getScreenCoordinate"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 3
            goto L_0x00f7
        L_0x00cc:
            java.lang.String r1 = "map#update"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 1
            goto L_0x00f7
        L_0x00d6:
            java.lang.String r1 = "map#isRotateGesturesEnabled"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 17
            goto L_0x00f7
        L_0x00e1:
            java.lang.String r1 = "map#isScrollGesturesEnabled"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 15
            goto L_0x00f7
        L_0x00ec:
            java.lang.String r1 = "map#getVisibleRegion"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00f6
            r0 = 2
            goto L_0x00f7
        L_0x00f6:
            r0 = -1
        L_0x00f7:
            r1 = 0
            switch(r0) {
                case 0: goto L_0x02db;
                case 1: goto L_0x02c6;
                case 2: goto L_0x02ab;
                case 3: goto L_0x028d;
                case 4: goto L_0x026f;
                case 5: goto L_0x025b;
                case 6: goto L_0x024b;
                case 7: goto L_0x0223;
                case 8: goto L_0x01fb;
                case 9: goto L_0x01d3;
                case 10: goto L_0x01aa;
                case 11: goto L_0x019a;
                case 12: goto L_0x018f;
                case 13: goto L_0x016e;
                case 14: goto L_0x0163;
                case 15: goto L_0x0158;
                case 16: goto L_0x014d;
                case 17: goto L_0x0142;
                case 18: goto L_0x0137;
                case 19: goto L_0x0130;
                case 20: goto L_0x0100;
                default: goto L_0x00fb;
            }
        L_0x00fb:
            r5.notImplemented()
            goto L_0x02e5
        L_0x0100:
            java.lang.Object r4 = r4.arguments
            java.lang.String r4 = (java.lang.String) r4
            if (r4 != 0) goto L_0x010d
            com.google.android.gms.maps.GoogleMap r4 = r3.googleMap
            boolean r4 = r4.setMapStyle(r1)
            goto L_0x0118
        L_0x010d:
            com.google.android.gms.maps.GoogleMap r0 = r3.googleMap
            com.google.android.gms.maps.model.MapStyleOptions r1 = new com.google.android.gms.maps.model.MapStyleOptions
            r1.<init>(r4)
            boolean r4 = r0.setMapStyle(r1)
        L_0x0118:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r2)
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r4)
            r0.add(r1)
            if (r4 != 0) goto L_0x012b
            java.lang.String r4 = "Unable to set the map style. Please check console logs for errors."
            r0.add(r4)
        L_0x012b:
            r5.success(r0)
            goto L_0x02e5
        L_0x0130:
            com.google.android.gms.maps.GoogleMap r4 = r3.googleMap
            boolean r4 = r4.isTrafficEnabled()
            goto L_0x01a4
        L_0x0137:
            com.google.android.gms.maps.GoogleMap r4 = r3.googleMap
            com.google.android.gms.maps.UiSettings r4 = r4.getUiSettings()
            boolean r4 = r4.isMyLocationButtonEnabled()
            goto L_0x01a4
        L_0x0142:
            com.google.android.gms.maps.GoogleMap r4 = r3.googleMap
            com.google.android.gms.maps.UiSettings r4 = r4.getUiSettings()
            boolean r4 = r4.isRotateGesturesEnabled()
            goto L_0x01a4
        L_0x014d:
            com.google.android.gms.maps.GoogleMap r4 = r3.googleMap
            com.google.android.gms.maps.UiSettings r4 = r4.getUiSettings()
            boolean r4 = r4.isTiltGesturesEnabled()
            goto L_0x01a4
        L_0x0158:
            com.google.android.gms.maps.GoogleMap r4 = r3.googleMap
            com.google.android.gms.maps.UiSettings r4 = r4.getUiSettings()
            boolean r4 = r4.isScrollGesturesEnabled()
            goto L_0x01a4
        L_0x0163:
            com.google.android.gms.maps.GoogleMap r4 = r3.googleMap
            com.google.android.gms.maps.UiSettings r4 = r4.getUiSettings()
            boolean r4 = r4.isZoomGesturesEnabled()
            goto L_0x01a4
        L_0x016e:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r2)
            com.google.android.gms.maps.GoogleMap r0 = r3.googleMap
            float r0 = r0.getMinZoomLevel()
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            r4.add(r0)
            com.google.android.gms.maps.GoogleMap r0 = r3.googleMap
            float r0 = r0.getMaxZoomLevel()
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            r4.add(r0)
            goto L_0x02d7
        L_0x018f:
            com.google.android.gms.maps.GoogleMap r4 = r3.googleMap
            com.google.android.gms.maps.UiSettings r4 = r4.getUiSettings()
            boolean r4 = r4.isMapToolbarEnabled()
            goto L_0x01a4
        L_0x019a:
            com.google.android.gms.maps.GoogleMap r4 = r3.googleMap
            com.google.android.gms.maps.UiSettings r4 = r4.getUiSettings()
            boolean r4 = r4.isCompassEnabled()
        L_0x01a4:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            goto L_0x02d7
        L_0x01aa:
            java.lang.String r0 = "circlesToAdd"
            java.lang.Object r0 = r4.argument(r0)
            io.flutter.plugins.googlemaps.CirclesController r2 = r3.circlesController
            java.util.List r0 = (java.util.List) r0
            r2.addCircles(r0)
            java.lang.String r0 = "circlesToChange"
            java.lang.Object r0 = r4.argument(r0)
            io.flutter.plugins.googlemaps.CirclesController r2 = r3.circlesController
            java.util.List r0 = (java.util.List) r0
            r2.changeCircles(r0)
            java.lang.String r0 = "circleIdsToRemove"
            java.lang.Object r4 = r4.argument(r0)
            io.flutter.plugins.googlemaps.CirclesController r0 = r3.circlesController
            java.util.List r4 = (java.util.List) r4
            r0.removeCircles(r4)
            goto L_0x026a
        L_0x01d3:
            java.lang.String r0 = "polylinesToAdd"
            java.lang.Object r0 = r4.argument(r0)
            io.flutter.plugins.googlemaps.PolylinesController r2 = r3.polylinesController
            java.util.List r0 = (java.util.List) r0
            r2.addPolylines(r0)
            java.lang.String r0 = "polylinesToChange"
            java.lang.Object r0 = r4.argument(r0)
            io.flutter.plugins.googlemaps.PolylinesController r2 = r3.polylinesController
            java.util.List r0 = (java.util.List) r0
            r2.changePolylines(r0)
            java.lang.String r0 = "polylineIdsToRemove"
            java.lang.Object r4 = r4.argument(r0)
            io.flutter.plugins.googlemaps.PolylinesController r0 = r3.polylinesController
            java.util.List r4 = (java.util.List) r4
            r0.removePolylines(r4)
            goto L_0x026a
        L_0x01fb:
            java.lang.String r0 = "polygonsToAdd"
            java.lang.Object r0 = r4.argument(r0)
            io.flutter.plugins.googlemaps.PolygonsController r2 = r3.polygonsController
            java.util.List r0 = (java.util.List) r0
            r2.addPolygons(r0)
            java.lang.String r0 = "polygonsToChange"
            java.lang.Object r0 = r4.argument(r0)
            io.flutter.plugins.googlemaps.PolygonsController r2 = r3.polygonsController
            java.util.List r0 = (java.util.List) r0
            r2.changePolygons(r0)
            java.lang.String r0 = "polygonIdsToRemove"
            java.lang.Object r4 = r4.argument(r0)
            io.flutter.plugins.googlemaps.PolygonsController r0 = r3.polygonsController
            java.util.List r4 = (java.util.List) r4
            r0.removePolygons(r4)
            goto L_0x026a
        L_0x0223:
            java.lang.String r0 = "markersToAdd"
            java.lang.Object r0 = r4.argument(r0)
            io.flutter.plugins.googlemaps.MarkersController r2 = r3.markersController
            java.util.List r0 = (java.util.List) r0
            r2.addMarkers(r0)
            java.lang.String r0 = "markersToChange"
            java.lang.Object r0 = r4.argument(r0)
            io.flutter.plugins.googlemaps.MarkersController r2 = r3.markersController
            java.util.List r0 = (java.util.List) r0
            r2.changeMarkers(r0)
            java.lang.String r0 = "markerIdsToRemove"
            java.lang.Object r4 = r4.argument(r0)
            io.flutter.plugins.googlemaps.MarkersController r0 = r3.markersController
            java.util.List r4 = (java.util.List) r4
            r0.removeMarkers(r4)
            goto L_0x026a
        L_0x024b:
            java.lang.String r0 = "cameraUpdate"
            java.lang.Object r4 = r4.argument(r0)
            float r0 = r3.density
            com.google.android.gms.maps.CameraUpdate r4 = io.flutter.plugins.googlemaps.Convert.toCameraUpdate(r4, r0)
            r3.animateCamera(r4)
            goto L_0x026a
        L_0x025b:
            java.lang.String r0 = "cameraUpdate"
            java.lang.Object r4 = r4.argument(r0)
            float r0 = r3.density
            com.google.android.gms.maps.CameraUpdate r4 = io.flutter.plugins.googlemaps.Convert.toCameraUpdate(r4, r0)
            r3.moveCamera(r4)
        L_0x026a:
            r5.success(r1)
            goto L_0x02e5
        L_0x026f:
            com.google.android.gms.maps.GoogleMap r0 = r3.googleMap
            if (r0 == 0) goto L_0x0288
            java.lang.Object r4 = r4.arguments
            android.graphics.Point r4 = io.flutter.plugins.googlemaps.Convert.toPoint(r4)
            com.google.android.gms.maps.GoogleMap r0 = r3.googleMap
            com.google.android.gms.maps.Projection r0 = r0.getProjection()
            com.google.android.gms.maps.model.LatLng r4 = r0.fromScreenLocation(r4)
            java.lang.Object r4 = io.flutter.plugins.googlemaps.Convert.latLngToJson(r4)
            goto L_0x02d7
        L_0x0288:
            java.lang.String r4 = "GoogleMap uninitialized"
            java.lang.String r0 = "getLatLng called prior to map initialization"
            goto L_0x02c2
        L_0x028d:
            com.google.android.gms.maps.GoogleMap r0 = r3.googleMap
            if (r0 == 0) goto L_0x02a6
            java.lang.Object r4 = r4.arguments
            com.google.android.gms.maps.model.LatLng r4 = io.flutter.plugins.googlemaps.Convert.toLatLng(r4)
            com.google.android.gms.maps.GoogleMap r0 = r3.googleMap
            com.google.android.gms.maps.Projection r0 = r0.getProjection()
            android.graphics.Point r4 = r0.toScreenLocation(r4)
            java.util.Map r4 = io.flutter.plugins.googlemaps.Convert.pointToJson(r4)
            goto L_0x02d7
        L_0x02a6:
            java.lang.String r4 = "GoogleMap uninitialized"
            java.lang.String r0 = "getScreenCoordinate called prior to map initialization"
            goto L_0x02c2
        L_0x02ab:
            com.google.android.gms.maps.GoogleMap r4 = r3.googleMap
            if (r4 == 0) goto L_0x02be
            com.google.android.gms.maps.Projection r4 = r4.getProjection()
            com.google.android.gms.maps.model.VisibleRegion r4 = r4.getVisibleRegion()
            com.google.android.gms.maps.model.LatLngBounds r4 = r4.latLngBounds
            java.lang.Object r4 = io.flutter.plugins.googlemaps.Convert.latlngBoundsToJson(r4)
            goto L_0x02d7
        L_0x02be:
            java.lang.String r4 = "GoogleMap uninitialized"
            java.lang.String r0 = "getVisibleRegion called prior to map initialization"
        L_0x02c2:
            r5.error(r4, r0, r1)
            goto L_0x02e5
        L_0x02c6:
            java.lang.String r0 = "options"
            java.lang.Object r4 = r4.argument(r0)
            io.flutter.plugins.googlemaps.Convert.interpretGoogleMapOptions(r4, r3)
            com.google.android.gms.maps.model.CameraPosition r4 = r3.getCameraPosition()
            java.lang.Object r4 = io.flutter.plugins.googlemaps.Convert.cameraPositionToJson(r4)
        L_0x02d7:
            r5.success(r4)
            goto L_0x02e5
        L_0x02db:
            com.google.android.gms.maps.GoogleMap r4 = r3.googleMap
            if (r4 == 0) goto L_0x02e3
            r5.success(r1)
            return
        L_0x02e3:
            r3.mapReadyResult = r5
        L_0x02e5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.googlemaps.GoogleMapController.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }

    public void onPolygonClick(Polygon polygon) {
        this.polygonsController.onPolygonTap(polygon.getId());
    }

    public void onPolylineClick(Polyline polyline) {
        this.polylinesController.onPolylineTap(polyline.getId());
    }

    public void setCameraTargetBounds(LatLngBounds latLngBounds) {
        this.googleMap.setLatLngBoundsForCameraTarget(latLngBounds);
    }

    public void setCompassEnabled(boolean z) {
        this.googleMap.getUiSettings().setCompassEnabled(z);
    }

    public void setIndoorEnabled(boolean z) {
        this.indoorEnabled = z;
    }

    public void setInitialCircles(Object obj) {
        this.initialCircles = (List) obj;
        if (this.googleMap != null) {
            updateInitialCircles();
        }
    }

    public void setInitialMarkers(Object obj) {
        this.initialMarkers = (List) obj;
        if (this.googleMap != null) {
            updateInitialMarkers();
        }
    }

    public void setInitialPolygons(Object obj) {
        this.initialPolygons = (List) obj;
        if (this.googleMap != null) {
            updateInitialPolygons();
        }
    }

    public void setInitialPolylines(Object obj) {
        this.initialPolylines = (List) obj;
        if (this.googleMap != null) {
            updateInitialPolylines();
        }
    }

    public void setMapToolbarEnabled(boolean z) {
        this.googleMap.getUiSettings().setMapToolbarEnabled(z);
    }

    public void setMapType(int i) {
        this.googleMap.setMapType(i);
    }

    public void setMinMaxZoomPreference(Float f, Float f2) {
        this.googleMap.resetMinMaxZoomPreference();
        if (f != null) {
            this.googleMap.setMinZoomPreference(f.floatValue());
        }
        if (f2 != null) {
            this.googleMap.setMaxZoomPreference(f2.floatValue());
        }
    }

    public void setMyLocationButtonEnabled(boolean z) {
        if (this.myLocationButtonEnabled != z) {
            this.myLocationButtonEnabled = z;
            if (this.googleMap != null) {
                updateMyLocationSettings();
            }
        }
    }

    public void setMyLocationEnabled(boolean z) {
        if (this.myLocationEnabled != z) {
            this.myLocationEnabled = z;
            if (this.googleMap != null) {
                updateMyLocationSettings();
            }
        }
    }

    public void setPadding(float f, float f2, float f3, float f4) {
        GoogleMap googleMap2 = this.googleMap;
        if (googleMap2 != null) {
            float f5 = this.density;
            googleMap2.setPadding((int) (f2 * f5), (int) (f * f5), (int) (f4 * f5), (int) (f3 * f5));
        }
    }

    public void setRotateGesturesEnabled(boolean z) {
        this.googleMap.getUiSettings().setRotateGesturesEnabled(z);
    }

    public void setScrollGesturesEnabled(boolean z) {
        this.googleMap.getUiSettings().setScrollGesturesEnabled(z);
    }

    public void setTiltGesturesEnabled(boolean z) {
        this.googleMap.getUiSettings().setTiltGesturesEnabled(z);
    }

    public void setTrackCameraPosition(boolean z) {
        this.trackCameraPosition = z;
    }

    public void setTrafficEnabled(boolean z) {
        this.trafficEnabled = z;
    }

    public void setZoomGesturesEnabled(boolean z) {
        this.googleMap.getUiSettings().setZoomGesturesEnabled(z);
    }
}
