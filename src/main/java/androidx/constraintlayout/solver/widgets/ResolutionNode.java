package androidx.constraintlayout.solver.widgets;

import java.util.HashSet;
import java.util.Iterator;

public class ResolutionNode {
    public static final int REMOVED = 2;
    public static final int RESOLVED = 1;
    public static final int UNRESOLVED = 0;
    HashSet<ResolutionNode> h = new HashSet<>(2);
    int i = 0;

    public void addDependent(ResolutionNode resolutionNode) {
        this.h.add(resolutionNode);
    }

    public void didResolve() {
        this.i = 1;
        Iterator<ResolutionNode> it = this.h.iterator();
        while (it.hasNext()) {
            it.next().resolve();
        }
    }

    public void invalidate() {
        this.i = 0;
        Iterator<ResolutionNode> it = this.h.iterator();
        while (it.hasNext()) {
            it.next().invalidate();
        }
    }

    public void invalidateAnchors() {
        if (this instanceof ResolutionAnchor) {
            this.i = 0;
        }
        Iterator<ResolutionNode> it = this.h.iterator();
        while (it.hasNext()) {
            it.next().invalidateAnchors();
        }
    }

    public boolean isResolved() {
        return this.i == 1;
    }

    public void remove(ResolutionDimension resolutionDimension) {
    }

    public void reset() {
        this.i = 0;
        this.h.clear();
    }

    public void resolve() {
    }
}
