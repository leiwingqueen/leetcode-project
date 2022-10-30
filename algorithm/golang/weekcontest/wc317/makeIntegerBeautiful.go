package wc317

func makeIntegerBeautiful(n int64, target int) int64 {
	arr := make([]int, 13)
	sum := 0
	for i := 0; i < 13; i++ {
		arr[i] = int(n % 10)
		n /= 10
		sum += arr[i]
	}
	if sum <= target {
		return 0
	}
	diff := sum - target
	var res int64
	pow := int64(1)
	for i := 0; i < 13; i++ {
		if diff <= 0 {
			return res
		}
		if arr[i] >= 1 {
			res += int64(10-arr[i]) * pow
			diff -= arr[i]
			arr[i+1]++
			for j := i + 1; j < 13; j++ {
				// arr[j] += 1
				if arr[j] < 10 {
					diff++
					break
				}
				diff -= 9
				arr[j] = 0
				arr[j+1]++
			}
		}
		pow *= 10
	}
	return res
}
