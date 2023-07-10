package wc353

import "testing"

func Test_checkArray2(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		// nums = [2,2,3,1,1,0], k = 3
		{"t1", args{[]int{2, 2, 3, 1, 1, 0}, 3}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := checkArray2(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("checkArray2() = %v, want %v", got, tt.want)
			}
		})
	}
}
