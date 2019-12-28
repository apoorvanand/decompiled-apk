package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.facebook.share.model.ShareMedia;

public final class ShareVideo extends ShareMedia {
    public static final Parcelable.Creator<ShareVideo> CREATOR = new Parcelable.Creator<ShareVideo>() {
        public ShareVideo createFromParcel(Parcel parcel) {
            return new ShareVideo(parcel);
        }

        public ShareVideo[] newArray(int i) {
            return new ShareVideo[i];
        }
    };
    private final Uri localUrl;

    public static final class Builder extends ShareMedia.Builder<ShareVideo, Builder> {
        /* access modifiers changed from: private */
        public Uri localUrl;

        /* access modifiers changed from: package-private */
        public Builder b(Parcel parcel) {
            return readFrom((ShareVideo) parcel.readParcelable(ShareVideo.class.getClassLoader()));
        }

        public ShareVideo build() {
            return new ShareVideo(this);
        }

        public Builder readFrom(ShareVideo shareVideo) {
            return shareVideo == null ? this : ((Builder) super.readFrom(shareVideo)).setLocalUrl(shareVideo.getLocalUrl());
        }

        public Builder setLocalUrl(@Nullable Uri uri) {
            this.localUrl = uri;
            return this;
        }
    }

    ShareVideo(Parcel parcel) {
        super(parcel);
        this.localUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }

    private ShareVideo(Builder builder) {
        super((ShareMedia.Builder) builder);
        this.localUrl = builder.localUrl;
    }

    public int describeContents() {
        return 0;
    }

    @Nullable
    public Uri getLocalUrl() {
        return this.localUrl;
    }

    public ShareMedia.Type getMediaType() {
        return ShareMedia.Type.VIDEO;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.localUrl, 0);
    }
}
