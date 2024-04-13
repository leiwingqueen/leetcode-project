package bwc128

import (
	"reflect"
	"testing"
)

func Test_minimumTime(t *testing.T) {
	type args struct {
		n         int
		edges     [][]int
		disappear []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{3, [][]int{
			{0, 1, 2}, {1, 2, 1}, {0, 2, 4},
		}, []int{1, 1, 5}}, []int{0, -1, 4}},
		// n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,3,5]
		{"t2", args{3, [][]int{
			{0, 1, 2}, {1, 2, 1}, {0, 2, 4},
		}, []int{1, 3, 5}}, []int{0, 2, 3}},
		// 3
		//[[2,0,8],[1,0,10],[1,2,3],[0,2,4],[1,2,6],[1,1,3],[1,0,6],[0,2,4]]
		//[4,2,8]
		{"t3", args{3, [][]int{
			{2, 0, 8}, {1, 0, 10}, {1, 2, 3}, {0, 2, 4}, {1, 2, 6}, {1, 1, 3}, {1, 0, 6}, {0, 2, 4},
		}, []int{4, 2, 8}}, []int{0, -1, 4}},
		// 3
		//[[0,1,2],[1,2,1],[0,2,4]]
		//[1,3,5]
		{"t4", args{3, [][]int{
			{0, 1, 2}, {1, 2, 1}, {0, 2, 4},
		}, []int{1, 3, 5}}, []int{0, 2, 3}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumTime(tt.args.n, tt.args.edges, tt.args.disappear); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("minimumTime() = %v, want %v", got, tt.want)
			}
		})
	}
}
