package backtrace

import "testing"

func Test_maxStudents(t *testing.T) {
	type args struct {
		seats [][]byte
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [[".","#","#","."],[".",".",".","#"],[".",".",".","."],["#",".","#","#"]]
		{"t1", args{[][]byte{
			{'.', '#', '#', '.'}, {'.', '.', '.', '#'}, {'.', '.', '.', '.'}, {'#', '.', '#', '#'},
		}}, 5},
		// [["#",".","#","#",".","#"],[".","#","#","#","#","."],["#",".","#","#",".","#"]]
		{"t2", args{[][]byte{
			{'#', '.', '#', '#', '.', '#'}, {'.', '#', '#', '#', '#', '.'}, {'#', '.', '#', '#', '.', '#'},
		}}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxStudents3(tt.args.seats); got != tt.want {
				t.Errorf("maxStudents() = %v, want %v", got, tt.want)
			}
		})
	}
}
