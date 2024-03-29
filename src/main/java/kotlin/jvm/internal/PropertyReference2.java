package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty2;

public abstract class PropertyReference2 extends PropertyReference implements KProperty2 {
    /* access modifiers changed from: protected */
    public KCallable a() {
        return Reflection.property2(this);
    }

    @SinceKotlin(version = "1.1")
    public Object getDelegate(Object obj, Object obj2) {
        return ((KProperty2) b()).getDelegate(obj, obj2);
    }

    public KProperty2.Getter getGetter() {
        return ((KProperty2) b()).getGetter();
    }

    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }
}
