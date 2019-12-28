package com.facebook;

import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;

public final class ProfileManager {
    public static final String ACTION_CURRENT_PROFILE_CHANGED = "com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED";
    public static final String EXTRA_NEW_PROFILE = "com.facebook.sdk.EXTRA_NEW_PROFILE";
    public static final String EXTRA_OLD_PROFILE = "com.facebook.sdk.EXTRA_OLD_PROFILE";
    private static volatile ProfileManager instance;
    private Profile currentProfile;
    private final LocalBroadcastManager localBroadcastManager;
    private final ProfileCache profileCache;

    ProfileManager(LocalBroadcastManager localBroadcastManager2, ProfileCache profileCache2) {
        Validate.notNull(localBroadcastManager2, "localBroadcastManager");
        Validate.notNull(profileCache2, "profileCache");
        this.localBroadcastManager = localBroadcastManager2;
        this.profileCache = profileCache2;
    }

    static ProfileManager a() {
        if (instance == null) {
            synchronized (ProfileManager.class) {
                if (instance == null) {
                    instance = new ProfileManager(LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()), new ProfileCache());
                }
            }
        }
        return instance;
    }

    private void sendCurrentProfileChangedBroadcast(Profile profile, Profile profile2) {
        Intent intent = new Intent(ACTION_CURRENT_PROFILE_CHANGED);
        intent.putExtra(EXTRA_OLD_PROFILE, profile);
        intent.putExtra(EXTRA_NEW_PROFILE, profile2);
        this.localBroadcastManager.sendBroadcast(intent);
    }

    private void setCurrentProfile(@Nullable Profile profile, boolean z) {
        Profile profile2 = this.currentProfile;
        this.currentProfile = profile;
        if (z) {
            if (profile != null) {
                this.profileCache.a(profile);
            } else {
                this.profileCache.b();
            }
        }
        if (!Utility.areObjectsEqual(profile2, profile)) {
            sendCurrentProfileChangedBroadcast(profile2, profile);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(@Nullable Profile profile) {
        setCurrentProfile(profile, true);
    }

    /* access modifiers changed from: package-private */
    public Profile b() {
        return this.currentProfile;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        Profile a = this.profileCache.a();
        if (a == null) {
            return false;
        }
        setCurrentProfile(a, false);
        return true;
    }
}
