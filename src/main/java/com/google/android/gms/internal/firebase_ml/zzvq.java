package com.google.android.gms.internal.firebase_ml;

final class zzvq {
    static String a(zzru zzru) {
        String str;
        zzvr zzvr = new zzvr(zzru);
        StringBuilder sb = new StringBuilder(zzvr.size());
        for (int i = 0; i < zzvr.size(); i++) {
            int zzcc = zzvr.zzcc(i);
            if (zzcc == 34) {
                str = "\\\"";
            } else if (zzcc == 39) {
                str = "\\'";
            } else if (zzcc != 92) {
                switch (zzcc) {
                    case 7:
                        str = "\\a";
                        break;
                    case 8:
                        str = "\\b";
                        break;
                    case 9:
                        str = "\\t";
                        break;
                    case 10:
                        str = "\\n";
                        break;
                    case 11:
                        str = "\\v";
                        break;
                    case 12:
                        str = "\\f";
                        break;
                    case 13:
                        str = "\\r";
                        break;
                    default:
                        if (zzcc < 32 || zzcc > 126) {
                            sb.append('\\');
                            sb.append((char) (((zzcc >>> 6) & 3) + 48));
                            sb.append((char) (((zzcc >>> 3) & 7) + 48));
                            zzcc = (zzcc & 7) + 48;
                        }
                        sb.append((char) zzcc);
                        continue;
                }
            } else {
                str = "\\\\";
            }
            sb.append(str);
        }
        return sb.toString();
    }
}
