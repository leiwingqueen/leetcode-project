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
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := filterOccupiedIntervals(tt.args.occupiedIntervals, tt.args.freeStart, tt.args.freeEnd); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("filterOccupiedIntervals() = %v, want %v", got, tt.want)
			}
		})
	}
}
