package wc406

import "testing"

func Test_minimumCost(t *testing.T) {
	type args struct {
		m             int
		n             int
		horizontalCut []int
		verticalCut   []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// 3
		//2
		//[1,3]
		//[5]
		{"t1", args{3, 2, []int{1, 3}, []int{5}}, 13},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumCost(tt.args.m, tt.args.n, tt.args.horizontalCut, tt.args.verticalCut); got != tt.want {
				t.Errorf("minimumCost() = %v, want %v", got, tt.want)
			}
		})
	}
}
