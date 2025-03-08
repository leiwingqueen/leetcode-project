package wc439

// 给你一个长度为 n 的数组 original 和一个长度为 n x 2 的二维数组 bounds，其中 bounds[i] = [ui, vi]。
//
//你需要找到长度为 n 且满足以下条件的 可能的 数组 copy 的数量：
//
//对于 1 <= i <= n - 1 ，都有 (copy[i] - copy[i - 1]) == (original[i] - original[i - 1]) 。
//对于 0 <= i <= n - 1 ，都有 ui <= copy[i] <= vi 。
//返回满足这些条件的数组数目。
//
//
//
//示例 1
//
//输入：original = [1,2,3,4], bounds = [[1,2],[2,3],[3,4],[4,5]]
//
//输出：2
//
//解释：
//
//可能的数组为：
//
//[1, 2, 3, 4]
//[2, 3, 4, 5]
//示例 2
//
//输入：original = [1,2,3,4], bounds = [[1,10],[2,9],[3,8],[4,7]]
//
//输出：4
//
//解释：
//
//可能的数组为：
//
//[1, 2, 3, 4]
//[2, 3, 4, 5]
//[3, 4, 5, 6]
//[4, 5, 6, 7]
//示例 3
//
//输入：original = [1,2,1,2], bounds = [[1,1],[2,3],[3,3],[2,3]]
//
//输出：0
//
//解释：
//
//没有可行的数组。
//
//
//
//提示：
//
//2 <= n == original.length <= 105
//1 <= original[i] <= 109
//bounds.length == n
//bounds[i].length == 2
//1 <= bounds[i][0] <= bounds[i][1] <= 109

// 思路其实也简单，我们先考虑一个问题，如果第一个数字我们确定了，那么整个数组其实我们也能确定了。
// 那么剩下我们只需要求满足条件的数组的第一个元素的范围
// 先只考虑左边界的约束，我们找到第一个满足ui <= copy[i] 的 copy[0]
// 然后只考虑右边界，找到最后一个满足copy[i]<=vi的copy[0]
// 由于查找这两个具有单调性，我们可以使用二分查找来找
func countArrays(original []int, bounds [][]int) int {
	n := len(original)
	check1 := func(num int) bool {
		for i := 0; i < n; i++ {
			u := bounds[i][0]
			if num < u {
				return false
			}
			if i != n-1 {
				num += original[i+1] - original[i]
			}
		}
		return true
	}
	if !check1(bounds[0][1]) {
		return 0
	}
	l, r := bounds[0][0], bounds[0][1]
	for l < r {
		mid := l + (r-l)/2
		if check1(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	p1 := l
	check2 := func(num int) bool {
		for i := 0; i < n; i++ {
			v := bounds[i][1]
			if num > v {
				return false
			}
			if i != n-1 {
				num += original[i+1] - original[i]
			}
		}
		return true
	}
	if !check2(bounds[0][0]) {
		return 0
	}
	l, r = bounds[0][0], bounds[0][1]
	for l < r {
		mid := l + (r-l+1)/2
		if check2(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	p2 := l
	// 范围为[p1,p2]
	return max(0, p2-p1+1)
}
