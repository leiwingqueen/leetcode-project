package array

import (
	"reflect"
	"testing"
)

func Test_maximumBeauty(t *testing.T) {
	type args struct {
		items   [][]int
		queries []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		// [[1,2],[3,2],[2,4],[5,6],[3,5]]
		// [1,2,3,4,5,6]
		{"t1", args{[][]int{
			{1, 2}, {3, 2}, {2, 4}, {5, 6}, {3, 5},
		}, []int{
			1, 2, 3, 4, 5, 6,
		}}, []int{
			2, 4, 5, 5, 6, 6,
		}},
		// [[1,2],[1,2],[1,3],[1,4]]
		// 1
		{"t2", args{[][]int{
			{1, 2}, {1, 2}, {1, 3}, {1, 4},
		}, []int{1}}, []int{4}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumBeauty(tt.args.items, tt.args.queries); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("maximumBeauty() = %v, want %v", got, tt.want)
			}
		})
	}
}
