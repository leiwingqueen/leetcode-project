package slidewindow

import "testing"

func Test_minimumScore(t *testing.T) {
	type args struct {
		s string
		t string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		//"eeecaeecdeeadcdbcaa"
		//"edecabe"
		{"t1", args{"eeecaeecdeeadcdbcaa", "edecabe"}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumScore(tt.args.s, tt.args.t); got != tt.want {
				t.Errorf("minimumScore() = %v, want %v", got, tt.want)
			}
		})
	}
}
