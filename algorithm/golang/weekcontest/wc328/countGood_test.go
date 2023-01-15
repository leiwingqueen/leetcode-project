package wc328

import "testing"

func Test_countGood(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 1, 1, 1, 1}, 10}, 1},
		// [3,1,4,3,2,2,4]
		//2
		{"t2", args{[]int{3, 1, 4, 3, 2, 2, 4}, 2}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countGood(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("countGood() = %v, want %v", got, tt.want)
			}
		})
	}
}
