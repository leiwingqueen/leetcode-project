package greedy

// 你会得到一个字符串 text 。你应该把它分成 k 个子字符串 (subtext1, subtext2，…， subtextk) ，要求满足:
//
//subtexti 是 非空 字符串
//所有子字符串的连接等于 text ( 即subtext1 + subtext2 + ... + subtextk == text )
//对于所有 i 的有效值( 即 1 <= i <= k ) ，subtexti == subtextk - i + 1 均成立
//返回k可能最大值。
//
//示例 1：
//
//输入：text = "ghiabcdefhelloadamhelloabcdefghi"
//输出：7
//解释：我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。
//示例 2：
//
//输入：text = "merchant"
//输出：1
//解释：我们可以把字符串拆分成 "(merchant)"。
//示例 3：
//
//输入：text = "antaprezatepzapreanta"
//输出：11
//解释：我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。
//提示：
//
//1 <= text.length <= 1000
//text 仅由小写英文字符组成
//Related Topics
//贪心
//双指针
//字符串
//动态规划
//哈希函数
//滚动哈希
//
//👍 115
//👎 0

func longestDecomposition(text string) int {
	var dfs func(l, r int) int
	dfs = func(l, r int) int {
		if l > r {
			return 0
		}
		size := r - l + 1
		for i := 1; i <= size/2; i++ {
			p1, p2 := l, r-i+1
			flag := true
			for j := 0; j < i; j++ {
				if text[p1] != text[p2] {
					flag = false
					break
				}
				p1++
				p2++
			}
			if flag {
				return dfs(l+i, r-i) + 2
			}
		}
		return 1
	}
	return dfs(0, len(text)-1)
}
