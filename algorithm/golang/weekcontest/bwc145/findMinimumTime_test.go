package bwc145

import "testing"

func Test_findMinimumTime(t *testing.T) {
	type args struct {
		strength []int
		K        int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [3,4,1]
		{"t1", args{[]int{3, 4, 1}, 1}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findMinimumTime2(tt.args.strength, tt.args.K); got != tt.want {
				t.Errorf("findMinimumTime() = %v, want %v", got, tt.want)
			}
		})
	}
}
