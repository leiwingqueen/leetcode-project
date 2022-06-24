package com.liyongquan.design;

import com.liyongquan.tree.TreeNode;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * <p>
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 *  
 * <p>
 * 提示：
 * <p>
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-search-tree-iterator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BSTIterator2 {
    private Deque<TreeNode> stack;

    /**
     * 本质上还是中序遍历，但是用的是自己实现的栈来代替递归
     * <p>
     * 空间复杂度O(h)
     * 时间复杂度O(h)
     *
     * @param root
     */
    public BSTIterator2(TreeNode root) {
        this.stack = new LinkedList<>();
        pushLeftMost(root);
    }

    /**
     * 时间复杂度O(h)
     * 空间复杂度O(h)
     *
     * @return
     */
    public int next() {
        if (!hasNext()) {
            throw new RuntimeException("get next error");
        }
        TreeNode poll = stack.pollFirst();
        //非叶子节点
        if (poll.right != null) {
            pushLeftMost(poll.right);
        }
        return poll.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushLeftMost(TreeNode node) {
        while (node != null) {
            stack.offerFirst(node);
            node = node.left;
        }
    }
}
