package bwc418

import (
	"reflect"
	"testing"
)

func Test_remainingMethods(t *testing.T) {
	type args struct {
		n           int
		k           int
		invocations [][]int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		// 3
		//2
		//[[1,0],[2,0]]
		{"t1", args{3, 2, [][]int{
			{1, 0},
			{2, 0},
		}}, []int{0, 1, 2}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := remainingMethods(tt.args.n, tt.args.k, tt.args.invocations); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("remainingMethods() = %v, want %v", got, tt.want)
			}
		})
	}
}
