package dp

import "leetcode-go/util"

//926. 将字符串翻转到单调递增
//如果一个二进制字符串，是以一些 0（可能没有 0）后面跟着一些 1（也可能没有 1）的形式组成的，那么该字符串是 单调递增 的。
//
//给你一个二进制字符串 s，你可以将任何 0 翻转为 1 或者将 1 翻转为 0 。
//
//返回使 s 单调递增的最小翻转次数。
//
//
//
//示例 1：
//
//输入：s = "00110"
//输出：1
//解释：翻转最后一位得到 00111.
//示例 2：
//
//输入：s = "010110"
//输出：2
//解释：翻转得到 011111，或者是 000111。
//示例 3：
//
//输入：s = "00011000"
//输出：2
//解释：翻转得到 00000000。
//
//
//提示：
//
//1 <= s.length <= 105
//s[i] 为 '0' 或 '1'
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/flip-string-to-monotone-increasing
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func minFlipsMonoIncr(s string) int {
	var f0, f1 int
	if s[0] == '0' {
		f0 = 0
		f1 = 1
	} else {
		f0 = 1
		f1 = 0
	}
	for i := 1; i < len(s); i++ {
		fn0 := f0
		if s[i] == '1' {
			fn0++
		}
		fn1 := util.Min(f0, f1)
		if s[i] == '0' {
			fn1++
		}
		f0 = fn0
		f1 = fn1
	}
	return util.Min(f0, f1)
}
