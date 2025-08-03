package wc461

// 给你一个长度为 n 的整数数组 nums。
//
//如果存在索引 0 < p < q < n − 1，使得数组满足以下条件，则称其为 三段式数组（trionic）：
//
//nums[0...p] 严格 递增，
//nums[p...q] 严格 递减，
//nums[q...n − 1] 严格 递增。
//如果 nums 是三段式数组，返回 true；否则，返回 false。
//
//
//
//示例 1:
//
//输入: nums = [1,3,5,4,2,6]
//
//输出: true
//
//解释:
//
//选择 p = 2, q = 4：
//
//nums[0...2] = [1, 3, 5] 严格递增 (1 < 3 < 5)。
//nums[2...4] = [5, 4, 2] 严格递减 (5 > 4 > 2)。
//nums[4...5] = [2, 6] 严格递增 (2 < 6)。
//示例 2:
//
//输入: nums = [2,1,3]
//
//输出: false
//
//解释:
//
//无法选出能使数组满足三段式要求的 p 和 q 。
//
//
//
//提示:
//
//3 <= n <= 100
//-1000 <= nums[i] <= 1000

func isTrionic(nums []int) bool {
	n := len(nums)
	p := 1
	for p < n && nums[p] > nums[p-1] {
		p++
	}
	if p == 1 {
		return false
	}
	p--
	q := p + 1
	for q < n && nums[q] < nums[q-1] {
		q++
	}
	if q > n-1 || q == p+1 {
		return false
	}
	q--
	// 检查最后一段
	for i := q; i < n; i++ {
		if nums[i] <= nums[i-1] {
			return false
		}
	}
	return true
}
