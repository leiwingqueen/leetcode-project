package com.liyongquan.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    private static class Pair {
        Node key;
        int value;
        Pair(Node key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    /**
     * 递归解法。
     * 假设节点的数量为n，时间复杂度O(n^2)。
     * 连接过程需要对所有节点扫描一遍，计算某一个节点下的所有的最左节点/最右节点需要O(n)的复杂度
     *
     * @param root
     * @return
     */
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

    /**
     * 这里适合用广度优先搜索。时间复杂度O(n)，空间复杂度O(1)
     *
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        Node pre = null;
        int depth = 0;
        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            if (pre != null && depth == poll.value) {
                pre.next = poll.key;
            }
            if (depth != poll.value) {
                depth = poll.value;
            }
            pre = poll.key;
            if (pre.left != null) {
                queue.add(new Pair(pre.left, depth + 1));
            }
            if (pre.right != null) {
                queue.add(new Pair(pre.right, depth + 1));
            }
        }
        return root;
    }

    /**
     * 广度优先搜索优化。减少一个depth的变量
     *
     * @param root
     * @return
     */
    public Node connect3(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (pre != null) {
                    pre.next = node;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                pre = node;
            }
        }
        return root;
    }

    /**
     * 利用链表的特征，进行扫描，进一步把空间复杂度降为O(1)
     *
     * @param root
     * @return
     */
    public Node connect4(Node root) {
        if (root == null) {
            return null;
        }
        Node preHead = root;
        while (preHead != null) {
            Node p1 = preHead;
            Node curHead = null, p2 = null;
            while (p1 != null) {
                if (p1.left != null) {
                    if (curHead == null) {
                        curHead = p1.left;
                        p2 = curHead;
                    } else {
                        p2.next = p1.left;
                        p2 = p2.next;
                    }
                }
                if (p1.right != null) {
                    if (curHead == null) {
                        curHead = p1.right;
                        p2 = curHead;
                    } else {
                        p2.next = p1.right;
                        p2 = p2.next;
                    }
                }
                p1 = p1.next;
            }
            preHead = curHead;
        }
        return root;
    }

    /**
     * 在4的基础上优化下代码可读性
     *
     * @param root
     * @return
     */
    public Node connect5(Node root) {
        if (root == null) {
            return null;
        }
        Node preHead = root;
        while (preHead != null) {
            Node curHead = null, p2 = null;
            for (Node p1 = preHead; p1 != null; p1 = p1.next) {
                if (p1.left != null) {
                    if (curHead == null) {
                        curHead = p1.left;
                        p2 = curHead;
                    } else {
                        p2.next = p1.left;
                        p2 = p2.next;
                    }
                }
                if (p1.right != null) {
                    if (curHead == null) {
                        curHead = p1.right;
                        p2 = curHead;
                    } else {
                        p2.next = p1.right;
                        p2 = p2.next;
                    }
                }
            }
            preHead = curHead;
        }
        return root;
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
