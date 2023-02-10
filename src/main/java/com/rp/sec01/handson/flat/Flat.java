package com.rp.sec01.handson.flat;

import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;

public class Flat {
    public static void main(String[] args) {
        Customer
                .getCustomers()
                .concatMap(c -> Order.getOrdersForCus(c.getId()))
                .subscribe(new DefSubscriber<>());

        Util.sleepSeconds(20);
    }
}
