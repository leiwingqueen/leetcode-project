package wc326

import "sort"

func distinctPrimeFactors(nums []int) int {
	sort.Ints(nums)
	check := func(num int) bool {
		for i := 2; i < num; i++ {
			if num%i == 0 {
				return false
			}
		}
		return true
	}
	calPrimes := func(mx int) []int {
		var res []int
		for i := 2; i <= mx; i++ {
			if check(i) {
				res = append(res, i)
			}
		}
		return res
	}
	primes := calPrimes(nums[len(nums)-1])
	mp := make(map[int]struct{})
	for _, num := range nums {
		for _, prime := range primes {
			if num <= 1 {
				break
			}
			for num%prime == 0 {
				num /= prime
				mp[prime] = struct{}{}
			}
		}
	}
	return len(mp)
}
