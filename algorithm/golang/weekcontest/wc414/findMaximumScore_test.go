package wc414

import "testing"

func Test_findMaximumScore(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 3, 1, 5}}, 7},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findMaximumScore(tt.args.nums); got != tt.want {
				t.Errorf("findMaximumScore() = %v, want %v", got, tt.want)
			}
		})
	}
}
