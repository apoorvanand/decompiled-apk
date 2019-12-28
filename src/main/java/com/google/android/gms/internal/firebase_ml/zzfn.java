package com.google.android.gms.internal.firebase_ml;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class zzfn {
    private static final Pattern zzud = Pattern.compile("[\\w!#$&.+\\-\\^_]+|[*]");
    private static final Pattern zzue = Pattern.compile("[\\p{ASCII}&&[^\\p{Cntrl} ;/=\\[\\]\\(\\)\\<\\>\\@\\,\\:\\\"\\?\\=]]+");
    private static final Pattern zzuf;
    private static final Pattern zzug;
    private String type = "application";
    private String zzuh = "octet-stream";
    private final SortedMap<String, String> zzui = new TreeMap();
    private String zzuj;

    static {
        StringBuilder sb = new StringBuilder(String.valueOf("[^\\s/=;\"]+").length() + 14 + String.valueOf("[^\\s/=;\"]+").length() + String.valueOf(";.*").length());
        sb.append("\\s*(");
        sb.append("[^\\s/=;\"]+");
        sb.append(")/(");
        sb.append("[^\\s/=;\"]+");
        sb.append(")\\s*(");
        sb.append(";.*");
        sb.append(")?");
        zzuf = Pattern.compile(sb.toString(), 32);
        StringBuilder sb2 = new StringBuilder(String.valueOf("\"([^\"]*)\"").length() + 1 + String.valueOf("[^\\s;\"]*").length());
        sb2.append("\"([^\"]*)\"");
        sb2.append("|");
        sb2.append("[^\\s;\"]*");
        String sb3 = sb2.toString();
        StringBuilder sb4 = new StringBuilder(String.valueOf("[^\\s/=;\"]+").length() + 12 + String.valueOf(sb3).length());
        sb4.append("\\s*;\\s*(");
        sb4.append("[^\\s/=;\"]+");
        sb4.append(")=(");
        sb4.append(sb3);
        sb4.append(")");
        zzug = Pattern.compile(sb4.toString());
    }

    public zzfn(String str) {
        Matcher matcher = zzuf.matcher(str);
        zzky.checkArgument(matcher.matches(), "Type must be in the 'maintype/subtype; parameter=value' format");
        String group = matcher.group(1);
        zzky.checkArgument(zzud.matcher(group).matches(), "Type contains reserved characters");
        this.type = group;
        this.zzuj = null;
        String group2 = matcher.group(2);
        zzky.checkArgument(zzud.matcher(group2).matches(), "Subtype contains reserved characters");
        this.zzuh = group2;
        this.zzuj = null;
        String group3 = matcher.group(3);
        if (group3 != null) {
            Matcher matcher2 = zzug.matcher(group3);
            while (matcher2.find()) {
                String group4 = matcher2.group(1);
                String group5 = matcher2.group(3);
                if (group5 == null) {
                    group5 = matcher2.group(2);
                }
                zza(group4, group5);
            }
        }
    }

    static boolean a(String str) {
        return zzue.matcher(str).matches();
    }

    private final zzfn zza(String str, String str2) {
        if (str2 == null) {
            this.zzuj = null;
            this.zzui.remove(str.toLowerCase(Locale.US));
            return this;
        }
        zzky.checkArgument(zzue.matcher(str).matches(), "Name contains reserved characters");
        this.zzuj = null;
        this.zzui.put(str.toLowerCase(Locale.US), str2);
        return this;
    }

    private final boolean zza(zzfn zzfn) {
        return zzfn != null && this.type.equalsIgnoreCase(zzfn.type) && this.zzuh.equalsIgnoreCase(zzfn.zzuh);
    }

    public static boolean zzb(String str, String str2) {
        return str2 != null && new zzfn(str).zza(new zzfn(str2));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzfn)) {
            return false;
        }
        zzfn zzfn = (zzfn) obj;
        return zza(zzfn) && this.zzui.equals(zzfn.zzui);
    }

    public final int hashCode() {
        return zzeg().hashCode();
    }

    public final String toString() {
        return zzeg();
    }

    public final zzfn zza(Charset charset) {
        zza("charset", charset == null ? null : charset.name());
        return this;
    }

    public final String zzeg() {
        String str = this.zzuj;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.type);
        sb.append('/');
        sb.append(this.zzuh);
        SortedMap<String, String> sortedMap = this.zzui;
        if (sortedMap != null) {
            for (Map.Entry next : sortedMap.entrySet()) {
                String str2 = (String) next.getValue();
                sb.append("; ");
                sb.append((String) next.getKey());
                sb.append("=");
                if (!a(str2)) {
                    String replace = str2.replace("\\", "\\\\").replace("\"", "\\\"");
                    StringBuilder sb2 = new StringBuilder(String.valueOf(replace).length() + 2);
                    sb2.append("\"");
                    sb2.append(replace);
                    sb2.append("\"");
                    str2 = sb2.toString();
                }
                sb.append(str2);
            }
        }
        this.zzuj = sb.toString();
        return this.zzuj;
    }

    public final Charset zzei() {
        String str = (String) this.zzui.get("charset".toLowerCase(Locale.US));
        if (str == null) {
            return null;
        }
        return Charset.forName(str);
    }
}
