package wc461

import "testing"

func Test_isTrionic(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 3, 5, 4, 2, 6}}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := isTrionic(tt.args.nums); got != tt.want {
				t.Errorf("isTrionic() = %v, want %v", got, tt.want)
			}
		})
	}
}
