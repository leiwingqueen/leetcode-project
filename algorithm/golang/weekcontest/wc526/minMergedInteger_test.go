package wc526

import "testing"

func Test_minMergedInteger(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{23, 55, 42, 21}}, 2355},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minMergedInteger(tt.args.nums); got != tt.want {
				t.Errorf("minMergedInteger() = %v, want %v", got, tt.want)
			}
		})
	}
}
