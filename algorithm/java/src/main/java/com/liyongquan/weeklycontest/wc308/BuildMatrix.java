package com.liyongquan.weeklycontest.wc308;

import java.util.HashSet;
import java.util.Set;

public class BuildMatrix {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int m = rowConditions.length;
        int n = colConditions.length;
        int[] rowDegree = new int[k];
        Set<Integer>[] rowGraph = new Set[k];
        for (int i = 0; i < k; i++) {
            rowGraph[i] = new HashSet<>();
        }
        for (int i = 0; i < m; i++) {
            int top = rowConditions[i][0];
            int down = rowConditions[i][1];
            if (rowGraph[top - 1].add(down - 1)) {
                rowDegree[down - 1]++;
            }
        }
        int[] colDegree = new int[k];
        Set<Integer>[] colGraph = new Set[k];
        for (int i = 0; i < k; i++) {
            colGraph[i] = new HashSet<>();
        }
        for (int i = 0; i < n; i++) {
            int top = colConditions[i][0];
            int down = colConditions[i][1];
            if (colGraph[top - 1].add(down - 1)) {
                colDegree[down - 1]++;
            }
        }
        int[] rows = new int[k];
        for (int i = 0; i < k; i++) {
            int j = 0;
            while (j < k && rowDegree[j] != 0) {
                j++;
            }
            if (j == k) {
                return new int[][]{};
            }
            //rows[0] = j + 1;
            rows[j] = i;
            rowDegree[j] = -1;
            for (Integer to : rowGraph[j]) {
                rowDegree[to]--;
            }
        }
        int[] cols = new int[k];
        for (int i = 0; i < k; i++) {
            int j = 0;
            while (j < k && colDegree[j] != 0) {
                j++;
            }
            if (j == k) {
                return new int[][]{};
            }
            cols[j] = i;
            colDegree[j] = -1;
            for (Integer to : colGraph[j]) {
                colDegree[to]--;
            }
        }
        int[][] res = new int[k][k];
        for (int i = 0; i < k; i++) {
            int r = rows[i];
            int c = cols[i];
            res[r][c] = i + 1;
        }
        return res;
    }
}
