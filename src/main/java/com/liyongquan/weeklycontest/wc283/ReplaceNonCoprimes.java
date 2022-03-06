package com.liyongquan.weeklycontest.wc283;

import java.util.*;

public class ReplaceNonCoprimes {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        Deque<Integer> deque = new LinkedList<>();
        for (int num : nums) {
            deque.offerLast(num);
        }
        LinkedList<Integer> res = new LinkedList<>();
        while (!deque.isEmpty()) {
            Integer a = deque.pollFirst();
            if (deque.isEmpty()) {
                res.offerLast(a);
                return res;
            }
            Integer b = deque.pollFirst();
            int gcd = gcd(a, b);
            if (gcd == 1) {
                res.add(a);
                deque.offerFirst(b);
            } else {
                deque.offerFirst(a / gcd * b);
                if (!res.isEmpty()) {
                    deque.offerFirst(res.pollLast());
                }
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
