package dp

import "testing"

func Test_maximumProfit(t *testing.T) {
	type args struct {
		prices []int
		k      int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 7, 9, 8, 2}, 2}, 14},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumProfit2(tt.args.prices, tt.args.k); got != tt.want {
				t.Errorf("maximumProfit() = %v, want %v", got, tt.want)
			}
		})
	}
}
