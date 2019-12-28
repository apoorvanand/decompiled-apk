package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class ArraySet<E> implements Collection<E>, Set<E> {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean DEBUG = false;
    private static final int[] INT = new int[0];
    private static final Object[] OBJECT = new Object[0];
    private static final String TAG = "ArraySet";
    @Nullable
    private static Object[] sBaseCache;
    private static int sBaseCacheSize;
    @Nullable
    private static Object[] sTwiceBaseCache;
    private static int sTwiceBaseCacheSize;
    Object[] a;
    int b;
    private MapCollections<E, E> mCollections;
    private int[] mHashes;

    public ArraySet() {
        this(0);
    }

    public ArraySet(int i) {
        if (i == 0) {
            this.mHashes = INT;
            this.a = OBJECT;
        } else {
            allocArrays(i);
        }
        this.b = 0;
    }

    public ArraySet(@Nullable ArraySet<E> arraySet) {
        this();
        if (arraySet != null) {
            addAll(arraySet);
        }
    }

    public ArraySet(@Nullable Collection<E> collection) {
        this();
        if (collection != null) {
            addAll(collection);
        }
    }

    private void allocArrays(int i) {
        if (i == 8) {
            synchronized (ArraySet.class) {
                if (sTwiceBaseCache != null) {
                    Object[] objArr = sTwiceBaseCache;
                    this.a = objArr;
                    sTwiceBaseCache = (Object[]) objArr[0];
                    this.mHashes = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    sTwiceBaseCacheSize--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (ArraySet.class) {
                if (sBaseCache != null) {
                    Object[] objArr2 = sBaseCache;
                    this.a = objArr2;
                    sBaseCache = (Object[]) objArr2[0];
                    this.mHashes = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    sBaseCacheSize--;
                    return;
                }
            }
        }
        this.mHashes = new int[i];
        this.a = new Object[i];
    }

    private static void freeArrays(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (ArraySet.class) {
                if (sTwiceBaseCacheSize < 10) {
                    objArr[0] = sTwiceBaseCache;
                    objArr[1] = iArr;
                    for (int i2 = i - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    sTwiceBaseCache = objArr;
                    sTwiceBaseCacheSize++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (ArraySet.class) {
                if (sBaseCacheSize < 10) {
                    objArr[0] = sBaseCache;
                    objArr[1] = iArr;
                    for (int i3 = i - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    sBaseCache = objArr;
                    sBaseCacheSize++;
                }
            }
        }
    }

    private MapCollections<E, E> getCollection() {
        if (this.mCollections == null) {
            this.mCollections = new MapCollections<E, E>() {
                /* access modifiers changed from: protected */
                public int a() {
                    return ArraySet.this.b;
                }

                /* access modifiers changed from: protected */
                public int a(Object obj) {
                    return ArraySet.this.indexOf(obj);
                }

                /* access modifiers changed from: protected */
                public Object a(int i, int i2) {
                    return ArraySet.this.a[i];
                }

                /* access modifiers changed from: protected */
                public E a(int i, E e) {
                    throw new UnsupportedOperationException("not a map");
                }

                /* access modifiers changed from: protected */
                public void a(int i) {
                    ArraySet.this.removeAt(i);
                }

                /* access modifiers changed from: protected */
                public void a(E e, E e2) {
                    ArraySet.this.add(e);
                }

                /* access modifiers changed from: protected */
                public int b(Object obj) {
                    return ArraySet.this.indexOf(obj);
                }

                /* access modifiers changed from: protected */
                public Map<E, E> b() {
                    throw new UnsupportedOperationException("not a map");
                }

                /* access modifiers changed from: protected */
                public void c() {
                    ArraySet.this.clear();
                }
            };
        }
        return this.mCollections;
    }

    private int indexOf(Object obj, int i) {
        int i2 = this.b;
        if (i2 == 0) {
            return -1;
        }
        int a2 = ContainerHelpers.a(this.mHashes, i2, i);
        if (a2 < 0 || obj.equals(this.a[a2])) {
            return a2;
        }
        int i3 = a2 + 1;
        while (i3 < i2 && this.mHashes[i3] == i) {
            if (obj.equals(this.a[i3])) {
                return i3;
            }
            i3++;
        }
        int i4 = a2 - 1;
        while (i4 >= 0 && this.mHashes[i4] == i) {
            if (obj.equals(this.a[i4])) {
                return i4;
            }
            i4--;
        }
        return ~i3;
    }

    private int indexOfNull() {
        int i = this.b;
        if (i == 0) {
            return -1;
        }
        int a2 = ContainerHelpers.a(this.mHashes, i, 0);
        if (a2 < 0 || this.a[a2] == null) {
            return a2;
        }
        int i2 = a2 + 1;
        while (i2 < i && this.mHashes[i2] == 0) {
            if (this.a[i2] == null) {
                return i2;
            }
            i2++;
        }
        int i3 = a2 - 1;
        while (i3 >= 0 && this.mHashes[i3] == 0) {
            if (this.a[i3] == null) {
                return i3;
            }
            i3--;
        }
        return ~i2;
    }

    public boolean add(@Nullable E e) {
        int i;
        int i2;
        if (e == null) {
            i2 = indexOfNull();
            i = 0;
        } else {
            int hashCode = e.hashCode();
            i = hashCode;
            i2 = indexOf(e, hashCode);
        }
        if (i2 >= 0) {
            return false;
        }
        int i3 = ~i2;
        int i4 = this.b;
        if (i4 >= this.mHashes.length) {
            int i5 = 4;
            if (i4 >= 8) {
                i5 = (i4 >> 1) + i4;
            } else if (i4 >= 4) {
                i5 = 8;
            }
            int[] iArr = this.mHashes;
            Object[] objArr = this.a;
            allocArrays(i5);
            int[] iArr2 = this.mHashes;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.a, 0, objArr.length);
            }
            freeArrays(iArr, objArr, this.b);
        }
        int i6 = this.b;
        if (i3 < i6) {
            int[] iArr3 = this.mHashes;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr2 = this.a;
            System.arraycopy(objArr2, i3, objArr2, i7, this.b - i3);
        }
        this.mHashes[i3] = i;
        this.a[i3] = e;
        this.b++;
        return true;
    }

    public void addAll(@NonNull ArraySet<? extends E> arraySet) {
        int i = arraySet.b;
        ensureCapacity(this.b + i);
        if (this.b != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                add(arraySet.valueAt(i2));
            }
        } else if (i > 0) {
            System.arraycopy(arraySet.mHashes, 0, this.mHashes, 0, i);
            System.arraycopy(arraySet.a, 0, this.a, 0, i);
            this.b = i;
        }
    }

    public boolean addAll(@NonNull Collection<? extends E> collection) {
        ensureCapacity(this.b + collection.size());
        boolean z = false;
        for (Object add : collection) {
            z |= add(add);
        }
        return z;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void append(E e) {
        int i = this.b;
        int hashCode = e == null ? 0 : e.hashCode();
        int[] iArr = this.mHashes;
        if (i >= iArr.length) {
            throw new IllegalStateException("Array is full");
        } else if (i <= 0 || iArr[i - 1] <= hashCode) {
            this.b = i + 1;
            this.mHashes[i] = hashCode;
            this.a[i] = e;
        } else {
            add(e);
        }
    }

    public void clear() {
        int i = this.b;
        if (i != 0) {
            freeArrays(this.mHashes, this.a, i);
            this.mHashes = INT;
            this.a = OBJECT;
            this.b = 0;
        }
    }

    public boolean contains(@Nullable Object obj) {
        return indexOf(obj) >= 0;
    }

    public boolean containsAll(@NonNull Collection<?> collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public void ensureCapacity(int i) {
        int[] iArr = this.mHashes;
        if (iArr.length < i) {
            Object[] objArr = this.a;
            allocArrays(i);
            int i2 = this.b;
            if (i2 > 0) {
                System.arraycopy(iArr, 0, this.mHashes, 0, i2);
                System.arraycopy(objArr, 0, this.a, 0, this.b);
            }
            freeArrays(iArr, objArr, this.b);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            int i = 0;
            while (i < this.b) {
                try {
                    if (!set.contains(valueAt(i))) {
                        return false;
                    }
                    i++;
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int[] iArr = this.mHashes;
        int i = this.b;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    public int indexOf(@Nullable Object obj) {
        return obj == null ? indexOfNull() : indexOf(obj, obj.hashCode());
    }

    public boolean isEmpty() {
        return this.b <= 0;
    }

    public Iterator<E> iterator() {
        return getCollection().getKeySet().iterator();
    }

    public boolean remove(@Nullable Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        removeAt(indexOf);
        return true;
    }

    public boolean removeAll(@NonNull ArraySet<? extends E> arraySet) {
        int i = arraySet.b;
        int i2 = this.b;
        for (int i3 = 0; i3 < i; i3++) {
            remove(arraySet.valueAt(i3));
        }
        return i2 != this.b;
    }

    public boolean removeAll(@NonNull Collection<?> collection) {
        boolean z = false;
        for (Object remove : collection) {
            z |= remove(remove);
        }
        return z;
    }

    public E removeAt(int i) {
        E[] eArr = this.a;
        E e = eArr[i];
        int i2 = this.b;
        if (i2 <= 1) {
            freeArrays(this.mHashes, eArr, i2);
            this.mHashes = INT;
            this.a = OBJECT;
            this.b = 0;
        } else {
            int[] iArr = this.mHashes;
            int i3 = 8;
            if (iArr.length <= 8 || i2 >= iArr.length / 3) {
                this.b--;
                int i4 = this.b;
                if (i < i4) {
                    int[] iArr2 = this.mHashes;
                    int i5 = i + 1;
                    System.arraycopy(iArr2, i5, iArr2, i, i4 - i);
                    Object[] objArr = this.a;
                    System.arraycopy(objArr, i5, objArr, i, this.b - i);
                }
                this.a[this.b] = null;
            } else {
                if (i2 > 8) {
                    i3 = i2 + (i2 >> 1);
                }
                int[] iArr3 = this.mHashes;
                Object[] objArr2 = this.a;
                allocArrays(i3);
                this.b--;
                if (i > 0) {
                    System.arraycopy(iArr3, 0, this.mHashes, 0, i);
                    System.arraycopy(objArr2, 0, this.a, 0, i);
                }
                int i6 = this.b;
                if (i < i6) {
                    int i7 = i + 1;
                    System.arraycopy(iArr3, i7, this.mHashes, i, i6 - i);
                    System.arraycopy(objArr2, i7, this.a, i, this.b - i);
                }
            }
        }
        return e;
    }

    public boolean retainAll(@NonNull Collection<?> collection) {
        boolean z = false;
        for (int i = this.b - 1; i >= 0; i--) {
            if (!collection.contains(this.a[i])) {
                removeAt(i);
                z = true;
            }
        }
        return z;
    }

    public int size() {
        return this.b;
    }

    @NonNull
    public Object[] toArray() {
        int i = this.b;
        Object[] objArr = new Object[i];
        System.arraycopy(this.a, 0, objArr, 0, i);
        return objArr;
    }

    @NonNull
    public <T> T[] toArray(@NonNull T[] tArr) {
        if (tArr.length < this.b) {
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.b);
        }
        System.arraycopy(this.a, 0, tArr, 0, this.b);
        int length = tArr.length;
        int i = this.b;
        if (length > i) {
            tArr[i] = null;
        }
        return tArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.b * 14);
        sb.append('{');
        for (int i = 0; i < this.b; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object valueAt = valueAt(i);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Nullable
    public E valueAt(int i) {
        return this.a[i];
    }
}
