package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
enum PublicSuffixType {
    PRIVATE(':', ','),
    ICANN('!', '?');
    
    private final char innerNodeCode;
    private final char leafNodeCode;

    private PublicSuffixType(char c, char c2) {
        this.innerNodeCode = c;
        this.leafNodeCode = c2;
    }

    static PublicSuffixType a(char c) {
        for (PublicSuffixType publicSuffixType : values()) {
            if (publicSuffixType.b() == c || publicSuffixType.a() == c) {
                return publicSuffixType;
            }
        }
        throw new IllegalArgumentException("No enum corresponding to given code: " + c);
    }

    /* access modifiers changed from: package-private */
    public char a() {
        return this.leafNodeCode;
    }

    /* access modifiers changed from: package-private */
    public char b() {
        return this.innerNodeCode;
    }
}
