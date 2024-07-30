package bwc135

// 给你一个字符串 s 。
//
// 你需要对 s 执行以下操作 任意 次：
//
// 选择一个下标 i ，满足 s[i] 左边和右边都 至少 有一个字符与它相同。
// 删除 s[i] 左边 离它 最近 且相同的字符。
// 删除 s[i] 右边 离它 最近 且相同的字符。
// 请你返回执行完所有操作后， s 的 最短 长度。
//
// 示例 1：
//
// 输入：s = "abaacbcbb"
//
// 输出：5
//
// 解释：
// 我们执行以下操作：
//
// 选择下标 2 ，然后删除下标 0 和 3 处的字符，得到 s = "bacbcbb" 。
// 选择下标 3 ，然后删除下标 0 和 5 处的字符，得到 s = "acbcb" 。
// 示例 2：
//
// 输入：s = "aa"
//
// 输出：2
//
// 解释：
// 无法对字符串进行任何操作，所以返回初始字符串的长度。
//
// 提示：
//
// 1 <= s.length <= 2 * 105
// s 只包含小写英文字母。

// 贪心即可?写得很绕
func minimumLength(s string) int {
	st1, st2 := make([][]int, 26), make([][]int, 26)
	n := len(s)
	for i := n - 1; i > 0; i-- {
		idx := s[i] - 'a'
		st2[idx] = append(st2[idx], i)
	}
	cnt := 0
	deleted := make([]bool, n)
	for i := 0; i < n; i++ {
		idx := s[i] - 'a'
		if deleted[i] {
			continue
		}
		// pop右边的节点
		for len(st2[idx]) > 0 && st2[idx][len(st2[idx])-1] <= i {
			st2[idx] = st2[idx][:len(st2[idx])-1]
		}
		if len(st1[idx]) > 0 && len(st2[idx]) > 0 {
			l := st1[idx][len(st1[idx])-1]
			r := st2[idx][len(st2[idx])-1]
			deleted[l] = true
			deleted[r] = true
			st1[idx] = st1[idx][:len(st1[idx])-1]
			st2[idx] = st2[idx][:len(st2[idx])-1]
			cnt += 2
		}
		st1[idx] = append(st1[idx], i)
	}
	return n - cnt
}
