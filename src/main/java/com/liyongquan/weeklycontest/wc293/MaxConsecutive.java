package com.liyongquan.weeklycontest.wc293;

public class MaxConsecutive {
    int res = 0;

    public int maxConsecutive(int bottom, int top, int[] special) {
        Node node = new Node(bottom, top);
        for (int sp : special) {
            search(node, sp);
        }
        dfs(node);
        return res;
    }

    private void dfs(Node root) {
        if (root.left == null && root.right == null) {
            if (!root.del) {
                res = Math.max(res, root.top - root.bottom + 1);
            }
        } else {
            if (root.left != null) {
                dfs(root.left);
            }
            if (root.right != null) {
                dfs(root.right);
            }
        }
    }

    private void search(Node root, int sp) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            //[left,sp-1],[sp+1,right]
            if (sp - 1 >= root.bottom) {
                root.left = new Node(root.bottom, sp - 1);
            }
            if (root.top >= sp + 1) {
                root.right = new Node(sp + 1, root.top);
            }
            if (root.left == null && root.right == null) {
                root.del = true;
            }
        } else {
            if (root.left != null && sp <= root.left.top) {
                search(root.left, sp);
            } else {
                search(root.right, sp);
            }
        }
    }

    private static class Node {
        int bottom;
        int top;
        Node left;
        Node right;
        boolean del;

        public Node(int bottom, int top) {
            this.bottom = bottom;
            this.top = top;
            del = false;
        }
    }
}
