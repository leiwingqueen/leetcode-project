package dp

import "testing"

func Test_splitArraySameAverage(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 3, 4, 5, 6, 7, 8}}, true},
		{"t2", args{[]int{3, 1}}, false},
		{"t3", args{[]int{1817, 3082, 8735, 9101, 2576, 3473, 9941, 5336, 8452, 2584, 2518, 3196, 1421, 8460, 6863, 6956, 3668, 17}}, false},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := splitArraySameAverage(tt.args.nums); got != tt.want {
				t.Errorf("splitArraySameAverage() = %v, want %v", got, tt.want)
			}
		})
	}
}
