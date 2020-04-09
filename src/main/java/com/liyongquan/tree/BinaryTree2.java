package com.liyongquan.tree;

import java.util.Stack;

public class BinaryTree2 {
    /**
     * DFS找到每个结点的路径，然后对比每条路径的最长的公共结点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack1=new Stack<>();
        Stack<TreeNode> stack2=new Stack<>();
        find(root,p,stack1);
        find(root,q,stack2);
        //输出路径
        /*stack1.forEach(s-> System.out.print(s.val+","));
        System.out.println();
        stack2.forEach(s-> System.out.print(s.val+","));
        System.out.println();*/
        int i=0;
        TreeNode parent=null;
        while (i<stack1.size()&&i<stack2.size()){
            if (stack1.elementAt(i).val==stack2.elementAt(i).val) {
                parent=stack1.elementAt(i);
            }else{
                break;
            }
            i++;
        }
        return parent;
    }
    private boolean find(TreeNode root, TreeNode p, Stack<TreeNode> path){
        if (root==null) {
            return false;
        }
        path.push(root);
        if (p.val==root.val) {
            return true;
        }
        if (find(root.left,p,path)||find(root.right,p,path)) {
            return true;
        }
        path.pop();
        return false;
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
        //构造完全二叉树
        Integer[] tree=new Integer[]{3,5,1,6,2,0,8,null,null,7,4};
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
        TreeNode root=nodes[0];
        BinaryTree2 binaryTree2=new BinaryTree2();
        TreeNode treeNode = binaryTree2.lowestCommonAncestor(root, new TreeNode(5), new TreeNode(1));
        System.out.println(treeNode.val);
    }
}
