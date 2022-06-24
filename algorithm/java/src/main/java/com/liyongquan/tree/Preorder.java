package com.liyongquan.tree;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 *  
 * <p>
 * <p>
 * <p>
 *  
 * <p>
 * 返回其前序遍历: [1,3,5,6,2,4]。
 * <p>
 *  
 * <p>
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Preorder {
    public List<Integer> preorder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new LinkedList<>();
        list.add(root.val);
        if (root.children != null) {
            for (Node child : root.children) {
                list.addAll(preorder(child));
            }
        }
        return list;
    }

    /**
     * 迭代解法
     *
     * @param root
     * @return
     */
    public List<Integer> preorder2(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Deque<Node> stack = new LinkedList<>();
        stack.add(root);
        List<Integer> list = new LinkedList<>();
        while (!stack.isEmpty()) {
            Node node = stack.poll();
            list.add(node.val);
            if (node.children != null) {
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    stack.push(node.children.get(i));
                }
            }
        }
        return list;
    }

}
