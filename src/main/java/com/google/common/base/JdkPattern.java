package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@GwtIncompatible
final class JdkPattern extends CommonPattern implements Serializable {
    private static final long serialVersionUID = 0;
    private final Pattern pattern;

    private static final class JdkMatcher extends CommonMatcher {
        final Matcher a;

        JdkMatcher(Matcher matcher) {
            this.a = (Matcher) Preconditions.checkNotNull(matcher);
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return this.a.matches();
        }

        /* access modifiers changed from: package-private */
        public boolean a(int i) {
            return this.a.find(i);
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            return this.a.find();
        }

        /* access modifiers changed from: package-private */
        public int c() {
            return this.a.end();
        }

        /* access modifiers changed from: package-private */
        public int d() {
            return this.a.start();
        }
    }

    JdkPattern(Pattern pattern2) {
        this.pattern = (Pattern) Preconditions.checkNotNull(pattern2);
    }

    /* access modifiers changed from: package-private */
    public CommonMatcher a(CharSequence charSequence) {
        return new JdkMatcher(this.pattern.matcher(charSequence));
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return this.pattern.pattern();
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.pattern.flags();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof JdkPattern)) {
            return false;
        }
        return this.pattern.equals(((JdkPattern) obj).pattern);
    }

    public int hashCode() {
        return this.pattern.hashCode();
    }

    public String toString() {
        return this.pattern.toString();
    }
}
