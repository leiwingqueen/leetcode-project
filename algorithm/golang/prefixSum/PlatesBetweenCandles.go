package prefixSum

//给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。
//
//同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti] （包含左右端点的字符）。对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。
//
//比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
//请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
//
//
//
//示例 1:
//
//
//
//输入：s = "**|**|***|", queries = [[2,5],[5,9]]
//输出：[2,3]
//解释：
//- queries[0] 有两个盘子在蜡烛之间。
//- queries[1] 有三个盘子在蜡烛之间。
//示例 2:
//
//
//
//输入：s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
//输出：[9,0,0,0,0]
//解释：
//- queries[0] 有 9 个盘子在蜡烛之间。
//- 另一个查询没有盘子在蜡烛之间。
//
//
//提示：
//
//3 <= s.length <= 105
//s 只包含字符 '*' 和 '|' 。
//1 <= queries.length <= 105
//queries[i].length == 2
//0 <= lefti <= righti < s.length
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/plates-between-candles
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//前缀和，加速计算过程
func platesBetweenCandles(s string, queries [][]int) []int {
	//计算左右两边最近一根蜡烛
	n := len(s)
	left := make([]int, n)
	right := make([]int, n)
	pre := -1
	for i := 0; i < n; i++ {
		if s[i] == '|' {
			pre = i
		}
		left[i] = pre
	}
	pre = -1
	for i := n - 1; i >= 0; i-- {
		if s[i] == '|' {
			pre = i
		}
		right[i] = pre
	}
	//前缀和，计算前n个数组的碟子的数量
	sum := make([]int, n+1)
	for i := 1; i <= n; i++ {
		if s[i-1] == '*' {
			sum[i] = sum[i-1] + 1
		} else {
			sum[i] = sum[i-1]
		}
	}
	res := make([]int, len(queries))
	for i, query := range queries {
		l := query[0]
		r := query[1]
		if left[r] < 0 || right[l] < 0 || right[l] >= left[r] {
			res[i] = 0
		} else {
			res[i] = sum[left[r]] - sum[right[l]]
		}
	}
	return res
}
