package com.google.firebase.auth.internal;

import android.os.Parcelable;

public final class zzl implements Parcelable.Creator<zzm> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v7, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r18) {
        /*
            r17 = this;
            r0 = r18
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r18)
            r2 = 0
            r3 = 0
            r5 = r2
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r15 = r13
            r16 = r15
            r14 = 0
        L_0x0015:
            int r2 = r18.dataPosition()
            if (r2 >= r1) goto L_0x0082
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r18)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 1: goto L_0x0078;
                case 2: goto L_0x006e;
                case 3: goto L_0x0069;
                case 4: goto L_0x0064;
                case 5: goto L_0x005d;
                case 6: goto L_0x0058;
                case 7: goto L_0x0053;
                case 8: goto L_0x004e;
                case 9: goto L_0x0044;
                case 10: goto L_0x003f;
                case 11: goto L_0x0035;
                case 12: goto L_0x002a;
                default: goto L_0x0026;
            }
        L_0x0026:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0015
        L_0x002a:
            android.os.Parcelable$Creator<com.google.firebase.auth.internal.zzao> r3 = com.google.firebase.auth.internal.zzao.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r16 = r2
            com.google.firebase.auth.internal.zzao r16 = (com.google.firebase.auth.internal.zzao) r16
            goto L_0x0015
        L_0x0035:
            android.os.Parcelable$Creator<com.google.firebase.auth.zzf> r3 = com.google.firebase.auth.zzf.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r15 = r2
            com.google.firebase.auth.zzf r15 = (com.google.firebase.auth.zzf) r15
            goto L_0x0015
        L_0x003f:
            boolean r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0015
        L_0x0044:
            android.os.Parcelable$Creator<com.google.firebase.auth.internal.zzo> r3 = com.google.firebase.auth.internal.zzo.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r13 = r2
            com.google.firebase.auth.internal.zzo r13 = (com.google.firebase.auth.internal.zzo) r13
            goto L_0x0015
        L_0x004e:
            java.lang.Boolean r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBooleanObject(r0, r2)
            goto L_0x0015
        L_0x0053:
            java.lang.String r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0015
        L_0x0058:
            java.util.ArrayList r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x0015
        L_0x005d:
            android.os.Parcelable$Creator<com.google.firebase.auth.internal.zzi> r3 = com.google.firebase.auth.internal.zzi.CREATOR
            java.util.ArrayList r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x0015
        L_0x0064:
            java.lang.String r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0015
        L_0x0069:
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0015
        L_0x006e:
            android.os.Parcelable$Creator<com.google.firebase.auth.internal.zzi> r3 = com.google.firebase.auth.internal.zzi.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r6 = r2
            com.google.firebase.auth.internal.zzi r6 = (com.google.firebase.auth.internal.zzi) r6
            goto L_0x0015
        L_0x0078:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzes> r3 = com.google.android.gms.internal.firebase_auth.zzes.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r5 = r2
            com.google.android.gms.internal.firebase_auth.zzes r5 = (com.google.android.gms.internal.firebase_auth.zzes) r5
            goto L_0x0015
        L_0x0082:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.firebase.auth.internal.zzm r0 = new com.google.firebase.auth.internal.zzm
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.zzl.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzm[i];
    }
}
