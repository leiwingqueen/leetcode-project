package com.liyongquan.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * 输出: ["1->2->5", "1->3"]
 * <p>
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreeAllPath {
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        return dfs(root, String.valueOf(root.val));
    }

    private List<String> dfs(TreeNode node, String path) {
        List<String> result = new ArrayList<>();
        //看左子树的路径
        if (node.left != null) {
            List<String> left = dfs(node.left, path + "->" + node.left.val);
            result.addAll(left);
        }
        //扫描右子树的路径
        if (node.right != null) {
            List<String> right = dfs(node.right, path + "->" + node.right.val);
            result.addAll(right);
        }
        //当前结点就是叶子结点
        if (result.size() <= 0) {
            result.add(path);
        }
        return result;
    }
}
