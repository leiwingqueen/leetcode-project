package com.liyongquan.tree;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BuildTree {
    /**
     * 递归解法
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, 0, 0, preorder.length);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int p1, int p2, int len) {
        if (len == 0 || p1 >= preorder.length || p2 >= inorder.length) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[p1]);
        //根节点对应中序遍历的位置，其实可以用二分查找提高查找效率
        int rootIdx = p2;
        for (int i = p2; i < p2 + len; i++) {
            if (inorder[i] == preorder[p1]) {
                rootIdx = i;
                break;
            }
        }
        //计算左右子树分别的节点数量
        int lLen = rootIdx - p2;
        int rLen = p2 + len - 1 - rootIdx;
        TreeNode left = dfs(preorder, inorder, p1 + 1, p2, lLen);
        TreeNode right = dfs(preorder, inorder, p1 + 1 + lLen, rootIdx + 1, rLen);
        root.left = left;
        root.right = right;
        return root;
    }
}
