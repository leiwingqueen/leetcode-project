package bwc127

import "testing"

func Test_minimumSubarrayLength2(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 3}, 2}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumSubarrayLength2(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("minimumSubarrayLength2() = %v, want %v", got, tt.want)
			}
		})
	}
}
