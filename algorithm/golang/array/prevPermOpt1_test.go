package array

import (
	"reflect"
	"testing"
)

func Test_prevPermOpt3(t *testing.T) {
	type args struct {
		arr []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		// [3,1,1,3]
		{"t1", args{[]int{3, 1, 1, 3}}, []int{1, 3, 1, 3}},
		{"t2", args{[]int{3, 2, 1}}, []int{3, 1, 2}},
		// [1,9,4,6,7]
		{"t3", args{[]int{1, 9, 4, 6, 7}}, []int{1, 7, 4, 6, 9}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := prevPermOpt3(tt.args.arr); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("prevPermOpt3() = %v, want %v", got, tt.want)
			}
		})
	}
}
