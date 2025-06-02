package wc452

import (
	"reflect"
	"testing"
)

func Test_minAbsDiff(t *testing.T) {
	type args struct {
		grid [][]int
		k    int
	}
	tests := []struct {
		name string
		args args
		want [][]int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{{3, -1}}, 1}, [][]int{{0, 0}}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minAbsDiff(tt.args.grid, tt.args.k); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("minAbsDiff() = %v, want %v", got, tt.want)
			}
		})
	}
}
