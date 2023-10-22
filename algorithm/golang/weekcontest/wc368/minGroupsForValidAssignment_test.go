package wc368

import "testing"

func Test_minGroupsForValidAssignment(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 1, 3, 3, 1, 1, 2, 2, 3, 1, 3, 2}}, 5},
		{"t2", args{[]int{10, 10, 10, 3, 1, 1}}, 4},
		{"t3", args{[]int{2, 3, 2, 2, 2}}, 3},
		{"t4", args{[]int{2, 1, 1, 2, 2, 3, 1, 3, 1, 1, 1, 1, 2}}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minGroupsForValidAssignment(tt.args.nums); got != tt.want {
				t.Errorf("minGroupsForValidAssignment() = %v, want %v", got, tt.want)
			}
		})
	}
}
