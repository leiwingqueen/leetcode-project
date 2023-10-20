package bwc115

import "testing"

func Test_countSubMultisets(t *testing.T) {
	type args struct {
		nums []int
		l    int
		r    int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// 1,2,2,3
		{"t1", args{[]int{1, 2, 2, 3}, 6, 6}, 1},
		// [2,1,4,2,7]
		{"t2", args{[]int{2, 1, 4, 2, 7}, 1, 5}, 7},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countSubMultisets(tt.args.nums, tt.args.l, tt.args.r); got != tt.want {
				t.Errorf("countSubMultisets() = %v, want %v", got, tt.want)
			}
		})
	}
}
