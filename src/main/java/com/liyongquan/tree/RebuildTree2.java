package com.liyongquan.tree;

import javax.lang.model.element.VariableElement;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RebuildTree2 {
    /**
     * 典型的递归解法。
     * 后序遍历的最后一个节点为根节点。通过这个根节点我们可以找到中序遍历分别左子树和右子树，从而可以定位得到左子树和右子树分别的中序遍历和后续遍历。
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return dfs(inorder, postorder, new int[]{0, inorder.length - 1}, new int[]{0, postorder.length - 1});
    }

    private TreeNode dfs(int[] inorder, int[] postorder, int[] p1, int[] p2) {
        int length = p1[1] - p1[0] + 1;
        if (length <= 0) {
            return null;
        }
        if (length == 1) {
            return new TreeNode(inorder[p1[0]]);
        }
        TreeNode root = new TreeNode(postorder[p2[1]]);
        //找到中序遍历的根节点的位置
        int i = p1[0];
        while (i <= p1[1] && inorder[i] != postorder[p2[1]]) {
            i++;
        }
        //中序遍历的左子树的位置 p1[0]~i-1,右子树的位置 i+1,p1[1]
        //后续遍历的左子树的位置 p2[0],p2[0]+i-p1[0]-1,右子树的位置 p2[0]+i-p1[0],p2[1]
        TreeNode left = dfs(inorder, postorder, new int[]{p1[0], i - 1}, new int[]{p2[0], p2[0] + i - p1[0] - 1});
        TreeNode right = dfs(inorder, postorder, new int[]{i + 1, p1[1]}, new int[]{p2[0] + i - p1[0], p2[1] - 1});
        root.left = left;
        root.right = right;
        return root;
    }

    public static void main(String[] args) {
        RebuildTree2 rt = new RebuildTree2();
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode treeNode = rt.buildTree(inorder, postorder);
    }
}
