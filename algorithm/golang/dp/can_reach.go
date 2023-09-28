package dp

// 微软面试题
// kangaroo jump
// arr表示长度为n的马路，其中1表示该坐标上有陷阱，0表示没有。
// 袋鼠有两种跳法，往前调1格，或者往前跳minJump~maxJump格(假设当前下标是i,则范围是[i+minJump+1,i+maxJump+1])
// 求袋鼠是否能到达最后一个点

// f(n)=f(n-1)|| {f(n-maxJump-1)...f(n-minJump-1)}
func canReach(arr []int, minJump int, maxJump int) bool {
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	if arr[0] == 1 {
		return false
	}
	n := len(arr)
	dp := make([]bool, n)
	dp[0] = true
	for i := 1; i < n; i++ {
		if arr[i] == 1 {
			dp[i] = false
		} else {
			if dp[i-1] {
				dp[i] = true
			} else {
				for j := max(0, i-maxJump-1); j <= min(n-1, i-minJump-1); j++ {
					if dp[j] {
						dp[i] = true
						break
					}
				}
			}
		}
	}
	return dp[n-1]
}

// 上面基础上增加一个条件，假设袋鼠能往后跳，跳的范围跟上面一致

// 假设能往后跳,bfs
func canReach2(arr []int, minJump int, maxJump int) bool {
	n := len(arr)
	visit := make([]bool, n)
	visit[0] = true
	queue := []int{0}
	for len(queue) > 0 {
		node := queue[0]
		queue = queue[1:]
		// 往前和后退一步
		if node+1 < n && !visit[node+1] && arr[node+1] == 0 {
			queue = append(queue, node+1)
			visit[node+1] = true
		}
		if node-1 >= 0 && !visit[node-1] && arr[node-1] == 0 {
			queue = append(queue, node-1)
			visit[node-1] = true
		}
		// 往前和往后若干步
		for i := minJump; i <= maxJump && node+i+1 < n; i++ {
			next := node + i + 1
			if !visit[next] && arr[next] == 0 {
				queue = append(queue, next)
				visit[next] = true
			}
		}
		for i := minJump; i <= maxJump && node-i-1 >= 0; i++ {
			next := node - i - 1
			if !visit[next] && arr[next] == 0 {
				queue = append(queue, next)
				visit[next] = true
			}
		}
	}
	return visit[n-1]
}
