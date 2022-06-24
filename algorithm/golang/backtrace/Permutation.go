package backtrace

func permutation(s string) []string {
	res := make([]string, 0, 100)
	backtrace([]byte(s), make([]byte, len(s)), 0, &res)
	return res
}

func backtrace(s []byte, path []byte, idx int, res *[]string) {
	if idx == len(s) {
		*res = append(*res, string(path))
		return
	}
	set := make(map[byte]bool)
	for i := idx; i < len(s); i++ {
		if _, ok := set[s[i]]; ok {
			continue
		}
		set[s[i]] = true
		//交换字符
		swap(s, i, idx)
		path[idx] = s[idx]
		backtrace(s, path, idx+1, res)
		//还原
		swap(s, i, idx)
	}
}

func swap(s []byte, i int, j int) {
	tmp := s[i]
	s[i] = s[j]
	s[j] = tmp
}
