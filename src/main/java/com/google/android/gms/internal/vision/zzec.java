package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzec;
import com.google.android.gms.internal.vision.zzed;
import java.io.IOException;

public abstract class zzec<MessageType extends zzec<MessageType, BuilderType>, BuilderType extends zzed<MessageType, BuilderType>> implements zzhf {
    private static boolean zzrj = false;
    protected int zzri = 0;

    /* access modifiers changed from: package-private */
    public void a(int i) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int b() {
        throw new UnsupportedOperationException();
    }

    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzeq()];
            zzfe zzg = zzfe.zzg(bArr);
            zzb(zzg);
            zzg.zzea();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + String.valueOf("byte array").length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    public final zzeo zzce() {
        try {
            zzev a = zzeo.a(zzeq());
            zzb(a.zzdp());
            return a.zzdo();
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + String.valueOf("ByteString").length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }
}
