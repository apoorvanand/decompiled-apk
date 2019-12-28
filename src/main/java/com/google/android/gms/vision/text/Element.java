package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzan;
import java.util.ArrayList;
import java.util.List;

public class Element implements Text {
    private zzan zzes;

    Element(zzan zzan) {
        this.zzes = zzan;
    }

    public Rect getBoundingBox() {
        return zzc.a((Text) this);
    }

    public List<? extends Text> getComponents() {
        return new ArrayList();
    }

    public Point[] getCornerPoints() {
        return zzc.a(this.zzes.zzfd);
    }

    public String getLanguage() {
        return this.zzes.zzex;
    }

    public String getValue() {
        return this.zzes.zzfg;
    }
}
