package wc337

import "testing"

func Test_findSmallestInteger(t *testing.T) {
	type args struct {
		nums  []int
		value int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [0,-3]
		//4
		{"t1", args{[]int{0, -3}, 4}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findSmallestInteger(tt.args.nums, tt.args.value); got != tt.want {
				t.Errorf("findSmallestInteger() = %v, want %v", got, tt.want)
			}
		})
	}
}
