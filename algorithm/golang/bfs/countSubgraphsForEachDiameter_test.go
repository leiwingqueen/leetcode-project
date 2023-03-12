package bfs

import (
	"reflect"
	"testing"
)

func Test_countSubgraphsForEachDiameter(t *testing.T) {
	type args struct {
		n     int
		edges [][]int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		// 4
		//[[1,2],[2,3],[2,4]]
		{"t1", args{4, [][]int{
			{1, 2},
			{2, 3},
			{2, 4},
		}}, []int{3, 4, 0}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countSubgraphsForEachDiameter(tt.args.n, tt.args.edges); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("countSubgraphsForEachDiameter() = %v, want %v", got, tt.want)
			}
		})
	}
}
