package wc429

import "testing"

func Test_maxDistinctElements(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 2, 3, 3, 4}, 2}, 6},
		{"t2", args{[]int{4, 4, 4, 4}, 1}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxDistinctElements(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("maxDistinctElements() = %v, want %v", got, tt.want)
			}
		})
	}
}
