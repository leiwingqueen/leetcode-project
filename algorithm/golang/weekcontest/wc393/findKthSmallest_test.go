package wc393

import "testing"

func Test_findKthSmallest(t *testing.T) {
	type args struct {
		coins []int
		k     int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		{"t1", args{[]int{3, 6, 9}, 3}, 9},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findKthSmallest(tt.args.coins, tt.args.k); got != tt.want {
				t.Errorf("findKthSmallest() = %v, want %v", got, tt.want)
			}
		})
	}
}
