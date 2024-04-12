package wc392

import "testing"

func Test_minOperationsToMakeMedianK(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{2, 5, 6, 8, 5}, 7}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minOperationsToMakeMedianK(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("minOperationsToMakeMedianK() = %v, want %v", got, tt.want)
			}
		})
	}
}
