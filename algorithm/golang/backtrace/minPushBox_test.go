package backtrace

import (
	"fmt"
	"testing"
)

func Test_minPushBox(t *testing.T) {
	type args struct {
		grid [][]byte
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [["#","#","#","#","#","#"],["#","T","#","#","#","#"],["#",".",".","B",".","#"],["#",".","#","#",".","#"],["#",".",".",".","S","#"],["#","#","#","#","#","#"]]
		{"t1", args{[][]byte{{'#', '#', '#', '#', '#', '#'}, {'#', 'T', '#', '#', '#', '#'}, {'#', '.', '.', 'B', '.', '#'}, {'#', '.', '#', '#', '.', '#'}, {'#', '.', '.', '.', 'S', '#'}, {'#', '#', '#', '#', '#', '#'}}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minPushBox(tt.args.grid); got != tt.want {
				t.Errorf("minPushBox() = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test(t *testing.T) {
	s := fmt.Sprintf("%d#%d#%d#%d", 1, 2, 3, 4)
	fmt.Println(s)
}
