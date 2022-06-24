package com.liyongquan.tree;

import com.liyongquan.util.tree.BinaryTreeUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BalanceBSTTest {
    private BalanceBST bst = new BalanceBST();

    /**
     * [1,null,2,null,3,null,4,null,null]
     */
    @Test
    public void balanceBST() {
        TreeNode root = BinaryTreeUtil.deserialize(new Integer[]{1, null, 2, null, 3, null, 4, null, null});
        TreeNode nr = bst.balanceBST(root);
        BinaryTreeUtil.print(nr);
        System.out.println("finish");
    }

    @Test
    public void balanceBST2() {
        TreeNode root = BinaryTreeUtil.deserialize(new Integer[]{1, null, 15, 14, 17, 7, null, null, null, 2, 12, null, 3, 9, null, null, null, null, 11});
        TreeNode nr = bst.balanceBST(root);
        BinaryTreeUtil.print(nr);
        System.out.println("finish");
    }

    @Test
    public void rotateLeft() {
        /**
         * RR的结构
         */
        TreeNode root = BinaryTreeUtil.deserialize(new Integer[]{1, null, 2, null, 3});
        Map<TreeNode, Integer> nodeHeight = new HashMap<>();
        BalanceBST.initHeight(root, 0, nodeHeight);
        TreeNode nr = bst.rotateLeft(root, nodeHeight);
        BinaryTreeUtil.print(nr);
        /**
         * RR结构，但是左子树不为空
         */
        root = BinaryTreeUtil.deserialize(new Integer[]{1, null, 3, 2, 4});
        nodeHeight = new HashMap<>();
        BalanceBST.initHeight(root, 0, nodeHeight);
        printHeight(nodeHeight);
        nr = bst.rotateLeft(root, nodeHeight);
        BinaryTreeUtil.print(nr);
        printHeight(nodeHeight);
    }

    private void printHeight(Map<TreeNode, Integer> height) {
        System.out.println("===========树的高度==========");
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<TreeNode, Integer> entry : height.entrySet()) {
            sb.append(entry.getKey().val + ":" + entry.getValue() + ",");
        }
        System.out.println(sb.toString());
    }
}