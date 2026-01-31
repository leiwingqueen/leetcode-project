package wc486

import (
	"reflect"
	"testing"
)

func Test_rotateElements(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, -2, 3, -4}, 3}, []int{3, -2, 1, -4}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := rotateElements(tt.args.nums, tt.args.k); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("rotateElements() = %v, want %v", got, tt.want)
			}
		})
	}
}
