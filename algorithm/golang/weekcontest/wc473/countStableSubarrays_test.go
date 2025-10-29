package wc473

import "testing"

func Test_countStableSubarrays(t *testing.T) {
	type args struct {
		capacity []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{9, 3, 3, 3, 9}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countStableSubarrays3(tt.args.capacity); got != tt.want {
				t.Errorf("countStableSubarrays() = %v, want %v", got, tt.want)
			}
		})
	}
}
