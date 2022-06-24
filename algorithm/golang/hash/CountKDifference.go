package hash

//给你一个整数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k 。
//
//|x| 的值定义为：
//
//如果 x >= 0 ，那么值为 x 。
//如果 x < 0 ，那么值为 -x 。
// 
//
//示例 1：
//
//输入：nums = [1,2,2,1], k = 1
//输出：4
//解释：差的绝对值为 1 的数对为：
//- [1,2,2,1]
//- [1,2,2,1]
//- [1,2,2,1]
//- [1,2,2,1]
//示例 2：
//
//输入：nums = [1,3], k = 3
//输出：0
//解释：没有任何数对差的绝对值为 3 。
//示例 3：
//
//输入：nums = [3,2,1,5,4], k = 2
//输出：3
//解释：差的绝对值为 2 的数对为：
//- [3,2,1,5,4]
//- [3,2,1,5,4]
//- [3,2,1,5,4]
// 
//
//提示：
//
//1 <= nums.length <= 200
//1 <= nums[i] <= 100
//1 <= k <= 99
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-number-of-pairs-with-absolute-difference-k
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func countKDifference(nums []int, k int) int {
	mp := make(map[int]int)
	cnt := 0
	for _, num := range nums {
		cnt += mp[num+k]
		if num-k > 0 {
			cnt += mp[num-k]
		}
		mp[num]++
	}
	return cnt
}
