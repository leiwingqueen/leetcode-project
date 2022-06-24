package com.liyongquan.tree;

/**
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 *
 * 示例 2：
 *
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SubTree {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B==null) {
            return false;
        }
        if (isSub(A,B)) {
            return true;
        }
        if (A.left!=null&&isSubStructure(A.left,B)) {
            return true;
        }
        if (A.right!=null&&isSubStructure(A.right,B)) {
            return true;
        }
        return false;
    }

    public boolean isSub(TreeNode A,TreeNode B){
        if (A==null) {
            return false;
        }
        if (A.val!=B.val) {
            return false;
        }
        if (B.left!=null) {
            if (!isSub(A.left,B.left)) {
                return false;
            }
        }
        if (B.right!=null) {
            if (!isSub(A.right,B.right)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode node1=new TreeNode(2);
        TreeNode node2=new TreeNode(3);
        TreeNode node3=new TreeNode(2);
        TreeNode node4=new TreeNode(1);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        SubTree subTree=new SubTree();
        boolean subStructure = subTree.isSubStructure(node1, new TreeNode(1));
        System.out.println(subStructure);
    }
}
