package com.liyongquan.util;

/**
 * 红黑树的原理介绍
 * https://www.cnblogs.com/LiaHon/p/11203229.html
 * <p>
 * treemap常用操作
 * https://www.cnblogs.com/LiaHon/p/11221634.html
 * 为什么需要红黑树
 * https://zhuanlan.zhihu.com/p/72505589
 * <p>
 * 纸上得来终觉浅，觉知此事要撸码
 * <p>
 * 红黑树的特点：
 * 性质1：每个节点要么是黑色，要么是红色。
 * <p>
 * 性质2：根节点是黑色。
 * <p>
 * 性质3：每个叶子节点（NIL）是黑色。
 * <p>
 * 性质4：每个红色结点的两个子结点一定都是黑色。
 * <p>
 * 性质5：任意一结点到每个叶子结点的路径都包含数量相同的黑结点。
 */
public class RBTree {
    RBNode root;

    /**
     * 查找过程跟二叉搜索树没有任何区别
     *
     * @param data
     * @return
     */
    public RBNode search(int data) {
        RBNode node = root;
        while (node != null) {
            if (node.data == data) {
                return node;
            } else if (data < node.data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    public boolean insert(int data) {
        if (root == null) {
            root = new RBNode();
            root.data = data;
            root.color = 'b';
            return true;
        }
        //找到插入的位置
        RBNode node = root, parent = null;
        while (node != null) {
            parent = node;
            if (data == node.data) {
                return false;
            } else if (data < node.data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        //插入
        RBNode nd = new RBNode();
        nd.color = 'r';
        nd.data = data;
        if (data < parent.data) {
            parent.left = nd;
        } else {
            parent.right = nd;
        }
        return true;
    }

    public static class RBNode {
        // r or b
        private char color;
        private Integer data;
        private RBNode left;
        private RBNode right;

        public static RBNode empty() {
            RBNode node = new RBNode();
            node.setColor('b');
            return node;
        }

        public char getColor() {
            return color;
        }

        public void setColor(char color) {
            this.color = color;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }
    }
}
