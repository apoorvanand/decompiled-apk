package com.facebook;

import android.content.SharedPreferences;
import com.facebook.internal.Validate;
import com.google.android.gms.common.Scopes;
import org.json.JSONException;
import org.json.JSONObject;

final class ProfileCache {
    private final SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences(AccessTokenManager.SHARED_PREFERENCES_NAME, 0);

    ProfileCache() {
    }

    /* access modifiers changed from: package-private */
    public Profile a() {
        String string = this.sharedPreferences.getString("com.facebook.ProfileManager.CachedProfile", (String) null);
        if (string != null) {
            try {
                return new Profile(new JSONObject(string));
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(Profile profile) {
        Validate.notNull(profile, Scopes.PROFILE);
        JSONObject a = profile.a();
        if (a != null) {
            this.sharedPreferences.edit().putString("com.facebook.ProfileManager.CachedProfile", a.toString()).apply();
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.sharedPreferences.edit().remove("com.facebook.ProfileManager.CachedProfile").apply();
    }
}
