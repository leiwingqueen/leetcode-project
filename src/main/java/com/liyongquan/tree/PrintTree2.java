package com.liyongquan.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 *
 *
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 *
 *
 * 提示：
 *
 *     节点总数 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintTree2 {
    /**
     * 跟上一题类似，我们只需要增加一个数组来记录每个结点的深度即可
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root==null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int i=0;
        List<Integer> depth=new ArrayList<>();
        int curDepth=0;
        depth.add(curDepth);
        List<Integer> row=new ArrayList<>();
        List<List<Integer>> result=new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            if (depth.get(i)!=curDepth) {
                result.add(row);
                row=new ArrayList<>();
                curDepth++;
            }
            row.add(poll.val);
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
    }
}
