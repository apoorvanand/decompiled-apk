package com.google.android.gms.internal.firebase_auth;

final class zzkg {
    static String a(zzgf zzgf) {
        String str;
        zzkf zzkf = new zzkf(zzgf);
        StringBuilder sb = new StringBuilder(zzkf.size());
        for (int i = 0; i < zzkf.size(); i++) {
            int zzp = zzkf.zzp(i);
            if (zzp == 34) {
                str = "\\\"";
            } else if (zzp == 39) {
                str = "\\'";
            } else if (zzp != 92) {
                switch (zzp) {
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
                        if (zzp < 32 || zzp > 126) {
                            sb.append('\\');
                            sb.append((char) (((zzp >>> 6) & 3) + 48));
                            sb.append((char) (((zzp >>> 3) & 7) + 48));
                            zzp = (zzp & 7) + 48;
                        }
                        sb.append((char) zzp);
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
