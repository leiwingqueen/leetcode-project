package array

import (
	"reflect"
	"testing"
)

func Test_minInterval(t *testing.T) {
	type args struct {
		intervals [][]int
		queries   []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		// [[1,4],[2,4],[3,6],[4,4]]
		//[2,3,4,5]
		{"t1", args{[][]int{
			{1, 4}, {2, 4}, {3, 6}, {4, 4},
		}, []int{2, 3, 4, 5}}, []int{3, 3, 1, 4}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minInterval(tt.args.intervals, tt.args.queries); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("minInterval() = %v, want %v", got, tt.want)
			}
		})
	}
}
