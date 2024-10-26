package dp

import "testing"

func Test_maxTotalReward6(t *testing.T) {
	type args struct {
		rewardValues []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 1, 3, 3}}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxTotalReward6(tt.args.rewardValues); got != tt.want {
				t.Errorf("maxTotalReward6() = %v, want %v", got, tt.want)
			}
		})
	}
}
