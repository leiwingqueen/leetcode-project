package array

import (
	"reflect"
	"testing"
)

func Test_filterOccupiedIntervals(t *testing.T) {
	type args struct {
		occupiedIntervals [][]int
		freeStart         int
		freeEnd           int
	}
	tests := []struct {
		name string
		args args
		want [][]int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{2, 6}, {4, 8}, {10, 10}, {10, 12}, {14, 16},
		}, 7, 11}, [][]int{
			{2, 6}, {12, 12}, {14, 16},
		}},
		{"t2", args{[][]int{
			{1, 5}, {2, 3},
		}, 3, 8}, [][]int{
			{1, 2},
		}},
		{"t3", args{[][]int{
			{1, 3},
		}, 2, 2}, [][]int{
			{1, 1}, {3, 3},
		}},
		{"t4", args{
			[][]int{
				{28, 30}, {24, 26}, {40, 41}, {23, 40}, {64, 71},
			}, 69, 69,
		}, [][]int{
			{23, 41},
			{64, 68},
			{70, 71},
		}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := filterOccupiedIntervals(tt.args.occupiedIntervals, tt.args.freeStart, tt.args.freeEnd); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("filterOccupiedIntervals() = %v, want %v", got, tt.want)
			}
		})
	}
}
