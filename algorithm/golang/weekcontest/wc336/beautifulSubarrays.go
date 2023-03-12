package wc336

// 超时
func beautifulSubarrays(nums []int) int64 {
	n := len(nums)
	prefix := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		prefix[i] = make([]int, 31)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < 31; j++ {
			c := 0
			if nums[i]&(1<<j) != 0 {
				c = 1
			}
			prefix[i+1][j] = prefix[i][j] + c
		}
	}
	var res int64
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			p := make([]int, 31)
			flag := true
			for k := 0; k < 31; k++ {
				p[k] = prefix[j+1][k] - prefix[i][k]
				if p[k]%2 == 1 {
					flag = false
					break
				}
			}
			if flag {
				res++
			}
		}
	}
	return res
}

func beautifulSubarrays2(nums []int) int64 {
	n := len(nums)
	var res int64
	sum := 0
	mp := make(map[int]int)
	mp[0] = 1
	for i := 0; i < n; i++ {
		num := nums[i]
		res += int64(mp[sum^num])
		sum ^= num
		mp[sum]++
	}
	return res
}
