package com.tekartik.sqflite;

import io.flutter.plugin.common.MethodCall;

public class LogLevel {
    static Integer a(MethodCall methodCall) {
        return (Integer) methodCall.argument("logLevel");
    }

    static boolean a(int i) {
        return i >= 1;
    }

    static boolean b(int i) {
        return i >= 2;
    }
}
