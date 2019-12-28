package com.google.android.gms.internal.firebase_ml;

import java.util.Collections;
import java.util.List;

final class zzty extends zztw {
    private static final Class<?> zzbob = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzty() {
        super();
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzwa.f(obj, j);
    }

    /* access modifiers changed from: package-private */
    public final void a(Object obj, long j) {
        Object obj2;
        List list = (List) zzwa.f(obj, j);
        if (list instanceof zztv) {
            obj2 = ((zztv) list).zzqk();
        } else if (!zzbob.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzuy) || !(list instanceof zztl)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zztl zztl = (zztl) list;
                if (zztl.zzog()) {
                    zztl.zzoh();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzwa.a(obj, j, obj2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: com.google.android.gms.internal.firebase_ml.zztu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: com.google.android.gms.internal.firebase_ml.zztu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: com.google.android.gms.internal.firebase_ml.zztu} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <E> void a(java.lang.Object r5, java.lang.Object r6, long r7) {
        /*
            r4 = this;
            java.util.List r6 = zzc(r6, r7)
            int r0 = r6.size()
            java.util.List r1 = zzc(r5, r7)
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L_0x0034
            boolean r2 = r1 instanceof com.google.android.gms.internal.firebase_ml.zztv
            if (r2 == 0) goto L_0x001c
            com.google.android.gms.internal.firebase_ml.zztu r1 = new com.google.android.gms.internal.firebase_ml.zztu
            r1.<init>((int) r0)
            goto L_0x0030
        L_0x001c:
            boolean r2 = r1 instanceof com.google.android.gms.internal.firebase_ml.zzuy
            if (r2 == 0) goto L_0x002b
            boolean r2 = r1 instanceof com.google.android.gms.internal.firebase_ml.zztl
            if (r2 == 0) goto L_0x002b
            com.google.android.gms.internal.firebase_ml.zztl r1 = (com.google.android.gms.internal.firebase_ml.zztl) r1
            com.google.android.gms.internal.firebase_ml.zztl r1 = r1.zzcb(r0)
            goto L_0x0030
        L_0x002b:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r0)
        L_0x0030:
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r5, (long) r7, (java.lang.Object) r1)
            goto L_0x0084
        L_0x0034:
            java.lang.Class<?> r2 = zzbob
            java.lang.Class r3 = r1.getClass()
            boolean r2 = r2.isAssignableFrom(r3)
            if (r2 == 0) goto L_0x0052
            java.util.ArrayList r2 = new java.util.ArrayList
            int r3 = r1.size()
            int r3 = r3 + r0
            r2.<init>(r3)
            r2.addAll(r1)
        L_0x004d:
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r5, (long) r7, (java.lang.Object) r2)
            r1 = r2
            goto L_0x0084
        L_0x0052:
            boolean r2 = r1 instanceof com.google.android.gms.internal.firebase_ml.zzvx
            if (r2 == 0) goto L_0x0066
            com.google.android.gms.internal.firebase_ml.zztu r2 = new com.google.android.gms.internal.firebase_ml.zztu
            int r3 = r1.size()
            int r3 = r3 + r0
            r2.<init>((int) r3)
            com.google.android.gms.internal.firebase_ml.zzvx r1 = (com.google.android.gms.internal.firebase_ml.zzvx) r1
            r2.addAll(r1)
            goto L_0x004d
        L_0x0066:
            boolean r2 = r1 instanceof com.google.android.gms.internal.firebase_ml.zzuy
            if (r2 == 0) goto L_0x0084
            boolean r2 = r1 instanceof com.google.android.gms.internal.firebase_ml.zztl
            if (r2 == 0) goto L_0x0084
            r2 = r1
            com.google.android.gms.internal.firebase_ml.zztl r2 = (com.google.android.gms.internal.firebase_ml.zztl) r2
            boolean r3 = r2.zzog()
            if (r3 != 0) goto L_0x0084
            int r1 = r1.size()
            int r1 = r1 + r0
            com.google.android.gms.internal.firebase_ml.zztl r0 = r2.zzcb(r1)
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r5, (long) r7, (java.lang.Object) r0)
            r1 = r0
        L_0x0084:
            int r0 = r1.size()
            int r2 = r6.size()
            if (r0 <= 0) goto L_0x0093
            if (r2 <= 0) goto L_0x0093
            r1.addAll(r6)
        L_0x0093:
            if (r0 <= 0) goto L_0x0096
            r6 = r1
        L_0x0096:
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r5, (long) r7, (java.lang.Object) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzty.a(java.lang.Object, java.lang.Object, long):void");
    }
}
