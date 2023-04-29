package bwc103

func findThePrefixCommonArray(A []int, B []int) []int {
	n := len(A)
	res := make([]int, n)
	for i := 0; i < n; i++ {
		mp1 := make([]int, n)
		mp2 := make([]int, n)
		for j := 0; j <= i; j++ {
			mp1[A[j]-1] = 1
			mp2[B[j]-1] = 1
		}
		cnt := 0
		for j := 0; j < n; j++ {
			if mp1[j] == 1 && mp2[j] == 1 {
				cnt++
			}
		}
		res[i] = cnt
	}
	return res
}
