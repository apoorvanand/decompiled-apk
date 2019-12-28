package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class CommonPattern {
    CommonPattern() {
    }

    /* access modifiers changed from: package-private */
    public abstract CommonMatcher a(CharSequence charSequence);

    /* access modifiers changed from: package-private */
    public abstract String a();

    /* access modifiers changed from: package-private */
    public abstract int b();

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract String toString();
}
