package wc312

import "testing"

func Test_numberOfGoodPaths(t *testing.T) {
	type args struct {
		vals  []int
		edges [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 3, 2, 1, 3}, [][]int{
			{0, 1}, {0, 2}, {2, 3}, {2, 4},
		}}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numberOfGoodPaths(tt.args.vals, tt.args.edges); got != tt.want {
				t.Errorf("numberOfGoodPaths() = %v, want %v", got, tt.want)
			}
		})
	}
}
