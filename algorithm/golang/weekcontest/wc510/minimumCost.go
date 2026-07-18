package wc510

func minimumCost(nums []int, k int) int {
	mod := 1_000_000_007
	resource := k
	cost := 0
	i := 0
	for _, num := range nums {
		if num <= resource {
			resource -= num
		} else {
			for num > resource {
				i++
				cost = (cost + i) % mod
				resource += k
			}
			resource -= num
		}
	}
	return cost
}

// 还是会溢出
func minimumCost2(nums []int, k int) int {
	mod := 1_000_000_007
	resource := k
	cost := 0
	i := 0
	for _, num := range nums {
		if num <= resource {
			resource -= num
		} else {
			diff := num - resource
			cnt := (diff + k - 1) / k
			resource += cnt * k
			// [i+1,i+cnt]
			sum := (i + 1 + i + cnt) * cnt / 2
			cost = (cost + sum%mod) % mod
			i += cnt
			resource -= num
		}
	}
	return cost
}

func minimumCost3(nums []int, k int) int {
	mod := 1_000_000_007
	resource := k
	cost := 0
	i := 0
	for _, num := range nums {
		if num <= resource {
			resource -= num
		} else {
			diff := num - resource
			cnt := (diff + k - 1) / k
			resource = (k - diff%k) % k
			// [i+1,i+cnt]
			sum := ((i + 1 + i + cnt) % mod) * (cnt % mod) / 2
			cost = (cost + sum%mod) % mod
			i += cnt
		}
	}
	return cost
}
