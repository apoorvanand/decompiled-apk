package androidx.appcompat.view.menu;

class BaseWrapper<T> {
    final T b;

    BaseWrapper(T t) {
        if (t != null) {
            this.b = t;
            return;
        }
        throw new IllegalArgumentException("Wrapped Object can not be null.");
    }

    public T getWrappedObject() {
        return this.b;
    }
}