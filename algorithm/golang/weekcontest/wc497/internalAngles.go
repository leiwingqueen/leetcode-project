package wc497

import (
	"math"
	"slices"
)

func internalAngles(sides []int) []float64 {
	slices.Sort(sides)
	a, b, c := sides[0], sides[1], sides[2]
	if a+b <= c {
		return []float64{}
	}
	const rad = 180 / math.Pi
	A := math.Acos(float64(b*b+c*c-a*a)/float64(2*b*c)) * rad
	B := math.Acos(float64(a*a+c*c-b*b)/float64(2*a*c)) * rad
	C := 180 - A - B
	res := []float64{A, B, C}
	slices.Sort(res)
	return res
}
