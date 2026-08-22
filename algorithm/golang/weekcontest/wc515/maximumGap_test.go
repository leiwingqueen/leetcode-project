package wc515

import "testing"

func Test_maximumGap(t *testing.T) {
	type args struct {
		skill   string
		station string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"ba", "bacc"}, 1},
		{"t2", args{"caa", "acaa"}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumGap(tt.args.skill, tt.args.station); got != tt.want {
				t.Errorf("maximumGap() = %v, want %v", got, tt.want)
			}
		})
	}
}
