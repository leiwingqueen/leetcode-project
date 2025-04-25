package wc446

import (
	"reflect"
	"testing"
)

func Test_resultArray(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want []int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 3, 4, 5}, 3}, []int64{9, 2, 4}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := resultArray(tt.args.nums, tt.args.k); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("resultArray() = %v, want %v", got, tt.want)
			}
		})
	}
}
