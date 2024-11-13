package dp

import (
	"reflect"
	"testing"
)

func Test_countKConstraintSubstrings(t *testing.T) {
	type args struct {
		s       string
		k       int
		queries [][]int
	}
	tests := []struct {
		name string
		args args
		want []int64
	}{
		// TODO: Add test cases.
		{"t1", args{"0001111", 2, [][]int{
			{0, 6},
		}}, []int64{26}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countKConstraintSubstrings(tt.args.s, tt.args.k, tt.args.queries); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("countKConstraintSubstrings() = %v, want %v", got, tt.want)
			}
		})
	}
}
