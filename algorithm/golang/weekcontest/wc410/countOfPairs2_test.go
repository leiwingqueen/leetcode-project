package wc410

import "testing"

func Test_countOfPairs3(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{2, 3, 2}}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countOfPairs3(tt.args.nums); got != tt.want {
				t.Errorf("countOfPairs3() = %v, want %v", got, tt.want)
			}
		})
	}
}
