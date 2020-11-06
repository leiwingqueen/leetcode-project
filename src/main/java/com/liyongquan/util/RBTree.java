package com.liyongquan.util;

/**
 * 红黑树的原理介绍
 * https://www.cnblogs.com/LiaHon/p/11203229.html
 * <p>
 * treemap常用操作
 * https://www.cnblogs.com/LiaHon/p/11221634.html
 * <p>
 * 纸上得来终觉浅，觉知此事要撸码
 *
 * 红黑树的特点：
 * 性质1：每个节点要么是黑色，要么是红色。
 *
 * 性质2：根节点是黑色。
 *
 * 性质3：每个叶子节点（NIL）是黑色。
 *
 * 性质4：每个红色结点的两个子结点一定都是黑色。
 *
 * 性质5：任意一结点到每个叶子结点的路径都包含数量相同的黑结点。
 */
public class RBTree {
    RBNode root;

    /**
     * 查找过程跟二叉搜索树没有任何区别
     *
     * @param key
     * @return
     */
    public RBNode search(int key) {
        RBNode node = root;
        while (node != null) {
            if (node.key == key) {
                return node;
            } else if (key < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    public static class RBNode {
        // r or b
        char color;
        int key;
        RBNode left;
        RBNode right;
    }
}
