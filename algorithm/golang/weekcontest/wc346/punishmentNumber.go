package wc346

func punishmentNumber(n int) int {
	var dfs func(arr []int, idx int, expect int) bool
	check := func(num int) bool {
		k := num * num
		if k == 0 {
			return true
		}
		var arr []int
		for k > 0 {
			arr = append(arr, k%10)
			k /= 10
		}
		if dfs(arr, 0, num) {
			return true
		} else {
			return false
		}
	}
	dfs = func(arr []int, idx int, expect int) bool {
		if idx == len(arr) {
			return expect == 0
		}
		for k := 1; k <= len(arr)-idx; k++ {
			num := 0
			for i := idx + k - 1; i >= idx; i-- {
				num = num*10 + arr[i]
			}
			if num == 0 && k > 1 {
				continue
			}
			if num > expect {
				return false
			}
			if dfs(arr, idx+k, expect-num) {
				return true
			}
		}
		return false
	}
	res := 0
	for i := 1; i <= n; i++ {
		if check(i) {
			res += i * i
		}
	}
	return res
}
