package wc320

import "testing"

func Test_minimumFuelCost(t *testing.T) {
	type args struct {
		roads [][]int
		seats int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{3, 1}, {3, 2}, {1, 0}, {0, 4}, {0, 5}, {4, 6},
		}, 2}, 7},
		// [[0,1],[0,2],[1,3],[1,4]]
		//5
		{"t2", args{[][]int{
			{0, 1}, {0, 2}, {1, 3}, {1, 4},
		}, 5}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumFuelCost(tt.args.roads, tt.args.seats); got != tt.want {
				t.Errorf("minimumFuelCost() = %v, want %v", got, tt.want)
			}
		})
	}
}
