package wc300

import "testing"

func Test_countPaths(t *testing.T) {
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
			{1, 1}, {3, 4},
		}}, 8},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countPaths(tt.args.grid); got != tt.want {
				t.Errorf("countPaths() = %v, want %v", got, tt.want)
			}
		})
	}
}
