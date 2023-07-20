package array

import "testing"

func Test_robotSim(t *testing.T) {
	type args struct {
		commands  []int
		obstacles [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [4,-1,4,-2,4]
		//[[2,4]]
		{"t1", args{[]int{
			4, -1, 4, -2, 4,
		}, [][]int{
			{2, 4},
		}}, 65},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := robotSim(tt.args.commands, tt.args.obstacles); got != tt.want {
				t.Errorf("robotSim() = %v, want %v", got, tt.want)
			}
		})
	}
}
