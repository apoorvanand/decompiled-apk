package com.google.android.gms.internal.firebase_ml;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public final class zzik {
    private static final zzil zzaal = new zzim("-_.*", true);
    private static final zzil zzaam = new zzim("-_.!~*'()@:$&,;=", false);
    private static final zzil zzaan = new zzim("-_.!~*'()@:$&,;=+/?", false);
    private static final zzil zzaao = new zzim("-_.!~*'():$&,;=", false);
    private static final zzil zzaap = new zzim("-_.!~*'()@:$,;/?:", false);

    public static String zzan(String str) {
        return zzaal.zzat(str);
    }

    public static String zzao(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String zzap(String str) {
        return zzaam.zzat(str);
    }

    public static String zzaq(String str) {
        return zzaan.zzat(str);
    }

    public static String zzar(String str) {
        return zzaao.zzat(str);
    }

    public static String zzas(String str) {
        return zzaap.zzat(str);
    }
}
