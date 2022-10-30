package bwc90

import (
	"reflect"
	"testing"
)

func Test_secondGreaterElement2(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		// [2,4,0,9,6]
		{"t1", args{[]int{2, 4, 0, 9, 6}}, []int{9, 6, 6, -1, -1}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := secondGreaterElement2(tt.args.nums); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("secondGreaterElement2() = %v, want %v", got, tt.want)
			}
		})
	}
}
