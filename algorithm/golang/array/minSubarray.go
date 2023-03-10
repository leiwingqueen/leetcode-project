package array

// 超出时间限制
func minSubarray(nums []int, p int) int {
	n := len(nums)
	sum := 0
	for _, num := range nums {
		sum += num
	}
	mod := sum % p
	if mod == 0 {
		return 0
	}
	prefixSum := make([]int, n+1)
	for i := 0; i < n; i++ {
		prefixSum[i+1] = prefixSum[i] + nums[i]
	}
	//[0,1,2,3,4,5]
	// f(n)代表前n个数字的和，[i,j)的和就可以表达成f(j)-f(i)
	// 穷举所有的长度
	for i := 1; i < n; i++ {
		// 穷举对应的下标[j,j+i)
		for j := 0; j <= n-i; j++ {
			// [j,j+i)
			s := prefixSum[j+i] - prefixSum[j]
			if s%p == mod {
				return i
			}
		}
	}
	return -1
}

// [i,j)的和=mod最短的子数组
// 穷举j，找到最大的左边界i，令(f(j)-f(i))%p==mod
// (f(j)-f(i)+p)%p==mod
// f(j)%p-f(i)%p==mod
// f(i)%p=(f(j)%p-mod+p)%p
func minSubarray2(nums []int, p int) int {
	n := len(nums)
	sum := 0
	for _, num := range nums {
		sum += num
	}
	mod := sum % p
	if mod == 0 {
		return 0
	}
	mp := make(map[int]int)
	mp[0] = 0
	s := 0
	res := -1
	for i := 0; i < n; i++ {
		s += nums[i]
		key := (s%p - mod + p) % p
		if idx, exist := mp[key]; exist {
			if res < 0 || i-idx < res {
				res = i - idx
			}
		}
		mp[s%p] = i + 1
	}
	return res
}
