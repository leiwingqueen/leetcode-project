package com.liyongquan.array;


/**
 * 832. 翻转图像
 * <p>
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 * <p>
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 * <p>
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,1,0],[1,0,1],[0,0,0]]
 * 输出: [[1,0,0],[0,1,0],[1,1,1]]
 * 解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 * 然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 * 示例 2:
 * <p>
 * 输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 * 然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 说明:
 * <p>
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 * 通过次数43,678提交次数57,527
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flipping-an-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FlipAndInvertImage {
    /**
     * 按题意先翻转再反转
     *
     * @param A
     * @return
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int row = A.length, col = A[0].length;
        //翻转
        for (int i = 0; i < row; i++) {
            int l = 0, r = col - 1;
            while (l < r) {
                int tmp = A[i][l];
                A[i][l] = A[i][r];
                A[i][r] = tmp;
                l++;
                r--;
            }
        }
        //反转
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                A[i][j] = A[i][j] ^ 1;
            }
        }
        return A;
    }

    /**
     * 少一次遍历
     *
     * @param A
     * @return
     */
    public int[][] flipAndInvertImage2(int[][] A) {
        int row = A.length, col = A[0].length;
        //翻转
        for (int i = 0; i < row; i++) {
            int l = 0, r = col - 1;
            while (l < r) {
                int tmp = A[i][l] ^ 1;
                A[i][l] = A[i][r] ^ 1;
                A[i][r] = tmp;
                l++;
                r--;
            }
            //中间的数字也需要反转
            if (l == r) {
                A[i][l] ^= 1;
            }
        }
        return A;
    }
}
