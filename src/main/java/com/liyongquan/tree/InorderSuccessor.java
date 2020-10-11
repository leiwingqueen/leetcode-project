package com.liyongquan.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * <p>
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [2,1,3], p = 1
 * <p>
 * 2
 * / \
 * 1   3
 * <p>
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 * <p>
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * <p>
 * 输出: null
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/successor-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InorderSuccessor {
    /**
     * 傻瓜解法，先不考虑任何优化，先做一次中序遍历，把所有节点按中序遍历打印出来，再找到对应的中序后缀
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        List<TreeNode> list = new LinkedList<>();
        dfs(root, list);
        //linklist使用迭代器的性能会更好，get(i)的性能是O(n)
        Iterator<TreeNode> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == p) {
                return iterator.hasNext() ? iterator.next() : null;
            }
        }
        return null;
    }

    private void dfs(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            dfs(root.left, list);
        }
        list.add(root);
        if (root.right != null) {
            dfs(root.right, list);
        }
    }
}
