package androidx.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import androidx.annotation.RequiresApi;
import androidx.core.util.ObjectsCompat;
import androidx.media.MediaSessionManager;

@RequiresApi(28)
class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {
    MediaSessionManager a;

    static final class RemoteUserInfoImplApi28 implements MediaSessionManager.RemoteUserInfoImpl {
        final MediaSessionManager.RemoteUserInfo a;

        RemoteUserInfoImplApi28(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.a = remoteUserInfo;
        }

        RemoteUserInfoImplApi28(String str, int i, int i2) {
            this.a = new MediaSessionManager.RemoteUserInfo(str, i, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RemoteUserInfoImplApi28)) {
                return false;
            }
            return this.a.equals(((RemoteUserInfoImplApi28) obj).a);
        }

        public String getPackageName() {
            return this.a.getPackageName();
        }

        public int getPid() {
            return this.a.getPid();
        }

        public int getUid() {
            return this.a.getUid();
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.a);
        }
    }

    MediaSessionManagerImplApi28(Context context) {
        super(context);
        this.a = (android.media.session.MediaSessionManager) context.getSystemService("media_session");
    }

    public boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        if (remoteUserInfoImpl instanceof RemoteUserInfoImplApi28) {
            return this.a.isTrustedForMediaControl(((RemoteUserInfoImplApi28) remoteUserInfoImpl).a);
        }
        return false;
    }
}
