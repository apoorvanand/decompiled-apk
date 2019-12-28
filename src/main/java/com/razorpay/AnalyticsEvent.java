package com.razorpay;

import androidx.core.app.NotificationCompat;
import proguard.annotation.Keep;
import proguard.annotation.KeepClassMembers;

@Keep
@KeepClassMembers
enum AnalyticsEvent {
    CALLING_ON_SUCCESS("ch:and", "merch:cb:onsuccess", "calling"),
    MERCHANT_ON_SUCCESS_CALLED("ch:and", "merch:cb:onsuccess", "called"),
    CALLING_ON_ERROR("ch:and", "merch:cb:onerror", "calling"),
    MERCHANT_ON_ERROR_CALLED("ch:and", "merch:cb:onerror", "called"),
    EXTERNAL_WALLET_SELECTED("ch:and", "merch:cb:external", "selected"),
    CALLING_EXTERNAL_WALLET_SELECTED("ch:and", "merch:cb:external", "calling"),
    MERCHANT_EXTERNAL_WALLET_SELECTED_CALLED("ch:and", "merch:cb:external", "called"),
    HANDOVER_ERROR("ch:and", "merch:cb:handover", "fail"),
    CHECKOUT_LOADED("ch:and", BuildConfig.SDK_TYPE, "loaded"),
    INTERNAL_DESTROY_METHOD_CALLED("ch:and", BuildConfig.SDK_TYPE, "destroy"),
    ACTIVITY_ONDESTROY_CALLED("ch:and", "checkout:activity", "destroy"),
    CHECKOUT_SOFT_BACK_PRESSED("ch:and", "back", "soft"),
    CHECKOUT_HARD_BACK_PRESSED("ch:and", "back", "hard"),
    CHECKOUT_PAYMENT_COMPLETE("ch:and", "payment", "complete"),
    CHECKOUT_INIT("ch:and", "checkout:activity:init", "start"),
    CHECKOUT_SUBMIT("ch:and", BuildConfig.SDK_TYPE, "submit"),
    CARD_SAVING_START("ch:and", "cardsaving", "start"),
    CARD_SAVING_END("ch:and", "cardsaving", "end"),
    MULTIPLE_TOKEN_EVENT("ch:and", "cardsaving", "multiple:tokens"),
    SHARE_PREFERENCES_SECURITY_EXCEPTION("ch:and", "cardsaving", "deprecated"),
    WEB_VIEW_JS_ERROR("ch:and", "js:console", "error"),
    WEB_VIEW_SSL_ERROR("ch:and", "webview", "ssl:error"),
    ALERT_PAYMENT_CONTINUE("ch:and", "back:alert", "pymnt:continue"),
    ALERT_PAYMENT_CANCELLED("ch:and", "back:alert", "pymnt:cancelled"),
    PAYMENT_ID_ATTACHED("ch:and", "payment:id", "attached"),
    WEB_VIEW_UNEXPECTED_NULL("ch:and", "webview", "null"),
    WEB_VIEW_PRIMARY_TO_SECONDARY_SWITCH("ch:and", "webview", "primary:secondary"),
    WEB_VIEW_SECONDARY_TO_PRIMARY_SWITCH("ch:and", "webview", "secondary:primary"),
    NATIVE_INTENT_CALLED("ch:and", "intent", "called"),
    NATIVE_INTENT_ONACTIVITY_RESULT("ch:and", "intent", "result:received"),
    CHECKOUT_TLS_ERROR("ch:and", "tls", "error"),
    CHECKOUT_AMAZON_PROCESS_PAYMENT_CALLED("ch:amz_pay:and", "process:payment", "called"),
    CHECKOUT_AMAZON_INTERNAL_CALLBACK_SUCCESS("ch:amz_pay:and", "internal:callback", "success"),
    CHECKOUT_AMAZON_INTERNAL_CALLBACK_ERROR("ch:amz_pay:and", "internal:callback", "error"),
    CHECKOUT_AMAZON_INTERNAL_CALLBACK_ERROR_EXCEPTION("ch:amz_pay:and", "internal:callback", "error:exception"),
    CHECKOUT_AMAZON_RAZORPAY_AMAZON_CALLING_PROCESS_PAYMENT("ch:amz_pay:and", "razorpay_amazon", "calling:process_payment"),
    CHECKOUT_AMAZON_RAZORPAY_AMAZON_CALLING_PROCESS_PAYMENT_EXCEPTION("ch:amz_pay:and", "razorpay_amazon", "exception:process_payment"),
    CHECKOUT_AMAZON_ON_ERROR_CALLED("ch:amz_pay:and", "on:error", "called"),
    PRELOAD_START("ch:and", "preload", "start"),
    PRELOAD_COMPLETE("ch:and", "preload", "complete"),
    PRELOAD_ABORT("ch:and", "preload", "abort"),
    MAGIC_PAYMENT_FLOW_START("magic:and", "payment", "start"),
    MAGIC_PAYMENT_FLOW_END("magic:and", "payment", "end"),
    MAGIC_PAGE_FINISH("magic:and", "magic", "page:finish"),
    MAGIC_SHOW_WEBVIEW("magic:and", "webview", "show"),
    MAGIC_SHOW_WEBVIEW_CLICKED("magic:and", "show:webview", "clicked"),
    MAGIC_RESET("magic:and", "magic", "reset"),
    MAGIC_TIMER_CALLBACK("magic:and", "timer", "callback:received"),
    MAGIC_OTP_EXTRACT("magic:and", "otp", "extract"),
    MAGIC_OTP_SHOW("magic:and", "otp", "show"),
    MAGIC_OTP_RESEND_CLICKED("magic:and", "otp:resend", "clicked"),
    MAGIC_OTP_MANUAL_VIEW_CLICKED("magic:and", "otp:manual:view", "clicked"),
    MAGIC_USE_MANUAL_OTP_CLICKED("magic:and", "otp:manual:use", "clicked"),
    MAGIC_USE_OTP_CLICKED("magic:and", "otp:use", "clicked"),
    MAGIC_CHOICE_OTP_CLICKED("magic:and", "choice:otp", "clicked"),
    MAGIC_CHOICE_PASSWORD_CLICKED("magic:and", "choice:password", "clicked"),
    MAGIC_SET_PAGE_TYPE("magic:and", "pagetype", ""),
    MAGIC_INIT_END("magic:and", "init", "end"),
    SMS_PERMISSION_ALREADY_GRANTED("assist:and", "perm:sms", "already:granted"),
    SMS_PERMISSION_ALREADY_NOT_GRANTED("assist:and", "perm:sms", "already:not:granted"),
    SMS_PERMISSION_ALREADY_GRANTED_BY_MERCHANT("assist:and", "perm:sms", "already:granted:merchant"),
    SMS_PERMISSION_ALREADY_ASKED("assist:and", "perm:sms", "already:asked"),
    SMS_PERMISSION_ALREADY_NOT_ASKED("assist:and", "perm:sms", "already:not:asked"),
    SMS_PERMISSION_NOW_GRANTED("assist:and", "perm:sms", "now:granted"),
    SMS_PERMISSION_NOW_DENIED("assist:and", "perm:sms", "now:denied"),
    PAGE_LOAD_START("assist:and", "rzpassist", "page:start"),
    PAGE_LOAD_FINISH("assist:and", "rzpassist", "page:finish"),
    CHECKOUT_PAGE_LOAD_START("ch:and", "rzpassist", "ch:page:start"),
    CHECKOUT_PAGE_LOAD_FINISH("ch:and", "rzpassist", "ch:page:finish"),
    OTP_RECEIVED("assist:and", "rzpassist", "otp:receive"),
    ERROR_LOGGED("assist:and", "error", "log"),
    PAGE_NOT_IDENTIFIED("assist:and", "pagetype", "unindentified"),
    JS_EVENT("", "", ""),
    FETCH_PREFERENCES_CALLED("cu:and", "preferences", NotificationCompat.CATEGORY_CALL),
    FETCH_PREFERENCES_CACHE_HIT("cu:and", "preferences", "cache:hit"),
    FETCH_PREFERENCES_CACHE_MISS("cu:and", "preferences", "cache:miss"),
    FETCH_PREFERENCES_CALL_SUCCESS("cu:and", "preferences", "call:success"),
    FETCH_PREFERENCES_METHODS_CALL_FAIL("cu:and", "preferences", "call:fail"),
    CUSTOM_UI_INIT_END("cu:and", "init", "end"),
    CUSTOM_UI_PAYMENT_COMPLETE("cu:and", "payment", "complete"),
    CUSTOM_UI_SUBMIT_START("cu:and", "submit", "start"),
    CUSTOM_UI_BACK_PRESSED_SOFT("cu:and", "back", "soft"),
    CUSTOM_UI_BACK_PRESSED_HARD("cu:and", "back", "hard"),
    CUSTOM_UI_PAYMENT_ID_ATTACHED("cu:and", "payment:id", "attached"),
    CUSTOM_UI_MERCHANT_KEY_CHANGED("cu:and", "merchant_key", "changed"),
    CUSTOM_UI_GET_APPS_SUPPORTING_UPI("cu:and", "upi:apps", "called"),
    CUSTOM_UI_SHOULD_SHOW_UPI_INTENT_METHOD("cu:and", "upi_intent:should_show", "called"),
    CUSTOM_UI_UPI_APP_PASSED("cu:and", "upi_app:name", "passed"),
    CUSTOM_UI_NATIVE_INTENT_CALLED("cu:and", "intent", "called"),
    CUSTOM_UI_NATIVE_INTENT_ONACTIVITY_RESULT("cu:and", "intent", "result:received"),
    CUSTOM_UI_UPI_INTENT_APPS_PREFERENCE_PASSED("cu:and", "upi_apps:preference", "passed"),
    CUSTOM_UI_CUSTOM_APP_CHOOSER_SHOWN("cu:and", "upi:custom_app_chooser", "shown"),
    CUSTOM_UI_UPI_MERCHANT_PASSED_APP_LAUNCHED("cu:and", "upi:merchant_passed_app", "launched"),
    CUSTOM_UI_UPI_APP_LAUNCHED("cu:and", "upi:app_custom_chooser", "launched"),
    CUSTOM_UI_UPI_INTENT_APPS_PREFERRED_ORDER_PASSED("cu:and", "upi_apps:preferred_order", "passed"),
    CUSTOM_UI_UPI_INTENT_APPS_OTHER_ORDER_PASSED("cu:and", "upi_apps:other_order", "passed"),
    CUSTOM_UI_TLS_ERROR("cu:and", "tls", "error"),
    CUSTOMUI_INTERNAL_CALLBACK_SUCCESS("cu:and", "internal:callback", "success"),
    CUSTOMUI_INTERNAL_CALLBACK_ERROR("cu:and", "internal:callback", "error"),
    CUSTOMUI_INTERNAL_CALLBACK_ERROR_EXCEPTION("cu:and", "internal:callback", "error:exception"),
    CUSTOMUI_METHOD_AMAZONPAY_PASSED("cu:and", "payment_method", "amazon_pay:passed"),
    AMAZON_PROCESS_SIGN_ENCRYPT_RESPONSE_CALLED("amz_pay:and", "cb:sign_encrypt_response", "called"),
    AMAZON_PROCESS_SIGN_ENCRYPT_RESPONSE_EXCEPTION("amz_pay:and", "cb:sign_encrypt_response", "exception_received"),
    AMAZON_PROCESS_VERIFY_SIGNATURE_CALLED("amz_pay:and", "cb:sign_encrypt_response", "called"),
    AMAZON_PROCESS_VERIFY_SIGNATURE_EXCEPTION("amz_pay:and", "cb:sign_encrypt_response", "exception_received"),
    AMAZON_PROCESS_VERIFY_SIGNATURE_INTERNAL_SUCCESS_CALLED("amz_pay:and", "cb:sign_encrypt_response", "calling:internal_success"),
    AMAZON_PROCESS_VERIFY_SIGNATURE_INTERNAL_ERROR_CALLED("amz_pay:and", "cb:sign_encrypt_response", "calling:internal_error"),
    AMAZON_PROCESS_PAYMENT_CALLED("amz_pay:and", "process:payment", "called"),
    AMAZON_INTERNAL_CALLBACK_SUCCESS("amz_pay:and", "internal:callback", "success"),
    AMAZON_INTERNAL_CALLBACK_ERROR("amz_pay:and", "internal:callback", "error"),
    AMAZON_PWAIN_CALLBACK_SUCCESS("amz_pay:and", "pwain:callback", "success"),
    AMAZON_PWAIN_CALLBACK_FAILURE("amz_pay:and", "pwain:callback", "failure"),
    AMAZON_PWAIN_CALLBACK_PAYMENT_VALIDATION_FAILURE("amz_pay:and", "pwain:callback", "payment_validation:failure"),
    AMAZON_PWAIN_CALLBACK_MERCHANT_BACKEND_ERROR("amz_pay:and", "pwain:callback", "merchant_backend:error"),
    AMAZON_PWAIN_CALLBACK_MOBILE_SDK_ERROR("amz_pay:and", "pwain:callback", "mobile_sdk:error"),
    AMAZON_PWAIN_CALLBACK_NETWORK_UNAVAILABLE("amz_pay:and", "pwain:callback", "network:unavailable"),
    AMAZON_PWAIN_CALLBACK_ON_CANCEL("amz_pay:and", "pwain:callback", "on:cancel");
    
    private String eventName;

    private AnalyticsEvent(String str, String str2, String str3) {
        this.eventName = constructEventName(str, str2, str3);
    }

    static String constructEventName(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_");
        sb.append(str2);
        sb.append("_");
        sb.append(str3);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String getEventName() {
        return this.eventName;
    }

    /* access modifiers changed from: package-private */
    public final void setEventName(String str) {
        this.eventName = str;
    }
}
