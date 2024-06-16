package wc402

import "testing"

func Test_countCompleteDayPairs2(t *testing.T) {
	type args struct {
		hours []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{72, 48, 24, 3}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countCompleteDayPairs2(tt.args.hours); got != tt.want {
				t.Errorf("countCompleteDayPairs2() = %v, want %v", got, tt.want)
			}
		})
	}
}
