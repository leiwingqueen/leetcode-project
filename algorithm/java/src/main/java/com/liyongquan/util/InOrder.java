package com.liyongquan.util;

import com.liyongquan.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 树的中序遍历，不适用递归，直接使用自己实现的栈来模拟
 */
public class InOrder {
    /**
     * 常用递归
     *
     * @param root
     * @return
     */
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        recursive(root, res);
        return res;
    }

    private void recursive(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        recursive(root.left, list);
        list.add(root.val);
        recursive(root.right, list);
    }

    /**
     * 非递归方法
     *
     * 一直递归左节点入栈。
     *
     * 一个是栈顶部的节点是一个叶节点。这是最好的情况，因为我们什么都不用做，只需将节点从栈中弹出并返回其值。所以这是个常数时间的操作。
     * 另一个情况是栈顶部的节点拥有右节点。我们不需要检查左节点，因为左节点已经添加到栈中了。栈顶元素要么没有左节点，要么左节点已经被处理了。如果栈顶部拥有右节点，那么我们需要对右节点上调用帮助函数。该时间复杂度取决于树的结构。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/binary-search-tree-iterator/solution/er-cha-sou-suo-shu-die-dai-qi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public List<Integer> inOrder2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        push(root, stack);
        while (stack.size() > 0) {
            TreeNode poll = stack.pollFirst();
            //叶子节点直接出栈
            res.add(poll.val);
            if (poll.right != null) {
                //右子树入栈
                push(root.right, stack);
            }
        }
        return res;
    }

    private void push(TreeNode node, Deque<TreeNode> stack) {
        if (node == null) {
            return;
        }
        while (node != null) {
            stack.offerFirst(node);
            node = node.left;
        }
    }
}
