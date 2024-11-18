package wc424

import "testing"

func Test_isZeroArray(t *testing.T) {
	type args struct {
		nums    []int
		queries [][]int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 0, 1}, [][]int{
			{0, 2},
		}}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := isZeroArray(tt.args.nums, tt.args.queries); got != tt.want {
				t.Errorf("isZeroArray() = %v, want %v", got, tt.want)
			}
		})
	}
}
