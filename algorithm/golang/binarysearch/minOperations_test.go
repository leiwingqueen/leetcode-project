package binarysearch

import "testing"

func Test_minOperations(t *testing.T) {
	type args struct {
		nums1 []int
		nums2 []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{6, 6}, []int{1}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minOperations(tt.args.nums1, tt.args.nums2); got != tt.want {
				t.Errorf("minOperations() = %v, want %v", got, tt.want)
			}
		})
	}
}
