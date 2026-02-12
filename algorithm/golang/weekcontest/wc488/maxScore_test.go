package wc488

import "testing"

func Test_maxScore(t *testing.T) {
	type args struct {
		nums1 []int
		nums2 []int
		k     int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 3, 2}, []int{4, 5, 1}, 2}, 22},
		{"t2", args{[]int{-3, -2}, []int{1, 2}, 2}, -7},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxScore(tt.args.nums1, tt.args.nums2, tt.args.k); got != tt.want {
				t.Errorf("maxScore() = %v, want %v", got, tt.want)
			}
		})
	}
}
