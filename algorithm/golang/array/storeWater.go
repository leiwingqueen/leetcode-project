package array

import "math"

func storeWater(bucket []int, vat []int) int {
	mxMat := 0
	for _, v := range vat {
		if v > mxMat {
			mxMat = v
		}
	}
	if mxMat == 0 {
		return 0
	}
	res := math.MaxInt
	for x := 1; x <= mxMat; x++ {
		total := 0
		for i, v := range vat {
			// 至少要达到的容量
			w := (v + x - 1) / x
			if bucket[i] < w {
				total += w - bucket[i]
			}
		}
		total += x
		if total < res {
			res = total
		}
	}
	return res
}
