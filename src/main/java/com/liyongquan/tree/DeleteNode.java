package com.liyongquan.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 450. 删除二叉搜索树中的节点
 * <p>
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * <p>
 * 示例:
 * <p>
 * root = [5,3,6,2,4,null,7]
 * key = 3
 * <p>
 * 5
 * / \
 * 3   6
 * / \   \
 * 2   4   7
 * <p>
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * <p>
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * <p>
 * 5
 * / \
 * 4   6
 * /     \
 * 2       7
 * <p>
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * <p>
 * 5
 * / \
 * 2   6
 * \   \
 * 4   7
 * 通过次数31,728提交次数69,302
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DeleteNode {
    /**
     * 递归解法
     * <p>
     * 不通过，还是有场景覆盖不了
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.right != null) {
                //把右子树提升
                int val = root.right.val;
                root.val = val;
                TreeNode right = deleteNode(root.right, root.right.val);
                root.right = right;
            } else {
                //把左子树提升
                int val = root.left.val;
                root.val = val;
                TreeNode left = deleteNode(root.left, root.left.val);
                root.left = left;
            }
        } else if (key < root.val) {
            TreeNode left = deleteNode(root.left, key);
            root.left = left;
        } else {
            TreeNode right = deleteNode(root.right, key);
            root.right = right;
        }
        return root;
    }

    /**
     * 最蠢的做法，直接把树转换成排序数组(中序遍历)。
     * <p>
     * 通过二分查找找到删除的点。然后再构建左右子树
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        List<Integer> list = dfs(root);
        //找到删除的节点
        int idx = binarySearch(list, key);
        if (idx == -1) {
            return root;
        }
        //只剩一个节点的场景
        if (list.size() == 1) {
            return null;
        }
        //构建树,找到第一个有效的节点
        if (idx > 0) {
            int rootIdx = idx - 1;
            TreeNode left = buildTree(list, 0, rootIdx - 1);
            TreeNode right = buildTree(list, idx + 1, list.size() - 1);
            TreeNode nr = new TreeNode(list.get(rootIdx));
            nr.left = left;
            nr.right = right;
            return nr;
        } else {
            int rootIdx = idx + 1;
            TreeNode left = buildTree(list, 0, idx - 1);
            TreeNode right = buildTree(list, rootIdx + 1, list.size() - 1);
            TreeNode nr = new TreeNode(list.get(rootIdx));
            nr.left = left;
            nr.right = right;
            return nr;
        }
    }

    private int binarySearch(List<Integer> list, int key) {
        //找到删除的节点
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int middle = (l + r) / 2;
            Integer value = list.get(middle);
            if (value == key) {
                return middle;
            } else if (value < key) {
                l = middle + 1;
            } else {
                r = middle - 1;
            }
        }
        return list.get(l) == key ? l : -1;
    }

    private TreeNode buildTree(List<Integer> list, int start, int end) {
        if (start > end || start < 0 || end >= list.size()) {
            return null;
        }
        if (start == end) {
            return new TreeNode(list.get(start));
        }
        int middle = (start + end) / 2;
        TreeNode root = new TreeNode(list.get(middle));
        TreeNode left = buildTree(list, start, middle - 1);
        TreeNode right = buildTree(list, middle + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }

    private List<Integer> dfs(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> left = dfs(root.left);
        left.add(root.val);
        left.addAll(dfs(root.right));
        return left;
    }
}
