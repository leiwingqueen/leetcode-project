package wc365

import "testing"

func Test_minSizeSubarray(t *testing.T) {
	type args struct {
		nums   []int
		target int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 3}, 5}, 2},
		{"t2", args{[]int{2, 4, 6, 8}, 3}, -1},
		{"t3", args{[]int{2, 1, 5, 7, 7, 1, 6, 3}, 39}, 9},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minSizeSubarray(tt.args.nums, tt.args.target); got != tt.want {
				t.Errorf("minSizeSubarray() = %v, want %v", got, tt.want)
			}
		})
	}
}
