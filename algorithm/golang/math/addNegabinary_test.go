package math

import (
	"reflect"
	"testing"
)

func Test_addNegabinary2(t *testing.T) {
	type args struct {
		arr1 []int
		arr2 []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		// [1,1,1,1,1]
		//[1,0,1]
		{"t1", args{[]int{1, 1, 1, 1, 1}, []int{1, 0, 1}}, []int{1, 0, 0, 0, 0}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := addNegabinary2(tt.args.arr1, tt.args.arr2); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("addNegabinary2() = %v, want %v", got, tt.want)
			}
		})
	}
}
