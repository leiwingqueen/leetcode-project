package com.liyongquan.tree;

import com.liyongquan.util.tree.BinaryTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LeafSimilarTest {
    private LeafSimilar ls = new LeafSimilar();

    @Test
    public void leafSimilar() {
        TreeNode root1 = BinaryTreeUtil.deserialize(new Integer[]{1, 2});
        TreeNode root2 = BinaryTreeUtil.deserialize(new Integer[]{2, 2});
        boolean res = ls.leafSimilar(root1, root2);
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }
}