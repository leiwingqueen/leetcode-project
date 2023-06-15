package array

import (
	"reflect"
	"testing"
)

func Test_canMakePaliQueries2(t *testing.T) {
	type args struct {
		s       string
		queries [][]int
	}
	tests := []struct {
		name string
		args args
		want []bool
	}{
		// TODO: Add test cases.
		{"t1", args{"abcda", [][]int{
			{3, 3, 0}, {1, 2, 0}, {0, 3, 1}, {0, 3, 2}, {0, 4, 1},
		}}, []bool{true, false, false, true, true}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := canMakePaliQueries2(tt.args.s, tt.args.queries); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("canMakePaliQueries2() = %v, want %v", got, tt.want)
			}
		})
	}
}
