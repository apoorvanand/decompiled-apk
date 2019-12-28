package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

final class zaa implements FastParser.zaa<Integer> {
    zaa() {
    }

    public final /* synthetic */ Object zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Integer.valueOf(fastParser.zad(bufferedReader));
    }
}
