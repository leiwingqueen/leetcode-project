package bwc87

func smallestSubarrays(nums []int) []int {
	n := len(nums)
	mx := make([]int, n)
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			mx[i] |= nums[j]
		}
	}
	res := make([]int, n)
	for i := 0; i < n; i++ {
		or := 0
		for j := i; j < n; j++ {
			or |= nums[j]
			if or == mx[i] {
				res[i] = j - i + 1
				break
			}
		}
	}
	return res
}

// 其实这道题不是那么容易
func smallestSubarray2(nums []int) []int {
	n := len(nums)
	or := 0
	j := n - 1
	cnt := make([]int, 31)
	inc := func(num int, diff int) {
		for i := 0; i < 31; i++ {
			if num <= 0 {
				break
			}
			if num&0b1 != 0 {
				cnt[i] += diff
			}
			num >>= 1
		}
	}
	check := func(num int) bool {
		for i := 0; i < 31; i++ {
			if num&0b1 != 0 && cnt[i] <= 1 {
				return false
			}
			num >>= 1
		}
		return true
	}
	res := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		or |= nums[i]
		inc(nums[i], 1)
		for j > i && check(nums[j]) {
			inc(nums[j], -1)
			j--
		}
		res[i] = j - i + 1
	}
	return res
}
