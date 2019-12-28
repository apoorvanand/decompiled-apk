package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import javax.annotation.Nullable;

@Beta
public abstract class Invokable<T, R> extends Element implements GenericDeclaration {

    static class ConstructorInvokable<T> extends Invokable<T, T> {
        final Constructor<?> a;

        ConstructorInvokable(Constructor<?> constructor) {
            super(constructor);
            this.a = constructor;
        }

        private boolean mayNeedHiddenThis() {
            Class<?> declaringClass = this.a.getDeclaringClass();
            if (declaringClass.getEnclosingConstructor() != null) {
                return true;
            }
            Method enclosingMethod = declaringClass.getEnclosingMethod();
            return enclosingMethod != null ? !Modifier.isStatic(enclosingMethod.getModifiers()) : declaringClass.getEnclosingClass() != null && !Modifier.isStatic(declaringClass.getModifiers());
        }

        /* access modifiers changed from: package-private */
        public final Object a(@Nullable Object obj, Object[] objArr) {
            try {
                return this.a.newInstance(objArr);
            } catch (InstantiationException e) {
                throw new RuntimeException(this.a + " failed.", e);
            }
        }

        /* access modifiers changed from: package-private */
        public Type[] a() {
            Type[] genericParameterTypes = this.a.getGenericParameterTypes();
            if (genericParameterTypes.length <= 0 || !mayNeedHiddenThis()) {
                return genericParameterTypes;
            }
            Class<?>[] parameterTypes = this.a.getParameterTypes();
            return (genericParameterTypes.length == parameterTypes.length && parameterTypes[0] == getDeclaringClass().getEnclosingClass()) ? (Type[]) Arrays.copyOfRange(genericParameterTypes, 1, genericParameterTypes.length) : genericParameterTypes;
        }

        /* access modifiers changed from: package-private */
        public Type[] b() {
            return this.a.getGenericExceptionTypes();
        }

        /* access modifiers changed from: package-private */
        public final Annotation[][] c() {
            return this.a.getParameterAnnotations();
        }

        /* access modifiers changed from: package-private */
        public Type d() {
            Class declaringClass = getDeclaringClass();
            TypeVariable[] typeParameters = declaringClass.getTypeParameters();
            return typeParameters.length > 0 ? Types.a((Class<?>) declaringClass, (Type[]) typeParameters) : declaringClass;
        }

        public final TypeVariable<?>[] getTypeParameters() {
            TypeVariable[] typeParameters = getDeclaringClass().getTypeParameters();
            TypeVariable[] typeParameters2 = this.a.getTypeParameters();
            TypeVariable<?>[] typeVariableArr = new TypeVariable[(typeParameters.length + typeParameters2.length)];
            System.arraycopy(typeParameters, 0, typeVariableArr, 0, typeParameters.length);
            System.arraycopy(typeParameters2, 0, typeVariableArr, typeParameters.length, typeParameters2.length);
            return typeVariableArr;
        }

        public final boolean isOverridable() {
            return false;
        }

        public final boolean isVarArgs() {
            return this.a.isVarArgs();
        }
    }

    static class MethodInvokable<T> extends Invokable<T, Object> {
        final Method a;

        MethodInvokable(Method method) {
            super(method);
            this.a = method;
        }

        /* access modifiers changed from: package-private */
        public final Object a(@Nullable Object obj, Object[] objArr) {
            return this.a.invoke(obj, objArr);
        }

        /* access modifiers changed from: package-private */
        public Type[] a() {
            return this.a.getGenericParameterTypes();
        }

        /* access modifiers changed from: package-private */
        public Type[] b() {
            return this.a.getGenericExceptionTypes();
        }

        /* access modifiers changed from: package-private */
        public final Annotation[][] c() {
            return this.a.getParameterAnnotations();
        }

        /* access modifiers changed from: package-private */
        public Type d() {
            return this.a.getGenericReturnType();
        }

        public final TypeVariable<?>[] getTypeParameters() {
            return this.a.getTypeParameters();
        }

        public final boolean isOverridable() {
            return !isFinal() && !isPrivate() && !isStatic() && !Modifier.isFinal(getDeclaringClass().getModifiers());
        }

        public final boolean isVarArgs() {
            return this.a.isVarArgs();
        }
    }

    <M extends AccessibleObject & Member> Invokable(M m) {
        super(m);
    }

    public static <T> Invokable<T, T> from(Constructor<T> constructor) {
        return new ConstructorInvokable(constructor);
    }

    public static Invokable<?, Object> from(Method method) {
        return new MethodInvokable(method);
    }

    /* access modifiers changed from: package-private */
    public abstract Object a(@Nullable Object obj, Object[] objArr);

    /* access modifiers changed from: package-private */
    public abstract Type[] a();

    /* access modifiers changed from: package-private */
    public abstract Type[] b();

    /* access modifiers changed from: package-private */
    public abstract Annotation[][] c();

    /* access modifiers changed from: package-private */
    public abstract Type d();

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final Class<? super T> getDeclaringClass() {
        return super.getDeclaringClass();
    }

    public final ImmutableList<TypeToken<? extends Throwable>> getExceptionTypes() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type of : b()) {
            builder.add((Object) TypeToken.of(of));
        }
        return builder.build();
    }

    public TypeToken<T> getOwnerType() {
        return TypeToken.of(getDeclaringClass());
    }

    public final ImmutableList<Parameter> getParameters() {
        Type[] a = a();
        Annotation[][] c = c();
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < a.length; i++) {
            builder.add((Object) new Parameter(this, i, TypeToken.of(a[i]), c[i]));
        }
        return builder.build();
    }

    public final TypeToken<? extends R> getReturnType() {
        return TypeToken.of(d());
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @CanIgnoreReturnValue
    public final R invoke(@Nullable T t, Object... objArr) {
        return a(t, (Object[]) Preconditions.checkNotNull(objArr));
    }

    public abstract boolean isOverridable();

    public abstract boolean isVarArgs();

    public final <R1 extends R> Invokable<T, R1> returning(TypeToken<R1> typeToken) {
        if (typeToken.isSupertypeOf((TypeToken<?>) getReturnType())) {
            return this;
        }
        throw new IllegalArgumentException("Invokable is known to return " + getReturnType() + ", not " + typeToken);
    }

    public final <R1 extends R> Invokable<T, R1> returning(Class<R1> cls) {
        return returning(TypeToken.of(cls));
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
