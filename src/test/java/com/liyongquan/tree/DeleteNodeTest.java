package com.liyongquan.tree;

import com.liyongquan.util.tree.BinaryTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class DeleteNodeTest {
    private DeleteNode dn = new DeleteNode();

    @Test
    public void deleteNode() {
        Integer[] array = new Integer[]{5, 3, 6, 2, 4, null, 7};
        TreeNode root = BinaryTreeUtil.deserialize(array);
        TreeNode nr = dn.deleteNode2(root, 3);
        List<Integer> serialize = BinaryTreeUtil.serialize(nr);
        for (Integer se : serialize) {
            log.info("{}", se);
        }
    }
}