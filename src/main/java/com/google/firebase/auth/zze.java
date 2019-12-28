package com.google.firebase.auth;

import android.os.Parcelable;

public final class zze implements Parcelable.Creator<zzf> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r9) {
        /*
            r8 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r9)
            r1 = 0
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
        L_0x000a:
            int r1 = r9.dataPosition()
            if (r1 >= r0) goto L_0x003d
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r9)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 1: goto L_0x0038;
                case 2: goto L_0x0033;
                case 3: goto L_0x002e;
                case 4: goto L_0x0024;
                case 5: goto L_0x001f;
                default: goto L_0x001b;
            }
        L_0x001b:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r9, r1)
            goto L_0x000a
        L_0x001f:
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r9, r1)
            goto L_0x000a
        L_0x0024:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzfm> r2 = com.google.android.gms.internal.firebase_auth.zzfm.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r1, r2)
            r6 = r1
            com.google.android.gms.internal.firebase_auth.zzfm r6 = (com.google.android.gms.internal.firebase_auth.zzfm) r6
            goto L_0x000a
        L_0x002e:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r9, r1)
            goto L_0x000a
        L_0x0033:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r9, r1)
            goto L_0x000a
        L_0x0038:
            java.lang.String r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r9, r1)
            goto L_0x000a
        L_0x003d:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r9, r0)
            com.google.firebase.auth.zzf r9 = new com.google.firebase.auth.zzf
            r2 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.zze.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzf[i];
    }
}
