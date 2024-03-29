package com.liyongquan.backtrack;

//输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
//
// 
//
//示例:
//给定如下二叉树，以及目标和 target = 22，
//
//              5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \    / \
//        7    2  5   1
//返回:
//
//[
//   [5,4,11,2],
//   [5,8,4,5]
//]
// 
//
//提示：
//
//节点总数 <= 10000
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import com.liyongquan.tree.TreeNode;

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/9/2
 */
public class PathSum {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new LinkedList<>();
        if (root.left == null && root.right == null) {
            if (root.val == target) {
                res.add(Arrays.asList(root.val));
            }
            return res;
        }
        if (root.left != null) {
            List<List<Integer>> r1 = pathSum(root.left, target - root.val);
            for (List<Integer> list : r1) {
                List<Integer> r = new LinkedList<>();
                r.add(root.val);
                r.addAll(list);
                res.add(r);
            }
        }
        if (root.right != null) {
            List<List<Integer>> r2 = pathSum(root.right, target - root.val);
            for (List<Integer> list : r2) {
                List<Integer> r = new LinkedList<>();
                r.add(root.val);
                r.addAll(list);
                res.add(r);
            }
        }
        return res;
    }

    /**
     * 简化了每次迭代
     */
    public static final int INIT_SIZE = 10;

    public List<List<Integer>> pathSum2(TreeNode root, int target) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new LinkedList<>();
        backtrace(root, new ArrayList<>(INIT_SIZE), 0, target, res);
        return res;
    }

    private void backtrace(TreeNode root, ArrayList<Integer> path, int idx, int target, List<List<Integer>> res) {
        if (root.left == null && root.right == null) {
            if (root.val == target) {
                updatePath(path, idx, root.val);
                addRes(res, path, idx + 1);
            }
        } else {
            updatePath(path, idx, root.val);
            if (root.left != null) {
                backtrace(root.left, path, idx + 1, target - root.val, res);
            }
            if (root.right != null) {
                backtrace(root.right, path, idx + 1, target - root.val, res);
            }
        }
    }

    private void addRes(List<List<Integer>> res, List<Integer> r, int len) {
        List<Integer> tmp = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            tmp.add(r.get(i));
        }
        res.add(tmp);
    }

    private void updatePath(ArrayList<Integer> path, int idx, int value) {
        if (idx < path.size()) {
            path.set(idx, value);
        } else {
            path.add(value);
        }
    }
}
