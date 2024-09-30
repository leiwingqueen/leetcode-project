package bwc140

import "testing"

func Test_maximumTotalSum(t *testing.T) {
	type args struct {
		maximumHeight []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{2, 2, 1}}, -1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumTotalSum(tt.args.maximumHeight); got != tt.want {
				t.Errorf("maximumTotalSum() = %v, want %v", got, tt.want)
			}
		})
	}
}
