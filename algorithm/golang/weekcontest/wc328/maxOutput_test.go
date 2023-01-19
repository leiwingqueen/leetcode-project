package wc328

import "testing"

func Test_maxOutput(t *testing.T) {
	type args struct {
		n     int
		edges [][]int
		price []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		//6
		//[[0,1],[1,2],[1,3],[3,4],[3,5]]
		//[9,8,7,6,10,5]
		{"t1", args{6, [][]int{
			{0, 1}, {1, 2}, {1, 3}, {3, 4}, {3, 5},
		}, []int{9, 8, 7, 6, 10, 5}}, 24},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxOutput(tt.args.n, tt.args.edges, tt.args.price); got != tt.want {
				t.Errorf("maxOutput() = %v, want %v", got, tt.want)
			}
		})
	}
}
