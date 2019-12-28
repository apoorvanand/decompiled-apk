package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.ServiceConfigurationError;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
final class Platform {
    private static final Logger logger = Logger.getLogger(Platform.class.getName());
    private static final PatternCompiler patternCompiler = loadPatternCompiler();

    private static final class JdkPatternCompiler implements PatternCompiler {
        private JdkPatternCompiler() {
        }

        public CommonPattern compile(String str) {
            return new JdkPattern(Pattern.compile(str));
        }
    }

    private Platform() {
    }

    static long a() {
        return System.nanoTime();
    }

    static CharMatcher a(CharMatcher charMatcher) {
        return charMatcher.a();
    }

    static <T extends Enum<T>> Optional<T> a(Class<T> cls, String str) {
        WeakReference weakReference = Enums.a(cls).get(str);
        return weakReference == null ? Optional.absent() : Optional.of(cls.cast(weakReference.get()));
    }

    static String a(double d) {
        return String.format(Locale.ROOT, "%.4g", new Object[]{Double.valueOf(d)});
    }

    static boolean a(@Nullable String str) {
        return str == null || str.isEmpty();
    }

    static CommonPattern b(String str) {
        Preconditions.checkNotNull(str);
        return patternCompiler.compile(str);
    }

    private static PatternCompiler loadPatternCompiler() {
        return new JdkPatternCompiler();
    }

    private static void logPatternCompilerError(ServiceConfigurationError serviceConfigurationError) {
        logger.log(Level.WARNING, "Error loading regex compiler, falling back to next option", serviceConfigurationError);
    }
}
