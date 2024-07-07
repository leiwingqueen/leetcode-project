package wc405

func validStrings(n int) []string {
	var res []string
	var dfs func(idx int, path []byte)
	dfs = func(idx int, path []byte) {
		if idx == n {
			res = append(res, string(path))
			return
		}
		if idx == 0 {
			path[0] = '0'
			dfs(idx+1, path)
			path[0] = '1'
			dfs(idx+1, path)
		} else {
			if path[idx-1] == '0' {
				path[idx] = '1'
				dfs(idx+1, path)
			} else {
				path[idx] = '1'
				dfs(idx+1, path)
				path[idx] = '0'
				dfs(idx+1, path)
			}
		}
	}
	dfs(0, make([]byte, n))
	return res
}
