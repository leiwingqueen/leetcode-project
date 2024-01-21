package wc381

import (
	"reflect"
	"testing"
)

func Test_countOfPairs2(t *testing.T) {
	type args struct {
		n int
		x int
		y int
	}
	tests := []struct {
		name string
		args args
		want []int64
	}{
		// TODO: Add test cases.
		{"t1", args{3, 1, 3}, []int64{6, 0, 0}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countOfPairs2(tt.args.n, tt.args.x, tt.args.y); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("countOfPairs2() = %v, want %v", got, tt.want)
			}
		})
	}
}
