package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.lang.reflect.Array;

@GwtCompatible(emulated = true)
final class Platform {
    private Platform() {
    }

    static MapMaker a(MapMaker mapMaker) {
        return mapMaker.weakKeys();
    }

    static <T> T[] a(T[] tArr, int i) {
        return (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i);
    }
}
