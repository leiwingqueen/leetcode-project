package com.liyongquan.tree;

//给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
//
// 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bs
//t/ 相同
//
//
//
//
//
// 示例 1：
//
//
//输入：root = [4,2,6,1,3]
//输出：1
//
//
// 示例 2：
//
//
//输入：root = [1,0,48,null,null,12,49]
//输出：1
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [2, 100] 内
// 0 <= Node.val <= 105
//
//
//
// Related Topics 树 深度优先搜索 递归
// 👍 135 👎 0

import com.liyongquan.hash.FindNumOfValidWords;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MinDiffInBST {
    /**
     * BST的中序遍历就是升序，最小的距离必然是两个相邻结点的最小差值
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        inOrder(root, res);
        int min = Integer.MAX_VALUE;
        Iterator<Integer> it = res.iterator();
        int pre = it.next();
        while (it.hasNext()) {
            Integer next = it.next();
            min = Math.min(Math.abs(next - pre), min);
            pre = next;
        }
        return min;
    }

    private void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }

    /**
     * 中序遍历-非递归写法
     *
     * @param root
     * @return
     */
    public int minDiffInBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        //上一次的值，设置为差比最大值还大
        int pre = 211;
        int min = Integer.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            if (root == null) {
                TreeNode parent = stack.pop();
                //访问父节点
                min = Math.min(Math.abs(parent.val - pre), min);
                pre = parent.val;
                root = parent.right;
            } else {
                if (root.left != null) {
                    stack.add(root);
                    root = root.left;
                } else {
                    //访问node
                    min = Math.min(Math.abs(root.val - pre), min);
                    pre = root.val;
                    //访问右子树
                    if (root.right != null) {
                        root = root.right;
                    } else {
                        //叶子节点，设置结点为null，下一次循环进来再对父节点进行处理
                        root = null;
                    }
                }
            }
        }
        return min;
    }

    //TODO:Morris
}
