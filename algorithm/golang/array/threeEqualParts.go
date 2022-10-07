package array

// 给定一个由 0 和 1 组成的数组 arr ，将数组分成  3 个非空的部分 ，使得所有这些部分表示相同的二进制值。
//
//如果可以做到，请返回任何 [i, j]，其中 i+1 < j，这样一来：
//
//arr[0], arr[1], ..., arr[i] 为第一部分；
//arr[i + 1], arr[i + 2], ..., arr[j - 1] 为第二部分；
//arr[j], arr[j + 1], ..., arr[arr.length - 1] 为第三部分。
//这三个部分所表示的二进制值相等。
//如果无法做到，就返回 [-1, -1]。
//
//注意，在考虑每个部分所表示的二进制时，应当将其看作一个整体。例如，[1,1,0] 表示十进制中的 6，而不会是 3。此外，前导零也是被允许的，所以 [0,1,1] 和 [1,1] 表示相同的值。
//
//
//
//示例 1：
//
//输入：arr = [1,0,1,0,1]
//输出：[0,3]
//示例 2：
//
//输入：arr = [1,1,0,1,1]
//输出：[-1,-1]
//示例 3:
//
//输入：arr = [1,1,0,0,1]
//输出：[0,2]
//
//
//提示：
//
//3 <= arr.length <= 3 * 104
//arr[i] 是 0 或 1
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/three-equal-parts
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 超时
func threeEqualParts(arr []int) []int {
	n := len(arr)
	var check func(i int, j int) bool
	check = func(i int, j int) bool {
		num1 := 0
		for k := 0; k <= i; k++ {
			num1 = (num1 << 1) + arr[k]
		}
		num2 := 0
		for k := i + 1; k < j; k++ {
			num2 = (num2 << 1) + arr[k]
		}
		if num1 != num2 {
			return false
		}
		num3 := 0
		for k := j; k < len(arr); k++ {
			num3 = (num3 << 1) + arr[k]
		}
		return num1 == num3
	}
	// [0,i],[i+1,j-1],[j,n-1]
	for i := 0; i < n-2; i++ {
		for j := i + 2; j < n; j++ {
			if check(i, j) {
				return []int{i, j}
			}
		}
	}
	return []int{-1, -1}
}

func threeEqualParts2(arr []int) []int {
	n := len(arr)
	oneCnt := 0
	for _, num := range arr {
		if num == 1 {
			oneCnt++
		}
	}
	if oneCnt == 0 {
		return []int{0, 2}
	}
	if oneCnt%3 != 0 {
		return []int{-1, -1}
	}
	cnt := oneCnt / 3
	start := n - 1
	c := 0
	for start >= 0 && c < cnt {
		if arr[start] == 1 {
			c++
		}
		start--
	}
	var split func(start int, end int) int
	split = func(start int, end int) int {
		//第三个数字为[idx+1,n-1]
		// 第一个1
		p1 := start - 1
		for arr[p1] == 0 {
			p1--
		}
		p2 := end
		for arr[p2] != 1 {
			p2--
		}
		// 后导0的数量
		l := end - p2
		if start-p1-1 < l {
			return -1
		}
		return p1 + l + 1
	}
	j := split(start+1, n-1)
	if j < 0 {
		return []int{-1, -1}
	}
	end := j - 1
	start = j - 1
	c = 0
	for start >= 0 && c < cnt {
		if arr[start] == 1 {
			c++
		}
		start--
	}
	i := split(start+1, end)
	if i < 0 {
		return []int{-1, -1}
	}
	var check func(i int, j int) bool
	check = func(i int, j int) bool {
		num1 := 0
		for k := 0; k < i; k++ {
			num1 = (num1 << 1) + arr[k]
		}
		num2 := 0
		for k := i; k < j; k++ {
			num2 = (num2 << 1) + arr[k]
		}
		if num1 != num2 {
			return false
		}
		num3 := 0
		for k := j; k < len(arr); k++ {
			num3 = (num3 << 1) + arr[k]
		}
		return num1 == num3
	}
	if check(i, j) {
		return []int{i - 1, j}
	} else {
		return []int{-1, -1}
	}
}
