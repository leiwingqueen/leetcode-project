package hash

import "testing"

func Test_numMatchingSubseq(t *testing.T) {
	type args struct {
		s     string
		words []string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// "abcde"
		//["a","bb","acd","ace"]
		{"t1", args{"abcde", []string{"a", "bb", "acd", "ace"}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numMatchingSubseq(tt.args.s, tt.args.words); got != tt.want {
				t.Errorf("numMatchingSubseq() = %v, want %v", got, tt.want)
			}
		})
	}
}
