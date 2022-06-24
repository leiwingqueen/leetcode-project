package com.liyongquan.tree;

import java.util.*;

/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * <p>
 * 假定 BST 有如下定义：
 * <p>
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 * <p>
 * 1
 * \
 * 2
 * /
 * 2
 * 返回[2].
 * <p>
 * 提示：如果众数超过1个，不需考虑输出顺序
 * <p>
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMode {
    /**
     * 当你发现自己的代码写得很恶心的时候，很有可能是你的思路完全错了
     *
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> count = count(root);
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            Integer v = entry.getValue();
            if (v > max) {
                max = v;
            }
        }
        List<Integer> result = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() == max) {
                result.add(entry.getKey());
            }
        }
        int[] r = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            r[i] = result.get(i);
        }
        return r;
    }

    /**
     * 统计所有数字的数量
     *
     * @param node
     * @return
     */
    private Map<Integer, Integer> count(TreeNode node) {
        if (node == null) {
            return new HashMap<>();
        }
        Map<Integer, Integer> left = count(node.left);
        Map<Integer, Integer> right = count(node.right);
        left.put(node.val, left.getOrDefault(node.val, 0) + 1);
        right.forEach((k, v) -> left.put(k, left.getOrDefault(k, 0) + v));
        return left;
    }

    /**
     * bst有个特性，中序遍历的数组必然为非递减序列
     *
     * @param root
     * @return
     */
    public int[] findMode2(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        List<Integer> list = dfs(root);
        int max = 1, count = 1;
        List<Integer> ans = new LinkedList<>();
        ans.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) == list.get(i)) {
                count++;
            } else {
                count = 1;
            }
            if (count == max) {
                ans.add(list.get(i));
            }
            if (count > max) {
                max = count;
                ans.clear();
            }
        }
        int[] r=new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            r[i]=ans.get(i);
        }
        return r;
    }

    private List<Integer> dfs(TreeNode node) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new LinkedList<>();
        if (node.left != null) {
            list.addAll(dfs(node.left));
        }
        list.add(node.val);
        if (node.right != null) {
            list.addAll(dfs(node.right));
        }
        return list;
    }
}
