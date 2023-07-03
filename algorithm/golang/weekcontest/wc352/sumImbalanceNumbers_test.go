package wc352

import "testing"

func Test_sumImbalanceNumbers(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{
			2, 3, 1, 4,
		}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := sumImbalanceNumbers(tt.args.nums); got != tt.want {
				t.Errorf("sumImbalanceNumbers() = %v, want %v", got, tt.want)
			}
		})
	}
}
