package wc334

import "testing"

func Test_minimumTime3(t *testing.T) {
	type args struct {
		grid [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{0, 1, 100},
			{100, 100, 100},
		}}, 100},
		// [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
		{"t2", args{[][]int{
			{0, 1, 3, 2}, {5, 1, 2, 5}, {4, 3, 8, 6},
		}}, 7},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumTime4(tt.args.grid); got != tt.want {
				t.Errorf("minimumTime3() = %v, want %v", got, tt.want)
			}
		})
	}
}
