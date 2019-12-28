package com.roughike.facebooklogin.facebooklogin;

import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.internal.NativeProtocol;
import com.facebook.login.LoginResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class FacebookLoginResults {
    static final Map<String, String> a = new HashMap<String, String>() {
        {
            put("status", "cancelledByUser");
        }
    };

    FacebookLoginResults() {
    }

    static Map<String, Object> a(final AccessToken accessToken) {
        if (accessToken == null) {
            return null;
        }
        return new HashMap<String, Object>() {
            {
                put("token", accessToken.getToken());
                put("userId", accessToken.getUserId());
                put("expires", Long.valueOf(accessToken.getExpires().getTime()));
                put(NativeProtocol.RESULT_ARGS_PERMISSIONS, new ArrayList(accessToken.getPermissions()));
                put("declinedPermissions", new ArrayList(accessToken.getDeclinedPermissions()));
            }
        };
    }

    static Map<String, String> a(final FacebookException facebookException) {
        return new HashMap<String, String>() {
            {
                put("status", "error");
                put("errorMessage", facebookException.getMessage());
            }
        };
    }

    static Map<String, Object> a(LoginResult loginResult) {
        final Map<String, Object> a2 = a(loginResult.getAccessToken());
        return new HashMap<String, Object>() {
            {
                put("status", "loggedIn");
                put("accessToken", a2);
            }
        };
    }
}
