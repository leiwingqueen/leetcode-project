package array

import (
	"reflect"
	"testing"
)

func Test_buildArray2(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{
			0, 2, 1, 5, 3, 4,
		}}, []int{0, 1, 2, 4, 5, 3}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := buildArray2(tt.args.nums); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("buildArray2() = %v, want %v", got, tt.want)
			}
		})
	}
}
