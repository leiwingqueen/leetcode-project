package wc433

import "testing"

func Test_minMaxSums(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 3}, 2}, 24},
		{"t2", args{[]int{0, 1, 1}, 3}, 9},
		// k=1,[0],[1],[1],2
		// k=2,[0,1],[0,1],[0,1],[1,1],5
		// k=3,[0,1,1],2
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minMaxSums2(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("minMaxSums() = %v, want %v", got, tt.want)
			}
		})
	}
}
