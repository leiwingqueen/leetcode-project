package wc328

import (
	"reflect"
	"testing"
)

func Test_rangeAddQueries2(t *testing.T) {
	type args struct {
		n       int
		queries [][]int
	}
	tests := []struct {
		name string
		args args
		want [][]int
	}{
		// TODO: Add test cases.
		{"t1", args{3, [][]int{
			{1, 1, 2, 2},
			{0, 0, 1, 1},
		}}, [][]int{{1, 1, 0}, {1, 2, 1}, {0, 1, 1}}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := rangeAddQueries2(tt.args.n, tt.args.queries); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("rangeAddQueries2() = %v, want %v", got, tt.want)
			}
		})
	}
}
