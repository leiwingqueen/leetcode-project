package com.liyongquan.tree;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinAbsTest {
    private MinAbs ma = new MinAbs();

    @Test
    public void getMinimumDifference() {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(5), n2 = new TreeNode(3);
        root.right = n1;
        n1.left = n2;
        int result = ma.getMinimumDifference(root);
        Assert.assertEquals(2, result);
    }
}