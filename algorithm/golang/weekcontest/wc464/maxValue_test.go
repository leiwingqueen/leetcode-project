package wc464

import (
	"reflect"
	"testing"
)

func Test_maxValue(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		// 11,18,11
		{"t1", args{[]int{11, 18, 11}}, []int{11, 18, 18}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxValue(tt.args.nums); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("maxValue() = %v, want %v", got, tt.want)
			}
		})
	}
}
