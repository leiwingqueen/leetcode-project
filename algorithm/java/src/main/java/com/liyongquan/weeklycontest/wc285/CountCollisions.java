package com.liyongquan.weeklycontest.wc285;

import java.util.Stack;

public class CountCollisions {

    private static class Pair {
        int key;
        char value;
        Pair(int key, char value) {
            this.key = key;
            this.value = value;
        }
    }
    public int countCollisions(String directions) {
        Stack<Pair> rStack = new Stack<>();
        Stack<Character> lStack = new Stack<>();
        int n = directions.length();
        int cnt = 0;
        for (int i = n - 1; i > 0; i--) {
            char ch = directions.charAt(i);
            if (ch == 'L') {
                rStack.add(new Pair(i, ch));
            } else if (ch == 'R') {
                if (rStack.size() > 0) {
                    rStack.add(new Pair(i, 'S'));
                }
            } else {
                rStack.add(new Pair(i, ch));
            }
        }
        for (int i = 0; i < n; i++) {
            char ch = directions.charAt(i);
            if (rStack.size() > 0 && rStack.peek().key <= i) {
                rStack.pop();
            }
            if (ch == 'L') {
                if (lStack.size() > 0) {
                    if (lStack.peek() == 'R') {
                        cnt += 1;
                    } else {
                        cnt += 1;
                    }
                    lStack.add('S');
                }
            } else if (ch == 'S') {
                lStack.add('S');
            } else {
                if (rStack.size() > 0) {
                    if (rStack.peek().value == 'L') {
                        cnt += 1;
                    } else {
                        cnt += 1;
                    }
                }
                lStack.add('L');
            }
        }
        return cnt;
    }
}
