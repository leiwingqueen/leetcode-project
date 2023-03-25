package binarysearch

import "testing"

func Test_findLengthOfShortestSubarray2(t *testing.T) {
	type args struct {
		arr []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [5,4,3,2,1]
		{"t1", args{[]int{5, 4, 3, 2, 1}}, 4},
		// [1,2,3,10,4,2,3,5]
		{"t2", args{[]int{1, 2, 3, 10, 4, 2, 3, 5}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findLengthOfShortestSubarray2(tt.args.arr); got != tt.want {
				t.Errorf("findLengthOfShortestSubarray2() = %v, want %v", got, tt.want)
			}
		})
	}
}
