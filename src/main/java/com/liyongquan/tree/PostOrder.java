package com.liyongquan.tree;

import lombok.extern.slf4j.Slf4j;

/**
 * 二叉树的后序遍历
 *
 * 把二叉树的后续遍历还原成一颗BST
 */
@Slf4j
public class PostOrder {
    public TreeNode fun(int[] array) {
        return build(array, 0, array.length - 1);
    }

    private TreeNode build(int[] array, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(array[start]);
        }
        TreeNode root = new TreeNode(array[end]);
        int idx = start;
        while (idx <= end && array[idx] < root.val) {
            idx++;
        }
        //左子树[start,idx-1]，右子树[idx,end]
        TreeNode left = build(array, start, idx - 1);
        TreeNode right = build(array, idx, end - 1);
        root.left = left;
        root.right = right;
        return root;
    }
}
