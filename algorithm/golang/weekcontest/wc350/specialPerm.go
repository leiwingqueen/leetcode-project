package wc350

// 超时
func specialPerm(nums []int) int {
	n := len(nums)
	mod := 1_000_000_007
	var dfs func(arr []int, idx int) int
	dfs = func(arr []int, idx int) int {
		if idx == n {
			return 1
		}
		res := 0
		for i := idx; i < n; i++ {
			if idx == 0 || arr[i]%arr[idx-1] == 0 || arr[idx-1]%arr[i] == 0 {
				// swap
				arr[i], arr[idx] = arr[idx], arr[i]
				res = (res + dfs(arr, idx+1)) % mod
				arr[i], arr[idx] = arr[idx], arr[i]
			}
		}
		return res
	}
	return dfs(nums, 0)
}

// 状态压缩，记忆
func specialPerm2(nums []int) int {
	n := len(nums)
	mod := 1_000_000_007
	// mem := make(map[int]int)
	var dfs func(state int, pre int) int
	dfs = func(state int, pre int) int {
		if state == 0 {
			return 1
		}
		/*if v, exist := mem[state]; exist {
			return v
		}*/
		res := 0
		for i := 0; i < n; i++ {
			if state&(1<<i) != 0 && (nums[i]%pre == 0 || pre%nums[i] == 0) {
				res = (res + dfs(state^(1<<i), nums[i])) % mod
			}
		}
		// mem[state] = res
		// fmt.Println(fmt.Sprintf("state:%d,pre:%d,res:%d", state, pre, res))
		return res
	}
	return dfs((1<<n)-1, 1)
}
