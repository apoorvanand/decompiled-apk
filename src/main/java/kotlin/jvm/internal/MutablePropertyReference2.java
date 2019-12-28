package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty2;

public abstract class MutablePropertyReference2 extends MutablePropertyReference implements KMutableProperty2 {
    /* access modifiers changed from: protected */
    public KCallable a() {
        return Reflection.mutableProperty2(this);
    }

    @SinceKotlin(version = "1.1")
    public Object getDelegate(Object obj, Object obj2) {
        return ((KMutableProperty2) b()).getDelegate(obj, obj2);
    }

    public KProperty2.Getter getGetter() {
        return ((KMutableProperty2) b()).getGetter();
    }

    public KMutableProperty2.Setter getSetter() {
        return ((KMutableProperty2) b()).getSetter();
    }

    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }
}
