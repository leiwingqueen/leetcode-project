package prefixSum

import "testing"

func Test_maxTotalFruits(t *testing.T) {
	type args struct {
		fruits   [][]int
		startPos int
		k        int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [[2,8],[6,3],[8,6]]
		//5
		//4
		{"t1", args{[][]int{
			{2, 8},
			{6, 3},
			{8, 6},
		}, 5, 4}, 9},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxTotalFruits(tt.args.fruits, tt.args.startPos, tt.args.k); got != tt.want {
				t.Errorf("maxTotalFruits() = %v, want %v", got, tt.want)
			}
		})
	}
}
