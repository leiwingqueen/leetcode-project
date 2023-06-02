package bwc105

import "testing"

func Test_canTraverseAllPairs2(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{2, 3, 6}}, true},
		{"t2", args{[]int{99991, 99991}}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := canTraverseAllPairs2(tt.args.nums); got != tt.want {
				t.Errorf("canTraverseAllPairs2() = %v, want %v", got, tt.want)
			}
		})
	}
}
