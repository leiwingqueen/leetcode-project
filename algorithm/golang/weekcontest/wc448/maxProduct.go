package wc448

func maxProduct(n int) int {
	cnt := make([]int, 10)
	for n >= 0 {
		if n == 0 {
			cnt[0]++
			break
		}
		cnt[n%10]++
		n /= 10
	}
	res := 1
	need := 2
	i := 9
	for need > 0 {
		if cnt[i] == 0 {
			i--
			continue
		}
		res *= i
		need--
		cnt[i]--
	}
	return res
}
