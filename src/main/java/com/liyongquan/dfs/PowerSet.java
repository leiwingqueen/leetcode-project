package com.liyongquan.dfs;

import javax.xml.transform.Result;
import java.util.*;

/**
 * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入： nums = [1,2,3]
 * 输出：
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * 通过次数9,867提交次数12,122
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-set-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PowerSet {
    /**
     * dfs方法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        return dfs(nums, nums.length);
    }

    private List<List<Integer>> dfs(int[] nums, int size) {
        List<List<Integer>> result = new LinkedList<>();
        if (size <= 0) {
            result.add(Collections.emptyList());
            return result;
        }
        List<List<Integer>> r = dfs(nums, size - 1);
        result.addAll(r);
        for (List<Integer> list : r) {
            List<Integer> tmp = new LinkedList<>();
            for (Integer num : list) {
                tmp.add(num);
            }
            tmp.add(nums[size - 1]);
            result.add(tmp);
        }
        return result;
    }

    /**
     * 时间复杂度
     * @param nums
     * @return
     */
    //https://leetcode-cn.com/problems/power-set-lcci/solution/mian-shi-ti-0804-mi-ji-by-bvsxg7tlzy/
    //非递归解法，本质上跟递归解法是一致的
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(1 << nums.length);
        result.add(Collections.emptyList());
        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> list = new LinkedList<>();
                list.addAll(result.get(i));
                list.add(num);
                result.add(list);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PowerSet ps = new PowerSet();
        List<List<Integer>> result = ps.subsets(new int[]{1, 2, 3});
    }
}
