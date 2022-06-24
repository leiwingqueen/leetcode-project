package math

//给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
//
//你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
//
//
//
//示例 1：
//
//输入：n = 13
//输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
//示例 2：
//
//输入：n = 2
//输出：[1,2]
//
//
//提示：
//
//1 <= n <= 5 * 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/lexicographical-numbers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func lexicalOrder(n int) []int {
	res := make([]int, 0, n)
	var dfs func(k int)
	dfs = func(k int) {
		if k > n {
			return
		}
		res = append(res, k)
		for i := 0; i <= 9; i++ {
			dfs(k*10 + i)
		}
	}
	for i := 1; i <= 9; i++ {
		dfs(i)
	}
	return res
}
