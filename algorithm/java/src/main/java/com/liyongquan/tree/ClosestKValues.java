package com.liyongquan.tree;

import java.util.*;

//给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找到最接近目标值 target 的 k 个值。
//
// 注意：
//
//
// 给定的目标值 target 是一个浮点数
// 你可以默认 k 值永远是有效的，即 k ≤ 总结点数
// 题目保证该二叉搜索树中只会存在一种 k 个值集合最接近目标值
//
//
// 示例：
//
// 输入: root = [4,2,5,1,3]，目标值 = 3.714286，且 k = 2
//
//    4
//   / \
//  2   5
// / \
//1   3
//
//输出: [4,3]
//
// 拓展：
//假设该二叉搜索树是平衡的，请问您是否能在小于 O(n)（n 为总结点数）的时间复杂度内解决该问题呢？
// Related Topics 栈 树
// 👍 76 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

public class ClosestKValues {
    /**
     * 最蠢的方式，先中序遍历，然后二分找到>=target的第一个节点，最后通过左右两个指针找到对应的k个数字
     * <p>
     * 时间复杂度O(n)
     *
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        //二分找到对应的位置
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (list.get(mid) == target) {
                l = mid;
                break;
            } else if (list.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        //左右两个指针查找最小的k个数字,l<target,r>=target
        r = l;
        l--;
        int cnt = 0;
        List<Integer> res = new ArrayList<>(k);
        while (cnt < k) {
            if (l < 0) {
                res.add(list.get(r++));
            } else if (r >= list.size()) {
                res.add(list.get(l--));
            } else {
                if (Math.abs(target - list.get(l)) < Math.abs(list.get(r) - target)) {
                    res.add(list.get(l));
                    l--;
                } else {
                    res.add(list.get(r));
                    r++;
                }
            }
            cnt++;
        }
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }


    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        //找到>=target的第一个节点
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            if (root.val == target) {
                break;
            } else if (root.val < target) {
                stack.add(root);
                root = root.right;
            } else {
                if (root.left == null || root.left.val < target) {
                    break;
                }
                stack.add(root);
                root = root.left;
            }
        }
        TreeNode smaller = findSmaller(root, stack);
        return null;
    }

    /**
     * 有左子树，则节点在左子树
     * 否则查看父节点,看父节点是否<当前值
     *
     * @param node
     * @param stack
     * @return
     */
    private TreeNode findSmaller(TreeNode node, Stack<TreeNode> stack) {
        return null;
    }

    private TreeNode findLarger(TreeNode node, Stack<TreeNode> stack) {
        return null;
    }
}
