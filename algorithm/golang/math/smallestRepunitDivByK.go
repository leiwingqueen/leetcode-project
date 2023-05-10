package math

func smallestRepunitDivByK(k int) int {
	var num int64
	num = 1
	for i := 1; i <= 19; i++ {
		if num%int64(k) == 0 {
			return i
		}
		num = num*10 + 1
	}
	return -1
}

func smallestRepunitDivByK2(k int) int {
	mp := make([]bool, k)
	num := 1
	i := 1
	for {
		if num%k == 0 {
			return i
		}
		if mp[num%k] {
			// 存在环
			return -1
		}
		mp[num%k] = true
		num = num%k*10 + 1
		i++
	}
}
