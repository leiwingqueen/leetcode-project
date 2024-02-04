package wc383

import "testing"

func Test_minimumTimeToInitialState(t *testing.T) {
	type args struct {
		word string
		k    int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"abacaba", 3}, 2},
		{"t2", args{"abacaba", 4}, 1},
		{"t3", args{"abcbabcd", 2}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumTimeToInitialState(tt.args.word, tt.args.k); got != tt.want {
				t.Errorf("minimumTimeToInitialState() = %v, want %v", got, tt.want)
			}
		})
	}
}
