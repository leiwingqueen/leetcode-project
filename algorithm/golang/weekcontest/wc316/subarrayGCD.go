package wc316

// 错误
func subarrayGCD(nums []int, k int) int {
	n := len(nums)
	arr := make([]int, n)
	for i := 0; i < n; i++ {
		if nums[i]%k == 0 {
			arr[i] = nums[i] / k
		} else {
			arr[i] = -1
		}
	}
	res := 0
	for i := 0; i < n; i++ {
		if arr[i] == 1 {
			l := i - 1
			r := i + 1
			for l >= 0 && arr[l] > 0 {
				l--
			}
			for r < n && arr[r] > 0 && arr[r] != 1 {
				r++
			}
			res += (i - l) * (r - i)
		}
	}
	return res
}

func subarrayGCD2(nums []int, k int) int {
	n := len(nums)
	var gcd func(a int, b int) int
	gcd = func(a int, b int) int {
		if b == 0 {
			return a
		} else {
			return gcd(b, a%b)
		}
	}
	res := 0
	for i := 0; i < n; i++ {
		r := nums[i]
		for j := i; j < n; j++ {
			r = gcd(r, nums[j])
			if r%k != 0 {
				break
			}
			if r == k {
				res++
			}
		}
	}
	return res
}
