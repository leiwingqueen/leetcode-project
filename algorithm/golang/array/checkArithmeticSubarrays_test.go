package array

import (
	"reflect"
	"testing"
)

func Test_checkArithmeticSubarrays(t *testing.T) {
	type args struct {
		nums []int
		l    []int
		r    []int
	}
	tests := []struct {
		name string
		args args
		want []bool
	}{
		// TODO: Add test cases.
		// [4,6,5,9,3,7]
		//[0,0,2]
		//[2,3,5]
		{"t1", args{[]int{4, 6, 5, 9, 3, 7}, []int{0, 0, 2}, []int{2, 3, 5}}, []bool{true, false, true}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := checkArithmeticSubarrays(tt.args.nums, tt.args.l, tt.args.r); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("checkArithmeticSubarrays() = %v, want %v", got, tt.want)
			}
		})
	}
}
