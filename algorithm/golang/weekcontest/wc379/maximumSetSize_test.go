package wc379

import "testing"

func Test_maximumSetSize(t *testing.T) {
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
		{"t1", args{[]int{1, 2, 1, 2}, []int{1, 1, 1, 1}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumSetSize(tt.args.nums1, tt.args.nums2); got != tt.want {
				t.Errorf("maximumSetSize() = %v, want %v", got, tt.want)
			}
		})
	}
}
