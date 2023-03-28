package dp

import "testing"

func Test_shortestCommonSupersequence3(t *testing.T) {
	type args struct {
		str1 string
		str2 string
	}
	tests := []struct {
		name string
		args args
		want string
	}{
		// TODO: Add test cases.
		// "aabbabaa"
		//"aabbbbbbaa"
		{"t1", args{"aabbabaa",
			"aabbbbbbaa"}, "aabbabbbbaa"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := shortestCommonSupersequence3(tt.args.str1, tt.args.str2); got != tt.want {
				t.Errorf("shortestCommonSupersequence3() = %v, want %v", got, tt.want)
			}
		})
	}
}
