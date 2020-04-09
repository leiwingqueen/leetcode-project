package com.liyongquan.util.tree;

public class FullBinaryTree {
    /**
     * 利用完全二叉树的特性构造二叉树
     * null表示空结点
     * @return
     */
    public TreeNode buildTree(Integer[] tree){
        //构造完全二叉树
        //Integer[] tree=new Integer[]{3,5,1,6,2,0,8,null,null,7,4};
        TreeNode[] nodes=new TreeNode[tree.length];
        for (int i = 0; i < tree.length; i++) {
            Integer integer = tree[i];
            if (integer!=null) {
                TreeNode node = new TreeNode(integer);
                nodes[i]=node;
            }
        }
        for (int i = 0; i < tree.length; i++) {
            TreeNode node = nodes[i];
            if (node==null) {
                continue;
            }
            //父结点的位置
            int parent=(i-1)/2;
            //奇数为左子数，偶数为右子数
            boolean left=i%2==1;
            TreeNode parentNode = nodes[parent];
            if (parentNode!=null) {
                if (left) {
                    parentNode.left=node;
                }else {
                    parentNode.right=node;
                }
            }
        }
        return nodes[0];
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Integer[] tree=new Integer[]{3,5,1,6,2,0,8,null,null,7,4};
        FullBinaryTree fullBinaryTree=new FullBinaryTree();
        fullBinaryTree.buildTree(tree);
    }
}
