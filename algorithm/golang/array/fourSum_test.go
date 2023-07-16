package array

import (
	"reflect"
	"testing"
)

func Test_fourSum2(t *testing.T) {
	type args struct {
		nums   []int
		target int
	}
	tests := []struct {
		name string
		args args
		want [][]int
	}{
		// TODO: Add test cases.
		// [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
		{"t1", args{[]int{1, 0, -1, 0, -2, 2}, 0}, [][]int{
			{-2, -1, 1, 2}, {-2, 0, 0, 2}, {-1, 0, 0, 1},
		}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := fourSum2(tt.args.nums, tt.args.target); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("fourSum2() = %v, want %v", got, tt.want)
			}
		})
	}
}
