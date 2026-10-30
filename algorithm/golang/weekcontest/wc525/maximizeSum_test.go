package wc525

import "testing"

func Test_maximizeSum(t *testing.T) {
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
		{"t1", args{[]int{123, 45}, 1}, 366},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximizeSum(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("maximizeSum() = %v, want %v", got, tt.want)
			}
		})
	}
}
