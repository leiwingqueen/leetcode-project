package wc371

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
		// [1,2,7]
		//[4,5,3]
		{"t1", args{[]int{1, 2, 7}, []int{4, 5, 3}}, 1},
		// [1,5,4]
		//[2,5,3]
		{"t2", args{[]int{1, 5, 4}, []int{2, 5, 3}}, -1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minOperations(tt.args.nums1, tt.args.nums2); got != tt.want {
				t.Errorf("minOperations() = %v, want %v", got, tt.want)
			}
		})
	}
}
