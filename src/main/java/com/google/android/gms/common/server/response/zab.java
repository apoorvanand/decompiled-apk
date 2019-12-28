package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

final class zab implements FastParser.zaa<Long> {
    zab() {
    }

    public final /* synthetic */ Object zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Long.valueOf(fastParser.zae(bufferedReader));
    }
}
