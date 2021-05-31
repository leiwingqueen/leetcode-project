package com.liyongquan.tree;

import java.util.*;

/**
 * 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。给定一个由不同节点组成的二叉搜索树，输出所有可能生成此树的数组。
 * <p>
 *  
 * <p>
 * 示例：
 * 给定如下二叉树
 * <p>
 * 2
 * / \
 * 1   3
 * 返回：
 * <p>
 * [
 * [2,1,3],
 * [2,3,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bst-sequences-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BSTSequences {
    /**
     * 居然通过了，不容易>_<
     *
     * 性能击败5%
     *
     * @param root
     * @return
     */
    public List<List<Integer>> BSTSequences(TreeNode root) {
        if (root == null) {
            List<List<Integer>> res = new LinkedList<>();
            res.add(Collections.emptyList());
            return res;
        }
        List<List<Integer>> left = BSTSequences(root.left);
        List<List<Integer>> right = BSTSequences(root.right);
        List<List<Integer>> res = new LinkedList<>();
        for (List<Integer> l1 : left) {
            for (List<Integer> l2 : right) {
                int[] path = new int[l1.size() + l2.size() + 1];
                path[0] = root.val;
                List<List<Integer>> sub = new LinkedList<>();
                combine(l1, l2, 0, 0, path, 1, sub);
                res.addAll(sub);
            }
        }
        return res;
    }

    private void combine(List<Integer> arr1, List<Integer> arr2, int idx1, int idx2, int[] path, int idx, List<List<Integer>> res) {
        if (idx1 == arr1.size() && idx2 == arr2.size()) {
            List<Integer> r = new ArrayList<>(path.length);
            for (int i = 0; i < path.length; i++) {
                r.add(path[i]);
            }
            res.add(r);
            return;
        }
        if (idx1 < arr1.size()) {
            path[idx] = arr1.get(idx1);
            combine(arr1, arr2, idx1 + 1, idx2, path, idx + 1, res);
        }
        if (idx2 < arr2.size()) {
            path[idx] = arr2.get(idx2);
            combine(arr1, arr2, idx1, idx2 + 1, path, idx + 1, res);
        }
    }
}
