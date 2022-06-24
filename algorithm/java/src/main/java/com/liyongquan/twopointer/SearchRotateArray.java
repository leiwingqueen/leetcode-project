package com.liyongquan.twopointer;

/**
 * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
 * <p>
 * 示例1:
 * <p>
 * 输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 * 输出: 8（元素5在该数组中的索引）
 * 示例2:
 * <p>
 * 输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 * 输出：-1 （没有找到）
 * 提示:
 * <p>
 * arr 长度范围在[1, 1000000]之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-rotate-array-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchRotateArray {
    /**
     * 数据量是百万级别，优先考虑的是logn复杂度的算法。二分查找
     * <p>
     * 我们可以考虑，数据旋转后会分成两部分。两部分分别都是排好序的，在排好序的两部分中查找target不难(二分查找)。
     * <p>
     * 现在的问题是我们如何能找到两部分的分界线。我们可以考虑分界线为i，两部分分别为[0,i],[i+1,n]。
     * 其中[0,i]的任意一个元素必然 > [i+1,n]。则有若位点arr[j] < 右边界，则往左边继续查找，若位点arr[j] > 右边界，则继续向右边界查找
     *
     * @param arr
     * @param target
     * @return
     */
    public int search(int[] arr, int target) {
        if (arr.length == 1) {
            return target == arr[0] ? 0 : -1;
        }
        //查找边界值
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (arr[middle] > arr[left]) {
                //分界点在右半区间
                left = middle;
            } else if (arr[middle] < arr[left]) {
                //区间在左半部
                right = middle - 1;
            } else {
                //相等的场景无法区分。例如[1,2,1,1,1]和[1,1,1,2,1]。无法确定2是在左半部分还是右半部分。
                if (arr[left] <= arr[left + 1]) {
                    left++;
                } else {
                    right = left;
                }
            }
        }
        //本身已经排好序的场景
        if (left == 0 && arr[0] < arr[1]) {
            return binarySearch(arr, target, 0, arr.length - 1);
        }
        //左半部分
        if (target >= arr[0] && target <= arr[left]) {
            return binarySearch(arr, target, 0, left);
        } else {
            return binarySearch(arr, target, left + 1, arr.length - 1);
        }
    }

    private int binarySearch(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        while (left < right) {
            int middle = (left + right) / 2;
            if (arr[middle] == target) {
                if (arr[left] == target) {
                    return left;
                } else {
                    //退化成顺序扫描
                    left++;
                }
            } else if (arr[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return arr[left] == target ? left : -1;
    }

    /**
     * 大佬们的思路
     *                                     nums[left] <= target
     *                               ┌─  && target <= nums[mid]   ──>  right = mid
     *                               │   （目标在左边的升序区间中）         （右边界移动到mid）
     *   ┌─  nums[left] < nums[mid] ─┼
     *   │     （左边区间升序）         │
     *   │                           └─    否则目标在右半边          ──>  left = mid + 1
     *   │                                                             （左边界移动到mid+1）
     *   │
     *   │                                 nums[left] <= target
     *   │                           ┌─  || target <= nums[mid]   ──>  right = mid
     *   │                           │    （目标在左半边）                （右边界移动到mid）
     *  ─┼─  nums[left] > nums[mid] ─┼
     *   │     （左边不是升序）         │
     *   │                           └─    否则目标在右半边          ──>  left = mid + 1
     *   │                                                              （左边界移动到mid+1）
     *   │
     *   │
     *   │                           ┌─   nums[left] != target    ──>  left++
     *   │                           │     （左值不等于目标               （需要逐一清理重复值）
     *   └─ nums[left] == nums[mid] ─┼         说明还没找到）
     *       （可能是已经找到了目标      │
     *         也可能是遇到了重复值）    └─   nums[left] == target    ──>  right = left
     *                                     （左值等于目标                 （将右边界移动到left，循环结束）
     *                                       已经找到最左边的目标值）
     *
     * 作者：armeria-program
     * 链接：https://leetcode-cn.com/problems/search-rotate-array-lcci/solution/er-fen-fa-by-armeria-program/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int search2(int[] arr, int target) {
        if (arr.length == 1) {
            return target == arr[0] ? 0 : -1;
        }
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            //分界点在右半区域，左半区域为自增
            if (arr[middle] > arr[left]) {
                //结果在左半区域
                if (target >= arr[left] && target <= arr[middle]) {
                    right = middle;
                } else {
                    left = middle + 1;
                }
            } else if (arr[middle] < arr[left]) {
                //分界点在左半区域，右半区域为自增
                if (target >= arr[left] || target <= arr[middle]) {
                    right = middle;
                } else {
                    left = middle + 1;
                }
            } else {
                //相等的情况只能退化成线性扫描
                if (target == arr[left]) {
                    return left;
                } else {
                    left++;
                }
            }
        }
        return arr[left] == target ? left : -1;
    }
}
