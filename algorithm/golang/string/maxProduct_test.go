package string

import "testing"

func Test_maxProduct3(t *testing.T) {
	type args struct {
		words []string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]string{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}}, 16},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxProduct3(tt.args.words); got != tt.want {
				t.Errorf("maxProduct3() = %v, want %v", got, tt.want)
			}
		})
	}
}
