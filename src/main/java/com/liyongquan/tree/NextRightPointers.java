package com.liyongquan.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NextRightPointers {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        List<Node> left = left(root.left, 1);
        List<Node> right = left(root.right, 0);
        int length = Math.min(left.size(), right.size());
        for (int i = 0; i < length; i++) {
            left.get(i).next = right.get(i);
        }
        connect(root.left);
        connect(root.right);
        return root;
    }

    private List<Node> left(Node node, int lr) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<Node> result = new LinkedList<>();
        result.add(node);
        List<Node> left = left(node.left, lr);
        List<Node> right = left(node.right, lr);
        int length = Math.max(left.size(), right.size());
        for (int i = 0; i < length; i++) {
            if (lr == 0) {
                if (i < left.size()) {
                    result.add(left.get(i));
                } else {
                    result.add(right.get(i));
                }
            } else {
                if (i < right.size()) {
                    result.add(right.get(i));
                } else {
                    result.add(left.get(i));
                }
            }
        }
        return result;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
