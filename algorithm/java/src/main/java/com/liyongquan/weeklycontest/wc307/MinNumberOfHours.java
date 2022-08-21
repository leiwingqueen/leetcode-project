package com.liyongquan.weeklycontest.wc307;

import com.liyongquan.weeklycontest.wc306.EdgeScore;

public class MinNumberOfHours {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int r1 = 0, r2 = 0;
        int n = energy.length;
        for (int i = 0; i < n; i++) {
            if (initialEnergy <= energy[i]) {
                int diff = energy[i] - initialEnergy + 1;
                initialEnergy += diff;
                r1 += diff;
            }
            if (initialExperience <= experience[i]) {
                int diff = experience[i] - initialExperience + 1;
                initialExperience += diff;
                r2 += diff;
            }
            initialEnergy -= energy[i];
            initialExperience += experience[i];
        }
        return r1 + r2;
    }
}
