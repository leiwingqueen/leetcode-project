package array

import (
	"reflect"
	"testing"
)

func Test_maxNumber(t *testing.T) {
	type args struct {
		nums1 []int
		nums2 []int
		k     int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{3, 9}, []int{8, 9}, 3}, []int{9, 8, 9}},
		{"t2", args{[]int{3, 4, 6, 5}, []int{9, 1, 2, 5, 8, 3}, 5}, []int{9, 8, 6, 5, 3}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxNumber(tt.args.nums1, tt.args.nums2, tt.args.k); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("maxNumber() = %v, want %v", got, tt.want)
			}
		})
	}
}
