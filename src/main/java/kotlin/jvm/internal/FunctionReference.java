package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;

public class FunctionReference extends CallableReference implements FunctionBase, KFunction {
    private final int arity;

    public FunctionReference(int i) {
        this.arity = i;
    }

    @SinceKotlin(version = "1.1")
    public FunctionReference(int i, Object obj) {
        super(obj);
        this.arity = i;
    }

    /* access modifiers changed from: protected */
    @SinceKotlin(version = "1.1")
    public KCallable a() {
        return Reflection.function(this);
    }

    /* access modifiers changed from: protected */
    @SinceKotlin(version = "1.1")
    /* renamed from: c */
    public KFunction b() {
        return (KFunction) super.b();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FunctionReference) {
            FunctionReference functionReference = (FunctionReference) obj;
            if (getOwner() != null ? getOwner().equals(functionReference.getOwner()) : functionReference.getOwner() == null) {
                return getName().equals(functionReference.getName()) && getSignature().equals(functionReference.getSignature()) && Intrinsics.areEqual(getBoundReceiver(), functionReference.getBoundReceiver());
            }
        } else if (obj instanceof KFunction) {
            return obj.equals(compute());
        } else {
            return false;
        }
    }

    public int getArity() {
        return this.arity;
    }

    public int hashCode() {
        return (((getOwner() == null ? 0 : getOwner().hashCode() * 31) + getName().hashCode()) * 31) + getSignature().hashCode();
    }

    @SinceKotlin(version = "1.1")
    public boolean isExternal() {
        return b().isExternal();
    }

    @SinceKotlin(version = "1.1")
    public boolean isInfix() {
        return b().isInfix();
    }

    @SinceKotlin(version = "1.1")
    public boolean isInline() {
        return b().isInline();
    }

    @SinceKotlin(version = "1.1")
    public boolean isOperator() {
        return b().isOperator();
    }

    @SinceKotlin(version = "1.1")
    public boolean isSuspend() {
        return b().isSuspend();
    }

    public String toString() {
        KCallable compute = compute();
        if (compute != this) {
            return compute.toString();
        }
        if ("<init>".equals(getName())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + getName() + " (Kotlin reflection is not available)";
    }
}
