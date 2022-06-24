package backtrace

import "sort"

//你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
//
//如果你能使这个正方形，则返回 true ，否则返回 false 。
//
//
//
//示例 1:
//
//
//
//输入: matchsticks = [1,1,2,2,2]
//输出: true
//解释: 能拼成一个边长为2的正方形，每边两根火柴。
//示例 2:
//
//输入: matchsticks = [3,3,3,3,4]
//输出: false
//解释: 不能用所有火柴拼成一个正方形。
//
//
//提示:
//
//1 <= matchsticks.length <= 15
//1 <= matchsticks[i] <= 108
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/matchsticks-to-square
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//简单的回溯
//超时，不通过
func makesquare(matchsticks []int) bool {
	sum := 0
	for _, tick := range matchsticks {
		sum += tick
	}
	if sum%4 != 0 {
		return false
	}
	sort.Slice(matchsticks, func(i, j int) bool {
		return matchsticks[i] > matchsticks[j]
	})
	var dfs func(bucket []int, idx int) bool
	dfs = func(bucket []int, idx int) bool {
		if idx == len(matchsticks) {
			for _, bk := range bucket {
				if bk != 0 {
					return false
				}
			}
			return true
		}
		//每个桶选择一次
		for i, bk := range bucket {
			if bk >= matchsticks[idx] {
				bucket[i] -= matchsticks[idx]
				if dfs(bucket, idx+1) {
					return true
				}
				bucket[i] += matchsticks[idx]
			}
		}
		return false
	}
	bk := sum / 4
	bucket := []int{bk, bk, bk, bk}
	return dfs(bucket, 0)
}

//TODO:状态压缩
