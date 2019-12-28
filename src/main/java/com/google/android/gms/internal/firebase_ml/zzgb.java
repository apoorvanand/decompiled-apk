package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class zzgb implements zzia {
    public static final String MEDIA_TYPE = new zzfn("application/x-www-form-urlencoded").zza(zzhi.UTF_8).zzeg();

    private static Object zza(Type type, List<Type> list, String str) {
        return zzhl.zza(zzhl.zza(list, type), str);
    }

    public static void zze(String str, Object obj) {
        String str2 = str;
        Object obj2 = obj;
        if (str2 != null) {
            try {
                StringReader stringReader = new StringReader(str2);
                Class<?> cls = obj.getClass();
                zzhj zzd = zzhj.zzd(cls);
                List asList = Arrays.asList(new Type[]{cls});
                zzhs zzhs = zzhs.class.isAssignableFrom(cls) ? (zzhs) obj2 : null;
                Map map = Map.class.isAssignableFrom(cls) ? (Map) obj2 : null;
                zzhf zzhf = new zzhf(obj2);
                StringWriter stringWriter = new StringWriter();
                StringWriter stringWriter2 = new StringWriter();
                StringWriter stringWriter3 = stringWriter;
                boolean z = true;
                while (true) {
                    int read = stringReader.read();
                    if (read == -1 || read == 38) {
                        String zzao = zzik.zzao(stringWriter3.toString());
                        if (zzao.length() != 0) {
                            String zzao2 = zzik.zzao(stringWriter2.toString());
                            zzhr zzal = zzd.zzal(zzao);
                            if (zzal != null) {
                                Type zza = zzhl.zza((List<Type>) asList, zzal.getGenericType());
                                if (zzig.zzc(zza)) {
                                    Class<?> zzb = zzig.zzb(asList, zzig.zzd(zza));
                                    zzhf.zza(zzal.zzgp(), zzb, zza((Type) zzb, (List<Type>) asList, zzao2));
                                } else if (zzig.zza(zzig.zzb(asList, zza), (Class<?>) Iterable.class)) {
                                    Collection<Object> collection = (Collection) zzal.zzh(obj2);
                                    if (collection == null) {
                                        collection = zzhl.zzb(zza);
                                        zzal.zzb(obj2, collection);
                                    }
                                    collection.add(zza(zza == Object.class ? null : zzig.zze(zza), (List<Type>) asList, zzao2));
                                } else {
                                    zzal.zzb(obj2, zza(zza, (List<Type>) asList, zzao2));
                                }
                            } else if (map != null) {
                                ArrayList arrayList = (ArrayList) map.get(zzao);
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                    if (zzhs != null) {
                                        zzhs.zzb(zzao, arrayList);
                                    } else {
                                        map.put(zzao, arrayList);
                                    }
                                }
                                arrayList.add(zzao2);
                            }
                        }
                        StringWriter stringWriter4 = new StringWriter();
                        StringWriter stringWriter5 = new StringWriter();
                        if (read == -1) {
                            zzhf.zzgk();
                            return;
                        }
                        stringWriter3 = stringWriter4;
                        stringWriter2 = stringWriter5;
                        z = true;
                    } else if (read != 61) {
                        if (z) {
                            stringWriter3.write(read);
                        } else {
                            stringWriter2.write(read);
                        }
                    } else if (z) {
                        z = false;
                    } else {
                        stringWriter2.write(read);
                    }
                }
            } catch (IOException e) {
                throw zzlh.zza(e);
            }
        }
    }

    public final <T> T zza(InputStream inputStream, Charset charset, Class<T> cls) {
        throw new NoSuchMethodError();
    }
}
