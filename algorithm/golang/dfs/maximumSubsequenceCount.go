package dfs

// 给你一个下标从 0 开始的字符串 text 和另一个下标从 0 开始且长度为 2 的字符串 pattern ，两者都只包含小写英文字母。
//
//你可以在 text 中任意位置插入 一个 字符，这个插入的字符必须是 pattern[0] 或者 pattern[1] 。注意，这个字符可以插入在 text 开头或者结尾的位置。
//
//请你返回插入一个字符后，text 中最多包含多少个等于 pattern 的 子序列 。
//
//子序列 指的是将一个字符串删除若干个字符后（也可以不删除），剩余字符保持原本顺序得到的字符串。
//
//
//
//示例 1：
//
//输入：text = "abdcdbc", pattern = "ac"
//输出：4
//解释：
//如果我们在 text[1] 和 text[2] 之间添加 pattern[0] = 'a' ，那么我们得到 "abadcdbc" 。那么 "ac" 作为子序列出现 4 次。
//其他得到 4 个 "ac" 子序列的方案还有 "aabdcdbc" 和 "abdacdbc" 。
//但是，"abdcadbc" ，"abdccdbc" 和 "abdcdbcc" 这些字符串虽然是可行的插入方案，但是只出现了 3 次 "ac" 子序列，所以不是最优解。
//可以证明插入一个字符后，无法得到超过 4 个 "ac" 子序列。
//示例 2：
//
//输入：text = "aabb", pattern = "ab"
//输出：6
//解释：
//可以得到 6 个 "ab" 子序列的部分方案为 "aaabb" ，"aaabb" 和 "aabbb" 。
//
//
//提示：
//
//1 <= text.length <= 105
//pattern.length == 2
//text 和 pattern 都只包含小写英文字母。

func maximumSubsequenceCount(text string, pattern string) int64 {
	if pattern[0] == pattern[1] {
		cnt := 0
		for i := 0; i < len(text); i++ {
			if text[i] == pattern[0] {
				cnt++
			}
		}
		return int64(cnt) * int64(cnt-1) / 2
	} else {
		cal := func(arr []int) int64 {
			n := len(arr)
			l, r := 0, 0
			// 查找连续的patten[0]
			for l < n && arr[l] != 0 {
				l++
			}
			if l == n {
				return 0
			}
			r = l
			var res int64
			for r < n {
				for r < n && arr[r] == 0 {
					r++
				}
				cnt1 := r - l
				if r == n {
					return res
				}
				l = r
				for r < n && arr[r] == 1 {
					r++
				}
				cnt2 := r - l
				l = r
				res += int64(cnt1) * int64(cnt2)
			}
			return res
		}
		var arr []int
		for i := range text {
			if text[i] == pattern[0] {
				arr = append(arr, 0)
			} else if text[i] == pattern[1] {
				arr = append(arr, 1)
			}
		}
		return max(cal(append(arr, 1)), cal(append([]int{0}, arr...)))
	}
}

func maximumSubsequenceCount2(text string, pattern string) int64 {
	if pattern[0] == pattern[1] {
		cnt := 0
		for i := 0; i < len(text); i++ {
			if text[i] == pattern[0] {
				cnt++
			}
		}
		return int64(cnt+1) * int64(cnt) / 2
	} else {
		cal := func(arr []int) int64 {
			n := len(arr)
			cnt0 := 0
			var res int64
			for i := 0; i < n; i++ {
				if arr[i] == 1 {
					res += int64(cnt0)
				} else {
					cnt0++
				}
			}
			return res
		}
		var arr []int
		for i := range text {
			if text[i] == pattern[0] {
				arr = append(arr, 0)
			} else if text[i] == pattern[1] {
				arr = append(arr, 1)
			}
		}
		return max(cal(append(arr, 1)), cal(append([]int{0}, arr...)))
	}
}
