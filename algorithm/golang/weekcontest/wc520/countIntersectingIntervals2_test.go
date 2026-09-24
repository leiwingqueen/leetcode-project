package wc520

import "testing"

func Test_countIntersectingIntervals2(t *testing.T) {
	type args struct {
		intervals [][]int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{1, 2}, {3, 4}, {5, 6},
		}}, 0},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countIntersectingIntervals2(tt.args.intervals); got != tt.want {
				t.Errorf("countIntersectingIntervals2() = %v, want %v", got, tt.want)
			}
		})
	}
}
