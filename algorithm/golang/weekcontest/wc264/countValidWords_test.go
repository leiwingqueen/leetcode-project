package wc264

import "testing"

func Test_countValidWords(t *testing.T) {
	type args struct {
		sentence string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"cat and  dog"}, 3},
		{"t2", args{"he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countValidWords(tt.args.sentence); got != tt.want {
				t.Errorf("countValidWords() = %v, want %v", got, tt.want)
			}
		})
	}
}
