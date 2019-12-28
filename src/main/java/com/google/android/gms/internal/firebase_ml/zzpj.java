package com.google.android.gms.internal.firebase_ml;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.RecognizedLanguage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public final class zzpj {
    private static final GmsLogger zzaoe = new GmsLogger("TextAnnotationConverter", "");

    @Nullable
    static FirebaseVisionText a(@NonNull zzjy zzjy, float f) {
        Iterator<zzja> it;
        Iterator<zzjr> it2;
        Iterator<zzjs> it3;
        Iterator<zzja> it4;
        Iterator<zzjr> it5;
        String str;
        FirebaseVisionText.Element element;
        float f2 = f;
        Preconditions.checkNotNull(zzjy, "The input TextAnnotation can not be null");
        FirebaseVisionText.TextBlock textBlock = null;
        if (zzjy.getPages().size() <= 0) {
            zzaoe.d("TextAnnotationConverter", "Text Annotation is null, return null");
            return null;
        }
        if (zzjy.getPages().size() > 1) {
            zzaoe.d("TextAnnotationConverter", "Text Annotation has more than one page, which should not happen");
        }
        ArrayList arrayList = new ArrayList();
        Iterator<zzjr> it6 = zzjy.getPages().iterator();
        while (it6.hasNext()) {
            Iterator<zzja> it7 = it6.next().getBlocks().iterator();
            while (it7.hasNext()) {
                zzja next = it7.next();
                Preconditions.checkNotNull(next, "Input block can not be null");
                ArrayList arrayList2 = new ArrayList();
                if (next.getParagraphs() == null) {
                    it2 = it6;
                    it = it7;
                } else {
                    Iterator<zzjs> it8 = next.getParagraphs().iterator();
                    while (it8.hasNext()) {
                        zzjs next2 = it8.next();
                        if (next2 != null) {
                            Preconditions.checkNotNull(next2, "Input Paragraph can not be null");
                            ArrayList arrayList3 = new ArrayList();
                            ArrayList arrayList4 = new ArrayList();
                            HashSet<RecognizedLanguage> hashSet = new HashSet<>();
                            StringBuilder sb = new StringBuilder();
                            float f3 = 0.0f;
                            ArrayList arrayList5 = arrayList4;
                            int i = 0;
                            while (i < next2.getWords().size()) {
                                zzkd zzkd = next2.getWords().get(i);
                                if (zzkd != null) {
                                    Preconditions.checkNotNull(zzkd, "Input Word can not be null");
                                    Rect zza = zzol.zza(zzkd.zzha(), f2);
                                    List<RecognizedLanguage> zze = zze(zzkd.zzhb());
                                    it5 = it6;
                                    Preconditions.checkNotNull(zzkd, "Input Word can not be null");
                                    if (zzkd.getSymbols() == null) {
                                        str = "";
                                        it4 = it7;
                                    } else {
                                        StringBuilder sb2 = new StringBuilder();
                                        for (zzjx text : zzkd.getSymbols()) {
                                            sb2.append(text.getText());
                                            it7 = it7;
                                        }
                                        it4 = it7;
                                        str = sb2.toString();
                                    }
                                    if (str.isEmpty()) {
                                        it3 = it8;
                                        element = null;
                                    } else {
                                        it3 = it8;
                                        element = new FirebaseVisionText.Element(str, zza, zze, zzkd.getConfidence());
                                    }
                                    if (element != null) {
                                        arrayList5.add(element);
                                        float zza2 = f3 + zzol.zza(element.getConfidence());
                                        hashSet.addAll(element.getRecognizedLanguages());
                                        sb.append(element.getText());
                                        Preconditions.checkNotNull(zzkd, "Input word can not be null");
                                        String zza3 = zza(zzkd);
                                        String str2 = "";
                                        if (zza3 != null) {
                                            if (zza3.equals("SPACE") || zza3.equals("SURE_SPACE")) {
                                                str2 = " ";
                                            } else if (zza3.equals("HYPHEN")) {
                                                str2 = "-";
                                            }
                                        }
                                        sb.append(str2);
                                        Preconditions.checkNotNull(zzkd, "Input word can not be null");
                                        String zza4 = zza(zzkd);
                                        if (!(zza4 != null && (zza4.equals("EOL_SURE_SPACE") || zza4.equals("LINE_BREAK") || zza4.equals("HYPHEN")))) {
                                            if (i != next2.getWords().size() - 1) {
                                                f3 = zza2;
                                            }
                                        }
                                        Preconditions.checkNotNull(arrayList5, "Input elements can not be null");
                                        ArrayList arrayList6 = arrayList5;
                                        int size = arrayList6.size();
                                        int i2 = 0;
                                        Rect rect = null;
                                        while (i2 < size) {
                                            Object obj = arrayList6.get(i2);
                                            i2++;
                                            FirebaseVisionText.Element element2 = (FirebaseVisionText.Element) obj;
                                            if (element2.getBoundingBox() != null) {
                                                if (rect == null) {
                                                    rect = new Rect();
                                                }
                                                Rect rect2 = rect;
                                                rect2.union(element2.getBoundingBox());
                                                rect = rect2;
                                            }
                                        }
                                        String sb3 = sb.toString();
                                        ArrayList arrayList7 = new ArrayList();
                                        for (RecognizedLanguage recognizedLanguage : hashSet) {
                                            if (!(recognizedLanguage == null || recognizedLanguage.getLanguageCode() == null || recognizedLanguage.getLanguageCode().isEmpty())) {
                                                arrayList7.add(recognizedLanguage);
                                            }
                                        }
                                        arrayList3.add(new FirebaseVisionText.Line(sb3, rect, arrayList7, arrayList5, Float.compare(zza2, 0.0f) > 0 ? Float.valueOf(zza2 / ((float) arrayList5.size())) : null));
                                        ArrayList arrayList8 = new ArrayList();
                                        hashSet.clear();
                                        arrayList5 = arrayList8;
                                        sb = new StringBuilder();
                                        f3 = 0.0f;
                                        i++;
                                        it6 = it5;
                                        it7 = it4;
                                        it8 = it3;
                                        zzjy zzjy2 = zzjy;
                                    }
                                } else {
                                    it5 = it6;
                                    it4 = it7;
                                    it3 = it8;
                                }
                                i++;
                                it6 = it5;
                                it7 = it4;
                                it8 = it3;
                                zzjy zzjy22 = zzjy;
                            }
                            Iterator<zzjr> it9 = it6;
                            Iterator<zzja> it10 = it7;
                            Iterator<zzjs> it11 = it8;
                            arrayList2.addAll(arrayList3);
                            zzjy zzjy3 = zzjy;
                        } else {
                            zzjy zzjy4 = zzjy;
                        }
                    }
                    it2 = it6;
                    it = it7;
                    if (arrayList2.isEmpty()) {
                        textBlock = null;
                    } else {
                        StringBuilder sb4 = new StringBuilder();
                        ArrayList arrayList9 = arrayList2;
                        int size2 = arrayList9.size();
                        int i3 = 0;
                        while (i3 < size2) {
                            Object obj2 = arrayList9.get(i3);
                            i3++;
                            sb4.append(((FirebaseVisionText.Line) obj2).getText());
                            sb4.append("\n");
                        }
                        textBlock = new FirebaseVisionText.TextBlock(sb4.toString(), zzol.zza(next.zzha(), f2), zze(next.zzhb()), arrayList2, next.getConfidence());
                    }
                }
                if (textBlock != null) {
                    arrayList.add(textBlock);
                }
                it6 = it2;
                it7 = it;
                zzjy zzjy5 = zzjy;
                textBlock = null;
            }
            zzjy zzjy6 = zzjy;
        }
        return new FirebaseVisionText(zzjy.getText(), arrayList);
    }

    @VisibleForTesting
    @Nullable
    private static String zza(@NonNull zzkd zzkd) {
        Preconditions.checkNotNull(zzkd, "Input Word can not be null");
        if (zzkd.getSymbols() == null || zzkd.getSymbols().size() <= 0) {
            return null;
        }
        zzjx zzjx = zzkd.getSymbols().get(zzkd.getSymbols().size() - 1);
        if (zzjx.zzhb() == null || zzjx.zzhb().zzhj() == null) {
            return null;
        }
        return zzkd.getSymbols().get(zzkd.getSymbols().size() - 1).zzhb().zzhj().getType();
    }

    public static List<RecognizedLanguage> zze(@Nullable zzjz zzjz) {
        ArrayList arrayList = new ArrayList();
        if (!(zzjz == null || zzjz.zzhk() == null)) {
            for (zzjf zza : zzjz.zzhk()) {
                RecognizedLanguage zza2 = RecognizedLanguage.zza(zza);
                if (zza2 != null) {
                    arrayList.add(zza2);
                }
            }
        }
        return arrayList;
    }
}
