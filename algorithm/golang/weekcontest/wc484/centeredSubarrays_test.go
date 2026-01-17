package wc484

import "testing"

func Test_centeredSubarrays(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{-1, 1, 0}}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := centeredSubarrays(tt.args.nums); got != tt.want {
				t.Errorf("centeredSubarrays() = %v, want %v", got, tt.want)
			}
		})
	}
}
