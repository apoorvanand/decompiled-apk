package com.google.android.gms.internal.vision;

final class zzik {
    static String a(zzeo zzeo) {
        String str;
        zzil zzil = new zzil(zzeo);
        StringBuilder sb = new StringBuilder(zzil.size());
        for (int i = 0; i < zzil.size(); i++) {
            int zzai = zzil.zzai(i);
            if (zzai == 34) {
                str = "\\\"";
            } else if (zzai == 39) {
                str = "\\'";
            } else if (zzai != 92) {
                switch (zzai) {
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
                        if (zzai < 32 || zzai > 126) {
                            sb.append('\\');
                            sb.append((char) (((zzai >>> 6) & 3) + 48));
                            sb.append((char) (((zzai >>> 3) & 7) + 48));
                            zzai = (zzai & 7) + 48;
                        }
                        sb.append((char) zzai);
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
