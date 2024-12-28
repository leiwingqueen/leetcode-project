package bwc146

import "testing"

func Test_subsequencesWithMiddleMode(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 2, 3, 3, 4}}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := subsequencesWithMiddleMode(tt.args.nums); got != tt.want {
				t.Errorf("subsequencesWithMiddleMode() = %v, want %v", got, tt.want)
			}
		})
	}
}
