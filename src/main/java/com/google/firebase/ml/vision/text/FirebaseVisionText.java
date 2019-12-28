package com.google.firebase.ml.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.vision.text.Text;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirebaseVisionText {
    private final String text;
    private final List<TextBlock> zzaxf = new ArrayList();

    public static class Element extends TextBase {
        Element(@NonNull com.google.android.gms.vision.text.Element element) {
            super(element);
        }

        public Element(@NonNull String str, @Nullable Rect rect, @NonNull List<RecognizedLanguage> list, @Nullable Float f) {
            super(str, rect, list, f);
        }

        @Nullable
        public /* bridge */ /* synthetic */ Rect getBoundingBox() {
            return super.getBoundingBox();
        }

        @Nullable
        public /* bridge */ /* synthetic */ Float getConfidence() {
            return super.getConfidence();
        }

        @Nullable
        public /* bridge */ /* synthetic */ Point[] getCornerPoints() {
            return super.getCornerPoints();
        }

        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }
    }

    public static class Line extends TextBase {
        @GuardedBy("this")
        private final List<Element> zzaxg;

        Line(@NonNull com.google.android.gms.vision.text.Line line) {
            super(line);
            this.zzaxg = new ArrayList();
            for (Text text : line.getComponents()) {
                if (text instanceof com.google.android.gms.vision.text.Element) {
                    this.zzaxg.add(new Element((com.google.android.gms.vision.text.Element) text));
                } else {
                    Log.e("FirebaseVisionText", "A subcomponent of line is should be an element!");
                }
            }
        }

        public Line(@NonNull String str, @Nullable Rect rect, @NonNull List<RecognizedLanguage> list, @NonNull List<Element> list2, @Nullable Float f) {
            super(str, rect, list, f);
            this.zzaxg = list2;
        }

        @Nullable
        public /* bridge */ /* synthetic */ Rect getBoundingBox() {
            return super.getBoundingBox();
        }

        @Nullable
        public /* bridge */ /* synthetic */ Float getConfidence() {
            return super.getConfidence();
        }

        @Nullable
        public /* bridge */ /* synthetic */ Point[] getCornerPoints() {
            return super.getCornerPoints();
        }

        public synchronized List<Element> getElements() {
            return this.zzaxg;
        }

        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }
    }

    static class TextBase {
        private final Float confidence;
        private final Point[] cornerPoints;
        private final String text;
        private final Rect zzaus;
        private final List<RecognizedLanguage> zzavl;

        TextBase(@NonNull Text text2) {
            Preconditions.checkNotNull(text2, "Text to construct FirebaseVisionText classes can't be null");
            this.confidence = null;
            this.text = text2.getValue();
            this.zzaus = text2.getBoundingBox();
            this.cornerPoints = text2.getCornerPoints();
            this.zzavl = Collections.emptyList();
        }

        private TextBase(@NonNull String str, @Nullable Rect rect, @NonNull List<RecognizedLanguage> list, @Nullable Float f) {
            Preconditions.checkNotNull(str, "Text string cannot be null");
            Preconditions.checkNotNull(list, "Text languages cannot be null");
            this.confidence = f;
            this.cornerPoints = null;
            this.text = str;
            this.zzaus = rect;
            this.zzavl = list;
        }

        @Nullable
        public Rect getBoundingBox() {
            return this.zzaus;
        }

        @Nullable
        public Float getConfidence() {
            return this.confidence;
        }

        @Nullable
        public Point[] getCornerPoints() {
            return this.cornerPoints;
        }

        public List<RecognizedLanguage> getRecognizedLanguages() {
            return this.zzavl;
        }

        public String getText() {
            String str = this.text;
            return str == null ? "" : str;
        }
    }

    public static class TextBlock extends TextBase {
        @GuardedBy("this")
        private final List<Line> zzaxh;

        TextBlock(@NonNull com.google.android.gms.vision.text.TextBlock textBlock) {
            super(textBlock);
            this.zzaxh = new ArrayList();
            for (Text text : textBlock.getComponents()) {
                if (text instanceof com.google.android.gms.vision.text.Line) {
                    this.zzaxh.add(new Line((com.google.android.gms.vision.text.Line) text));
                } else {
                    Log.e("FirebaseVisionText", "A subcomponent of textblock is should be a line!");
                }
            }
        }

        public TextBlock(@NonNull String str, @Nullable Rect rect, @NonNull List<RecognizedLanguage> list, @NonNull List<Line> list2, @Nullable Float f) {
            super(str, rect, list, f);
            this.zzaxh = list2;
        }

        @Nullable
        public /* bridge */ /* synthetic */ Rect getBoundingBox() {
            return super.getBoundingBox();
        }

        @Nullable
        public /* bridge */ /* synthetic */ Float getConfidence() {
            return super.getConfidence();
        }

        @Nullable
        public /* bridge */ /* synthetic */ Point[] getCornerPoints() {
            return super.getCornerPoints();
        }

        public synchronized List<Line> getLines() {
            return this.zzaxh;
        }

        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }
    }

    public FirebaseVisionText(@NonNull SparseArray<com.google.android.gms.vision.text.TextBlock> sparseArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sparseArray.size(); i++) {
            com.google.android.gms.vision.text.TextBlock textBlock = sparseArray.get(sparseArray.keyAt(i));
            if (textBlock != null) {
                TextBlock textBlock2 = new TextBlock(textBlock);
                this.zzaxf.add(textBlock2);
                if (sb.length() != 0) {
                    sb.append("\n");
                }
                if (textBlock.getValue() != null) {
                    sb.append(textBlock2.getText());
                }
            }
        }
        this.text = sb.toString();
    }

    public FirebaseVisionText(@NonNull String str, @NonNull List<TextBlock> list) {
        this.text = str;
        this.zzaxf.addAll(list);
    }

    public String getText() {
        return this.text;
    }

    public List<TextBlock> getTextBlocks() {
        return Collections.unmodifiableList(this.zzaxf);
    }
}
