package string

import "testing"

func Test_countTime(t *testing.T) {
	type args struct {
		time string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"??:?3"}, 144},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countTime(tt.args.time); got != tt.want {
				t.Errorf("countTime() = %v, want %v", got, tt.want)
			}
		})
	}
}
