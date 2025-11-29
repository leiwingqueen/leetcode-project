package greedy

import "testing"

func Test_maxEvents(t *testing.T) {
	type args struct {
		events [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{1, 2}, {1, 2}, {3, 3}, {1, 5}, {1, 5},
		}}, 5},
		{"t2", args{[][]int{
			{1, 5}, {1, 5}, {1, 5}, {2, 3}, {2, 3},
		}}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxEvents2(tt.args.events); got != tt.want {
				t.Errorf("maxEvents() = %v, want %v", got, tt.want)
			}
		})
	}
}
