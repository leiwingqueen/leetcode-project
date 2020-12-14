package com.liyongquan.sort;

public class QuickSort {
    public int[] sortArray(int[] nums) {
        int[] out = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            out[i] = nums[i];
        }
        quickSort(out, 0, nums.length - 1);
        return out;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right || left < 0 || right >= nums.length) {
            return;
        }
        //取第一位作为pivot
        int pivot = nums[left];
        int start = left;
        int end = right;
        while (left < right) {
            //右指针左移
            while (nums[right] > pivot && right > left) {
                right--;
            }
            if (left == right) {
                break;
            }
            nums[left] = nums[right];
            left++;
            //左指针右移
            while (nums[left] < pivot && right > left) {
                left++;
            }
            if (left == right) {
                break;
            }
            nums[right] = nums[left];
            right--;
        }
        nums[left] = pivot;
        /*System.out.println("==========================排序结果 start====================");
        for (int i = start; i <= end; i++) {
            System.out.println(nums[i]);
        }
        System.out.println("==========================排序结果 end====================");*/
        quickSort(nums, start, left - 1);
        quickSort(nums, left + 1, end);
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 2, 6, 1, 10, 12, 7};
        QuickSort sort = new QuickSort();
        int[] ints = sort.sortArray(array);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
