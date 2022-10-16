package wc315

func countDistinctIntegers(nums []int) int {
	mp := make(map[int]struct{})
	var revert func(num int) int
	revert = func(num int) int {
		res := 0
		for num > 0 {
			res = res*10 + num%10
			num /= 10
		}
		return res
	}
	for _, num := range nums {
		mp[num] = struct{}{}
		mp[revert(num)] = struct{}{}
	}
	return len(mp)
}
