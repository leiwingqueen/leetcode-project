package graph

import "testing"

func Test_minCost(t *testing.T) {
	type args struct {
		maxTime     int
		edges       [][]int
		passingFees []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{29, [][]int{
			{0, 1, 10}, {1, 2, 10}, {2, 5, 10}, {0, 3, 1}, {3, 4, 10}, {4, 5, 15},
		}, []int{5, 1, 2, 20, 20, 3}}, 48},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minCost(tt.args.maxTime, tt.args.edges, tt.args.passingFees); got != tt.want {
				t.Errorf("minCost() = %v, want %v", got, tt.want)
			}
		})
	}
}
