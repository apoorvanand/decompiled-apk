package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Parcel;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(23)
class MediaBrowserCompatApi23 {

    interface ItemCallback {
        void onError(@NonNull String str);

        void onItemLoaded(Parcel parcel);
    }

    static class ItemCallbackProxy<T extends ItemCallback> extends MediaBrowser.ItemCallback {
        protected final T a;

        public ItemCallbackProxy(T t) {
            this.a = t;
        }

        public void onError(@NonNull String str) {
            this.a.onError(str);
        }

        public void onItemLoaded(MediaBrowser.MediaItem mediaItem) {
            T t;
            Parcel parcel;
            if (mediaItem == null) {
                t = this.a;
                parcel = null;
            } else {
                parcel = Parcel.obtain();
                mediaItem.writeToParcel(parcel, 0);
                t = this.a;
            }
            t.onItemLoaded(parcel);
        }
    }

    private MediaBrowserCompatApi23() {
    }

    public static Object createItemCallback(ItemCallback itemCallback) {
        return new ItemCallbackProxy(itemCallback);
    }

    public static void getItem(Object obj, String str, Object obj2) {
        ((MediaBrowser) obj).getItem(str, (MediaBrowser.ItemCallback) obj2);
    }
}
