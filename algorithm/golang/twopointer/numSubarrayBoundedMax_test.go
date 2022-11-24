package twopointer

import "testing"

func Test_numSubarrayBoundedMax(t *testing.T) {
	type args struct {
		nums  []int
		left  int
		right int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [2,1,4,3]
		//2
		//3
		{"t1", args{[]int{2, 1, 4, 3}, 2, 3}, 3},
		// [73,55,36,5,55,14,9,7,72,52]
		//32
		//69
		{"t2", args{[]int{73, 55, 36, 5, 55, 14, 9, 7, 72, 52}, 32, 69}, 22},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numSubarrayBoundedMax2(tt.args.nums, tt.args.left, tt.args.right); got != tt.want {
				t.Errorf("numSubarrayBoundedMax() = %v, want %v", got, tt.want)
			}
		})
	}
}
