package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzfx;
import com.google.android.gms.internal.firebase_auth.zzga;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class zzfx<MessageType extends zzfx<MessageType, BuilderType>, BuilderType extends zzga<MessageType, BuilderType>> implements zzjc {
    private static boolean zzvn = false;
    protected int zzvm = 0;

    protected static <T> void a(Iterable<T> iterable, List<? super T> list) {
        zzht.a(iterable);
        if (iterable instanceof zzij) {
            List<?> zzjd = ((zzij) iterable).zzjd();
            zzij zzij = (zzij) list;
            int size = list.size();
            for (Object next : zzjd) {
                if (next == null) {
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(zzij.size() - size);
                    sb.append(" is null.");
                    String sb2 = sb.toString();
                    for (int size2 = zzij.size() - 1; size2 >= size; size2--) {
                        zzij.remove(size2);
                    }
                    throw new NullPointerException(sb2);
                } else if (next instanceof zzgf) {
                    zzij.zzc((zzgf) next);
                } else {
                    zzij.add((String) next);
                }
            }
        } else if (iterable instanceof zzjl) {
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
    public int a() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        throw new UnsupportedOperationException();
    }

    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzik()];
            zzha zzc = zzha.zzc(bArr);
            zzb(zzc);
            zzc.zzhj();
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

    public final zzgf zzft() {
        try {
            zzgn b = zzgf.b(zzik());
            zzb(b.zzgh());
            return b.zzgg();
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
