package wc373

import (
	"reflect"
	"testing"
)

func Test_lexicographicallySmallestArray(t *testing.T) {
	type args struct {
		nums  []int
		limit int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 5, 3, 9, 8}, 2}, []int{1, 3, 5, 8, 9}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := lexicographicallySmallestArray(tt.args.nums, tt.args.limit); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("lexicographicallySmallestArray() = %v, want %v", got, tt.want)
			}
		})
	}
}
