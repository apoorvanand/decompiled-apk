package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzn extends zza implements zzk {
    public zzn() {
        super("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public static zzk asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
        return queryLocalInterface instanceof zzk ? (zzk) queryLocalInterface : new zzm(iBinder);
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v11, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v16, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v26, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v34, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v39, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v44, types: [com.google.android.gms.internal.measurement.zzv] */
    /* JADX WARNING: type inference failed for: r1v49, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v54, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v59, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v64, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v83, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v88, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v94, types: [com.google.android.gms.internal.measurement.zzq] */
    /* JADX WARNING: type inference failed for: r1v99, types: [com.google.android.gms.internal.measurement.zzq] */
    /* JADX WARNING: type inference failed for: r1v104, types: [com.google.android.gms.internal.measurement.zzq] */
    /* JADX WARNING: type inference failed for: r1v109, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v114, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v119, types: [com.google.android.gms.internal.measurement.zzp] */
    /* JADX WARNING: type inference failed for: r1v124 */
    /* JADX WARNING: type inference failed for: r1v125 */
    /* JADX WARNING: type inference failed for: r1v126 */
    /* JADX WARNING: type inference failed for: r1v127 */
    /* JADX WARNING: type inference failed for: r1v128 */
    /* JADX WARNING: type inference failed for: r1v129 */
    /* JADX WARNING: type inference failed for: r1v130 */
    /* JADX WARNING: type inference failed for: r1v131 */
    /* JADX WARNING: type inference failed for: r1v132 */
    /* JADX WARNING: type inference failed for: r1v133 */
    /* JADX WARNING: type inference failed for: r1v134 */
    /* JADX WARNING: type inference failed for: r1v135 */
    /* JADX WARNING: type inference failed for: r1v136 */
    /* JADX WARNING: type inference failed for: r1v137 */
    /* JADX WARNING: type inference failed for: r1v138 */
    /* JADX WARNING: type inference failed for: r1v139 */
    /* JADX WARNING: type inference failed for: r1v140 */
    /* JADX WARNING: type inference failed for: r1v141 */
    /* JADX WARNING: type inference failed for: r1v142 */
    /* JADX WARNING: type inference failed for: r1v143 */
    /* JADX WARNING: type inference failed for: r1v144 */
    /* JADX WARNING: type inference failed for: r1v145 */
    /* JADX WARNING: type inference failed for: r1v146 */
    /* JADX WARNING: type inference failed for: r1v147 */
    /* JADX WARNING: type inference failed for: r1v148 */
    /* JADX WARNING: type inference failed for: r1v149 */
    /* JADX WARNING: type inference failed for: r1v150 */
    /* JADX WARNING: type inference failed for: r1v151 */
    /* JADX WARNING: type inference failed for: r1v152 */
    /* JADX WARNING: type inference failed for: r1v153 */
    /* JADX WARNING: type inference failed for: r1v154 */
    /* JADX WARNING: type inference failed for: r1v155 */
    /* JADX WARNING: type inference failed for: r1v156 */
    /* JADX WARNING: type inference failed for: r1v157 */
    /* JADX WARNING: type inference failed for: r1v158 */
    /* JADX WARNING: type inference failed for: r1v159 */
    /* JADX WARNING: type inference failed for: r1v160 */
    /* JADX WARNING: type inference failed for: r1v161 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(int r9, android.os.Parcel r10, android.os.Parcel r11, int r12) {
        /*
            r8 = this;
            r1 = 0
            switch(r9) {
                case 1: goto L_0x0403;
                case 2: goto L_0x03e2;
                case 3: goto L_0x03a9;
                case 4: goto L_0x038b;
                case 5: goto L_0x0361;
                case 6: goto L_0x033f;
                case 7: goto L_0x0332;
                case 8: goto L_0x0321;
                case 9: goto L_0x030c;
                case 10: goto L_0x02e6;
                case 11: goto L_0x02d9;
                case 12: goto L_0x02d0;
                case 13: goto L_0x02c7;
                case 14: goto L_0x02be;
                case 15: goto L_0x02a4;
                case 16: goto L_0x0286;
                case 17: goto L_0x0268;
                case 18: goto L_0x024a;
                case 19: goto L_0x022c;
                case 20: goto L_0x020e;
                case 21: goto L_0x01f0;
                case 22: goto L_0x01d2;
                case 23: goto L_0x01c5;
                case 24: goto L_0x01b8;
                case 25: goto L_0x01a7;
                case 26: goto L_0x0196;
                case 27: goto L_0x017d;
                case 28: goto L_0x016c;
                case 29: goto L_0x015b;
                case 30: goto L_0x014a;
                case 31: goto L_0x0120;
                case 32: goto L_0x00f6;
                case 33: goto L_0x00d0;
                case 34: goto L_0x00b2;
                case 35: goto L_0x0094;
                case 36: goto L_0x0076;
                case 37: goto L_0x006d;
                case 38: goto L_0x004b;
                case 39: goto L_0x0042;
                case 40: goto L_0x0024;
                case 41: goto L_0x0006;
                default: goto L_0x0004;
            }
        L_0x0004:
            r0 = 0
            return r0
        L_0x0006:
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x000d
            goto L_0x001f
        L_0x000d:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r2 == 0) goto L_0x001a
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x001f
        L_0x001a:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r0)
        L_0x001f:
            r8.getDeepLink(r1)
            goto L_0x041a
        L_0x0024:
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x002b
            goto L_0x003d
        L_0x002b:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r2 == 0) goto L_0x0038
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x003d
        L_0x0038:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r0)
        L_0x003d:
            r8.isDataCollectionEnabled(r1)
            goto L_0x041a
        L_0x0042:
            boolean r0 = com.google.android.gms.internal.measurement.zzd.zza(r10)
            r8.setDataCollectionEnabled(r0)
            goto L_0x041a
        L_0x004b:
            android.os.IBinder r2 = r10.readStrongBinder()
            if (r2 != 0) goto L_0x0052
            goto L_0x0064
        L_0x0052:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r2.queryLocalInterface(r1)
            boolean r3 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r3 == 0) goto L_0x005f
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x0064
        L_0x005f:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r2)
        L_0x0064:
            int r0 = r10.readInt()
            r8.getTestFlag(r1, r0)
            goto L_0x041a
        L_0x006d:
            java.util.HashMap r0 = com.google.android.gms.internal.measurement.zzd.zzb(r10)
            r8.initForTests(r0)
            goto L_0x041a
        L_0x0076:
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x007d
            goto L_0x008f
        L_0x007d:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IEventHandlerProxy"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzq
            if (r2 == 0) goto L_0x008a
            com.google.android.gms.internal.measurement.zzq r1 = (com.google.android.gms.internal.measurement.zzq) r1
            goto L_0x008f
        L_0x008a:
            com.google.android.gms.internal.measurement.zzs r1 = new com.google.android.gms.internal.measurement.zzs
            r1.<init>(r0)
        L_0x008f:
            r8.unregisterOnMeasurementEventListener(r1)
            goto L_0x041a
        L_0x0094:
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x009b
            goto L_0x00ad
        L_0x009b:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IEventHandlerProxy"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzq
            if (r2 == 0) goto L_0x00a8
            com.google.android.gms.internal.measurement.zzq r1 = (com.google.android.gms.internal.measurement.zzq) r1
            goto L_0x00ad
        L_0x00a8:
            com.google.android.gms.internal.measurement.zzs r1 = new com.google.android.gms.internal.measurement.zzs
            r1.<init>(r0)
        L_0x00ad:
            r8.registerOnMeasurementEventListener(r1)
            goto L_0x041a
        L_0x00b2:
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x00b9
            goto L_0x00cb
        L_0x00b9:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IEventHandlerProxy"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzq
            if (r2 == 0) goto L_0x00c6
            com.google.android.gms.internal.measurement.zzq r1 = (com.google.android.gms.internal.measurement.zzq) r1
            goto L_0x00cb
        L_0x00c6:
            com.google.android.gms.internal.measurement.zzs r1 = new com.google.android.gms.internal.measurement.zzs
            r1.<init>(r0)
        L_0x00cb:
            r8.setEventInterceptor(r1)
            goto L_0x041a
        L_0x00d0:
            int r1 = r10.readInt()
            java.lang.String r2 = r10.readString()
            android.os.IBinder r3 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            android.os.IBinder r4 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r4)
            android.os.IBinder r0 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            r0 = r8
            r0.logHealthData(r1, r2, r3, r4, r5)
            goto L_0x041a
        L_0x00f6:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzd.zza((android.os.Parcel) r10, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            android.os.IBinder r3 = r10.readStrongBinder()
            if (r3 != 0) goto L_0x0105
            goto L_0x0117
        L_0x0105:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r3.queryLocalInterface(r1)
            boolean r4 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r4 == 0) goto L_0x0112
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x0117
        L_0x0112:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r3)
        L_0x0117:
            long r3 = r10.readLong()
            r8.performAction(r2, r1, r3)
            goto L_0x041a
        L_0x0120:
            android.os.IBinder r2 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            android.os.IBinder r3 = r10.readStrongBinder()
            if (r3 != 0) goto L_0x012f
            goto L_0x0141
        L_0x012f:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r3.queryLocalInterface(r1)
            boolean r4 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r4 == 0) goto L_0x013c
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x0141
        L_0x013c:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r3)
        L_0x0141:
            long r3 = r10.readLong()
            r8.onActivitySaveInstanceState(r2, r1, r3)
            goto L_0x041a
        L_0x014a:
            android.os.IBinder r1 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r10.readLong()
            r8.onActivityResumed(r1, r2)
            goto L_0x041a
        L_0x015b:
            android.os.IBinder r1 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r10.readLong()
            r8.onActivityPaused(r1, r2)
            goto L_0x041a
        L_0x016c:
            android.os.IBinder r1 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r10.readLong()
            r8.onActivityDestroyed(r1, r2)
            goto L_0x041a
        L_0x017d:
            android.os.IBinder r1 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzd.zza((android.os.Parcel) r10, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            long r3 = r10.readLong()
            r8.onActivityCreated(r1, r2, r3)
            goto L_0x041a
        L_0x0196:
            android.os.IBinder r1 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r10.readLong()
            r8.onActivityStopped(r1, r2)
            goto L_0x041a
        L_0x01a7:
            android.os.IBinder r1 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r10.readLong()
            r8.onActivityStarted(r1, r2)
            goto L_0x041a
        L_0x01b8:
            java.lang.String r1 = r10.readString()
            long r2 = r10.readLong()
            r8.endAdUnitExposure(r1, r2)
            goto L_0x041a
        L_0x01c5:
            java.lang.String r1 = r10.readString()
            long r2 = r10.readLong()
            r8.beginAdUnitExposure(r1, r2)
            goto L_0x041a
        L_0x01d2:
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x01d9
            goto L_0x01eb
        L_0x01d9:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r2 == 0) goto L_0x01e6
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x01eb
        L_0x01e6:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r0)
        L_0x01eb:
            r8.generateEventId(r1)
            goto L_0x041a
        L_0x01f0:
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x01f7
            goto L_0x0209
        L_0x01f7:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r2 == 0) goto L_0x0204
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x0209
        L_0x0204:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r0)
        L_0x0209:
            r8.getGmpAppId(r1)
            goto L_0x041a
        L_0x020e:
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x0215
            goto L_0x0227
        L_0x0215:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r2 == 0) goto L_0x0222
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x0227
        L_0x0222:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r0)
        L_0x0227:
            r8.getAppInstanceId(r1)
            goto L_0x041a
        L_0x022c:
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x0233
            goto L_0x0245
        L_0x0233:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r2 == 0) goto L_0x0240
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x0245
        L_0x0240:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r0)
        L_0x0245:
            r8.getCachedAppInstanceId(r1)
            goto L_0x041a
        L_0x024a:
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x0251
            goto L_0x0263
        L_0x0251:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IStringProvider"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzv
            if (r2 == 0) goto L_0x025e
            com.google.android.gms.internal.measurement.zzv r1 = (com.google.android.gms.internal.measurement.zzv) r1
            goto L_0x0263
        L_0x025e:
            com.google.android.gms.internal.measurement.zzu r1 = new com.google.android.gms.internal.measurement.zzu
            r1.<init>(r0)
        L_0x0263:
            r8.setInstanceIdProvider(r1)
            goto L_0x041a
        L_0x0268:
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x026f
            goto L_0x0281
        L_0x026f:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r2 == 0) goto L_0x027c
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x0281
        L_0x027c:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r0)
        L_0x0281:
            r8.getCurrentScreenClass(r1)
            goto L_0x041a
        L_0x0286:
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x028d
            goto L_0x029f
        L_0x028d:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r2 == 0) goto L_0x029a
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x029f
        L_0x029a:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r0)
        L_0x029f:
            r8.getCurrentScreenName(r1)
            goto L_0x041a
        L_0x02a4:
            android.os.IBinder r1 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            java.lang.String r2 = r10.readString()
            java.lang.String r3 = r10.readString()
            long r4 = r10.readLong()
            r0 = r8
            r0.setCurrentScreen(r1, r2, r3, r4)
            goto L_0x041a
        L_0x02be:
            long r0 = r10.readLong()
            r8.setSessionTimeoutDuration(r0)
            goto L_0x041a
        L_0x02c7:
            long r0 = r10.readLong()
            r8.setMinimumSessionDuration(r0)
            goto L_0x041a
        L_0x02d0:
            long r0 = r10.readLong()
            r8.resetAnalyticsData(r0)
            goto L_0x041a
        L_0x02d9:
            boolean r1 = com.google.android.gms.internal.measurement.zzd.zza(r10)
            long r2 = r10.readLong()
            r8.setMeasurementEnabled(r1, r2)
            goto L_0x041a
        L_0x02e6:
            java.lang.String r2 = r10.readString()
            java.lang.String r3 = r10.readString()
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x02f5
            goto L_0x0307
        L_0x02f5:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r4 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r4 == 0) goto L_0x0302
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x0307
        L_0x0302:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r0)
        L_0x0307:
            r8.getConditionalUserProperties(r2, r3, r1)
            goto L_0x041a
        L_0x030c:
            java.lang.String r1 = r10.readString()
            java.lang.String r2 = r10.readString()
            android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.measurement.zzd.zza((android.os.Parcel) r10, r3)
            android.os.Bundle r0 = (android.os.Bundle) r0
            r8.clearConditionalUserProperty(r1, r2, r0)
            goto L_0x041a
        L_0x0321:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzd.zza((android.os.Parcel) r10, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            long r2 = r10.readLong()
            r8.setConditionalUserProperty(r1, r2)
            goto L_0x041a
        L_0x0332:
            java.lang.String r1 = r10.readString()
            long r2 = r10.readLong()
            r8.setUserId(r1, r2)
            goto L_0x041a
        L_0x033f:
            java.lang.String r2 = r10.readString()
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x034a
            goto L_0x035c
        L_0x034a:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r3 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r3 == 0) goto L_0x0357
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x035c
        L_0x0357:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r0)
        L_0x035c:
            r8.getMaxUserProperties(r2, r1)
            goto L_0x041a
        L_0x0361:
            java.lang.String r2 = r10.readString()
            java.lang.String r3 = r10.readString()
            boolean r4 = com.google.android.gms.internal.measurement.zzd.zza(r10)
            android.os.IBinder r0 = r10.readStrongBinder()
            if (r0 != 0) goto L_0x0374
            goto L_0x0386
        L_0x0374:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r5 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r5 == 0) goto L_0x0381
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x0386
        L_0x0381:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r0)
        L_0x0386:
            r8.getUserProperties(r2, r3, r4, r1)
            goto L_0x041a
        L_0x038b:
            java.lang.String r1 = r10.readString()
            java.lang.String r2 = r10.readString()
            android.os.IBinder r3 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            boolean r4 = com.google.android.gms.internal.measurement.zzd.zza(r10)
            long r5 = r10.readLong()
            r0 = r8
            r0.setUserProperty(r1, r2, r3, r4, r5)
            goto L_0x041a
        L_0x03a9:
            java.lang.String r2 = r10.readString()
            java.lang.String r3 = r10.readString()
            android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.measurement.zzd.zza((android.os.Parcel) r10, r4)
            android.os.Bundle r4 = (android.os.Bundle) r4
            android.os.IBinder r5 = r10.readStrongBinder()
            if (r5 != 0) goto L_0x03c1
        L_0x03bf:
            r5 = r1
            goto L_0x03d4
        L_0x03c1:
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            android.os.IInterface r1 = r5.queryLocalInterface(r1)
            boolean r6 = r1 instanceof com.google.android.gms.internal.measurement.zzp
            if (r6 == 0) goto L_0x03ce
            com.google.android.gms.internal.measurement.zzp r1 = (com.google.android.gms.internal.measurement.zzp) r1
            goto L_0x03bf
        L_0x03ce:
            com.google.android.gms.internal.measurement.zzr r1 = new com.google.android.gms.internal.measurement.zzr
            r1.<init>(r5)
            goto L_0x03bf
        L_0x03d4:
            long r6 = r10.readLong()
            r0 = r8
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r0.logEventAndBundle(r1, r2, r3, r4, r5)
            goto L_0x041a
        L_0x03e2:
            java.lang.String r1 = r10.readString()
            java.lang.String r2 = r10.readString()
            android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.measurement.zzd.zza((android.os.Parcel) r10, r3)
            android.os.Bundle r3 = (android.os.Bundle) r3
            boolean r4 = com.google.android.gms.internal.measurement.zzd.zza(r10)
            boolean r5 = com.google.android.gms.internal.measurement.zzd.zza(r10)
            long r6 = r10.readLong()
            r0 = r8
            r0.logEvent(r1, r2, r3, r4, r5, r6)
            goto L_0x041a
        L_0x0403:
            android.os.IBinder r1 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzx> r2 = com.google.android.gms.internal.measurement.zzx.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzd.zza((android.os.Parcel) r10, r2)
            com.google.android.gms.internal.measurement.zzx r2 = (com.google.android.gms.internal.measurement.zzx) r2
            long r3 = r10.readLong()
            r8.initialize(r1, r2, r3)
        L_0x041a:
            r11.writeNoException()
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzn.a(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
