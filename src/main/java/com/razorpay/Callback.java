package com.razorpay;

import proguard.annotation.Keep;
import proguard.annotation.KeepClassMembers;

@Keep
@KeepClassMembers
interface Callback {
    void run(t_$J_ t__j_);
}
