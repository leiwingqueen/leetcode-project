package com.liyongquan.tree;

import com.liyongquan.util.tree.BinaryTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class GenerateTreesTest {

    @Test
    public void generateTrees() {
        GenerateTrees gt = new GenerateTrees();
        List<TreeNode> list = gt.generateTrees(3);
        for (TreeNode node : list) {
            List<Integer> serialize = BinaryTreeUtil.serialize(node);
            StringBuilder sb = new StringBuilder();
            for (Integer i : serialize) {
                sb.append(i + ",");
            }
            log.info(sb.toString());
        }
    }
}