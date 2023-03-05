package wc335

// 溢出的问题如何解决
func findValidSplit(nums []int) int {
	n := len(nums)
	var l int64
	var r int64
	l = int64(nums[0])
	r = 1
	for i := 1; i < n; i++ {
		r *= int64(nums[i])
	}
	if gcd(l, r) == 1 {
		return 1
	}
	for i := 1; i <= n-2; i++ {
		l *= int64(nums[i])
		r /= int64(nums[i])
		if gcd(l, r) == 1 {
			return i
		}
	}
	return -1
}

func gcd(a int64, b int64) int64 {
	if b == 0 {
		return a
	} else {
		return gcd(b, a%b)
	}
}

// 勉强通过
func findValidSplit2(nums []int) int {
	if len(nums) <= 1 {
		return -1
	}
	// 质因数分解
	divide := func(num int) map[int]int {
		res := make(map[int]int)
		for i := 2; i <= num/i; i++ {
			if num%i == 0 {
				k := 0
				for num%i == 0 {
					num /= i
					k++
				}
				res[i] = k
			}
		}
		if num > 1 {
			res[num]++
		}
		return res
	}
	n := len(nums)
	l := make(map[int]int)
	r := make(map[int]int)
	m := divide(nums[0])
	for k, v := range m {
		l[k] += v
	}
	for i := 1; i < n; i++ {
		m2 := divide(nums[i])
		for k, v := range m2 {
			r[k] += v
		}
	}
	flag := true
	for k, _ := range l {
		if r[k] > 0 {
			flag = false
			break
		}
	}
	if flag {
		return 0
	}
	for i := 1; i <= n-2; i++ {
		m2 := divide(nums[i])
		for k, v := range m2 {
			l[k] += v
			r[k] -= v
		}
		flag2 := true
		for k, v := range l {
			if v > 0 && r[k] > 0 {
				flag2 = false
				break
			}
		}
		if flag2 {
			return i
		}
	}
	return -1
}
