package wc527

import "testing"

func Test_countSuffix(t *testing.T) {
	type args struct {
		nums   []int
		target []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// Add test cases.
		{"t1", args{[]int{3, 1, 2}, []int{1, 3, 2}}, 0},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countSuffix(tt.args.nums, tt.args.target); got != tt.want {
				t.Errorf("countSuffix() = %v, want %v", got, tt.want)
			}
		})
	}
}
