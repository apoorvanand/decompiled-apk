package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzrl;
import com.google.android.gms.internal.firebase_ml.zzrm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class zzrl<MessageType extends zzrl<MessageType, BuilderType>, BuilderType extends zzrm<MessageType, BuilderType>> implements zzum {
    private static boolean zzbhv = false;
    protected int zzbhu = 0;

    protected static <T> void a(Iterable<T> iterable, List<? super T> list) {
        zzte.a(iterable);
        if (iterable instanceof zztv) {
            List<?> zzqj = ((zztv) iterable).zzqj();
            zztv zztv = (zztv) list;
            int size = list.size();
            for (Object next : zzqj) {
                if (next == null) {
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(zztv.size() - size);
                    sb.append(" is null.");
                    String sb2 = sb.toString();
                    for (int size2 = zztv.size() - 1; size2 >= size; size2--) {
                        zztv.remove(size2);
                    }
                    throw new NullPointerException(sb2);
                } else if (next instanceof zzru) {
                    zztv.zzc((zzru) next);
                } else {
                    zztv.add((String) next);
                }
            }
        } else if (iterable instanceof zzuy) {
            list.addAll((Collection) iterable);
        } else {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
            }
            int size3 = list.size();
            for (T next2 : iterable) {
                if (next2 == null) {
                    StringBuilder sb3 = new StringBuilder(37);
                    sb3.append("Element at index ");
                    sb3.append(list.size() - size3);
                    sb3.append(" is null.");
                    String sb4 = sb3.toString();
                    for (int size4 = list.size() - 1; size4 >= size3; size4--) {
                        list.remove(size4);
                    }
                    throw new NullPointerException(sb4);
                }
                list.add(next2);
            }
        }
    }

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
            byte[] bArr = new byte[zzpe()];
            zzsj zzh = zzsj.zzh(bArr);
            zzb(zzh);
            zzh.zzot();
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

    public final zzru zzod() {
        try {
            zzsc b = zzru.b(zzpe());
            zzb(b.zzoq());
            return b.zzop();
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
