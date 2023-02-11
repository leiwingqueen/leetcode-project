package array

import "testing"

func Test_fillCups2(t *testing.T) {
	type args struct {
		amount []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{5, 4, 4}}, 7},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := fillCups2(tt.args.amount); got != tt.want {
				t.Errorf("fillCups2() = %v, want %v", got, tt.want)
			}
		})
	}
}
