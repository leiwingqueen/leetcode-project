package bwc170

func totalWaviness(num1 int, num2 int) int {
	cal := func(num int) int {
		var arr []int
		for num > 0 {
			arr = append(arr, num%10)
			num = num / 10
		}
		if len(arr) < 3 {
			return 0
		}
		cnt := 0
		for i := 1; i < len(arr)-1; i++ {
			if (arr[i] > arr[i-1] && arr[i] > arr[i+1]) ||
				(arr[i] < arr[i-1] && arr[i] < arr[i+1]) {
				cnt++
			}
		}
		return cnt
	}
	res := 0
	for i := num1; i <= num2; i++ {
		res += cal(i)
	}
	return res
}
