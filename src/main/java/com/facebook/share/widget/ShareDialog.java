package com.facebook.share.widget;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.AppCall;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.DialogFeature;
import com.facebook.internal.DialogPresenter;
import com.facebook.internal.FacebookDialogBase;
import com.facebook.internal.FragmentWrapper;
import com.facebook.internal.NativeAppCallAttachmentStore;
import com.facebook.internal.Utility;
import com.facebook.share.Sharer;
import com.facebook.share.internal.CameraEffectFeature;
import com.facebook.share.internal.LegacyNativeDialogParameters;
import com.facebook.share.internal.NativeDialogParameters;
import com.facebook.share.internal.OpenGraphActionDialogFeature;
import com.facebook.share.internal.ShareContentValidation;
import com.facebook.share.internal.ShareDialogFeature;
import com.facebook.share.internal.ShareFeedContent;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.share.internal.ShareStoryFeature;
import com.facebook.share.internal.WebDialogParameters;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareStoryContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ShareDialog extends FacebookDialogBase<ShareContent, Sharer.Result> implements Sharer {
    private static final int DEFAULT_REQUEST_CODE = CallbackManagerImpl.RequestCodeOffset.Share.toRequestCode();
    private static final String FEED_DIALOG = "feed";
    private static final String TAG = "ShareDialog";
    private static final String WEB_OG_SHARE_DIALOG = "share_open_graph";
    public static final String WEB_SHARE_DIALOG = "share";
    private boolean isAutomaticMode;
    private boolean shouldFailOnDataError;

    private class CameraEffectHandler extends FacebookDialogBase<ShareContent, Sharer.Result>.ModeHandler {
        private CameraEffectHandler() {
            super();
        }

        public boolean canShow(ShareContent shareContent, boolean z) {
            return (shareContent instanceof ShareCameraEffectContent) && ShareDialog.canShowNative(shareContent.getClass());
        }

        public AppCall createAppCall(final ShareContent shareContent) {
            ShareContentValidation.validateForNativeShare(shareContent);
            final AppCall c = ShareDialog.this.c();
            final boolean shouldFailOnDataError = ShareDialog.this.getShouldFailOnDataError();
            DialogPresenter.setupAppCallForNativeDialog(c, new DialogPresenter.ParameterProvider() {
                public Bundle getLegacyParameters() {
                    return LegacyNativeDialogParameters.create(c.getCallId(), shareContent, shouldFailOnDataError);
                }

                public Bundle getParameters() {
                    return NativeDialogParameters.create(c.getCallId(), shareContent, shouldFailOnDataError);
                }
            }, ShareDialog.getFeature(shareContent.getClass()));
            return c;
        }

        public Object getMode() {
            return Mode.NATIVE;
        }
    }

    private class FeedHandler extends FacebookDialogBase<ShareContent, Sharer.Result>.ModeHandler {
        private FeedHandler() {
            super();
        }

        public boolean canShow(ShareContent shareContent, boolean z) {
            return (shareContent instanceof ShareLinkContent) || (shareContent instanceof ShareFeedContent);
        }

        public AppCall createAppCall(ShareContent shareContent) {
            Bundle bundle;
            ShareDialog shareDialog = ShareDialog.this;
            shareDialog.logDialogShare(shareDialog.a(), shareContent, Mode.FEED);
            AppCall c = ShareDialog.this.c();
            if (shareContent instanceof ShareLinkContent) {
                ShareLinkContent shareLinkContent = (ShareLinkContent) shareContent;
                ShareContentValidation.validateForWebShare(shareLinkContent);
                bundle = WebDialogParameters.createForFeed(shareLinkContent);
            } else {
                bundle = WebDialogParameters.createForFeed((ShareFeedContent) shareContent);
            }
            DialogPresenter.setupAppCallForWebDialog(c, ShareDialog.FEED_DIALOG, bundle);
            return c;
        }

        public Object getMode() {
            return Mode.FEED;
        }
    }

    public enum Mode {
        AUTOMATIC,
        NATIVE,
        WEB,
        FEED
    }

    private class NativeHandler extends FacebookDialogBase<ShareContent, Sharer.Result>.ModeHandler {
        private NativeHandler() {
            super();
        }

        public boolean canShow(ShareContent shareContent, boolean z) {
            boolean z2;
            if (shareContent == null || (shareContent instanceof ShareCameraEffectContent) || (shareContent instanceof ShareStoryContent)) {
                return false;
            }
            if (!z) {
                z2 = shareContent.getShareHashtag() != null ? DialogPresenter.canPresentNativeDialogWithFeature(ShareDialogFeature.HASHTAG) : true;
                if ((shareContent instanceof ShareLinkContent) && !Utility.isNullOrEmpty(((ShareLinkContent) shareContent).getQuote())) {
                    z2 &= DialogPresenter.canPresentNativeDialogWithFeature(ShareDialogFeature.LINK_SHARE_QUOTES);
                }
            } else {
                z2 = true;
            }
            return z2 && ShareDialog.canShowNative(shareContent.getClass());
        }

        public AppCall createAppCall(final ShareContent shareContent) {
            ShareDialog shareDialog = ShareDialog.this;
            shareDialog.logDialogShare(shareDialog.a(), shareContent, Mode.NATIVE);
            ShareContentValidation.validateForNativeShare(shareContent);
            final AppCall c = ShareDialog.this.c();
            final boolean shouldFailOnDataError = ShareDialog.this.getShouldFailOnDataError();
            DialogPresenter.setupAppCallForNativeDialog(c, new DialogPresenter.ParameterProvider() {
                public Bundle getLegacyParameters() {
                    return LegacyNativeDialogParameters.create(c.getCallId(), shareContent, shouldFailOnDataError);
                }

                public Bundle getParameters() {
                    return NativeDialogParameters.create(c.getCallId(), shareContent, shouldFailOnDataError);
                }
            }, ShareDialog.getFeature(shareContent.getClass()));
            return c;
        }

        public Object getMode() {
            return Mode.NATIVE;
        }
    }

    private class ShareStoryHandler extends FacebookDialogBase<ShareContent, Sharer.Result>.ModeHandler {
        private ShareStoryHandler() {
            super();
        }

        public boolean canShow(ShareContent shareContent, boolean z) {
            return (shareContent instanceof ShareStoryContent) && ShareDialog.canShowNative(shareContent.getClass());
        }

        public AppCall createAppCall(final ShareContent shareContent) {
            ShareContentValidation.validateForStoryShare(shareContent);
            final AppCall c = ShareDialog.this.c();
            final boolean shouldFailOnDataError = ShareDialog.this.getShouldFailOnDataError();
            DialogPresenter.setupAppCallForNativeDialog(c, new DialogPresenter.ParameterProvider() {
                public Bundle getLegacyParameters() {
                    return LegacyNativeDialogParameters.create(c.getCallId(), shareContent, shouldFailOnDataError);
                }

                public Bundle getParameters() {
                    return NativeDialogParameters.create(c.getCallId(), shareContent, shouldFailOnDataError);
                }
            }, ShareDialog.getFeature(shareContent.getClass()));
            return c;
        }

        public Object getMode() {
            return Mode.NATIVE;
        }
    }

    private class WebShareHandler extends FacebookDialogBase<ShareContent, Sharer.Result>.ModeHandler {
        private WebShareHandler() {
            super();
        }

        private SharePhotoContent createAndMapAttachments(SharePhotoContent sharePhotoContent, UUID uuid) {
            SharePhotoContent.Builder readFrom = new SharePhotoContent.Builder().readFrom(sharePhotoContent);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < sharePhotoContent.getPhotos().size(); i++) {
                SharePhoto sharePhoto = sharePhotoContent.getPhotos().get(i);
                Bitmap bitmap = sharePhoto.getBitmap();
                if (bitmap != null) {
                    NativeAppCallAttachmentStore.Attachment createAttachment = NativeAppCallAttachmentStore.createAttachment(uuid, bitmap);
                    sharePhoto = new SharePhoto.Builder().readFrom(sharePhoto).setImageUrl(Uri.parse(createAttachment.getAttachmentUrl())).setBitmap((Bitmap) null).build();
                    arrayList2.add(createAttachment);
                }
                arrayList.add(sharePhoto);
            }
            readFrom.setPhotos(arrayList);
            NativeAppCallAttachmentStore.addAttachments(arrayList2);
            return readFrom.build();
        }

        private String getActionName(ShareContent shareContent) {
            if ((shareContent instanceof ShareLinkContent) || (shareContent instanceof SharePhotoContent)) {
                return "share";
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                return ShareDialog.WEB_OG_SHARE_DIALOG;
            }
            return null;
        }

        public boolean canShow(ShareContent shareContent, boolean z) {
            return shareContent != null && ShareDialog.canShowWebCheck(shareContent);
        }

        public AppCall createAppCall(ShareContent shareContent) {
            ShareDialog shareDialog = ShareDialog.this;
            shareDialog.logDialogShare(shareDialog.a(), shareContent, Mode.WEB);
            AppCall c = ShareDialog.this.c();
            ShareContentValidation.validateForWebShare(shareContent);
            DialogPresenter.setupAppCallForWebDialog(c, getActionName(shareContent), shareContent instanceof ShareLinkContent ? WebDialogParameters.create((ShareLinkContent) shareContent) : shareContent instanceof SharePhotoContent ? WebDialogParameters.create(createAndMapAttachments((SharePhotoContent) shareContent, c.getCallId())) : WebDialogParameters.create((ShareOpenGraphContent) shareContent));
            return c;
        }

        public Object getMode() {
            return Mode.WEB;
        }
    }

    public ShareDialog(Activity activity) {
        super(activity, DEFAULT_REQUEST_CODE);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(DEFAULT_REQUEST_CODE);
    }

    public ShareDialog(Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }

    public ShareDialog(androidx.fragment.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }

    private ShareDialog(FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, DEFAULT_REQUEST_CODE);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(DEFAULT_REQUEST_CODE);
    }

    private ShareDialog(FragmentWrapper fragmentWrapper, int i) {
        super(fragmentWrapper, i);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(i);
    }

    public static boolean canShow(Class<? extends ShareContent> cls) {
        return canShowWebTypeCheck(cls) || canShowNative(cls);
    }

    /* access modifiers changed from: private */
    public static boolean canShowNative(Class<? extends ShareContent> cls) {
        DialogFeature feature = getFeature(cls);
        return feature != null && DialogPresenter.canPresentNativeDialogWithFeature(feature);
    }

    /* access modifiers changed from: private */
    public static boolean canShowWebCheck(ShareContent shareContent) {
        if (!canShowWebTypeCheck(shareContent.getClass())) {
            return false;
        }
        if (!(shareContent instanceof ShareOpenGraphContent)) {
            return true;
        }
        try {
            ShareInternalUtility.toJSONObjectForWeb((ShareOpenGraphContent) shareContent);
            return true;
        } catch (Exception e) {
            Utility.logd(TAG, "canShow returned false because the content of the Opem Graph object can't be shared via the web dialog", e);
            return false;
        }
    }

    private static boolean canShowWebTypeCheck(Class<? extends ShareContent> cls) {
        return ShareLinkContent.class.isAssignableFrom(cls) || ShareOpenGraphContent.class.isAssignableFrom(cls) || (SharePhotoContent.class.isAssignableFrom(cls) && AccessToken.isCurrentAccessTokenActive());
    }

    /* access modifiers changed from: private */
    public static DialogFeature getFeature(Class<? extends ShareContent> cls) {
        if (ShareLinkContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.SHARE_DIALOG;
        }
        if (SharePhotoContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.PHOTOS;
        }
        if (ShareVideoContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.VIDEO;
        }
        if (ShareOpenGraphContent.class.isAssignableFrom(cls)) {
            return OpenGraphActionDialogFeature.OG_ACTION_DIALOG;
        }
        if (ShareMediaContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.MULTIMEDIA;
        }
        if (ShareCameraEffectContent.class.isAssignableFrom(cls)) {
            return CameraEffectFeature.SHARE_CAMERA_EFFECT;
        }
        if (ShareStoryContent.class.isAssignableFrom(cls)) {
            return ShareStoryFeature.SHARE_STORY_ASSET;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void logDialogShare(Context context, ShareContent shareContent, Mode mode) {
        String str;
        if (this.isAutomaticMode) {
            mode = Mode.AUTOMATIC;
        }
        switch (mode) {
            case AUTOMATIC:
                str = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_AUTOMATIC;
                break;
            case WEB:
                str = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB;
                break;
            case NATIVE:
                str = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
                break;
            default:
                str = "unknown";
                break;
        }
        DialogFeature feature = getFeature(shareContent.getClass());
        String str2 = feature == ShareDialogFeature.SHARE_DIALOG ? "status" : feature == ShareDialogFeature.PHOTOS ? AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO : feature == ShareDialogFeature.VIDEO ? "video" : feature == OpenGraphActionDialogFeature.OG_ACTION_DIALOG ? "open_graph" : "unknown";
        AppEventsLogger newLogger = AppEventsLogger.newLogger(context);
        Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_show", str);
        bundle.putString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_TYPE, str2);
        newLogger.logSdkEvent("fb_share_dialog_show", (Double) null, bundle);
    }

    public static void show(Activity activity, ShareContent shareContent) {
        new ShareDialog(activity).show(shareContent);
    }

    public static void show(Fragment fragment, ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }

    public static void show(androidx.fragment.app.Fragment fragment, ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }

    private static void show(FragmentWrapper fragmentWrapper, ShareContent shareContent) {
        new ShareDialog(fragmentWrapper).show(shareContent);
    }

    /* access modifiers changed from: protected */
    public void a(CallbackManagerImpl callbackManagerImpl, FacebookCallback<Sharer.Result> facebookCallback) {
        ShareInternalUtility.registerSharerCallback(getRequestCode(), callbackManagerImpl, facebookCallback);
    }

    /* access modifiers changed from: protected */
    public List<FacebookDialogBase<ShareContent, Sharer.Result>.ModeHandler> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new NativeHandler());
        arrayList.add(new FeedHandler());
        arrayList.add(new WebShareHandler());
        arrayList.add(new CameraEffectHandler());
        arrayList.add(new ShareStoryHandler());
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public AppCall c() {
        return new AppCall(getRequestCode());
    }

    public boolean canShow(ShareContent shareContent, Mode mode) {
        Object obj = mode;
        if (mode == Mode.AUTOMATIC) {
            obj = a;
        }
        return a(shareContent, obj);
    }

    public boolean getShouldFailOnDataError() {
        return this.shouldFailOnDataError;
    }

    public void setShouldFailOnDataError(boolean z) {
        this.shouldFailOnDataError = z;
    }

    public void show(ShareContent shareContent, Mode mode) {
        this.isAutomaticMode = mode == Mode.AUTOMATIC;
        Object obj = mode;
        if (this.isAutomaticMode) {
            obj = a;
        }
        b(shareContent, obj);
    }
}
