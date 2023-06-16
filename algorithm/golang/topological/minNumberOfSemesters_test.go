package topological

import "testing"

func Test_minNumberOfSemesters(t *testing.T) {
	type args struct {
		n         int
		relations [][]int
		k         int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// 4
		//[[2,1],[3,1],[1,4]]
		//2
		{"t1", args{4, [][]int{
			{2, 1}, {3, 1}, {1, 4},
		}, 2}, 3},
		// [[2,1],[3,1],[4,1],[1,5]]
		//			2
		{"t2", args{5, [][]int{
			{2, 1}, {3, 1}, {4, 1}, {1, 5},
		}, 2}, 4},
		// 14
		//			[[11,7]]
		//			2
		{"t3", args{14, [][]int{
			{11, 7},
		}, 2}, 7},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minNumberOfSemesters2(tt.args.n, tt.args.relations, tt.args.k); got != tt.want {
				t.Errorf("minNumberOfSemesters() = %v, want %v", got, tt.want)
			}
		})
	}
}
