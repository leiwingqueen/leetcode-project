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
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumTime(tt.args.n, tt.args.relations, tt.args.time); got != tt.want {
				t.Errorf("minimumTime() = %v, want %v", got, tt.want)
			}
		})
	}
}
