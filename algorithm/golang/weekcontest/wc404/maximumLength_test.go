package wc404

import "testing"

func Test_maximumLength(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 1, 1, 2, 1, 2}}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumLength(tt.args.nums); got != tt.want {
				t.Errorf("maximumLength() = %v, want %v", got, tt.want)
			}
		})
	}
}
