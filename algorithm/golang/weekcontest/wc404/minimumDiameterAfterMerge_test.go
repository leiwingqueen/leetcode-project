package wc404

import "testing"

func Test_minimumDiameterAfterMerge(t *testing.T) {
	type args struct {
		edges1 [][]int
		edges2 [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{0, 1}, {0, 2}, {0, 3},
		}, [][]int{
			{0, 1},
		}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumDiameterAfterMerge(tt.args.edges1, tt.args.edges2); got != tt.want {
				t.Errorf("minimumDiameterAfterMerge() = %v, want %v", got, tt.want)
			}
		})
	}
}
