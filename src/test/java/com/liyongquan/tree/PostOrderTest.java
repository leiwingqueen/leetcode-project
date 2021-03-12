package com.liyongquan.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class PostOrderTest {
    private PostOrder po=new PostOrder();
    @Test
    public void fun() {
        TreeNode t = po.fun(new int[]{1, 4, 3, 6, 9, 7, 5});
        log.info("{}",t.val);
    }
}