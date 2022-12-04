package wc322

import "testing"

func Test_minScore(t *testing.T) {
	type args struct {
		n     int
		roads [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// 4
		//[[1,2,2],[1,3,4],[3,4,7]]
		{"t1", args{4, [][]int{{1, 2, 2}, {1, 3, 4}, {3, 4, 7}}}, 2},
		// 4
		//[[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
		{"t2", args{4, [][]int{{1, 2, 9}, {2, 3, 6}, {2, 4, 5}, {1, 4, 7}}}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minScore2(tt.args.n, tt.args.roads); got != tt.want {
				t.Errorf("minScore() = %v, want %v", got, tt.want)
			}
		})
	}
}
