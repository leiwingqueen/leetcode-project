package prefixSum

import "testing"

func Test_waysToMakeFair(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{2, 1, 6, 4}}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := waysToMakeFair(tt.args.nums); got != tt.want {
				t.Errorf("waysToMakeFair() = %v, want %v", got, tt.want)
			}
		})
	}
}
