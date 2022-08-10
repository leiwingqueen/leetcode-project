package com.liyongquan.weeklycontest.wc301;

import java.util.Iterator;
import java.util.TreeSet;

public class SmallestInfiniteSet {
    private TreeSet<Integer> treeSet;

    public SmallestInfiniteSet() {
        treeSet = new TreeSet<>();
    }

    public int popSmallest() {
        if (treeSet.size() == 0) {
            treeSet.add(1);
            return 1;
        } else {
            Iterator<Integer> it = treeSet.iterator();
            int pre = 0;
            while (it.hasNext()) {
                Integer next = it.next();
                if (next - pre > 1) {
                    break;
                }
                pre = next;
            }
            treeSet.add(pre + 1);
            return pre + 1;
        }
    }

    public void addBack(int num) {
        treeSet.remove(num);
    }
}
