package wc345

import "testing"

func Test_maxMoves3(t *testing.T) {
	type args struct {
		grid [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{187, 167, 209, 251, 152, 236, 263, 128, 135},
			{267, 249, 251, 285, 73, 204, 70, 207, 74},
			{189, 159, 235, 66, 84, 89, 153, 111, 189},
			{120, 81, 210, 7, 2, 231, 92, 128, 218},
			{193, 131, 244, 293, 284, 175, 226, 205, 245},
		}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxMoves3(tt.args.grid); got != tt.want {
				t.Errorf("maxMoves3() = %v, want %v", got, tt.want)
			}
		})
	}
}
