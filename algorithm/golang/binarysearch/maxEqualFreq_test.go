package binarysearch

import "testing"

func Test_maxEqualFreq(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5}}, 13},
		{"t2", args{[]int{2, 2, 1, 1, 5, 3, 3, 5}}, 7},
		{"t3", args{[]int{10, 2, 8, 9, 3, 8, 1, 5, 2, 3, 7, 6}}, 8},
		{"t4", args{[]int{10, 2, 2, 2, 2, 2, 2, 2, 2, 3, 7, 6}}, 9},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxEqualFreq2(tt.args.nums); got != tt.want {
				t.Errorf("maxEqualFreq() = %v, want %v", got, tt.want)
			}
		})
	}
}
