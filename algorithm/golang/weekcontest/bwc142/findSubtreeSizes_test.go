package bwc142

import (
	"reflect"
	"testing"
)

func Test_findSubtreeSizes(t *testing.T) {
	type args struct {
		parent []int
		s      string
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{-1, 0, 0, 1, 1, 1}, "abaabc"}, []int{6, 3, 1, 1, 1, 1}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findSubtreeSizes(tt.args.parent, tt.args.s); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("findSubtreeSizes() = %v, want %v", got, tt.want)
			}
		})
	}
}
