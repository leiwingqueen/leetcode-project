package wc347

import "testing"

func Test_maxIncreasingCells(t *testing.T) {
	type args struct {
		mat [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [[3,1],[3,4]]
		{"t1", args{[][]int{
			{3, 1},
			{3, 4},
		}}, 2},
		// [[7,6,3],[-7,-5,6],[-7,0,-4],[6,6,0],[-8,6,0]]
		{"t2", args{[][]int{
			{7, 6, 3}, {-7, -5, 6}, {-7, 0, -4}, {6, 6, 0}, {-8, 6, 0},
		}}, 7},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxIncreasingCells(tt.args.mat); got != tt.want {
				t.Errorf("maxIncreasingCells() = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test_maxIncreasingCells2(t *testing.T) {
	type args struct {
		mat [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{3, 1},
			{3, 4},
		}}, 2},
		{"t2", args{[][]int{{1}, {-4}}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxIncreasingCells2(tt.args.mat); got != tt.want {
				t.Errorf("maxIncreasingCells2() = %v, want %v", got, tt.want)
			}
		})
	}
}
