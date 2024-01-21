package wc381

import (
	"reflect"
	"testing"
)

func Test_countOfPairs(t *testing.T) {
	type args struct {
		n int
		x int
		y int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{5, 2, 4}, []int{10, 8, 2, 0, 0}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countOfPairs(tt.args.n, tt.args.x, tt.args.y); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("countOfPairs() = %v, want %v", got, tt.want)
			}
		})
	}
}
