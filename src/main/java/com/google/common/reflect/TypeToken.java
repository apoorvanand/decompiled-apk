package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Primitives;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeResolver;
import com.google.common.reflect.Types;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Beta
public abstract class TypeToken<T> extends TypeCapture<T> implements Serializable {
    /* access modifiers changed from: private */
    public final Type runtimeType;
    private transient TypeResolver typeResolver;

    private static class Bounds {
        private final Type[] bounds;
        private final boolean target;

        Bounds(Type[] typeArr, boolean z) {
            this.bounds = typeArr;
            this.target = z;
        }

        /* access modifiers changed from: package-private */
        public boolean a(Type type) {
            for (Type of : this.bounds) {
                boolean isSubtypeOf = TypeToken.of(of).isSubtypeOf(type);
                boolean z = this.target;
                if (isSubtypeOf == z) {
                    return z;
                }
            }
            return !this.target;
        }

        /* access modifiers changed from: package-private */
        public boolean b(Type type) {
            TypeToken<?> of = TypeToken.of(type);
            for (Type isSubtypeOf : this.bounds) {
                boolean isSubtypeOf2 = of.isSubtypeOf(isSubtypeOf);
                boolean z = this.target;
                if (isSubtypeOf2 == z) {
                    return z;
                }
            }
            return !this.target;
        }
    }

    private final class ClassSet extends TypeToken<T>.TypeSet {
        private static final long serialVersionUID = 0;
        private transient ImmutableSet<TypeToken<? super T>> classes;

        private ClassSet() {
            super();
        }

        private Object readResolve() {
            return TypeToken.this.getTypes().classes();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.classes;
            if (immutableSet != null) {
                return immutableSet;
            }
            ImmutableSet<TypeToken<?>> set = FluentIterable.from(TypeCollector.a.a().a(TypeToken.this)).filter(TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
            this.classes = set;
            return set;
        }

        public TypeToken<T>.TypeSet classes() {
            return this;
        }

        public TypeToken<T>.TypeSet interfaces() {
            throw new UnsupportedOperationException("classes().interfaces() not supported.");
        }

        public Set<Class<? super T>> rawTypes() {
            return ImmutableSet.copyOf(TypeCollector.b.a().a(TypeToken.this.getRawTypes()));
        }
    }

    private final class InterfaceSet extends TypeToken<T>.TypeSet {
        private static final long serialVersionUID = 0;
        private final transient TypeToken<T>.TypeSet allTypes;
        private transient ImmutableSet<TypeToken<? super T>> interfaces;

        InterfaceSet(TypeToken<T>.TypeSet typeSet) {
            super();
            this.allTypes = typeSet;
        }

        private Object readResolve() {
            return TypeToken.this.getTypes().interfaces();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.interfaces;
            if (immutableSet != null) {
                return immutableSet;
            }
            ImmutableSet<TypeToken<? super T>> set = FluentIterable.from(this.allTypes).filter(TypeFilter.INTERFACE_ONLY).toSet();
            this.interfaces = set;
            return set;
        }

        public TypeToken<T>.TypeSet classes() {
            throw new UnsupportedOperationException("interfaces().classes() not supported.");
        }

        public TypeToken<T>.TypeSet interfaces() {
            return this;
        }

        public Set<Class<? super T>> rawTypes() {
            return FluentIterable.from(TypeCollector.b.a(TypeToken.this.getRawTypes())).filter(new Predicate<Class<?>>() {
                public boolean apply(Class<?> cls) {
                    return cls.isInterface();
                }
            }).toSet();
        }
    }

    private static final class SimpleTypeToken<T> extends TypeToken<T> {
        private static final long serialVersionUID = 0;

        SimpleTypeToken(Type type) {
            super(type);
        }
    }

    private static abstract class TypeCollector<K> {
        static final TypeCollector<TypeToken<?>> a = new TypeCollector<TypeToken<?>>() {
            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public Class<?> b(TypeToken<?> typeToken) {
                return typeToken.getRawType();
            }

            /* access modifiers changed from: package-private */
            /* renamed from: b */
            public Iterable<? extends TypeToken<?>> c(TypeToken<?> typeToken) {
                return typeToken.c();
            }

            /* access modifiers changed from: package-private */
            @Nullable
            /* renamed from: c */
            public TypeToken<?> d(TypeToken<?> typeToken) {
                return typeToken.b();
            }
        };
        static final TypeCollector<Class<?>> b = new TypeCollector<Class<?>>() {
            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public Class<?> b(Class<?> cls) {
                return cls;
            }

            /* access modifiers changed from: package-private */
            /* renamed from: b */
            public Iterable<? extends Class<?>> c(Class<?> cls) {
                return Arrays.asList(cls.getInterfaces());
            }

            /* access modifiers changed from: package-private */
            @Nullable
            /* renamed from: c */
            public Class<?> d(Class<?> cls) {
                return cls.getSuperclass();
            }
        };

        private static class ForwardingTypeCollector<K> extends TypeCollector<K> {
            private final TypeCollector<K> delegate;

            ForwardingTypeCollector(TypeCollector<K> typeCollector) {
                super();
                this.delegate = typeCollector;
            }

            /* access modifiers changed from: package-private */
            public Class<?> b(K k) {
                return this.delegate.b(k);
            }

            /* access modifiers changed from: package-private */
            public Iterable<? extends K> c(K k) {
                return this.delegate.c(k);
            }

            /* access modifiers changed from: package-private */
            public K d(K k) {
                return this.delegate.d(k);
            }
        }

        private TypeCollector() {
        }

        @CanIgnoreReturnValue
        private int collectTypes(K k, Map<? super K, Integer> map) {
            Integer num = map.get(k);
            if (num != null) {
                return num.intValue();
            }
            int isInterface = b(k).isInterface();
            for (Object collectTypes : c(k)) {
                isInterface = Math.max(isInterface, collectTypes(collectTypes, map));
            }
            Object d = d(k);
            if (d != null) {
                isInterface = Math.max(isInterface, collectTypes(d, map));
            }
            int i = isInterface + 1;
            map.put(k, Integer.valueOf(i));
            return i;
        }

        private static <K, V> ImmutableList<K> sortKeysByValue(final Map<K, V> map, final Comparator<? super V> comparator) {
            return new Ordering<K>() {
                public int compare(K k, K k2) {
                    return comparator.compare(map.get(k), map.get(k2));
                }
            }.immutableSortedCopy(map.keySet());
        }

        /* access modifiers changed from: package-private */
        public ImmutableList<K> a(Iterable<? extends K> iterable) {
            HashMap newHashMap = Maps.newHashMap();
            for (Object collectTypes : iterable) {
                collectTypes(collectTypes, newHashMap);
            }
            return sortKeysByValue(newHashMap, Ordering.natural().reverse());
        }

        /* access modifiers changed from: package-private */
        public final ImmutableList<K> a(K k) {
            return a(ImmutableList.of(k));
        }

        /* access modifiers changed from: package-private */
        public final TypeCollector<K> a() {
            return new ForwardingTypeCollector<K>(this) {
                /* access modifiers changed from: package-private */
                public ImmutableList<K> a(Iterable<? extends K> iterable) {
                    ImmutableList.Builder builder = ImmutableList.builder();
                    for (Object next : iterable) {
                        if (!b(next).isInterface()) {
                            builder.add((Object) next);
                        }
                    }
                    return super.a(builder.build());
                }

                /* access modifiers changed from: package-private */
                public Iterable<? extends K> c(K k) {
                    return ImmutableSet.of();
                }
            };
        }

        /* access modifiers changed from: package-private */
        public abstract Class<?> b(K k);

        /* access modifiers changed from: package-private */
        public abstract Iterable<? extends K> c(K k);

        /* access modifiers changed from: package-private */
        @Nullable
        public abstract K d(K k);
    }

    private enum TypeFilter implements Predicate<TypeToken<?>> {
        IGNORE_TYPE_VARIABLE_OR_WILDCARD {
            public boolean apply(TypeToken<?> typeToken) {
                return !(typeToken.runtimeType instanceof TypeVariable) && !(typeToken.runtimeType instanceof WildcardType);
            }
        },
        INTERFACE_ONLY {
            public boolean apply(TypeToken<?> typeToken) {
                return typeToken.getRawType().isInterface();
            }
        }
    }

    public class TypeSet extends ForwardingSet<TypeToken<? super T>> implements Serializable {
        private static final long serialVersionUID = 0;
        private transient ImmutableSet<TypeToken<? super T>> types;

        TypeSet() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.types;
            if (immutableSet != null) {
                return immutableSet;
            }
            ImmutableSet<TypeToken<?>> set = FluentIterable.from(TypeCollector.a.a(TypeToken.this)).filter(TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
            this.types = set;
            return set;
        }

        public TypeToken<T>.TypeSet classes() {
            return new ClassSet();
        }

        public TypeToken<T>.TypeSet interfaces() {
            return new InterfaceSet(this);
        }

        public Set<Class<? super T>> rawTypes() {
            return ImmutableSet.copyOf(TypeCollector.b.a(TypeToken.this.getRawTypes()));
        }
    }

    protected TypeToken() {
        this.runtimeType = a();
        Preconditions.checkState(!(this.runtimeType instanceof TypeVariable), "Cannot construct a TypeToken for a type variable.\nYou probably meant to call new TypeToken<%s>(getClass()) that can resolve the type variable for you.\nIf you do need to create a TypeToken of a type variable, please use TypeToken.of() instead.", (Object) this.runtimeType);
    }

    private TypeToken(Type type) {
        this.runtimeType = (Type) Preconditions.checkNotNull(type);
    }

    @VisibleForTesting
    static <T> TypeToken<? extends T> a(Class<T> cls) {
        Type a;
        if (cls.isArray()) {
            a = Types.a(a(cls.getComponentType()).runtimeType);
        } else {
            TypeVariable[] typeParameters = cls.getTypeParameters();
            Type type = (!cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) ? null : a(cls.getEnclosingClass()).runtimeType;
            if (typeParameters.length <= 0 && (type == null || type == cls.getEnclosingClass())) {
                return of(cls);
            }
            a = Types.a(type, (Class<?>) cls, (Type[]) typeParameters);
        }
        return of(a);
    }

    private static Bounds any(Type[] typeArr) {
        return new Bounds(typeArr, true);
    }

    @Nullable
    private TypeToken<? super T> boundAsSuperclass(Type type) {
        TypeToken<?> of = of(type);
        if (of.getRawType().isInterface()) {
            return null;
        }
        return of;
    }

    private ImmutableList<TypeToken<? super T>> boundsAsInterfaces(Type[] typeArr) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type of : typeArr) {
            TypeToken<?> of2 = of(of);
            if (of2.getRawType().isInterface()) {
                builder.add((Object) of2);
            }
        }
        return builder.build();
    }

    private static Bounds every(Type[] typeArr) {
        return new Bounds(typeArr, false);
    }

    private TypeToken<? extends T> getArraySubtype(Class<?> cls) {
        return of(newArrayClassOrGenericArrayType(getComponentType().getSubtype(cls.getComponentType()).runtimeType));
    }

    private TypeToken<? super T> getArraySupertype(Class<? super T> cls) {
        return of(newArrayClassOrGenericArrayType(((TypeToken) Preconditions.checkNotNull(getComponentType(), "%s isn't a super type of %s", (Object) cls, (Object) this)).getSupertype(cls.getComponentType()).runtimeType));
    }

    @Nullable
    private Type getOwnerTypeIfPresent() {
        Type type = this.runtimeType;
        if (type instanceof ParameterizedType) {
            return ((ParameterizedType) type).getOwnerType();
        }
        if (type instanceof Class) {
            return ((Class) type).getEnclosingClass();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public ImmutableSet<Class<? super T>> getRawTypes() {
        final ImmutableSet.Builder builder = ImmutableSet.builder();
        new TypeVisitor() {
            /* access modifiers changed from: package-private */
            public void a(Class<?> cls) {
                builder.add((Object) cls);
            }

            /* access modifiers changed from: package-private */
            public void a(GenericArrayType genericArrayType) {
                builder.add((Object) Types.a((Class<?>) TypeToken.of(genericArrayType.getGenericComponentType()).getRawType()));
            }

            /* access modifiers changed from: package-private */
            public void a(ParameterizedType parameterizedType) {
                builder.add((Object) (Class) parameterizedType.getRawType());
            }

            /* access modifiers changed from: package-private */
            public void a(TypeVariable<?> typeVariable) {
                visit(typeVariable.getBounds());
            }

            /* access modifiers changed from: package-private */
            public void a(WildcardType wildcardType) {
                visit(wildcardType.getUpperBounds());
            }
        }.visit(this.runtimeType);
        return builder.build();
    }

    private TypeToken<? extends T> getSubtypeFromLowerBounds(Class<?> cls, Type[] typeArr) {
        if (typeArr.length > 0) {
            return of(typeArr[0]).getSubtype(cls);
        }
        throw new IllegalArgumentException(cls + " isn't a subclass of " + this);
    }

    private TypeToken<? super T> getSupertypeFromUpperBounds(Class<? super T> cls, Type[] typeArr) {
        for (Type of : typeArr) {
            TypeToken<?> of2 = of(of);
            if (of2.isSubtypeOf((Type) cls)) {
                return of2.getSupertype(cls);
            }
        }
        throw new IllegalArgumentException(cls + " isn't a super type of " + this);
    }

    private boolean is(Type type) {
        if (this.runtimeType.equals(type)) {
            return true;
        }
        if (!(type instanceof WildcardType)) {
            return false;
        }
        WildcardType wildcardType = (WildcardType) type;
        return every(wildcardType.getUpperBounds()).b(this.runtimeType) && every(wildcardType.getLowerBounds()).a(this.runtimeType);
    }

    private boolean isOwnedBySubtypeOf(Type type) {
        Iterator it = getTypes().iterator();
        while (it.hasNext()) {
            Type ownerTypeIfPresent = ((TypeToken) it.next()).getOwnerTypeIfPresent();
            if (ownerTypeIfPresent != null && of(ownerTypeIfPresent).isSubtypeOf(type)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSubtypeOfArrayType(GenericArrayType genericArrayType) {
        TypeToken<?> of;
        Type type = this.runtimeType;
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (!cls.isArray()) {
                return false;
            }
            of = of(cls.getComponentType());
        } else if (!(type instanceof GenericArrayType)) {
            return false;
        } else {
            of = of(((GenericArrayType) type).getGenericComponentType());
        }
        return of.isSubtypeOf(genericArrayType.getGenericComponentType());
    }

    private boolean isSubtypeOfParameterizedType(ParameterizedType parameterizedType) {
        Class<? super Object> rawType = of((Type) parameterizedType).getRawType();
        if (!someRawTypeIsSubclassOf(rawType)) {
            return false;
        }
        TypeVariable[] typeParameters = rawType.getTypeParameters();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (int i = 0; i < typeParameters.length; i++) {
            if (!resolveType(typeParameters[i]).is(actualTypeArguments[i])) {
                return false;
            }
        }
        return Modifier.isStatic(((Class) parameterizedType.getRawType()).getModifiers()) || parameterizedType.getOwnerType() == null || isOwnedBySubtypeOf(parameterizedType.getOwnerType());
    }

    private boolean isSupertypeOfArray(GenericArrayType genericArrayType) {
        Type type = this.runtimeType;
        if (type instanceof Class) {
            Class cls = (Class) type;
            return !cls.isArray() ? cls.isAssignableFrom(Object[].class) : of(genericArrayType.getGenericComponentType()).isSubtypeOf((Type) cls.getComponentType());
        } else if (type instanceof GenericArrayType) {
            return of(genericArrayType.getGenericComponentType()).isSubtypeOf(((GenericArrayType) this.runtimeType).getGenericComponentType());
        } else {
            return false;
        }
    }

    private boolean isWrapper() {
        return Primitives.allWrapperTypes().contains(this.runtimeType);
    }

    private static Type newArrayClassOrGenericArrayType(Type type) {
        return Types.JavaVersion.JAVA7.a(type);
    }

    public static <T> TypeToken<T> of(Class<T> cls) {
        return new SimpleTypeToken(cls);
    }

    public static TypeToken<?> of(Type type) {
        return new SimpleTypeToken(type);
    }

    /* access modifiers changed from: private */
    public Type[] resolveInPlace(Type[] typeArr) {
        for (int i = 0; i < typeArr.length; i++) {
            typeArr[i] = resolveType(typeArr[i]).getType();
        }
        return typeArr;
    }

    private TypeToken<?> resolveSupertype(Type type) {
        TypeToken<?> resolveType = resolveType(type);
        resolveType.typeResolver = this.typeResolver;
        return resolveType;
    }

    private Type resolveTypeArgsForSubclass(Class<?> cls) {
        if ((this.runtimeType instanceof Class) && (cls.getTypeParameters().length == 0 || getRawType().getTypeParameters().length != 0)) {
            return cls;
        }
        TypeToken<? extends Object> a = a(cls);
        return new TypeResolver().where(a.getSupertype(getRawType()).runtimeType, this.runtimeType).resolveType(a.runtimeType);
    }

    private boolean someRawTypeIsSubclassOf(Class<?> cls) {
        Iterator it = getRawTypes().iterator();
        while (it.hasNext()) {
            if (cls.isAssignableFrom((Class) it.next())) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final TypeToken<? super T> b() {
        Type type;
        Type type2 = this.runtimeType;
        if (type2 instanceof TypeVariable) {
            type = ((TypeVariable) type2).getBounds()[0];
        } else if (type2 instanceof WildcardType) {
            type = ((WildcardType) type2).getUpperBounds()[0];
        } else {
            Type genericSuperclass = getRawType().getGenericSuperclass();
            if (genericSuperclass == null) {
                return null;
            }
            return resolveSupertype(genericSuperclass);
        }
        return boundAsSuperclass(type);
    }

    /* access modifiers changed from: package-private */
    public final ImmutableList<TypeToken<? super T>> c() {
        Type[] upperBounds;
        Type type = this.runtimeType;
        if (type instanceof TypeVariable) {
            upperBounds = ((TypeVariable) type).getBounds();
        } else if (type instanceof WildcardType) {
            upperBounds = ((WildcardType) type).getUpperBounds();
        } else {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (Type resolveSupertype : getRawType().getGenericInterfaces()) {
                builder.add((Object) resolveSupertype(resolveSupertype));
            }
            return builder.build();
        }
        return boundsAsInterfaces(upperBounds);
    }

    public final Invokable<T, T> constructor(Constructor<?> constructor) {
        Preconditions.checkArgument(constructor.getDeclaringClass() == getRawType(), "%s not declared by %s", (Object) constructor, (Object) getRawType());
        return new Invokable.ConstructorInvokable<T>(constructor) {
            /* access modifiers changed from: package-private */
            public Type[] a() {
                return TypeToken.this.resolveInPlace(super.a());
            }

            /* access modifiers changed from: package-private */
            public Type[] b() {
                return TypeToken.this.resolveInPlace(super.b());
            }

            /* access modifiers changed from: package-private */
            public Type d() {
                return TypeToken.this.resolveType(super.d()).getType();
            }

            public TypeToken<T> getOwnerType() {
                return TypeToken.this;
            }

            public String toString() {
                return getOwnerType() + "(" + Joiner.on(", ").join((Object[]) a()) + ")";
            }
        };
    }

    /* access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    public final TypeToken<T> d() {
        new TypeVisitor() {
            /* access modifiers changed from: package-private */
            public void a(GenericArrayType genericArrayType) {
                visit(genericArrayType.getGenericComponentType());
            }

            /* access modifiers changed from: package-private */
            public void a(ParameterizedType parameterizedType) {
                visit(parameterizedType.getActualTypeArguments());
                visit(parameterizedType.getOwnerType());
            }

            /* access modifiers changed from: package-private */
            public void a(TypeVariable<?> typeVariable) {
                throw new IllegalArgumentException(TypeToken.this.runtimeType + "contains a type variable and is not safe for the operation");
            }

            /* access modifiers changed from: package-private */
            public void a(WildcardType wildcardType) {
                visit(wildcardType.getLowerBounds());
                visit(wildcardType.getUpperBounds());
            }
        }.visit(this.runtimeType);
        return this;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof TypeToken) {
            return this.runtimeType.equals(((TypeToken) obj).runtimeType);
        }
        return false;
    }

    @Nullable
    public final TypeToken<?> getComponentType() {
        Type e = Types.e(this.runtimeType);
        if (e == null) {
            return null;
        }
        return of(e);
    }

    public final Class<? super T> getRawType() {
        return (Class) getRawTypes().iterator().next();
    }

    public final TypeToken<? extends T> getSubtype(Class<?> cls) {
        Preconditions.checkArgument(!(this.runtimeType instanceof TypeVariable), "Cannot get subtype of type variable <%s>", (Object) this);
        Type type = this.runtimeType;
        if (type instanceof WildcardType) {
            return getSubtypeFromLowerBounds(cls, ((WildcardType) type).getLowerBounds());
        }
        if (isArray()) {
            return getArraySubtype(cls);
        }
        Preconditions.checkArgument(getRawType().isAssignableFrom(cls), "%s isn't a subclass of %s", (Object) cls, (Object) this);
        return of(resolveTypeArgsForSubclass(cls));
    }

    public final TypeToken<? super T> getSupertype(Class<? super T> cls) {
        Preconditions.checkArgument(someRawTypeIsSubclassOf(cls), "%s is not a super class of %s", (Object) cls, (Object) this);
        Type type = this.runtimeType;
        return type instanceof TypeVariable ? getSupertypeFromUpperBounds(cls, ((TypeVariable) type).getBounds()) : type instanceof WildcardType ? getSupertypeFromUpperBounds(cls, ((WildcardType) type).getUpperBounds()) : cls.isArray() ? getArraySupertype(cls) : resolveSupertype(a(cls).runtimeType);
    }

    public final Type getType() {
        return this.runtimeType;
    }

    public final TypeToken<T>.TypeSet getTypes() {
        return new TypeSet();
    }

    public int hashCode() {
        return this.runtimeType.hashCode();
    }

    public final boolean isArray() {
        return getComponentType() != null;
    }

    public final boolean isPrimitive() {
        Type type = this.runtimeType;
        return (type instanceof Class) && ((Class) type).isPrimitive();
    }

    public final boolean isSubtypeOf(TypeToken<?> typeToken) {
        return isSubtypeOf(typeToken.getType());
    }

    public final boolean isSubtypeOf(Type type) {
        Preconditions.checkNotNull(type);
        if (type instanceof WildcardType) {
            return any(((WildcardType) type).getLowerBounds()).b(this.runtimeType);
        }
        Type type2 = this.runtimeType;
        if (type2 instanceof WildcardType) {
            return any(((WildcardType) type2).getUpperBounds()).a(type);
        }
        if (type2 instanceof TypeVariable) {
            return type2.equals(type) || any(((TypeVariable) this.runtimeType).getBounds()).a(type);
        }
        if (type2 instanceof GenericArrayType) {
            return of(type).isSupertypeOfArray((GenericArrayType) this.runtimeType);
        }
        if (type instanceof Class) {
            return someRawTypeIsSubclassOf((Class) type);
        }
        if (type instanceof ParameterizedType) {
            return isSubtypeOfParameterizedType((ParameterizedType) type);
        }
        if (type instanceof GenericArrayType) {
            return isSubtypeOfArrayType((GenericArrayType) type);
        }
        return false;
    }

    public final boolean isSupertypeOf(TypeToken<?> typeToken) {
        return typeToken.isSubtypeOf(getType());
    }

    public final boolean isSupertypeOf(Type type) {
        return of(type).isSubtypeOf(getType());
    }

    public final Invokable<T, Object> method(Method method) {
        Preconditions.checkArgument(someRawTypeIsSubclassOf(method.getDeclaringClass()), "%s not declared by %s", (Object) method, (Object) this);
        return new Invokable.MethodInvokable<T>(method) {
            /* access modifiers changed from: package-private */
            public Type[] a() {
                return TypeToken.this.resolveInPlace(super.a());
            }

            /* access modifiers changed from: package-private */
            public Type[] b() {
                return TypeToken.this.resolveInPlace(super.b());
            }

            /* access modifiers changed from: package-private */
            public Type d() {
                return TypeToken.this.resolveType(super.d()).getType();
            }

            public TypeToken<T> getOwnerType() {
                return TypeToken.this;
            }

            public String toString() {
                return getOwnerType() + "." + super.toString();
            }
        };
    }

    public final TypeToken<?> resolveType(Type type) {
        Preconditions.checkNotNull(type);
        TypeResolver typeResolver2 = this.typeResolver;
        if (typeResolver2 == null) {
            typeResolver2 = TypeResolver.a(this.runtimeType);
            this.typeResolver = typeResolver2;
        }
        return of(typeResolver2.resolveType(type));
    }

    public String toString() {
        return Types.d(this.runtimeType);
    }

    public final TypeToken<T> unwrap() {
        return isWrapper() ? of(Primitives.unwrap((Class) this.runtimeType)) : this;
    }

    public final <X> TypeToken<T> where(TypeParameter<X> typeParameter, TypeToken<X> typeToken) {
        return new SimpleTypeToken(new TypeResolver().a((Map<TypeResolver.TypeVariableKey, ? extends Type>) ImmutableMap.of(new TypeResolver.TypeVariableKey(typeParameter.a), typeToken.runtimeType)).resolveType(this.runtimeType));
    }

    public final <X> TypeToken<T> where(TypeParameter<X> typeParameter, Class<X> cls) {
        return where(typeParameter, of(cls));
    }

    public final TypeToken<T> wrap() {
        return isPrimitive() ? of(Primitives.wrap((Class) this.runtimeType)) : this;
    }

    /* access modifiers changed from: protected */
    public Object writeReplace() {
        return of(new TypeResolver().resolveType(this.runtimeType));
    }
}
