package wc485

import "testing"

func Test_maxCapacity(t *testing.T) {
	type args struct {
		costs    []int
		capacity []int
		budget   int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{
			[]int{3, 5, 7, 4},
			[]int{2, 4, 3, 6},
			7,
		}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxCapacity(tt.args.costs, tt.args.capacity, tt.args.budget); got != tt.want {
				t.Errorf("maxCapacity() = %v, want %v", got, tt.want)
			}
		})
	}
}
