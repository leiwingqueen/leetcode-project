package math

import "testing"

func Test_validSquare(t *testing.T) {
	type args struct {
		p1 []int
		p2 []int
		p3 []int
		p4 []int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{0, 0}, []int{1, 1}, []int{1, 0}, []int{0, 1}}, true},
		{"t2", args{[]int{0, 0}, []int{0, 0}, []int{0, 0}, []int{0, 0}}, false},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := validSquare(tt.args.p1, tt.args.p2, tt.args.p3, tt.args.p4); got != tt.want {
				t.Errorf("validSquare() = %v, want %v", got, tt.want)
			}
		})
	}
}
