package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

@GwtCompatible
abstract class MultitransformedIterator<F, T> implements Iterator<T> {
    final Iterator<? extends F> a;
    private Iterator<? extends T> current = Iterators.a();
    private Iterator<? extends T> removeFrom;

    MultitransformedIterator(Iterator<? extends F> it) {
        this.a = (Iterator) Preconditions.checkNotNull(it);
    }

    /* access modifiers changed from: package-private */
    public abstract Iterator<? extends T> a(F f);

    public boolean hasNext() {
        Preconditions.checkNotNull(this.current);
        if (this.current.hasNext()) {
            return true;
        }
        while (this.a.hasNext()) {
            Iterator<? extends T> a2 = a(this.a.next());
            this.current = a2;
            Preconditions.checkNotNull(a2);
            if (this.current.hasNext()) {
                return true;
            }
        }
        return false;
    }

    public T next() {
        if (hasNext()) {
            Iterator<? extends T> it = this.current;
            this.removeFrom = it;
            return it.next();
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        CollectPreconditions.a(this.removeFrom != null);
        this.removeFrom.remove();
        this.removeFrom = null;
    }
}
