package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzan;
import java.util.ArrayList;
import java.util.List;

public class Line implements Text {
    private zzae zzet;
    private List<Element> zzeu;

    Line(zzae zzae) {
        this.zzet = zzae;
    }

    public float getAngle() {
        return this.zzet.zzfd.zzfb;
    }

    public Rect getBoundingBox() {
        return zzc.a((Text) this);
    }

    public List<? extends Text> getComponents() {
        if (this.zzet.zzfc.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzeu == null) {
            this.zzeu = new ArrayList(this.zzet.zzfc.length);
            for (zzan element : this.zzet.zzfc) {
                this.zzeu.add(new Element(element));
            }
        }
        return this.zzeu;
    }

    public Point[] getCornerPoints() {
        return zzc.a(this.zzet.zzfd);
    }

    public String getLanguage() {
        return this.zzet.zzex;
    }

    public String getValue() {
        return this.zzet.zzfg;
    }

    public boolean isVertical() {
        return this.zzet.zzfi;
    }
}
