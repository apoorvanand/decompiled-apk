package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible
public class Joiner {
    /* access modifiers changed from: private */
    public final String separator;

    public static final class MapJoiner {
        private final Joiner joiner;
        private final String keyValueSeparator;

        private MapJoiner(Joiner joiner2, String str) {
            this.joiner = joiner2;
            this.keyValueSeparator = (String) Preconditions.checkNotNull(str);
        }

        @CanIgnoreReturnValue
        @Beta
        public <A extends Appendable> A appendTo(A a, Iterable<? extends Map.Entry<?, ?>> iterable) {
            return appendTo(a, iterable.iterator());
        }

        @CanIgnoreReturnValue
        @Beta
        public <A extends Appendable> A appendTo(A a, Iterator<? extends Map.Entry<?, ?>> it) {
            Preconditions.checkNotNull(a);
            if (it.hasNext()) {
                while (true) {
                    Map.Entry entry = (Map.Entry) it.next();
                    a.append(this.joiner.a(entry.getKey()));
                    a.append(this.keyValueSeparator);
                    a.append(this.joiner.a(entry.getValue()));
                    if (!it.hasNext()) {
                        break;
                    }
                    a.append(this.joiner.separator);
                }
            }
            return a;
        }

        @CanIgnoreReturnValue
        public <A extends Appendable> A appendTo(A a, Map<?, ?> map) {
            return appendTo(a, (Iterable<? extends Map.Entry<?, ?>>) map.entrySet());
        }

        @CanIgnoreReturnValue
        @Beta
        public StringBuilder appendTo(StringBuilder sb, Iterable<? extends Map.Entry<?, ?>> iterable) {
            return appendTo(sb, iterable.iterator());
        }

        @CanIgnoreReturnValue
        @Beta
        public StringBuilder appendTo(StringBuilder sb, Iterator<? extends Map.Entry<?, ?>> it) {
            try {
                appendTo(sb, it);
                return sb;
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @CanIgnoreReturnValue
        public StringBuilder appendTo(StringBuilder sb, Map<?, ?> map) {
            return appendTo(sb, (Iterable<? extends Map.Entry<?, ?>>) map.entrySet());
        }

        @Beta
        public String join(Iterable<? extends Map.Entry<?, ?>> iterable) {
            return join(iterable.iterator());
        }

        @Beta
        public String join(Iterator<? extends Map.Entry<?, ?>> it) {
            return appendTo(new StringBuilder(), it).toString();
        }

        public String join(Map<?, ?> map) {
            return join((Iterable<? extends Map.Entry<?, ?>>) map.entrySet());
        }

        public MapJoiner useForNull(String str) {
            return new MapJoiner(this.joiner.useForNull(str), this.keyValueSeparator);
        }
    }

    private Joiner(Joiner joiner) {
        this.separator = joiner.separator;
    }

    private Joiner(String str) {
        this.separator = (String) Preconditions.checkNotNull(str);
    }

    private static Iterable<Object> iterable(final Object obj, final Object obj2, final Object[] objArr) {
        Preconditions.checkNotNull(objArr);
        return new AbstractList<Object>() {
            public Object get(int i) {
                switch (i) {
                    case 0:
                        return obj;
                    case 1:
                        return obj2;
                    default:
                        return objArr[i - 2];
                }
            }

            public int size() {
                return objArr.length + 2;
            }
        };
    }

    public static Joiner on(char c) {
        return new Joiner(String.valueOf(c));
    }

    public static Joiner on(String str) {
        return new Joiner(str);
    }

    /* access modifiers changed from: package-private */
    public CharSequence a(Object obj) {
        Preconditions.checkNotNull(obj);
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    @CanIgnoreReturnValue
    public <A extends Appendable> A appendTo(A a, Iterable<?> iterable) {
        return appendTo(a, iterable.iterator());
    }

    @CanIgnoreReturnValue
    public final <A extends Appendable> A appendTo(A a, @Nullable Object obj, @Nullable Object obj2, Object... objArr) {
        return appendTo(a, (Iterable<?>) iterable(obj, obj2, objArr));
    }

    @CanIgnoreReturnValue
    public <A extends Appendable> A appendTo(A a, Iterator<?> it) {
        Preconditions.checkNotNull(a);
        if (it.hasNext()) {
            while (true) {
                a.append(a((Object) it.next()));
                if (!it.hasNext()) {
                    break;
                }
                a.append(this.separator);
            }
        }
        return a;
    }

    @CanIgnoreReturnValue
    public final <A extends Appendable> A appendTo(A a, Object[] objArr) {
        return appendTo(a, (Iterable<?>) Arrays.asList(objArr));
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder sb, Iterable<?> iterable) {
        return appendTo(sb, iterable.iterator());
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder sb, @Nullable Object obj, @Nullable Object obj2, Object... objArr) {
        return appendTo(sb, (Iterable<?>) iterable(obj, obj2, objArr));
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder sb, Iterator<?> it) {
        try {
            appendTo(sb, it);
            return sb;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder sb, Object[] objArr) {
        return appendTo(sb, (Iterable<?>) Arrays.asList(objArr));
    }

    public final String join(Iterable<?> iterable) {
        return join(iterable.iterator());
    }

    public final String join(@Nullable Object obj, @Nullable Object obj2, Object... objArr) {
        return join((Iterable<?>) iterable(obj, obj2, objArr));
    }

    public final String join(Iterator<?> it) {
        return appendTo(new StringBuilder(), it).toString();
    }

    public final String join(Object[] objArr) {
        return join((Iterable<?>) Arrays.asList(objArr));
    }

    public Joiner skipNulls() {
        return new Joiner(this) {
            /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
                	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
                */
            /* JADX WARNING: Removed duplicated region for block: B:14:0x0035 A[SYNTHETIC] */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
            public <A extends java.lang.Appendable> A appendTo(A r3, java.util.Iterator<?> r4) {
                /*
                    r2 = this;
                    java.lang.String r0 = "appendable"
                    com.google.common.base.Preconditions.checkNotNull(r3, r0)
                    java.lang.String r0 = "parts"
                    com.google.common.base.Preconditions.checkNotNull(r4, r0)
                L_0x000a:
                    boolean r0 = r4.hasNext()
                    if (r0 == 0) goto L_0x001f
                    java.lang.Object r0 = r4.next()
                    if (r0 == 0) goto L_0x000a
                L_0x0016:
                    com.google.common.base.Joiner r1 = com.google.common.base.Joiner.this
                    java.lang.CharSequence r0 = r1.a((java.lang.Object) r0)
                    r3.append(r0)
                L_0x001f:
                    boolean r0 = r4.hasNext()
                    if (r0 == 0) goto L_0x0035
                    java.lang.Object r0 = r4.next()
                    if (r0 == 0) goto L_0x001f
                    com.google.common.base.Joiner r1 = com.google.common.base.Joiner.this
                    java.lang.String r1 = r1.separator
                    r3.append(r1)
                    goto L_0x0016
                L_0x0035:
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.Joiner.AnonymousClass2.appendTo(java.lang.Appendable, java.util.Iterator):java.lang.Appendable");
            }

            public Joiner useForNull(String str) {
                throw new UnsupportedOperationException("already specified skipNulls");
            }

            public MapJoiner withKeyValueSeparator(String str) {
                throw new UnsupportedOperationException("can't use .skipNulls() with maps");
            }
        };
    }

    public Joiner useForNull(final String str) {
        Preconditions.checkNotNull(str);
        return new Joiner(this) {
            /* access modifiers changed from: package-private */
            public CharSequence a(@Nullable Object obj) {
                return obj == null ? str : Joiner.this.a(obj);
            }

            public Joiner skipNulls() {
                throw new UnsupportedOperationException("already specified useForNull");
            }

            public Joiner useForNull(String str) {
                throw new UnsupportedOperationException("already specified useForNull");
            }
        };
    }

    public MapJoiner withKeyValueSeparator(char c) {
        return withKeyValueSeparator(String.valueOf(c));
    }

    public MapJoiner withKeyValueSeparator(String str) {
        return new MapJoiner(str);
    }
}
