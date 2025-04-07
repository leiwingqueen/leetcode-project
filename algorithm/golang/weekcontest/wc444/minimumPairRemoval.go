package wc444

// 给你一个数组 nums，你可以执行以下操作任意次数：
//
//选择 相邻 元素对中 和最小 的一对。如果存在多个这样的对，选择最左边的一个。
//用它们的和替换这对元素。
//返回将数组变为 非递减 所需的 最小操作次数 。
//
//如果一个数组中每个元素都大于或等于它前一个元素（如果存在的话），则称该数组为非递减。
//
//
//
//示例 1：
//
//输入： nums = [5,2,3,1]
//
//输出： 2
//
//解释：
//
//元素对 (3,1) 的和最小，为 4。替换后 nums = [5,2,4]。
//元素对 (2,4) 的和为 6。替换后 nums = [5,6]。
//数组 nums 在两次操作后变为非递减。
//
//示例 2：
//
//输入： nums = [1,2,2]
//
//输出： 0
//
//解释：
//
//数组 nums 已经是非递减的。
//
//
//
//提示：
//
//1 <= nums.length <= 50
//-1000 <= nums[i] <= 1000

// 数据量小，先直接模拟
func minimumPairRemoval(nums []int) int {
	n := len(nums)
	check := func(size int) bool {
		for i := 0; i < n-1; i++ {
			if nums[i] > nums[i+1] {
				return false
			}
		}
		return true
	}
	res := 0
	for n > 1 {
		// 先判断是否升序
		if check(n) {
			return res
		}
		// 查找要合并的位置
		minIdx := 0
		for i := 0; i < n-1; i++ {
			t := nums[i] + nums[i+1]
			if t < nums[minIdx]+nums[minIdx+1] {
				minIdx = i
			}
		}
		// 更新数组
		nums[minIdx] += nums[minIdx+1]
		for i := minIdx + 2; i < n; i++ {
			nums[i-1] = nums[i]
		}
		res++
		n--
	}
	return res
}
