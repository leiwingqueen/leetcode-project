package wc387

import "testing"

func Test_minimumOperationsToWriteY(t *testing.T) {
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
			{1, 2, 2}, {1, 1, 0}, {0, 1, 0},
		}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumOperationsToWriteY(tt.args.grid); got != tt.want {
				t.Errorf("minimumOperationsToWriteY() = %v, want %v", got, tt.want)
			}
		})
	}
}
