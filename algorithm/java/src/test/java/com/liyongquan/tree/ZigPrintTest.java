package com.liyongquan.tree;

import com.liyongquan.util.tree.BinaryTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class ZigPrintTest {
    private ZigPrint zigPrint = new ZigPrint();

    @Test
    public void print() {
        TreeNode root = BinaryTreeUtil.deserialize(new Integer[]{3, 9, 20, 1, 2, 15, 7});
        List<List<Integer>> res = zigPrint.print(root);
        for (List<Integer> list : res) {
            StringBuilder sb = new StringBuilder();
            for (Integer num : list) {
                sb.append(num + ",");
            }
            log.info("{}", sb.toString());
        }
    }
}