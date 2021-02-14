package com.liyongquan.tree;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [0]
 * 输出：[0]
 *  
 * <p>
 * 提示：
 * <p>
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *  
 * <p>
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Flatten {
    /**
     * 非原地算法比较简单，我们考虑到扁平化后的树跟原来的树的前序遍历需要一致，那么我们只需要去对原来的树进行前序遍历来构造新树即可。
     * <p>
     * 1.根节点为空，直接返回
     * 2.根节点继续为新树的父节点
     * 3.递归左子树构造扁平化后的左子树，并且返回新左子树的最后的节点
     * 4.递归右子树，把右子树挂在左子树的右节点上
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode cur = root, last = root;
        TreeNode tmp = root.right;
        if (root.left != null) {
            TreeNode left = dfs(root.left);
            root.right = root.left;
            root.left = null;
            cur = left;
            last = cur;
        }
        if (tmp != null) {
            TreeNode right = dfs(tmp);
            cur.right = tmp;
            last = right;
        }
        return last;
    }
}
