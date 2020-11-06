package com.liyongquan.util;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

public class RBTreeTest {
    @Test
    public void insert() {
        RBTree tree = new RBTree();
        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }
        printTree(tree);
    }

    /**
     * 逐层打印二叉树
     */
    private void printTree(RBTree tree) {
        if (tree.root == null) {
            System.out.println("[]");
            return;
        }
        Queue<RBTree.RBNode> queue = new LinkedList<>();
        queue.add(tree.root);
        while (queue.size() > 0) {
            int size = queue.size();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < size; i++) {
                RBTree.RBNode poll = queue.poll();
                builder.append(poll.getData() + ",");
                if (poll.getData() != null) {
                    queue.offer(poll.getLeft() != null ? poll.getLeft() : RBTree.RBNode.empty());
                    queue.offer(poll.getRight() != null ? poll.getRight() : RBTree.RBNode.empty());
                }
            }
            System.out.println(builder.toString());
        }
    }
}