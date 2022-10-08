package array

import (
	"reflect"
	"testing"
)

func Test_advantageCount(t *testing.T) {
	type args struct {
		nums1 []int
		nums2 []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		// [12,24,8,32]
		//[13,25,32,11]
		{"t1", args{[]int{12, 24, 8, 32}, []int{13, 25, 32, 11}}, []int{24, 32, 8, 12}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := advantageCount(tt.args.nums1, tt.args.nums2); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("advantageCount() = %v, want %v", got, tt.want)
			}
		})
	}
}
