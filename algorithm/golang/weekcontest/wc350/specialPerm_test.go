package wc350

import "testing"

func Test_specialPerm2(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{2, 3, 6}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := specialPerm3(tt.args.nums); got != tt.want {
				t.Errorf("specialPerm2() = %v, want %v", got, tt.want)
			}
		})
	}
}
