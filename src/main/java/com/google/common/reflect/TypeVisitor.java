package com.google.common.reflect;

import com.google.common.collect.Sets;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Set;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
abstract class TypeVisitor {
    private final Set<Type> visited = Sets.newHashSet();

    TypeVisitor() {
    }

    /* access modifiers changed from: package-private */
    public void a(Class<?> cls) {
    }

    /* access modifiers changed from: package-private */
    public void a(GenericArrayType genericArrayType) {
    }

    /* access modifiers changed from: package-private */
    public void a(ParameterizedType parameterizedType) {
    }

    /* access modifiers changed from: package-private */
    public void a(TypeVariable<?> typeVariable) {
    }

    /* access modifiers changed from: package-private */
    public void a(WildcardType wildcardType) {
    }

    public final void visit(Type... typeArr) {
        for (TypeVariable typeVariable : typeArr) {
            if (typeVariable != null && this.visited.add(typeVariable)) {
                try {
                    if (typeVariable instanceof TypeVariable) {
                        a((TypeVariable<?>) typeVariable);
                    } else if (typeVariable instanceof WildcardType) {
                        a((WildcardType) typeVariable);
                    } else if (typeVariable instanceof ParameterizedType) {
                        a((ParameterizedType) typeVariable);
                    } else if (typeVariable instanceof Class) {
                        a((Class<?>) typeVariable);
                    } else if (typeVariable instanceof GenericArrayType) {
                        a((GenericArrayType) typeVariable);
                    } else {
                        throw new AssertionError("Unknown type: " + typeVariable);
                    }
                } catch (Throwable th) {
                    this.visited.remove(typeVariable);
                    throw th;
                }
            }
        }
    }
}
