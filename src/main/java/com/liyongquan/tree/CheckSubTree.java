package com.liyongquan.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
 * <p>
 * 如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
 * <p>
 * 示例1:
 * <p>
 * 输入：t1 = [1, 2, 3], t2 = [2]
 * 输出：true
 * 示例2:
 * <p>
 * 输入：t1 = [1, null, 2, 4], t2 = [3, 2]
 * 输出：false
 * 提示：
 * <p>
 * 树的节点数目范围为[0, 20000]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-subtree-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CheckSubTree {
    /**
     * 暴力解法。
     * 先找到t2的根节点在t1中的位置n，再检查两棵树是否一致
     * <p>
     * 时间复杂度O(n*m).n,m分别是t1,t2的结点数量
     *
     * @param t1
     * @param t2
     * @return
     */
    public boolean checkSubTree2(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        //bfs
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(t1);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == t2.val) {
                if (check(node, t2)) {
                    return true;
                }
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return false;
    }

    private boolean check(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return check(t1.left, t2.left) && check(t1.right, t2.right);
    }


    /**
     * 稍改为使用递归，理论是时间复杂度是一样的，但是递归比非递归要快好多。。
     *
     * @param t1
     * @param t2
     * @return
     */
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        if (t1.val == t2.val && check(t1, t2)) {
            return true;
        } else {
            return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
        }
    }

    //TODO:总感觉性能还能再优化，因为每次都要重新判断两颗子树是否相同，没法利用上一次的搜索结果。
    //TODO:前缀树？
}
