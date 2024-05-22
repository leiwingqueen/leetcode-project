package wc398

import "testing"

func Test_sumDigitDifferences(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{13, 23, 12}}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := sumDigitDifferences(tt.args.nums); got != tt.want {
				t.Errorf("sumDigitDifferences() = %v, want %v", got, tt.want)
			}
		})
	}
}
