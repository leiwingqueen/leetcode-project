package array

import "testing"

func Test_minOperationsMaxProfit2(t *testing.T) {
	type args struct {
		customers    []int
		boardingCost int
		runningCost  int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{10, 10, 6, 4, 7}, 3, 8}, 9},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minOperationsMaxProfit2(tt.args.customers, tt.args.boardingCost, tt.args.runningCost); got != tt.want {
				t.Errorf("minOperationsMaxProfit2() = %v, want %v", got, tt.want)
			}
		})
	}
}
