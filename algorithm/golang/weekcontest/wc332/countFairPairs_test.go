package wc332

import "testing"

func Test_countFairPairs(t *testing.T) {
	type args struct {
		nums  []int
		lower int
		upper int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{0, 1, 7, 4, 4, 5}, 3, 6}, 6},
		// [1,7,9,2,5]
		//11
		//11
		{"t2", args{[]int{1, 7, 9, 2, 5}, 11, 11}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countFairPairs(tt.args.nums, tt.args.lower, tt.args.upper); got != tt.want {
				t.Errorf("countFairPairs() = %v, want %v", got, tt.want)
			}
		})
	}
}
