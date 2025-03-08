package wc439

import "testing"

func Test_minCost(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{6, 2, 8, 4}}, 12},
		{"t2", args{[]int{7, 7, 10}}, 17},
		{"t3", args{[]int{12, 15, 1, 15, 18}}, 34},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minCost3(tt.args.nums); got != tt.want {
				t.Errorf("minCost() = %v, want %v", got, tt.want)
			}
		})
	}
}
