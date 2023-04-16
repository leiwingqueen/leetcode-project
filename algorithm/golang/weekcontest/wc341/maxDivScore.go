package wc341

func maxDivScore(nums []int, divisors []int) int {
	res := -1
	mx := -1
	for _, divisor := range divisors {
		sum := 0
		for _, num := range nums {
			if num%divisor == 0 {
				sum++
			}
		}
		if sum > mx || sum == mx && divisor < res {
			res, mx = divisor, sum
		}
	}
	return res
}
