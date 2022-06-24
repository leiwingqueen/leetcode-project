package com.liyongquan.weeklycontest.bwc49;

public class SquareIsWhite {
    public boolean squareIsWhite(String coordinates) {
        int idx1 = (coordinates.charAt(0) - 'a') & 0b1;
        int idx2 = (coordinates.charAt(1) - 1) & 0b1;
        return (idx1 ^ idx2) == 1;
    }
}
