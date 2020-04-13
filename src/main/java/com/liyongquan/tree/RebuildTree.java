package com.liyongquan.tree;

/**
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 * 根据前序遍历和中序遍历构造二叉树
 */
public class RebuildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length<=0||inorder.length<=0) {
            return null;
        }
        return buildTree(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder,
                              int pi,int pj,int ii,int ij) {
        //找到根结点在inorder中的位置,左边即为左子树，右边即为右子数
        int root=preorder[pi];
        int i=ii;
        for (; i <= ij; i++) {
            if (inorder[i]==root) {
                break;
            }
        }
        TreeNode r=new TreeNode(root);
        //中序遍历 左子数为[ii,i-1],右子树为[i+1,ij]
        int length1=i-ii;
        int length2=ij-i;
        TreeNode left=null;
        TreeNode right=null;
        if (length1>0) {
            left = buildTree(preorder, inorder, pi+1, pi+length1, ii, i - 1);
        }
        if (length2>0) {
            right = buildTree(preorder, inorder, pi+length1+1, pi+length1+length2, i+1, ij);
        }
        r.left=left;
        r.right=right;
        return r;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        /**
         * 前序遍历 preorder = [3,9,20,15,7]
         * 中序遍历 inorder = [9,3,15,20,7]
         *
         *     3
         *    / \
         *   9  20
         *     /  \
         *    15   7
         */
        int[] preorder=new int[]{3,9,20,15,7};
        int[] inorder=new int[]{9,3,15,20,7};
        RebuildTree tree=new RebuildTree();
        TreeNode treeNode = tree.buildTree(preorder, inorder);
        System.out.println("end");
    }
}
