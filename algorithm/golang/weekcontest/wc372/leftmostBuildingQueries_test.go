package wc372

import (
	"reflect"
	"testing"
)

func Test_leftmostBuildingQueries2(t *testing.T) {
	type args struct {
		heights []int
		queries [][]int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{6, 4, 8, 5, 2, 7}, [][]int{
			{0, 1}, {0, 3}, {2, 4}, {3, 4}, {2, 2},
		}}, []int{2, 5, -1, 5, 2}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := leftmostBuildingQueries2(tt.args.heights, tt.args.queries); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("leftmostBuildingQueries2() = %v, want %v", got, tt.want)
			}
		})
	}
}
