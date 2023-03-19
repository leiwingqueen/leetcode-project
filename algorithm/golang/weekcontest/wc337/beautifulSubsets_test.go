package wc337

import "testing"

func Test_beautifulSubsets2(t *testing.T) {
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
		// [95,96]
		//5
		{"t1", args{[]int{95, 96}, 5}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := beautifulSubsets3(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("beautifulSubsets2() = %v, want %v", got, tt.want)
			}
		})
	}
}
