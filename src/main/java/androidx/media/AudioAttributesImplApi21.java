package androidx.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@TargetApi(21)
class AudioAttributesImplApi21 implements AudioAttributesImpl {
    private static final String TAG = "AudioAttributesCompat21";
    static Method c;
    AudioAttributes a;
    int b;

    AudioAttributesImplApi21() {
        this.b = -1;
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes, int i) {
        this.b = -1;
        this.a = audioAttributes;
        this.b = i;
    }

    static Method a() {
        try {
            if (c == null) {
                c = AudioAttributes.class.getMethod("toLegacyStreamType", new Class[]{AudioAttributes.class});
            }
            return c;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static AudioAttributesImpl fromBundle(Bundle bundle) {
        AudioAttributes audioAttributes;
        if (bundle == null || (audioAttributes = (AudioAttributes) bundle.getParcelable("androidx.media.audio_attrs.FRAMEWORKS")) == null) {
            return null;
        }
        return new AudioAttributesImplApi21(audioAttributes, bundle.getInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplApi21)) {
            return false;
        }
        return this.a.equals(((AudioAttributesImplApi21) obj).a);
    }

    public Object getAudioAttributes() {
        return this.a;
    }

    public int getContentType() {
        return this.a.getContentType();
    }

    public int getFlags() {
        return this.a.getFlags();
    }

    public int getLegacyStreamType() {
        int i = this.b;
        if (i != -1) {
            return i;
        }
        Method a2 = a();
        if (a2 == null) {
            Log.w(TAG, "No AudioAttributes#toLegacyStreamType() on API: " + Build.VERSION.SDK_INT);
            return -1;
        }
        try {
            return ((Integer) a2.invoke((Object) null, new Object[]{this.a})).intValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.w(TAG, "getLegacyStreamType() failed on API: " + Build.VERSION.SDK_INT, e);
            return -1;
        }
    }

    public int getRawLegacyStreamType() {
        return this.b;
    }

    public int getUsage() {
        return this.a.getUsage();
    }

    public int getVolumeControlStream() {
        return Build.VERSION.SDK_INT >= 26 ? this.a.getVolumeControlStream() : AudioAttributesCompat.a(true, getFlags(), getUsage());
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("androidx.media.audio_attrs.FRAMEWORKS", this.a);
        int i = this.b;
        if (i != -1) {
            bundle.putInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", i);
        }
        return bundle;
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.a;
    }
}
