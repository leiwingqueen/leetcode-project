package bwc119

import "testing"

func Test_removeAlmostEqualCharacters(t *testing.T) {
	type args struct {
		word string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"aaaaa"}, 2},
		{"t2", args{"abddez"}, 2},
		{"t3", args{"zyxyxyz"}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := removeAlmostEqualCharacters(tt.args.word); got != tt.want {
				t.Errorf("removeAlmostEqualCharacters() = %v, want %v", got, tt.want)
			}
		})
	}
}
