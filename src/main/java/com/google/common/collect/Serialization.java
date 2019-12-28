package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multiset;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

@GwtIncompatible
final class Serialization {

    static final class FieldSetter<T> {
        private final Field field;

        private FieldSetter(Field field2) {
            this.field = field2;
            field2.setAccessible(true);
        }

        /* access modifiers changed from: package-private */
        public void a(T t, int i) {
            try {
                this.field.set(t, Integer.valueOf(i));
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: package-private */
        public void a(T t, Object obj) {
            try {
                this.field.set(t, obj);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    private Serialization() {
    }

    static int a(ObjectInputStream objectInputStream) {
        return objectInputStream.readInt();
    }

    static <T> FieldSetter<T> a(Class<T> cls, String str) {
        try {
            return new FieldSetter<>(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    static <K, V> void a(Multimap<K, V> multimap, ObjectInputStream objectInputStream) {
        a(multimap, objectInputStream, objectInputStream.readInt());
    }

    static <K, V> void a(Multimap<K, V> multimap, ObjectInputStream objectInputStream, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            Collection<V> collection = multimap.get(objectInputStream.readObject());
            int readInt = objectInputStream.readInt();
            for (int i3 = 0; i3 < readInt; i3++) {
                collection.add(objectInputStream.readObject());
            }
        }
    }

    static <K, V> void a(Multimap<K, V> multimap, ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeInt(multimap.asMap().size());
        for (Map.Entry next : multimap.asMap().entrySet()) {
            objectOutputStream.writeObject(next.getKey());
            objectOutputStream.writeInt(((Collection) next.getValue()).size());
            for (Object writeObject : (Collection) next.getValue()) {
                objectOutputStream.writeObject(writeObject);
            }
        }
    }

    static <E> void a(Multiset<E> multiset, ObjectInputStream objectInputStream) {
        a(multiset, objectInputStream, objectInputStream.readInt());
    }

    static <E> void a(Multiset<E> multiset, ObjectInputStream objectInputStream, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            multiset.add(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }

    static <E> void a(Multiset<E> multiset, ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeInt(multiset.entrySet().size());
        for (Multiset.Entry next : multiset.entrySet()) {
            objectOutputStream.writeObject(next.getElement());
            objectOutputStream.writeInt(next.getCount());
        }
    }

    static <K, V> void a(Map<K, V> map, ObjectInputStream objectInputStream) {
        a(map, objectInputStream, objectInputStream.readInt());
    }

    static <K, V> void a(Map<K, V> map, ObjectInputStream objectInputStream, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            map.put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    static <K, V> void a(Map<K, V> map, ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeInt(map.size());
        for (Map.Entry next : map.entrySet()) {
            objectOutputStream.writeObject(next.getKey());
            objectOutputStream.writeObject(next.getValue());
        }
    }
}
