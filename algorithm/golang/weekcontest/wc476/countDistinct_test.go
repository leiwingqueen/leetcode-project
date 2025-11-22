package wc476

import "testing"

func Test_countDistinct(t *testing.T) {
	type args struct {
		n int64
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{10}, 9},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countDistinct(tt.args.n); got != tt.want {
				t.Errorf("countDistinct() = %v, want %v", got, tt.want)
			}
		})
	}
}
