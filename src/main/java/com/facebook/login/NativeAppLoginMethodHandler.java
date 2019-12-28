package com.facebook.login;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.login.LoginClient;

abstract class NativeAppLoginMethodHandler extends LoginMethodHandler {
    NativeAppLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    NativeAppLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    private String getError(Bundle bundle) {
        String string = bundle.getString("error");
        return string == null ? bundle.getString(NativeProtocol.BRIDGE_ARG_ERROR_TYPE) : string;
    }

    private String getErrorMessage(Bundle bundle) {
        String string = bundle.getString(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE);
        return string == null ? bundle.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION) : string;
    }

    private LoginClient.Result handleResultCancel(LoginClient.Request request, Intent intent) {
        Bundle extras = intent.getExtras();
        String error = getError(extras);
        String obj = extras.get(NativeProtocol.BRIDGE_ARG_ERROR_CODE) != null ? extras.get(NativeProtocol.BRIDGE_ARG_ERROR_CODE).toString() : null;
        return ServerProtocol.errorConnectionFailure.equals(obj) ? LoginClient.Result.a(request, error, getErrorMessage(extras), obj) : LoginClient.Result.a(request, error);
    }

    private LoginClient.Result handleResultOk(LoginClient.Request request, Intent intent) {
        Bundle extras = intent.getExtras();
        String error = getError(extras);
        String obj = extras.get(NativeProtocol.BRIDGE_ARG_ERROR_CODE) != null ? extras.get(NativeProtocol.BRIDGE_ARG_ERROR_CODE).toString() : null;
        String errorMessage = getErrorMessage(extras);
        String string = extras.getString("e2e");
        if (!Utility.isNullOrEmpty(string)) {
            b(string);
        }
        if (error == null && obj == null && errorMessage == null) {
            try {
                return LoginClient.Result.a(request, createAccessTokenFromWebBundle(request.a(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, request.d()));
            } catch (FacebookException e) {
                return LoginClient.Result.a(request, (String) null, e.getMessage());
            }
        } else if (ServerProtocol.errorsProxyAuthDisabled.contains(error)) {
            return null;
        } else {
            return ServerProtocol.errorsUserCanceled.contains(error) ? LoginClient.Result.a(request, (String) null) : LoginClient.Result.a(request, error, errorMessage, obj);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i, int i2, Intent intent) {
        LoginClient.Request pendingRequest = this.b.getPendingRequest();
        LoginClient.Result a = intent == null ? LoginClient.Result.a(pendingRequest, "Operation canceled") : i2 == 0 ? handleResultCancel(pendingRequest, intent) : i2 != -1 ? LoginClient.Result.a(pendingRequest, "Unexpected resultCode from authorization.", (String) null) : handleResultOk(pendingRequest, intent);
        if (a != null) {
            this.b.a(a);
            return true;
        }
        this.b.f();
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(Intent intent, int i) {
        if (intent == null) {
            return false;
        }
        try {
            this.b.getFragment().startActivityForResult(intent, i);
            return true;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public abstract boolean a(LoginClient.Request request);
}
