package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

final class zae implements FastParser.zaa<Boolean> {
    zae() {
    }

    public final /* synthetic */ Object zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Boolean.valueOf(fastParser.zaa(bufferedReader, false));
    }
}
