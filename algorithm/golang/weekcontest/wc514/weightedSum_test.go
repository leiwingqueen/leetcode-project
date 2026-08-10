package wc514

import "testing"

func Test_weightedSum(t *testing.T) {
	type args struct {
		parent []int
		nums   []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{-1, 0, 0, 0, 2, 2}, []int{5, 2, 3, 1, 4, 6}}, 37},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := weightedSum(tt.args.parent, tt.args.nums); got != tt.want {
				t.Errorf("weightedSum() = %v, want %v", got, tt.want)
			}
		})
	}
}
