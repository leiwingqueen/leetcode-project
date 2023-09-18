package wc363

func maxNumberOfAlloys(n int, k int, budget int, composition [][]int, stock []int, cost []int) int {
	check := func(i, l int) bool {
		c := 0
		for j := 0; j < n; j++ {
			m := l * composition[i][j]
			if stock[j] < m {
				c += (m - stock[j]) * cost[j]
			}
		}
		return c <= budget
	}
	find := func(i int) int {
		l, r := 0, 200_000_000
		for l < r {
			mid := l + (r-l+1)/2
			if check(i, mid) {
				l = mid
			} else {
				r = mid - 1
			}
		}
		return l
	}
	res := 0
	for i := 0; i < k; i++ {
		c := find(i)
		if c > res {
			res = c
		}
	}
	return res
}
