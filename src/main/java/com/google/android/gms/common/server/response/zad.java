package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

final class zad implements FastParser.zaa<Double> {
    zad() {
    }

    public final /* synthetic */ Object zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Double.valueOf(fastParser.zah(bufferedReader));
    }
}
