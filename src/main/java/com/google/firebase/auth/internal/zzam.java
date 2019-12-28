package com.google.firebase.auth.internal;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzbl;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

final class zzam {
    private static final Logger zzjt = new Logger("JSONParser", new String[0]);

    @VisibleForTesting
    private static List<Object> zza(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = zza((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = zzc((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }

    @VisibleForTesting
    private static Map<String, Object> zzc(JSONObject jSONObject) {
        ArrayMap arrayMap = new ArrayMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONArray) {
                obj = zza((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = zzc((JSONObject) obj);
            }
            arrayMap.put(next, obj);
        }
        return arrayMap;
    }

    @NonNull
    public static Map<String, Object> zzdd(String str) {
        Preconditions.checkNotEmpty(str);
        List<String> zza = com.google.android.gms.internal.firebase_auth.zzam.zzd('.').zza(str);
        if (zza.size() < 2) {
            Logger logger = zzjt;
            String valueOf = String.valueOf(str);
            logger.e(valueOf.length() != 0 ? "Invalid idToken ".concat(valueOf) : new String("Invalid idToken "), new Object[0]);
        } else {
            try {
                Map<String, Object> zzde = zzde(new String(Base64Utils.decodeUrlSafeNoPadding(zza.get(1)), "UTF-8"));
                return zzde == null ? Collections.EMPTY_MAP : zzde;
            } catch (UnsupportedEncodingException e) {
                zzjt.e("Unable to decode token", e, new Object[0]);
            }
        }
        return Collections.EMPTY_MAP;
    }

    @Nullable
    public static Map<String, Object> zzde(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != JSONObject.NULL) {
                return zzc(jSONObject);
            }
            return null;
        } catch (Exception e) {
            Log.d("JSONParser", "Failed to parse JSONObject into Map.");
            throw new zzbl((Throwable) e);
        }
    }
}
