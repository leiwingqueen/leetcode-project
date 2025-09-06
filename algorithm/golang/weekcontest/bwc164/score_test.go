package bwc164

import "testing"

func Test_score3(t *testing.T) {
	type args struct {
		cards []string
		x     byte
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]string{"aa", "ab", "ba", "ac"}, 'a'}, 2},
		{"t2", args{[]string{"cb", "ca", "cc"}, 'c'}, 1},
		{"t3", args{[]string{"bb", "bc", "cc", "cb", "bc", "bc", "aa", "cc", "ab", "ba"}, 'b'}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := score4(tt.args.cards, tt.args.x); got != tt.want {
				t.Errorf("score3() = %v, want %v", got, tt.want)
			}
		})
	}
}
