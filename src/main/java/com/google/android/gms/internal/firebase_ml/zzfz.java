package com.google.android.gms.internal.firebase_ml;

import kotlin.text.Typography;

enum zzfz {
    PLUS('+', "", ",", false, true),
    HASH('#', "#", ",", false, true),
    DOT('.', ".", ".", false, false),
    FORWARD_SLASH('/', "/", "/", false, false),
    SEMI_COLON(';', ";", ";", true, false),
    QUERY('?', "?", "&", true, false),
    AMP(Character.valueOf(Typography.amp), "&", "&", true, false),
    SIMPLE((String) null, "", ",", false, false);
    
    private final Character zzvv;
    private final String zzvw;
    private final String zzvx;
    private final boolean zzvy;
    private final boolean zzvz;

    private zzfz(Character ch, String str, String str2, boolean z, boolean z2) {
        this.zzvv = ch;
        this.zzvw = (String) zzky.checkNotNull(str);
        this.zzvx = (String) zzky.checkNotNull(str2);
        this.zzvy = z;
        this.zzvz = z2;
        if (ch != null) {
            zzfy.a.put(ch, this);
        }
    }

    /* access modifiers changed from: package-private */
    public final String a() {
        return this.zzvw;
    }

    /* access modifiers changed from: package-private */
    public final String a(String str) {
        return this.zzvz ? zzik.zzap(str) : zzik.zzan(str);
    }

    /* access modifiers changed from: package-private */
    public final String b() {
        return this.zzvx;
    }

    /* access modifiers changed from: package-private */
    public final boolean c() {
        return this.zzvy;
    }

    /* access modifiers changed from: package-private */
    public final int d() {
        return this.zzvv == null ? 0 : 1;
    }

    /* access modifiers changed from: package-private */
    public final boolean e() {
        return this.zzvz;
    }
}
