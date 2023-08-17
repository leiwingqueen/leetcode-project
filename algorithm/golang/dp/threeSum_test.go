package dp

import (
	"reflect"
	"testing"
)

func Test_threeSum2(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want [][]int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{0, 0, 0}}, [][]int{{0, 0, 0}}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := threeSum2(tt.args.nums); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("threeSum2() = %v, want %v", got, tt.want)
			}
		})
	}
}
