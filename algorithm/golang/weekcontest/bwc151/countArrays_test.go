package wc439

import "testing"

func Test_countArrays(t *testing.T) {
	type args struct {
		original []int
		bounds   [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 3, 4}, [][]int{
			{1, 10}, {2, 9}, {3, 8}, {4, 7},
		}}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countArrays(tt.args.original, tt.args.bounds); got != tt.want {
				t.Errorf("countArrays() = %v, want %v", got, tt.want)
			}
		})
	}
}
