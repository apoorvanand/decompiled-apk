package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzad;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzh;
import com.google.android.gms.internal.maps.zzi;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzl;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzo;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.internal.maps.zzu;
import com.google.android.gms.internal.maps.zzw;
import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.internal.maps.zzz;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

public final class zzg extends zza implements IGoogleMapDelegate {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    public final zzh addCircle(CircleOptions circleOptions) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) circleOptions);
        Parcel a2 = a(35, a);
        zzh zzc = zzi.zzc(a2.readStrongBinder());
        a2.recycle();
        return zzc;
    }

    public final zzk addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) groundOverlayOptions);
        Parcel a2 = a(12, a);
        zzk zzd = zzl.zzd(a2.readStrongBinder());
        a2.recycle();
        return zzd;
    }

    public final zzt addMarker(MarkerOptions markerOptions) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) markerOptions);
        Parcel a2 = a(11, a);
        zzt zzg = zzu.zzg(a2.readStrongBinder());
        a2.recycle();
        return zzg;
    }

    public final zzw addPolygon(PolygonOptions polygonOptions) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) polygonOptions);
        Parcel a2 = a(10, a);
        zzw zzh = zzx.zzh(a2.readStrongBinder());
        a2.recycle();
        return zzh;
    }

    public final zzz addPolyline(PolylineOptions polylineOptions) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) polylineOptions);
        Parcel a2 = a(9, a);
        zzz zzi = zzaa.zzi(a2.readStrongBinder());
        a2.recycle();
        return zzi;
    }

    public final zzac addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) tileOverlayOptions);
        Parcel a2 = a(13, a);
        zzac zzj = zzad.zzj(a2.readStrongBinder());
        a2.recycle();
        return zzj;
    }

    public final void animateCamera(IObjectWrapper iObjectWrapper) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        b(5, a);
    }

    public final void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzc zzc) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        zzc.zza(a, (IInterface) zzc);
        b(6, a);
    }

    public final void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzc zzc) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        a.writeInt(i);
        zzc.zza(a, (IInterface) zzc);
        b(7, a);
    }

    public final void clear() {
        b(14, a());
    }

    public final CameraPosition getCameraPosition() {
        Parcel a = a(1, a());
        CameraPosition cameraPosition = (CameraPosition) zzc.zza(a, CameraPosition.CREATOR);
        a.recycle();
        return cameraPosition;
    }

    public final zzn getFocusedBuilding() {
        Parcel a = a(44, a());
        zzn zze = zzo.zze(a.readStrongBinder());
        a.recycle();
        return zze;
    }

    public final void getMapAsync(zzap zzap) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzap);
        b(53, a);
    }

    public final int getMapType() {
        Parcel a = a(15, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final float getMaxZoomLevel() {
        Parcel a = a(2, a());
        float readFloat = a.readFloat();
        a.recycle();
        return readFloat;
    }

    public final float getMinZoomLevel() {
        Parcel a = a(3, a());
        float readFloat = a.readFloat();
        a.recycle();
        return readFloat;
    }

    public final Location getMyLocation() {
        Parcel a = a(23, a());
        Location location = (Location) zzc.zza(a, Location.CREATOR);
        a.recycle();
        return location;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IProjectionDelegate getProjection() {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.a()
            r1 = 26
            android.os.Parcel r0 = r4.a(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.maps.internal.IProjectionDelegate"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.maps.internal.IProjectionDelegate
            if (r3 == 0) goto L_0x0020
            r1 = r2
            com.google.android.gms.maps.internal.IProjectionDelegate r1 = (com.google.android.gms.maps.internal.IProjectionDelegate) r1
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.maps.internal.zzbr r2 = new com.google.android.gms.maps.internal.zzbr
            r2.<init>(r1)
            r1 = r2
        L_0x0026:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzg.getProjection():com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IUiSettingsDelegate getUiSettings() {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.a()
            r1 = 25
            android.os.Parcel r0 = r4.a(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.maps.internal.IUiSettingsDelegate"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.maps.internal.IUiSettingsDelegate
            if (r3 == 0) goto L_0x0020
            r1 = r2
            com.google.android.gms.maps.internal.IUiSettingsDelegate r1 = (com.google.android.gms.maps.internal.IUiSettingsDelegate) r1
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.maps.internal.zzbx r2 = new com.google.android.gms.maps.internal.zzbx
            r2.<init>(r1)
            r1 = r2
        L_0x0026:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzg.getUiSettings():com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    public final boolean isBuildingsEnabled() {
        Parcel a = a(40, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isIndoorEnabled() {
        Parcel a = a(19, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isMyLocationEnabled() {
        Parcel a = a(21, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isTrafficEnabled() {
        Parcel a = a(17, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final void moveCamera(IObjectWrapper iObjectWrapper) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        b(4, a);
    }

    public final void onCreate(Bundle bundle) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) bundle);
        b(54, a);
    }

    public final void onDestroy() {
        b(57, a());
    }

    public final void onEnterAmbient(Bundle bundle) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) bundle);
        b(81, a);
    }

    public final void onExitAmbient() {
        b(82, a());
    }

    public final void onLowMemory() {
        b(58, a());
    }

    public final void onPause() {
        b(56, a());
    }

    public final void onResume() {
        b(55, a());
    }

    public final void onSaveInstanceState(Bundle bundle) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) bundle);
        Parcel a2 = a(60, a);
        if (a2.readInt() != 0) {
            bundle.readFromParcel(a2);
        }
        a2.recycle();
    }

    public final void onStart() {
        b(101, a());
    }

    public final void onStop() {
        b(102, a());
    }

    public final void resetMinMaxZoomPreference() {
        b(94, a());
    }

    public final void setBuildingsEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(41, a);
    }

    public final void setContentDescription(String str) {
        Parcel a = a();
        a.writeString(str);
        b(61, a);
    }

    public final boolean setIndoorEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        Parcel a2 = a(20, a);
        boolean zza = zzc.zza(a2);
        a2.recycle();
        return zza;
    }

    public final void setInfoWindowAdapter(zzh zzh) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzh);
        b(33, a);
    }

    public final void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLngBounds);
        b(95, a);
    }

    public final void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iLocationSourceDelegate);
        b(24, a);
    }

    public final boolean setMapStyle(MapStyleOptions mapStyleOptions) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) mapStyleOptions);
        Parcel a2 = a(91, a);
        boolean zza = zzc.zza(a2);
        a2.recycle();
        return zza;
    }

    public final void setMapType(int i) {
        Parcel a = a();
        a.writeInt(i);
        b(16, a);
    }

    public final void setMaxZoomPreference(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(93, a);
    }

    public final void setMinZoomPreference(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(92, a);
    }

    public final void setMyLocationEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(22, a);
    }

    public final void setOnCameraChangeListener(zzl zzl) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzl);
        b(27, a);
    }

    public final void setOnCameraIdleListener(zzn zzn) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzn);
        b(99, a);
    }

    public final void setOnCameraMoveCanceledListener(zzp zzp) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzp);
        b(98, a);
    }

    public final void setOnCameraMoveListener(zzr zzr) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzr);
        b(97, a);
    }

    public final void setOnCameraMoveStartedListener(zzt zzt) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzt);
        b(96, a);
    }

    public final void setOnCircleClickListener(zzv zzv) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzv);
        b(89, a);
    }

    public final void setOnGroundOverlayClickListener(zzx zzx) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzx);
        b(83, a);
    }

    public final void setOnIndoorStateChangeListener(zzz zzz) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzz);
        b(45, a);
    }

    public final void setOnInfoWindowClickListener(zzab zzab) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzab);
        b(32, a);
    }

    public final void setOnInfoWindowCloseListener(zzad zzad) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzad);
        b(86, a);
    }

    public final void setOnInfoWindowLongClickListener(zzaf zzaf) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzaf);
        b(84, a);
    }

    public final void setOnMapClickListener(zzaj zzaj) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzaj);
        b(28, a);
    }

    public final void setOnMapLoadedCallback(zzal zzal) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzal);
        b(42, a);
    }

    public final void setOnMapLongClickListener(zzan zzan) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzan);
        b(29, a);
    }

    public final void setOnMarkerClickListener(zzar zzar) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzar);
        b(30, a);
    }

    public final void setOnMarkerDragListener(zzat zzat) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzat);
        b(31, a);
    }

    public final void setOnMyLocationButtonClickListener(zzav zzav) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzav);
        b(37, a);
    }

    public final void setOnMyLocationChangeListener(zzax zzax) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzax);
        b(36, a);
    }

    public final void setOnMyLocationClickListener(zzaz zzaz) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzaz);
        b(107, a);
    }

    public final void setOnPoiClickListener(zzbb zzbb) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzbb);
        b(80, a);
    }

    public final void setOnPolygonClickListener(zzbd zzbd) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzbd);
        b(85, a);
    }

    public final void setOnPolylineClickListener(zzbf zzbf) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzbf);
        b(87, a);
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
        Parcel a = a();
        a.writeInt(i);
        a.writeInt(i2);
        a.writeInt(i3);
        a.writeInt(i4);
        b(39, a);
    }

    public final void setTrafficEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(18, a);
    }

    public final void setWatermarkEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(51, a);
    }

    public final void snapshot(zzbs zzbs, IObjectWrapper iObjectWrapper) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzbs);
        zzc.zza(a, (IInterface) iObjectWrapper);
        b(38, a);
    }

    public final void snapshotForTest(zzbs zzbs) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzbs);
        b(71, a);
    }

    public final void stopAnimation() {
        b(8, a());
    }

    public final boolean useViewLifecycleWhenInFragment() {
        Parcel a = a(59, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }
}
