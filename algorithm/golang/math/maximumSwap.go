package math

func maximumSwap(num int) int {
	if num <= 9 {
		return num
	}
	arr := make([]int, 0)
	tmp := num
	for tmp > 0 {
		arr = append(arr, tmp%10)
		tmp /= 10
	}
	n := len(arr)
	res := num
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			arr[i], arr[j] = arr[j], arr[i]
			k := 0
			for l := n - 1; l >= 0; l-- {
				k = k*10 + arr[l]
			}
			if k > res {
				res = k
			}
			arr[i], arr[j] = arr[j], arr[i]
		}
	}
	return res
}
