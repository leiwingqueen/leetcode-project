package wc503

import (
	"reflect"
	"testing"
)

func Test_limitOccurrences(t *testing.T) {
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
		{"t1", args{[]int{1, 1, 1, 2, 2, 3}, 2}, []int{1, 1, 2, 2, 3}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := limitOccurrences(tt.args.nums, tt.args.k); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("limitOccurrences() = %v, want %v", got, tt.want)
			}
		})
	}
}
