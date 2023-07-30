package wc356

import "testing"

func Test_countCompleteSubarrays(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 3, 1, 2, 2}}, 4},
		{"t2", args{[]int{459, 459, 962, 1579, 1435, 756, 1872, 1597}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countCompleteSubarrays(tt.args.nums); got != tt.want {
				t.Errorf("countCompleteSubarrays() = %v, want %v", got, tt.want)
			}
		})
	}
}
