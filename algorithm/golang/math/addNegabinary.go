package math

// 给出基数为 -2 的两个数 arr1 和 arr2，返回两数相加的结果。
//
//数字以 数组形式 给出：数组由若干 0 和 1 组成，按最高有效位到最低有效位的顺序排列。例如，arr = [1,1,0,1] 表示数字 (-2)^3 + (-2)^2 + (-2)^0 = -3。数组形式 中的数字 arr 也同样不含前导零：即 arr == [0] 或 arr[0] == 1。
//
//返回相同表示形式的 arr1 和 arr2 相加的结果。两数的表示形式为：不含前导零、由若干 0 和 1 组成的数组。
//
//
//
//示例 1：
//
//输入：arr1 = [1,1,1,1,1], arr2 = [1,0,1]
//输出：[1,0,0,0,0]
//解释：arr1 表示 11，arr2 表示 5，输出表示 16 。
//示例 2：
//
//输入：arr1 = [0], arr2 = [0]
//输出：[0]
//示例 3：
//
//输入：arr1 = [0], arr2 = [1]
//输出：[1]
//
//
//提示：
//
//1 <= arr1.length, arr2.length <= 1000
//arr1[i] 和 arr2[i] 都是 0 或 1
//arr1 和 arr2 都没有前导0
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/adding-two-negabinary-numbers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func addNegabinary(arr1 []int, arr2 []int) []int {
	convert := func(arr []int) int {
		num := 0
		for _, k := range arr1 {
			num = num*-2 + k
		}
		return num
	}
	num1 := convert(arr1)
	num2 := convert(arr2)
	decode := func(num int) []int {
		if num == 0 {
			return []int{0}
		}
		var arr []int
		for num > 0 {
			if num&0b1 == 0 {
				arr = append(arr, 0)
			} else {
				arr = append(arr, 1)
			}
			num >>= 1
		}
		res := make([]int, len(arr))
		for i, k := range arr {
			res[len(arr)-i-1] = k
		}
		return res
	}
	return decode(num1 + num2)
}

// 终于通过了，不过这也太难了吧
func addNegabinary2(arr1 []int, arr2 []int) []int {
	p1, p2 := len(arr1)-1, len(arr2)-1
	var res []int
	idx := 0
	// 进位处理
	handleFull := func(idx int) {
		for res[idx] > 1 {
			res[idx] -= 2
			if idx+1 >= len(res) {
				res = append(res, 0)
			}
			if res[idx+1] > 0 {
				res[idx+1]--
			} else {
				res[idx+1]++
				if idx+2 >= len(res) {
					res = append(res, 0)
				}
				res[idx+2]++
			}
		}
	}
	for p1 >= 0 || p2 >= 0 {
		if idx >= len(res) {
			res = append(res, 0)
		}
		if p1 < 0 {
			res[idx] += arr2[p2]
			handleFull(idx)
			p2--
		} else if p2 < 0 {
			res[idx] += arr1[p1]
			p1--
			handleFull(idx)
		} else {
			res[idx] += arr1[p1] + arr2[p2]
			handleFull(idx)
			p1--
			p2--
		}
		idx++
	}
	// 处理完剩下的数字
	for idx < len(res) {
		handleFull(idx)
		idx++
	}
	// 反转处理
	revert := make([]int, len(res))
	for i, num := range res {
		revert[len(res)-i-1] = num
	}
	// 去掉前导0
	for revert[0] == 0 && len(revert) > 1 {
		revert = revert[1:]
	}
	return revert
}
