package math

//本质上就是统计质数的个数k，然后求k!*(n-k)!
func numPrimeArrangements(n int) int {
	isPrime := func(num int) bool {
		if num <= 1 {
			return false
		}
		for i := 2; i < num; i++ {
			if num%i == 0 {
				return false
			}
		}
		return true
	}
	mod := 1_000_000_007
	k := 0
	for i := 2; i <= n; i++ {
		if isPrime(i) {
			k++
		}
	}
	p := n - k
	res := 1
	for k > 1 {
		res = (res * k) % mod
		k--
	}
	for p > 1 {
		res = (res * p) % mod
		p--
	}
	return res
}
