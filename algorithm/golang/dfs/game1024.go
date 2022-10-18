package dfs

// leetcode 1024游戏判断
func check(nums []int, op []byte) bool {
	v1 := make([]bool, len(nums))
	v2 := make([]bool, len(op))
	r1 := make([][]int, 0)
	r2 := make([][]byte, 0)
	var dfs1 func(path []int, idx int, size int)
	var dfs2 func(path []byte, idx int, size int)
	dfs1 = func(path []int, idx int, size int) {
		if idx == size {
			r := make([]int, size)
			for i := 0; i < size; i++ {
				r[i] = path[i]
			}
			r1 = append(r1, r)
			return
		}
		for i := 0; i < len(v1); i++ {
			if !(v1[i]) {
				v1[i] = true
				path[idx] = nums[i]
				dfs1(path, idx+1, size)
				v1[i] = false
			}
		}
	}
	dfs2 = func(path []byte, idx int, size int) {
		if idx == size {
			r := make([]byte, size)
			for i := 0; i < size; i++ {
				r[i] = path[i]
			}
			r2 = append(r2, r)
			return
		}
		for i := 0; i < len(v2); i++ {
			if !(v2[i]) {
				v2[i] = true
				path[idx] = op[i]
				dfs2(path, idx+1, size)
				v2[i] = false
			}
		}
	}
	dfs1(make([]int, len(nums)), 0, len(nums))
	dfs2(make([]byte, len(op)), 0, len(op))

	return false
}
