package com.liyongquan.sort;

/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * <p>
 * 返回 13。
 *  
 * <p>
 * 提示：
 * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthSmallest {
    public int kthSmallest(int[][] matrix, int k) {
        int[] sort = matrix[0];
        for (int i = 1; i < matrix.length; i++) {
            sort = mergeSort(sort, matrix[i]);
        }
        return sort[k - 1];
    }

    private int[] mergeSort(int[] a, int[] b) {
        int[] r = new int[a.length + b.length];
        int index = 0;
        int p1 = 0, p2 = 0;
        while (index < a.length + b.length) {
            if (p1 >= a.length) {
                r[index++] = b[p2++];
            } else if (p2 >= b.length) {
                r[index++] = a[p1++];
            }else if(a[p1]>=b[p2]){
                r[index++] = b[p2++];
            }else{
                r[index++] = a[p1++];
            }
        }
        return r;
    }

    public static void main(String[] args) {
        KthSmallest smallest = new KthSmallest();
        int[][] matrix = {
                {3, 8, 3},
                {3, 8, 8},
                {3, 9, 13}
        };
        int i = smallest.kthSmallest(matrix, 8);
        System.out.println(i);
    }
}
