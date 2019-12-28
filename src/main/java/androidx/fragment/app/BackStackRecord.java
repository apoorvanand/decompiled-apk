package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.util.LogWriter;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentManagerImpl;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

final class BackStackRecord extends FragmentTransaction implements FragmentManager.BackStackEntry, FragmentManagerImpl.OpGenerator {
    final FragmentManagerImpl a;
    ArrayList<Op> b = new ArrayList<>();
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    boolean i;
    boolean j = true;
    @Nullable
    String k;
    boolean l;
    int m = -1;
    int n;
    CharSequence o;
    int p;
    CharSequence q;
    ArrayList<String> r;
    ArrayList<String> s;
    boolean t = false;
    ArrayList<Runnable> u;

    static final class Op {
        int a;
        Fragment b;
        int c;
        int d;
        int e;
        int f;

        Op() {
        }

        Op(int i, Fragment fragment) {
            this.a = i;
            this.b = fragment;
        }
    }

    public BackStackRecord(FragmentManagerImpl fragmentManagerImpl) {
        this.a = fragmentManagerImpl;
    }

    private void doAddOp(int i2, Fragment fragment, @Nullable String str, int i3) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from" + " instance state.");
        }
        fragment.mFragmentManager = this.a;
        if (str != null) {
            if (fragment.mTag == null || str.equals(fragment.mTag)) {
                fragment.mTag = str;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
            }
        }
        if (i2 != 0) {
            if (i2 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            } else if (fragment.mFragmentId == 0 || fragment.mFragmentId == i2) {
                fragment.mFragmentId = i2;
                fragment.mContainerId = i2;
            } else {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i2);
            }
        }
        a(new Op(i3, fragment));
    }

    private static boolean isFragmentPostponed(Op op) {
        Fragment fragment = op.b;
        return fragment != null && fragment.mAdded && fragment.mView != null && !fragment.mDetached && !fragment.mHidden && fragment.isPostponed();
    }

    /* access modifiers changed from: package-private */
    public int a(boolean z) {
        if (!this.l) {
            if (FragmentManagerImpl.a) {
                Log.v("FragmentManager", "Commit: " + this);
                PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
                dump("  ", (FileDescriptor) null, printWriter, (String[]) null);
                printWriter.close();
            }
            this.l = true;
            this.m = this.i ? this.a.allocBackStackIndex(this) : -1;
            this.a.enqueueAction(this, z);
            return this.m;
        }
        throw new IllegalStateException("commit already called");
    }

    /* access modifiers changed from: package-private */
    public Fragment a(ArrayList<Fragment> arrayList, Fragment fragment) {
        Fragment fragment2 = fragment;
        int i2 = 0;
        while (i2 < this.b.size()) {
            Op op = this.b.get(i2);
            switch (op.a) {
                case 1:
                case 7:
                    arrayList.add(op.b);
                    break;
                case 2:
                    Fragment fragment3 = op.b;
                    int i3 = fragment3.mContainerId;
                    Fragment fragment4 = fragment2;
                    int i4 = i2;
                    boolean z = false;
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        Fragment fragment5 = arrayList.get(size);
                        if (fragment5.mContainerId == i3) {
                            if (fragment5 == fragment3) {
                                z = true;
                            } else {
                                if (fragment5 == fragment4) {
                                    this.b.add(i4, new Op(9, fragment5));
                                    i4++;
                                    fragment4 = null;
                                }
                                Op op2 = new Op(3, fragment5);
                                op2.c = op.c;
                                op2.e = op.e;
                                op2.d = op.d;
                                op2.f = op.f;
                                this.b.add(i4, op2);
                                arrayList.remove(fragment5);
                                i4++;
                            }
                        }
                    }
                    if (z) {
                        this.b.remove(i4);
                        i4--;
                    } else {
                        op.a = 1;
                        arrayList.add(fragment3);
                    }
                    i2 = i4;
                    fragment2 = fragment4;
                    break;
                case 3:
                case 6:
                    arrayList.remove(op.b);
                    if (op.b != fragment2) {
                        break;
                    } else {
                        this.b.add(i2, new Op(9, op.b));
                        i2++;
                        fragment2 = null;
                        break;
                    }
                case 8:
                    this.b.add(i2, new Op(9, fragment2));
                    i2++;
                    fragment2 = op.b;
                    break;
            }
            i2++;
        }
        return fragment2;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            Op op = this.b.get(i2);
            Fragment fragment = op.b;
            if (fragment != null) {
                fragment.setNextTransition(this.g, this.h);
            }
            int i3 = op.a;
            if (i3 != 1) {
                switch (i3) {
                    case 3:
                        fragment.setNextAnim(op.d);
                        this.a.removeFragment(fragment);
                        break;
                    case 4:
                        fragment.setNextAnim(op.d);
                        this.a.hideFragment(fragment);
                        break;
                    case 5:
                        fragment.setNextAnim(op.c);
                        this.a.showFragment(fragment);
                        break;
                    case 6:
                        fragment.setNextAnim(op.d);
                        this.a.detachFragment(fragment);
                        break;
                    case 7:
                        fragment.setNextAnim(op.c);
                        this.a.attachFragment(fragment);
                        break;
                    case 8:
                        this.a.setPrimaryNavigationFragment(fragment);
                        break;
                    case 9:
                        this.a.setPrimaryNavigationFragment((Fragment) null);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown cmd: " + op.a);
                }
            } else {
                fragment.setNextAnim(op.c);
                this.a.addFragment(fragment, false);
            }
            if (!(this.t || op.a == 1 || fragment == null)) {
                this.a.d(fragment);
            }
        }
        if (!this.t) {
            FragmentManagerImpl fragmentManagerImpl = this.a;
            fragmentManagerImpl.a(fragmentManagerImpl.l, true);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        if (this.i) {
            if (FragmentManagerImpl.a) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i2);
            }
            int size = this.b.size();
            for (int i3 = 0; i3 < size; i3++) {
                Op op = this.b.get(i3);
                if (op.b != null) {
                    op.b.mBackStackNesting += i2;
                    if (FragmentManagerImpl.a) {
                        Log.v("FragmentManager", "Bump nesting of " + op.b + " to " + op.b.mBackStackNesting);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Op op) {
        this.b.add(op);
        op.c = this.c;
        op.d = this.d;
        op.e = this.e;
        op.f = this.f;
    }

    /* access modifiers changed from: package-private */
    public void a(Fragment.OnStartEnterTransitionListener onStartEnterTransitionListener) {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            Op op = this.b.get(i2);
            if (isFragmentPostponed(op)) {
                op.b.setOnStartEnterTransitionListener(onStartEnterTransitionListener);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(ArrayList<BackStackRecord> arrayList, int i2, int i3) {
        if (i3 == i2) {
            return false;
        }
        int size = this.b.size();
        int i4 = -1;
        for (int i5 = 0; i5 < size; i5++) {
            Op op = this.b.get(i5);
            int i6 = op.b != null ? op.b.mContainerId : 0;
            if (!(i6 == 0 || i6 == i4)) {
                for (int i7 = i2; i7 < i3; i7++) {
                    BackStackRecord backStackRecord = arrayList.get(i7);
                    int size2 = backStackRecord.b.size();
                    for (int i8 = 0; i8 < size2; i8++) {
                        Op op2 = backStackRecord.b.get(i8);
                        if ((op2.b != null ? op2.b.mContainerId : 0) == i6) {
                            return true;
                        }
                    }
                }
                i4 = i6;
            }
        }
        return false;
    }

    public FragmentTransaction add(int i2, Fragment fragment) {
        doAddOp(i2, fragment, (String) null, 1);
        return this;
    }

    public FragmentTransaction add(int i2, Fragment fragment, @Nullable String str) {
        doAddOp(i2, fragment, str, 1);
        return this;
    }

    public FragmentTransaction add(Fragment fragment, @Nullable String str) {
        doAddOp(0, fragment, str, 1);
        return this;
    }

    public FragmentTransaction addSharedElement(View view, String str) {
        if (FragmentTransition.a()) {
            String transitionName = ViewCompat.getTransitionName(view);
            if (transitionName != null) {
                if (this.r == null) {
                    this.r = new ArrayList<>();
                    this.s = new ArrayList<>();
                } else if (this.s.contains(str)) {
                    throw new IllegalArgumentException("A shared element with the target name '" + str + "' has already been added to the transaction.");
                } else if (this.r.contains(transitionName)) {
                    throw new IllegalArgumentException("A shared element with the source name '" + transitionName + " has already been added to the transaction.");
                }
                this.r.add(transitionName);
                this.s.add(str);
            } else {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
        }
        return this;
    }

    public FragmentTransaction addToBackStack(@Nullable String str) {
        if (this.j) {
            this.i = true;
            this.k = str;
            return this;
        }
        throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
    }

    public FragmentTransaction attach(Fragment fragment) {
        a(new Op(7, fragment));
        return this;
    }

    /* access modifiers changed from: package-private */
    public Fragment b(ArrayList<Fragment> arrayList, Fragment fragment) {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            Op op = this.b.get(i2);
            int i3 = op.a;
            if (i3 != 1) {
                if (i3 != 3) {
                    switch (i3) {
                        case 6:
                            break;
                        case 7:
                            break;
                        case 8:
                            fragment = null;
                            break;
                        case 9:
                            fragment = op.b;
                            break;
                    }
                }
                arrayList.add(op.b);
            }
            arrayList.remove(op.b);
        }
        return fragment;
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            Op op = this.b.get(size);
            Fragment fragment = op.b;
            if (fragment != null) {
                fragment.setNextTransition(FragmentManagerImpl.reverseTransit(this.g), this.h);
            }
            int i2 = op.a;
            if (i2 != 1) {
                switch (i2) {
                    case 3:
                        fragment.setNextAnim(op.e);
                        this.a.addFragment(fragment, false);
                        break;
                    case 4:
                        fragment.setNextAnim(op.e);
                        this.a.showFragment(fragment);
                        break;
                    case 5:
                        fragment.setNextAnim(op.f);
                        this.a.hideFragment(fragment);
                        break;
                    case 6:
                        fragment.setNextAnim(op.e);
                        this.a.attachFragment(fragment);
                        break;
                    case 7:
                        fragment.setNextAnim(op.f);
                        this.a.detachFragment(fragment);
                        break;
                    case 8:
                        this.a.setPrimaryNavigationFragment((Fragment) null);
                        break;
                    case 9:
                        this.a.setPrimaryNavigationFragment(fragment);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown cmd: " + op.a);
                }
            } else {
                fragment.setNextAnim(op.f);
                this.a.removeFragment(fragment);
            }
            if (!(this.t || op.a == 3 || fragment == null)) {
                this.a.d(fragment);
            }
        }
        if (!this.t && z) {
            FragmentManagerImpl fragmentManagerImpl = this.a;
            fragmentManagerImpl.a(fragmentManagerImpl.l, true);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            if (isFragmentPostponed(this.b.get(i2))) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean b(int i2) {
        int size = this.b.size();
        for (int i3 = 0; i3 < size; i3++) {
            Op op = this.b.get(i3);
            int i4 = op.b != null ? op.b.mContainerId : 0;
            if (i4 != 0 && i4 == i2) {
                return true;
            }
        }
        return false;
    }

    public int commit() {
        return a(false);
    }

    public int commitAllowingStateLoss() {
        return a(true);
    }

    public void commitNow() {
        disallowAddToBackStack();
        this.a.execSingleAction(this, false);
    }

    public void commitNowAllowingStateLoss() {
        disallowAddToBackStack();
        this.a.execSingleAction(this, true);
    }

    public FragmentTransaction detach(Fragment fragment) {
        a(new Op(6, fragment));
        return this;
    }

    public FragmentTransaction disallowAddToBackStack() {
        if (!this.i) {
            this.j = false;
            return this;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        dump(str, printWriter, true);
    }

    public void dump(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.k);
            printWriter.print(" mIndex=");
            printWriter.print(this.m);
            printWriter.print(" mCommitted=");
            printWriter.println(this.l);
            if (this.g != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.g));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.h));
            }
            if (!(this.c == 0 && this.d == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.c));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.d));
            }
            if (!(this.e == 0 && this.f == 0)) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.e));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f));
            }
            if (!(this.n == 0 && this.o == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.n));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.o);
            }
            if (!(this.p == 0 && this.q == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.p));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.q);
            }
        }
        if (!this.b.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Operations:");
            str + "    ";
            int size = this.b.size();
            for (int i2 = 0; i2 < size; i2++) {
                Op op = this.b.get(i2);
                switch (op.a) {
                    case 0:
                        str2 = "NULL";
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case 2:
                        str2 = "REPLACE";
                        break;
                    case 3:
                        str2 = "REMOVE";
                        break;
                    case 4:
                        str2 = "HIDE";
                        break;
                    case 5:
                        str2 = "SHOW";
                        break;
                    case 6:
                        str2 = "DETACH";
                        break;
                    case 7:
                        str2 = "ATTACH";
                        break;
                    case 8:
                        str2 = "SET_PRIMARY_NAV";
                        break;
                    case 9:
                        str2 = "UNSET_PRIMARY_NAV";
                        break;
                    default:
                        str2 = "cmd=" + op.a;
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.print(str2);
                printWriter.print(" ");
                printWriter.println(op.b);
                if (z) {
                    if (!(op.c == 0 && op.d == 0)) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(op.c));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(op.d));
                    }
                    if (op.e != 0 || op.f != 0) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(op.e));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(op.f));
                    }
                }
            }
        }
    }

    public boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        if (FragmentManagerImpl.a) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(false);
        if (!this.i) {
            return true;
        }
        this.a.a(this);
        return true;
    }

    @Nullable
    public CharSequence getBreadCrumbShortTitle() {
        return this.p != 0 ? this.a.m.b().getText(this.p) : this.q;
    }

    public int getBreadCrumbShortTitleRes() {
        return this.p;
    }

    @Nullable
    public CharSequence getBreadCrumbTitle() {
        return this.n != 0 ? this.a.m.b().getText(this.n) : this.o;
    }

    public int getBreadCrumbTitleRes() {
        return this.n;
    }

    public int getId() {
        return this.m;
    }

    @Nullable
    public String getName() {
        return this.k;
    }

    public int getTransition() {
        return this.g;
    }

    public int getTransitionStyle() {
        return this.h;
    }

    public FragmentTransaction hide(Fragment fragment) {
        a(new Op(4, fragment));
        return this;
    }

    public boolean isAddToBackStackAllowed() {
        return this.j;
    }

    public boolean isEmpty() {
        return this.b.isEmpty();
    }

    public FragmentTransaction remove(Fragment fragment) {
        a(new Op(3, fragment));
        return this;
    }

    public FragmentTransaction replace(int i2, Fragment fragment) {
        return replace(i2, fragment, (String) null);
    }

    public FragmentTransaction replace(int i2, Fragment fragment, @Nullable String str) {
        if (i2 != 0) {
            doAddOp(i2, fragment, str, 2);
            return this;
        }
        throw new IllegalArgumentException("Must use non-zero containerViewId");
    }

    public FragmentTransaction runOnCommit(Runnable runnable) {
        if (runnable != null) {
            disallowAddToBackStack();
            if (this.u == null) {
                this.u = new ArrayList<>();
            }
            this.u.add(runnable);
            return this;
        }
        throw new IllegalArgumentException("runnable cannot be null");
    }

    public void runOnCommitRunnables() {
        ArrayList<Runnable> arrayList = this.u;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.u.get(i2).run();
            }
            this.u = null;
        }
    }

    public FragmentTransaction setAllowOptimization(boolean z) {
        return setReorderingAllowed(z);
    }

    public FragmentTransaction setBreadCrumbShortTitle(int i2) {
        this.p = i2;
        this.q = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(@Nullable CharSequence charSequence) {
        this.p = 0;
        this.q = charSequence;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(int i2) {
        this.n = i2;
        this.o = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(@Nullable CharSequence charSequence) {
        this.n = 0;
        this.o = charSequence;
        return this;
    }

    public FragmentTransaction setCustomAnimations(int i2, int i3) {
        return setCustomAnimations(i2, i3, 0, 0);
    }

    public FragmentTransaction setCustomAnimations(int i2, int i3, int i4, int i5) {
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
        return this;
    }

    public FragmentTransaction setPrimaryNavigationFragment(@Nullable Fragment fragment) {
        a(new Op(8, fragment));
        return this;
    }

    public FragmentTransaction setReorderingAllowed(boolean z) {
        this.t = z;
        return this;
    }

    public FragmentTransaction setTransition(int i2) {
        this.g = i2;
        return this;
    }

    public FragmentTransaction setTransitionStyle(int i2) {
        this.h = i2;
        return this;
    }

    public FragmentTransaction show(Fragment fragment) {
        a(new Op(5, fragment));
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.m >= 0) {
            sb.append(" #");
            sb.append(this.m);
        }
        if (this.k != null) {
            sb.append(" ");
            sb.append(this.k);
        }
        sb.append("}");
        return sb.toString();
    }
}
