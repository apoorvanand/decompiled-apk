package androidx.constraintlayout.solver.widgets;

public class ResolutionDimension extends ResolutionNode {
    float a = 0.0f;

    public void remove() {
        this.i = 2;
    }

    public void reset() {
        super.reset();
        this.a = 0.0f;
    }

    public void resolve(int i) {
        if (this.i == 0 || this.a != ((float) i)) {
            this.a = (float) i;
            if (this.i == 1) {
                invalidate();
            }
            didResolve();
        }
    }
}
