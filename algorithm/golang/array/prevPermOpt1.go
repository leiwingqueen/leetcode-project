package array

// 给你一个正整数数组 arr（可能存在重复的元素），请你返回可在 一次交换（交换两数字 arr[i] 和 arr[j] 的位置）后得到的、按字典序排列小于 arr 的最大排列。
//
//如果无法这么操作，就请返回原数组。
//
//
//
//示例 1：
//
//输入：arr = [3,2,1]
//输出：[3,1,2]
//解释：交换 2 和 1
//示例 2：
//
//输入：arr = [1,1,5]
//输出：[1,1,5]
//解释：已经是最小排列
//示例 3：
//
//输入：arr = [1,9,4,6,7]
//输出：[1,7,4,6,9]
//解释：交换 9 和 7
//
//
//提示：
//
//1 <= arr.length <= 104
//1 <= arr[i] <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/previous-permutation-with-one-swap
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 必然超时
func prevPermOpt1(arr []int) []int {
	n := len(arr)
	p1, p2 := -1, -1
	for i := 0; i < n-1; i++ {
		mx := 0
		for j := i; j < n; j++ {
			if arr[i] <= arr[j] {
				continue
			}
			if arr[j] > mx {
				mx = arr[j]
				p1 = i
				p2 = j
			}
		}
	}
	if p1 >= 0 {
		arr[p1], arr[p2] = arr[p2], arr[p1]
	}
	return arr
}

// 单调栈优化。求某个下标的右数组的最大值
// 还是错
func prevPermOpt2(arr []int) []int {
	n := len(arr)
	stack := make([]int, 0)
	p1 := n - 1
	for p1 >= 0 {
		for len(stack) > 0 && arr[stack[len(stack)-1]] >= arr[p1] {
			stack = stack[:len(stack)-1]
		}
		if len(stack) > 0 {
			p2 := stack[len(stack)-1]
			arr[p1], arr[p2] = arr[p2], arr[p1]
			return arr
		}
		stack = append(stack, p1)
		p1--
	}
	return arr
}
