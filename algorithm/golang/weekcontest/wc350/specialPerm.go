package wc350

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
