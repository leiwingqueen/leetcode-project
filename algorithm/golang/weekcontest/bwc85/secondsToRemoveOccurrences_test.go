package bwc85

import "testing"

func Test_secondsToRemoveOccurrences(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"0110101"}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := secondsToRemoveOccurrences2(tt.args.s); got != tt.want {
				t.Errorf("secondsToRemoveOccurrences() = %v, want %v", got, tt.want)
			}
		})
	}
}
