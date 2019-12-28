package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@GwtCompatible(emulated = true)
public final class Splitter {
    /* access modifiers changed from: private */
    public final int limit;
    /* access modifiers changed from: private */
    public final boolean omitEmptyStrings;
    private final Strategy strategy;
    /* access modifiers changed from: private */
    public final CharMatcher trimmer;

    @Beta
    public static final class MapSplitter {
        private static final String INVALID_ENTRY_MESSAGE = "Chunk [%s] is not a valid entry";
        private final Splitter entrySplitter;
        private final Splitter outerSplitter;

        private MapSplitter(Splitter splitter, Splitter splitter2) {
            this.outerSplitter = splitter;
            this.entrySplitter = (Splitter) Preconditions.checkNotNull(splitter2);
        }

        public Map<String, String> split(CharSequence charSequence) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (String next : this.outerSplitter.split(charSequence)) {
                Iterator a = this.entrySplitter.splittingIterator(next);
                Preconditions.checkArgument(a.hasNext(), INVALID_ENTRY_MESSAGE, (Object) next);
                String str = (String) a.next();
                Preconditions.checkArgument(!linkedHashMap.containsKey(str), "Duplicate key [%s] found.", (Object) str);
                Preconditions.checkArgument(a.hasNext(), INVALID_ENTRY_MESSAGE, (Object) next);
                linkedHashMap.put(str, (String) a.next());
                Preconditions.checkArgument(!a.hasNext(), INVALID_ENTRY_MESSAGE, (Object) next);
            }
            return Collections.unmodifiableMap(linkedHashMap);
        }
    }

    private static abstract class SplittingIterator extends AbstractIterator<String> {
        final CharSequence c;
        final CharMatcher d;
        final boolean e;
        int f = 0;
        int g;

        protected SplittingIterator(Splitter splitter, CharSequence charSequence) {
            this.d = splitter.trimmer;
            this.e = splitter.omitEmptyStrings;
            this.g = splitter.limit;
            this.c = charSequence;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
            if (r0 >= r1) goto L_0x0044;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
            if (r6.d.matches(r6.c.charAt(r0)) == false) goto L_0x0044;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
            r0 = r0 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
            if (r1 <= r0) goto L_0x0059;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0054, code lost:
            if (r6.d.matches(r6.c.charAt(r1 - 1)) == false) goto L_0x0059;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
            r1 = r1 - 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x005b, code lost:
            if (r6.e == false) goto L_0x0060;
         */
        /* renamed from: c */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String a() {
            /*
                r6 = this;
            L_0x0000:
                int r0 = r6.f
            L_0x0002:
                int r1 = r6.f
                r2 = -1
                if (r1 == r2) goto L_0x0090
                int r1 = r6.separatorStart(r1)
                if (r1 != r2) goto L_0x0016
                java.lang.CharSequence r1 = r6.c
                int r1 = r1.length()
                r6.f = r2
                goto L_0x001c
            L_0x0016:
                int r3 = r6.separatorEnd(r1)
                r6.f = r3
            L_0x001c:
                int r3 = r6.f
                if (r3 != r0) goto L_0x0031
                int r3 = r3 + 1
                r6.f = r3
                int r1 = r6.f
                java.lang.CharSequence r3 = r6.c
                int r3 = r3.length()
                if (r1 < r3) goto L_0x0002
                r6.f = r2
                goto L_0x0002
            L_0x0031:
                if (r0 >= r1) goto L_0x0044
                com.google.common.base.CharMatcher r3 = r6.d
                java.lang.CharSequence r4 = r6.c
                char r4 = r4.charAt(r0)
                boolean r3 = r3.matches(r4)
                if (r3 == 0) goto L_0x0044
                int r0 = r0 + 1
                goto L_0x0031
            L_0x0044:
                if (r1 <= r0) goto L_0x0059
                com.google.common.base.CharMatcher r3 = r6.d
                java.lang.CharSequence r4 = r6.c
                int r5 = r1 + -1
                char r4 = r4.charAt(r5)
                boolean r3 = r3.matches(r4)
                if (r3 == 0) goto L_0x0059
                int r1 = r1 + -1
                goto L_0x0044
            L_0x0059:
                boolean r3 = r6.e
                if (r3 == 0) goto L_0x0060
                if (r0 != r1) goto L_0x0060
                goto L_0x0000
            L_0x0060:
                int r3 = r6.g
                r4 = 1
                if (r3 != r4) goto L_0x0082
                java.lang.CharSequence r1 = r6.c
                int r1 = r1.length()
                r6.f = r2
            L_0x006d:
                if (r1 <= r0) goto L_0x0085
                com.google.common.base.CharMatcher r2 = r6.d
                java.lang.CharSequence r3 = r6.c
                int r4 = r1 + -1
                char r3 = r3.charAt(r4)
                boolean r2 = r2.matches(r3)
                if (r2 == 0) goto L_0x0085
                int r1 = r1 + -1
                goto L_0x006d
            L_0x0082:
                int r3 = r3 - r4
                r6.g = r3
            L_0x0085:
                java.lang.CharSequence r2 = r6.c
                java.lang.CharSequence r0 = r2.subSequence(r0, r1)
                java.lang.String r0 = r0.toString()
                return r0
            L_0x0090:
                java.lang.Object r0 = r6.b()
                java.lang.String r0 = (java.lang.String) r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.Splitter.SplittingIterator.a():java.lang.String");
        }

        /* access modifiers changed from: package-private */
        public abstract int separatorEnd(int i);

        /* access modifiers changed from: package-private */
        public abstract int separatorStart(int i);
    }

    private interface Strategy {
        Iterator<String> iterator(Splitter splitter, CharSequence charSequence);
    }

    private Splitter(Strategy strategy2) {
        this(strategy2, false, CharMatcher.none(), Integer.MAX_VALUE);
    }

    private Splitter(Strategy strategy2, boolean z, CharMatcher charMatcher, int i) {
        this.strategy = strategy2;
        this.omitEmptyStrings = z;
        this.trimmer = charMatcher;
        this.limit = i;
    }

    public static Splitter fixedLength(final int i) {
        Preconditions.checkArgument(i > 0, "The length may not be less than 1");
        return new Splitter(new Strategy() {
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) {
                    public int separatorEnd(int i) {
                        return i;
                    }

                    public int separatorStart(int i) {
                        int i2 = i + i;
                        if (i2 < this.c.length()) {
                            return i2;
                        }
                        return -1;
                    }
                };
            }
        });
    }

    public static Splitter on(char c) {
        return on(CharMatcher.is(c));
    }

    public static Splitter on(final CharMatcher charMatcher) {
        Preconditions.checkNotNull(charMatcher);
        return new Splitter(new Strategy() {
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) {
                    /* access modifiers changed from: package-private */
                    public int separatorEnd(int i) {
                        return i + 1;
                    }

                    /* access modifiers changed from: package-private */
                    public int separatorStart(int i) {
                        return charMatcher.indexIn(this.c, i);
                    }
                };
            }
        });
    }

    private static Splitter on(final CommonPattern commonPattern) {
        Preconditions.checkArgument(!commonPattern.a("").a(), "The pattern may not match the empty string: %s", (Object) commonPattern);
        return new Splitter(new Strategy() {
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                final CommonMatcher a2 = commonPattern.a(charSequence);
                return new SplittingIterator(splitter, charSequence) {
                    public int separatorEnd(int i) {
                        return a2.c();
                    }

                    public int separatorStart(int i) {
                        if (a2.a(i)) {
                            return a2.d();
                        }
                        return -1;
                    }
                };
            }
        });
    }

    public static Splitter on(final String str) {
        Preconditions.checkArgument(str.length() != 0, "The separator may not be the empty string.");
        return str.length() == 1 ? on(str.charAt(0)) : new Splitter(new Strategy() {
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) {
                    public int separatorEnd(int i) {
                        return i + str.length();
                    }

                    public int separatorStart(int i) {
                        int length = str.length();
                        int length2 = this.c.length() - length;
                        while (i <= length2) {
                            int i2 = 0;
                            while (i2 < length) {
                                if (this.c.charAt(i2 + i) != str.charAt(i2)) {
                                    i++;
                                } else {
                                    i2++;
                                }
                            }
                            return i;
                        }
                        return -1;
                    }
                };
            }
        });
    }

    @GwtIncompatible
    public static Splitter on(Pattern pattern) {
        return on((CommonPattern) new JdkPattern(pattern));
    }

    @GwtIncompatible
    public static Splitter onPattern(String str) {
        return on(Platform.b(str));
    }

    /* access modifiers changed from: private */
    public Iterator<String> splittingIterator(CharSequence charSequence) {
        return this.strategy.iterator(this, charSequence);
    }

    public Splitter limit(int i) {
        Preconditions.checkArgument(i > 0, "must be greater than zero: %s", i);
        return new Splitter(this.strategy, this.omitEmptyStrings, this.trimmer, i);
    }

    public Splitter omitEmptyStrings() {
        return new Splitter(this.strategy, true, this.trimmer, this.limit);
    }

    public Iterable<String> split(final CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return Splitter.this.splittingIterator(charSequence);
            }

            public String toString() {
                Joiner on = Joiner.on(", ");
                StringBuilder sb = new StringBuilder();
                sb.append('[');
                StringBuilder appendTo = on.appendTo(sb, (Iterable<?>) this);
                appendTo.append(']');
                return appendTo.toString();
            }
        };
    }

    @Beta
    public List<String> splitToList(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        Iterator<String> splittingIterator = splittingIterator(charSequence);
        ArrayList arrayList = new ArrayList();
        while (splittingIterator.hasNext()) {
            arrayList.add(splittingIterator.next());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Splitter trimResults() {
        return trimResults(CharMatcher.whitespace());
    }

    public Splitter trimResults(CharMatcher charMatcher) {
        Preconditions.checkNotNull(charMatcher);
        return new Splitter(this.strategy, this.omitEmptyStrings, charMatcher, this.limit);
    }

    @Beta
    public MapSplitter withKeyValueSeparator(char c) {
        return withKeyValueSeparator(on(c));
    }

    @Beta
    public MapSplitter withKeyValueSeparator(Splitter splitter) {
        return new MapSplitter(splitter);
    }

    @Beta
    public MapSplitter withKeyValueSeparator(String str) {
        return withKeyValueSeparator(on(str));
    }
}
