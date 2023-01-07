package com.liyongquan.weeklycontest.bwc95;

public class CategorizeBox {
    public String categorizeBox(int length, int width, int height, int mass) {
        boolean bulky = false;
        long square = (long) length * width * height;
        if ((length > 10_000 || width > 10_000 || height > 10_000 || mass > 10_000) && square >= 1_000_000_000) {
            bulky = true;
        }
        boolean heavy = mass >= 100;
        if (bulky && heavy) {
            return "Both";
        } else if (bulky) {
            return "Bulky";
        } else if (heavy) {
            return "Heavy";
        } else {
            return "Neither";
        }
    }
}
