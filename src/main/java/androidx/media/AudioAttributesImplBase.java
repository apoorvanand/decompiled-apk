package androidx.media;

import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.Arrays;

class AudioAttributesImplBase implements AudioAttributesImpl {
    int a = 0;
    int b = 0;
    int c = 0;
    int d = -1;

    AudioAttributesImplBase() {
    }

    AudioAttributesImplBase(int i, int i2, int i3, int i4) {
        this.b = i;
        this.c = i2;
        this.a = i3;
        this.d = i4;
    }

    public static AudioAttributesImpl fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new AudioAttributesImplBase(bundle.getInt("androidx.media.audio_attrs.CONTENT_TYPE", 0), bundle.getInt("androidx.media.audio_attrs.FLAGS", 0), bundle.getInt("androidx.media.audio_attrs.USAGE", 0), bundle.getInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        return this.b == audioAttributesImplBase.getContentType() && this.c == audioAttributesImplBase.getFlags() && this.a == audioAttributesImplBase.getUsage() && this.d == audioAttributesImplBase.d;
    }

    public Object getAudioAttributes() {
        return null;
    }

    public int getContentType() {
        return this.b;
    }

    public int getFlags() {
        int i = this.c;
        int legacyStreamType = getLegacyStreamType();
        if (legacyStreamType == 6) {
            i |= 4;
        } else if (legacyStreamType == 7) {
            i |= 1;
        }
        return i & 273;
    }

    public int getLegacyStreamType() {
        int i = this.d;
        return i != -1 ? i : AudioAttributesCompat.a(false, this.c, this.a);
    }

    public int getRawLegacyStreamType() {
        return this.d;
    }

    public int getUsage() {
        return this.a;
    }

    public int getVolumeControlStream() {
        return AudioAttributesCompat.a(true, this.c, this.a);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.a), Integer.valueOf(this.d)});
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("androidx.media.audio_attrs.USAGE", this.a);
        bundle.putInt("androidx.media.audio_attrs.CONTENT_TYPE", this.b);
        bundle.putInt("androidx.media.audio_attrs.FLAGS", this.c);
        int i = this.d;
        if (i != -1) {
            bundle.putInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", i);
        }
        return bundle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.d != -1) {
            sb.append(" stream=");
            sb.append(this.d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.a(this.a));
        sb.append(" content=");
        sb.append(this.b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.c).toUpperCase());
        return sb.toString();
    }
}
