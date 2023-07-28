package topological

import "testing"

func Test_minimumTime(t *testing.T) {
	type args struct {
		n         int
		relations [][]int
		time      []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{5, [][]int{
			{1, 5}, {2, 5}, {3, 5}, {3, 4}, {4, 5},
		}, []int{1, 2, 3, 4, 5}}, 12},
		{"t2", args{9, [][]int{
			{2, 7}, {2, 6}, {3, 6}, {4, 6}, {7, 6}, {2, 1}, {3, 1}, {4, 1}, {6, 1}, {7, 1}, {3, 8}, {5, 8}, {7, 8}, {1, 9}, {2, 9}, {6, 9}, {7, 9},
		}, []int{9, 5, 9, 5, 8, 7, 7, 8, 4}}, 32},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumTime2(tt.args.n, tt.args.relations, tt.args.time); got != tt.want {
				t.Errorf("minimumTime() = %v, want %v", got, tt.want)
			}
		})
	}
}
