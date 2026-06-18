package math

import "testing"

func Test_angleClock(t *testing.T) {
	type args struct {
		hour    int
		minutes int
	}
	tests := []struct {
		name string
		args args
		want float64
	}{
		{"t1", args{12, 30}, 165},
		{"t2", args{1, 57}, 76.5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := angleClock(tt.args.hour, tt.args.minutes); got != tt.want {
				t.Errorf("angleClock() = %v, want %v", got, tt.want)
			}
		})
	}
}
