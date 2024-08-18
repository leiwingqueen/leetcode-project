package bwc137

import "testing"

func Test_maximumValueSum(t *testing.T) {
	type args struct {
		board [][]int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{-3, 1, 1, 1}, {-3, 1, -3, 1}, {-3, 2, 1, 1},
		}}, 4},
		// [[1,2,3],[4,5,6],[7,8,9]]
		{"t2", args{[][]int{
			{1, 2, 3}, {4, 5, 6}, {7, 8, 9},
		}}, 15},
		{"t3", args{[][]int{
			{1, 1, 1}, {1, 1, 1}, {1, 1, 1},
		}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumValueSum2(tt.args.board); got != tt.want {
				t.Errorf("maximumValueSum() = %v, want %v", got, tt.want)
			}
		})
	}
}
