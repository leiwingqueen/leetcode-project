package wc356

import "testing"

func Test_countSteppingNumbers(t *testing.T) {
	type args struct {
		low  string
		high string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"1", "11"}, 10},
		{"t2", args{"90", "101"}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countSteppingNumbers3(tt.args.low, tt.args.high); got != tt.want {
				t.Errorf("countSteppingNumbers() = %v, want %v", got, tt.want)
			}
		})
	}
}
