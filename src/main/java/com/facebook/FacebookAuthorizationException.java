package com.facebook;

public class FacebookAuthorizationException extends FacebookException {
    public FacebookAuthorizationException() {
    }

    public FacebookAuthorizationException(String str) {
        super(str);
    }

    public FacebookAuthorizationException(String str, Throwable th) {
        super(str, th);
    }

    public FacebookAuthorizationException(Throwable th) {
        super(th);
    }
}