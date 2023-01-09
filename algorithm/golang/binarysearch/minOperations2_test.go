package binarysearch

import "testing"

func Test_minOperations3(t *testing.T) {
	type args struct {
		nums []int
		x    int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [5,2,3,1,1]
		//5
		{"t1", args{[]int{5, 2, 3, 1, 1}, 5}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minOperations3(tt.args.nums, tt.args.x); got != tt.want {
				t.Errorf("minOperations3() = %v, want %v", got, tt.want)
			}
		})
	}
}
