package wc442

import "testing"

func Test_minTime2(t *testing.T) {
	type args struct {
		skill []int
		mana  []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 5, 2, 4}, []int{5, 1, 4, 2}}, 110},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minTime2(tt.args.skill, tt.args.mana); got != tt.want {
				t.Errorf("minTime2() = %v, want %v", got, tt.want)
			}
		})
	}
}
