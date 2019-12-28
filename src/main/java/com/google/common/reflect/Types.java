package com.google.common.reflect;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.UnmodifiableIterator;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessControlException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import kotlin.text.Typography;

final class Types {
    /* access modifiers changed from: private */
    public static final Joiner COMMA_JOINER = Joiner.on(", ").useForNull("null");
    /* access modifiers changed from: private */
    public static final Function<Type, String> TYPE_NAME = new Function<Type, String>() {
        public String apply(Type type) {
            return JavaVersion.a.c(type);
        }
    };

    private enum ClassOwnership {
        OWNED_BY_ENCLOSING_CLASS {
            /* access modifiers changed from: package-private */
            @Nullable
            public Class<?> a(Class<?> cls) {
                return cls.getEnclosingClass();
            }
        },
        LOCAL_CLASS_HAS_NO_OWNER {
            /* access modifiers changed from: package-private */
            @Nullable
            public Class<?> a(Class<?> cls) {
                if (cls.isLocalClass()) {
                    return null;
                }
                return cls.getEnclosingClass();
            }
        };
        
        static final ClassOwnership a = null;

        static {
            a = detectJvmBehavior();
        }

        private static ClassOwnership detectJvmBehavior() {
            ParameterizedType parameterizedType = (ParameterizedType) new AnonymousClass1LocalClass<String>() {
            }.getClass().getGenericSuperclass();
            for (ClassOwnership classOwnership : values()) {
                if (classOwnership.a(AnonymousClass1LocalClass.class) == parameterizedType.getOwnerType()) {
                    return classOwnership;
                }
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public abstract Class<?> a(Class<?> cls);
    }

    private static final class GenericArrayTypeImpl implements Serializable, GenericArrayType {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        GenericArrayTypeImpl(Type type) {
            this.componentType = JavaVersion.a.b(type);
        }

        public boolean equals(Object obj) {
            if (obj instanceof GenericArrayType) {
                return Objects.equal(getGenericComponentType(), ((GenericArrayType) obj).getGenericComponentType());
            }
            return false;
        }

        public Type getGenericComponentType() {
            return this.componentType;
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return Types.d(this.componentType) + "[]";
        }
    }

    enum JavaVersion {
        JAVA6 {
            /* access modifiers changed from: package-private */
            public Type b(Type type) {
                Preconditions.checkNotNull(type);
                if (!(type instanceof Class)) {
                    return type;
                }
                Class cls = (Class) type;
                return cls.isArray() ? new GenericArrayTypeImpl(cls.getComponentType()) : type;
            }

            /* access modifiers changed from: package-private */
            /* renamed from: d */
            public GenericArrayType a(Type type) {
                return new GenericArrayTypeImpl(type);
            }
        },
        JAVA7 {
            /* access modifiers changed from: package-private */
            public Type a(Type type) {
                return type instanceof Class ? Types.a((Class<?>) (Class) type) : new GenericArrayTypeImpl(type);
            }

            /* access modifiers changed from: package-private */
            public Type b(Type type) {
                return (Type) Preconditions.checkNotNull(type);
            }
        },
        JAVA8 {
            /* access modifiers changed from: package-private */
            public Type a(Type type) {
                return JAVA7.a(type);
            }

            /* access modifiers changed from: package-private */
            public Type b(Type type) {
                return JAVA7.b(type);
            }

            /* access modifiers changed from: package-private */
            public String c(Type type) {
                try {
                    return (String) Type.class.getMethod("getTypeName", new Class[0]).invoke(type, new Object[0]);
                } catch (NoSuchMethodException unused) {
                    throw new AssertionError("Type.getTypeName should be available in Java 8");
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException(e2);
                }
            }
        };
        
        static final JavaVersion a = null;

        /* access modifiers changed from: package-private */
        public final ImmutableList<Type> a(Type[] typeArr) {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (Type b : typeArr) {
                builder.add((Object) b(b));
            }
            return builder.build();
        }

        /* access modifiers changed from: package-private */
        public abstract Type a(Type type);

        /* access modifiers changed from: package-private */
        public abstract Type b(Type type);

        /* access modifiers changed from: package-private */
        public String c(Type type) {
            return Types.d(type);
        }
    }

    static final class NativeTypeVariableEquals<X> {
        static final boolean a = (!NativeTypeVariableEquals.class.getTypeParameters()[0].equals(Types.a(NativeTypeVariableEquals.class, "X", new Type[0])));

        NativeTypeVariableEquals() {
        }
    }

    private static final class ParameterizedTypeImpl implements Serializable, ParameterizedType {
        private static final long serialVersionUID = 0;
        private final ImmutableList<Type> argumentsList;
        private final Type ownerType;
        private final Class<?> rawType;

        ParameterizedTypeImpl(@Nullable Type type, Class<?> cls, Type[] typeArr) {
            Preconditions.checkNotNull(cls);
            Preconditions.checkArgument(typeArr.length == cls.getTypeParameters().length);
            Types.disallowPrimitiveType(typeArr, "type parameter");
            this.ownerType = type;
            this.rawType = cls;
            this.argumentsList = JavaVersion.a.a(typeArr);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) obj;
            return getRawType().equals(parameterizedType.getRawType()) && Objects.equal(getOwnerType(), parameterizedType.getOwnerType()) && Arrays.equals(getActualTypeArguments(), parameterizedType.getActualTypeArguments());
        }

        public Type[] getActualTypeArguments() {
            return Types.toArray(this.argumentsList);
        }

        public Type getOwnerType() {
            return this.ownerType;
        }

        public Type getRawType() {
            return this.rawType;
        }

        public int hashCode() {
            Type type = this.ownerType;
            return ((type == null ? 0 : type.hashCode()) ^ this.argumentsList.hashCode()) ^ this.rawType.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.ownerType != null) {
                sb.append(JavaVersion.a.c(this.ownerType));
                sb.append('.');
            }
            sb.append(this.rawType.getName());
            sb.append(Typography.less);
            sb.append(Types.COMMA_JOINER.join((Iterable<?>) Iterables.transform(this.argumentsList, Types.TYPE_NAME)));
            sb.append(Typography.greater);
            return sb.toString();
        }
    }

    private static final class TypeVariableImpl<D extends GenericDeclaration> {
        private final ImmutableList<Type> bounds;
        private final D genericDeclaration;
        private final String name;

        TypeVariableImpl(D d, String str, Type[] typeArr) {
            Types.disallowPrimitiveType(typeArr, "bound for type variable");
            this.genericDeclaration = (GenericDeclaration) Preconditions.checkNotNull(d);
            this.name = (String) Preconditions.checkNotNull(str);
            this.bounds = ImmutableList.copyOf((E[]) typeArr);
        }

        public boolean equals(Object obj) {
            if (NativeTypeVariableEquals.a) {
                if (obj == null || !Proxy.isProxyClass(obj.getClass()) || !(Proxy.getInvocationHandler(obj) instanceof TypeVariableInvocationHandler)) {
                    return false;
                }
                TypeVariableImpl a = ((TypeVariableInvocationHandler) Proxy.getInvocationHandler(obj)).typeVariableImpl;
                return this.name.equals(a.getName()) && this.genericDeclaration.equals(a.getGenericDeclaration()) && this.bounds.equals(a.bounds);
            } else if (!(obj instanceof TypeVariable)) {
                return false;
            } else {
                TypeVariable typeVariable = (TypeVariable) obj;
                return this.name.equals(typeVariable.getName()) && this.genericDeclaration.equals(typeVariable.getGenericDeclaration());
            }
        }

        public Type[] getBounds() {
            return Types.toArray(this.bounds);
        }

        public D getGenericDeclaration() {
            return this.genericDeclaration;
        }

        public String getName() {
            return this.name;
        }

        public String getTypeName() {
            return this.name;
        }

        public int hashCode() {
            return this.genericDeclaration.hashCode() ^ this.name.hashCode();
        }

        public String toString() {
            return this.name;
        }
    }

    private static final class TypeVariableInvocationHandler implements InvocationHandler {
        private static final ImmutableMap<String, Method> typeVariableMethods;
        /* access modifiers changed from: private */
        public final TypeVariableImpl<?> typeVariableImpl;

        static {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (Method method : TypeVariableImpl.class.getMethods()) {
                if (method.getDeclaringClass().equals(TypeVariableImpl.class)) {
                    try {
                        method.setAccessible(true);
                    } catch (AccessControlException unused) {
                    }
                    builder.put(method.getName(), method);
                }
            }
            typeVariableMethods = builder.build();
        }

        TypeVariableInvocationHandler(TypeVariableImpl<?> typeVariableImpl2) {
            this.typeVariableImpl = typeVariableImpl2;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) {
            String name = method.getName();
            Method method2 = typeVariableMethods.get(name);
            if (method2 != null) {
                try {
                    return method2.invoke(this.typeVariableImpl, objArr);
                } catch (InvocationTargetException e) {
                    throw e.getCause();
                }
            } else {
                throw new UnsupportedOperationException(name);
            }
        }
    }

    static final class WildcardTypeImpl implements Serializable, WildcardType {
        private static final long serialVersionUID = 0;
        private final ImmutableList<Type> lowerBounds;
        private final ImmutableList<Type> upperBounds;

        WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            Types.disallowPrimitiveType(typeArr, "lower bound for wildcard");
            Types.disallowPrimitiveType(typeArr2, "upper bound for wildcard");
            this.lowerBounds = JavaVersion.a.a(typeArr);
            this.upperBounds = JavaVersion.a.a(typeArr2);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) obj;
            return this.lowerBounds.equals(Arrays.asList(wildcardType.getLowerBounds())) && this.upperBounds.equals(Arrays.asList(wildcardType.getUpperBounds()));
        }

        public Type[] getLowerBounds() {
            return Types.toArray(this.lowerBounds);
        }

        public Type[] getUpperBounds() {
            return Types.toArray(this.upperBounds);
        }

        public int hashCode() {
            return this.lowerBounds.hashCode() ^ this.upperBounds.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("?");
            UnmodifiableIterator<Type> it = this.lowerBounds.iterator();
            while (it.hasNext()) {
                sb.append(" super ");
                sb.append(JavaVersion.a.c(it.next()));
            }
            for (Type c : Types.filterUpperBounds(this.upperBounds)) {
                sb.append(" extends ");
                sb.append(JavaVersion.a.c(c));
            }
            return sb.toString();
        }
    }

    private Types() {
    }

    static Class<?> a(Class<?> cls) {
        return Array.newInstance(cls, 0).getClass();
    }

    static ParameterizedType a(Class<?> cls, Type... typeArr) {
        return new ParameterizedTypeImpl(ClassOwnership.a.a(cls), cls, typeArr);
    }

    static ParameterizedType a(@Nullable Type type, Class<?> cls, Type... typeArr) {
        if (type == null) {
            return a(cls, typeArr);
        }
        Preconditions.checkNotNull(typeArr);
        Preconditions.checkArgument(cls.getEnclosingClass() != null, "Owner type for unenclosed %s", (Object) cls);
        return new ParameterizedTypeImpl(type, cls, typeArr);
    }

    static Type a(Type type) {
        if (!(type instanceof WildcardType)) {
            return JavaVersion.a.a(type);
        }
        WildcardType wildcardType = (WildcardType) type;
        Type[] lowerBounds = wildcardType.getLowerBounds();
        boolean z = true;
        Preconditions.checkArgument(lowerBounds.length <= 1, "Wildcard cannot have more than one lower bounds.");
        if (lowerBounds.length == 1) {
            return c(a(lowerBounds[0]));
        }
        Type[] upperBounds = wildcardType.getUpperBounds();
        if (upperBounds.length != 1) {
            z = false;
        }
        Preconditions.checkArgument(z, "Wildcard should have only one upper bound.");
        return b(a(upperBounds[0]));
    }

    static <D extends GenericDeclaration> TypeVariable<D> a(D d, String str, Type... typeArr) {
        if (typeArr.length == 0) {
            typeArr = new Type[]{Object.class};
        }
        return newTypeVariableImpl(d, str, typeArr);
    }

    @VisibleForTesting
    static WildcardType b(Type type) {
        return new WildcardTypeImpl(new Type[0], new Type[]{type});
    }

    @VisibleForTesting
    static WildcardType c(Type type) {
        return new WildcardTypeImpl(new Type[]{type}, new Type[]{Object.class});
    }

    static String d(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    /* access modifiers changed from: private */
    public static void disallowPrimitiveType(Type[] typeArr, String str) {
        for (Class cls : typeArr) {
            if (cls instanceof Class) {
                Class cls2 = cls;
                Preconditions.checkArgument(!cls2.isPrimitive(), "Primitive type '%s' used as %s", (Object) cls2, (Object) str);
            }
        }
    }

    @Nullable
    static Type e(Type type) {
        Preconditions.checkNotNull(type);
        final AtomicReference atomicReference = new AtomicReference();
        new TypeVisitor() {
            /* access modifiers changed from: package-private */
            public void a(Class<?> cls) {
                atomicReference.set(cls.getComponentType());
            }

            /* access modifiers changed from: package-private */
            public void a(GenericArrayType genericArrayType) {
                atomicReference.set(genericArrayType.getGenericComponentType());
            }

            /* access modifiers changed from: package-private */
            public void a(TypeVariable<?> typeVariable) {
                atomicReference.set(Types.subtypeOfComponentType(typeVariable.getBounds()));
            }

            /* access modifiers changed from: package-private */
            public void a(WildcardType wildcardType) {
                atomicReference.set(Types.subtypeOfComponentType(wildcardType.getUpperBounds()));
            }
        }.visit(type);
        return (Type) atomicReference.get();
    }

    /* access modifiers changed from: private */
    public static Iterable<Type> filterUpperBounds(Iterable<Type> iterable) {
        return Iterables.filter(iterable, Predicates.not(Predicates.equalTo(Object.class)));
    }

    private static <D extends GenericDeclaration> TypeVariable<D> newTypeVariableImpl(D d, String str, Type[] typeArr) {
        return (TypeVariable) Reflection.newProxy(TypeVariable.class, new TypeVariableInvocationHandler(new TypeVariableImpl(d, str, typeArr)));
    }

    /* access modifiers changed from: private */
    @Nullable
    public static Type subtypeOfComponentType(Type[] typeArr) {
        for (Type e : typeArr) {
            Type e2 = e(e);
            if (e2 != null) {
                if (e2 instanceof Class) {
                    Class cls = (Class) e2;
                    if (cls.isPrimitive()) {
                        return cls;
                    }
                }
                return b(e2);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static Type[] toArray(Collection<Type> collection) {
        return (Type[]) collection.toArray(new Type[collection.size()]);
    }
}
