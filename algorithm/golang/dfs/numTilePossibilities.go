package dfs

import "strings"

// 你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。返回你可以印出的非空字母序列的数目。
//
//注意：本题中，每个活字字模只能使用一次。
//
//
//
//示例 1：
//
//输入："AAB"
//输出：8
//解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
//示例 2：
//
//输入："AAABBC"
//输出：188
//示例 3：
//
//输入："V"
//输出：1
//
//
//提示：
//
//1 <= tiles.length <= 7
//tiles 由大写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/letter-tile-possibilities
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 回溯+记忆
func numTilePossibilities(tiles string) int {
	n := len(tiles)
	cnt := make([]int, 26)
	for i := 0; i < n; i++ {
		idx := tiles[i] - 'A'
		cnt[idx]++
	}
	convert := func(arr []int) string {
		builder := strings.Builder{}
		for _, c := range arr {
			builder.WriteByte(byte(c + '0'))
		}
		return builder.String()
	}
	var dfs func(arr []int, k int, cache map[string]int) int
	dfs = func(arr []int, k int, cache map[string]int) int {
		if k == 0 {
			return 1
		}
		if d, exist := cache[convert(arr)]; exist {
			return d
		}
		res := 0
		for i, c := range arr {
			if c > 0 {
				arr[i]--
				sub := dfs(arr, k-1, cache)
				res += sub
				arr[i]++
			}
		}
		cache[convert(arr)] = res
		return res
	}
	res := 0
	for i := 1; i <= n; i++ {
		res += dfs(cnt, i, make(map[string]int))
	}
	return res
}
