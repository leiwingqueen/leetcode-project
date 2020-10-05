package com.liyongquan.tree;

/**
 * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
 * <p>
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-height-tree-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        int middle = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        TreeNode left = build(nums, start, middle - 1);
        TreeNode right = build(nums, middle + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }

    public static void main(String[] args) {
        SortedArrayToBST bst = new SortedArrayToBST();
        TreeNode node = bst.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println(node.val);
    }
}
