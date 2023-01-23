package bwc96

// 给你两个整数数组 nums1 和 nums2 ，它们已经按非降序排序，请你返回两个数组的 最小公共整数 。如果两个数组 nums1 和 nums2 没有公共整数，请你返回 -1 。
//
//如果一个整数在两个数组中都 至少出现一次 ，那么这个整数是数组 nums1 和 nums2 公共 的。
//
//
//
//示例 1：
//
//输入：nums1 = [1,2,3], nums2 = [2,4]
//输出：2
//解释：两个数组的最小公共元素是 2 ，所以我们返回 2 。
//示例 2：
//
//输入：nums1 = [1,2,3,6], nums2 = [2,3,4,5]
//输出：2
//解释：两个数组中的公共元素是 2 和 3 ，2 是较小值，所以返回 2 。
//
//
//提示：
//
//1 <= nums1.length, nums2.length <= 105
//1 <= nums1[i], nums2[j] <= 109
//nums1 和 nums2 都是 非降序 的。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-common-value
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func getCommon(nums1 []int, nums2 []int) int {
	search := func(num int) bool {
		l, r := 0, len(nums2)-1
		for l <= r {
			mid := l + (r-l)/2
			if nums2[mid] == num {
				return true
			} else if nums2[mid] < num {
				l = mid + 1
			} else {
				r = mid - 1
			}
		}
		return false
	}
	for _, num := range nums1 {
		if search(num) {
			return num
		}
	}
	return -1
}
