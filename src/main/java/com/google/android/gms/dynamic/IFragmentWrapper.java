package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;

public interface IFragmentWrapper extends IInterface {

    public static abstract class Stub extends zzb implements IFragmentWrapper {

        public static class zza extends com.google.android.gms.internal.common.zza implements IFragmentWrapper {
            zza(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.dynamic.IFragmentWrapper");
            }

            public final Bundle getArguments() {
                Parcel a = a(3, a());
                Bundle bundle = (Bundle) zzc.zza(a, Bundle.CREATOR);
                a.recycle();
                return bundle;
            }

            public final int getId() {
                Parcel a = a(4, a());
                int readInt = a.readInt();
                a.recycle();
                return readInt;
            }

            public final boolean getRetainInstance() {
                Parcel a = a(7, a());
                boolean zza = zzc.zza(a);
                a.recycle();
                return zza;
            }

            public final String getTag() {
                Parcel a = a(8, a());
                String readString = a.readString();
                a.recycle();
                return readString;
            }

            public final int getTargetRequestCode() {
                Parcel a = a(10, a());
                int readInt = a.readInt();
                a.recycle();
                return readInt;
            }

            public final boolean getUserVisibleHint() {
                Parcel a = a(11, a());
                boolean zza = zzc.zza(a);
                a.recycle();
                return zza;
            }

            public final boolean isAdded() {
                Parcel a = a(13, a());
                boolean zza = zzc.zza(a);
                a.recycle();
                return zza;
            }

            public final boolean isDetached() {
                Parcel a = a(14, a());
                boolean zza = zzc.zza(a);
                a.recycle();
                return zza;
            }

            public final boolean isHidden() {
                Parcel a = a(15, a());
                boolean zza = zzc.zza(a);
                a.recycle();
                return zza;
            }

            public final boolean isInLayout() {
                Parcel a = a(16, a());
                boolean zza = zzc.zza(a);
                a.recycle();
                return zza;
            }

            public final boolean isRemoving() {
                Parcel a = a(17, a());
                boolean zza = zzc.zza(a);
                a.recycle();
                return zza;
            }

            public final boolean isResumed() {
                Parcel a = a(18, a());
                boolean zza = zzc.zza(a);
                a.recycle();
                return zza;
            }

            public final boolean isVisible() {
                Parcel a = a(19, a());
                boolean zza = zzc.zza(a);
                a.recycle();
                return zza;
            }

            public final void setHasOptionsMenu(boolean z) {
                Parcel a = a();
                zzc.writeBoolean(a, z);
                b(21, a);
            }

            public final void setMenuVisibility(boolean z) {
                Parcel a = a();
                zzc.writeBoolean(a, z);
                b(22, a);
            }

            public final void setRetainInstance(boolean z) {
                Parcel a = a();
                zzc.writeBoolean(a, z);
                b(23, a);
            }

            public final void setUserVisibleHint(boolean z) {
                Parcel a = a();
                zzc.writeBoolean(a, z);
                b(24, a);
            }

            public final void startActivity(Intent intent) {
                Parcel a = a();
                zzc.zza(a, (Parcelable) intent);
                b(25, a);
            }

            public final void startActivityForResult(Intent intent, int i) {
                Parcel a = a();
                zzc.zza(a, (Parcelable) intent);
                a.writeInt(i);
                b(26, a);
            }

            public final void zza(IObjectWrapper iObjectWrapper) {
                Parcel a = a();
                zzc.zza(a, (IInterface) iObjectWrapper);
                b(20, a);
            }

            public final IObjectWrapper zzae() {
                Parcel a = a(2, a());
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a.readStrongBinder());
                a.recycle();
                return asInterface;
            }

            public final IFragmentWrapper zzaf() {
                Parcel a = a(5, a());
                IFragmentWrapper asInterface = Stub.asInterface(a.readStrongBinder());
                a.recycle();
                return asInterface;
            }

            public final IObjectWrapper zzag() {
                Parcel a = a(6, a());
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a.readStrongBinder());
                a.recycle();
                return asInterface;
            }

            public final IFragmentWrapper zzah() {
                Parcel a = a(9, a());
                IFragmentWrapper asInterface = Stub.asInterface(a.readStrongBinder());
                a.recycle();
                return asInterface;
            }

            public final IObjectWrapper zzai() {
                Parcel a = a(12, a());
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a.readStrongBinder());
                a.recycle();
                return asInterface;
            }

            public final void zzb(IObjectWrapper iObjectWrapper) {
                Parcel a = a();
                zzc.zza(a, (IInterface) iObjectWrapper);
                b(27, a);
            }
        }

        public Stub() {
            super("com.google.android.gms.dynamic.IFragmentWrapper");
        }

        public static IFragmentWrapper asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
            return queryLocalInterface instanceof IFragmentWrapper ? (IFragmentWrapper) queryLocalInterface : new zza(iBinder);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0058, code lost:
            r3.writeNoException();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a3, code lost:
            r3.writeNoException();
            com.google.android.gms.internal.common.zzc.writeBoolean(r3, r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b8, code lost:
            r3.writeNoException();
            r3.writeInt(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00ce, code lost:
            r3.writeNoException();
            com.google.android.gms.internal.common.zzc.zza(r3, r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            return true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean a(int r1, android.os.Parcel r2, android.os.Parcel r3, int r4) {
            /*
                r0 = this;
                switch(r1) {
                    case 2: goto L_0x00ca;
                    case 3: goto L_0x00bf;
                    case 4: goto L_0x00b4;
                    case 5: goto L_0x00af;
                    case 6: goto L_0x00aa;
                    case 7: goto L_0x009f;
                    case 8: goto L_0x0094;
                    case 9: goto L_0x008f;
                    case 10: goto L_0x008a;
                    case 11: goto L_0x0085;
                    case 12: goto L_0x0080;
                    case 13: goto L_0x007b;
                    case 14: goto L_0x0076;
                    case 15: goto L_0x0071;
                    case 16: goto L_0x006c;
                    case 17: goto L_0x0067;
                    case 18: goto L_0x0062;
                    case 19: goto L_0x005d;
                    case 20: goto L_0x004d;
                    case 21: goto L_0x0045;
                    case 22: goto L_0x003d;
                    case 23: goto L_0x0035;
                    case 24: goto L_0x002d;
                    case 25: goto L_0x0021;
                    case 26: goto L_0x0011;
                    case 27: goto L_0x0005;
                    default: goto L_0x0003;
                }
            L_0x0003:
                r1 = 0
                return r1
            L_0x0005:
                android.os.IBinder r1 = r2.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
                r0.zzb(r1)
                goto L_0x0058
            L_0x0011:
                android.os.Parcelable$Creator r1 = android.content.Intent.CREATOR
                android.os.Parcelable r1 = com.google.android.gms.internal.common.zzc.zza((android.os.Parcel) r2, r1)
                android.content.Intent r1 = (android.content.Intent) r1
                int r2 = r2.readInt()
                r0.startActivityForResult(r1, r2)
                goto L_0x0058
            L_0x0021:
                android.os.Parcelable$Creator r1 = android.content.Intent.CREATOR
                android.os.Parcelable r1 = com.google.android.gms.internal.common.zzc.zza((android.os.Parcel) r2, r1)
                android.content.Intent r1 = (android.content.Intent) r1
                r0.startActivity(r1)
                goto L_0x0058
            L_0x002d:
                boolean r1 = com.google.android.gms.internal.common.zzc.zza(r2)
                r0.setUserVisibleHint(r1)
                goto L_0x0058
            L_0x0035:
                boolean r1 = com.google.android.gms.internal.common.zzc.zza(r2)
                r0.setRetainInstance(r1)
                goto L_0x0058
            L_0x003d:
                boolean r1 = com.google.android.gms.internal.common.zzc.zza(r2)
                r0.setMenuVisibility(r1)
                goto L_0x0058
            L_0x0045:
                boolean r1 = com.google.android.gms.internal.common.zzc.zza(r2)
                r0.setHasOptionsMenu(r1)
                goto L_0x0058
            L_0x004d:
                android.os.IBinder r1 = r2.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
                r0.zza(r1)
            L_0x0058:
                r3.writeNoException()
                goto L_0x00d4
            L_0x005d:
                boolean r1 = r0.isVisible()
                goto L_0x00a3
            L_0x0062:
                boolean r1 = r0.isResumed()
                goto L_0x00a3
            L_0x0067:
                boolean r1 = r0.isRemoving()
                goto L_0x00a3
            L_0x006c:
                boolean r1 = r0.isInLayout()
                goto L_0x00a3
            L_0x0071:
                boolean r1 = r0.isHidden()
                goto L_0x00a3
            L_0x0076:
                boolean r1 = r0.isDetached()
                goto L_0x00a3
            L_0x007b:
                boolean r1 = r0.isAdded()
                goto L_0x00a3
            L_0x0080:
                com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzai()
                goto L_0x00ce
            L_0x0085:
                boolean r1 = r0.getUserVisibleHint()
                goto L_0x00a3
            L_0x008a:
                int r1 = r0.getTargetRequestCode()
                goto L_0x00b8
            L_0x008f:
                com.google.android.gms.dynamic.IFragmentWrapper r1 = r0.zzah()
                goto L_0x00ce
            L_0x0094:
                java.lang.String r1 = r0.getTag()
                r3.writeNoException()
                r3.writeString(r1)
                goto L_0x00d4
            L_0x009f:
                boolean r1 = r0.getRetainInstance()
            L_0x00a3:
                r3.writeNoException()
                com.google.android.gms.internal.common.zzc.writeBoolean(r3, r1)
                goto L_0x00d4
            L_0x00aa:
                com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzag()
                goto L_0x00ce
            L_0x00af:
                com.google.android.gms.dynamic.IFragmentWrapper r1 = r0.zzaf()
                goto L_0x00ce
            L_0x00b4:
                int r1 = r0.getId()
            L_0x00b8:
                r3.writeNoException()
                r3.writeInt(r1)
                goto L_0x00d4
            L_0x00bf:
                android.os.Bundle r1 = r0.getArguments()
                r3.writeNoException()
                com.google.android.gms.internal.common.zzc.zzb(r3, r1)
                goto L_0x00d4
            L_0x00ca:
                com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzae()
            L_0x00ce:
                r3.writeNoException()
                com.google.android.gms.internal.common.zzc.zza((android.os.Parcel) r3, (android.os.IInterface) r1)
            L_0x00d4:
                r1 = 1
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamic.IFragmentWrapper.Stub.a(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    Bundle getArguments();

    int getId();

    boolean getRetainInstance();

    String getTag();

    int getTargetRequestCode();

    boolean getUserVisibleHint();

    boolean isAdded();

    boolean isDetached();

    boolean isHidden();

    boolean isInLayout();

    boolean isRemoving();

    boolean isResumed();

    boolean isVisible();

    void setHasOptionsMenu(boolean z);

    void setMenuVisibility(boolean z);

    void setRetainInstance(boolean z);

    void setUserVisibleHint(boolean z);

    void startActivity(Intent intent);

    void startActivityForResult(Intent intent, int i);

    void zza(IObjectWrapper iObjectWrapper);

    IObjectWrapper zzae();

    IFragmentWrapper zzaf();

    IObjectWrapper zzag();

    IFragmentWrapper zzah();

    IObjectWrapper zzai();

    void zzb(IObjectWrapper iObjectWrapper);
}
