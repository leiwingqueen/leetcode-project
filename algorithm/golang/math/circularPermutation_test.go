package math

import (
	"reflect"
	"testing"
)

func Test_circularPermutation(t *testing.T) {
	type args struct {
		n     int
		start int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{2, 3}, []int{3, 2, 0, 1}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := circularPermutation(tt.args.n, tt.args.start); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("circularPermutation() = %v, want %v", got, tt.want)
			}
		})
	}
}
