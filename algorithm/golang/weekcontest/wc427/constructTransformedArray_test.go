package wc427

import (
	"reflect"
	"testing"
)

func Test_constructTransformedArray(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{-1, 4, -1}}, []int{-1, -1, 4}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := constructTransformedArray(tt.args.nums); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("constructTransformedArray() = %v, want %v", got, tt.want)
			}
		})
	}
}
