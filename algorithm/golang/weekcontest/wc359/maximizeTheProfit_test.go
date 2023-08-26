package wc359

import "testing"

func Test_maximizeTheProfit(t *testing.T) {
	type args struct {
		n      int
		offers [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// 5
		//[[0,0,1],[0,2,2],[1,3,2]]
		{"t1", args{5, [][]int{
			{0, 0, 1}, {0, 2, 2}, {1, 3, 2},
		}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximizeTheProfit(tt.args.n, tt.args.offers); got != tt.want {
				t.Errorf("maximizeTheProfit() = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test_maximizeTheProfit2(t *testing.T) {
	type args struct {
		n      int
		offers [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"t1", args{5, [][]int{
			{0, 0, 1}, {0, 2, 2}, {1, 3, 2},
		}}, 3},
		{"t2", args{5, [][]int{
			{0, 0, 1}, {0, 2, 10}, {1, 3, 2},
		}}, 10},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximizeTheProfit2(tt.args.n, tt.args.offers); got != tt.want {
				t.Errorf("maximizeTheProfit2() = %v, want %v", got, tt.want)
			}
		})
	}
}
