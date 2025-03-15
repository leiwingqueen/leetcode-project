package wc440

import (
	"reflect"
	"testing"
)

func Test_findMaxSum(t *testing.T) {
	type args struct {
		nums1 []int
		nums2 []int
		k     int
	}
	tests := []struct {
		name string
		args args
		want []int64
	}{
		// TODO: Add test cases.
		// [4,2,1,5,3]
		//[10,20,30,40,50]
		//2
		{"t1", args{[]int{4, 2, 1, 5, 3}, []int{10, 20, 30, 40, 50}, 2},
			[]int64{80, 30, 0, 80, 50}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findMaxSum(tt.args.nums1, tt.args.nums2, tt.args.k); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("findMaxSum() = %v, want %v", got, tt.want)
			}
		})
	}
}
