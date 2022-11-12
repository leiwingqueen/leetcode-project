package bwc91

import "testing"

func Test_mostProfitablePath(t *testing.T) {
	type args struct {
		edges  [][]int
		bob    int
		amount []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{{0, 1}}, 1, []int{-7280, 2350}}, -7280},
		// [[0,1],[1,2],[1,3],[3,4]]
		//3
		//[-2,4,2,-4,6]
		{"t2", args{[][]int{{0, 1}, {1, 2}, {1, 3}, {3, 4}}, 3, []int{-2, 4, 2, -4, 6}}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := mostProfitablePath(tt.args.edges, tt.args.bob, tt.args.amount); got != tt.want {
				t.Errorf("mostProfitablePath() = %v, want %v", got, tt.want)
			}
		})
	}
}
