package com.liyongquan.tree;

//给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
//
//假设二叉树中至少有一个节点。
//
// 
//
//示例 1:
//
//
//
//输入: root = [2,1,3]
//输出: 1
//示例 2:
//
//
//
//输入: [1,2,3,4,null,5,6,null,null,7]
//输出: 7
// 
//
//提示:
//
//二叉树的节点个数的范围是 [1,104]
//-231 <= Node.val <= 231 - 1 
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-bottom-left-tree-value
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.LinkedList;
import java.util.Queue;

public class FindBottomLeftValue {
    //从右往左扫描，最后一个就是我们要求的节点
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int last = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                last = node.val;
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
        }
        return last;
    }
}
