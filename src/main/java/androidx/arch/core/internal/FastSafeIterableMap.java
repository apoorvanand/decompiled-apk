package androidx.arch.core.internal;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.arch.core.internal.SafeIterableMap;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class FastSafeIterableMap<K, V> extends SafeIterableMap<K, V> {
    private HashMap<K, SafeIterableMap.Entry<K, V>> mHashMap = new HashMap<>();

    /* access modifiers changed from: protected */
    public SafeIterableMap.Entry<K, V> a(K k) {
        return this.mHashMap.get(k);
    }

    public Map.Entry<K, V> ceil(K k) {
        if (contains(k)) {
            return this.mHashMap.get(k).d;
        }
        return null;
    }

    public boolean contains(K k) {
        return this.mHashMap.containsKey(k);
    }

    public V putIfAbsent(@NonNull K k, @NonNull V v) {
        SafeIterableMap.Entry a = a(k);
        if (a != null) {
            return a.b;
        }
        this.mHashMap.put(k, a(k, v));
        return null;
    }

    public V remove(@NonNull K k) {
        V remove = super.remove(k);
        this.mHashMap.remove(k);
        return remove;
    }
}
