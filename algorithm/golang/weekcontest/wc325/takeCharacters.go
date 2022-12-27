package wc325

// 给你一个由字符 'a'、'b'、'c' 组成的字符串 s 和一个非负整数 k 。每分钟，你可以选择取走 s 最左侧 还是 最右侧 的那个字符。
//
//你必须取走每种字符 至少 k 个，返回需要的 最少 分钟数；如果无法取到，则返回 -1 。
//
//
//
//示例 1：
//
//输入：s = "aabaaaacaabc", k = 2
//输出：8
//解释：
//从 s 的左侧取三个字符，现在共取到两个字符 'a' 、一个字符 'b' 。
//从 s 的右侧取五个字符，现在共取到四个字符 'a' 、两个字符 'b' 和两个字符 'c' 。
//共需要 3 + 5 = 8 分钟。
//可以证明需要的最少分钟数是 8 。
//示例 2：
//
//输入：s = "a", k = 1
//输出：-1
//解释：无法取到一个字符 'b' 或者 'c'，所以返回 -1 。
//
//
//提示：
//
//1 <= s.length <= 105
//s 仅由字母 'a'、'b'、'c' 组成
//0 <= k <= s.length

func takeCharacters(s string, k int) int {
	n := len(s)
	preSum := make([][]int, n+1)
	preSum[0] = []int{0, 0, 0}
	for i := 0; i < n; i++ {
		preSum[i+1] = make([]int, 3)
		for j := 0; j < 3; j++ {
			preSum[i+1][j] = preSum[i][j]
		}
		preSum[i+1][s[n-i-1]-'a']++
	}
	search := func(arr []int) int {
		for i := 0; i < 3; i++ {
			if preSum[n][i] < arr[i] {
				return -1
			}
		}
		l := 0
		r := n
		for l < r {
			mid := l + (r-l)/2
			flag := true
			for i := 0; i < 3; i++ {
				if preSum[mid][i] < arr[i] {
					flag = false
					break
				}
			}
			if flag {
				r = mid
			} else {
				l = mid + 1
			}
		}
		return l
	}
	left := []int{0, 0, 0}
	right := []int{0, 0, 0}
	res := -1
	for i := 0; i < n; i++ {
		for j := 0; j < 3; j++ {
			right[j] = k - left[j]
			if right[j] < 0 {
				right[j] = 0
			}
		}
		p := search(right)
		if p >= 0 && p+i <= n && (res < 0 || p+i < res) {
			res = p + i
		}
		left[s[i]-'a']++
	}
	return res
}
