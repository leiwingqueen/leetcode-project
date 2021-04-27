package com.liyongquan.tree;

import com.liyongquan.util.tree.BinaryTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RangeSumBSTTest {
    private RangeSumBST bst = new RangeSumBST();

    /**
     * [15,9,21,7,13,19,23,5,null,11,null,17]
     * 19
     * 21
     */
    @Test
    public void rangeSumBST() {
        TreeNode root = BinaryTreeUtil.deserialize(new Integer[]{15, 9, 21, 7, 13, 19, 23, 5, null, 11, null, 17});
        int res = bst.rangeSumBST(root, 19, 21);
        log.info("{}", res);
        Assert.assertEquals(40, res);
    }
}