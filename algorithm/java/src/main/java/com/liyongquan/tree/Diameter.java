package com.liyongquan.tree;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * <p>
 *  
 * <p>
 * 示例 :
 * 给定二叉树
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * <p>
 *  
 * <p>
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Diameter {
    public int diameterOfBinaryTree(TreeNode root) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        dfs(root, atomicInteger);
        return atomicInteger.intValue()-1;
    }

    private int dfs(TreeNode node, AtomicInteger max) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left, max);
        int right = dfs(node.right, max);
        if (left + right + 1 > max.intValue()) {
            max.set(left + right + 1);
        }
        return Math.max(left, right) + 1;
    }
}
