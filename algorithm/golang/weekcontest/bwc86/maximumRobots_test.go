package bwc86

import "testing"

func Test_maximumRobots(t *testing.T) {
	type args struct {
		chargeTimes  []int
		runningCosts []int
		budget       int64
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [3,6,1,3,4]
		//[2,1,3,4,5]
		//25
		{"t1", args{[]int{3, 6, 1, 3, 4}, []int{2, 1, 3, 4, 5}, 25}, 3},
		//[19,63,21,8,5,46,56,45,54,30,92,63,31,71,87,94,67,8,19,89,79,25]
		//[91,92,39,89,62,81,33,99,28,99,86,19,5,6,19,94,65,86,17,10,8,42]
		//85
		{"t2", args{[]int{19, 63, 21, 8, 5, 46, 56, 45, 54, 30, 92, 63, 31, 71, 87, 94, 67, 8, 19, 89, 79, 25},
			[]int{91, 92, 39, 89, 62, 81, 33, 99, 28, 99, 86, 19, 5, 6, 19, 94, 65, 86, 17, 10, 8, 42}, 85}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumRobots(tt.args.chargeTimes, tt.args.runningCosts, tt.args.budget); got != tt.want {
				t.Errorf("maximumRobots() = %v, want %v", got, tt.want)
			}
		})
	}
}
