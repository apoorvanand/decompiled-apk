package com.google.firebase.ml.vision.document;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_ml.zzja;
import com.google.android.gms.internal.firebase_ml.zzjf;
import com.google.android.gms.internal.firebase_ml.zzjr;
import com.google.android.gms.internal.firebase_ml.zzjs;
import com.google.android.gms.internal.firebase_ml.zzjx;
import com.google.android.gms.internal.firebase_ml.zzjy;
import com.google.android.gms.internal.firebase_ml.zzjz;
import com.google.android.gms.internal.firebase_ml.zzkd;
import com.google.android.gms.internal.firebase_ml.zzol;
import com.google.android.gms.internal.firebase_ml.zzpj;
import com.google.firebase.ml.vision.text.RecognizedLanguage;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirebaseVisionDocumentText {
    private final List<Block> blocks;
    private final String text;

    public static class Block extends DocumentTextBase {
        private final List<Paragraph> paragraphs;

        private Block(@NonNull List<RecognizedLanguage> list, @Nullable RecognizedBreak recognizedBreak, @Nullable Rect rect, @NonNull List<Paragraph> list2, @NonNull String str, Float f) {
            super(list, recognizedBreak, rect, str, f);
            this.paragraphs = list2;
        }

        /* access modifiers changed from: private */
        public static Block zza(@NonNull zzja zzja, float f) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            List<RecognizedLanguage> zze = zzpj.zze(zzja.zzhb());
            if (zzja.getParagraphs() != null) {
                for (zzjs next : zzja.getParagraphs()) {
                    if (next != null) {
                        Paragraph zzb = Paragraph.zza(next, f);
                        if (sb.length() != 0) {
                            sb.append("\n");
                        }
                        sb.append(zzb.getText());
                        arrayList.add(zzb);
                    }
                }
            }
            return new Block(zze, new RecognizedBreak(), zzol.zza(zzja.zzha(), f), arrayList, sb.toString(), zzja.getConfidence());
        }

        @Nullable
        public /* bridge */ /* synthetic */ Rect getBoundingBox() {
            return super.getBoundingBox();
        }

        @Nullable
        public /* bridge */ /* synthetic */ Float getConfidence() {
            return super.getConfidence();
        }

        public List<Paragraph> getParagraphs() {
            return this.paragraphs;
        }

        @Nullable
        public /* bridge */ /* synthetic */ RecognizedBreak getRecognizedBreak() {
            return super.getRecognizedBreak();
        }

        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }
    }

    static class DocumentTextBase {
        private final Float confidence;
        private final String text;
        private final Rect zzaus;
        private final List<RecognizedLanguage> zzavl;
        private final RecognizedBreak zzavm;

        DocumentTextBase(@NonNull List<RecognizedLanguage> list, @Nullable RecognizedBreak recognizedBreak, @Nullable Rect rect, @NonNull String str, @Nullable Float f) {
            this.text = str;
            this.zzavl = list;
            this.zzavm = recognizedBreak;
            this.zzaus = rect;
            this.confidence = f;
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
        public RecognizedBreak getRecognizedBreak() {
            return this.zzavm;
        }

        public List<RecognizedLanguage> getRecognizedLanguages() {
            return this.zzavl;
        }

        public String getText() {
            return this.text;
        }
    }

    public static class Paragraph extends DocumentTextBase {
        private final List<Word> words;

        private Paragraph(@NonNull List<RecognizedLanguage> list, @Nullable RecognizedBreak recognizedBreak, @Nullable Rect rect, @NonNull List<Word> list2, @NonNull String str, @Nullable Float f) {
            super(list, recognizedBreak, rect, str, f);
            this.words = list2;
        }

        /* access modifiers changed from: private */
        public static Paragraph zza(@NonNull zzjs zzjs, float f) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            List<RecognizedLanguage> zze = zzpj.zze(zzjs.zzhb());
            if (zzjs.getWords() != null) {
                for (zzkd next : zzjs.getWords()) {
                    if (next != null) {
                        Word zzb = Word.zza(next, f);
                        sb.append(zzb.getText());
                        sb.append(FirebaseVisionDocumentText.zza(zzb.getRecognizedBreak()));
                        arrayList.add(zzb);
                    }
                }
            }
            return new Paragraph(zze, new RecognizedBreak(), zzol.zza(zzjs.zzha(), f), arrayList, sb.toString(), zzjs.getConfidence());
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
        public /* bridge */ /* synthetic */ RecognizedBreak getRecognizedBreak() {
            return super.getRecognizedBreak();
        }

        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }

        public List<Word> getWords() {
            return this.words;
        }
    }

    public static class RecognizedBreak {
        public static final int EOL_SURE_SPACE = 3;
        public static final int HYPHEN = 4;
        public static final int LINE_BREAK = 5;
        public static final int SPACE = 1;
        public static final int SURE_SPACE = 2;
        public static final int UNKNOWN = 0;
        private final int type;
        private final boolean zzavn;

        @Retention(RetentionPolicy.SOURCE)
        public @interface BreakType {
        }

        private RecognizedBreak(int i, boolean z) {
            this.type = i;
            this.zzavn = z;
        }

        /* access modifiers changed from: private */
        @Nullable
        public static RecognizedBreak zzc(@Nullable zzjz zzjz) {
            if (zzjz == null || zzjz.zzhj() == null) {
                return null;
            }
            int i = 4;
            boolean z = false;
            if (zzjz.zzhj().getType() != null) {
                String type2 = zzjz.zzhj().getType();
                char c = 65535;
                switch (type2.hashCode()) {
                    case -1651884996:
                        if (type2.equals("SURE_SPACE")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1571028039:
                        if (type2.equals("EOL_SURE_SPACE")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 79100134:
                        if (type2.equals("SPACE")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 1541383380:
                        if (type2.equals("LINE_BREAK")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 2145946930:
                        if (type2.equals("HYPHEN")) {
                            c = 3;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        i = 1;
                        break;
                    case 1:
                        i = 2;
                        break;
                    case 2:
                        i = 3;
                        break;
                    case 3:
                        break;
                    case 4:
                        i = 5;
                        break;
                }
            }
            i = 0;
            if (zzjz.zzhj().zzhd() != null) {
                z = zzjz.zzhj().zzhd().booleanValue();
            }
            return new RecognizedBreak(i, z);
        }

        public int getDetectedBreakType() {
            return this.type;
        }

        public boolean getIsPrefix() {
            return this.zzavn;
        }
    }

    public static class Symbol extends DocumentTextBase {
        private Symbol(@NonNull List<RecognizedLanguage> list, @Nullable RecognizedBreak recognizedBreak, @Nullable Rect rect, @NonNull String str, Float f) {
            super(list, recognizedBreak, rect, str, f);
        }

        /* access modifiers changed from: private */
        public static Symbol zza(@NonNull zzjx zzjx, float f) {
            return new Symbol(FirebaseVisionDocumentText.zza(zzjx.zzhb()), RecognizedBreak.zzc(zzjx.zzhb()), zzol.zza(zzjx.zzha(), f), zzol.zzcc(zzjx.getText()), zzjx.getConfidence());
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
        public /* bridge */ /* synthetic */ RecognizedBreak getRecognizedBreak() {
            return super.getRecognizedBreak();
        }

        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }
    }

    public static class Word extends DocumentTextBase {
        private final List<Symbol> symbols;

        private Word(@NonNull List<RecognizedLanguage> list, @Nullable RecognizedBreak recognizedBreak, @Nullable Rect rect, @NonNull List<Symbol> list2, @NonNull String str, @Nullable Float f) {
            super(list, recognizedBreak, rect, str, f);
            this.symbols = list2;
        }

        /* access modifiers changed from: private */
        public static Word zza(@NonNull zzkd zzkd, float f) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            List<RecognizedLanguage> zze = zzpj.zze(zzkd.zzhb());
            RecognizedBreak recognizedBreak = null;
            if (zzkd.getSymbols() != null) {
                for (zzjx next : zzkd.getSymbols()) {
                    if (next != null) {
                        Symbol zzb = Symbol.zza(next, f);
                        RecognizedBreak recognizedBreak2 = zzb.getRecognizedBreak();
                        sb.append(zzb.getText());
                        arrayList.add(Symbol.zza(next, f));
                        recognizedBreak = recognizedBreak2;
                    }
                }
            }
            return new Word(zze, recognizedBreak, zzol.zza(zzkd.zzha(), f), arrayList, sb.toString(), zzkd.getConfidence());
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
        public /* bridge */ /* synthetic */ RecognizedBreak getRecognizedBreak() {
            return super.getRecognizedBreak();
        }

        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        public List<Symbol> getSymbols() {
            return this.symbols;
        }

        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }
    }

    private FirebaseVisionDocumentText(@NonNull String str, @NonNull List<Block> list) {
        this.text = str;
        this.blocks = list;
    }

    @Nullable
    public static FirebaseVisionDocumentText zza(@Nullable zzjy zzjy, float f) {
        if (zzjy == null) {
            return null;
        }
        String zzcc = zzol.zzcc(zzjy.getText());
        ArrayList arrayList = new ArrayList();
        if (zzjy.getPages() != null) {
            for (zzjr next : zzjy.getPages()) {
                if (next != null) {
                    for (zzja next2 : next.getBlocks()) {
                        if (next2 != null) {
                            arrayList.add(Block.zza(next2, f));
                        }
                    }
                }
            }
        }
        return new FirebaseVisionDocumentText(zzcc, arrayList);
    }

    /* access modifiers changed from: private */
    public static String zza(@Nullable RecognizedBreak recognizedBreak) {
        if (recognizedBreak == null) {
            return "";
        }
        switch (recognizedBreak.getDetectedBreakType()) {
            case 1:
            case 2:
                return " ";
            case 3:
            case 5:
                return "\n";
            case 4:
                return "-\n";
            default:
                return "";
        }
    }

    /* access modifiers changed from: private */
    public static List<RecognizedLanguage> zza(@Nullable zzjz zzjz) {
        if (zzjz == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        if (zzjz.zzhk() != null) {
            for (zzjf zza : zzjz.zzhk()) {
                RecognizedLanguage zza2 = RecognizedLanguage.zza(zza);
                if (zza2 != null) {
                    arrayList.add(zza2);
                }
            }
        }
        return arrayList;
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

    public String getText() {
        String str = this.text;
        return str != null ? str : "";
    }
}
