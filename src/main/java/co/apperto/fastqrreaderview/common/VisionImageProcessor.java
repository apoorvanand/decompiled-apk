package co.apperto.fastqrreaderview.common;

import android.graphics.Bitmap;
import android.media.Image;
import java.nio.ByteBuffer;

public interface VisionImageProcessor {
    void process(Bitmap bitmap);

    void process(Image image, int i);

    void process(ByteBuffer byteBuffer, FrameMetadata frameMetadata);

    void stop();
}
