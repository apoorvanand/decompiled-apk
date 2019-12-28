package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import javax.annotation.Nullable;

@GwtCompatible
public final class MoreObjects {

    public static final class ToStringHelper {
        private final String className;
        private final ValueHolder holderHead;
        private ValueHolder holderTail;
        private boolean omitNullValues;

        private static final class ValueHolder {
            String a;
            Object b;
            ValueHolder c;

            private ValueHolder() {
            }
        }

        private ToStringHelper(String str) {
            this.holderHead = new ValueHolder();
            this.holderTail = this.holderHead;
            this.omitNullValues = false;
            this.className = (String) Preconditions.checkNotNull(str);
        }

        private ValueHolder addHolder() {
            ValueHolder valueHolder = new ValueHolder();
            this.holderTail.c = valueHolder;
            this.holderTail = valueHolder;
            return valueHolder;
        }

        private ToStringHelper addHolder(@Nullable Object obj) {
            addHolder().b = obj;
            return this;
        }

        private ToStringHelper addHolder(String str, @Nullable Object obj) {
            ValueHolder addHolder = addHolder();
            addHolder.b = obj;
            addHolder.a = (String) Preconditions.checkNotNull(str);
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, char c) {
            return addHolder(str, String.valueOf(c));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, double d) {
            return addHolder(str, String.valueOf(d));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, float f) {
            return addHolder(str, String.valueOf(f));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, int i) {
            return addHolder(str, String.valueOf(i));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, long j) {
            return addHolder(str, String.valueOf(j));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, @Nullable Object obj) {
            return addHolder(str, obj);
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, boolean z) {
            return addHolder(str, String.valueOf(z));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(char c) {
            return addHolder(String.valueOf(c));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(double d) {
            return addHolder(String.valueOf(d));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(float f) {
            return addHolder(String.valueOf(f));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(int i) {
            return addHolder(String.valueOf(i));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(long j) {
            return addHolder(String.valueOf(j));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(@Nullable Object obj) {
            return addHolder(obj);
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(boolean z) {
            return addHolder(String.valueOf(z));
        }

        @CanIgnoreReturnValue
        public ToStringHelper omitNullValues() {
            this.omitNullValues = true;
            return this;
        }

        public String toString() {
            boolean z = this.omitNullValues;
            String str = "";
            StringBuilder sb = new StringBuilder(32);
            sb.append(this.className);
            sb.append('{');
            ValueHolder valueHolder = this.holderHead;
            while (true) {
                valueHolder = valueHolder.c;
                if (valueHolder != null) {
                    Object obj = valueHolder.b;
                    if (!z || obj != null) {
                        sb.append(str);
                        str = ", ";
                        if (valueHolder.a != null) {
                            sb.append(valueHolder.a);
                            sb.append('=');
                        }
                        if (obj == null || !obj.getClass().isArray()) {
                            sb.append(obj);
                        } else {
                            String deepToString = Arrays.deepToString(new Object[]{obj});
                            sb.append(deepToString, 1, deepToString.length() - 1);
                        }
                    }
                } else {
                    sb.append('}');
                    return sb.toString();
                }
            }
        }
    }

    private MoreObjects() {
    }

    public static <T> T firstNonNull(@Nullable T t, @Nullable T t2) {
        return t != null ? t : Preconditions.checkNotNull(t2);
    }

    public static ToStringHelper toStringHelper(Class<?> cls) {
        return new ToStringHelper(cls.getSimpleName());
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj.getClass().getSimpleName());
    }

    public static ToStringHelper toStringHelper(String str) {
        return new ToStringHelper(str);
    }
}