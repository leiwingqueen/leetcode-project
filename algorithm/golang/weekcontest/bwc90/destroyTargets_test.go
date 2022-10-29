package bwc90

import "testing"

func Test_destroyTargets(t *testing.T) {
	type args struct {
		nums  []int
		space int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		//[1,3,5,2,4,6]
		//2
		{"t1", args{[]int{1, 3, 5, 2, 4, 6}, 2}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := destroyTargets(tt.args.nums, tt.args.space); got != tt.want {
				t.Errorf("destroyTargets() = %v, want %v", got, tt.want)
			}
		})
	}
}
