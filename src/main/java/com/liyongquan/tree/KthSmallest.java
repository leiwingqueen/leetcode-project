package com.liyongquan.tree;

//给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
//
//
//
// 示例 1：
//
//
//输入：root = [3,1,4,null,2], k = 1
//输出：1
//
//
// 示例 2：
//
//
//输入：root = [5,3,6,2,4,null,null,1], k = 3
//输出：3
//
//
//
//
//
//
// 提示：
//
//
// 树中的节点数为 n 。
// 1 <= k <= n <= 104
// 0 <= Node.val <= 104
//
//
//
//
// 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
// Related Topics 树 二分查找
// 👍 366 👎 0


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class KthSmallest {
    /**
     * 最粗暴的解法：
     * bst的中序遍历就是升序
     *
     * 性能击败5%
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new LinkedList<>();
        inorder(root, list);
        int idx = 0;
        Iterator<Integer> it = list.iterator();
        while (it.hasNext() && idx < k) {
            Integer num = it.next();
            if (idx == k - 1) {
                return num;
            }
            idx++;
        }
        return -1;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}
