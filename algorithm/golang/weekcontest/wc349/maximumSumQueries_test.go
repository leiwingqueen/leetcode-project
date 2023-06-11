package wc349

import (
	"reflect"
	"testing"
)

func Test_maximumSumQueries3(t *testing.T) {
	type args struct {
		nums1   []int
		nums2   []int
		queries [][]int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		// [3,2,5]
		//[2,3,4]
		//[[4,4],[3,2],[1,1]]
		{"t1", args{[]int{3, 2, 5}, []int{2, 3, 4}, [][]int{
			{4, 4},
			{3, 2},
			{1, 1},
		}}, []int{}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumSumQueries3(tt.args.nums1, tt.args.nums2, tt.args.queries); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("maximumSumQueries3() = %v, want %v", got, tt.want)
			}
		})
	}
}
