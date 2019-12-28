package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

final class zac implements FastParser.zaa<Float> {
    zac() {
    }

    public final /* synthetic */ Object zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Float.valueOf(fastParser.zag(bufferedReader));
    }
}
