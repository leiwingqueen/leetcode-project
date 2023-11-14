package bfs

import "testing"

func Test_findTheCity(t *testing.T) {
	type args struct {
		n                 int
		edges             [][]int
		distanceThreshold int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{5, [][]int{
			{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1},
		}, 2}, 0},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findTheCity2(tt.args.n, tt.args.edges, tt.args.distanceThreshold); got != tt.want {
				t.Errorf("findTheCity() = %v, want %v", got, tt.want)
			}
		})
	}
}
