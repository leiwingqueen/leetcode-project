package dp

import "testing"

func Test_findLUSlength2(t *testing.T) {
	type args struct {
		a string
		b string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"t1", args{"aaa", "aaa"}, -1},
		// TODO: Add test cases.
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findLUSlength2(tt.args.a, tt.args.b); got != tt.want {
				t.Errorf("findLUSlength2() = %v, want %v", got, tt.want)
			}
		})
	}
}
