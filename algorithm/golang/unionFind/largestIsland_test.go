package unionFind

import "testing"

func Test_largestIsland(t *testing.T) {
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
			{0, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 0, 0}, {0, 1, 0, 0, 1, 0, 0}, {1, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 0, 0}, {0, 1, 1, 1, 1, 0, 0},
		}}, 18},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := largestIsland(tt.args.grid); got != tt.want {
				t.Errorf("largestIsland() = %v, want %v", got, tt.want)
			}
		})
	}
}
