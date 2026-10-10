package math

import "testing"

func Test_minSumSquareDiff(t *testing.T) {
	type args struct {
		nums1 []int
		nums2 []int
		k1    int
		k2    int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 4, 10, 12}, []int{5, 8, 6, 9}, 10, 5}, 0},
		{"t2", args{[]int{11, 12, 13, 14, 15}, []int{13, 16, 16, 12, 14}, 3, 6}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minSumSquareDiff(tt.args.nums1, tt.args.nums2, tt.args.k1, tt.args.k2); got != tt.want {
				t.Errorf("minSumSquareDiff() = %v, want %v", got, tt.want)
			}
		})
	}
}
