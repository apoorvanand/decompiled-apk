package kotlin.jvm.internal;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import kotlin.SinceKotlin;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.reflect.KCallable;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;

public abstract class CallableReference implements Serializable, KCallable {
    @SinceKotlin(version = "1.1")
    public static final Object NO_RECEIVER = NoReceiver.INSTANCE;
    @SinceKotlin(version = "1.1")
    protected final Object a;
    private transient KCallable reflected;

    @SinceKotlin(version = "1.2")
    private static class NoReceiver implements Serializable {
        /* access modifiers changed from: private */
        public static final NoReceiver INSTANCE = new NoReceiver();

        private NoReceiver() {
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    public CallableReference() {
        this(NO_RECEIVER);
    }

    @SinceKotlin(version = "1.1")
    protected CallableReference(Object obj) {
        this.a = obj;
    }

    /* access modifiers changed from: protected */
    public abstract KCallable a();

    /* access modifiers changed from: protected */
    @SinceKotlin(version = "1.1")
    public KCallable b() {
        KCallable compute = compute();
        if (compute != this) {
            return compute;
        }
        throw new KotlinReflectionNotSupportedError();
    }

    public Object call(Object... objArr) {
        return b().call(objArr);
    }

    public Object callBy(Map map) {
        return b().callBy(map);
    }

    @SinceKotlin(version = "1.1")
    public KCallable compute() {
        KCallable kCallable = this.reflected;
        if (kCallable != null) {
            return kCallable;
        }
        KCallable a2 = a();
        this.reflected = a2;
        return a2;
    }

    public List<Annotation> getAnnotations() {
        return b().getAnnotations();
    }

    @SinceKotlin(version = "1.1")
    public Object getBoundReceiver() {
        return this.a;
    }

    public String getName() {
        throw new AbstractMethodError();
    }

    public KDeclarationContainer getOwner() {
        throw new AbstractMethodError();
    }

    public List<KParameter> getParameters() {
        return b().getParameters();
    }

    public KType getReturnType() {
        return b().getReturnType();
    }

    public String getSignature() {
        throw new AbstractMethodError();
    }

    @SinceKotlin(version = "1.1")
    public List<KTypeParameter> getTypeParameters() {
        return b().getTypeParameters();
    }

    @SinceKotlin(version = "1.1")
    public KVisibility getVisibility() {
        return b().getVisibility();
    }

    @SinceKotlin(version = "1.1")
    public boolean isAbstract() {
        return b().isAbstract();
    }

    @SinceKotlin(version = "1.1")
    public boolean isFinal() {
        return b().isFinal();
    }

    @SinceKotlin(version = "1.1")
    public boolean isOpen() {
        return b().isOpen();
    }

    @SinceKotlin(version = "1.3")
    public boolean isSuspend() {
        return b().isSuspend();
    }
}
