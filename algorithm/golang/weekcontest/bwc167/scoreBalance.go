package bwc167

func scoreBalance(s string) bool {
	n := len(s)
	sum := 0
	for i := 0; i < n; i++ {
		sum += int(s[i]-'a') + 1
	}
	leftSum := 0
	for i := 0; i < n; i++ {
		leftSum += int(s[i]-'a') + 1
		if leftSum*2 == sum {
			return true
		}
	}
	return false
}

// 可以稍微优化一下
func scoreBalance2(s string) bool {
	n := len(s)
	sum := 0
	for i := 0; i < n; i++ {
		sum += int(s[i]-'a') + 1
	}
	if sum%2 == 1 {
		return false
	}
	leftSum := 0
	for i := 0; i < n; i++ {
		leftSum += int(s[i]-'a') + 1
		if leftSum*2 == sum {
			return true
		}
		if leftSum*2 > sum {
			return false
		}
	}
	return false
}
