package androidx.core.os;

import android.os.Build;
import android.os.LocaleList;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.Size;
import java.util.Locale;

public final class LocaleListCompat {
    static final LocaleListInterface a = (Build.VERSION.SDK_INT >= 24 ? new LocaleListCompatApi24Impl() : new LocaleListCompatBaseImpl());
    private static final LocaleListCompat sEmptyLocaleList = new LocaleListCompat();

    @RequiresApi(24)
    static class LocaleListCompatApi24Impl implements LocaleListInterface {
        private LocaleList mLocaleList = new LocaleList(new Locale[0]);

        LocaleListCompatApi24Impl() {
        }

        public boolean equals(Object obj) {
            return this.mLocaleList.equals(((LocaleListCompat) obj).unwrap());
        }

        public Locale get(int i) {
            return this.mLocaleList.get(i);
        }

        @Nullable
        public Locale getFirstMatch(String[] strArr) {
            LocaleList localeList = this.mLocaleList;
            if (localeList != null) {
                return localeList.getFirstMatch(strArr);
            }
            return null;
        }

        public Object getLocaleList() {
            return this.mLocaleList;
        }

        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        @IntRange(from = -1)
        public int indexOf(Locale locale) {
            return this.mLocaleList.indexOf(locale);
        }

        public boolean isEmpty() {
            return this.mLocaleList.isEmpty();
        }

        public void setLocaleList(@NonNull Locale... localeArr) {
            this.mLocaleList = new LocaleList(localeArr);
        }

        @IntRange(from = 0)
        public int size() {
            return this.mLocaleList.size();
        }

        public String toLanguageTags() {
            return this.mLocaleList.toLanguageTags();
        }

        public String toString() {
            return this.mLocaleList.toString();
        }
    }

    static class LocaleListCompatBaseImpl implements LocaleListInterface {
        private LocaleListHelper mLocaleList = new LocaleListHelper(new Locale[0]);

        LocaleListCompatBaseImpl() {
        }

        public boolean equals(Object obj) {
            return this.mLocaleList.equals(((LocaleListCompat) obj).unwrap());
        }

        public Locale get(int i) {
            return this.mLocaleList.a(i);
        }

        @Nullable
        public Locale getFirstMatch(String[] strArr) {
            LocaleListHelper localeListHelper = this.mLocaleList;
            if (localeListHelper != null) {
                return localeListHelper.a(strArr);
            }
            return null;
        }

        public Object getLocaleList() {
            return this.mLocaleList;
        }

        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        @IntRange(from = -1)
        public int indexOf(Locale locale) {
            return this.mLocaleList.a(locale);
        }

        public boolean isEmpty() {
            return this.mLocaleList.a();
        }

        public void setLocaleList(@NonNull Locale... localeArr) {
            this.mLocaleList = new LocaleListHelper(localeArr);
        }

        @IntRange(from = 0)
        public int size() {
            return this.mLocaleList.b();
        }

        public String toLanguageTags() {
            return this.mLocaleList.c();
        }

        public String toString() {
            return this.mLocaleList.toString();
        }
    }

    private LocaleListCompat() {
    }

    public static LocaleListCompat create(@NonNull Locale... localeArr) {
        LocaleListCompat localeListCompat = new LocaleListCompat();
        localeListCompat.setLocaleListArray(localeArr);
        return localeListCompat;
    }

    @NonNull
    public static LocaleListCompat forLanguageTags(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] split = str.split(",", -1);
        Locale[] localeArr = new Locale[split.length];
        for (int i = 0; i < localeArr.length; i++) {
            localeArr[i] = Build.VERSION.SDK_INT >= 21 ? Locale.forLanguageTag(split[i]) : LocaleHelper.a(split[i]);
        }
        LocaleListCompat localeListCompat = new LocaleListCompat();
        localeListCompat.setLocaleListArray(localeArr);
        return localeListCompat;
    }

    @Size(min = 1)
    @NonNull
    public static LocaleListCompat getAdjustedDefault() {
        if (Build.VERSION.SDK_INT >= 24) {
            return wrap(LocaleList.getAdjustedDefault());
        }
        return create(Locale.getDefault());
    }

    @Size(min = 1)
    @NonNull
    public static LocaleListCompat getDefault() {
        if (Build.VERSION.SDK_INT >= 24) {
            return wrap(LocaleList.getDefault());
        }
        return create(Locale.getDefault());
    }

    @NonNull
    public static LocaleListCompat getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    @RequiresApi(24)
    private void setLocaleList(LocaleList localeList) {
        int size = localeList.size();
        if (size > 0) {
            Locale[] localeArr = new Locale[size];
            for (int i = 0; i < size; i++) {
                localeArr[i] = localeList.get(i);
            }
            a.setLocaleList(localeArr);
        }
    }

    private void setLocaleListArray(Locale... localeArr) {
        a.setLocaleList(localeArr);
    }

    @RequiresApi(24)
    public static LocaleListCompat wrap(Object obj) {
        LocaleListCompat localeListCompat = new LocaleListCompat();
        if (obj instanceof LocaleList) {
            localeListCompat.setLocaleList((LocaleList) obj);
        }
        return localeListCompat;
    }

    public boolean equals(Object obj) {
        return a.equals(obj);
    }

    public Locale get(int i) {
        return a.get(i);
    }

    public Locale getFirstMatch(String[] strArr) {
        return a.getFirstMatch(strArr);
    }

    public int hashCode() {
        return a.hashCode();
    }

    @IntRange(from = -1)
    public int indexOf(Locale locale) {
        return a.indexOf(locale);
    }

    public boolean isEmpty() {
        return a.isEmpty();
    }

    @IntRange(from = 0)
    public int size() {
        return a.size();
    }

    @NonNull
    public String toLanguageTags() {
        return a.toLanguageTags();
    }

    public String toString() {
        return a.toString();
    }

    @Nullable
    public Object unwrap() {
        return a.getLocaleList();
    }
}
