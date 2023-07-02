package wc352

import (
	"reflect"
	"testing"
)

func Test_findPrimePairs(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want [][]int
	}{
		// TODO: Add test cases.
		{"t1", args{10}, [][]int{
			{3, 7},
			{5, 5},
		}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findPrimePairs(tt.args.n); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("findPrimePairs() = %v, want %v", got, tt.want)
			}
		})
	}
}
