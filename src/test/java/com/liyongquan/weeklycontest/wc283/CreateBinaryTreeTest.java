package com.liyongquan.weeklycontest.wc283;

import com.liyongquan.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CreateBinaryTreeTest {
    private CreateBinaryTree cbt = new CreateBinaryTree();

    @Test
    public void createBinaryTree() {
        int[][] desc = {
                {20, 15, 1}, {20, 17, 0}, {50, 20, 1}, {50, 80, 0}, {80, 19, 1}
        };
        TreeNode node = cbt.createBinaryTree(desc);
        log.info("{}", node.val);
    }
}