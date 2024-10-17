package wc418

import "testing"

func Test_maxGoodNumber(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 3}}, 30},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxGoodNumber(tt.args.nums); got != tt.want {
				t.Errorf("maxGoodNumber() = %v, want %v", got, tt.want)
			}
		})
	}
}
