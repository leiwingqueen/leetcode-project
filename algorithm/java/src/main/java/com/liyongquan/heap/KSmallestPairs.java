package com.liyongquan.heap;

import java.util.*;

/**
 * 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
 * <p>
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
 * <p>
 * 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 示例 2:
 * <p>
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 示例 3:
 * <p>
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KSmallestPairs {
    /**
     * 先尝试使用贪心算法
     * <p>
     * 最小的数字必然是[0,0]
     * 次小的数字会从[0,1],[1,0]里面选择，假如次小的数字是[0,1]，那么下一个数字将从[0,2],[1,0]中选择。直到填满k个数字
     * <p>
     * 不通过，比较的过程还是不严谨
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1.length == 0 || nums2.length == 0) {
            return Collections.emptyList();
        }
        int i = 0, j = 0, m = 1, n = 0;
        List<List<Integer>> res = new ArrayList<>(k);
        for (int l = 0; l < k; l++) {
            if (i >= nums1.length) {
                break;
            }
            if (i == nums1.length - 1 || j == 0 || n >= j || nums1[i] + nums2[j] <= nums1[m] + nums2[n]) {
                res.add(Arrays.asList(nums1[i], nums2[j]));
                System.out.println(String.format("选择[%s,%s]", i, j));
                j++;
                if (j == nums2.length) {
                    j = 0;
                    i++;
                    m++;
                }
            } else {
                res.add(Arrays.asList(nums1[m], nums2[n]));
                System.out.println(String.format("选择[%s,%s]", m, n));
                n++;
            }
        }
        return res;
    }

    /**
     * 我们可以考虑。假设[i,j]为一个配对，则[i,j+1]>=[i,j]。
     * <p>
     * 我们假设把配对按第一个数组的序号分组，我们每次从每个分组中取最小的元素。
     * eg.
     * [1,1,2]
     * [1,2,3]
     * <p>
     * 分组为
     * 首元素index=0：[1,1],[1,2],[1,3]
     * 首元素index=1：[1,1],[1,2],[1,3]
     * 首元素index=2：[2,1],[2,2],[2,3]
     * <p>
     * 注意每个分组都是升序。我们只需要每次取分组头的最小值
     * <p>
     * 事实上我们不需要为每个分组单独维护一个数组，因为分组的index是升序的，只要pop了一个元素，我们可以马上push对应的下一个元素进行排序。
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        if (nums1.length == 0 || nums2.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>(k);
        //push首元素
        PriorityQueue<int[]> pq = new PriorityQueue<>(nums1.length,((o1, o2) -> nums1[o1[0]]+nums2[o1[1]]-nums1[o2[0]]-nums2[o2[1]]));
        for (int i = 0; i < nums1.length; i++) {
            pq.add(new int[]{i, 0});
        }
        int idx = 0;
        while (idx < k && !pq.isEmpty()) {
            int[] poll = pq.poll();
            res.add(Arrays.asList(nums1[poll[0]], nums2[poll[1]]));
            //push分组的下一个元素
            if (poll[1] < nums2.length - 1) {
                pq.add(new int[]{poll[0], poll[1] + 1});
            }
            idx++;
        }
        return res;
    }
}
