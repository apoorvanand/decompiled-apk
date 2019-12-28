package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.firebase_auth.zzbl;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.zzac;
import com.google.firebase.auth.zzx;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzat {
    private Logger zzjt = new Logger("StorageHelpers", new String[0]);
    private Context zzml;
    private String zzuy;
    private SharedPreferences zzuz = this.zzml.getSharedPreferences(String.format("com.google.firebase.auth.api.Store.%s", new Object[]{this.zzuy}), 0);

    public zzat(Context context, String str) {
        Preconditions.checkNotNull(context);
        this.zzuy = Preconditions.checkNotEmpty(str);
        this.zzml = context.getApplicationContext();
    }

    private final zzm zzd(JSONObject jSONObject) {
        JSONArray jSONArray;
        zzo zzb;
        try {
            String string = jSONObject.getString("cachedTokenState");
            String string2 = jSONObject.getString("applicationName");
            boolean z = jSONObject.getBoolean("anonymous");
            String str = ExifInterface.GPS_MEASUREMENT_2D;
            String string3 = jSONObject.getString(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
            if (string3 != null) {
                str = string3;
            }
            JSONArray jSONArray2 = jSONObject.getJSONArray("userInfos");
            int length = jSONArray2.length();
            ArrayList arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                arrayList.add(zzi.zzda(jSONArray2.getString(i)));
            }
            zzm zzm = new zzm(FirebaseApp.getInstance(string2), arrayList);
            if (!TextUtils.isEmpty(string)) {
                zzm.zza(zzes.zzcn(string));
            }
            if (!z) {
                zzm.zzcx();
            }
            zzm.zzdb(str);
            if (jSONObject.has("userMetadata") && (zzb = zzo.zzb(jSONObject.getJSONObject("userMetadata"))) != null) {
                zzm.zza(zzb);
            }
            if (jSONObject.has("userMultiFactorInfo") && (jSONArray = jSONObject.getJSONArray("userMultiFactorInfo")) != null) {
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject2 = new JSONObject(jSONArray.getString(i2));
                    arrayList2.add("phone".equals(jSONObject2.optString("factorIdKey")) ? zzac.zza(jSONObject2) : null);
                }
                zzm.zzb(arrayList2);
            }
            return zzm;
        } catch (zzbl | ArrayIndexOutOfBoundsException | IllegalArgumentException | JSONException e) {
            this.zzjt.wtf(e);
            return null;
        }
    }

    @Nullable
    private final String zzi(FirebaseUser firebaseUser) {
        JSONObject jSONObject = new JSONObject();
        if (!zzm.class.isAssignableFrom(firebaseUser.getClass())) {
            return null;
        }
        zzm zzm = (zzm) firebaseUser;
        try {
            jSONObject.put("cachedTokenState", zzm.zzcz());
            jSONObject.put("applicationName", zzm.zzcu().getName());
            jSONObject.put("type", "com.google.firebase.auth.internal.DefaultFirebaseUser");
            if (zzm.zzff() != null) {
                JSONArray jSONArray = new JSONArray();
                List<zzi> zzff = zzm.zzff();
                for (int i = 0; i < zzff.size(); i++) {
                    jSONArray.put(zzff.get(i).zzew());
                }
                jSONObject.put("userInfos", jSONArray);
            }
            jSONObject.put("anonymous", zzm.isAnonymous());
            jSONObject.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, ExifInterface.GPS_MEASUREMENT_2D);
            if (zzm.getMetadata() != null) {
                jSONObject.put("userMetadata", ((zzo) zzm.getMetadata()).zzfg());
            }
            List<zzx> zzdc = ((zzq) zzm.zzdb()).zzdc();
            if (zzdc != null && !zzdc.isEmpty()) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < zzdc.size(); i2++) {
                    jSONArray2.put(zzdc.get(i2).toJson());
                }
                jSONObject.put("userMultiFactorInfo", jSONArray2);
            }
            return jSONObject.toString();
        } catch (Exception e) {
            this.zzjt.wtf("Failed to turn object into JSON", e, new Object[0]);
            throw new zzbl((Throwable) e);
        }
    }

    public final void clear(String str) {
        this.zzuz.edit().remove(str).apply();
    }

    public final void zza(FirebaseUser firebaseUser, zzes zzes) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzes);
        this.zzuz.edit().putString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}), zzes.zzew()).apply();
    }

    @Nullable
    public final FirebaseUser zzfr() {
        String string = this.zzuz.getString("com.google.firebase.auth.FIREBASE_USER", (String) null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has("type") && "com.google.firebase.auth.internal.DefaultFirebaseUser".equalsIgnoreCase(jSONObject.optString("type"))) {
                return zzd(jSONObject);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public final void zzg(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        String zzi = zzi(firebaseUser);
        if (!TextUtils.isEmpty(zzi)) {
            this.zzuz.edit().putString("com.google.firebase.auth.FIREBASE_USER", zzi).apply();
        }
    }

    public final zzes zzh(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        String string = this.zzuz.getString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}), (String) null);
        if (string != null) {
            return zzes.zzcn(string);
        }
        return null;
    }
}
