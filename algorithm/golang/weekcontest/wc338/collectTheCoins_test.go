package wc338

import "testing"

func Test_collectTheCoins(t *testing.T) {
	type args struct {
		coins []int
		edges [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 0, 0, 0, 0, 1}, [][]int{
			{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5},
		}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := collectTheCoins(tt.args.coins, tt.args.edges); got != tt.want {
				t.Errorf("collectTheCoins() = %v, want %v", got, tt.want)
			}
		})
	}
}
