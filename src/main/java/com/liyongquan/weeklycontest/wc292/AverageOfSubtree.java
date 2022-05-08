package com.liyongquan.weeklycontest.wc292;

import com.liyongquan.tree.TreeNode;

public class AverageOfSubtree {
    private int cnt = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return cnt;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int c = left[0] + right[0] + 1;
        int s = left[1] + right[1] + root.val;
        if (s / c == root.val) {
            cnt++;
        }
        return new int[]{c, s};
    }
}
