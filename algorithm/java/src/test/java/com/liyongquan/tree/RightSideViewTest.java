package com.liyongquan.tree;

import com.liyongquan.util.tree.BinaryTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class RightSideViewTest {
    private RightSideView rsv = new RightSideView();

    @Test
    public void rightSideView() {
        TreeNode root = BinaryTreeUtil.deserialize(new Integer[]{1, 2, 3, null, 5, null, 4});
        List<Integer> res = rsv.rightSideView(root);
        res.stream().forEach(x -> log.info("{}", x));
    }
}