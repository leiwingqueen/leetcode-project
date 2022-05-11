package com.liyongquan.tree;

//449. 序列化和反序列化二叉搜索树
//序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
//
//设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
//
//编码的字符串应尽可能紧凑。
//
// 
//
//示例 1：
//
//输入：root = [2,1,3]
//输出：[2,1,3]
//示例 2：
//
//输入：root = []
//输出：[]
// 
//
//提示：
//
//树中节点数范围是 [0, 104]
//0 <= Node.val <= 104
//题目数据 保证 输入的树是一棵二叉搜索树。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/serialize-and-deserialize-bst
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//中序遍历和前序遍历可以唯一确定一颗树，由于BST的中序遍历就是升序的，所以中序遍历其实我们是没必要存的
public class BSTCodec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        doSerialize(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        String[] split = data.split(",");
        int[] arr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        return doDeserialize(arr, 0, arr.length - 1);
    }

    private void doSerialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val + ",");
        doSerialize(root.left, sb);
        doSerialize(root.right, sb);
    }

    private TreeNode doDeserialize(int[] arr, int l, int r) {
        if (l > r) {
            return null;
        }
        TreeNode root = new TreeNode(arr[l]);
        if (l == r) {
            return root;
        }
        //找到第一个>arr[l]的节点
        if (arr[r] < root.val) {
            TreeNode lNode = doDeserialize(arr, l + 1, r);
            root.left = lNode;
        } else {
            int left = l + 1;
            int right = r;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] > root.val) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            TreeNode lNode = doDeserialize(arr, l + 1, left - 1);
            TreeNode rNode = doDeserialize(arr, left, r);
            root.left = lNode;
            root.right = rNode;
        }
        return root;
    }
}
