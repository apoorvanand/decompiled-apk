package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

@GwtCompatible(emulated = true, serializable = true)
abstract class ImmutableAsList<E> extends ImmutableList<E> {

    @GwtIncompatible
    static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableCollection<?> a;

        SerializedForm(ImmutableCollection<?> immutableCollection) {
            this.a = immutableCollection;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.a.asList();
        }
    }

    ImmutableAsList() {
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return b().a();
    }

    /* access modifiers changed from: package-private */
    public abstract ImmutableCollection<E> b();

    public boolean contains(Object obj) {
        return b().contains(obj);
    }

    public boolean isEmpty() {
        return b().isEmpty();
    }

    public int size() {
        return b().size();
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(b());
    }
}
