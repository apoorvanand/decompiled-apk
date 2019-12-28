package com.facebook.login;

import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.FacebookServiceException;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.login.LoginClient;
import java.util.Locale;

abstract class WebLoginMethodHandler extends LoginMethodHandler {
    private static final String WEB_VIEW_AUTH_HANDLER_STORE = "com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY";
    private static final String WEB_VIEW_AUTH_HANDLER_TOKEN_KEY = "TOKEN";
    private String e2e;

    WebLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    WebLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    private static final String getRedirectUri() {
        return "fb" + FacebookSdk.getApplicationId() + "://authorize";
    }

    private String loadCookieToken() {
        return this.b.a().getSharedPreferences(WEB_VIEW_AUTH_HANDLER_STORE, 0).getString(WEB_VIEW_AUTH_HANDLER_TOKEN_KEY, "");
    }

    private void saveCookieToken(String str) {
        this.b.a().getSharedPreferences(WEB_VIEW_AUTH_HANDLER_STORE, 0).edit().putString(WEB_VIEW_AUTH_HANDLER_TOKEN_KEY, str).apply();
    }

    /* access modifiers changed from: protected */
    public Bundle a(Bundle bundle, LoginClient.Request request) {
        bundle.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, getRedirectUri());
        bundle.putString("client_id", request.d());
        LoginClient loginClient = this.b;
        bundle.putString("e2e", LoginClient.j());
        bundle.putString(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN_AND_SIGNED_REQUEST);
        bundle.putString(ServerProtocol.DIALOG_PARAM_RETURN_SCOPES, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        bundle.putString(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, request.i());
        if (c() != null) {
            bundle.putString(ServerProtocol.DIALOG_PARAM_SSO_DEVICE, c());
        }
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void a(LoginClient.Request request, Bundle bundle, FacebookException facebookException) {
        LoginClient.Result result;
        String str;
        this.e2e = null;
        if (bundle != null) {
            if (bundle.containsKey("e2e")) {
                this.e2e = bundle.getString("e2e");
            }
            try {
                AccessToken createAccessTokenFromWebBundle = createAccessTokenFromWebBundle(request.a(), bundle, a_(), request.d());
                result = LoginClient.Result.a(this.b.getPendingRequest(), createAccessTokenFromWebBundle);
                CookieSyncManager.createInstance(this.b.a()).sync();
                saveCookieToken(createAccessTokenFromWebBundle.getToken());
            } catch (FacebookException e) {
                result = LoginClient.Result.a(this.b.getPendingRequest(), (String) null, e.getMessage());
            }
        } else if (facebookException instanceof FacebookOperationCanceledException) {
            result = LoginClient.Result.a(this.b.getPendingRequest(), "User canceled log in.");
        } else {
            this.e2e = null;
            String message = facebookException.getMessage();
            if (facebookException instanceof FacebookServiceException) {
                FacebookRequestError requestError = ((FacebookServiceException) facebookException).getRequestError();
                str = String.format(Locale.ROOT, "%d", new Object[]{Integer.valueOf(requestError.getErrorCode())});
                message = requestError.toString();
            } else {
                str = null;
            }
            result = LoginClient.Result.a(this.b.getPendingRequest(), (String) null, message, str);
        }
        if (!Utility.isNullOrEmpty(this.e2e)) {
            b(this.e2e);
        }
        this.b.a(result);
    }

    /* access modifiers changed from: package-private */
    public abstract AccessTokenSource a_();

    /* access modifiers changed from: protected */
    public Bundle b(LoginClient.Request request) {
        String str;
        String str2;
        Bundle bundle = new Bundle();
        if (!Utility.isNullOrEmpty(request.a())) {
            String join = TextUtils.join(",", request.a());
            bundle.putString("scope", join);
            a("scope", join);
        }
        bundle.putString(ServerProtocol.DIALOG_PARAM_DEFAULT_AUDIENCE, request.c().getNativeProtocolAudience());
        bundle.putString(ServerProtocol.DIALOG_PARAM_STATE, a(request.e()));
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        String token = currentAccessToken != null ? currentAccessToken.getToken() : null;
        if (token == null || !token.equals(loadCookieToken())) {
            Utility.clearFacebookCookies(this.b.a());
            str = "access_token";
            str2 = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        } else {
            bundle.putString("access_token", token);
            str = "access_token";
            str2 = AppEventsConstants.EVENT_PARAM_VALUE_YES;
        }
        a(str, str2);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public String c() {
        return null;
    }
}
