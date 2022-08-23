package com.liyongquan.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class PrintBinaryTreeTest {
    private PrintBinaryTree binaryTree = new PrintBinaryTree();

    @Test
    public void printTree() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        List<List<String>> lists = binaryTree.printTree(treeNode);
        for (List<String> list : lists) {
            log.info("{}", list);
        }
    }
}