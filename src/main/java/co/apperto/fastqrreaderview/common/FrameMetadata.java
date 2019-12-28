package co.apperto.fastqrreaderview.common;

public class FrameMetadata {
    private final int cameraFacing;
    private final int height;
    private final int rotation;
    private final int width;

    public static class Builder {
        private int cameraFacing;
        private int height;
        private int rotation;
        private int width;

        public FrameMetadata build() {
            return new FrameMetadata(this.width, this.height, this.rotation, this.cameraFacing);
        }

        public Builder setCameraFacing(int i) {
            this.cameraFacing = i;
            return this;
        }

        public Builder setHeight(int i) {
            this.height = i;
            return this;
        }

        public Builder setRotation(int i) {
            this.rotation = i;
            return this;
        }

        public Builder setWidth(int i) {
            this.width = i;
            return this;
        }
    }

    private FrameMetadata(int i, int i2, int i3, int i4) {
        this.width = i;
        this.height = i2;
        this.rotation = i3;
        this.cameraFacing = i4;
    }

    public int getCameraFacing() {
        return this.cameraFacing;
    }

    public int getHeight() {
        return this.height;
    }

    public int getRotation() {
        return this.rotation;
    }

    public int getWidth() {
        return this.width;
    }
}
