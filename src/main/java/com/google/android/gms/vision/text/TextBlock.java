package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextBlock implements Text {
    private Point[] cornerPoints;
    private zzae[] zzev;
    private List<Line> zzew;
    private String zzex;
    private Rect zzey;

    TextBlock(SparseArray<zzae> sparseArray) {
        this.zzev = new zzae[sparseArray.size()];
        int i = 0;
        while (true) {
            zzae[] zzaeArr = this.zzev;
            if (i < zzaeArr.length) {
                zzaeArr[i] = sparseArray.valueAt(i);
                i++;
            } else {
                return;
            }
        }
    }

    public Rect getBoundingBox() {
        if (this.zzey == null) {
            this.zzey = zzc.a((Text) this);
        }
        return this.zzey;
    }

    public List<? extends Text> getComponents() {
        zzae[] zzaeArr = this.zzev;
        if (zzaeArr.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzew == null) {
            this.zzew = new ArrayList(zzaeArr.length);
            for (zzae line : this.zzev) {
                this.zzew.add(new Line(line));
            }
        }
        return this.zzew;
    }

    public Point[] getCornerPoints() {
        TextBlock textBlock;
        zzae[] zzaeArr;
        TextBlock textBlock2 = this;
        if (textBlock2.cornerPoints == null) {
            char c = 0;
            if (textBlock2.zzev.length == 0) {
                textBlock2.cornerPoints = new Point[0];
            } else {
                int i = Integer.MAX_VALUE;
                int i2 = 0;
                int i3 = Integer.MAX_VALUE;
                int i4 = Integer.MIN_VALUE;
                int i5 = Integer.MIN_VALUE;
                while (true) {
                    zzaeArr = textBlock2.zzev;
                    if (i2 >= zzaeArr.length) {
                        break;
                    }
                    zzy zzy = zzaeArr[i2].zzfd;
                    zzy zzy2 = textBlock2.zzev[c].zzfd;
                    double sin = Math.sin(Math.toRadians((double) zzy2.zzfb));
                    double cos = Math.cos(Math.toRadians((double) zzy2.zzfb));
                    Point[] pointArr = new Point[4];
                    pointArr[c] = new Point(zzy.left, zzy.top);
                    pointArr[c].offset(-zzy2.left, -zzy2.top);
                    int i6 = i;
                    int i7 = (int) ((((double) pointArr[c].x) * cos) + (((double) pointArr[c].y) * sin));
                    int i8 = (int) ((((double) (-pointArr[0].x)) * sin) + (((double) pointArr[0].y) * cos));
                    pointArr[0].x = i7;
                    pointArr[0].y = i8;
                    pointArr[1] = new Point(zzy.width + i7, i8);
                    pointArr[2] = new Point(zzy.width + i7, zzy.height + i8);
                    pointArr[3] = new Point(i7, i8 + zzy.height);
                    i = i6;
                    for (int i9 = 0; i9 < 4; i9++) {
                        Point point = pointArr[i9];
                        i = Math.min(i, point.x);
                        i4 = Math.max(i4, point.x);
                        i3 = Math.min(i3, point.y);
                        i5 = Math.max(i5, point.y);
                    }
                    i2++;
                    textBlock2 = this;
                    c = 0;
                }
                zzy zzy3 = zzaeArr[0].zzfd;
                int i10 = zzy3.left;
                int i11 = zzy3.top;
                double sin2 = Math.sin(Math.toRadians((double) zzy3.zzfb));
                double cos2 = Math.cos(Math.toRadians((double) zzy3.zzfb));
                int i12 = i;
                Point[] pointArr2 = {new Point(i12, i3), new Point(i4, i3), new Point(i4, i5), new Point(i12, i5)};
                for (int i13 = 0; i13 < 4; i13++) {
                    pointArr2[i13].x = (int) ((((double) pointArr2[i13].x) * cos2) - (((double) pointArr2[i13].y) * sin2));
                    pointArr2[i13].y = (int) ((((double) pointArr2[i13].x) * sin2) + (((double) pointArr2[i13].y) * cos2));
                    pointArr2[i13].offset(i10, i11);
                }
                textBlock = this;
                textBlock.cornerPoints = pointArr2;
                return textBlock.cornerPoints;
            }
        }
        textBlock = textBlock2;
        return textBlock.cornerPoints;
    }

    public String getLanguage() {
        String str = this.zzex;
        if (str != null) {
            return str;
        }
        HashMap hashMap = new HashMap();
        for (zzae zzae : this.zzev) {
            hashMap.put(zzae.zzex, Integer.valueOf((hashMap.containsKey(zzae.zzex) ? ((Integer) hashMap.get(zzae.zzex)).intValue() : 0) + 1));
        }
        this.zzex = (String) ((Map.Entry) Collections.max(hashMap.entrySet(), new zza(this))).getKey();
        String str2 = this.zzex;
        if (str2 == null || str2.isEmpty()) {
            this.zzex = "und";
        }
        return this.zzex;
    }

    public String getValue() {
        zzae[] zzaeArr = this.zzev;
        if (zzaeArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(zzaeArr[0].zzfg);
        for (int i = 1; i < this.zzev.length; i++) {
            sb.append("\n");
            sb.append(this.zzev[i].zzfg);
        }
        return sb.toString();
    }
}
