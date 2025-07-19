package bwc160

import "testing"

func Test_minCost(t *testing.T) {
	type args struct {
		m        int
		n        int
		waitCost [][]int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{2, 2, [][]int{
			{3, 5}, {2, 4},
		}}, 9},
		{
			"t2",
			args{2, 3, [][]int{
				{6, 1, 4}, {3, 2, 5},
			}},
			16,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minCost(tt.args.m, tt.args.n, tt.args.waitCost); got != tt.want {
				t.Errorf("minCost() = %v, want %v", got, tt.want)
			}
		})
	}
}
