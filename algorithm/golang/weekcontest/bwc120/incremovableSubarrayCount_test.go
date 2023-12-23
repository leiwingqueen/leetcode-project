package bwc120

import "testing"

func Test_incremovableSubarrayCount2(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 3, 4}}, 10},
		{"t2", args{[]int{6, 5, 7, 8}}, 7},
		{"t3", args{[]int{8, 7, 6, 6}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := incremovableSubarrayCount2(tt.args.nums); got != tt.want {
				t.Errorf("incremovableSubarrayCount2() = %v, want %v", got, tt.want)
			}
		})
	}
}
