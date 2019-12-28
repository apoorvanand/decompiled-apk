package androidx.media;

import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseIntArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AudioAttributesCompat implements VersionedParcelable {
    public static final int CONTENT_TYPE_MOVIE = 3;
    public static final int CONTENT_TYPE_MUSIC = 2;
    public static final int CONTENT_TYPE_SONIFICATION = 4;
    public static final int CONTENT_TYPE_SPEECH = 1;
    public static final int CONTENT_TYPE_UNKNOWN = 0;
    public static final int FLAG_AUDIBILITY_ENFORCED = 1;
    public static final int FLAG_HW_AV_SYNC = 16;
    private static final int[] SDK_USAGES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16};
    private static final int SUPPRESSIBLE_CALL = 2;
    private static final int SUPPRESSIBLE_NOTIFICATION = 1;
    private static final SparseIntArray SUPPRESSIBLE_USAGES = new SparseIntArray();
    private static final String TAG = "AudioAttributesCompat";
    public static final int USAGE_ALARM = 4;
    public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;
    public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;
    public static final int USAGE_ASSISTANCE_SONIFICATION = 13;
    public static final int USAGE_ASSISTANT = 16;
    public static final int USAGE_GAME = 14;
    public static final int USAGE_MEDIA = 1;
    public static final int USAGE_NOTIFICATION = 5;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;
    public static final int USAGE_NOTIFICATION_EVENT = 10;
    public static final int USAGE_NOTIFICATION_RINGTONE = 6;
    public static final int USAGE_UNKNOWN = 0;
    private static final int USAGE_VIRTUAL_SOURCE = 15;
    public static final int USAGE_VOICE_COMMUNICATION = 2;
    public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;
    static boolean a;
    AudioAttributesImpl b;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AttributeContentType {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AttributeUsage {
    }

    static abstract class AudioManagerHidden {
        public static final int STREAM_ACCESSIBILITY = 10;
        public static final int STREAM_BLUETOOTH_SCO = 6;
        public static final int STREAM_SYSTEM_ENFORCED = 7;
        public static final int STREAM_TTS = 9;

        private AudioManagerHidden() {
        }
    }

    public static class Builder {
        private int mContentType = 0;
        private int mFlags = 0;
        private int mLegacyStream = -1;
        private int mUsage = 0;

        public Builder() {
        }

        public Builder(AudioAttributesCompat audioAttributesCompat) {
            this.mUsage = audioAttributesCompat.getUsage();
            this.mContentType = audioAttributesCompat.getContentType();
            this.mFlags = audioAttributesCompat.getFlags();
            this.mLegacyStream = audioAttributesCompat.a();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0031, code lost:
            r3.mContentType = 4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0034, code lost:
            r3.mContentType = r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0036, code lost:
            r3.mUsage = androidx.media.AudioAttributesCompat.b(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x003c, code lost:
            return r3;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.media.AudioAttributesCompat.Builder a(int r4) {
            /*
                r3 = this;
                r0 = 1
                r1 = 4
                switch(r4) {
                    case 0: goto L_0x0034;
                    case 1: goto L_0x0031;
                    case 2: goto L_0x0031;
                    case 3: goto L_0x002f;
                    case 4: goto L_0x0031;
                    case 5: goto L_0x0031;
                    case 6: goto L_0x0027;
                    case 7: goto L_0x0021;
                    case 8: goto L_0x0031;
                    case 9: goto L_0x0031;
                    case 10: goto L_0x0034;
                    default: goto L_0x0005;
                }
            L_0x0005:
                java.lang.String r0 = "AudioAttributesCompat"
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Invalid stream type "
                r1.append(r2)
                r1.append(r4)
                java.lang.String r2 = " for AudioAttributesCompat"
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                android.util.Log.e(r0, r1)
                goto L_0x0036
            L_0x0021:
                int r2 = r3.mFlags
                r0 = r0 | r2
                r3.mFlags = r0
                goto L_0x0031
            L_0x0027:
                r3.mContentType = r0
                int r0 = r3.mFlags
                r0 = r0 | r1
                r3.mFlags = r0
                goto L_0x0036
            L_0x002f:
                r0 = 2
                goto L_0x0034
            L_0x0031:
                r3.mContentType = r1
                goto L_0x0036
            L_0x0034:
                r3.mContentType = r0
            L_0x0036:
                int r4 = androidx.media.AudioAttributesCompat.b(r4)
                r3.mUsage = r4
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media.AudioAttributesCompat.Builder.a(int):androidx.media.AudioAttributesCompat$Builder");
        }

        public AudioAttributesCompat build() {
            AudioAttributesImpl audioAttributesImpl;
            if (AudioAttributesCompat.a || Build.VERSION.SDK_INT < 21) {
                audioAttributesImpl = new AudioAttributesImplBase(this.mContentType, this.mFlags, this.mUsage, this.mLegacyStream);
            } else {
                AudioAttributes.Builder usage = new AudioAttributes.Builder().setContentType(this.mContentType).setFlags(this.mFlags).setUsage(this.mUsage);
                int i = this.mLegacyStream;
                if (i != -1) {
                    usage.setLegacyStreamType(i);
                }
                audioAttributesImpl = new AudioAttributesImplApi21(usage.build(), this.mLegacyStream);
            }
            return new AudioAttributesCompat(audioAttributesImpl);
        }

        public Builder setContentType(int i) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    this.mContentType = i;
                    break;
                default:
                    this.mUsage = 0;
                    break;
            }
            return this;
        }

        public Builder setFlags(int i) {
            this.mFlags = (i & 1023) | this.mFlags;
            return this;
        }

        public Builder setLegacyStreamType(int i) {
            if (i != 10) {
                this.mLegacyStream = i;
                return Build.VERSION.SDK_INT >= 21 ? a(i) : this;
            }
            throw new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback");
        }

        public Builder setUsage(int i) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    break;
                case 16:
                    if (AudioAttributesCompat.a || Build.VERSION.SDK_INT <= 25) {
                        i = 12;
                        break;
                    }
                default:
                    i = 0;
                    break;
            }
            this.mUsage = i;
            return this;
        }
    }

    static {
        SUPPRESSIBLE_USAGES.put(5, 1);
        SUPPRESSIBLE_USAGES.put(6, 2);
        SUPPRESSIBLE_USAGES.put(7, 2);
        SUPPRESSIBLE_USAGES.put(8, 1);
        SUPPRESSIBLE_USAGES.put(9, 1);
        SUPPRESSIBLE_USAGES.put(10, 1);
    }

    AudioAttributesCompat() {
    }

    AudioAttributesCompat(AudioAttributesImpl audioAttributesImpl) {
        this.b = audioAttributesImpl;
    }

    static int a(boolean z, int i, int i2) {
        if ((i & 1) == 1) {
            return z ? 1 : 7;
        }
        if ((i & 4) == 4) {
            return z ? 0 : 6;
        }
        switch (i2) {
            case 0:
                return z ? Integer.MIN_VALUE : 3;
            case 1:
            case 12:
            case 14:
            case 16:
                return 3;
            case 2:
                return 0;
            case 3:
                return z ? 0 : 8;
            case 4:
                return 4;
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
                return 5;
            case 6:
                return 2;
            case 11:
                return 10;
            case 13:
                return 1;
            default:
                if (!z) {
                    return 3;
                }
                throw new IllegalArgumentException("Unknown usage value " + i2 + " in audio attributes");
        }
    }

    static String a(int i) {
        switch (i) {
            case 0:
                return "USAGE_UNKNOWN";
            case 1:
                return "USAGE_MEDIA";
            case 2:
                return "USAGE_VOICE_COMMUNICATION";
            case 3:
                return "USAGE_VOICE_COMMUNICATION_SIGNALLING";
            case 4:
                return "USAGE_ALARM";
            case 5:
                return "USAGE_NOTIFICATION";
            case 6:
                return "USAGE_NOTIFICATION_RINGTONE";
            case 7:
                return "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
            case 8:
                return "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
            case 9:
                return "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
            case 10:
                return "USAGE_NOTIFICATION_EVENT";
            case 11:
                return "USAGE_ASSISTANCE_ACCESSIBILITY";
            case 12:
                return "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
            case 13:
                return "USAGE_ASSISTANCE_SONIFICATION";
            case 14:
                return "USAGE_GAME";
            case 16:
                return "USAGE_ASSISTANT";
            default:
                return "unknown usage " + i;
        }
    }

    static int b(int i) {
        switch (i) {
            case 0:
                return 2;
            case 1:
            case 7:
                return 13;
            case 2:
                return 6;
            case 3:
                return 1;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 2;
            case 8:
                return 3;
            case 10:
                return 11;
            default:
                return 0;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static AudioAttributesCompat fromBundle(Bundle bundle) {
        AudioAttributesImpl fromBundle = Build.VERSION.SDK_INT >= 21 ? AudioAttributesImplApi21.fromBundle(bundle) : AudioAttributesImplBase.fromBundle(bundle);
        if (fromBundle == null) {
            return null;
        }
        return new AudioAttributesCompat(fromBundle);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void setForceLegacyBehavior(boolean z) {
        a = z;
    }

    @Nullable
    public static AudioAttributesCompat wrap(@NonNull Object obj) {
        if (Build.VERSION.SDK_INT < 21 || a) {
            return null;
        }
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21((AudioAttributes) obj);
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        audioAttributesCompat.b = audioAttributesImplApi21;
        return audioAttributesCompat;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.b.getRawLegacyStreamType();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesCompat)) {
            return false;
        }
        AudioAttributesCompat audioAttributesCompat = (AudioAttributesCompat) obj;
        AudioAttributesImpl audioAttributesImpl = this.b;
        return audioAttributesImpl == null ? audioAttributesCompat.b == null : audioAttributesImpl.equals(audioAttributesCompat.b);
    }

    public int getContentType() {
        return this.b.getContentType();
    }

    public int getFlags() {
        return this.b.getFlags();
    }

    public int getLegacyStreamType() {
        return this.b.getLegacyStreamType();
    }

    public int getUsage() {
        return this.b.getUsage();
    }

    public int getVolumeControlStream() {
        return this.b.getVolumeControlStream();
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Bundle toBundle() {
        return this.b.toBundle();
    }

    public String toString() {
        return this.b.toString();
    }

    @Nullable
    public Object unwrap() {
        return this.b.getAudioAttributes();
    }
}
