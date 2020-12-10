package com.liyongquan.sort;

/**
 * 自己实现的
 */
public class HeapSort2 {
    public void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        //先构建一个大顶堆,最后的非叶子节点len/2-1
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjust(arr, i, arr.length);
        }
        //依次pop堆顶元素出来，然后再做调整
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            adjust(arr, 0, i);
        }
    }

    /**
     * 调整堆,调整idx的所有子树
     *
     * @param arr
     * @param idx
     * @param len
     */
    private void adjust(int[] arr, int idx, int len) {
        //非叶子节点
        while (2 * idx + 1 < len) {
            int k = 2 * idx + 1;
            //右子树>左子树的场景，使用右子树做比较
            if (k + 1 < len && arr[k + 1] > arr[k]) {
                k++;
            }
            //判断是否要做交换(不需要交换，则证明子树已经满足最大堆的要求)
            if (arr[idx] >= arr[k]) {
                return;
            }
            swap(arr, idx, k);
            //交换后继续调整子树
            idx = k;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
