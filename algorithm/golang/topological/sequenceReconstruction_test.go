package topological

import "testing"

func Test_sequenceReconstruction(t *testing.T) {
	type args struct {
		nums      []int
		sequences [][]int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 3},
			[][]int{{1, 2}, {1, 3}, {2, 3}}}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := sequenceReconstruction(tt.args.nums, tt.args.sequences); got != tt.want {
				t.Errorf("sequenceReconstruction() = %v, want %v", got, tt.want)
			}
		})
	}
}
