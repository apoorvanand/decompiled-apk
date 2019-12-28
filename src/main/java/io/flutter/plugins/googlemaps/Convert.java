package io.flutter.plugins.googlemaps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Convert {
    Convert() {
    }

    static Object cameraPositionToJson(CameraPosition cameraPosition) {
        if (cameraPosition == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("bearing", Float.valueOf(cameraPosition.bearing));
        hashMap.put("target", latLngToJson(cameraPosition.target));
        hashMap.put("tilt", Float.valueOf(cameraPosition.tilt));
        hashMap.put("zoom", Float.valueOf(cameraPosition.zoom));
        return hashMap;
    }

    static Object circleIdToJson(String str) {
        if (str == null) {
            return null;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("circleId", str);
        return hashMap;
    }

    private static BitmapDescriptor getBitmapFromBytes(List<?> list) {
        if (list.size() == 2) {
            try {
                return BitmapDescriptorFactory.fromBitmap(toBitmap(list.get(1)));
            } catch (Exception e) {
                throw new IllegalArgumentException("Unable to interpret bytes as a valid image.", e);
            }
        } else {
            throw new IllegalArgumentException("fromBytes should have exactly one argument, the bytes. Got: " + list.size());
        }
    }

    static String interpretCircleOptions(Object obj, CircleOptionsSink circleOptionsSink) {
        Map<?, ?> map = toMap(obj);
        Object obj2 = map.get("consumeTapEvents");
        if (obj2 != null) {
            circleOptionsSink.setConsumeTapEvents(toBoolean(obj2));
        }
        Object obj3 = map.get("fillColor");
        if (obj3 != null) {
            circleOptionsSink.setFillColor(toInt(obj3));
        }
        Object obj4 = map.get("strokeColor");
        if (obj4 != null) {
            circleOptionsSink.setStrokeColor(toInt(obj4));
        }
        Object obj5 = map.get("visible");
        if (obj5 != null) {
            circleOptionsSink.setVisible(toBoolean(obj5));
        }
        Object obj6 = map.get("strokeWidth");
        if (obj6 != null) {
            circleOptionsSink.setStrokeWidth((float) toInt(obj6));
        }
        Object obj7 = map.get("zIndex");
        if (obj7 != null) {
            circleOptionsSink.setZIndex(toFloat(obj7));
        }
        Object obj8 = map.get("center");
        if (obj8 != null) {
            circleOptionsSink.setCenter(toLatLng(obj8));
        }
        Object obj9 = map.get("radius");
        if (obj9 != null) {
            circleOptionsSink.setRadius(toDouble(obj9));
        }
        String str = (String) map.get("circleId");
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("circleId was null");
    }

    static void interpretGoogleMapOptions(Object obj, GoogleMapOptionsSink googleMapOptionsSink) {
        Map<?, ?> map = toMap(obj);
        Object obj2 = map.get("cameraTargetBounds");
        if (obj2 != null) {
            googleMapOptionsSink.setCameraTargetBounds(toLatLngBounds(toList(obj2).get(0)));
        }
        Object obj3 = map.get("compassEnabled");
        if (obj3 != null) {
            googleMapOptionsSink.setCompassEnabled(toBoolean(obj3));
        }
        Object obj4 = map.get("mapToolbarEnabled");
        if (obj4 != null) {
            googleMapOptionsSink.setMapToolbarEnabled(toBoolean(obj4));
        }
        Object obj5 = map.get("mapType");
        if (obj5 != null) {
            googleMapOptionsSink.setMapType(toInt(obj5));
        }
        Object obj6 = map.get("minMaxZoomPreference");
        if (obj6 != null) {
            List<?> list = toList(obj6);
            googleMapOptionsSink.setMinMaxZoomPreference(toFloatWrapper(list.get(0)), toFloatWrapper(list.get(1)));
        }
        Object obj7 = map.get("padding");
        if (obj7 != null) {
            List<?> list2 = toList(obj7);
            googleMapOptionsSink.setPadding(toFloat(list2.get(0)), toFloat(list2.get(1)), toFloat(list2.get(2)), toFloat(list2.get(3)));
        }
        Object obj8 = map.get("rotateGesturesEnabled");
        if (obj8 != null) {
            googleMapOptionsSink.setRotateGesturesEnabled(toBoolean(obj8));
        }
        Object obj9 = map.get("scrollGesturesEnabled");
        if (obj9 != null) {
            googleMapOptionsSink.setScrollGesturesEnabled(toBoolean(obj9));
        }
        Object obj10 = map.get("tiltGesturesEnabled");
        if (obj10 != null) {
            googleMapOptionsSink.setTiltGesturesEnabled(toBoolean(obj10));
        }
        Object obj11 = map.get("trackCameraPosition");
        if (obj11 != null) {
            googleMapOptionsSink.setTrackCameraPosition(toBoolean(obj11));
        }
        Object obj12 = map.get("zoomGesturesEnabled");
        if (obj12 != null) {
            googleMapOptionsSink.setZoomGesturesEnabled(toBoolean(obj12));
        }
        Object obj13 = map.get("myLocationEnabled");
        if (obj13 != null) {
            googleMapOptionsSink.setMyLocationEnabled(toBoolean(obj13));
        }
        Object obj14 = map.get("myLocationButtonEnabled");
        if (obj14 != null) {
            googleMapOptionsSink.setMyLocationButtonEnabled(toBoolean(obj14));
        }
        Object obj15 = map.get("indoorEnabled");
        if (obj15 != null) {
            googleMapOptionsSink.setIndoorEnabled(toBoolean(obj15));
        }
        Object obj16 = map.get("trafficEnabled");
        if (obj16 != null) {
            googleMapOptionsSink.setTrafficEnabled(toBoolean(obj16));
        }
    }

    private static void interpretInfoWindowOptions(MarkerOptionsSink markerOptionsSink, Map<String, Object> map) {
        String str = (String) map.get("title");
        String str2 = (String) map.get("snippet");
        if (str != null) {
            markerOptionsSink.setInfoWindowText(str, str2);
        }
        Object obj = map.get("anchor");
        if (obj != null) {
            List<?> list = toList(obj);
            markerOptionsSink.setInfoWindowAnchor(toFloat(list.get(0)), toFloat(list.get(1)));
        }
    }

    static String interpretMarkerOptions(Object obj, MarkerOptionsSink markerOptionsSink) {
        Map<?, ?> map = toMap(obj);
        Object obj2 = map.get("alpha");
        if (obj2 != null) {
            markerOptionsSink.setAlpha(toFloat(obj2));
        }
        Object obj3 = map.get("anchor");
        if (obj3 != null) {
            List<?> list = toList(obj3);
            markerOptionsSink.setAnchor(toFloat(list.get(0)), toFloat(list.get(1)));
        }
        Object obj4 = map.get("consumeTapEvents");
        if (obj4 != null) {
            markerOptionsSink.setConsumeTapEvents(toBoolean(obj4));
        }
        Object obj5 = map.get("draggable");
        if (obj5 != null) {
            markerOptionsSink.setDraggable(toBoolean(obj5));
        }
        Object obj6 = map.get("flat");
        if (obj6 != null) {
            markerOptionsSink.setFlat(toBoolean(obj6));
        }
        Object obj7 = map.get("icon");
        if (obj7 != null) {
            markerOptionsSink.setIcon(toBitmapDescriptor(obj7));
        }
        Object obj8 = map.get("infoWindow");
        if (obj8 != null) {
            interpretInfoWindowOptions(markerOptionsSink, (Map) obj8);
        }
        Object obj9 = map.get("position");
        if (obj9 != null) {
            markerOptionsSink.setPosition(toLatLng(obj9));
        }
        Object obj10 = map.get("rotation");
        if (obj10 != null) {
            markerOptionsSink.setRotation(toFloat(obj10));
        }
        Object obj11 = map.get("visible");
        if (obj11 != null) {
            markerOptionsSink.setVisible(toBoolean(obj11));
        }
        Object obj12 = map.get("zIndex");
        if (obj12 != null) {
            markerOptionsSink.setZIndex(toFloat(obj12));
        }
        String str = (String) map.get("markerId");
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("markerId was null");
    }

    static String interpretPolygonOptions(Object obj, PolygonOptionsSink polygonOptionsSink) {
        Map<?, ?> map = toMap(obj);
        Object obj2 = map.get("consumeTapEvents");
        if (obj2 != null) {
            polygonOptionsSink.setConsumeTapEvents(toBoolean(obj2));
        }
        Object obj3 = map.get("geodesic");
        if (obj3 != null) {
            polygonOptionsSink.setGeodesic(toBoolean(obj3));
        }
        Object obj4 = map.get("visible");
        if (obj4 != null) {
            polygonOptionsSink.setVisible(toBoolean(obj4));
        }
        Object obj5 = map.get("fillColor");
        if (obj5 != null) {
            polygonOptionsSink.setFillColor(toInt(obj5));
        }
        Object obj6 = map.get("strokeColor");
        if (obj6 != null) {
            polygonOptionsSink.setStrokeColor(toInt(obj6));
        }
        Object obj7 = map.get("strokeWidth");
        if (obj7 != null) {
            polygonOptionsSink.setStrokeWidth((float) toInt(obj7));
        }
        Object obj8 = map.get("zIndex");
        if (obj8 != null) {
            polygonOptionsSink.setZIndex(toFloat(obj8));
        }
        Object obj9 = map.get("points");
        if (obj9 != null) {
            polygonOptionsSink.setPoints(toPoints(obj9));
        }
        String str = (String) map.get("polygonId");
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("polygonId was null");
    }

    static String interpretPolylineOptions(Object obj, PolylineOptionsSink polylineOptionsSink) {
        Map<?, ?> map = toMap(obj);
        Object obj2 = map.get("consumeTapEvents");
        if (obj2 != null) {
            polylineOptionsSink.setConsumeTapEvents(toBoolean(obj2));
        }
        Object obj3 = map.get("color");
        if (obj3 != null) {
            polylineOptionsSink.setColor(toInt(obj3));
        }
        Object obj4 = map.get("endCap");
        if (obj4 != null) {
            polylineOptionsSink.setEndCap(toCap(obj4));
        }
        Object obj5 = map.get("geodesic");
        if (obj5 != null) {
            polylineOptionsSink.setGeodesic(toBoolean(obj5));
        }
        Object obj6 = map.get("jointType");
        if (obj6 != null) {
            polylineOptionsSink.setJointType(toInt(obj6));
        }
        Object obj7 = map.get("startCap");
        if (obj7 != null) {
            polylineOptionsSink.setStartCap(toCap(obj7));
        }
        Object obj8 = map.get("visible");
        if (obj8 != null) {
            polylineOptionsSink.setVisible(toBoolean(obj8));
        }
        Object obj9 = map.get("width");
        if (obj9 != null) {
            polylineOptionsSink.setWidth((float) toInt(obj9));
        }
        Object obj10 = map.get("zIndex");
        if (obj10 != null) {
            polylineOptionsSink.setZIndex(toFloat(obj10));
        }
        Object obj11 = map.get("points");
        if (obj11 != null) {
            polylineOptionsSink.setPoints(toPoints(obj11));
        }
        Object obj12 = map.get("pattern");
        if (obj12 != null) {
            polylineOptionsSink.setPattern(toPattern(obj12));
        }
        String str = (String) map.get("polylineId");
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("polylineId was null");
    }

    static Object latLngToJson(LatLng latLng) {
        return Arrays.asList(new Double[]{Double.valueOf(latLng.latitude), Double.valueOf(latLng.longitude)});
    }

    static Object latlngBoundsToJson(LatLngBounds latLngBounds) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("southwest", latLngToJson(latLngBounds.southwest));
        hashMap.put("northeast", latLngToJson(latLngBounds.northeast));
        return hashMap;
    }

    static Object markerIdToJson(String str) {
        if (str == null) {
            return null;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("markerId", str);
        return hashMap;
    }

    static Map<String, Integer> pointToJson(Point point) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("x", Integer.valueOf(point.x));
        hashMap.put("y", Integer.valueOf(point.y));
        return hashMap;
    }

    static Object polygonIdToJson(String str) {
        if (str == null) {
            return null;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("polygonId", str);
        return hashMap;
    }

    static Object polylineIdToJson(String str) {
        if (str == null) {
            return null;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("polylineId", str);
        return hashMap;
    }

    private static Bitmap toBitmap(Object obj) {
        byte[] bArr = (byte[]) obj;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (decodeByteArray != null) {
            return decodeByteArray;
        }
        throw new IllegalArgumentException("Unable to decode bytes as a valid bitmap.");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002f, code lost:
        if (r2.equals("defaultMarker") == false) goto L_0x0050;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.maps.model.BitmapDescriptor toBitmapDescriptor(java.lang.Object r8) {
        /*
            java.util.List r0 = toList(r8)
            r1 = 0
            java.lang.Object r2 = r0.get(r1)
            java.lang.String r2 = toString(r2)
            int r3 = r2.hashCode()
            r4 = -458749035(0xffffffffe4a80b95, float:-2.47991E22)
            r5 = 3
            r6 = 2
            r7 = 1
            if (r3 == r4) goto L_0x0046
            r4 = 52960614(0x3281d66, float:4.940451E-37)
            if (r3 == r4) goto L_0x003c
            r4 = 54063841(0x338f2e1, float:5.435155E-37)
            if (r3 == r4) goto L_0x0032
            r4 = 784458203(0x2ec1e1db, float:8.816744E-11)
            if (r3 == r4) goto L_0x0029
            goto L_0x0050
        L_0x0029:
            java.lang.String r3 = "defaultMarker"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0050
            goto L_0x0051
        L_0x0032:
            java.lang.String r1 = "fromBytes"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0050
            r1 = 3
            goto L_0x0051
        L_0x003c:
            java.lang.String r1 = "fromAsset"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0050
            r1 = 1
            goto L_0x0051
        L_0x0046:
            java.lang.String r1 = "fromAssetImage"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0050
            r1 = 2
            goto L_0x0051
        L_0x0050:
            r1 = -1
        L_0x0051:
            switch(r1) {
                case 0: goto L_0x00d7;
                case 1: goto L_0x00a7;
                case 2: goto L_0x0075;
                case 3: goto L_0x0070;
                default: goto L_0x0054;
            }
        L_0x0054:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot interpret "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = " as BitmapDescriptor"
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r0.<init>(r8)
            throw r0
        L_0x0070:
            com.google.android.gms.maps.model.BitmapDescriptor r8 = getBitmapFromBytes(r0)
            return r8
        L_0x0075:
            int r8 = r0.size()
            if (r8 != r5) goto L_0x008c
            java.lang.Object r8 = r0.get(r7)
            java.lang.String r8 = toString(r8)
            java.lang.String r8 = io.flutter.view.FlutterMain.getLookupKeyForAsset(r8)
            com.google.android.gms.maps.model.BitmapDescriptor r8 = com.google.android.gms.maps.model.BitmapDescriptorFactory.fromAsset(r8)
            return r8
        L_0x008c:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "'fromAssetImage' Expected exactly 3 arguments, got: "
            r1.append(r2)
            int r0 = r0.size()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r8.<init>(r0)
            throw r8
        L_0x00a7:
            int r8 = r0.size()
            if (r8 != r6) goto L_0x00be
            java.lang.Object r8 = r0.get(r7)
            java.lang.String r8 = toString(r8)
            java.lang.String r8 = io.flutter.view.FlutterMain.getLookupKeyForAsset(r8)
            com.google.android.gms.maps.model.BitmapDescriptor r8 = com.google.android.gms.maps.model.BitmapDescriptorFactory.fromAsset(r8)
            return r8
        L_0x00be:
            java.lang.Object r8 = r0.get(r7)
            java.lang.String r8 = toString(r8)
            java.lang.Object r0 = r0.get(r6)
            java.lang.String r0 = toString(r0)
            java.lang.String r8 = io.flutter.view.FlutterMain.getLookupKeyForAsset(r8, r0)
            com.google.android.gms.maps.model.BitmapDescriptor r8 = com.google.android.gms.maps.model.BitmapDescriptorFactory.fromAsset(r8)
            return r8
        L_0x00d7:
            int r8 = r0.size()
            if (r8 != r7) goto L_0x00e2
            com.google.android.gms.maps.model.BitmapDescriptor r8 = com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker()
            return r8
        L_0x00e2:
            java.lang.Object r8 = r0.get(r7)
            float r8 = toFloat(r8)
            com.google.android.gms.maps.model.BitmapDescriptor r8 = com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.googlemaps.Convert.toBitmapDescriptor(java.lang.Object):com.google.android.gms.maps.model.BitmapDescriptor");
    }

    private static boolean toBoolean(Object obj) {
        return ((Boolean) obj).booleanValue();
    }

    static CameraPosition toCameraPosition(Object obj) {
        Map<?, ?> map = toMap(obj);
        CameraPosition.Builder builder = CameraPosition.builder();
        builder.bearing(toFloat(map.get("bearing")));
        builder.target(toLatLng(map.get("target")));
        builder.tilt(toFloat(map.get("tilt")));
        builder.zoom(toFloat(map.get("zoom")));
        return builder.build();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0027, code lost:
        if (r2.equals("newCameraPosition") != false) goto L_0x0072;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.maps.CameraUpdate toCameraUpdate(java.lang.Object r6, float r7) {
        /*
            java.util.List r0 = toList(r6)
            r1 = 0
            java.lang.Object r2 = r0.get(r1)
            java.lang.String r2 = toString(r2)
            int r3 = r2.hashCode()
            r4 = 2
            r5 = 1
            switch(r3) {
                case -813625658: goto L_0x0067;
                case -696286326: goto L_0x005d;
                case -696286120: goto L_0x0053;
                case -696285778: goto L_0x0048;
                case -402165756: goto L_0x003e;
                case -145042503: goto L_0x0034;
                case -110027141: goto L_0x002a;
                case 354871598: goto L_0x0021;
                case 1661158683: goto L_0x0017;
                default: goto L_0x0016;
            }
        L_0x0016:
            goto L_0x0071
        L_0x0017:
            java.lang.String r1 = "newLatLngBounds"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0071
            r1 = 2
            goto L_0x0072
        L_0x0021:
            java.lang.String r3 = "newCameraPosition"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0071
            goto L_0x0072
        L_0x002a:
            java.lang.String r1 = "zoomOut"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0071
            r1 = 7
            goto L_0x0072
        L_0x0034:
            java.lang.String r1 = "newLatLngZoom"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0071
            r1 = 3
            goto L_0x0072
        L_0x003e:
            java.lang.String r1 = "scrollBy"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0071
            r1 = 4
            goto L_0x0072
        L_0x0048:
            java.lang.String r1 = "zoomTo"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0071
            r1 = 8
            goto L_0x0072
        L_0x0053:
            java.lang.String r1 = "zoomIn"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0071
            r1 = 6
            goto L_0x0072
        L_0x005d:
            java.lang.String r1 = "zoomBy"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0071
            r1 = 5
            goto L_0x0072
        L_0x0067:
            java.lang.String r1 = "newLatLng"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0071
            r1 = 1
            goto L_0x0072
        L_0x0071:
            r1 = -1
        L_0x0072:
            switch(r1) {
                case 0: goto L_0x011c;
                case 1: goto L_0x010f;
                case 2: goto L_0x00fa;
                case 3: goto L_0x00e5;
                case 4: goto L_0x00d0;
                case 5: goto L_0x00a8;
                case 6: goto L_0x00a3;
                case 7: goto L_0x009e;
                case 8: goto L_0x0091;
                default: goto L_0x0075;
            }
        L_0x0075:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Cannot interpret "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = " as CameraUpdate"
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r7.<init>(r6)
            throw r7
        L_0x0091:
            java.lang.Object r6 = r0.get(r5)
            float r6 = toFloat(r6)
            com.google.android.gms.maps.CameraUpdate r6 = com.google.android.gms.maps.CameraUpdateFactory.zoomTo(r6)
            return r6
        L_0x009e:
            com.google.android.gms.maps.CameraUpdate r6 = com.google.android.gms.maps.CameraUpdateFactory.zoomOut()
            return r6
        L_0x00a3:
            com.google.android.gms.maps.CameraUpdate r6 = com.google.android.gms.maps.CameraUpdateFactory.zoomIn()
            return r6
        L_0x00a8:
            int r6 = r0.size()
            if (r6 != r4) goto L_0x00bb
            java.lang.Object r6 = r0.get(r5)
            float r6 = toFloat(r6)
            com.google.android.gms.maps.CameraUpdate r6 = com.google.android.gms.maps.CameraUpdateFactory.zoomBy(r6)
            return r6
        L_0x00bb:
            java.lang.Object r6 = r0.get(r5)
            float r6 = toFloat(r6)
            java.lang.Object r0 = r0.get(r4)
            android.graphics.Point r7 = toPoint(r0, r7)
            com.google.android.gms.maps.CameraUpdate r6 = com.google.android.gms.maps.CameraUpdateFactory.zoomBy(r6, r7)
            return r6
        L_0x00d0:
            java.lang.Object r6 = r0.get(r5)
            float r6 = toFractionalPixels(r6, r7)
            java.lang.Object r0 = r0.get(r4)
            float r7 = toFractionalPixels(r0, r7)
            com.google.android.gms.maps.CameraUpdate r6 = com.google.android.gms.maps.CameraUpdateFactory.scrollBy(r6, r7)
            return r6
        L_0x00e5:
            java.lang.Object r6 = r0.get(r5)
            com.google.android.gms.maps.model.LatLng r6 = toLatLng(r6)
            java.lang.Object r7 = r0.get(r4)
            float r7 = toFloat(r7)
            com.google.android.gms.maps.CameraUpdate r6 = com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(r6, r7)
            return r6
        L_0x00fa:
            java.lang.Object r6 = r0.get(r5)
            com.google.android.gms.maps.model.LatLngBounds r6 = toLatLngBounds(r6)
            java.lang.Object r0 = r0.get(r4)
            int r7 = toPixels(r0, r7)
            com.google.android.gms.maps.CameraUpdate r6 = com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds(r6, r7)
            return r6
        L_0x010f:
            java.lang.Object r6 = r0.get(r5)
            com.google.android.gms.maps.model.LatLng r6 = toLatLng(r6)
            com.google.android.gms.maps.CameraUpdate r6 = com.google.android.gms.maps.CameraUpdateFactory.newLatLng(r6)
            return r6
        L_0x011c:
            java.lang.Object r6 = r0.get(r5)
            com.google.android.gms.maps.model.CameraPosition r6 = toCameraPosition(r6)
            com.google.android.gms.maps.CameraUpdate r6 = com.google.android.gms.maps.CameraUpdateFactory.newCameraPosition(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.googlemaps.Convert.toCameraUpdate(java.lang.Object, float):com.google.android.gms.maps.CameraUpdate");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        if (r2.equals("buttCap") != false) goto L_0x0050;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.maps.model.Cap toCap(java.lang.Object r7) {
        /*
            java.util.List r0 = toList(r7)
            r1 = 0
            java.lang.Object r2 = r0.get(r1)
            java.lang.String r2 = toString(r2)
            int r3 = r2.hashCode()
            r4 = -179356(0xfffffffffffd4364, float:NaN)
            r5 = 1
            r6 = 2
            if (r3 == r4) goto L_0x0045
            r4 = 241309887(0xe6218bf, float:2.7868566E-30)
            if (r3 == r4) goto L_0x003c
            r1 = 1314340213(0x4e573d75, float:9.0278227E8)
            if (r3 == r1) goto L_0x0032
            r1 = 1611528865(0x600dfaa1, float:4.0922666E19)
            if (r3 == r1) goto L_0x0028
            goto L_0x004f
        L_0x0028:
            java.lang.String r1 = "customCap"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x004f
            r1 = 3
            goto L_0x0050
        L_0x0032:
            java.lang.String r1 = "squareCap"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x004f
            r1 = 2
            goto L_0x0050
        L_0x003c:
            java.lang.String r3 = "buttCap"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x004f
            goto L_0x0050
        L_0x0045:
            java.lang.String r1 = "roundCap"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x004f
            r1 = 1
            goto L_0x0050
        L_0x004f:
            r1 = -1
        L_0x0050:
            switch(r1) {
                case 0: goto L_0x00a5;
                case 1: goto L_0x009f;
                case 2: goto L_0x0099;
                case 3: goto L_0x006f;
                default: goto L_0x0053;
            }
        L_0x0053:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot interpret "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = " as Cap"
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r0.<init>(r7)
            throw r0
        L_0x006f:
            int r7 = r0.size()
            if (r7 != r6) goto L_0x0083
            com.google.android.gms.maps.model.CustomCap r7 = new com.google.android.gms.maps.model.CustomCap
            java.lang.Object r0 = r0.get(r5)
            com.google.android.gms.maps.model.BitmapDescriptor r0 = toBitmapDescriptor(r0)
            r7.<init>(r0)
            return r7
        L_0x0083:
            com.google.android.gms.maps.model.CustomCap r7 = new com.google.android.gms.maps.model.CustomCap
            java.lang.Object r1 = r0.get(r5)
            com.google.android.gms.maps.model.BitmapDescriptor r1 = toBitmapDescriptor(r1)
            java.lang.Object r0 = r0.get(r6)
            float r0 = toFloat(r0)
            r7.<init>(r1, r0)
            return r7
        L_0x0099:
            com.google.android.gms.maps.model.SquareCap r7 = new com.google.android.gms.maps.model.SquareCap
            r7.<init>()
            return r7
        L_0x009f:
            com.google.android.gms.maps.model.RoundCap r7 = new com.google.android.gms.maps.model.RoundCap
            r7.<init>()
            return r7
        L_0x00a5:
            com.google.android.gms.maps.model.ButtCap r7 = new com.google.android.gms.maps.model.ButtCap
            r7.<init>()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.googlemaps.Convert.toCap(java.lang.Object):com.google.android.gms.maps.model.Cap");
    }

    private static double toDouble(Object obj) {
        return ((Number) obj).doubleValue();
    }

    private static float toFloat(Object obj) {
        return ((Number) obj).floatValue();
    }

    private static Float toFloatWrapper(Object obj) {
        if (obj == null) {
            return null;
        }
        return Float.valueOf(toFloat(obj));
    }

    private static float toFractionalPixels(Object obj, float f) {
        return toFloat(obj) * f;
    }

    private static int toInt(Object obj) {
        return ((Number) obj).intValue();
    }

    static LatLng toLatLng(Object obj) {
        List<?> list = toList(obj);
        return new LatLng(toDouble(list.get(0)), toDouble(list.get(1)));
    }

    private static LatLngBounds toLatLngBounds(Object obj) {
        if (obj == null) {
            return null;
        }
        List<?> list = toList(obj);
        return new LatLngBounds(toLatLng(list.get(0)), toLatLng(list.get(1)));
    }

    private static List<?> toList(Object obj) {
        return (List) obj;
    }

    private static Map<?, ?> toMap(Object obj) {
        return (Map) obj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0060, code lost:
        if (r3.equals("dot") != false) goto L_0x0064;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.google.android.gms.maps.model.PatternItem> toPattern(java.lang.Object r8) {
        /*
            java.util.List r8 = toList(r8)
            boolean r0 = r8.isEmpty()
            if (r0 == 0) goto L_0x000c
            r8 = 0
            return r8
        L_0x000c:
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r8.size()
            r0.<init>(r1)
            java.util.Iterator r8 = r8.iterator()
        L_0x0019:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x00ad
            java.lang.Object r1 = r8.next()
            java.util.List r1 = toList(r1)
            r2 = 0
            java.lang.Object r3 = r1.get(r2)
            java.lang.String r3 = toString(r3)
            r4 = -1
            int r5 = r3.hashCode()
            r6 = 99657(0x18549, float:1.39649E-40)
            r7 = 1
            if (r5 == r6) goto L_0x005a
            r2 = 102102(0x18ed6, float:1.43075E-40)
            if (r5 == r2) goto L_0x0050
            r2 = 3075986(0x2eef92, float:4.310374E-39)
            if (r5 == r2) goto L_0x0046
            goto L_0x0063
        L_0x0046:
            java.lang.String r2 = "dash"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0063
            r2 = 1
            goto L_0x0064
        L_0x0050:
            java.lang.String r2 = "gap"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0063
            r2 = 2
            goto L_0x0064
        L_0x005a:
            java.lang.String r5 = "dot"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0063
            goto L_0x0064
        L_0x0063:
            r2 = -1
        L_0x0064:
            switch(r2) {
                case 0: goto L_0x00a3;
                case 1: goto L_0x0091;
                case 2: goto L_0x0083;
                default: goto L_0x0067;
            }
        L_0x0067:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot interpret "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = " as PatternItem"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r8.<init>(r0)
            throw r8
        L_0x0083:
            com.google.android.gms.maps.model.Gap r2 = new com.google.android.gms.maps.model.Gap
            java.lang.Object r1 = r1.get(r7)
            float r1 = toFloat(r1)
            r2.<init>(r1)
            goto L_0x009e
        L_0x0091:
            com.google.android.gms.maps.model.Dash r2 = new com.google.android.gms.maps.model.Dash
            java.lang.Object r1 = r1.get(r7)
            float r1 = toFloat(r1)
            r2.<init>(r1)
        L_0x009e:
            r0.add(r2)
            goto L_0x0019
        L_0x00a3:
            com.google.android.gms.maps.model.Dot r1 = new com.google.android.gms.maps.model.Dot
            r1.<init>()
            r0.add(r1)
            goto L_0x0019
        L_0x00ad:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.googlemaps.Convert.toPattern(java.lang.Object):java.util.List");
    }

    private static int toPixels(Object obj, float f) {
        return (int) toFractionalPixels(obj, f);
    }

    static Point toPoint(Object obj) {
        Map map = (Map) obj;
        return new Point(((Integer) map.get("x")).intValue(), ((Integer) map.get("y")).intValue());
    }

    private static Point toPoint(Object obj, float f) {
        List<?> list = toList(obj);
        return new Point(toPixels(list.get(0), f), toPixels(list.get(1), f));
    }

    private static List<LatLng> toPoints(Object obj) {
        List<?> list = toList(obj);
        ArrayList arrayList = new ArrayList(list.size());
        for (Object list2 : list) {
            List<?> list3 = toList(list2);
            arrayList.add(new LatLng((double) toFloat(list3.get(0)), (double) toFloat(list3.get(1))));
        }
        return arrayList;
    }

    private static String toString(Object obj) {
        return (String) obj;
    }
}
