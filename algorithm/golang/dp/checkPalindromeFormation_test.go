package dp

import "testing"

func Test_checkPalindromeFormation2(t *testing.T) {
	type args struct {
		a string
		b string
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{"x", "y"}, true},
		// "abdef"
		//"fecab"
		{"t2", args{"abdef", "fecab"}, true},
		// "pvhmupgqeltozftlmfjjde"
		//"yjgpzbezspnnpszebzmhvp"
		{"t3", args{"pvhmupgqeltozftlmfjjde", "yjgpzbezspnnpszebzmhvp"}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := checkPalindromeFormation2(tt.args.a, tt.args.b); got != tt.want {
				t.Errorf("checkPalindromeFormation2() = %v, want %v", got, tt.want)
			}
		})
	}
}
