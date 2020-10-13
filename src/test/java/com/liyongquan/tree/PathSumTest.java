package com.liyongquan.tree;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathSumTest {
    private PathSum ps = new PathSum();

    @Test
    public void pathSum2() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        int i = ps.pathSum2(root, 2);
        Assert.assertEquals(1, i);
    }
}