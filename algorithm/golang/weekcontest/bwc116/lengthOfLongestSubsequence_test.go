package bwc116

import "testing"

func Test_lengthOfLongestSubsequence(t *testing.T) {
	type args struct {
		nums   []int
		target int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [4,1,3,2,1,5]
		//7
		{"t1", args{[]int{4, 1, 3, 2, 1, 5}, 7}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := lengthOfLongestSubsequence(tt.args.nums, tt.args.target); got != tt.want {
				t.Errorf("lengthOfLongestSubsequence() = %v, want %v", got, tt.want)
			}
		})
	}
}
