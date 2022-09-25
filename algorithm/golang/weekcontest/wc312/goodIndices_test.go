package wc312

import (
	"reflect"
	"testing"
)

func Test_goodIndices(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{878724, 201541, 179099, 98437, 35765, 327555, 475851, 598885, 849470, 943442}, 4}, []int{4, 5}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := goodIndices(tt.args.nums, tt.args.k); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("goodIndices() = %v, want %v", got, tt.want)
			}
		})
	}
}
