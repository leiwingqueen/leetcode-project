package bwc84

import "testing"

func Test_taskSchedulerII(t *testing.T) {
	type args struct {
		tasks []int
		space int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{4, 10, 10, 9, 10, 4, 10, 4}, 8}, 30},
		{"t2", args{[]int{5, 8, 8, 3}, 2}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := taskSchedulerII(tt.args.tasks, tt.args.space); got != tt.want {
				t.Errorf("taskSchedulerII() = %v, want %v", got, tt.want)
			}
		})
	}
}
