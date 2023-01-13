package wc327

import "testing"

func Test_maxKelements(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 10, 3, 3, 3},
			3}, 17},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxKelements(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("maxKelements() = %v, want %v", got, tt.want)
			}
		})
	}
}
