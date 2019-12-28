package com.razorpay;

import proguard.annotation.Keep;
import proguard.annotation.KeepClassMembers;

@Keep
@KeepClassMembers
class BaseConstants {
    static final String CANCEL_PARAM = "/cancel?";
    static final String DEFAULT_PROGRESS_COLOR = "#4aa3df";
    static final String DEVELOPMENT = "development";
    static final int INCOMPATIBLE_PLUGIN = 7;
    static final int INVALID_OPTIONS = 3;
    static final String KEY_ID_PARAM = "key_id=";
    static final String KEY_MISSING_EXCEPTION = "Please set your Razorpay API key in AndroidManifest.xml";
    static final String METADATA_KEY = "com.razorpay.ApiKey";
    static final String METADATA_PLUGIN_PREFIX = "com.razorpay.plugin.";
    static final int NETWORK_ERROR = 2;
    static final int PARSING_ERROR = 4;
    static final int PAYMENT_CANCELED = 0;
    static final int PAYMENT_ERROR = 5;
    static final String PAYMENT_PREFERENCES_CACHE_KEY = "rzp_payment_preferences";
    static final String PRODUCTION = "production";
    static final String RZP_PAYMENTS_ENDPOINT = "https://api.razorpay.com/v1/payments/";
    static final String RZP_PROGRESS_COLOR = "#0783B4";
    static final String RZP_URL = "https://api.razorpay.com";
    static final String STATUS_PARAM = "/status?";
    static final int TLS_ERROR = 6;
    static final String TLS_ERROR_MESSAGE = "TLSv1  is not supported for security reasons";
    static final String UNKNOWN = "unknown";
    static final int UPI_REQUEST_CODE = 99;
    static final String UPI_URL_SCHEMA = "upi://pay";

    BaseConstants() {
    }
}
