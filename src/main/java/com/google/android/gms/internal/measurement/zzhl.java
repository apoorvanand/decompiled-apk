package com.google.android.gms.internal.measurement;

final class zzhl {
    static String a(zzdp zzdp) {
        String str;
        zzho zzho = new zzho(zzdp);
        StringBuilder sb = new StringBuilder(zzho.size());
        for (int i = 0; i < zzho.size(); i++) {
            int zzaq = zzho.zzaq(i);
            if (zzaq == 34) {
                str = "\\\"";
            } else if (zzaq == 39) {
                str = "\\'";
            } else if (zzaq != 92) {
                switch (zzaq) {
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
                        if (zzaq < 32 || zzaq > 126) {
                            sb.append('\\');
                            sb.append((char) (((zzaq >>> 6) & 3) + 48));
                            sb.append((char) (((zzaq >>> 3) & 7) + 48));
                            zzaq = (zzaq & 7) + 48;
                        }
                        sb.append((char) zzaq);
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
