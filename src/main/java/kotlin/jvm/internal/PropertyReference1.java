package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty1;

public abstract class PropertyReference1 extends PropertyReference implements KProperty1 {
    public PropertyReference1() {
    }

    @SinceKotlin(version = "1.1")
    public PropertyReference1(Object obj) {
        super(obj);
    }

    /* access modifiers changed from: protected */
    public KCallable a() {
        return Reflection.property1(this);
    }

    @SinceKotlin(version = "1.1")
    public Object getDelegate(Object obj) {
        return ((KProperty1) b()).getDelegate(obj);
    }

    public KProperty1.Getter getGetter() {
        return ((KProperty1) b()).getGetter();
    }

    public Object invoke(Object obj) {
        return get(obj);
    }
}
