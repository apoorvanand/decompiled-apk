package androidx.arch.core.internal;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SafeIterableMap<K, V> implements Iterable<Map.Entry<K, V>> {
    Entry<K, V> a;
    private Entry<K, V> mEnd;
    private WeakHashMap<SupportRemove<K, V>, Boolean> mIterators = new WeakHashMap<>();
    private int mSize = 0;

    static class AscendingIterator<K, V> extends ListIterator<K, V> {
        AscendingIterator(Entry<K, V> entry, Entry<K, V> entry2) {
            super(entry, entry2);
        }

        /* access modifiers changed from: package-private */
        public Entry<K, V> a(Entry<K, V> entry) {
            return entry.c;
        }

        /* access modifiers changed from: package-private */
        public Entry<K, V> b(Entry<K, V> entry) {
            return entry.d;
        }
    }

    private static class DescendingIterator<K, V> extends ListIterator<K, V> {
        DescendingIterator(Entry<K, V> entry, Entry<K, V> entry2) {
            super(entry, entry2);
        }

        /* access modifiers changed from: package-private */
        public Entry<K, V> a(Entry<K, V> entry) {
            return entry.d;
        }

        /* access modifiers changed from: package-private */
        public Entry<K, V> b(Entry<K, V> entry) {
            return entry.c;
        }
    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        @NonNull
        final K a;
        @NonNull
        final V b;
        Entry<K, V> c;
        Entry<K, V> d;

        Entry(@NonNull K k, @NonNull V v) {
            this.a = k;
            this.b = v;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.a.equals(entry.a) && this.b.equals(entry.b);
        }

        @NonNull
        public K getKey() {
            return this.a;
        }

        @NonNull
        public V getValue() {
            return this.b;
        }

        public int hashCode() {
            return this.a.hashCode() ^ this.b.hashCode();
        }

        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.a + "=" + this.b;
        }
    }

    private class IteratorWithAdditions implements SupportRemove<K, V>, Iterator<Map.Entry<K, V>> {
        private boolean mBeforeStart = true;
        private Entry<K, V> mCurrent;

        IteratorWithAdditions() {
        }

        public boolean hasNext() {
            if (this.mBeforeStart) {
                return SafeIterableMap.this.a != null;
            }
            Entry<K, V> entry = this.mCurrent;
            return (entry == null || entry.c == null) ? false : true;
        }

        public Map.Entry<K, V> next() {
            Entry<K, V> entry;
            if (this.mBeforeStart) {
                this.mBeforeStart = false;
                entry = SafeIterableMap.this.a;
            } else {
                Entry<K, V> entry2 = this.mCurrent;
                entry = entry2 != null ? entry2.c : null;
            }
            this.mCurrent = entry;
            return this.mCurrent;
        }

        public void supportRemove(@NonNull Entry<K, V> entry) {
            Entry<K, V> entry2 = this.mCurrent;
            if (entry == entry2) {
                this.mCurrent = entry2.d;
                this.mBeforeStart = this.mCurrent == null;
            }
        }
    }

    private static abstract class ListIterator<K, V> implements SupportRemove<K, V>, Iterator<Map.Entry<K, V>> {
        Entry<K, V> a;
        Entry<K, V> b;

        ListIterator(Entry<K, V> entry, Entry<K, V> entry2) {
            this.a = entry2;
            this.b = entry;
        }

        private Entry<K, V> nextNode() {
            Entry<K, V> entry = this.b;
            Entry<K, V> entry2 = this.a;
            if (entry == entry2 || entry2 == null) {
                return null;
            }
            return a(entry);
        }

        /* access modifiers changed from: package-private */
        public abstract Entry<K, V> a(Entry<K, V> entry);

        /* access modifiers changed from: package-private */
        public abstract Entry<K, V> b(Entry<K, V> entry);

        public boolean hasNext() {
            return this.b != null;
        }

        public Map.Entry<K, V> next() {
            Entry<K, V> entry = this.b;
            this.b = nextNode();
            return entry;
        }

        public void supportRemove(@NonNull Entry<K, V> entry) {
            if (this.a == entry && entry == this.b) {
                this.b = null;
                this.a = null;
            }
            Entry<K, V> entry2 = this.a;
            if (entry2 == entry) {
                this.a = b(entry2);
            }
            if (this.b == entry) {
                this.b = nextNode();
            }
        }
    }

    interface SupportRemove<K, V> {
        void supportRemove(@NonNull Entry<K, V> entry);
    }

    /* access modifiers changed from: protected */
    public Entry<K, V> a(K k) {
        Entry<K, V> entry = this.a;
        while (entry != null && !entry.a.equals(k)) {
            entry = entry.c;
        }
        return entry;
    }

    /* access modifiers changed from: protected */
    public Entry<K, V> a(@NonNull K k, @NonNull V v) {
        Entry<K, V> entry = new Entry<>(k, v);
        this.mSize++;
        Entry<K, V> entry2 = this.mEnd;
        if (entry2 == null) {
            this.a = entry;
            this.mEnd = this.a;
            return entry;
        }
        entry2.c = entry;
        entry.d = entry2;
        this.mEnd = entry;
        return entry;
    }

    public Iterator<Map.Entry<K, V>> descendingIterator() {
        DescendingIterator descendingIterator = new DescendingIterator(this.mEnd, this.a);
        this.mIterators.put(descendingIterator, false);
        return descendingIterator;
    }

    public Map.Entry<K, V> eldest() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SafeIterableMap)) {
            return false;
        }
        SafeIterableMap safeIterableMap = (SafeIterableMap) obj;
        if (size() != safeIterableMap.size()) {
            return false;
        }
        Iterator it = iterator();
        Iterator it2 = safeIterableMap.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object next = it2.next();
            if ((entry == null && next != null) || (entry != null && !entry.equals(next))) {
                return false;
            }
        }
        return !it.hasNext() && !it2.hasNext();
    }

    public int hashCode() {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((Map.Entry) it.next()).hashCode();
        }
        return i;
    }

    @NonNull
    public Iterator<Map.Entry<K, V>> iterator() {
        AscendingIterator ascendingIterator = new AscendingIterator(this.a, this.mEnd);
        this.mIterators.put(ascendingIterator, false);
        return ascendingIterator;
    }

    public SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions() {
        SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions = new IteratorWithAdditions();
        this.mIterators.put(iteratorWithAdditions, false);
        return iteratorWithAdditions;
    }

    public Map.Entry<K, V> newest() {
        return this.mEnd;
    }

    public V putIfAbsent(@NonNull K k, @NonNull V v) {
        Entry a2 = a(k);
        if (a2 != null) {
            return a2.b;
        }
        a(k, v);
        return null;
    }

    public V remove(@NonNull K k) {
        Entry a2 = a(k);
        if (a2 == null) {
            return null;
        }
        this.mSize--;
        if (!this.mIterators.isEmpty()) {
            for (SupportRemove<K, V> supportRemove : this.mIterators.keySet()) {
                supportRemove.supportRemove(a2);
            }
        }
        if (a2.d != null) {
            a2.d.c = a2.c;
        } else {
            this.a = a2.c;
        }
        if (a2.c != null) {
            a2.c.d = a2.d;
        } else {
            this.mEnd = a2.d;
        }
        a2.c = null;
        a2.d = null;
        return a2.b;
    }

    public int size() {
        return this.mSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator it = iterator();
        while (it.hasNext()) {
            sb.append(((Map.Entry) it.next()).toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
