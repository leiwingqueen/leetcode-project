package wc398

import (
	"reflect"
	"testing"
)

func Test_isArraySpecial3(t *testing.T) {
	type args struct {
		nums    []int
		queries [][]int
	}
	tests := []struct {
		name string
		args args
		want []bool
	}{
		// [4,3,1,6]
		//[[0,2],[2,3]]
		{"t1", args{[]int{4, 3, 1, 6}, [][]int{
			{0, 2},
			{2, 3},
		}}, []bool{false, true}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := isArraySpecial3(tt.args.nums, tt.args.queries); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("isArraySpecial3() = %v, want %v", got, tt.want)
			}
		})
	}
}
