package graph

// 给出一个单词数组 words ，其中每个单词都由小写英文字母组成。
//
//如果我们可以 不改变其他字符的顺序 ，在 wordA 的任何地方添加 恰好一个 字母使其变成 wordB ，那么我们认为 wordA 是 wordB 的 前身 。
//
//例如，"abc" 是 "abac" 的 前身 ，而 "cba" 不是 "bcad" 的 前身
//词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word1 是 word2 的前身，word2 是 word3 的前身，依此类推。一个单词通常是 k == 1 的 单词链 。
//
//从给定单词列表 words 中选择单词组成词链，返回 词链的 最长可能长度 。
//
//
//示例 1：
//
//输入：words = ["a","b","ba","bca","bda","bdca"]
//输出：4
//解释：最长单词链之一为 ["a","ba","bda","bdca"]
//示例 2:
//
//输入：words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
//输出：5
//解释：所有的单词都可以放入单词链 ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
//示例 3:
//
//输入：words = ["abcd","dbqca"]
//输出：1
//解释：字链["abcd"]是最长的字链之一。
//["abcd"，"dbqca"]不是一个有效的单词链，因为字母的顺序被改变了。
//
//
//提示：
//
//1 <= words.length <= 1000
//1 <= words[i].length <= 16
//words[i] 仅由小写英文字母组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/longest-string-chain
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 性能击败5%
func longestStrChain(words []string) int {
	// 构造有向图
	check := func(str1, str2 string) bool {
		if len(str1) > len(str2) {
			str1, str2 = str2, str1
		}
		if len(str2)-len(str1) != 1 {
			return false
		}
		p1, p2 := 0, 0
		diff := 0
		for p1 < len(str1) {
			if diff > 1 {
				return false
			}
			if str1[p1] != str2[p2] {
				diff++
				p2++
			} else {
				p1++
				p2++
			}
		}
		return true
	}
	// 构造图并且计算每个点的入度
	n := len(words)
	graph := make([][]int, n)
	degree := make([]int, n)
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			str1, str2 := words[i], words[j]
			if check(str1, str2) {
				if len(str1) < len(str2) {
					graph[i] = append(graph[i], j)
					degree[j]++
				} else {
					graph[j] = append(graph[j], i)
					degree[i]++
				}
			}
		}
	}
	bfs := func(start int) int {
		queue := []int{start}
		depth := 0
		for len(queue) > 0 {
			size := len(queue)
			for i := 0; i < size; i++ {
				node := queue[i]
				for _, next := range graph[node] {
					queue = append(queue, next)
				}
			}
			queue = queue[size:]
			depth++
		}
		return depth
	}
	res := 0
	for i := 0; i < n; i++ {
		// 从入度为0的开始检索
		if degree[i] == 0 {
			depth := bfs(i)
			if depth > res {
				res = depth
			}
		}
	}
	return res
}
