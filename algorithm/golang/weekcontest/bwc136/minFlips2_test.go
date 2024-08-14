package bwc136

import "testing"

func Test_minFlips2(t *testing.T) {
	type args struct {
		grid [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{1, 0, 0}, {0, 1, 0}, {0, 0, 1},
		}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minFlips2(tt.args.grid); got != tt.want {
				t.Errorf("minFlips2() = %v, want %v", got, tt.want)
			}
		})
	}
}
