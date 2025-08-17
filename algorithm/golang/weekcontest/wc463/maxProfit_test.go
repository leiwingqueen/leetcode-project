package wc463

import "testing"

func Test_maxProfit(t *testing.T) {
	type args struct {
		prices   []int
		strategy []int
		k        int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{5, 4, 3}, []int{1, 1, 0}, 2}, 9},
		{"t1", args{[]int{4, 7, 13}, []int{-1, -1, 0}, 2}, 9},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxProfit(tt.args.prices, tt.args.strategy, tt.args.k); got != tt.want {
				t.Errorf("maxProfit() = %v, want %v", got, tt.want)
			}
		})
	}
}
