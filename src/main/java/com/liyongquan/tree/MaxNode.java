package com.liyongquan.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 * <p>
 * <p>
 * 给定一棵二叉搜索树
 * 示例 1:
 * <p>
 * 输入: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 * 2
 * 输出: 4
 * <p>
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * 输出: 4
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 ≤ k ≤ 二叉搜索树元素个数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxNode {
    /**
     * 二叉搜索树的特点是已经排序好的树。
     * 先访问树的右子树，然后再访问当前结点，最后再访问左子树便能得到树的排序。(类似于中序遍历，只是先访问右子树)
     * <p>
     * DFS
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        List<Integer> result=new LinkedList<>();
        scan(root,0,result,k);
        return result.get(k-1);
    }

    private void scan(TreeNode node, int index, List<Integer> result,int k){
        //只需要找到前K个，提前终结流程
        if (k==index) {
            return;
        }
        if (node==null) {
            return;
        }
        scan(node.right, index, result,k);
        result.add(node.val);
        scan(node.left, index, result,k);
    }

    public static void main(String[] args) {

    }
}
