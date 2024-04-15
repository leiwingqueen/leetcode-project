package wc393

import "testing"

func Test_maximumPrimeDifference(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{4, 2, 9, 5, 3}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumPrimeDifference(tt.args.nums); got != tt.want {
				t.Errorf("maximumPrimeDifference() = %v, want %v", got, tt.want)
			}
		})
	}
}
