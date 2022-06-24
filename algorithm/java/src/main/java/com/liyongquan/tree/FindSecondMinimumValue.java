package com.liyongquan.tree;

import com.liyongquan.dp.Robber;

/**
 * @author liyongquan
 * @date 2021/7/27
 */
public class FindSecondMinimumValue {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null) {
            return -1;
        }
        int left = findSecondMinimumValue(root.left);
        int right = findSecondMinimumValue(root.right);
        int res = Integer.MAX_VALUE;
        if (root.left.val > root.val) {
            res = Math.min(res, root.left.val);
        }
        if (root.right.val > root.val) {
            res = Math.min(res, root.right.val);
        }
        if (left > root.val) {
            res = Math.min(res, left);
        }
        if (right > root.val) {
            res = Math.min(res, right);
        }
        return res;
    }
}

