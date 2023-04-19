package wc341

import "testing"

func Test_minimumTotalPrice2(t *testing.T) {
	type args struct {
		n     int
		edges [][]int
		price []int
		trips [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// 4
		//[[0,1],[1,2],[1,3]]
		//[2,2,10,6]
		//[[0,3],[2,1],[2,3]]
		{"t1", args{4, [][]int{
			{0, 1}, {1, 2}, {1, 3},
		}, []int{2, 2, 10, 6}, [][]int{
			{0, 3}, {2, 1}, {2, 3},
		}}, 23},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumTotalPrice3(tt.args.n, tt.args.edges, tt.args.price, tt.args.trips); got != tt.want {
				t.Errorf("minimumTotalPrice2() = %v, want %v", got, tt.want)
			}
		})
	}
}
