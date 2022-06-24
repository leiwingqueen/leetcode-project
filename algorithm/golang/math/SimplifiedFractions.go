package math

import "fmt"

func simplifiedFractions(n int) []string {
	res := make([]string, 0)
	for i := 2; i <= n; i++ {
		for j := 1; j < i; j++ {
			if gcd(i, j) == 1 {
				res = append(res, fmt.Sprintf("%d/%d", j, i))
			}
		}
	}
	return res
}

func gcd(a int, b int) int {
	if b == 0 {
		return a
	} else {
		return gcd(b, a%b)
	}
}
