package backtrace

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
			for j := 0; j < n-1; j++ {
				for k := j + 1; k < n; k++ {
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
