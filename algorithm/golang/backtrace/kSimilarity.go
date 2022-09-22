package backtrace

// 对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次，能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k 。
//
//给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值。
//
//
//
//示例 1：
//
//输入：s1 = "ab", s2 = "ba"
//输出：1
//示例 2：
//
//输入：s1 = "abc", s2 = "bca"
//输出：2
//
//
//提示：
//
//1 <= s1.length <= 20
//s2.length == s1.length
//s1 和 s2  只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母
//s2 是 s1 的一个字母异位词
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/k-similar-strings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 回溯必然是超时的
func kSimilarity(s1 string, s2 string) int {
	var dfs func(arr1 []byte, arr2 []byte, idx int, cnt int) int
	dfs = func(arr1 []byte, arr2 []byte, idx int, cnt int) int {
		n := len(arr1)
		if idx == n-1 {
			return cnt
		}
		if cnt > n*n {
			return -1
		}
		if arr1[idx] == arr2[idx] {
			return dfs(arr1, arr2, idx+1, cnt)
		}
		res := -1
		for i := idx; i < n-1; i++ {
			for j := idx + 1; j < n; j++ {
				arr1[i], arr1[j] = arr1[j], arr1[i]
				sub := dfs(arr1, arr2, idx, cnt+1)
				if sub >= 0 && (res < 0 || sub < res) {
					res = sub
				}
				arr1[i], arr1[j] = arr1[j], arr1[i]
			}
		}
		return res
	}
	return dfs([]byte(s1), []byte(s2), 0, 0)
}

// 把每个状态看成一个节点，bfs求最短路径
func kSimilarity2(s1 string, s2 string) int {
	n := len(s1)
	arr1 := []byte(s1)
	arr2 := []byte(s2)
	hashCode := func(arr []byte) int64 {
		var res int64
		for _, ch := range arr {
			k := int64(ch - 'a')
			res = (res << 3) + k
		}
		return res
	}
	queue := [][]byte{arr1}
	visit := make(map[int64]bool)
	visit[hashCode(arr1)] = true
	depth := 0
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			node := queue[i]
			if hashCode(node) == hashCode(arr2) {
				return depth
			}
			// 跳过前面相同的
			j := 0
			for j < n && node[j] == arr2[j] {
				j++
			}
			// 其实这里是使用了一点贪心，每次交换至少消除一个不同的数字
			for k := j + 1; k < n; k++ {
				if node[k] == arr2[j] {
					tmp := make([]byte, n)
					for idx, ch := range node {
						tmp[idx] = ch
					}
					tmp[j], tmp[k] = tmp[k], tmp[j]
					if !(visit[hashCode(tmp)]) {
						queue = append(queue, tmp)
						visit[hashCode(tmp)] = true
					}
				}
			}
		}
		queue = queue[size:]
		depth++
	}
	return -1
}

// dfs 尝试做下优化
func kSimilarity3(s1 string, s2 string) int {
	hashCode := func(arr []byte) int64 {
		var res int64
		for _, ch := range arr {
			k := int64(ch - 'a')
			res = (res << 3) + k
		}
		return res
	}
	cache := make(map[int64]int)
	var dfs func(arr1 []byte, arr2 []byte, idx int, cnt int) int
	dfs = func(arr1 []byte, arr2 []byte, idx int, cnt int) int {
		if v, exist := cache[hashCode(arr1)]; exist {
			return v
		}
		res := -1
		defer func() {
			cache[hashCode(arr1)] = res
		}()
		n := len(arr1)
		if idx == n-1 {
			res = 0
			return res
		}
		if cnt > n {
			return res
		}
		if arr1[idx] == arr2[idx] {
			return dfs(arr1, arr2, idx+1, cnt)
		}
		for i := idx + 1; i < n; i++ {
			if arr1[i] == arr2[idx] {
				arr1[i], arr1[idx] = arr1[idx], arr1[i]
				sub := dfs(arr1, arr2, idx+1, cnt+1)
				if sub >= 0 && (res < 0 || sub+1 < res) {
					res = sub + 1
				}
				arr1[i], arr1[idx] = arr1[idx], arr1[i]
			}
		}
		return res
	}
	return dfs([]byte(s1), []byte(s2), 0, 0)
}
