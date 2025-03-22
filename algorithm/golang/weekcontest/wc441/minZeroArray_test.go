package wc441

import "testing"

func Test_minZeroArray(t *testing.T) {
	type args struct {
		nums    []int
		queries [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{2, 0, 2}, [][]int{
			{0, 2, 1}, {0, 2, 1}, {1, 1, 3},
		}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minZeroArray(tt.args.nums, tt.args.queries); got != tt.want {
				t.Errorf("minZeroArray() = %v, want %v", got, tt.want)
			}
		})
	}
}
