package com.google.firebase.ml.vision.common;

public class FirebaseVisionLatLng {
    private final double zzava;
    private final double zzavb;

    public FirebaseVisionLatLng(double d, double d2) {
        this.zzava = d;
        this.zzavb = d2;
    }

    public double getLatitude() {
        return this.zzava;
    }

    public double getLongitude() {
        return this.zzavb;
    }
}
