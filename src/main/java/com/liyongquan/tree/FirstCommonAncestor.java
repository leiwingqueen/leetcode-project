package com.liyongquan.tree;

import java.util.LinkedList;

/**
 * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
 * <p>
 * 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * 3
 * / \
 * 5   1
 * / \ / \
 * 6  2 0  8
 * / \
 * 7   4
 * 示例 1:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * 说明:
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-common-ancestor-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FirstCommonAncestor {
    /**
     * 场景1：假设p/q为根节点，则认为根节点为最近公共祖先。
     * 场景2: 若p,q分别在节点m的左右子树上，则认为m为结果
     * 场景3: 若p,q均在左子树/右子树，则继续往下查找(递归)
     * <p>
     * 问题：如何判断p,q是否在左/右子树，可以使用bfs/dfs进行查找
     * <p>
     * 两重递归，时间复杂度O(n^2)，勉强能accept
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        boolean l1 = find(root.left, p);
        boolean l2 = find(root.left, q);
        boolean r1 = find(root.right, p);
        boolean r2 = find(root.right, q);
        if ((l1 && r2) || (l2 && r1)) {
            return root;
        }
        if (l1) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    private boolean find(TreeNode root, TreeNode p) {
        if (root == null) {
            return false;
        }
        if (root == p) {
            return true;
        }
        if (root.left != null) {
            if (find(root.left, p)) {
                return true;
            }
        }
        if (root.right != null) {
            if (find(root.right, p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 回溯法，找到节点p,q的所有的祖先列表(自己也是自己的祖先)，然后找到两个列表的最后一个相同的节点就是最近的祖先。
     * <p>
     * 时间复杂度O(n)，空间复杂度O(n) Accept
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> r1 = new LinkedList<>(), r2 = new LinkedList<>();
        findAncestor(root, p, r1);
        findAncestor(root, q, r2);
        /*StringBuilder sb = new StringBuilder();
        for (TreeNode node : r1) {
            sb.append(node.val + ",");
        }
        sb.append("\n");
        for (TreeNode node : r2) {
            sb.append(node.val+",");
        }
        System.out.println(sb.toString());*/
        TreeNode result = null;
        for (int i = 0; i < Math.min(r1.size(), r2.size()); i++) {
            if (r1.get(i) == r2.get(i)) {
                result = r1.get(i);
            } else {
                break;
            }
        }
        return result;
    }

    private boolean findAncestor(TreeNode root, TreeNode p, LinkedList<TreeNode> result) {
        if (root == null) {
            return false;
        }
        if (root == p) {
            result.add(p);
            return true;
        }
        result.offerLast(root);
        if (findAncestor(root.left, p, result)) {
            return true;
        }
        if (findAncestor(root.right, p, result)) {
            return true;
        }
        result.pollLast();
        return false;
    }
}
