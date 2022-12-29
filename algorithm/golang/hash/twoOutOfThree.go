package hash

// 给你三个整数数组 nums1、nums2 和 nums3 ，请你构造并返回一个 元素各不相同的 数组，且由 至少 在 两个 数组中出现的所有值组成。数组中的元素可以按 任意 顺序排列。
//
//
//示例 1：
//
//输入：nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
//输出：[3,2]
//解释：至少在两个数组中出现的所有值为：
//- 3 ，在全部三个数组中都出现过。
//- 2 ，在数组 nums1 和 nums2 中出现过。
//示例 2：
//
//输入：nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
//输出：[2,3,1]
//解释：至少在两个数组中出现的所有值为：
//- 2 ，在数组 nums2 和 nums3 中出现过。
//- 3 ，在数组 nums1 和 nums2 中出现过。
//- 1 ，在数组 nums1 和 nums3 中出现过。
//示例 3：
//
//输入：nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
//输出：[]
//解释：不存在至少在两个数组中出现的值。
//
//
//提示：
//
//1 <= nums1.length, nums2.length, nums3.length <= 100
//1 <= nums1[i], nums2[j], nums3[k] <= 100
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/two-out-of-three
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func twoOutOfThree(nums1 []int, nums2 []int, nums3 []int) []int {
	mp := make(map[int]int)
	for _, num := range nums1 {
		mp[num] |= 1 << 0
	}
	for _, num := range nums2 {
		mp[num] |= 1 << 1
	}
	for _, num := range nums3 {
		mp[num] |= 1 << 2
	}
	res := make([]int, 0)
	for num, cnt := range mp {
		// 这里判断bitCount>1就很巧妙
		if cnt&(cnt-1) > 0 {
			res = append(res, num)
		}
	}
	return res
}
