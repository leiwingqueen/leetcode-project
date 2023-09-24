package wc364

import "testing"

func Test_maximumSumOfHeights2(t *testing.T) {
	type args struct {
		maxHeights []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		// [6,5,3,9,2,7]
		{"t1", args{[]int{6, 5, 3, 9, 2, 7}}, 22},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumSumOfHeights2(tt.args.maxHeights); got != tt.want {
				t.Errorf("maximumSumOfHeights2() = %v, want %v", got, tt.want)
			}
		})
	}
}
