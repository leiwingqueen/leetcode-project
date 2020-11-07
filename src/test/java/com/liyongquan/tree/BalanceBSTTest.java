package com.liyongquan.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class BalanceBSTTest {
    private BalanceBST bst = new BalanceBST();

    @Test
    public void balanceBST() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n1.right = n2;
        n2.right = n3;
        n3.right = n4;
        TreeNode root = bst.balanceBST(n1);
        System.out.println("finish");
    }
}