package com.liyongquan.tree;

import com.liyongquan.util.tree.BinaryTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaxPathSumTest {
    private MaxPathSum mps = new MaxPathSum();

    @Test
    public void maxPathSum() {
        TreeNode root = BinaryTreeUtil.deserialize(new Integer[]{-10, 9, 20, null, null, 15, 7});
        int res = mps.maxPathSum(root);
        log.info("{}", res);
        Assert.assertEquals(42, res);
    }

    @Test
    public void test2() {
        TreeNode root = BinaryTreeUtil.deserialize(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1});
        int res = mps.maxPathSum(root);
        log.info("{}", res);
        Assert.assertEquals(48, res);
    }
}