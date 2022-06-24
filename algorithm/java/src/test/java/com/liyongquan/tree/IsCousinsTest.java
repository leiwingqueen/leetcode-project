package com.liyongquan.tree;

import com.liyongquan.util.tree.BinaryTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class IsCousinsTest {
    private IsCousins isCousins = new IsCousins();

    @Test
    public void isCousins() {
        TreeNode root = BinaryTreeUtil.deserialize(new Integer[]{1, 2, 3, null, 4});
        boolean res = isCousins.isCousins(root, 2, 3);
        log.info("{}", res);
    }

    @Test
    public void test2() {
        TreeNode root = BinaryTreeUtil.deserialize(new Integer[]{1, 2, 3, null, null, 4, 5});
        boolean res = isCousins.isCousins(root, 4, 5);
        log.info("{}", res);
        Assert.assertEquals(false, res);
    }

    /**
     * [1,2,4,3,19,10,5,15,8,null,null,13,14,null,6,null,17,null,null,null,null,18,null,7,11,null,null,null,null,null,9,16,12,null,null,20]
     * 11
     * 17
     */
    @Test
    public void test3() {
        TreeNode root = BinaryTreeUtil.deserialize(new Integer[]{1, 2, 4, 3, 19, 10, 5, 15, 8, null, null, 13, 14, null, 6, null, 17, null, null, null, null, 18, null, 7, 11, null, null, null, null, null, 9, 16, 12, null, null, 20});
        boolean res = isCousins.isCousins(root, 11, 17);
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }
}