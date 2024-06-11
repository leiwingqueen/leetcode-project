package wc401

import "testing"

func Test_maxTotalReward2(t *testing.T) {
	type args struct {
		rewardValues []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 6, 4, 3, 2}}, 11},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxTotalReward2(tt.args.rewardValues); got != tt.want {
				t.Errorf("maxTotalReward2() = %v, want %v", got, tt.want)
			}
		})
	}
}
