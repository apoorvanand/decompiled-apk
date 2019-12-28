package io.flutter.view;

import android.content.Context;
import java.io.File;

class ResourcePaths {
    public static final String TEMPORARY_RESOURCE_PREFIX = ".org.chromium.Chromium.";

    ResourcePaths() {
    }

    public static File createTempFile(Context context, String str) {
        return File.createTempFile(TEMPORARY_RESOURCE_PREFIX, "_" + str, context.getCacheDir());
    }
}
