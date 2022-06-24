package com.liyongquan.tree;

import java.util.*;

/**
 * 987. 二叉树的垂序遍历
 * <p>
 * 给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
 * <p>
 * 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。
 * <p>
 * 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。
 * <p>
 * 返回二叉树的 垂序遍历 序列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[9],[3,15],[20],[7]]
 * 解释：
 * 列 -1 ：只有结点 9 在此列中。
 * 列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
 * 列  1 ：只有结点 20 在此列中。
 * 列  2 ：只有结点 7 在此列中。
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[[4],[2],[1,5,6],[3],[7]]
 * 解释：
 * 列 -2 ：只有结点 4 在此列中。
 * 列 -1 ：只有结点 2 在此列中。
 * 列  0 ：结点 1 、5 和 6 都在此列中。
 * 1 在上面，所以它出现在前面。
 * 5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
 * 列  1 ：只有结点 3 在此列中。
 * 列  2 ：只有结点 7 在此列中。
 * 示例 3：
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,6,5,7]
 * 输出：[[4],[2],[1,5,6],[3],[7]]
 * 解释：
 * 这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
 * 因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。
 *  
 * <p>
 * 提示：
 * <p>
 * 树中结点数目总数在范围 [1, 1000] 内
 * 0 <= Node.val <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/vertical-order-traversal-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class VerticalTraversal {
    /**
     * dfs
     * <p>
     * 先计算出对应的坐标，然后再进行排序输出
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        //这里用栈还是队列都不是问题
        Queue<NodeExt> queue = new LinkedList<>();
        List<NodeExt> list = new ArrayList<>();
        queue.add(new NodeExt(root, new int[]{0, 0}));
        while (!queue.isEmpty()) {
            NodeExt pop = queue.poll();
            list.add(pop);
            if (pop.node.left != null) {
                queue.offer(new NodeExt(pop.node.left, new int[]{pop.pos[0] + 1, pop.pos[1] - 1}));
            }
            if (pop.node.right != null) {
                queue.offer(new NodeExt(pop.node.right, new int[]{pop.pos[0] + 1, pop.pos[1] + 1}));
            }
        }
        //排序
        list.sort((o1, o2) -> {
            if (o1.pos[1] != o2.pos[1]) {
                return o1.pos[1] - o2.pos[1];
            }
            if (o1.pos[0] != o2.pos[0]) {
                return o1.pos[0] - o2.pos[0];
            }
            return o1.node.val - o2.node.val;
        });
        //输出结果
        List<List<Integer>> res = new LinkedList<>();
        int idx = 0;
        while (idx < list.size()) {
            int start = idx;
            List<Integer> l = new LinkedList<>();
            while (idx < list.size() && list.get(idx).pos[1] == list.get(start).pos[1]) {
                l.add(list.get(idx).node.val);
                idx++;
            }
            res.add(l);
        }
        return res;
    }

    private static class NodeExt {
        TreeNode node;
        int[] pos;

        public NodeExt(TreeNode node, int[] pos) {
            this.node = node;
            this.pos = pos;
        }
    }

}
