package com.liyongquan.tree;

import com.liyongquan.array.Rotate;
import javafx.util.Pair;

/**
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
 * <p>
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
 * <p>
 * 如果有多种构造方法，请你返回任意一种。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,2,null,3,null,4,null,null]
 * 输出：[2,1,3,null,null,null,4]
 * 解释：这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
 *  
 * <p>
 * 提示：
 * <p>
 * 树节点的数目在 1 到 10^4 之间。
 * 树节点的值互不相同，且在 1 到 10^5 之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balance-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BalanceBST {
    public TreeNode balanceBST(TreeNode root) {
        return balance(root).getKey();
    }

    private Pair<TreeNode, Integer> balance(TreeNode root) {
        if (root == null) {
            return new Pair<>(null, 0);
        }
        Pair<TreeNode, Integer> l = balance(root.left);
        Pair<TreeNode, Integer> r = balance(root.right);
        //不需要旋转
        if (Math.abs(l.getValue() - r.getValue()) <= 1) {
            return new Pair<>(root, Math.max(l.getValue(), r.getValue()) + 1);
        }
        //左旋
        TreeNode nr;
        if (l.getValue() < r.getValue()) {
            root.right = null;
            root.left = l.getKey();
            nr = r.getKey();
            if (nr.left == null) {
                nr.left = root;
            } else {
                //把根节点放在左下角
                TreeNode tmp = nr;
                while (tmp.left != null) {
                    tmp = tmp.left;
                }
                tmp.left = root;
            }
        } else {
            //右旋
            root.left = null;
            root.right = r.getKey();
            nr = l.getKey();
            if (nr.right == null) {
                nr.right = root;
            } else {
                //把根节点放在右下角
                TreeNode tmp = nr;
                while (tmp.right != null) {
                    tmp = tmp.right;
                }
                tmp.right = root;
            }
        }
        return new Pair<>(nr, Math.max(l.getValue(), r.getValue()));
    }
}
