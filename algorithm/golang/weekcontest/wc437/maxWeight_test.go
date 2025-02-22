package wc437

import "testing"

func Test_maxWeight(t *testing.T) {
	type args struct {
		pizzas []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{5, 2, 2, 4, 3, 3, 1, 3, 2, 5, 4, 2}}, 14},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxWeight2(tt.args.pizzas); got != tt.want {
				t.Errorf("maxWeight() = %v, want %v", got, tt.want)
			}
		})
	}
}
