package io.flutter.plugins;

import co.apperto.fastqrreaderview.FastQrReaderViewPlugin;
import com.benjaminabel.vibration.VibrationPlugin;
import com.github.adee42.keyboardvisibility.KeyboardVisibilityPlugin;
import com.hashbnm.razorpayplugin.RazorpayPlugin;
import com.roughike.facebooklogin.facebooklogin.FacebookLoginPlugin;
import com.sidlatau.flutteremailsender.FlutterEmailSenderPlugin;
import com.tekartik.sqflite.SqflitePlugin;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.connectivity.ConnectivityPlugin;
import io.flutter.plugins.firebase.core.FirebaseCorePlugin;
import io.flutter.plugins.firebaseauth.FirebaseAuthPlugin;
import io.flutter.plugins.googlemaps.GoogleMapsPlugin;
import io.flutter.plugins.googlesignin.GoogleSignInPlugin;
import io.flutter.plugins.pathprovider.PathProviderPlugin;
import io.flutter.plugins.share.SharePlugin;
import io.flutter.plugins.sharedpreferences.SharedPreferencesPlugin;
import io.flutter.plugins.urllauncher.UrlLauncherPlugin;
import io.github.ponnamkarthik.toast.fluttertoast.FluttertoastPlugin;

public final class GeneratedPluginRegistrant {
    private static boolean alreadyRegisteredWith(PluginRegistry pluginRegistry) {
        String canonicalName = GeneratedPluginRegistrant.class.getCanonicalName();
        if (pluginRegistry.hasPlugin(canonicalName)) {
            return true;
        }
        pluginRegistry.registrarFor(canonicalName);
        return false;
    }

    public static void registerWith(PluginRegistry pluginRegistry) {
        if (!alreadyRegisteredWith(pluginRegistry)) {
            ConnectivityPlugin.registerWith(pluginRegistry.registrarFor("io.flutter.plugins.connectivity.ConnectivityPlugin"));
            FastQrReaderViewPlugin.registerWith(pluginRegistry.registrarFor("co.apperto.fastqrreaderview.FastQrReaderViewPlugin"));
            FirebaseAuthPlugin.registerWith(pluginRegistry.registrarFor("io.flutter.plugins.firebaseauth.FirebaseAuthPlugin"));
            FirebaseCorePlugin.registerWith(pluginRegistry.registrarFor("io.flutter.plugins.firebase.core.FirebaseCorePlugin"));
            FlutterEmailSenderPlugin.registerWith(pluginRegistry.registrarFor("com.sidlatau.flutteremailsender.FlutterEmailSenderPlugin"));
            FacebookLoginPlugin.registerWith(pluginRegistry.registrarFor("com.roughike.facebooklogin.facebooklogin.FacebookLoginPlugin"));
            FluttertoastPlugin.registerWith(pluginRegistry.registrarFor("io.github.ponnamkarthik.toast.fluttertoast.FluttertoastPlugin"));
            GoogleMapsPlugin.registerWith(pluginRegistry.registrarFor("io.flutter.plugins.googlemaps.GoogleMapsPlugin"));
            GoogleSignInPlugin.registerWith(pluginRegistry.registrarFor("io.flutter.plugins.googlesignin.GoogleSignInPlugin"));
            KeyboardVisibilityPlugin.registerWith(pluginRegistry.registrarFor("com.github.adee42.keyboardvisibility.KeyboardVisibilityPlugin"));
            PathProviderPlugin.registerWith(pluginRegistry.registrarFor("io.flutter.plugins.pathprovider.PathProviderPlugin"));
            RazorpayPlugin.registerWith(pluginRegistry.registrarFor("com.hashbnm.razorpayplugin.RazorpayPlugin"));
            SharePlugin.registerWith(pluginRegistry.registrarFor("io.flutter.plugins.share.SharePlugin"));
            SharedPreferencesPlugin.registerWith(pluginRegistry.registrarFor("io.flutter.plugins.sharedpreferences.SharedPreferencesPlugin"));
            SqflitePlugin.registerWith(pluginRegistry.registrarFor("com.tekartik.sqflite.SqflitePlugin"));
            UrlLauncherPlugin.registerWith(pluginRegistry.registrarFor("io.flutter.plugins.urllauncher.UrlLauncherPlugin"));
            VibrationPlugin.registerWith(pluginRegistry.registrarFor("com.benjaminabel.vibration.VibrationPlugin"));
        }
    }
}
