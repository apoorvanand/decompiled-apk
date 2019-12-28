package com.facebook.share;

public interface Sharer {

    public static class Result {
        final String a;

        public Result(String str) {
            this.a = str;
        }

        public String getPostId() {
            return this.a;
        }
    }

    boolean getShouldFailOnDataError();

    void setShouldFailOnDataError(boolean z);
}
