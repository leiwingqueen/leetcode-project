package com.liyongquan.tree;

import com.liyongquan.util.tree.BinaryTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class InorderTraversalTest {
    private InorderTraversal it = new InorderTraversal();

    @Test
    public void inorderTraversal2() {
        Integer[] arr = {1, null, 2, 3};
        TreeNode root = BinaryTreeUtil.deserialize(arr);
        List<Integer> res = it.inorderTraversal2(root);
        for (Integer re : res) {
            log.info("{}", re);
        }
    }
}