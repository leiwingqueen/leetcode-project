package array

import "testing"

func Test_decodeMessage(t *testing.T) {
	type args struct {
		key     string
		message string
	}
	tests := []struct {
		name string
		args args
		want string
	}{
		// TODO: Add test cases.
		// "the quick brown fox jumps over the lazy dog"
		//"vkbs bs t suepuv"
		{"t1", args{"the quick brown fox jumps over the lazy dog", "vkbs bs t suepuv"}, "this is a secret"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := decodeMessage(tt.args.key, tt.args.message); got != tt.want {
				t.Errorf("decodeMessage() = %v, want %v", got, tt.want)
			}
		})
	}
}
