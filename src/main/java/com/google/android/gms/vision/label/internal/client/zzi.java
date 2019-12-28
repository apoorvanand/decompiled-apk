package com.google.android.gms.vision.label.internal.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.vision.zzm;
import com.google.android.gms.vision.label.ImageLabel;

public final class zzi extends zzm<INativeImageLabeler> {
    private ImageLabelerOptions zzdr;

    public zzi(Context context, ImageLabelerOptions imageLabelerOptions) {
        super(context, "LabelerNativeHandle", "imagelabel");
        this.zzdr = imageLabelerOptions;
        b();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object a(DynamiteModule dynamiteModule, Context context) {
        zzb asInterface = zzc.asInterface(dynamiteModule.instantiate("com.google.android.gms.vision.label.ChimeraNativeImageLabelerCreator"));
        if (asInterface == null) {
            return null;
        }
        return asInterface.newImageLabeler(ObjectWrapper.wrap(context), this.zzdr);
    }

    /* access modifiers changed from: protected */
    public final void a() {
        ((INativeImageLabeler) b()).zzs();
    }

    public final ImageLabel[] zza(Bitmap bitmap, LabelOptions labelOptions) {
        if (!isOperational()) {
            return new ImageLabel[0];
        }
        try {
            zze[] zza = ((INativeImageLabeler) b()).zza(ObjectWrapper.wrap(bitmap), labelOptions);
            ImageLabel[] imageLabelArr = new ImageLabel[zza.length];
            for (int i = 0; i != zza.length; i++) {
                imageLabelArr[i] = new ImageLabel(zza[i].zzdn, zza[i].label, zza[i].zzdo);
            }
            return imageLabelArr;
        } catch (RemoteException e) {
            Log.e("LabelerNativeHandle", "Error calling native image labeler", e);
            return new ImageLabel[0];
        }
    }
}