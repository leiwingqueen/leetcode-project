package com.liyongquan.weeklycontest.wc284;

public class DigArtifacts {
    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        int[][] digM = new int[n][n];
        for (int[] d : dig) {
            digM[d[0]][d[1]] = 1;
        }
        int cnt = 0;
        for (int[] art : artifacts) {
            int r1 = art[0];
            int c1 = art[1];
            int r2 = art[2];
            int c2 = art[3];
            boolean flag = true;
            for (int i = r1; i <= r2; i++) {
                for (int j = c1; j <= c2; j++) {
                    if (digM[i][j] == 0) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            if (flag) {
                cnt++;
            }
        }
        return cnt;
    }
}
