package wc332

import "testing"

func Test_findTheArrayConcVal(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{7, 52, 2, 4}}, 596},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findTheArrayConcVal(tt.args.nums); got != tt.want {
				t.Errorf("findTheArrayConcVal() = %v, want %v", got, tt.want)
			}
		})
	}
}
