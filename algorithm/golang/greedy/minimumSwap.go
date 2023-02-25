package greedy

import "math"

// 有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的方式使这两个字符串相同。
//
//每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。
//
//交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i] 和 s1[j]。
//
//最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。
//
//
//
//示例 1：
//
//输入：s1 = "xx", s2 = "yy"
//输出：1
//解释：
//交换 s1[0] 和 s2[1]，得到 s1 = "yx"，s2 = "yx"。
//示例 2：
//
//输入：s1 = "xy", s2 = "yx"
//输出：2
//解释：
//交换 s1[0] 和 s2[0]，得到 s1 = "yy"，s2 = "xx" 。
//交换 s1[0] 和 s2[1]，得到 s1 = "xy"，s2 = "xy" 。
//注意，你不能交换 s1[0] 和 s1[1] 使得 s1 变成 "yx"，因为我们只能交换属于两个不同字符串的字符。
//示例 3：
//
//输入：s1 = "xx", s2 = "xy"
//输出：-1
//示例 4：
//
//输入：s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
//输出：4
//
//
//提示：
//
//1 <= s1.length, s2.length <= 1000
//s1, s2 只包含 'x' 或 'y'。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-swaps-to-make-strings-equal
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 错误
func minimumSwap(s1 string, s2 string) int {
	cnt1 := []int{0, 0}
	cnt2 := []int{0, 0}
	for i := 0; i < len(s1); i++ {
		if s1[i] == 'x' {
			cnt1[0]++
		} else {
			cnt1[1]++
		}
	}
	for i := 0; i < len(s2); i++ {
		if s2[i] == 'x' {
			cnt2[0]++
		} else {
			cnt2[1]++
		}
	}
	if (cnt1[0]+cnt2[0])%2 != 0 || (cnt1[1]+cnt2[1])%2 != 0 {
		return -1
	}
	res := (cnt1[0]+cnt2[0])/2 - cnt1[0]
	if res < 0 {
		res = -res
	}
	return res
}

// 继续贪心，还是错误
func minimumSwap2(s1 string, s2 string) int {
	cnt1 := []int{0, 0}
	cnt2 := []int{0, 0}
	for i := 0; i < len(s1); i++ {
		if s1[i] == 'x' {
			cnt1[0]++
		} else {
			cnt1[1]++
		}
	}
	for i := 0; i < len(s2); i++ {
		if s2[i] == 'x' {
			cnt2[0]++
		} else {
			cnt2[1]++
		}
	}
	if (cnt1[0]+cnt2[0])%2 != 0 || (cnt1[1]+cnt2[1])%2 != 0 {
		return -1
	}
	n := len(s1)
	arr1 := []byte(s1)
	arr2 := []byte(s2)
	var dfs func(idx int) int
	dfs = func(idx int) int {
		if idx == n {
			return 0
		}
		if arr1[idx] == arr2[idx] {
			return dfs(idx + 1)
		}
		i := idx + 1
		for ; i < n; i++ {
			if arr1[i] == arr1[idx] {
				arr1[i] = arr2[idx]
				return dfs(idx+1) + 1
			}
		}
		i = idx + 1
		for ; i < n; i++ {
			if arr2[i] == arr1[idx] {
				arr2[i] = arr2[idx]
				return dfs(idx+1) + 2
			}
		}
		return -1
	}
	return dfs(0)
}

// 解法3？为什么还是错了？
func minimumSwap3(s1 string, s2 string) int {
	cnt1 := []int{0, 0}
	cnt2 := []int{0, 0}
	min := func(a int, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	for i := 0; i < len(s1); i++ {
		if s1[i] == 'x' {
			cnt1[0]++
		} else {
			cnt1[1]++
		}
	}
	for i := 0; i < len(s2); i++ {
		if s2[i] == 'x' {
			cnt2[0]++
		} else {
			cnt2[1]++
		}
	}
	if (cnt1[0]+cnt2[0])%2 != 0 || (cnt1[1]+cnt2[1])%2 != 0 {
		return -1
	}
	n := len(s1)
	arr1 := []byte(s1)
	arr2 := []byte(s2)
	var dfs func(idx int) int
	dfs = func(idx int) int {
		if idx == n {
			return 0
		}
		if arr1[idx] == arr2[idx] {
			return dfs(idx + 1)
		}
		i := idx + 1
		for ; i < n; i++ {
			if arr1[i] == arr1[idx] {
				break
			}
		}
		sub1 := math.MaxInt32
		if i < n {
			arr1[i] = arr2[idx]
			sub1 = dfs(idx+1) + 1
			// rollback
			arr1[i] = arr1[idx]
		}
		i = idx + 1
		for ; i < n; i++ {
			if arr2[i] == arr1[idx] {
				break
			}
		}
		sub2 := math.MaxInt32
		if i < n {
			arr2[i] = arr2[idx]
			sub2 = dfs(idx+1) + 2
			// rollback
			arr2[i] = arr1[idx]
		}
		return min(sub1, sub2)
	}
	return dfs(0)
}

// 归纳总结
func minimumSwap4(s1 string, s2 string) int {
	n := len(s1)
	p1 := 0
	p2 := 0
	for i := 0; i < n; i++ {
		if s1[i] != s2[i] {
			if s1[i] == 'x' {
				p1++
			} else {
				p2++
			}
		}
	}
	cnt := 0
	if p1 > 0 {
		cnt += p1 / 2
		p1 %= 2
	}
	if p2 > 0 {
		cnt += p2 / 2
		p2 %= 2
	}
	if (p1+p2)%2 != 0 {
		return -1
	}
	cnt += p1 + p2
	return cnt
}
