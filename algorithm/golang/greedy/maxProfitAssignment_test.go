package greedy

import "testing"

func Test_maxProfitAssignment(t *testing.T) {
	type args struct {
		difficulty []int
		profit     []int
		worker     []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"t1", args{[]int{2, 4, 6, 8, 10},
			[]int{10, 20, 30, 40, 50},
			[]int{4, 5, 6, 7}}, 100},
		{"t2", args{[]int{68, 35, 52, 47, 86},
			[]int{67, 17, 1, 81, 3},
			[]int{92, 10, 85, 84, 82}}, 324},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxProfitAssignment(tt.args.difficulty, tt.args.profit, tt.args.worker); got != tt.want {
				t.Errorf("maxProfitAssignment() = %v, want %v", got, tt.want)
			}
		})
	}
}
