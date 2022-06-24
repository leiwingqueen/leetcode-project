package interview

import (
	"reflect"
	"testing"
)

func Test_findBigger(t *testing.T) {
	type args struct {
		arr []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"test1", args{[]int{1, 5, 3, 6, 4, 8, 9, 10}},
			[]int{5, 6, 6, 8, 8, 9, 10, -1}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findBigger(tt.args.arr); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("findBigger() = %v, want %v", got, tt.want)
			}
		})
	}
}
