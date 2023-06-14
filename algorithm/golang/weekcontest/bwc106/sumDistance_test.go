package bwc106

import "testing"

func Test_sumDistance(t *testing.T) {
	type args struct {
		nums []int
		s    string
		d    int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{-10, -13, 10, 14, 11}, "LRLLR", 2}, 146},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := sumDistance(tt.args.nums, tt.args.s, tt.args.d); got != tt.want {
				t.Errorf("sumDistance() = %v, want %v", got, tt.want)
			}
		})
	}
}
