package com.google.android.gms.measurement.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzbk;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzey;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzp extends zzjh {
    zzp(zzjg zzjg) {
        super(zzjg);
    }

    private final Boolean zza(double d, zzbk.zzc zzc) {
        try {
            return zza(new BigDecimal(d), zzc, Math.ulp(d));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(long j, zzbk.zzc zzc) {
        try {
            return zza(new BigDecimal(j), zzc, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(zzbk.zza zza, String str, List<zzbs.zze> list, long j) {
        Boolean bool;
        String str2;
        Object obj;
        if (zza.zzkd()) {
            Boolean zza2 = zza(j, zza.zzke());
            if (zza2 == null) {
                return null;
            }
            if (!zza2.booleanValue()) {
                return false;
            }
        }
        HashSet hashSet = new HashSet();
        for (zzbk.zzb next : zza.zzkc()) {
            if (next.zzkr().isEmpty()) {
                zzab().zzgn().zza("null or empty param name in filter. event", zzy().a(str));
                return null;
            }
            hashSet.add(next.zzkr());
        }
        ArrayMap arrayMap = new ArrayMap();
        for (zzbs.zze next2 : list) {
            if (hashSet.contains(next2.getName())) {
                if (next2.zzna()) {
                    str2 = next2.getName();
                    if (next2.zzna()) {
                        obj = Long.valueOf(next2.zznb());
                        arrayMap.put(str2, obj);
                    }
                } else {
                    if (next2.zznd()) {
                        str2 = next2.getName();
                        if (next2.zznd()) {
                            obj = Double.valueOf(next2.zzne());
                        }
                    } else if (next2.zzmx()) {
                        str2 = next2.getName();
                        obj = next2.zzmy();
                    } else {
                        zzab().zzgn().zza("Unknown value for param. event, param", zzy().a(str), zzy().b(next2.getName()));
                        return null;
                    }
                    arrayMap.put(str2, obj);
                }
                obj = null;
                arrayMap.put(str2, obj);
            }
        }
        Iterator<zzbk.zzb> it = zza.zzkc().iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                return true;
            }
            zzbk.zzb next3 = it.next();
            if (!next3.zzkp() || !next3.zzkq()) {
                z = false;
            }
            String zzkr = next3.zzkr();
            if (zzkr.isEmpty()) {
                zzab().zzgn().zza("Event has empty param name. event", zzy().a(str));
                return null;
            }
            Object obj2 = arrayMap.get(zzkr);
            if (obj2 instanceof Long) {
                if (!next3.zzkn()) {
                    zzab().zzgn().zza("No number filter for long param. event, param", zzy().a(str), zzy().b(zzkr));
                    return null;
                }
                Boolean zza3 = zza(((Long) obj2).longValue(), next3.zzko());
                if (zza3 == null) {
                    return null;
                }
                if (zza3.booleanValue() == z) {
                    return false;
                }
            } else if (obj2 instanceof Double) {
                if (!next3.zzkn()) {
                    zzab().zzgn().zza("No number filter for double param. event, param", zzy().a(str), zzy().b(zzkr));
                    return null;
                }
                Boolean zza4 = zza(((Double) obj2).doubleValue(), next3.zzko());
                if (zza4 == null) {
                    return null;
                }
                if (zza4.booleanValue() == z) {
                    return false;
                }
            } else if (obj2 instanceof String) {
                if (next3.zzkl()) {
                    bool = zza((String) obj2, next3.zzkm());
                } else if (next3.zzkn()) {
                    String str3 = (String) obj2;
                    if (zzjo.a(str3)) {
                        bool = zza(str3, next3.zzko());
                    } else {
                        zzab().zzgn().zza("Invalid param value for number filter. event, param", zzy().a(str), zzy().b(zzkr));
                        return null;
                    }
                } else {
                    zzab().zzgn().zza("No filter for String param. event, param", zzy().a(str), zzy().b(zzkr));
                    return null;
                }
                if (bool == null) {
                    return null;
                }
                if (bool.booleanValue() == z) {
                    return false;
                }
            } else if (obj2 == null) {
                zzab().zzgs().zza("Missing param for filter. event, param", zzy().a(str), zzy().b(zzkr));
                return false;
            } else {
                zzab().zzgn().zza("Unknown param type. event, param", zzy().a(str), zzy().b(zzkr));
                return null;
            }
        }
    }

    private final Boolean zza(zzbk.zzd zzd, zzbs.zzk zzk) {
        zzeh zzgn;
        String str;
        Boolean zza;
        zzbk.zzb zzli = zzd.zzli();
        boolean zzkq = zzli.zzkq();
        if (zzk.zzna()) {
            if (!zzli.zzkn()) {
                zzgn = zzab().zzgn();
                str = "No number filter for long property. property";
            } else {
                zza = zza(zzk.zznb(), zzli.zzko());
                return zza(zza, zzkq);
            }
        } else if (zzk.zznd()) {
            if (!zzli.zzkn()) {
                zzgn = zzab().zzgn();
                str = "No number filter for double property. property";
            } else {
                zza = zza(zzk.zzne(), zzli.zzko());
                return zza(zza, zzkq);
            }
        } else if (zzk.zzmx()) {
            if (!zzli.zzkl()) {
                if (!zzli.zzkn()) {
                    zzab().zzgn().zza("No string or number filter defined. property", zzy().c(zzk.getName()));
                } else if (zzjo.a(zzk.zzmy())) {
                    zza = zza(zzk.zzmy(), zzli.zzko());
                } else {
                    zzab().zzgn().zza("Invalid user property value for Numeric number filter. property, value", zzy().c(zzk.getName()), zzk.zzmy());
                }
                return null;
            }
            zza = zza(zzk.zzmy(), zzli.zzkm());
            return zza(zza, zzkq);
        } else {
            zzgn = zzab().zzgn();
            str = "User property has no value, property";
        }
        zzgn.zza(str, zzy().c(zzk.getName()));
        return null;
    }

    @VisibleForTesting
    private static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z);
    }

    private final Boolean zza(String str, zzbk.zzc zzc) {
        if (!zzjo.a(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzc, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(String str, zzbk.zze.zza zza, boolean z, String str2, List<String> list, String str3) {
        boolean startsWith;
        if (str == null) {
            return null;
        }
        if (zza == zzbk.zze.zza.IN_LIST) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && zza != zzbk.zze.zza.REGEXP) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (zzo.a[zza.ordinal()]) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    zzab().zzgn().zza("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                startsWith = str.startsWith(str2);
                break;
            case 3:
                startsWith = str.endsWith(str2);
                break;
            case 4:
                startsWith = str.contains(str2);
                break;
            case 5:
                startsWith = str.equals(str2);
                break;
            case 6:
                startsWith = list.contains(str);
                break;
            default:
                return null;
        }
        return Boolean.valueOf(startsWith);
    }

    @VisibleForTesting
    private final Boolean zza(String str, zzbk.zze zze) {
        List<String> list;
        Preconditions.checkNotNull(zze);
        if (str == null || !zze.zzlk() || zze.zzll() == zzbk.zze.zza.UNKNOWN_MATCH_TYPE) {
            return null;
        }
        if (zze.zzll() == zzbk.zze.zza.IN_LIST) {
            if (zze.zzlr() == 0) {
                return null;
            }
        } else if (!zze.zzlm()) {
            return null;
        }
        zzbk.zze.zza zzll = zze.zzll();
        boolean zzlp = zze.zzlp();
        String zzln = (zzlp || zzll == zzbk.zze.zza.REGEXP || zzll == zzbk.zze.zza.IN_LIST) ? zze.zzln() : zze.zzln().toUpperCase(Locale.ENGLISH);
        if (zze.zzlr() == 0) {
            list = null;
        } else {
            List<String> zzlq = zze.zzlq();
            if (!zzlp) {
                ArrayList arrayList = new ArrayList(zzlq.size());
                for (String upperCase : zzlq) {
                    arrayList.add(upperCase.toUpperCase(Locale.ENGLISH));
                }
                zzlq = Collections.unmodifiableList(arrayList);
            }
            list = zzlq;
        }
        return zza(str, zzll, zzlp, zzln, list, zzll == zzbk.zze.zza.REGEXP ? zzln : null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0085, code lost:
        if (r2 != null) goto L_0x0087;
     */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Boolean zza(java.math.BigDecimal r7, com.google.android.gms.internal.measurement.zzbk.zzc r8, double r9) {
        /*
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)
            boolean r0 = r8.zzku()
            r1 = 0
            if (r0 == 0) goto L_0x0109
            com.google.android.gms.internal.measurement.zzbk$zzc$zzb r0 = r8.zzkv()
            com.google.android.gms.internal.measurement.zzbk$zzc$zzb r2 = com.google.android.gms.internal.measurement.zzbk.zzc.zzb.UNKNOWN_COMPARISON_TYPE
            if (r0 != r2) goto L_0x0014
            goto L_0x0109
        L_0x0014:
            com.google.android.gms.internal.measurement.zzbk$zzc$zzb r0 = r8.zzkv()
            com.google.android.gms.internal.measurement.zzbk$zzc$zzb r2 = com.google.android.gms.internal.measurement.zzbk.zzc.zzb.BETWEEN
            if (r0 != r2) goto L_0x0029
            boolean r0 = r8.zzla()
            if (r0 == 0) goto L_0x0028
            boolean r0 = r8.zzlc()
            if (r0 != 0) goto L_0x0030
        L_0x0028:
            return r1
        L_0x0029:
            boolean r0 = r8.zzky()
            if (r0 != 0) goto L_0x0030
            return r1
        L_0x0030:
            com.google.android.gms.internal.measurement.zzbk$zzc$zzb r0 = r8.zzkv()
            com.google.android.gms.internal.measurement.zzbk$zzc$zzb r2 = r8.zzkv()
            com.google.android.gms.internal.measurement.zzbk$zzc$zzb r3 = com.google.android.gms.internal.measurement.zzbk.zzc.zzb.BETWEEN
            if (r2 != r3) goto L_0x0067
            java.lang.String r2 = r8.zzlb()
            boolean r2 = com.google.android.gms.measurement.internal.zzjo.a((java.lang.String) r2)
            if (r2 == 0) goto L_0x0066
            java.lang.String r2 = r8.zzld()
            boolean r2 = com.google.android.gms.measurement.internal.zzjo.a((java.lang.String) r2)
            if (r2 != 0) goto L_0x0051
            goto L_0x0066
        L_0x0051:
            java.math.BigDecimal r2 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0066 }
            java.lang.String r3 = r8.zzlb()     // Catch:{ NumberFormatException -> 0x0066 }
            r2.<init>(r3)     // Catch:{ NumberFormatException -> 0x0066 }
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0066 }
            java.lang.String r8 = r8.zzld()     // Catch:{ NumberFormatException -> 0x0066 }
            r3.<init>(r8)     // Catch:{ NumberFormatException -> 0x0066 }
            r8 = r2
            r2 = r1
            goto L_0x007d
        L_0x0066:
            return r1
        L_0x0067:
            java.lang.String r2 = r8.zzkz()
            boolean r2 = com.google.android.gms.measurement.internal.zzjo.a((java.lang.String) r2)
            if (r2 != 0) goto L_0x0072
            return r1
        L_0x0072:
            java.math.BigDecimal r2 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0109 }
            java.lang.String r8 = r8.zzkz()     // Catch:{ NumberFormatException -> 0x0109 }
            r2.<init>(r8)     // Catch:{ NumberFormatException -> 0x0109 }
            r8 = r1
            r3 = r8
        L_0x007d:
            com.google.android.gms.internal.measurement.zzbk$zzc$zzb r4 = com.google.android.gms.internal.measurement.zzbk.zzc.zzb.BETWEEN
            if (r0 != r4) goto L_0x0085
            if (r8 == 0) goto L_0x0084
            goto L_0x0087
        L_0x0084:
            return r1
        L_0x0085:
            if (r2 == 0) goto L_0x0109
        L_0x0087:
            int[] r4 = com.google.android.gms.measurement.internal.zzo.b
            int r0 = r0.ordinal()
            r0 = r4[r0]
            r4 = -1
            r5 = 0
            r6 = 1
            switch(r0) {
                case 1: goto L_0x00fd;
                case 2: goto L_0x00f1;
                case 3: goto L_0x00a8;
                case 4: goto L_0x0096;
                default: goto L_0x0095;
            }
        L_0x0095:
            goto L_0x0109
        L_0x0096:
            int r8 = r7.compareTo(r8)
            if (r8 == r4) goto L_0x00a3
            int r7 = r7.compareTo(r3)
            if (r7 == r6) goto L_0x00a3
            r5 = 1
        L_0x00a3:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00a8:
            r0 = 0
            int r8 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r8 == 0) goto L_0x00e5
            java.math.BigDecimal r8 = new java.math.BigDecimal
            r8.<init>(r9)
            java.math.BigDecimal r0 = new java.math.BigDecimal
            r1 = 2
            r0.<init>(r1)
            java.math.BigDecimal r8 = r8.multiply(r0)
            java.math.BigDecimal r8 = r2.subtract(r8)
            int r8 = r7.compareTo(r8)
            if (r8 != r6) goto L_0x00e0
            java.math.BigDecimal r8 = new java.math.BigDecimal
            r8.<init>(r9)
            java.math.BigDecimal r9 = new java.math.BigDecimal
            r9.<init>(r1)
            java.math.BigDecimal r8 = r8.multiply(r9)
            java.math.BigDecimal r8 = r2.add(r8)
            int r7 = r7.compareTo(r8)
            if (r7 != r4) goto L_0x00e0
            r5 = 1
        L_0x00e0:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00e5:
            int r7 = r7.compareTo(r2)
            if (r7 != 0) goto L_0x00ec
            r5 = 1
        L_0x00ec:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00f1:
            int r7 = r7.compareTo(r2)
            if (r7 != r6) goto L_0x00f8
            r5 = 1
        L_0x00f8:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00fd:
            int r7 = r7.compareTo(r2)
            if (r7 != r4) goto L_0x0104
            r5 = 1
        L_0x0104:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x0109:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzp.zza(java.math.BigDecimal, com.google.android.gms.internal.measurement.zzbk$zzc, double):java.lang.Boolean");
    }

    private static List<zzbs.zzb> zza(Map<Integer, Long> map) {
        if (map == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(map.size());
        for (Integer intValue : map.keySet()) {
            int intValue2 = intValue.intValue();
            arrayList.add((zzbs.zzb) ((zzey) zzbs.zzb.zzmh().zzk(intValue2).zzae(map.get(Integer.valueOf(intValue2)).longValue()).zzug()));
        }
        return arrayList;
    }

    private static void zza(Map<Integer, Long> map, int i, long j) {
        Long l = map.get(Integer.valueOf(i));
        long j2 = j / 1000;
        if (l == null || j2 > l.longValue()) {
            map.put(Integer.valueOf(i), Long.valueOf(j2));
        }
    }

    private static void zzb(Map<Integer, List<Long>> map, int i, long j) {
        List list = map.get(Integer.valueOf(i));
        if (list == null) {
            list = new ArrayList();
            map.put(Integer.valueOf(i), list);
        }
        list.add(Long.valueOf(j / 1000));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v22, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v55, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v15, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v3, resolved type: androidx.collection.ArrayMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v28, resolved type: androidx.collection.ArrayMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v7, resolved type: androidx.collection.ArrayMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v86, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v39, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r39v4, resolved type: java.util.ArrayList} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:409:0x0cc5, code lost:
        if (r0.zzkb() == false) goto L_0x0cd0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:410:0x0cc7, code lost:
        r8 = java.lang.Integer.valueOf(r0.getId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:411:0x0cd0, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:412:0x0cd1, code lost:
        r1.zza("Invalid property filter ID. appId, id", r4, java.lang.String.valueOf(r8));
        r15.add(java.lang.Integer.valueOf(r5));
        r0 = r66;
        r1 = r67;
        r7 = r14;
        r3 = r16;
        r4 = r18;
        r12 = r24;
        r43 = r40;
        r24 = r42;
        r8 = r65;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0361  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0474  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0491  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x04ab  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x0530  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x05ae  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x063a  */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x065b  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x0756  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x09bf  */
    /* JADX WARNING: Removed duplicated region for block: B:418:0x0d28  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01e1  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzbs.zza> a(java.lang.String r65, java.util.List<com.google.android.gms.internal.measurement.zzbs.zzc> r66, java.util.List<com.google.android.gms.internal.measurement.zzbs.zzk> r67) {
        /*
            r64 = this;
            r7 = r64
            r9 = r65
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r65)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r66)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r67)
            java.util.HashSet r15 = new java.util.HashSet
            r15.<init>()
            androidx.collection.ArrayMap r13 = new androidx.collection.ArrayMap
            r13.<init>()
            androidx.collection.ArrayMap r14 = new androidx.collection.ArrayMap
            r14.<init>()
            androidx.collection.ArrayMap r11 = new androidx.collection.ArrayMap
            r11.<init>()
            androidx.collection.ArrayMap r12 = new androidx.collection.ArrayMap
            r12.<init>()
            androidx.collection.ArrayMap r10 = new androidx.collection.ArrayMap
            r10.<init>()
            com.google.android.gms.measurement.internal.zzs r0 = r64.zzad()
            boolean r25 = r0.f(r9)
            com.google.android.gms.measurement.internal.zzs r0 = r64.zzad()
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzak.zziq
            boolean r26 = r0.zzd(r9, r1)
            com.google.android.gms.measurement.internal.zzs r0 = r64.zzad()
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzak.zziy
            boolean r27 = r0.zzd(r9, r1)
            com.google.android.gms.measurement.internal.zzs r0 = r64.zzad()
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzak.zziz
            boolean r28 = r0.zzd(r9, r1)
            if (r27 != 0) goto L_0x0055
            if (r28 == 0) goto L_0x007c
        L_0x0055:
            java.util.Iterator r0 = r66.iterator()
        L_0x0059:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x007c
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.measurement.zzbs$zzc r1 = (com.google.android.gms.internal.measurement.zzbs.zzc) r1
            java.lang.String r2 = "_s"
            java.lang.String r3 = r1.getName()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0059
            long r0 = r1.getTimestampMillis()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r29 = r0
            goto L_0x007e
        L_0x007c:
            r29 = 0
        L_0x007e:
            r6 = 1
            r4 = 0
            if (r29 == 0) goto L_0x00c1
            if (r28 == 0) goto L_0x00c1
            com.google.android.gms.measurement.internal.zzx r1 = r64.zzgy()
            r1.c()
            r1.zzo()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r65)
            android.content.ContentValues r0 = new android.content.ContentValues
            r0.<init>()
            java.lang.String r2 = "current_session_count"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
            r0.put(r2, r3)
            android.database.sqlite.SQLiteDatabase r2 = r1.d()     // Catch:{ SQLiteException -> 0x00af }
            java.lang.String r3 = "events"
            java.lang.String r5 = "app_id = ?"
            java.lang.String[] r8 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x00af }
            r8[r4] = r9     // Catch:{ SQLiteException -> 0x00af }
            r2.update(r3, r0, r5, r8)     // Catch:{ SQLiteException -> 0x00af }
            goto L_0x00c1
        L_0x00af:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzef r1 = r1.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()
            java.lang.String r2 = "Error resetting session-scoped event counts. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r65)
            r1.zza(r2, r3, r0)
        L_0x00c1:
            com.google.android.gms.measurement.internal.zzx r0 = r64.zzgy()
            java.util.Map r0 = r0.b(r9)
            if (r0 == 0) goto L_0x0353
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0353
            java.util.HashSet r1 = new java.util.HashSet
            java.util.Set r2 = r0.keySet()
            r1.<init>(r2)
            if (r27 == 0) goto L_0x01d6
            if (r29 == 0) goto L_0x01d6
            com.google.android.gms.measurement.internal.zzp r2 = r64.zzgx()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r65)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)
            androidx.collection.ArrayMap r3 = new androidx.collection.ArrayMap
            r3.<init>()
            boolean r5 = r0.isEmpty()
            if (r5 != 0) goto L_0x01d7
            com.google.android.gms.measurement.internal.zzx r5 = r2.zzgy()
            java.util.Map r5 = r5.a((java.lang.String) r9)
            java.util.Set r8 = r0.keySet()
            java.util.Iterator r8 = r8.iterator()
        L_0x0103:
            boolean r17 = r8.hasNext()
            if (r17 == 0) goto L_0x01d7
            java.lang.Object r17 = r8.next()
            java.lang.Integer r17 = (java.lang.Integer) r17
            int r17 = r17.intValue()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r17)
            java.lang.Object r6 = r0.get(r6)
            com.google.android.gms.internal.measurement.zzbs$zzi r6 = (com.google.android.gms.internal.measurement.zzbs.zzi) r6
            java.lang.Integer r4 = java.lang.Integer.valueOf(r17)
            java.lang.Object r4 = r5.get(r4)
            java.util.List r4 = (java.util.List) r4
            if (r4 == 0) goto L_0x01bf
            boolean r20 = r4.isEmpty()
            if (r20 == 0) goto L_0x0131
            goto L_0x01bf
        L_0x0131:
            r20 = r5
            com.google.android.gms.measurement.internal.zzjo r5 = r2.zzgw()
            r21 = r8
            java.util.List r8 = r6.zzpy()
            java.util.List r5 = r5.a((java.util.List<java.lang.Long>) r8, (java.util.List<java.lang.Integer>) r4)
            boolean r8 = r5.isEmpty()
            if (r8 != 0) goto L_0x01ba
            com.google.android.gms.internal.measurement.zzey$zza r8 = r6.zzuj()
            com.google.android.gms.internal.measurement.zzey$zza r8 = (com.google.android.gms.internal.measurement.zzey.zza) r8
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r8 = (com.google.android.gms.internal.measurement.zzbs.zzi.zza) r8
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r8 = r8.zzqr()
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r5 = r8.zzo(r5)
            com.google.android.gms.measurement.internal.zzjo r8 = r2.zzgw()
            r22 = r2
            java.util.List r2 = r6.zzpv()
            java.util.List r2 = r8.a((java.util.List<java.lang.Long>) r2, (java.util.List<java.lang.Integer>) r4)
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r8 = r5.zzqq()
            r8.zzn(r2)
            r2 = 0
        L_0x016d:
            int r8 = r6.zzqc()
            if (r2 >= r8) goto L_0x018b
            com.google.android.gms.internal.measurement.zzbs$zzb r8 = r6.zzae(r2)
            int r8 = r8.getIndex()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            boolean r8 = r4.contains(r8)
            if (r8 == 0) goto L_0x0188
            r5.zzaj(r2)
        L_0x0188:
            int r2 = r2 + 1
            goto L_0x016d
        L_0x018b:
            r2 = 0
        L_0x018c:
            int r8 = r6.zzqf()
            if (r2 >= r8) goto L_0x01aa
            com.google.android.gms.internal.measurement.zzbs$zzj r8 = r6.zzag(r2)
            int r8 = r8.getIndex()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            boolean r8 = r4.contains(r8)
            if (r8 == 0) goto L_0x01a7
            r5.zzak(r2)
        L_0x01a7:
            int r2 = r2 + 1
            goto L_0x018c
        L_0x01aa:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r17)
            com.google.android.gms.internal.measurement.zzgi r4 = r5.zzug()
            com.google.android.gms.internal.measurement.zzey r4 = (com.google.android.gms.internal.measurement.zzey) r4
            com.google.android.gms.internal.measurement.zzbs$zzi r4 = (com.google.android.gms.internal.measurement.zzbs.zzi) r4
            r3.put(r2, r4)
            goto L_0x01cc
        L_0x01ba:
            r5 = r20
            r8 = r21
            goto L_0x01d2
        L_0x01bf:
            r22 = r2
            r20 = r5
            r21 = r8
            java.lang.Integer r2 = java.lang.Integer.valueOf(r17)
            r3.put(r2, r6)
        L_0x01cc:
            r5 = r20
            r8 = r21
            r2 = r22
        L_0x01d2:
            r4 = 0
            r6 = 1
            goto L_0x0103
        L_0x01d6:
            r3 = r0
        L_0x01d7:
            java.util.Iterator r1 = r1.iterator()
        L_0x01db:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0353
            java.lang.Object r2 = r1.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
            java.lang.Object r4 = r3.get(r4)
            com.google.android.gms.internal.measurement.zzbs$zzi r4 = (com.google.android.gms.internal.measurement.zzbs.zzi) r4
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)
            java.lang.Object r5 = r14.get(r5)
            java.util.BitSet r5 = (java.util.BitSet) r5
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
            java.lang.Object r6 = r11.get(r6)
            java.util.BitSet r6 = (java.util.BitSet) r6
            if (r25 == 0) goto L_0x026e
            androidx.collection.ArrayMap r8 = new androidx.collection.ArrayMap
            r8.<init>()
            if (r4 == 0) goto L_0x0262
            int r17 = r4.zzqc()
            if (r17 != 0) goto L_0x0219
            goto L_0x0262
        L_0x0219:
            java.util.List r17 = r4.zzqb()
            java.util.Iterator r17 = r17.iterator()
        L_0x0221:
            boolean r20 = r17.hasNext()
            if (r20 == 0) goto L_0x0262
            java.lang.Object r20 = r17.next()
            com.google.android.gms.internal.measurement.zzbs$zzb r20 = (com.google.android.gms.internal.measurement.zzbs.zzb) r20
            boolean r21 = r20.zzme()
            if (r21 == 0) goto L_0x0259
            int r21 = r20.getIndex()
            r22 = r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r21)
            boolean r21 = r20.zzmf()
            if (r21 == 0) goto L_0x0252
            long r20 = r20.zzmg()
            java.lang.Long r20 = java.lang.Long.valueOf(r20)
            r63 = r20
            r20 = r3
            r3 = r63
            goto L_0x0255
        L_0x0252:
            r20 = r3
            r3 = 0
        L_0x0255:
            r8.put(r1, r3)
            goto L_0x025d
        L_0x0259:
            r22 = r1
            r20 = r3
        L_0x025d:
            r3 = r20
            r1 = r22
            goto L_0x0221
        L_0x0262:
            r22 = r1
            r20 = r3
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r12.put(r1, r8)
            goto L_0x0273
        L_0x026e:
            r22 = r1
            r20 = r3
            r8 = 0
        L_0x0273:
            if (r5 != 0) goto L_0x028d
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r14.put(r1, r5)
            java.util.BitSet r6 = new java.util.BitSet
            r6.<init>()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r11.put(r1, r6)
        L_0x028d:
            if (r4 == 0) goto L_0x02ea
            r1 = 0
        L_0x0290:
            int r3 = r4.zzpw()
            int r3 = r3 << 6
            if (r1 >= r3) goto L_0x02ea
            java.util.List r3 = r4.zzpv()
            boolean r3 = com.google.android.gms.measurement.internal.zzjo.a((java.util.List<java.lang.Long>) r3, (int) r1)
            if (r3 == 0) goto L_0x02cf
            com.google.android.gms.measurement.internal.zzef r3 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgs()
            r17 = r11
            java.lang.String r11 = "Filter already evaluated. audience ID, filter ID"
            r21 = r12
            java.lang.Integer r12 = java.lang.Integer.valueOf(r2)
            r23 = r14
            java.lang.Integer r14 = java.lang.Integer.valueOf(r1)
            r3.zza(r11, r12, r14)
            r6.set(r1)
            java.util.List r3 = r4.zzpy()
            boolean r3 = com.google.android.gms.measurement.internal.zzjo.a((java.util.List<java.lang.Long>) r3, (int) r1)
            if (r3 == 0) goto L_0x02d5
            r5.set(r1)
            r3 = 1
            goto L_0x02d6
        L_0x02cf:
            r17 = r11
            r21 = r12
            r23 = r14
        L_0x02d5:
            r3 = 0
        L_0x02d6:
            if (r8 == 0) goto L_0x02e1
            if (r3 != 0) goto L_0x02e1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)
            r8.remove(r3)
        L_0x02e1:
            int r1 = r1 + 1
            r11 = r17
            r12 = r21
            r14 = r23
            goto L_0x0290
        L_0x02ea:
            r17 = r11
            r21 = r12
            r23 = r14
            com.google.android.gms.internal.measurement.zzbs$zza$zza r1 = com.google.android.gms.internal.measurement.zzbs.zza.zzmc()
            r3 = 0
            com.google.android.gms.internal.measurement.zzbs$zza$zza r1 = r1.zzk(r3)
            if (r27 == 0) goto L_0x0309
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            java.lang.Object r3 = r0.get(r3)
            com.google.android.gms.internal.measurement.zzbs$zzi r3 = (com.google.android.gms.internal.measurement.zzbs.zzi) r3
            r1.zza((com.google.android.gms.internal.measurement.zzbs.zzi) r3)
            goto L_0x030c
        L_0x0309:
            r1.zza((com.google.android.gms.internal.measurement.zzbs.zzi) r4)
        L_0x030c:
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r3 = com.google.android.gms.internal.measurement.zzbs.zzi.zzqh()
            java.util.List r4 = com.google.android.gms.measurement.internal.zzjo.a((java.util.BitSet) r5)
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r3 = r3.zzo(r4)
            java.util.List r4 = com.google.android.gms.measurement.internal.zzjo.a((java.util.BitSet) r6)
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r3 = r3.zzn(r4)
            if (r25 == 0) goto L_0x0335
            java.util.List r4 = zza(r8)
            r3.zzp(r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
            androidx.collection.ArrayMap r5 = new androidx.collection.ArrayMap
            r5.<init>()
            r10.put(r4, r5)
        L_0x0335:
            r1.zza((com.google.android.gms.internal.measurement.zzbs.zzi.zza) r3)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            com.google.android.gms.internal.measurement.zzgi r1 = r1.zzug()
            com.google.android.gms.internal.measurement.zzey r1 = (com.google.android.gms.internal.measurement.zzey) r1
            com.google.android.gms.internal.measurement.zzbs$zza r1 = (com.google.android.gms.internal.measurement.zzbs.zza) r1
            r13.put(r2, r1)
            r11 = r17
            r3 = r20
            r12 = r21
            r1 = r22
            r14 = r23
            goto L_0x01db
        L_0x0353:
            r17 = r11
            r21 = r12
            r23 = r14
            boolean r0 = r66.isEmpty()
            r14 = 256(0x100, float:3.59E-43)
            if (r0 != 0) goto L_0x09ae
            androidx.collection.ArrayMap r12 = new androidx.collection.ArrayMap
            r12.<init>()
            java.util.Iterator r30 = r66.iterator()
            r31 = 0
            r1 = r31
            r0 = 0
            r8 = 0
        L_0x0370:
            boolean r3 = r30.hasNext()
            if (r3 == 0) goto L_0x09ae
            java.lang.Object r3 = r30.next()
            r6 = r3
            com.google.android.gms.internal.measurement.zzbs$zzc r6 = (com.google.android.gms.internal.measurement.zzbs.zzc) r6
            java.lang.String r4 = r6.getName()
            java.util.List r20 = r6.zzmj()
            r64.zzgw()
            java.lang.String r3 = "_eid"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzjo.b(r6, r3)
            r5 = r3
            java.lang.Long r5 = (java.lang.Long) r5
            if (r5 == 0) goto L_0x0395
            r3 = 1
            goto L_0x0396
        L_0x0395:
            r3 = 0
        L_0x0396:
            if (r3 == 0) goto L_0x03a2
            java.lang.String r11 = "_ep"
            boolean r11 = r4.equals(r11)
            if (r11 == 0) goto L_0x03a2
            r11 = 1
            goto L_0x03a3
        L_0x03a2:
            r11 = 0
        L_0x03a3:
            r33 = 1
            if (r11 == 0) goto L_0x04d4
            r64.zzgw()
            java.lang.String r3 = "_en"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzjo.b(r6, r3)
            r11 = r3
            java.lang.String r11 = (java.lang.String) r11
            boolean r3 = android.text.TextUtils.isEmpty(r11)
            if (r3 == 0) goto L_0x03ca
            com.google.android.gms.measurement.internal.zzef r3 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()
            java.lang.String r4 = "Extra parameter without an event name. eventId"
            r3.zza(r4, r5)
            r19 = r15
            goto L_0x04d0
        L_0x03ca:
            if (r8 == 0) goto L_0x03de
            if (r0 == 0) goto L_0x03de
            long r3 = r5.longValue()
            long r35 = r0.longValue()
            int r24 = (r3 > r35 ? 1 : (r3 == r35 ? 0 : -1))
            if (r24 == 0) goto L_0x03db
            goto L_0x03de
        L_0x03db:
            r24 = r0
            goto L_0x0408
        L_0x03de:
            com.google.android.gms.measurement.internal.zzx r3 = r64.zzgy()
            android.util.Pair r3 = r3.zza((java.lang.String) r9, (java.lang.Long) r5)
            if (r3 == 0) goto L_0x04c1
            java.lang.Object r4 = r3.first
            if (r4 != 0) goto L_0x03ee
            goto L_0x04c1
        L_0x03ee:
            java.lang.Object r0 = r3.first
            com.google.android.gms.internal.measurement.zzbs$zzc r0 = (com.google.android.gms.internal.measurement.zzbs.zzc) r0
            java.lang.Object r1 = r3.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            r64.zzgw()
            java.lang.String r3 = "_eid"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzjo.b(r0, r3)
            java.lang.Long r3 = (java.lang.Long) r3
            r8 = r0
            r24 = r3
        L_0x0408:
            long r35 = r1 - r33
            int r0 = (r35 > r31 ? 1 : (r35 == r31 ? 0 : -1))
            if (r0 > 0) goto L_0x044c
            com.google.android.gms.measurement.internal.zzx r1 = r64.zzgy()
            r1.zzo()
            com.google.android.gms.measurement.internal.zzef r0 = r1.zzab()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgs()
            java.lang.String r2 = "Clearing complex main event info. appId"
            r0.zza(r2, r9)
            android.database.sqlite.SQLiteDatabase r0 = r1.d()     // Catch:{ SQLiteException -> 0x0437 }
            java.lang.String r2 = "delete from main_event_params where app_id=?"
            r4 = 1
            java.lang.String[] r3 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0435 }
            r18 = 0
            r3[r18] = r9     // Catch:{ SQLiteException -> 0x0433 }
            r0.execSQL(r2, r3)     // Catch:{ SQLiteException -> 0x0433 }
            goto L_0x0448
        L_0x0433:
            r0 = move-exception
            goto L_0x043b
        L_0x0435:
            r0 = move-exception
            goto L_0x0439
        L_0x0437:
            r0 = move-exception
            r4 = 1
        L_0x0439:
            r18 = 0
        L_0x043b:
            com.google.android.gms.measurement.internal.zzef r1 = r1.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()
            java.lang.String r2 = "Error clearing complex main event"
            r1.zza(r2, r0)
        L_0x0448:
            r19 = r15
            r15 = r6
            goto L_0x0461
        L_0x044c:
            r4 = 1
            r18 = 0
            com.google.android.gms.measurement.internal.zzx r1 = r64.zzgy()
            r2 = r65
            r3 = r5
            r18 = 1
            r4 = r35
            r19 = r15
            r15 = r6
            r6 = r8
            r1.zza(r2, r3, r4, r6)
        L_0x0461:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r1 = r8.zzmj()
            java.util.Iterator r1 = r1.iterator()
        L_0x046e:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x048b
            java.lang.Object r2 = r1.next()
            com.google.android.gms.internal.measurement.zzbs$zze r2 = (com.google.android.gms.internal.measurement.zzbs.zze) r2
            r64.zzgw()
            java.lang.String r3 = r2.getName()
            com.google.android.gms.internal.measurement.zzbs$zze r3 = com.google.android.gms.measurement.internal.zzjo.a((com.google.android.gms.internal.measurement.zzbs.zzc) r15, (java.lang.String) r3)
            if (r3 != 0) goto L_0x046e
            r0.add(r2)
            goto L_0x046e
        L_0x048b:
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x04ab
            java.util.Iterator r1 = r20.iterator()
        L_0x0495:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x04a5
            java.lang.Object r2 = r1.next()
            com.google.android.gms.internal.measurement.zzbs$zze r2 = (com.google.android.gms.internal.measurement.zzbs.zze) r2
            r0.add(r2)
            goto L_0x0495
        L_0x04a5:
            r39 = r0
            r37 = r8
            r0 = r11
            goto L_0x04bd
        L_0x04ab:
            com.google.android.gms.measurement.internal.zzef r0 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgn()
            java.lang.String r1 = "No unique parameters in main event. eventName"
            r0.zza(r1, r11)
            r37 = r8
            r0 = r11
            r39 = r20
        L_0x04bd:
            r38 = r24
            goto L_0x0522
        L_0x04c1:
            r19 = r15
            com.google.android.gms.measurement.internal.zzef r3 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()
            java.lang.String r4 = "Extra parameter without existing main event. eventName, eventId"
            r3.zza(r4, r11, r5)
        L_0x04d0:
            r15 = r19
            goto L_0x0370
        L_0x04d4:
            r19 = r15
            r15 = r6
            if (r3 == 0) goto L_0x0518
            r64.zzgw()
            java.lang.String r0 = "_epc"
            java.lang.Long r1 = java.lang.Long.valueOf(r31)
            java.lang.Object r0 = com.google.android.gms.measurement.internal.zzjo.b(r15, r0)
            if (r0 != 0) goto L_0x04e9
            r0 = r1
        L_0x04e9:
            java.lang.Long r0 = (java.lang.Long) r0
            long r35 = r0.longValue()
            int r0 = (r35 > r31 ? 1 : (r35 == r31 ? 0 : -1))
            if (r0 > 0) goto L_0x0503
            com.google.android.gms.measurement.internal.zzef r0 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgn()
            java.lang.String r1 = "Complex event with zero extra param count. eventName"
            r0.zza(r1, r4)
            r11 = r4
            r0 = r5
            goto L_0x0512
        L_0x0503:
            com.google.android.gms.measurement.internal.zzx r1 = r64.zzgy()
            r2 = r65
            r3 = r5
            r11 = r4
            r0 = r5
            r4 = r35
            r6 = r15
            r1.zza(r2, r3, r4, r6)
        L_0x0512:
            r38 = r0
            r0 = r11
            r37 = r15
            goto L_0x0520
        L_0x0518:
            r11 = r4
            r38 = r0
            r35 = r1
            r37 = r8
            r0 = r11
        L_0x0520:
            r39 = r20
        L_0x0522:
            com.google.android.gms.measurement.internal.zzx r1 = r64.zzgy()
            java.lang.String r2 = r15.getName()
            com.google.android.gms.measurement.internal.zzae r1 = r1.zzc(r9, r2)
            if (r1 != 0) goto L_0x05ae
            com.google.android.gms.measurement.internal.zzef r1 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgn()
            java.lang.String r2 = "Event aggregate wasn't created during raw event logging. appId, event"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r65)
            com.google.android.gms.measurement.internal.zzed r4 = r64.zzy()
            java.lang.String r4 = r4.a((java.lang.String) r0)
            r1.zza(r2, r3, r4)
            if (r28 == 0) goto L_0x057e
            com.google.android.gms.measurement.internal.zzae r1 = new com.google.android.gms.measurement.internal.zzae
            r5 = 0
            r8 = r1
            java.lang.String r2 = r15.getName()
            r6 = r10
            r10 = r2
            r2 = 1
            r41 = r12
            r4 = r17
            r40 = r21
            r11 = r2
            r42 = r13
            r43 = r23
            r13 = r2
            r45 = r15
            r44 = r19
            r15 = r2
            long r17 = r45.getTimestampMillis()
            r19 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r3 = r9
            r9 = r65
            r8.<init>(r9, r10, r11, r13, r15, r17, r19, r21, r22, r23, r24)
            goto L_0x05f5
        L_0x057e:
            r3 = r9
            r6 = r10
            r41 = r12
            r42 = r13
            r45 = r15
            r4 = r17
            r44 = r19
            r40 = r21
            r43 = r23
            r5 = 0
            com.google.android.gms.measurement.internal.zzae r1 = new com.google.android.gms.measurement.internal.zzae
            java.lang.String r10 = r45.getName()
            r11 = 1
            r13 = 1
            long r15 = r45.getTimestampMillis()
            r17 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r8 = r1
            r9 = r65
            r8.<init>(r9, r10, r11, r13, r15, r17, r19, r20, r21, r22)
            goto L_0x05f5
        L_0x05ae:
            r3 = r9
            r6 = r10
            r41 = r12
            r42 = r13
            r45 = r15
            r4 = r17
            r44 = r19
            r40 = r21
            r43 = r23
            r5 = 0
            if (r28 == 0) goto L_0x05f8
            com.google.android.gms.measurement.internal.zzae r2 = new com.google.android.gms.measurement.internal.zzae
            r46 = r2
            java.lang.String r8 = r1.a
            r47 = r8
            java.lang.String r8 = r1.b
            r48 = r8
            long r8 = r1.c
            long r49 = r8 + r33
            long r8 = r1.d
            long r51 = r8 + r33
            long r8 = r1.e
            long r53 = r8 + r33
            long r8 = r1.f
            r55 = r8
            long r8 = r1.g
            r57 = r8
            java.lang.Long r8 = r1.h
            r59 = r8
            java.lang.Long r8 = r1.i
            r60 = r8
            java.lang.Long r8 = r1.j
            r61 = r8
            java.lang.Boolean r1 = r1.k
            r62 = r1
            r46.<init>(r47, r48, r49, r51, r53, r55, r57, r59, r60, r61, r62)
            r8 = r2
        L_0x05f5:
            r33 = r6
            goto L_0x0627
        L_0x05f8:
            com.google.android.gms.measurement.internal.zzae r2 = new com.google.android.gms.measurement.internal.zzae
            r8 = r2
            java.lang.String r9 = r1.a
            java.lang.String r10 = r1.b
            long r11 = r1.c
            long r11 = r11 + r33
            long r13 = r1.d
            long r13 = r13 + r33
            r33 = r6
            long r5 = r1.e
            r15 = r5
            long r5 = r1.f
            r17 = r5
            long r5 = r1.g
            r19 = r5
            java.lang.Long r5 = r1.h
            r21 = r5
            java.lang.Long r5 = r1.i
            r22 = r5
            java.lang.Long r5 = r1.j
            r23 = r5
            java.lang.Boolean r1 = r1.k
            r24 = r1
            r8.<init>(r9, r10, r11, r13, r15, r17, r19, r21, r22, r23, r24)
        L_0x0627:
            com.google.android.gms.measurement.internal.zzx r1 = r64.zzgy()
            r1.zza((com.google.android.gms.measurement.internal.zzae) r8)
            long r9 = r8.c
            r11 = r41
            java.lang.Object r1 = r11.get(r0)
            java.util.Map r1 = (java.util.Map) r1
            if (r1 != 0) goto L_0x064c
            com.google.android.gms.measurement.internal.zzx r1 = r64.zzgy()
            java.util.Map r1 = r1.a((java.lang.String) r3, (java.lang.String) r0)
            if (r1 != 0) goto L_0x0649
            androidx.collection.ArrayMap r1 = new androidx.collection.ArrayMap
            r1.<init>()
        L_0x0649:
            r11.put(r0, r1)
        L_0x064c:
            r12 = r1
            java.util.Set r1 = r12.keySet()
            java.util.Iterator r13 = r1.iterator()
        L_0x0655:
            boolean r1 = r13.hasNext()
            if (r1 == 0) goto L_0x0992
            java.lang.Object r1 = r13.next()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r14 = r1.intValue()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            r15 = r44
            boolean r1 = r15.contains(r1)
            if (r1 == 0) goto L_0x0685
            com.google.android.gms.measurement.internal.zzef r1 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgs()
            java.lang.String r2 = "Skipping failed audience ID"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r14)
            r1.zza(r2, r5)
            r44 = r15
            goto L_0x0655
        L_0x0685:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            r5 = r43
            java.lang.Object r1 = r5.get(r1)
            java.util.BitSet r1 = (java.util.BitSet) r1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r14)
            java.lang.Object r2 = r4.get(r2)
            java.util.BitSet r2 = (java.util.BitSet) r2
            if (r25 == 0) goto L_0x06bc
            java.lang.Integer r6 = java.lang.Integer.valueOf(r14)
            r16 = r9
            r9 = r40
            java.lang.Object r6 = r9.get(r6)
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Integer r10 = java.lang.Integer.valueOf(r14)
            r18 = r6
            r6 = r33
            java.lang.Object r10 = r6.get(r10)
            java.util.Map r10 = (java.util.Map) r10
            r19 = r1
            goto L_0x06c7
        L_0x06bc:
            r16 = r9
            r6 = r33
            r9 = r40
            r19 = r1
            r10 = 0
            r18 = 0
        L_0x06c7:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            r20 = r10
            r10 = r42
            java.lang.Object r1 = r10.get(r1)
            com.google.android.gms.internal.measurement.zzbs$zza r1 = (com.google.android.gms.internal.measurement.zzbs.zza) r1
            if (r1 != 0) goto L_0x0738
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            com.google.android.gms.internal.measurement.zzbs$zza$zza r2 = com.google.android.gms.internal.measurement.zzbs.zza.zzmc()
            r41 = r11
            r11 = 1
            com.google.android.gms.internal.measurement.zzbs$zza$zza r2 = r2.zzk(r11)
            com.google.android.gms.internal.measurement.zzgi r2 = r2.zzug()
            com.google.android.gms.internal.measurement.zzey r2 = (com.google.android.gms.internal.measurement.zzey) r2
            com.google.android.gms.internal.measurement.zzbs$zza r2 = (com.google.android.gms.internal.measurement.zzbs.zza) r2
            r10.put(r1, r2)
            java.util.BitSet r1 = new java.util.BitSet
            r1.<init>()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r14)
            r5.put(r2, r1)
            java.util.BitSet r2 = new java.util.BitSet
            r2.<init>()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r14)
            r4.put(r11, r2)
            if (r25 == 0) goto L_0x0733
            androidx.collection.ArrayMap r11 = new androidx.collection.ArrayMap
            r11.<init>()
            r19 = r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            r9.put(r1, r11)
            androidx.collection.ArrayMap r1 = new androidx.collection.ArrayMap
            r1.<init>()
            r21 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r14)
            r6.put(r2, r1)
            r18 = r13
            r2 = r21
            r13 = r1
            r63 = r19
            r19 = r11
            r11 = r63
            goto L_0x0742
        L_0x0733:
            r19 = r1
            r21 = r2
            goto L_0x073a
        L_0x0738:
            r41 = r11
        L_0x073a:
            r11 = r19
            r19 = r18
            r18 = r13
            r13 = r20
        L_0x0742:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            java.lang.Object r1 = r12.get(r1)
            java.util.List r1 = (java.util.List) r1
            java.util.Iterator r20 = r1.iterator()
        L_0x0750:
            boolean r1 = r20.hasNext()
            if (r1 == 0) goto L_0x097e
            java.lang.Object r1 = r20.next()
            com.google.android.gms.internal.measurement.zzbk$zza r1 = (com.google.android.gms.internal.measurement.zzbk.zza) r1
            if (r28 == 0) goto L_0x076d
            if (r27 == 0) goto L_0x076d
            boolean r21 = r1.zzki()
            if (r21 == 0) goto L_0x076d
            r21 = r2
            long r2 = r8.e
            r22 = r2
            goto L_0x0771
        L_0x076d:
            r21 = r2
            r22 = r16
        L_0x0771:
            com.google.android.gms.measurement.internal.zzef r2 = r64.zzab()
            r3 = 2
            boolean r2 = r2.a((int) r3)
            if (r2 == 0) goto L_0x07cd
            com.google.android.gms.measurement.internal.zzef r2 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgs()
            java.lang.String r3 = "Evaluating filter. audience, filter, event"
            r24 = r4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)
            boolean r33 = r1.zzkb()
            if (r33 == 0) goto L_0x07a1
            int r33 = r1.getId()
            java.lang.Integer r33 = java.lang.Integer.valueOf(r33)
            r43 = r5
            r5 = r33
            r33 = r6
            goto L_0x07a6
        L_0x07a1:
            r43 = r5
            r33 = r6
            r5 = 0
        L_0x07a6:
            com.google.android.gms.measurement.internal.zzed r6 = r64.zzy()
            r34 = r8
            java.lang.String r8 = r1.zzjz()
            java.lang.String r6 = r6.a((java.lang.String) r8)
            r2.zza(r3, r4, r5, r6)
            com.google.android.gms.measurement.internal.zzef r2 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgs()
            java.lang.String r3 = "Filter definition"
            com.google.android.gms.measurement.internal.zzjo r4 = r64.zzgw()
            java.lang.String r4 = r4.a((com.google.android.gms.internal.measurement.zzbk.zza) r1)
            r2.zza(r3, r4)
            goto L_0x07d5
        L_0x07cd:
            r24 = r4
            r43 = r5
            r33 = r6
            r34 = r8
        L_0x07d5:
            boolean r2 = r1.zzkb()
            if (r2 == 0) goto L_0x0931
            int r2 = r1.getId()
            r8 = 256(0x100, float:3.59E-43)
            if (r2 <= r8) goto L_0x07e5
            goto L_0x0931
        L_0x07e5:
            if (r25 == 0) goto L_0x08b3
            boolean r2 = r1.zzkf()
            boolean r40 = r1.zzkg()
            if (r27 == 0) goto L_0x07f9
            boolean r3 = r1.zzki()
            if (r3 == 0) goto L_0x07f9
            r3 = 1
            goto L_0x07fa
        L_0x07f9:
            r3 = 0
        L_0x07fa:
            if (r2 != 0) goto L_0x0804
            if (r40 != 0) goto L_0x0804
            if (r3 == 0) goto L_0x0801
            goto L_0x0804
        L_0x0801:
            r42 = 0
            goto L_0x0806
        L_0x0804:
            r42 = 1
        L_0x0806:
            int r2 = r1.getId()
            boolean r2 = r11.get(r2)
            if (r2 == 0) goto L_0x0841
            if (r42 != 0) goto L_0x0841
            com.google.android.gms.measurement.internal.zzef r2 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgs()
            java.lang.String r3 = "Event filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)
            boolean r5 = r1.zzkb()
            if (r5 == 0) goto L_0x082f
            int r1 = r1.getId()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            goto L_0x0830
        L_0x082f:
            r1 = 0
        L_0x0830:
            r2.zza(r3, r4, r1)
            r2 = r21
            r4 = r24
            r6 = r33
            r8 = r34
            r5 = r43
            r3 = r65
            goto L_0x0750
        L_0x0841:
            r5 = r19
            r19 = r1
            r1 = r64
            r6 = r21
            r2 = r19
            r4 = r65
            r8 = 2
            r3 = r0
            r8 = r4
            r21 = r12
            r12 = r24
            r4 = r39
            r24 = r10
            r7 = r43
            r10 = r6
            r43 = r9
            r9 = r5
            r5 = r22
            java.lang.Boolean r1 = r1.zza(r2, r3, r4, r5)
            com.google.android.gms.measurement.internal.zzef r2 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgs()
            java.lang.String r3 = "Event filter result"
            if (r1 != 0) goto L_0x0873
            java.lang.String r4 = "null"
            goto L_0x0874
        L_0x0873:
            r4 = r1
        L_0x0874:
            r2.zza(r3, r4)
            if (r1 != 0) goto L_0x087b
            goto L_0x0914
        L_0x087b:
            int r2 = r19.getId()
            r10.set(r2)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x096a
            int r1 = r19.getId()
            r11.set(r1)
            if (r42 == 0) goto L_0x096a
            boolean r1 = r45.zzml()
            if (r1 == 0) goto L_0x096a
            if (r40 == 0) goto L_0x08a6
            int r1 = r19.getId()
            long r2 = r45.getTimestampMillis()
            zzb(r13, r1, r2)
            goto L_0x096a
        L_0x08a6:
            int r1 = r19.getId()
            long r2 = r45.getTimestampMillis()
            zza((java.util.Map<java.lang.Integer, java.lang.Long>) r9, (int) r1, (long) r2)
            goto L_0x096a
        L_0x08b3:
            r7 = r43
            r8 = r65
            r43 = r9
            r9 = r19
            r19 = r1
            r63 = r24
            r24 = r10
            r10 = r21
            r21 = r12
            r12 = r63
            int r1 = r19.getId()
            boolean r1 = r11.get(r1)
            if (r1 == 0) goto L_0x08f2
            com.google.android.gms.measurement.internal.zzef r1 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgs()
            java.lang.String r2 = "Event filter already evaluated true. audience ID, filter ID"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r14)
            boolean r4 = r19.zzkb()
            if (r4 == 0) goto L_0x08ef
            int r4 = r19.getId()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            goto L_0x0967
        L_0x08ef:
            r4 = 0
            goto L_0x0967
        L_0x08f2:
            r1 = r64
            r2 = r19
            r3 = r0
            r4 = r39
            r5 = r22
            java.lang.Boolean r1 = r1.zza(r2, r3, r4, r5)
            com.google.android.gms.measurement.internal.zzef r2 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgs()
            java.lang.String r3 = "Event filter result"
            if (r1 != 0) goto L_0x090e
            java.lang.String r4 = "null"
            goto L_0x090f
        L_0x090e:
            r4 = r1
        L_0x090f:
            r2.zza(r3, r4)
            if (r1 != 0) goto L_0x091c
        L_0x0914:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            r15.add(r1)
            goto L_0x096a
        L_0x091c:
            int r2 = r19.getId()
            r10.set(r2)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x096a
            int r1 = r19.getId()
            r11.set(r1)
            goto L_0x096a
        L_0x0931:
            r7 = r43
            r8 = r65
            r43 = r9
            r9 = r19
            r19 = r1
            r63 = r24
            r24 = r10
            r10 = r21
            r21 = r12
            r12 = r63
            com.google.android.gms.measurement.internal.zzef r1 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgn()
            java.lang.String r2 = "Invalid event filter ID. appId, id"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r65)
            boolean r4 = r19.zzkb()
            if (r4 == 0) goto L_0x0962
            int r4 = r19.getId()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            goto L_0x0963
        L_0x0962:
            r4 = 0
        L_0x0963:
            java.lang.String r4 = java.lang.String.valueOf(r4)
        L_0x0967:
            r1.zza(r2, r3, r4)
        L_0x096a:
            r5 = r7
            r3 = r8
            r19 = r9
            r2 = r10
            r4 = r12
            r12 = r21
            r10 = r24
            r6 = r33
            r8 = r34
            r9 = r43
            r7 = r64
            goto L_0x0750
        L_0x097e:
            r43 = r5
            r33 = r6
            r40 = r9
            r42 = r10
            r44 = r15
            r9 = r16
            r13 = r18
            r11 = r41
            r7 = r64
            goto L_0x0655
        L_0x0992:
            r7 = r43
            r9 = r3
            r17 = r4
            r23 = r7
            r12 = r11
            r10 = r33
            r1 = r35
            r8 = r37
            r0 = r38
            r21 = r40
            r13 = r42
            r15 = r44
            r7 = r64
            r14 = 256(0x100, float:3.59E-43)
            goto L_0x0370
        L_0x09ae:
            r8 = r9
            r33 = r10
            r24 = r13
            r12 = r17
            r43 = r21
            r7 = r23
            boolean r0 = r67.isEmpty()
            if (r0 != 0) goto L_0x0d0c
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            java.util.Iterator r1 = r67.iterator()
        L_0x09c8:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0d0c
            java.lang.Object r2 = r1.next()
            com.google.android.gms.internal.measurement.zzbs$zzk r2 = (com.google.android.gms.internal.measurement.zzbs.zzk) r2
            java.lang.String r3 = r2.getName()
            java.lang.Object r3 = r0.get(r3)
            java.util.Map r3 = (java.util.Map) r3
            if (r3 != 0) goto L_0x09fa
            com.google.android.gms.measurement.internal.zzx r3 = r64.zzgy()
            java.lang.String r4 = r2.getName()
            java.util.Map r3 = r3.b(r8, r4)
            if (r3 != 0) goto L_0x09f3
            androidx.collection.ArrayMap r3 = new androidx.collection.ArrayMap
            r3.<init>()
        L_0x09f3:
            java.lang.String r4 = r2.getName()
            r0.put(r4, r3)
        L_0x09fa:
            java.util.Set r4 = r3.keySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0a02:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0d04
            java.lang.Object r5 = r4.next()
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            boolean r6 = r15.contains(r6)
            if (r6 == 0) goto L_0x0a2e
            com.google.android.gms.measurement.internal.zzef r6 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzgs()
            java.lang.String r9 = "Skipping failed audience ID"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r6.zza(r9, r5)
            goto L_0x0a02
        L_0x0a2e:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            java.lang.Object r6 = r7.get(r6)
            java.util.BitSet r6 = (java.util.BitSet) r6
            java.lang.Integer r9 = java.lang.Integer.valueOf(r5)
            java.lang.Object r9 = r12.get(r9)
            java.util.BitSet r9 = (java.util.BitSet) r9
            if (r25 == 0) goto L_0x0a5f
            java.lang.Integer r10 = java.lang.Integer.valueOf(r5)
            r11 = r43
            java.lang.Object r10 = r11.get(r10)
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Integer r13 = java.lang.Integer.valueOf(r5)
            r14 = r33
            java.lang.Object r13 = r14.get(r13)
            java.util.Map r13 = (java.util.Map) r13
            r66 = r0
            goto L_0x0a67
        L_0x0a5f:
            r14 = r33
            r11 = r43
            r66 = r0
            r10 = 0
            r13 = 0
        L_0x0a67:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            r67 = r1
            r1 = r24
            java.lang.Object r0 = r1.get(r0)
            com.google.android.gms.internal.measurement.zzbs$zza r0 = (com.google.android.gms.internal.measurement.zzbs.zza) r0
            if (r0 != 0) goto L_0x0ac1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            com.google.android.gms.internal.measurement.zzbs$zza$zza r6 = com.google.android.gms.internal.measurement.zzbs.zza.zzmc()
            r9 = 1
            com.google.android.gms.internal.measurement.zzbs$zza$zza r6 = r6.zzk(r9)
            com.google.android.gms.internal.measurement.zzgi r6 = r6.zzug()
            com.google.android.gms.internal.measurement.zzey r6 = (com.google.android.gms.internal.measurement.zzey) r6
            com.google.android.gms.internal.measurement.zzbs$zza r6 = (com.google.android.gms.internal.measurement.zzbs.zza) r6
            r1.put(r0, r6)
            java.util.BitSet r6 = new java.util.BitSet
            r6.<init>()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            r7.put(r0, r6)
            java.util.BitSet r9 = new java.util.BitSet
            r9.<init>()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            r12.put(r0, r9)
            if (r25 == 0) goto L_0x0ac1
            androidx.collection.ArrayMap r10 = new androidx.collection.ArrayMap
            r10.<init>()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            r11.put(r0, r10)
            androidx.collection.ArrayMap r13 = new androidx.collection.ArrayMap
            r13.<init>()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            r14.put(r0, r13)
        L_0x0ac1:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            java.lang.Object r0 = r3.get(r0)
            java.util.List r0 = (java.util.List) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x0acf:
            boolean r16 = r0.hasNext()
            if (r16 == 0) goto L_0x0cf2
            java.lang.Object r16 = r0.next()
            r17 = r0
            r0 = r16
            com.google.android.gms.internal.measurement.zzbk$zzd r0 = (com.google.android.gms.internal.measurement.zzbk.zzd) r0
            r16 = r3
            com.google.android.gms.measurement.internal.zzef r3 = r64.zzab()
            r18 = r4
            r4 = 2
            boolean r3 = r3.a((int) r4)
            if (r3 == 0) goto L_0x0b3b
            com.google.android.gms.measurement.internal.zzef r3 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgs()
            java.lang.String r4 = "Evaluating filter. audience, filter, property"
            r43 = r7
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)
            boolean r19 = r0.zzkb()
            if (r19 == 0) goto L_0x0b11
            int r19 = r0.getId()
            java.lang.Integer r19 = java.lang.Integer.valueOf(r19)
            r33 = r14
            r8 = r19
            goto L_0x0b14
        L_0x0b11:
            r33 = r14
            r8 = 0
        L_0x0b14:
            com.google.android.gms.measurement.internal.zzed r14 = r64.zzy()
            r40 = r11
            java.lang.String r11 = r0.getPropertyName()
            java.lang.String r11 = r14.c(r11)
            r3.zza(r4, r7, r8, r11)
            com.google.android.gms.measurement.internal.zzef r3 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgs()
            java.lang.String r4 = "Filter definition"
            com.google.android.gms.measurement.internal.zzjo r7 = r64.zzgw()
            java.lang.String r7 = r7.a((com.google.android.gms.internal.measurement.zzbk.zzd) r0)
            r3.zza(r4, r7)
            goto L_0x0b41
        L_0x0b3b:
            r43 = r7
            r40 = r11
            r33 = r14
        L_0x0b41:
            boolean r3 = r0.zzkb()
            if (r3 == 0) goto L_0x0cab
            int r3 = r0.getId()
            r4 = 256(0x100, float:3.59E-43)
            if (r3 <= r4) goto L_0x0b51
            goto L_0x0cab
        L_0x0b51:
            if (r25 == 0) goto L_0x0c2f
            boolean r3 = r0.zzkf()
            boolean r7 = r0.zzkg()
            if (r27 == 0) goto L_0x0b65
            boolean r8 = r0.zzki()
            if (r8 == 0) goto L_0x0b65
            r8 = 1
            goto L_0x0b66
        L_0x0b65:
            r8 = 0
        L_0x0b66:
            if (r3 != 0) goto L_0x0b6f
            if (r7 != 0) goto L_0x0b6f
            if (r8 == 0) goto L_0x0b6d
            goto L_0x0b6f
        L_0x0b6d:
            r3 = 0
            goto L_0x0b70
        L_0x0b6f:
            r3 = 1
        L_0x0b70:
            int r11 = r0.getId()
            boolean r11 = r6.get(r11)
            if (r11 == 0) goto L_0x0bab
            if (r3 != 0) goto L_0x0bab
            com.google.android.gms.measurement.internal.zzef r3 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgs()
            java.lang.String r7 = "Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r5)
            boolean r11 = r0.zzkb()
            if (r11 == 0) goto L_0x0b99
            int r0 = r0.getId()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L_0x0b9a
        L_0x0b99:
            r0 = 0
        L_0x0b9a:
            r3.zza(r7, r8, r0)
            r3 = r16
            r0 = r17
            r4 = r18
            r14 = r33
            r11 = r40
            r7 = r43
            goto L_0x0c71
        L_0x0bab:
            r14 = r43
            r11 = r64
            java.lang.Boolean r19 = r11.zza((com.google.android.gms.internal.measurement.zzbk.zzd) r0, (com.google.android.gms.internal.measurement.zzbs.zzk) r2)
            com.google.android.gms.measurement.internal.zzef r20 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r4 = r20.zzgs()
            r24 = r12
            java.lang.String r12 = "Property filter result"
            if (r19 != 0) goto L_0x0bc8
            java.lang.String r20 = "null"
            r42 = r1
            r1 = r20
            goto L_0x0bcc
        L_0x0bc8:
            r42 = r1
            r1 = r19
        L_0x0bcc:
            r4.zza(r12, r1)
            if (r19 != 0) goto L_0x0bd3
            goto L_0x0c8e
        L_0x0bd3:
            int r1 = r0.getId()
            r9.set(r1)
            if (r27 == 0) goto L_0x0be4
            if (r8 == 0) goto L_0x0be4
            boolean r1 = r19.booleanValue()
            if (r1 == 0) goto L_0x0c62
        L_0x0be4:
            if (r26 == 0) goto L_0x0bf6
            int r1 = r0.getId()
            boolean r1 = r6.get(r1)
            if (r1 == 0) goto L_0x0bf6
            boolean r1 = r0.zzkf()
            if (r1 == 0) goto L_0x0c01
        L_0x0bf6:
            int r1 = r0.getId()
            boolean r4 = r19.booleanValue()
            r6.set(r1, r4)
        L_0x0c01:
            boolean r1 = r19.booleanValue()
            if (r1 == 0) goto L_0x0c62
            if (r3 == 0) goto L_0x0c62
            boolean r1 = r2.zzqs()
            if (r1 == 0) goto L_0x0c62
            long r3 = r2.zzqt()
            if (r27 == 0) goto L_0x0c1d
            if (r8 == 0) goto L_0x0c1d
            if (r29 == 0) goto L_0x0c1d
            long r3 = r29.longValue()
        L_0x0c1d:
            if (r7 == 0) goto L_0x0c27
            int r0 = r0.getId()
            zzb(r13, r0, r3)
            goto L_0x0c62
        L_0x0c27:
            int r0 = r0.getId()
            zza((java.util.Map<java.lang.Integer, java.lang.Long>) r10, (int) r0, (long) r3)
            goto L_0x0c62
        L_0x0c2f:
            r42 = r1
            r24 = r12
            r14 = r43
            r11 = r64
            int r1 = r0.getId()
            boolean r1 = r6.get(r1)
            if (r1 == 0) goto L_0x0c75
            com.google.android.gms.measurement.internal.zzef r1 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgs()
            java.lang.String r3 = "Property filter already evaluated true. audience ID, filter ID"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
            boolean r7 = r0.zzkb()
            if (r7 == 0) goto L_0x0c5e
            int r0 = r0.getId()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r0)
            goto L_0x0c5f
        L_0x0c5e:
            r8 = 0
        L_0x0c5f:
            r1.zza(r3, r4, r8)
        L_0x0c62:
            r7 = r14
            r3 = r16
            r0 = r17
            r4 = r18
            r12 = r24
            r14 = r33
            r11 = r40
            r1 = r42
        L_0x0c71:
            r8 = r65
            goto L_0x0acf
        L_0x0c75:
            java.lang.Boolean r1 = r11.zza((com.google.android.gms.internal.measurement.zzbk.zzd) r0, (com.google.android.gms.internal.measurement.zzbs.zzk) r2)
            com.google.android.gms.measurement.internal.zzef r3 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgs()
            java.lang.String r4 = "Property filter result"
            if (r1 != 0) goto L_0x0c88
            java.lang.String r7 = "null"
            goto L_0x0c89
        L_0x0c88:
            r7 = r1
        L_0x0c89:
            r3.zza(r4, r7)
            if (r1 != 0) goto L_0x0c96
        L_0x0c8e:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            r15.add(r0)
            goto L_0x0c62
        L_0x0c96:
            int r3 = r0.getId()
            r9.set(r3)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0c62
            int r0 = r0.getId()
            r6.set(r0)
            goto L_0x0c62
        L_0x0cab:
            r42 = r1
            r24 = r12
            r14 = r43
            r11 = r64
            com.google.android.gms.measurement.internal.zzef r1 = r64.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgn()
            java.lang.String r3 = "Invalid property filter ID. appId, id"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r65)
            boolean r6 = r0.zzkb()
            if (r6 == 0) goto L_0x0cd0
            int r0 = r0.getId()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r0)
            goto L_0x0cd1
        L_0x0cd0:
            r8 = 0
        L_0x0cd1:
            java.lang.String r0 = java.lang.String.valueOf(r8)
            r1.zza(r3, r4, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            r15.add(r0)
            r0 = r66
            r1 = r67
            r7 = r14
            r3 = r16
            r4 = r18
            r12 = r24
            r43 = r40
            r24 = r42
            r8 = r65
            goto L_0x0a02
        L_0x0cf2:
            r40 = r11
            r11 = r64
            r0 = r66
            r24 = r1
            r33 = r14
            r43 = r40
            r8 = r65
            r1 = r67
            goto L_0x0a02
        L_0x0d04:
            r42 = r24
            r11 = r64
            r8 = r65
            goto L_0x09c8
        L_0x0d0c:
            r14 = r7
            r42 = r24
            r40 = r43
            r11 = r64
            r24 = r12
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Set r0 = r14.keySet()
            java.util.Iterator r2 = r0.iterator()
        L_0x0d22:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0fd1
            java.lang.Object r0 = r2.next()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            boolean r3 = r15.contains(r3)
            if (r3 != 0) goto L_0x0fcd
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            r4 = r42
            java.lang.Object r3 = r4.get(r3)
            com.google.android.gms.internal.measurement.zzbs$zza r3 = (com.google.android.gms.internal.measurement.zzbs.zza) r3
            if (r3 != 0) goto L_0x0d4f
            com.google.android.gms.internal.measurement.zzbs$zza$zza r3 = com.google.android.gms.internal.measurement.zzbs.zza.zzmc()
            goto L_0x0d57
        L_0x0d4f:
            com.google.android.gms.internal.measurement.zzey$zza r3 = r3.zzuj()
            com.google.android.gms.internal.measurement.zzey$zza r3 = (com.google.android.gms.internal.measurement.zzey.zza) r3
            com.google.android.gms.internal.measurement.zzbs$zza$zza r3 = (com.google.android.gms.internal.measurement.zzbs.zza.C0017zza) r3
        L_0x0d57:
            r3.zzi(r0)
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r5 = com.google.android.gms.internal.measurement.zzbs.zzi.zzqh()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            java.lang.Object r6 = r14.get(r6)
            java.util.BitSet r6 = (java.util.BitSet) r6
            java.util.List r6 = com.google.android.gms.measurement.internal.zzjo.a((java.util.BitSet) r6)
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r5 = r5.zzo(r6)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            r7 = r24
            java.lang.Object r6 = r7.get(r6)
            java.util.BitSet r6 = (java.util.BitSet) r6
            java.util.List r6 = com.google.android.gms.measurement.internal.zzjo.a((java.util.BitSet) r6)
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r5 = r5.zzn(r6)
            if (r25 == 0) goto L_0x0f37
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            r8 = r40
            java.lang.Object r6 = r8.get(r6)
            java.util.Map r6 = (java.util.Map) r6
            java.util.List r6 = zza(r6)
            r5.zzp(r6)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            r9 = r33
            java.lang.Object r6 = r9.get(r6)
            java.util.Map r6 = (java.util.Map) r6
            if (r6 != 0) goto L_0x0db1
            java.util.List r6 = java.util.Collections.emptyList()
            r66 = r2
            r17 = r7
            goto L_0x0e22
        L_0x0db1:
            java.util.ArrayList r10 = new java.util.ArrayList
            int r12 = r6.size()
            r10.<init>(r12)
            java.util.Set r12 = r6.keySet()
            java.util.Iterator r12 = r12.iterator()
        L_0x0dc2:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x0e1d
            java.lang.Object r13 = r12.next()
            java.lang.Integer r13 = (java.lang.Integer) r13
            r66 = r2
            com.google.android.gms.internal.measurement.zzbs$zzj$zza r2 = com.google.android.gms.internal.measurement.zzbs.zzj.zzqo()
            r17 = r7
            int r7 = r13.intValue()
            com.google.android.gms.internal.measurement.zzbs$zzj$zza r2 = r2.zzal(r7)
            java.lang.Object r7 = r6.get(r13)
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L_0x0e09
            java.util.Collections.sort(r7)
            java.util.Iterator r7 = r7.iterator()
        L_0x0ded:
            boolean r13 = r7.hasNext()
            if (r13 == 0) goto L_0x0e09
            java.lang.Object r13 = r7.next()
            java.lang.Long r13 = (java.lang.Long) r13
            r67 = r6
            r16 = r7
            long r6 = r13.longValue()
            r2.zzbj(r6)
            r6 = r67
            r7 = r16
            goto L_0x0ded
        L_0x0e09:
            r67 = r6
            com.google.android.gms.internal.measurement.zzgi r2 = r2.zzug()
            com.google.android.gms.internal.measurement.zzey r2 = (com.google.android.gms.internal.measurement.zzey) r2
            com.google.android.gms.internal.measurement.zzbs$zzj r2 = (com.google.android.gms.internal.measurement.zzbs.zzj) r2
            r10.add(r2)
            r2 = r66
            r6 = r67
            r7 = r17
            goto L_0x0dc2
        L_0x0e1d:
            r66 = r2
            r17 = r7
            r6 = r10
        L_0x0e22:
            if (r26 == 0) goto L_0x0e38
            boolean r2 = r3.zzlw()
            if (r2 == 0) goto L_0x0e38
            com.google.android.gms.internal.measurement.zzbs$zzi r2 = r3.zzlx()
            java.util.List r2 = r2.zzqe()
            boolean r7 = r2.isEmpty()
            if (r7 == 0) goto L_0x0e40
        L_0x0e38:
            r40 = r8
            r33 = r9
            r16 = 1
            goto L_0x0f33
        L_0x0e40:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>(r6)
            androidx.collection.ArrayMap r6 = new androidx.collection.ArrayMap
            r6.<init>()
            java.util.Iterator r2 = r2.iterator()
        L_0x0e4e:
            boolean r10 = r2.hasNext()
            if (r10 == 0) goto L_0x0e85
            java.lang.Object r10 = r2.next()
            com.google.android.gms.internal.measurement.zzbs$zzj r10 = (com.google.android.gms.internal.measurement.zzbs.zzj) r10
            boolean r12 = r10.zzme()
            if (r12 == 0) goto L_0x0e82
            int r12 = r10.zzql()
            if (r12 <= 0) goto L_0x0e82
            int r12 = r10.getIndex()
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            int r13 = r10.zzql()
            r16 = 1
            int r13 = r13 + -1
            long r18 = r10.zzai(r13)
            java.lang.Long r10 = java.lang.Long.valueOf(r18)
            r6.put(r12, r10)
            goto L_0x0e4e
        L_0x0e82:
            r16 = 1
            goto L_0x0e4e
        L_0x0e85:
            r16 = 1
            r2 = 0
        L_0x0e88:
            int r10 = r7.size()
            if (r2 >= r10) goto L_0x0eee
            java.lang.Object r10 = r7.get(r2)
            com.google.android.gms.internal.measurement.zzbs$zzj r10 = (com.google.android.gms.internal.measurement.zzbs.zzj) r10
            boolean r12 = r10.zzme()
            if (r12 == 0) goto L_0x0ea3
            int r12 = r10.getIndex()
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            goto L_0x0ea4
        L_0x0ea3:
            r12 = 0
        L_0x0ea4:
            java.lang.Object r12 = r6.remove(r12)
            java.lang.Long r12 = (java.lang.Long) r12
            if (r12 == 0) goto L_0x0ee6
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            long r18 = r12.longValue()
            r40 = r8
            r8 = 0
            long r20 = r10.zzai(r8)
            int r22 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r22 >= 0) goto L_0x0ec3
            r13.add(r12)
        L_0x0ec3:
            java.util.List r12 = r10.zzqk()
            r13.addAll(r12)
            com.google.android.gms.internal.measurement.zzey$zza r10 = r10.zzuj()
            com.google.android.gms.internal.measurement.zzey$zza r10 = (com.google.android.gms.internal.measurement.zzey.zza) r10
            com.google.android.gms.internal.measurement.zzbs$zzj$zza r10 = (com.google.android.gms.internal.measurement.zzbs.zzj.zza) r10
            com.google.android.gms.internal.measurement.zzbs$zzj$zza r10 = r10.zzqw()
            com.google.android.gms.internal.measurement.zzbs$zzj$zza r10 = r10.zzr(r13)
            com.google.android.gms.internal.measurement.zzgi r10 = r10.zzug()
            com.google.android.gms.internal.measurement.zzey r10 = (com.google.android.gms.internal.measurement.zzey) r10
            com.google.android.gms.internal.measurement.zzbs$zzj r10 = (com.google.android.gms.internal.measurement.zzbs.zzj) r10
            r7.set(r2, r10)
            goto L_0x0ee9
        L_0x0ee6:
            r40 = r8
            r8 = 0
        L_0x0ee9:
            int r2 = r2 + 1
            r8 = r40
            goto L_0x0e88
        L_0x0eee:
            r40 = r8
            r8 = 0
            java.util.Set r2 = r6.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0ef9:
            boolean r10 = r2.hasNext()
            if (r10 == 0) goto L_0x0f30
            java.lang.Object r10 = r2.next()
            java.lang.Integer r10 = (java.lang.Integer) r10
            com.google.android.gms.internal.measurement.zzbs$zzj$zza r12 = com.google.android.gms.internal.measurement.zzbs.zzj.zzqo()
            int r13 = r10.intValue()
            com.google.android.gms.internal.measurement.zzbs$zzj$zza r12 = r12.zzal(r13)
            java.lang.Object r10 = r6.get(r10)
            java.lang.Long r10 = (java.lang.Long) r10
            r33 = r9
            long r8 = r10.longValue()
            com.google.android.gms.internal.measurement.zzbs$zzj$zza r8 = r12.zzbj(r8)
            com.google.android.gms.internal.measurement.zzgi r8 = r8.zzug()
            com.google.android.gms.internal.measurement.zzey r8 = (com.google.android.gms.internal.measurement.zzey) r8
            com.google.android.gms.internal.measurement.zzbs$zzj r8 = (com.google.android.gms.internal.measurement.zzbs.zzj) r8
            r7.add(r8)
            r9 = r33
            r8 = 0
            goto L_0x0ef9
        L_0x0f30:
            r33 = r9
            r6 = r7
        L_0x0f33:
            r5.zzq(r6)
            goto L_0x0f3d
        L_0x0f37:
            r66 = r2
            r17 = r7
            r16 = 1
        L_0x0f3d:
            r3.zza((com.google.android.gms.internal.measurement.zzbs.zzi.zza) r5)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            com.google.android.gms.internal.measurement.zzgi r5 = r3.zzug()
            com.google.android.gms.internal.measurement.zzey r5 = (com.google.android.gms.internal.measurement.zzey) r5
            com.google.android.gms.internal.measurement.zzbs$zza r5 = (com.google.android.gms.internal.measurement.zzbs.zza) r5
            r4.put(r2, r5)
            com.google.android.gms.internal.measurement.zzgi r2 = r3.zzug()
            com.google.android.gms.internal.measurement.zzey r2 = (com.google.android.gms.internal.measurement.zzey) r2
            com.google.android.gms.internal.measurement.zzbs$zza r2 = (com.google.android.gms.internal.measurement.zzbs.zza) r2
            r1.add(r2)
            com.google.android.gms.measurement.internal.zzx r2 = r64.zzgy()
            com.google.android.gms.internal.measurement.zzbs$zzi r3 = r3.zzlv()
            r2.c()
            r2.zzo()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r65)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)
            byte[] r3 = r3.toByteArray()
            android.content.ContentValues r5 = new android.content.ContentValues
            r5.<init>()
            java.lang.String r6 = "app_id"
            r7 = r65
            r5.put(r6, r7)
            java.lang.String r6 = "audience_id"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5.put(r6, r0)
            java.lang.String r0 = "current_results"
            r5.put(r0, r3)
            android.database.sqlite.SQLiteDatabase r0 = r2.d()     // Catch:{ SQLiteException -> 0x0fb2 }
            java.lang.String r3 = "audience_filter_values"
            r6 = 5
            r8 = 0
            long r5 = r0.insertWithOnConflict(r3, r8, r5, r6)     // Catch:{ SQLiteException -> 0x0fb0 }
            r9 = -1
            int r0 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r0 != 0) goto L_0x0fc5
            com.google.android.gms.measurement.internal.zzef r0 = r2.zzab()     // Catch:{ SQLiteException -> 0x0fb0 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgk()     // Catch:{ SQLiteException -> 0x0fb0 }
            java.lang.String r3 = "Failed to insert filter results (got -1). appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r65)     // Catch:{ SQLiteException -> 0x0fb0 }
            r0.zza(r3, r5)     // Catch:{ SQLiteException -> 0x0fb0 }
            goto L_0x0fc5
        L_0x0fb0:
            r0 = move-exception
            goto L_0x0fb4
        L_0x0fb2:
            r0 = move-exception
            r8 = 0
        L_0x0fb4:
            com.google.android.gms.measurement.internal.zzef r2 = r2.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgk()
            java.lang.String r3 = "Error storing filter results. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r65)
            r2.zza(r3, r5, r0)
        L_0x0fc5:
            r2 = r66
            r42 = r4
            r24 = r17
            goto L_0x0d22
        L_0x0fcd:
            r7 = r65
            goto L_0x0d22
        L_0x0fd1:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzp.a(java.lang.String, java.util.List, java.util.List):java.util.List");
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return false;
    }
}
