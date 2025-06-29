package binarysearch

import "testing"

func Test_numSubseq2(t *testing.T) {
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
		{"t1", args{[]int{3, 5, 6, 7}, 9}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numSubseq2(tt.args.nums, tt.args.target); got != tt.want {
				t.Errorf("numSubseq2() = %v, want %v", got, tt.want)
			}
		})
	}
}
