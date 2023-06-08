package dp

import "testing"

func Test_miceAndCheese(t *testing.T) {
	type args struct {
		reward1 []int
		reward2 []int
		k       int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 1}, []int{1, 1}, 2}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := miceAndCheese(tt.args.reward1, tt.args.reward2, tt.args.k); got != tt.want {
				t.Errorf("miceAndCheese() = %v, want %v", got, tt.want)
			}
		})
	}
}
