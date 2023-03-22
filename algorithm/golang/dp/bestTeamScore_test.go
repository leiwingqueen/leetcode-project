package dp

import "testing"

func Test_bestTeamScore(t *testing.T) {
	type args struct {
		scores []int
		ages   []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [1,3,5,10,15]
		//[1,2,3,4,5]
		{"t1", args{[]int{1, 3, 5, 10, 15}, []int{1, 2, 3, 4, 5}}, 34},
		// [319776,611683,835240,602298,430007,574,142444,858606,734364,896074]
		//[1,1,1,1,1,1,1,1,1,1]
		{"t2", args{[]int{319776, 611683, 835240, 602298, 430007, 574, 142444, 858606, 734364, 896074},
			[]int{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}}, 5431066},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := bestTeamScore(tt.args.scores, tt.args.ages); got != tt.want {
				t.Errorf("bestTeamScore() = %v, want %v", got, tt.want)
			}
		})
	}
}
