package com.liyongquan.tree;

//给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
//
// 
//
//示例 1：
//
//
//
//输入：root1 = [2,1,4], root2 = [1,0,3]
//输出：[0,1,1,2,3,4]
//示例 2：
//
//
//
//输入：root1 = [1,null,8], root2 = [8,1]
//输出：[1,1,8,8]
// 
//
//提示：
//
//每棵树的节点数在 [0, 5000] 范围内
//-105 <= Node.val <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;

public class GetAllElements {

    /**
     * 先暴力
     *
     * @param root1
     * @param root2
     * @return
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        dfs(root1, l1);
        dfs(root2, l2);
        List<Integer> res = new ArrayList<>();
        int p1 = 0, p2 = 0;
        while (p1 < l1.size() || p2 < l2.size()) {
            if (p1 == l1.size()) {
                res.add(l2.get(p2++));
            } else if (p2 == l2.size()) {
                res.add(l1.get(p1++));
            } else {
                if (l1.get(p1) <= l2.get(p2)) {
                    res.add(l1.get(p1++));
                } else {
                    res.add(l2.get(p2++));
                }
            }
        }
        return res;
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }

    //TODO:非递归写法肯定更优

    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    private List<Integer> midOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || stack.size() > 0) {
            if (cur == null) {
                cur = stack.pop();
                list.add(cur.val);
                //访问右子树
                cur = cur.right;
            } else {
                stack.add(cur);
                cur = cur.left;
            }
        }
        return list;
    }
}
