package array

import (
	"reflect"
	"testing"
)

func Test_maxSubsequence(t *testing.T) {
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
		{"t1", args{[]int{2, 1, 3, 3}, 2}, []int{3, 3}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxSubsequence(tt.args.nums, tt.args.k); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("maxSubsequence() = %v, want %v", got, tt.want)
			}
		})
	}
}
