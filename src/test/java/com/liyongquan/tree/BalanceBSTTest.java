package com.liyongquan.tree;

import com.liyongquan.util.tree.BinaryTreeUtil;
import org.junit.Test;

import static org.junit.Assert.*;

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
}