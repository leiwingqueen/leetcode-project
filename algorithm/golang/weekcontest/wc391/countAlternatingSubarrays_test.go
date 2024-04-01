package wc391

import "testing"

func Test_countAlternatingSubarrays(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{0, 1, 1, 1}}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countAlternatingSubarrays(tt.args.nums); got != tt.want {
				t.Errorf("countAlternatingSubarrays() = %v, want %v", got, tt.want)
			}
		})
	}
}
