package com.google.firebase.auth.api.internal;

import com.google.android.gms.internal.firebase_auth.zza;

public abstract class zzdy extends zza implements zzdz {
    public zzdy() {
        super("com.google.firebase.auth.api.internal.IFirebaseAuthService");
    }

    /* JADX WARNING: type inference failed for: r5v17, types: [android.os.IInterface] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) {
        /*
            r3 = this;
            r7 = 0
            switch(r4) {
                case 1: goto L_0x08e4;
                case 2: goto L_0x08c3;
                case 3: goto L_0x089e;
                case 4: goto L_0x0875;
                case 5: goto L_0x084f;
                case 6: goto L_0x0829;
                case 7: goto L_0x0803;
                case 8: goto L_0x07dd;
                case 9: goto L_0x07bb;
                case 10: goto L_0x0799;
                case 11: goto L_0x076f;
                case 12: goto L_0x0745;
                case 13: goto L_0x0723;
                case 14: goto L_0x06fd;
                case 15: goto L_0x06db;
                case 16: goto L_0x06bc;
                case 17: goto L_0x069a;
                case 18: goto L_0x0678;
                case 19: goto L_0x0656;
                case 20: goto L_0x0634;
                case 21: goto L_0x060e;
                case 22: goto L_0x05e8;
                case 23: goto L_0x05c2;
                case 24: goto L_0x0598;
                case 25: goto L_0x056e;
                case 26: goto L_0x0544;
                case 27: goto L_0x0522;
                case 28: goto L_0x04f8;
                case 29: goto L_0x04d2;
                default: goto L_0x0004;
            }
        L_0x0004:
            switch(r4) {
                case 101: goto L_0x04ac;
                case 102: goto L_0x0486;
                case 103: goto L_0x0460;
                case 104: goto L_0x043a;
                case 105: goto L_0x0414;
                case 106: goto L_0x03ee;
                case 107: goto L_0x03c8;
                case 108: goto L_0x03a2;
                case 109: goto L_0x037c;
                default: goto L_0x0007;
            }
        L_0x0007:
            switch(r4) {
                case 111: goto L_0x0356;
                case 112: goto L_0x0330;
                case 113: goto L_0x030a;
                case 114: goto L_0x02e4;
                case 115: goto L_0x02be;
                case 116: goto L_0x0298;
                case 117: goto L_0x0272;
                default: goto L_0x000a;
            }
        L_0x000a:
            switch(r4) {
                case 119: goto L_0x024c;
                case 120: goto L_0x0226;
                case 121: goto L_0x0200;
                case 122: goto L_0x01da;
                case 123: goto L_0x01b4;
                case 124: goto L_0x018e;
                default: goto L_0x000d;
            }
        L_0x000d:
            switch(r4) {
                case 126: goto L_0x0168;
                case 127: goto L_0x0142;
                case 128: goto L_0x011c;
                case 129: goto L_0x00f6;
                case 130: goto L_0x00d0;
                case 131: goto L_0x00aa;
                case 132: goto L_0x0084;
                case 133: goto L_0x005e;
                case 134: goto L_0x0038;
                case 135: goto L_0x0012;
                default: goto L_0x0010;
            }
        L_0x0010:
            r4 = 0
            return r4
        L_0x0012:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzdx> r4 = com.google.android.gms.internal.firebase_auth.zzdx.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzdx r4 = (com.google.android.gms.internal.firebase_auth.zzdx) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0021
            goto L_0x0033
        L_0x0021:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x002e
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0033
        L_0x002e:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0033:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzdx) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0038:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzcd> r4 = com.google.android.gms.internal.firebase_auth.zzcd.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzcd r4 = (com.google.android.gms.internal.firebase_auth.zzcd) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0047
            goto L_0x0059
        L_0x0047:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0054
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0059
        L_0x0054:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0059:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzcd) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x005e:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzdn> r4 = com.google.android.gms.internal.firebase_auth.zzdn.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzdn r4 = (com.google.android.gms.internal.firebase_auth.zzdn) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x006d
            goto L_0x007f
        L_0x006d:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x007a
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x007f
        L_0x007a:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x007f:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzdn) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0084:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzcb> r4 = com.google.android.gms.internal.firebase_auth.zzcb.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzcb r4 = (com.google.android.gms.internal.firebase_auth.zzcb) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0093
            goto L_0x00a5
        L_0x0093:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x00a0
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x00a5
        L_0x00a0:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x00a5:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzcb) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x00aa:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzdp> r4 = com.google.android.gms.internal.firebase_auth.zzdp.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzdp r4 = (com.google.android.gms.internal.firebase_auth.zzdp) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x00b9
            goto L_0x00cb
        L_0x00b9:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x00c6
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x00cb
        L_0x00c6:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x00cb:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzdp) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x00d0:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzdl> r4 = com.google.android.gms.internal.firebase_auth.zzdl.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzdl r4 = (com.google.android.gms.internal.firebase_auth.zzdl) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x00df
            goto L_0x00f1
        L_0x00df:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x00ec
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x00f1
        L_0x00ec:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x00f1:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzdl) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x00f6:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzdh> r4 = com.google.android.gms.internal.firebase_auth.zzdh.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzdh r4 = (com.google.android.gms.internal.firebase_auth.zzdh) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0105
            goto L_0x0117
        L_0x0105:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0112
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0117
        L_0x0112:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0117:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzdh) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x011c:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzct> r4 = com.google.android.gms.internal.firebase_auth.zzct.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzct r4 = (com.google.android.gms.internal.firebase_auth.zzct) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x012b
            goto L_0x013d
        L_0x012b:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0138
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x013d
        L_0x0138:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x013d:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzct) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0142:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzcx> r4 = com.google.android.gms.internal.firebase_auth.zzcx.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzcx r4 = (com.google.android.gms.internal.firebase_auth.zzcx) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0151
            goto L_0x0163
        L_0x0151:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x015e
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0163
        L_0x015e:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0163:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzcx) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0168:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzcr> r4 = com.google.android.gms.internal.firebase_auth.zzcr.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzcr r4 = (com.google.android.gms.internal.firebase_auth.zzcr) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0177
            goto L_0x0189
        L_0x0177:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0184
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0189
        L_0x0184:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0189:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzcr) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x018e:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzcn> r4 = com.google.android.gms.internal.firebase_auth.zzcn.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzcn r4 = (com.google.android.gms.internal.firebase_auth.zzcn) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x019d
            goto L_0x01af
        L_0x019d:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x01aa
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x01af
        L_0x01aa:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x01af:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzcn) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x01b4:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzdj> r4 = com.google.android.gms.internal.firebase_auth.zzdj.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzdj r4 = (com.google.android.gms.internal.firebase_auth.zzdj) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x01c3
            goto L_0x01d5
        L_0x01c3:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x01d0
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x01d5
        L_0x01d0:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x01d5:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzdj) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x01da:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzcv> r4 = com.google.android.gms.internal.firebase_auth.zzcv.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzcv r4 = (com.google.android.gms.internal.firebase_auth.zzcv) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x01e9
            goto L_0x01fb
        L_0x01e9:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x01f6
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x01fb
        L_0x01f6:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x01fb:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzcv) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0200:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzbv> r4 = com.google.android.gms.internal.firebase_auth.zzbv.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzbv r4 = (com.google.android.gms.internal.firebase_auth.zzbv) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x020f
            goto L_0x0221
        L_0x020f:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x021c
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0221
        L_0x021c:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0221:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzbv) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0226:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzbn> r4 = com.google.android.gms.internal.firebase_auth.zzbn.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzbn r4 = (com.google.android.gms.internal.firebase_auth.zzbn) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0235
            goto L_0x0247
        L_0x0235:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0242
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0247
        L_0x0242:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0247:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzbn) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x024c:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzbt> r4 = com.google.android.gms.internal.firebase_auth.zzbt.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzbt r4 = (com.google.android.gms.internal.firebase_auth.zzbt) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x025b
            goto L_0x026d
        L_0x025b:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0268
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x026d
        L_0x0268:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x026d:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzbt) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0272:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzbz> r4 = com.google.android.gms.internal.firebase_auth.zzbz.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzbz r4 = (com.google.android.gms.internal.firebase_auth.zzbz) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0281
            goto L_0x0293
        L_0x0281:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x028e
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0293
        L_0x028e:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0293:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzbz) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0298:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzcz> r4 = com.google.android.gms.internal.firebase_auth.zzcz.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzcz r4 = (com.google.android.gms.internal.firebase_auth.zzcz) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x02a7
            goto L_0x02b9
        L_0x02a7:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x02b4
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x02b9
        L_0x02b4:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x02b9:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzcz) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x02be:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzcp> r4 = com.google.android.gms.internal.firebase_auth.zzcp.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzcp r4 = (com.google.android.gms.internal.firebase_auth.zzcp) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x02cd
            goto L_0x02df
        L_0x02cd:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x02da
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x02df
        L_0x02da:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x02df:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzcp) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x02e4:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzdt> r4 = com.google.android.gms.internal.firebase_auth.zzdt.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzdt r4 = (com.google.android.gms.internal.firebase_auth.zzdt) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x02f3
            goto L_0x0305
        L_0x02f3:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0300
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0305
        L_0x0300:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0305:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzdt) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x030a:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzdr> r4 = com.google.android.gms.internal.firebase_auth.zzdr.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzdr r4 = (com.google.android.gms.internal.firebase_auth.zzdr) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0319
            goto L_0x032b
        L_0x0319:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0326
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x032b
        L_0x0326:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x032b:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzdr) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0330:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzcl> r4 = com.google.android.gms.internal.firebase_auth.zzcl.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzcl r4 = (com.google.android.gms.internal.firebase_auth.zzcl) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x033f
            goto L_0x0351
        L_0x033f:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x034c
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0351
        L_0x034c:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0351:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzcl) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0356:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzcj> r4 = com.google.android.gms.internal.firebase_auth.zzcj.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzcj r4 = (com.google.android.gms.internal.firebase_auth.zzcj) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0365
            goto L_0x0377
        L_0x0365:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0372
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0377
        L_0x0372:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0377:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzcj) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x037c:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzch> r4 = com.google.android.gms.internal.firebase_auth.zzch.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzch r4 = (com.google.android.gms.internal.firebase_auth.zzch) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x038b
            goto L_0x039d
        L_0x038b:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0398
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x039d
        L_0x0398:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x039d:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzch) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x03a2:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzdf> r4 = com.google.android.gms.internal.firebase_auth.zzdf.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzdf r4 = (com.google.android.gms.internal.firebase_auth.zzdf) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x03b1
            goto L_0x03c3
        L_0x03b1:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x03be
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x03c3
        L_0x03be:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x03c3:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzdf) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x03c8:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzbx> r4 = com.google.android.gms.internal.firebase_auth.zzbx.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzbx r4 = (com.google.android.gms.internal.firebase_auth.zzbx) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x03d7
            goto L_0x03e9
        L_0x03d7:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x03e4
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x03e9
        L_0x03e4:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x03e9:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzbx) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x03ee:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzbr> r4 = com.google.android.gms.internal.firebase_auth.zzbr.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzbr r4 = (com.google.android.gms.internal.firebase_auth.zzbr) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x03fd
            goto L_0x040f
        L_0x03fd:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x040a
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x040f
        L_0x040a:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x040f:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzbr) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0414:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzbp> r4 = com.google.android.gms.internal.firebase_auth.zzbp.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzbp r4 = (com.google.android.gms.internal.firebase_auth.zzbp) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0423
            goto L_0x0435
        L_0x0423:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0430
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0435
        L_0x0430:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0435:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzbp) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x043a:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzdv> r4 = com.google.android.gms.internal.firebase_auth.zzdv.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzdv r4 = (com.google.android.gms.internal.firebase_auth.zzdv) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0449
            goto L_0x045b
        L_0x0449:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0456
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x045b
        L_0x0456:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x045b:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzdv) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0460:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzdb> r4 = com.google.android.gms.internal.firebase_auth.zzdb.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzdb r4 = (com.google.android.gms.internal.firebase_auth.zzdb) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x046f
            goto L_0x0481
        L_0x046f:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x047c
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0481
        L_0x047c:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0481:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzdb) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0486:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzdd> r4 = com.google.android.gms.internal.firebase_auth.zzdd.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzdd r4 = (com.google.android.gms.internal.firebase_auth.zzdd) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0495
            goto L_0x04a7
        L_0x0495:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x04a2
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x04a7
        L_0x04a2:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x04a7:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzdd) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x04ac:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzcf> r4 = com.google.android.gms.internal.firebase_auth.zzcf.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzcf r4 = (com.google.android.gms.internal.firebase_auth.zzcf) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x04bb
            goto L_0x04cd
        L_0x04bb:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x04c8
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x04cd
        L_0x04c8:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x04cd:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzcf) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x04d2:
            android.os.Parcelable$Creator<com.google.firebase.auth.EmailAuthCredential> r4 = com.google.firebase.auth.EmailAuthCredential.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.firebase.auth.EmailAuthCredential r4 = (com.google.firebase.auth.EmailAuthCredential) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x04e1
            goto L_0x04f3
        L_0x04e1:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x04ee
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x04f3
        L_0x04ee:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x04f3:
            r3.zza((com.google.firebase.auth.EmailAuthCredential) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x04f8:
            java.lang.String r4 = r5.readString()
            android.os.Parcelable$Creator<com.google.firebase.auth.ActionCodeSettings> r0 = com.google.firebase.auth.ActionCodeSettings.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r0)
            com.google.firebase.auth.ActionCodeSettings r0 = (com.google.firebase.auth.ActionCodeSettings) r0
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x050b
            goto L_0x051d
        L_0x050b:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r1 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r1 == 0) goto L_0x0518
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x051d
        L_0x0518:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x051d:
            r3.zzc((java.lang.String) r4, (com.google.firebase.auth.ActionCodeSettings) r0, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0522:
            java.lang.String r4 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x052d
            goto L_0x053f
        L_0x052d:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x053a
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x053f
        L_0x053a:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x053f:
            r3.zzk(r4, r7)
            goto L_0x0904
        L_0x0544:
            java.lang.String r4 = r5.readString()
            android.os.Parcelable$Creator<com.google.firebase.auth.ActionCodeSettings> r0 = com.google.firebase.auth.ActionCodeSettings.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r0)
            com.google.firebase.auth.ActionCodeSettings r0 = (com.google.firebase.auth.ActionCodeSettings) r0
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0557
            goto L_0x0569
        L_0x0557:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r1 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r1 == 0) goto L_0x0564
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0569
        L_0x0564:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0569:
            r3.zzb((java.lang.String) r4, (com.google.firebase.auth.ActionCodeSettings) r0, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x056e:
            java.lang.String r4 = r5.readString()
            android.os.Parcelable$Creator<com.google.firebase.auth.ActionCodeSettings> r0 = com.google.firebase.auth.ActionCodeSettings.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r0)
            com.google.firebase.auth.ActionCodeSettings r0 = (com.google.firebase.auth.ActionCodeSettings) r0
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0581
            goto L_0x0593
        L_0x0581:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r1 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r1 == 0) goto L_0x058e
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0593
        L_0x058e:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0593:
            r3.zza((java.lang.String) r4, (com.google.firebase.auth.ActionCodeSettings) r0, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0598:
            java.lang.String r4 = r5.readString()
            android.os.Parcelable$Creator<com.google.firebase.auth.PhoneAuthCredential> r0 = com.google.firebase.auth.PhoneAuthCredential.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r0)
            com.google.firebase.auth.PhoneAuthCredential r0 = (com.google.firebase.auth.PhoneAuthCredential) r0
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x05ab
            goto L_0x05bd
        L_0x05ab:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r1 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r1 == 0) goto L_0x05b8
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x05bd
        L_0x05b8:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x05bd:
            r3.zza((java.lang.String) r4, (com.google.firebase.auth.PhoneAuthCredential) r0, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x05c2:
            android.os.Parcelable$Creator<com.google.firebase.auth.PhoneAuthCredential> r4 = com.google.firebase.auth.PhoneAuthCredential.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.firebase.auth.PhoneAuthCredential r4 = (com.google.firebase.auth.PhoneAuthCredential) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x05d1
            goto L_0x05e3
        L_0x05d1:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x05de
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x05e3
        L_0x05de:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x05e3:
            r3.zza((com.google.firebase.auth.PhoneAuthCredential) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x05e8:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzfe> r4 = com.google.android.gms.internal.firebase_auth.zzfe.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzfe r4 = (com.google.android.gms.internal.firebase_auth.zzfe) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x05f7
            goto L_0x0609
        L_0x05f7:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0604
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0609
        L_0x0604:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0609:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzfe) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x060e:
            java.lang.String r4 = r5.readString()
            java.lang.String r0 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x061d
            goto L_0x062f
        L_0x061d:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r1 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r1 == 0) goto L_0x062a
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x062f
        L_0x062a:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x062f:
            r3.zzf(r4, r0, r7)
            goto L_0x0904
        L_0x0634:
            java.lang.String r4 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x063f
            goto L_0x0651
        L_0x063f:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x064c
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0651
        L_0x064c:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0651:
            r3.zzj(r4, r7)
            goto L_0x0904
        L_0x0656:
            java.lang.String r4 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0661
            goto L_0x0673
        L_0x0661:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x066e
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0673
        L_0x066e:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0673:
            r3.zzi(r4, r7)
            goto L_0x0904
        L_0x0678:
            java.lang.String r4 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0683
            goto L_0x0695
        L_0x0683:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x0690
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0695
        L_0x0690:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0695:
            r3.zzh(r4, r7)
            goto L_0x0904
        L_0x069a:
            java.lang.String r4 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x06a5
            goto L_0x06b7
        L_0x06a5:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x06b2
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x06b7
        L_0x06b2:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x06b7:
            r3.zzg(r4, r7)
            goto L_0x0904
        L_0x06bc:
            android.os.IBinder r4 = r5.readStrongBinder()
            if (r4 != 0) goto L_0x06c3
            goto L_0x06d6
        L_0x06c3:
            java.lang.String r5 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r5 = r4.queryLocalInterface(r5)
            boolean r7 = r5 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r7 == 0) goto L_0x06d1
            r7 = r5
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x06d6
        L_0x06d1:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r4)
        L_0x06d6:
            r3.zza(r7)
            goto L_0x0904
        L_0x06db:
            java.lang.String r4 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x06e6
            goto L_0x06f8
        L_0x06e6:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x06f3
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x06f8
        L_0x06f3:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x06f8:
            r3.zzf(r4, r7)
            goto L_0x0904
        L_0x06fd:
            java.lang.String r4 = r5.readString()
            java.lang.String r0 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x070c
            goto L_0x071e
        L_0x070c:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r1 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r1 == 0) goto L_0x0719
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x071e
        L_0x0719:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x071e:
            r3.zze(r4, r0, r7)
            goto L_0x0904
        L_0x0723:
            java.lang.String r4 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x072e
            goto L_0x0740
        L_0x072e:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x073b
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0740
        L_0x073b:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0740:
            r3.zze(r4, r7)
            goto L_0x0904
        L_0x0745:
            java.lang.String r4 = r5.readString()
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzfm> r0 = com.google.android.gms.internal.firebase_auth.zzfm.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r0)
            com.google.android.gms.internal.firebase_auth.zzfm r0 = (com.google.android.gms.internal.firebase_auth.zzfm) r0
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0758
            goto L_0x076a
        L_0x0758:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r1 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r1 == 0) goto L_0x0765
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x076a
        L_0x0765:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x076a:
            r3.zza((java.lang.String) r4, (com.google.android.gms.internal.firebase_auth.zzfm) r0, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x076f:
            java.lang.String r4 = r5.readString()
            java.lang.String r0 = r5.readString()
            java.lang.String r1 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0782
            goto L_0x0794
        L_0x0782:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r2 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r2 == 0) goto L_0x078f
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0794
        L_0x078f:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0794:
            r3.zza(r4, r0, r1, r7)
            goto L_0x0904
        L_0x0799:
            java.lang.String r4 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x07a4
            goto L_0x07b6
        L_0x07a4:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x07b1
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x07b6
        L_0x07b1:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x07b6:
            r3.zzd(r4, r7)
            goto L_0x0904
        L_0x07bb:
            java.lang.String r4 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x07c6
            goto L_0x07d8
        L_0x07c6:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x07d3
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x07d8
        L_0x07d3:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x07d8:
            r3.zzc(r4, r7)
            goto L_0x0904
        L_0x07dd:
            java.lang.String r4 = r5.readString()
            java.lang.String r0 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x07ec
            goto L_0x07fe
        L_0x07ec:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r1 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r1 == 0) goto L_0x07f9
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x07fe
        L_0x07f9:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x07fe:
            r3.zzd(r4, r0, r7)
            goto L_0x0904
        L_0x0803:
            java.lang.String r4 = r5.readString()
            java.lang.String r0 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0812
            goto L_0x0824
        L_0x0812:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r1 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r1 == 0) goto L_0x081f
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0824
        L_0x081f:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0824:
            r3.zzc((java.lang.String) r4, (java.lang.String) r0, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0829:
            java.lang.String r4 = r5.readString()
            java.lang.String r0 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0838
            goto L_0x084a
        L_0x0838:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r1 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r1 == 0) goto L_0x0845
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x084a
        L_0x0845:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x084a:
            r3.zzb((java.lang.String) r4, (java.lang.String) r0, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x084f:
            java.lang.String r4 = r5.readString()
            java.lang.String r0 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x085e
            goto L_0x0870
        L_0x085e:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r1 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r1 == 0) goto L_0x086b
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0870
        L_0x086b:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0870:
            r3.zza((java.lang.String) r4, (java.lang.String) r0, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x0875:
            java.lang.String r4 = r5.readString()
            android.os.Parcelable$Creator<com.google.firebase.auth.UserProfileChangeRequest> r0 = com.google.firebase.auth.UserProfileChangeRequest.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r0)
            com.google.firebase.auth.UserProfileChangeRequest r0 = (com.google.firebase.auth.UserProfileChangeRequest) r0
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0888
            goto L_0x089a
        L_0x0888:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r1 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r1 == 0) goto L_0x0895
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x089a
        L_0x0895:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x089a:
            r3.zza((java.lang.String) r4, (com.google.firebase.auth.UserProfileChangeRequest) r0, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x089e:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzfm> r4 = com.google.android.gms.internal.firebase_auth.zzfm.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.firebase_auth.zzd.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.firebase_auth.zzfm r4 = (com.google.android.gms.internal.firebase_auth.zzfm) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x08ad
            goto L_0x08bf
        L_0x08ad:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x08ba
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x08bf
        L_0x08ba:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x08bf:
            r3.zza((com.google.android.gms.internal.firebase_auth.zzfm) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
            goto L_0x0904
        L_0x08c3:
            java.lang.String r4 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x08ce
            goto L_0x08e0
        L_0x08ce:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x08db
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x08e0
        L_0x08db:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x08e0:
            r3.zzb(r4, r7)
            goto L_0x0904
        L_0x08e4:
            java.lang.String r4 = r5.readString()
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x08ef
            goto L_0x0901
        L_0x08ef:
            java.lang.String r7 = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks"
            android.os.IInterface r7 = r5.queryLocalInterface(r7)
            boolean r0 = r7 instanceof com.google.firebase.auth.api.internal.zzdu
            if (r0 == 0) goto L_0x08fc
            com.google.firebase.auth.api.internal.zzdu r7 = (com.google.firebase.auth.api.internal.zzdu) r7
            goto L_0x0901
        L_0x08fc:
            com.google.firebase.auth.api.internal.zzdw r7 = new com.google.firebase.auth.api.internal.zzdw
            r7.<init>(r5)
        L_0x0901:
            r3.zza((java.lang.String) r4, (com.google.firebase.auth.api.internal.zzdu) r7)
        L_0x0904:
            r6.writeNoException()
            r4 = 1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.api.internal.zzdy.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
