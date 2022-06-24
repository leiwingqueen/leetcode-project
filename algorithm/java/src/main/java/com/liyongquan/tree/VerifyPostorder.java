package com.liyongquan.tree;

//剑指 Offer 33. 二叉搜索树的后序遍历序列
//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
//
// 
//
//参考以下这颗二叉搜索树：
//
//     5
//    / \
//   2   6
//  / \
// 1   3
//示例 1：
//
//输入: [1,6,3,2,5]
//输出: false
//示例 2：
//
//输入: [1,3,2,6,5]
//输出: true
// 
//
//提示：
//
//数组长度 <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/9/3
 */
public class VerifyPostorder {
    /**
     * 递归解法
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        return verify(postorder, 0, postorder.length - 1);
    }

    private boolean verify(int[] postorder, int start, int end) {
        if (start >= end) {
            return true;
        }
        int root = postorder[end];
        int idx = start;
        while (idx < end && postorder[idx] < root) {
            idx++;
        }
        if (idx == end) {
            return verify(postorder, start, end - 1);
        } else {
            //检测idx~end-1是否都大于end
            int mid = idx;
            while (idx < end) {
                if (postorder[idx] <= root) {
                    return false;
                }
                idx++;
            }
            return verify(postorder, start, mid - 1) && verify(postorder, mid, end - 1);
        }
    }

    //TODO:单调栈
}
