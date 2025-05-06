package wc448

import (
	"reflect"
	"testing"
)

func Test_specialGrid(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want [][]int
	}{
		// TODO: Add test cases.
		{"t1", args{1}, [][]int{
			{3, 0},
			{2, 1},
		}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := specialGrid(tt.args.n); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("specialGrid() = %v, want %v", got, tt.want)
			}
		})
	}
}
