package androidx.fragment.app;

import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class FragmentTransition {
    private static final int[] INVERSE_OPS = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8};
    private static final FragmentTransitionImpl PLATFORM_IMPL = (Build.VERSION.SDK_INT >= 21 ? new FragmentTransitionCompat21() : null);
    private static final FragmentTransitionImpl SUPPORT_IMPL = resolveSupportImpl();

    static class FragmentContainerTransition {
        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;

        FragmentContainerTransition() {
        }
    }

    private FragmentTransition() {
    }

    static View a(ArrayMap<String, View> arrayMap, FragmentContainerTransition fragmentContainerTransition, Object obj, boolean z) {
        BackStackRecord backStackRecord = fragmentContainerTransition.lastInTransaction;
        if (obj == null || arrayMap == null || backStackRecord.r == null || backStackRecord.r.isEmpty()) {
            return null;
        }
        return arrayMap.get((z ? backStackRecord.r : backStackRecord.s).get(0));
    }

    static ArrayMap<String, View> a(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, FragmentContainerTransition fragmentContainerTransition) {
        SharedElementCallback sharedElementCallback;
        ArrayList<String> arrayList;
        String findKeyForValue;
        Fragment fragment = fragmentContainerTransition.lastIn;
        View view = fragment.getView();
        if (arrayMap.isEmpty() || obj == null || view == null) {
            arrayMap.clear();
            return null;
        }
        ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
        fragmentTransitionImpl.a((Map<String, View>) arrayMap2, view);
        BackStackRecord backStackRecord = fragmentContainerTransition.lastInTransaction;
        if (fragmentContainerTransition.lastInIsPop) {
            sharedElementCallback = fragment.getExitTransitionCallback();
            arrayList = backStackRecord.r;
        } else {
            sharedElementCallback = fragment.getEnterTransitionCallback();
            arrayList = backStackRecord.s;
        }
        if (arrayList != null) {
            arrayMap2.retainAll(arrayList);
            arrayMap2.retainAll(arrayMap.values());
        }
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(arrayList, arrayMap2);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = arrayList.get(size);
                View view2 = arrayMap2.get(str);
                if (view2 == null) {
                    String findKeyForValue2 = findKeyForValue(arrayMap, str);
                    if (findKeyForValue2 != null) {
                        arrayMap.remove(findKeyForValue2);
                    }
                } else if (!str.equals(ViewCompat.getTransitionName(view2)) && (findKeyForValue = findKeyForValue(arrayMap, str)) != null) {
                    arrayMap.put(findKeyForValue, ViewCompat.getTransitionName(view2));
                }
            }
        } else {
            retainValues(arrayMap, arrayMap2);
        }
        return arrayMap2;
    }

    static ArrayList<View> a(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.getView();
        if (view2 != null) {
            fragmentTransitionImpl.a(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        fragmentTransitionImpl.addTargets(obj, arrayList2);
        return arrayList2;
    }

    static void a(Fragment fragment, Fragment fragment2, boolean z, ArrayMap<String, View> arrayMap, boolean z2) {
        SharedElementCallback enterTransitionCallback = z ? fragment2.getEnterTransitionCallback() : fragment.getEnterTransitionCallback();
        if (enterTransitionCallback != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = arrayMap == null ? 0 : arrayMap.size();
            for (int i = 0; i < size; i++) {
                arrayList2.add(arrayMap.keyAt(i));
                arrayList.add(arrayMap.valueAt(i));
            }
            if (z2) {
                enterTransitionCallback.onSharedElementStart(arrayList2, arrayList, (List<View>) null);
            } else {
                enterTransitionCallback.onSharedElementEnd(arrayList2, arrayList, (List<View>) null);
            }
        }
    }

    static void a(FragmentManagerImpl fragmentManagerImpl, ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z) {
        if (fragmentManagerImpl.l >= 1) {
            SparseArray sparseArray = new SparseArray();
            for (int i3 = i; i3 < i2; i3++) {
                BackStackRecord backStackRecord = arrayList.get(i3);
                if (arrayList2.get(i3).booleanValue()) {
                    calculatePopFragments(backStackRecord, sparseArray, z);
                } else {
                    calculateFragments(backStackRecord, sparseArray, z);
                }
            }
            if (sparseArray.size() != 0) {
                View view = new View(fragmentManagerImpl.m.b());
                int size = sparseArray.size();
                for (int i4 = 0; i4 < size; i4++) {
                    int keyAt = sparseArray.keyAt(i4);
                    ArrayMap<String, String> calculateNameOverrides = calculateNameOverrides(keyAt, arrayList, arrayList2, i, i2);
                    FragmentContainerTransition fragmentContainerTransition = (FragmentContainerTransition) sparseArray.valueAt(i4);
                    if (z) {
                        configureTransitionsReordered(fragmentManagerImpl, keyAt, fragmentContainerTransition, view, calculateNameOverrides);
                    } else {
                        configureTransitionsOrdered(fragmentManagerImpl, keyAt, fragmentContainerTransition, view, calculateNameOverrides);
                    }
                }
            }
        }
    }

    static void a(ArrayList<View> arrayList, int i) {
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).setVisibility(i);
            }
        }
    }

    static boolean a() {
        return (PLATFORM_IMPL == null && SUPPORT_IMPL == null) ? false : true;
    }

    private static void addSharedElementsWithMatchingNames(ArrayList<View> arrayList, ArrayMap<String, View> arrayMap, Collection<String> collection) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View valueAt = arrayMap.valueAt(size);
            if (collection.contains(ViewCompat.getTransitionName(valueAt))) {
                arrayList.add(valueAt);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        if (r10.mAdded != false) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x006c, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x006e, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0079, code lost:
        r13 = r1;
        r1 = false;
        r12 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0088, code lost:
        if (r10.mHidden == false) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x008a, code lost:
        r1 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void addToFirstInLastOut(androidx.fragment.app.BackStackRecord r16, androidx.fragment.app.BackStackRecord.Op r17, android.util.SparseArray<androidx.fragment.app.FragmentTransition.FragmentContainerTransition> r18, boolean r19, boolean r20) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            androidx.fragment.app.Fragment r10 = r1.b
            if (r10 != 0) goto L_0x000d
            return
        L_0x000d:
            int r11 = r10.mContainerId
            if (r11 != 0) goto L_0x0012
            return
        L_0x0012:
            if (r3 == 0) goto L_0x001b
            int[] r4 = INVERSE_OPS
            int r1 = r1.a
            r1 = r4[r1]
            goto L_0x001d
        L_0x001b:
            int r1 = r1.a
        L_0x001d:
            r4 = 0
            r5 = 1
            if (r1 == r5) goto L_0x007d
            switch(r1) {
                case 3: goto L_0x0053;
                case 4: goto L_0x003b;
                case 5: goto L_0x0029;
                case 6: goto L_0x0053;
                case 7: goto L_0x007d;
                default: goto L_0x0024;
            }
        L_0x0024:
            r1 = 0
        L_0x0025:
            r12 = 0
            r13 = 0
            goto L_0x0090
        L_0x0029:
            if (r20 == 0) goto L_0x0038
            boolean r1 = r10.mHiddenChanged
            if (r1 == 0) goto L_0x008c
            boolean r1 = r10.mHidden
            if (r1 != 0) goto L_0x008c
            boolean r1 = r10.mAdded
            if (r1 == 0) goto L_0x008c
            goto L_0x008a
        L_0x0038:
            boolean r1 = r10.mHidden
            goto L_0x008d
        L_0x003b:
            if (r20 == 0) goto L_0x004a
            boolean r1 = r10.mHiddenChanged
            if (r1 == 0) goto L_0x006e
            boolean r1 = r10.mAdded
            if (r1 == 0) goto L_0x006e
            boolean r1 = r10.mHidden
            if (r1 == 0) goto L_0x006e
        L_0x0049:
            goto L_0x006c
        L_0x004a:
            boolean r1 = r10.mAdded
            if (r1 == 0) goto L_0x006e
            boolean r1 = r10.mHidden
            if (r1 != 0) goto L_0x006e
            goto L_0x0049
        L_0x0053:
            if (r20 == 0) goto L_0x0070
            boolean r1 = r10.mAdded
            if (r1 != 0) goto L_0x006e
            android.view.View r1 = r10.mView
            if (r1 == 0) goto L_0x006e
            android.view.View r1 = r10.mView
            int r1 = r1.getVisibility()
            if (r1 != 0) goto L_0x006e
            float r1 = r10.mPostponedAlpha
            r6 = 0
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 < 0) goto L_0x006e
        L_0x006c:
            r1 = 1
            goto L_0x0079
        L_0x006e:
            r1 = 0
            goto L_0x0079
        L_0x0070:
            boolean r1 = r10.mAdded
            if (r1 == 0) goto L_0x006e
            boolean r1 = r10.mHidden
            if (r1 != 0) goto L_0x006e
            goto L_0x006c
        L_0x0079:
            r13 = r1
            r1 = 0
            r12 = 1
            goto L_0x0090
        L_0x007d:
            if (r20 == 0) goto L_0x0082
            boolean r1 = r10.mIsNewlyAdded
            goto L_0x008d
        L_0x0082:
            boolean r1 = r10.mAdded
            if (r1 != 0) goto L_0x008c
            boolean r1 = r10.mHidden
            if (r1 != 0) goto L_0x008c
        L_0x008a:
            r1 = 1
            goto L_0x008d
        L_0x008c:
            r1 = 0
        L_0x008d:
            r4 = r1
            r1 = 1
            goto L_0x0025
        L_0x0090:
            java.lang.Object r6 = r2.get(r11)
            androidx.fragment.app.FragmentTransition$FragmentContainerTransition r6 = (androidx.fragment.app.FragmentTransition.FragmentContainerTransition) r6
            if (r4 == 0) goto L_0x00a2
            androidx.fragment.app.FragmentTransition$FragmentContainerTransition r6 = ensureContainer(r6, r2, r11)
            r6.lastIn = r10
            r6.lastInIsPop = r3
            r6.lastInTransaction = r0
        L_0x00a2:
            r14 = r6
            r15 = 0
            if (r20 != 0) goto L_0x00c9
            if (r1 == 0) goto L_0x00c9
            if (r14 == 0) goto L_0x00b0
            androidx.fragment.app.Fragment r1 = r14.firstOut
            if (r1 != r10) goto L_0x00b0
            r14.firstOut = r15
        L_0x00b0:
            androidx.fragment.app.FragmentManagerImpl r4 = r0.a
            int r1 = r10.mState
            if (r1 >= r5) goto L_0x00c9
            int r1 = r4.l
            if (r1 < r5) goto L_0x00c9
            boolean r1 = r0.t
            if (r1 != 0) goto L_0x00c9
            r4.e(r10)
            r6 = 1
            r7 = 0
            r8 = 0
            r9 = 0
            r5 = r10
            r4.a((androidx.fragment.app.Fragment) r5, (int) r6, (int) r7, (int) r8, (boolean) r9)
        L_0x00c9:
            if (r13 == 0) goto L_0x00db
            if (r14 == 0) goto L_0x00d1
            androidx.fragment.app.Fragment r1 = r14.firstOut
            if (r1 != 0) goto L_0x00db
        L_0x00d1:
            androidx.fragment.app.FragmentTransition$FragmentContainerTransition r14 = ensureContainer(r14, r2, r11)
            r14.firstOut = r10
            r14.firstOutIsPop = r3
            r14.firstOutTransaction = r0
        L_0x00db:
            if (r20 != 0) goto L_0x00e7
            if (r12 == 0) goto L_0x00e7
            if (r14 == 0) goto L_0x00e7
            androidx.fragment.app.Fragment r0 = r14.lastIn
            if (r0 != r10) goto L_0x00e7
            r14.lastIn = r15
        L_0x00e7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentTransition.addToFirstInLastOut(androidx.fragment.app.BackStackRecord, androidx.fragment.app.BackStackRecord$Op, android.util.SparseArray, boolean, boolean):void");
    }

    public static void calculateFragments(BackStackRecord backStackRecord, SparseArray<FragmentContainerTransition> sparseArray, boolean z) {
        int size = backStackRecord.b.size();
        for (int i = 0; i < size; i++) {
            addToFirstInLastOut(backStackRecord, backStackRecord.b.get(i), sparseArray, false, z);
        }
    }

    private static ArrayMap<String, String> calculateNameOverrides(int i, ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            BackStackRecord backStackRecord = arrayList.get(i4);
            if (backStackRecord.b(i)) {
                boolean booleanValue = arrayList2.get(i4).booleanValue();
                if (backStackRecord.r != null) {
                    int size = backStackRecord.r.size();
                    if (booleanValue) {
                        arrayList3 = backStackRecord.r;
                        arrayList4 = backStackRecord.s;
                    } else {
                        ArrayList<String> arrayList5 = backStackRecord.r;
                        arrayList3 = backStackRecord.s;
                        arrayList4 = arrayList5;
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        String str = arrayList4.get(i5);
                        String str2 = arrayList3.get(i5);
                        String remove = arrayMap.remove(str2);
                        if (remove != null) {
                            arrayMap.put(str, remove);
                        } else {
                            arrayMap.put(str, str2);
                        }
                    }
                }
            }
        }
        return arrayMap;
    }

    public static void calculatePopFragments(BackStackRecord backStackRecord, SparseArray<FragmentContainerTransition> sparseArray, boolean z) {
        if (backStackRecord.a.n.onHasView()) {
            for (int size = backStackRecord.b.size() - 1; size >= 0; size--) {
                addToFirstInLastOut(backStackRecord, backStackRecord.b.get(size), sparseArray, true, z);
            }
        }
    }

    private static boolean canHandleAll(FragmentTransitionImpl fragmentTransitionImpl, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!fragmentTransitionImpl.canHandle(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static ArrayMap<String, View> captureOutSharedElements(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, FragmentContainerTransition fragmentContainerTransition) {
        SharedElementCallback sharedElementCallback;
        ArrayList<String> arrayList;
        if (arrayMap.isEmpty() || obj == null) {
            arrayMap.clear();
            return null;
        }
        Fragment fragment = fragmentContainerTransition.firstOut;
        ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
        fragmentTransitionImpl.a((Map<String, View>) arrayMap2, fragment.getView());
        BackStackRecord backStackRecord = fragmentContainerTransition.firstOutTransaction;
        if (fragmentContainerTransition.firstOutIsPop) {
            sharedElementCallback = fragment.getEnterTransitionCallback();
            arrayList = backStackRecord.s;
        } else {
            sharedElementCallback = fragment.getExitTransitionCallback();
            arrayList = backStackRecord.r;
        }
        arrayMap2.retainAll(arrayList);
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(arrayList, arrayMap2);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = arrayList.get(size);
                View view = arrayMap2.get(str);
                if (view == null) {
                    arrayMap.remove(str);
                } else if (!str.equals(ViewCompat.getTransitionName(view))) {
                    arrayMap.put(ViewCompat.getTransitionName(view), arrayMap.remove(str));
                }
            }
        } else {
            arrayMap.retainAll(arrayMap2.keySet());
        }
        return arrayMap2;
    }

    private static FragmentTransitionImpl chooseImpl(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            Object enterTransition = fragment2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        FragmentTransitionImpl fragmentTransitionImpl = PLATFORM_IMPL;
        if (fragmentTransitionImpl != null && canHandleAll(fragmentTransitionImpl, arrayList)) {
            return PLATFORM_IMPL;
        }
        FragmentTransitionImpl fragmentTransitionImpl2 = SUPPORT_IMPL;
        if (fragmentTransitionImpl2 != null && canHandleAll(fragmentTransitionImpl2, arrayList)) {
            return SUPPORT_IMPL;
        }
        if (PLATFORM_IMPL == null && SUPPORT_IMPL == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    private static Object configureSharedElementsOrdered(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, ArrayMap<String, String> arrayMap, FragmentContainerTransition fragmentContainerTransition, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        ArrayMap<String, String> arrayMap2;
        Object obj3;
        Object obj4;
        Rect rect;
        FragmentTransitionImpl fragmentTransitionImpl2 = fragmentTransitionImpl;
        FragmentContainerTransition fragmentContainerTransition2 = fragmentContainerTransition;
        ArrayList<View> arrayList3 = arrayList;
        Object obj5 = obj;
        Fragment fragment = fragmentContainerTransition2.lastIn;
        Fragment fragment2 = fragmentContainerTransition2.firstOut;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = fragmentContainerTransition2.lastInIsPop;
        if (arrayMap.isEmpty()) {
            arrayMap2 = arrayMap;
            obj3 = null;
        } else {
            obj3 = getSharedElementTransition(fragmentTransitionImpl2, fragment, fragment2, z);
            arrayMap2 = arrayMap;
        }
        ArrayMap<String, View> captureOutSharedElements = captureOutSharedElements(fragmentTransitionImpl2, arrayMap2, obj3, fragmentContainerTransition2);
        if (arrayMap.isEmpty()) {
            obj4 = null;
        } else {
            arrayList3.addAll(captureOutSharedElements.values());
            obj4 = obj3;
        }
        if (obj5 == null && obj2 == null && obj4 == null) {
            return null;
        }
        a(fragment, fragment2, z, captureOutSharedElements, true);
        if (obj4 != null) {
            rect = new Rect();
            fragmentTransitionImpl2.setSharedElementTargets(obj4, view, arrayList3);
            setOutEpicenter(fragmentTransitionImpl, obj4, obj2, captureOutSharedElements, fragmentContainerTransition2.firstOutIsPop, fragmentContainerTransition2.firstOutTransaction);
            if (obj5 != null) {
                fragmentTransitionImpl2.setEpicenter(obj5, rect);
            }
        } else {
            rect = null;
        }
        final FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl;
        final ArrayMap<String, String> arrayMap3 = arrayMap;
        final Object obj6 = obj4;
        final FragmentContainerTransition fragmentContainerTransition3 = fragmentContainerTransition;
        AnonymousClass4 r13 = r0;
        final ArrayList<View> arrayList4 = arrayList2;
        final View view2 = view;
        final Fragment fragment3 = fragment;
        final Fragment fragment4 = fragment2;
        final boolean z2 = z;
        final ArrayList<View> arrayList5 = arrayList;
        final Object obj7 = obj;
        final Rect rect2 = rect;
        AnonymousClass4 r0 = new Runnable() {
            public void run() {
                ArrayMap<String, View> a2 = FragmentTransition.a(fragmentTransitionImpl3, (ArrayMap<String, String>) arrayMap3, obj6, fragmentContainerTransition3);
                if (a2 != null) {
                    arrayList4.addAll(a2.values());
                    arrayList4.add(view2);
                }
                FragmentTransition.a(fragment3, fragment4, z2, a2, false);
                Object obj = obj6;
                if (obj != null) {
                    fragmentTransitionImpl3.swapSharedElementTargets(obj, arrayList5, arrayList4);
                    View a3 = FragmentTransition.a(a2, fragmentContainerTransition3, obj7, z2);
                    if (a3 != null) {
                        fragmentTransitionImpl3.a(a3, rect2);
                    }
                }
            }
        };
        OneShotPreDrawListener.add(viewGroup, r13);
        return obj4;
    }

    private static Object configureSharedElementsReordered(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, ArrayMap<String, String> arrayMap, FragmentContainerTransition fragmentContainerTransition, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        final Rect rect;
        final View view2;
        FragmentTransitionImpl fragmentTransitionImpl2 = fragmentTransitionImpl;
        View view3 = view;
        ArrayMap<String, String> arrayMap2 = arrayMap;
        FragmentContainerTransition fragmentContainerTransition2 = fragmentContainerTransition;
        ArrayList<View> arrayList3 = arrayList;
        ArrayList<View> arrayList4 = arrayList2;
        Object obj4 = obj;
        Fragment fragment = fragmentContainerTransition2.lastIn;
        Fragment fragment2 = fragmentContainerTransition2.firstOut;
        if (fragment != null) {
            fragment.getView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = fragmentContainerTransition2.lastInIsPop;
        Object sharedElementTransition = arrayMap.isEmpty() ? null : getSharedElementTransition(fragmentTransitionImpl, fragment, fragment2, z);
        ArrayMap<String, View> captureOutSharedElements = captureOutSharedElements(fragmentTransitionImpl, arrayMap2, sharedElementTransition, fragmentContainerTransition2);
        ArrayMap<String, View> a = a(fragmentTransitionImpl, arrayMap2, sharedElementTransition, fragmentContainerTransition2);
        if (arrayMap.isEmpty()) {
            if (captureOutSharedElements != null) {
                captureOutSharedElements.clear();
            }
            if (a != null) {
                a.clear();
            }
            obj3 = null;
        } else {
            addSharedElementsWithMatchingNames(arrayList3, captureOutSharedElements, arrayMap.keySet());
            addSharedElementsWithMatchingNames(arrayList4, a, arrayMap.values());
            obj3 = sharedElementTransition;
        }
        if (obj4 == null && obj2 == null && obj3 == null) {
            return null;
        }
        a(fragment, fragment2, z, captureOutSharedElements, true);
        if (obj3 != null) {
            arrayList4.add(view3);
            fragmentTransitionImpl.setSharedElementTargets(obj3, view3, arrayList3);
            setOutEpicenter(fragmentTransitionImpl, obj3, obj2, captureOutSharedElements, fragmentContainerTransition2.firstOutIsPop, fragmentContainerTransition2.firstOutTransaction);
            Rect rect2 = new Rect();
            View a2 = a(a, fragmentContainerTransition2, obj4, z);
            if (a2 != null) {
                fragmentTransitionImpl.setEpicenter(obj4, rect2);
            }
            rect = rect2;
            view2 = a2;
        } else {
            view2 = null;
            rect = null;
        }
        final Fragment fragment3 = fragment;
        final Fragment fragment4 = fragment2;
        final boolean z2 = z;
        final ArrayMap<String, View> arrayMap3 = a;
        final FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl;
        OneShotPreDrawListener.add(viewGroup, new Runnable() {
            public void run() {
                FragmentTransition.a(fragment3, fragment4, z2, (ArrayMap<String, View>) arrayMap3, false);
                View view = view2;
                if (view != null) {
                    fragmentTransitionImpl3.a(view, rect);
                }
            }
        });
        return obj3;
    }

    private static void configureTransitionsOrdered(FragmentManagerImpl fragmentManagerImpl, int i, FragmentContainerTransition fragmentContainerTransition, View view, ArrayMap<String, String> arrayMap) {
        Fragment fragment;
        Fragment fragment2;
        FragmentTransitionImpl chooseImpl;
        Object obj;
        FragmentManagerImpl fragmentManagerImpl2 = fragmentManagerImpl;
        FragmentContainerTransition fragmentContainerTransition2 = fragmentContainerTransition;
        View view2 = view;
        ArrayMap<String, String> arrayMap2 = arrayMap;
        ViewGroup viewGroup = fragmentManagerImpl2.n.onHasView() ? (ViewGroup) fragmentManagerImpl2.n.onFindViewById(i) : null;
        if (viewGroup != null && (chooseImpl = chooseImpl(fragment2, fragment)) != null) {
            boolean z = fragmentContainerTransition2.lastInIsPop;
            boolean z2 = fragmentContainerTransition2.firstOutIsPop;
            Object enterTransition = getEnterTransition(chooseImpl, (fragment = fragmentContainerTransition2.lastIn), z);
            Object exitTransition = getExitTransition(chooseImpl, (fragment2 = fragmentContainerTransition2.firstOut), z2);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = arrayList;
            Object obj2 = exitTransition;
            FragmentTransitionImpl fragmentTransitionImpl = chooseImpl;
            Object configureSharedElementsOrdered = configureSharedElementsOrdered(chooseImpl, viewGroup, view, arrayMap, fragmentContainerTransition, arrayList, arrayList2, enterTransition, obj2);
            Object obj3 = enterTransition;
            if (obj3 == null && configureSharedElementsOrdered == null) {
                obj = obj2;
                if (obj == null) {
                    return;
                }
            } else {
                obj = obj2;
            }
            ArrayList<View> a = a(fragmentTransitionImpl, obj, fragment2, (ArrayList<View>) arrayList3, view2);
            Object obj4 = (a == null || a.isEmpty()) ? null : obj;
            fragmentTransitionImpl.addTarget(obj3, view2);
            Object mergeTransitions = mergeTransitions(fragmentTransitionImpl, obj3, obj4, configureSharedElementsOrdered, fragment, fragmentContainerTransition2.lastInIsPop);
            if (mergeTransitions != null) {
                ArrayList arrayList4 = new ArrayList();
                FragmentTransitionImpl fragmentTransitionImpl2 = fragmentTransitionImpl;
                fragmentTransitionImpl2.scheduleRemoveTargets(mergeTransitions, obj3, arrayList4, obj4, a, configureSharedElementsOrdered, arrayList2);
                scheduleTargetChange(fragmentTransitionImpl2, viewGroup, fragment, view, arrayList2, obj3, arrayList4, obj4, a);
                ArrayList arrayList5 = arrayList2;
                fragmentTransitionImpl.a((View) viewGroup, (ArrayList<View>) arrayList5, (Map<String, String>) arrayMap2);
                fragmentTransitionImpl.beginDelayedTransition(viewGroup, mergeTransitions);
                fragmentTransitionImpl.a(viewGroup, (ArrayList<View>) arrayList5, (Map<String, String>) arrayMap2);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001e, code lost:
        r11 = r4.lastIn;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void configureTransitionsReordered(androidx.fragment.app.FragmentManagerImpl r17, int r18, androidx.fragment.app.FragmentTransition.FragmentContainerTransition r19, android.view.View r20, androidx.collection.ArrayMap<java.lang.String, java.lang.String> r21) {
        /*
            r0 = r17
            r4 = r19
            r9 = r20
            androidx.fragment.app.FragmentContainer r1 = r0.n
            boolean r1 = r1.onHasView()
            if (r1 == 0) goto L_0x0019
            androidx.fragment.app.FragmentContainer r0 = r0.n
            r1 = r18
            android.view.View r0 = r0.onFindViewById(r1)
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            goto L_0x001a
        L_0x0019:
            r0 = 0
        L_0x001a:
            r10 = r0
            if (r10 != 0) goto L_0x001e
            return
        L_0x001e:
            androidx.fragment.app.Fragment r11 = r4.lastIn
            androidx.fragment.app.Fragment r12 = r4.firstOut
            androidx.fragment.app.FragmentTransitionImpl r13 = chooseImpl(r12, r11)
            if (r13 != 0) goto L_0x0029
            return
        L_0x0029:
            boolean r14 = r4.lastInIsPop
            boolean r0 = r4.firstOutIsPop
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.lang.Object r7 = getEnterTransition(r13, r11, r14)
            java.lang.Object r6 = getExitTransition(r13, r12, r0)
            r0 = r13
            r1 = r10
            r2 = r20
            r3 = r21
            r4 = r19
            r5 = r8
            r17 = r6
            r6 = r15
            r18 = r7
            r16 = r10
            r10 = r8
            r8 = r17
            java.lang.Object r8 = configureSharedElementsReordered(r0, r1, r2, r3, r4, r5, r6, r7, r8)
            r6 = r18
            if (r6 != 0) goto L_0x0061
            if (r8 != 0) goto L_0x0061
            r7 = r17
            if (r7 != 0) goto L_0x0063
            return
        L_0x0061:
            r7 = r17
        L_0x0063:
            java.util.ArrayList r5 = a((androidx.fragment.app.FragmentTransitionImpl) r13, (java.lang.Object) r7, (androidx.fragment.app.Fragment) r12, (java.util.ArrayList<android.view.View>) r10, (android.view.View) r9)
            java.util.ArrayList r9 = a((androidx.fragment.app.FragmentTransitionImpl) r13, (java.lang.Object) r6, (androidx.fragment.app.Fragment) r11, (java.util.ArrayList<android.view.View>) r15, (android.view.View) r9)
            r0 = 4
            a(r9, r0)
            r0 = r13
            r1 = r6
            r2 = r7
            r3 = r8
            r4 = r11
            r11 = r5
            r5 = r14
            java.lang.Object r14 = mergeTransitions(r0, r1, r2, r3, r4, r5)
            if (r14 == 0) goto L_0x00a4
            replaceHide(r13, r7, r12, r11)
            java.util.ArrayList r12 = r13.a((java.util.ArrayList<android.view.View>) r15)
            r0 = r13
            r1 = r14
            r2 = r6
            r3 = r9
            r4 = r7
            r5 = r11
            r6 = r8
            r7 = r15
            r0.scheduleRemoveTargets(r1, r2, r3, r4, r5, r6, r7)
            r0 = r16
            r13.beginDelayedTransition(r0, r14)
            r1 = r13
            r2 = r0
            r3 = r10
            r4 = r15
            r5 = r12
            r6 = r21
            r1.a(r2, r3, r4, r5, r6)
            r0 = 0
            a(r9, r0)
            r13.swapSharedElementTargets(r8, r10, r15)
        L_0x00a4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentTransition.configureTransitionsReordered(androidx.fragment.app.FragmentManagerImpl, int, androidx.fragment.app.FragmentTransition$FragmentContainerTransition, android.view.View, androidx.collection.ArrayMap):void");
    }

    private static FragmentContainerTransition ensureContainer(FragmentContainerTransition fragmentContainerTransition, SparseArray<FragmentContainerTransition> sparseArray, int i) {
        if (fragmentContainerTransition != null) {
            return fragmentContainerTransition;
        }
        FragmentContainerTransition fragmentContainerTransition2 = new FragmentContainerTransition();
        sparseArray.put(i, fragmentContainerTransition2);
        return fragmentContainerTransition2;
    }

    private static String findKeyForValue(ArrayMap<String, String> arrayMap, String str) {
        int size = arrayMap.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(arrayMap.valueAt(i))) {
                return arrayMap.keyAt(i);
            }
        }
        return null;
    }

    private static Object getEnterTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return fragmentTransitionImpl.cloneTransition(z ? fragment.getReenterTransition() : fragment.getEnterTransition());
    }

    private static Object getExitTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return fragmentTransitionImpl.cloneTransition(z ? fragment.getReturnTransition() : fragment.getExitTransition());
    }

    private static Object getSharedElementTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(z ? fragment2.getSharedElementReturnTransition() : fragment.getSharedElementEnterTransition()));
    }

    private static Object mergeTransitions(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        return (obj == null || obj2 == null || fragment == null) ? true : z ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap() ? fragmentTransitionImpl.mergeTransitionsTogether(obj2, obj, obj3) : fragmentTransitionImpl.mergeTransitionsInSequence(obj2, obj, obj3);
    }

    private static void replaceHide(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, final ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            fragmentTransitionImpl.scheduleHideFragmentView(obj, fragment.getView(), arrayList);
            OneShotPreDrawListener.add(fragment.mContainer, new Runnable() {
                public void run() {
                    FragmentTransition.a(arrayList, 4);
                }
            });
        }
    }

    private static FragmentTransitionImpl resolveSupportImpl() {
        try {
            return (FragmentTransitionImpl) Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    private static void retainValues(ArrayMap<String, String> arrayMap, ArrayMap<String, View> arrayMap2) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            if (!arrayMap2.containsKey(arrayMap.valueAt(size))) {
                arrayMap.removeAt(size);
            }
        }
    }

    private static void scheduleTargetChange(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        final Object obj3 = obj;
        final FragmentTransitionImpl fragmentTransitionImpl2 = fragmentTransitionImpl;
        final View view2 = view;
        final Fragment fragment2 = fragment;
        final ArrayList<View> arrayList4 = arrayList;
        final ArrayList<View> arrayList5 = arrayList2;
        final ArrayList<View> arrayList6 = arrayList3;
        final Object obj4 = obj2;
        ViewGroup viewGroup2 = viewGroup;
        OneShotPreDrawListener.add(viewGroup, new Runnable() {
            public void run() {
                Object obj = obj3;
                if (obj != null) {
                    fragmentTransitionImpl2.removeTarget(obj, view2);
                    arrayList5.addAll(FragmentTransition.a(fragmentTransitionImpl2, obj3, fragment2, (ArrayList<View>) arrayList4, view2));
                }
                if (arrayList6 != null) {
                    if (obj4 != null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(view2);
                        fragmentTransitionImpl2.replaceTargets(obj4, arrayList6, arrayList);
                    }
                    arrayList6.clear();
                    arrayList6.add(view2);
                }
            }
        });
    }

    private static void setOutEpicenter(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, ArrayMap<String, View> arrayMap, boolean z, BackStackRecord backStackRecord) {
        if (backStackRecord.r != null && !backStackRecord.r.isEmpty()) {
            View view = arrayMap.get((z ? backStackRecord.s : backStackRecord.r).get(0));
            fragmentTransitionImpl.setEpicenter(obj, view);
            if (obj2 != null) {
                fragmentTransitionImpl.setEpicenter(obj2, view);
            }
        }
    }
}
