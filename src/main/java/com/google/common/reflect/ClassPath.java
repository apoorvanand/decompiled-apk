package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Logger;
import javax.annotation.Nullable;

@Beta
public final class ClassPath {
    private static final String CLASS_FILE_NAME_EXTENSION = ".class";
    /* access modifiers changed from: private */
    public static final Splitter CLASS_PATH_ATTRIBUTE_SEPARATOR = Splitter.on(" ").omitEmptyStrings();
    private static final Predicate<ClassInfo> IS_TOP_LEVEL = new Predicate<ClassInfo>() {
        public boolean apply(ClassInfo classInfo) {
            return classInfo.className.indexOf(36) == -1;
        }
    };
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(ClassPath.class.getName());
    private final ImmutableSet<ResourceInfo> resources;

    @Beta
    public static final class ClassInfo extends ResourceInfo {
        /* access modifiers changed from: private */
        public final String className;

        ClassInfo(String str, ClassLoader classLoader) {
            super(str, classLoader);
            this.className = ClassPath.a(str);
        }

        public String getName() {
            return this.className;
        }

        public String getPackageName() {
            return Reflection.getPackageName(this.className);
        }

        public String getSimpleName() {
            int lastIndexOf = this.className.lastIndexOf(36);
            if (lastIndexOf != -1) {
                return CharMatcher.digit().trimLeadingFrom(this.className.substring(lastIndexOf + 1));
            }
            String packageName = getPackageName();
            return packageName.isEmpty() ? this.className : this.className.substring(packageName.length() + 1);
        }

        public Class<?> load() {
            try {
                return this.a.loadClass(this.className);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }

        public String toString() {
            return this.className;
        }
    }

    @VisibleForTesting
    static final class DefaultScanner extends Scanner {
        private final SetMultimap<ClassLoader, String> resources = MultimapBuilder.hashKeys().linkedHashSetValues().build();

        DefaultScanner() {
        }

        private void scanDirectory(File file, ClassLoader classLoader, String str) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                ClassPath.logger.warning("Cannot read directory " + file);
                return;
            }
            for (File file2 : listFiles) {
                String name = file2.getName();
                if (file2.isDirectory()) {
                    scanDirectory(file2, classLoader, str + name + "/");
                } else {
                    String str2 = str + name;
                    if (!str2.equals("META-INF/MANIFEST.MF")) {
                        this.resources.get(classLoader).add(str2);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public ImmutableSet<ResourceInfo> a() {
            ImmutableSet.Builder builder = ImmutableSet.builder();
            for (Map.Entry next : this.resources.entries()) {
                builder.add((Object) ResourceInfo.a((String) next.getValue(), (ClassLoader) next.getKey()));
            }
            return builder.build();
        }

        /* access modifiers changed from: protected */
        public void a(ClassLoader classLoader, File file) {
            scanDirectory(file, classLoader, "");
        }

        /* access modifiers changed from: protected */
        public void a(ClassLoader classLoader, JarFile jarFile) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry nextElement = entries.nextElement();
                if (!nextElement.isDirectory() && !nextElement.getName().equals("META-INF/MANIFEST.MF")) {
                    this.resources.get(classLoader).add(nextElement.getName());
                }
            }
        }
    }

    @Beta
    public static class ResourceInfo {
        final ClassLoader a;
        private final String resourceName;

        ResourceInfo(String str, ClassLoader classLoader) {
            this.resourceName = (String) Preconditions.checkNotNull(str);
            this.a = (ClassLoader) Preconditions.checkNotNull(classLoader);
        }

        static ResourceInfo a(String str, ClassLoader classLoader) {
            return str.endsWith(ClassPath.CLASS_FILE_NAME_EXTENSION) ? new ClassInfo(str, classLoader) : new ResourceInfo(str, classLoader);
        }

        public final ByteSource asByteSource() {
            return Resources.asByteSource(url());
        }

        public final CharSource asCharSource(Charset charset) {
            return Resources.asCharSource(url(), charset);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ResourceInfo)) {
                return false;
            }
            ResourceInfo resourceInfo = (ResourceInfo) obj;
            return this.resourceName.equals(resourceInfo.resourceName) && this.a == resourceInfo.a;
        }

        public final String getResourceName() {
            return this.resourceName;
        }

        public int hashCode() {
            return this.resourceName.hashCode();
        }

        public String toString() {
            return this.resourceName;
        }

        public final URL url() {
            URL resource = this.a.getResource(this.resourceName);
            if (resource != null) {
                return resource;
            }
            throw new NoSuchElementException(this.resourceName);
        }
    }

    static abstract class Scanner {
        private final Set<File> scannedUris = Sets.newHashSet();

        Scanner() {
        }

        @VisibleForTesting
        static ImmutableMap<File, ClassLoader> a(ClassLoader classLoader) {
            LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
            ClassLoader parent = classLoader.getParent();
            if (parent != null) {
                newLinkedHashMap.putAll(a(parent));
            }
            if (classLoader instanceof URLClassLoader) {
                for (URL url : ((URLClassLoader) classLoader).getURLs()) {
                    if (url.getProtocol().equals("file")) {
                        File file = new File(url.getFile());
                        if (!newLinkedHashMap.containsKey(file)) {
                            newLinkedHashMap.put(file, classLoader);
                        }
                    }
                }
            }
            return ImmutableMap.copyOf(newLinkedHashMap);
        }

        @VisibleForTesting
        static ImmutableSet<File> a(File file, @Nullable Manifest manifest) {
            if (manifest == null) {
                return ImmutableSet.of();
            }
            ImmutableSet.Builder builder = ImmutableSet.builder();
            String value = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH.toString());
            if (value != null) {
                for (String next : ClassPath.CLASS_PATH_ATTRIBUTE_SEPARATOR.split(value)) {
                    try {
                        URL a = a(file, next);
                        if (a.getProtocol().equals("file")) {
                            builder.add((Object) new File(a.getFile()));
                        }
                    } catch (MalformedURLException unused) {
                        Logger a2 = ClassPath.logger;
                        a2.warning("Invalid Class-Path entry: " + next);
                    }
                }
            }
            return builder.build();
        }

        @VisibleForTesting
        static URL a(File file, String str) {
            return new URL(file.toURI().toURL(), str);
        }

        private void scanFrom(File file, ClassLoader classLoader) {
            try {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        a(classLoader, file);
                    } else {
                        scanJar(file, classLoader);
                    }
                }
            } catch (SecurityException e) {
                Logger a = ClassPath.logger;
                a.warning("Cannot access " + file + ": " + e);
            }
        }

        private void scanJar(File file, ClassLoader classLoader) {
            try {
                JarFile jarFile = new JarFile(file);
                try {
                    UnmodifiableIterator<File> it = a(file, jarFile.getManifest()).iterator();
                    while (it.hasNext()) {
                        a(it.next(), classLoader);
                    }
                    a(classLoader, jarFile);
                } finally {
                    try {
                        jarFile.close();
                    } catch (IOException unused) {
                    }
                }
            } catch (IOException unused2) {
            }
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public final void a(File file, ClassLoader classLoader) {
            if (this.scannedUris.add(file.getCanonicalFile())) {
                scanFrom(file, classLoader);
            }
        }

        /* access modifiers changed from: protected */
        public abstract void a(ClassLoader classLoader, File file);

        /* access modifiers changed from: protected */
        public abstract void a(ClassLoader classLoader, JarFile jarFile);

        public final void scan(ClassLoader classLoader) {
            UnmodifiableIterator<Map.Entry<File, ClassLoader>> it = a(classLoader).entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                a((File) next.getKey(), (ClassLoader) next.getValue());
            }
        }
    }

    private ClassPath(ImmutableSet<ResourceInfo> immutableSet) {
        this.resources = immutableSet;
    }

    @VisibleForTesting
    static String a(String str) {
        return str.substring(0, str.length() - 6).replace('/', '.');
    }

    public static ClassPath from(ClassLoader classLoader) {
        DefaultScanner defaultScanner = new DefaultScanner();
        defaultScanner.scan(classLoader);
        return new ClassPath(defaultScanner.a());
    }

    public ImmutableSet<ClassInfo> getAllClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).toSet();
    }

    public ImmutableSet<ResourceInfo> getResources() {
        return this.resources;
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).filter(IS_TOP_LEVEL).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses(String str) {
        Preconditions.checkNotNull(str);
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo next = it.next();
            if (next.getPackageName().equals(str)) {
                builder.add((Object) next);
            }
        }
        return builder.build();
    }

    public ImmutableSet<ClassInfo> getTopLevelClassesRecursive(String str) {
        Preconditions.checkNotNull(str);
        String str2 = str + '.';
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo next = it.next();
            if (next.getName().startsWith(str2)) {
                builder.add((Object) next);
            }
        }
        return builder.build();
    }
}
