package array

import "testing"

func Test_getWinner(t *testing.T) {
	type args struct {
		arr []int
		k   int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{2, 1, 3, 5, 4, 6, 7}, 2}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := getWinner(tt.args.arr, tt.args.k); got != tt.want {
				t.Errorf("getWinner() = %v, want %v", got, tt.want)
			}
		})
	}
}
