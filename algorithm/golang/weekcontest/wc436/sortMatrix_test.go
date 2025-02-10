package wc436

import (
	"reflect"
	"testing"
)

func Test_sortMatrix(t *testing.T) {
	type args struct {
		grid [][]int
	}
	tests := []struct {
		name string
		args args
		want [][]int
	}{
		// TODO: Add test cases.
		// [1,7,3],[9,8,2],[4,5,6]
		{"t1", args{[][]int{
			{1, 7, 3},
			{9, 8, 2},
			{4, 5, 6},
		}}, [][]int{
			{8, 2, 3},
			{9, 6, 7},
			{4, 5, 1},
		}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := sortMatrix(tt.args.grid); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("sortMatrix() = %v, want %v", got, tt.want)
			}
		})
	}
}
