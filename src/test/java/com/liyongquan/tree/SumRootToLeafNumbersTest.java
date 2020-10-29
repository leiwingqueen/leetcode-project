package com.liyongquan.tree;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SumRootToLeafNumbersTest {

    @Test
    public void sumNumbers() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        SumRootToLeafNumbers sr = new SumRootToLeafNumbers();
        int i = sr.sumNumbers(root);
        Assert.assertEquals(1, i);
    }
}