package com.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.LongCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AccessToken implements Parcelable {
    public static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String APPLICATION_ID_KEY = "application_id";
    public static final Parcelable.Creator<AccessToken> CREATOR = new Parcelable.Creator() {
        public AccessToken createFromParcel(Parcel parcel) {
            return new AccessToken(parcel);
        }

        public AccessToken[] newArray(int i) {
            return new AccessToken[i];
        }
    };
    private static final int CURRENT_JSON_FORMAT = 1;
    public static final String DATA_ACCESS_EXPIRATION_TIME = "data_access_expiration_time";
    private static final String DECLINED_PERMISSIONS_KEY = "declined_permissions";
    private static final AccessTokenSource DEFAULT_ACCESS_TOKEN_SOURCE = AccessTokenSource.FACEBOOK_APPLICATION_WEB;
    private static final Date DEFAULT_EXPIRATION_TIME = MAX_DATE;
    private static final Date DEFAULT_LAST_REFRESH_TIME = new Date();
    private static final String EXPIRES_AT_KEY = "expires_at";
    public static final String EXPIRES_IN_KEY = "expires_in";
    private static final String LAST_REFRESH_KEY = "last_refresh";
    private static final Date MAX_DATE = new Date(LongCompanionObject.MAX_VALUE);
    private static final String PERMISSIONS_KEY = "permissions";
    private static final String SOURCE_KEY = "source";
    private static final String TOKEN_KEY = "token";
    public static final String USER_ID_KEY = "user_id";
    private static final String VERSION_KEY = "version";
    private final String applicationId;
    private final Date dataAccessExpirationTime;
    private final Set<String> declinedPermissions;
    private final Date expires;
    private final Date lastRefresh;
    private final Set<String> permissions;
    private final AccessTokenSource source;
    private final String token;
    private final String userId;

    public interface AccessTokenCreationCallback {
        void onError(FacebookException facebookException);

        void onSuccess(AccessToken accessToken);
    }

    public interface AccessTokenRefreshCallback {
        void OnTokenRefreshFailed(FacebookException facebookException);

        void OnTokenRefreshed(AccessToken accessToken);
    }

    AccessToken(Parcel parcel) {
        this.expires = new Date(parcel.readLong());
        ArrayList arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        this.permissions = Collections.unmodifiableSet(new HashSet(arrayList));
        arrayList.clear();
        parcel.readStringList(arrayList);
        this.declinedPermissions = Collections.unmodifiableSet(new HashSet(arrayList));
        this.token = parcel.readString();
        this.source = AccessTokenSource.valueOf(parcel.readString());
        this.lastRefresh = new Date(parcel.readLong());
        this.applicationId = parcel.readString();
        this.userId = parcel.readString();
        this.dataAccessExpirationTime = new Date(parcel.readLong());
    }

    public AccessToken(String str, String str2, String str3, @Nullable Collection<String> collection, @Nullable Collection<String> collection2, @Nullable AccessTokenSource accessTokenSource, @Nullable Date date, @Nullable Date date2, @Nullable Date date3) {
        Validate.notNullOrEmpty(str, "accessToken");
        Validate.notNullOrEmpty(str2, "applicationId");
        Validate.notNullOrEmpty(str3, "userId");
        this.expires = date == null ? DEFAULT_EXPIRATION_TIME : date;
        this.permissions = Collections.unmodifiableSet(collection != null ? new HashSet(collection) : new HashSet());
        this.declinedPermissions = Collections.unmodifiableSet(collection2 != null ? new HashSet(collection2) : new HashSet());
        this.token = str;
        this.source = accessTokenSource == null ? DEFAULT_ACCESS_TOKEN_SOURCE : accessTokenSource;
        this.lastRefresh = date2 == null ? DEFAULT_LAST_REFRESH_TIME : date2;
        this.applicationId = str2;
        this.userId = str3;
        this.dataAccessExpirationTime = (date3 == null || date3.getTime() == 0) ? DEFAULT_EXPIRATION_TIME : date3;
    }

    static AccessToken a(Bundle bundle) {
        List<String> a = a(bundle, LegacyTokenHelper.PERMISSIONS_KEY);
        List<String> a2 = a(bundle, LegacyTokenHelper.DECLINED_PERMISSIONS_KEY);
        String applicationId2 = LegacyTokenHelper.getApplicationId(bundle);
        if (Utility.isNullOrEmpty(applicationId2)) {
            applicationId2 = FacebookSdk.getApplicationId();
        }
        String str = applicationId2;
        String token2 = LegacyTokenHelper.getToken(bundle);
        try {
            return new AccessToken(token2, str, Utility.awaitGetGraphMeRequestWithCache(token2).getString(ShareConstants.WEB_DIALOG_PARAM_ID), a, a2, LegacyTokenHelper.getSource(bundle), LegacyTokenHelper.a(bundle, LegacyTokenHelper.EXPIRATION_DATE_KEY), LegacyTokenHelper.a(bundle, LegacyTokenHelper.LAST_REFRESH_DATE_KEY), (Date) null);
        } catch (JSONException unused) {
            return null;
        }
    }

    static AccessToken a(AccessToken accessToken) {
        return new AccessToken(accessToken.token, accessToken.applicationId, accessToken.getUserId(), accessToken.getPermissions(), accessToken.getDeclinedPermissions(), accessToken.source, new Date(), new Date(), accessToken.dataAccessExpirationTime);
    }

    static AccessToken a(JSONObject jSONObject) {
        if (jSONObject.getInt("version") <= 1) {
            String string = jSONObject.getString(TOKEN_KEY);
            Date date = new Date(jSONObject.getLong(EXPIRES_AT_KEY));
            JSONArray jSONArray = jSONObject.getJSONArray("permissions");
            JSONArray jSONArray2 = jSONObject.getJSONArray(DECLINED_PERMISSIONS_KEY);
            Date date2 = new Date(jSONObject.getLong(LAST_REFRESH_KEY));
            AccessTokenSource valueOf = AccessTokenSource.valueOf(jSONObject.getString("source"));
            return new AccessToken(string, jSONObject.getString(APPLICATION_ID_KEY), jSONObject.getString(USER_ID_KEY), Utility.jsonArrayToStringList(jSONArray), Utility.jsonArrayToStringList(jSONArray2), valueOf, date, date2, new Date(jSONObject.getLong(DATA_ACCESS_EXPIRATION_TIME)));
        }
        throw new FacebookException("Unknown AccessToken serialization format.");
    }

    static List<String> a(Bundle bundle, String str) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList(str);
        return stringArrayList == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(stringArrayList));
    }

    static void a() {
        AccessToken b = AccessTokenManager.a().b();
        if (b != null) {
            setCurrentAccessToken(a(b));
        }
    }

    private void appendPermissions(StringBuilder sb) {
        String str;
        sb.append(" permissions:");
        if (this.permissions == null) {
            str = "null";
        } else {
            sb.append("[");
            sb.append(TextUtils.join(", ", this.permissions));
            str = "]";
        }
        sb.append(str);
    }

    /* access modifiers changed from: private */
    public static AccessToken createFromBundle(List<String> list, Bundle bundle, AccessTokenSource accessTokenSource, Date date, String str) {
        String string = bundle.getString("access_token");
        Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle, EXPIRES_IN_KEY, date);
        String string2 = bundle.getString(USER_ID_KEY);
        Date bundleLongAsDate2 = Utility.getBundleLongAsDate(bundle, DATA_ACCESS_EXPIRATION_TIME, new Date(0));
        if (Utility.isNullOrEmpty(string) || bundleLongAsDate == null) {
            return null;
        }
        return new AccessToken(string, str, string2, list, (Collection<String>) null, accessTokenSource, bundleLongAsDate, new Date(), bundleLongAsDate2);
    }

    public static void createFromNativeLinkingIntent(Intent intent, final String str, final AccessTokenCreationCallback accessTokenCreationCallback) {
        FacebookException facebookException;
        Validate.notNull(intent, "intent");
        if (intent.getExtras() == null) {
            facebookException = new FacebookException("No extras found on intent");
        } else {
            final Bundle bundle = new Bundle(intent.getExtras());
            String string = bundle.getString("access_token");
            if (string == null || string.isEmpty()) {
                facebookException = new FacebookException("No access token found on intent");
            } else {
                String string2 = bundle.getString(USER_ID_KEY);
                if (string2 == null || string2.isEmpty()) {
                    Utility.getGraphMeRequestWithCacheAsync(string, new Utility.GraphMeRequestWithCacheCallback() {
                        public void onFailure(FacebookException facebookException) {
                            accessTokenCreationCallback.onError(facebookException);
                        }

                        public void onSuccess(JSONObject jSONObject) {
                            try {
                                bundle.putString(AccessToken.USER_ID_KEY, jSONObject.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
                                accessTokenCreationCallback.onSuccess(AccessToken.createFromBundle((List<String>) null, bundle, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), str));
                            } catch (JSONException unused) {
                                accessTokenCreationCallback.onError(new FacebookException("Unable to generate access token due to missing user id"));
                            }
                        }
                    });
                    return;
                } else {
                    accessTokenCreationCallback.onSuccess(createFromBundle((List<String>) null, bundle, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), str));
                    return;
                }
            }
        }
        accessTokenCreationCallback.onError(facebookException);
    }

    public static AccessToken getCurrentAccessToken() {
        return AccessTokenManager.a().b();
    }

    public static boolean isCurrentAccessTokenActive() {
        AccessToken b = AccessTokenManager.a().b();
        return b != null && !b.isExpired();
    }

    public static boolean isDataAccessActive() {
        AccessToken b = AccessTokenManager.a().b();
        return b != null && !b.isDataAccessExpired();
    }

    public static void refreshCurrentAccessTokenAsync() {
        AccessTokenManager.a().a((AccessTokenRefreshCallback) null);
    }

    public static void refreshCurrentAccessTokenAsync(AccessTokenRefreshCallback accessTokenRefreshCallback) {
        AccessTokenManager.a().a(accessTokenRefreshCallback);
    }

    public static void setCurrentAccessToken(AccessToken accessToken) {
        AccessTokenManager.a().a(accessToken);
    }

    private String tokenToString() {
        return this.token == null ? "null" : FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.INCLUDE_ACCESS_TOKENS) ? this.token : "ACCESS_TOKEN_REMOVED";
    }

    /* access modifiers changed from: package-private */
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", 1);
        jSONObject.put(TOKEN_KEY, this.token);
        jSONObject.put(EXPIRES_AT_KEY, this.expires.getTime());
        jSONObject.put("permissions", new JSONArray(this.permissions));
        jSONObject.put(DECLINED_PERMISSIONS_KEY, new JSONArray(this.declinedPermissions));
        jSONObject.put(LAST_REFRESH_KEY, this.lastRefresh.getTime());
        jSONObject.put("source", this.source.name());
        jSONObject.put(APPLICATION_ID_KEY, this.applicationId);
        jSONObject.put(USER_ID_KEY, this.userId);
        jSONObject.put(DATA_ACCESS_EXPIRATION_TIME, this.dataAccessExpirationTime.getTime());
        return jSONObject;
    }

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        r1 = r4.applicationId;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.facebook.AccessToken
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            com.facebook.AccessToken r5 = (com.facebook.AccessToken) r5
            java.util.Date r1 = r4.expires
            java.util.Date r3 = r5.expires
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006a
            java.util.Set<java.lang.String> r1 = r4.permissions
            java.util.Set<java.lang.String> r3 = r5.permissions
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006a
            java.util.Set<java.lang.String> r1 = r4.declinedPermissions
            java.util.Set<java.lang.String> r3 = r5.declinedPermissions
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006a
            java.lang.String r1 = r4.token
            java.lang.String r3 = r5.token
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006a
            com.facebook.AccessTokenSource r1 = r4.source
            com.facebook.AccessTokenSource r3 = r5.source
            if (r1 != r3) goto L_0x006a
            java.util.Date r1 = r4.lastRefresh
            java.util.Date r3 = r5.lastRefresh
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006a
            java.lang.String r1 = r4.applicationId
            if (r1 != 0) goto L_0x004d
            java.lang.String r1 = r5.applicationId
            if (r1 != 0) goto L_0x006a
            goto L_0x0055
        L_0x004d:
            java.lang.String r3 = r5.applicationId
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006a
        L_0x0055:
            java.lang.String r1 = r4.userId
            java.lang.String r3 = r5.userId
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006a
            java.util.Date r1 = r4.dataAccessExpirationTime
            java.util.Date r5 = r5.dataAccessExpirationTime
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x006a
            goto L_0x006b
        L_0x006a:
            r0 = 0
        L_0x006b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.AccessToken.equals(java.lang.Object):boolean");
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public Date getDataAccessExpirationTime() {
        return this.dataAccessExpirationTime;
    }

    public Set<String> getDeclinedPermissions() {
        return this.declinedPermissions;
    }

    public Date getExpires() {
        return this.expires;
    }

    public Date getLastRefresh() {
        return this.lastRefresh;
    }

    public Set<String> getPermissions() {
        return this.permissions;
    }

    public AccessTokenSource getSource() {
        return this.source;
    }

    public String getToken() {
        return this.token;
    }

    public String getUserId() {
        return this.userId;
    }

    public int hashCode() {
        int hashCode = (((((((((((527 + this.expires.hashCode()) * 31) + this.permissions.hashCode()) * 31) + this.declinedPermissions.hashCode()) * 31) + this.token.hashCode()) * 31) + this.source.hashCode()) * 31) + this.lastRefresh.hashCode()) * 31;
        String str = this.applicationId;
        return ((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.userId.hashCode()) * 31) + this.dataAccessExpirationTime.hashCode();
    }

    public boolean isDataAccessExpired() {
        return new Date().after(this.dataAccessExpirationTime);
    }

    public boolean isExpired() {
        return new Date().after(this.expires);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{AccessToken");
        sb.append(" token:");
        sb.append(tokenToString());
        appendPermissions(sb);
        sb.append("}");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.expires.getTime());
        parcel.writeStringList(new ArrayList(this.permissions));
        parcel.writeStringList(new ArrayList(this.declinedPermissions));
        parcel.writeString(this.token);
        parcel.writeString(this.source.name());
        parcel.writeLong(this.lastRefresh.getTime());
        parcel.writeString(this.applicationId);
        parcel.writeString(this.userId);
        parcel.writeLong(this.dataAccessExpirationTime.getTime());
    }
}
